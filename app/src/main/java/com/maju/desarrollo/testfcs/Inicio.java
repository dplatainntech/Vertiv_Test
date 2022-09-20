package com.maju.desarrollo.testfcs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatBestel;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatEstado;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatRegion;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ServiceClass.Vigencia;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Inicio extends AppCompatActivity {

    OperacionesDB D_B;
    UsuarioD usuario = null;
    String Sesion="";
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    InternetandVPN internetandVPN = new InternetandVPN();
    int tiempoEspera = 1000;
    private static final String TAG = "INICIO";
    boolean finalUsuVigente;
    SharedPreferences direcorio;
    String FechahoraSynk;
    String MensajeError="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        getSupportActionBar().hide();
        boolean UsuVigente = true;

        D_B = OperacionesDB.obtenerInstancia(getApplication());
        usuario = D_B.obtenerUsuario();
        Sesion = D_B.sesionActiva();

        //region colocar version app
        direcorio =  getSharedPreferences(getString(R.string.pref_file),Context.MODE_PRIVATE);
        direcorio.edit().putInt("version_app", BuildConfig.VERSION_CODE).apply();

        direcorio = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
        int Version = direcorio.getInt("version_app",0);

        //Toast.makeText(getApplicationContext(),"Version: "+ Version,Toast.LENGTH_LONG).show();


        //endregion


        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        ProgressBar barra =  (ProgressBar) findViewById(R.id.progressBar);

        barra.getIndeterminateDrawable()
                .setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Log.i(TAG, "Previo a validacion");
        if(Sesion.equals("ACTIVA")) {
            if(sincronizarporFacha()) {
                //region Sincronizar
                String fechaString =  dateFormat.format(new Date());
                direcorio =  getSharedPreferences(getString(R.string.pref_file),Context.MODE_PRIVATE);
                direcorio.edit().putString("fecha_sinc",fechaString).apply();

                tiempoEspera = 7000;
                Log.i(TAG, "entro a validacion");
                try {
                    if (internetandVPN.webservise()) {
                        Log.i(TAG, "Con internet");
                        if(internetandVPN.vpnOn()) {
                            if (activo()) {
                                Log.i(TAG, "Activo 1");
                                //sinc1();
                                //UsuVigente = consultarvigencia();
                            }
                            else {
                                D_B.CambioSesion(usuario, "INACTIVO");

                                Intent siguiente2 = new Intent(Inicio.this, LoginActivity.class);
                                startActivity(siguiente2);
                                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Es necesaria la conecciona por VPN", Toast.LENGTH_LONG).show();
                        }
                    }
                } catch (InterruptedException e) {
                    Log.i(TAG, "Error 1");
                    e.printStackTrace();

                } catch (IOException e) {
                    Log.i(TAG, "Error 2");
                    e.printStackTrace();
                }
            /*Intent siguiente2 = new Intent(Inicio.this, MainActivity.class);
            siguiente2.putExtra("OtraPantalla", "");
            siguiente2.putExtra("valorPaso", "");
            startActivity(siguiente2);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);

             */
                //endregion
            }else{
                Intent siguiente2 = new Intent(Inicio.this, MainActivity.class);
                siguiente2.putExtra("OtraPantalla","" );
                siguiente2.putExtra("valorPaso", "");
                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }

        }
        else{
            sinc7();
        }

      /*  final boolean finalUsuVigente = UsuVigente;
        new Handler().postDelayed(new Runnable(){
            public void run(){

                finish();
            }
        }, tiempoEspera);
*/





    }

    public  boolean activo(){
        final Boolean[] avanzar = {true};
        final String ok = "1";
        Login item = new Login();
        item.setUsuario(usuario.getCORREO());
        item.setContraseña(usuario.getCONTRASEÑA());

        compositeDisposable.add(iMyAPI.Login(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Login>() {
                    @Override
                    public void accept(Login item) throws Exception {
                        if(item.getExitoso().equals("1")){
                            finalUsuVigente = true;
                            Log.i(TAG, "Activo ok");
                          sinc1();
                        }else{
                            finalUsuVigente = false;
                            MensajeError = item.getError();
                            Log.i(TAG, "Inactivo u otro: " + item.getError());
                            sinc7();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //Toast.makeText(this(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        //alerta(throwable.getMessage());
                    }
                })
        );
        return avanzar[0];



    }

    public boolean consultarvigencia(){
        final boolean[] vigente = {false};
        Vigencia datos = new Vigencia();
        datos.setIdUsuario(usuario.getID_USER());

        compositeDisposable.add(iMyAPI.vigenciaUsuario(datos).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Vigencia>() {
                    @Override
                    public void accept(Vigencia lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        if (lista.getExitoso().equals("1")) {
                            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                            Date strDate = sdf.parse(lista.getFechaVigencia());
                            if (new Date().after(strDate)) {
                                vigente[0] = false;
                                Log.i(TAG, "Activo false");
                            }else{
                                vigente[0] = true;
                                Log.i(TAG, "Activo true");
                            }

                            // 5/13/2020 12:00:00 AM
                            //alerta("No se obtuvieron Folios Pre Trabajo.");
                        } else {
                            //D_B.guardarFoliosPT(lista);

                            //Toast.makeText(this, "Folios guardados", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );

        return vigente[0];
    }

    public void sinc1(){
        //region SINC FOLIOS PRE TRABAJO
        Log.i(TAG, "Sink 1");
        D_B.newMensaje("SINCRONIZACION DE FOLIOS PRE-TRABAJO");
        D_B.actualizarFechaSinc();
        CatFolios task = new CatFolios();
        task.setUsuario(usuario.getID_USER());
        task.setExitoso("0");
        D_B.borrarTablasSinc("folios");
        task.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.obtenerfoliosPT(task).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatFolios>>() {
                    @Override
                    public void accept(List<CatFolios> lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        if (lista.size() == 0) {
                            //alerta("No se obtuvieron Folios Pre Trabajo.");
                            sinc2();
                        } else {
                            D_B.guardarFoliosPT(lista);
                                sinc2();
                            //Toast.makeText(getContext(), "Folios guardados", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sinc2();
                    }
                })

        );


        //endregion
    }
    public void sinc2(){
        Log.i(TAG, "Sink 2");
        //region SINC DIRECCIONES
        CatEstado catEstado = new CatEstado();
        catEstado.setExitoso("1");
        sinc3();
        //endregion
    }
    public void sinc3(){
        //region SINC TASX
        Log.i(TAG, "Sink 3");
        CatAsignaCliente taskT = new CatAsignaCliente();
        taskT.setEmail(usuario.getCORREO());

        D_B.borrarTablasSinc("tareas");
        D_B.newMensaje("SINCRONIZACION DE ASIGNACIONES");
        D_B.actualizarFechaSinc();
        taskT.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.obtenerTask(taskT).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatAsignaCliente>>() {
                    @Override
                    public void accept(List<CatAsignaCliente> lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        //Toast.makeText(getContext(), lista.get(0).getItem_Number().toString(), Toast.LENGTH_SHORT).show();

                        D_B.guardarTask(lista);
                        sinc4();
                        Log.i(TAG, "Tareas");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sinc4();
                    }
                })

        );

        //endregion
    }
    public void sinc4(){
        //region SINC CLIENTES
        Log.i(TAG, "Sink 4");
        CatCliente cliente = new CatCliente();
        D_B.borrarTablasSinc("clientes");
        D_B.newMensaje("SINCRONIZACION DE CATALOGO DE CLIENTES");
        D_B.actualizarFechaSinc();
        cliente.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.obtenerClientes(cliente).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatCliente>>() {
                    @Override
                    public void accept(List<CatCliente> lista) throws Exception {
                        D_B.guardarCLientes(lista);
                        sinc5();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sinc5();
                    }
                })

        );

        //endregion
    }
    public void sinc5(){
        //region SINC BESTEL
        Log.i(TAG, "Sink 5");
        CatBestel bestelC = new CatBestel();

        D_B.borrarTablasSinc("bestel");
        D_B.newMensaje("SINCRONIZACION DE DIRECCIONES BESTEL");
        D_B.actualizarFechaSinc();
        compositeDisposable.add(iMyAPI.obtenerDireccionesBestel(bestelC).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatBestel>>() {
                    @Override
                    public void accept(List<CatBestel> lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        D_B.guardardireccionesBestel(lista);
                        sinc6();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sinc6();
                    }
                })

        );

        //endregion
    }
    public void sinc6(){
        //region Catalogo direcciones
        Log.i(TAG, "Sink 6");
        CatEstado catEst = new CatEstado();
        D_B.borrarTablasSinc("estados");
        D_B.actualizarFechaSinc();
        catEst.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.catEstado(catEst).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatEstado>>() {
                    @Override
                    public void accept(List<CatEstado> lista) throws Exception {
                        D_B.guardarestados(lista);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );

        CatRegion task2 = new CatRegion();

        D_B.borrarTablasSinc("regiones");
        D_B.actualizarFechaSinc();
        task2.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.catRegiones(task2).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<CatRegion>>() {
                    @Override
                    public void accept(List<CatRegion> lista) throws Exception {
                        D_B.guardarregiones(lista);
                        sinc7();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        sinc7();
                    }
                })

        );

        //endregion
    }
    public void sinc7(){
        Log.i(TAG, "Sink 7");
        if(Sesion.equals("ACTIVA")) {
            String pantalla = "";
            if(!finalUsuVigente) { pantalla = "ContraseñaObligatorio"; }
            Intent siguiente2 = new Intent(Inicio.this, MainActivity.class);
            siguiente2.putExtra("OtraPantalla", pantalla);
            siguiente2.putExtra("valorPaso", MensajeError);
            startActivity(siguiente2);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);

        }else{
            Intent siguiente2 = new Intent(Inicio.this, LoginActivity.class);
            startActivity(siguiente2);
            overridePendingTransition(R.anim.left_in, R.anim.left_out);
        }
    }

    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
    public boolean sincronizarporFacha(){
        boolean ok = true;
        direcorio = getSharedPreferences(getString(R.string.pref_file), Context.MODE_PRIVATE);
        FechahoraSynk = direcorio.getString("fecha_sinc",null);

        if(FechahoraSynk != null) {
            //Toast.makeText(getApplicationContext(), "Fecha S : " + FechahoraSynk, Toast.LENGTH_LONG).show();
            Date Fecha_actual = new Date();
            int dias = 0;
            try {
                dias = (int) ((Fecha_actual.getTime() - dateFormat.parse(FechahoraSynk).getTime()) / 86400000);
                if(dias<=0){
                    ok=false;
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else{
            String fechaString =  dateFormat.format(new Date());
            direcorio =  getSharedPreferences(getString(R.string.pref_file),Context.MODE_PRIVATE);
            direcorio.edit().putString("fecha_sinc",fechaString).apply();
        }

        return ok;
    }
}


/*
 if(internetandVPN.webservise()) {
 if(internetandVPN.vpnOn()) {  }
                        else{
                            Toast.makeText(getApplicationContext(), "Es necesaria la conecciona por VPN", Toast.LENGTH_LONG).show();
                        }
 }

*/