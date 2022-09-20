package com.maju.desarrollo.testfcs.Formatos.IN1;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN1Evaluacion extends Fragment {
    OperacionesDB D_B;
    String id_formato,firma;;
    Bestel1 formato;

    public IN1Evaluacion() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =   inflater.inflate(R.layout.fragment_in1_evaluacion, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato = getArguments().getString("key_idFormato");
        formato = D_B.obtenerBestel1(id_formato);



        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.bestelEvaluacion, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner evaluacion = (Spinner)v.findViewById(R.id.evaluacion);
        evaluacion.setAdapter(adapter);

        final RadioButton radioB_Si1 = (RadioButton)v.findViewById(R.id.radioB_Si1);
        final RadioButton radioB_No1 = (RadioButton)v.findViewById(R.id.radioB_No1);
        final RadioButton radioB_Si2 = (RadioButton)v.findViewById(R.id.radioB_Si2);
        final RadioButton radioB_No2 = (RadioButton)v.findViewById(R.id.radioB_No2);



        try{evaluacion.setSelection(adapter.getPosition(formato.getEDSREvalucionSitio()));}catch (Exception e){}

        try{
            if(formato.getEDSRTiempo().equals("si")){
                radioB_Si1.setChecked(true);
            }else if(formato.getEDSRTiempo().equals("no")){
                radioB_No1.setChecked(true);
            }
        }catch (Exception e){
            String a = e.getMessage();
        }

        try{
            if(formato.getEDSRCalidad().equals("si")){
                radioB_Si2.setChecked(true);
            }else if(formato.getEDSRCalidad().equals("no")){
                radioB_No2.setChecked(true);
            }
        }catch (Exception e){

            String a = e.getMessage();
        }





        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "IN1");
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
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();
                //try{if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("SI")){si1.setChecked(true);} }catch (Exception e){}

                if(radioB_Si1.isChecked()){formato.setEDSRTiempo("si");}
                if(radioB_No1.isChecked()){formato.setEDSRTiempo("no");}

                if(radioB_Si2.isChecked()){formato.setEDSRCalidad("si");}
                if(radioB_No2.isChecked()){formato.setEDSRCalidad("no");}

                formato.setEDSREvalucionSitio(evaluacion.getSelectedItem().toString());

                D_B.guardarBestel1(formato);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                IN1Menu myfargment = new IN1Menu();
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
