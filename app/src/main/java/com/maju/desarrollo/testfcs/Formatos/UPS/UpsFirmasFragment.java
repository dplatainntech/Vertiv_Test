package com.maju.desarrollo.testfcs.Formatos.UPS;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpsFirmasFragment extends Fragment {
    Spinner spinner1,spinner2,spinner3;
    OperacionesDB D_B;
    String id_formato;
    UPS Formato;
    InternetandVPN validaciones = new InternetandVPN();

    public UpsFirmasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_ups_firmas, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerUPS(id_formato);


       /* spinner1 = (Spinner) v.findViewById(R.id.spinerFirma1);
        spinner2 = (Spinner) v.findViewById(R.id.spinerFirma2);
        spinner3 = (Spinner) v.findViewById(R.id.spinerFirma3);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.firma_Clente, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);
*/
        final EditText nombreFirma1 = (EditText)v.findViewById(R.id.nombreFirma1);
        final EditText nombreFirma2 = (EditText)v.findViewById(R.id.nombreFirma2);
        final EditText nombreFirma3 = (EditText)v.findViewById(R.id.nombreFirma3);

        final EditText empresa = (EditText)v.findViewById(R.id.empresa);
        final EditText telefono = (EditText)v.findViewById(R.id.telefono);
        final EditText correo = (EditText)v.findViewById(R.id.correo);

        nombreFirma1.setText(Formato.getNombreFirmaCliente());
        nombreFirma2.setText(Formato.getNombreFirmaVertiv());
        nombreFirma3.setText(Formato.getNombreFirmaClienteFinal());
        empresa.setText(Formato.getCLIENTEFINAL_EMPRESA());
        telefono.setText(Formato.getCLIENTEFINAL_TELEFONO());
        correo.setText(Formato.getCLIENTEFINAL_CORREO());


        ImageView F1 = (ImageView)v.findViewById(R.id.cbtnFirma1);
        ImageView F2 = (ImageView)v.findViewById(R.id.cbtnFirma2);
        ImageView F3 = (ImageView)v.findViewById(R.id.cbtnFirma3);
        try{ if(Formato.getFirmaCliente().length()>10){ F1.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}
        try{ if(Formato.getFirmaVertiv().length()>10){ F2.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}
        try{ if(Formato.getFirmaClienteFinal().length()>10){ F3.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}

        //nombreFirma1.setText(Formato);

        //region Botones
        F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma1");

                Formato.setNombreFirmaCliente(nombreFirma1.getText().toString());
                Formato.setNombreFirmaVertiv(nombreFirma2.getText().toString());
                Formato.setNombreFirmaClienteFinal(nombreFirma3.getText().toString());

                D_B.guardarUPS(Formato);

                UPSLienzo myfargment = new UPSLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });


        F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma2");
                Formato.setNombreFirmaCliente(nombreFirma1.getText().toString());
                Formato.setNombreFirmaVertiv(nombreFirma2.getText().toString());
                Formato.setNombreFirmaClienteFinal(nombreFirma3.getText().toString());

                D_B.guardarUPS(Formato);

                UPSLienzo myfargment = new UPSLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });


        F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma3");
                Formato.setNombreFirmaCliente(nombreFirma1.getText().toString());
                Formato.setNombreFirmaVertiv(nombreFirma2.getText().toString());
                Formato.setNombreFirmaClienteFinal(nombreFirma3.getText().toString());

                D_B.guardarUPS(Formato);
                UPSLienzo myfargment = new UPSLienzo();
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
                args.putString("OtraPantalla", "UPS");
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
                //Formato.setFIRMA1(nombreFirma1.getText().toString());
                //Formato.setFIRMA2(nombreFirma2.getText().toString());
                //Formato.setFIRMA13(nombreFirma3.getText().toString());
                Formato.setNombreFirmaCliente(nombreFirma1.getText().toString());
                Formato.setNombreFirmaVertiv(nombreFirma2.getText().toString());
                Formato.setNombreFirmaClienteFinal(nombreFirma3.getText().toString());

                Formato.setCLIENTEFINAL_EMPRESA(empresa.getText().toString());
                Formato.setCLIENTEFINAL_TELEFONO(telefono.getText().toString());
                Formato.setCLIENTEFINAL_CORREO(correo.getText().toString());

                D_B.guardarUPS(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                UpsMenuFragment myfargment = new UpsMenuFragment();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo.setTextColor(Color.parseColor("#FF0000"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if(s.length()>0)
                    clearBox.setVisibility(View.VISIBLE);
                else
                    clearBox.setVisibility(View.GONE); */
            }
        });

        return v;
    }


}
