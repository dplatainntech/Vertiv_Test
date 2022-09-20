package com.maju.desarrollo.testfcs.Formatos.Baterias;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriasMedicionesFragment extends Fragment {
  Spinner spinner1,spinner2,spinner3;
  OperacionesDB D_B;
  String id_formato;
  Baterias bateria;
  ImageView imf1,imf2,imf3;


  public BateriasMedicionesFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_baterias_mediciones, container, false);

    ((MainActivity) getActivity()).ocultarCabecera();
    D_B = OperacionesDB.obtenerInstancia(getContext());
    id_formato= getArguments().getString("key_idFormato");
    bateria = D_B.obtenerBateriaF_id(id_formato);

    final EditText campo1 = (EditText)v.findViewById(R.id.campo1);
    final EditText campo2 = (EditText)v.findViewById(R.id.campo2);
    final EditText campo3 = (EditText)v.findViewById(R.id.campo3);
    final EditText campo4 = (EditText)v.findViewById(R.id.campo4);
    final EditText campo5 = (EditText)v.findViewById(R.id.campo5);
    final EditText campo6 = (EditText)v.findViewById(R.id.campo6);
    final EditText campo7 = (EditText)v.findViewById(R.id.campo7);
    final EditText campo8 = (EditText)v.findViewById(R.id.campo8);
    final EditText campo9 = (EditText)v.findViewById(R.id.campo9);
    final EditText campo10 = (EditText)v.findViewById(R.id.campo10);
    final EditText campo11 = (EditText)v.findViewById(R.id.campo11);
    final EditText campo12 = (EditText)v.findViewById(R.id.campo12);
    final EditText campo13 = (EditText)v.findViewById(R.id.campo13);
    final EditText campo14 = (EditText)v.findViewById(R.id.campo14);

    try{campo1.setText(bateria.getModeloMarca());}catch (Exception e){}
    try{campo2.setText(bateria.getAparenciaLimpieza());}catch (Exception e){}
    try{campo3.setText(bateria.getJarrasCubiertasSellado());}catch (Exception e){}
    try{campo4.setText(bateria.getTemperaturaBaterias());}catch (Exception e){}
    try{campo5.setText(bateria.getTemperaturaAmbiente());}catch (Exception e){}
    try{campo6.setText(bateria.getTorque());}catch (Exception e){}
    try{campo7.setText(bateria.getTerminales());}catch (Exception e){}
    try{campo8.setText(bateria.getCodigoFecha());}catch (Exception e){}
    try{campo9.setText(bateria.getAñosServicio());}catch (Exception e){}
    try{campo10.setText(bateria.getConectoresTornillos());}catch (Exception e){}
    try{campo11.setText(bateria.getVoltajeFlotacionVDC());}catch (Exception e){}
    try{campo12.setText(bateria.getCorrienteFlotacion());}catch (Exception e){}
    try{campo13.setText(bateria.getCorrienteRizo());}catch (Exception e){}
    try{campo14.setText(bateria.getVoltajeRizo());}catch (Exception e){}



    Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
    cancelar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //region
        //getActivity().onBackPressed();
        Bundle args = new Bundle();
        args.putString("OtraPantalla", "Baterias");
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
        bateria.setModeloMarca(campo1.getText().toString());
        bateria.setAparenciaLimpieza(campo2.getText().toString());
        bateria.setJarrasCubiertasSellado(campo3.getText().toString());
        bateria.setTemperaturaBaterias(campo4.getText().toString());
        bateria.setTemperaturaAmbiente(campo5.getText().toString());
        bateria.setTorque(campo6.getText().toString());
        bateria.setTerminales(campo7.getText().toString());
        bateria.setCodigoFecha(campo8.getText().toString());
        bateria.setAñosServicio(campo9.getText().toString());
        bateria.setConectoresTornillos(campo10.getText().toString());
        bateria.setVoltajeFlotacionVDC(campo11.getText().toString());
        bateria.setCorrienteFlotacion(campo12.getText().toString());
        bateria.setCorrienteRizo(campo13.getText().toString());
        bateria.setVoltajeRizo(campo14.getText().toString());

        D_B.guardarBatrias(bateria,id_formato);

        Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);
        BateriasMenuFragment myfargment = new BateriasMenuFragment();
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
