package com.maju.desarrollo.testfcs.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.Baterias.BateriasGeneralesFragment;
import com.maju.desarrollo.testfcs.Formatos.DCPower.PowerGeneralesFragment;
import com.maju.desarrollo.testfcs.Formatos.Garantias.GarantiasGenerales;
import com.maju.desarrollo.testfcs.Formatos.IN1.IN1Generales;
import com.maju.desarrollo.testfcs.Formatos.IN2.IN2Generales;
import com.maju.desarrollo.testfcs.Formatos.PDU.PDUGenerales;
import com.maju.desarrollo.testfcs.Formatos.STS2.STS2GeneralesFragment;
import com.maju.desarrollo.testfcs.Formatos.Servicios.ServiciosGral;
import com.maju.desarrollo.testfcs.Formatos.Thermal.ThermGeneralFragment;
import com.maju.desarrollo.testfcs.Formatos.UPS.UpsGeneralesFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuServicios_desdeTask extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    String task;
    CatAsignaCliente item;

    public MenuServicios_desdeTask() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_menu_servicios_desde_task, container, false);

        task= getArguments().getString("id_task");
        D_B = OperacionesDB.obtenerInstancia(getContext());


        item = D_B.obtenerTaskId(task);

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
        LinearLayout formatosBestel = v.findViewById(R.id.formatosBestel);

        UsuarioD usuarioDat = D_B.obtenerUsuario();

        if(!usuarioDat.getPaisDescripcion().equals("MEXICO")){
            formatosBestel.setVisibility(View.GONE);
        }

        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                id_formato = D_B.nuevoServicio(item, "5");
                IN1Generales menu = new IN1Generales();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        n2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "6");
                IN2Generales menu = new IN2Generales();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        baterias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "2");
                BateriasGeneralesFragment menu = new BateriasGeneralesFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        power.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "3");
                PowerGeneralesFragment menu = new PowerGeneralesFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        garant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GarantiasGenerales menu = new GarantiasGenerales();
                id_formato = D_B.nuevoServicio(item, "4");
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        gral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "8");
                ServiciosGral menu = new ServiciosGral();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();     }
        });
        sts2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "9");
                STS2GeneralesFragment menu = new STS2GeneralesFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        thermal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "10");
                ThermGeneralFragment menu = new ThermGeneralFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        pdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "7");
                PDUGenerales menu = new PDUGenerales();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });
        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_formato = D_B.nuevoServicio(item, "11");
                UpsGeneralesFragment menu = new UpsGeneralesFragment();
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                menu.setArguments(args);

                FragmentManager fragmentManager = ((FragmentActivity)getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
            }
        });

        //endregion
        return v;
    }

}
