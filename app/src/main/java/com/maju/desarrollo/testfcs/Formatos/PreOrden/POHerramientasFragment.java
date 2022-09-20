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
import android.widget.RadioButton;
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
public class POHerramientasFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden="";
    PreOrden preOrden;
    EditText descripcion;
    String hojasSeguridad="";
    String HERRAM_DELIMITACION_AT="";
    EditText listaH,otro;

    public POHerramientasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_poherramientas, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
        final EditText listaH = (EditText)v.findViewById(R.id.EHM_lista);
        final RadioButton radioButtonSi = (RadioButton)v.findViewById(R.id.EHM_rd_si);
        final RadioButton radioButtonNo = (RadioButton)v.findViewById(R.id.EHM_rd_no);
        final RadioButton radioButtonNa = (RadioButton)v.findViewById(R.id.EHM_rd_na);
        final CheckBox check1 = (CheckBox)v.findViewById(R.id.EHM_chk_1);
        final CheckBox check2 = (CheckBox)v.findViewById(R.id.EHM_chk_2);
        final CheckBox check3 = (CheckBox)v.findViewById(R.id.EHM_chk_3);
        final CheckBox check4 = (CheckBox)v.findViewById(R.id.EHM_chk_4);
        final CheckBox check5 = (CheckBox)v.findViewById(R.id.EHM_chk_5);
        final EditText otro = (EditText)v.findViewById(R.id.EHM_edtx_otro);
        otro.setEnabled(false);

        //ver Valres preguardados
        listaH.setText(preOrden.getHERRAM_DESCRIPCION_EHM());

    try{if(preOrden.getHERRAM_HOJAS_SEGURIDAD().equals("SI")){radioButtonSi.setChecked(true);}}catch (Exception e){}
    try{if(preOrden.getHERRAM_HOJAS_SEGURIDAD().equals("NO")){radioButtonNo.setChecked(true);}}catch (Exception e){}
            try{if(preOrden.getHERRAM_HOJAS_SEGURIDAD().equals("N/A")){radioButtonNa.setChecked(true);}}catch (Exception e){}

            try {
                HERRAM_DELIMITACION_AT = preOrden.getHERRAM_DELIMITACION_AT();
                if (HERRAM_DELIMITACION_AT.equals("Cinta de Precaución")) {
                    check1.setChecked(true);
                } else if (HERRAM_DELIMITACION_AT.equals("Conos/Pedestales")) {
                    check2.setChecked(true);
                } else if (HERRAM_DELIMITACION_AT.equals("Mixta (Conos y Cinta.)")) {
                    check3.setChecked(true);
                } else if (HERRAM_DELIMITACION_AT.equals("Malla")) {
                    check4.setChecked(true);
                } else if(!HERRAM_DELIMITACION_AT.isEmpty()) {
                    check5.setChecked(true);
                    otro.setText(HERRAM_DELIMITACION_AT);
                    otro.setEnabled(true);
                }
            } catch (Exception e){}

        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                check2.setChecked(false);
                check3.setChecked(false);
                check4.setChecked(false);
                check5.setChecked(false);
                otro.setText("");
                otro.setEnabled(false);
                }

            }
        });
        check2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    check1.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    otro.setText("");
                    otro.setEnabled(false);
                }

            }
        });
        check3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    check1.setChecked(false);
                    check2.setChecked(false);
                    check4.setChecked(false);
                    check5.setChecked(false);
                    otro.setText("");
                    otro.setEnabled(false);
                }

            }
        });
        check4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    check1.setChecked(false);
                    check3.setChecked(false);
                    check2.setChecked(false);
                    check5.setChecked(false);
                    otro.setText("");
                    otro.setEnabled(false);
                }

            }
        });
        check5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    check1.setChecked(false);
                    check3.setChecked(false);
                    check4.setChecked(false);
                    check2.setChecked(false);
                    otro.setText("");
                    otro.setEnabled(true);
                }

            }
        });




        //preOrden.gethe


        Button cancelar = (Button)v.findViewById(R.id.EHM_btn_Cancelar);
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
                dialog.setTargetFragment(POHerramientasFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.EHM_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                boolean pasa = true;
                if(check5.isChecked() && otro.getText().toString().trim().length()==0){
                    pasa = false;
                }
                else{}
//region guardar
                if(pasa) {
                    if (radioButtonSi.isChecked()) {
                        hojasSeguridad = "SI";
                    }
                    if (radioButtonNo.isChecked()) {
                        hojasSeguridad = "NO";
                    }
                    if (radioButtonNa.isChecked()) {
                        hojasSeguridad = "N/A";
                    }


                    try {
                        HERRAM_DELIMITACION_AT = preOrden.getHERRAM_DELIMITACION_AT();
                        if (check1.isChecked()) {
                            HERRAM_DELIMITACION_AT = "Cinta de Precaución";
                        } else if (check2.isChecked()) {
                            HERRAM_DELIMITACION_AT = "Conos/Pedestales";
                        } else if (check3.isChecked()) {
                            HERRAM_DELIMITACION_AT = "Mixta (Conos y Cinta.)";
                        } else if (check4.isChecked()) {
                            HERRAM_DELIMITACION_AT = "Malla";
                        } else {
                            //check5.isChecked();
                            HERRAM_DELIMITACION_AT = otro.getText().toString();
                        }
                    } catch (Exception e) {
                    }

                    preOrden.setHERRAM_DESCRIPCION_EHM(listaH.getText().toString());
                    preOrden.setHERRAM_HOJAS_SEGURIDAD(hojasSeguridad);
                    preOrden.setHERRAM_DELIMITACION_AT(HERRAM_DELIMITACION_AT);

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
                //endregion

            }
        });

        return v;
    }

}
