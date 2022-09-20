package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class POFirmasFragment extends Fragment {
    Spinner spinner1,spinner2,spinner3,spinner4,spinner5,spinner6;
    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;
    EditText Nombre1,Nombre2,Nombre3,Nombre4,Nombre5,Nombre6;

    public POFirmasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pofirmas, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);

        spinner1 = (Spinner) v.findViewById(R.id.spinerFirma1);
        spinner2 = (Spinner) v.findViewById(R.id.spinerFirma2);
        spinner3 = (Spinner) v.findViewById(R.id.spinerFirma3);
        spinner4 = (Spinner) v.findViewById(R.id.spinerFirma4);
        spinner5 = (Spinner) v.findViewById(R.id.spinerFirma5);
        spinner6 = (Spinner) v.findViewById(R.id.spinerFirma6);
        Nombre1 = (EditText) v.findViewById(R.id.nombreFirma1);
        Nombre2 = (EditText) v.findViewById(R.id.nombreFirma2);
        Nombre3 = (EditText) v.findViewById(R.id.nombreFirma3);
        Nombre4 = (EditText) v.findViewById(R.id.nombreFirma4);
        Nombre5 = (EditText) v.findViewById(R.id.nombreFirma5);
        Nombre6 = (EditText) v.findViewById(R.id.nombreFirma6);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.firma_PT, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
        spinner4.setAdapter(adapter);
        spinner5.setAdapter(adapter);
        spinner6.setAdapter(adapter);

        try{Nombre1.setText(preOrden.getFIRMA_1_NOMBRE());}catch (Exception e){}
        try{Nombre2.setText(preOrden.getFIRMA_2_NOMBRE());}catch (Exception e){}
        try{Nombre3.setText(preOrden.getFIRMA_3_NOMBRE());}catch (Exception e){}
        try{Nombre4.setText(preOrden.getFIRMA_4_NOMBRE());}catch (Exception e){}
        try{Nombre5.setText(preOrden.getFIRMA_5_NOMBRE());}catch (Exception e){}
        try{Nombre6.setText(preOrden.getFIRMA_6_NOMBRE());}catch (Exception e){}

        try{spinner1.setSelection(adapter.getPosition(preOrden.getFIRMA_1_CARGO()));}catch (Exception e){}
        try{spinner2.setSelection(adapter.getPosition(preOrden.getFIRMA_2_CARGO()));}catch (Exception e){}
        try{spinner3.setSelection(adapter.getPosition(preOrden.getFIRMA_3_CARGO()));}catch (Exception e){}
        try{spinner4.setSelection(adapter.getPosition(preOrden.getFIRMA_4_CARGO()));}catch (Exception e){}
        try{spinner5.setSelection(adapter.getPosition(preOrden.getFIRMA_5_CARGO()));}catch (Exception e){}
        try{spinner6.setSelection(adapter.getPosition(preOrden.getFIRMA_6_CARGO()));}catch (Exception e){}



        //region Botones
        ImageButton F1 = (ImageButton)v.findViewById(R.id.btnFirma1);
        F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preOrden.setFIRMA_1_NOMBRE(Nombre1.getText().toString());
                preOrden.setFIRMA_1_CARGO(spinner1.getSelectedItem().toString());
                guardarDatos();

                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma1");
                startActivity(siguiente2);
            }
        });

        ImageButton F2 = (ImageButton)v.findViewById(R.id.btnFirma2);
        F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preOrden.setFIRMA_2_NOMBRE(Nombre2.getText().toString());
                preOrden.setFIRMA_2_CARGO(spinner2.getSelectedItem().toString());
                guardarDatos();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma2");
                startActivity(siguiente2);
            }
        });

        ImageButton F3 = (ImageButton)v.findViewById(R.id.btnFirma3);
        F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preOrden.setFIRMA_3_NOMBRE(Nombre3.getText().toString());
                preOrden.setFIRMA_3_CARGO(spinner3.getSelectedItem().toString());
                guardarDatos();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma3");
                startActivity(siguiente2);
            }
        });

        ImageButton F4 = (ImageButton)v.findViewById(R.id.btnFirma4);
        F4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preOrden.setFIRMA_4_NOMBRE(Nombre4.getText().toString());
                preOrden.setFIRMA_4_CARGO(spinner4.getSelectedItem().toString());
                guardarDatos();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma4");
                startActivity(siguiente2);
            }
        });

        ImageButton F5 = (ImageButton)v.findViewById(R.id.btnFirma5);
        F5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preOrden.setFIRMA_5_NOMBRE(Nombre5.getText().toString());
                preOrden.setFIRMA_5_CARGO(spinner5.getSelectedItem().toString());
                guardarDatos();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma5");
                startActivity(siguiente2);
            }
        });

        ImageButton F6 = (ImageButton)v.findViewById(R.id.btnFirma6);
        F6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preOrden.setFIRMA_6_NOMBRE(Nombre6.getText().toString());
                preOrden.setFIRMA_6_CARGO(spinner6.getSelectedItem().toString());
                guardarDatos();
                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "AreaFirmas");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                siguiente2.putExtra("Firma_aGuardar", "Firma6");
                startActivity(siguiente2);
            }
        });


        Button cancelar = (Button)v.findViewById(R.id.firmas_btn_Cancelar);
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
                dialog.setTargetFragment(POFirmasFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.firmas_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());
                preOrden.setFIRMA_1_NOMBRE(Nombre1.getText().toString());
                preOrden.setFIRMA_1_CARGO(spinner1.getSelectedItem().toString());
                preOrden.setFIRMA_2_NOMBRE(Nombre2.getText().toString());
                preOrden.setFIRMA_2_CARGO(spinner2.getSelectedItem().toString());
                preOrden.setFIRMA_3_NOMBRE(Nombre3.getText().toString());
                preOrden.setFIRMA_3_CARGO(spinner3.getSelectedItem().toString());
                preOrden.setFIRMA_4_NOMBRE(Nombre4.getText().toString());
                preOrden.setFIRMA_4_CARGO(spinner4.getSelectedItem().toString());
                preOrden.setFIRMA_5_NOMBRE(Nombre5.getText().toString());
                preOrden.setFIRMA_5_CARGO(spinner5.getSelectedItem().toString());
                preOrden.setFIRMA_6_NOMBRE(Nombre6.getText().toString());
                preOrden.setFIRMA_6_CARGO(spinner6.getSelectedItem().toString());


                D_B.guardarPreOrden(preOrden);
                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                siguiente.putExtra("valorPaso", preOrden.getFOLIO());
                startActivity(siguiente);

            }
        });

        //endregion

        try{if(!preOrden.getFIRMA_1_IMAGEN().isEmpty()){F1.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_2_IMAGEN().isEmpty()){F2.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_3_IMAGEN().isEmpty()){F3.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_4_IMAGEN().isEmpty()){F4.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_5_IMAGEN().isEmpty()){F5.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}
        try{if(!preOrden.getFIRMA_6_IMAGEN().isEmpty()){F6.setImageResource(R.drawable.btn_firma_verde);}}catch (Exception e){}

        return v;
    }

    public void guardarDatos(){
        D_B.guardarPreOrden(preOrden);
    }

}
