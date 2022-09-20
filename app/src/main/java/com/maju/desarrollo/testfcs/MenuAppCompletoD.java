package com.maju.desarrollo.testfcs;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.AccionesM.CContrasena;
import com.maju.desarrollo.testfcs.AccionesM.Mensajes;
import com.maju.desarrollo.testfcs.AccionesM.Sincronizar;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuAppCompletoD extends DialogFragment {
    OperacionesDB D_B;
    UsuarioD usuarioDat;
    SharedPreferences direcorio;
    TextView mensajesNumero;

    public MenuAppCompletoD() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu_app_completo_d, container, false);

        D_B = OperacionesDB.obtenerInstancia(getContext());
        usuarioDat = D_B.obtenerUsuario();

        LinearLayout sincronizar = (LinearLayout)v.findViewById(R.id.M_sincronizar);
        LinearLayout contraseña = (LinearLayout)v.findViewById(R.id.M_contraseña);
        LinearLayout mensajes = (LinearLayout)v.findViewById(R.id.M_mensajes);
        LinearLayout salir = (LinearLayout)v.findViewById(R.id.M_salir);
        LinearLayout dismit = (LinearLayout)v.findViewById(R.id.M_dismit);
        TextView usuario = (TextView)v.findViewById(R.id.nombreUsuariosLogin);
        TextView versioln = (TextView)v.findViewById(R.id.versionAppVertiv);
        mensajesNumero = v.findViewById(R.id.mensajesNumero);

        String vers= BuildConfig.VERSION_NAME;
        usuario.setText(usuarioDat.getCORREO());
        versioln.setText(vers);

        String numeroE = D_B.ObtenerMensajes();
        if(numeroE.equals("") || numeroE.equals("0")){
            mensajesNumero.setVisibility(View.GONE);
        }else{
            mensajesNumero.setText(numeroE);
        }


        dismit.setBackgroundColor(getColorWithAlpha(Color.WHITE, 0.1f));
        final MainActivity main = new MainActivity();
        sincronizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sincronizar menuFragment = new Sincronizar();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, menuFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                dismiss();
            }
        });
        contraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CContrasena menuFragment = new CContrasena();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, menuFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                dismiss();

            }
        });
        mensajes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mensajes menuFragment = new Mensajes();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, menuFragment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                dismiss();

            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D_B.CambioSesion(usuarioDat,"INACTIVO");

                direcorio =  getActivity().getSharedPreferences(getString(R.string.pref_file),Context.MODE_PRIVATE);
                direcorio.edit().putString("fecha_sinc",null).apply();

                Intent siguiente2 = new Intent(getActivity(), LoginActivity.class);
                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
                dismiss();

            }

            private void overridePendingTransition(int left_in, int left_out) {
            }
        });

        dismit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;

    }

    public static int getColorWithAlpha(int color, float ratio) {
        int newColor = 0;
        int alpha = Math.round(Color.alpha(color) * ratio);
        int r = Color.red(color);
        int g = Color.green(color);
        int b = Color.blue(color);
        newColor = Color.argb(alpha, r, g, b);
        return newColor;
    }


    @Override
    public void onStart() {
        super.onStart();
        //int wt = MainActivity.anchoPantalla();

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int widthActivityPhone = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels
        int alertW = (int)(widthActivityPhone);


        Window window = getDialog().getWindow();
        if(window == null) return;
        WindowManager.LayoutParams params = window.getAttributes();

        params.width = alertW;
        params.height = height;
        window.setAttributes(params);

        //window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        window.setBackgroundDrawable(new ColorDrawable(getColorWithAlpha(Color.BLACK, 0.6f)));

        
    }


}
