package com.maju.desarrollo.testfcs.Formatos.PDU;

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
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.PDU;


public class PDUMateriales extends Fragment {
    TextView FechaVencimiento1, FechaVencimiento2, FechaVencimiento3, FechaVencimiento4, FechaVencimiento5, FechaVencimiento6;
    String id_formato;
    OperacionesDB D_B;
    PDU Formato;
    public PDUMateriales() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pdumateriales, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerPDU(id_formato);
        final CheckBox checkBoxNa1 = (CheckBox)v.findViewById(R.id.checkBoxNa1);
        final CheckBox checkBoxNa2 = (CheckBox)v.findViewById(R.id.checkBoxNa2);

        final LinearLayout layout1 = (LinearLayout)v.findViewById(R.id.layout1);
        final LinearLayout layout2 = (LinearLayout)v.findViewById(R.id.layout2);

        final EditText Cantidad1 = (EditText)v.findViewById(R.id.cantidad1);
        final EditText parte1= (EditText)v.findViewById(R.id.parte1);
        final EditText especifica1 = (EditText)v.findViewById(R.id.especifica1);
        final EditText Cantidad2 = (EditText)v.findViewById(R.id.cantidad2);
        final EditText parte2 = (EditText)v.findViewById(R.id.parte2);
        final EditText especifica2 = (EditText)v.findViewById(R.id.especifica2);
        final EditText Cantidad3 = (EditText)v.findViewById(R.id.cantidad3);
        final EditText parte3 = (EditText)v.findViewById(R.id.parte3);
        final EditText especifica3 = (EditText)v.findViewById(R.id.especifica3);
        final EditText Cantidad4 = (EditText)v.findViewById(R.id.cantidad4);
        final EditText parte4 = (EditText)v.findViewById(R.id.parte4);
        final EditText especifica4 = (EditText)v.findViewById(R.id.especifica4);
        final EditText Cantidad5 = (EditText)v.findViewById(R.id.cantidad5);
        final EditText parte5 = (EditText)v.findViewById(R.id.parte5);
        final EditText especifica5 = (EditText)v.findViewById(R.id.especifica5);
        final EditText Cantidad6 = (EditText)v.findViewById(R.id.cantidad6);
        final EditText parte6 = (EditText)v.findViewById(R.id.parte6);
        final EditText especifica6 = (EditText)v.findViewById(R.id.especifica6);
        final EditText Cantidad7 = (EditText)v.findViewById(R.id.cantidad7);
        final EditText parte7 = (EditText)v.findViewById(R.id.parte7);
        final EditText especifica7 = (EditText)v.findViewById(R.id.especifica7);
        final EditText Cantidad8 = (EditText)v.findViewById(R.id.cantidad8);
        final EditText parte8 = (EditText)v.findViewById(R.id.parte8);
        final EditText especifica8 = (EditText)v.findViewById(R.id.especifica8);
        final EditText quipo1 = (EditText)v.findViewById(R.id.quipo1);
        final EditText nid1 = (EditText)v.findViewById(R.id.nid1);
        FechaVencimiento1 = (TextView) v.findViewById(R.id.fecha1);
        final EditText quipo2 = (EditText)v.findViewById(R.id.quipo2);
        final EditText nid2 = (EditText)v.findViewById(R.id.nid2);
        FechaVencimiento2 = (TextView)v.findViewById(R.id.fecha2);
        final EditText quipo3 = (EditText)v.findViewById(R.id.quipo3);
        final EditText nid3 = (EditText)v.findViewById(R.id.nid3);
        FechaVencimiento3 = (TextView)v.findViewById(R.id.fecha3);
        final EditText quipo4 = (EditText)v.findViewById(R.id.quipo4);
        final EditText nid4 = (EditText)v.findViewById(R.id.nid4);
        FechaVencimiento4 = (TextView)v.findViewById(R.id.fecha4);
        final EditText quipo5 = (EditText)v.findViewById(R.id.quipo5);
        final EditText nid5 = (EditText)v.findViewById(R.id.nid5);
        FechaVencimiento5 = (TextView)v.findViewById(R.id.fecha5);

        final EditText quipo6 = (EditText)v.findViewById(R.id.quipo6);
        final EditText nid6 = (EditText)v.findViewById(R.id.nid6);
        FechaVencimiento6 = (TextView)v.findViewById(R.id.fecha6);




        try{if(
            Formato.getCantidad1().equals(" ") &&
            Formato.getNoParte1().equals(" ") &&
            Formato.getDescripcion1().equals(" ")
        ){
            checkBoxNa1.setChecked(true);
        }}catch (Exception e){}
        try{if(
            Formato.getEquipo1().equals(" ") &&
            Formato.getNoId1().equals(" ") &&
            Formato.getFechaVencimiento1().equals(" ")
        ){
            checkBoxNa2.setChecked(true);
        }}catch (Exception e){}

