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
public class POPrevencionFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;

    public POPrevencionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_poprevencion, container, false);
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
        final CheckBox check19 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox23);
        final EditText otro = (EditText)v.findViewById(R.id.Reiesgo_Edt_otro);
        otro.setEnabled(false);

        try{
            if(preOrden.getPREVEN_DISPO_MECANICA().equals("SI")){check1.setChecked(true);};
            if(preOrden.getPREVEN_SUST_QUIMICOS().equals("SI")){check2.setChecked(true);}
            if(preOrden.getPREVEN_AISLAR_RUIDO().equals("SI")){check3.setChecked(true);}
            if(preOrden.getPREVEN_PROTECTORES_MAQUINAS().equals("SI")){check4.setChecked(true);}
            if(preOrden.getPREVEN_PLATA_ANDAMIOS().equals("SI")){check5.setChecked(true);}
            if(preOrden.getPREVEN_SIS_PNTS_ANCLAJE().equals("SI")){check6.setChecked(true);}
            if(preOrden.getPREVEN_ILUMI_ART().equals("SI")){check7.setChecked(true);}
            if(preOrden.getPREVEN_DISYUNTOR().equals("SI")){check8.setChecked(true);}
            if(preOrden.getPREVEN_SIST_PUESTA_TIERRA().equals("SI")){check9.setChecked(true);}
            if(preOrden.getPREVEN_ORDEN_LIMPIEZA().equals("SI")){check10.setChecked(true);}
            if(preOrden.getPREVEN_AREA_TRABAJO().equals("SI")){check11.setChecked(true);}
            if(preOrden.getPREVEN_BE_FUENTES_ENERGIA().equals("SI")){check12.setChecked(true);}
            if(preOrden.getPREVEN_MUROS_DERRAME().equals("SI")){check13.setChecked(true);}
            if(preOrden.getPREVEN_PERMISOS().equals("SI")){check14.setChecked(true);}
            if(preOrden.getPREVEN_INSTRUCTIVOS().equals("SI")){check15.setChecked(true);}
            if(preOrden.getPREVEN_SUPERVISION().equals("SI")){check16.setChecked(true);}
            if(preOrden.getPREVEN_HERRAMI_AISLADAS().equals("SI")){check17.setChecked(true);}
            if(preOrden.getPREVEN_EPP().equals("SI")){check18.setChecked(true);}
            if(preOrden.getPREVEN_OTRO().length()>1){check19.setChecked(true);
                otro.setText(preOrden.getPREVEN_OTRO());
                otro.setEnabled(true);

            }

        }catch (Exception e){}


        check19.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
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




        Button cancelar = (Button)v.findViewById(R.id.Preven_btn_Cancelar);
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
                dialog.setTargetFragment(POPrevencionFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.Preven_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());
                boolean pasa = true;
                if(check19.isChecked() && otro.getText().toString().trim().length()==0){
                    pasa = false;
                }
                if(pasa){


                //region
                if(check1.isChecked()){preOrden.setPREVEN_DISPO_MECANICA("SI");}else{preOrden.setPREVEN_DISPO_MECANICA("");}
                if(check2.isChecked()){preOrden.setPREVEN_SUST_QUIMICOS("SI");}else{preOrden.setPREVEN_SUST_QUIMICOS("");}
                if(check3.isChecked()){preOrden.setPREVEN_AISLAR_RUIDO("SI");}else{preOrden.setPREVEN_AISLAR_RUIDO("");}
                if(check4.isChecked()){preOrden.setPREVEN_PROTECTORES_MAQUINAS("SI");}else{preOrden.setPREVEN_PROTECTORES_MAQUINAS("");}
                if(check5.isChecked()){preOrden.setPREVEN_PLATA_ANDAMIOS("SI");}else{preOrden.setPREVEN_PLATA_ANDAMIOS("");}
                if(check6.isChecked()){preOrden.setPREVEN_SIS_PNTS_ANCLAJE("SI");}else{preOrden.setPREVEN_SIS_PNTS_ANCLAJE("");}
                if(check7.isChecked()){preOrden.setPREVEN_ILUMI_ART("SI");}else{preOrden.setPREVEN_ILUMI_ART("");}
                if(check8.isChecked()){preOrden.setPREVEN_DISYUNTOR("SI");}else{preOrden.setPREVEN_DISYUNTOR("");}
                if(check9.isChecked()){preOrden.setPREVEN_SIST_PUESTA_TIERRA("SI");}else{preOrden.setPREVEN_SIST_PUESTA_TIERRA("");}
                if(check10.isChecked()){preOrden.setPREVEN_ORDEN_LIMPIEZA("SI");}else{preOrden.setPREVEN_ORDEN_LIMPIEZA("");}
                if(check11.isChecked()){preOrden.setPREVEN_AREA_TRABAJO("SI");}else{preOrden.setPREVEN_AREA_TRABAJO("");}
                if(check12.isChecked()){preOrden.setPREVEN_BE_FUENTES_ENERGIA("SI");}else{preOrden.setPREVEN_BE_FUENTES_ENERGIA("");}
                if(check13.isChecked()){preOrden.setPREVEN_MUROS_DERRAME("SI");}else{preOrden.setPREVEN_MUROS_DERRAME("");}
                if(check14.isChecked()){preOrden.setPREVEN_PERMISOS("SI");}else{preOrden.setPREVEN_PERMISOS("");}
                if(check15.isChecked()){preOrden.setPREVEN_INSTRUCTIVOS("SI");}else{preOrden.setPREVEN_INSTRUCTIVOS("");}
                if(check16.isChecked()){preOrden.setPREVEN_SUPERVISION("SI");}else{preOrden.setPREVEN_SUPERVISION("");}
                if(check17.isChecked()){preOrden.setPREVEN_HERRAMI_AISLADAS("SI");}else{preOrden.setPREVEN_HERRAMI_AISLADAS("");}
                if(check18.isChecked()){preOrden.setPREVEN_EPP("SI");}else{preOrden.setPREVEN_EPP("");}
                preOrden.setPREVEN_OTRO(otro.getText().toString());
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
                    valores.putString("mensaje","Especificar Otro.");

                    AlertaGenerica alerta = new AlertaGenerica();
                    alerta.setArguments(valores);
                    alerta.setCancelable(false);
                    alerta.show(getFragmentManager(), "a");
                }
            }
        });
        return v;
    }

}
