package com.maju.desarrollo.testfcs.Formatos.Calidad;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.CanvasView;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalidadLienzo extends Fragment {

    OperacionesDB D_B;
    String id_formato,firma;
    private CanvasView customCanvas;
    Bitmap firmaGuardada;
    EncuestaCalidadServicio Formato;

    public CalidadLienzo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_calidad_lienzo, container, false);

        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ((MainActivity) getActivity()).ocultarCabecera();


        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato = getArguments().getString("key_idFormato");
        firma = getArguments().getString("Firma_aGuardar");
        //preOrden = D_B.obtenerPreOrden_id(id_PreOrden);

        customCanvas = (CanvasView)v.findViewById(R.id.signature_canvas);

        Formato = D_B.obtenerCalidad(id_formato);


        ImageView no = (ImageView)v.findViewById(R.id.ButtonFirmaNo);
        ImageView si = (ImageView)v.findViewById(R.id.ButtonFirmaOk);
        ImageView limpiar = (ImageView)v.findViewById(R.id.ButtonFirmaLimpiar);

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customCanvas.clearCanvas();
            }
        });

        si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (customCanvas.comprovarCanvas()==true) {

                    customCanvas.setDrawingCacheEnabled(true);
                    Bitmap bitmap2 =  customCanvas.getDrawingCache(); //customCanvas.getDrawingCache();

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream);
                    byte[] imagen = stream.toByteArray();
                    String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);

                    if(firma.equals("Firma1")) { Formato.setFirmaVertiv(imagenString); }
                    if(firma.equals("Firma2")) { Formato.setFirmaCliente(imagenString); }
                    if(firma.equals("Firma3")) { Formato.setFirmaClienteFinal(imagenString); }

                    D_B.guardarCalidad(Formato);

                    Bundle args = new Bundle();
                    args.putString("key_idFormato", id_formato);

                    CalidadFirmas myfargment = new CalidadFirmas();
                    myfargment.setArguments(args);
                    FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                }
                else {
                    Toast.makeText(getContext(), "Aun no se ha firmado", Toast.LENGTH_LONG).show();
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);

                CalidadFirmas myfargment = new CalidadFirmas();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });



        return v;
    }


    @Override
    public void onStart() {
        super.onStart();
        //getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }
}

 /* if(firma.equals("Firma1")) { preOrden.setFIRMA_1_IMAGEN(imagenString); }
                    if(firma.equals("Firma2")) { preOrden.setFIRMA_2_IMAGEN(imagenString); }
                    if(firma.equals("Firma3")) { preOrden.setFIRMA_3_IMAGEN(imagenString); }
                    if(firma.equals("Firma4")) { preOrden.setFIRMA_4_IMAGEN(imagenString); }
                    if(firma.equals("Firma5")) { preOrden.setFIRMA_5_IMAGEN(imagenString); }
                    if(firma.equals("Firma6")) { preOrden.setFIRMA_6_IMAGEN(imagenString); }


                    D_B.guardarPreOrden(preOrden);
                    */