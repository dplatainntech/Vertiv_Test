package com.maju.desarrollo.testfcs.Formatos.IN1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN1Firmas extends Fragment {
    OperacionesDB D_B;
    String id_formato,firma;;
    Bestel1 formato;

    public IN1Firmas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =   inflater.inflate(R.layout.fragment_in1_firmas, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato = getArguments().getString("key_idFormato");

        formato = D_B.obtenerBestel1(id_formato);

        ImageView F1 = (ImageView)v.findViewById(R.id.cbtnFirma1);
        ImageView F2 = (ImageView)v.findViewById(R.id.cbtnFirma2);
        final EditText vertivCE = (EditText)v.findViewById(R.id.nombreFirma1);
        final EditText cliente = (EditText)v.findViewById(R.id.nombreFirma2);

        try{ if(formato.getFirmaVertiv().length()>10){ F1.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}
        try{ if(formato.getFirmaCliente().length()>10){ F2.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}

        vertivCE.setText(formato.getNombreFirmaVertiv());
        cliente.setText(formato.getNombreFirmaCliente());

        F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formato.setNombreFirmaVertiv(vertivCE.getText().toString());
                D_B.guardarBestel1(formato);


                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma1");

                IN1Lienzo myfargment = new IN1Lienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });


        F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                formato.setNombreFirmaCliente(cliente.getText().toString());
                D_B.guardarBestel1(formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma2");


                IN1Lienzo myfargment = new IN1Lienzo();
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
                args.putString("OtraPantalla", "IN1");
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
                formato.setNombreFirmaVertiv(vertivCE.getText().toString());
                formato.setNombreFirmaCliente(cliente.getText().toString());
                D_B.guardarBestel1(formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                IN1Menu myfargment = new IN1Menu();
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
