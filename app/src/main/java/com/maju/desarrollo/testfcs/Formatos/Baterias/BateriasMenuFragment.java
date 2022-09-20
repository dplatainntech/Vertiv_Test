package com.maju.desarrollo.testfcs.Formatos.Baterias;


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
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.BateriaS;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriasMenuFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    Baterias bateria;
    int completo;
    Button Terminar;
    InternetandVPN validaciones = new InternetandVPN();
    UsuarioD usuario;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImgB64 b64 = new ImgB64();
    progresoLoad alerta = new progresoLoad();
    BateriaS Formato = new BateriaS();
    SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

    public BateriasMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_baterias_menu, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        ((MainActivity) getActivity()).verCabecera();
        id_formato= getArguments().getString("key_idFormato");
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        usuario = D_B.obtenerUsuario();
        if(id_formato.equals("NUEVO")){
            //se crea nueva Formato y se regresa el id
            id_formato = D_B.nuevoBateria();  // = nuevaPreOrden()
            D_B.nuevoRegistroFormato(id_formato,"2"); //crear registrode formato. Baterias
            bateria = D_B.obtenerBateriaF_id(id_formato);
        }else{
            bateria = D_B.obtenerBateriaF_id(id_formato);
        }


        LinearLayout M1 = (LinearLayout)v.findViewById(R.id.MN_M1);
        LinearLayout M2 = (LinearLayout)v.findViewById(R.id.MN_M2);
        LinearLayout M3 = (LinearLayout)v.findViewById(R.id.MN_M3);
        LinearLayout M4 = (LinearLayout)v.findViewById(R.id.MN_M4);
        LinearLayout M5 = (LinearLayout)v.findViewById(R.id.MN_M5);
        LinearLayout M6 = (LinearLayout)v.findViewById(R.id.MN_M6);
        LinearLayout M7 = (LinearLayout)v.findViewById(R.id.MN_M7);
        LinearLayout M8 = (LinearLayout)v.findViewById(R.id.MN_M8);
        ImageView im1 = (ImageView)v.findViewById(R.id.imgM1);
        ImageView im2 = (ImageView)v.findViewById(R.id.imgM2);
        ImageView im3 = (ImageView)v.findViewById(R.id.imgM3);
        ImageView im4 = (ImageView)v.findViewById(R.id.imgM4);
        ImageView im5 = (ImageView)v.findViewById(R.id.imgM5);
        ImageView im6 = (ImageView)v.findViewById(R.id.imgM6);
        ImageView im7 = (ImageView)v.findViewById(R.id.imgM7);
        ImageView im8 = (ImageView)v.findViewById(R.id.imgM8);
        ImageView im9 = (ImageView)v.findViewById(R.id.imgM9);



        //region Botones acciones




        M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                BateriasGeneralesFragment myfargment = new BateriasGeneralesFragment();
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

                BateriasBateriaFragment myfargment = new BateriasBateriaFragment();
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

                BateriasMedicionesFragment myfargment = new BateriasMedicionesFragment();
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

                BateriasComentariosFragment myfargment = new BateriasComentariosFragment();
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

                BateriasMaterialesFragment myfargment = new BateriasMaterialesFragment();
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

                BateriasFotosFragment myfargment = new BateriasFotosFragment();
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

                BateriasFirmas myfargment = new BateriasFirmas();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });
        M8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                BateriasAdicionalesFragment myfargment = new BateriasAdicionalesFragment();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

