package com.maju.desarrollo.testfcs.Formatos.Thermal;
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
import android.widget.RadioButton;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.ThermalManagagementS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThermParametrosFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false,ActivoM4 = false,ActivoM5 = false,
            ActivoM6 = false,ActivoM7 = false,ActivoM8 = false,ActivoM9 = false,ActivoM10 = false,
            ActivoM11 = false,ActivoM12 = false;;

    TextView fechacontorolP,campo27;
    ThermalManagagementS Formato;



    public ThermParametrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v =  inflater.inflate(R.layout.fragment_therm_parametros, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");

        Formato = D_B.obtenerThermal(id_formato);


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

        //region RELACION CAMPOS
        final EditText SETTEMP = (EditText)v.findViewById(R.id.SETTEMP);
        final EditText SETHUM = (EditText)v.findViewById(R.id.SETHUM);
        final EditText SENS = (EditText)v.findViewById(R.id.SENS);
        final EditText SENS2 = (EditText)v.findViewById(R.id.SENS2);
        final EditText HITEMP = (EditText)v.findViewById(R.id.HITEMP);
        final EditText HIHUM = (EditText)v.findViewById(R.id.HIHUM);
        final EditText LOTEMP = (EditText)v.findViewById(R.id.LOTEMP);
        final EditText LOHUM = (EditText)v.findViewById(R.id.LOHUM);
        final EditText INYECCION = (EditText)v.findViewById(R.id.INYECCION);
        final EditText RETORNO = (EditText)v.findViewById(R.id.RETORNO);
        final EditText TIPOMICROPROCESADOR = (EditText)v.findViewById(R.id.TIPOMICROPROCESADOR);
        final EditText MarcaUniMane = (EditText)v.findViewById(R.id.MarcaUniMane);
        final EditText TipoEvaporadorUniMane = (EditText)v.findViewById(R.id.TipoEvaporadorUniMane);
        final EditText ModeloUniMane = (EditText)v.findViewById(R.id.ModeloUniMane);
        final EditText VoltajeGeneralL1L2 = (EditText)v.findViewById(R.id.VoltajeGeneralL1L2);
        final EditText VoltajeGeneralL2L3 = (EditText)v.findViewById(R.id.VoltajeGeneralL2L3);
        final EditText VoltajeGeneralL3L1 = (EditText)v.findViewById(R.id.VoltajeGeneralL3L1);
        final EditText NoSerieUniMane = (EditText)v.findViewById(R.id.NoSerieUniMane);

        final EditText campo18 = (EditText)v.findViewById(R.id.campo18);
        final EditText campo19 = (EditText)v.findViewById(R.id.campo19);
        final EditText campo20 = (EditText)v.findViewById(R.id.campo20);
        final EditText campo21 = (EditText)v.findViewById(R.id.campo21);
        final EditText campo22 = (EditText)v.findViewById(R.id.campo22);
        final EditText campo23 = (EditText)v.findViewById(R.id.campo23);
        final EditText campo24 = (EditText)v.findViewById(R.id.campo24);
        final EditText campo25 = (EditText)v.findViewById(R.id.campo25);
        final EditText campo26 = (EditText)v.findViewById(R.id.campo26);
        final EditText campo27 = (EditText)v.findViewById(R.id.campo27);
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
        final EditText campo46B = (EditText)v.findViewById(R.id.campo46B);
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
        final EditText campo63 = (EditText)v.findViewById(R.id.campo63);
        final EditText campo63B = (EditText)v.findViewById(R.id.campo63B);
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
        final EditText campo88 = (EditText)v.findViewById(R.id.campo88);
        final EditText campo89 = (EditText)v.findViewById(R.id.campo89);
        final EditText campo90 = (EditText)v.findViewById(R.id.campo90);
        final EditText campo91 = (EditText)v.findViewById(R.id.campo91);
        final EditText campo92 = (EditText)v.findViewById(R.id.campo92);
        final EditText campo93 = (EditText)v.findViewById(R.id.campo93);
        final EditText campo94 = (EditText)v.findViewById(R.id.campo94);
        final EditText campo95 = (EditText)v.findViewById(R.id.campo95);
        final EditText campo96 = (EditText)v.findViewById(R.id.campo96);
        final EditText campo97 = (EditText)v.findViewById(R.id.campo97);
        final EditText campo98 = (EditText)v.findViewById(R.id.campo98);
        final EditText campo99 = (EditText)v.findViewById(R.id.campo99);
        final EditText campo100 = (EditText)v.findViewById(R.id.campo100);
        final EditText campo101 = (EditText)v.findViewById(R.id.campo101);
        final EditText campo102 = (EditText)v.findViewById(R.id.campo102);
        final EditText campo103 = (EditText)v.findViewById(R.id.campo103);
        final EditText campo104 = (EditText)v.findViewById(R.id.campo104);
        final EditText campo105 = (EditText)v.findViewById(R.id.campo105);
        final EditText campo106 = (EditText)v.findViewById(R.id.campo106);
        final EditText campo107 = (EditText)v.findViewById(R.id.campo107);
        final EditText campo108 = (EditText)v.findViewById(R.id.campo108);
        final EditText campo109 = (EditText)v.findViewById(R.id.campo109);
        final EditText campo110 = (EditText)v.findViewById(R.id.campo110);
        final EditText campo111 = (EditText)v.findViewById(R.id.campo111);
        final EditText campo112 = (EditText)v.findViewById(R.id.campo112);
        final EditText campo113 = (EditText)v.findViewById(R.id.campo113);
        final EditText campo114 = (EditText)v.findViewById(R.id.campo114);
        final EditText campo115 = (EditText)v.findViewById(R.id.campo115);
        final EditText campo116 = (EditText)v.findViewById(R.id.campo116);

        final RadioButton rd_seco1 = (RadioButton) v.findViewById(R.id.rd_seco1);
        final RadioButton rd_humedo1 = (RadioButton) v.findViewById(R.id.rd_humedo1);
        final RadioButton rd_seco2 = (RadioButton) v.findViewById(R.id.rd_seco2);
        final RadioButton rd_humedo2 = (RadioButton) v.findViewById(R.id.rd_humedo2);

        //endregion

        //region MOSTRAR DATOS GUARDADOS
        try{if(Formato.getMiraIndicadoraHumedo1().equals("true")){rd_humedo1.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getMiraIndicadoraSeco1().equals("true")){rd_seco1.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getMiraIndicadoraHumedo2().equals("true")){rd_humedo2.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getMiraIndicadoraSeco2().equals("true")){rd_seco2.setChecked(true);}}catch (Exception e){}

        try{SETTEMP.setText(Formato.getSETTEMP());}catch (Exception e){}
        try{SETHUM.setText(Formato.getSETHUM());}catch (Exception e){}
        try{SENS.setText(Formato.getSENS());}catch (Exception e){}
        try{SENS2.setText(Formato.getSENS2());}catch (Exception e){}
        try{HITEMP.setText(Formato.getHITEMP());}catch (Exception e){}
        try{HIHUM.setText(Formato.getHIHUM());}catch (Exception e){}
        try{LOTEMP.setText(Formato.getLOTEMP());}catch (Exception e){}
        try{LOHUM.setText(Formato.getLOHUM());}catch (Exception e){}
        try{INYECCION.setText(Formato.getINYECCION());}catch (Exception e){}
        try{RETORNO.setText(Formato.getRETORNO());}catch (Exception e){}
        try{TIPOMICROPROCESADOR.setText(Formato.getTIPOMICROPROCESADOR());}catch (Exception e){}
        try{MarcaUniMane.setText(Formato.getMarcaUniMane());}catch (Exception e){}
        try{TipoEvaporadorUniMane.setText(Formato.getTipoEvaporadorUniMane());}catch (Exception e){}
        try{ModeloUniMane.setText(Formato.getModeloUniMane());}catch (Exception e){}
        try{VoltajeGeneralL1L2.setText(Formato.getVoltajeGeneralL1L2());}catch (Exception e){}
        try{VoltajeGeneralL2L3.setText(Formato.getVoltajeGeneralL2L3());}catch (Exception e){}
        try{VoltajeGeneralL3L1.setText(Formato.getVoltajeGeneralL3L1());}catch (Exception e){}
        try{NoSerieUniMane.setText(Formato.getNoSerieUniMane());}catch (Exception e){}



        try{campo18.setText(Formato.getNoResistencias());}catch (Exception e){}
        try{campo19.setText(Formato.getEstadoFísico());}catch (Exception e){}
        try{campo20.setText(Formato.getVerificación());}catch (Exception e){}
        try{campo21.setText(Formato.getConductores());}catch (Exception e){}
        try{campo22.setText(Formato.getContactores());}catch (Exception e){}
        try{campo23.setText(Formato.getFusibles());}catch (Exception e){}

        try{campo24.setText(Formato.getAmpResistencia1L1());}catch (Exception e){}
        try{campo26.setText(Formato.getAmpResistencia1L2());}catch (Exception e){}
        try{campo28.setText(Formato.getAmpResistencia2L1());}catch (Exception e){}
        try{campo25.setText(Formato.getAmpResistencia2L2());}catch (Exception e){}
        try{campo27.setText(Formato.getAmpResistencia3L1());}catch (Exception e){}
        try{campo29.setText(Formato.getAmpResistencia3L2());}catch (Exception e){}

        try{campo30.setText(Formato.getMotorMarca());}catch (Exception e){}
        try{campo31.setText(Formato.getModelo());}catch (Exception e){}
        try{campo32.setText(Formato.getVoltsPlaca());}catch (Exception e){}
        try{campo33.setText(Formato.getPoleaMotor());}catch (Exception e){}
        try{campo34.setText(Formato.getPoleaTurbina());}catch (Exception e){}
        try{campo35.setText(Formato.getReqCambioBanda());}catch (Exception e){}
        try{campo36.setText(Formato.getModeloBandas());}catch (Exception e){}
        try{campo37.setText(Formato.getAmpMotorL1());}catch (Exception e){}
        try{campo38.setText(Formato.getAmpMotorL2());}catch (Exception e){}
        try{campo39.setText(Formato.getAmpMotorL3());}catch (Exception e){}
        try{campo40.setText(Formato.getNumeroBandas());}catch (Exception e){}
        try{campo41.setText(Formato.getChumacerasLubricadas());}catch (Exception e){}
        try{campo42.setText(Formato.getAjustesPoleas());}catch (Exception e){}
        try{campo43.setText(Formato.getRotacion());}catch (Exception e){}

        try{campo44.setText(Formato.getVAC24());}catch (Exception e){}
        try{campo45.setText(Formato.getVAC5());}catch (Exception e){}
        try{campo46.setText(Formato.getObservacionesMicroprocesador());}catch (Exception e){}

        try{campo46B.setText(Formato.getHumificadorTipo());}catch (Exception e){}
        try{campo47.setText(Formato.getCondiciones());}catch (Exception e){}
        try{campo48.setText(Formato.getSensor());}catch (Exception e){}
        try{campo49.setText(Formato.getSelenoideVolts());}catch (Exception e){}
        try{campo50.setText(Formato.getNoLamparas());}catch (Exception e){}
        try{campo51.setText(Formato.getLíneaAguaDrenaje());}catch (Exception e){}
        try{campo52.setText(Formato.getAmpLineaL1());}catch (Exception e){}
        try{campo53.setText(Formato.getAmpLineaL2());}catch (Exception e){}
        try{campo54.setText(Formato.getAmpLineaL3());}catch (Exception e){}

        try{campo55.setText(Formato.getMarca1());}catch (Exception e){}
        try{campo56.setText(Formato.getModelo1());}catch (Exception e){}
        try{campo57.setText(Formato.getNoSerie1());}catch (Exception e){}
        try{campo58.setText(Formato.getAceiteNivel1());}catch (Exception e){}
        try{campo59.setText(Formato.getTempValvulaServ1());}catch (Exception e){}
        try{campo60.setText(Formato.getTempValvulaExp1());}catch (Exception e){}
        try{campo61.setText(Formato.getPresionBajaLbs1());}catch (Exception e){}
        try{campo63.setText(Formato.getPresionBajaCorte1());}catch (Exception e){}
        try{campo63B.setText(Formato.getPresionAlta1());}catch (Exception e){}
        try{campo64.setText(Formato.getAmpLíneaL11());}catch (Exception e){}
        try{campo65.setText(Formato.getAmpLíneaL21());}catch (Exception e){}
        try{campo66.setText(Formato.getAmpLíneaL31());}catch (Exception e){}

        try{campo67.setText(Formato.getMarca2());}catch (Exception e){}
        try{campo68.setText(Formato.getModelo2());}catch (Exception e){}
        try{campo69.setText(Formato.getNoSerie2());}catch (Exception e){}
        try{campo70.setText(Formato.getAceiteNivel2());}catch (Exception e){}
        try{campo71.setText(Formato.getTempValvulaServ2());}catch (Exception e){}
        try{campo72.setText(Formato.getTempValvulaExp2());}catch (Exception e){}
        try{campo73.setText(Formato.getPresionBajaLbs2());}catch (Exception e){}
        try{campo74.setText(Formato.getPresionBajaCorte2());}catch (Exception e){}
        try{campo75.setText(Formato.getPresionAlta2());}catch (Exception e){}
        try{campo76.setText(Formato.getAmpLíneaL12());}catch (Exception e){}
        try{campo77.setText(Formato.getAmpLíneaL22());}catch (Exception e){}
        try{campo78.setText(Formato.getAmpLíneaL32());}catch (Exception e){}

        try{campo79.setText(Formato.getMarcaConde());}catch (Exception e){}
        try{campo80.setText(Formato.getModeloConde());}catch (Exception e){}
        try{campo81.setText(Formato.getNoSerieConde());}catch (Exception e){}
        try{campo82.setText(Formato.getMarcaMotores());}catch (Exception e){}
        try{campo83.setText(Formato.getModeloMotVariable());}catch (Exception e){}
        try{campo84.setText(Formato.getModeloMotConstante());}catch (Exception e){}
        try{campo85.setText(Formato.getAmpsPlaca());}catch (Exception e){}
        try{campo86.setText(Formato.getVoltsPlaca1());}catch (Exception e){}
        try{campo87.setText(Formato.getL12());}catch (Exception e){}
        try{campo88.setText(Formato.getL23());}catch (Exception e){}
        try{campo89.setText(Formato.getL31());}catch (Exception e){}
        try{campo90.setText(Formato.getAMotorUnoL1());}catch (Exception e){}
        try{campo92.setText(Formato.getAMotorUnoL2());}catch (Exception e){}
        try{campo91.setText(Formato.getAMotorUnoL3());}catch (Exception e){}
        try{campo93.setText(Formato.getAMotorDosL1());}catch (Exception e){}
        try{campo94.setText(Formato.getAMotorDosL2());}catch (Exception e){}
        try{campo95.setText(Formato.getAMotorDosL3());}catch (Exception e){}
        try{campo96.setText(Formato.getAMotorTres1());}catch (Exception e){}
        try{campo97.setText(Formato.getAMotorTres2());}catch (Exception e){}
        try{campo98.setText(Formato.getAMotorTres3());}catch (Exception e){}
        try{campo99.setText(Formato.getAMotorCuatroL1());}catch (Exception e){}
        try{campo101.setText(Formato.getAMotorCuatroL2());}catch (Exception e){}
        try{campo100.setText(Formato.getAMotorCuatroL3());}catch (Exception e){}

        try{campo102.setText(Formato.getLavadoSerpentinesCom());}catch (Exception e){}
        try{campo103.setText(Formato.getReaprieteTornilleríaGeneralCom());}catch (Exception e){}
        try{campo104.setText(Formato.getRevisionFusiblesCom());}catch (Exception e){}
        try{campo105.setText(Formato.getRevisionContactoresCom());}catch (Exception e){}
        try{campo106.setText(Formato.getLavadoAspasProteccionesCom());}catch (Exception e){}
        try{campo107.setText(Formato.getLimpiezaInternaExternaCom());}catch (Exception e){}
        try{campo108.setText(Formato.getLimpiezaCharolaHumidificacionCom());}catch (Exception e){}
        try{campo109.setText(Formato.getRevisarEstadoLamparaCom());}catch (Exception e){}
        try{campo110.setText(Formato.getRevisarSedimentacionMineraCom());}catch (Exception e){}
        try{campo111.setText(Formato.getRevisarCondicionesBandasCom());}catch (Exception e){}
        try{campo112.setText(Formato.getRevisarAmortiguadoresCom());}catch (Exception e){}
        try{campo113.setText(Formato.getRevisarLubricacionBalerosCom());}catch (Exception e){}
        try{campo114.setText(Formato.getRevisarFlechaBaseMotorCom());}catch (Exception e){}
        try{campo115.setText(Formato.getRevisarAbrazaderasSoportesCom());}catch (Exception e){}
        try{campo116.setText(Formato.getCambiosFiltrosAireCom());}catch (Exception e){}








        //endregion


        //region Validaciones Colores en boton
        int m1 = 8;
        try{if(Formato.getSETTEMP().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getSETHUM().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getSENS().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getSENS2().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getHITEMP().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getHIHUM().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getLOTEMP().length()>0){m1= m1-1;}}catch(Exception e){}
        try{if(Formato.getLOHUM().length()>0){m1= m1-1;}}catch(Exception e){}
        if(m1==0){Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m1<8){Btn_M1.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m2 = 7;
        try{if(Formato.getMarcaUniMane().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getTipoEvaporadorUniMane().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getModeloUniMane().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getVoltajeGeneralL1L2().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getVoltajeGeneralL2L3().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getVoltajeGeneralL3L1().length()>0){m2= m2-1;}}catch(Exception e){}
        try{if(Formato.getNoSerieUniMane().length()>0){m2= m2-1;}}catch(Exception e){}

        if(m2==0){Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m2<7){Btn_M2.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m3 = 12;
        try{if(Formato.getNoResistencias().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getEstadoFísico().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getVerificación().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getConductores().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getContactores().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getFusibles().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia1L1().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia1L2().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia2L1().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia2L2().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia3L1().length()>0){m3= m3-1;}}catch(Exception e){}
        try{if(Formato.getAmpResistencia3L2().length()>0){m3= m3-1;}}catch(Exception e){}
        if(m3==0){Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m3<12){Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m4= 14;

        try{if(Formato.getMotorMarca().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getModelo().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getVoltsPlaca().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getPoleaMotor().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getPoleaTurbina().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getReqCambioBanda().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getModeloBandas().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getAmpMotorL1().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getAmpMotorL2().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getAmpMotorL3().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getNumeroBandas().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getChumacerasLubricadas().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getAjustesPoleas().length()>0){m4= m4-1;}}catch(Exception e){}
        try{if(Formato.getRotacion().length()>0){m4= m4-1;}}catch(Exception e){}
        if(m4==0){Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m4<14){Btn_M4.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m5 = 3;
        try{if(Formato.getVAC24().length()>0){m5= m5-1;}}catch(Exception e){}
        try{if(Formato.getVAC5().length()>0){m5= m5-1;}}catch(Exception e){}
        try{if(Formato.getObservacionesMicroprocesador().length()>0){m5= m5-1;}}catch(Exception e){}

        if(m5==0){Btn_M5.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m5<3){Btn_M5.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m6= 9;
        try{if(Formato.getHumificadorTipo().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getCondiciones().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getSensor().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getSelenoideVolts().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getNoLamparas().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getLíneaAguaDrenaje().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getAmpLineaL1().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getAmpLineaL2().length()>0){m6= m6-1;}}catch(Exception e){}
        try{if(Formato.getAmpLineaL3().length()>0){m6= m6-1;}}catch(Exception e){}
        if(m6==0){Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m6<9){Btn_M6.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m7 = 12;

        try{if(Formato.getMarca1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getModelo1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getNoSerie1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getAceiteNivel1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getTempValvulaServ1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getTempValvulaExp1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getPresionBajaLbs1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getPresionBajaCorte1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getPresionAlta1().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL11().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL21().length()>0){m7= m7-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL31().length()>0){m7= m7-1;}}catch(Exception e){}

        if(m7==0){Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m7<12){Btn_M7.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m8 = 13;
        try{if(Formato.getMarca2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getModelo2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getNoSerie2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getAceiteNivel2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getTempValvulaServ2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getTempValvulaExp2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getPresionBajaLbs2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getPresionBajaCorte2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getPresionAlta2().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL12().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL22().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Formato.getAmpLíneaL32().length()>0){m8= m8-1;}}catch(Exception e){}
        try{if(Boolean.parseBoolean(Formato.getMiraIndicadoraSeco2()) || Boolean.parseBoolean(Formato.getMiraIndicadoraHumedo2())){m8= m8-1;}}catch(Exception e){}

        if(m8==0){Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m8<13){Btn_M8.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m9=23;

        try{if(Formato.getMarcaConde().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getModeloConde().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getNoSerieConde().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getMarcaMotores().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getModeloMotVariable().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getModeloMotConstante().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAmpsPlaca().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getVoltsPlaca1().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getL12().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getL23().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getL31().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorUnoL1().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorUnoL2().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorUnoL3().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorDosL1().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorDosL2().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorDosL3().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorTres1().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorTres2().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorTres3().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorCuatroL1().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorCuatroL2().length()>0){m9= m9-1;}}catch(Exception e){}
        try{if(Formato.getAMotorCuatroL3().length()>0){m9= m9-1;}}catch(Exception e){}

        if(m9==0){Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m9<23){Btn_M9.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        int m10 = 15;
        try{if(Formato.getLavadoSerpentinesCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getReaprieteTornilleríaGeneralCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisionFusiblesCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisionContactoresCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getLavadoAspasProteccionesCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getLimpiezaInternaExternaCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getLimpiezaCharolaHumidificacionCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarEstadoLamparaCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarSedimentacionMineraCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarCondicionesBandasCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarAmortiguadoresCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarLubricacionBalerosCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarFlechaBaseMotorCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getRevisarAbrazaderasSoportesCom().length()>0){m10= m10-1;}}catch(Exception e){}
        try{if(Formato.getCambiosFiltrosAireCom().length()>0){m10= m10-1;}}catch(Exception e){}

        if(m10==0){Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m10<15){Btn_M10.setBackgroundColor(Color.parseColor("#FE5B1B"));}


        //endregion

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


        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Thermal");
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
                //region SET a FORMATO
                if (rd_humedo1.isChecked()) {Formato.setMiraIndicadoraHumedo1("true");}else{Formato.setMiraIndicadoraHumedo1("false");}
                if (rd_seco1.isChecked()) {Formato.setMiraIndicadoraSeco1("true");}else{Formato.setMiraIndicadoraSeco1("false");}
                if (rd_humedo2.isChecked()) {Formato.setMiraIndicadoraHumedo2("true");}else{Formato.setMiraIndicadoraHumedo2("false");}
                if (rd_seco2.isChecked()) {Formato.setMiraIndicadoraSeco2("true");}else{Formato.setMiraIndicadoraSeco2("false");}

                Formato.setSETTEMP(SETTEMP.getText().toString());
                Formato.setSETHUM(SETHUM.getText().toString());
                Formato.setSENS(SENS.getText().toString());
                Formato.setSENS2(SENS2.getText().toString());
                Formato.setHITEMP(HITEMP.getText().toString());
                Formato.setHIHUM(HIHUM.getText().toString());
                Formato.setLOTEMP(LOTEMP.getText().toString());
                Formato.setLOHUM(LOHUM.getText().toString());
                Formato.setINYECCION(INYECCION.getText().toString());
                Formato.setRETORNO(RETORNO.getText().toString());
                Formato.setTIPOMICROPROCESADOR(TIPOMICROPROCESADOR.getText().toString());
                Formato.setMarcaUniMane(MarcaUniMane.getText().toString());
                Formato.setTipoEvaporadorUniMane(TipoEvaporadorUniMane.getText().toString());
                Formato.setModeloUniMane(ModeloUniMane.getText().toString());
                Formato.setVoltajeGeneralL1L2(VoltajeGeneralL1L2.getText().toString());
                Formato.setVoltajeGeneralL2L3(VoltajeGeneralL2L3.getText().toString());
                Formato.setVoltajeGeneralL3L1(VoltajeGeneralL3L1.getText().toString());
                Formato.setNoSerieUniMane(NoSerieUniMane.getText().toString());

                Formato.setNoResistencias(campo18.getText().toString());
                Formato.setEstadoFísico(campo19.getText().toString());
                Formato.setVerificación(campo20.getText().toString());
                Formato.setConductores(campo21.getText().toString());
                Formato.setContactores(campo22.getText().toString());
                Formato.setFusibles(campo23.getText().toString());

                Formato.setAmpResistencia1L1(campo24.getText().toString());
                Formato.setAmpResistencia1L2(campo26.getText().toString());
                Formato.setAmpResistencia2L1(campo28.getText().toString());
                Formato.setAmpResistencia2L2(campo25.getText().toString());
                Formato.setAmpResistencia3L1(campo27.getText().toString());
                Formato.setAmpResistencia3L2(campo29.getText().toString());

                Formato.setMotorMarca(campo30.getText().toString());
                Formato.setModelo(campo31.getText().toString());
                Formato.setVoltsPlaca(campo32.getText().toString());
                Formato.setPoleaMotor(campo33.getText().toString());
                Formato.setPoleaTurbina(campo34.getText().toString());
                Formato.setReqCambioBanda(campo35.getText().toString());
                Formato.setModeloBandas(campo36.getText().toString());
                Formato.setAmpMotorL1(campo37.getText().toString());
                Formato.setAmpMotorL2(campo38.getText().toString());
                Formato.setAmpMotorL3(campo39.getText().toString());
                Formato.setNumeroBandas(campo40.getText().toString());
                Formato.setChumacerasLubricadas(campo41.getText().toString());
                Formato.setAjustesPoleas(campo42.getText().toString());
                Formato.setRotacion(campo43.getText().toString());
                Formato.setVAC24(campo44.getText().toString());
                Formato.setVAC5(campo45.getText().toString());
                Formato.setObservacionesMicroprocesador(campo46.getText().toString());
                Formato.setHumificadorTipo(campo46B.getText().toString());
                Formato.setCondiciones(campo47.getText().toString());
                Formato.setSensor(campo48.getText().toString());
                Formato.setSelenoideVolts(campo49.getText().toString());
                Formato.setNoLamparas(campo50.getText().toString());
                Formato.setLíneaAguaDrenaje(campo51.getText().toString());
                Formato.setAmpLineaL1(campo52.getText().toString());
                Formato.setAmpLineaL2(campo53.getText().toString());
                Formato.setAmpLineaL3(campo54.getText().toString());
                Formato.setMarca1(campo55.getText().toString());
                Formato.setModelo1(campo56.getText().toString());
                Formato.setNoSerie1(campo57.getText().toString());
                Formato.setAceiteNivel1(campo58.getText().toString());
                Formato.setTempValvulaServ1(campo59.getText().toString());
                Formato.setTempValvulaExp1(campo60.getText().toString());
                Formato.setPresionBajaLbs1(campo61.getText().toString());
                Formato.setPresionBajaCorte1(campo63.getText().toString());
                Formato.setPresionAlta1(campo63B.getText().toString());
                Formato.setAmpLíneaL11(campo64.getText().toString());
                Formato.setAmpLíneaL21(campo65.getText().toString());
                Formato.setAmpLíneaL31(campo66.getText().toString());
                Formato.setMarca2(campo67.getText().toString());
                Formato.setModelo2(campo68.getText().toString());
                Formato.setNoSerie2(campo69.getText().toString());
                Formato.setAceiteNivel2(campo70.getText().toString());
                Formato.setTempValvulaServ2(campo71.getText().toString());
                Formato.setTempValvulaExp2(campo72.getText().toString());
                Formato.setPresionBajaLbs2(campo73.getText().toString());
                Formato.setPresionBajaCorte2(campo74.getText().toString());
                Formato.setPresionAlta2(campo75.getText().toString());
                Formato.setAmpLíneaL12(campo76.getText().toString());
                Formato.setAmpLíneaL22(campo77.getText().toString());
                Formato.setAmpLíneaL32(campo78.getText().toString());
                Formato.setMarcaConde(campo79.getText().toString());
                Formato.setModeloConde(campo80.getText().toString());
                Formato.setNoSerieConde(campo81.getText().toString());
                Formato.setMarcaMotores(campo82.getText().toString());
                Formato.setModeloMotVariable(campo83.getText().toString());
                Formato.setModeloMotConstante(campo84.getText().toString());
                Formato.setAmpsPlaca(campo85.getText().toString());
                Formato.setVoltsPlaca1(campo86.getText().toString());
                Formato.setL12(campo87.getText().toString());
                Formato.setL23(campo88.getText().toString());
                Formato.setL31(campo89.getText().toString());
                Formato.setAMotorUnoL1(campo90.getText().toString());
                Formato.setAMotorUnoL2(campo92.getText().toString());
                Formato.setAMotorUnoL3(campo91.getText().toString());
                Formato.setAMotorDosL1(campo93.getText().toString());
                Formato.setAMotorDosL2(campo94.getText().toString());
                Formato.setAMotorDosL3(campo95.getText().toString());
                Formato.setAMotorTres1(campo96.getText().toString());
                Formato.setAMotorTres2(campo97.getText().toString());
                Formato.setAMotorTres3(campo98.getText().toString());
                Formato.setAMotorCuatroL1(campo99.getText().toString());
                Formato.setAMotorCuatroL2(campo101.getText().toString());
                Formato.setAMotorCuatroL3(campo100.getText().toString());
                Formato.setLavadoSerpentinesCom(campo102.getText().toString());
                Formato.setReaprieteTornilleríaGeneralCom(campo103.getText().toString());
                Formato.setRevisionFusiblesCom(campo104.getText().toString());
                Formato.setRevisionContactoresCom(campo105.getText().toString());
                Formato.setLavadoAspasProteccionesCom(campo106.getText().toString());
                Formato.setLimpiezaInternaExternaCom(campo107.getText().toString());
                Formato.setLimpiezaCharolaHumidificacionCom(campo108.getText().toString());
                Formato.setRevisarEstadoLamparaCom(campo109.getText().toString());
                Formato.setRevisarSedimentacionMineraCom(campo110.getText().toString());

                Formato.setRevisarCondicionesBandasCom(campo111.getText().toString());
                Formato.setRevisarAmortiguadoresCom(campo112.getText().toString());
                Formato.setRevisarLubricacionBalerosCom(campo113.getText().toString());
                Formato.setRevisarFlechaBaseMotorCom(campo114.getText().toString());
                Formato.setRevisarAbrazaderasSoportesCom(campo115.getText().toString());
                Formato.setCambiosFiltrosAireCom(campo116.getText().toString());
                //endregion

                D_B.guardarThrtmal(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                ThermMenuFragment myfargment = new ThermMenuFragment();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });



        //endregion

    return v;
    }

}