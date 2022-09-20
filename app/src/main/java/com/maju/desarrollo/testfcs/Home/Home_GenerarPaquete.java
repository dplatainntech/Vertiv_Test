package com.maju.desarrollo.testfcs.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.maju.desarrollo.testfcs.ServiceClass.CierrePreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.FoliosCierre;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_GenerarPaquete extends Fragment {
    OperacionesDB D_B;
    String id_PT;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    CatFolios Datos;
    InternetandVPN internetandVPN = new InternetandVPN();
    TextView llistaFolios;
    UsuarioD usuario;
    CatAsignaCliente tarea;

    public Home_GenerarPaquete() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home__generar_paquete, container, false);
        id_PT= getArguments().getString("FolioPT");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);

        Datos = D_B.obtenerFolioPT(id_PT);
        usuario = D_B.obtenerUsuario();

        final TextView folioPTC = (TextView)v.findViewById(R.id.folioPTC);
        final TextView cliente = (TextView)v.findViewById(R.id.cliente);
        final TextView sitio = (TextView)v.findViewById(R.id.sitio);
        final TextView sr = (TextView)v.findViewById(R.id.sr);
        final TextView task = (TextView)v.findViewById(R.id.task);
        final TextView item = (TextView)v.findViewById(R.id.item);
        final TextView serie = (TextView)v.findViewById(R.id.serie);
        llistaFolios = (TextView)v.findViewById(R.id.llistaFolios);

        tarea = D_B.obtenerTaskId(Datos.getGEN_SR().toString());

        folioPTC.setText(id_PT);
        cliente.setText(Datos.getCliente());
        sitio.setText(Datos.getNombreSitio());
        sr.setText(Datos.getGEN_SR());
        task.setText(Datos.getGEN_TASK());
        item.setText(tarea.getItem_Number());
        serie.setText(tarea.getSerial_Number());

        buscarFormatos();
       // llistaFolios.setText(folios);

        //item.setText(Datos.get());
        //serie.setText(Datos.get());



        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Home_Paquete myfargment = new Home_Paquete();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CierrePreTrabajo item = new CierrePreTrabajo();
                final progresoLoad alerta = new progresoLoad();
                alerta.setCancelable(true);
                alerta.show(getFragmentManager(), "a");

                item.setFolioPreTrabajo(id_PT);
                item.setPais(usuario.getPAIS());

                compositeDisposable.add(iMyAPI.GenerarPaquete(item).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<CierrePreTrabajo>() {
                            @Override
                            public void accept(CierrePreTrabajo lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if(lista.getExitoso().equals("1")) {
                                    D_B.newMensaje("PAQUETE GENERADO, FOLIO PRE-TRABAJO:" + id_PT);
                                    sincronizarFoiosDespuesde();
                                }else{
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
                                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                alerta.dismiss();
                            }
                        })

                );

            }
        });

        return v;
    }


    public void buscarFormatos(){
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        FoliosCierre item =  new FoliosCierre();
        item.setFolioPretrabajo(id_PT.toString());
        item.setPais(usuario.getPAIS().toString());

        try {
            if(internetandVPN.webservise()) {
                //region enviar formato

                compositeDisposable.add(iMyAPI.obtenerFormatosFolio(item).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<FoliosCierre>() {
                            @Override
                            public void accept(FoliosCierre lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    String datos = lista.getFormato().replace("|","\n");
                                    llistaFolios.setText(datos);
                                } else {
                                    D_B.newMensaje(lista.getError());
                                    Bundle valores = new Bundle();
                                    valores.putString("titulo", "Error");
                                    valores.putString("mensaje", "Se genero un error: " + lista.getError());

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
                                alerta.dismiss();
                            }
                        })

                );
                //endregion
            }else{
                Bundle valores = new Bundle();
                valores.putString("titulo", "Importante");
                valores.putString("mensaje", "Formato guardado localmente");

                AlertaGenerica alertaG = new AlertaGenerica();
                alertaG.setArguments(valores);
                alertaG.setCancelable(false);
                alertaG.show(getFragmentManager(), "a");
                MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                alerta.dismiss();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sincronizarFoiosDespuesde(){
        //region SINC FOLIOS PRE TRABAJO
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

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
                            if(lista.size()==0){
                                alerta.dismiss();
                            }else {
                                D_B.guardarFoliosPT(lista);
                                alerta.dismiss();
                                Home_Paquete myfargment = new Home_Paquete();
                                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();

                                //Toast.makeText(getContext(), "Folios guardados", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                            alerta.dismiss();
                        }
                    })

            );


        //endregion
    }
}
