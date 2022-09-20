package com.maju.desarrollo.testfcs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.maju.desarrollo.testfcs.Formatos.Calidad.CalidadMenu;
import com.maju.desarrollo.testfcs.Formatos.MenuServiciosTodos;
import com.maju.desarrollo.testfcs.Formatos.PreOrden.MenuPOFragment;
import com.maju.desarrollo.testfcs.Home.Home_ConsultaF;
import com.maju.desarrollo.testfcs.Home.Home_Documentos;
import com.maju.desarrollo.testfcs.Home.Home_Paquete;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPrincipalFragment extends Fragment {


    public MenuPrincipalFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_principal, container, false);

        LinearLayout PreOrden = (LinearLayout)v.findViewById(R.id.MenuLL_PreOrden);
        PreOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPOFragment menu = new MenuPOFragment();
                Bundle args = new Bundle();
                args.putString("key_idPreOrden", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout Servicios = (LinearLayout)v.findViewById(R.id.MenuLL_Servicios);
        Servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuServiciosTodos menu = new MenuServiciosTodos();
                //Bundle args = new Bundle();
                //args.putString("key_idPreOrden", "NUEVO");
                //menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout Asignaciones = (LinearLayout)v.findViewById(R.id.MenuLL_Asignaciones);
        Asignaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdenListFragment menu = new OrdenListFragment();
                //Bundle args = new Bundle();
                //args.putString("key_idPreOrden", "NUEVO");
                //menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout Calidad = (LinearLayout)v.findViewById(R.id.MenuLL_Calidad);
        Calidad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalidadMenu menu = new CalidadMenu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout COnsultaF = (LinearLayout)v.findViewById(R.id.COnsultaFolios);
        COnsultaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home_ConsultaF menu = new Home_ConsultaF();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout enProceso = (LinearLayout)v.findViewById(R.id.DocsProceso);
        enProceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home_Documentos menu = new Home_Documentos();
                Bundle args = new Bundle();
                args.putString("vistaF", "Proceso");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout terminados = (LinearLayout)v.findViewById(R.id.docsTermin);
        terminados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home_Documentos menu = new Home_Documentos();
                Bundle args = new Bundle();
                args.putString("vistaF", "Terminados");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        LinearLayout GenPaquete = (LinearLayout)v.findViewById(R.id.GenPaquete);
        GenPaquete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Home_Paquete menu = new Home_Paquete();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        return v;
    }

}
