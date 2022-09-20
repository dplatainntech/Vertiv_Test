package com.maju.desarrollo.testfcs.Formatos.Calidad;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalidadFirmas extends Fragment {

    Spinner spinner1,spinner2,spinner3;
    OperacionesDB D_B;
    String id_formato;
    EncuestaCalidadServicio Formato;
    EditText Nombre1,Nombre2,Nombre3;

    public CalidadFirmas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_calidad_firmas, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");

        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerCalidad(id_formato);

        Nombre1 = (EditText) v.findViewById(R.id.nombreFirma1);
        Nombre2 = (EditText) v.findViewById(R.id.nombreFirma2);
        Nombre3 = (EditText) v.findViewById(R.id.nombreFirma3);

        try{Nombre1.setText(Formato.getFNombreVertiv());}catch (Exception e){}
        try{Nombre2.setText(Formato.getFNombreCliente());}catch (Exception e){}
        try{Nombre3.setText(Formato.getFNombreClienteFinal());}catch (Exception e){}


        //region Botones
        ImageButton F1 = (ImageButton)v.findViewById(R.id.cbtnFirma1);
        F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formato.setFNombreVertiv(Nombre1.getText().toString());
                D_B.guardarCalidad(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma1");

                CalidadLienzo myfargment = new CalidadLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

        ImageButton F2 = (ImageButton)v.findViewById(R.id.cbtnFirma2);
        F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formato.setFNombreCliente(Nombre2.getText().toString());
                D_B.guardarCalidad(Formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma2");

                CalidadLienzo myfargment = new CalidadLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

        ImageButton F3 = (ImageButton)v.findViewById(R.id.cbtnFirma3);
        F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Formato.setFNombreClienteFinal(Nombre3.getText().toString());
                D_B.guardarCalidad(Formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma3");

                CalidadLienzo myfargment = new CalidadLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
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

                //D_B.guardarPower1(dcPower);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();
                Formato.setFNombreVertiv(Nombre1.getText().toString());
                Formato.setFNombreCliente(Nombre2.getText().toString());
                Formato.setFNombreClienteFinal(Nombre3.getText().toString());
                D_B.guardarCalidad(Formato);

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

        try{if(!Formato.getFirmaVertiv().isEmpty()){
            if(!Formato.getFNombreVertiv().isEmpty()) {
                F1.setImageResource(R.drawable.btn_firma_verde);
            }else{
                F1.setImageResource(R.drawable.btn_firma_anaranjado);
            }
        }
        }catch (Exception e){}
        try{if(!Formato.getFirmaCliente().isEmpty()){
            if(!Formato.getFNombreCliente().isEmpty()) {
                F2.setImageResource(R.drawable.btn_firma_verde);
            }else{
                F2.setImageResource(R.drawable.btn_firma_anaranjado);
            }
        }
        }catch (Exception e){}
        try{if(!Formato.getFirmaClienteFinal().isEmpty()){
            if(!Formato.getFNombreClienteFinal().isEmpty()) {
                F3.setImageResource(R.drawable.btn_firma_verde);
            }else{
                F3.setImageResource(R.drawable.btn_firma_anaranjado);
            }
        }
        }catch (Exception e){}




        return v;
    }

}
