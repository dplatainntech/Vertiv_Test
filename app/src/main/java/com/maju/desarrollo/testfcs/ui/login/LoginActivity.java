package com.maju.desarrollo.testfcs.ui.login;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Dialog.RecuperarContraseñaF;
import com.maju.desarrollo.testfcs.Inicio;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    ProgressBar loadingProgressBar;
    OperacionesDB datos;
     EditText usernameEditText;
     EditText passwordEditText;
     TextView recuperar;
    Button loginButton;
    UsuarioD usuario;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginViewModel = ViewModelProviders.of(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        getSupportActionBar().hide();

        //Activas las 2 lineas para eliminar todo del dispositivo
        //getApplicationContext().deleteDatabase("transfer.db");
        //datos= OperacionsBaseDatos.obtenerInstancia(getApplicationContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        datos = OperacionesDB.obtenerInstancia(getApplicationContext());
        usuario = datos.obtenerUsuario();

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        loadingProgressBar = findViewById(R.id.loading);
        recuperar = findViewById(R.id.recuperarCaoontra);

        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecuperarContraseñaF alerta = new RecuperarContraseñaF();
                alerta.setCancelable(true);
                alerta.show(getSupportFragmentManager(), "a");
            }
        });


        loginViewModel.getLoginFormState().observe(this, new Observer<LoginFormState>() {
            @Override
            public void onChanged(@Nullable LoginFormState loginFormState) {
                if (loginFormState == null) {
                    return;
                }
                loginButton.setEnabled(loginFormState.isDataValid());
                if (loginFormState.getUsernameError() != null) {
                    usernameEditText.setError(getString(loginFormState.getUsernameError()));
                }
                if (loginFormState.getPasswordError() != null) {
                    passwordEditText.setError(getString(loginFormState.getPasswordError()));
                }
            }
        });

        loginViewModel.getLoginResult().observe(this, new Observer<LoginResult>() {
            @Override
            public void onChanged(@Nullable LoginResult loginResult) {
                if (loginResult == null) {
                    return;
                }
                loadingProgressBar.setVisibility(View.GONE);
                if (loginResult.getError() != null) {
                    showLoginFailed(loginResult.getError());
                }
                if (loginResult.getSuccess() != null) {
                    //updateUiWithUser(loginResult.getSuccess());
                    ingrezar();
                }
                setResult(Activity.RESULT_OK);

                //Complete and destroy login activity once successful
                //finish();
            }
        });

        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                loginViewModel.loginDataChanged(usernameEditText.getText().toString(),
                        passwordEditText.getText().toString());
            }
        };
        usernameEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.addTextChangedListener(afterTextChangedListener);
        passwordEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    loginViewModel.login(usernameEditText.getText().toString(),
                            passwordEditText.getText().toString());
                }
                return false;
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!usernameEditText.getText().toString().isEmpty()&&
                        !passwordEditText.getText().toString().isEmpty()) {
                ingrezar();}else {
                    Toast.makeText(getApplicationContext(), "Es necesario colocar usuario y contraseña.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    public void ingrezar() {
        InternetandVPN internetandVPN = new InternetandVPN();

        try {
            if(internetandVPN.webservise()) {
                if(internetandVPN.vpnOn()) {
                 loadingProgressBar.setVisibility(View.VISIBLE);
                                            /*loginViewModel.login(usernameEditText.getText().toString(),
                                            passwordEditText.getText().toString());*/

                //if(usuario.getID_USER().equals(iduserService)){}
                Boolean ok = serviceLogin(usernameEditText.getText().toString(),passwordEditText.getText().toString());
                if(ok){

                }
               }
                else{
                      Toast.makeText(getApplicationContext(), "Es necesaria la conecciona por VPN", Toast.LENGTH_LONG).show();
                       }
            }
            else{
                Toast.makeText(getApplicationContext(), "No se tiene acceso a Internet", Toast.LENGTH_LONG).show();
                }
        } catch (InterruptedException e) {
             e.printStackTrace();
        } catch (IOException e) {
              e.printStackTrace();
        }
    }


    public Boolean serviceLogin(String a, String b){
        final Boolean[] avanzar = {true};
        final String ok = "1";
        Login item = new Login();
        item.setUsuario(a);
        item.setContraseña(b);

        compositeDisposable.add(iMyAPI.Login(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Login>() {
                    @Override
                    public void accept(Login item) throws Exception {
                        if(item.getExitoso().equals("1") || item.getExitoso().equals("4")){
                            if (usuario.getID_USER().equals(item.getIdUsuario().toString())) {
                                datos.CambioSesion(usuario, "ACTIVA");
                            } else {
                                datos.borrarTablas();
                                UsuarioD usuTemporal = new UsuarioD(
                                        item.getIdUsuario(), usernameEditText.getText().toString(),
                                        " ", " ", " ", usernameEditText.getText().toString()
                                        , passwordEditText.getText().toString(), item.getPais(), item.getPaisDescripcion()

                                );
                                datos.iniciioSesion(usuTemporal);
                            }

                            //Intent siguiente2 = new Intent(LoginActivity.this, MainActivity.class);
                            Intent siguiente2 = new Intent(LoginActivity.this, Inicio.class);
                            if(item.getExitoso().equals("4")){
                            siguiente2.putExtra("OtraPantalla", "");
                            siguiente2.putExtra("valorPaso", "");}
                            else{
                                siguiente2.putExtra("OtraPantalla", "");
                                siguiente2.putExtra("valorPaso", "");
                            }

                            startActivity(siguiente2);
                            overridePendingTransition(R.anim.left_in, R.anim.left_out);
                        }
                        else{
                            avanzar[0] = false;
                            alerta(item.getError());
                        }



                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //Toast.makeText(this(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        alerta(throwable.getMessage());
                    }
                })

        );
        return avanzar[0];
    }

    public void alerta(String mensaje){
        Bundle valores = new Bundle();
        valores.putString("titulo","Importante");
        valores.putString("mensaje",mensaje);

        AlertaGenerica alerta = new AlertaGenerica();
        alerta.setArguments(valores);
        alerta.setCancelable(false);
        alerta.show(getSupportFragmentManager(), "a");

    }

}
