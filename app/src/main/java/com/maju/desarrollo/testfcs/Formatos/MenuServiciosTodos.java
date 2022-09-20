package com.maju.desarrollo.testfcs.Formatos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.Baterias.BateriasMenuFragment;
import com.maju.desarrollo.testfcs.Formatos.DCPower.MenuPowerFragment;
import com.maju.desarrollo.testfcs.Formatos.Garantias.GarantiasMenu;
import com.maju.desarrollo.testfcs.Formatos.IN1.IN1Menu;
import com.maju.desarrollo.testfcs.Formatos.IN2.IN2Menu;
import com.maju.desarrollo.testfcs.Formatos.PDU.PDUMenu;
import com.maju.desarrollo.testfcs.Formatos.STS2.STS2MenuFragment;
import com.maju.desarrollo.testfcs.Formatos.Servicios.ServiciosMenu;
import com.maju.desarrollo.testfcs.Formatos.Thermal.ThermMenuFragment;
import com.maju.desarrollo.testfcs.Formatos.UPS.UpsMenuFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuServiciosTodos extends Fragment {

    LinearLayout ll_bestel;
    OperacionesDB D_B;
    UsuarioD usuario;

    public MenuServiciosTodos() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_servicios_todos, container, false);
        //((MainActivity) getActivity()).ocultarCabecera();

        //region Actividadres
        TextView n1 = (TextView)v.findViewById(R.id.MenuServ_N1);
        TextView n2 = (TextView)v.findViewById(R.id.MenuServ_N2);
        TextView baterias = (TextView)v.findViewById(R.id.MenuServ_Baterias);
        TextView power = (TextView)v.findViewById(R.id.MenuServ_Power);
        TextView garant = (TextView)v.findViewById(R.id.MenuServ_Garantias);
        TextView gral = (TextView)v.findViewById(R.id.MenuServ_SGeneral);
        TextView sts2 = (TextView)v.findViewById(R.id.MenuServ_STS2);
        TextView thermal = (TextView)v.findViewById(R.id.MenuServ_Thermal);
        TextView pdu = (TextView)v.findViewById(R.id.MenuServ_PDU);
        TextView ups = (TextView)v.findViewById(R.id.MenuServ_UPS);
        ll_bestel = v.findViewById(R.id.ll_bestel);

        D_B = OperacionesDB.obtenerInstancia(getContext());
        usuario = D_B.obtenerUsuario();

        if(!usuario.getPaisDescripcion().equals("MEXICO")){
            ll_bestel.setVisibility(View.GONE);
        }

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IN1Menu menu = new IN1Menu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IN2Menu menu = new IN2Menu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
                baterias.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        BateriasMenuFragment menu = new BateriasMenuFragment();
                        Bundle args = new Bundle();
                        args.putString("key_idFormato", "NUEVO");
                        menu.setArguments(args);

                        FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                    }
                });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MenuPowerFragment menu = new MenuPowerFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        garant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GarantiasMenu menu = new GarantiasMenu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        gral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ServiciosMenu menu = new ServiciosMenu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        sts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                STS2MenuFragment menu = new STS2MenuFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        thermal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThermMenuFragment menu = new ThermMenuFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        pdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PDUMenu menu = new PDUMenu();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UpsMenuFragment menu = new UpsMenuFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", "NUEVO");
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        //endregion

    return  v;
    }

}
