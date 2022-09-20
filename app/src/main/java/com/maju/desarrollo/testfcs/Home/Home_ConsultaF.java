package com.maju.desarrollo.testfcs.Home;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Adapter.adapterBusquedaFolioG;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FoliosReporte;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_ConsultaF extends Fragment {
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    InternetandVPN internetandVPN = new InternetandVPN();
    TextView fecha1,fecha2;
    ListView listaDatos;
    UsuarioD usuario;
    // List<FoliosReporte> lista;
    OperacionesDB D_B;
   // private adapterBusquedaFolioG miAdaptador;


    public Home_ConsultaF() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home__consulta, container, false);
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);

        ImageView Button_fecha1 = (ImageView)v.findViewById(R.id.Button_fecha1);
        ImageView Button_fecha2 = (ImageView)v.findViewById(R.id.Button_fecha2);

        fecha1 = (TextView)v.findViewById(R.id.fecha1);
        fecha2 = (TextView)v.findViewById(R.id.fecha2);
        final EditText cliente = (EditText) v.findViewById(R.id.cliente);
        final EditText sr = (EditText) v.findViewById(R.id.SR);
        final EditText task = (EditText) v.findViewById(R.id.task);
        final EditText proyecto = (EditText) v.findViewById(R.id.proyecto);

       listaDatos = (ListView)  v.findViewById(R.id.lista1);

        D_B = OperacionesDB.obtenerInstancia(getContext());

        usuario= D_B.obtenerUsuario();


        Button_fecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });
        Button_fecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });



        Button buscar = (Button)v.findViewById(R.id.buttonBUSCAR);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final progresoLoad alerta = new progresoLoad();
                alerta.setCancelable(true);
                alerta.show(getFragmentManager(), "a");

              /*  new Handler().postDelayed(new Runnable() {
                    public void run() {
                        alerta.dismiss();
                        //finish();
                    }
                }, 4000);
*/
              if(fecha1.getText().toString().length()>5 && fecha2.getText().toString().length()>5 ){
                  if(cliente.getText().toString().length()>1 ||
                          sr.getText().toString().length()>1 ||
                          proyecto.getText().toString().length()>1
              ){

                      FoliosReporte item = new FoliosReporte();
                      item.setFechaIniP(fecha1.getText().toString());
                      item.setFechaFinP(fecha2.getText().toString());
                      item.setClienteP(cliente.getText().toString());
                      item.setSRP(sr.getText().toString());
                      item.setTaskP(task.getText().toString());
                      item.setProyectoP(proyecto.getText().toString());


                      try {
                          if(internetandVPN.webservise()) {
                              //region enviar formato
                              item.setPais(usuario.getPAIS());
                              compositeDisposable.add(iMyAPI.consultaFolios(item).subscribeOn(Schedulers.io())
                                      .observeOn(AndroidSchedulers.mainThread())
                                      .subscribe(new Consumer<List<FoliosReporte>>() {
                                          @Override
                                          public void accept(List<FoliosReporte> lista) throws Exception {
                                              //lista.get(0).getItem_Number();

                                              if(lista.size()>0) {
                                                  verLista(lista);
                                                  alerta.dismiss();
                                              }else{
                                                  Bundle valores = new Bundle();
                                                  valores.putString("titulo", "Busqueda");
                                                  valores.putString("mensaje", "No se encontraron resultados en la busqueda.");

                                                  AlertaGenerica alertaE = new AlertaGenerica();
                                                  alertaE.setArguments(valores);
                                                  alertaE.setCancelable(false);
                                                  alertaE.show(getFragmentManager(), "a");
                                                  alerta.dismiss();
                                              }
                                        /*

                                        }*/
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


                  }else{
                      Bundle valores = new Bundle();
                      valores.putString("titulo", "Importante");
                      valores.putString("mensaje", "Coloca uno de los valores restantes para realizar la busqueda.");

                      AlertaGenerica alerta1 = new AlertaGenerica();
                      alerta1.setArguments(valores);
                      alerta1.setCancelable(false);
                      alerta1.show(getFragmentManager(), "a");
                      alerta.dismiss();
                  }
              }
              else{
                  Bundle valores = new Bundle();
                  valores.putString("titulo", "Importante");
                  valores.putString("mensaje", "Es indispensable colocar los 2 valores de fechas.");

                  AlertaGenerica alerta0 = new AlertaGenerica();
                  alerta0.setArguments(valores);
                  alerta0.setCancelable(false);
                  alerta0.show(getFragmentManager(), "a");
                  alerta.dismiss();

              }




            }
        });

        return v;
    }

    public void verLista(List<FoliosReporte> listaF){

        adapterBusquedaFolioG miAdaptador = new adapterBusquedaFolioG(listaF, getActivity().getApplicationContext());
        listaDatos.setAdapter(miAdaptador);

    }

    private void showDatePickerDialog1() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fecha1.setText(selectedDate);

            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    private void showDatePickerDialog2() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fecha2.setText(selectedDate);

            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

}
