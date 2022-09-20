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
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class POEmergenciasFragment extends Fragment {

  OperacionesDB D_B;
  String id_PreOrden;
  PreOrden preOrden;
  UsuarioD usuario;

  public POEmergenciasFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_poemergencias, container, false);

    D_B = OperacionesDB.obtenerInstancia(getContext());
    id_PreOrden= getArguments().getString("key_idPreOrden");

    preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
    usuario = D_B.obtenerUsuario();

    final EditText num_emergencia = (EditText)v.findViewById(R.id.emergen_editText1);
    final EditText num_vertiv = (EditText)v.findViewById(R.id.emergen_editText2);
    final EditText hospital = (EditText)v.findViewById(R.id.emergen_editText3);


    try{
      if(usuario.getPaisDescripcion().equals("MEXICO")) {
        if (preOrden.getEMERG_EMERGENCIAS().equals("911") || preOrden.getEMERG_EMERGENCIAS() == null) {
          //num_emergencia.getText().length()>1
          num_emergencia.setText("911");
        }//num_emergencia.setText(preOrden.getEMERG_EMERGENCIAS());}
        else {
          num_emergencia.setText(preOrden.getEMERG_EMERGENCIAS());
        }
      }else{
        num_emergencia.setText(preOrden.getEMERG_EMERGENCIAS());
      }

      num_vertiv.setText(preOrden.getEMERG_SUPERVISOR_VERTIV());
      hospital.setText(preOrden.getEMERG_HOSPITAL());
    }catch (Exception e){}


    Button cancelar = (Button)v.findViewById(R.id.Emergencia_btn_Cancelar);
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
        dialog.setTargetFragment(POEmergenciasFragment.this,1);
        dialog.setArguments(args);
        dialog.show(getFragmentManager(),"tConfirmacionFragment");
        //endregion
      }
    });
    Button guardar = (Button)v.findViewById(R.id.Emergencia_btn_Guardar);
    guardar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        //getActivity().onBackPressed();
        //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());
        //region
        preOrden.setEMERG_EMERGENCIAS(num_emergencia.getText().toString());
        preOrden.setEMERG_SUPERVISOR_VERTIV(num_vertiv.getText().toString());
        preOrden.setEMERG_HOSPITAL(hospital.getText().toString());

        //endregion

        D_B.guardarPreOrden(preOrden);
        Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

        Intent siguiente = new Intent(getActivity(), MainActivity.class);
        siguiente.putExtra("OtraPantalla", "MenuPreOrden");
        siguiente.putExtra("valorPaso", preOrden.getFOLIO());
        startActivity(siguiente);

      }
    });

    return  v;
  }

}