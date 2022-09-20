package com.maju.desarrollo.testfcs.Formatos.Refaccion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.maju.desarrollo.testfcs.Home.Home_VistaFolios;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefaMenu extends Fragment {

    OperacionesDB D_B;
    public RefaMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_refacciones, container, false);

        //vista = getArguments().getString("vistaF");
        D_B = OperacionesDB.obtenerInstancia(getContext());


        TextView CantPen = (TextView)v.findViewById(R.id.CantPendiente);
        TextView CantEnProceso = (TextView)v.findViewById(R.id.CantEnProceso);
        TextView CantRechazado = (TextView)v.findViewById(R.id.CantRechazado);
        TextView CantEntregado = (TextView)v.findViewById(R.id.CantEntregado);
        TextView CantTerminado = (TextView)v.findViewById(R.id.CantTerminado);

        LinearLayout Nueva = (LinearLayout)v.findViewById(R.id.NuevaSolicitud);
        LinearLayout Pendientes = (LinearLayout)v.findViewById(R.id.Pendientes);
        LinearLayout EnProceso = (LinearLayout)v.findViewById(R.id.EnProceso);
        LinearLayout Rechazado = (LinearLayout)v.findViewById(R.id.Rechazado);
        LinearLayout Entregado = (LinearLayout)v.findViewById(R.id.Entregado);
        LinearLayout Terminado = (LinearLayout)v.findViewById(R.id.Terminado);

        /*if(vista.equals("Proceso")){
            vista1.setVisibility(View.VISIBLE);
            listaPendientes = D_B.formatosEnProceso();

            int serv=0;
            serv =  listaPendientes.get(2) + listaPendientes.get(3) + listaPendientes.get(4) + listaPendientes.get(5) +
                    listaPendientes.get(6) + listaPendientes.get(7) + listaPendientes.get(8) + listaPendientes.get(9) +
                    listaPendientes.get(10) + listaPendientes.get(11) ;

            PT_Pend.setText(listaPendientes.get(0).toString());
            CA_Pend.setText(listaPendientes.get(1).toString());
            SE_Pend.setText(String.valueOf(serv));


        }*/

        Pendientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "0");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        EnProceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "Servicios");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        Rechazado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "1");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        Entregado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "1");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        Terminado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "1");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });

        Nueva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "1");

                /*Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();*/
            }
        });


        return v;
    }


}
