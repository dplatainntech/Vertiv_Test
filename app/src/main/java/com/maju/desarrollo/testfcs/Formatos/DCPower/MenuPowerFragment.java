package com.maju.desarrollo.testfcs.Formatos.DCPower;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.AccionesM.CContrasena;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.AlertaOk;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.ImgB64;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class MenuPowerFragment extends Fragment {

    OperacionesDB D_B;
    String id_formato;
    DCPower dcPower;
    DCPower2 dcPower2;
    Button Terminar;
    InternetandVPN validaciones = new InternetandVPN();
    UsuarioD usuario;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImgB64 b64 = new ImgB64();
    progresoLoad alerta = new progresoLoad();

    public MenuPowerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_power, container, false);
        ((MainActivity) getActivity()).verCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        //id_formato ="Key-fc0db2b0-ea3a-4358-84ac-0194c2c98bca"; //test

        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();

        if(id_formato.equals("NUEVO")){
            //se crea nueva Formato y se regresa el id
            id_formato = D_B.nuevoDCPower();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"3"); //crear registrode formato. Thermal
            dcPower = D_B.obtenerDcPower_id(id_formato);
            dcPower2= D_B.obtenerDcPower2_id(id_formato);

        }else{
            dcPower = D_B.obtenerDcPower_id(id_formato);
            dcPower2= D_B.obtenerDcPower2_id(id_formato);
        }

        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.PW_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.PW_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.PW_M3);
        LinearLayout M4 = (LinearLayout)v.findViewById(R.id.PW_M4);
        LinearLayout M5 = (LinearLayout)v.findViewById(R.id.PW_M5);
        LinearLayout M6 = (LinearLayout)v.findViewById(R.id.PW_M6);
        LinearLayout M7 = (LinearLayout)v.findViewById(R.id.PW_M7);
        ImageView im1 = (ImageView)v.findViewById(R.id.imgM1);
        ImageView im2 = (ImageView)v.findViewById(R.id.imgM2);
        ImageView im3 = (ImageView)v.findViewById(R.id.imgM3);
        ImageView im4 = (ImageView)v.findViewById(R.id.imgM4);
        ImageView im5 = (ImageView)v.findViewById(R.id.imgM5);
        ImageView im6 = (ImageView)v.findViewById(R.id.imgM6);
        ImageView im7 = (ImageView)v.findViewById(R.id.imgM7);

        //region Botones acciones
        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ContenedorCuestionariosPO containerFragment = new ContenedorCuestionariosPO();
                /*Intent siguiente2 = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente2.putExtra("FormilarioPO", "Generales");
                siguiente2.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente2);
                */
                Bundle args = new Bundle();
                args.putString("key_idFormato", dcPower.getID_fORMATO());

                PowerGeneralesFragment myfargment = new PowerGeneralesFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();


            }
        });
        M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerParametrosFragment myfargment = new PowerParametrosFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerComentFragment myfargment = new PowerComentFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerMaterialesBlankFragment myfargment = new PowerMaterialesBlankFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerFotosFragment myfargment = new PowerFotosFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerFirmasFragment myfargment = new PowerFirmasFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                PowerAdicionalesFragment myfargment = new PowerAdicionalesFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        //endregion

        int completo = 7;

        //region Modulo  Generales
        int M1_generales = 13;

        try{if(!dcPower.getGRAL_FOLIO_PRETRABAJO().isEmpty() || !dcPower.getGRAL_PROYECTO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_CLIENTE().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_DIRECCION().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_CONTACTO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_TELEFONO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_MAIL().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!Formato.getSRProyecto().isEmpty() || !Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_eferencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_sr().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_task().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_TIPO_SERVICIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_TIPO_SERVICIOOtro().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_FRECUENCIA().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_noTag().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_MODELO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_SERIE().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!dcPower.getGRAL_SERIE2().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_FECHA_INICIO().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!dcPower.getGRAL_FECHA_FIN().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}


        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
        else if(M1_generales < 13){im1.setImageResource(R.drawable.generalnar);}
        else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 2
        //region VALIDACIONES
        int actividadM =0;

        //region Validacion M1
        int MV1 = 5;


        try{if(!dcPower.getPARAM_GRALS_TIPO().isEmpty()){ MV1 =  MV1-1; actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_N_ESPECIFICAION().isEmpty()){ MV1 =  MV1-1; actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CAPACIDAD().isEmpty()){ MV1 =  MV1-1; actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CONFIGURACION().isEmpty()){ MV1 =  MV1-1; actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CARGA_ACT().isEmpty()){ MV1 =  MV1-1; actividadM++;}}catch (Exception e){}
        if(MV1==0){MV1 =0;}
        else if(MV1<5){MV1 = 1;}else{MV1 = 1;}
//endregion

        //region ValidacionM2
        int MC2_A = 3;
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_MODELO().isEmpty()){ MC2_A =  MC2_A-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION().isEmpty()){ MC2_A =  MC2_A-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD().isEmpty()){ MC2_A =  MC2_A-1;actividadM++;}}catch (Exception e){}
        int MC2_B = 32;
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R1().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R2().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R3().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R4().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R5().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R6().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R7().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R8().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R9().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R10().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R11().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R12().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R13().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R14().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R15().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R16().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R17().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R18().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R19().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R20().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R21().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R22().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R23().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R24().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R25().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R26().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R27().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R28().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R29().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R30().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R31().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R32().isEmpty()){ MC2_B =  MC2_B-1;actividadM++;}}catch (Exception e){}
        if(MC2_A==0 && MC2_B < 32){
            MC2_A = 0;}
        else if(MC2_A<3 || MC2_B < 32){
            MC2_A = 1;}
        else{ MC2_A = 1;}
