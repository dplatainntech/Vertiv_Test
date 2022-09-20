package com.maju.desarrollo.testfcs.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Documentos extends Fragment {

    String vista;
    OperacionesDB D_B;
    public Home_Documentos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home__documentos, container, false);

        vista= getArguments().getString("vistaF");
        D_B = OperacionesDB.obtenerInstancia(getContext());

        LinearLayout vista1 = (LinearLayout)v.findViewById(R.id.Vista1Docs);
        LinearLayout vista2 = (LinearLayout)v.findViewById(R.id.Vista2Docs);
        TextView PT_Pend = (TextView)v.findViewById(R.id.valor1);
        TextView SE_Pend = (TextView)v.findViewById(R.id.valor2);
        TextView CA_Pend = (TextView)v.findViewById(R.id.valor3);

        TextView PT_Term = (TextView)v.findViewById(R.id.valor4);
        TextView SE_Term = (TextView)v.findViewById(R.id.valor5);
        TextView CA_Term = (TextView)v.findViewById(R.id.valor6);

        LinearLayout LY_PT_P = (LinearLayout)v.findViewById(R.id.MenuLL_PreOrden);
        LinearLayout LY_SV_P = (LinearLayout)v.findViewById(R.id.MenuLL_Servicios);
        LinearLayout LY_CL_P = (LinearLayout)v.findViewById(R.id.MenuLL_Calidad);
        LinearLayout LY_PT_T = (LinearLayout)v.findViewById(R.id.MenuLT_PreTrabajo);
        LinearLayout LY_SV_T = (LinearLayout)v.findViewById(R.id.MenuLT_Servicio);
        LinearLayout LY_CL_T = (LinearLayout)v.findViewById(R.id.MenuLT_Calidad);


        vista1.setVisibility(View.GONE);
        vista2.setVisibility(View.GONE);
        ArrayList<Integer> listaPendientes = new ArrayList<Integer>();
  /*
                0.-PT
                1.-Calidad
                2.-Baterias
                3.-DCPower
                4.-Garantias
                5.-IN1
                6.-IN2
                7.-PDU
                8.-Servicios
                9.-STS2
                10.-Thermal
                11.-UPS
                 */
        if(vista.equals("Proceso")){
            vista1.setVisibility(View.VISIBLE);
            listaPendientes = D_B.formatosEnProceso();

            int serv=0;
            serv = listaPendientes.get(2) + listaPendientes.get(3) +listaPendientes.get(4) +listaPendientes.get(5) +
                    listaPendientes.get(6) +listaPendientes.get(7) +listaPendientes.get(8) +listaPendientes.get(9) +
                    listaPendientes.get(10) +listaPendientes.get(11) ;

            PT_Pend.setText(listaPendientes.get(0).toString());
            CA_Pend.setText(listaPendientes.get(1).toString());
            SE_Pend.setText(String.valueOf(serv));


        }
        if(vista.equals("Terminados")){ vista2.setVisibility(View.VISIBLE);

            listaPendientes = D_B.formatosTerminados();

            int serv=0;
            serv = listaPendientes.get(2) + listaPendientes.get(3) +listaPendientes.get(4) +listaPendientes.get(5) +
                    listaPendientes.get(6) +listaPendientes.get(7) +listaPendientes.get(8) +listaPendientes.get(9) +
                    listaPendientes.get(10) +listaPendientes.get(11) ;

            PT_Term.setText(listaPendientes.get(0).toString());
            CA_Term.setText(listaPendientes.get(1).toString());
            SE_Term.setText(String.valueOf(serv));

        }

        //region DOCUEMTOS EN PROCESO

        LY_PT_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "0");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LY_SV_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "Servicios");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LY_CL_P.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "1");
                args.putString("id_formato", "1");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//endregion

        //region DOCUEMTOS TERMINADOS

        LY_PT_T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "0");
                args.putString("id_formato", "0");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LY_SV_T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "0");
                args.putString("id_formato", "Servicios");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        LY_CL_T.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle args = new Bundle();
                args.putString("estatuslista", "0");
                args.putString("id_formato", "1");

                Home_VistaFolios myfargment = new Home_VistaFolios();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

//endregion




        return v;
    }


}
