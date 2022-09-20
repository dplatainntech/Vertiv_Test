package com.maju.desarrollo.testfcs.Formatos.Calidad;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.AccionesM.CContrasena;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.AlertaOk;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalidadMenu extends Fragment {

    OperacionesDB D_B;
    String id_formato;
    EncuestaCalidadServicio Formato;
    int completo = 3;
    Button terminar;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    UsuarioD usuario;
    InternetandVPN validaciones = new InternetandVPN();
    progresoLoad alerta = new progresoLoad();

    public CalidadMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calidad_menu, container, false);

        ((MainActivity) getActivity()).verCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        //id_formato ="Key-a7901967-7a60-4a8c-bac7-e2bc00b4f324"; //test
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();

        if(id_formato.equals("NUEVO")){
            id_formato = D_B.newCalidad();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"1"); //crear registrode formato. CAlidad
            Formato = D_B.obtenerCalidad(id_formato);
        }else{
            Formato = D_B.obtenerCalidad(id_formato);
        }

       /* Bundle valores = new Bundle();
        valores.putString("titulo","Encuesta de Calidad");
        valores.putString("mensaje","Si la respuesta a cualquiera de los conceptos es menor a 4 ayúdenos con sus comentarios para una mejora continua.");

        AlertaGenerica alerta = new AlertaGenerica();
            alerta.setArguments(valores);
            alerta.setCancelable(false);
            alerta.show(getFragmentManager(), "a");
*/

        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.CAL_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.CAL_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.CAL_M3);

        ImageView imgM3 = (ImageView)v.findViewById(R.id.imgM3);
        ImageView imgM2 = (ImageView)v.findViewById(R.id.imgM2);
        ImageView imgM1 = (ImageView)v.findViewById(R.id.imgM1);

        //region Botones acciones
        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                /*Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Generales");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);
                */
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                CalidadGeneral myfargment = new CalidadGeneral();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();


            }
        });
        M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                CalidadEvaluacion myfargment = new CalidadEvaluacion();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                CalidadFirmas myfargment = new CalidadFirmas();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });




        terminar = (Button)v.findViewById(R.id.buttonTerminar);
        terminar.setEnabled(false);
        terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getContext(), "Se termino Pre Trabajo", Toast.LENGTH_LONG).show();

                //region validaciones y envio de Formato
                alerta.setCancelable(true);
                alerta.show(getFragmentManager(), "a");

                try {
                    if(validaciones.webservise()){
                        if(validaciones.vpnOn()){
                           // Login activo = validaciones.activo(usuario.getID_USER(), usuario.getCONTRASEÑA(), iMyAPI);
                            Login itemAct = new Login();
                            itemAct.setUsuario(usuario.getCORREO());
                            itemAct.setContraseña(usuario.getCONTRASEÑA());

                            compositeDisposable.add(iMyAPI.Login(itemAct).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Login>() {
                                        @Override
                                        public void accept(Login item) throws Exception {
                                            if(item.getExitoso().equals("1") || item.getExitoso().equals("4")){
                                                if(item.getExitoso().equals("4")){
                                                    alertaMensajes("Importante", item.getError());
                                                }
                                                enviarFormato();
                                            }
                                            else{
                                                alerta.dismiss();
                                                if(item.getExitoso().equals("0") || item.getExitoso().equals("3")){
                                                    D_B.CambioSesion(usuario,"INACTIVO");
                                                    alertaMensajes("Importante", item.getError());

                                                    Intent siguiente2 = new Intent(getActivity(), LoginActivity.class);
                                                    startActivity(siguiente2);
                                                    overridePendingTransition(R.anim.left_in, R.anim.left_out);
                                                }
                                                if(item.getExitoso().equals("2")){
                                                    alertaMensajes("Importante", item.getError());
                                                    Intent siguiente2 = new Intent(getActivity(), CContrasena.class);
                                                    startActivity(siguiente2);
                                                }

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

                        }
                        else {
                            alerta.dismiss();
                            alertaMensajes("Alerta", "No hay conexión a la VPN");
                        }
                    }
                    else{
                        alertaMensajes("Importante", "No se establecio conexión a internet, formato guardado localmente");

                        MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                        alerta.dismiss();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    alerta.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                    alerta.dismiss();
                }
                //endregion

            }
        });


        //region Modulo 1 Generales

        int M1_generales1 = 10;
        try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        //try{if(!Formato.getContactoCliente().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getIdCliente().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getDireccionSitio().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getTelefono().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getEMail().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getSRProyecto().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        //try{if(!Formato.getTipoServicio().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getTipoServicio().isEmpty()){
            if(Formato.getTipoServicio().equals("Otro (Especifique)")){
                if(!Formato.getTiposervicioOtro().isEmpty()){M1_generales1 = M1_generales1 - 1;}
            }else{
                M1_generales1 = M1_generales1 - 1;
            }
        }
        }catch (Exception e){}

        try{if(!Formato.getModeloEquipo().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getNoSerie().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!Formato.getFecha().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}

        if(M1_generales1 == 0){ imgM1.setImageResource(R.drawable.generalverd); completo = completo-1;}
        else if(M1_generales1 < 10){imgM1.setImageResource(R.drawable.generalnar);}
        else {imgM1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 9 Firmas
        int M9_firma = 1;
        int M9_firmaGen1 = 2;
        int M7_Fr = 0;

        try{if(!Formato.getFirmaCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFNombreCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFirmaClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFNombreClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFirmaVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFNombreVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}

        //try{if(!Formato.getFirmaClienteFinal().isEmpty()){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}
        try{if(!Formato.getFirmaCliente().isEmpty() && !Formato.getFNombreCliente().isEmpty() ){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}
        try{if(!Formato.getFirmaClienteFinal().isEmpty() && !Formato.getFNombreClienteFinal().isEmpty() ){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}
        try{if(!Formato.getFirmaVertiv().isEmpty() && !Formato.getFNombreVertiv().isEmpty() ){M9_firma = M9_firma - 1;}}catch (Exception e){}

        if(M9_firma == 0 && M9_firmaGen1 <2){ imgM3.setImageResource(R.drawable.firmaverde); completo = completo-1;}
        else if((M9_firma < 1 && M9_firmaGen1 == 2) || (M9_firma == 1 && M9_firmaGen1 < 2)){imgM3.setImageResource(R.drawable.firmanar);}
        else {if(M7_Fr>0){
            imgM3.setImageResource(R.drawable.firmanar);
        }else{
            imgM3.setImageResource(R.drawable.firmagris);
        }}
        //endregion

        //region Modulo 7 Emergencias
        int M7_Eme = 19;

        try{if(!Formato.getI1().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getI2().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getI3().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getII1().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getII2().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getII3().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getII4().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getIII1().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getIII2().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getIII3().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getIII4().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!Formato.getRecomienda().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{
            if(Formato.getCRCNA().equals("0")){
            try{if(!Formato.getCRC1().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
            try{if(!Formato.getCRC2().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
            try{if(!Formato.getCRC3().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
                if(Integer.parseInt(Formato.getCRC1())<4 || Integer.parseInt(Formato.getCRC2())<4 ||Integer.parseInt(Formato.getCRC3())<4 ) {
                    if (Formato.getCRCComentario().length() > 5) {
                        M7_Eme = M7_Eme - 1;
                    }
                }else{
                    M7_Eme = M7_Eme - 1;
                }

            }
            else{
                if(Formato.getCRCNA().equals("1")){
                    M7_Eme = M7_Eme - 4;
                }
            }
        }catch (Exception e){}  // + 4

        try{
            if(Integer.parseInt(Formato.getI1())<4 || Integer.parseInt(Formato.getI2())<4 ||Integer.parseInt(Formato.getI3())<4){
            if(Formato.getIComentarios().length()>5){
                M7_Eme = M7_Eme - 1;
            }
        }
        else{M7_Eme = M7_Eme - 1;}}catch (Exception e){}

        try{
            if(Integer.parseInt(Formato.getII1())<4 || Integer.parseInt(Formato.getII2())<4 ||Integer.parseInt(Formato.getII3())<4 || Integer.parseInt(Formato.getII4())<4){
            if(Formato.getIIComentarios().length()>5){
                M7_Eme = M7_Eme - 1;
            }
        }
        else{M7_Eme = M7_Eme - 1;}}catch (Exception e){}

        try{
            if(Integer.parseInt(Formato.getIII1())<4 || Integer.parseInt(Formato.getIII2())<4 ||Integer.parseInt(Formato.getIII3())<4 || Integer.parseInt(Formato.getIII4())<4){
            if(Formato.getIIIComentarios().length()>5){
                M7_Eme = M7_Eme - 1;
            }
        }
        else{M7_Eme = M7_Eme - 1;}}catch (Exception e){}

        //try{if(!Formato.getComentarios().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}

        if(M7_Eme == 0){ imgM2.setImageResource(R.drawable.calidadverde);
            completo = completo-1;}
        else if(M7_Eme < 19){imgM2.setImageResource(R.drawable.calidadnar);}
        else {imgM2.setImageResource(R.drawable.calidadgris);}

        //endregion

        if(completo == 0){
            D_B.actualizarEsatusFormato("0",id_formato);
            terminar.setBackgroundResource(R.drawable.btnnaranja);
            terminar.setEnabled(true);
        }else{
            D_B.actualizarEsatusFormato("1",id_formato);
        }


        return v;
    }

    private void overridePendingTransition(int left_in, int left_out) {
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


    public void enviarFormato(){

        EncuestaCalidadServicio item = D_B.obtenerCalidad(id_formato);

        item.setIdUsuario(usuario.getID_USER());

        if(item.getTipoServicio().equals("Otro (Especifique)")){
            item.setTipoServicio(item.getTiposervicioOtro());
        }

        //region enviar formato
        item.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarCalidad(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EncuestaCalidadServicio>() {
                    @Override
                    public void accept(EncuestaCalidadServicio lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if(lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);
                            Bundle valores = new Bundle();
                            valores.putString("mensaje","Folio:" + lista.getFolioPreTrabajo().toString());
                            AlertaOk alerta = new AlertaOk();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");

                            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                            FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                            //Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

                        }
                        else{
                            D_B.newMensaje(lista.getError());
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
        //endregion

    }

    }
