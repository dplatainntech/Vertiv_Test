package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.CanvasView;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class PO_FirmasLienzo extends Fragment {
    OperacionesDB D_B;
    String id_PreOrden,firma;
    PreOrden preOrden;
    private CanvasView customCanvas;
    Bitmap firmaGuardada;

    public PO_FirmasLienzo() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_blank, container, false);

        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //((ContenedorCuestionariosPO) getActivity()).ocultarCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden = getArguments().getString("key_idPreOrden");
        firma = getArguments().getString("Firma_aGuardar");
        
        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);

        customCanvas = (CanvasView)v.findViewById(R.id.PO_signature_canvas);

        //region Verfirma
        try{
            if(firma.equals("Firma1")){
                    byte[] decodedString = Base64.decode(preOrden.getFIRMA_1_IMAGEN(), Base64.DEFAULT);
                    firmaGuardada = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //customCanvas.drawBitmap();
                customCanvas.pintarValor(firmaGuardada);
            }



        }catch (Exception e){}
        //endregion







        ImageView no = (ImageView)v.findViewById(R.id.PO_ButtonFirmaNo);
        ImageView si = (ImageView)v.findViewById(R.id.PO_ButtonFirmaOk);
        ImageView limpiar = (ImageView)v.findViewById(R.id.PO_ButtonFirmaLimpiar);

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
                   final String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);

                   try{
                       if(firma.equals("Firma1")) {
                           preOrden.setFIRMA_1_IMAGEN(Base64.encodeToString(imagen, Base64.DEFAULT)); }
                   }catch (Exception e){
                       Log.d("Imagen:", e.getMessage());
                   }
                    if(firma.equals("Firma2")) { preOrden.setFIRMA_2_IMAGEN(imagenString); }
                    if(firma.equals("Firma3")) { preOrden.setFIRMA_3_IMAGEN(imagenString); }
                    if(firma.equals("Firma4")) { preOrden.setFIRMA_4_IMAGEN(imagenString); }
                    if(firma.equals("Firma5")) { preOrden.setFIRMA_5_IMAGEN(imagenString); }
                    if(firma.equals("Firma6")) { preOrden.setFIRMA_6_IMAGEN(imagenString); }


                    D_B.guardarPreOrden(preOrden);
                    Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                    Intent siguiente = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                    siguiente.putExtra("FormilarioPO", "Firmas");
                    siguiente.putExtra("key_idPreOrden", preOrden.getFOLIO());
                    startActivity(siguiente);

                    /*  ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
                    String firma = "iVBORw0KGgoAAAANSUhEUgAACEYAAAPtCAYAAACJ3vhxAAAABHNCSVQICAgIfAhkiAAAIABJREFUeJzs3X3Q5WV93/H37rI87MryIGzBZq1iVAxjnYaNmOjEIEUziWk0hmJTG5unUaL1CasYoyHGWBsfUFGjYrTmgRBJiG2dTjQ2sS1jiZqKGUkEdEpREkB3m4VlYVl26R9nnTJw9pzf2b33/t0Lr9fMb+6d2XO+1+f8d82Zz7muAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZlg1dgAAAAAADopV1YbqmL3P8ff5e3x1dIt/N3ThEuYDAACAZaEYAQAAAHBoO7w6vXpCdWr1+L1/H1OtWeK1fJcEAADAIeewsQMAAAAAsJATqx+4z/PkJuUIAAAAYArFCAAAAICVa0P1vdX3VZv3PqeMmggAAAAOMYoRAAAAACvHSdWZ1VnV9ze5HsP1FQAAAHAAFCMAAAAAxnNs9UPVM5qUIb5niefvqP5+77Ol2lZt3fvv25Z4LQAAAFiR/OIAAAAAYPmsrZ5SPbM6u8kVGauXYO4N1V9Vf3O/5/YlmA0AAACHNMUIAAAAgIPr1CYliLObnAyx/gDn/U31+epL1dV7n20HOBMAAAAAAAAAYJAN1XOrD1Q3Vfce4HPt3lnPrzYu4+cAAACABwUnRgAAAAAcmFXVP6meVf1w9dRqzX7O2t3kBIjPVVdW/726eQkyAgAAAAAAAAAMdmT1Y9VHq2+3/6dB7Kj+S/XL1ZnVUcv5IQAAAAAAAAAAvuPY6l9Wl1fb2/8yxJer36jOqg5f1k8AAAAAAAAAAHAfj6h+sfp0tav9K0JsqS6t/nV14rKmBwAAAAAAAAC4n1OrC6q/aP+KEHuqL1Zvqr6/Wr288QEAAAAAAAAA/r9V1ZOrf1dd1/6VIbZWl1UvzKkQAAAAAAAAAMDI1lZnV++rbmr/yhA3VhdXz6jWLG98AAAAAAAAAIAH2ly9u9rS/pUhrqnevHcOAAAAAAAAAMDoHlm9rrq+xYsQe6rPVa+pHrPcwQEAAAAAAAAAptlQ/Wz1503KDYuUIe6u/qR6cXXScgcHAAAAAAAAAJjmsOpHqt+v7myxMsTt1cern2pSqgAAAAAAAAAAWBFOr95VfavFyhC7qk9Wz6+OXPbUAAAAAAAAAAD7sKm6oLq2xcoQ91b/s3ppdfyypwYAAAAAAAAA2IfV1Y9W/7Ha3WJliOurC6tHL3doAAAAAAAAAIBZHlH9cnVTi5Uhbq0urp6y/JEBAAAAAAAAAPZtdfWs6o";

                    byte[] decodedString = Base64.decode(imagenString, Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    */
                    //firmaVer.setImageBitmap(decodedByte);

                    Log.d("Imagen:", imagenString);
                }
                else {
                    Toast.makeText(getContext(), "Aun no se ha firmado", Toast.LENGTH_LONG).show();
                }
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente = new Intent(getActivity(), ContenedorCuestionariosPO.class);
                siguiente.putExtra("FormilarioPO", "Firmas");
                siguiente.putExtra("key_idPreOrden", preOrden.getFOLIO());
                startActivity(siguiente);
            }
        });

        return v;
    }

}
