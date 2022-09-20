package com.maju.desarrollo.testfcs.Formatos.IN1;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN1Inspeccion extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false,ActivoM4 = false,ActivoM5 = false,
            ActivoM6 = false,ActivoM7 = false,ActivoM8 = false,ActivoM9 = false,ActivoM10 = false,
            ActivoM11 = false;
    Bestel1 Formato;
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

    public IN1Inspeccion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =   inflater.inflate(R.layout.fragment_in1_inspeccion, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");

        Formato = D_B.obtenerBestel1(id_formato);


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

        final RadioGroup AATemperaturaEstatus= (RadioGroup)v.findViewById(R.id.AATemperaturaEstatus);
        final EditText AATemperaturaComentarios= (EditText)v.findViewById(R.id.AATemperaturaComentarios);
        final RadioGroup AACondensadoraEstatus= (RadioGroup)v.findViewById(R.id.AACondensadoraEstatus);
        final EditText AACondensadoraComentarios= (EditText)v.findViewById(R.id.AACondensadoraComentarios);
        final RadioGroup AAEvaporadoraEstatus= (RadioGroup)v.findViewById(R.id.AAEvaporadoraEstatus);
        final EditText AAEvaporadoraComentarios= (EditText)v.findViewById(R.id.AAEvaporadoraComentarios);
        final RadioGroup AASerpentinEstatus= (RadioGroup)v.findViewById(R.id.AASerpentinEstatus);
        final EditText AASerpentinComentarios= (EditText)v.findViewById(R.id.AASerpentinComentarios);
        final RadioGroup AAFugaGasEstatus= (RadioGroup)v.findViewById(R.id.AAFugaGasEstatus);
        final EditText AAFugaGasComentarios= (EditText)v.findViewById(R.id.AAFugaGasComentarios);
        final RadioGroup AAAlimentacionEstatus= (RadioGroup)v.findViewById(R.id.AAAlimentacionEstatus);
        final EditText AAAlimentacionComentarios= (EditText)v.findViewById(R.id.AAAlimentacionComentarios);
        final RadioGroup AAFiltrosEstatus= (RadioGroup)v.findViewById(R.id.AAFiltrosEstatus);
        final EditText AAFiltrosComentarios= (EditText)v.findViewById(R.id.AAFiltrosComentarios);
        final RadioGroup AALimpiezaGeneralEstatus= (RadioGroup)v.findViewById(R.id.AALimpiezaGeneralEstatus);
        final EditText AALimpiezaGeneralComentarios= (EditText)v.findViewById(R.id.AALimpiezaGeneralComentarios);
        final RadioGroup ECIluminaciionEstatus= (RadioGroup)v.findViewById(R.id.ECIluminaciionEstatus);
        final EditText ECIluminaciionComentarios= (EditText)v.findViewById(R.id.ECIluminaciionComentarios);
        final RadioGroup ECPinturaEstatus= (RadioGroup)v.findViewById(R.id.ECPinturaEstatus);
        final EditText ECPinturaComentarios= (EditText)v.findViewById(R.id.ECPinturaComentarios);
        final RadioGroup ECPisosEstatus= (RadioGroup)v.findViewById(R.id.ECPisosEstatus);
        final EditText ECPisosComentarios= (EditText)v.findViewById(R.id.ECPisosComentarios);
        final RadioGroup ECImpermeEstatus= (RadioGroup)v.findViewById(R.id.ECImpermeEstatus);
        final EditText ECImpermeComentarios= (EditText)v.findViewById(R.id.ECImpermeComentarios);
        final RadioGroup ECHidrosanitarioEstatus= (RadioGroup)v.findViewById(R.id.ECHidrosanitarioEstatus);
        final EditText ECHidrosanitarioComentarios= (EditText)v.findViewById(R.id.ECHidrosanitarioComentarios);
        final RadioGroup ECHerrejesEstatus= (RadioGroup)v.findViewById(R.id.ECHerrejesEstatus);
        final EditText ECHerrejesComentarios= (EditText)v.findViewById(R.id.ECHerrejesComentarios);
        final RadioGroup ECLimpiezaGeneralEstatus= (RadioGroup)v.findViewById(R.id.ECLimpiezaGeneralEstatus);
        final EditText ECLimpiezaGeneralComentarios= (EditText)v.findViewById(R.id.ECLimpiezaGeneralComentarios);
        final RadioGroup PFBAmperajeEstatus= (RadioGroup)v.findViewById(R.id.PFBAmperajeEstatus);
        final EditText PFBAmperajeComentarios= (EditText)v.findViewById(R.id.PFBAmperajeComentarios);
        final RadioGroup PFBConsumoPlantaEstatus= (RadioGroup)v.findViewById(R.id.PFBConsumoPlantaEstatus);
        final EditText PFBConsumoPlantaComentarios= (EditText)v.findViewById(R.id.PFBConsumoPlantaComentarios);
        final RadioGroup PFBRectificadoresEstatus= (RadioGroup)v.findViewById(R.id.PFBRectificadoresEstatus);
        final EditText PFBRectificadoresComentarios= (EditText)v.findViewById(R.id.PFBRectificadoresComentarios);
        final RadioGroup PFBSistemaInversorEstatus= (RadioGroup)v.findViewById(R.id.PFBSistemaInversorEstatus);
        final EditText PFBSistemaInversorComentarios= (EditText)v.findViewById(R.id.PFBSistemaInversorComentarios);
        final RadioGroup PFBBancosBateriasEstatus= (RadioGroup)v.findViewById(R.id.PFBBancosBateriasEstatus);
        final EditText PFBBancosBateriasComentarios= (EditText)v.findViewById(R.id.PFBBancosBateriasComentarios);
        final RadioGroup PFBTablerosEstatus= (RadioGroup)v.findViewById(R.id.PFBTablerosEstatus);
        final EditText PFBTablerosComentarios= (EditText)v.findViewById(R.id.PFBTablerosComentarios);
        final RadioGroup SUAlimentacionElectricaEstatus= (RadioGroup)v.findViewById(R.id.SUAlimentacionElectricaEstatus);
        final EditText SUAlimentacionElectricaComentarios= (EditText)v.findViewById(R.id.SUAlimentacionElectricaComentarios);
        final RadioGroup SUAlarmasEstatus= (RadioGroup)v.findViewById(R.id.SUAlarmasEstatus);
        final EditText SUAlarmasComentarios= (EditText)v.findViewById(R.id.SUAlarmasComentarios);
        final RadioGroup SUCargaEstatus= (RadioGroup)v.findViewById(R.id.SUCargaEstatus);
        final EditText SUCargaComentarios= (EditText)v.findViewById(R.id.SUCargaComentarios);
        final RadioGroup SUDescargaEstatus= (RadioGroup)v.findViewById(R.id.SUDescargaEstatus);
        final EditText SUDescargaComentarios= (EditText)v.findViewById(R.id.SUDescargaComentarios);
        final RadioGroup SCISistemaEstatus= (RadioGroup)v.findViewById(R.id.SCISistemaEstatus);
        final EditText SCISistemaComentarios= (EditText)v.findViewById(R.id.SCISistemaComentarios);
        final RadioGroup SCIDetectoresEstatus= (RadioGroup)v.findViewById(R.id.SCIDetectoresEstatus);
        final EditText SCIDetectoresComentarios= (EditText)v.findViewById(R.id.SCIDetectoresComentarios);
        final RadioGroup SCIExtintoresEstatus= (RadioGroup)v.findViewById(R.id.SCIExtintoresEstatus);
        final EditText SCIExtintoresComentarios= (EditText)v.findViewById(R.id.SCIExtintoresComentarios);
        final RadioGroup SCIGranadaTanquesEstatus= (RadioGroup)v.findViewById(R.id.SCIGranadaTanquesEstatus);
        final EditText SCIGranadaTanquesComentarios= (EditText)v.findViewById(R.id.SCIGranadaTanquesComentarios);
        final RadioGroup SCIFechaCaducidadEstatus= (RadioGroup)v.findViewById(R.id.SCIFechaCaducidadEstatus);
        final EditText SCIFechaCaducidadComentraios= (EditText)v.findViewById(R.id.SCIFechaCaducidadComentraios);
        final RadioGroup PEFugasAceiteEstatus= (RadioGroup)v.findViewById(R.id.PEFugasAceiteEstatus);
        final EditText PEFugasAceiteComentarios= (EditText)v.findViewById(R.id.PEFugasAceiteComentarios);
        final RadioGroup PEFiltrosEstatus= (RadioGroup)v.findViewById(R.id.PEFiltrosEstatus);
        final EditText PEFiltrosComentarios= (EditText)v.findViewById(R.id.PEFiltrosComentarios);
        final RadioGroup PETemperaturaEstatus= (RadioGroup)v.findViewById(R.id.PETemperaturaEstatus);
        final EditText PETemperaturaComentarios= (EditText)v.findViewById(R.id.PETemperaturaComentarios);
        final RadioGroup PEBandasEstatus= (RadioGroup)v.findViewById(R.id.PEBandasEstatus);
        final EditText PEBandasComentarios= (EditText)v.findViewById(R.id.PEBandasComentarios);
        final RadioGroup PEBateriasEstatus= (RadioGroup)v.findViewById(R.id.PEBateriasEstatus);
        final EditText PEBateriasComentarios= (EditText)v.findViewById(R.id.PEBateriasComentarios);
        final RadioGroup PELubricacionEstatus= (RadioGroup)v.findViewById(R.id.PELubricacionEstatus);
        final EditText PELubricacionComentarios= (EditText)v.findViewById(R.id.PELubricacionComentarios);
        final RadioGroup PECombustibleEstatus= (RadioGroup)v.findViewById(R.id.PECombustibleEstatus);
        final EditText PECombustibleComentarios= (EditText)v.findViewById(R.id.PECombustibleComentarios);
        final RadioGroup PEArranqueManualEstatus= (RadioGroup)v.findViewById(R.id.PEArranqueManualEstatus);
        final EditText PEArranqueManualComentarios= (EditText)v.findViewById(R.id.PEArranqueManualComentarios);
        final RadioGroup PELimpiezaGenetalEstatus= (RadioGroup)v.findViewById(R.id.PELimpiezaGenetalEstatus);
        final EditText PELimpiezaGenetalComentarios= (EditText)v.findViewById(R.id.PELimpiezaGenetalComentarios);
        final RadioGroup SALectorasEstatus= (RadioGroup)v.findViewById(R.id.SALectorasEstatus);
        final EditText SALectorasComentarios= (EditText)v.findViewById(R.id.SALectorasComentarios);
        final RadioGroup SATablerosControlEstatus= (RadioGroup)v.findViewById(R.id.SATablerosControlEstatus);
        final EditText SATablerosControlComentarios= (EditText)v.findViewById(R.id.SATablerosControlComentarios);
        final RadioGroup SSBarrasPuestaTierraEstatus= (RadioGroup)v.findViewById(R.id.SSBarrasPuestaTierraEstatus);
        final EditText SSBarrasPuestaTierraComentarios= (EditText)v.findViewById(R.id.SSBarrasPuestaTierraComentarios);
        final RadioGroup SSConexionPuestaTierraEstatus= (RadioGroup)v.findViewById(R.id.SSConexionPuestaTierraEstatus);
        final EditText SSConexionPuestaTierraComentarios= (EditText)v.findViewById(R.id.SSConexionPuestaTierraComentarios);
        final RadioGroup SSTransformadorEstatus= (RadioGroup)v.findViewById(R.id.SSTransformadorEstatus);
        final EditText SSTransformadorComentarios= (EditText)v.findViewById(R.id.SSTransformadorComentarios);
        final RadioGroup SSFusiblesEstatus= (RadioGroup)v.findViewById(R.id.SSFusiblesEstatus);
        final EditText SSFusiblesComentarios= (EditText)v.findViewById(R.id.SSFusiblesComentarios);
        final RadioGroup SSTemperaturaEstatus= (RadioGroup)v.findViewById(R.id.SSTemperaturaEstatus);
        final EditText SSTemperaturaComentarios= (EditText)v.findViewById(R.id.SSTemperaturaComentarios);
        final RadioGroup SSCuchillasEstatus= (RadioGroup)v.findViewById(R.id.SSCuchillasEstatus);
        final EditText SSCuchillasComentarios= (EditText)v.findViewById(R.id.SSCuchillasComentarios);
        final RadioGroup SSInterruptoresEstatus= (RadioGroup)v.findViewById(R.id.SSInterruptoresEstatus);
        final EditText SSInterruptoresComentarios= (EditText)v.findViewById(R.id.SSInterruptoresComentarios);
        final RadioGroup TELimpizaGeneralEstatus= (RadioGroup)v.findViewById(R.id.TELimpizaGeneralEstatus);
        final EditText TELimpizaGeneralComentarios= (EditText)v.findViewById(R.id.TELimpizaGeneralComentarios);
        final RadioGroup TEAnclasRetenidosEstatus= (RadioGroup)v.findViewById(R.id.TEAnclasRetenidosEstatus);
        final EditText TEAnclasRetenidosComentarios= (EditText)v.findViewById(R.id.TEAnclasRetenidosComentarios);
        final RadioGroup TELucesObstruccionEstatus= (RadioGroup)v.findViewById(R.id.TELucesObstruccionEstatus);
        final EditText TELucesObstruccionComentarios= (EditText)v.findViewById(R.id.TELucesObstruccionComentarios);
        final RadioGroup TETornilleriaEstatus= (RadioGroup)v.findViewById(R.id.TETornilleriaEstatus);
        final EditText TETornilleriaComentarios= (EditText)v.findViewById(R.id.TETornilleriaComentarios);
        final RadioGroup TEPuestaTierraEstatus= (RadioGroup)v.findViewById(R.id.TEPuestaTierraEstatus);
        final EditText TEPuestaTierraComentarios= (EditText)v.findViewById(R.id.TEPuestaTierraComentarios);
        final RadioGroup TESistemaApartaRayosEststus= (RadioGroup)v.findViewById(R.id.TESistemaApartaRayosEststus);
        final EditText TESistemaApartaRayosComentarios= (EditText)v.findViewById(R.id.TESistemaApartaRayosComentarios);
        final RadioGroup HFugasEstatus= (RadioGroup)v.findViewById(R.id.HFugasEstatus);
        final EditText HFugasComentarios= (EditText)v.findViewById(R.id.HFugasComentarios);
        final RadioGroup HBombasEstatus= (RadioGroup)v.findViewById(R.id.HBombasEstatus);
        final EditText HBombasComentarios= (EditText)v.findViewById(R.id.HBombasComentarios);
        final EditText l1= (EditText)v.findViewById(R.id.l1);
        final EditText l2= (EditText)v.findViewById(R.id.l2);
        final EditText l3= (EditText)v.findViewById(R.id.l3);
        final EditText a1= (EditText)v.findViewById(R.id.a1);
        final EditText a2= (EditText)v.findViewById(R.id.a2);
        final EditText a3= (EditText)v.findViewById(R.id.a3);
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

        AATemperaturaComentarios.setEnabled(false);

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
        if(m1==0){Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m1<8){Btn_M1.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m2 = 7;
        try{if(R9.length()>0){if(R9.equals("M") ){if( Formato.getECIluminaciionComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R10.length()>0){if(R10.equals("M") ){if( Formato.getECPinturaComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R11.length()>0){if(R11.equals("M") ){if( Formato.getECPisosComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R12.length()>0){if(R12.equals("M") ){if( Formato.getECImpermeComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R13.length()>0){if(R13.equals("M") ){if( Formato.getECHidrosanitarioComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R14.length()>0){if(R14.equals("M") ){if( Formato.getECHerrejesComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}
        try{if(R15.length()>0){if(R15.equals("M") ){if( Formato.getECLimpiezaGeneralComentarios().length()>0){m2= m2-1;}}else{m2= m2-1;}}}catch(Exception e){}

        if(m2==0){Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m2<7){Btn_M2.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m3 = 6;
        try{if(R16.length()>0){if(R16.equals("M") ){if( Formato.getPFBAmperajeComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R17.length()>0){if(R17.equals("M") ){if( Formato.getPFBConsumoPlantaComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R18.length()>0){if(R18.equals("M") ){if( Formato.getPFBRectificadoresComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R19.length()>0){if(R19.equals("M") ){if( Formato.getPFBSistemaInversorComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R20.length()>0){if(R20.equals("M") ){if( Formato.getPFBBancosBateriasComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}
        try{if(R21.length()>0){if(R21.equals("M") ){if( Formato.getPFBTablerosComentarios().length()>0){m3= m3-1;}}else{m3= m3-1;}}}catch(Exception e){}

        if(m3==0){Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m3<6){Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m4= 4;
        try{if(R22.length()>0){if(R22.equals("M") ){if( Formato.getSUAlimentacionElectricaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R23.length()>0){if(R23.equals("M") ){if( Formato.getSUAlarmasComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R24.length()>0){if(R24.equals("M") ){if( Formato.getSUCargaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}
        try{if(R25.length()>0){if(R25.equals("M") ){if( Formato.getSUDescargaComentarios().length()>0){m4= m4-1;}}else{m4= m4-1;}}}catch(Exception e){}

        if(m4==0){Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m4<4){Btn_M4.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m5 = 5;
        try{if(R26.length()>0){if(R26.equals("M") ){if( Formato.getSCISistemaComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R27.length()>0){if(R27.equals("M") ){if( Formato.getSCIDetectoresComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R28.length()>0){if(R28.equals("M") ){if( Formato.getSCIExtintoresComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R29.length()>0){if(R29.equals("M") ){if( Formato.getSCIGranadaTanquesComentarios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}
        try{if(R30.length()>0){if(R30.equals("M") ){if( Formato.getSCIFechaCaducidadComentraios().length()>0){m5= m5-1;}}else{m5= m5-1;}}}catch(Exception e){}

        if(m5==0){Btn_M5.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m5<5){Btn_M5.setBackgroundColor(Color.parseColor("#FE5B1B"));}

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

        if(m6==0){Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m6<9){Btn_M6.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m7 = 2;
        try{if(R40.length()>0){if(R40.equals("M") ){if( Formato.getSALectorasComentarios().length()>0){m7= m7-1;}}else{m7= m7-1;}}}catch(Exception e){}
        try{if(R41.length()>0){if(R41.equals("M") ){if( Formato.getSATablerosControlComentarios().length()>0){m7= m7-1;}}else{m7= m7-1;}}}catch(Exception e){}

        if(m7==0){Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m7<2){Btn_M7.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m8 = 7;
        try{if(R42.length()>0){if(R42.equals("M") ){if( Formato.getSSBarrasPuestaTierraComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R43.length()>0){if(R43.equals("M") ){if( Formato.getSSConexionPuestaTierraComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R44.length()>0){if(R44.equals("M") ){if( Formato.getSSTransformadorComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R45.length()>0){if(R45.equals("M") ){if( Formato.getSSFusiblesComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R46.length()>0){if(R46.equals("M") ){if( Formato.getSSTemperaturaComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R47.length()>0){if(R47.equals("M") ){if( Formato.getSSCuchillasComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}
        try{if(R48.length()>0){if(R48.equals("M") ){if( Formato.getSSInterruptoresComentarios().length()>0){m8= m8-1;}}else{m8= m8-1;}}}catch(Exception e){}

        if(m8==0){Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m8<7){Btn_M8.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m9=6;
        try{if(R49.length()>0){if(R49.equals("M") ){if( Formato.getTELimpizaGeneralComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R50.length()>0){if(R50.equals("M") ){if( Formato.getTEAnclasRetenidosComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R51.length()>0){if(R51.equals("M") ){if( Formato.getTELucesObstruccionComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R52.length()>0){if(R52.equals("M") ){if( Formato.getTETornilleriaComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R53.length()>0){if(R53.equals("M") ){if( Formato.getTEPuestaTierraComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}
        try{if(R54.length()>0){if(R54.equals("M") ){if( Formato.getTESistemaApartaRayosComentarios().length()>0){m9= m9-1;}}else{m9= m9-1;}}}catch(Exception e){}

        if(m9==0){Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m9<6){Btn_M9.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m10 = 2;
        try{if(R55.length()>0){if(R55.equals("M") ){if( Formato.getHFugasComentarios().length()>0){m10= m10-1;}}else{m10= m10-1;}}}catch(Exception e){}
        try{if(R56.length()>0){if(R56.equals("M") ){if( Formato.getHBombasComentarios().length()>0){m10= m10-1;}}else{m10= m10-1;}}}catch(Exception e){}

        if(m10==0){Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m10<2){Btn_M10.setBackgroundColor(Color.parseColor("#FE5B1B"));}


        int m11 = 2;

        try{if(Formato.getOtros7().length()>0){m11= m11-1;}}catch(Exception e){}
        try{if(Formato.getComentarios7().length()>0){m11= m11-1;}}catch(Exception e){}

        if(m11==0){Btn_M11.setBackgroundColor(Color.parseColor("#64A539"));}


        //endregion


        //region Modulo 1  (1-5)
        AATemperaturaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB1:
                        R1 = "B";
                        AATemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM1:
                        R1="M";
                        AATemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN1:
                        R1 = "N/A";
                        AATemperaturaComentarios.setText("");
                        AATemperaturaComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        AACondensadoraEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB2:
                        R2 = "B";
                        AACondensadoraComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM2:
                        R2 = "M";
                        AACondensadoraComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN2:
                        R2 = "N/A";
                        AACondensadoraComentarios.setText("");
                        AACondensadoraComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                AAEvaporadoraEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB3:
                                R3 = "B";
                                AAEvaporadoraComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM3:
                                R3 = "M";
                                AAEvaporadoraComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN3:
                                R3 = "N/A";
                                AAEvaporadoraComentarios.setText("");
                                AAEvaporadoraComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        AASerpentinEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB4:
                        R4 = "B";

                        AASerpentinComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM4:
                        R4 = "M";
                        AASerpentinComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN4:
                        R4 = "N/A";
                        AASerpentinComentarios.setText("");
                        AASerpentinComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                AAFugaGasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB5:
                        R5 = "B";
                                AAFugaGasComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM5:
                        R5 = "M";
                                AAFugaGasComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN5:
                        R5 = "N/A";
                                AAFugaGasComentarios.setText("");
                                AAFugaGasComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        AAAlimentacionEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB6:
                        R6 = "B";

                        AAAlimentacionComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM6:
                        R6 = "M";
                        AAAlimentacionComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN6:
                        R6 = "N/A";
                        AAAlimentacionComentarios.setText("");
                        AAAlimentacionComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                AAFiltrosEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB7:
                        R7 = "B";

                                AAFiltrosComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM7:
                        R7 = "M";
                                AAFiltrosComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN7:
                        R7 = "N/A";
                                AAFiltrosComentarios.setText("");
                                AAFiltrosComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        AALimpiezaGeneralEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB8:
                        R8 = "B";

                        AALimpiezaGeneralComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM8:
                        R8 = "M";
                        AALimpiezaGeneralComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN8:
                        R8 = "N/A";
                        AALimpiezaGeneralComentarios.setText("");
                        AALimpiezaGeneralComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                //endregion

        //region modilo 2  (6-12)
                ECIluminaciionEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB9:
                        R9 = "B";

                                ECIluminaciionComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM9:
                        R9 = "M";
                                ECIluminaciionComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN9:
                        R9 = "N/A";
                                ECIluminaciionComentarios.setText("");
                                ECIluminaciionComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        ECPinturaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB10:
                        R10 = "B";

                        ECPinturaComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM10:
                        R10 = "M";
                        ECPinturaComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN10:
                        R10 = "N/A";
                        ECPinturaComentarios.setText("");
                        ECPinturaComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                ECPisosEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB11:
                        R11 = "B";

                                ECPisosComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM11:
                        R11 = "M";
                                ECPisosComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN11:
                        R11 = "N/A";
                                ECPisosComentarios.setText("");
                                ECPisosComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        ECImpermeEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB12:
                        R12 = "B";

                        ECImpermeComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM12:
                        R12 = "M";
                        ECImpermeComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN12:
                        R12 = "N/A";
                        ECImpermeComentarios.setText("");
                        ECImpermeComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                ECHidrosanitarioEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB13:
                        R13 = "B";

                                ECHidrosanitarioComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM13:
                        R13 = "M";
                                ECHidrosanitarioComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN13:
                        R13 = "N/A";
                                ECHidrosanitarioComentarios.setText("");
                                ECHidrosanitarioComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        ECHerrejesEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB14:
                        R14 = "B";

                        ECHerrejesComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM14:
                        R14 = "M";
                        ECHerrejesComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN14:
                        R14 = "N/A";
                        ECHerrejesComentarios.setText("");
                        ECHerrejesComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                ECLimpiezaGeneralEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB15:
                        R15 = "B";

                                ECLimpiezaGeneralComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM15:
                        R15 = "M";
                                ECLimpiezaGeneralComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN15:
                        R15 = "N/A";
                                ECLimpiezaGeneralComentarios.setText("");
                                ECLimpiezaGeneralComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
                //endregion

        //region Modulo 3 (13-18)
        PFBAmperajeEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB16:
                        R16 = "B";

                        PFBAmperajeComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM16:
                        R16 = "M";
                        PFBAmperajeComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN16:
                        R16 = "N/A";
                        PFBAmperajeComentarios.setText("");
                        PFBAmperajeComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                PFBConsumoPlantaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB17:
                        R17 = "B";

                                PFBConsumoPlantaComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM17:
                        R17 = "M";
                                PFBConsumoPlantaComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN17:
                        R17 = "N/A";
                                PFBConsumoPlantaComentarios.setText("");
                                PFBConsumoPlantaComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        PFBRectificadoresEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB18:
                        R18 = "B";

                        PFBRectificadoresComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM18:
                        R18 = "M";
                        PFBRectificadoresComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN18:
                        R18 = "N/A";
                        PFBRectificadoresComentarios.setText("");
                        PFBRectificadoresComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                PFBSistemaInversorEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB19:
                        R19 = "B";

                                PFBSistemaInversorComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM19:
                        R19 = "M";
                                PFBSistemaInversorComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN19:
                        R19 = "N/A";
                                PFBSistemaInversorComentarios.setText("");
                                PFBSistemaInversorComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        PFBBancosBateriasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB20:
                        R20 = "B";

                        PFBBancosBateriasComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM20:
                        R20 = "M";
                        PFBBancosBateriasComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN20:
                        R20 = "N/A";
                        PFBBancosBateriasComentarios.setText("");
                        PFBBancosBateriasComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                PFBTablerosEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB21:
                        R21 = "B";

                                PFBTablerosComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM21:
                        R21 = "M";
                                PFBTablerosComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN21:
                        R21 = "N/A";
                                PFBTablerosComentarios.setText("");
                                PFBTablerosComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
                //endregion

        //region Modulo 4
        SUAlimentacionElectricaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB22:
                        R22 = "B";

                        SUAlimentacionElectricaComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM22:
                        R22 = "M";
                        SUAlimentacionElectricaComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN22:
                        R22 = "N/A";
                        SUAlimentacionElectricaComentarios.setText("");
                        SUAlimentacionElectricaComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                SUAlarmasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB23:
                                R23 = "B";
                                SUAlarmasComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM23:
                        R23 = "M";
                                SUAlarmasComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN23:
                        R23 = "N/A";
                                SUAlarmasComentarios.setText("");
                                SUAlarmasComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        SUCargaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB24:
                        R24 = "B";
                        SUCargaComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM24:
                        R24 = "M";
                        SUCargaComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN24:
                        R24 = "N/A";
                        SUCargaComentarios.setText("");
                        SUCargaComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                SUDescargaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB25:
                        R25 = "B";
                                SUDescargaComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM25:
                        R25 = "M";
                                SUDescargaComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN25:
                        R25 = "N/A";
                                SUDescargaComentarios.setText("");
                                SUDescargaComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        //endregion
        //region Modulo 5 ()
        SCISistemaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB26:
                        R26 = "B";

                        SCISistemaComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM26:
                        R26 = "M";
                        SCISistemaComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN26:
                        R26 = "N/A";
                        SCISistemaComentarios.setText("");
                        SCISistemaComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                SCIDetectoresEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB27:
                        R27 = "B";
                                SCIDetectoresComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM27:
                        R27 = "M";
                                SCIDetectoresComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN27:
                        R27 = "N/A";
                                SCIDetectoresComentarios.setText("");
                                SCIDetectoresComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        SCIExtintoresEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB28:
                        R28 = "B";
                        SCIExtintoresComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM28:
                        R28 = "M";
                        SCIExtintoresComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN28:
                        R28 = "N/A";
                        SCIExtintoresComentarios.setText("");
                        SCIExtintoresComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                SCIGranadaTanquesEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB29:
                        R29 = "B";
                                SCIGranadaTanquesComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM29:
                        R29 = "M";
                                SCIGranadaTanquesComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN29:
                        R29 = "N/A";
                                SCIGranadaTanquesComentarios.setText("");
                                SCIGranadaTanquesComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        SCIFechaCaducidadEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB30:
                        R30 = "B";
                        SCIFechaCaducidadComentraios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM30:
                        R30 = "M";
                        SCIFechaCaducidadComentraios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN30:
                        R30 = "N/A";
                        SCIFechaCaducidadComentraios.setText("");
                        SCIFechaCaducidadComentraios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
        //endregion
        //region Modulo 6 ()
                PEFugasAceiteEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB31:
                        R31 = "B";
                        PEFugasAceiteComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM31:
                        R31 = "M";
                        PEFugasAceiteComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN31:
                        R31 = "N/A";
                        PEFugasAceiteComentarios.setText("");
                        PEFugasAceiteComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        PEFiltrosEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB32:
                        R32 = "B";
                        PEFiltrosComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM32:
                        R32 = "M";
                        PEFiltrosComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN32:
                        R32 = "N/A";
                        PEFiltrosComentarios.setText("");
                        PEFiltrosComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                PETemperaturaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB33:
                        R33 = "B";
                        PETemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM33:
                        R33 = "M";
                        PETemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN33:
                        R33 = "N/A";
                        PETemperaturaComentarios.setText("");
                        PETemperaturaComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        PEBandasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB34:
                        R34 = "B";
                        PEBandasComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM34:
                        R34 = "M";
                        PEBandasComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN34:
                        R34 = "N/A";
                        PEBandasComentarios.setText("");
                        PEBandasComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                PEBateriasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB35:
                        R35 = "B";
                        PEBateriasComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM35:
                        R35 = "M";
                        PEBateriasComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN35:
                        R35 = "N/A";
                        PEBateriasComentarios.setText("");
                        PEBateriasComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        PELubricacionEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB36:
                        R36 = "B";
                        PELubricacionComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM36:
                        R36 = "M";
                        PELubricacionComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN36:
                        R36 = "N/A";
                        PELubricacionComentarios.setText("");
                        PELubricacionComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                PECombustibleEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB37:
                        R37 = "B";
                        PECombustibleComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM37:
                        R37 = "M";
                        PECombustibleComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN37:
                        R37 = "N/A";
                        PECombustibleComentarios.setText("");
                        PECombustibleComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        PEArranqueManualEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB38:
                        R38 = "B";
                        PEArranqueManualComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM38:
                        R38 = "M";
                        PEArranqueManualComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN38:
                        R38 = "N/A";
                        PEArranqueManualComentarios.setText("");
                        PEArranqueManualComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                PELimpiezaGenetalEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB39:
                        R39 = "B";
                        PELimpiezaGenetalComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM39:
                        R39 = "M";
                        PELimpiezaGenetalComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN39:
                        R39 = "N/A";
                        PELimpiezaGenetalComentarios.setText("");
                        PELimpiezaGenetalComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        //endregion
        //region Mmodulo 7 ()
        SALectorasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB40:
                        R40 = "B";
                        SALectorasComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM40:
                        R40 = "M";
                        SALectorasComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN40:
                        R40 = "N/A";
                        SALectorasComentarios.setText("");
                        SALectorasComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
                SATablerosControlEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB41:
                        R41 = "B";
                                SATablerosControlComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM41:
                        R41 = "M";
                                SATablerosControlComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN41:
                        R41 = "N/A";
                                SATablerosControlComentarios.setText("");
                                SATablerosControlComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        //endregion
        //region Modulo 8 ()
        SSBarrasPuestaTierraEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB42:
                        R42 = "B";
                        SSBarrasPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM42:
                        R42 = "M";
                        SSBarrasPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN42:
                        R42 = "N/A";
                        SSBarrasPuestaTierraComentarios.setText("");
                        SSBarrasPuestaTierraComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                SSConexionPuestaTierraEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB43:
                        R43 = "B";
                        SSConexionPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM43:
                        R43 = "M";
                        SSConexionPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN43:
                        R43 = "N/A";
                        SSConexionPuestaTierraComentarios.setText("");
                        SSConexionPuestaTierraComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        SSTransformadorEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB44:
                        R44 = "B";
                        SSTransformadorComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM44:
                        R44 = "M";
                        SSTransformadorComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN44:
                        R44 = "N/A";
                        SSTransformadorComentarios.setText("");
                        SSTransformadorComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                SSFusiblesEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB45:
                        R45 = "B";
                        SSFusiblesComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM45:
                        R45 = "M";
                        SSFusiblesComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN45:
                        R45 = "N/A";
                        SSFusiblesComentarios.setText("");
                        SSFusiblesComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        SSTemperaturaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB46:
                        R46 = "B";
                        SSTemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM46:
                        R46 = "M";
                        SSTemperaturaComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN46:
                        R46 = "N/A";
                        SSTemperaturaComentarios.setText("");
                        SSTemperaturaComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                SSCuchillasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB47:
                        R47 = "B";
                        SSCuchillasComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM47:
                        R47 = "M";
                        SSCuchillasComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN47:
                        R47 = "N/A";
                        SSCuchillasComentarios.setText("");
                        SSCuchillasComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        SSInterruptoresEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB48:
                        R48 = "B";
                        SSInterruptoresComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM48:
                        R48 = "M";
                        SSInterruptoresComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN48:
                        R48 = "N/A";
                        SSInterruptoresComentarios.setText("");
                        SSInterruptoresComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        //endregion
        //region Modulo 9 ()
                TELimpizaGeneralEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB49:
                        R49 = "B";
                        TELimpizaGeneralComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM49:
                        R49 = "M";
                        TELimpizaGeneralComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN49:
                        R49 = "N/A";
                        TELimpizaGeneralComentarios.setText("");
                        TELimpizaGeneralComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        TEAnclasRetenidosEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB50:
                        R50 = "B";
                        TEAnclasRetenidosComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM50:
                        R50 = "M";
                        TEAnclasRetenidosComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN50:
                        R50 = "N/A";
                        TEAnclasRetenidosComentarios.setText("");
                        TEAnclasRetenidosComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                TELucesObstruccionEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB51:
                        R51 = "B";
                        TELucesObstruccionComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM51:
                        R51 = "M";
                        TELucesObstruccionComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN51:
                        R51 = "N/A";
                        TELucesObstruccionComentarios.setText("");
                        TELucesObstruccionComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        TETornilleriaEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB52:
                        R52 = "B";
                        TETornilleriaComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM52:
                        R52 = "M";
                        TETornilleriaComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN52:
                        R52 = "N/A";
                        TETornilleriaComentarios.setText("");
                        TETornilleriaComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
                TEPuestaTierraEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB53:
                        R53 = "B";
                        TEPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM53:
                        R53 = "M";
                        TEPuestaTierraComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN53:
                        R53 = "N/A";
                        TEPuestaTierraComentarios.setText("");
                        TEPuestaTierraComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        TESistemaApartaRayosEststus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB54:
                        R54 = "B";
                        TESistemaApartaRayosComentarios.setEnabled(true);
                       // mostrarParticular(true);
                        break;
                    case R.id.rbM54:
                        R54 = "M";
                        TESistemaApartaRayosComentarios.setEnabled(true);
                       // mostrarParticular(false);
                        break;
                    case R.id.rbN54:
                        R54 = "N/A";
                        TESistemaApartaRayosComentarios.setText("");
                        TESistemaApartaRayosComentarios.setEnabled(false);
                       // mostrarParticular(false);
                        break;
                }
            }
        });
        //endregion
        //region Modulo 10 ()
                HFugasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                        switch (checkedId){
                            case R.id.rbB55:
                        R55 = "B";
                                HFugasComentarios.setEnabled(true);
                                // mostrarParticular(true);
                                break;
                            case R.id.rbM55:
                        R55 = "M";
                                HFugasComentarios.setEnabled(true);
                                // mostrarParticular(false);
                                break;
                            case R.id.rbN55:
                        R55 = "N/A";
                                HFugasComentarios.setText("");
                                HFugasComentarios.setEnabled(false);
                                // mostrarParticular(false);
                                break;
                        }
                    }
                });
        HBombasEstatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbB56:
                        R56 = "B";
                        HBombasComentarios.setEnabled(true);
                        // mostrarParticular(true);
                        break;
                    case R.id.rbM56:
                        R56 = "M";
                        HBombasComentarios.setEnabled(true);
                        // mostrarParticular(false);
                        break;
                    case R.id.rbN56:
                        R56 = "N/A";
                        HBombasComentarios.setText("");
                        HBombasComentarios.setEnabled(false);
                        // mostrarParticular(false);
                        break;
                }
            }
        });
        //endregion
        //region Modulo 11 ()



        //endregion


        //region Mostrar datos guardados
        l1.setText(Formato.getL1());
        l2.setText(Formato.getSSL2());
        l3.setText(Formato.getSSL3());
        a1.setText(Formato.getSSA1());
        a2.setText(Formato.getSSA2());
        a3.setText(Formato.getSSA3());
        try {switch (R1) {case "B":AATemperaturaEstatus.check(R.id.rbB1); break; case "M":AATemperaturaEstatus.check(R.id.rbM1); break;case "N/A":AATemperaturaEstatus.check(R.id.rbN1);break;} }catch (Exception e){}
        try {switch (R2) {case "B":AACondensadoraEstatus.check(R.id.rbB2); break; case "M":AACondensadoraEstatus.check(R.id.rbM2); break;case "N/A":AACondensadoraEstatus.check(R.id.rbN2);break;} }catch (Exception e){}
        try {switch (R3) {case "B":AAEvaporadoraEstatus.check(R.id.rbB3); break; case "M":AAEvaporadoraEstatus.check(R.id.rbM3); break;case "N/A":AAEvaporadoraEstatus.check(R.id.rbN3);break;} }catch (Exception e){}
        try {switch (R4) {case "B":AASerpentinEstatus.check(R.id.rbB4); break; case "M":AASerpentinEstatus.check(R.id.rbM4); break;case "N/A":AASerpentinEstatus.check(R.id.rbN4);break;} }catch (Exception e){}
        try {switch (R5) {case "B":AAFugaGasEstatus.check(R.id.rbB5); break; case "M":AAFugaGasEstatus.check(R.id.rbM5); break;case "N/A":AAFugaGasEstatus.check(R.id.rbN5);break;} }catch (Exception e){}
        try {switch (R6) {case "B":AAAlimentacionEstatus.check(R.id.rbB6); break; case "M":AAAlimentacionEstatus.check(R.id.rbM6); break;case "N/A":AAAlimentacionEstatus.check(R.id.rbN6);break;} }catch (Exception e){}
        try {switch (R7) {case "B":AAFiltrosEstatus.check(R.id.rbB7); break; case "M":AAFiltrosEstatus.check(R.id.rbM7); break;case "N/A":AAFiltrosEstatus.check(R.id.rbN7);break;} }catch (Exception e){}
        try {switch (R8) {case "B":AALimpiezaGeneralEstatus.check(R.id.rbB8); break; case "M":AALimpiezaGeneralEstatus.check(R.id.rbM8); break;case "N/A":AALimpiezaGeneralEstatus.check(R.id.rbN8);break;} }catch (Exception e){}
        try {switch (R9) {case "B":ECIluminaciionEstatus.check(R.id.rbB9); break; case "M":ECIluminaciionEstatus.check(R.id.rbM9); break;case "N/A":ECIluminaciionEstatus.check(R.id.rbN9);break;} }catch (Exception e){}
        try {switch (R10) {case "B":ECPinturaEstatus.check(R.id.rbB10); break; case "M":ECPinturaEstatus.check(R.id.rbM10); break;case "N/A":ECPinturaEstatus.check(R.id.rbN10);break;} }catch (Exception e){}
        try {switch (R11) {case "B":ECPisosEstatus.check(R.id.rbB11); break; case "M":ECPisosEstatus.check(R.id.rbM11); break;case "N/A":ECPisosEstatus.check(R.id.rbN11);break;} }catch (Exception e){}
        try {switch (R12) {case "B":ECImpermeEstatus.check(R.id.rbB12); break; case "M":ECImpermeEstatus.check(R.id.rbM12); break;case "N/A":ECImpermeEstatus.check(R.id.rbN12);break;} }catch (Exception e){}
        try {switch (R13) {case "B":ECHidrosanitarioEstatus.check(R.id.rbB13); break; case "M":ECHidrosanitarioEstatus.check(R.id.rbM13); break;case "N/A":ECHidrosanitarioEstatus.check(R.id.rbN13);break;} }catch (Exception e){}
        try {switch (R14) {case "B":ECHerrejesEstatus.check(R.id.rbB14); break; case "M":ECHerrejesEstatus.check(R.id.rbM14); break;case "N/A":ECHerrejesEstatus.check(R.id.rbN14);break;} }catch (Exception e){}
        try {switch (R15) {case "B":ECLimpiezaGeneralEstatus.check(R.id.rbB15); break; case "M":ECLimpiezaGeneralEstatus.check(R.id.rbM15); break;case "N/A":ECLimpiezaGeneralEstatus.check(R.id.rbN15);break;} }catch (Exception e){}
        try {switch (R16) {case "B":PFBAmperajeEstatus.check(R.id.rbB16); break; case "M":PFBAmperajeEstatus.check(R.id.rbM16); break;case "N/A":PFBAmperajeEstatus.check(R.id.rbN16);break;} }catch (Exception e){}
        try {switch (R17) {case "B":PFBConsumoPlantaEstatus.check(R.id.rbB17); break; case "M":PFBConsumoPlantaEstatus.check(R.id.rbM17); break;case "N/A":PFBConsumoPlantaEstatus.check(R.id.rbN17);break;} }catch (Exception e){}
        try {switch (R18) {case "B":PFBRectificadoresEstatus.check(R.id.rbB18); break; case "M":PFBRectificadoresEstatus.check(R.id.rbM18); break;case "N/A":PFBRectificadoresEstatus.check(R.id.rbN18);break;} }catch (Exception e){}
        try {switch (R19) {case "B":PFBSistemaInversorEstatus.check(R.id.rbB19); break; case "M":PFBSistemaInversorEstatus.check(R.id.rbM19); break;case "N/A":PFBSistemaInversorEstatus.check(R.id.rbN19);break;} }catch (Exception e){}
        try {switch (R20) {case "B":PFBBancosBateriasEstatus.check(R.id.rbB20); break; case "M":PFBBancosBateriasEstatus.check(R.id.rbM20); break;case "N/A":PFBBancosBateriasEstatus.check(R.id.rbN20);break;} }catch (Exception e){}
        try {switch (R21) {case "B":PFBTablerosEstatus.check(R.id.rbB21); break; case "M":PFBTablerosEstatus.check(R.id.rbM21); break;case "N/A":PFBTablerosEstatus.check(R.id.rbN21);break;} }catch (Exception e){}
        try {switch (R22) {case "B":SUAlimentacionElectricaEstatus.check(R.id.rbB22); break; case "M":SUAlimentacionElectricaEstatus.check(R.id.rbM22); break;case "N/A":SUAlimentacionElectricaEstatus.check(R.id.rbN22);break;} }catch (Exception e){}
        try {switch (R23) {case "B":SUAlarmasEstatus.check(R.id.rbB23); break; case "M":SUAlarmasEstatus.check(R.id.rbM23); break;case "N/A":SUAlarmasEstatus.check(R.id.rbN23);break;} }catch (Exception e){}
        try {switch (R24) {case "B":SUCargaEstatus.check(R.id.rbB24); break; case "M":SUCargaEstatus.check(R.id.rbM24); break;case "N/A":SUCargaEstatus.check(R.id.rbN24);break;} }catch (Exception e){}
        try {switch (R25) {case "B":SUDescargaEstatus.check(R.id.rbB25); break; case "M":SUDescargaEstatus.check(R.id.rbM25); break;case "N/A":SUDescargaEstatus.check(R.id.rbN25);break;} }catch (Exception e){}
        try {switch (R26) {case "B":SCISistemaEstatus.check(R.id.rbB26); break; case "M":SCISistemaEstatus.check(R.id.rbM26); break;case "N/A":SCISistemaEstatus.check(R.id.rbN26);break;} }catch (Exception e){}
        try {switch (R27) {case "B":SCIDetectoresEstatus.check(R.id.rbB27); break; case "M":SCIDetectoresEstatus.check(R.id.rbM27); break;case "N/A":SCIDetectoresEstatus.check(R.id.rbN27);break;} }catch (Exception e){}
        try {switch (R28) {case "B":SCIExtintoresEstatus.check(R.id.rbB28); break; case "M":SCIExtintoresEstatus.check(R.id.rbM28); break;case "N/A":SCIExtintoresEstatus.check(R.id.rbN28);break;} }catch (Exception e){}
        try {switch (R29) {case "B":SCIGranadaTanquesEstatus.check(R.id.rbB29); break; case "M":SCIGranadaTanquesEstatus.check(R.id.rbM29); break;case "N/A":SCIGranadaTanquesEstatus.check(R.id.rbN29);break;} }catch (Exception e){}
        try {switch (R30) {case "B":SCIFechaCaducidadEstatus.check(R.id.rbB30); break; case "M":SCIFechaCaducidadEstatus.check(R.id.rbM30); break;case "N/A":SCIFechaCaducidadEstatus.check(R.id.rbN30);break;} }catch (Exception e){}
        try {switch (R31) {case "B":PEFugasAceiteEstatus.check(R.id.rbB31); break; case "M":PEFugasAceiteEstatus.check(R.id.rbM31); break;case "N/A":PEFugasAceiteEstatus.check(R.id.rbN31);break;} }catch (Exception e){}
        try {switch (R32) {case "B":PEFiltrosEstatus.check(R.id.rbB32); break; case "M":PEFiltrosEstatus.check(R.id.rbM32); break;case "N/A":PEFiltrosEstatus.check(R.id.rbN32);break;} }catch (Exception e){}
        try {switch (R33) {case "B":PETemperaturaEstatus.check(R.id.rbB33); break; case "M":PETemperaturaEstatus.check(R.id.rbM33); break;case "N/A":PETemperaturaEstatus.check(R.id.rbN33);break;} }catch (Exception e){}
        try {switch (R34) {case "B":PEBandasEstatus.check(R.id.rbB34); break; case "M":PEBandasEstatus.check(R.id.rbM34); break;case "N/A":PEBandasEstatus.check(R.id.rbN34);break;} }catch (Exception e){}
        try {switch (R35) {case "B":PEBateriasEstatus.check(R.id.rbB35); break; case "M":PEBateriasEstatus.check(R.id.rbM35); break;case "N/A":PEBateriasEstatus.check(R.id.rbN35);break;} }catch (Exception e){}
        try {switch (R36) {case "B":PELubricacionEstatus.check(R.id.rbB36); break; case "M":PELubricacionEstatus.check(R.id.rbM36); break;case "N/A":PELubricacionEstatus.check(R.id.rbN36);break;} }catch (Exception e){}
        try {switch (R37) {case "B":PECombustibleEstatus.check(R.id.rbB37); break; case "M":PECombustibleEstatus.check(R.id.rbM37); break;case "N/A":PECombustibleEstatus.check(R.id.rbN37);break;} }catch (Exception e){}
        try {switch (R38) {case "B":PEArranqueManualEstatus.check(R.id.rbB38); break; case "M":PEArranqueManualEstatus.check(R.id.rbM38); break;case "N/A":PEArranqueManualEstatus.check(R.id.rbN38);break;} }catch (Exception e){}
        try {switch (R39) {case "B":PELimpiezaGenetalEstatus.check(R.id.rbB39); break; case "M":PELimpiezaGenetalEstatus.check(R.id.rbM39); break;case "N/A":PELimpiezaGenetalEstatus.check(R.id.rbN39);break;} }catch (Exception e){}
        try {switch (R40) {case "B":SALectorasEstatus.check(R.id.rbB40); break; case "M":SALectorasEstatus.check(R.id.rbM40); break;case "N/A":SALectorasEstatus.check(R.id.rbN40);break;} }catch (Exception e){}
        try {switch (R41) {case "B":SATablerosControlEstatus.check(R.id.rbB41); break; case "M":SATablerosControlEstatus.check(R.id.rbM41); break;case "N/A":SATablerosControlEstatus.check(R.id.rbN41);break;} }catch (Exception e){}
        try {switch (R42) {case "B":SSBarrasPuestaTierraEstatus.check(R.id.rbB42); break; case "M":SSBarrasPuestaTierraEstatus.check(R.id.rbM42); break;case "N/A":SSBarrasPuestaTierraEstatus.check(R.id.rbN42);break;} }catch (Exception e){}
        try {switch (R43) {case "B":SSConexionPuestaTierraEstatus.check(R.id.rbB43); break; case "M":SSConexionPuestaTierraEstatus.check(R.id.rbM43); break;case "N/A":SSConexionPuestaTierraEstatus.check(R.id.rbN43);break;} }catch (Exception e){}
        try {switch (R44) {case "B":SSTransformadorEstatus.check(R.id.rbB44); break; case "M":SSTransformadorEstatus.check(R.id.rbM44); break;case "N/A":SSTransformadorEstatus.check(R.id.rbN44);break;} }catch (Exception e){}
        try {switch (R45) {case "B":SSFusiblesEstatus.check(R.id.rbB45); break; case "M":SSFusiblesEstatus.check(R.id.rbM45); break;case "N/A":SSFusiblesEstatus.check(R.id.rbN45);break;} }catch (Exception e){}
        try {switch (R46) {case "B":SSTemperaturaEstatus.check(R.id.rbB46); break; case "M":SSTemperaturaEstatus.check(R.id.rbM46); break;case "N/A":SSTemperaturaEstatus.check(R.id.rbN46);break;} }catch (Exception e){}
        try {switch (R47) {case "B":SSCuchillasEstatus.check(R.id.rbB47); break; case "M":SSCuchillasEstatus.check(R.id.rbM47); break;case "N/A":SSCuchillasEstatus.check(R.id.rbN47);break;} }catch (Exception e){}
        try {switch (R48) {case "B":SSInterruptoresEstatus.check(R.id.rbB48); break; case "M":SSInterruptoresEstatus.check(R.id.rbM48); break;case "N/A":SSInterruptoresEstatus.check(R.id.rbN48);break;} }catch (Exception e){}
        try {switch (R49) {case "B":TELimpizaGeneralEstatus.check(R.id.rbB49); break; case "M":TELimpizaGeneralEstatus.check(R.id.rbM49); break;case "N/A":TELimpizaGeneralEstatus.check(R.id.rbN49);break;} }catch (Exception e){}
        try {switch (R50) {case "B":TEAnclasRetenidosEstatus.check(R.id.rbB50); break; case "M":TEAnclasRetenidosEstatus.check(R.id.rbM50); break;case "N/A":TEAnclasRetenidosEstatus.check(R.id.rbN50);break;} }catch (Exception e){}
        try {switch (R51) {case "B":TELucesObstruccionEstatus.check(R.id.rbB51); break; case "M":TELucesObstruccionEstatus.check(R.id.rbM51); break;case "N/A":TELucesObstruccionEstatus.check(R.id.rbN51);break;} }catch (Exception e){}
        try {switch (R52) {case "B":TETornilleriaEstatus.check(R.id.rbB52); break; case "M":TETornilleriaEstatus.check(R.id.rbM52); break;case "N/A":TETornilleriaEstatus.check(R.id.rbN52);break;} }catch (Exception e){}
        try {switch (R53) {case "B":TEPuestaTierraEstatus.check(R.id.rbB53); break; case "M":TEPuestaTierraEstatus.check(R.id.rbM53); break;case "N/A":TEPuestaTierraEstatus.check(R.id.rbN53);break;} }catch (Exception e){}
        try {switch (R54) {case "B":TESistemaApartaRayosEststus.check(R.id.rbB54); break; case "M":TESistemaApartaRayosEststus.check(R.id.rbM54); break;case "N/A":TESistemaApartaRayosEststus.check(R.id.rbN54);break;} }catch (Exception e){}
        try {switch (R55) {case "B":HFugasEstatus.check(R.id.rbB55); break; case "M":HFugasEstatus.check(R.id.rbM55); break;case "N/A":HFugasEstatus.check(R.id.rbN55);break;} }catch (Exception e){}
        try {switch (R56) {case "B":HBombasEstatus.check(R.id.rbB56); break; case "M":HBombasEstatus.check(R.id.rbM56); break;case "N/A":HBombasEstatus.check(R.id.rbN56);break;} }catch (Exception e){}




        AATemperaturaComentarios.setText(Formato.getAATemperaturaComentarios());
        AACondensadoraComentarios.setText(Formato.getAACondensadoraComentarios());
        AAEvaporadoraComentarios.setText(Formato.getAAEvaporadoraComentarios());
        AASerpentinComentarios.setText(Formato.getAASerpentinComentarios());
        AAFugaGasComentarios.setText(Formato.getAAFugaGasComentarios());
        AAAlimentacionComentarios.setText(Formato.getAAAlimentacionComentarios());
        AAFiltrosComentarios.setText(Formato.getAAFiltrosComentarios());
        AALimpiezaGeneralComentarios.setText(Formato.getAALimpiezaGeneralComentarios());

        ECIluminaciionComentarios.setText(Formato.getECIluminaciionComentarios());
        ECPinturaComentarios.setText(Formato.getECPinturaComentarios());
        ECPisosComentarios.setText(Formato.getECPisosComentarios());
        ECImpermeComentarios.setText(Formato.getECImpermeComentarios());
        ECHidrosanitarioComentarios.setText(Formato.getECHidrosanitarioComentarios());
        ECHerrejesComentarios.setText(Formato.getECHerrejesComentarios());
        ECLimpiezaGeneralComentarios.setText(Formato.getECLimpiezaGeneralComentarios());

        PFBAmperajeComentarios.setText(Formato.getPFBAmperajeComentarios());
        PFBConsumoPlantaComentarios.setText(Formato.getPFBConsumoPlantaComentarios());
        PFBRectificadoresComentarios.setText(Formato.getPFBRectificadoresComentarios());
        PFBSistemaInversorComentarios.setText(Formato.getPFBSistemaInversorComentarios());
        PFBBancosBateriasComentarios.setText(Formato.getPFBBancosBateriasComentarios());
        PFBTablerosComentarios.setText(Formato.getPFBTablerosComentarios());

        SUAlimentacionElectricaComentarios.setText(Formato.getSUAlimentacionElectricaComentarios());
        SUAlarmasComentarios.setText(Formato.getSUAlarmasComentarios());
        SUCargaComentarios.setText(Formato.getSUCargaComentarios());
        SUDescargaComentarios.setText(Formato.getSUDescargaComentarios());

        SCISistemaComentarios.setText(Formato.getSCISistemaComentarios());
        SCIDetectoresComentarios.setText(Formato.getSCIDetectoresComentarios());
        SCIExtintoresComentarios.setText(Formato.getSCIExtintoresComentarios());
        SCIGranadaTanquesComentarios.setText(Formato.getSCIGranadaTanquesComentarios());
        SCIFechaCaducidadComentraios.setText(Formato.getSCIFechaCaducidadComentraios());

        PEFugasAceiteComentarios.setText(Formato.getPEFugasAceiteComentarios());
        PEFiltrosComentarios.setText(Formato.getPEFiltrosComentarios());
        PETemperaturaComentarios.setText(Formato.getPETemperaturaComentarios());
        PEBandasComentarios.setText(Formato.getPEBandasComentarios());
        PEBateriasComentarios.setText(Formato.getPEBateriasComentarios());
        PELubricacionComentarios.setText(Formato.getPELubricacionComentarios());
        PECombustibleComentarios.setText(Formato.getPECombustibleComentarios());
        PEArranqueManualComentarios.setText(Formato.getPEArranqueManualComentarios());
        PELimpiezaGenetalComentarios.setText(Formato.getPELimpiezaGenetalComentarios());

        SALectorasComentarios.setText(Formato.getSALectorasComentarios());
        SATablerosControlComentarios.setText(Formato.getSATablerosControlComentarios());

        SSBarrasPuestaTierraComentarios.setText(Formato.getSSBarrasPuestaTierraComentarios());
        SSConexionPuestaTierraComentarios.setText(Formato.getSSConexionPuestaTierraComentarios());
        SSTransformadorComentarios.setText(Formato.getSSTransformadorComentarios());
        SSFusiblesComentarios.setText(Formato.getSSFusiblesComentarios());
        SSTemperaturaComentarios.setText(Formato.getSSTemperaturaComentarios());
        SSCuchillasComentarios.setText(Formato.getSSCuchillasComentarios());
        SSInterruptoresComentarios.setText(Formato.getSSInterruptoresComentarios());

        TELimpizaGeneralComentarios.setText(Formato.getTELimpizaGeneralComentarios());
        TEAnclasRetenidosComentarios.setText(Formato.getTEAnclasRetenidosComentarios());
        TELucesObstruccionComentarios.setText(Formato.getTELucesObstruccionComentarios());
        TETornilleriaComentarios.setText(Formato.getTETornilleriaComentarios());
        TEPuestaTierraComentarios.setText(Formato.getTEPuestaTierraComentarios());
        TESistemaApartaRayosComentarios.setText(Formato.getTESistemaApartaRayosComentarios());

        HFugasComentarios.setText(Formato.getHFugasComentarios());
        HBombasComentarios.setText(Formato.getHBombasComentarios());

        Otros1.setText(Formato.getOtros());
        comentario1.setText(Formato.getComentarios());
        Otros2.setText(Formato.getOtros1());
        comentario2.setText(Formato.getComentarios1());
        Otros3.setText(Formato.getOtros2());
        comentario3.setText(Formato.getComentarios2());
        Otros4.setText(Formato.getOtros3());
        comentario4.setText(Formato.getComentarios3());
        Otros5.setText(Formato.getOtros4());
        comentario5.setText(Formato.getComentarios4());
        Otros6.setText(Formato.getOtros5());
        comentario6.setText(Formato.getComentarios5());
        Otros7.setText(Formato.getOtros6());
        comentario7.setText(Formato.getComentarios6());
        Otros8.setText(Formato.getOtros7());
        comentario8.setText(Formato.getComentarios7());




//endregion






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

                if(!Otros8.getText().toString().isEmpty() && !Otros8.getText().toString().equals(" ")){
                    //Formato.setOtros7(Otros8.getText().toString());
                }else{
                    Otros8.setText(" ");
                }
                if(!comentario8.getText().toString().isEmpty() && !comentario8.getText().toString().equals(" ")){
                    //Formato.setComentarios7(comentario8.getText().toString());
                }else{
                    comentario8.setText(" ");
                }


            }
        });
        //endregion

        Button back = (Button)v.findViewById(R.id.PW_BTN_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                IN1Menu myfargment = new IN1Menu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region Guardar Datos

                Formato.setAATemperaturaEstatus(R1);
                Formato.setAACondensadoraEstatus(R2);
                Formato.setAAEvaporadoraEstatus(R3);
                Formato.setAASerpentinEstatus(R4);
                Formato.setAAFugaGasEstatus(R5);
                Formato.setAAAlimentacionEstatus(R6);
                Formato.setAAFiltrosEstatus(R7);
                Formato.setAALimpiezaGeneralEstatus(R8);
                Formato.setECIluminaciionEstatus(R9);
                Formato.setECPinturaEstatus(R10);
                Formato.setECPisosEstatus(R11);
                Formato.setECImpermeEstatus(R12);
                Formato.setECHidrosanitarioEstatus(R13);
                Formato.setECHerrejesEstatus(R14);
                Formato.setECLimpiezaGeneralEstatus(R15);
                Formato.setPFBAmperajeEstatus(R16);
                Formato.setPFBConsumoPlantaEstatus(R17);
                Formato.setPFBRectificadoresEstatus(R18);
                Formato.setPFBSistemaInversorEstatus(R19);
                Formato.setPFBBancosBateriasEstatus(R20);
                Formato.setPFBTablerosEstatus(R21);
                Formato.setSUAlimentacionElectricaEstatus(R22);
                Formato.setSUAlarmasEstatus(R23);
                Formato.setSUCargaEstatus(R24);
                Formato.setSUDescargaEstatus(R25);
                Formato.setSCISistemaEstatus(R26);
                Formato.setSCIDetectoresEstatus(R27);
                Formato.setSCIExtintoresEstatus(R28);
                Formato.setSCIGranadaTanquesEstatus(R29);
                Formato.setSCIFechaCaducidadEstatus(R30);
                Formato.setPEFugasAceiteEstatus(R31);
                Formato.setPEFiltrosEstatus(R32);
                Formato.setPETemperaturaEstatus(R33);
                Formato.setPEBandasEstatus(R34);
                Formato.setPEBateriasEstatus(R35);
                Formato.setPELubricacionEstatus(R36);
                Formato.setPECombustibleEstatus(R37);
                Formato.setPEArranqueManualEstatus(R38);
                Formato.setPELimpiezaGenetalEstatus(R39);
                Formato.setSALectorasEstatus(R40);
                Formato.setSATablerosControlEstatus(R41);
                Formato.setSSBarrasPuestaTierraEstatus(R42);
                Formato.setSSConexionPuestaTierraEstatus(R43);
                Formato.setSSTransformadorEstatus(R44);
                Formato.setSSFusiblesEstatus(R45);
                Formato.setSSTemperaturaEstatus(R46);
                Formato.setSSCuchillasEstatus(R47);
                Formato.setSSInterruptoresEstatus(R48);
                Formato.setTELimpizaGeneralEstatus(R49);
                Formato.setTEAnclasRetenidosEstatus(R50);
                Formato.setTELucesObstruccionEstatus(R51);
                Formato.setTETornilleriaEstatus(R52);
                Formato.setTEPuestaTierraEstatus(R53);
                Formato.setTESistemaApartaRayosEststus(R54);
                Formato.setHFugasEstatus(R55);
                Formato.setHBombasEstatus(R56);
                Formato.setSSL1(l1.getText().toString());
                Formato.setSSL2(l2.getText().toString());
                Formato.setSSL3(l3.getText().toString());
                Formato.setSSA1(a1.getText().toString());
                Formato.setSSA2(a2.getText().toString());
                Formato.setSSA3(a3.getText().toString());
                Formato.setOtros(Otros1.getText().toString());
                Formato.setComentarios(comentario1.getText().toString());
                Formato.setOtros1(Otros2.getText().toString());
                Formato.setComentarios1(comentario2.getText().toString());
                Formato.setOtros2(Otros3.getText().toString());
                Formato.setComentarios2(comentario3.getText().toString());
                Formato.setOtros3(Otros4.getText().toString());
                Formato.setComentarios3(comentario4.getText().toString());
                Formato.setOtros4(Otros5.getText().toString());
                Formato.setComentarios4(comentario5.getText().toString());
                Formato.setOtros5(Otros6.getText().toString());
                Formato.setComentarios5(comentario6.getText().toString());
                Formato.setOtros6(Otros7.getText().toString());
                Formato.setComentarios6(comentario7.getText().toString());
                Formato.setOtros7(Otros8.getText().toString());
                Formato.setComentarios7(comentario8.getText().toString());

               /* if(!Otros8.getText().toString().isEmpty() && !Otros8.getText().toString().equals(" ")){
                    Formato.setOtros7(Otros8.getText().toString());
                }else{
                    Formato.setOtros7(" ");
                }
                if(!comentario8.getText().toString().isEmpty() && !comentario8.getText().toString().equals(" ")){
                    Formato.setComentarios7(comentario8.getText().toString());
                }else{
                    Formato.setComentarios7(" ");
                }*/





                Formato.setAATemperaturaComentarios(AATemperaturaComentarios.getText().toString());
                Formato.setAACondensadoraComentarios(AACondensadoraComentarios.getText().toString());
                Formato.setAAEvaporadoraComentarios(AAEvaporadoraComentarios.getText().toString());
                Formato.setAASerpentinComentarios(AASerpentinComentarios.getText().toString());
                Formato.setAAFugaGasComentarios(AAFugaGasComentarios.getText().toString());
                Formato.setAAAlimentacionComentarios(AAAlimentacionComentarios.getText().toString());
                Formato.setAAFiltrosComentarios(AAFiltrosComentarios.getText().toString());
                Formato.setAALimpiezaGeneralComentarios(AALimpiezaGeneralComentarios.getText().toString());
                Formato.setECIluminaciionComentarios(ECIluminaciionComentarios.getText().toString());
                Formato.setECPinturaComentarios(ECPinturaComentarios.getText().toString());
                Formato.setECPisosComentarios(ECPisosComentarios.getText().toString());
                Formato.setECImpermeComentarios(ECImpermeComentarios.getText().toString());
                Formato.setECHidrosanitarioComentarios(ECHidrosanitarioComentarios.getText().toString());
                Formato.setECHerrejesComentarios(ECHerrejesComentarios.getText().toString());
                Formato.setECLimpiezaGeneralComentarios(ECLimpiezaGeneralComentarios.getText().toString());
                Formato.setPFBAmperajeComentarios(PFBAmperajeComentarios.getText().toString());
                Formato.setPFBConsumoPlantaComentarios(PFBConsumoPlantaComentarios.getText().toString());
                Formato.setPFBRectificadoresComentarios(PFBRectificadoresComentarios.getText().toString());
                Formato.setPFBSistemaInversorComentarios(PFBSistemaInversorComentarios.getText().toString());
                Formato.setPFBBancosBateriasComentarios(PFBBancosBateriasComentarios.getText().toString());
                Formato.setPFBTablerosComentarios(PFBTablerosComentarios.getText().toString());
                Formato.setSUAlimentacionElectricaComentarios(SUAlimentacionElectricaComentarios.getText().toString());
                Formato.setSUAlarmasComentarios(SUAlarmasComentarios.getText().toString());
                Formato.setSUCargaComentarios(SUCargaComentarios.getText().toString());
                Formato.setSUDescargaComentarios(SUDescargaComentarios.getText().toString());
                Formato.setSCISistemaComentarios(SCISistemaComentarios.getText().toString());
                Formato.setSCIDetectoresComentarios(SCIDetectoresComentarios.getText().toString());
                Formato.setSCIExtintoresComentarios(SCIExtintoresComentarios.getText().toString());
                Formato.setSCIGranadaTanquesComentarios(SCIGranadaTanquesComentarios.getText().toString());
                Formato.setSCIFechaCaducidadComentraios(SCIFechaCaducidadComentraios.getText().toString());
                Formato.setPEFugasAceiteComentarios(PEFugasAceiteComentarios.getText().toString());
                Formato.setPEFiltrosComentarios(PEFiltrosComentarios.getText().toString());
                Formato.setPETemperaturaComentarios(PETemperaturaComentarios.getText().toString());
                Formato.setPEBandasComentarios(PEBandasComentarios.getText().toString());
                Formato.setPEBateriasComentarios(PEBateriasComentarios.getText().toString());
                Formato.setPELubricacionComentarios(PELubricacionComentarios.getText().toString());
                Formato.setPECombustibleComentarios(PECombustibleComentarios.getText().toString());
                Formato.setPEArranqueManualComentarios(PEArranqueManualComentarios.getText().toString());
                Formato.setPELimpiezaGenetalComentarios(PELimpiezaGenetalComentarios.getText().toString());
                Formato.setSALectorasComentarios(SALectorasComentarios.getText().toString());
                Formato.setSATablerosControlComentarios(SATablerosControlComentarios.getText().toString());
                Formato.setSSBarrasPuestaTierraComentarios(SSBarrasPuestaTierraComentarios.getText().toString());
                Formato.setSSConexionPuestaTierraComentarios(SSConexionPuestaTierraComentarios.getText().toString());
                Formato.setSSTransformadorComentarios(SSTransformadorComentarios.getText().toString());
                Formato.setSSFusiblesComentarios(SSFusiblesComentarios.getText().toString());
                Formato.setSSTemperaturaComentarios(SSTemperaturaComentarios.getText().toString());
                Formato.setSSCuchillasComentarios(SSCuchillasComentarios.getText().toString());
                Formato.setSSInterruptoresComentarios(SSInterruptoresComentarios.getText().toString());
                Formato.setTELimpizaGeneralComentarios(TELimpizaGeneralComentarios.getText().toString());
                Formato.setTEAnclasRetenidosComentarios(TEAnclasRetenidosComentarios.getText().toString());
                Formato.setTELucesObstruccionComentarios(TELucesObstruccionComentarios.getText().toString());
                Formato.setTETornilleriaComentarios(TETornilleriaComentarios.getText().toString());
                Formato.setTEPuestaTierraComentarios(TEPuestaTierraComentarios.getText().toString());
                Formato.setTESistemaApartaRayosComentarios(TESistemaApartaRayosComentarios.getText().toString());
                Formato.setHFugasComentarios(HFugasComentarios.getText().toString());
                Formato.setHBombasComentarios(HBombasComentarios.getText().toString());

//endregion

                D_B.guardarBestel1(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                IN1Menu myfargment = new IN1Menu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



                return v;
    }

}
