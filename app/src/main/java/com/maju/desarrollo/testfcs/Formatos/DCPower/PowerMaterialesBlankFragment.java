package com.maju.desarrollo.testfcs.Formatos.DCPower;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerMaterialesBlankFragment extends Fragment {
    TextView fecha1, fecha2, fecha3, fecha4, fecha5, fecha6;
    OperacionesDB D_B;
    String id_formato;
    DCPower2 dcPower;

    public PowerMaterialesBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_power_materiales_blank, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        dcPower = D_B.obtenerDcPower2_id(id_formato);




        final CheckBox checkBoxNa1 = (CheckBox)v.findViewById(R.id.checkBoxNa1);
        final CheckBox checkBoxNa2 = (CheckBox)v.findViewById(R.id.checkBoxNa2);

        final LinearLayout layout1 = (LinearLayout)v.findViewById(R.id.layout1);
        final LinearLayout layout2 = (LinearLayout)v.findViewById(R.id.layout2);

        final EditText cantidad1 = (EditText)v.findViewById(R.id.cantidad1);
        final EditText parte1= (EditText)v.findViewById(R.id.parte1);
        final EditText especifica1 = (EditText)v.findViewById(R.id.especifica1);
        final EditText cantidad2 = (EditText)v.findViewById(R.id.cantidad2);
        final EditText parte2 = (EditText)v.findViewById(R.id.parte2);
        final EditText especifica2 = (EditText)v.findViewById(R.id.especifica2);
        final EditText cantidad3 = (EditText)v.findViewById(R.id.cantidad3);
        final EditText parte3 = (EditText)v.findViewById(R.id.parte3);
        final EditText especifica3 = (EditText)v.findViewById(R.id.especifica3);
        final EditText cantidad4 = (EditText)v.findViewById(R.id.cantidad4);
        final EditText parte4 = (EditText)v.findViewById(R.id.parte4);
        final EditText especifica4 = (EditText)v.findViewById(R.id.especifica4);
        final EditText cantidad5 = (EditText)v.findViewById(R.id.cantidad5);
        final EditText parte5 = (EditText)v.findViewById(R.id.parte5);
        final EditText especifica5 = (EditText)v.findViewById(R.id.especifica5);
        final EditText cantidad6 = (EditText)v.findViewById(R.id.cantidad6);
        final EditText parte6 = (EditText)v.findViewById(R.id.parte6);
        final EditText especifica6 = (EditText)v.findViewById(R.id.especifica6);
        final EditText cantidad7 = (EditText)v.findViewById(R.id.cantidad7);
        final EditText parte7 = (EditText)v.findViewById(R.id.parte7);
        final EditText especifica7 = (EditText)v.findViewById(R.id.especifica7);
        final EditText cantidad8 = (EditText)v.findViewById(R.id.cantidad8);
        final EditText parte8 = (EditText)v.findViewById(R.id.parte8);
        final EditText especifica8 = (EditText)v.findViewById(R.id.especifica8);
        final EditText quipo1 = (EditText)v.findViewById(R.id.quipo1);
        final EditText nid1 = (EditText)v.findViewById(R.id.nid1);
        fecha1 = (TextView) v.findViewById(R.id.fecha1);
        final EditText quipo2 = (EditText)v.findViewById(R.id.quipo2);
        final EditText nid2 = (EditText)v.findViewById(R.id.nid2);
        fecha2 = (TextView)v.findViewById(R.id.fecha2);
        final EditText quipo3 = (EditText)v.findViewById(R.id.quipo3);
        final EditText nid3 = (EditText)v.findViewById(R.id.nid3);
        fecha3 = (TextView)v.findViewById(R.id.fecha3);
        final EditText quipo4 = (EditText)v.findViewById(R.id.quipo4);
        final EditText nid4 = (EditText)v.findViewById(R.id.nid4);
        fecha4 = (TextView)v.findViewById(R.id.fecha4);
        final EditText quipo5 = (EditText)v.findViewById(R.id.quipo5);
        final EditText nid5 = (EditText)v.findViewById(R.id.nid5);
        fecha5 = (TextView)v.findViewById(R.id.fecha5);
        final EditText quipo6 = (EditText)v.findViewById(R.id.quipo6);
        final EditText nid6 = (EditText)v.findViewById(R.id.nid6);
        fecha6 = (TextView)v.findViewById(R.id.fecha6);

        try{if(dcPower.getMATERIA_PMU_NA().equals("N/A")){checkBoxNa1.setChecked(true);}}catch(Exception e){}
        try{if(dcPower.getMATERIA_EQUIPO_NA().equals("N/A")){checkBoxNa2.setChecked(true);}}catch(Exception e){}

        try{cantidad1 .setText(dcPower.getMATERIA_CANTIDAD1 ());}catch (Exception e){}
        try{parte1.setText(dcPower.getMATERIA_N_PARTE1());}catch (Exception e){}
        try{especifica1 .setText(dcPower.getMATERIA_DESC1());}catch (Exception e){}
        try{cantidad2 .setText(dcPower.getMATERIA_CANTIDAD2 ());}catch (Exception e){}
        try{parte2 .setText(dcPower.getMATERIA_N_PARTE2 ());}catch (Exception e){}
        try{especifica2 .setText(dcPower.getMATERIA_DESC2 ());}catch (Exception e){}
        try{cantidad3 .setText(dcPower.getMATERIA_CANTIDAD3 ());}catch (Exception e){}
        try{parte3 .setText(dcPower.getMATERIA_N_PARTE3 ());}catch (Exception e){}
        try{especifica3 .setText(dcPower.getMATERIA_DESC3 ());}catch (Exception e){}
        try{cantidad4 .setText(dcPower.getMATERIA_CANTIDAD4 ());}catch (Exception e){}
        try{parte4 .setText(dcPower.getMATERIA_N_PARTE4 ());}catch (Exception e){}
        try{especifica4 .setText(dcPower.getMATERIA_DESC4 ());}catch (Exception e){}
        try{cantidad5 .setText(dcPower.getMATERIA_CANTIDAD5 ());}catch (Exception e){}
        try{parte5 .setText(dcPower.getMATERIA_N_PARTE5 ());}catch (Exception e){}
        try{especifica5 .setText(dcPower.getMATERIA_DESC5 ());}catch (Exception e){}
        try{cantidad6 .setText(dcPower.getMATERIA_CANTIDAD6 ());}catch (Exception e){}
        try{parte6 .setText(dcPower.getMATERIA_N_PARTE6 ());}catch (Exception e){}
        try{especifica6 .setText(dcPower.getMATERIA_DESC6 ());}catch (Exception e){}
        try{cantidad7 .setText(dcPower.getMATERIA_CANTIDAD7 ());}catch (Exception e){}
        try{parte7 .setText(dcPower.getMATERIA_N_PARTE7 ());}catch (Exception e){}
        try{especifica7 .setText(dcPower.getMATERIA_DESC7 ());}catch (Exception e){}
        try{cantidad8 .setText(dcPower.getMATERIA_CANTIDAD8 ());}catch (Exception e){}
        try{parte8 .setText(dcPower.getMATERIA_N_PARTE8 ());}catch (Exception e){}
        try{especifica8 .setText(dcPower.getMATERIA_DESC8 ());}catch (Exception e){}


        try{quipo1 .setText(dcPower.getMATERIA_EQUIPO_EQUIPO1());}catch (Exception e){}
        try{nid1 .setText(dcPower.getMATERIA_EQUIPO_ID1());}catch (Exception e){}
        try{fecha1 .setText(dcPower.getMATERIA_EQUIPO_FEVHA1());}catch (Exception e){}
        try{quipo2 .setText(dcPower.getMATERIA_EQUIPO_EQUIPO2 ());}catch (Exception e){}
        try{nid2 .setText(dcPower.getMATERIA_EQUIPO_ID2 ());}catch (Exception e){}
        try{fecha2 .setText(dcPower.getMATERIA_EQUIPO_FEVHA2 ());}catch (Exception e){}
        try{quipo3 .setText(dcPower.getMATERIA_EQUIPO_EQUIPO3 ());}catch (Exception e){}
        try{nid3 .setText(dcPower.getMATERIA_EQUIPO_ID3 ());}catch (Exception e){}
        try{fecha3 .setText(dcPower.getMATERIA_EQUIPO_FEVHA3 ());}catch (Exception e){}
        try{quipo4 .setText(dcPower.getMATERIA_EQUIPO_EQUIPO4 ());}catch (Exception e){}
        try{nid4 .setText(dcPower.getMATERIA_EQUIPO_ID4 ());}catch (Exception e){}
        try{fecha4 .setText(dcPower.getMATERIA_EQUIPO_FEVHA4 ());}catch (Exception e){}
        try{quipo5 .setText(dcPower.getMATERIA_EQUIPO_EQUIPO5 ());}catch (Exception e){}
        try{nid5 .setText(dcPower.getMATERIA_EQUIPO_ID5 ());}catch (Exception e){}
        try{fecha5 .setText(dcPower.getMATERIA_EQUIPO_FEVHA5 ());}catch (Exception e){}



        ImageView btnfecha1 = (ImageView)v.findViewById(R.id.btnfecha1);
        ImageView btnfecha2 = (ImageView)v.findViewById(R.id.btnfecha2);
        ImageView btnfecha3 = (ImageView)v.findViewById(R.id.btnfecha3);
        ImageView btnfecha4 = (ImageView)v.findViewById(R.id.btnfecha4);
        ImageView btnfecha5 = (ImageView)v.findViewById(R.id.btnfecha5);
        ImageView btnfecha6 = (ImageView)v.findViewById(R.id.btnfecha6);









        //No se guardan valores
        if(checkBoxNa1.isChecked()){}

        //region Botones

        checkBoxNa1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    layout1.setVisibility(View.GONE);
                    //check1.setChecked(false);

                }else{
                    layout1.setVisibility(View.VISIBLE);
                }

            }
        });
        checkBoxNa2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    layout2.setVisibility(View.GONE);
                    //check1.setChecked(false);

                }else{
                    layout2.setVisibility(View.VISIBLE);
                }

            }
        });


        btnfecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("1");
                //endregion
            }
        });
        btnfecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("2");
                //endregion
            }
        });
        btnfecha3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("3");
                //endregion
            }
        });
        btnfecha4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("4");
                //endregion
            }
        });
        btnfecha5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("5");
                //endregion
            }
        });
        btnfecha6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("6");
                //endregion
            }
        });






        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "DCPower");
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
                if(checkBoxNa1.isChecked()){
                    dcPower.setMATERIA_PMU_NA("N/A");
                    dcPower.setMATERIA_CANTIDAD1(" ");
                    dcPower.setMATERIA_N_PARTE1(" ");
                    dcPower.setMATERIA_DESC1(" ");
                }
                else {
                    dcPower.setMATERIA_CANTIDAD1(cantidad1.getText().toString());
                    dcPower.setMATERIA_N_PARTE1(parte1.getText().toString());
                    dcPower.setMATERIA_DESC1(especifica1.getText().toString());
                    dcPower.setMATERIA_CANTIDAD2(cantidad2.getText().toString());
                    dcPower.setMATERIA_N_PARTE2(parte2.getText().toString());
                    dcPower.setMATERIA_DESC2(especifica2.getText().toString());
                    dcPower.setMATERIA_CANTIDAD3(cantidad3.getText().toString());
                    dcPower.setMATERIA_N_PARTE3(parte3.getText().toString());
                    dcPower.setMATERIA_DESC3(especifica3.getText().toString());
                    dcPower.setMATERIA_CANTIDAD4(cantidad4.getText().toString());
                    dcPower.setMATERIA_N_PARTE4(parte4.getText().toString());
                    dcPower.setMATERIA_DESC4(especifica4.getText().toString());
                    dcPower.setMATERIA_CANTIDAD5(cantidad5.getText().toString());
                    dcPower.setMATERIA_N_PARTE5(parte5.getText().toString());
                    dcPower.setMATERIA_DESC5(especifica5.getText().toString());
                    dcPower.setMATERIA_CANTIDAD6(cantidad6.getText().toString());
                    dcPower.setMATERIA_N_PARTE6(parte6.getText().toString());
                    dcPower.setMATERIA_DESC6(especifica6.getText().toString());
                    dcPower.setMATERIA_CANTIDAD7(cantidad7.getText().toString());
                    dcPower.setMATERIA_N_PARTE7(parte7.getText().toString());
                    dcPower.setMATERIA_DESC7(especifica7.getText().toString());
                    dcPower.setMATERIA_CANTIDAD8(cantidad8.getText().toString());
                    dcPower.setMATERIA_N_PARTE8(parte8.getText().toString());
                    dcPower.setMATERIA_DESC8(especifica8.getText().toString());
                }

                if(checkBoxNa2.isChecked()){
                    dcPower.setMATERIA_EQUIPO_NA("N/A");
                    dcPower.setMATERIA_EQUIPO_EQUIPO1(" ");
                    dcPower.setMATERIA_EQUIPO_ID1(" ");
                    dcPower.setMATERIA_EQUIPO_FEVHA1(" ");
                }
                else {
                    dcPower.setMATERIA_EQUIPO_EQUIPO1(quipo1.getText().toString());
                    dcPower.setMATERIA_EQUIPO_ID1(nid1.getText().toString());
                    dcPower.setMATERIA_EQUIPO_FEVHA1(fecha1.getText().toString());
                    dcPower.setMATERIA_EQUIPO_EQUIPO2(quipo2.getText().toString());
                    dcPower.setMATERIA_EQUIPO_ID2(nid2.getText().toString());
                    dcPower.setMATERIA_EQUIPO_FEVHA2(fecha2.getText().toString());
                    dcPower.setMATERIA_EQUIPO_EQUIPO3(quipo3.getText().toString());
                    dcPower.setMATERIA_EQUIPO_ID3(nid3.getText().toString());
                    dcPower.setMATERIA_EQUIPO_FEVHA3(fecha3.getText().toString());
                    dcPower.setMATERIA_EQUIPO_EQUIPO4(quipo4.getText().toString());
                    dcPower.setMATERIA_EQUIPO_ID4(nid4.getText().toString());
                    dcPower.setMATERIA_EQUIPO_FEVHA4(fecha4.getText().toString());
                    dcPower.setMATERIA_EQUIPO_EQUIPO5(quipo5.getText().toString());
                    dcPower.setMATERIA_EQUIPO_ID5(nid5.getText().toString());
                    dcPower.setMATERIA_EQUIPO_FEVHA5(fecha5.getText().toString());
                }

                D_B.guardarPower2(dcPower,id_formato);

                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                MenuPowerFragment myfargment = new MenuPowerFragment();
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

    private void showDatePickerDialog1(final String campoFecha ) {
        final DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;

                if(campoFecha.equals("1")){fecha1.setText(selectedDate);}
                if(campoFecha.equals("2")){fecha2.setText(selectedDate);}
                if(campoFecha.equals("3")){fecha3.setText(selectedDate);}
                if(campoFecha.equals("4")){fecha4.setText(selectedDate);}
                if(campoFecha.equals("5")){fecha5.setText(selectedDate);}
                if(campoFecha.equals("6")){fecha6.setText(selectedDate);}

            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

}
