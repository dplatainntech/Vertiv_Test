package com.maju.desarrollo.testfcs.Formatos.STS2;


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
import com.maju.desarrollo.testfcs.ServiceClass.STS2;

/**
 * A simple {@link Fragment} subclass.
 */
public class STS2ParametrosFragment extends Fragment {
    TextView FechaVencimiento1, FechaVencimiento2, FechaVencimiento3, FechaVencimiento4, FechaVencimiento5, FechaVencimiento6;
    String id_formato;
    OperacionesDB D_B;
    STS2 Formato;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false;

    public STS2ParametrosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sts2_parametros, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerSTS2(id_formato);

        Button Btn_M1 = (Button)v.findViewById(R.id.buttonM1);
        Button Btn_M2 = (Button)v.findViewById(R.id.buttonM2);
        Button Btn_M3 = (Button)v.findViewById(R.id.buttonM3);

        final LinearLayout content_M1 = (LinearLayout)v.findViewById(R.id.LyM1);
        final LinearLayout content_M2 = (LinearLayout)v.findViewById(R.id.LyM2);
        final LinearLayout content_M3 = (LinearLayout)v.findViewById(R.id.LyM3);

        final LinearLayout clickcampoA1 = (LinearLayout)v.findViewById(R.id.clickcampoA1);
        final LinearLayout clickcampoA2 = (LinearLayout)v.findViewById(R.id.clickcampoA2);

        final LinearLayout clickcampoB1 = (LinearLayout)v.findViewById(R.id.clickcampoB1);
        final LinearLayout clickcampoB2 = (LinearLayout)v.findViewById(R.id.clickcampoB2);
        final LinearLayout clickcampoB3 = (LinearLayout)v.findViewById(R.id.clickcampoB3);

        final LinearLayout vistaI_A1 = (LinearLayout)v.findViewById(R.id.vistaI_A1);
        final LinearLayout vistaI_A2 = (LinearLayout)v.findViewById(R.id.vistaI_A2);

        final LinearLayout vistaI_B1 = (LinearLayout)v.findViewById(R.id.vistaI_B1);
        final LinearLayout vistaI_B2 = (LinearLayout)v.findViewById(R.id.vistaI_B2);
        final LinearLayout vistaI_B3 = (LinearLayout)v.findViewById(R.id.vistaI_B3);

        final EditText VoltajePreAB = (EditText)v.findViewById(R.id.VoltajePreAB);
        final EditText VoltajePreBC = (EditText)v.findViewById(R.id.VoltajePreBC);
        final EditText VoltajePreCA = (EditText)v.findViewById(R.id.VoltajePreCA);
        final EditText CorrientePreA = (EditText)v.findViewById(R.id.CorrientePreA);
        final EditText CorrientePreB = (EditText)v.findViewById(R.id.CorrientePreB);
        final EditText CorrientePreC = (EditText)v.findViewById(R.id.CorrientePreC);
        final EditText VoltajeEmeAB = (EditText)v.findViewById(R.id.VoltajeEmeAB);
        final EditText VoltajeEmeBC = (EditText)v.findViewById(R.id.VoltajeEmeBC);
        final EditText VoltajeEmeCA = (EditText)v.findViewById(R.id.VoltajeEmeCA);
        final EditText CorrienteEmeA = (EditText)v.findViewById(R.id.CorrienteEmeA);
        final EditText CorrienteEmeB = (EditText)v.findViewById(R.id.CorrienteEmeB);
        final EditText CorrienteEmeC = (EditText)v.findViewById(R.id.CorrienteEmeC);
        final EditText TemperaturaSCR = (EditText)v.findViewById(R.id.TemperaturaSCR);
        final EditText NoTransferencias = (EditText)v.findViewById(R.id.NoTransferencias);
        final EditText LimpiezaGeneral = (EditText)v.findViewById(R.id.LimpiezaGeneral);
        final EditText AjusteHorarios = (EditText)v.findViewById(R.id.AjusteHorarios);
        final EditText A48VDCTP1 = (EditText)v.findViewById(R.id.A48VDCTP1);
        final EditText A24VDCTP5 = (EditText)v.findViewById(R.id.A24VDCTP5);
        final EditText A74VDCTP7 = (EditText)v.findViewById(R.id.A74VDCTP7);
        final EditText A5VDCTP8 = (EditText)v.findViewById(R.id.A5VDCTP8);
        final EditText A33VDCTP6 = (EditText)v.findViewById(R.id.A33VDCTP6);
        final EditText B24VDCTP5 = (EditText)v.findViewById(R.id.B24VDCTP5);
        final EditText B74VDCTP7 = (EditText)v.findViewById(R.id.B74VDCTP7);
        final EditText B5VDCTP8 = (EditText)v.findViewById(R.id.B5VDCTP8);
        final EditText KVA = (EditText)v.findViewById(R.id.KVA);
        final EditText KW = (EditText)v.findViewById(R.id.KW);

