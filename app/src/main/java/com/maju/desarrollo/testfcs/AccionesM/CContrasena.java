package com.maju.desarrollo.testfcs.AccionesM;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.CambioContrasena;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CContrasena extends Fragment {
    UsuarioD usuario;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    OperacionesDB D_B;
    InternetandVPN validaciones = new InternetandVPN();

    public CContrasena() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ccontrasena, container, false);

        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();
        final EditText anterior = (EditText)v.findViewById(R.id.editText6);
        final EditText nueva1 = (EditText)v.findViewById(R.id.editText7);
        final EditText nueva2 = (EditText)v.findViewById(R.id.editText8);
        TextView correoUsuario = (TextView)v.findViewById(R.id.correoUsuario);

        correoUsuario.setText(usuario.getNOMBRE());

        try{
           String mensaje = getArguments().getString("MensajeContraseña");
           if(!mensaje.isEmpty()){
               alertaMensajes("Atención", mensaje );
           }

        }
        catch (Exception ex){

        }

        //region Botones
        Button terminar = (Button)v.findViewById(R.id.btoSincronizar);
        //terminar.setEnabled(false);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Se termino Pre Trabajo", Toast.LENGTH_LONG).show();
                try {
                    if(validaciones.webservise()){
                        if(validaciones.vpnOn()){
                            if(anterior.getText().toString().equals(usuario.getCONTRASEÑA())){
                                if((nueva1.getText().toString().length()>5 && nueva2.getText().toString().length()>5) &&
                                        ( nueva1.getText().toString().equals(nueva2.getText().toString()))){
                                    //region conformacion
                                    final progresoLoad alerta = new progresoLoad();
                                    alerta.setCancelable(true);
                                    alerta.show(getFragmentManager(), "a");

                                    final CambioContrasena item = new CambioContrasena();
                                    item.setIdUsuario(usuario.getID_USER());
                                    item.setPass(nueva1.getText().toString());

                                    compositeDisposable.add(iMyAPI.cambioContraseña(item).subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<CambioContrasena>() {
                                                @Override
                                                public void accept(CambioContrasena lista) throws Exception {
                                                    //lista.get(0).getItem_Number();
                                                    alerta.dismiss();
                                                    if(lista.getVigencia().length()>0) {
                                                        usuario.setCONTRASEÑA(lista.getPass());
                                                        D_B.Cambiocontraseña(usuario);
                                                        Bundle valores = new Bundle();
                                                        valores.putString("titulo","Contraseña");
                                                        valores.putString("mensaje","Se actualizo la contraseña, es vigente hasta: " + lista.getVigencia().toString() );

                                                        AlertaGenerica alerta = new AlertaGenerica();
                                                        alerta.setArguments(valores);
                                                        alerta.setCancelable(false);
                                                        alerta.show(getFragmentManager(), "a");

                                                        MenuPrincipalFragment myfargment = new MenuPrincipalFragment();

                                                        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                                                        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();

                                                    }
                                                    else{
                                                        Bundle valores = new Bundle();
                                                        valores.putString("titulo","Error");
                                                        valores.putString("mensaje","Se genero un error: " + lista.getError());

                                                        AlertaGenerica alerta = new AlertaGenerica();
                                                        alerta.setArguments(valores);
                                                        alerta.setCancelable(false);
                                                        alerta.show(getFragmentManager(), "a");
                                                    }
                                                }
                                            }, new Consumer<Throwable>() {
                                                @Override
                                                public void accept(Throwable throwable) throws Exception {
                                                    Toast.makeText(getContext(),"Surgio un error, intentalo nuevamente." , Toast.LENGTH_SHORT).show();
                                                    alerta.dismiss();
                                                    //throwable.getMessage()
                                                }
                                            })

                                    );
                                    //endregion
                                }
                                else{
                                    Bundle valores = new Bundle();
                                    valores.putString("titulo","Atención");
                                    valores.putString("mensaje","La confirmación de contraeña no coincide.\n" +nueva1.getText().toString() + "\n" + nueva2.getText().toString() );

                                    AlertaGenerica alerta = new AlertaGenerica();
                                    alerta.setArguments(valores);
                                    alerta.setCancelable(false);
                                    alerta.show(getFragmentManager(), "a");
                                }
                            }else{
                                Bundle valores = new Bundle();
                                valores.putString("titulo","Atención");
                                valores.putString("mensaje","Contraseña actual no coincide. \n" + anterior.getText().toString() );

                                AlertaGenerica alerta = new AlertaGenerica();
                                alerta.setArguments(valores);
                                alerta.setCancelable(false);
                                alerta.show(getFragmentManager(), "a");
                            }


                        }
                        else {

                            alertaMensajes("Alerta", "No hay conexión a la VPN");
                        }
                    }
                    else{
                        alertaMensajes("Importante", "No se establecio conexión a internet, formato guardado localmente");

                        MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();

                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();

                }



            }
        });



        //endregion



        return v;
    }

    public void alertaMensajes(String titulo, String mensaje){
        Bundle valores = new Bundle();
        valores.putString("titulo",titulo);
        valores.putString("mensaje",mensaje);
        AlertaGenerica alertamnsaje = new AlertaGenerica();
        alertamnsaje.setArguments(valores);
        alertamnsaje.setCancelable(false);
        alertamnsaje.show(getFragmentManager(), "a");
    }

}
