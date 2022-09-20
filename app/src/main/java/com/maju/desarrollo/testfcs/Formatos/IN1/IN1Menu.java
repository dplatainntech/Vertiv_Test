package com.maju.desarrollo.testfcs.Formatos.IN1;


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
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;
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
public class IN1Menu extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    Bestel1 Formato;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImageView im1 ,im2,im3,im4 ,im5,im6,im7;
    int completo;
    Button Terminar;
    InternetandVPN validaciones = new InternetandVPN();
    ImgB64 b64 = new ImgB64();
    UsuarioD usuario;
    //region Respuestas
    String R1,
            R2,
            R3,
            R4,
            R5,
            R6,
            R7,
            R8,
            R9,
            R10,
            R11,
            R12,
            R13,
            R14,
            R15,
            R16,
            R17,
            R18,
            R19,
            R20,
            R21,
            R22,
            R23,
            R24,
            R25,
            R26,
            R27,
            R28,
            R29,
            R30,
            R31,
            R32,
            R33,
            R34,
            R35,
            R36,
            R37,
            R38,
            R39,
            R40,
            R41,
            R42,
            R43,
            R44,
            R45,
            R46,
            R47,
            R48,
            R49,
            R50,
            R51,
            R52,
            R53,
            R54,
            R55,R56;
