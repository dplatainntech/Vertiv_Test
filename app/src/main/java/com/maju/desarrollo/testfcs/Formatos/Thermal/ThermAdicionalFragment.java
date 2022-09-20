package com.maju.desarrollo.testfcs.Formatos.Thermal;
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

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.ThermalManagagementS;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThermAdicionalFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    ThermalManagagementS Formato;
    InternetandVPN validaciones = new InternetandVPN();

    public ThermAdicionalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_therm_adicional, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerThermal(id_formato);


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

        nombre1.setText(Formato.getAD_NOMBRE1());// = (EditText)v.findViewById(R.id.nombre1);
        correo1.setText(Formato.getAD_CORREO1());// = (EditText)v.findViewById(R.id.correo1);
        nombre2.setText(Formato.getAD_NOMBRE2());// = (EditText)v.findViewById(R.id.nombre2);
        correo2.setText(Formato.getAD_CORREO2());// = (EditText)v.findViewById(R.id.correo2);
        nombre3.setText(Formato.getAD_NOMBRE3());// = (EditText)v.findViewById(R.id.nombre3);
        correo3.setText(Formato.getAD_CORREO3());// = (EditText)v.findViewById(R.id.correo3);
        nombre4.setText(Formato.getAD_NOMBRE4());// = (EditText)v.findViewById(R.id.nombre4);
        correo4.setText(Formato.getAD_CORREO4());// = (EditText)v.findViewById(R.id.correo4);
        nombre5.setText(Formato.getAD_NOMBRE5());// = (EditText)v.findViewById(R.id.nombre5);
        correo5.setText(Formato.getAD_CORREO5());// = (EditText)v.findViewById(R.id.correo5);




        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Thermal");
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
                    Formato.setAD_NOMBRE5(" ");// = (EditText)v.findViewById(R.id.nombre1);
                    Formato.setAD_CORREO5(" ");// = (EditText)v.findViewById(R.id.correo1);

                }else {
                    Formato.setAD_NOMBRE1(nombre1.getText().toString());// = (EditText)v.findViewById(R.id.nombre1);
                    Formato.setAD_CORREO1(correo1.getText().toString());// = (EditText)v.findViewById(R.id.correo1);
                    Formato.setAD_NOMBRE2(nombre2.getText().toString());// = (EditText)v.findViewById(R.id.nombre2);
                    Formato.setAD_CORREO2(correo2.getText().toString());// = (EditText)v.findViewById(R.id.correo2);
                    Formato.setAD_NOMBRE3(nombre3.getText().toString());// = (EditText)v.findViewById(R.id.nombre3);
                    Formato.setAD_CORREO3(correo3.getText().toString());// = (EditText)v.findViewById(R.id.correo3);
                    Formato.setAD_NOMBRE4(nombre4.getText().toString());// = (EditText)v.findViewById(R.id.nombre4);
                    Formato.setAD_CORREO4(correo4.getText().toString());// = (EditText)v.findViewById(R.id.correo4);
                    Formato.setAD_NOMBRE5(nombre5.getText().toString());// = (EditText)v.findViewById(R.id.nombre5);
                    Formato.setAD_CORREO5(correo5.getText().toString());// = (EditText)v.findViewById(R.id.correo5);
                }
                D_B.guardarThrtmal(Formato);
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                ThermMenuFragment myfargment = new ThermMenuFragment();
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
