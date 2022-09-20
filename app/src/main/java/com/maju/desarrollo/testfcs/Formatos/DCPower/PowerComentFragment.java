package com.maju.desarrollo.testfcs.Formatos.DCPower;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerComentFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    DCPower2 dcPower;

    public PowerComentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_power_coment, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        dcPower = D_B.obtenerDcPower2_id(id_formato);

        final EditText cometaryC1 = (EditText)v.findViewById(R.id.comantario1);
        final EditText cometaryC2 = (EditText)v.findViewById(R.id.comantario2);
        final EditText cometaryC3 = (EditText)v.findViewById(R.id.accioncorrectiva3);
        final EditText cometaryC4 = (EditText)v.findViewById(R.id.comantarioComent4);

        try{cometaryC1.setText(dcPower.getCOMENTA_ACTIVIDADES());}catch (Exception e){}
        try{cometaryC2.setText(dcPower.getCOMENTA_DIAGNOSTICO());}catch (Exception e){}
        try{cometaryC3.setText(dcPower.getCOMENTA_ACCION());}catch (Exception e){}
        try{cometaryC4.setText(dcPower.getCOMENTA_COMENTARIOS());}catch (Exception e){}


        //region Botones
        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "DCPower");
                args.putString("valorPaso", id_formato);

                CancelasDialogFragmentMismoActivity dialog= new CancelasDialogFragmentMismoActivity();
                dialog.setCancelable(true);
                dialog.setTargetFragment(getParentFragment(),1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dcPower.setCOMENTA_ACTIVIDADES(cometaryC1.getText().toString());
                dcPower.setCOMENTA_DIAGNOSTICO(cometaryC2.getText().toString());
                dcPower.setCOMENTA_ACCION(cometaryC3.getText().toString());
                dcPower.setCOMENTA_COMENTARIOS(cometaryC4.getText().toString());

                D_B.guardarPower2(dcPower,id_formato);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                MenuPowerFragment myfargment = new MenuPowerFragment();
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
