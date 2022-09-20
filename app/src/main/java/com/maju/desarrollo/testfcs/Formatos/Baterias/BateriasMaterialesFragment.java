package com.maju.desarrollo.testfcs.Formatos.Baterias;


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
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriasMaterialesFragment extends Fragment {
TextView fecha1, fecha2, fecha3, fecha4, fecha5, fecha6;
    OperacionesDB D_B;
    String id_formato;
    Baterias bateria;

    public BateriasMaterialesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_baterias_materiales, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");

        bateria = D_B.obtenerBateriaF_id(id_formato);





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

        try{if(bateria.getMATE_cantidad1().equals(" ")){checkBoxNa1.setChecked(true);}}catch(Exception e){}
        try{if(bateria.getMATE_equipo1().equals(" ")){checkBoxNa2.setChecked(true);}}catch(Exception e){}

        try{cantidad1 .setText(bateria.getMATE_cantidad1 ());}catch (Exception e){}
        try{parte1.setText(bateria.getMATE_parte1());}catch (Exception e){}
        try{especifica1 .setText(bateria.getMATE_especifica1 ());}catch (Exception e){}
        try{cantidad2 .setText(bateria.getMATE_cantidad2 ());}catch (Exception e){}
        try{parte2 .setText(bateria.getMATE_parte2 ());}catch (Exception e){}
        try{especifica2 .setText(bateria.getMATE_especifica2 ());}catch (Exception e){}
        try{cantidad3 .setText(bateria.getMATE_cantidad3 ());}catch (Exception e){}
        try{parte3 .setText(bateria.getMATE_parte3 ());}catch (Exception e){}
        try{especifica3 .setText(bateria.getMATE_especifica3 ());}catch (Exception e){}
        try{cantidad4 .setText(bateria.getMATE_cantidad4 ());}catch (Exception e){}
        try{parte4 .setText(bateria.getMATE_parte4 ());}catch (Exception e){}
        try{especifica4 .setText(bateria.getMATE_especifica4 ());}catch (Exception e){}
        try{cantidad5 .setText(bateria.getMATE_cantidad5 ());}catch (Exception e){}
        try{parte5 .setText(bateria.getMATE_parte5 ());}catch (Exception e){}
        try{especifica5 .setText(bateria.getMATE_especifica5 ());}catch (Exception e){}
        try{cantidad6 .setText(bateria.getMATE_cantidad6 ());}catch (Exception e){}
        try{parte6 .setText(bateria.getMATE_parte6 ());}catch (Exception e){}
        try{especifica6 .setText(bateria.getMATE_especifica6 ());}catch (Exception e){}
        try{cantidad7 .setText(bateria.getMATE_cantidad7 ());}catch (Exception e){}
        try{parte7 .setText(bateria.getMATE_parte7 ());}catch (Exception e){}
        try{especifica7 .setText(bateria.getMATE_especifica7 ());}catch (Exception e){}
        try{cantidad8 .setText(bateria.getMATE_cantidad8 ());}catch (Exception e){}
        try{parte8 .setText(bateria.getMATE_parte8 ());}catch (Exception e){}
        try{especifica8 .setText(bateria.getMATE_especifica8 ());}catch (Exception e){}
        try{quipo1 .setText(bateria.getMATE_equipo1 ());}catch (Exception e){}
        try{nid1 .setText(bateria.getMATE_nid1 ());}catch (Exception e){}
        try{fecha1 .setText(bateria.getMATE_fecha1 ());}catch (Exception e){}
        try{quipo2 .setText(bateria.getMATE_equipo2 ());}catch (Exception e){}
        try{nid2 .setText(bateria.getMATE_nid2 ());}catch (Exception e){}
        try{fecha2 .setText(bateria.getMATE_fecha2 ());}catch (Exception e){}
        try{quipo3 .setText(bateria.getMATE_equipo3 ());}catch (Exception e){}
        try{nid3 .setText(bateria.getMATE_nid3 ());}catch (Exception e){}
        try{fecha3 .setText(bateria.getMATE_fecha3 ());}catch (Exception e){}
        try{quipo4 .setText(bateria.getMATE_equipo4 ());}catch (Exception e){}
        try{nid4 .setText(bateria.getMATE_nid4 ());}catch (Exception e){}
        try{fecha4 .setText(bateria.getMATE_fecha4 ());}catch (Exception e){}
        try{quipo5 .setText(bateria.getMATE_equipo5 ());}catch (Exception e){}
        try{nid5 .setText(bateria.getMATE_nid5 ());}catch (Exception e){}
        try{fecha5 .setText(bateria.getMATE_fecha5 ());}catch (Exception e){}
        try{quipo6 .setText(bateria.getMATE_equipo6 ());}catch (Exception e){}
        try{nid6 .setText(bateria.getMATE_nid6 ());}catch (Exception e){}
        try{fecha6 .setText(bateria.getMATE_fecha6 ());}catch (Exception e){}


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
                args.putString("OtraPantalla", "Baterias");
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
                    bateria.setMATE_cantidad1(" ");
                    bateria.setMATE_parte1(" ");
                    bateria.setMATE_especifica1(" ");
                }else {
                    bateria.setMATE_cantidad1(cantidad1 .getText().toString());
                    bateria.setMATE_parte1(parte1.getText().toString());
                    bateria.setMATE_especifica1(especifica1 .getText().toString());
                    bateria.setMATE_cantidad2(cantidad2 .getText().toString());
                    bateria.setMATE_parte2(parte2 .getText().toString());
                    bateria.setMATE_especifica2(especifica2 .getText().toString());
                    bateria.setMATE_cantidad3(cantidad3 .getText().toString());
                    bateria.setMATE_parte3(parte3 .getText().toString());
                    bateria.setMATE_especifica3(especifica3 .getText().toString());
                    bateria.setMATE_cantidad4(cantidad4 .getText().toString());
                    bateria.setMATE_parte4(parte4 .getText().toString());
                    bateria.setMATE_especifica4(especifica4 .getText().toString());
                    bateria.setMATE_cantidad5(cantidad5 .getText().toString());
                    bateria.setMATE_parte5(parte5 .getText().toString());
                    bateria.setMATE_especifica5(especifica5 .getText().toString());
                    bateria.setMATE_cantidad6(cantidad6 .getText().toString());
                    bateria.setMATE_parte6(parte6 .getText().toString());
                    bateria.setMATE_especifica6(especifica6 .getText().toString());
                    bateria.setMATE_cantidad7(cantidad7 .getText().toString());
                    bateria.setMATE_parte7(parte7 .getText().toString());
                    bateria.setMATE_especifica7(especifica7 .getText().toString());
                    bateria.setMATE_cantidad8(cantidad8 .getText().toString());
                    bateria.setMATE_parte8(parte8 .getText().toString());
                    bateria.setMATE_especifica8(especifica8 .getText().toString());
                }
                if(checkBoxNa2.isChecked()){
                    bateria.setMATE_equipo1(" ");
                    bateria.setMATE_nid1(" ");
                    bateria.setMATE_fecha1(" ");
                }
                else {
                    bateria.setMATE_equipo1(quipo1 .getText().toString());
                    bateria.setMATE_nid1(nid1 .getText().toString());
                    bateria.setMATE_fecha1(fecha1 .getText().toString());
                    bateria.setMATE_equipo2(quipo2 .getText().toString());
                    bateria.setMATE_nid2(nid2 .getText().toString());
                    bateria.setMATE_fecha2(fecha2 .getText().toString());
                    bateria.setMATE_equipo3(quipo3 .getText().toString());
                    bateria.setMATE_nid3(nid3 .getText().toString());
                    bateria.setMATE_fecha3(fecha3 .getText().toString());
                    bateria.setMATE_equipo4(quipo4 .getText().toString());
                    bateria.setMATE_nid4(nid4 .getText().toString());
                    bateria.setMATE_fecha4(fecha4 .getText().toString());
                    bateria.setMATE_equipo5(quipo5 .getText().toString());
                    bateria.setMATE_nid5(nid5 .getText().toString());
                    bateria.setMATE_fecha5(fecha5 .getText().toString());
                    bateria.setMATE_equipo6(quipo6 .getText().toString());
                    bateria.setMATE_nid6(nid6 .getText().toString());
                    bateria.setMATE_fecha6(fecha6 .getText().toString());
                }
                D_B.guardarBatrias(bateria,id_formato);

                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                BateriasMenuFragment myfargment = new BateriasMenuFragment();
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