        try{Cantidad1.setText(Formato.getCantidad1());}catch (Exception e){}
        try{parte1.setText(Formato.getNoParte1());}catch (Exception e){}
        try{especifica1.setText(Formato.getDescripcion1());}catch (Exception e){}
        try{Cantidad2.setText(Formato.getCantidad2());}catch (Exception e){}
        try{parte2.setText(Formato.getNoParte2());}catch (Exception e){}
        try{especifica2.setText(Formato.getDescripcion2());}catch (Exception e){}
        try{Cantidad3.setText(Formato.getCantidad3());}catch (Exception e){}
        try{parte3.setText(Formato.getNoParte3());}catch (Exception e){}
        try{especifica3.setText(Formato.getDescripcion3());}catch (Exception e){}
        try{Cantidad4.setText(Formato.getCantidad4());}catch (Exception e){}
        try{parte4.setText(Formato.getNoParte4());}catch (Exception e){}
        try{especifica4.setText(Formato.getDescripcion4());}catch (Exception e){}
        try{Cantidad5.setText(Formato.getCantidad5());}catch (Exception e){}
        try{parte5.setText(Formato.getNoParte5());}catch (Exception e){}
        try{especifica5.setText(Formato.getDescripcion5());}catch (Exception e){}
        try{Cantidad6.setText(Formato.getCantidad6());}catch (Exception e){}
        try{parte6.setText(Formato.getNoParte6());}catch (Exception e){}
        try{especifica6.setText(Formato.getDescripcion6());}catch (Exception e){}
        try{Cantidad7.setText(Formato.getCantidad7());}catch (Exception e){}
        try{parte7.setText(Formato.getNoParte7());}catch (Exception e){}
        try{especifica7.setText(Formato.getDescripcion7());}catch (Exception e){}
        try{Cantidad8.setText(Formato.getCantidad8());}catch (Exception e){}
        try{parte8.setText(Formato.getNoParte8());}catch (Exception e){}
        try{especifica8.setText(Formato.getDescripcion8());}catch (Exception e){}
        try{quipo1.setText(Formato.getEquipo1());}catch (Exception e){}
        try{nid1.setText(Formato.getNoId1());}catch (Exception e){}
        try{FechaVencimiento1.setText(Formato.getFechaVencimiento1());}catch (Exception e){}
        try{quipo2.setText(Formato.getEquipo2());}catch (Exception e){}
        try{nid2.setText(Formato.getNoId2());}catch (Exception e){}
        try{FechaVencimiento2.setText(Formato.getFechaVencimiento2());}catch (Exception e){}
        try{quipo3.setText(Formato.getEquipo3());}catch (Exception e){}
        try{nid3.setText(Formato.getNoId3());}catch (Exception e){}
        try{FechaVencimiento3.setText(Formato.getFechaVencimiento3());}catch (Exception e){}
        try{quipo4.setText(Formato.getEquipo4());}catch (Exception e){}
        try{nid4.setText(Formato.getNoId4());}catch (Exception e){}
        try{FechaVencimiento4.setText(Formato.getFechaVencimiento4());}catch (Exception e){}
        try{quipo5.setText(Formato.getEquipo5());}catch (Exception e){}
        try{nid5.setText(Formato.getNoId5());}catch (Exception e){}
        try{FechaVencimiento5.setText(Formato.getFechaVencimiento5());}catch (Exception e){}
        try{quipo6.setText(Formato.getEquipo6());}catch (Exception e){}
        try{nid6.setText(Formato.getNoId6());}catch (Exception e){}
        try{FechaVencimiento6.setText(Formato.getFechaVencimiento6());}catch (Exception e){}


        ImageView btnFechaVencimiento1 = (ImageView)v.findViewById(R.id.btnfecha1);
        ImageView btnFechaVencimiento2 = (ImageView)v.findViewById(R.id.btnfecha2);
        ImageView btnFechaVencimiento3 = (ImageView)v.findViewById(R.id.btnfecha3);
        ImageView btnFechaVencimiento4 = (ImageView)v.findViewById(R.id.btnfecha4);
        ImageView btnFechaVencimiento5 = (ImageView)v.findViewById(R.id.btnfecha5);
        ImageView btnFechaVencimiento6 = (ImageView)v.findViewById(R.id.btnfecha6);

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


