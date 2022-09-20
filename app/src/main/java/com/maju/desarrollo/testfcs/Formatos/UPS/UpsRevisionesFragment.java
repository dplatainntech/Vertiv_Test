package com.maju.desarrollo.testfcs.Formatos.UPS;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpsRevisionesFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    UPS Formato;

    public UpsRevisionesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_ups_revisiones, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerUPS(id_formato);


        final CheckBox CheckBox1 = (CheckBox)v.findViewById(R.id.CheckBox1);
        final CheckBox CheckBox2 = (CheckBox)v.findViewById(R.id.CheckBox2);
        final CheckBox CheckBox3 = (CheckBox)v.findViewById(R.id.CheckBox3);
        final CheckBox CheckBox4 = (CheckBox)v.findViewById(R.id.CheckBox4);
        final CheckBox CheckBox5 = (CheckBox)v.findViewById(R.id.CheckBox5);
        final CheckBox CheckBox6 = (CheckBox)v.findViewById(R.id.CheckBox6);
        final CheckBox CheckBox7 = (CheckBox)v.findViewById(R.id.CheckBox7);
        final CheckBox CheckBox8 = (CheckBox)v.findViewById(R.id.CheckBox8);
        final CheckBox CheckBox9 = (CheckBox)v.findViewById(R.id.CheckBox9);
        final CheckBox CheckBox10 = (CheckBox)v.findViewById(R.id.CheckBox10);
        final CheckBox CheckBox11 = (CheckBox)v.findViewById(R.id.CheckBox11);
        final CheckBox CheckBox12 = (CheckBox)v.findViewById(R.id.CheckBox12);
        final CheckBox CheckBox13 = (CheckBox)v.findViewById(R.id.CheckBox13);
        final CheckBox CheckBox14 = (CheckBox)v.findViewById(R.id.CheckBox14);

        final EditText campo1 = (EditText)v.findViewById(R.id.campo1);
        final EditText campo2 = (EditText)v.findViewById(R.id.campo2);
        final EditText campo3 = (EditText)v.findViewById(R.id.campo3);
        final EditText campo4 = (EditText)v.findViewById(R.id.campo4);
        final EditText campo5 = (EditText)v.findViewById(R.id.campo5);

        try{if(Formato.getRevisarAlarmas().equals("true")){CheckBox1.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getInspeccionCables().equals("true")){CheckBox2.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getCalibracionMedidor().equals("true")){CheckBox3.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getAjusteEcualizacion().equals("true")){CheckBox4.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getPantallaInformeEstado().equals("true")){CheckBox5.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getLimpiezaUPS().equals("true")){CheckBox6.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getSnubberSobretemperatura().equals("true")){CheckBox7.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getAjusteLímites().equals("true")){CheckBox8.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getPantallaProcedimiento().equals("true")){CheckBox9.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getPantallaBaterías().equals("true")){CheckBox10.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getRevisionModulosDaños().equals("true")){CheckBox11.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getInspeccionGeneral().equals("true")){CheckBox12.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getCapacitoresInflados().equals("true")){CheckBox13.setChecked(true);} }catch (Exception e){}
        try{if(Formato.getCapacitoresValvulas().equals("true")){CheckBox14.setChecked(true);} }catch (Exception e){}

        try{campo1.setText(Formato.getRectificador().toString());}catch (Exception e){}
        try{campo2.setText(Formato.getInversor().toString());}catch (Exception e){}
        try{campo3.setText(Formato.getSwitchEstatico().toString());}catch (Exception e){}
        try{campo4.setText(Formato.getTransformador().toString());}catch (Exception e){}
        try{campo5.setText(Formato.getRevSoftware().toString());}catch (Exception e){}




        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "UPS");
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

                if (CheckBox1.isChecked()) {Formato.setRevisarAlarmas("true");}else{Formato.setRevisarAlarmas(null);}
                if (CheckBox2.isChecked()) {Formato.setInspeccionCables("true");}else{Formato.setInspeccionCables(null);}
                if (CheckBox3.isChecked()) {Formato.setCalibracionMedidor("true");}else{Formato.setCalibracionMedidor(null);}
                if (CheckBox4.isChecked()) {Formato.setAjusteEcualizacion("true");}else{Formato.setAjusteEcualizacion(null);}
                if (CheckBox5.isChecked()) {Formato.setPantallaInformeEstado("true");}else{Formato.setPantallaInformeEstado(null);}
                if (CheckBox6.isChecked()) {Formato.setLimpiezaUPS("true");}else{Formato.setLimpiezaUPS(null);}
                if (CheckBox7.isChecked()) {Formato.setSnubberSobretemperatura("true");}else{Formato.setSnubberSobretemperatura(null);}
                if (CheckBox8.isChecked()) {Formato.setAjusteLímites("true");}else{Formato.setAjusteLímites(null);}
                if (CheckBox9.isChecked()) {Formato.setPantallaProcedimiento("true");}else{Formato.setPantallaProcedimiento(null);}
                if (CheckBox10.isChecked()) {Formato.setPantallaBaterías("true");}else{Formato.setPantallaBaterías(null);}
                if (CheckBox11.isChecked()) {Formato.setRevisionModulosDaños("true");}else{Formato.setRevisionModulosDaños(null);}
                if (CheckBox12.isChecked()) {Formato.setInspeccionGeneral("true");}else{Formato.setInspeccionGeneral(null);}
                if (CheckBox13.isChecked()) {Formato.setCapacitoresInflados("true");}else{Formato.setCapacitoresInflados(null);}
                if (CheckBox14.isChecked()) {Formato.setCapacitoresValvulas("true");}else{Formato.setCapacitoresValvulas(null);}

                Formato.setRectificador(campo1.getText().toString());
                Formato.setInversor(campo2.getText().toString());
                Formato.setSwitchEstatico(campo3.getText().toString());
                Formato.setTransformador(campo4.getText().toString());
                Formato.setRevSoftware(campo5.getText().toString());


                D_B.guardarUPS(Formato);

                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                UpsMenuFragment myfargment = new UpsMenuFragment();
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
