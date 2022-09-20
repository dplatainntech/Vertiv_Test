package com.maju.desarrollo.testfcs.Formatos.DCPower;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerAdicionalesFragment extends Fragment {
    Spinner spinner1,spinner2,spinner3;
    OperacionesDB D_B;
    String id_formato;
    DCPower2 baterias;
    ImageView imf1,imf2,imf3;
    InternetandVPN validaciones = new InternetandVPN();


    public PowerAdicionalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_power_adicionales, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        baterias = D_B.obtenerDcPower2_id(id_formato);



        final EditText nombre1 = (EditText)v.findViewById(R.id.nombre1);
        final EditText correo1 = (EditText)v.findViewById(R.id.correo1);
        final EditText nombre2 = (EditText)v.findViewById(R.id.nombre2);
        final EditText correo2 = (EditText)v.findViewById(R.id.correo2);
        final EditText nombre3 = (EditText)v.findViewById(R.id.nombre3);
        final EditText correo3 = (EditText)v.findViewById(R.id.correo3);
        final EditText nombre4 = (EditText)v.findViewById(R.id.nombre4);
        final EditText correo4 = (EditText)v.findViewById(R.id.correo4);
        final EditText nombre5 = (EditText)v.findViewById(R.id.nombre5);
        final EditText correo5 = (EditText)v.findViewById(R.id.correo5);

        nombre1.setText(baterias.getADICIONALES_NOMBRE1());// = (EditText)v.findViewById(R.id.nombre1);
        correo1.setText(baterias.getFIRMA_CORREO1());// = (EditText)v.findViewById(R.id.correo1);
        nombre2.setText(baterias.getADICIONALES_NOMBRE2());// = (EditText)v.findViewById(R.id.nombre2);
        correo2.setText(baterias.getFIRMA_CORREO2());// = (EditText)v.findViewById(R.id.correo2);
        nombre3.setText(baterias.getADICIONALES_NOMBRE3());// = (EditText)v.findViewById(R.id.nombre3);
        correo3.setText(baterias.getFIRMA_CORREO3());// = (EditText)v.findViewById(R.id.correo3);
        nombre4.setText(baterias.getADICIONALES_NOMBRE4());// = (EditText)v.findViewById(R.id.nombre4);
        correo4.setText(baterias.getFIRMA_CORREO4());// = (EditText)v.findViewById(R.id.correo4);
        nombre5.setText(baterias.getADICIONALES_NOMBRE5());// = (EditText)v.findViewById(R.id.nombre5);
        correo5.setText(baterias.getFIRMA_CORREO5());// = (EditText)v.findViewById(R.id.correo5);


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
                if(nombre1.getText().toString().isEmpty()&&
                        correo1.getText().toString().isEmpty() &&
                        nombre2.getText().toString().isEmpty() &&
                        correo2.getText().toString().isEmpty() &&
                        nombre3.getText().toString().isEmpty() &&
                        correo3.getText().toString().isEmpty() &&
                        nombre4.getText().toString().isEmpty() &&
                        correo4.getText().toString().isEmpty() &&
                        nombre5.getText().toString().isEmpty() &&
                        correo5.getText().toString().isEmpty()){
                    baterias.setADICIONALES_NOMBRE5(" ");// = (EditText)v.findViewById(R.id.nombre1);
                    baterias.setFIRMA_CORREO5(" ");// = (EditText)v.findViewById(R.id.correo1);

                }else {
                    baterias.setADICIONALES_NOMBRE1(nombre1.getText().toString());// = (EditText)v.findViewById(R.id.nombre1);
                    baterias.setFIRMA_CORREO1(correo1.getText().toString());// = (EditText)v.findViewById(R.id.correo1);
                    baterias.setADICIONALES_NOMBRE2(nombre2.getText().toString());// = (EditText)v.findViewById(R.id.nombre2);
                    baterias.setFIRMA_CORREO2(correo2.getText().toString());// = (EditText)v.findViewById(R.id.correo2);
                    baterias.setADICIONALES_NOMBRE3(nombre3.getText().toString());// = (EditText)v.findViewById(R.id.nombre3);
                    baterias.setFIRMA_CORREO3(correo3.getText().toString());// = (EditText)v.findViewById(R.id.correo3);
                    baterias.setADICIONALES_NOMBRE4(nombre4.getText().toString());// = (EditText)v.findViewById(R.id.nombre4);
                    baterias.setFIRMA_CORREO4(correo4.getText().toString());// = (EditText)v.findViewById(R.id.correo4);
                    baterias.setADICIONALES_NOMBRE5(nombre5.getText().toString());// = (EditText)v.findViewById(R.id.nombre5);
                    baterias.setFIRMA_CORREO5(correo5.getText().toString());// = (EditText)v.findViewById(R.id.correo5);
                }

                D_B.guardarPower2(baterias,id_formato);

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

        correo1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo1.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo1.setTextColor(Color.parseColor("#FF0000"));
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
        correo2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo2.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo2.setTextColor(Color.parseColor("#FF0000"));
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
        correo3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo3.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo3.setTextColor(Color.parseColor("#FF0000"));
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
        correo4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo4.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo4.setTextColor(Color.parseColor("#FF0000"));
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
        correo5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    correo5.setTextColor(Color.parseColor("#000000"));
                }else{
                    correo5.setTextColor(Color.parseColor("#FF0000"));
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
