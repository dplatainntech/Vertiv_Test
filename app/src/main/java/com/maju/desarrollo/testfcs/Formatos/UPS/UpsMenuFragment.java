package com.maju.desarrollo.testfcs.Formatos.UPS;


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
import com.maju.desarrollo.testfcs.Check.ImgB64;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpsMenuFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    UPS Formato;
    ImageView im1 ,im2,im3,im4 ,im5,im6,im7,im8;
    int completo;
    Button Terminar;
    InternetandVPN validaciones = new InternetandVPN();
    UsuarioD usuario;
    ImgB64 b64 = new ImgB64();
    progresoLoad alerta = new progresoLoad();

    public UpsMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ups_menu, container, false);

        ((MainActivity) getActivity()).verCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        id_formato= getArguments().getString("key_idFormato");
        usuario = D_B.obtenerUsuario();
        if(id_formato.equals("NUEVO")){
            id_formato = D_B.newUPS();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"11"); //crear registrode formato. UPS
            Formato = D_B.obtenerUPS(id_formato);
        }else{
            Formato = D_B.obtenerUPS(id_formato);
        }


        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.MN_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.MN_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.MN_M3);
        LinearLayout M4 = (LinearLayout)v.findViewById(R.id.MN_M4);
        LinearLayout M5 = (LinearLayout)v.findViewById(R.id.MN_M5);
        LinearLayout M6 = (LinearLayout)v.findViewById(R.id.MN_M6);
        LinearLayout M7 = (LinearLayout)v.findViewById(R.id.MN_M7);
        LinearLayout M8 = (LinearLayout)v.findViewById(R.id.MN_M8);
        im1 = (ImageView)v.findViewById(R.id.imgM1);
        im2 = (ImageView)v.findViewById(R.id.imgM2);
        im3 = (ImageView)v.findViewById(R.id.imgM3);
        im4 = (ImageView)v.findViewById(R.id.imgM4);
        im5 = (ImageView)v.findViewById(R.id.imgM5);
        im6 = (ImageView)v.findViewById(R.id.imgM6);
        im7 = (ImageView)v.findViewById(R.id.imgM7);
        im8 = (ImageView)v.findViewById(R.id.imgM8);

        //region Botones acciones
        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsGeneralesFragment myfargment = new UpsGeneralesFragment();
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

                UpsLecturasFragment myfargment = new UpsLecturasFragment();
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

                UpsRevisionesFragment myfargment = new UpsRevisionesFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

        M4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsComentariosFragment myfargment = new UpsComentariosFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();


            }
        });
        M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsMaterialesFragment myfargment = new UpsMaterialesFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsFotosFragment myfargment = new UpsFotosFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsFirmasFragment myfargment = new UpsFirmasFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                UpsAdicionalFragment myfargment = new UpsAdicionalFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });




        Terminar = (Button)v.findViewById(R.id.button2);
        Terminar.setEnabled(false);
        Terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        verAcencesModulos();
        return v;
    }

    public void verAcencesModulos(){
        completo = 8;

        //region Modulo  Generales
        int M1_generales = 11;

        try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getIdCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getDireccionSitio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getSRProyecto().isEmpty() || !Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTipoServicio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFreecuencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFechaInicio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFechaTermino().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getContactoCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTelefono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getEMail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
        else if(M1_generales < 11){im1.setImageResource(R.drawable.generalnar);}
        else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 2
        int M2_I = 35;
        try{if(!Formato.getVoltajeEntradaLLFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeEntradaLLFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeEntradaLLFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeEntradaLNFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeEntradaLNFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeEntradaLNFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteEntradaFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteEntradaFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteEntradaFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteFiltroEntradaFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteFiltroEntradaFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteFiltroEntradaFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLLFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLLFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLLFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLNFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLNFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeSalidaUPSLNFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeBypassFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeBypassFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeBypassFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteSalidaFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteSalidaFaseB().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteSalidaFaseC().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteFiltroSalidaFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeBusDCFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteBusDCFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getVoltajeRizoACFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCorrienteRizoACFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSincronoscopioFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPotenciaKWFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPotenciaKVAFaseA().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getFreqEntrada().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getFreqUPSSalida().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPorcentajeCarga().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}


        if(M2_I < 35){im2.setImageResource(R.drawable.lecturaverde);
            completo = completo-1;
        }
        else {im2.setImageResource(R.drawable.lecturagris);}


        //endregion

        //region Modulo3
        int M4_ = 19;

        try{if(Formato.getRevisarAlarmas().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getInspeccionCables().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getCalibracionMedidor().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getAjusteEcualizacion().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getPantallaInformeEstado().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getLimpiezaUPS().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getSnubberSobretemperatura().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getAjusteLímites().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getPantallaProcedimiento().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getPantallaBaterías().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getRevisionModulosDaños().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getInspeccionGeneral().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getCapacitoresInflados().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getCapacitoresValvulas().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getRectificador().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getInversor().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getSwitchEstatico().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getTransformador().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getRevSoftware().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}


        if(M4_ < 19){ im3.setImageResource(R.drawable.revisionverde);
            completo = completo-1;}
        else {im3.setImageResource(R.drawable.revisiongris);}

        //endregion

        //region Modulo Comentarios
        int M_ = 1;
        try{if(!Formato.getComentariosRecomendaciones().isEmpty()){M_ = M_- 1;}}catch (Exception e){}
        if(M_ == 0){ im4.setImageResource(R.drawable.comentariosverde);
            completo = completo-1;}
        else {im4.setImageResource(R.drawable.comentariosgris);}

        //endregion

        //region Modulo Materiales
        int M5_A = 27;
        int M5_B = 15;

        try{if(Formato.getCantidad1().equals(" ") &&
                Formato.getNoParte1().equals(" ") &&
                Formato.getDescripcion1().equals(" ")){
            M5_A = M5_A - 27;
        }
        else{
            try{if(!Formato.getCantidad1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCantidad9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNoParte9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getDescripcion9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}

        }}catch(Exception e){}
        try{if(Formato.getEquipo1().equals(" ") &&
                Formato.getNoId1().equals(" ") &&
                Formato.getFechaVencimiento1().equals(" ")){
            M5_B = M5_B - 15;
        }
        else{
            try{if(!Formato.getEquipo1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoId1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFechaVencimiento1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEquipo2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoId2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFechaVencimiento2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEquipo3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoId3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFechaVencimiento3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEquipo4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoId4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFechaVencimiento4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEquipo5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoId5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFechaVencimiento5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
        }}catch(Exception e){}

        if((M5_A + M5_B) == 0){ im5.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else if((M5_A < 27 && M5_B==15) || (M5_A == 27 && M5_B<15) ){
            im5.setImageResource(R.drawable.materialenar);
        }
        else if(M5_A <27 && M5_B <15){
            im5.setImageResource(R.drawable.materialesverde);
            completo = completo-1;
        }
        else {im5.setImageResource(R.drawable.materialesgris);}

        //endregion

        //region Modulo Fotos
        int M6_ = 12;
        int M_Foto1 = 2;
        int M_Foto2 = 2;
        int M_Foto3 = 2;
        int M_Foto4 = 2;
        int M_Foto5 = 2;
        int M_Foto6 = 2;

        try{if(!Formato.getDespues1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getDespues2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getDespues3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getDespues4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getDespues5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getDespues6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getAntes6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

        int constante1 = M_Foto1 + M_Foto2+ M_Foto3 +M_Foto4+M_Foto5+M_Foto6;
        if(constante1 == 12){
            im6.setImageResource(R.drawable.fotogris);
        }else{
            if(constante1== 0){
                im6.setImageResource(R.drawable.fotoverde);
                completo = completo-1;
            }else{
                int terinado = 1;
                if(M_Foto1 > 0 & M_Foto1<2){terinado = terinado +1;}
                if(M_Foto2 > 0 & M_Foto2<2){terinado = terinado +1;}
                if(M_Foto3 > 0 & M_Foto3<2){terinado = terinado +1;}
                if(M_Foto4 > 0 & M_Foto4<2){terinado = terinado +1;}
                if(M_Foto5 > 0 & M_Foto5<2){terinado = terinado +1;}
                if(M_Foto6 > 0 & M_Foto6<2){terinado = terinado +1;}
                if(terinado==1){
                    im6.setImageResource(R.drawable.fotoverde);
                    completo = completo-1;
                }else {
                    im6.setImageResource(R.drawable.fotonar);
                }
            }
        }





        //endregion

        //region Modulo Firmas

        int M7_ = 2;
        int obliga =2;
        int M7_Fr = 0;

        try{if(!Formato.getFirmaClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFirmaCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFirmaVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getCLIENTEFINAL_EMPRESA().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getCLIENTEFINAL_TELEFONO().isEmpty()){M7_Fr++;}}catch (Exception e){}

        try{
            if(!Formato.getFirmaClienteFinal().isEmpty() && !Formato.getNombreFirmaClienteFinal().isEmpty()){
                try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()&&
                        !Formato.getCLIENTEFINAL_EMPRESA().isEmpty()&&
                        !Formato.getCLIENTEFINAL_TELEFONO().isEmpty()){M7_ = M7_-1;}
                }catch (Exception e){}{
                }
            }
        }catch (Exception e){}


        try {
            if (!Formato.getFirmaCliente().isEmpty()&& !Formato.getNombreFirmaCliente().isEmpty()){ M7_ = M7_-1;   }
        } catch (Exception e) {
        }

        try {
            if (!Formato.getFirmaVertiv().isEmpty()){ obliga = obliga-1;   }
            if (!Formato.getNombreFirmaVertiv().isEmpty()){ obliga = obliga-1;   }
        } catch (Exception e) {
        }

        if(obliga == 0){
            if(M7_== 0 || M7_ == 1 ){im7.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im7.setImageResource(R.drawable.firmanar);}
            else {im7.setImageResource(R.drawable.firmagris);}
        }
        else if(M7_ < 2){im7.setImageResource(R.drawable.firmanar);}
        else {
            if(M7_Fr>0){
                im7.setImageResource(R.drawable.firmanar);
            }else{
                im7.setImageResource(R.drawable.firmagris);
            }
        }


        //endregion

        //region Modulo Adicionales
        int M8_ = 10;

        //try{if(!Formato..getADICI_NOMBRE1().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO1().isEmpty() && !Formato.getAD_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO2().isEmpty() && !Formato.getAD_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO3().isEmpty() && !Formato.getAD_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO4().isEmpty() && !Formato.getAD_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO5().isEmpty() && !Formato.getAD_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}


        if(M8_ < 10){im8.setImageResource(R.drawable.notificaverde);
            completo = completo-1;}
        else {im8.setImageResource(R.drawable.notificagris);}
        //endregion

        //region Modulo 9
        int M9_firma = 0;

        //endregion

        if(completo == 0){
            D_B.actualizarEsatusFormato("0",id_formato);
            Terminar.setBackgroundResource(R.drawable.btnnaranja);
            Terminar.setEnabled(true);
        }else{
            D_B.actualizarEsatusFormato("1",id_formato);
        }

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
        Formato.setIdUsuario(usuario.getID_USER());
        try{if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}}catch (Exception e){}
        final String idFormatoService = D_B.generarUID();
        Formato.setIdFormatoUPS(idFormatoService);
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
       /* try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
        */

        //region enviar formato
        Formato.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarUPS(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UPS>() {
                    @Override
                    public void accept(UPS lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if (lista.getExitoso().equals("1")) {
                            enviarFotos(lista.getFolioPreTrabajo());
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

                            Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

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

    }

    public void enviarFotos(String idFormatoService){
        //region Enviar_Fotos

        try{if(Formato.getAntes1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes1(b64.ImgB64(Formato.getAntes1()));
            item.setFormato(Formato.getAntes1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes2(b64.ImgB64(Formato.getAntes2()));
            item.setFormato(Formato.getAntes2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes3(b64.ImgB64(Formato.getAntes3()));
            item.setFormato(Formato.getAntes3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes4(b64.ImgB64(Formato.getAntes4()));
            item.setFormato(Formato.getAntes4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes5(b64.ImgB64(Formato.getAntes5()));
            item.setFormato(Formato.getAntes5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes6(b64.ImgB64(Formato.getAntes6()));
            item.setFormato(Formato.getAntes6());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues1(b64.ImgB64(Formato.getDespues1()));
            item.setFormato(Formato.getDespues1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues2(b64.ImgB64(Formato.getDespues2()));
            item.setFormato(Formato.getDespues2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues3(b64.ImgB64(Formato.getDespues3()));
            item.setFormato(Formato.getDespues3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues4(b64.ImgB64(Formato.getDespues4()));
            item.setFormato(Formato.getDespues4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues5(b64.ImgB64(Formato.getDespues5()));
            item.setFormato(Formato.getDespues5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues6(b64.ImgB64(Formato.getDespues6()));
            item.setFormato(Formato.getDespues6());
            ((MainActivity) getActivity()).imagenesEnvio(item);

        }}catch (Exception e){}
        //endregion
    }


}
