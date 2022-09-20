package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class POActividamesFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;
    EditText descripcion;

    public POActividamesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_poactividames, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);

        descripcion = (EditText)v.findViewById(R.id.EHM_lista);


        descripcion.setText(preOrden.getACTV_DESCRIPCION_ACTIVIDADES());


        Button cancelar = (Button)v.findViewById(R.id.EHM_btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", preOrden.getFOLIO());

                CancelasDialogFragment dialog= new CancelasDialogFragment();
                dialog.setCancelable(true);
                dialog.setTargetFragment(POActividamesFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.EHM_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                D_B.guardarPreOrden(preOrden);
                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                siguiente.putExtra("valorPaso", preOrden.getFOLIO());
                startActivity(siguiente);

            }
        });





        return v;
    }

}
