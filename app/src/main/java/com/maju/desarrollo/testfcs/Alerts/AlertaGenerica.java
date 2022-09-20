package com.maju.desarrollo.testfcs.Alerts;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlertaGenerica extends DialogFragment {

    String titulo,mensaje;

    public AlertaGenerica() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_alerta_generica, container, false);

        titulo= getArguments().getString("titulo");
        mensaje= getArguments().getString("mensaje");

        TextView tit = (TextView)v.findViewById(R.id.tituloAlerta);
        TextView mensaj = (TextView)v.findViewById(R.id.mensajeAlerta);

        tit.setText(titulo);
        mensaj.setText(mensaje);

        Button continuar = (Button)v.findViewById(R.id.genricoContinuar);
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return  v;
    }

    @Override
    public void onStart() {
        super.onStart();
        //int wt = MainActivity.anchoPantalla();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthActivityPhone = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        int alertW = (int)(.95 * widthActivityPhone);


        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = alertW;
        window.setAttributes(params);
    }
}
