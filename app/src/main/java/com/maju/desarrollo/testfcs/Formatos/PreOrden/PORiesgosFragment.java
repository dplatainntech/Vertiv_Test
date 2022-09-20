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
public class PORiesgosFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;

    public PORiesgosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_poriesgos, container, false);

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
        final CheckBox check21 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox21);
        final CheckBox check22 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox22);
        final CheckBox check23 = (CheckBox)v.findViewById(R.id.Reiesgo_checkBox23);
        final EditText otro = (EditText)v.findViewById(R.id.Reiesgo_Edt_otro);
        otro.setEnabled(false);

        try{
            if(preOrden.getRIESGO_PARTICULAS().equals("SI")){check1.setChecked(true);};
            if(preOrden.getRIESGO_ATRAPAMIENTO().equals("SI")){check2.setChecked(true);}
            if(preOrden.getRIESGO_GOLPES().equals("SI")){check3.setChecked(true);}
            if(preOrden.getRIESGO_QUEMADURAS().equals("SI")){check4.setChecked(true);}
            if(preOrden.getRIESGO_CAIDA_MATE().equals("SI")){check5.setChecked(true);}
            if(preOrden.getRIESGO_CAIDA_MISMO_NIVEL().equals("SI")){check6.setChecked(true);}
            if(preOrden.getRIESGO_CAIDA_DIST_NIVEL().equals("SI")){check7.setChecked(true);}
            if(preOrden.getRIESGO_LIMPIEZA_DEFI().equals("SI")){check8.setChecked(true);}
            if(preOrden.getRIESGO_OTRO_PERSONAL().equals("SI")){check9.setChecked(true);}
            if(preOrden.getRIESGO_CHOQUE_ELECTRICO().equals("SI")){check10.setChecked(true);}
            if(preOrden.getRIESGO_ARCO_ELECT().equals("SI")){check11.setChecked(true);}
            if(preOrden.getRIESGO_FUEGO().equals("SI")){check12.setChecked(true);}
            if(preOrden.getRIESGO_EXPO_RUIDO().equals("SI")){check13.setChecked(true);}
            if(preOrden.getRIESGO_EXP_VIBRA().equals("SI")){check14.setChecked(true);}
            if(preOrden.getRIESGO_FATIGA_VISUAL().equals("SI")){check15.setChecked(true);}
            if(preOrden.getRIESGO_TEMPERATURAS().equals("SI")){check16.setChecked(true);}
            if(preOrden.getRIESGO_DEFI_OXIGENO().equals("SI")){check17.setChecked(true);}
            if(preOrden.getRIESGO_GASES().equals("SI")){check18.setChecked(true);}
            if(preOrden.getRIESGO_POLVO().equals("SI")){check19.setChecked(true);}
            if(preOrden.getRIESGO_SOBRE_ESFUERZO().equals("SI")){check20.setChecked(true);}
            if(preOrden.getRIESGO_QUIMICOS().equals("SI")){check21.setChecked(true);}
            if(preOrden.getRIESGO_RUIDO().equals("SI")){check22.setChecked(true);}
            if(preOrden.getRIESGO_OTRO().length()>1){check23.setChecked(true);
                otro.setText(preOrden.getRIESGO_OTRO());
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




        Button cancelar = (Button)v.findViewById(R.id.Reiesgo_btn_Cancelar);
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
                dialog.setTargetFragment(PORiesgosFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.Reiesgo_btn_Guardar);
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
                if(check1.isChecked()){preOrden.setRIESGO_PARTICULAS("SI");}else{preOrden.setRIESGO_PARTICULAS("");}
                if(check2.isChecked()){preOrden.setRIESGO_ATRAPAMIENTO("SI");}else{preOrden.setRIESGO_ATRAPAMIENTO("");}
                if(check3.isChecked()){preOrden.setRIESGO_GOLPES("SI");}else{preOrden.setRIESGO_GOLPES("");}
                if(check4.isChecked()){preOrden.setRIESGO_QUEMADURAS("SI");}else{preOrden.setRIESGO_QUEMADURAS("");}
                if(check5.isChecked()){preOrden.setRIESGO_CAIDA_MATE("SI");}else{preOrden.setRIESGO_CAIDA_MATE("");}
                if(check6.isChecked()){preOrden.setRIESGO_CAIDA_MISMO_NIVEL("SI");}else{preOrden.setRIESGO_CAIDA_MISMO_NIVEL("");}
                if(check7.isChecked()){preOrden.setRIESGO_CAIDA_DIST_NIVEL("SI");}else{preOrden.setRIESGO_CAIDA_DIST_NIVEL("");}
                if(check8.isChecked()){preOrden.setRIESGO_LIMPIEZA_DEFI("SI");}else{preOrden.setRIESGO_LIMPIEZA_DEFI("");}
                if(check9.isChecked()){preOrden.setRIESGO_OTRO_PERSONAL("SI");}else{preOrden.setRIESGO_OTRO_PERSONAL("");}
                if(check10.isChecked()){preOrden.setRIESGO_CHOQUE_ELECTRICO("SI");}else{preOrden.setRIESGO_CHOQUE_ELECTRICO("");}
                if(check11.isChecked()){preOrden.setRIESGO_ARCO_ELECT("SI");}else{preOrden.setRIESGO_ARCO_ELECT("");}
                if(check12.isChecked()){preOrden.setRIESGO_FUEGO("SI");}else{preOrden.setRIESGO_FUEGO("");}
                if(check13.isChecked()){preOrden.setRIESGO_EXPO_RUIDO("SI");}else{preOrden.setRIESGO_EXPO_RUIDO("");}
                if(check14.isChecked()){preOrden.setRIESGO_EXP_VIBRA("SI");}else{preOrden.setRIESGO_EXP_VIBRA("");}
                if(check15.isChecked()){preOrden.setRIESGO_FATIGA_VISUAL("SI");}else{preOrden.setRIESGO_FATIGA_VISUAL("");}
                if(check16.isChecked()){preOrden.setRIESGO_TEMPERATURAS("SI");}else{preOrden.setRIESGO_TEMPERATURAS("");}
                if(check17.isChecked()){preOrden.setRIESGO_DEFI_OXIGENO("SI");}else{preOrden.setRIESGO_DEFI_OXIGENO("");}
                if(check18.isChecked()){preOrden.setRIESGO_GASES("SI");}else{preOrden.setRIESGO_GASES("");}
                if(check19.isChecked()){preOrden.setRIESGO_POLVO("SI");}else{preOrden.setRIESGO_POLVO("");}
                if(check20.isChecked()){preOrden.setRIESGO_SOBRE_ESFUERZO("SI");}else{preOrden.setRIESGO_SOBRE_ESFUERZO("");}
                if(check21.isChecked()){preOrden.setRIESGO_QUIMICOS("SI");}else{preOrden.setRIESGO_QUIMICOS("");}
                if(check22.isChecked()){preOrden.setRIESGO_RUIDO("SI");}else{preOrden.setRIESGO_RUIDO("");}
                preOrden.setRIESGO_OTRO(otro.getText().toString());

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
        return  v;
    }

}
