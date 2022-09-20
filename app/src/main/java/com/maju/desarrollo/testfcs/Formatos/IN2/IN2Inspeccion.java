package com.maju.desarrollo.testfcs.Formatos.IN2;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN2Inspeccion extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false,ActivoM4 = false,ActivoM5 = false,
            ActivoM6 = false,ActivoM7 = false,ActivoM8 = false,ActivoM9 = false,ActivoM10 = false,
            ActivoM11 = false,ActivoM12 = false;;

            TextView fechacontorolP,campo27;
    Bestel2 Formato;

    public IN2Inspeccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_in2_inspeccion, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");

        Formato = D_B.obtenerBestel2(id_formato);


        Button Btn_M1 = (Button)v.findViewById(R.id.Modulo1);
        Button Btn_M2 = (Button)v.findViewById(R.id.Modulo2);
        Button Btn_M3 = (Button)v.findViewById(R.id.Modulo3);
        Button Btn_M4 = (Button)v.findViewById(R.id.Modulo4);
        Button Btn_M5 = (Button)v.findViewById(R.id.Modulo5);
        Button Btn_M6 = (Button)v.findViewById(R.id.Modulo6);
        Button Btn_M7 = (Button)v.findViewById(R.id.Modulo7);
        Button Btn_M8 = (Button)v.findViewById(R.id.Modulo8);
        Button Btn_M9 = (Button)v.findViewById(R.id.Modulo9);
        Button Btn_M10 = (Button)v.findViewById(R.id.Modulo10);
        Button Btn_M11 = (Button)v.findViewById(R.id.Modulo11);
        Button Btn_M12 = (Button)v.findViewById(R.id.Modulo12);

        final LinearLayout content_M1 = (LinearLayout)v.findViewById(R.id.contenedorM1);
        final LinearLayout content_M2 = (LinearLayout)v.findViewById(R.id.contenedorM2);
        final LinearLayout content_M3 = (LinearLayout)v.findViewById(R.id.contenedorM3);
        final LinearLayout content_M4 = (LinearLayout)v.findViewById(R.id.contenedorM4);
        final LinearLayout content_M5 = (LinearLayout)v.findViewById(R.id.contenedorM5);
        final LinearLayout content_M6 = (LinearLayout)v.findViewById(R.id.contenedorM6);
        final LinearLayout content_M7 = (LinearLayout)v.findViewById(R.id.contenedorM7);
        final LinearLayout content_M8 = (LinearLayout)v.findViewById(R.id.contenedorM8);
        final LinearLayout content_M9 = (LinearLayout)v.findViewById(R.id.contenedorM9);
        final LinearLayout content_M10 = (LinearLayout)v.findViewById(R.id.contenedorM10);
        final LinearLayout content_M11 = (LinearLayout)v.findViewById(R.id.contenedorM11);
        final LinearLayout content_M12 = (LinearLayout)v.findViewById(R.id.contenedorM12);


        final EditText campo1 = (EditText)v.findViewById(R.id.campo1);
        final EditText campo2 = (EditText)v.findViewById(R.id.campo2);
        final EditText campo3 = (EditText)v.findViewById(R.id.campo3);
        final EditText campo4 = (EditText)v.findViewById(R.id.campo4);
        final EditText campo5 = (EditText)v.findViewById(R.id.campo5);
        final EditText campo6 = (EditText)v.findViewById(R.id.campo6);
        final EditText campo7 = (EditText)v.findViewById(R.id.campo7);
        final EditText campo8 = (EditText)v.findViewById(R.id.campo8);
        final EditText campo9 = (EditText)v.findViewById(R.id.campo9);
        final EditText campo10 = (EditText)v.findViewById(R.id.campo10);
        final EditText campo11 = (EditText)v.findViewById(R.id.campo11);
        final EditText campo12 = (EditText)v.findViewById(R.id.campo12);
        final EditText campo13 = (EditText)v.findViewById(R.id.campo13);
        final EditText campo14 = (EditText)v.findViewById(R.id.campo14);
        final EditText campo15 = (EditText)v.findViewById(R.id.campo15);
        final EditText campo16 = (EditText)v.findViewById(R.id.campo16);
        final EditText campo17 = (EditText)v.findViewById(R.id.campo17);
        final EditText campo18 = (EditText)v.findViewById(R.id.campo18);
        final EditText campo19 = (EditText)v.findViewById(R.id.campo19);
        final EditText campo20 = (EditText)v.findViewById(R.id.campo20);
        final EditText campo21 = (EditText)v.findViewById(R.id.campo21);
        final EditText campo22 = (EditText)v.findViewById(R.id.campo22);
        final EditText campo23 = (EditText)v.findViewById(R.id.campo23);
        final EditText campo24 = (EditText)v.findViewById(R.id.campo24);
        final EditText campo25 = (EditText)v.findViewById(R.id.campo25);
        final EditText campo26 = (EditText)v.findViewById(R.id.campo26);
        campo27 = (TextView)v.findViewById(R.id.campo27);
        final EditText campo28 = (EditText)v.findViewById(R.id.campo28);
        final EditText campo29 = (EditText)v.findViewById(R.id.campo29);
        final EditText campo30 = (EditText)v.findViewById(R.id.campo30);
        final EditText campo31 = (EditText)v.findViewById(R.id.campo31);
        final EditText campo32 = (EditText)v.findViewById(R.id.campo32);
        final EditText campo33 = (EditText)v.findViewById(R.id.campo33);
        final EditText campo34 = (EditText)v.findViewById(R.id.campo34);
        final EditText campo35 = (EditText)v.findViewById(R.id.campo35);
        final EditText campo36 = (EditText)v.findViewById(R.id.campo36);
        final EditText campo37 = (EditText)v.findViewById(R.id.campo37);
        final EditText campo38 = (EditText)v.findViewById(R.id.campo38);
        final EditText campo39 = (EditText)v.findViewById(R.id.campo39);
        final EditText campo40 = (EditText)v.findViewById(R.id.campo40);
        final EditText campo41 = (EditText)v.findViewById(R.id.campo41);
        final EditText campo42 = (EditText)v.findViewById(R.id.campo42);
        final EditText campo43 = (EditText)v.findViewById(R.id.campo43);
        final EditText campo44 = (EditText)v.findViewById(R.id.campo44);
        final EditText campo45 = (EditText)v.findViewById(R.id.campo45);
        final EditText campo46 = (EditText)v.findViewById(R.id.campo46);
        final EditText campo47 = (EditText)v.findViewById(R.id.campo47);
        final EditText campo48 = (EditText)v.findViewById(R.id.campo48);
        final EditText campo49 = (EditText)v.findViewById(R.id.campo49);
        final EditText campo50 = (EditText)v.findViewById(R.id.campo50);
        final EditText campo51 = (EditText)v.findViewById(R.id.campo51);
        final EditText campo52 = (EditText)v.findViewById(R.id.campo52);
        final EditText campo53 = (EditText)v.findViewById(R.id.campo53);
        final EditText campo54 = (EditText)v.findViewById(R.id.campo54);
        final EditText campo55 = (EditText)v.findViewById(R.id.campo55);
        final EditText campo56 = (EditText)v.findViewById(R.id.campo56);
        final EditText campo57 = (EditText)v.findViewById(R.id.campo57);
        final EditText campo58 = (EditText)v.findViewById(R.id.campo58);
        final EditText campo59 = (EditText)v.findViewById(R.id.campo59);
        final EditText campo60 = (EditText)v.findViewById(R.id.campo60);
        final EditText campo61 = (EditText)v.findViewById(R.id.campo61);
        final EditText campo62 = (EditText)v.findViewById(R.id.campo62);
        final EditText campo63 = (EditText)v.findViewById(R.id.campo63);
        final EditText campo64 = (EditText)v.findViewById(R.id.campo64);
        final EditText campo65 = (EditText)v.findViewById(R.id.campo65);
        final EditText campo66 = (EditText)v.findViewById(R.id.campo66);

        final EditText campo67 = (EditText)v.findViewById(R.id.campo67);
        final EditText campo68 = (EditText)v.findViewById(R.id.campo68);
        final EditText campo69 = (EditText)v.findViewById(R.id.campo69);
        final EditText campo70 = (EditText)v.findViewById(R.id.campo70);
        final EditText campo71 = (EditText)v.findViewById(R.id.campo71);
        final EditText campo72 = (EditText)v.findViewById(R.id.campo72);
        final EditText campo73 = (EditText)v.findViewById(R.id.campo73);
        final EditText campo74 = (EditText)v.findViewById(R.id.campo74);
        final EditText campo75 = (EditText)v.findViewById(R.id.campo75);
        final EditText campo76 = (EditText)v.findViewById(R.id.campo76);
        final EditText campo77 = (EditText)v.findViewById(R.id.campo77);
        final EditText campo78 = (EditText)v.findViewById(R.id.campo78);
        final EditText campo79 = (EditText)v.findViewById(R.id.campo79);
        final EditText campo80 = (EditText)v.findViewById(R.id.campo80);
        final EditText campo81 = (EditText)v.findViewById(R.id.campo81);
        final EditText campo82 = (EditText)v.findViewById(R.id.campo82);
        final EditText campo83 = (EditText)v.findViewById(R.id.campo83);
        final EditText campo84 = (EditText)v.findViewById(R.id.campo84);
        final EditText campo85 = (EditText)v.findViewById(R.id.campo85);
        final EditText campo86 = (EditText)v.findViewById(R.id.campo86);
        final EditText campo87 = (EditText)v.findViewById(R.id.campo87);
        final EditText Al1 = (EditText)v.findViewById(R.id.Al1);
        final EditText Al2 = (EditText)v.findViewById(R.id.Al2);
        final EditText Al3 = (EditText)v.findViewById(R.id.Al3);
        final EditText Bl1 = (EditText)v.findViewById(R.id.Bl1);
        final EditText Bl2 = (EditText)v.findViewById(R.id.Bl2);
        final EditText Bl3 = (EditText)v.findViewById(R.id.Bl3);
        final EditText Cl1 = (EditText)v.findViewById(R.id.Cl1);
        final EditText Cl2 = (EditText)v.findViewById(R.id.Cl2);
        final EditText Cl3 = (EditText)v.findViewById(R.id.Cl3);
        final EditText Dl1 = (EditText)v.findViewById(R.id.Dl1);
        final EditText Dl2 = (EditText)v.findViewById(R.id.Dl2);
        final EditText Dl3 = (EditText)v.findViewById(R.id.Dl3);
        final EditText El1 = (EditText)v.findViewById(R.id.El1);
        final EditText El2 = (EditText)v.findViewById(R.id.El2);
        final EditText El3 = (EditText)v.findViewById(R.id.El3);
        final EditText Fl1 = (EditText)v.findViewById(R.id.Fl1);
        final EditText Fl2 = (EditText)v.findViewById(R.id.Fl2);
        final EditText Fl3 = (EditText)v.findViewById(R.id.Fl3);
        final EditText Gl1 = (EditText)v.findViewById(R.id.Gl1);
        final EditText Gl2 = (EditText)v.findViewById(R.id.Gl2);
        final EditText Gl3 = (EditText)v.findViewById(R.id.Gl3);
        final EditText Hl1 = (EditText)v.findViewById(R.id.Hl1);
        final EditText Hl2 = (EditText)v.findViewById(R.id.Hl2);
        final EditText Hl3 = (EditText)v.findViewById(R.id.Hl3);
        final EditText Il1 = (EditText)v.findViewById(R.id.Il1);
        final EditText Il2 = (EditText)v.findViewById(R.id.Il2);
        final EditText Il3 = (EditText)v.findViewById(R.id.Il3);
        final EditText Jl1 = (EditText)v.findViewById(R.id.Jl1);
        final EditText Jl2 = (EditText)v.findViewById(R.id.Jl2);
        final EditText Jl3 = (EditText)v.findViewById(R.id.Jl3);
        final EditText Kl1 = (EditText)v.findViewById(R.id.Kl1);
        final EditText Kl2 = (EditText)v.findViewById(R.id.Kl2);
        final EditText Kl3 = (EditText)v.findViewById(R.id.Kl3);
        final EditText Kl4 = (EditText)v.findViewById(R.id.Kl4);
        final EditText Ll1 = (EditText)v.findViewById(R.id.Ll1);
        final EditText Ll2 = (EditText)v.findViewById(R.id.Ll2);
        final EditText Ll3 = (EditText)v.findViewById(R.id.Ll3);
        final EditText Ll4 = (EditText)v.findViewById(R.id.ll4);
        final EditText Ml1 = (EditText)v.findViewById(R.id.Ml1);
        final EditText Nl1 = (EditText)v.findViewById(R.id.Nl1);
        final EditText Nl2 = (EditText)v.findViewById(R.id.Nl2);
        final EditText Nl3 = (EditText)v.findViewById(R.id.Nl3);
        final EditText Ol1 = (EditText)v.findViewById(R.id.Ol1);
        final EditText Ol2 = (EditText)v.findViewById(R.id.Ol2);
        final EditText Ol3 = (EditText)v.findViewById(R.id.Ol3);
        final EditText Pl1 = (EditText)v.findViewById(R.id.Pl1);
        final EditText Pl2 = (EditText)v.findViewById(R.id.Pl2);
        final EditText Pl3 = (EditText)v.findViewById(R.id.Pl3);
        final EditText Ql1 = (EditText)v.findViewById(R.id.Ql1);
        final EditText Ql2 = (EditText)v.findViewById(R.id.Ql2);
        final EditText Ql3 = (EditText)v.findViewById(R.id.Ql3);
        final EditText Rl1 = (EditText)v.findViewById(R.id.Rl1);
        final EditText Rl2 = (EditText)v.findViewById(R.id.Rl2);

        fechacontorolP = (TextView)v.findViewById(R.id.fechacontorolP);
        ImageView Button_fechaCP = (ImageView)v.findViewById(R.id.Button_fechaCP);
        ImageView Button_fecha1 = (ImageView)v.findViewById(R.id.Button_fecha1);

        final EditText Otros1 = (EditText)v.findViewById(R.id.Otros1);
        final EditText comentario1 = (EditText)v.findViewById(R.id.comentario1);
        final EditText Otros2 = (EditText)v.findViewById(R.id.Otros2);
        final EditText comentario2 = (EditText)v.findViewById(R.id.comentario2);
        final EditText Otros3 = (EditText)v.findViewById(R.id.Otros3);
        final EditText comentario3 = (EditText)v.findViewById(R.id.comentario3);
        final EditText Otros4 = (EditText)v.findViewById(R.id.Otros4);
        final EditText comentario4 = (EditText)v.findViewById(R.id.comentario4);
        final EditText Otros5 = (EditText)v.findViewById(R.id.Otros5);
        final EditText comentario5 = (EditText)v.findViewById(R.id.comentario5);
        final EditText Otros6 = (EditText)v.findViewById(R.id.Otros6);
        final EditText comentario6 = (EditText)v.findViewById(R.id.comentario6);
        final EditText Otros7 = (EditText)v.findViewById(R.id.Otros7);
        final EditText comentario7 = (EditText)v.findViewById(R.id.comentario7);
        final EditText Otros8 = (EditText)v.findViewById(R.id.Otros8);
        final EditText comentario8 = (EditText)v.findViewById(R.id.comentario8);

        //region mostrar datos
        try{campo1.setText(Formato.getAATemperatura());}catch (Exception e){}
        try{campo2.setText(Formato.getAACondensadora());}catch (Exception e){}
        try{campo3.setText(Formato.getAAEvaporadora());}catch (Exception e){}
        try{campo4.setText(Formato.getAASerpentin());}catch (Exception e){}
        try{campo5.setText(Formato.getAAFugaGas());}catch (Exception e){}
        try{campo6.setText(Formato.getAAValvulas());}catch (Exception e){}
        try{campo7.setText(Formato.getAATermostatos());}catch (Exception e){}
        try{campo8.setText(Formato.getAABombas());}catch (Exception e){}
        try{campo9.setText(Formato.getECIliminacion());}catch (Exception e){}
        try{campo10.setText(Formato.getECPinturaMuros());}catch (Exception e){}
        try{campo11.setText(Formato.getECPisos());}catch (Exception e){}
        try{campo12.setText(Formato.getECImpermeabilizacion());}catch (Exception e){}
        try{campo13.setText(Formato.getECHidrosanitario());}catch (Exception e){}
        try{campo14.setText(Formato.getECHerrejes());}catch (Exception e){}
        try{campo15.setText(Formato.getECLimpiezaGeneral());}catch (Exception e){}
        try{campo16.setText(Formato.getPFVoltajeSalida());}catch (Exception e){}
        try{campo17.setText(Formato.getPFCorrienteDisplay());}catch (Exception e){}
        try{campo18.setText(Formato.getPFTensioDispaly());}catch (Exception e){}
        try{campo19.setText(Formato.getPFTemSalas());}catch (Exception e){}
        try{campo20.setText(Formato.getPFTensionl1l2());}catch (Exception e){}
        try{campo21.setText(Formato.getPFTensionl2l3());}catch (Exception e){}
        try{campo22.setText(Formato.getPFTensionl1l3());}catch (Exception e){}
        try{campo23.setText(Formato.getSCSistema());}catch (Exception e){}
        try{campo24.setText(Formato.getSCDetectores());}catch (Exception e){}
        try{campo25.setText(Formato.getSCExtintores());}catch (Exception e){}
        try{campo26.setText(Formato.getSCBotesAreneros());}catch (Exception e){}
        try{campo27.setText(Formato.getSCFechaCaducidad());}catch (Exception e){}
        try{campo28.setText(Formato.getSALectoras());}catch (Exception e){}
        try{campo29.setText(Formato.getSASupervisoresPuertas());}catch (Exception e){}
        try{campo30.setText(Formato.getSACCTV());}catch (Exception e){}
        try{campo31.setText(Formato.getSABaterias());}catch (Exception e){}
        try{campo32.setText(Formato.getSATablerosControl());}catch (Exception e){}
        try{campo33.setText(Formato.getSUAlarmas());}catch (Exception e){}
        try{campo34.setText(Formato.getSUTemperatura());}catch (Exception e){}
        try{campo35.setText(Formato.getSUCapacitores());}catch (Exception e){}
        try{campo36.setText(Formato.getSUVoltajeTotalBaterias());}catch (Exception e){}
        try{campo37.setText(Formato.getSUVerificacionVentiladiores());}catch (Exception e){}
        try{campo38.setText(Formato.getSUReapreteConexiones());}catch (Exception e){}
        try{campo39.setText(Formato.getPEFugasAceite());}catch (Exception e){}
        try{campo40.setText(Formato.getPEHorasOperacion());}catch (Exception e){}
        try{campo41.setText(Formato.getPEBaterias());}catch (Exception e){}
        try{campo42.setText(Formato.getPENivelDisel());}catch (Exception e){}
        try{campo43.setText(Formato.getPENivelAnticongelante());}catch (Exception e){}
        try{campo44.setText(Formato.getPEManguerasGeneral());}catch (Exception e){}
        try{campo45.setText(Formato.getPERuidosExtraños());}catch (Exception e){}
        try{campo46.setText(Formato.getSSESobreCalentamientoPorFase());}catch (Exception e){}
        try{campo47.setText(Formato.getSSEBarrasPuestaTierra());}catch (Exception e){}
        //try{campo48.setText(Formato.getSSCoexionPuntaTierra());}catch (Exception e){}
        try{campo49.setText(Formato.getSSETransformador());}catch (Exception e){}
        try{campo50.setText(Formato.getSSEFusibles());}catch (Exception e){}
        try{campo51.setText(Formato.getSSETemperatura());}catch (Exception e){}
        try{campo52.setText(Formato.getSSECuchillas());}catch (Exception e){}
        try{campo53.setText(Formato.getSSEInterruptores());}catch (Exception e){}
        try{campo54.setText(Formato.getTTLimpiezaGeneral());}catch (Exception e){}
        try{campo55.setText(Formato.getTTAnclasRetenidos());}catch (Exception e){}
        try{campo56.setText(Formato.getTTLucesObstruccion());}catch (Exception e){}
        try{campo57.setText(Formato.getTTTornilleriaHerraje());}catch (Exception e){}
        try{campo58.setText(Formato.getTTPuestaTierra());}catch (Exception e){}
        try{campo59.setText(Formato.getTTSistemaApartaRayos());}catch (Exception e){}
        try{campo60.setText(Formato.getHFugasGeneral());}catch (Exception e){}
        try{campo61.setText(Formato.getHHidroneumaticos());}catch (Exception e){}
        try{campo62.setText(Formato.getHBaños());}catch (Exception e){}
        try{campo63.setText(Formato.getHCisternasTanques());}catch (Exception e){}
        try{campo64.setText(Formato.getHBombas());}catch (Exception e){}
        try{campo65.setText(Formato.getHEmpaques());}catch (Exception e){}
        try{campo66.setText(Formato.getHAccesorios());}catch (Exception e){}

        try{campo67.setText(Formato.getAAPresionAlta());}catch (Exception e){}
        try{campo68.setText(Formato.getAAPresionBaja());}catch (Exception e){}
        try{campo69.setText(Formato.getAAFiltros());}catch (Exception e){}
        try{campo70.setText(Formato.getAALimpiezaGeneral());}catch (Exception e){}
        try{campo71.setText(Formato.getPFVoltajeFaseNeutro());}catch (Exception e){}
        try{campo72.setText(Formato.getPFRectificadoresTemp());}catch (Exception e){}
        try{campo73.setText(Formato.getPFLimpieza());}catch (Exception e){}
        try{campo74.setText(Formato.getSUCorrienteCargaBaterias());}catch (Exception e){}
        try{campo75.setText(Formato.getSUCorrienteDescargaBaterias());}catch (Exception e){}
        try{campo76.setText(Formato.getSUTorqueBaterias());}catch (Exception e){}
        try{campo77.setText(Formato.getSUVoltajeEntreTierra());}catch (Exception e){}
        try{campo78.setText(Formato.getSULimpieza());}catch (Exception e){}
        try{campo79.setText(Formato.getPEPrecalentador());}catch (Exception e){}
        try{campo80.setText(Formato.getPEFiltros());}catch (Exception e){}
        try{campo81.setText(Formato.getPETemperatura());}catch (Exception e){}
        try{campo82.setText(Formato.getPEBandas());}catch (Exception e){}
        try{campo83.setText(Formato.getPEBateriasLiquido());}catch (Exception e){}
        try{campo84.setText(Formato.getPELubricacion());}catch (Exception e){}
        try{campo85.setText(Formato.getPEArranqueManualSinCarga());}catch (Exception e){}
        try{campo86.setText(Formato.getPELimpiezaGeneral());}catch (Exception e){}
        try{campo87.setText(Formato.getPETableroTransparencia());}catch (Exception e){}

        try{Al1.setText(Formato.getAAVoltajeL1());}catch (Exception e){}
        try{Al2.setText(Formato.getAAVoltajeL2());}catch (Exception e){}
        try{Al3.setText(Formato.getAAVoltajeL3());}catch (Exception e){}
        try{Bl1.setText(Formato.getAAAmperajeL1());}catch (Exception e){}
        try{Bl2.setText(Formato.getAAAmperajeL2());}catch (Exception e){}
        try{Bl3.setText(Formato.getAAAmperajeL3());}catch (Exception e){}
        try{Cl1.setText(Formato.getPFCorrientesL1());}catch (Exception e){}
        try{Cl2.setText(Formato.getPFCorrientesL2());}catch (Exception e){}
        try{Cl3.setText(Formato.getPFCorrientesL3());}catch (Exception e){}
        try{Dl1.setText(Formato.getPFVoltajeNL1());}catch (Exception e){}
        try{Dl2.setText(Formato.getPFVoltajeNL2());}catch (Exception e){}
        try{Dl3.setText(Formato.getPFVoltajeNL3());}catch (Exception e){}
        try{El1.setText(Formato.getSUVoltajeEntradaL1());}catch (Exception e){}
        try{El2.setText(Formato.getSUVoltajeEntradaL2());}catch (Exception e){}
        try{El3.setText(Formato.getSUVoltajeEntradaL3());}catch (Exception e){}
        try{Fl1.setText(Formato.getSUVoltajeByPassL1());}catch (Exception e){}
        try{Fl2.setText(Formato.getSUVoltajeByPassL2());}catch (Exception e){}
        try{Fl3.setText(Formato.getSUVoltajeByPassL3());}catch (Exception e){}
        try{Gl1.setText(Formato.getSUVoltajeSalidaL1());}catch (Exception e){}
        try{Gl2.setText(Formato.getSUVoltajeSalidaL2());}catch (Exception e){}
        try{Gl3.setText(Formato.getSUVoltajeSalidaL3());}catch (Exception e){}
        try{Hl1.setText(Formato.getSUCorrienteSalidaL1());}catch (Exception e){}
        try{Hl2.setText(Formato.getSUCorrienteSalidaL2());}catch (Exception e){}
        try{Hl3.setText(Formato.getSUCorrienteSalidaL3());}catch (Exception e){}
        try{Il1.setText(Formato.getPEAmperajeL1());}catch (Exception e){}
        try{Il2.setText(Formato.getPEAmperajeL2());}catch (Exception e){}
        try{Il3.setText(Formato.getPEAmperajeL3());}catch (Exception e){}
        try{Jl1.setText(Formato.getPEVoltajeL1());}catch (Exception e){}
        try{Jl2.setText(Formato.getPEVoltajeL2());}catch (Exception e){}
        try{Jl3.setText(Formato.getPEVoltajeL3());}catch (Exception e){}
        try{Kl1.setText(Formato.getSSESobreCalentamientoPorFaseL1());}catch (Exception e){}
        try{Kl2.setText(Formato.getSSEBarrasPuestaTierraL2());}catch (Exception e){}
        try{Kl3.setText(Formato.getSSEConexionPuestaTierraL3());}catch (Exception e){}
        try{Kl4.setText(Formato.getSSETransformadorNT());}catch (Exception e){}
        try{Ll1.setText(Formato.getSSEFusiblesA1());}catch (Exception e){}
        try{Ll2.setText(Formato.getSSETemperaturaA2());}catch (Exception e){}
        try{Ll3.setText(Formato.getSSECuchillasA3());}catch (Exception e){}
        try{Ll4.setText(Formato.getSSEInterruptoresNT());}catch (Exception e){}
        try{Ml1.setText(Formato.getOPTEM());}catch (Exception e){}
        try{Nl1.setText(Formato.getOPL1L2());}catch (Exception e){}
        try{Nl2.setText(Formato.getOPL2L3());}catch (Exception e){}
        try{Nl3.setText(Formato.getOPL3L1());}catch (Exception e){}
        try{Ol1.setText(Formato.getOPP1W());}catch (Exception e){}
        try{Ol2.setText(Formato.getOPP1VAR());}catch (Exception e){}
        try{Ol3.setText(Formato.getOPP1VA());}catch (Exception e){}
        try{Pl1.setText(Formato.getOPL1N());}catch (Exception e){}
        try{Pl2.setText(Formato.getOPL2N());}catch (Exception e){}
        try{Pl3.setText(Formato.getOPL3N());}catch (Exception e){}
        try{Ql1.setText(Formato.getOPPF1());}catch (Exception e){}
        try{Ql2.setText(Formato.getOPPF2());}catch (Exception e){}
        try{Ql3.setText(Formato.getOPPF3());}catch (Exception e){}
        try{Rl1.setText(Formato.getOPHZ());}catch (Exception e){}
        try{Rl2.setText(Formato.getOPREVRPM());}catch (Exception e){}
        try{campo27.setText(Formato.getSCFechaCaducidad());}catch (Exception e){}
        try{fechacontorolP.setText(Formato.getPFFechaControl());}catch (Exception e){}
        //endregion

        //region Validaciones
        int VM1 = 17;
        try{if(Formato.getAATemperatura().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAACondensadora().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAEvaporadora().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAASerpentin().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAFugaGas().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAValvulas().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAATermostatos().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAABombas().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAVoltajeL1().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAVoltajeL2().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAVoltajeL3().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAAmperajeL1().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAAmperajeL2().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{if(Formato.getAAAmperajeL3().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{ if(Formato.getAAPresionAlta().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{ if(Formato.getAAPresionBaja().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{ if(Formato.getAAFiltros().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        try{ if(Formato.getAALimpiezaGeneral().length()>0){ VM1 = VM1 -1;}}catch (Exception e){}
        if(VM1<17){Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM2 = 7;
        try{if(Formato.getECIliminacion().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECPinturaMuros().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECPisos().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECImpermeabilizacion().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECHidrosanitario().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECHerrejes().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        try{if(Formato.getECLimpiezaGeneral().length()>0){ VM2 = VM2 -1;}}catch (Exception e){}
        if(VM2<7){Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM3 = 17;
        try{ if(Formato.getPFVoltajeSalida().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFCorrienteDisplay().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFTensioDispaly().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFTemSalas().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFTensionl1l2().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFTensionl2l3().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFTensionl1l3().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFCorrientesL1().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFCorrientesL2().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFCorrientesL3().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFVoltajeNL1().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFVoltajeNL2().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFVoltajeNL3().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{if(Formato.getPFFechaControl().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFVoltajeFaseNeutro().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFRectificadoresTemp().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        try{ if(Formato.getPFLimpieza().length()>0){ VM3 = VM3 -1;}}catch (Exception e){}
        if(VM3<17){Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM4 = 5;
        try{ if(Formato.getSCSistema().length()>0){ VM4 = VM4 -1;}}catch (Exception e){}
        try{ if(Formato.getSCDetectores().length()>0){ VM4 = VM4 -1;}}catch (Exception e){}
        try{ if(Formato.getSCExtintores().length()>0){ VM4 = VM4 -1;}}catch (Exception e){}
        try{ if(Formato.getSCBotesAreneros().length()>0){ VM4 = VM4 -1;}}catch (Exception e){}
        try{ if(Formato.getSCFechaCaducidad().length()>0){ VM4 = VM4 -1;}}catch (Exception e){}
        if(VM4<5){Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM5 = 5;
        try{ if(Formato.getSALectoras().length()>0){ VM5 = VM5 -1;}}catch (Exception e){}
        try{ if(Formato.getSASupervisoresPuertas().length()>0){ VM5 = VM5 -1;}}catch (Exception e){}
        try{ if(Formato.getSACCTV().length()>0){ VM5 = VM5 -1;}}catch (Exception e){}
        try{ if(Formato.getSABaterias().length()>0){ VM5 = VM5 -1;}}catch (Exception e){}
        try{ if(Formato.getSATablerosControl().length()>0){ VM5 = VM5 -1;}}catch (Exception e){}
        if(VM5<5){Btn_M5.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM6 = 23;
        try{ if(Formato.getSUAlarmas().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUTemperatura().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCapacitores().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeTotalBaterias().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVerificacionVentiladiores().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUReapreteConexiones().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeEntradaL1().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeEntradaL2().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeEntradaL3().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeByPassL1().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeByPassL2().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeByPassL3().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeSalidaL1().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeSalidaL2().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeSalidaL3().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCorrienteSalidaL1().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCorrienteSalidaL2().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCorrienteSalidaL3().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCorrienteCargaBaterias().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUCorrienteDescargaBaterias().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUTorqueBaterias().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSUVoltajeEntreTierra().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        try{ if(Formato.getSULimpieza().length()>0){ VM6 = VM6 -1;}}catch (Exception e){}
        if(VM6<23){Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM7 = 22;
        try{ if(Formato.getPEFugasAceite().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEHorasOperacion().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEBaterias().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPENivelDisel().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPENivelAnticongelante().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEManguerasGeneral().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPERuidosExtraños().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEAmperajeL1().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEAmperajeL2().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEAmperajeL3().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEVoltajeL1().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEVoltajeL2().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEVoltajeL3().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEPrecalentador().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEFiltros().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPETemperatura().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEBandas().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEBateriasLiquido().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPELubricacion().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPEArranqueManualSinCarga().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPELimpiezaGeneral().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        try{ if(Formato.getPETableroTransparencia().length()>0){ VM7 = VM7 -1;}}catch (Exception e){}
        if(VM7<22){Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM8 = 15;
        try{ if(Formato.getSSESobreCalentamientoPorFase().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSEBarrasPuestaTierra().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSETransformador().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSEFusibles().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSETemperatura().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSECuchillas().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSEInterruptores().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSESobreCalentamientoPorFaseL1().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSEBarrasPuestaTierraL2().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSEConexionPuestaTierraL3().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSETransformadorNT().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSEFusiblesA1().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{if(Formato.getSSETemperaturaA2().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSECuchillasA3().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        try{ if(Formato.getSSEInterruptoresNT().length()>0){ VM8 = VM8 -1;}}catch (Exception e){}
        if(VM8<15){Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM9 = 6;
        try{ if(Formato.getTTLimpiezaGeneral().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        try{ if(Formato.getTTAnclasRetenidos().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        try{ if(Formato.getTTLucesObstruccion().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        try{ if(Formato.getTTTornilleriaHerraje().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        try{ if(Formato.getTTPuestaTierra().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        try{ if(Formato.getTTSistemaApartaRayos().length()>0){ VM9 = VM9 -1;}}catch (Exception e){}
        if(VM9<6){Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM10 = 7;
        try{ if(Formato.getHFugasGeneral().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHHidroneumaticos().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHBaños().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHCisternasTanques().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHBombas().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHEmpaques().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        try{ if(Formato.getHAccesorios().length()>0){ VM10 = VM10 -1;}}catch (Exception e){}
        if(VM10<7){Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));}

        int VM11 = 15;
        try{ if(Formato.getOPTEM().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL1L2().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL2L3().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL3L1().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1W().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1VAR().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPP1VA().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL1N().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL2N().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPL3N().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF1().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF2().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPPF3().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPHZ().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        try{ if(Formato.getOPREVRPM().length()>0){ VM11 = VM11 -1;}}catch (Exception e){}
        if(VM11<15){Btn_M12.setBackgroundColor(Color.parseColor("#64A539"));}


        int VMO = 16;
        try{ if(Formato.getOTROS().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getAccionesTomadas().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros2().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios2().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros3().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios3().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros4().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios4().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros5().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios5().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros6().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios6().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros7().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios7().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getOtros8().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        try{ if(Formato.getComentarios8().length()>0){ VMO = VMO -1;}}catch (Exception e){}
        if(VMO<16){Btn_M11.setBackgroundColor(Color.parseColor("#64A539"));}


        //endregion

        Otros1.setText(Formato.getOTROS());
        comentario1.setText(Formato.getAccionesTomadas());
        Otros2.setText(Formato.getOtros2());
        comentario2.setText(Formato.getComentarios2());

        Otros3.setText(Formato.getOtros3());
        comentario3.setText(Formato.getComentarios3());
        Otros4.setText(Formato.getOtros4());
        comentario4.setText(Formato.getComentarios4());
        Otros5.setText(Formato.getOtros5());
        comentario5.setText(Formato.getComentarios5());
        Otros6.setText(Formato.getOtros6());
        comentario6.setText(Formato.getComentarios6());
        Otros7.setText(Formato.getOtros7());
        comentario7.setText(Formato.getComentarios7());
        Otros8.setText(Formato.getOtros8());
        comentario8.setText(Formato.getComentarios8());

        Formato.setAATemperatura(campo1.getText().toString());


        //region Botones
        //region BTN M1
        Btn_M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM1){
                    content_M1.setVisibility(View.VISIBLE);
                    ActivoM1 = true;
                }
                else{
                    content_M1.setVisibility(View.GONE);
                    ActivoM1 = false;
                }
            }
        });
        //endregion

        //region BTN M2
        Btn_M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM2){
                    content_M2.setVisibility(View.VISIBLE);
                    ActivoM2 = true;
                }
                else{
                    content_M2.setVisibility(View.GONE);
                    ActivoM2 = false;
                }
            }
        });
        //endregion

        //region BTN M3
        Btn_M3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM3){
                    content_M3.setVisibility(View.VISIBLE);
                    ActivoM3 = true;
                }
                else{
                    content_M3.setVisibility(View.GONE);
                    ActivoM3 = false;
                }
            }
        });
        //endregion

        //region BTN M4
        Btn_M4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM4){
                    content_M4.setVisibility(View.VISIBLE);
                    ActivoM4 = true;
                }
                else{
                    content_M4.setVisibility(View.GONE);
                    ActivoM4 = false;
                }
            }
        });
        //endregion

        //region BTN M5
        Btn_M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM5){
                    content_M5.setVisibility(View.VISIBLE);
                    ActivoM5 = true;
                }
                else{
                    content_M5.setVisibility(View.GONE);
                    ActivoM5 = false;
                }
            }
        });
        //endregion

        //region BTN M6
        Btn_M6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM6){
                    content_M6.setVisibility(View.VISIBLE);
                    ActivoM6 = true;
                }
                else{
                    content_M6.setVisibility(View.GONE);
                    ActivoM6 = false;
                }
            }
        });
        //endregion

        //region BTN M7
        Btn_M7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM7){
                    content_M7.setVisibility(View.VISIBLE);
                    ActivoM7 = true;
                }
                else{
                    content_M7.setVisibility(View.GONE);
                    ActivoM7 = false;
                }
            }
        });
        //endregion

        //region BTN M8
        Btn_M8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM8){
                    content_M8.setVisibility(View.VISIBLE);
                    ActivoM8 = true;
                }
                else{
                    content_M8.setVisibility(View.GONE);
                    ActivoM8 = false;
                }
            }
        });
        //endregion

        //region BTN M9
        Btn_M9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM9){
                    content_M9.setVisibility(View.VISIBLE);
                    ActivoM9 = true;
                }
                else{
                    content_M9.setVisibility(View.GONE);
                    ActivoM9 = false;
                }
            }
        });
        //endregion

        //region BTN M10
        Btn_M10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM10){
                    content_M10.setVisibility(View.VISIBLE);
                    ActivoM10 = true;
                }
                else{
                    content_M10.setVisibility(View.GONE);
                    ActivoM10 = false;
                }
            }
        });
        //endregion

        //region BTN M11
        Btn_M11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM11){
                    content_M11.setVisibility(View.VISIBLE);
                    ActivoM11 = true;
                }
                else{
                    content_M11.setVisibility(View.GONE);
                    ActivoM11 = false;
                }
            }
        });
        //endregion

        //region BTN M12
        Btn_M12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM12){
                    content_M12.setVisibility(View.VISIBLE);
                    ActivoM12 = true;
                }
                else{
                    content_M12.setVisibility(View.GONE);
                    ActivoM12 = false;
                }
            }
        });
        //endregion


        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "IN2");
                args.putString("valorPaso", id_formato);

                CancelasDialogFragmentMismoActivity dialog= new CancelasDialogFragmentMismoActivity();
                dialog.setCancelable(true);
                dialog.setTargetFragment(getParentFragment(),1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                //D_B.guardarPower1(dcPower);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();
                Formato.setAATemperatura(campo1.getText().toString());
                Formato.setAACondensadora(campo2.getText().toString());
                Formato.setAAEvaporadora(campo3.getText().toString());
                Formato.setAASerpentin(campo4.getText().toString());
                Formato.setAAFugaGas(campo5.getText().toString());
                Formato.setAAValvulas(campo6.getText().toString());
                Formato.setAATermostatos(campo7.getText().toString());
                Formato.setAABombas(campo8.getText().toString());
                Formato.setECIliminacion(campo9.getText().toString());
                Formato.setECPinturaMuros(campo10.getText().toString());
                Formato.setECPisos(campo11.getText().toString());
                Formato.setECImpermeabilizacion(campo12.getText().toString());
                Formato.setECHidrosanitario(campo13.getText().toString());
                Formato.setECHerrejes(campo14.getText().toString());
                Formato.setECLimpiezaGeneral(campo15.getText().toString());
                Formato.setPFVoltajeSalida(campo16.getText().toString());
                Formato.setPFCorrienteDisplay(campo17.getText().toString());
                Formato.setPFTensioDispaly(campo18.getText().toString());
                Formato.setPFTemSalas(campo19.getText().toString());
                Formato.setPFTensionl1l2(campo20.getText().toString());
                Formato.setPFTensionl2l3(campo21.getText().toString());
                Formato.setPFTensionl1l3(campo22.getText().toString());
                Formato.setSCSistema(campo23.getText().toString());
                Formato.setSCDetectores(campo24.getText().toString());
                Formato.setSCExtintores(campo25.getText().toString());
                Formato.setSCBotesAreneros(campo26.getText().toString());
                Formato.setSCFechaCaducidad(campo27.getText().toString());
                Formato.setSALectoras(campo28.getText().toString());
                Formato.setSASupervisoresPuertas(campo29.getText().toString());
                Formato.setSACCTV(campo30.getText().toString());
                Formato.setSABaterias(campo31.getText().toString());
                Formato.setSATablerosControl(campo32.getText().toString());
                Formato.setSUAlarmas(campo33.getText().toString());
                Formato.setSUTemperatura(campo34.getText().toString());
                Formato.setSUCapacitores(campo35.getText().toString());
                Formato.setSUVoltajeTotalBaterias(campo36.getText().toString());
                Formato.setSUVerificacionVentiladiores(campo37.getText().toString());
                Formato.setSUReapreteConexiones(campo38.getText().toString());
                Formato.setPEFugasAceite(campo39.getText().toString());
                Formato.setPEHorasOperacion(campo40.getText().toString());
                Formato.setPEBaterias(campo41.getText().toString());
                Formato.setPENivelDisel(campo42.getText().toString());
                Formato.setPENivelAnticongelante(campo43.getText().toString());
                Formato.setPEManguerasGeneral(campo44.getText().toString());
                Formato.setPERuidosExtraños(campo45.getText().toString());
                Formato.setSSESobreCalentamientoPorFase(campo46.getText().toString());
                Formato.setSSEBarrasPuestaTierra(campo47.getText().toString());
                Formato.setSSEConexionPuestaTierra(campo48.getText().toString());
                Formato.setSSETransformador(campo49.getText().toString());
                Formato.setSSEFusibles(campo50.getText().toString());
                Formato.setSSETemperatura(campo51.getText().toString());
                Formato.setSSECuchillas(campo52.getText().toString());
                Formato.setSSEInterruptores(campo53.getText().toString());
                Formato.setTTLimpiezaGeneral(campo54.getText().toString());
                Formato.setTTAnclasRetenidos(campo55.getText().toString());
                Formato.setTTLucesObstruccion(campo56.getText().toString());
                Formato.setTTTornilleriaHerraje(campo57.getText().toString());
                Formato.setTTPuestaTierra(campo58.getText().toString());
                Formato.setTTSistemaApartaRayos(campo59.getText().toString());
                Formato.setHFugasGeneral(campo60.getText().toString());
                Formato.setHHidroneumaticos(campo61.getText().toString());
                Formato.setHBaños(campo62.getText().toString());
                Formato.setHCisternasTanques(campo63.getText().toString());
                Formato.setHBombas(campo64.getText().toString());
                Formato.setHEmpaques(campo65.getText().toString());
                Formato.setHAccesorios(campo66.getText().toString());
                Formato.setOTROS(Otros1.getText().toString());
                Formato.setAccionesTomadas(comentario1.getText().toString());
                Formato.setOtros2(Otros2.getText().toString());
                Formato.setComentarios2(comentario2.getText().toString());
                Formato.setOtros3(Otros3.getText().toString());
                Formato.setComentarios3(comentario3.getText().toString());
                Formato.setOtros4(Otros4.getText().toString());
                Formato.setComentarios4(comentario4.getText().toString());
                Formato.setOtros5(Otros5.getText().toString());
                Formato.setComentarios5(comentario5.getText().toString());
                Formato.setOtros6(Otros6.getText().toString());
                Formato.setComentarios6(comentario6.getText().toString());
                Formato.setOtros7(Otros7.getText().toString());
                Formato.setComentarios7(comentario7.getText().toString());
                Formato.setOtros8(Otros8.getText().toString());
                Formato.setComentarios8(comentario8.getText().toString());
                Formato.setAAPresionAlta(campo67.getText().toString());
                Formato.setAAPresionBaja(campo68.getText().toString());
                Formato.setAAFiltros(campo69.getText().toString());
                Formato.setAALimpiezaGeneral(campo70.getText().toString());
                Formato.setPFVoltajeFaseNeutro(campo71.getText().toString());
                Formato.setPFRectificadoresTemp(campo72.getText().toString());
                Formato.setPFLimpieza(campo73.getText().toString());
                Formato.setSUCorrienteCargaBaterias(campo74.getText().toString());
                Formato.setSUCorrienteDescargaBaterias(campo75.getText().toString());
                Formato.setSUTorqueBaterias(campo76.getText().toString());
                Formato.setSUVoltajeEntreTierra(campo77.getText().toString());
                Formato.setSULimpieza(campo78.getText().toString());
                Formato.setPEPrecalentador(campo79.getText().toString());
                Formato.setPEFiltros(campo80.getText().toString());
                Formato.setPETemperatura(campo81.getText().toString());
                Formato.setPEBandas(campo82.getText().toString());
                Formato.setPEBateriasLiquido(campo83.getText().toString());
                Formato.setPELubricacion(campo84.getText().toString());
                Formato.setPEArranqueManualSinCarga(campo85.getText().toString());
                Formato.setPELimpiezaGeneral(campo86.getText().toString());
                Formato.setPETableroTransparencia(campo87.getText().toString());
                Formato.setAAVoltajeL1(Al1.getText().toString());
                Formato.setAAVoltajeL2(Al2.getText().toString());
                Formato.setAAVoltajeL3(Al3.getText().toString());
                Formato.setAAAmperajeL1(Bl1.getText().toString());
                Formato.setAAAmperajeL2(Bl2.getText().toString());
                Formato.setAAAmperajeL3(Bl3.getText().toString());
                Formato.setPFCorrientesL1(Cl1.getText().toString());
                Formato.setPFCorrientesL2(Cl2.getText().toString());
                Formato.setPFCorrientesL3(Cl3.getText().toString());
                Formato.setPFVoltajeNL1(Dl1.getText().toString());
                Formato.setPFVoltajeNL2(Dl2.getText().toString());
                Formato.setPFVoltajeNL3(Dl3.getText().toString());
                Formato.setSUVoltajeEntradaL1(El1.getText().toString());
                Formato.setSUVoltajeEntradaL2(El2.getText().toString());
                Formato.setSUVoltajeEntradaL3(El3.getText().toString());
                Formato.setSUVoltajeByPassL1(Fl1.getText().toString());
                Formato.setSUVoltajeByPassL2(Fl2.getText().toString());
                Formato.setSUVoltajeByPassL3(Fl3.getText().toString());
                Formato.setSUVoltajeSalidaL1(Gl1.getText().toString());
                Formato.setSUVoltajeSalidaL2(Gl2.getText().toString());
                Formato.setSUVoltajeSalidaL3(Gl3.getText().toString());
                Formato.setSUCorrienteSalidaL1(Hl1.getText().toString());
                Formato.setSUCorrienteSalidaL2(Hl2.getText().toString());
                Formato.setSUCorrienteSalidaL3(Hl3.getText().toString());
                Formato.setPEAmperajeL1(Il1.getText().toString());
                Formato.setPEAmperajeL2(Il2.getText().toString());
                Formato.setPEAmperajeL3(Il3.getText().toString());
                Formato.setPEVoltajeL1(Jl1.getText().toString());
                Formato.setPEVoltajeL2(Jl2.getText().toString());
                Formato.setPEVoltajeL3(Jl3.getText().toString());
                Formato.setSSESobreCalentamientoPorFaseL1(Kl1.getText().toString());
                Formato.setSSEBarrasPuestaTierraL2(Kl2.getText().toString());
                Formato.setSSEConexionPuestaTierraL3(Kl3.getText().toString());
                Formato.setSSETransformadorNT(Kl4.getText().toString());
                Formato.setSSEFusiblesA1(Ll1.getText().toString());
                Formato.setSSETemperaturaA2(Ll2.getText().toString());
                Formato.setSSECuchillasA3(Ll3.getText().toString());
                Formato.setSSEInterruptoresNT(Ll4.getText().toString());
                Formato.setOPTEM(Ml1.getText().toString());
                Formato.setOPL1L2(Nl1.getText().toString());
                Formato.setOPL2L3(Nl2.getText().toString());
                Formato.setOPL3L1(Nl3.getText().toString());
                Formato.setOPP1W(Ol1.getText().toString());
                Formato.setOPP1VAR(Ol2.getText().toString());
                Formato.setOPP1VA(Ol3.getText().toString());
                Formato.setOPL1N(Pl1.getText().toString());
                Formato.setOPL2N(Pl2.getText().toString());
                Formato.setOPL3N(Pl3.getText().toString());
                Formato.setOPPF1(Ql1.getText().toString());
                Formato.setOPPF2(Ql2.getText().toString());
                Formato.setOPPF3(Ql3.getText().toString());
                Formato.setOPHZ(Rl1.getText().toString());
                Formato.setOPREVRPM(Rl2.getText().toString());
                Formato.setPFFechaControl(fechacontorolP.getText().toString());




                if(campo66.getText().toString().equals("")){
                    Formato.setHAccesorios("");
                }

                D_B.guardarBestel2(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                IN2Menu myfargment = new IN2Menu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        Button_fechaCP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });

        Button_fecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });


        //endregion
        return v;
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
                fechacontorolP.setText(selectedDate);
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
                campo27.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }
}
