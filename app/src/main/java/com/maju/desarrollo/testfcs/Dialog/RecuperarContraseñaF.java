package com.maju.desarrollo.testfcs.Dialog;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.ServiceClass.RecuperarContrasena;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecuperarContraseñaF extends DialogFragment {
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    InternetandVPN validaciones = new InternetandVPN();

    public RecuperarContraseñaF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_recuperar_contrasena, container, false);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        final EditText correoUsuario = (EditText) v.findViewById(R.id.correoRecu);
        Button enviar = (Button) v.findViewById(R.id.botonRec);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(validaciones.webservise()) {
                        if (validaciones.vpnOn()){
                            if(validaciones.emailOk(correoUsuario.getText().toString())){
                                RecuperarContrasena item = new RecuperarContrasena();
                                String cor = correoUsuario.getText().toString();
                                item.setEmail(cor);

                                compositeDisposable.add(iMyAPI.recuperarContraseña(item).subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new Consumer<RecuperarContrasena>() {
                                            @Override
                                            public void accept(RecuperarContrasena lista) throws Exception {
                                                if(lista.getExitoso().equals("1")) {
                                                    Bundle valores = new Bundle();
                                                    valores.putString("titulo","Importante");
                                                    valores.putString("mensaje","Se envio contraseña a correo electrónico.");

                                                    AlertaGenerica alerta = new AlertaGenerica();
                                                    alerta.setArguments(valores);
                                                    alerta.setCancelable(false);
                                                    alerta.show(getFragmentManager(), "a");

                                                    dismiss();

                                                }else{
                                                    Bundle valores = new Bundle();
                                                    valores.putString("titulo","Importante");
                                                    valores.putString("mensaje","El usuario mencionado no existe." + lista.getError());

                                                    AlertaGenerica alerta = new AlertaGenerica();
                                                    alerta.setArguments(valores);
                                                    alerta.setCancelable(false);
                                                    alerta.show(getFragmentManager(), "a");
                                                }
                                            }
                                        }, new Consumer<Throwable>() {
                                            @Override
                                            public void accept(Throwable throwable) throws Exception {
                                                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();

                                            }
                                        })

                                );

                            }
                            else {
                                Bundle valores = new Bundle();
                                valores.putString("titulo","Importante");
                                valores.putString("mensaje","Usuario no cumple con formato.");

                                AlertaGenerica alerta = new AlertaGenerica();
                                alerta.setArguments(valores);
                                alerta.setCancelable(false);
                                alerta.show(getFragmentManager(), "a");
                            }
                        }
                        else{
                            Bundle valores = new Bundle();
                            valores.putString("titulo","Importante");
                            valores.putString("mensaje","Es necesaria la conxión a la VPN");

                            AlertaGenerica alerta = new AlertaGenerica();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");
                        }

                    }
                    else {
                        Bundle valores = new Bundle();
                        valores.putString("titulo","Importante");
                        valores.putString("mensaje","No se encontro conexión a internet, intentalo nuevamente.");

                        AlertaGenerica alerta = new AlertaGenerica();
                        alerta.setArguments(valores);
                        alerta.setCancelable(false);
                        alerta.show(getFragmentManager(), "a");
                    }
                     } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthActivityPhone = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        int alertW = (int)(.95 * widthActivityPhone);


        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = alertW;
        window.setAttributes(params);
    }

}
