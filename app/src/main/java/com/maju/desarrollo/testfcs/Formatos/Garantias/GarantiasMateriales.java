package com.maju.desarrollo.testfcs.Formatos.Garantias;

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
import com.maju.desarrollo.testfcs.SQLite.Model.SGarantias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

public class GarantiasMateriales extends Fragment {
    TextView fecha1, fecha2, fecha3, fecha4, fecha5, fecha6;
    String id_formato;
    OperacionesDB D_B;
    SGarantias Formato;
    public GarantiasMateriales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_garantias_materiales, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerGarantia_id(id_formato);


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

        try{if(Formato.getMATERIALESUTILIZADOS().equals("N/A")){checkBoxNa1.setChecked(true);}}catch(Exception e){}
        try{if(Formato.getEQUIPOMEDICION().equals("N/A")){checkBoxNa2.setChecked(true);}}catch(Exception e){}

        try{cantidad1.setText(Formato.getCANTIDAD1());}catch (Exception e){}
        try{parte1.setText(Formato.getNPARTE1());}catch (Exception e){}
        try{especifica1.setText(Formato.getESPECIFICACION1());}catch (Exception e){}
        try{cantidad2.setText(Formato.getCANTIDAD2());}catch (Exception e){}
        try{parte2.setText(Formato.getNPARTE2());}catch (Exception e){}
        try{especifica2.setText(Formato.getESPECIFICACION2());}catch (Exception e){}
        try{cantidad3.setText(Formato.getCANTIDAD3());}catch (Exception e){}
        try{parte3.setText(Formato.getNPARTE3());}catch (Exception e){}
        try{especifica3.setText(Formato.getESPECIFICACION3());}catch (Exception e){}
        try{cantidad4.setText(Formato.getCANTIDAD4());}catch (Exception e){}
        try{parte4.setText(Formato.getNPARTE4());}catch (Exception e){}
        try{especifica4.setText(Formato.getESPECIFICACION4());}catch (Exception e){}
        try{cantidad5.setText(Formato.getCANTIDAD5());}catch (Exception e){}
        try{parte5.setText(Formato.getNPARTE5());}catch (Exception e){}
        try{especifica5.setText(Formato.getESPECIFICACION5());}catch (Exception e){}
        try{cantidad6.setText(Formato.getCANTIDAD6());}catch (Exception e){}
        try{parte6.setText(Formato.getNPARTE6());}catch (Exception e){}
        try{especifica6.setText(Formato.getESPECIFICACION6());}catch (Exception e){}
        try{cantidad7.setText(Formato.getCANTIDAD7());}catch (Exception e){}
        try{parte7.setText(Formato.getNPARTE7());}catch (Exception e){}
        try{especifica7.setText(Formato.getESPECIFICACION7());}catch (Exception e){}
        try{cantidad8.setText(Formato.getCANTIDAD8());}catch (Exception e){}
        try{parte8.setText(Formato.getNPARTE8());}catch (Exception e){}
        try{especifica8.setText(Formato.getESPECIFICACION8());}catch (Exception e){}
        try{quipo1.setText(Formato.getEQUIPO1());}catch (Exception e){}
        try{nid1.setText(Formato.getNoID1());}catch (Exception e){}
        try{fecha1.setText(Formato.getFECHA1());}catch (Exception e){}
        try{quipo2.setText(Formato.getEQUIPO2());}catch (Exception e){}
        try{nid2.setText(Formato.getNoID2());}catch (Exception e){}
        try{fecha2.setText(Formato.getFECHA2());}catch (Exception e){}
        try{quipo3.setText(Formato.getEQUIPO3());}catch (Exception e){}
        try{nid3.setText(Formato.getNoID3());}catch (Exception e){}
        try{fecha3.setText(Formato.getFECHA3());}catch (Exception e){}
        try{quipo4.setText(Formato.getEQUIPO4());}catch (Exception e){}
        try{nid4.setText(Formato.getNoID4());}catch (Exception e){}
        try{fecha4.setText(Formato.getFECHA4());}catch (Exception e){}
        try{quipo5.setText(Formato.getEQUIPO5());}catch (Exception e){}
        try{nid5.setText(Formato.getNoID5());}catch (Exception e){}
        try{fecha5.setText(Formato.getFECHA5());}catch (Exception e){}
       /* try{quipo6.setText(Formato.getE());}catch (Exception e){}
        try{nid6.setText(Formato.getNoID6());}catch (Exception e){}
        try{fecha6.setText(Formato.getFECHA6());}catch (Exception e){}

        */


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
                args.putString("OtraPantalla", "Garantias");
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
                    Formato.setMATERIALESUTILIZADOS("N/A");
                    Formato.setCANTIDAD1(" ");
                    Formato.setNPARTE1(" ");
                    Formato.setESPECIFICACION1(" ");
                }else {
                    Formato.setMATERIALESUTILIZADOS("");
                    Formato.setCANTIDAD1(cantidad1.getText().toString());
                    Formato.setNPARTE1(parte1.getText().toString());
                    Formato.setESPECIFICACION1(especifica1.getText().toString());
                    Formato.setCANTIDAD2(cantidad2.getText().toString());
                    Formato.setNPARTE2(parte2.getText().toString());
                    Formato.setESPECIFICACION2(especifica2.getText().toString());
                    Formato.setCANTIDAD3(cantidad3.getText().toString());
                    Formato.setNPARTE3(parte3.getText().toString());
                    Formato.setESPECIFICACION3(especifica3.getText().toString());
                    Formato.setCANTIDAD4(cantidad4.getText().toString());
                    Formato.setNPARTE4(parte4.getText().toString());
                    Formato.setESPECIFICACION4(especifica4.getText().toString());
                    Formato.setCANTIDAD5(cantidad5.getText().toString());
                    Formato.setNPARTE5(parte5.getText().toString());
                    Formato.setESPECIFICACION5(especifica5.getText().toString());
                    Formato.setCANTIDAD6(cantidad6.getText().toString());
                    Formato.setNPARTE6(parte6.getText().toString());
                    Formato.setESPECIFICACION6(especifica6.getText().toString());
                    Formato.setCANTIDAD7(cantidad7.getText().toString());
                    Formato.setNPARTE7(parte7.getText().toString());
                    Formato.setESPECIFICACION7(especifica7.getText().toString());
                    Formato.setCANTIDAD8(cantidad8.getText().toString());
                    Formato.setNPARTE8(parte8.getText().toString());
                    Formato.setESPECIFICACION8(especifica8.getText().toString());
                }
                if(checkBoxNa2.isChecked()){
                    Formato.setEQUIPOMEDICION("N/A");
                    Formato.setEQUIPO1(" ");
                    Formato.setNoID1(" ");
                    Formato.setFECHA1(" ");
                }
                else {
                    Formato.setEQUIPOMEDICION("");
                    Formato.setEQUIPO1(quipo1.getText().toString());
                    Formato.setNoID1(nid1.getText().toString());
                    Formato.setFECHA1(fecha1.getText().toString());
                    Formato.setEQUIPO2(quipo2.getText().toString());
                    Formato.setNoID2(nid2.getText().toString());
                    Formato.setFECHA2(fecha2.getText().toString());
                    Formato.setEQUIPO3(quipo3.getText().toString());
                    Formato.setNoID3(nid3.getText().toString());
                    Formato.setFECHA3(fecha3.getText().toString());
                    Formato.setEQUIPO4(quipo4.getText().toString());
                    Formato.setNoID4(nid4.getText().toString());
                    Formato.setFECHA4(fecha4.getText().toString());
                    Formato.setEQUIPO5(quipo5.getText().toString());
                    Formato.setNoID5(nid5.getText().toString());
                    Formato.setFECHA5(fecha5.getText().toString());

                }

                D_B.guardarGarantias(Formato);

                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                GarantiasMenu myfargment = new GarantiasMenu();
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
