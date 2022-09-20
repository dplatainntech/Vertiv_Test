package com.maju.desarrollo.testfcs.Formatos.IN2;


import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN2Lienzo extends Fragment {
    OperacionesDB D_B;
    String id_formato,firma;;
    Bestel2 formato;
    private CanvasView customCanvas;
    Bitmap firmaGuardada;

    public IN2Lienzo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_in2_lienzo, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();


        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato = getArguments().getString("key_idFormato");
        firma = getArguments().getString("Firma_aGuardar");

        formato = D_B.obtenerBestel2(id_formato);

        customCanvas = (CanvasView)v.findViewById(R.id.signature_canvas);



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

                    Matrix matrix = new Matrix();
                    matrix.postRotate(90);
                    //Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmapOrg, width, height, true);
                    //Bitmap rotatedBitmap = Bitmap.createBitmap(bitmap2, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);

                    byte[] imagen = stream.toByteArray();
                    String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);


                    if(firma.equals("Firma1")) { formato.setFirmaVertiv(imagenString); }
                    if(firma.equals("Firma2")) { formato.setFirmaCliente(imagenString); }

                    D_B.guardarBestel2(formato);

                    //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                    Bundle args = new Bundle();
                    args.putString("key_idFormato", id_formato);

                    IN2Firmas myfargment = new IN2Firmas();
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

                IN2Firmas myfargment = new IN2Firmas();
                myfargment.setArguments(args);
                FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            }
        });

        return v;
    }

}
