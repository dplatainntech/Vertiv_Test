package com.maju.desarrollo.testfcs.Formatos.STS2;


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
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.STS2;

/**
 * A simple {@link Fragment} subclass.
 */
public class STS2ComentariosFragment extends Fragment {

    OperacionesDB D_B;
    String id_Formato;
    STS2 item;

    public STS2ComentariosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_sts2_comentarios, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_Formato= getArguments().getString("key_idFormato");
        item = D_B.obtenerSTS2(id_Formato);

        final EditText materialesT = (EditText)v.findViewById(R.id.materiales);
        try{materialesT.setText(item.getComentariosRecomendaciones());}catch (Exception e){}

        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "STS2");
                args.putString("valorPaso", id_Formato);

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
                item.setComentariosRecomendaciones(materialesT.getText().toString());
                D_B.guardarSTS2(item);

                Bundle args = new Bundle();
                args.putString("key_idFormato",id_Formato);
                STS2MenuFragment myfargment = new STS2MenuFragment();
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
