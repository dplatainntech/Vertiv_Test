package com.maju.desarrollo.testfcs.Formatos.UPS;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpsLecturasFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    UPS Formato;

    public UpsLecturasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ups_lecturas, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerUPS(id_formato);


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
        final EditText campo15 = (EditText)v.findViewById(R.id.campo15);
        final EditText campo16 = (EditText)v.findViewById(R.id.campo16);
        final EditText campo17 = (EditText)v.findViewById(R.id.campo17);
        final EditText campo18 = (EditText)v.findViewById(R.id.campo18);
        final EditText campo19 = (EditText)v.findViewById(R.id.campo19);
        final EditText campo20 = (EditText)v.findViewById(R.id.campo20);
        final EditText campo21 = (EditText)v.findViewById(R.id.campo21);
        final EditText campo22 = (EditText)v.findViewById(R.id.campo22);
        final EditText campo23 = (EditText)v.findViewById(R.id.campo23);
        final EditText campo24 = (EditText)v.findViewById(R.id.campo24);
        final EditText campo25 = (EditText)v.findViewById(R.id.campo25);
        final EditText campo26 = (EditText)v.findViewById(R.id.campo26);
        final TextView campo27 = (TextView)v.findViewById(R.id.campo27);
        final EditText campo28 = (EditText)v.findViewById(R.id.campo28);
        final EditText campo29 = (EditText)v.findViewById(R.id.campo29);
        final EditText campo30 = (EditText)v.findViewById(R.id.campo30);
        final EditText campo31 = (EditText)v.findViewById(R.id.campo31);
        final EditText campo32 = (EditText)v.findViewById(R.id.campo32);
        final EditText campo33 = (EditText)v.findViewById(R.id.campo33);
        final EditText campo34 = (EditText)v.findViewById(R.id.campo34);
        final EditText campo35 = (EditText)v.findViewById(R.id.campo35);



        try{campo1.setText(Formato.getVoltajeEntradaLLFaseA().toString());}catch (Exception e){}
        try{campo2.setText(Formato.getVoltajeEntradaLLFaseB().toString());}catch (Exception e){}
        try{campo3.setText(Formato.getVoltajeEntradaLLFaseC().toString());}catch (Exception e){}
        try{campo4.setText(Formato.getVoltajeEntradaLNFaseA().toString());}catch (Exception e){}
        try{campo5.setText(Formato.getVoltajeEntradaLNFaseB().toString());}catch (Exception e){}
        try{campo6.setText(Formato.getVoltajeEntradaLNFaseC().toString());}catch (Exception e){}
        try{campo7.setText(Formato.getCorrienteEntradaFaseA().toString());}catch (Exception e){}
        try{campo8.setText(Formato.getCorrienteEntradaFaseB().toString());}catch (Exception e){}
        try{campo9.setText(Formato.getCorrienteEntradaFaseC().toString());}catch (Exception e){}
        try{campo10.setText(Formato.getCorrienteFiltroEntradaFaseA().toString());}catch (Exception e){}
        try{campo11.setText(Formato.getCorrienteFiltroEntradaFaseB().toString());}catch (Exception e){}
        try{campo12.setText(Formato.getCorrienteFiltroEntradaFaseC().toString());}catch (Exception e){}
        try{campo13.setText(Formato.getVoltajeSalidaUPSLLFaseA().toString());}catch (Exception e){}
        try{campo14.setText(Formato.getVoltajeSalidaUPSLLFaseB().toString());}catch (Exception e){}
        try{campo15.setText(Formato.getVoltajeSalidaUPSLLFaseC().toString());}catch (Exception e){}
        try{campo16.setText(Formato.getVoltajeSalidaUPSLNFaseA().toString());}catch (Exception e){}
        try{campo17.setText(Formato.getVoltajeSalidaUPSLNFaseB().toString());}catch (Exception e){}
        try{campo18.setText(Formato.getVoltajeSalidaUPSLNFaseC().toString());}catch (Exception e){}
        try{campo19.setText(Formato.getVoltajeBypassFaseA().toString());}catch (Exception e){}
        try{campo20.setText(Formato.getVoltajeBypassFaseB().toString());}catch (Exception e){}
        try{campo21.setText(Formato.getVoltajeBypassFaseC().toString());}catch (Exception e){}
        try{campo22.setText(Formato.getCorrienteSalidaFaseA().toString());}catch (Exception e){}
        try{campo23.setText(Formato.getCorrienteSalidaFaseB().toString());}catch (Exception e){}
        try{campo24.setText(Formato.getCorrienteSalidaFaseC().toString());}catch (Exception e){}
        try{campo25.setText(Formato.getCorrienteFiltroSalidaFaseA().toString());}catch (Exception e){}
        try{campo26.setText(Formato.getVoltajeBusDCFaseA().toString());}catch (Exception e){}
        try{campo27.setText(Formato.getCorrienteBusDCFaseA().toString());}catch (Exception e){}
        try{campo28.setText(Formato.getVoltajeRizoACFaseA().toString());}catch (Exception e){}
        try{campo29.setText(Formato.getCorrienteRizoACFaseA().toString());}catch (Exception e){}
        try{campo30.setText(Formato.getSincronoscopioFaseA().toString());}catch (Exception e){}
        try{campo31.setText(Formato.getPotenciaKWFaseA().toString());}catch (Exception e){}
        try{campo32.setText(Formato.getPotenciaKVAFaseA().toString());}catch (Exception e){}
        try{campo33.setText(Formato.getFreqEntrada().toString());}catch (Exception e){}
        try{campo34.setText(Formato.getFreqUPSSalida().toString());}catch (Exception e){}
        try{campo35.setText(Formato.getPorcentajeCarga().toString());}catch (Exception e){}





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

                if(campo1.getText().toString().isEmpty()&&
                campo2.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo2);
                campo3.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo3);
                campo4.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo4);
                campo5.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo5);
                campo6.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo6);
                campo7.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo7);
                campo8.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo8);
                campo9.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo9);
                campo10.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo10);
                campo11.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo11);
                campo12.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo12);
                campo13.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo13);
                campo14.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo14);
                campo15.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo15);
                campo16.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo16);
                campo17.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo17);
                campo18.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo18);
                campo19.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo19);
                campo20.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo20);
                campo21.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo21);
                campo22.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo22);
                campo23.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo23);
                campo24.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo24);
                campo25.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo25);
                campo26.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo26);
                campo27.getText().toString().isEmpty()&& //(TextView)v.findViewById(R.id.campo27);
                campo28.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo28);
                campo29.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo29);
                campo30.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo30);
                campo31.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo31);
                campo32.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo32);
                campo33.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo33);
                campo34.getText().toString().isEmpty()&& //(EditText)v.findViewById(R.id.campo34);
                campo35.getText().toString().isEmpty() //(EditText)v.findViewById(R.id.campo35);)
                ){
                    Formato.setPorcentajeCarga(" ");
                }else {
                    Formato.setVoltajeEntradaLLFaseA(campo1.getText().toString());
                    Formato.setVoltajeEntradaLLFaseB(campo2.getText().toString());
                    Formato.setVoltajeEntradaLLFaseC(campo3.getText().toString());
                    Formato.setVoltajeEntradaLNFaseA(campo4.getText().toString());
                    Formato.setVoltajeEntradaLNFaseB(campo5.getText().toString());
                    Formato.setVoltajeEntradaLNFaseC(campo6.getText().toString());
                    Formato.setCorrienteEntradaFaseA(campo7.getText().toString());
                    Formato.setCorrienteEntradaFaseB(campo8.getText().toString());
                    Formato.setCorrienteEntradaFaseC(campo9.getText().toString());
                    Formato.setCorrienteFiltroEntradaFaseA(campo10.getText().toString());
                    Formato.setCorrienteFiltroEntradaFaseB(campo11.getText().toString());
                    Formato.setCorrienteFiltroEntradaFaseC(campo12.getText().toString());
                    Formato.setVoltajeSalidaUPSLLFaseA(campo13.getText().toString());
                    Formato.setVoltajeSalidaUPSLLFaseB(campo14.getText().toString());
                    Formato.setVoltajeSalidaUPSLLFaseC(campo15.getText().toString());
                    Formato.setVoltajeSalidaUPSLNFaseA(campo16.getText().toString());
                    Formato.setVoltajeSalidaUPSLNFaseB(campo17.getText().toString());
                    Formato.setVoltajeSalidaUPSLNFaseC(campo18.getText().toString());
                    Formato.setVoltajeBypassFaseA(campo19.getText().toString());
                    Formato.setVoltajeBypassFaseB(campo20.getText().toString());
                    Formato.setVoltajeBypassFaseC(campo21.getText().toString());
                    Formato.setCorrienteSalidaFaseA(campo22.getText().toString());
                    Formato.setCorrienteSalidaFaseB(campo23.getText().toString());
                    Formato.setCorrienteSalidaFaseC(campo24.getText().toString());
                    Formato.setCorrienteFiltroSalidaFaseA(campo25.getText().toString());
                    Formato.setVoltajeBusDCFaseA(campo26.getText().toString());
                    Formato.setCorrienteBusDCFaseA(campo27.getText().toString());
                    Formato.setVoltajeRizoACFaseA(campo28.getText().toString());
                    Formato.setCorrienteRizoACFaseA(campo29.getText().toString());
                    Formato.setSincronoscopioFaseA(campo30.getText().toString());
                    Formato.setPotenciaKWFaseA(campo31.getText().toString());
                    Formato.setPotenciaKVAFaseA(campo32.getText().toString());
                    Formato.setFreqEntrada(campo33.getText().toString());
                    Formato.setFreqUPSSalida(campo34.getText().toString());
                    Formato.setPorcentajeCarga(campo35.getText().toString());
                }

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
