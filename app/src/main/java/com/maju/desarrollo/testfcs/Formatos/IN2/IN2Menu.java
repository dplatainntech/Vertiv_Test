package com.maju.desarrollo.testfcs.Formatos.IN2;


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
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
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
public class IN2Menu extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Bestel2 Formato;
    ImageView im1 ,im2,im3,im4 ,im5,im6,im7;
    int completo;
    Button Terminar;
    InternetandVPN validaciones = new InternetandVPN();
    UsuarioD usuario;
    ImgB64 b64 = new ImgB64();
    progresoLoad alerta = new progresoLoad();

    public IN2Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_in2_menu, container, false);
        ((MainActivity) getActivity()).verCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        id_formato= getArguments().getString("key_idFormato");
        usuario = D_B.obtenerUsuario();
        if(id_formato.equals("NUEVO")){
            id_formato = D_B.newBestel2();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"6"); //crear registrode formato. BN1
            Formato = D_B.obtenerBestel2(id_formato);
        }else{
            Formato = D_B.obtenerBestel2(id_formato);
        }


        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.MN_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.MN_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.MN_M3);
        LinearLayout M4 = (LinearLayout)v.findViewById(R.id.MN_M4);
        LinearLayout M5 = (LinearLayout)v.findViewById(R.id.MN_M5);
        LinearLayout M6 = (LinearLayout)v.findViewById(R.id.MN_M6);
        im1 = (ImageView)v.findViewById(R.id.imgM1);
        im2 = (ImageView)v.findViewById(R.id.imgM2);
        im3 = (ImageView)v.findViewById(R.id.imgM3);
        im4 = (ImageView)v.findViewById(R.id.imgM4);
        im5 = (ImageView)v.findViewById(R.id.imgM5);
        im6 = (ImageView)v.findViewById(R.id.imgM6);
        im7 = (ImageView)v.findViewById(R.id.imgM7);

        //region Botones acciones
        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                IN2Generales myfargment = new IN2Generales();
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

                IN2Inspeccion myfargment = new IN2Inspeccion();
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

                IN2Materiales myfargment = new IN2Materiales();
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

                IN2Fotografias myfargment = new IN2Fotografias();
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

                IN2Firmas myfargment = new IN2Firmas();
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

                IN2Adicionales myfargment = new IN2Adicionales();
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
        completo = 6;

        //region Modulo  Generales
        int M1_generales = 15;

        try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getIdCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getIdRegion().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getIdEstado().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTipoSitio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getNombreSitio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getDireccionSitio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getProyecto().isEmpty() || !Formato.getTask().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTipoServicio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFrecuencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFechaInicio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFechaTermino().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getContactoCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTelefono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getTask().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getNoTag().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getEmail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
        else if(M1_generales < 15){im1.setImageResource(R.drawable.generalnar);}
        else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 2
        int M2_I = 97;
        try{if(!Formato.getAATemperatura().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAACondensadora().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAEvaporadora().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAASerpentin().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAFugaGas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAValvulas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAATermostatos().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAABombas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECIliminacion().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECPinturaMuros().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECPisos().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECImpermeabilizacion().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECHidrosanitario().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECHerrejes().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECLimpiezaGeneral().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFVoltajeSalida().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFCorrienteDisplay().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFTensioDispaly().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFTemSalas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFTensionl1l2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFTensionl2l3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFTensionl1l3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCSistema().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCDetectores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCExtintores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCBotesAreneros().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCFechaCaducidad().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSALectoras().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSASupervisoresPuertas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSACCTV().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSABaterias().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSATablerosControl().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUAlarmas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUTemperatura().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUCapacitores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUVoltajeTotalBaterias().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUVerificacionVentiladiores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUReapreteConexiones().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEFugasAceite().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEHorasOperacion().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEBaterias().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPENivelDisel().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPENivelAnticongelante().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEManguerasGeneral().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPERuidosExtraños().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSESobreCalentamientoPorFase().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSEBarrasPuestaTierra().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        //try{if(!Formato.getSSCoexionPuntaTierra().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSETransformador().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSEFusibles().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSETemperatura().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSECuchillas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSEInterruptores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTLimpiezaGeneral().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTAnclasRetenidos().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTLucesObstruccion().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTTornilleriaHerraje().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTPuestaTierra().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTTSistemaApartaRayos().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHFugasGeneral().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHHidroneumaticos().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHBaños().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHCisternasTanques().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHBombas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHEmpaques().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHAccesorios().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){}
        try{ if(Formato.getOPTEM().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL1L2().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL2L3().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL3L1().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1W().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1VAR().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1VA().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL1N().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL2N().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPL3N().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF1().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF2().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF3().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPHZ().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOPREVRPM().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOTROS().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getAccionesTomadas().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros2().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios2().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros3().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios3().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros4().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios4().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros5().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios5().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros6().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios6().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros7().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios7().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getOtros8().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios8().length()>0){ M2_I = M2_I -1;}}catch (Exception e){}
        
        
        if(M2_I < 97){im2.setImageResource(R.drawable.actividadesver);
            completo = completo-1;
        }
        else {im2.setImageResource(R.drawable.actividadesgris);}


        //endregion

        //region Modulo Materiales
        int M5_ = 1;
        try{if(!Formato.getMaterialesActividades().isEmpty()){M5_ = M5_- 1;}}catch (Exception e){}
        if(M5_ == 0){ im3.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else {im3.setImageResource(R.drawable.materialesgris);}

        //endregion

        //region Modulo4
        /*int M4_ = 2;
        try{if(Formato.getEDSRTiempo().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getEDSRCalidad().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        //try{if(!Formato.isEDSREvalucionSitio()){M4_ = M4_ - 1;}}catch (Exception e){}

        if(M4_ == 0){ im4.setImageResource(R.drawable.inspeccionverd);
            completo = completo-1;}
        else if(M4_ < 2){ im4.setImageResource(R.drawable.inspeccionnar); }
        else {im4.setImageResource(R.drawable.inspecciongris);}
*/
        //endregion

        //region Modulo 3
        /*int M3_ = 1;

        try{if(!Formato.getMateriales().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}

        if(M3_ == 0){ im3.setImageResource(R.drawable.materialesverde); }
        else {im3.setImageResource(R.drawable.materialesgris);}

         */
        //endregion

        //region Modulo Fotos
        int M6_ = 12;
        int M_Foto1 = 2;
        int M_Foto2 = 2;
        int M_Foto3 = 2;
        int M_Foto4 = 2;
        int M_Foto5 = 2;
        int M_Foto6 = 2;

        try{if(!Formato.getDespuesFoto1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getDespuesFoto2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getDespuesFoto3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getDespuesFoto4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getDespuesFoto5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getDespuesFoto6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getAntesFoto6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

        int constante1 = M_Foto1 + M_Foto2+ M_Foto3 +M_Foto4+M_Foto5+M_Foto6;
        if(constante1 == 12){
            im4.setImageResource(R.drawable.fotogris);
        }else{
            if(constante1== 0){
                im4.setImageResource(R.drawable.fotoverde);
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
                    im4.setImageResource(R.drawable.fotoverde);
                    completo = completo-1;
                }else {
                    im4.setImageResource(R.drawable.fotonar);
                }
            }
        }





        //endregion

        //region Modulo Firmas
        int M7_ = 4;
        int M7_F= 0;
        try{if(!Formato.getNombreFirmaVertiv().isEmpty()){M7_ = M7_- 1; M7_F ++;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaCliente().isEmpty()){M7_ = M7_- 1;  M7_F ++;}}catch (Exception e){}
        try{if(!Formato.getFirmaCliente().isEmpty()){M7_ = M7_- 1;  M7_F ++;}}catch (Exception e){}
        try{if(!Formato.getFirmaVertiv().isEmpty()){M7_ = M7_- 1;  M7_F ++;}}catch (Exception e){}

        if(M7_ == 0){ im5.setImageResource(R.drawable.firmaverde);
            completo = completo-1;}
        else if(M7_ < 4){im5.setImageResource(R.drawable.firmanar);}
        else {im5.setImageResource(R.drawable.firmagris);}

        //endregion

        //region Modulo Adicionales
        int M8_ = 10;

        //try{if(!Formato..getADICI_NOMBRE1().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO1().isEmpty() && !Formato.getAD_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO2().isEmpty() && !Formato.getAD_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO3().isEmpty() && !Formato.getAD_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO4().isEmpty() && !Formato.getAD_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO5().isEmpty() && !Formato.getAD_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}


       if(M8_ < 10){im6.setImageResource(R.drawable.notificaverde);
           completo = completo-1;}
        else {im6.setImageResource(R.drawable.notificagris);}
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

        final String idFormatoService = D_B.generarUID();
        Formato.setIdBestelNivel2(idFormatoService);
        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        /*try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
*/

        //region enviar formato
        compositeDisposable.add(iMyAPI.enviarBestel2(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bestel2>() {
                    @Override
                    public void accept(Bestel2 lista) throws Exception {
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

        try{if(Formato.getAntesFoto1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
            item.setFormato(Formato.getAntesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
            item.setFormato(Formato.getAntesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
            item.setFormato(Formato.getAntesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
            item.setFormato(Formato.getAntesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
            item.setFormato(Formato.getAntesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
            item.setFormato(Formato.getAntesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
            item.setFormato(Formato.getDespuesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
            item.setFormato(Formato.getDespuesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
            item.setFormato(Formato.getDespuesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
            item.setFormato(Formato.getDespuesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
            item.setFormato(Formato.getDespuesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
            item.setFormato(Formato.getDespuesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(item);

        }}catch (Exception e){}
        //endregion
    }

}
