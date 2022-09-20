package com.maju.desarrollo.testfcs.Formatos.PDU;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.PDU;

/**
 * A simple {@link Fragment} subclass.
 */
public class PDUParametros extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false,ActivoM4 = false,ActivoM5 = false,
            ActivoM6 = false,ActivoM7 = false,ActivoM8 = false,ActivoM9 = false,ActivoM10 = false,
            ActivoM11 = false,ActivoM12 = false;;

    TextView fechacontorolP,campo27;
    PDU Formato;


    public PDUParametros() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pduparametros, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerPDU(id_formato);

        Button Btn_M1 = (Button)v.findViewById(R.id.Modulo1);
        Button Btn_M2 = (Button)v.findViewById(R.id.Modulo2);
        Button Btn_M3 = (Button)v.findViewById(R.id.Modulo3);

        final LinearLayout content_M1 = (LinearLayout)v.findViewById(R.id.contenedorM1);
        final LinearLayout content_M2 = (LinearLayout)v.findViewById(R.id.contenedorM2);
        final LinearLayout content_M3 = (LinearLayout)v.findViewById(R.id.contenedorM3);

        final CheckBox check1 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox1);
        final CheckBox check2 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox2);
        final CheckBox check3 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox3);
        final CheckBox check4 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox4);
        final CheckBox check5 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox5);
        final CheckBox check6 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox6);

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

        try{if(Formato.getRevisionEstadoTransformador().equals("true")){check1.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionConexionesTransformador().equals("true")){check2.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionCableadoPotencia().equals("true")){check3.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionTarjetaControl().equals("true")){check4.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionDisplay().equals("true")){check5.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionBotonesPanel().equals("true")){check6.setChecked(true);}}catch (Exception e){}

        try{campo1.setText(Formato.getVoltajeEAB());}catch (Exception e){}
        try{campo2.setText(Formato.getVoltajeEBC());}catch (Exception e){}
        try{campo3.setText(Formato.getVoltajeECA());}catch (Exception e){}
        try{campo4.setText(Formato.getCorrienteEA());}catch (Exception e){}
        try{campo5.setText(Formato.getCorrienteEB());}catch (Exception e){}
        try{campo6.setText(Formato.getCorrienteEC());}catch (Exception e){}
        try{campo7.setText(Formato.getTemperatura());}catch (Exception e){}
        try{campo8.setText(Formato.getRotacion());}catch (Exception e){}
        try{campo9.setText(Formato.getTaps());}catch (Exception e){}
        try{campo10.setText(Formato.getVoltajeSAB());}catch (Exception e){}
        try{campo11.setText(Formato.getVoltajeSBC());}catch (Exception e){}
        try{campo12.setText(Formato.getVoltajeSCA());}catch (Exception e){}
        try{campo13.setText(Formato.getVoltajeSAN());}catch (Exception e){}
        try{campo14.setText(Formato.getVoltajeSBN());}catch (Exception e){}
        try{campo15.setText(Formato.getVoltajeSCN());}catch (Exception e){}
        try{campo16.setText(Formato.getCorrienteSAB());}catch (Exception e){}
        try{campo17.setText(Formato.getCorrienteSBC());}catch (Exception e){}
        try{campo18.setText(Formato.getCorrienteSCA());}catch (Exception e){}
        try{campo19.setText(Formato.getCorrienteSAN());}catch (Exception e){}
        try{campo20.setText(Formato.getCorrienteSBN());}catch (Exception e){}
        try{campo21.setText(Formato.getCorrienteSCN());}catch (Exception e){}
        try{campo22.setText(Formato.getVTHDA());}catch (Exception e){}
        try{campo23.setText(Formato.getVTHDB());}catch (Exception e){}
        try{campo24.setText(Formato.getVTHDC());}catch (Exception e){}
        try{campo25.setText(Formato.getITHDA());}catch (Exception e){}
        try{campo26.setText(Formato.getITHDB());}catch (Exception e){}
        try{campo27.setText(Formato.getITHDC());}catch (Exception e){}
        try{campo28.setText(Formato.getKFACTORA());}catch (Exception e){}
        try{campo29.setText(Formato.getKFACTORB());}catch (Exception e){}
        try{campo30.setText(Formato.getKFACTORC());}catch (Exception e){}
        try{campo31.setText(Formato.getPEAKRMSA());}catch (Exception e){}
        try{campo32.setText(Formato.getPEAKRMSB());}catch (Exception e){}
        try{campo33.setText(Formato.getPEAKRMSC());}catch (Exception e){}
        try{campo34.setText(Formato.getKVA());}catch (Exception e){}
        try{campo35.setText(Formato.getKW());}catch (Exception e){}
        try{campo36.setText(Formato.getFP());}catch (Exception e){}
        try{campo37.setText(Formato.getPorcentajeCarga());}catch (Exception e){}
        try{campo38.setText(Formato.getKWHR());}catch (Exception e){}

        try{
        if(Formato.getRevisionEstadoTransformador().equals("true")
        || Formato.getRevisionConexionesTransformador().equals("true")
        || Formato.getRevisionCableadoPotencia().equals("true")
        || Formato.getRevisionTarjetaControl().equals("true")
        || Formato.getRevisionDisplay().equals("true")
        || Formato.getRevisionBotonesPanel().equals("true")){
            Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));
        }}catch (Exception e){}

        int m2= 9;
        try {
            if (Formato.getVoltajeEAB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajeEBC().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajeECA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEC().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getTemperatura().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getRotacion().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getTaps().length() >= 1) {
                m2 = m2 - 1;
            }
        }catch (Exception e){}
        if(m2==0){Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m2<9){Btn_M2.setBackgroundColor(Color.parseColor("#FE5B1B"));}


        int m3= 29;
        try {
            if (Formato.getVoltajeSAB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVoltajeSBC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVoltajeSCA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVoltajeSAN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVoltajeSBN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVoltajeSCN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSAB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSBC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSCA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSAN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSBN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getCorrienteSCN().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVTHDA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVTHDB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getVTHDC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getITHDA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getITHDB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getITHDC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKFACTORA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKFACTORB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKFACTORC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getPEAKRMSA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getPEAKRMSB().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getPEAKRMSC().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKVA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKW().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getFP().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getPorcentajeCarga().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKWHR().length() >= 1) {
                m3 = m3 - 1;
            }
        }catch (Exception e){}
        if(m3==0){Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m3<29){Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}

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


        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "PDU");
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
                if(check1.isChecked()){Formato.setRevisionEstadoTransformador("true");}else{Formato.setRevisionEstadoTransformador("false");}
                if(check2.isChecked()){Formato.setRevisionConexionesTransformador("true");}else{Formato.setRevisionConexionesTransformador("false");}
                if(check3.isChecked()){Formato.setRevisionCableadoPotencia("true");}else{Formato.setRevisionCableadoPotencia("false");}
                if(check4.isChecked()){Formato.setRevisionTarjetaControl("true");}else{Formato.setRevisionTarjetaControl("false");}
                if(check5.isChecked()){Formato.setRevisionDisplay("true");}else{Formato.setRevisionDisplay("false");}
                if(check6.isChecked()){Formato.setRevisionBotonesPanel("true");}else{Formato.setRevisionBotonesPanel("false");}

                Formato.setVoltajeEAB(campo1.getText().toString());
                Formato.setVoltajeEBC(campo2.getText().toString());
                Formato.setVoltajeECA(campo3.getText().toString());
                Formato.setCorrienteEA(campo4.getText().toString());
                Formato.setCorrienteEB(campo5.getText().toString());
                Formato.setCorrienteEC(campo6.getText().toString());
                Formato.setTemperatura(campo7.getText().toString());
                Formato.setRotacion(campo8.getText().toString());
                Formato.setTaps(campo9.getText().toString());
                Formato.setVoltajeSAB(campo10.getText().toString());
                Formato.setVoltajeSBC(campo11.getText().toString());
                Formato.setVoltajeSCA(campo12.getText().toString());
                Formato.setVoltajeSAN(campo13.getText().toString());
                Formato.setVoltajeSBN(campo14.getText().toString());
                Formato.setVoltajeSCN(campo15.getText().toString());
                Formato.setCorrienteSAB(campo16.getText().toString());
                Formato.setCorrienteSBC(campo17.getText().toString());
                Formato.setCorrienteSCA(campo18.getText().toString());
                Formato.setCorrienteSAN(campo19.getText().toString());
                Formato.setCorrienteSBN(campo20.getText().toString());
                Formato.setCorrienteSCN(campo21.getText().toString());
                Formato.setVTHDA(campo22.getText().toString());
                Formato.setVTHDB(campo23.getText().toString());
                Formato.setVTHDC(campo24.getText().toString());
                Formato.setITHDA(campo25.getText().toString());
                Formato.setITHDB(campo26.getText().toString());
                Formato.setITHDC(campo27.getText().toString());
                Formato.setKFACTORA(campo28.getText().toString());
                Formato.setKFACTORB(campo29.getText().toString());
                Formato.setKFACTORC(campo30.getText().toString());
                Formato.setPEAKRMSA(campo31.getText().toString());
                Formato.setPEAKRMSB(campo32.getText().toString());
                Formato.setPEAKRMSC(campo33.getText().toString());
                Formato.setKVA(campo34.getText().toString());
                Formato.setKW(campo35.getText().toString());
                Formato.setFP(campo36.getText().toString());
                Formato.setPorcentajeCarga(campo37.getText().toString());
                Formato.setKWHR(campo38.getText().toString());

                D_B.guardarPDU(Formato);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato",id_formato);
                PDUMenu myfargment = new PDUMenu();
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
