package com.maju.desarrollo.testfcs;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Areafirma extends AppCompatActivity {

    private CanvasView customCanvas;
    String NOMBRE_DIRECTORIO = "MisPDFs";
    String NOMBRE_DOCUMENTO = "Vertiv_";
    Bitmap bmFoto;
    String bmFirma1;
    String bmFirma2;
    Bitmap enviar;
    ImageView firmaVer;
    String imagenString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_areafirma);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        bmFoto = (Bitmap) intent.getParcelableExtra("KetFoto");
        bmFirma1 = intent.getExtras().getString("KetFirma1");
        bmFirma2 = intent.getExtras().getString("KetFirma2");
        //firmaVer = (ImageView) findViewById(R.id.imageView2);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);

        crearPDF();

    }


    public void confirmar(View view){
        //ver_firma();
        if (customCanvas.comprovarCanvas()==true) {

            customCanvas.setDrawingCacheEnabled(true);
            Bitmap bitmap2 =  customCanvas.getDrawingCache(); //customCanvas.getDrawingCache();

            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap2.compress(Bitmap.CompressFormat.PNG, 50, stream);
            byte[] imagen = stream.toByteArray();
            imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);


            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            String firma = "iVBORw0KGgoAAAANSUhEUgAACEYAAAPtCAYAAACJ3vhxAAAABHNCSVQICAgIfAhkiAAAIABJREFUeJzs3X3Q5WV93/H37rI87MryIGzBZq1iVAxjnYaNmOjEIEUziWk0hmJTG5unUaL1CasYoyHGWBsfUFGjYrTmgRBJiG2dTjQ2sS1jiZqKGUkEdEpREkB3m4VlYVl26R9nnTJw9pzf2b33/t0Lr9fMb+6d2XO+1+f8d82Zz7muAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZlg1dgAAAAAADopV1YbqmL3P8ff5e3x1dIt/N3ThEuYDAACAZaEYAQAAAHBoO7w6vXpCdWr1+L1/H1OtWeK1fJcEAADAIeewsQMAAAAAsJATqx+4z/PkJuUIAAAAYArFCAAAAICVa0P1vdX3VZv3PqeMmggAAAAOMYoRAAAAACvHSdWZ1VnV9ze5HsP1FQAAAHAAFCMAAAAAxnNs9UPVM5qUIb5niefvqP5+77Ol2lZt3fvv25Z4LQAAAFiR/OIAAAAAYPmsrZ5SPbM6u8kVGauXYO4N1V9Vf3O/5/YlmA0AAACHNMUIAAAAgIPr1CYliLObnAyx/gDn/U31+epL1dV7n20HOBMAAAAAAAAAYJAN1XOrD1Q3Vfce4HPt3lnPrzYu4+cAAACABwUnRgAAAAAcmFXVP6meVf1w9dRqzX7O2t3kBIjPVVdW/726eQkyAgAAAAAAAAAMdmT1Y9VHq2+3/6dB7Kj+S/XL1ZnVUcv5IQAAAAAAAAAAvuPY6l9Wl1fb2/8yxJer36jOqg5f1k8AAAAAAAAAAHAfj6h+sfp0tav9K0JsqS6t/nV14rKmBwAAAAAAAAC4n1OrC6q/aP+KEHuqL1Zvqr6/Wr288QEAAAAAAAAA/r9V1ZOrf1dd1/6VIbZWl1UvzKkQAAAAAAAAAMDI1lZnV++rbmr/yhA3VhdXz6jWLG98AAAAAAAAAIAH2ly9u9rS/pUhrqnevHcOAAAAAAAAAMDoHlm9rrq+xYsQe6rPVa+pHrPcwQEAAAAAAAAAptlQ/Wz1503KDYuUIe6u/qR6cXXScgcHAAAAAAAAAJjmsOpHqt+v7myxMsTt1cern2pSqgAAAAAAAAAAWBFOr95VfavFyhC7qk9Wz6+OXPbUAAAAAAAAAAD7sKm6oLq2xcoQ91b/s3ppdfyypwYAAAAAAAAA2IfV1Y9W/7Ha3WJliOurC6tHL3doAAAAAAAAAIBZHlH9cnVTi5Uhbq0urp6y/JEBAAAAAAAAAPZtdfWs6o";

            byte[] decodedString = Base64.decode(imagenString, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            //firmaVer.setImageBitmap(decodedByte);

            Log.d("Imagen:", imagenString);


            //region Pendiente

           // Intent siguiente2 = new Intent(Areafirma.this, FirmasPet.class);
           // String s = "";
           // siguiente2.putExtra("KetFoto", bmFoto);
            //startActivity(siguiente2);
            //overridePendingTransition(R.anim.left_in, R.anim.left_out);

                    if(bmFirma1.equals("NO")) {
                        Intent saltar = new Intent(Areafirma.this, FirmasPet.class);

                        saltar.putExtra("KetFoto", bmFoto);
                        saltar.putExtra("KetFirma1", imagenString);
                        saltar.putExtra("KetFirma2", bmFirma2);
                        //Toast.makeText(getApplicationContext(), "Firma 1", Toast.LENGTH_LONG).show();
                        startActivity(saltar);
                        overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    }else {
                        Intent saltar = new Intent(Areafirma.this, FirmasPet.class);
                        //String s = "";
                        saltar.putExtra("KetFoto", bmFoto);
                        saltar.putExtra("KetFirma1", bmFirma1);
                        saltar.putExtra("KetFirma2", imagenString);
                        //Toast.makeText(getApplicationContext(), "Firma 2", Toast.LENGTH_SHORT).show();
                        startActivity(saltar);
                        overridePendingTransition(R.anim.left_in, R.anim.left_out);
                    }

                    //onBackPressed();

                    //endregion
        }
        else {
            Toast.makeText(getApplicationContext(), "Aun no se ha firmado", Toast.LENGTH_LONG).show();
        }
    }


    public void ver_firma(){
        //region base 64
        String firma = "iVBORw0KGgoAAAANSUhEUgAACEYAAAPtCAYAAACJ3vhxAAAABHNCSVQICAgIfAhkiAAAIABJREFUeJzs3X3Q5WV93/H37rI87MryIGzBZq1iVAxjnYaNmOjEIEUziWk0hmJTG5unUaL1CasYoyHGWBsfUFGjYrTmgRBJiG2dTjQ2sS1jiZqKGUkEdEpREkB3m4VlYVl26R9nnTJw9pzf2b33/t0Lr9fMb+6d2XO+1+f8d82Zz7muAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZlg1dgAAAAAADopV1YbqmL3P8ff5e3x1dIt/N3ThEuYDAACAZaEYAQAAAHBoO7w6vXpCdWr1+L1/H1OtWeK1fJcEAADAIeewsQMAAAAAsJATqx+4z/PkJuUIAAAAYArFCAAAAICVa0P1vdX3VZv3PqeMmggAAAAOMYoRAAAAACvHSdWZ1VnV9ze5HsP1FQAAAHAAFCMAAAAAxnNs9UPVM5qUIb5niefvqP5+77Ol2lZt3fvv25Z4LQAAAFiR/OIAAAAAYPmsrZ5SPbM6u8kVGauXYO4N1V9Vf3O/5/YlmA0AAACHNMUIAAAAgIPr1CYliLObnAyx/gDn/U31+epL1dV7n20HOBMAAAAAAAAAYJAN1XOrD1Q3Vfce4HPt3lnPrzYu4+cAAACABwUnRgAAAAAcmFXVP6meVf1w9dRqzX7O2t3kBIjPVVdW/726eQkyAgAAAAAAAAAMdmT1Y9VHq2+3/6dB7Kj+S/XL1ZnVUcv5IQAAAAAAAAAAvuPY6l9Wl1fb2/8yxJer36jOqg5f1k8AAAAAAAAAAHAfj6h+sfp0tav9K0JsqS6t/nV14rKmBwAAAAAAAAC4n1OrC6q/aP+KEHuqL1Zvqr6/Wr288QEAAAAAAAAA/r9V1ZOrf1dd1/6VIbZWl1UvzKkQAAAAAAAAAMDI1lZnV++rbmr/yhA3VhdXz6jWLG98AAAAAAAAAIAH2ly9u9rS/pUhrqnevHcOAAAAAAAAAMDoHlm9rrq+xYsQe6rPVa+pHrPcwQEAAAAAAAAAptlQ/Wz1503KDYuUIe6u/qR6cXXScgcHAAAAAAAAAJjmsOpHqt+v7myxMsTt1cern2pSqgAAAAAAAAAAWBFOr95VfavFyhC7qk9Wz6+OXPbUAAAAAAAAAAD7sKm6oLq2xcoQ91b/s3ppdfyypwYAAAAAAAAA2IfV1Y9W/7Ha3WJliOurC6tHL3doAAAAAAAAAIBZHlH9cnVTi5Uhbq0urp6y/JEBAAAAAAAAAPZtdfWs6o";

        //endregion

        byte[] decodedString = Base64.decode(firma, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        customCanvas.pintarValor(decodedByte);
        //imageView.setImageBitmap(decodedByte);
    }

    public void crearPDF() {
        Document documento = new Document();

        try {
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File file = crearFichero("Firma_Vertiv_" + timeStamp + ".pdf");
            FileOutputStream ficheroPDF = new FileOutputStream(file.getAbsolutePath());

            PdfWriter writer = PdfWriter.getInstance(documento, ficheroPDF);

            documento.open();

            documento.add(new Paragraph("VERTIV \n\n"));
            documento.add(new Paragraph( " Texto 1\n\n"));

            // Insertamos una tabla
            PdfPTable tabla = new PdfPTable(5);
            for(int i = 0 ; i < 15 ; i++) {
                tabla.addCell("CELDA "+i);
            }

            documento.add(tabla);

           /* customCanvas.setDrawingCacheEnabled(true);

            Bitmap bitmap2 = customCanvas.getDrawingCache();
*/
            ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
            String firma = "iVBORw0KGgoAAAANSUhEUgAACEYAAAPtCAYAAACJ3vhxAAAABHNCSVQICAgIfAhkiAAAIABJREFUeJzs3X3Q5WV93/H37rI87MryIGzBZq1iVAxjnYaNmOjEIEUziWk0hmJTG5unUaL1CasYoyHGWBsfUFGjYrTmgRBJiG2dTjQ2sS1jiZqKGUkEdEpREkB3m4VlYVl26R9nnTJw9pzf2b33/t0Lr9fMb+6d2XO+1+f8d82Zz7muAgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAZlg1dgAAAAAADopV1YbqmL3P8ff5e3x1dIt/N3ThEuYDAACAZaEYAQAAAHBoO7w6vXpCdWr1+L1/H1OtWeK1fJcEAADAIeewsQMAAAAAsJATqx+4z/PkJuUIAAAAYArFCAAAAICVa0P1vdX3VZv3PqeMmggAAAAOMYoRAAAAACvHSdWZ1VnV9ze5HsP1FQAAAHAAFCMAAAAAxnNs9UPVM5qUIb5niefvqP5+77Ol2lZt3fvv25Z4LQAAAFiR/OIAAAAAYPmsrZ5SPbM6u8kVGauXYO4N1V9Vf3O/5/YlmA0AAACHNMUIAAAAgIPr1CYliLObnAyx/gDn/U31+epL1dV7n20HOBMAAAAAAAAAYJAN1XOrD1Q3Vfce4HPt3lnPrzYu4+cAAACABwUnRgAAAAAcmFXVP6meVf1w9dRqzX7O2t3kBIjPVVdW/726eQkyAgAAAAAAAAAMdmT1Y9VHq2+3/6dB7Kj+S/XL1ZnVUcv5IQAAAAAAAAAAvuPY6l9Wl1fb2/8yxJer36jOqg5f1k8AAAAAAAAAAHAfj6h+sfp0tav9K0JsqS6t/nV14rKmBwAAAAAAAAC4n1OrC6q/aP+KEHuqL1Zvqr6/Wr288QEAAAAAAAAA/r9V1ZOrf1dd1/6VIbZWl1UvzKkQAAAAAAAAAMDI1lZnV++rbmr/yhA3VhdXz6jWLG98AAAAAAAAAIAH2ly9u9rS/pUhrqnevHcOAAAAAAAAAMDoHlm9rrq+xYsQe6rPVa+pHrPcwQEAAAAAAAAAptlQ/Wz1503KDYuUIe6u/qR6cXXScgcHAAAAAAAAAJjmsOpHqt+v7myxMsTt1cern2pSqgAAAAAAAAAAWBFOr95VfavFyhC7qk9Wz6+OXPbUAAAAAAAAAAD7sKm6oLq2xcoQ91b/s3ppdfyypwYAAAAAAAAA2IfV1Y9W/7Ha3WJliOurC6tHL3doAAAAAAAAAIBZHlH9cnVTi5Uhbq0urp6y/JEBAAAAAAAAAPZtdfWs6o";

            byte[] decodedString = Base64.decode(firma, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            decodedByte.compress(Bitmap.CompressFormat.JPEG, 100 , stream2);

            Image myImg2 = Image.getInstance(stream2.toByteArray());

           //myImg2.rotate();
           myImg2.scaleAbsoluteWidth(200);
           myImg2.scaleAbsoluteHeight(80);
            myImg2.setAlignment(Image.MIDDLE);



            documento.add(myImg2);


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