//endregion

        //region ValidacionM3
        int MV3A = 1;
        int MV3B = 6;
        try{if(dcPower.getPARAM_INV_GABINETES_NA().equals("true")){
            MV3A = 0;
        }else{
            try{if(!dcPower.getPARAM_INV_GABINETES_ESPECIFIC().isEmpty()){ MV3A =  MV3A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB1().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB2().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB3().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB4().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB5().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB6().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}

            if(MV3A==0 && MV3B < 6){
                MV3A = 0;}
            else if(MV3A<=1 || MV3B <= 6){
                MV3A = 1;}
        }
        }catch (Exception m){
            try{if(!dcPower.getPARAM_INV_GABINETES_ESPECIFIC().isEmpty()){ MV3A =  MV3A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB1().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB2().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB3().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB4().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB5().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB6().isEmpty()){ MV3B =  MV3B-1;actividadM++;}}catch (Exception e){}

            if(MV3A==0 && MV3B < 6){
                MV3A = 0;}
            else if(MV3A<=1 || MV3B <= 6){
                MV3A = 1;}
        }
//endregion

        //region ValidacionM4
        int MV4A = 2;
        int MV4B = 4;
        try{if(dcPower.getPARAM_SUPRESOR_NA().equals("true")){
            MV4A = 0;
        }
        else{
            try{if(!dcPower.getPARAM_SUPRESOR_MODELO().isEmpty()){ MV4A =  MV4A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_MARCA().isEmpty()){ MV4A =  MV4A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_INSTALAC().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CAPACIDAD().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}

            if(MV4A==0 && MV4B < 4){
                MV4A = 0;}
            else if(MV4A<2 || MV4B < 4){
                MV4A = 1;}else{ MV4A = 1;}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_SUPRESOR_MODELO().isEmpty()){ MV4A =  MV4A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_MARCA().isEmpty()){ MV4A =  MV4A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_INSTALAC().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CAPACIDAD().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS().isEmpty()){ MV4B =  MV4B-1;actividadM++;}}catch (Exception e){}

            if(MV4A==0 && MV4B < 4){
                MV4A = 0;}
            else if(MV4A<2 || MV4B < 4){
                MV4A = 1;}else{ MV4A = 1;}
        }
        //endregion

        //region ValidacionM5
        int MV5 = 21;
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M1().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M2().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M3().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M4().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M5().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M6().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M7().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M8().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M9().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M10().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_VERIFICAION().isEmpty()){ MV5 =  MV5-1;actividadM++;}}catch (Exception e){}
        if(MV5==0){
            MV5 = 0;}
        else if(MV5<=21){
            MV5 = 1;}
        //endregion

        //region ValidacionM6
        int MV6 = 8;
        try{
            if(dcPower.getPARAM_STPF_DC_NA().equals("true")){
            MV6 = 0;
        }else{
            try{if(!dcPower.getPARAM_STPF_DC_CAMP1().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP2().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP3().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP4().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP5().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP6().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP7().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP8().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            if(MV6<8){
                MV6 = 0;}
            else if(MV6==8){
                MV6 = 1;}

        }}
        catch (Exception m){
            try{if(!dcPower.getPARAM_STPF_DC_CAMP1().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP2().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP3().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP4().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP5().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP6().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP7().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP8().isEmpty()){ MV6 =  MV6-1;actividadM++;}}catch (Exception e){}
            if(MV6<8){
                MV6 = 0;}
            else if(MV6==8){
                MV6 = 1;}

        }
        //endregion

        //region ValidacionM7
        int MV7 = 7;
        try{if(dcPower.getPARAM_ITPF_DC_NA().equals("true")){
            MV7 = 0;
        }else{

            try{if(!dcPower.getPARAM_ITPF_DC_CAMP1().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP2().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP3().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP4().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP5().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP6().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP7().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}

            if(MV7<7){
                MV7 = 0;}
            else if(MV7==7){
                MV7 = 1;}
        }}catch (Exception m){
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP1().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP2().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP3().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP4().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP5().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP6().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP7().isEmpty()){ MV7 =  MV7-1;actividadM++;}}catch (Exception e){}

            if(MV7<7){
                MV7 = 0;}
            else if(MV7==7){
                MV7 = 1;}
        }
        //endregion

        //region ValidacionM8
        int MV8 = 5;
        try{if(dcPower.getPARAM_SISTEMA_DIST_NA().equals("true")){
            MV8 = 0;
        }else{

            try{if(!dcPower.getPARAM_SISTEMA_DIST_MODELO().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MARCA().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_ESPECIFI().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            if(MV8==0){
                MV8 = 0;}
            else if(MV8<5){
                MV8 = 1;}else{
                MV8 = 1;}
        }}catch (Exception m){
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MODELO().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MARCA().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_ESPECIFI().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION().isEmpty()){ MV8 =  MV8-1;actividadM++;}}catch (Exception e){}
            if(MV8==0){
                MV8 = 0;}
            else if(MV8<5){
                MV8 = 1;}else{
                MV8 = 1;}
        }
        //endregion

        //region ValidacionM9
        int MV9A = 7;
        int MV9B = 8;
        try{if(dcPower.getPARAM_IDR_NA().equals("true")){
            MV9A = 0;
        }else{
            try{if(!dcPower.getPARAM_IDR_CAMP1().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP2().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP3().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP4().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP5().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP6().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP7().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK1().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK2().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK3().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK4().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK5().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK6().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK7().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK8().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            if(MV9A<7 && MV9B <8){
                MV9A = 0;}
            else if(MV9A==7 || MV9B==8 ){
                MV9A = 1;}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_IDR_CAMP1().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP2().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP3().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP4().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP5().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP6().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP7().isEmpty()){ MV9A =  MV9A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK1().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK2().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK3().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK4().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK5().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK6().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK7().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK8().isEmpty()){ MV9B =  MV9B-1;actividadM++;}}catch (Exception e){}
            if(MV9A<7 && MV9B <8){
                MV9A = 0;}
            else if(MV9A==7 || MV9B==8 ){
                MV9A = 1;}
        }
//endregion

        //region ValidacionM10
        int MV10 = 5;
        int MV10A = 10;
        int MV10C = 10;
        int MV10B = 10;
        int MV10P = 5;
        try{if(dcPower.getPARAM_SISTEMA_INVER_NA().equals("true")){
            MV10 = 0;

        }
        else{
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MODELO().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MARCA().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_ESPEC().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CONFG().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV1().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV2().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV3().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV4().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV5().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV6().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV7().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV8().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV9().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV10().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV1().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV2().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV3().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV4().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV5().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV6().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV7().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV8().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV9().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV10().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV1().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV2().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV3().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV4().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV5().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV6().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV7().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV8().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV9().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV10().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI1().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI2().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI3().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI4().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_LIMP().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}

            if(MV10 == 0 && MV10A<10 && MV10B <10 && MV10C <10 && MV10P <5){
                MV10 = 0;}
            else if(MV10 < 5 || MV10A<10 || MV10B <10 || MV10C <10 ||MV10P <5){
                MV10 = 1;}else{
                MV10 = 1;
            }

        }}
        catch (Exception m){
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MODELO().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MARCA().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_ESPEC().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CONFG().isEmpty()){ MV10 =  MV10-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV1().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV2().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV3().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV4().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV5().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV6().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV7().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV8().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV9().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV10().isEmpty()){ MV10A =  MV10A-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV1().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV2().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV3().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV4().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV5().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV6().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV7().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV8().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV9().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV10().isEmpty()){ MV10B =  MV10B-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV1().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV2().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV3().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV4().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV5().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV6().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV7().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV8().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV9().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV10().isEmpty()){ MV10C =  MV10C-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI1().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI2().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI3().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI4().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_LIMP().isEmpty()){ MV10P =  MV10P-1;actividadM++;}}catch (Exception e){}

            if(MV10 == 0 && MV10A<10 && MV10B <10 && MV10C <10 && MV10P <5){
                MV10 = 0;}
            else if(MV10 < 5 || MV10A<10 || MV10B <10 || MV10C <10 ||MV10P <5){
                MV10 = 1;}else{ MV10 = 1;}
        }
        //endregion

        //region ValidacionM11
        int MV11 = 30;
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC1().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC2().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC3().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC4().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC5().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC6().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC7().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC8().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC1().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC2().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC3().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC4().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC5().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC6().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC7().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA1().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA2().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA3().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA4().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA5().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA6().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA7().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA8().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA1().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA2().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA3().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA4().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA5().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA6().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA7().isEmpty()){ MV11 =  MV11-1;actividadM++;}}catch (Exception e){}
        if(MV11 == 0){
            MV11 = 0;}
        else if(MV11 < 30 && MV11 >0){
            MV11 = 1;}
        else{
                MV11 = 1;
        }

        // endregion


        //endregion

        int validacionesM2 = MV1 + MC2_A +MV3A +MV4A+ MV5 + MV6 +MV7+ MV8+ MV9A+ MV10 + MV11;
        if( validacionesM2 ==0){im2.setImageResource(R.drawable.lectura2verde);
            completo = completo-1;}
        else if(validacionesM2 < 11) {im2.setImageResource(R.drawable.lectura2nar);}
        else{
            if(actividadM > 0){im2.setImageResource(R.drawable.lectura2nar);}else {
                im2.setImageResource(R.drawable.lectura2gris);
            }
        }
        //endregion

        //region Modulo 3
        int  M3_ = 4;
        try{if(!dcPower2.getCOMENTA_ACTIVIDADES().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!dcPower2.getCOMENTA_DIAGNOSTICO().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!dcPower2.getCOMENTA_ACCION().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!dcPower2.getCOMENTA_COMENTARIOS().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}

        if(M3_ == 0){ im3.setImageResource(R.drawable.comentariosverde);
            completo = completo-1;}
        else if(M3_ < 4){im3.setImageResource(R.drawable.comentariosnar);}
        else {im3.setImageResource(R.drawable.comentariosgris);}
        //endregion

        //region Modulo Comentarios
       /* int M4_ = 1;
        try{if(!dcPower.getCOMENT_cometarios().isEmpty()){M4_ = M4_ - 1;}}catch (Exception e){}

        if(M4_ == 0){ im4.setImageResource(R.drawable.comentariosverde);
            completo = completo-1;}
        else {im4.setImageResource(R.drawable.comentariosgris);}
*/
        //endregion

        //region Modulo Materiales
        int M5_A = 24;
        int M5_B = 15;

        try{if(dcPower2.getMATERIA_PMU_NA().equals("N/A")){
            M5_A = M5_A - 24;
        }
        else{
            //region validaciones
            try{if(!dcPower2.getMATERIA_CANTIDAD1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            //endregion

        }}catch(Exception ex){
            //region validaciones
            try{if(!dcPower2.getMATERIA_CANTIDAD1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_CANTIDAD8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_N_PARTE8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_DESC8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            //endregion
        }

        try{if(dcPower2.getMATERIA_EQUIPO_NA().equals("N/A")){
            M5_B = M5_B - 15;
        }
        else{
            //region Validaciones
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            //endregion
        }}catch(Exception ex){
            //region Validaciones
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_EQUIPO5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_ID5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!dcPower2.getMATERIA_EQUIPO_FEVHA5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            //endregion
        }

        if((M5_A + M5_B) == 0){ im4.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else if((M5_A < 24 && M5_B==15) || (M5_A == 24 && M5_B<15) ){
            im4.setImageResource(R.drawable.materialenar);
        }
        else if(M5_A <24 && M5_B <15){
            im4.setImageResource(R.drawable.materialesverde);
            completo = completo-1;
        }
        else {im4.setImageResource(R.drawable.materialesgris);}

        //endregion

        //region Modulo Fotos
        int M6_ = 12;
        int M_Foto1 = 2;
        int M_Foto2 = 2;
        int M_Foto3 = 2;
        int M_Foto4 = 2;
        int M_Foto5 = 2;
        int M_Foto6 = 2;

        try{if(!dcPower2.getFOTOS_DESPUES1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_DESPUES2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_DESPUES3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_DESPUES4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_DESPUES5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_DESPUES6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFOTOS_ANTES6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

        int constante1 = M_Foto1 + M_Foto2+ M_Foto3 +M_Foto4+M_Foto5+M_Foto6;
        if(constante1 == 12){
            im5.setImageResource(R.drawable.fotogris);
        }else{
            if(constante1== 0){
                im5.setImageResource(R.drawable.fotoverde);
                completo = completo-1;
            }else{
                int terinado = 1;
                if(M_Foto1 > 0 & M_Foto1<2){terinado = terinado +1;}
                if(M_Foto2 > 0 & M_Foto2<2){terinado = terinado +1;}
                if(M_Foto3 > 0 & M_Foto3<2){terinado = terinado +1;}
                if(M_Foto4 > 0 & M_Foto4<2){terinado = terinado +1;}
                if(M_Foto5 > 0 & M_Foto5<2){terinado = terinado +1;}
                if(M_Foto6 > 0 & M_Foto6<2){terinado = terinado +1;}
                if(terinado==1){
                    im5.setImageResource(R.drawable.fotoverde);
                    completo = completo-1;
                }else {
                    im5.setImageResource(R.drawable.fotonar);
                }
            }
        }



        //endregion

        //region Modulo Firmas
        int M7_ = 2;
        int obliga =2;
        int M7_Fr = 0;

        try{if(!dcPower2.getFIRMA_FIRMA3().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_NOMBRE3().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_FIRMA1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_NOMBRE1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_FIRMA2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_NOMBRE2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_EMPRESA().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_TELEFONO().isEmpty()){M7_Fr++;}}catch (Exception e){}

        try{
            if(!dcPower2.getFIRMA_FIRMA3().isEmpty() && !dcPower2.getFIRMA_NOMBRE3().isEmpty()){
                try{if(!dcPower2.getFIRMA_CORREO().isEmpty()&&
                        !dcPower2.getFIRMA_EMPRESA().isEmpty()&&
                        !dcPower2.getFIRMA_TELEFONO().isEmpty()){M7_ = M7_-1;}
                }catch (Exception e){}{
                }
            }
        }catch (Exception e){}


        try {
            if (!dcPower2.getFIRMA_FIRMA1().isEmpty()&& !dcPower2.getFIRMA_NOMBRE1().isEmpty()){ M7_ = M7_-1;   }
        } catch (Exception e) {
        }

        try {
            if (!dcPower2.getFIRMA_FIRMA2().isEmpty()){ obliga = obliga-1;   }
            if (!dcPower2.getFIRMA_NOMBRE2().isEmpty()){ obliga = obliga-1;   }
        } catch (Exception e) {
        }

        if(obliga == 0){
            if(M7_== 0 || M7_ == 1 ){im6.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im6.setImageResource(R.drawable.firmanar);}
            else {im6.setImageResource(R.drawable.firmagris);}
        }
        else if(M7_ < 2){im6.setImageResource(R.drawable.firmanar);}
        else {
            if(M7_Fr>0){
                im6.setImageResource(R.drawable.firmanar);
        }else{
                im6.setImageResource(R.drawable.firmagris);
        }}




        //endregion

        //region Modulo Adicionales
        int M8_ = 10;

        try{if(!dcPower2.getADICIONALES_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getADICIONALES_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getADICIONALES_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getADICIONALES_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getADICIONALES_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
        try{if(!dcPower2.getFIRMA_CORREO5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}


        if(M8_ < 10){im7.setImageResource(R.drawable.notificaverde);
            completo = completo-1;}
        else {im7.setImageResource(R.drawable.notificagris);}
        //endregion

        //region Terminar Boton
        Terminar = (Button)v.findViewById(R.id.button2);
        Terminar.setEnabled(false);
        Terminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //region validaciones y envio de Formato
                alerta.setCancelable(true);
                alerta.show(getFragmentManager(), "a");


                try {
                    if(validaciones.webservise()){
                        if(validaciones.vpnOn()){
                            // Login activo = validaciones.activo(usuario.getID_USER(), usuario.getCONTRASEÑA(), iMyAPI);
                            Login itemAct = new Login();
                            itemAct.setUsuario(usuario.getCORREO());
                            itemAct.setContraseña(usuario.getCONTRASEÑA());

                            compositeDisposable.add(iMyAPI.Login(itemAct).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new Consumer<Login>() {
                                        @Override
                                        public void accept(Login item) throws Exception {
                                            if(item.getExitoso().equals("1") || item.getExitoso().equals("4")){
                                                if(item.getExitoso().equals("4")){
                                                    alertaMensajes("Importante", item.getError());
                                                }
                                                enviarFormato();
                                            }
                                            else{
                                                alerta.dismiss();
                                                if(item.getExitoso().equals("0") || item.getExitoso().equals("3")){
                                                    D_B.CambioSesion(usuario,"INACTIVO");
                                                    alertaMensajes("Importante", item.getError());

                                                    Intent siguiente2 = new Intent(getActivity(), LoginActivity.class);
                                                    startActivity(siguiente2);
                                                    overridePendingTransition(R.anim.left_in, R.anim.left_out);

                                                }
                                                if(item.getExitoso().equals("2")){
                                                    alertaMensajes("Importante", item.getError());
                                                    Intent siguiente2 = new Intent(getActivity(), CContrasena.class);
                                                    startActivity(siguiente2);
                                                }

                                            }

                                        }
                                    }, new Consumer<Throwable>() {
                                        @Override
                                        public void accept(Throwable throwable) throws Exception {
                                            //Toast.makeText(this(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            //alerta(throwable.getMessage());
                                        }
                                    })
                            );

                        }
                        else {
                            alerta.dismiss();
                            alertaMensajes("Alerta", "No hay conexión a la VPN");
                        }
                    }
                    else{
                        alertaMensajes("Importante", "No se establecio conexión a internet, formato guardado localmente");

                        MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                        alerta.dismiss();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    alerta.dismiss();
                } catch (IOException e) {
                    e.printStackTrace();
                    alerta.dismiss();
                }
                //endregion
            }
        });
//endregion

        if(completo == 0){
            D_B.actualizarEsatusFormato("0",id_formato);
            Terminar.setBackgroundResource(R.drawable.btnnaranja);
            Terminar.setEnabled(true);
        }else{
            D_B.actualizarEsatusFormato("1",id_formato);
        }

        return v;

    }

    private void overridePendingTransition(int left_in, int left_out) {
    }
    public void alertaMensajes(String titulo, String mensaje){
        Bundle valores = new Bundle();
        valores.putString("titulo",titulo);
        valores.putString("mensaje",mensaje);
        AlertaGenerica alertamnsaje = new AlertaGenerica();
        alertamnsaje.setArguments(valores);
        alertamnsaje.setCancelable(false);
        alertamnsaje.show(getFragmentManager(), "a");
    }
    com.maju.desarrollo.testfcs.ServiceClass.DCPower Formato = new com.maju.desarrollo.testfcs.ServiceClass.DCPower();
    public void enviarFormato(){
        //com.maju.desarrollo.testfcs.ServiceClass.DCPower Formato = new com.maju.desarrollo.testfcs.ServiceClass.DCPower();
        //region
        //Modulo 1
        final String idFormatoService = D_B.generarUID();
        Formato.setIdDCPower(idFormatoService);

        if(dcPower.getGRAL_PROYECTO().length()>1){ Formato.setSRProyecto(dcPower.getGRAL_PROYECTO());}
        else {Formato.setSRProyecto(dcPower.getGRAL_SR());}
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setIdCliente(dcPower.getGRAL_CLIENTE().toString());
        Formato.setFolioPreTrabajo(dcPower.getGRAL_FOLIO_PRETRABAJO());
        Formato.setFechaInicio(dcPower.getGRAL_FECHA_INICIO());
        Formato.setFechaTermino(dcPower.getGRAL_FECHA_FIN());
        Formato.setContactoCliente(dcPower.getGRAL_CONTACTO());
        Formato.setTASK(dcPower.getGRAL_TASK());
        Formato.setTelefono(dcPower.getGRAL_TELEFONO());
        Formato.setTipoServicio(dcPower.getGRAL_TIPO_SERVICIO());
        Formato.setEMail(dcPower.getGRAL_MAIL());
        Formato.setFreecuencia(dcPower.getGRAL_FRECUENCIA());
        Formato.setModeloEquipo(dcPower.getGRAL_MODELO());
        Formato.setNoTag(dcPower.getGRAL_NTAG());
        Formato.setNoSerie(dcPower.getGRAL_SERIE());
        Formato.setDireccionSitio(dcPower.getGRAL_DIRECCION());

        //Modulo 2
        Formato.setPGSDCTipo(dcPower.getPARAM_GRALS_TIPO());
        Formato.setPGSDCNoEspecificacion(dcPower.getPARAM_GRALS_N_ESPECIFICAION());
        Formato.setPGSDCCapacidad(dcPower.getPARAM_GRALS_CAPACIDAD());
        Formato.setPGSDCConfiguracion(dcPower.getPARAM_GRALS_CONFIGURACION());
        Formato.setPGSDCCargaActual(dcPower.getPARAM_GRALS_CARGA_ACT());
        Formato.setPGIRModelo(dcPower.getPARAM_INV_RECTIFICADORES_MODELO());
        Formato.setPGIRNoEspecificacion(dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION());
        Formato.setPGIRCapacidad(dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD());
        Formato.setNoSerieR1(dcPower.getPARAM_INV_RECTIFICADORES_R1());
        Formato.setNoSerieR2(dcPower.getPARAM_INV_RECTIFICADORES_R2());
        Formato.setNoSerieR3(dcPower.getPARAM_INV_RECTIFICADORES_R3());
        Formato.setNoSerieR4(dcPower.getPARAM_INV_RECTIFICADORES_R4());
        Formato.setNoSerieR5(dcPower.getPARAM_INV_RECTIFICADORES_R5());
        Formato.setNoSerieR6(dcPower.getPARAM_INV_RECTIFICADORES_R6());
        Formato.setNoSerieR7(dcPower.getPARAM_INV_RECTIFICADORES_R7());
        Formato.setNoSerieR8(dcPower.getPARAM_INV_RECTIFICADORES_R8());
        Formato.setNoSerieR9(dcPower.getPARAM_INV_RECTIFICADORES_R9());
        Formato.setNoSerieR10(dcPower.getPARAM_INV_RECTIFICADORES_R10());
        Formato.setNoSerieR11(dcPower.getPARAM_INV_RECTIFICADORES_R11());
        Formato.setNoSerieR12(dcPower.getPARAM_INV_RECTIFICADORES_R12());
        Formato.setNoSerieR13(dcPower.getPARAM_INV_RECTIFICADORES_R13());
        Formato.setNoSerieR14(dcPower.getPARAM_INV_RECTIFICADORES_R14());
        Formato.setNoSerieR15(dcPower.getPARAM_INV_RECTIFICADORES_R15());
        Formato.setNoSerieR16(dcPower.getPARAM_INV_RECTIFICADORES_R16());
        Formato.setNoSerieR17(dcPower.getPARAM_INV_RECTIFICADORES_R17());
        Formato.setNoSerieR18(dcPower.getPARAM_INV_RECTIFICADORES_R18());
        Formato.setNoSerieR19(dcPower.getPARAM_INV_RECTIFICADORES_R19());
        Formato.setNoSerieR20(dcPower.getPARAM_INV_RECTIFICADORES_R20());
        Formato.setNoSerieR21(dcPower.getPARAM_INV_RECTIFICADORES_R21());
        Formato.setNoSerieR22(dcPower.getPARAM_INV_RECTIFICADORES_R22());
        Formato.setNoSerieR23(dcPower.getPARAM_INV_RECTIFICADORES_R23());
        Formato.setNoSerieR24(dcPower.getPARAM_INV_RECTIFICADORES_R24());
        Formato.setNoSerieR25(dcPower.getPARAM_INV_RECTIFICADORES_R25());
        Formato.setNoSerieR26(dcPower.getPARAM_INV_RECTIFICADORES_R26());
        Formato.setNoSerieR27(dcPower.getPARAM_INV_RECTIFICADORES_R27());
        Formato.setNoSerieR28(dcPower.getPARAM_INV_RECTIFICADORES_R28());
        Formato.setNoSerieR29(dcPower.getPARAM_INV_RECTIFICADORES_R29());
        Formato.setNoSerieR30(dcPower.getPARAM_INV_RECTIFICADORES_R30());
        Formato.setNoSerieR31(dcPower.getPARAM_INV_RECTIFICADORES_R31());
        Formato.setNoSerieR32(dcPower.getPARAM_INV_RECTIFICADORES_R32());
//PGIGRPDC(dcPower.getPARAM_INV_GABINETES_NA());
        Formato.setEspecificacionesGabinete(dcPower.getPARAM_INV_GABINETES_ESPECIFIC());
        Formato.setNoSerieGab1(dcPower.getPARAM_INV_GABINETES_GAB1());
        Formato.setNoSerieGab2(dcPower.getPARAM_INV_GABINETES_GAB2());
        Formato.setNoSerieGab3(dcPower.getPARAM_INV_GABINETES_GAB3());
        Formato.setNoSerieGab4(dcPower.getPARAM_INV_GABINETES_GAB4());
        Formato.setNoSerieGab5(dcPower.getPARAM_INV_GABINETES_GAB5());
        Formato.setNoSerieGab6(dcPower.getPARAM_INV_GABINETES_GAB6());
//VISPTVSSTAF(dcPower.getPARAM_SUPRESOR_NA());
        Formato.setVISPTVSSTAFModelo(dcPower.getPARAM_SUPRESOR_MODELO());
        Formato.setVISPTVSSTAFMarca(dcPower.getPARAM_SUPRESOR_MARCA());
        Formato.setInstalacionMecanicaGabineteTVSS(dcPower.getPARAM_SUPRESOR_INSTALAC());
        Formato.setCalibreCableadoConexionTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION());
        Formato.setCapacidadbreakersConexionTableroAc(dcPower.getPARAM_SUPRESOR_CAPACIDAD());
        Formato.setCalibreCableTierrasTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS());
        Formato.setPAPFDCVoltajeAlimentacion(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1());
        Formato.setPAPFDCVoltajeFaseAB(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2());
        Formato.setPAPFDCVoltajeFaseBC(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3());
        Formato.setPAPFDCVoltajeFaseCA(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4());
        Formato.setPAPFDCVoltajeFaseAN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5());
        Formato.setPAPFDCVoltajeFaseBN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6());
        Formato.setPAPFDCVoltajeFaseCN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7());
        Formato.setPAPFDCVoltajeNeutroGND(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8());
        Formato.setPOPFDCVoltajeFlotacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M1());
        Formato.setPOPFDCVoltajeIgualacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M2());
        Formato.setPOPFDCAlarmaBajoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M3());
        Formato.setPOPFDCAlarmaBajoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M4());
        Formato.setPOPFDCDesconexionBajoVoltaje(dcPower.getPARAM_AyO_PLANTA_OPERAC_M5());
        Formato.setPOPFDCAlarmaAltoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M6());
        Formato.setPOPFDCAlarmaAltoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M7());
        Formato.setPOPFDCAlarmaFusibleCargaAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M8());
        Formato.setPOPFDCAlarmaFusibleBateriasAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M9());
        Formato.setPOPFDCAlarmaFallaRectificador(dcPower.getPARAM_AyO_PLANTA_OPERAC_M10());
        Formato.setLimpiezaInternaExternaComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA());
        Formato.setLimpiezaIndividualComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV());
        Formato.setVerificacionTorquesComen(dcPower.getPARAM_AyO_PLANTA_VERIFICAION());