        btnFechaVencimiento1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("1");
                //endregion
            }
        });
        btnFechaVencimiento2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("2");
                //endregion
            }
        });
        btnFechaVencimiento3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("3");
                //endregion
            }
        });
        btnFechaVencimiento4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("4");
                //endregion
            }
        });
        btnFechaVencimiento5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                showDatePickerDialog1("5");
                //endregion
            }
        });
        btnFechaVencimiento6.setOnClickListener(new View.OnClickListener() {
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
                //No se guardan valores
                if(checkBoxNa1.isChecked()){
                    Formato.setMATERIALESUTILIZADOS("N/A");
                    Formato.setCantidad1(" ");
                    Formato.setNoParte1(" ");
                    Formato.setDescripcion1(" ");
                }
                else {
                    Formato.setMATERIALESUTILIZADOS("");
                    Formato.setCantidad1("");
                    Formato.setNoParte1("");
                    Formato.setDescripcion1("");

                    Formato.setCantidad1(Cantidad1.getText().toString());
                    Formato.setNoParte1(parte1.getText().toString());
                    Formato.setDescripcion1(especifica1.getText().toString());
                    Formato.setCantidad2(Cantidad2.getText().toString());
                    Formato.setNoParte2(parte2.getText().toString());
                    Formato.setDescripcion2(especifica2.getText().toString());
                    Formato.setCantidad3(Cantidad3.getText().toString());
                    Formato.setNoParte3(parte3.getText().toString());
                    Formato.setDescripcion3(especifica3.getText().toString());
                    Formato.setCantidad4(Cantidad4.getText().toString());
                    Formato.setNoParte4(parte4.getText().toString());
                    Formato.setDescripcion4(especifica4.getText().toString());
                    Formato.setCantidad5(Cantidad5.getText().toString());
                    Formato.setNoParte5(parte5.getText().toString());
                    Formato.setDescripcion5(especifica5.getText().toString());
                    Formato.setCantidad6(Cantidad6.getText().toString());
                    Formato.setNoParte6(parte6.getText().toString());
                    Formato.setDescripcion6(especifica6.getText().toString());
                    Formato.setCantidad7(Cantidad7.getText().toString());
                    Formato.setNoParte7(parte7.getText().toString());
                    Formato.setDescripcion7(especifica7.getText().toString());
                    Formato.setCantidad8(Cantidad8.getText().toString());
                    Formato.setNoParte8(parte8.getText().toString());
                    Formato.setDescripcion8(especifica8.getText().toString());
                }
                if(checkBoxNa2.isChecked()){
                    Formato.setEquipoMEDICION("N/A");
                    Formato.setEquipo1(" ");
                    Formato.setNoId1(" ");
                    Formato.setFechaVencimiento1(" ");
                }
                else {
                    Formato.setEquipoMEDICION("");
                    Formato.setEquipo1("");
                    Formato.setNoId1("");
                    Formato.setFechaVencimiento1("");
                    Formato.setEquipo1(quipo1.getText().toString());
                    Formato.setNoId1(nid1.getText().toString());
                    Formato.setFechaVencimiento1(FechaVencimiento1.getText().toString());
                    Formato.setEquipo2(quipo2.getText().toString());
                    Formato.setNoId2(nid2.getText().toString());
                    Formato.setFechaVencimiento2(FechaVencimiento2.getText().toString());
                    Formato.setEquipo3(quipo3.getText().toString());
                    Formato.setNoId3(nid3.getText().toString());
                    Formato.setFechaVencimiento3(FechaVencimiento3.getText().toString());
                    Formato.setEquipo4(quipo4.getText().toString());
                    Formato.setNoId4(nid4.getText().toString());
                    Formato.setFechaVencimiento4(FechaVencimiento4.getText().toString());
                    Formato.setEquipo5(quipo5.getText().toString());
                    Formato.setNoId5(nid5.getText().toString());
                    Formato.setFechaVencimiento5(FechaVencimiento5.getText().toString());
                    Formato.setEquipo6(quipo6.getText().toString());
                    Formato.setNoId6(nid6.getText().toString());
                    Formato.setFechaVencimiento6(FechaVencimiento6.getText().toString());
                }

                D_B.guardarPDU(Formato);

                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
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


    private void showDatePickerDialog1(final String campoFechaVencimiento ) {
        final DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;

                if(campoFechaVencimiento.equals("1")){FechaVencimiento1.setText(selectedDate);}
                if(campoFechaVencimiento.equals("2")){FechaVencimiento2.setText(selectedDate);}
                if(campoFechaVencimiento.equals("3")){FechaVencimiento3.setText(selectedDate);}
                if(campoFechaVencimiento.equals("4")){FechaVencimiento4.setText(selectedDate);}
                if(campoFechaVencimiento.equals("5")){FechaVencimiento5.setText(selectedDate);}
                if(campoFechaVencimiento.equals("6")){FechaVencimiento6.setText(selectedDate);}

            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }


}
