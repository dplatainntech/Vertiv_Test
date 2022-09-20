package com.maju.desarrollo.testfcs.Formatos.Garantias;


import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Check.Save;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Dialog.fotoView;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.SGarantias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.io.ByteArrayOutputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class GarantiasGarantia extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    SGarantias Formato;
    TextView fechaLote;
    String imagenString=null;
    TextView estadoFoto;
    public GarantiasGarantia() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_garantias_garantia, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        Formato = D_B.obtenerGarantia_id(id_formato);


        //id_formato= getArguments().getString("key_idFormato");

        final Spinner tipoGarantia = (Spinner)v.findViewById(R.id.tipoGarantia);

        final EditText folioCCC= (EditText)v.findViewById(R.id.folioCCC);
        fechaLote = (TextView)v.findViewById(R.id.fechaLote);
        final EditText lote= (EditText)v.findViewById(R.id.lote);
        final EditText aduana= (EditText)v.findViewById(R.id.aduana);
        final EditText nuevoserial= (EditText)v.findViewById(R.id.nuevoserial);
        final EditText reportefalla= (EditText)v.findViewById(R.id.reportefalla);
        final EditText accioncorrectiva= (EditText)v.findViewById(R.id.accioncorrectiva);
        final EditText comentario= (EditText)v.findViewById(R.id.comentario);
        estadoFoto = (TextView)v.findViewById(R.id.validacionF);
        ImageView adjuntar = (ImageView)v.findViewById(R.id.fotoAdj);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tipoGarantiaGarantia, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoGarantia.setAdapter(adapter);




        try{tipoGarantia.setSelection(adapter.getPosition(Formato.getTIPOGARANTIA().toString()));}catch(Exception e){}
        folioCCC.setText(Formato.getFOLIOCCC());//= (EditText)v.findViewById(R.id.folioCCC);
        fechaLote.setText(Formato.getFECHALOTE());//= (EditText)v.findViewById(R.id.fechaLote);
        lote.setText(Formato.getLOTE());//= (EditText)v.findViewById(R.id.lote);
        aduana.setText(Formato.getADUANA());//= (EditText)v.findViewById(R.id.aduana);
        nuevoserial.setText(Formato.getNUEVOSERIAL());//= (EditText)v.findViewById(R.id.nuevoserial);
        reportefalla.setText(Formato.getREPORTEFALLA());//= (EditText)v.findViewById(R.id.reportefalla);
        accioncorrectiva.setText(Formato.getACCIONCORRECTIVA());//= (EditText)v.findViewById(R.id.accioncorrectiva);
        comentario.setText(Formato.getCOMENTARIOS());//= (EditText)v.findViewById(R.id.comentario);

        try{ if(Formato.getGarantiaArchivo().length()>10){
            estadoFoto.setTextColor(Color.parseColor("#52D211"));} }catch (Exception e){}
        imagenString = Formato.getGarantiaArchivo();

        adjuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity().getApplicationContext(), "entro", Toast.LENGTH_SHORT).show();
                tomarFoto();
            }
        });


        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Garantias");
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

                Formato.setTIPOGARANTIA(tipoGarantia.getSelectedItem().toString());//
                Formato.setFOLIOCCC(folioCCC.getText().toString());//= (EditText)v.findViewById(R.id.folioCCC);
                Formato.setFECHALOTE(fechaLote.getText().toString());//= (EditText)v.findViewById(R.id.fechaLote);
                Formato.setLOTE(lote.getText().toString());//= (EditText)v.findViewById(R.id.lote);
                Formato.setADUANA(aduana.getText().toString());//= (EditText)v.findViewById(R.id.aduana);
                Formato.setNUEVOSERIAL(nuevoserial.getText().toString());//= (EditText)v.findViewById(R.id.nuevoserial);
                Formato.setREPORTEFALLA(reportefalla.getText().toString());//= (EditText)v.findViewById(R.id.reportefalla);
                Formato.setACCIONCORRECTIVA(accioncorrectiva.getText().toString());//= (EditText)v.findViewById(R.id.accioncorrectiva);
                Formato.setCOMENTARIOS(comentario.getText().toString());//= (EditText)v.findViewById(R.id.comentario);
                Formato.setGarantiaArchivo(imagenString);
                D_B.guardarGarantias(Formato);


                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                GarantiasMenu myfargment = new GarantiasMenu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });


        fechaLote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });

        checkExternalStoragePermission();
        return v;
    }
    private void showDatePickerDialog1() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fechaLote.setText(selectedDate);

            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }


    InternetandVPN funciones = new InternetandVPN();
    Save savefile = new Save();

    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    private Uri imageUri;
    String imageurl;
    Bitmap imageBitmap;

    public void tomarFoto() {
        values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "MyPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
        imageUri = getActivity().getContentResolver().insert(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

        startActivityForResult(intent, PICTURE_RESULT);


    }

    /*
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            //region guardar foto (no activada)
            //tomarfoto.setVisibility(View.GONE);
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            byte[] imagen = stream.toByteArray();
            imagenString = Base64.encodeToString(imagen, Base64.DEFAULT);
            estadoFoto.setTextColor(Color.parseColor("#52D211"));
        }
    }

     */

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

                    //Bitmap bitmap= Bitmap.createBitmap((int)(imageBitmap.getWidth() * 0.50),(int)(imageBitmap.getHeight()*.50),Bitmap.Config.ARGB_8888);//(int)(.70 * widthActivityPhone),(int)(.80 * height),Bitmap.Config.ARGB_8888);
                    Bitmap bitmap= Bitmap.createBitmap(imageBitmap.getWidth(),imageBitmap.getHeight(),Bitmap.Config.ARGB_8888);
                    Canvas canvas = new Canvas(bitmap);
                    Paint paint = new Paint();


                    canvas.drawBitmap(imageBitmap,10,10,paint);

                    paint.setAntiAlias(true);
                    paint.setTextSize(35.f);
                    paint.setTextAlign(Paint.Align.LEFT);
                    paint.setColor(Color.parseColor("#33FF66"));

                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] imagen = stream.toByteArray();


                    String imagenStringF = Base64.encodeToString(imagen, Base64.DEFAULT);
                    Bundle valores = new Bundle();
                    valores.putString("Foto",imagenStringF);

                    fotoView alerta = new fotoView();
                    alerta.setArguments(valores);
                    alerta.setCancelable(false);
                    alerta.show(getFragmentManager(), "a");

                    imagenString = savefile.SaveImage(getContext(), bitmap);

                    estadoFoto.setTextColor(Color.parseColor("#52D211"));

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
