package com.maju.desarrollo.testfcs.Formatos.Baterias;


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
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriasFirmas extends Fragment {
    Spinner spinner1,spinner2,spinner3;
    OperacionesDB D_B;
    String id_formato;
    Baterias baterias;
    ImageView imf1,imf2,imf3;
    InternetandVPN validaciones = new InternetandVPN();


    public BateriasFirmas() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_baterias_firmas, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        baterias = D_B.obtenerBateriaF_id(id_formato);



        final EditText nombreFirma1 = (EditText)v.findViewById(R.id.nombreFirma1);
        final EditText nombreFirma2 = (EditText)v.findViewById(R.id.nombreFirma2);
        final EditText nombreFirma3 = (EditText)v.findViewById(R.id.nombreFirma3);

        final EditText empresaF = (EditText)v.findViewById(R.id.empresa);
        final EditText telefonoF = (EditText)v.findViewById(R.id.telefono);
        final EditText correoF = (EditText)v.findViewById(R.id.correo);
        ImageView F1 = (ImageView)v.findViewById(R.id.cbtnFirma1);
        ImageView F2 = (ImageView)v.findViewById(R.id.cbtnFirma2);
        ImageView F3 = (ImageView)v.findViewById(R.id.cbtnFirma3);






        try{ nombreFirma1.setText(baterias.getFIRMA_NOMBRE1()); }catch (Exception e){}
        try{ nombreFirma2.setText(baterias.getFIRMA_NOMBRE2()); }catch (Exception e){}
        try{ nombreFirma3.setText(baterias.getFIRMA_NOMBRE3()); }catch (Exception e){}

        try{ empresaF.setText(baterias.getFIRMA_EMPRESA()); }catch (Exception e){}
        try{ telefonoF.setText(baterias.getFIRMA_TELEFONO()); }catch (Exception e){}
        try{ correoF.setText(baterias.getFIRMA_CORREO()); }catch (Exception e){}

        try{ if(baterias.getFIRMA_IMG1().length()>10){ F1.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}
        try{ if(baterias.getFIRMA_IMG2().length()>10){ F2.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}
        try{ if(baterias.getFIRMA_IMG3().length()>10){ F3.setImageResource(R.drawable.btn_firma_verde); } }catch (Exception e){}


        //region Botones

        F1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baterias.setFIRMA_NOMBRE1(nombreFirma1.getText().toString());
                baterias.setFIRMA_NOMBRE2(nombreFirma2.getText().toString());
                baterias.setFIRMA_NOMBRE3(nombreFirma3.getText().toString());
                baterias.setFIRMA_EMPRESA(empresaF.getText().toString());
                baterias.setFIRMA_TELEFONO(telefonoF.getText().toString());
                baterias.setFIRMA_CORREO(correoF.getText().toString());
                D_B.guardarBatrias(baterias,id_formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma1");

                BateriasLienzo myfargment = new BateriasLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });


        F2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baterias.setFIRMA_NOMBRE1(nombreFirma1.getText().toString());
                baterias.setFIRMA_NOMBRE2(nombreFirma2.getText().toString());
                baterias.setFIRMA_NOMBRE3(nombreFirma3.getText().toString());
                baterias.setFIRMA_EMPRESA(empresaF.getText().toString());
                baterias.setFIRMA_TELEFONO(telefonoF.getText().toString());
                baterias.setFIRMA_CORREO(correoF.getText().toString());
                D_B.guardarBatrias(baterias,id_formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma2");

                BateriasLienzo myfargment = new BateriasLienzo();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });


        F3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                baterias.setFIRMA_NOMBRE1(nombreFirma1.getText().toString());
                baterias.setFIRMA_NOMBRE2(nombreFirma2.getText().toString());
                baterias.setFIRMA_NOMBRE3(nombreFirma3.getText().toString());
                baterias.setFIRMA_EMPRESA(empresaF.getText().toString());
                baterias.setFIRMA_TELEFONO(telefonoF.getText().toString());
                baterias.setFIRMA_CORREO(correoF.getText().toString());
                D_B.guardarBatrias(baterias,id_formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                args.putString("Firma_aGuardar", "Firma3");

                BateriasLienzo myfargment = new BateriasLienzo();
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

                baterias.setFIRMA_NOMBRE1(nombreFirma1.getText().toString());
                baterias.setFIRMA_NOMBRE2(nombreFirma2.getText().toString());
                baterias.setFIRMA_NOMBRE3(nombreFirma3.getText().toString());

                baterias.setFIRMA_EMPRESA(empresaF.getText().toString());
                baterias.setFIRMA_TELEFONO(telefonoF.getText().toString());
                baterias.setFIRMA_CORREO(correoF.getText().toString());


                D_B.guardarBatrias(baterias,id_formato);

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

        correoF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correoF.setTextColor(Color.parseColor("#000000"));
                }else{
                    correoF.setTextColor(Color.parseColor("#FF0000"));
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
