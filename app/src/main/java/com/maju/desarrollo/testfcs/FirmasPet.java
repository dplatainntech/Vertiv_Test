package com.maju.desarrollo.testfcs;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FirmasPet extends AppCompatActivity {

    String NOMBRE_DIRECTORIO = "MisPDFs";
    String NOMBRE_DOCUMENTO = "Vertiv_";
    ImageView guardar;
    Bitmap bmFoto;
    String bmFirma1 = "";
    String bmFirma2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firmas_pet);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        bmFoto = (Bitmap) intent.getParcelableExtra("KetFoto");
        //parametros.getString("keypass");
        bmFirma1 = intent.getExtras().getString("KetFirma1");
        bmFirma2 = intent.getExtras().getString("KetFirma2");

        Button siguiente = (Button)findViewById(R.id.buttonF1);
        Button siguiente2 = (Button)findViewById(R.id.buttonF2);
         guardar = (ImageView)findViewById(R.id.imageViewGuardar);

         if(bmFirma1.equals("NO")){

         }else{
             siguiente.setBackgroundColor((Color.GREEN));
         }
        if(bmFirma2.equals("NO")){}else{
            siguiente2.setBackgroundColor((Color.GREEN));
        }



        ImageButton atras = (ImageButton)findViewById(R.id.imageButtonAtrasFP);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // region pedir Firmas
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente2 = new Intent(FirmasPet.this, Areafirma.class);
                String s = "";
                siguiente2.putExtra("KetFoto", bmFoto);
                siguiente2.putExtra("KetFirma1", bmFirma1);
                siguiente2.putExtra("KetFirma2", bmFirma2);
                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        siguiente2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente2 = new Intent(FirmasPet.this, Areafirma.class);
                String s = "";
                siguiente2.putExtra("KetFoto", bmFoto);
                siguiente2.putExtra("KetFirma1", bmFirma1);
                siguiente2.putExtra("KetFirma2", bmFirma2);
                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
//endregion

        //Permisos para guardar datos
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) !=
                        PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,},
                    1000);
        }

        // region Genera el documento
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bmFirma1 != null){
                    Toast.makeText(FirmasPet.this, "Falta Firma 1", Toast.LENGTH_LONG).show();
                }else if(bmFirma2 != null){ Toast.makeText(FirmasPet.this, "Falta Firma 2", Toast.LENGTH_LONG).show();}
                else {
                    crearPDF();
                    Toast.makeText(FirmasPet.this, "SE CREO EL PDF EN DESCARGAS", Toast.LENGTH_LONG).show();
                }

                Intent siguiente2 = new Intent(FirmasPet.this, MainActivity.class);
                String s = "";

                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);

            }
        });

        //endregion

    }

    public void crearPDF() {
        Document documento = new Document();

        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File file = crearFichero(NOMBRE_DOCUMENTO + timeStamp + ".pdf");
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();

            documento.add(new Paragraph("VERTIV \n\n"));
            documento.add(new Paragraph( "Foto\n\n"));
/*
            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(5);
            for(int i = 0 ; i < 15 ; i++) {
                tabla.addCell("CELDA "+i);
            }

            documento.add(tabla);
*/
            ByteArrayOutputStream streamFoto = new ByteArrayOutputStream();
            bmFoto.compress(Bitmap.CompressFormat.PNG, 10 , streamFoto);
            Image myImgFoto = Image.getInstance(streamFoto.toByteArray());
            myImgFoto.scaleAbsoluteWidth(250);
            myImgFoto.scaleAbsoluteHeight(350);
            documento.add(myImgFoto);


            documento.add(new Paragraph( "Firma 1\n\n"));


         /*   ByteArrayOutputStream streamF1 = new ByteArrayOutputStream();
            bmFirma1.compress(Bitmap.CompressFormat.PNG, 10 , streamF1);
            Image myImgF1 = Image.getInstance(streamF1.toByteArray());
            myImgF1.scaleAbsoluteWidth(200);
            myImgF1.scaleAbsoluteHeight(80);
            documento.add(myImgF1);
*/
            documento.add(new Paragraph( "Firma 2\n\n"));

         /*   ByteArrayOutputStream streamF2 = new ByteArrayOutputStream();
            bmFirma2.compress(Bitmap.CompressFormat.PNG, 10 , streamF2);
            Image myImgF2 = Image.getInstance(streamF2.toByteArray());
            myImgF2.scaleAbsoluteWidth(200);
            myImgF2.scaleAbsoluteHeight(80);
            myImgF2.setAlignment(Image.MIDDLE);
            documento.add(myImgF2);
*/

        } catch(DocumentException e) {
        } catch(IOException e) {
        } finally {
            documento.close();
        }
    }

    public File crearFichero(String nombreFichero) {
        File ruta = getRuta();

        File fichero = null;
        if(ruta != null) {
            fichero = new File(ruta, nombreFichero);
        }

        return fichero;
    }

    public File getRuta() {
        File ruta = null;

        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            ruta = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), NOMBRE_DIRECTORIO);

            if(ruta != null) {
                if(!ruta.mkdirs()) {
                    if(!ruta.exists()) {
                        return null;
                    }
                }
            }

        }
        return ruta;
    }

}
