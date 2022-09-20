package com.maju.desarrollo.testfcs.Formatos.Calidad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalidadEvaluacion extends Fragment {

    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6,spinner7,spinner8,spinner9,spinner10,spinner11,spinner12,spinner13,spinner14;
    EditText Comentario1,Comentario2,Comentario3,Comentario4;
    OperacionesDB D_B;
    String id_formato;
    EncuestaCalidadServicio Formato;
    String calificacion="",noAplicaV="0";

    public CalidadEvaluacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calidad_evaluacion, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        ((MainActivity) getActivity()).ocultarCabecera();

        //id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerCalidad(id_formato);


         Bundle valores = new Bundle();
        valores.putString("titulo","Encuesta de Calidad");
        valores.putString("mensaje","Si la respuesta a cualquiera de los conceptos es menor a 4 ayúdenos con sus comentarios para una mejora continua.");

        AlertaGenerica alerta = new AlertaGenerica();
            alerta.setArguments(valores);
            alerta.setCancelable(false);
            alerta.show(getFragmentManager(), "a");

            spinner1 = (Spinner)v.findViewById(R.id.spiner1);
        spinner1 = (Spinner)v.findViewById(R.id.spiner1);
        spinner2 = (Spinner)v.findViewById(R.id.spiner2);
        spinner3 = (Spinner)v.findViewById(R.id.spiner3);
        spinner4 = (Spinner)v.findViewById(R.id.spiner4);
        spinner5 = (Spinner)v.findViewById(R.id.spiner5);
        spinner6 = (Spinner)v.findViewById(R.id.spiner6);
        spinner7 = (Spinner)v.findViewById(R.id.spiner7);
        spinner8 = (Spinner)v.findViewById(R.id.spiner8);
        spinner9 = (Spinner)v.findViewById(R.id.spiner9);
        spinner10 = (Spinner)v.findViewById(R.id.spiner10);
        spinner11 = (Spinner)v.findViewById(R.id.spiner11);
        spinner12 = (Spinner)v.findViewById(R.id.spiner12);
        spinner13 = (Spinner)v.findViewById(R.id.spiner13);
        spinner14 = (Spinner)v.findViewById(R.id.spiner14);
        Comentario1 = (EditText)v.findViewById(R.id.comentario1);
        Comentario2 = (EditText)v.findViewById(R.id.comentario2);
        Comentario3 = (EditText)v.findViewById(R.id.comentario3);
        final RadioButton R1 = v.findViewById(R.id.radioButton1);
        final RadioButton R2 = v.findViewById(R.id.radioButton2);
        final RadioButton R3 = v.findViewById(R.id.radioButton3);
        final RadioButton R4 = v.findViewById(R.id.radioButton4);
        final RadioButton R5 = v.findViewById(R.id.radioButton5);
        final RadioButton R6 = v.findViewById(R.id.radioButton6);
        final RadioButton R7 = v.findViewById(R.id.radioButton7);
        final RadioButton R8 = v.findViewById(R.id.radioButton8);
        final RadioButton R9 = v.findViewById(R.id.radioButton9);
        final RadioButton R10 = v.findViewById(R.id.radioButton10);
        Comentario4 = v.findViewById(R.id.comentario4);
        final RadioGroup grupoRB = v.findViewById(R.id.grupoRB);
        CheckBox noAplica = v.findViewById(R.id.noAplica);
        final LinearLayout LY_CRC = v.findViewById(R.id.LY_CRC);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.calificacionesEva, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);
        spinner7.setAdapter(adapter);
        spinner8.setAdapter(adapter);
        spinner9.setAdapter(adapter);
        spinner10.setAdapter(adapter);
        spinner11.setAdapter(adapter);
        spinner12.setAdapter(adapter);
        spinner13.setAdapter(adapter);
        spinner14.setAdapter(adapter);


        try{spinner1.setSelection(adapter.getPosition(Formato.getI1()));}catch (Exception e){}
        try{spinner2.setSelection(adapter.getPosition(Formato.getI2()));}catch (Exception e){}
        try{spinner3.setSelection(adapter.getPosition(Formato.getI3()));}catch (Exception e){}
        try{spinner4.setSelection(adapter.getPosition(Formato.getII1()));}catch (Exception e){}
        try{spinner5.setSelection(adapter.getPosition(Formato.getII2()));}catch (Exception e){}
        try{spinner6.setSelection(adapter.getPosition(Formato.getII3()));}catch (Exception e){}
        try{spinner7.setSelection(adapter.getPosition(Formato.getII4()));}catch (Exception e){}
        try{spinner8.setSelection(adapter.getPosition(Formato.getIII1()));}catch (Exception e){}
        try{spinner9.setSelection(adapter.getPosition(Formato.getIII2()));}catch (Exception e){}
        try{spinner10.setSelection(adapter.getPosition(Formato.getIII3()));}catch (Exception e){}
        try{spinner11.setSelection(adapter.getPosition(Formato.getIII4()));}catch (Exception e){}
        try{spinner12.setSelection(adapter.getPosition(Formato.getCRC1()));}catch (Exception e){}
        try{spinner13.setSelection(adapter.getPosition(Formato.getCRC2()));}catch (Exception e){}
        try{spinner14.setSelection(adapter.getPosition(Formato.getCRC3()));}catch (Exception e){}
        try{calificacion = Formato.getRecomienda();}catch (Exception e){}
        try{
            calificacion = Formato.getRecomienda();

            switch (calificacion){
                case "1":
                    R1.setChecked(true);
                    break;
                case "2":
                    R2.setChecked(true);
                    break;
                case "3":
                    R3.setChecked(true);
                    break;
                case "4":
                    R4.setChecked(true);
                    break;
                case "5":
                    R5.setChecked(true);
                    break;
                case "6":
                    R6.setChecked(true);
                    break;
                case "7":
                    R7.setChecked(true);
                    break;
                case "8":
                    R8.setChecked(true);
                    break;
                case "9":
                    R9.setChecked(true);
                    break;
                case "10":
                    R10.setChecked(true);
                    break;

            }

        }catch (Exception e){}

       try {
           Comentario1.setText(Formato.getIComentarios());
           Comentario2.setText(Formato.getIIComentarios());
           Comentario3.setText(Formato.getIIIComentarios());
           Comentario4.setText(Formato.getCRCComentario());
       }catch (Exception e){}

        try{if(Formato.getCRCNA().equals("1"))
            noAplicaV = Formato.getCRCNA();}catch (Exception e){}

        try {
            if (noAplicaV.equals("1")) {
                noAplica.setChecked(true);
                LY_CRC.setVisibility(View.GONE);
            }
        }catch (Exception e){}

        grupoRB.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(R1.isChecked()){ calificacion = "1"; }
                if(R2.isChecked()){ calificacion = "2"; }
                if(R3.isChecked()){ calificacion = "3"; }
                if(R4.isChecked()){ calificacion = "4"; }
                if(R5.isChecked()){ calificacion = "5"; }
                if(R6.isChecked()){ calificacion = "6"; }
                if(R7.isChecked()){ calificacion = "7"; }
                if(R8.isChecked()){ calificacion = "8"; }
                if(R9.isChecked()){ calificacion = "9"; }
                if(R10.isChecked()){ calificacion = "10"; }
            }
        });
        noAplica.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    LY_CRC.setVisibility(View.GONE);
                    noAplicaV="1";
                }else{
                    LY_CRC.setVisibility(View.VISIBLE);
                    noAplicaV="0";
                }
            }
        });

        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Calidad");
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

                Formato.setI1(spinner1.getSelectedItem().toString());
                Formato.setI2(spinner2.getSelectedItem().toString());
                Formato.setI3(spinner3.getSelectedItem().toString());
                Formato.setII1(spinner4.getSelectedItem().toString());
                Formato.setII2(spinner5.getSelectedItem().toString());
                Formato.setII3(spinner6.getSelectedItem().toString());
                Formato.setII4(spinner7.getSelectedItem().toString());
                Formato.setIII1(spinner8.getSelectedItem().toString());
                Formato.setIII2(spinner9.getSelectedItem().toString());
                Formato.setIII3(spinner10.getSelectedItem().toString());
                Formato.setIII4(spinner11.getSelectedItem().toString());
                String comentarios = Comentario1.getText()+ "&&&"+Comentario2.getText()+"&&&"+Comentario3.getText();
                Formato.setIComentarios( Comentario1.getText().toString());
                Formato.setIIComentarios(Comentario2.getText().toString());
                Formato.setIIIComentarios(Comentario3.getText().toString());

                Formato.setRecomienda(calificacion);
                Formato.setCRCNA(noAplicaV);
                if(noAplicaV.equals("0")){
                    Formato.setCRC1(spinner12.getSelectedItem().toString());
                    Formato.setCRC2(spinner13.getSelectedItem().toString());
                    Formato.setCRC3(spinner14.getSelectedItem().toString());
                    Formato.setCRCComentario(Comentario4.getText().toString());
                }else{
                    Formato.setCRC1("");
                    Formato.setCRC2("");
                    Formato.setCRC3("");
                    Formato.setCRCComentario("");
                }


                D_B.guardarCalidad(Formato);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                CalidadMenu myfargment = new CalidadMenu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


         return v;
    }

}
