package com.maju.desarrollo.testfcs.Formatos.Servicios;


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
import com.maju.desarrollo.testfcs.SQLite.Model.Servicios;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.GeneralServicios;
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
public class ServiciosMenu extends Fragment {

    OperacionesDB D_B;
    String id_formato;
    Servicios Formato;
    int completo;
    ImageView im1 ,im2,im3,im4 ,im5,im6,im7;
    Button Terminar;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImgB64 b64 = new ImgB64();
    InternetandVPN validaciones =  new InternetandVPN();
    UsuarioD usuario;
    progresoLoad alerta = new progresoLoad();


    public ServiciosMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_servicios_menu, container, false);

        ((MainActivity) getActivity()).verCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();
        //id_formato = "Key-4871f01c-c092-4edd-b920-bb1c9fd1f781";
        if(id_formato.equals("NUEVO")){
            id_formato = D_B.newServicio();
            D_B.nuevoRegistroFormato(id_formato,"8"); //crear registrode formato. BN1
            Formato = D_B.obtenerServicio(id_formato);
        }else{
            Formato = D_B.obtenerServicio(id_formato);
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

                //quitar



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




        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                ServiciosGral myfargment = new ServiciosGral();
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

                ServiciosServicio myfargment = new ServiciosServicio();
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

                ServiciosMateriales myfargment = new ServiciosMateriales();
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

                ServiciosFotos myfargment = new ServiciosFotos();
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

                ServiciosFirmas myfargment = new ServiciosFirmas();
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

                ServiciosAdicionales myfargment = new ServiciosAdicionales();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

        completo = 6;
        //region Modulo  Generales
        int M1_generales = 13;

        try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getDirección().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getContacto().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTelefono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getMail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getTIPOSERVICIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getTIPOSERVICIO().isEmpty()){
            if(Formato.getTIPOSERVICIO().equals("Otro (Especifique)")){
                if(!Formato.getESPECICIFACION().isEmpty()){M1_generales = M1_generales - 1;}
            }else{
                M1_generales = M1_generales - 1;
            }
        }
        }catch (Exception e){}


        try{if(!Formato.getFRECUENCIA().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getProyecto().isEmpty() || !Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getSR().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getESPECICIFACION().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getARRANCOVERTIV().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getNTAG().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getMODELO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getNSERIE().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!Formato.getNSERIE2().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFECHAINICIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getFECHAFIN().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
         else if(M1_generales < 13){ im1.setImageResource(R.drawable.generalnar);}
             else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo SERVICIOS
        int M2_I = 2;
        try{if(!Formato.getACTIVIDAD().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getCOMENTARIOS().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}

        if(M2_I == 0){ im2.setImageResource(R.drawable.servicioverde);
            completo = completo-1;}
        else if(M2_I < 2){im2.setImageResource(R.drawable.servicionar);}
        else {im2.setImageResource(R.drawable.serviciogris);}


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
            else {im5.setImageResource(R.drawable.firmagris);}
        }
        else if(M7_ < 2){im5.setImageResource(R.drawable.firmanar);}
        else {
            if(M7_Fr>0){
                im5.setImageResource(R.drawable.firmanar);
            }else{
                im5.setImageResource(R.drawable.firmagris);
            }
        }


        /*
        if (!Formato.getIMAGENFIRMA1().isEmpty() && !Formato.getFIRMA1().isEmpty()) {
            M7_ = M7_ - 1;
        }

        if((CLienteF == 1 || CLienteF == 3)) {
            try {
                if(CLienteF == 1){ M7_ = M7_ - 1;}
                if(otraF ==0){ M7_ = M7_ - 1;}
                if (!Formato.getIMAGENFIRMA1().isEmpty() && !Formato.getFIRMA1().isEmpty()) {
                    M7_ = M7_ - 1;
                }
            } catch (Exception e) {
            }

            if(M7_== 0 && M7_ ==1){im5.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im5.setImageResource(R.drawable.firmanar);}
            else {im5.setImageResource(R.drawable.firmagris);}
        }
        else{
            im5.setImageResource(R.drawable.firmanar);
        }
*/



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

    GeneralServicios item;
    public void enviarFormato(){
        item =D_B.generarServiciosparaEnvioServer(Formato);
        final String idFormatoService = D_B.generarUID();
        item.setIdFormatoGeneralServicios(idFormatoService);
        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{item.setSRProyecto(Formato.getSR());}

       /* try{if(Formato.getFOTOANTES1().contains(".jpg")){
            item.setAntes1(b64.ImgB64(Formato.getFOTOANTES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES2().contains(".jpg")){
            item.setAntes2(b64.ImgB64(Formato.getFOTOANTES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES3().contains(".jpg")){
            item.setAntes3(b64.ImgB64(Formato.getFOTOANTES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES4().contains(".jpg")){
            item.setAntes4(b64.ImgB64(Formato.getFOTOANTES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES5().contains(".jpg")){
            item.setAntes5(b64.ImgB64(Formato.getFOTOANTES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES6().contains(".jpg")){
            item.setAntes6(b64.ImgB64(Formato.getFOTOANTES6()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES1().contains(".jpg")){
            item.setDespues1(b64.ImgB64(Formato.getFOTODESPUES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES2().contains(".jpg")){
            item.setDespues2(b64.ImgB64(Formato.getFOTODESPUES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES3().contains(".jpg")){
            item.setDespues3(b64.ImgB64(Formato.getFOTODESPUES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES4().contains(".jpg")){
            item.setDespues4(b64.ImgB64(Formato.getFOTODESPUES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES5().contains(".jpg")){
            item.setDespues5(b64.ImgB64(Formato.getFOTODESPUES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES6().contains(".jpg")){
            item.setDespues6(b64.ImgB64(Formato.getFOTODESPUES6()));
        }}catch (Exception e){}
        */

        enviarFotos(item.getFolioPreTrabajo());
        alerta.dismiss();


        //region envio de formato
        item.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarGServicios(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GeneralServicios>() {
                    @Override
                    public void accept(GeneralServicios lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if(lista.getExitoso().equals("1")) {
                            enviarFotos(lista.getFolioPreTrabajo());
                            D_B.actualizarEsatusFormato("3",id_formato);

                            //Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();
                            Bundle valores = new Bundle();
                            valores.putString("mensaje","Folio:" + lista.getFolioPreTrabajo().toString());
                            AlertaOk alerta2 = new AlertaOk();
                            alerta2.setArguments(valores);
                            alerta2.setCancelable(false);
                            alerta2.show(getFragmentManager(), "a");

                            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                            FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                            // Toast.makeText(getContext(), "Formato Enviado.", Toast.LENGTH_SHORT).show();


                            alerta.dismiss();

                        }
                        else{
                            alerta.dismiss();
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

    public void enviarFotos(String idFormatoService){
        //region Enviar_Fotos

        try{if(Formato.getFOTOANTES1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes1(b64.ImgB64(Formato.getFOTOANTES1()));
            item.setFormato(Formato.getFOTOANTES1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes2(b64.ImgB64(Formato.getFOTOANTES2()));
            item.setFormato(Formato.getFOTOANTES2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes3(b64.ImgB64(Formato.getFOTOANTES3()));
            item.setFormato(Formato.getFOTOANTES3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes4(b64.ImgB64(Formato.getFOTOANTES4()));
            item.setFormato(Formato.getFOTOANTES4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes5(b64.ImgB64(Formato.getFOTOANTES5()));
            item.setFormato(Formato.getFOTOANTES5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntes6(b64.ImgB64(Formato.getFOTOANTES6()));
            item.setFormato(Formato.getFOTOANTES6());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues1(b64.ImgB64(Formato.getFOTODESPUES1()));
            item.setFormato(Formato.getFOTODESPUES1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues2(b64.ImgB64(Formato.getFOTODESPUES2()));
            item.setFormato(Formato.getFOTODESPUES2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues3(b64.ImgB64(Formato.getFOTODESPUES3()));
            item.setFormato(Formato.getFOTODESPUES3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues4(b64.ImgB64(Formato.getFOTODESPUES4()));
            item.setFormato(Formato.getFOTODESPUES4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues5(b64.ImgB64(Formato.getFOTODESPUES5()));
            item.setFormato(Formato.getFOTODESPUES5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespues6(b64.ImgB64(Formato.getFOTODESPUES6()));
            item.setFormato(Formato.getFOTODESPUES6());
            ((MainActivity) getActivity()).imagenesEnvio(item);

        }}catch (Exception e){}
        //endregion
    }

}