        final CheckBox check1 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox1);
        final CheckBox check2 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox2);
        final CheckBox check3 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox3);
        final CheckBox check4 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox4);
        final CheckBox check5 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox5);
        final CheckBox check6 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox6);
        final CheckBox check7 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox7);
        final CheckBox check8 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox8);
        final CheckBox check9 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox9);
        final CheckBox check10 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox10);

        final CheckBox CargaFuenteA = (CheckBox)v.findViewById(R.id.CargaFuenteA);
        final CheckBox CargaFuenteB = (CheckBox)v.findViewById(R.id.CargaFuenteB);



        try{VoltajePreAB.setText(Formato.getVoltajePreAB());}catch (Exception e){}
        try{VoltajePreBC.setText(Formato.getVoltajePreBC());}catch (Exception e){}
        try{VoltajePreCA.setText(Formato.getVoltajePreCA());}catch (Exception e){}
        try{CorrientePreA.setText(Formato.getCorrientePreA());}catch (Exception e){}
        try{CorrientePreB.setText(Formato.getCorrientePreB());}catch (Exception e){}
        try{CorrientePreC.setText(Formato.getCorrientePreC());}catch (Exception e){}
        try{VoltajeEmeAB.setText(Formato.getVoltajeEmeAB());}catch (Exception e){}
        try{VoltajeEmeBC.setText(Formato.getVoltajeEmeBC());}catch (Exception e){}
        try{VoltajeEmeCA.setText(Formato.getVoltajeEmeCA());}catch (Exception e){}
        try{CorrienteEmeA.setText(Formato.getCorrienteEmeA());}catch (Exception e){}
        try{CorrienteEmeB.setText(Formato.getCorrienteEmeB());}catch (Exception e){}
        try{CorrienteEmeC.setText(Formato.getCorrienteEmeC());}catch (Exception e){}
        try{TemperaturaSCR.setText(Formato.getTemperaturaSCR());}catch (Exception e){}
        try{NoTransferencias.setText(Formato.getNoTransferencias());}catch (Exception e){}
        try{LimpiezaGeneral.setText(Formato.getLimpiezaGeneral());}catch (Exception e){}
        try{AjusteHorarios.setText(Formato.getAjusteHorarios());}catch (Exception e){}
        try{A48VDCTP1.setText(Formato.getA48VDCTP1());}catch (Exception e){}
        try{A24VDCTP5.setText(Formato.getA24VDCTP5());}catch (Exception e){}
        try{A74VDCTP7.setText(Formato.getA74VDCTP7());}catch (Exception e){}
        try{A5VDCTP8.setText(Formato.getA5VDCTP8());}catch (Exception e){}
        try{A33VDCTP6.setText(Formato.getA33VDCTP6());}catch (Exception e){}
        try{B24VDCTP5.setText(Formato.getB24VDCTP5());}catch (Exception e){}
        try{B74VDCTP7.setText(Formato.getB74VDCTP7());}catch (Exception e){}
        try{B5VDCTP8.setText(Formato.getB5VDCTP8());}catch (Exception e){}
        try{KVA.setText(Formato.getKVA());}catch (Exception e){}
        try{KW.setText(Formato.getKW());}catch (Exception e){}

        try{if(Formato.getRevisionCableadoPotencia().equals("true")){check1.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionConexiones().equals("true")){check2.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionComponentesPotencia().equals("true")){check3.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionSCR().equals("true")){check4.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisarHistorialAlarmas().equals("true")){check5.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionTarjetas().equals("true")){check6.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionPanel().equals("true")){check7.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionCapacitoresPotencia().equals("true")){check8.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionFuenteAlimentacion().equals("true")){check9.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getRevisionPoints().equals("true")){check10.setChecked(true);}}catch (Exception e){}

        try{if(Formato.getCargaFuenteA().equals("true")){CargaFuenteA.setChecked(true);}}catch (Exception e){}
        try{if(Formato.getCargaFuenteB().equals("true")){CargaFuenteB.setChecked(true);}}catch (Exception e){}


        try{
            if(Formato.getRevisionCableadoPotencia().equals("true")
                    || Formato.getRevisionConexiones().equals("true")
                    || Formato.getRevisionComponentesPotencia().equals("true")
                    || Formato.getRevisionSCR().equals("true")
                    || Formato.getRevisarHistorialAlarmas().equals("true")
                    || Formato.getRevisionTarjetas().equals("true")
                    || Formato.getRevisionPanel().equals("true")
                    || Formato.getRevisionCapacitoresPotencia().equals("true")
                    || Formato.getRevisionFuenteAlimentacion().equals("true")
                    || Formato.getRevisionPoints().equals("true")){
                Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));
            }}catch (Exception e){}

        int m2= 12;
        try {
            if (Formato.getVoltajePreAB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajePreBC().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajePreCA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrientePreA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrientePreB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrientePreC().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajeEmeAB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajeEmeBC().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getVoltajeEmeCA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEmeA().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEmeB().length() >= 1) {
                m2 = m2 - 1;
            }
            if (Formato.getCorrienteEmeC().length() >= 1) {
                m2 = m2 - 1;
            }
        }catch (Exception e){}
        if(m2==0){Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m2<12){Btn_M2.setBackgroundColor(Color.parseColor("#FE5B1B"));}


        int m3= 6;
        try {
            if (Formato.getTemperaturaSCR().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getNoTransferencias().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getLimpiezaGeneral().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getAjusteHorarios().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKVA().length() >= 1) {
                m3 = m3 - 1;
            }
            if (Formato.getKW().length() >= 1) {
                m3 = m3 - 1;
            }
           /* if (Formato.getCargaFuenteA().equals("true")) {
                m3 = m3 - 1;
            }
            if (Formato.getCargaFuenteB().equals("true")) {
                m3 = m3 - 1;
            }*/

        }catch (Exception e){}
        if(m3==0){Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(m3<6){Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}








        //region botones principal
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
//endregion

        //region BTN M1
        clickcampoA1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaI_A1.setVisibility(View.VISIBLE);
                vistaI_A2.setVisibility(View.GONE);

                clickcampoA1.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampoA2.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion

        //region BTN M2
        clickcampoA2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaI_A1.setVisibility(View.GONE);
                vistaI_A2.setVisibility(View.VISIBLE);

                clickcampoA1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampoA2.setBackgroundColor(Color.parseColor("#B7B5B5"));

            }
        });
        //endregion


        //region BTN M1
        clickcampoB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaI_B1.setVisibility(View.VISIBLE);
                vistaI_B2.setVisibility(View.GONE);
                vistaI_B3.setVisibility(View.GONE);

                clickcampoB1.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampoB2.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampoB3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion

        //region BTN M2
        clickcampoB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaI_B1.setVisibility(View.GONE);
                vistaI_B2.setVisibility(View.VISIBLE);
                vistaI_B3.setVisibility(View.GONE);
                clickcampoB1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampoB2.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampoB3.setBackgroundColor(Color.parseColor("#E5E3E3"));
            }
        });
        //endregion

        //region BTN M3
        clickcampoB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vistaI_B1.setVisibility(View.GONE);
                vistaI_B2.setVisibility(View.GONE);
                vistaI_B3.setVisibility(View.VISIBLE);
                clickcampoB1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampoB2.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampoB3.setBackgroundColor(Color.parseColor("#B7B5B5"));
            }
        });
        //endregion





        //region Buardar/Cancelar
        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "STS2");
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
                if(check1.isChecked()){Formato.setRevisionCableadoPotencia("true");}else{Formato.setRevisionCableadoPotencia("false");}
                if(check2.isChecked()){Formato.setRevisionConexiones("true");}else{Formato.setRevisionConexiones("false");}
                if(check3.isChecked()){Formato.setRevisionComponentesPotencia("true");}else{Formato.setRevisionComponentesPotencia("false");}
                if(check4.isChecked()){Formato.setRevisionSCR("true");}else{Formato.setRevisionSCR("false");}
                if(check5.isChecked()){Formato.setRevisarHistorialAlarmas("true");}else{Formato.setRevisarHistorialAlarmas("false");}
                if(check6.isChecked()){Formato.setRevisionTarjetas("true");}else{Formato.setRevisionTarjetas("false");}
                if(check7.isChecked()){Formato.setRevisionPanel("true");}else{Formato.setRevisionPanel("false");}
                if(check8.isChecked()){Formato.setRevisionCapacitoresPotencia("true");}else{Formato.setRevisionCapacitoresPotencia("false");}
                if(check9.isChecked()){Formato.setRevisionFuenteAlimentacion("true");}else{Formato.setRevisionFuenteAlimentacion("false");}
                if(check10.isChecked()){Formato.setRevisionPoints("true");}else{Formato.setRevisionPoints("false");}
                if(CargaFuenteA.isChecked()){Formato.setCargaFuenteA("true");}else{Formato.setCargaFuenteA("false");}
                if(CargaFuenteB.isChecked()){Formato.setCargaFuenteB("true");}else{Formato.setCargaFuenteB("false");}

                Formato.setVoltajePreAB(VoltajePreAB.getText().toString());
                Formato.setVoltajePreBC(VoltajePreBC.getText().toString());
                Formato.setVoltajePreCA(VoltajePreCA.getText().toString());
                Formato.setCorrientePreA(CorrientePreA.getText().toString());
                Formato.setCorrientePreB(CorrientePreB.getText().toString());
                Formato.setCorrientePreC(CorrientePreC.getText().toString());
                Formato.setVoltajeEmeAB(VoltajeEmeAB.getText().toString());
                Formato.setVoltajeEmeBC(VoltajeEmeBC.getText().toString());
                Formato.setVoltajeEmeCA(VoltajeEmeCA.getText().toString());
                Formato.setCorrienteEmeA(CorrienteEmeA.getText().toString());
                Formato.setCorrienteEmeB(CorrienteEmeB.getText().toString());
                Formato.setCorrienteEmeC(CorrienteEmeC.getText().toString());
                Formato.setTemperaturaSCR(TemperaturaSCR.getText().toString());
                Formato.setNoTransferencias(NoTransferencias.getText().toString());
                Formato.setLimpiezaGeneral(LimpiezaGeneral.getText().toString());
                Formato.setAjusteHorarios(AjusteHorarios.getText().toString());
                Formato.setA48VDCTP1(A48VDCTP1.getText().toString());
                Formato.setA24VDCTP5(A24VDCTP5.getText().toString());
                Formato.setA74VDCTP7(A74VDCTP7.getText().toString());
                Formato.setA5VDCTP8(A5VDCTP8.getText().toString());
                Formato.setA33VDCTP6(A33VDCTP6.getText().toString());
                Formato.setB24VDCTP5(B24VDCTP5.getText().toString());
                Formato.setB74VDCTP7(B74VDCTP7.getText().toString());
                Formato.setB5VDCTP8(B5VDCTP8.getText().toString());
                Formato.setKVA(KVA.getText().toString());
                Formato.setKW(KW.getText().toString());
                D_B.guardarSTS2(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                STS2MenuFragment myfargment = new STS2MenuFragment();
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
