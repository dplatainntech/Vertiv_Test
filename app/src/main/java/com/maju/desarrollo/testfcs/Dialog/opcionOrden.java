package com.maju.desarrollo.testfcs.Dialog;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.Calidad.CalidadGeneral;
import com.maju.desarrollo.testfcs.Formatos.PreOrden.ContenedorCuestionariosPO;
import com.maju.desarrollo.testfcs.Home.MenuServicios_desdeTask;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;

/**
 * A simple {@link Fragment} subclass.
 */
public class opcionOrden extends DialogFragment {
    String idtarea;
    OperacionesDB D_B;

    public opcionOrden() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_opcion_orden, container, false);
        ImageButton continuar = (ImageButton)v.findViewById(R.id.imageButtonOrdenClose);

        idtarea= getArguments().getString("idSR");
        D_B = OperacionesDB.obtenerInstancia(getContext());

        final CatAsignaCliente item = D_B.obtenerTaskId(idtarea);

        TextView sr = (TextView)v.findViewById(R.id.sr);
        TextView ts = (TextView)v.findViewById(R.id.ts);
        TextView cliente = (TextView)v.findViewById(R.id.adaptercliente);
        TextView direccion = (TextView)v.findViewById(R.id.adapterDireccion);
        TextView fecha = (TextView)v.findViewById(R.id.adapterFecha);



        ImageView PoreO = (ImageView)v.findViewById(R.id.imageView6);
        ImageView Serv = (ImageView)v.findViewById(R.id.imageView9);
        ImageView Calid = (ImageView)v.findViewById(R.id.imageView10);


        sr.setText("SR: "+item.getSr_Nbr());
        ts.setText("TASK: "+item.getTask_Nbr());
        cliente.setText(item.getInstall_Party_Name());
        direccion.setText(item.getAdress());
        fecha.setText(item.getTask_Start_Planned());


        LinearLayout contenedor = (LinearLayout)v.findViewById(R.id.layouttransparent);
        //contenedor.setBackgroundColor(Color.argb(138,221,45,0));
        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        PoreO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String idPT = D_B.nuevaPreOrden(item);
                D_B.nuevoRegistroFormato(idPT,"0");

                Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Generales");
                siguiente2.putExtra("key_idPreOrden", idPT);
                startActivity(siguiente2);
                dismiss();
            }
        });

        Serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuServicios_desdeTask menu = new MenuServicios_desdeTask();
                Bundle args = new Bundle();
                args.putString("id_task", item.getSr_Nbr());
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                dismiss();
            }
        });

        Calid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String idPT = D_B.nuevaCalidad(item);
                D_B.nuevoRegistroFormato(idPT,"1");
                CalidadGeneral menu = new CalidadGeneral();
                Bundle args = new Bundle();
                args.putString("key_idFormato", idPT);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                dismiss();
            }
        });




        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

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
