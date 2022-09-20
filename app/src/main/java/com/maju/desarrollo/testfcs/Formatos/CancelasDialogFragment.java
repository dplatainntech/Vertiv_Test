package com.maju.desarrollo.testfcs.Formatos;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CancelasDialogFragment extends DialogFragment {


    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    public CancelasDialogFragment() {
        // Required empty public constructor
    }

    String pantalla;
    String valorPaso;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cancelas_dialog, container, false);

        pantalla =getArguments().getString("OtraPantalla");
        valorPaso=getArguments().getString("valorPaso");

        Button Cancelar = (Button)v.findViewById(R.id.dialogoCancelar_cancelar);
        Button Continuar = (Button)v.findViewById(R.id.dialogoCancelar_continuar);

        Continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String c = "Cancelado";
                //mOnInputSelected.sendInput(c);
                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", pantalla);
                siguiente.putExtra("valorPaso", valorPaso);
                startActivity(siguiente);
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
