package com.maju.desarrollo.testfcs.Formatos.UPS;


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
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpsFotosFragment extends Fragment {
    String id_formato;
    Bitmap imageBitmap;
    String sLatitud = "0.0";
    String sLongitud = "0.0";
    OperacionesDB D_B;
    UPS Formato;
    Save savefile = new Save();
    InternetandVPN funciones = new InternetandVPN();

    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    private Uri imageUri;

    String imageurl;
    ImageView foto1_A_icon,foto2_A_icon,foto3_A_icon,foto4_A_icon,foto5_A_icon,foto6_A_icon,foto1_D_icon,foto2_D_icon,foto3_D_icon,foto4_D_icon,foto5_D_icon,foto6_D_icon;
    String [] coord;
    String Foto_guardar;
    String foto1A_Str,foto2A_Str,foto3A_Str,foto4A_Str,foto5A_Str,foto6A_Str,foto1D_Str,foto2D_Str,foto3D_Str,foto4D_Str,foto5D_Str,foto6D_Str;



    public UpsFotosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_ups_fotos, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");

        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerUPS(id_formato);
        //baterias = D_B.obtenerBateriaF_id(id_formato);


        LinearLayout F1A = (LinearLayout)v.findViewById(R.id.Foto1A);
        LinearLayout F2A = (LinearLayout)v.findViewById(R.id.Foto2A);
        LinearLayout F3A = (LinearLayout)v.findViewById(R.id.Foto3A);
        LinearLayout F4A = (LinearLayout)v.findViewById(R.id.Foto4A);
        LinearLayout F5A = (LinearLayout)v.findViewById(R.id.Foto5A);
        LinearLayout F6A = (LinearLayout)v.findViewById(R.id.Foto6A);
        LinearLayout F1D = (LinearLayout)v.findViewById(R.id.Foto1D);
        LinearLayout F2D = (LinearLayout)v.findViewById(R.id.Foto2D);
        LinearLayout F3D = (LinearLayout)v.findViewById(R.id.Foto3D);
        LinearLayout F4D = (LinearLayout)v.findViewById(R.id.Foto4D);
        LinearLayout F5D = (LinearLayout)v.findViewById(R.id.Foto5D);
        LinearLayout F6D = (LinearLayout)v.findViewById(R.id.Foto6D);

        foto1_A_icon = (ImageView)v.findViewById(R.id.fotoT1);
        foto2_A_icon = (ImageView)v.findViewById(R.id.fotoT2);
        foto3_A_icon = (ImageView)v.findViewById(R.id.fotoT3);
        foto4_A_icon = (ImageView)v.findViewById(R.id.fotoT4);
        foto5_A_icon = (ImageView)v.findViewById(R.id.fotoT5);
        foto6_A_icon = (ImageView)v.findViewById(R.id.fotoT6);

        foto1_D_icon = (ImageView)v.findViewById(R.id.fotoD1);
        foto2_D_icon = (ImageView)v.findViewById(R.id.fotoD2);
        foto3_D_icon = (ImageView)v.findViewById(R.id.fotoD3);
        foto4_D_icon = (ImageView)v.findViewById(R.id.fotoD4);
        foto5_D_icon = (ImageView)v.findViewById(R.id.fotoD5);
        foto6_D_icon = (ImageView)v.findViewById(R.id.fotoD6);


        try{ if(Formato.getAntes1().length()>10){foto1_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getAntes2().length()>10){foto2_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getAntes3().length()>10){foto3_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getAntes4().length()>10){foto4_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getAntes5().length()>10){foto5_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getAntes6().length()>10){foto6_A_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}

        try{ if(Formato.getDespues1().length()>10){foto1_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getDespues2().length()>10){foto2_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getDespues3().length()>10){foto3_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getDespues4().length()>10){foto4_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getDespues5().length()>10){foto5_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}
        try{ if(Formato.getDespues6().length()>10){foto6_D_icon.setImageResource(R.drawable.fotoverde);} }catch (Exception e){}

        foto1A_Str= Formato.getAntes1();
        foto2A_Str= Formato.getAntes2();
        foto3A_Str= Formato.getAntes3();
        foto4A_Str= Formato.getAntes4();
        foto5A_Str= Formato.getAntes5();
        foto6A_Str= Formato.getAntes6();
        foto1D_Str= Formato.getDespues1();
        foto2D_Str= Formato.getDespues2();
        foto3D_Str= Formato.getDespues3();
        foto4D_Str= Formato.getDespues4();
        foto5D_Str= Formato.getDespues5();
        foto6D_Str= Formato.getDespues6();



        //region ACCIONES FOTOS
        F1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                coord = a.split("&");
                Foto_guardar = "1A";
                inicioF();
            }
        });
        F2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(foto1A_Str != null && foto1A_Str.length()>10){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
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
                if(foto2A_Str != null && foto2A_Str.length()>10){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "3A";
                    inicioF();
                }

            }
        });
        F4A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(foto3A_Str != null && foto3A_Str.length()>10){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "4A";
                    inicioF();
                }

            }
        });
        F5A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(foto4A_Str != null && foto4A_Str.length()>10){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "5A";
                    inicioF();
                }

            }
        });
        F6A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if(foto5A_Str != null && foto5A_Str.length()>10){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "6A";
                    inicioF();
                }
            }
        });


        F1D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if((foto1A_Str != null && foto1A_Str.length()>10)   ){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "1D";
                    inicioF();
                }

            }
        });
        F2D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if((foto2A_Str != null && foto2A_Str.length()>10) && (foto1D_Str != null && foto1D_Str.length()>10) ){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "2D";
                    inicioF();
                }

            }
        });
        F3D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if( (foto3A_Str != null && foto3A_Str.length()>10) && (foto2D_Str != null && foto2D_Str.length()>10)){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "3D";
                    inicioF();
                }

            }
        });
        F4D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if((foto4A_Str != null && foto4A_Str.length()>10) && (foto3D_Str != null && foto3D_Str.length()>10) ){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "4D";
                    inicioF();
                }

            }
        });
        F5D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if((foto5A_Str != null && foto5A_Str.length()>10) && (foto4D_Str != null && foto4D_Str.length()>10)){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "5D";
                    inicioF();
                }

            }
        });
        F6D.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // inicioF();
                if((foto6A_Str != null && foto6A_Str.length()>10) && (foto5D_Str != null && foto5D_Str.length()>10)){
                    String a = ((MainActivity) getActivity()).obtenerCoordenadas();
                    coord = a.split("&");
                    Foto_guardar = "6D";
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
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                //D_B.guardarPower1(dcPower);
                Formato.setAntes1(foto1A_Str);
                Formato.setAntes2(foto2A_Str);
                Formato.setAntes3(foto3A_Str);
                Formato.setAntes4(foto4A_Str);
                Formato.setAntes5(foto5A_Str);
                Formato.setAntes6(foto6A_Str);

                Formato.setDespues1(foto1D_Str);
                Formato.setDespues2(foto2D_Str);
                Formato.setDespues3(foto3D_Str);
                Formato.setDespues4(foto4D_Str);
                Formato.setDespues5(foto5D_Str);
                Formato.setDespues6(foto6D_Str);

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


                    if(Foto_guardar.equals("1A")){foto1_A_icon.setImageResource(R.drawable.fotoverde);foto1A_Str = imagenString ;}
                    if(Foto_guardar.equals("2A")){foto2_A_icon.setImageResource(R.drawable.fotoverde);foto2A_Str = imagenString;}
                    if(Foto_guardar.equals("3A")){foto3_A_icon.setImageResource(R.drawable.fotoverde);foto3A_Str = imagenString;}
                    if(Foto_guardar.equals("4A")){foto4_A_icon.setImageResource(R.drawable.fotoverde);foto4A_Str = imagenString;}
                    if(Foto_guardar.equals("5A")){foto5_A_icon.setImageResource(R.drawable.fotoverde);foto5A_Str = imagenString;}
                    if(Foto_guardar.equals("6A")){foto6_A_icon.setImageResource(R.drawable.fotoverde);foto6A_Str = imagenString;}

                    if(Foto_guardar.equals("1D")){foto1_D_icon.setImageResource(R.drawable.fotoverde);foto1D_Str = imagenString;}
                    if(Foto_guardar.equals("2D")){foto2_D_icon.setImageResource(R.drawable.fotoverde);foto2D_Str = imagenString;}
                    if(Foto_guardar.equals("3D")){foto3_D_icon.setImageResource(R.drawable.fotoverde);foto3D_Str = imagenString;}
                    if(Foto_guardar.equals("4D")){foto4_D_icon.setImageResource(R.drawable.fotoverde);foto4D_Str = imagenString;}
                    if(Foto_guardar.equals("5D")){foto5_D_icon.setImageResource(R.drawable.fotoverde);foto5D_Str = imagenString;}
                    if(Foto_guardar.equals("6D")){foto6_D_icon.setImageResource(R.drawable.fotoverde);foto6D_Str = imagenString;}


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
