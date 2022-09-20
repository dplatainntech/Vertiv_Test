package com.maju.desarrollo.testfcs.Formatos.Servicios;


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
import com.maju.desarrollo.testfcs.SQLite.Model.Servicios;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServiciosServicio extends Fragment {

    String id_formato;
    OperacionesDB D_B;
    Servicios Formato;

    public ServiciosServicio() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_servicios_servicio, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerServicio(id_formato);


        final EditText campo4 = (EditText)v.findViewById(R.id.campo4);
        final EditText campo3 = (EditText)v.findViewById(R.id.campo3);
        final EditText campo2 = (EditText)v.findViewById(R.id.campo2);
        final EditText campo1 = (EditText)v.findViewById(R.id.campo1);


        campo4.setText(Formato.getCOMENTARIOS());
        campo3.setText(Formato.getACCIONCORRECTIVA());
        campo2.setText(Formato.getDIAGNOSTICO());
        campo1.setText(Formato.getACTIVIDAD());



        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Servicios");
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
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                //D_B.guardarPower1(dcPower);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Formato.setCOMENTARIOS(campo4.getText().toString());
                Formato.setACCIONCORRECTIVA(campo3.getText().toString());
                Formato.setDIAGNOSTICO(campo2.getText().toString());
                Formato.setACTIVIDAD(campo1.getText().toString());

                D_B.guardarservicios(Formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                ServiciosMenu myfargment = new ServiciosMenu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return v;
    }

}