//STPFDC(dcPower.getPARAM_STPF_DC_NA());
        Formato.setVerificacionConexionesTerminalesComen(dcPower.getPARAM_STPF_DC_CAMP1());
        Formato.setDebeSerZaptaComen(dcPower.getPARAM_STPF_DC_CAMP2());
        Formato.setVerificacionEtiquetadoTierrasComen(dcPower.getPARAM_STPF_DC_CAMP3());
        Formato.setVerificacionCableadoExistenteTierrasComen(dcPower.getPARAM_STPF_DC_CAMP4());
        Formato.setMidaResistenciaComen(dcPower.getPARAM_STPF_DC_CAMP5());
        Formato.setCablesGabineteComen(dcPower.getPARAM_STPF_DC_CAMP6());
        Formato.setCableadoEstantesComen(dcPower.getPARAM_STPF_DC_CAMP7());
        Formato.setRetireTapasEquiposComen(dcPower.getPARAM_ITPF_DC_CAMP1());
        Formato.setVisualmenteInspeccionComen(dcPower.getPARAM_ITPF_DC_CAMP2());
        Formato.setAyudaCamaraTermograficaComen(dcPower.getPARAM_ITPF_DC_CAMP3());
        Formato.setEtapaRectificadoresComen(dcPower.getPARAM_ITPF_DC_CAMP4());
        Formato.setPanelGabineteACComen(dcPower.getPARAM_ITPF_DC_CAMP5());
        Formato.setPanelGabineteDCComen(dcPower.getPARAM_ITPF_DC_CAMP6());
        Formato.setCableadoAlimentacionComen(dcPower.getPARAM_ITPF_DC_CAMP7());
        Formato.setPGSDDCModelo(dcPower.getPARAM_SISTEMA_DIST_MODELO());
        Formato.setPGSDDCMarca(dcPower.getPARAM_SISTEMA_DIST_MARCA());
        Formato.setPGSDDCNoEspecifico(dcPower.getPARAM_SISTEMA_DIST_ESPECIFI());
        Formato.setPGSDDCCapacidad(dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD());
        Formato.setPGSDDCConfiguracion(dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION());
        Formato.setLimpiezaGabinete(dcPower.getPARAM_IDR_CAMP1());
        Formato.setVerificacionEstado(dcPower.getPARAM_IDR_CAMP2());
        Formato.setVerificarGabinete(dcPower.getPARAM_IDR_CAMP3());
        Formato.setVerificarTorque(dcPower.getPARAM_IDR_CAMP4());
        Formato.setVerificarTorqueCargasGabinete(dcPower.getPARAM_IDR_CAMP5());
        Formato.setVerificarModulos(dcPower.getPARAM_IDR_CAMP6());
        Formato.setValoresReales(dcPower.getPARAM_IDR_CAMP7());
        Formato.setBreakers1(dcPower.getPARAM_IDR_BREAK1());
        Formato.setBreakers2(dcPower.getPARAM_IDR_BREAK2());
        Formato.setBreakers3(dcPower.getPARAM_IDR_BREAK3());
        Formato.setBreakers4(dcPower.getPARAM_IDR_BREAK4());
        Formato.setBreakers5(dcPower.getPARAM_IDR_BREAK5());
        Formato.setBreakers6(dcPower.getPARAM_IDR_BREAK6());
        Formato.setBreakers7(dcPower.getPARAM_IDR_BREAK7());
        Formato.setBreakers8(dcPower.getPARAM_IDR_BREAK8());
        Formato.setPGSIModelo(dcPower.getPARAM_SISTEMA_INVER_MODELO());
        Formato.setPGSIMarca(dcPower.getPARAM_SISTEMA_INVER_MARCA());
        Formato.setPGSINoEspecificacion(dcPower.getPARAM_SISTEMA_INVER_ESPEC());
        Formato.setPGSICapacidad(dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD());
        Formato.setPGSIConfiguracion(dcPower.getPARAM_SISTEMA_INVER_CONFG());
        Formato.setNoSerieLineaAINV1(dcPower.getPARAM_SISTEMA_INVER_A_INV1());
        Formato.setNoSerieLineaAINV2(dcPower.getPARAM_SISTEMA_INVER_A_INV2());
        Formato.setNoSerieLineaAINV3(dcPower.getPARAM_SISTEMA_INVER_A_INV3());
        Formato.setNoSerieLineaAINV4(dcPower.getPARAM_SISTEMA_INVER_A_INV4());
        Formato.setNoSerieLineaAINV5(dcPower.getPARAM_SISTEMA_INVER_A_INV5());
        Formato.setNoSerieLineaAINV6(dcPower.getPARAM_SISTEMA_INVER_A_INV6());
        Formato.setNoSerieLineaAINV7(dcPower.getPARAM_SISTEMA_INVER_A_INV7());
        Formato.setNoSerieLineaAINV8(dcPower.getPARAM_SISTEMA_INVER_A_INV8());
        Formato.setNoSerieLineaAINV9(dcPower.getPARAM_SISTEMA_INVER_A_INV9());
        Formato.setNoSerieLineaAINV10(dcPower.getPARAM_SISTEMA_INVER_A_INV10());
        Formato.setNoSerieLineaBINV1(dcPower.getPARAM_SISTEMA_INVER_B_INV1());
        Formato.setNoSerieLineaBINV2(dcPower.getPARAM_SISTEMA_INVER_B_INV2());
        Formato.setNoSerieLineaBINV3(dcPower.getPARAM_SISTEMA_INVER_B_INV3());
        Formato.setNoSerieLineaBINV4(dcPower.getPARAM_SISTEMA_INVER_B_INV4());
        Formato.setNoSerieLineaBINV5(dcPower.getPARAM_SISTEMA_INVER_B_INV5());
        Formato.setNoSerieLineaBINV6(dcPower.getPARAM_SISTEMA_INVER_B_INV6());
        Formato.setNoSerieLineaBINV7(dcPower.getPARAM_SISTEMA_INVER_B_INV7());
        Formato.setNoSerieLineaBINV8(dcPower.getPARAM_SISTEMA_INVER_B_INV8());
        Formato.setNoSerieLineaBINV9(dcPower.getPARAM_SISTEMA_INVER_B_INV9());
        Formato.setNoSerieLineaBINV10(dcPower.getPARAM_SISTEMA_INVER_B_INV10());
        Formato.setNoSerieLineaCINV1(dcPower.getPARAM_SISTEMA_INVER_C_INV1());
        Formato.setNoSerieLineaCINV2(dcPower.getPARAM_SISTEMA_INVER_C_INV2());
        Formato.setNoSerieLineaCINV3(dcPower.getPARAM_SISTEMA_INVER_C_INV3());
        Formato.setNoSerieLineaCINV4(dcPower.getPARAM_SISTEMA_INVER_C_INV4());
        Formato.setNoSerieLineaCINV5(dcPower.getPARAM_SISTEMA_INVER_C_INV5());
        Formato.setNoSerieLineaCINV6(dcPower.getPARAM_SISTEMA_INVER_C_INV6());
        Formato.setNoSerieLineaCINV7(dcPower.getPARAM_SISTEMA_INVER_C_INV7());
        Formato.setNoSerieLineaCINV8(dcPower.getPARAM_SISTEMA_INVER_C_INV8());
        Formato.setNoSerieLineaCINV9(dcPower.getPARAM_SISTEMA_INVER_C_INV9());
        Formato.setNoSerieLineaCINV10(dcPower.getPARAM_SISTEMA_INVER_C_INV10());
        Formato.setVerificarManeraVisualComen(dcPower.getPARAM_SISTEMA_INVER_VERI1());
        Formato.setVerificarReportarExistenciaComen(dcPower.getPARAM_SISTEMA_INVER_VERI2());
        Formato.setVerificacionBuenEstadoComen(dcPower.getPARAM_SISTEMA_INVER_VERI3());
        Formato.setVerificarRegistrarCargaComen(dcPower.getPARAM_SISTEMA_INVER_VERI4());
        Formato.setLimpiezaGabineteInversor(dcPower.getPARAM_SISTEMA_INVER_LIMP());
        Formato.setExtraerModuloCom(dcPower2.getCampoextra1());
        Formato.setColocarModuloInversorPosicionOriginalCom(dcPower2.getCampoextra2());
        Formato.setVerificarGabineteConectadoSistemaTierrasCom(dcPower2.getCampoextra3());
        Formato.setVerificarTorqueCableadoEntradaSalidaSistemaInversorcom(dcPower2.getCampoextra4());
        Formato.setExtrerComputadoraInventarioSistemaCom(dcPower2.getCampoextra5());
        Formato.setVerificarModulosSupervisionSistemaCom(dcPower2.getCampoextra6());
        Formato.setVoltajeFaseABVac(dcPower.getPARAM_NAyS_ALIM_VAC1());
        Formato.setVoltajeFaseBCVac(dcPower.getPARAM_NAyS_ALIM_VAC2());
        Formato.setVoltajeFaseCAVac(dcPower.getPARAM_NAyS_ALIM_VAC3());
        Formato.setVoltajeFaseANVac(dcPower.getPARAM_NAyS_ALIM_VAC4());
        Formato.setVoltajeFaseBNVac(dcPower.getPARAM_NAyS_ALIM_VAC5());
        Formato.setVoltajeFaseCNVac(dcPower.getPARAM_NAyS_ALIM_VAC6());
        Formato.setVoltajeNeutroVac(dcPower.getPARAM_NAyS_ALIM_VAC7());
        Formato.setVoltajeAlimentacionDCVAC(dcPower.getPARAM_NAyS_ALIM_VAC8());
        Formato.setVoltajeFaseABSVac(dcPower.getPARAM_NAyS_SALID_VAC1());
        Formato.setVoltajeFaseBCSVac(dcPower.getPARAM_NAyS_SALID_VAC2());
        Formato.setVoltajeFaseCASVac(dcPower.getPARAM_NAyS_SALID_VAC3());
        Formato.setVoltajeFaseANSVac(dcPower.getPARAM_NAyS_SALID_VAC4());
        Formato.setVoltajeFaseBNSVac(dcPower.getPARAM_NAyS_SALID_VAC5());
        Formato.setVoltajeFaseCNSVac(dcPower.getPARAM_NAyS_SALID_VAC6());
        Formato.setVoltajeNeutroSVac(dcPower.getPARAM_NAyS_SALID_VAC7());
        Formato.setVoltajeFaseABACA(dcPower.getPARAM_NAyS_ALIM_ACA1());
        Formato.setVoltajeFaseBCACA(dcPower.getPARAM_NAyS_ALIM_ACA2());
        Formato.setVoltajeFaseCAACA(dcPower.getPARAM_NAyS_ALIM_ACA3());
        Formato.setVoltajeFaseANACA(dcPower.getPARAM_NAyS_ALIM_ACA4());
        Formato.setVoltajeFaseBNACA(dcPower.getPARAM_NAyS_ALIM_ACA5());
        Formato.setVoltajeFaseCNACA(dcPower.getPARAM_NAyS_ALIM_ACA6());
        Formato.setVoltajeNeutroACA(dcPower.getPARAM_NAyS_ALIM_ACA7());
        Formato.setVoltajeAlimentacionDCACA(dcPower.getPARAM_NAyS_ALIM_ACA8());
        Formato.setVoltajeFaseABSACA(dcPower.getPARAM_NAyS_SALID_ACA1());
        Formato.setVoltajeFaseBCSACA(dcPower.getPARAM_NAyS_SALID_ACA2());
        Formato.setVoltajeFaseCASACA(dcPower.getPARAM_NAyS_SALID_ACA3());
        Formato.setVoltajeFaseANSACA(dcPower.getPARAM_NAyS_SALID_ACA4());
        Formato.setVoltajeFaseBNSACA(dcPower.getPARAM_NAyS_SALID_ACA5());
        Formato.setVoltajeFaseCNSACA(dcPower.getPARAM_NAyS_SALID_ACA6());
        Formato.setVoltajeNeutroSACA(dcPower.getPARAM_NAyS_SALID_ACA7());



        //Modulo 3
        Formato.setActividad(dcPower2.getCOMENTA_ACTIVIDADES());
        Formato.setDiagnostico(dcPower2.getCOMENTA_DIAGNOSTICO());
        Formato.setAccionCorrectiva(dcPower2.getCOMENTA_ACCION());
        Formato.setComentariosRecomendaciones(dcPower2.getCOMENTA_COMENTARIOS());

        //Modulo 4
        Formato.setCantidad1(dcPower2.getMATERIA_CANTIDAD1());
        Formato.setCantidad2(dcPower2.getMATERIA_CANTIDAD2());
        Formato.setCantidad3(dcPower2.getMATERIA_CANTIDAD3());
        Formato.setCantidad4(dcPower2.getMATERIA_CANTIDAD4());
        Formato.setCantidad5(dcPower2.getMATERIA_CANTIDAD5());
        Formato.setCantidad6(dcPower2.getMATERIA_CANTIDAD6());
        Formato.setCantidad7(dcPower2.getMATERIA_CANTIDAD7());
        Formato.setNoParte1(dcPower2.getMATERIA_N_PARTE1());
        Formato.setNoParte2(dcPower2.getMATERIA_N_PARTE2());
        Formato.setNoParte3(dcPower2.getMATERIA_N_PARTE3());
        Formato.setNoParte4(dcPower2.getMATERIA_N_PARTE4());
        Formato.setNoParte5(dcPower2.getMATERIA_N_PARTE5());
        Formato.setNoParte6(dcPower2.getMATERIA_N_PARTE6());
        Formato.setNoParte7(dcPower2.getMATERIA_N_PARTE7());
        Formato.setDescripcion1(dcPower2.getMATERIA_DESC1());
        Formato.setDescripcion2(dcPower2.getMATERIA_DESC2());
        Formato.setDescripcion3(dcPower2.getMATERIA_DESC3());
        Formato.setDescripcion4(dcPower2.getMATERIA_DESC4());
        Formato.setDescripcion5(dcPower2.getMATERIA_DESC5());
        Formato.setDescripcion6(dcPower2.getMATERIA_DESC6());
        Formato.setDescripcion7(dcPower2.getMATERIA_DESC7());
        Formato.setEquipo1(dcPower2.getMATERIA_EQUIPO_EQUIPO1());
        Formato.setEquipo2(dcPower2.getMATERIA_EQUIPO_EQUIPO2());
        Formato.setEquipo3(dcPower2.getMATERIA_EQUIPO_EQUIPO3());
        Formato.setEquipo4(dcPower2.getMATERIA_EQUIPO_EQUIPO4());
        Formato.setEquipo5(dcPower2.getMATERIA_EQUIPO_EQUIPO5());
        //Formato.setEMEquipo6(dcPower2.getMATERIA_EQUIPO_EQUIPO6());
        //Formato.setEMEquipo7(dcPower2.getequipo7);
        Formato.setNoId1(dcPower2.getMATERIA_EQUIPO_ID1());
        Formato.setNoId2(dcPower2.getMATERIA_EQUIPO_ID2());
        Formato.setNoId3(dcPower2.getMATERIA_EQUIPO_ID3());
        Formato.setNoId4(dcPower2.getMATERIA_EQUIPO_ID4());
        Formato.setNoId5(dcPower2.getMATERIA_EQUIPO_ID5());
        //Formato.setNoID6(dcPower2.getMATERIA_EQUIPO_ID6());
        // Formato.setNoID7(dcPower2.getn);
        Formato.setFechaVencimiento1(dcPower2.getMATERIA_EQUIPO_FEVHA1());
        Formato.setFechaVencimiento2(dcPower2.getMATERIA_EQUIPO_FEVHA2());
        Formato.setFechaVencimiento3(dcPower2.getMATERIA_EQUIPO_FEVHA3());
        Formato.setFechaVencimiento4(dcPower2.getMATERIA_EQUIPO_FEVHA4());
        Formato.setFechaVencimiento5(dcPower2.getMATERIA_EQUIPO_FEVHA5());
        //Formato.setFechaVencimiento6(dcPower2.getMATERIA_EQUIPO_FEVHA6());
        //Formato.setFechaVencimiento7(cdPower2ge);

        //Modulo 5
        Formato.setAntesFoto1(dcPower2.getFOTOS_ANTES1());
        Formato.setAntesFoto2(dcPower2.getFOTOS_ANTES2());
        Formato.setAntesFoto3(dcPower2.getFOTOS_ANTES3());
        Formato.setAntesFoto4(dcPower2.getFOTOS_ANTES4());
        Formato.setAntesFoto5(dcPower2.getFOTOS_ANTES5());
        Formato.setAntesFoto6(dcPower2.getFOTOS_ANTES6());
        Formato.setDespuesFoto1(dcPower2.getFOTOS_DESPUES1());
        Formato.setDespuesFoto2(dcPower2.getFOTOS_DESPUES2());
        Formato.setDespuesFoto3(dcPower2.getFOTOS_DESPUES3());
        Formato.setDespuesFoto4(dcPower2.getFOTOS_DESPUES4());
        Formato.setDespuesFoto5(dcPower2.getFOTOS_DESPUES5());
        Formato.setDespuesFoto6(dcPower2.getFOTOS_DESPUES6());

        //Modulo 7
        Formato.setFirmaCliente(dcPower2.getFIRMA_FIRMA1());
        Formato.setFirmaVertiv(dcPower2.getFIRMA_FIRMA2());
        Formato.setFirmaClienteFinal(dcPower2.getFIRMA_FIRMA3());
        Formato.setNombreFirmaCliente(dcPower2.getFIRMA_NOMBRE1());
        Formato.setNombreFirmaVertiv(dcPower2.getFIRMA_NOMBRE2());
        Formato.setNombreFirmaClienteFinal(dcPower2.getFIRMA_NOMBRE3());
        Formato.setCLIENTEFINAL_EMPRESA(dcPower2.getFIRMA_EMPRESA());
        Formato.setCLIENTEFINAL_TELEFONO(dcPower2.getFIRMA_TELEFONO());
        Formato.setCLIENTEFINAL_CORREO(dcPower2.getFIRMA_CORREO());

        //Modulo 8
        Formato.setAD_NOMBRE1(dcPower2.getADICIONALES_NOMBRE1());
        Formato.setAD_CORREO1(dcPower2.getFIRMA_CORREO1());
        Formato.setAD_NOMBRE2(dcPower2.getADICIONALES_NOMBRE2());
        Formato.setAD_CORREO2(dcPower2.getFIRMA_CORREO2());
        Formato.setAD_NOMBRE3(dcPower2.getADICIONALES_NOMBRE3());
        Formato.setAD_CORREO3(dcPower2.getFIRMA_CORREO3());
        Formato.setAD_NOMBRE4(dcPower2.getADICIONALES_NOMBRE4());
        Formato.setAD_CORREO4(dcPower2.getFIRMA_CORREO4());
        Formato.setAD_NOMBRE5(dcPower2.getADICIONALES_NOMBRE5());
        Formato.setAD_CORREO5(dcPower2.getFIRMA_CORREO5());

        /*try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
         */

        //endregion
        //region enviar formato
        Formato.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarDCPower(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.maju.desarrollo.testfcs.ServiceClass.DCPower>() {
                    @Override
                    public void accept(com.maju.desarrollo.testfcs.ServiceClass.DCPower lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if (lista.getExitoso().equals("1")) {
                            enviarFotos(lista.getFolioPreTrabajo());
                            D_B.actualizarEsatusFormato("3",id_formato);
                            Bundle valores = new Bundle();
                            valores.putString("mensaje","Folio:" + lista.getFolioPreTrabajo().toString());
                            AlertaOk alerta = new AlertaOk();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");

                            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                            FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();

                            //Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

                        } else {
                            D_B.newMensaje(lista.getError());
                            Bundle valores = new Bundle();
                            valores.putString("titulo", "Error");
                            valores.putString("mensaje", "Se genero un error: " + lista.getError());

                            AlertaGenerica alerta = new AlertaGenerica();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        alerta.dismiss();
                    }
                })

        );
        //endregion
    }

    public void enviarFotos(String idFormatoService){
        //region Enviar_Fotos

        try{if(Formato.getAntesFoto1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
            item.setFormato(Formato.getAntesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
            item.setFormato(Formato.getAntesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
            item.setFormato(Formato.getAntesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
            item.setFormato(Formato.getAntesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
            item.setFormato(Formato.getAntesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
            item.setFormato(Formato.getAntesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
            item.setFormato(Formato.getDespuesFoto1());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
            item.setFormato(Formato.getDespuesFoto2());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
            item.setFormato(Formato.getDespuesFoto3());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
            item.setFormato(Formato.getDespuesFoto4());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
            item.setFormato(Formato.getDespuesFoto5());
            ((MainActivity) getActivity()).imagenesEnvio(item);
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            FotosFormato item = new FotosFormato();
            item.setId(idFormatoService);
            item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
            item.setFormato(Formato.getDespuesFoto6());
            ((MainActivity) getActivity()).imagenesEnvio(item);

        }}catch (Exception e){}
        //endregion
    }
}