//endregion

        completo = 8;

        //region Modulo  Generales
        int M1_generales = 13;

        try{if(!bateria.getGRAL_folioPT().isEmpty() || !bateria.getGRAL_proyecto().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_cliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_direccion().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_contacto().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_telefono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_mail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_proyecto().isEmpty() || !bateria.getGRAL_task().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_proyecto().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_eferencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_sr().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_task().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_tipoServ().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_tiposervOtro().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_freciencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_noTag().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_modelo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_serie1().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        //try{if(!bateria.getGRAL_serie2().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_fecha1().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
        try{if(!bateria.getGRAL_fecha2().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}


        if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
            completo = completo-1;}
        else if(M1_generales < 13){im1.setImageResource(R.drawable.generalnar);}
        else {im1.setImageResource(R.drawable.generalgris);}

        //endregion

        //region Modulo 2
        int M2_ = 120;
        //region
        try{if(!bateria.getVDC1().isEmpty() && !bateria.getVAC1().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC2().isEmpty() && !bateria.getVAC2().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC3().isEmpty() && !bateria.getVAC3().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC4().isEmpty() && !bateria.getVAC4().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC5().isEmpty() && !bateria.getVAC5().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC6().isEmpty() && !bateria.getVAC6().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC7().isEmpty() && !bateria.getVAC7().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC8().isEmpty() && !bateria.getVAC8().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC9().isEmpty() && !bateria.getVAC9().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC10().isEmpty() && !bateria.getVAC10().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC11().isEmpty() && !bateria.getVAC11().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC12().isEmpty() && !bateria.getVAC12().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC13().isEmpty() && !bateria.getVAC13().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC14().isEmpty() && !bateria.getVAC14().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC15().isEmpty() && !bateria.getVAC15().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC16().isEmpty() && !bateria.getVAC16().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC17().isEmpty() && !bateria.getVAC17().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC18().isEmpty() && !bateria.getVAC18().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC19().isEmpty() && !bateria.getVAC19().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC20().isEmpty() && !bateria.getVAC20().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC21().isEmpty() && !bateria.getVAC21().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC22().isEmpty() && !bateria.getVAC22().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC23().isEmpty() && !bateria.getVAC23().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC24().isEmpty() && !bateria.getVAC24().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC25().isEmpty() && !bateria.getVAC25().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC26().isEmpty() && !bateria.getVAC26().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC27().isEmpty() && !bateria.getVAC27().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC28().isEmpty() && !bateria.getVAC28().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC29().isEmpty() && !bateria.getVAC29().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC30().isEmpty() && !bateria.getVAC30().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC31().isEmpty() && !bateria.getVAC31().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC32().isEmpty() && !bateria.getVAC32().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC33().isEmpty() && !bateria.getVAC33().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC34().isEmpty() && !bateria.getVAC34().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC35().isEmpty() && !bateria.getVAC35().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC36().isEmpty() && !bateria.getVAC36().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC37().isEmpty() && !bateria.getVAC37().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC38().isEmpty() && !bateria.getVAC38().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC39().isEmpty() && !bateria.getVAC39().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC40().isEmpty() && !bateria.getVAC40().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC41().isEmpty() && !bateria.getVAC41().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC42().isEmpty() && !bateria.getVAC42().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC43().isEmpty() && !bateria.getVAC43().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC44().isEmpty() && !bateria.getVAC44().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC45().isEmpty() && !bateria.getVAC45().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC46().isEmpty() && !bateria.getVAC46().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC47().isEmpty() && !bateria.getVAC47().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC48().isEmpty() && !bateria.getVAC48().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC49().isEmpty() && !bateria.getVAC49().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC50().isEmpty() && !bateria.getVAC50().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC51().isEmpty() && !bateria.getVAC51().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC52().isEmpty() && !bateria.getVAC52().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC53().isEmpty() && !bateria.getVAC53().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC54().isEmpty() && !bateria.getVAC54().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC55().isEmpty() && !bateria.getVAC55().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC56().isEmpty() && !bateria.getVAC56().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC57().isEmpty() && !bateria.getVAC57().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC58().isEmpty() && !bateria.getVAC58().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC59().isEmpty() && !bateria.getVAC59().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC60().isEmpty() && !bateria.getVAC60().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC61().isEmpty() && !bateria.getVAC61().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC62().isEmpty() && !bateria.getVAC62().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC63().isEmpty() && !bateria.getVAC63().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC64().isEmpty() && !bateria.getVAC64().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC65().isEmpty() && !bateria.getVAC65().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC66().isEmpty() && !bateria.getVAC66().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC67().isEmpty() && !bateria.getVAC67().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC68().isEmpty() && !bateria.getVAC68().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC69().isEmpty() && !bateria.getVAC69().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC70().isEmpty() && !bateria.getVAC70().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC71().isEmpty() && !bateria.getVAC71().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC72().isEmpty() && !bateria.getVAC72().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC73().isEmpty() && !bateria.getVAC73().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC74().isEmpty() && !bateria.getVAC74().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC75().isEmpty() && !bateria.getVAC75().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC76().isEmpty() && !bateria.getVAC76().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC77().isEmpty() && !bateria.getVAC77().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC78().isEmpty() && !bateria.getVAC78().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC79().isEmpty() && !bateria.getVAC79().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC80().isEmpty() && !bateria.getVAC80().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC81().isEmpty() && !bateria.getVAC81().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC82().isEmpty() && !bateria.getVAC82().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC83().isEmpty() && !bateria.getVAC83().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC84().isEmpty() && !bateria.getVAC84().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC85().isEmpty() && !bateria.getVAC85().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC86().isEmpty() && !bateria.getVAC86().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC87().isEmpty() && !bateria.getVAC87().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC88().isEmpty() && !bateria.getVAC88().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC89().isEmpty() && !bateria.getVAC89().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC90().isEmpty() && !bateria.getVAC90().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC91().isEmpty() && !bateria.getVAC91().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC92().isEmpty() && !bateria.getVAC92().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC93().isEmpty() && !bateria.getVAC93().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC94().isEmpty() && !bateria.getVAC94().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC95().isEmpty() && !bateria.getVAC95().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC96().isEmpty() && !bateria.getVAC96().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC97().isEmpty() && !bateria.getVAC97().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC98().isEmpty() && !bateria.getVAC98().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC99().isEmpty() && !bateria.getVAC99().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC100().isEmpty() && !bateria.getVAC100().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC101().isEmpty() && !bateria.getVAC101().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC102().isEmpty() && !bateria.getVAC102().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC103().isEmpty() && !bateria.getVAC103().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC104().isEmpty() && !bateria.getVAC104().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC105().isEmpty() && !bateria.getVAC105().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC106().isEmpty() && !bateria.getVAC106().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC107().isEmpty() && !bateria.getVAC107().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC108().isEmpty() && !bateria.getVAC108().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC109().isEmpty() && !bateria.getVAC109().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC110().isEmpty() && !bateria.getVAC110().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC111().isEmpty() && !bateria.getVAC111().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC112().isEmpty() && !bateria.getVAC112().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC113().isEmpty() && !bateria.getVAC113().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC114().isEmpty() && !bateria.getVAC114().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC115().isEmpty() && !bateria.getVAC115().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC116().isEmpty() && !bateria.getVAC116().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC117().isEmpty() && !bateria.getVAC117().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC118().isEmpty() && !bateria.getVAC118().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC119().isEmpty() && !bateria.getVAC119().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}
        try{if(!bateria.getVDC120().isEmpty() && !bateria.getVAC120().isEmpty()){ M2_ =  M2_-1;}}catch (Exception e){}

            //region
        int bC=0;
        try{if(!bateria.getVAC1().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC2().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC3().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC4().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC5().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC6().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC7().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC8().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC9().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC10().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC11().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC12().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC13().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC14().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC15().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC16().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC17().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC18().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC19().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC20().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC21().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC22().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC23().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC24().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC25().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC26().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC27().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC28().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC29().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC30().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC31().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC32().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC33().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC34().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC35().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC36().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC37().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC38().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC39().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC40().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC41().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC42().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC43().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC44().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC45().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC46().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC47().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC48().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC49().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC50().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC51().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC52().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC53().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC54().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC55().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC56().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC57().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC58().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC59().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC60().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC61().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC62().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC63().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC64().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC65().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC66().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC67().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC68().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC69().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC70().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC71().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC72().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC73().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC74().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC75().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC76().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC77().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC78().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC79().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC80().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC81().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC82().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC83().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC84().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC85().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC86().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC87().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC88().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC89().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC90().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC91().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC92().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC93().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC94().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC95().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC96().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC97().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC98().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC99().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC100().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC101().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC102().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC103().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC104().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC105().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC106().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC107().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC108().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC109().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC110().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC111().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC112().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC113().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC114().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC115().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC116().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC117().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC118().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC119().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVAC120().isEmpty()){bC++;}}catch (Exception e){}

        try{if(!bateria.getVDC1().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC2().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC3().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC4().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC5().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC6().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC7().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC8().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC9().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC10().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC11().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC12().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC13().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC14().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC15().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC16().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC17().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC18().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC19().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC20().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC21().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC22().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC23().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC24().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC25().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC26().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC27().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC28().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC29().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC30().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC31().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC32().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC33().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC34().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC35().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC36().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC37().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC38().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC39().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC40().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC41().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC42().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC43().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC44().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC45().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC46().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC47().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC48().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC49().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC50().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC51().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC52().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC53().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC54().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC55().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC56().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC57().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC58().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC59().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC60().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC61().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC62().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC63().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC64().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC65().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC66().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC67().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC68().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC69().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC70().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC71().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC72().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC73().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC74().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC75().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC76().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC77().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC78().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC79().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC80().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC81().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC82().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC83().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC84().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC85().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC86().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC87().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC88().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC89().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC90().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC91().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC92().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC93().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC94().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC95().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC96().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC97().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC98().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC99().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC100().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC101().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC102().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC103().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC104().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC105().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC106().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC107().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC108().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC109().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC110().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC111().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC112().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC113().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC114().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC115().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC116().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC117().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC118().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC119().isEmpty()){bC++;}}catch (Exception e){}
        try{if(!bateria.getVDC120().isEmpty()){bC++;}}catch (Exception e){}
            //endregion

        // endregion

        if(M2_ < 120){im2.setImageResource(R.drawable.bateriaverde);
            completo = completo-1;}
        else
            if(bC>0){  im2.setImageResource(R.drawable.baterianar); }else{
            im2.setImageResource(R.drawable.bateriagris);}
        //endregion

        //region Modulo 3
        int  M3_ = 14;
        try{if(!bateria.getModeloMarca().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getAparenciaLimpieza().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getJarrasCubiertasSellado().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getTemperaturaBaterias().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getTemperaturaAmbiente().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getTorque().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getTerminales().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getCodigoFecha().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getAñosServicio().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getConectoresTornillos().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getVoltajeFlotacionVDC().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getCorrienteFlotacion().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getCorrienteRizo().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}
        try{if(!bateria.getVoltajeRizo().isEmpty()){M3_ = M3_ - 1;}}catch (Exception e){}

        if(M3_ == 0){ im3.setImageResource(R.drawable.medicionverde);
            completo = completo-1;}
        else if(M3_ < 14){im3.setImageResource(R.drawable.medicionnar);}
        else {im3.setImageResource(R.drawable.mediciongris);}
        //endregion

        //region Modulo Comentarios
        int M4_ = 1;
        try{if(!bateria.getCOMENT_cometarios().isEmpty()){M4_ = M4_ - 1;}}catch (Exception e){}

        if(M4_ == 0){ im4.setImageResource(R.drawable.comentariosverde);
            completo = completo-1;}
        else {im4.setImageResource(R.drawable.comentariosgris);}

        //endregion

        //region Modulo Materiales
        int M5_A = 24;
        int M5_B = 18;

        try{if(bateria.getMATE_cantidad1().equals(" ")){
            M5_A = M5_A - 24;
        }
        else{
            try{if(!bateria.getMATE_cantidad1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_cantidad8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_parte8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_especifica8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}

        }}catch(Exception e){}
        try{if(bateria.getMATE_equipo1().equals(" ")){
            M5_B = M5_B - 18;
        }
        else{
            try{if(!bateria.getMATE_equipo1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_equipo2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_equipo3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_equipo4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_equipo5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_equipo6().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_nid6().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
            try{if(!bateria.getMATE_fecha6().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
        }}catch(Exception e){}

        if((M5_A + M5_B) == 0){ im5.setImageResource(R.drawable.materialesverde);
            completo = completo-1;}
        else if((M5_A < 24 && M5_B==18) || (M5_A == 24 && M5_B<18) ){
            im5.setImageResource(R.drawable.materialenar);
        }
        else if(M5_A <24 && M5_B <18){
            im5.setImageResource(R.drawable.materialesverde);
            completo = completo-1;
        }
        else {im5.setImageResource(R.drawable.materialesgris);}

        //endregion

        //region Modulo Fotos
        int M6_ = 12;
        int M_Foto1 = 2;
        int M_Foto2 = 2;
        int M_Foto3 = 2;
        int M_Foto4 = 2;
        int M_Foto5 = 2;
        int M_Foto6 = 2;

        try{if(!bateria.getFOTOS_D1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_D2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_D3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_D4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_D5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_D6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
        try{if(!bateria.getFOTOS_A6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

        int constante1 = M_Foto1 + M_Foto2+ M_Foto3 +M_Foto4+M_Foto5+M_Foto6;
        if(constante1 == 12){
            im6.setImageResource(R.drawable.fotogris);
        }else{
            if(constante1== 0){
                im6.setImageResource(R.drawable.fotoverde);
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
                    im6.setImageResource(R.drawable.fotoverde);
                    completo = completo-1;
                }else {
                    im6.setImageResource(R.drawable.fotonar);
                }
            }
        }



        //endregion

        //region Modulo Firmas
        int M7_ = 2;
        int obliga =2;
        int M7_Fr = 0;

        try{if(!bateria.getFIRMA_IMG3().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_NOMBRE3().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_IMG1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_NOMBRE1().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_IMG2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_NOMBRE2().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_CORREO().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_EMPRESA().isEmpty()){M7_Fr++;}}catch (Exception e){}
        try{if(!bateria.getFIRMA_TELEFONO().isEmpty()){M7_Fr++;}}catch (Exception e){}

        try{
            if(!bateria.getFIRMA_IMG3().isEmpty() && !bateria.getFIRMA_NOMBRE3().isEmpty()){
                try{if(!bateria.getFIRMA_CORREO().isEmpty()&&
                        !bateria.getFIRMA_EMPRESA().isEmpty()&&
                        !bateria.getFIRMA_TELEFONO().isEmpty()){M7_ = M7_-1;}
                }catch (Exception e){}{
                }
            }
        }catch (Exception e){}


        try {
            if (!bateria.getFIRMA_IMG1().isEmpty()&& !bateria.getFIRMA_NOMBRE1().isEmpty()){ M7_ = M7_-1;   }
        } catch (Exception e) {
        }

        try {
            if (!bateria.getFIRMA_IMG2().isEmpty()){ obliga = obliga-1;   }
            if (!bateria.getFIRMA_NOMBRE2().isEmpty()){ obliga = obliga-1;   }
        } catch (Exception e) {
        }

        if(obliga == 0){
            if(M7_== 0 || M7_ == 1 ){im7.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im7.setImageResource(R.drawable.firmanar);}
            else {im7.setImageResource(R.drawable.firmagris);}
        }
        else if(M7_ < 2){im7.setImageResource(R.drawable.firmanar);}
        else {
            if(M7_Fr>0){
                im7.setImageResource(R.drawable.firmanar);
            }else{
                im7.setImageResource(R.drawable.firmagris);
            }
        }
        //endregion


        //region Modulo Adicionales
        int M8_ = 10;

            try{if(!bateria.getADICI_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_CORREO1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_CORREO2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_CORREO3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_CORREO4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
            try{if(!bateria.getADICI_CORREO5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}


        if(M8_ < 10){im8.setImageResource(R.drawable.notificaverde);
            completo = completo-1;}
        else {im8.setImageResource(R.drawable.notificagris);}
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
    public void enviarFormato() {

        //region
        final String idFormatoService = D_B.generarUID();

        if(bateria.getGRAL_proyecto().length()>1){ Formato.setSRProyecto(bateria.getGRAL_proyecto());}
        else {Formato.setSRProyecto(bateria.getGRAL_sr());}
        Formato.setIdFormatoBateria(idFormatoService);
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setIdCliente(bateria.getGRAL_cliente());
        Formato.setFolioPreTrabajo(bateria.getGRAL_folioPT());
        Formato.setFechaInicio(bateria.getGRAL_fecha1());
        Formato.setFechaTermino(bateria.getGRAL_fecha2());
        Formato.setContactoCliente(bateria.getGRAL_contacto());
        Formato.setTASK(bateria.getGRAL_task());
        Formato.setTelefono(bateria.getGRAL_telefono());
        Formato.setTipoServicio(bateria.getGRAL_tipoServ());
        Formato.setEMail(bateria.getGRAL_mail());
        Formato.setFrecuencia(bateria.getGRAL_freciencia());
        Formato.setModeloEquipo(bateria.getGRAL_modelo());
        Formato.setNoTag(bateria.getGRAL_noTag());
        Formato.setNoSerie(bateria.getGRAL_serie1());
        Formato.setDireccionSitio(bateria.getGRAL_direccion());
        Formato.setVDC1(bateria.getVDC1());
        Formato.setVDC2(bateria.getVDC2());
        Formato.setVDC3(bateria.getVDC3());
        Formato.setVDC4(bateria.getVDC4());
        Formato.setVDC5(bateria.getVDC5());
        Formato.setVDC6(bateria.getVDC6());
        Formato.setVDC7(bateria.getVDC7());
        Formato.setVDC8(bateria.getVDC8());
        Formato.setVDC9(bateria.getVDC9());
        Formato.setVDC10(bateria.getVDC10());
        Formato.setVDC11(bateria.getVDC11());
        Formato.setVDC12(bateria.getVDC12());
        Formato.setVDC13(bateria.getVDC13());
        Formato.setVDC14(bateria.getVDC14());
        Formato.setVDC15(bateria.getVDC15());
        Formato.setVDC16(bateria.getVDC16());
        Formato.setVDC17(bateria.getVDC17());
        Formato.setVDC18(bateria.getVDC18());
        Formato.setVDC19(bateria.getVDC19());
        Formato.setVDC20(bateria.getVDC20());
        Formato.setVDC21(bateria.getVDC21());
        Formato.setVDC22(bateria.getVDC22());
        Formato.setVDC23(bateria.getVDC23());
        Formato.setVDC24(bateria.getVDC24());
        Formato.setVDC25(bateria.getVDC25());
        Formato.setVDC26(bateria.getVDC26());
        Formato.setVDC27(bateria.getVDC27());
        Formato.setVDC28(bateria.getVDC28());
        Formato.setVDC29(bateria.getVDC29());
        Formato.setVDC30(bateria.getVDC30());
        Formato.setVDC31(bateria.getVDC31());
        Formato.setVDC32(bateria.getVDC32());
        Formato.setVDC33(bateria.getVDC33());
        Formato.setVDC34(bateria.getVDC34());
        Formato.setVDC35(bateria.getVDC35());
        Formato.setVDC36(bateria.getVDC36());
        Formato.setVDC37(bateria.getVDC37());
        Formato.setVDC38(bateria.getVDC38());
        Formato.setVDC39(bateria.getVDC39());
        Formato.setVDC40(bateria.getVDC40());
        Formato.setVDC41(bateria.getVDC41());
        Formato.setVDC42(bateria.getVDC42());
        Formato.setVDC43(bateria.getVDC43());
        Formato.setVDC44(bateria.getVDC44());
        Formato.setVDC45(bateria.getVDC45());
        Formato.setVDC46(bateria.getVDC46());
        Formato.setVDC47(bateria.getVDC47());
        Formato.setVDC48(bateria.getVDC48());
        Formato.setVDC49(bateria.getVDC49());
        Formato.setVDC50(bateria.getVDC50());
        Formato.setVDC51(bateria.getVDC51());
        Formato.setVDC52(bateria.getVDC52());
        Formato.setVDC53(bateria.getVDC53());
        Formato.setVDC54(bateria.getVDC54());
        Formato.setVDC55(bateria.getVDC55());
        Formato.setVDC56(bateria.getVDC56());
        Formato.setVDC57(bateria.getVDC57());
        Formato.setVDC58(bateria.getVDC58());
        Formato.setVDC59(bateria.getVDC59());
        Formato.setVDC60(bateria.getVDC60());
        Formato.setVDC61(bateria.getVDC61());
        Formato.setVDC62(bateria.getVDC62());
        Formato.setVDC63(bateria.getVDC63());
        Formato.setVDC64(bateria.getVDC64());
        Formato.setVDC65(bateria.getVDC65());
        Formato.setVDC66(bateria.getVDC66());
        Formato.setVDC67(bateria.getVDC67());
        Formato.setVDC68(bateria.getVDC68());
        Formato.setVDC69(bateria.getVDC69());
        Formato.setVDC70(bateria.getVDC70());
        Formato.setVDC71(bateria.getVDC71());
        Formato.setVDC72(bateria.getVDC72());
        Formato.setVDC73(bateria.getVDC73());
        Formato.setVDC74(bateria.getVDC74());
        Formato.setVDC75(bateria.getVDC75());
        Formato.setVDC76(bateria.getVDC76());
        Formato.setVDC77(bateria.getVDC77());
        Formato.setVDC78(bateria.getVDC78());
        Formato.setVDC79(bateria.getVDC79());
        Formato.setVDC80(bateria.getVDC80());
        Formato.setVDC81(bateria.getVDC81());
        Formato.setVDC82(bateria.getVDC82());
        Formato.setVDC83(bateria.getVDC83());
        Formato.setVDC84(bateria.getVDC84());
        Formato.setVDC85(bateria.getVDC85());
        Formato.setVDC86(bateria.getVDC86());
        Formato.setVDC87(bateria.getVDC87());
        Formato.setVDC88(bateria.getVDC88());
        Formato.setVDC89(bateria.getVDC89());
        Formato.setVDC90(bateria.getVDC90());
        Formato.setVDC91(bateria.getVDC91());
        Formato.setVDC92(bateria.getVDC92());
        Formato.setVDC93(bateria.getVDC93());
        Formato.setVDC94(bateria.getVDC94());
        Formato.setVDC95(bateria.getVDC95());
        Formato.setVDC96(bateria.getVDC96());
        Formato.setVDC97(bateria.getVDC97());
        Formato.setVDC98(bateria.getVDC98());
        Formato.setVDC99(bateria.getVDC99());
        Formato.setVDC100(bateria.getVDC100());
        Formato.setVDC101(bateria.getVDC101());
        Formato.setVDC102(bateria.getVDC102());
        Formato.setVDC103(bateria.getVDC103());
        Formato.setVDC104(bateria.getVDC104());
        Formato.setVDC105(bateria.getVDC105());
        Formato.setVDC106(bateria.getVDC106());
        Formato.setVDC107(bateria.getVDC107());
        Formato.setVDC108(bateria.getVDC108());
        Formato.setVDC109(bateria.getVDC109());
        Formato.setVDC110(bateria.getVDC110());
        Formato.setVDC111(bateria.getVDC111());
        Formato.setVDC112(bateria.getVDC112());
        Formato.setVDC113(bateria.getVDC113());
        Formato.setVDC114(bateria.getVDC114());
        Formato.setVDC115(bateria.getVDC115());
        Formato.setVDC116(bateria.getVDC116());
        Formato.setVDC117(bateria.getVDC117());
        Formato.setVDC118(bateria.getVDC118());
        Formato.setVDC119(bateria.getVDC119());
        Formato.setVDC120(bateria.getVDC120());
        Formato.setVAC1(bateria.getVAC1());
        Formato.setVAC2(bateria.getVAC2());
        Formato.setVAC3(bateria.getVAC3());
        Formato.setVAC4(bateria.getVAC4());
        Formato.setVAC5(bateria.getVAC5());
        Formato.setVAC6(bateria.getVAC6());
        Formato.setVAC7(bateria.getVAC7());
        Formato.setVAC8(bateria.getVAC8());
        Formato.setVAC9(bateria.getVAC9());
        Formato.setVAC10(bateria.getVAC10());
        Formato.setVAC11(bateria.getVAC11());
        Formato.setVAC12(bateria.getVAC12());
        Formato.setVAC13(bateria.getVAC13());
        Formato.setVAC14(bateria.getVAC14());
        Formato.setVAC15(bateria.getVAC15());
        Formato.setVAC16(bateria.getVAC16());
        Formato.setVAC17(bateria.getVAC17());
        Formato.setVAC18(bateria.getVAC18());
        Formato.setVAC19(bateria.getVAC19());
        Formato.setVAC20(bateria.getVAC20());
        Formato.setVAC21(bateria.getVAC21());
        Formato.setVAC22(bateria.getVAC22());
        Formato.setVAC23(bateria.getVAC23());
        Formato.setVAC24(bateria.getVAC24());
        Formato.setVAC25(bateria.getVAC25());
        Formato.setVAC26(bateria.getVAC26());
        Formato.setVAC27(bateria.getVAC27());
        Formato.setVAC28(bateria.getVAC28());
        Formato.setVAC29(bateria.getVAC29());
        Formato.setVAC30(bateria.getVAC30());
        Formato.setVAC31(bateria.getVAC31());
        Formato.setVAC32(bateria.getVAC32());
        Formato.setVAC33(bateria.getVAC33());
        Formato.setVAC34(bateria.getVAC34());
        Formato.setVAC35(bateria.getVAC35());
        Formato.setVAC36(bateria.getVAC36());
        Formato.setVAC37(bateria.getVAC37());
        Formato.setVAC38(bateria.getVAC38());
        Formato.setVAC39(bateria.getVAC39());
        Formato.setVAC40(bateria.getVAC40());
        Formato.setVAC41(bateria.getVAC41());
        Formato.setVAC42(bateria.getVAC42());
        Formato.setVAC43(bateria.getVAC43());
        Formato.setVAC44(bateria.getVAC44());
        Formato.setVAC45(bateria.getVAC45());
        Formato.setVAC46(bateria.getVAC46());
        Formato.setVAC47(bateria.getVAC47());
        Formato.setVAC48(bateria.getVAC48());
        Formato.setVAC49(bateria.getVAC49());
        Formato.setVAC50(bateria.getVAC50());
        Formato.setVAC51(bateria.getVAC51());
        Formato.setVAC52(bateria.getVAC52());
        Formato.setVAC53(bateria.getVAC53());
        Formato.setVAC54(bateria.getVAC54());
        Formato.setVAC55(bateria.getVAC55());
        Formato.setVAC56(bateria.getVAC56());
        Formato.setVAC57(bateria.getVAC57());
        Formato.setVAC58(bateria.getVAC58());
        Formato.setVAC59(bateria.getVAC59());
        Formato.setVAC60(bateria.getVAC60());
        Formato.setVAC61(bateria.getVAC61());
        Formato.setVAC62(bateria.getVAC62());
        Formato.setVAC63(bateria.getVAC63());
        Formato.setVAC64(bateria.getVAC64());
        Formato.setVAC65(bateria.getVAC65());
        Formato.setVAC66(bateria.getVAC66());
        Formato.setVAC67(bateria.getVAC67());
        Formato.setVAC68(bateria.getVAC68());
        Formato.setVAC69(bateria.getVAC69());
        Formato.setVAC70(bateria.getVAC70());
        Formato.setVAC71(bateria.getVAC71());
        Formato.setVAC72(bateria.getVAC72());
        Formato.setVAC73(bateria.getVAC73());
        Formato.setVAC74(bateria.getVAC74());
        Formato.setVAC75(bateria.getVAC75());
        Formato.setVAC76(bateria.getVAC76());
        Formato.setVAC77(bateria.getVAC77());
        Formato.setVAC78(bateria.getVAC78());
        Formato.setVAC79(bateria.getVAC79());
        Formato.setVAC80(bateria.getVAC80());
        Formato.setVAC81(bateria.getVAC81());
        Formato.setVAC82(bateria.getVAC82());
        Formato.setVAC83(bateria.getVAC83());
        Formato.setVAC84(bateria.getVAC84());
        Formato.setVAC85(bateria.getVAC85());
        Formato.setVAC86(bateria.getVAC86());
        Formato.setVAC87(bateria.getVAC87());
        Formato.setVAC88(bateria.getVAC88());
        Formato.setVAC89(bateria.getVAC89());
        Formato.setVAC90(bateria.getVAC90());
        Formato.setVAC91(bateria.getVAC91());
        Formato.setVAC92(bateria.getVAC92());
        Formato.setVAC93(bateria.getVAC93());
        Formato.setVAC94(bateria.getVAC94());
        Formato.setVAC95(bateria.getVAC95());
        Formato.setVAC96(bateria.getVAC96());
        Formato.setVAC97(bateria.getVAC97());
        Formato.setVAC98(bateria.getVAC98());
        Formato.setVAC99(bateria.getVAC99());
        Formato.setVAC100(bateria.getVAC100());
        Formato.setVAC101(bateria.getVAC101());
        Formato.setVAC102(bateria.getVAC102());
        Formato.setVAC103(bateria.getVAC103());
        Formato.setVAC104(bateria.getVAC104());
        Formato.setVAC105(bateria.getVAC105());
        Formato.setVAC106(bateria.getVAC106());
        Formato.setVAC107(bateria.getVAC107());
        Formato.setVAC108(bateria.getVAC108());
        Formato.setVAC109(bateria.getVAC109());
        Formato.setVAC110(bateria.getVAC110());
        Formato.setVAC111(bateria.getVAC111());
        Formato.setVAC112(bateria.getVAC112());
        Formato.setVAC113(bateria.getVAC113());
        Formato.setVAC114(bateria.getVAC114());
        Formato.setVAC115(bateria.getVAC115());
        Formato.setVAC116(bateria.getVAC116());
        Formato.setVAC117(bateria.getVAC117());
        Formato.setVAC118(bateria.getVAC118());
        Formato.setVAC119(bateria.getVAC119());
        Formato.setVAC120(bateria.getVAC120());
        Formato.setModeloMarca(bateria.getModeloMarca());
        Formato.setAparenciaLimpieza(bateria.getAparenciaLimpieza());
        Formato.setCodigoFecha(bateria.getCodigoFecha());
        Formato.setAñosServicio(bateria.getAñosServicio());
        Formato.setConectoresTornillos(bateria.getConectoresTornillos());
        Formato.setJarrasCubiertasSellado(bateria.getJarrasCubiertasSellado());
        Formato.setVoltajeFlotacionVDC(bateria.getVoltajeFlotacionVDC());
        Formato.setTemperaturaBaterias(bateria.getTemperaturaBaterias());
        Formato.setCorrienteFlotacion(bateria.getCorrienteFlotacion());
        Formato.setTemperaturaAmbiente(bateria.getTemperaturaAmbiente());
        Formato.setCorrienteRizo(bateria.getCorrienteRizo());
        Formato.setTorque(bateria.getTorque());
        Formato.setVoltajeRizo(bateria.getVoltajeRizo());
        Formato.setTerminales(bateria.getTerminales());
        Formato.setComentarios(bateria.getCOMENT_cometarios());
        Formato.setPMUCantidad1(bateria.getMATE_cantidad1());
        Formato.setPMUCantidad2(bateria.getMATE_cantidad2());
        Formato.setPMUCantidad3(bateria.getMATE_cantidad3());
        Formato.setPMUCantidad4(bateria.getMATE_cantidad4());
        Formato.setPMUCantidad5(bateria.getMATE_cantidad5());
        Formato.setPMUCantidad6(bateria.getMATE_cantidad6());
        Formato.setPMUCantidad7(bateria.getMATE_cantidad7());
        Formato.setPMUNParte1(bateria.getMATE_parte1());
        Formato.setPMUNParte2(bateria.getMATE_parte2());
        Formato.setPMUNParte3(bateria.getMATE_parte3());
        Formato.setPMUNParte4(bateria.getMATE_parte4());
        Formato.setPMUNParte5(bateria.getMATE_parte5());
        Formato.setPMUNParte6(bateria.getMATE_parte6());
        Formato.setPMUNParte7(bateria.getMATE_parte7());
        Formato.setPMUDescripcion1(bateria.getMATE_especifica1());
        Formato.setPMUDescripcion2(bateria.getMATE_especifica2());
        Formato.setPMUDescripcion3(bateria.getMATE_especifica3());
        Formato.setPMUDescripcion4(bateria.getMATE_especifica4());
        Formato.setPMUDescripcion5(bateria.getMATE_especifica5());
        Formato.setPMUDescripcion6(bateria.getMATE_especifica6());
        Formato.setPMUDescripcion7(bateria.getMATE_especifica7());
        Formato.setEMEquipo1(bateria.getMATE_equipo1());
        Formato.setEMEquipo2(bateria.getMATE_equipo2());
        Formato.setEMEquipo3(bateria.getMATE_equipo3());
        Formato.setEMEquipo4(bateria.getMATE_equipo4());
        Formato.setEMEquipo5(bateria.getMATE_equipo5());
        Formato.setEMEquipo6(bateria.getMATE_equipo6());
        //Formato.setEMEquipo7(bateria.getequipo7);
        Formato.setNoID1(bateria.getMATE_nid1());
        Formato.setNoID2(bateria.getMATE_nid2());
        Formato.setNoID3(bateria.getMATE_nid3());
        Formato.setNoID4(bateria.getMATE_nid4());
        Formato.setNoID5(bateria.getMATE_nid5());
        Formato.setNoID6(bateria.getMATE_nid6());
        // Formato.setNoID7(bateria.getn);
        Formato.setFechaVencimiento1(bateria.getMATE_fecha1());
        Formato.setFechaVencimiento2(bateria.getMATE_fecha2());
        Formato.setFechaVencimiento3(bateria.getMATE_fecha3());
        Formato.setFechaVencimiento4(bateria.getMATE_fecha4());
        Formato.setFechaVencimiento5(bateria.getMATE_fecha5());
        Formato.setFechaVencimiento6(bateria.getMATE_fecha6());
        //Formato.setFechaVencimiento7(bateria.ge);
        Formato.setAntesFoto1(bateria.getFOTOS_A1());
        Formato.setAntesFoto2(bateria.getFOTOS_A2());
        Formato.setAntesFoto3(bateria.getFOTOS_A3());
        Formato.setAntesFoto4(bateria.getFOTOS_A4());
        Formato.setAntesFoto5(bateria.getFOTOS_A5());
        Formato.setAntesFoto6(bateria.getFOTOS_A6());
        Formato.setDespuesFoto1(bateria.getFOTOS_D1());
        Formato.setDespuesFoto2(bateria.getFOTOS_D2());
        Formato.setDespuesFoto3(bateria.getFOTOS_D3());
        Formato.setDespuesFoto4(bateria.getFOTOS_D4());
        Formato.setDespuesFoto5(bateria.getFOTOS_D5());
        Formato.setDespuesFoto6(bateria.getFOTOS_D6());
        Formato.setFirmaCliente(bateria.getFIRMA_IMG1());
        Formato.setFirmaVertiv(bateria.getFIRMA_IMG2());
        Formato.setFirmaClienteFinal(bateria.getFIRMA_IMG3());
        Formato.setNombreFirmaCliente(bateria.getFIRMA_NOMBRE1());
        Formato.setNombreFirmaVertiv(bateria.getFIRMA_NOMBRE2());
        Formato.setNombreFirmaClienteFinal(bateria.getFIRMA_NOMBRE3());
        Formato.setAD_NOMBRE1(bateria.getADICI_NOMBRE1());
        Formato.setAD_CORREO1(bateria.getADICI_CORREO1());
        Formato.setAD_NOMBRE2(bateria.getADICI_NOMBRE2());
        Formato.setAD_CORREO2(bateria.getADICI_CORREO2());
        Formato.setAD_NOMBRE3(bateria.getADICI_NOMBRE3());
        Formato.setAD_CORREO3(bateria.getADICI_CORREO3());
        Formato.setAD_NOMBRE4(bateria.getADICI_NOMBRE4());
        Formato.setAD_CORREO4(bateria.getADICI_CORREO4());
        Formato.setAD_NOMBRE5(bateria.getADICI_NOMBRE5());
        Formato.setAD_CORREO5(bateria.getADICI_CORREO5());
        Formato.setCLIENTEFINAL_EMPRESA(bateria.getFIRMA_EMPRESA());
        Formato.setCLIENTEFINAL_TELEFONO(bateria.getFIRMA_TELEFONO());
        Formato.setCLIENTEFINAL_CORREO(bateria.getFIRMA_CORREO());



        //endregion
        //region enviar formato
        Formato.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarBaterias(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BateriaS>() {
                    @Override
                    public void accept(BateriaS lista) throws Exception {
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

                            Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

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
