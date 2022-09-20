package com.maju.desarrollo.testfcs.Formatos;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maju.desarrollo.testfcs.Formatos.Baterias.BateriasMenuFragment;
import com.maju.desarrollo.testfcs.Formatos.Calidad.CalidadMenu;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelasDialogFragmentMismoActivity extends DialogFragment {


    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    public CancelasDialogFragmentMismoActivity() {
        // Required empty public constructor
    }
    String menu;
    String valorPaso;
    Fragment myfargment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_cancelas_dialog, container, false);

        menu =getArguments().getString("OtraPantalla");
        valorPaso=getArguments().getString("valorPaso");

        Button Cancelar = (Button)v.findViewById(R.id.dialogoCancelar_cancelar);
        Button Continuar = (Button)v.findViewById(R.id.dialogoCancelar_continuar);

        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", valorPaso);

                if(menu.equals("DCPower")){myfargment = new MenuPowerFragment();}
                if(menu.equals("Calidad")){myfargment = new CalidadMenu();}
                if(menu.equals("Servicios")){myfargment = new ServiciosMenu();}
                if(menu.equals("Garantias")){myfargment = new GarantiasMenu();}
                if(menu.equals("PDU")){myfargment = new PDUMenu();}
                if(menu.equals("STS2")){myfargment = new STS2MenuFragment();}
                if(menu.equals("IN2")){myfargment = new IN2Menu();}
                if(menu.equals("IN1")){myfargment = new IN1Menu();}
                if(menu.equals("Baterias")){myfargment = new BateriasMenuFragment();}
                if(menu.equals("UPS")){myfargment = new UpsMenuFragment();}
                if(menu.equals("Thermal")){myfargment = new ThermMenuFragment();}
                //if(menu.equals("servicios")){myfargment = new ServiciosMenu();}




                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                dismiss();
            }
        });

        Cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        return v;
    }


   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected= (OnInputSelected) getTargetFragment();

        }catch (ClassCastException e){

        }
    }*/

}
