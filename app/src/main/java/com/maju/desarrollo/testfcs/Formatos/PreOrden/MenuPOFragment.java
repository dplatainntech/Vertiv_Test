package com.maju.desarrollo.testfcs.Formatos.PreOrden;

//211BDCCB-9B6C-4715-AB79-50D70ED1F4EE   MEXICO
//ca9825b5-22b3-4287-b516-093935bbe6e1  PERU
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.PreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPOFragment extends Fragment {
    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;
    ImageView actividades,herramientas,riesgos,prevencion,epp,emergencias,inspencion,firmas;
    ImageView generales;
    int completo;
    Button terminar;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    UsuarioD usuario;
    ImgB64 b64 = new ImgB64();
    InternetandVPN validaciones =  new InternetandVPN();
    progresoLoad alerta = new progresoLoad();

    public MenuPOFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_po, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        D_B.consultarCLientes();
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();
        id_PreOrden= getArguments().getString("key_idPreOrden");
        //id_PreOrden="Key-c31bf2a4-8c6b-4f75-a933-056423fa8c57";
        //Key-5ccefd0e-eece-4587-9391-236f99d0eed2
        if(id_PreOrden.equals("NUEVO")){
            //se crea nueva Pre Orden y se regresa el id
            id_PreOrden = D_B.nuevaPreOrden();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_PreOrden,"0"); //crear registrode formato. PT
            preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
        }else{
            preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
        }
        //Toast.makeText(getContext(), "entre otra ves", Toast.LENGTH_LONG).show();



        //region Botones
        terminar = (Button)v.findViewById(R.id.buttonTerminar);
        terminar.setEnabled(false);
        terminar.setOnClickListener(new View.OnClickListener() {
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

        generales = (ImageView)v.findViewById(R.id.btnGenerales);
        generales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Generales");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         actividades = (ImageView)v.findViewById(R.id.actividadesPO);
        actividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Actividades");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         herramientas = (ImageView)v.findViewById(R.id.herramientasPO);
        herramientas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Herramientas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);
            }
        });
         riesgos = (ImageView)v.findViewById(R.id.riesgosPO);
        riesgos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Riesgos");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         prevencion = (ImageView)v.findViewById(R.id.prevencionPO);
        prevencion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Prevencion");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         epp = (ImageView)v.findViewById(R.id.eppPO);
        epp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "EPP");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         emergencias = (ImageView)v.findViewById(R.id.emergenciasPO);
        emergencias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Emergencia");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         inspencion = (ImageView)v.findViewById(R.id.inspencionPO);
        inspencion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Inspeccion");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });
         firmas = (ImageView)v.findViewById(R.id.firmasPO);
        firmas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Firmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);

            }
        });


        //endregion

        verAcencesModulos();

        return v;
    }

    public void verAcencesModulos(){

        completo = 9;

        //generales,actividades,herramientas,riesgos,prevencion,epp,emergencias,inspencion,firmas;
        //region Modulo 1 Generales
        int M1_generales = 5;
        int M1_generales1 = 1;

        try{if(!preOrden.getGEN_PROYECTO().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}
        try{if(!preOrden.getGEN_SR().isEmpty()){M1_generales1 = M1_generales1 - 1;}}catch (Exception e){}

        try{if(!preOrden.getGEN_CLIENTE().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!preOrden.getGEN_SITIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!preOrden.getGEN_DIRECCION().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!preOrden.getGEN_FECHA().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!preOrden.getGEN_LIDER_GRUPO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

       int validador = M1_generales + M1_generales1;

        if(validador == 0){ generales.setImageResource(R.drawable.generalverd); completo = completo-1;}
        else if(validador < 6){generales.setImageResource(R.drawable.generalnar);}
        else {generales.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 2 Descripcion Actividades
        int M2_DescripcionAc = 1;
        try{if(!preOrden.getACTV_DESCRIPCION_ACTIVIDADES().isEmpty()){M2_DescripcionAc = M2_DescripcionAc - 1;}}catch (Exception e){}

        if(M2_DescripcionAc == 0){ actividades.setImageResource(R.drawable.actividadesver);
            completo = completo-1;}
        else if(M2_DescripcionAc < 1){actividades.setImageResource(R.drawable.actividadesnar);}
        else {actividades.setImageResource(R.drawable.actividadesgris);}
        //endregion

        //region Modulo 3 Herramientas
        int M3_Herramientas = 3;
        try{if(!preOrden.getHERRAM_DESCRIPCION_EHM().isEmpty()){M3_Herramientas = M3_Herramientas - 1;}}catch (Exception e){}
        try{if(!preOrden.getHERRAM_HOJAS_SEGURIDAD().isEmpty()){M3_Herramientas = M3_Herramientas - 1;}}catch (Exception e){}
        try{if(!preOrden.getHERRAM_DELIMITACION_AT().isEmpty()){M3_Herramientas = M3_Herramientas - 1;}}catch (Exception e){}

        if(M3_Herramientas == 0){ herramientas.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else if(M3_Herramientas < 3){herramientas.setImageResource(R.drawable.materialenar);}
        else {herramientas.setImageResource(R.drawable.materialesgris);}
        //endregion

        //region Modulo 4 Riesgos
        int M4_Riesgos = 23;
        try{if(!preOrden.getRIESGO_PARTICULAS().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_ATRAPAMIENTO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_GOLPES().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_QUEMADURAS().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_CAIDA_MATE().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_CAIDA_MISMO_NIVEL().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_CAIDA_DIST_NIVEL().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_LIMPIEZA_DEFI().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_OTRO_PERSONAL().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_CHOQUE_ELECTRICO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_ARCO_ELECT().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_FUEGO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_EXPO_RUIDO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_EXP_VIBRA().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_FATIGA_VISUAL().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_TEMPERATURAS().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_DEFI_OXIGENO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_GASES().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_POLVO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_SOBRE_ESFUERZO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_QUIMICOS().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_RUIDO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}
        try{if(!preOrden.getRIESGO_OTRO().isEmpty()){M4_Riesgos = M4_Riesgos - 1;}}catch (Exception e){}

        if(M4_Riesgos < 23){ riesgos.setImageResource(R.drawable.riesgosverd);
            completo = completo-1;}
        else {riesgos.setImageResource(R.drawable.riesgosgris);}
        //endregion

        //region Modulo 5 Prevencion
        int M5_Prevencion = 19;
        try{if(!preOrden.getPREVEN_DISPO_MECANICA().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_SUST_QUIMICOS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_AISLAR_RUIDO().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_PROTECTORES_MAQUINAS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_PLATA_ANDAMIOS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_SIS_PNTS_ANCLAJE().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_ILUMI_ART().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_DISYUNTOR().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_SIST_PUESTA_TIERRA().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_ORDEN_LIMPIEZA().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_AREA_TRABAJO().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_BE_FUENTES_ENERGIA().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_MUROS_DERRAME().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_PERMISOS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_INSTRUCTIVOS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_SUPERVISION().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_HERRAMI_AISLADAS().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_EPP().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}
        try{if(!preOrden.getPREVEN_OTRO().isEmpty()){M5_Prevencion = M5_Prevencion - 1;}}catch (Exception e){}

        if(M5_Prevencion < 19){ prevencion.setImageResource(R.drawable.prevencionverd);
            completo = completo-1;}
        else {prevencion.setImageResource(R.drawable.prevenciongris);}

        //endregion

        //region Modulo 6 EPP
        int M6_EPP = 21;
        try{if(!preOrden.getEPP_BASICO_CASCO().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_BASICO_GAFAS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_BASICO_TAPONES().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_BASICO_ZAPATOS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_BASICO_GUANTES().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_BASICO_BARBIQUEJO().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_GAFAS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_CASCO().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_ZAPATOS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_GUANTES().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_SOBREGUANTE().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_CARETA().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_BALACLAVA().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_TRAJE().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_PROTECTORES_AUDI().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_ELECTICO_MANGAS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_OTROS_PROTECCION_CAIDAS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_OTROS_PROTECCION_RESPITA().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_OTROS_PROTECCION_SOLDAD().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_OTROS_PROTECCION_QUIMICOS().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}
        try{if(!preOrden.getEPP_OTROS_ADICIONALES().isEmpty()){M6_EPP = M6_EPP - 1;}}catch (Exception e){}

        if(M6_EPP < 21){ epp.setImageResource(R.drawable.eppverd);
            completo = completo-1;}
        else {epp.setImageResource(R.drawable.eppgris);}
        //endregion

        //region Modulo 7 Emergencias
        int M7_Eme = 3;
        try{if(!preOrden.getEMERG_EMERGENCIAS().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!preOrden.getEMERG_SUPERVISOR_VERTIV().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}
        try{if(!preOrden.getEMERG_HOSPITAL().isEmpty()){M7_Eme = M7_Eme - 1;}}catch (Exception e){}

        if(M7_Eme == 0){ emergencias.setImageResource(R.drawable.emergenciaverd);
            completo = completo-1;}
        else if(M7_Eme < 3){emergencias.setImageResource(R.drawable.emergencianar);}
        else {emergencias.setImageResource(R.drawable.emergenciagris);}

        //endregion

        //region Modulo 8 Inspeccion
        int M8_Inspeccion = 4;
        try{if(!preOrden.getINSPEC_CONDIC_OPTIMAS().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_ACTIVIDADES().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_TRABAJOS_ALTURA().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_CONDIC_INSEGURAS().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
       /* try{if(!preOrden.getINSPEC_CONDIC_OPTIMAS_NO().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_PERMISO_ARCHIVO().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_CAUSAS_ACCIDENTES().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        try{if(!preOrden.getINSPEC_MEDIDAS_CORRECTIVAS().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
        //try{if(!preOrden.getINSPEC_PERMISO_ARCHIVO().isEmpty()){M8_Inspeccion = M8_Inspeccion - 1;}}catch (Exception e){}
*/

        if(M8_Inspeccion == 0){ inspencion.setImageResource(R.drawable.inspeccionverd);
            completo = completo-1;}
        else if(M8_Inspeccion < 4){inspencion.setImageResource(R.drawable.inspeccionnar);}
        else {inspencion.setImageResource(R.drawable.inspecciongris);}
        //endregion

        //region Modulo 9 Firmas
        int M9_firma = 18;
        int M9_firmaGen1 = 3;
        int M9_firmaGen2 = 3;
        int M9_firmaGen3 = 3;
        int M9_firmaGen4 = 3;
        int M9_firmaGen5 = 3;
        int M9_firmaGen6 = 3;
        try{if(!preOrden.getFIRMA_1_NOMBRE().isEmpty()){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_1_CARGO().isEmpty()){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_1_IMAGEN().isEmpty()){M9_firmaGen1 = M9_firmaGen1 - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_2_NOMBRE().isEmpty()){M9_firmaGen2 = M9_firmaGen2 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_2_CARGO().isEmpty()){M9_firmaGen2 = M9_firmaGen2 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_2_IMAGEN().isEmpty()){M9_firmaGen2 = M9_firmaGen2 - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_3_NOMBRE().isEmpty()){M9_firmaGen3 = M9_firmaGen3 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_3_CARGO().isEmpty()){M9_firmaGen3 = M9_firmaGen3 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_3_IMAGEN().isEmpty()){M9_firmaGen3 = M9_firmaGen3 - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_4_NOMBRE().isEmpty()){M9_firmaGen4 = M9_firmaGen4 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_4_CARGO().isEmpty()){M9_firmaGen4 = M9_firmaGen4 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_4_IMAGEN().isEmpty()){M9_firmaGen4 = M9_firmaGen4 - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_5_NOMBRE().isEmpty()){M9_firmaGen5 = M9_firmaGen5 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_5_CARGO().isEmpty()){M9_firmaGen5 = M9_firmaGen5 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_5_IMAGEN().isEmpty()){M9_firmaGen5 = M9_firmaGen5 - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_6_NOMBRE().isEmpty()){M9_firmaGen6 = M9_firmaGen6 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_6_CARGO().isEmpty()){M9_firmaGen6 = M9_firmaGen6 - 1;}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_6_IMAGEN().isEmpty()){M9_firmaGen6 = M9_firmaGen6 - 1;}}catch (Exception e){}

        int constante1 = M9_firmaGen1 + M9_firmaGen2+ M9_firmaGen3 +M9_firmaGen4+M9_firmaGen5+M9_firmaGen6;
        if(constante1 == 18){
            firmas.setImageResource(R.drawable.firmagris);
        }else{
             if(constante1== 0){
                 firmas.setImageResource(R.drawable.firmaverde);
                 completo = completo-1;
             }else{
                 int terinado = 1;
                 if(M9_firmaGen1 > 0 & M9_firmaGen1<3){terinado = terinado +1;}
                 if(M9_firmaGen2 > 0 & M9_firmaGen2<3){terinado = terinado +1;}
                 if(M9_firmaGen3 > 0 & M9_firmaGen3<3){terinado = terinado +1;}
                 if(M9_firmaGen4 > 0 & M9_firmaGen4<3){terinado = terinado +1;}
                 if(M9_firmaGen5 > 0 & M9_firmaGen5<3){terinado = terinado +1;}
                 if(M9_firmaGen6 > 0 & M9_firmaGen6<3){terinado = terinado +1;}

                 if(terinado==1){
                     firmas.setImageResource(R.drawable.firmaverde);
                     completo = completo-1;
                 }else {
                     firmas.setImageResource(R.drawable.firmanar);
                 }
             }
        }


        //--------------------------------------------------------------------------------------------------------------
      /*  int M9_firmaGen1 = 0;
        int M9_firmaGen2 = 0;
        int M9_firmaGen3 = 0;
        int M9_firmaGen4 = 0;
        int M9_firmaGen5 = 0;
        int M9_firmaGen6 = 0;

        try{if(!preOrden.getFIRMA_1_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_1_CARGO().isEmpty() &
               !preOrden.getFIRMA_1_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_2_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_2_CARGO().isEmpty() &
               !preOrden.getFIRMA_2_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_3_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_3_CARGO().isEmpty() &
               !preOrden.getFIRMA_3_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_4_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_4_CARGO().isEmpty() &
               !preOrden.getFIRMA_4_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_1_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_1_CARGO().isEmpty() &
               !preOrden.getFIRMA_1_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_5_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_5_CARGO().isEmpty() &
               !preOrden.getFIRMA_5_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        try{if(!preOrden.getFIRMA_6_NOMBRE().isEmpty() &
               !preOrden.getFIRMA_6_CARGO().isEmpty() &
               !preOrden.getFIRMA_6_IMAGEN().isEmpty()){M9_firmaGen = M9_firmaGen - 1;}}catch (Exception e){}

        if(M9_firmaGen < 6){ firmas.setImageResource(R.drawable.firmaverde);
            completo = completo-1;}
        else if(M9_firma < 18){firmas.setImageResource(R.drawable.firmanar);}
        else {firmas.setImageResource(R.drawable.firmagris);}

        */

        //endregion

     if(completo == 0){
         D_B.actualizarEsatusFormato("0",id_PreOrden);
         terminar.setBackgroundResource(R.drawable.btnnaranja);
         terminar.setEnabled(true);
     }else{
         D_B.actualizarEsatusFormato("1",id_PreOrden);
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
                            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
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

    PreTrabajo item;
    public void enviarFormato(){
        //region Generar Pre trabajo
       // PreTrabajo item = D_B.obtenerPTPrelleno();
        item = D_B.obtenerPTPrelleno();

        final String idFormatoService = D_B.generarUID();
        item.setIdPretrabajo(idFormatoService);
        if(preOrden.getGEN_SR().length()>1){
            String id =D_B.obtenerIDCliente(preOrden.getGEN_CLIENTE(),preOrden.getGEN_SITIO(),preOrden.getGEN_DIRECCION());
            item.setIdCliente(id);//obligatorios
        }
        else{
            item.setIdCliente(null);//obligatorios
        }
        item.setIdCliente(preOrden.getGEN_CLIENTE());
        item.setGEN_SR(preOrden.getGEN_SR());
        item.setGEN_TASK(preOrden.getGEN_TASK());
        item.setGEN_PROYECTO(preOrden.getGEN_PROYECTO());
        item.setGEN_SITIO(preOrden.getGEN_SITIO());
        item.setGEN_REFERENCIA(preOrden.getGEN_REFERENCIA());
        item.setGEN_USUARIO_FINAL(preOrden.getGEN_USUARIO_FINAL());
        item.setNombreCliente(preOrden.getGEN_CLIENTE());
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setEPP_ELECTICO_GUANTES(true);}
        item.setINSPEC_PERMISO_ARCHIVO(preOrden.getINSPEC_PERMISO_ARCHIVO());
        item.setFIRMA_1_CARGO(preOrden.getFIRMA_1_CARGO());
        item.setFIRMA_2_CARGO(preOrden.getFIRMA_2_CARGO());
        item.setFIRMA_3_CARGO(preOrden.getFIRMA_3_CARGO());
        item.setFIRMA_4_CARGO(preOrden.getFIRMA_4_CARGO());
        item.setFIRMA_5_CARGO(preOrden.getFIRMA_5_CARGO());
        item.setFIRMA_6_CARGO(preOrden.getFIRMA_6_CARGO());
        item.setIdUsuario(usuario.getID_USER());//obligatorios

        item.setNombreSitio(preOrden.getGEN_SITIO());
        item.setFecha(preOrden.getGEN_FECHA());
        item.setLiderGrupoCuadrilla(preOrden.getGEN_LIDER_GRUPO());
        item.setDireccionSitio(preOrden.getGEN_DIRECCION());
        item.setDescripcionActividad(preOrden.getACTV_DESCRIPCION_ACTIVIDADES());
        item.setEquiposHerramientasMateriales(preOrden.getHERRAM_DESCRIPCION_EHM());
        if(preOrden.getHERRAM_HOJAS_SEGURIDAD().length()>1){item.setHojasSeguridad(true);}
        if(preOrden.getHERRAM_DELIMITACION_AT().equals("Cinta de Precaución")){item.setCintaPrecaucion(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Conos/Pedestales")){item.setConosPedestales(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Mixta (Conos y Cinta.)")){item.setMixta(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else item.setOtrosArea(preOrden.getHERRAM_DELIMITACION_AT());
        if(preOrden.getRIESGO_PARTICULAS().length()>1){item.setProyeccionParticulas(true);}
        if(preOrden.getRIESGO_ATRAPAMIENTO().length()>1){item.setAtrapamiento(true);}
        if(preOrden.getRIESGO_GOLPES().length()>1){item.setGolpesCortes(true);}
        if(preOrden.getRIESGO_QUEMADURAS().length()>1){item.setQuemaduras(true);}
        if(preOrden.getRIESGO_CAIDA_MATE().length()>1){item.setCaidaMateriales(true);}
        if(preOrden.getRIESGO_CAIDA_MISMO_NIVEL().length()>1){item.setCaidaMismoNivel(true);}
        if(preOrden.getRIESGO_CAIDA_DIST_NIVEL().length()>1){item.setCaidaDistintoNivel(true);}
        if(preOrden.getRIESGO_LIMPIEZA_DEFI().length()>1){item.setOrdenLimpieza(true);}
        if(preOrden.getRIESGO_OTRO_PERSONAL().length()>1){item.setOtroPersonalTrabajando(true);}
        if(preOrden.getRIESGO_CHOQUE_ELECTRICO().length()>1){item.setChoqueElectrico(true);}
        if(preOrden.getRIESGO_ARCO_ELECT().length()>1){item.setArcoElectrico(true);}
        if(preOrden.getRIESGO_FUEGO().length()>1){item.setFuegoExplosion(true);}
        if(preOrden.getRIESGO_EXPO_RUIDO().length()>1){item.setExposicionRuido(true);}
        if(preOrden.getRIESGO_EXP_VIBRA().length()>1){item.setExposicionVibraciones(true);}
        if(preOrden.getRIESGO_FATIGA_VISUAL().length()>1){item.setFatigaVisual(true);}
        if(preOrden.getRIESGO_TEMPERATURAS().length()>1){item.setExposicionAltasBjasTemperaturas(true);}
        if(preOrden.getRIESGO_DEFI_OXIGENO().length()>1){item.setDeficienciaOxigeno(true);}
        if(preOrden.getRIESGO_GASES().length()>1){item.setExposiconGases(true);}
        if(preOrden.getRIESGO_POLVO().length()>1){item.setExposicionPolvo(true);}
        if(preOrden.getRIESGO_SOBRE_ESFUERZO().length()>1){item.setSobreEsfuerzo(true);}
        if(preOrden.getRIESGO_QUIMICOS().length()>1){item.setManipulacionProductosQuimicos(true);}
        if(preOrden.getRIESGO_RUIDO().length()>1){item.setRuido(true);}
        item.setOtrosRiesgos(preOrden.getRIESGO_OTRO());
        if(preOrden.getPREVEN_DISPO_MECANICA().length()>1){item.setUsarDispositivosElevacion(true);}
        if(preOrden.getPREVEN_SUST_QUIMICOS().length()>1){item.setSustituirQuimicosToxicos(true);}
        if(preOrden.getPREVEN_AISLAR_RUIDO().length()>1){item.setAislarRuidoGenerado(true);}
        if(preOrden.getPREVEN_PROTECTORES_MAQUINAS().length()>1){item.setColocarGuardasProtectoras(true);}
        if(preOrden.getPREVEN_PLATA_ANDAMIOS().length()>1){item.setInstalarPlataformas(true);}
        if(preOrden.getPREVEN_SIS_PNTS_ANCLAJE().length()>1){item.setInstalarSistemaPuntosAnclaje(true);}
        if(preOrden.getPREVEN_ILUMI_ART().length()>1){item.setInstalarIluminacion(true);}
        if(preOrden.getPREVEN_DISYUNTOR().length()>1){item.setInstalarDisyuntor(true);}
        if(preOrden.getPREVEN_SIST_PUESTA_TIERRA().length()>1){item.setInstalarSistemaPuestaTierra(true);}
        if(preOrden.getPREVEN_ORDEN_LIMPIEZA().length()>1){item.setMantenerOrden(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setSeñalizarAreaTrabajo(true);}
        if(preOrden.getPREVEN_BE_FUENTES_ENERGIA().length()>1){item.setBloquearEtiquetarFuentesEnergia(true);}
        if(preOrden.getPREVEN_MUROS_DERRAME().length()>1){item.setInstalarMurosContenerDerrames(true);}
        if(preOrden.getPREVEN_PERMISOS().length()>1){item.setPermisoTrabajo(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setProcedTrabajo(true);}
        if(preOrden.getPREVEN_SUPERVISION().length()>1){item.setSupervisionPermanente(true);}
        if(preOrden.getPREVEN_HERRAMI_AISLADAS().length()>1){item.setUsarHerramientaAislada(true);}
        if(preOrden.getPREVEN_EPP().length()>1){item.setEquipoProteccionPersonal(true);}
        item.setOtrosMedidasPrevension(preOrden.getPREVEN_OTRO());
        if(preOrden.getEPP_BASICO_CASCO().length()>1){item.setCasco(true);}
        if(preOrden.getEPP_BASICO_GAFAS().length()>1){item.setGafasProtectoras(true);}
        if(preOrden.getEPP_BASICO_TAPONES().length()>1){item.setProtectoresAuditores(true);}
        if(preOrden.getEPP_BASICO_ZAPATOS().length()>1){item.setZapatosSeguridad(true);}
        if(preOrden.getEPP_BASICO_GUANTES().length()>1){item.setGuantesTrabajo(true);}
        if(preOrden.getEPP_BASICO_BARBIQUEJO().length()>1){item.setBarbiquejo(true);}
        if(preOrden.getEPP_ELECTICO_GAFAS().length()>1){item.setGafasSeguridad(true);}
        if(preOrden.getEPP_ELECTICO_CASCO().length()>1){item.setCascoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_ZAPATOS().length()>1){item.setZapatosDielectricos(true);}
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setSobreguanteCuero(true);}
        if(preOrden.getEPP_ELECTICO_CARETA().length()>1){item.setCaretaArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_BALACLAVA().length()>1){item.setBalaClava(true);}
        if(preOrden.getEPP_ELECTICO_TRAJE().length()>1){item.setTrajeArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_PROTECTORES_AUDI().length()>1){item.setProtectoresAuditivos(true);}
        if(preOrden.getEPP_ELECTICO_MANGAS().length()>1){item.setMangasDielectricas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_CAIDAS().length()>1){item.setProteccionContraCaidas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_RESPITA().length()>1){item.setProteccionRespiratoria(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_SOLDAD().length()>1){item.setProteccionSoldadora(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_QUIMICOS().length()>1){item.setProteccionContraQuimicos(true);}
        item.setAdiconales(preOrden.getEPP_OTROS_ADICIONALES());
        if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("SI")){item.setInspeccionEPP(true);}
        else if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("NO")){item.setInspeccionEPP(false);}
        item.setEspecifiqueDano(preOrden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE());

        if(preOrden.getINSPEC_ACTIVIDADES().equals("Entrada en espacios confinado")){item.setEntradaEspaciosConfinados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en caliente")){item.setTrabajosCaliente(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en equipos energizados (>50V)")){item.setTrabajosEquiposEnergizados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("N/A")){item.setNA(true);}

        if(preOrden.getINSPEC_TRABAJOS_ALTURA().equals("SI")){item.setTrabajosAltura(true);}
        if(preOrden.getINSPEC_CONDIC_INSEGURAS().equals("SI")){item.setCondicionesInseguras(true);}
        item.setCondicionInsegura(preOrden.getINSPEC_CAUSAS_ACCIDENTES());
        item.setMedidasCorrectivas(preOrden.getINSPEC_MEDIDAS_CORRECTIVAS());
        item.setNombre1(preOrden.getFIRMA_1_NOMBRE());
        item.setArchivoFirma1(preOrden.getFIRMA_1_IMAGEN());
        item.setNombre2(preOrden.getFIRMA_2_NOMBRE());
        item.setArchivoFirma2(preOrden.getFIRMA_2_IMAGEN());
        item.setNombre3(preOrden.getFIRMA_3_NOMBRE());
        item.setArchivoFirma3(preOrden.getFIRMA_3_IMAGEN());
        item.setNombre4(preOrden.getFIRMA_4_NOMBRE());
        item.setArchivoFirma4(preOrden.getFIRMA_4_IMAGEN());
        item.setNombre5(preOrden.getFIRMA_5_NOMBRE());
        item.setArchivoFirma5(preOrden.getFIRMA_5_IMAGEN());
        item.setNombre6(preOrden.getFIRMA_6_NOMBRE());
        item.setArchivoFirma6(preOrden.getFIRMA_6_IMAGEN());
        item.setNumeroEmergencia(preOrden.getEMERG_EMERGENCIAS());
        item.setNumeroSupervisor(preOrden.getEMERG_SUPERVISOR_VERTIV());
        item.setHospitalCercano(preOrden.getEMERG_HOSPITAL());
        item.setTipoParticipante1(preOrden.getFIRMA_1_CARGO());
        item.setTipoParticipante2(preOrden.getFIRMA_2_CARGO());
        item.setTipoParticipante3(preOrden.getFIRMA_3_CARGO());
        item.setTipoParticipante4(preOrden.getFIRMA_4_CARGO());
        item.setTipoParticipante5(preOrden.getFIRMA_5_CARGO());
        item.setTipoParticipante6(preOrden.getFIRMA_6_CARGO());
        /*try{if(item.getINSPEC_PERMISO_ARCHIVO().contains(".jpg")){
            item.setINSPEC_PERMISO_ARCHIVO(b64.ImgB64(item.getINSPEC_PERMISO_ARCHIVO()));
        }}catch (Exception e){}

         */


        //endregion
        //region envio de formato
        item.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.EnvioPreoTrabajo(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PreTrabajo>() {
                    @Override
                    public void accept(PreTrabajo lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if(lista.getExitoso().equals("1")) {
                            enviarFotos(idFormatoService);

                            D_B.actualizarEsatusFormato("3",id_PreOrden);
                            // Toast.makeText(getContext(), "Folio: " + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();
                            Bundle valores = new Bundle();
                            valores.putString("mensaje","Folio:" + lista.getFolioPreTrabajo().toString());
                            AlertaOk alerta = new AlertaOk();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");

                            sincronizarFoiosDespuesde();

                                    /*MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                                    FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();*/
                            //Toast.makeText(getContext(), "Folio: " + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

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
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        alerta.dismiss();
                    }
                })

        );
        //endregion
    }

    public void enviarFotos(String idFormatoService){
        //region Enviar_Fotos

        try{
            if(item.getINSPEC_PERMISO_ARCHIVO().contains(".jpg")){
            FotosFormato itemF = new FotosFormato();
            itemF.setId(idFormatoService);
            itemF.setINSPEC_PERMISO_ARCHIVO(b64.ImgB64(item.getINSPEC_PERMISO_ARCHIVO()));
            itemF.setFormato("1");
            ((MainActivity) getActivity()).imagenesEnvio(itemF);
        }}catch (Exception e){}

        //endregion
    }

}
