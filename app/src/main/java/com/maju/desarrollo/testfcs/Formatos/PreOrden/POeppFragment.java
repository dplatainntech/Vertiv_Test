package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class POeppFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;

    public POeppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_poepp, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
        //region
        final CheckBox check1 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox1);
        final CheckBox check2 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox2);
        final CheckBox check3 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox3);
        final CheckBox check4 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox4);
        final CheckBox check5 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox5);
        final CheckBox check6 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox6);
        final CheckBox check7= (CheckBox)v.findViewById(R.id.Reiesgo_checkBox7);
        final CheckBox check8 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox8);
        final CheckBox check9 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox9);
        final CheckBox check10 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox10);
        final CheckBox check11 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox11);
        final CheckBox check12 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox12);
        final CheckBox check13 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox13);
        final CheckBox check14 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox14);
        final CheckBox check15 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox15);
        final CheckBox check16 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox16);
        final CheckBox check17 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox17);
        final CheckBox check18 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox18);
        final CheckBox check19 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox19);
        final CheckBox check20 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox20);
        final CheckBox check23 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox23);
        final EditText otro = (EditText)v.findViewById(R.id.Reiesgo_Edt_otro);
        otro.setEnabled(false);

        try{
            if(preOrden.getEPP_BASICO_CASCO().equals("SI")){check1.setChecked(true);}
            if(preOrden.getEPP_BASICO_GAFAS().equals("SI")){check2.setChecked(true);}
            if(preOrden.getEPP_BASICO_TAPONES().equals("SI")){check3.setChecked(true);}
            if(preOrden.getEPP_BASICO_ZAPATOS().equals("SI")){check4.setChecked(true);}
            if(preOrden.getEPP_BASICO_GUANTES().equals("SI")){check5.setChecked(true);}
            if(preOrden.getEPP_BASICO_BARBIQUEJO().equals("SI")){check6.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_GAFAS().equals("SI")){check7.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_CASCO().equals("SI")){check8.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_ZAPATOS().equals("SI")){check9.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_GUANTES().equals("SI")){check10.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_SOBREGUANTE().equals("SI")){check11.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_CARETA().equals("SI")){check12.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_BALACLAVA().equals("SI")){check13.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_TRAJE().equals("SI")){check14.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_PROTECTORES_AUDI().equals("SI")){check15.setChecked(true);}
            if(preOrden.getEPP_ELECTICO_MANGAS().equals("SI")){check16.setChecked(true);}
            if(preOrden.getEPP_OTROS_PROTECCION_CAIDAS().equals("SI")){check17.setChecked(true);}
            if(preOrden.getEPP_OTROS_PROTECCION_RESPITA().equals("SI")){check18.setChecked(true);}
            if(preOrden.getEPP_OTROS_PROTECCION_SOLDAD().equals("SI")){check19.setChecked(true);}
            if(preOrden.getEPP_OTROS_PROTECCION_QUIMICOS().equals("SI")){check20.setChecked(true);}
            if(preOrden.getEPP_OTROS_ADICIONALES().length()>1){check23.setChecked(true);
                otro.setText(preOrden.getEPP_OTROS_ADICIONALES());
                otro.setEnabled(true);
            }

        }catch (Exception e){}


        check23.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    otro.setText("");
                    otro.setEnabled(true);
                }else{
                    otro.setText("");
                    otro.setEnabled(false);
                }

            }
        });


        //endregion




        Button cancelar = (Button)v.findViewById(R.id.EPP_btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", preOrden.getFOLIO());

                CancelasDialogFragment dialog= new CancelasDialogFragment();
                dialog.setCancelable(true);
                dialog.setTargetFragment(POeppFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.EPP_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());
                boolean pasa = true;
                if(check23.isChecked() && otro.getText().toString().trim().length()==0){
                    pasa = false;
                }
                if(pasa){
                //region
                if(check1.isChecked()){preOrden.setEPP_BASICO_CASCO("SI");}else{preOrden.setEPP_BASICO_CASCO("");}
                if(check2.isChecked()){preOrden.setEPP_BASICO_GAFAS("SI");}else{preOrden.setEPP_BASICO_GAFAS("");}
                if(check3.isChecked()){preOrden.setEPP_BASICO_TAPONES("SI");}else{preOrden.setEPP_BASICO_TAPONES("");}
                if(check4.isChecked()){preOrden.setEPP_BASICO_ZAPATOS("SI");}else{preOrden.setEPP_BASICO_ZAPATOS("");}
                if(check5.isChecked()){preOrden.setEPP_BASICO_GUANTES("SI");}else{preOrden.setEPP_BASICO_GUANTES("");}
                if(check6.isChecked()){preOrden.setEPP_BASICO_BARBIQUEJO("SI");}else{preOrden.setEPP_BASICO_BARBIQUEJO("");}
                if(check7.isChecked()){preOrden.setEPP_ELECTICO_GAFAS("SI");}else{preOrden.setEPP_ELECTICO_GAFAS("");}
                if(check8.isChecked()){preOrden.setEPP_ELECTICO_CASCO("SI");}else{preOrden.setEPP_ELECTICO_CASCO("");}
                if(check9.isChecked()){preOrden.setEPP_ELECTICO_ZAPATOS("SI");}else{preOrden.setEPP_ELECTICO_ZAPATOS("");}
                if(check10.isChecked()){preOrden.setEPP_ELECTICO_GUANTES("SI");}else{preOrden.setEPP_ELECTICO_GUANTES("");}
                if(check11.isChecked()){preOrden.setEPP_ELECTICO_SOBREGUANTE("SI");}else{preOrden.setEPP_ELECTICO_SOBREGUANTE("");}
                if(check12.isChecked()){preOrden.setEPP_ELECTICO_CARETA("SI");}else{preOrden.setEPP_ELECTICO_CARETA("");}
                if(check13.isChecked()){preOrden.setEPP_ELECTICO_BALACLAVA("SI");}else{preOrden.setEPP_ELECTICO_BALACLAVA("");}
                if(check14.isChecked()){preOrden.setEPP_ELECTICO_TRAJE("SI");}else{preOrden.setEPP_ELECTICO_TRAJE("");}
                if(check15.isChecked()){preOrden.setEPP_ELECTICO_PROTECTORES_AUDI("SI");}else{preOrden.setEPP_ELECTICO_PROTECTORES_AUDI("");}
                if(check16.isChecked()){preOrden.setEPP_ELECTICO_MANGAS("SI");}else{preOrden.setEPP_ELECTICO_MANGAS("");}
                if(check17.isChecked()){preOrden.setEPP_OTROS_PROTECCION_CAIDAS("SI");}else{preOrden.setEPP_OTROS_PROTECCION_CAIDAS("");}
                if(check18.isChecked()){preOrden.setEPP_OTROS_PROTECCION_RESPITA("SI");}else{preOrden.setEPP_OTROS_PROTECCION_RESPITA("");}
                if(check19.isChecked()){preOrden.setEPP_OTROS_PROTECCION_SOLDAD("SI");}else{preOrden.setEPP_OTROS_PROTECCION_SOLDAD("");}
                if(check20.isChecked()){preOrden.setEPP_OTROS_PROTECCION_QUIMICOS("SI");}else{preOrden.setEPP_OTROS_PROTECCION_QUIMICOS("");}
                preOrden.setEPP_OTROS_ADICIONALES(otro.getText().toString());

                //endregion

                D_B.guardarPreOrden(preOrden);
                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                siguiente.putExtra("valorPaso", preOrden.getFOLIO());
                startActivity(siguiente);
                }else{
                    Bundle valores = new Bundle();
                    valores.putString("titulo","Información Faltante");
                    valores.putString("mensaje","Especificar Adicionales.");

                    AlertaGenerica alerta = new AlertaGenerica();
                    alerta.setArguments(valores);
                    alerta.setCancelable(false);
                    alerta.show(getFragmentManager(), "a");
                    otro.requestFocus();
                }

            }
        });
        return v;
    }

}
