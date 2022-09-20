package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Check.Save;
import com.maju.desarrollo.testfcs.Dialog.fotoView;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.Formatos.PreOrden.MenuPOFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class POFotosFragment extends Fragment {

    String id_formato;
    Bitmap imageBitmap;
    String sLatitud = "0.0";
    String sLongitud = "0.0";
    OperacionesDB D_B;
    PreOrden Formato;
    Save savefile = new Save();
    InternetandVPN funciones = new InternetandVPN();


    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    private Uri imageUri;

    String imageurl;
    ImageView foto1_A_icon,foto2_A_icon,foto3_A_icon;
    String [] coord;
    String Foto_guardar;
    String FOTO_EPP,FOTO_HERRAMIENTA,FOTO_EQUIPO;


    public POFotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_pofotos, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        //((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idPreOrden");

        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerPreOrden_id(id_formato);

        LinearLayout F1A = (LinearLayout)v.findViewById(R.id.Foto1A);
        LinearLayout F2A = (LinearLayout)v.findViewById(R.id.Foto2A);
        LinearLayout F3A = (LinearLayout)v.findViewById(R.id.Foto3A);


        foto1_A_icon = (ImageView)v.findViewById(R.id.fotoT1);
        foto2_A_icon = (ImageView)v.findViewById(R.id.fotoT2);
        foto3_A_icon = (ImageView)v.findViewById(R.id.fotoT3);


        try{ if(Formato.getFOTO_EPP().length()>10){foto1_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getFOTO_HERRAMIENTA().length()>10){foto2_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getFOTO_EQUIPO().length()>10){foto3_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}


        FOTO_EPP = Formato.getFOTO_EPP();
        FOTO_HERRAMIENTA = Formato.getFOTO_HERRAMIENTA();
        FOTO_EQUIPO = Formato.getFOTO_EQUIPO();




        //region ACCIONES FOTOS
        F1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                String a = ((ContenedorCuestionariosPO) getActivity()).obtenerCoordenadas();
                coord = a.split("&");
                Foto_guardar = "1A";
                inicioF();
            }
        });
        F2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(FOTO_EPP != null && FOTO_EPP.length()>10){
                    String a = ((ContenedorCuestionariosPO) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "2A";
                    inicioF();
                }

            }
        });
        F3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(FOTO_HERRAMIENTA != null && FOTO_HERRAMIENTA.length()>10){
                    String a = ((ContenedorCuestionariosPO) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "3A";
                    inicioF();
                }

            }
        });



        //endregion

        //region Botones
        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                /*Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", id_formato);

                CancelasDialogFragmentMismoActivity dialog= new CancelasDialogFragmentMismoActivity();
                dialog.setCancelable(true);
                dialog.setTargetFragment(getParentFragment(),1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");*/
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", Formato.getFOLIO());

                CancelasDialogFragment dialog= new CancelasDialogFragment();
                dialog.setCancelable(true);
                dialog.setTargetFragment(POFotosFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                //D_B.guardarPower1(dcPower);
                Formato.setFOTO_EPP(FOTO_EPP);
                Formato.setFOTO_HERRAMIENTA(FOTO_HERRAMIENTA);
                Formato.setFOTO_EQUIPO(FOTO_EQUIPO);


                D_B.guardarPreOrden(Formato);


                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                siguiente.putExtra("valorPaso", Formato.getFOLIO());
                startActivity(siguiente);

            }
        });
        //endregion

        checkExternalStoragePermission();

        return v;
    }


    public void inicioF(){
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "MyPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
        imageUri = getActivity().getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, PICTURE_RESULT);
        //getActivity().startActivityForResult(intent, PICTURE_RESULT);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICTURE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    //imageBitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);  //(Bitmap) data.getExtras().get("data");
                    //Obtiene la ruta donde se encuentra guardada la imagen.
                    imageurl = getRealPathFromURI(imageUri);

                    //imageBitmap = (BitmapFactory.decodeFile(imageurl));
                    imageBitmap = funciones.compressImage(imageurl);

                    //File dir = new File(imageUri.toString());
                    //dir.delete();

                    sLatitud =coord[0]; sLongitud = coord[1];

                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                    String horaStamp = new SimpleDateFormat("HH:mm").format(new Date());
                    String ltd = sLatitud + "  " + sLongitud ;

                    String fch = timeStamp + "  " + horaStamp + " Horas";

                    //Bitmap bitmap= Bitmap.createBitmap((int)(imageBitmap.getWidth() * 0.50),(int)(imageBitmap.getHeight()*.50),Bitmap.Config.ARGB_8888);//(int)(.70 * widthActivityPhone),(int)(.80 * height),Bitmap.Config.ARGB_8888);
                    Bitmap bitmap= Bitmap.createBitmap(imageBitmap.getWidth(),imageBitmap.getHeight(),Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();


                    canvas.drawBitmap(imageBitmap,10,10,paint);

                    paint.setAntiAlias(true);
                    paint.setTextSize(35.f);
                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setColor(Color.parseColor("#33FF66"));
                    canvas.drawText(ltd,30,imageBitmap.getHeight() - (int)(imageBitmap.getHeight() *.18),paint);
                    canvas.drawText(fch,30, imageBitmap.getHeight() - (int)(imageBitmap.getHeight()*.10),paint);


                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] imagen = stream.toByteArray();

                    String imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
                    Bundle valores = new Bundle();
                    valores.putString("Foto",imagenString);

                    fotoView alerta = new fotoView();
                    alerta.setArguments(valores);
                    alerta.setCancelable(false);
                    alerta.show(getFragmentManager(), "a");

                    //Se guarda imagen comprimida y con coordenadas y se obtiene el nombre(ese dato es el que se guarda el DB)
                    imagenString = savefile.SaveImage(getContext(), bitmap);


                    if(Foto_guardar.equals("1A")){foto1_A_icon.setImageResource(R.drawable.fotoverde);FOTO_EPP = imagenString ;}
                    if(Foto_guardar.equals("2A")){foto2_A_icon.setImageResource(R.drawable.fotoverde);FOTO_HERRAMIENTA = imagenString;}
                    if(Foto_guardar.equals("3A")){foto3_A_icon.setImageResource(R.drawable.fotoverde);FOTO_EQUIPO = imagenString;}



                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(),
                            "Foto no guardada, intentar nuevamente", Toast.LENGTH_SHORT).show();
                }

            }
            else{
                Toast.makeText(getContext(),
                        "Foto no guardada, intentar nuevamente", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(),
                    "Foto no guardada, intentar nuevamente", Toast.LENGTH_SHORT).show();
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private boolean checkExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer!");
            return true;
        }

        return false;
    }



}