//endregion
progresoLoad alerta = new progresoLoad();


    public IN1Menu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_in1_menu, container, false);
        ((MainActivity) getActivity()).verCabecera();

        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();
        //id_formato = "Key-9b595303-064f-4685-8a43-0b21665d3c58";

        if(id_formato.equals("NUEVO")){
            //se crea nueva Formato y se regresa el id
            id_formato = D_B.newBestel1();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"5"); //crear registrode formato. BN1
            Formato = D_B.obtenerBestel1(id_formato);
        }else{
            Formato = D_B.obtenerBestel1(id_formato);
        }

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

        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.MN_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.MN_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.MN_M3);
        LinearLayout M4 = (LinearLayout)v.findViewById(R.id.MN_M4);
        LinearLayout M5 = (LinearLayout)v.findViewById(R.id.MN_M5);
        LinearLayout M6 = (LinearLayout)v.findViewById(R.id.MN_M6);
        LinearLayout M7 = (LinearLayout)v.findViewById(R.id.MN_M7);
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

                IN1Generales myfargment = new IN1Generales();
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

                IN1Inspeccion myfargment = new IN1Inspeccion();
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

                IN1Materiales myfargment = new IN1Materiales();
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

                IN1Evaluacion myfargment = new IN1Evaluacion();
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

                IN1Fotografias myfargment = new IN1Fotografias();
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

                IN1Firmas myfargment = new IN1Firmas();
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

                IN1Adicionales myfargment = new IN1Adicionales();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        verAcencesModulos();

        return v;

    }

    public void verAcencesModulos(){
        completo = 7;

        //region Modulo  Generales
        int M1_generales = 15;

         try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
         try{if(!Formato.getCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getRegion().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getEstado().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
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
        int M2_I = 56;
        /*
        try{if(!Formato.getAATemperaturaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAACondensadoraEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAEvaporadoraEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAASerpentinEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAFugaGasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAAlimentacionEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAAFiltrosEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getAALimpiezaGeneralEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECIluminaciionEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECPinturaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECPisosEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECImpermeEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECHidrosanitarioEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECHerrejesEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getECLimpiezaGeneralEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBAmperajeEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBConsumoPlantaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBRectificadoresEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBSistemaInversorEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBBancosBateriasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPFBTablerosEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUAlimentacionElectricaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUAlarmasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUCargaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSUDescargaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCISistemaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCIDetectoresEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCIExtintoresEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCIGranadaTanquesEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSCIFechaCaducidadEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEFugasAceiteEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEFiltrosEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPETemperaturaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEBandasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEBateriasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPELubricacionEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPECombustibleEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPEArranqueManualEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getPELimpiezaGenetalEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSALectorasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSATablerosControlEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSBarrasPuestaTierraEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSConexionPuestaTierraEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSTransformadorEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSFusiblesEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSTemperaturaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSCuchillasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getSSInterruptoresEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTELimpizaGeneralEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTEAnclasRetenidosEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTELucesObstruccionEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTETornilleriaEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTEPuestaTierraEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getTESistemaApartaRayosEststus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHFugasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
        try{if(!Formato.getHBombasEstatus().isEmpty()){M2_I = M2_I - 1;}}catch (Exception e){}
*/
        //region obtenerDatosGuargadoos
        R1=Formato.getAATemperaturaEstatus();
        R2=Formato.getAACondensadoraEstatus();
        R3=Formato.getAAEvaporadoraEstatus();
        R4=Formato.getAASerpentinEstatus();
        R5=Formato.getAAFugaGasEstatus();
        R6=Formato.getAAAlimentacionEstatus();
        R7=Formato.getAAFiltrosEstatus();
        R8=Formato.getAALimpiezaGeneralEstatus();
        R9=Formato.getECIluminaciionEstatus();
        R10=Formato.getECPinturaEstatus();
        R11=Formato.getECPisosEstatus();
        R12=Formato.getECImpermeEstatus();
        R13=Formato.getECHidrosanitarioEstatus();
        R14=Formato.getECHerrejesEstatus();
        R15=Formato.getECLimpiezaGeneralEstatus();
        R16=Formato.getPFBAmperajeEstatus();
        R17=Formato.getPFBConsumoPlantaEstatus();
        R18=Formato.getPFBRectificadoresEstatus();
        R19=Formato.getPFBSistemaInversorEstatus();
        R20=Formato.getPFBBancosBateriasEstatus();
        R21=Formato.getPFBTablerosEstatus();
        R22=Formato.getSUAlimentacionElectricaEstatus();
        R23=Formato.getSUAlarmasEstatus();
        R24=Formato.getSUCargaEstatus();
        R25=Formato.getSUDescargaEstatus();
        R26=Formato.getSCISistemaEstatus();
        R27=Formato.getSCIDetectoresEstatus();
        R28=Formato.getSCIExtintoresEstatus();
        R29=Formato.getSCIGranadaTanquesEstatus();
        R30=Formato.getSCIFechaCaducidadEstatus();
        R31=Formato.getPEFugasAceiteEstatus();
        R32=Formato.getPEFiltrosEstatus();
        R33=Formato.getPETemperaturaEstatus();
        R34=Formato.getPEBandasEstatus();
        R35=Formato.getPEBateriasEstatus();
        R36=Formato.getPELubricacionEstatus();
        R37=Formato.getPECombustibleEstatus();
        R38=Formato.getPEArranqueManualEstatus();
        R39=Formato.getPELimpiezaGenetalEstatus();
        R40=Formato.getSALectorasEstatus();
        R41=Formato.getSATablerosControlEstatus();
        R42=Formato.getSSBarrasPuestaTierraEstatus();
        R43=Formato.getSSConexionPuestaTierraEstatus();
        R44=Formato.getSSTransformadorEstatus();
        R45=Formato.getSSFusiblesEstatus();
        R46=Formato.getSSTemperaturaEstatus();
        R47=Formato.getSSCuchillasEstatus();
        R48=Formato.getSSInterruptoresEstatus();
        R49=Formato.getTELimpizaGeneralEstatus();
        R50=Formato.getTEAnclasRetenidosEstatus();
        R51=Formato.getTELucesObstruccionEstatus();
        R52=Formato.getTETornilleriaEstatus();
        R53=Formato.getTEPuestaTierraEstatus();
        R54=Formato.getTESistemaApartaRayosEststus();
        R55=Formato.getHFugasEstatus();
        R56=Formato.getHBombasEstatus();

        //endregion
        //region Validaciones Colores en boton
        //try{if(R26.length()>0){if(R26.equals("M") && !Formato.getAATemperaturaComentarios().isEmpty()){m5= m5-1;}else{m5= m5-1;}}}catch(Exception e){}
        int m1 = 8;
        try{if(R1.length()>0){if(R1.equals("M") ){if(Formato.getAATemperaturaComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R2.length()>0){if(R2.equals("M") ){if( Formato.getAACondensadoraComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R3.length()>0){if(R3.equals("M") ){if( Formato.getAAEvaporadoraComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R4.length()>0){if(R4.equals("M") ){if( Formato.getAASerpentinComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R5.length()>0){if(R5.equals("M") ){if( Formato.getAAFugaGasComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R6.length()>0){if(R6.equals("M") ){if( Formato.getAAAlimentacionComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R7.length()>0){if(R7.equals("M") ){if( Formato.getAAFiltrosComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}
        try{if(R8.length()>0){if(R8.equals("M") ){if( Formato.getAALimpiezaGeneralComentarios().length()>0){m1= m1-1;}}else{m1= m1-1;}}}catch(Exception e){}


        int m2 = 7;
        try{if(R9.length()>0){if(R9.equals("M") ){if( Formato.getECIluminaciionComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R10.length()>0){if(R10.equals("M") ){if( Formato.getECPinturaComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R11.length()>0){if(R11.equals("M") ){if( Formato.getECPisosComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R12.length()>0){if(R12.equals("M") ){if( Formato.getECImpermeComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R13.length()>0){if(R13.equals("M") ){if( Formato.getECHidrosanitarioComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R14.length()>0){if(R14.equals("M") ){if( Formato.getECHerrejesComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R15.length()>0){if(R15.equals("M") ){if( Formato.getECLimpiezaGeneralComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}



        int m3 = 6;
        try{if(R16.length()>0){if(R16.equals("M") ){if( Formato.getPFBAmperajeComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R17.length()>0){if(R17.equals("M") ){if( Formato.getPFBConsumoPlantaComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R18.length()>0){if(R18.equals("M") ){if( Formato.getPFBRectificadoresComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R19.length()>0){if(R19.equals("M") ){if( Formato.getPFBSistemaInversorComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R20.length()>0){if(R20.equals("M") ){if( Formato.getPFBBancosBateriasComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R21.length()>0){if(R21.equals("M") ){if( Formato.getPFBTablerosComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}



        int m4= 4;
        try{if(R22.length()>0){if(R22.equals("M") ){if( Formato.getSUAlimentacionElectricaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R23.length()>0){if(R23.equals("M") ){if( Formato.getSUAlarmasComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R24.length()>0){if(R24.equals("M") ){if( Formato.getSUCargaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R25.length()>0){if(R25.equals("M") ){if( Formato.getSUDescargaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}

        int m5 = 5;
        try{if(R26.length()>0){if(R26.equals("M") ){if( Formato.getSCISistemaComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R27.length()>0){if(R27.equals("M") ){if( Formato.getSCIDetectoresComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R28.length()>0){if(R28.equals("M") ){if( Formato.getSCIExtintoresComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R29.length()>0){if(R29.equals("M") ){if( Formato.getSCIGranadaTanquesComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R30.length()>0){if(R30.equals("M") ){if( Formato.getSCIFechaCaducidadComentraios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}

        int m6= 9;
        try{if(R31.length()>0){if(R31.equals("M") ){if( Formato.getPEFugasAceiteComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R32.length()>0){if(R32.equals("M") ){if( Formato.getPEFiltrosComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R33.length()>0){if(R33.equals("M") ){if( Formato.getPETemperaturaComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R34.length()>0){if(R34.equals("M") ){if( Formato.getPEBandasComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R35.length()>0){if(R35.equals("M") ){if( Formato.getPEBateriasComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R36.length()>0){if(R36.equals("M") ){if( Formato.getPELubricacionComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R37.length()>0){if(R37.equals("M") ){if( Formato.getPECombustibleComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R38.length()>0){if(R38.equals("M") ){if( Formato.getPEArranqueManualComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}
        try{if(R39.length()>0){if(R39.equals("M") ){if( Formato.getPELimpiezaGenetalComentarios().length()>0){m6= m6-1;}}else{m6= m6-1;}}}catch(Exception e){}

        int m7 = 2;
        try{if(R40.length()>0){if(R40.equals("M") ){if( Formato.getSALectorasComentarios().length()>0){m7= m7-1;}}else{m7= m7-1;}}}catch(Exception e){}
        try{if(R41.length()>0){if(R41.equals("M") ){if( Formato.getSATablerosControlComentarios().length()>0){m7= m7-1;}}else{m7= m7-1;}}}catch(Exception e){}

        int m8 = 7;
        try{if(R42.length()>0){if(R42.equals("M") ){if( Formato.getSSBarrasPuestaTierraComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R43.length()>0){if(R43.equals("M") ){if( Formato.getSSConexionPuestaTierraComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R44.length()>0){if(R44.equals("M") ){if( Formato.getSSTransformadorComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R45.length()>0){if(R45.equals("M") ){if( Formato.getSSFusiblesComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R46.length()>0){if(R46.equals("M") ){if( Formato.getSSTemperaturaComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R47.length()>0){if(R47.equals("M") ){if( Formato.getSSCuchillasComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R48.length()>0){if(R48.equals("M") ){if( Formato.getSSInterruptoresComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}

        int m9=6;
        try{if(R49.length()>0){if(R49.equals("M") ){if( Formato.getTELimpizaGeneralComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R50.length()>0){if(R50.equals("M") ){if( Formato.getTEAnclasRetenidosComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R51.length()>0){if(R51.equals("M") ){if( Formato.getTELucesObstruccionComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R52.length()>0){if(R52.equals("M") ){if( Formato.getTETornilleriaComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R53.length()>0){if(R53.equals("M") ){if( Formato.getTEPuestaTierraComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R54.length()>0){if(R54.equals("M") ){if( Formato.getTESistemaApartaRayosComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}


        int m10 = 2;
        try{if(R55.length()>0){if(R55.equals("M") ){if( Formato.getHFugasComentarios().length()>0){m10= m10-1;}}else{m10= m10-1;}}}catch(Exception e){}
        try{if(R56.length()>0){if(R56.equals("M") ){if( Formato.getHBombasComentarios().length()>0){m10= m10-1;}}else{m10= m10-1;}}}catch(Exception e){}


        int m11 = 1;

        try{if(Formato.getOtros7().length()>0 && Formato.getComentarios7().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros6().length()>0 && Formato.getComentarios6().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros5().length()>0 && Formato.getComentarios5().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros4().length()>0 && Formato.getComentarios4().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros3().length()>0 && Formato.getComentarios3().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros2().length()>0 && Formato.getComentarios2().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros1().length()>0 && Formato.getComentarios1().length()>0){m11= 0;}}catch(Exception e){}
        try{if(Formato.getOtros().length()>0 && Formato.getComentarios().length()>0){m11= 0;}}catch(Exception e){}

        //endregion
        int variablex = m1 + m2+ m3+ m4 + m5 + m6 + m7 + m8 + m9 + m10 + m11 ;

        if(variablex == 0){ im2.setImageResource(R.drawable.actividadesver);
            completo = completo-1;}
        else if(variablex < 57){im2.setImageResource(R.drawable.actividadesnar);}
        else {im2.setImageResource(R.drawable.actividadesgris);}


        //endregion

        //region Modulo Materiales
        int M5_ = 1;
        try{if(!Formato.getMateriales().isEmpty()){M5_ = M5_- 1;}}catch (Exception e){}
        if(M5_ == 0){ im3.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else {im3.setImageResource(R.drawable.materialesgris);}

        //endregion

        //region Modulo4
        int M4_ = 2;
        try{if(Formato.getEDSRTiempo().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        try{if(Formato.getEDSRCalidad().length()>1){M4_ = M4_ - 1;}}catch (Exception e){}
        //try{if(!Formato.isEDSREvalucionSitio()){M4_ = M4_ - 1;}}catch (Exception e){}

        if(M4_ == 0){ im4.setImageResource(R.drawable.inspeccionverd);
            completo = completo-1;}
        else if(M4_ < 2){ im4.setImageResource(R.drawable.inspeccionnar); }
        else {im4.setImageResource(R.drawable.inspecciongris);}

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
            im5.setImageResource(R.drawable.fotogris);
        }else{
            if(constante1== 0){
                im5.setImageResource(R.drawable.fotoverde);
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
                    im5.setImageResource(R.drawable.fotoverde);
                    completo = completo-1;
                }else {
                    im5.setImageResource(R.drawable.fotonar);
                }
            }
        }





        //endregion

        //region Modulo Firmas
        int M7_ = 4;
        try{if(!Formato.getFirmaCliente().isEmpty() ){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getFirmaVertiv().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaCliente().isEmpty() ){M7_ = M7_- 1;}}catch (Exception e){}
        try{if(!Formato.getNombreFirmaVertiv().isEmpty() ){M7_ = M7_- 1;}}catch (Exception e){}

        if(M7_ == 0){ im6.setImageResource(R.drawable.firmaverde);
            completo = completo-1;}
        else if(M7_ < 4){im6.setImageResource(R.drawable.firmanar);}
        else {im6.setImageResource(R.drawable.firmagris);}

        //endregion

        //region Modulo Adicionales
        int M8_ = 10;

        //try{if(!Formato..getADICI_NOMBRE1().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}


        //if(M8_ == 0){ im7.setImageResource(R.drawable.notificaverde); }

        try{if(!Formato.getAD_CORREO1().isEmpty() && !Formato.getAD_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO2().isEmpty() && !Formato.getAD_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO3().isEmpty() && !Formato.getAD_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO4().isEmpty() && !Formato.getAD_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!Formato.getAD_CORREO5().isEmpty() && !Formato.getAD_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}

        if(M8_ < 10){im7.setImageResource(R.drawable.notificaverde);
            completo = completo-1;}
        else {im7.setImageResource(R.drawable.notificagris);}
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
        Formato.setIdBestelNivel1(idFormatoService);
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}

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
        compositeDisposable.add(iMyAPI.enviarBestel1(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bestel1>() {
                    @Override
                    public void accept(Bestel1 lista) throws Exception {
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
