package com.maju.desarrollo.testfcs.Formatos.Garantias;


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
import com.maju.desarrollo.testfcs.SQLite.Model.SGarantias;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Garantia;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarantiasMenu extends Fragment {

    OperacionesDB D_B;
    String id_formato;
    SGarantias Formato;
    int completo;
    ImageView im1 ,im2,im3,im4 ,im5,im6,im7;
    Button Terminar;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    InternetandVPN validaciones = new InternetandVPN();
    UsuarioD usuario;
    ImgB64 b64 = new ImgB64();
    progresoLoad alerta = new progresoLoad();
    String ArchivoGarantia="";
    SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");


    public GarantiasMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_garantias_menu, container, false);

        ((MainActivity) getActivity()).verCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);

        usuario = D_B.obtenerUsuario();

        if(id_formato.equals("NUEVO")){
            id_formato = D_B.newGarantia();
            D_B.nuevoRegistroFormato(id_formato,"4"); //crear registrode formato. BN1
            Formato = D_B.obtenerGarantia_id(id_formato);
        }else{
            Formato = D_B.obtenerGarantia_id(id_formato);
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


        //region Botones acciones

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



        //region Botones acciones
        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                GarantiasGenerales myfargment = new GarantiasGenerales();
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

                GarantiasGarantia myfargment = new GarantiasGarantia();
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

                GarantiasMateriales myfargment = new GarantiasMateriales();
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

                GarantiasFotos myfargment = new GarantiasFotos();
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

                Garantiasfirmas myfargment = new Garantiasfirmas();
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

                GarantiasAdicionales myfargment = new GarantiasAdicionales();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

//endregion




        completo = 6;
        //region Modulo  Generales
        int M1_generales = 11;

        try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        try{if(!Formato.getCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getDirección().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getContacto().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTeléfono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getMail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getProyecto().isEmpty() || !Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        //try{if(!Formato.getReferencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getSR().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getNTAG().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getMODELO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getNSERIE().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFECHAINICIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFECHAFIN().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
        else if(M1_generales < 11){ im1.setImageResource(R.drawable.generalnar);}
        else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo SERVICIOS
        int M2_I = 5;
        try {
            if (!Formato.getTIPOGARANTIA().isEmpty()) {
                if (Formato.getTIPOGARANTIA().equals("Reemplazo")) {
                    try {
                        if (!Formato.getNUEVOSERIAL().isEmpty()) {
                            M2_I = M2_I - 1;
                        }
                    } catch (Exception e) {
                    }
                } else {
                    M2_I = M2_I - 1;
                }
            }
        }catch (Exception e){}
        try{if(!Formato.getFOLIOCCC().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        //try{if(!Formato.getFECHALOTE().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        //try{if(!Formato.getADUANA().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}

        try{if(!Formato.getREPORTEFALLA().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getACCIONCORRECTIVA().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCOMENTARIOS().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        //try{if(!Formato.getGarantiaArchivo().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}

        if(M2_I == 0){ im2.setImageResource(R.drawable.garantiaverde);
            completo = completo-1;}
        else if(M2_I < 5){im2.setImageResource(R.drawable.garantianar);}
        else {im2.setImageResource(R.drawable.garantiagris);}


        //endregion

       //region Modulo Materiales
        int M5_A = 27;
        int M5_B = 15;

        try{if(Formato.getMATERIALESUTILIZADOS().equals("N/A")){
            M5_A = M5_A - 27;
        }
        else{
            try{if(!Formato.getCANTIDAD1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getCANTIDAD9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getNPARTE9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!Formato.getESPECIFICACION9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}

        }}catch(Exception e){}
        try{if(Formato.getEQUIPOMEDICION().equals("N/A")){
            M5_B = M5_B - 15;
        }
        else{
            try{if(!Formato.getEQUIPO1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoID1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFECHA1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEQUIPO2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoID2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFECHA2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEQUIPO3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoID3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFECHA3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEQUIPO4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoID4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFECHA4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getEQUIPO5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getNoID5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!Formato.getFECHA5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
        }}catch(Exception e){}

        if((M5_A + M5_B) == 0){ im3.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else if((M5_A < 27 && M5_B==15) || (M5_A == 27 && M5_B<15) ){
            im3.setImageResource(R.drawable.materialenar);
        }
        else if(M5_A <27 && M5_B <15){
            im3.setImageResource(R.drawable.materialesverde);
            completo = completo-1;
        }
        else {im3.setImageResource(R.drawable.materialesgris);}

        //endregion




        //region Modulo Fotos
        int M6_ = 12;
        int M_Foto1 = 2;
        int M_Foto2 = 2;
        int M_Foto3 = 2;
        int M_Foto4 = 2;
        int M_Foto5 = 2;
        int M_Foto6 = 2;

        try{if(!Formato.getFOTODESPUES1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTODESPUES2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTODESPUES3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTODESPUES4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTODESPUES5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTODESPUES6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!Formato.getFOTOANTES6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

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

        int M7_ = 2;
        int obliga =2;
        int M7_Fr = 0;

        try{if(!Formato.getIMAGENFIRMA3().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFIRMA13().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getIMAGENFIRMA1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFIRMA1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getIMAGENFIRMA2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getFIRMA2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getCLIENTEFINAL_EMPRESA().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!Formato.getcLIENTEFINAL_TELEFONO().isEmpty()){M7_Fr++;}}catch (Exception e){}

        try{
            if(!Formato.getIMAGENFIRMA3().isEmpty() && !Formato.getFIRMA13().isEmpty()){
                try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()&&
                        !Formato.getCLIENTEFINAL_EMPRESA().isEmpty()&&
                        !Formato.getcLIENTEFINAL_TELEFONO().isEmpty()){M7_ = M7_-1;}
                }catch (Exception e){}{
                }
            }
        }catch (Exception e){}

        try {
            if (!Formato.getIMAGENFIRMA1().isEmpty()&& !Formato.getFIRMA1().isEmpty()){ M7_ = M7_-1;   }
        } catch (Exception e) {
        }

        try {
            if (!Formato.getIMAGENFIRMA2().isEmpty()){ obliga = obliga-1;   }
            if (!Formato.getFIRMA2().isEmpty()){ obliga = obliga-1;   }
        } catch (Exception e) {
        }

        if(obliga == 0){
            if(M7_== 0 || M7_ == 1 ){im5.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im5.setImageResource(R.drawable.firmanar);}
            else {im6.setImageResource(R.drawable.firmagris);}
        }
        else if(M7_ < 2){im5.setImageResource(R.drawable.firmanar);}
        else {
            if(M7_Fr>0){
                im5.setImageResource(R.drawable.firmanar);
            }else{
                im5.setImageResource(R.drawable.firmagris);
            }
        }

        //endregion

        //region Modulo Adicionales
        int M8_ = 10;

        //try{if(!Formato..getADICI_NOMBRE1().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}



        if(M8_ < 10){im6.setImageResource(R.drawable.notificaverde);
            completo = completo-1;}
        else {im6.setImageResource(R.drawable.notificagris);}
        //endregion


        if(completo == 0){
            D_B.actualizarEsatusFormato("0",id_formato);
            Terminar.setBackgroundResource(R.drawable.btnnaranja);
            Terminar.setEnabled(true);
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
    Garantia item = new Garantia();
    public void enviarFormato(){
       // Garantia item = new Garantia();
        final String idFormatoService = D_B.generarUID();
        item.setIdGarantia(idFormatoService);

        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{
            item.setSRProyecto(Formato.getSR());
        }
        //item.setIdG
        item.setFolioPreTrabajo(Formato.getFolioPreTrabajo());
        item.setFechaInicio(Formato.getFECHAINICIO());
        item.setFechaTermino(Formato.getFECHAFIN());
        item.setIdCliente(Formato.getCliente());
        //item.setSRProyecto(Formato.getSR());
        item.setTASK(Formato.getTASK());
        item.setContactoCliente(Formato.getContacto());
        item.setTipoServicio(Formato.getTIPOSERVICIO());
        item.setTelefono(Formato.getTeléfono());
        item.setFreecuencia(Formato.getFRECUENCIA());
        item.setEMail(Formato.getMail());
        item.setNoTag(Formato.getNTAG());
        item.setModeloEquipo(Formato.getMODELO());
        item.setNoSerie(Formato.getNSERIE());
        item.setDireccionSitio(Formato.getDirección());
        item.setTipoGararntia(Formato.getTIPOGARANTIA());
        item.setFolioCCC(Formato.getFOLIOCCC());
        item.setFehcaLote(Formato.getFECHALOTE());
        item.setLotePedimento(Formato.getLOTE());
        item.setAduana(Formato.getADUANA());
        item.setNuevoSERIAL(Formato.getNUEVOSERIAL());
        item.setReporteFalla(Formato.getREPORTEFALLA());
        item.setComentariosRecomendaciones(Formato.getCOMENTARIOS());
        item.setCantidad1(Formato.getCANTIDAD1());
        item.setCantidad2(Formato.getCANTIDAD2());
        item.setCantidad3(Formato.getCANTIDAD3());
        item.setCantidad4(Formato.getCANTIDAD4());
        item.setCantidad5(Formato.getCANTIDAD5());
        item.setCantidad6(Formato.getCANTIDAD6());
        item.setCantidad7(Formato.getCANTIDAD7());
        item.setCantidad8(Formato.getCANTIDAD8());
        item.setCantidad9(Formato.getCANTIDAD9());
        item.setNoParte1(Formato.getNPARTE1());
        item.setNoParte2(Formato.getNPARTE2());
        item.setNoParte3(Formato.getNPARTE3());
        item.setNoParte4(Formato.getNPARTE4());
        item.setNoParte5(Formato.getNPARTE5());
        item.setNoParte6(Formato.getNPARTE6());
        item.setNoParte7(Formato.getNPARTE7());
        item.setNoParte8(Formato.getNPARTE8());
        item.setNoParte9(Formato.getNPARTE9());
        item.setDescripcion1(Formato.getESPECIFICACION1());
        item.setDescripcion2(Formato.getESPECIFICACION2());
        item.setDescripcion3(Formato.getESPECIFICACION3());
        item.setDescripcion4(Formato.getESPECIFICACION4());
        item.setDescripcion5(Formato.getESPECIFICACION5());
        item.setDescripcion6(Formato.getESPECIFICACION6());
        item.setDescripcion7(Formato.getESPECIFICACION7());
        item.setDescripcion8(Formato.getESPECIFICACION8());
        item.setDescripcion9(Formato.getESPECIFICACION9());
        item.setEquipo1(Formato.getEQUIPO1());
        item.setEquipo2(Formato.getEQUIPO2());
        item.setEquipo3(Formato.getEQUIPO3());
        item.setEquipo4(Formato.getEQUIPO4());
        item.setEquipo5(Formato.getEQUIPO5());

        item.setNoId1(Formato.getNoID1());
        item.setNoId2(Formato.getNoID2());
        item.setNoId3(Formato.getNoID3());
        item.setNoId4(Formato.getNoID4());
        item.setNoId5(Formato.getNoID5());

        item.setFechaVencimiento1(Formato.getFECHA1());
        item.setFechaVencimiento2(Formato.getFECHA2());
        item.setFechaVencimiento3(Formato.getFECHA3());
        item.setFechaVencimiento4(Formato.getFECHA4());
        item.setFechaVencimiento5(Formato.getFECHA5());

        item.setAntesFoto1(Formato.getFOTOANTES1());
        item.setAntesFoto2(Formato.getFOTOANTES2());
        item.setAntesFoto3(Formato.getFOTOANTES3());
        item.setAntesFoto4(Formato.getFOTOANTES4());
        item.setAntesFoto5(Formato.getFOTOANTES5());
        item.setAntesFoto6(Formato.getFOTOANTES6());
        item.setDespuesFoto1(Formato.getFOTODESPUES1());
        item.setDespuesFoto2(Formato.getFOTODESPUES2());
        item.setDespuesFoto3(Formato.getFOTODESPUES3());
        item.setDespuesFoto4(Formato.getFOTODESPUES4());
        item.setDespuesFoto5(Formato.getFOTODESPUES5());
        item.setDespuesFoto6(Formato.getFOTODESPUES6());

        item.setFirmaCliente(Formato.getIMAGENFIRMA1());
        item.setFirmaVertiv(Formato.getIMAGENFIRMA2());
        item.setFirmaClienteFinal(Formato.getIMAGENFIRMA3());

        item.setNombreFirmaCliente(Formato.getFIRMA1());
        item.setNombreFirmaVertiv(Formato.getFIRMA2());
        item.setNombreFirmaClienteFinal(Formato.getFIRMA13());
        item.setAD_NOMBRE1(Formato.getAD_NOMBRE1());
        item.setAD_CORREO1(Formato.getAD_CORREO1());
        item.setAD_NOMBRE2(Formato.getAD_NOMBRE2());
        item.setAD_CORREO2(Formato.getAD_CORREO2());
        item.setAD_NOMBRE3(Formato.getAD_NOMBRE3());
        item.setAD_CORREO3(Formato.getAD_CORREO3());
        item.setAD_NOMBRE4(Formato.getAD_NOMBRE4());
        item.setAD_CORREO4(Formato.getAD_CORREO4());
        item.setAD_NOMBRE5(Formato.getAD_NOMBRE5());
        item.setAD_CORREO5(Formato.getAD_CORREO5());
        item.setCLIENTEFINAL_EMPRESA(Formato.getCLIENTEFINAL_EMPRESA());
        item.setCLIENTEFINAL_TELEFONO(Formato.getcLIENTEFINAL_TELEFONO());
        item.setCLIENTEFINAL_CORREO(Formato.getCLIENTEFINAL_CORREO());

        //ArchivoGarantia= "imagen"+df.format(new Date().getTime()+".jpg");
        //Formato.setGarantiaArchivo(ArchivoGarantia);

        item.setGarantiaArchivo(Formato.getGarantiaArchivo());


        item.setACCIONCORRECTIVA(Formato.getACCIONCORRECTIVA());

        item.setIdUsuario(usuario.getID_USER());

            item.setAntesFoto1((Formato.getFOTOANTES1()));
            item.setAntesFoto2((Formato.getFOTOANTES2()));
            item.setAntesFoto3((Formato.getFOTOANTES3()));
            item.setAntesFoto4((Formato.getFOTOANTES4()));
            item.setAntesFoto5((Formato.getFOTOANTES5()));
            item.setAntesFoto6((Formato.getFOTOANTES6()));
            item.setDespuesFoto1((Formato.getFOTODESPUES1()));
            item.setDespuesFoto2((Formato.getFOTODESPUES2()));
            item.setDespuesFoto3((Formato.getFOTODESPUES3()));
            item.setDespuesFoto4((Formato.getFOTODESPUES4()));
            item.setDespuesFoto5((Formato.getFOTODESPUES5()));
            item.setDespuesFoto6((Formato.getFOTODESPUES6()));



        List<FotosFormato> listaFotos = new ArrayList<FotosFormato>();
/*
        try{if(item.getAntesFoto1().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setFormato("6");
            itemF.setId("");
            itemF.setAntes1(b64.ImgB64(item.getAntesFoto1()));
            listaFotos.add(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto2().contains(".jpg")){
            item.setAntesFoto2(b64.ImgB64(item.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto3().contains(".jpg")){
            item.setAntesFoto3(b64.ImgB64(item.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto4().contains(".jpg")){
            item.setAntesFoto4(b64.ImgB64(item.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto5().contains(".jpg")){
            item.setAntesFoto5(b64.ImgB64(item.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto6().contains(".jpg")){
            item.setAntesFoto6(b64.ImgB64(item.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto1().contains(".jpg")){
            item.setDespuesFoto1(b64.ImgB64(item.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto2().contains(".jpg")){
            item.setDespuesFoto2(b64.ImgB64(item.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto3().contains(".jpg")){
            item.setDespuesFoto3(b64.ImgB64(item.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto4().contains(".jpg")){
            item.setDespuesFoto4(b64.ImgB64(item.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto5().contains(".jpg")){
            item.setDespuesFoto5(b64.ImgB64(item.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto6().contains(".jpg")){
            item.setDespuesFoto6(b64.ImgB64(item.getDespuesFoto6()));
        }}catch (Exception e){}
*/
        //region enviar formato
        item.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarGarantia(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Garantia>() {
                    @Override
                    public void accept(Garantia lista) throws Exception {
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

        try{if(item.getAntesFoto1().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto1(b64.ImgB64(item.getAntesFoto1()));
            itemF.setFormato(item.getAntesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto2().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto2(b64.ImgB64(item.getAntesFoto2()));
            itemF.setFormato(item.getAntesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto3().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto3(b64.ImgB64(item.getAntesFoto3()));
            itemF.setFormato(item.getAntesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto4().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto4(b64.ImgB64(item.getAntesFoto4()));
            itemF.setFormato(item.getAntesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto5().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto5(b64.ImgB64(item.getAntesFoto5()));
            itemF.setFormato(item.getAntesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getAntesFoto6().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setAntesFoto6(b64.ImgB64(item.getAntesFoto6()));
            itemF.setFormato(item.getAntesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto1().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto1(b64.ImgB64(item.getDespuesFoto1()));
            itemF.setFormato(item.getDespuesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto2().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto2(b64.ImgB64(item.getDespuesFoto2()));
            itemF.setFormato(item.getDespuesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto3().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto3(b64.ImgB64(item.getDespuesFoto3()));
            itemF.setFormato(item.getDespuesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto4().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto4(b64.ImgB64(item.getDespuesFoto4()));
            itemF.setFormato(item.getDespuesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto5().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto5(b64.ImgB64(item.getDespuesFoto5()));
            itemF.setFormato(item.getDespuesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}
        try{if(item.getDespuesFoto6().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setDespuesFoto6(b64.ImgB64(item.getDespuesFoto6()));
            itemF.setFormato(item.getDespuesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);

        }}catch (Exception e){}

        try{if(Formato.getGarantiaArchivo().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setGarantiaArchivo(b64.ImgB64(Formato.getGarantiaArchivo()));
            itemF.setFormato(Formato.getGarantiaArchivo());
            ((MainActivity) getActivity()).imagenesEnvio(itemF);

        }}catch (Exception e){}
        //endregion
    }


}
