package com.maju.desarrollo.testfcs.Check;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class ImgB64 {

    private String NameOfFolder = "/VertivApp/Imgs";

    public String ImgB64(String nombreFIle) {
        //String FICHERO = "Nombre_del_fichero.png";
        String imagenString;
        String file_path = Environment.getExternalStorageDirectory().getAbsolutePath() + NameOfFolder;
        File ruta = new File(file_path);
        if (!ruta.exists()) {
            ruta.mkdirs();
        }

        File fichero = new File(ruta, nombreFIle);
        if (fichero.exists()) {
            Bitmap myBitmap = BitmapFactory.decodeFile(fichero.getAbsolutePath());

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            byte[] imagen = stream.toByteArray();

            imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
        }else{
            imagenString = "";
        }

        return imagenString;
    }

}
