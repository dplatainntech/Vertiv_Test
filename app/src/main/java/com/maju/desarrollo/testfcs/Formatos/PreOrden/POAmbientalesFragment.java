package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Check.Save;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class POInspeccionFragment extends Fragment {

    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;
    String imagenString=null;
    TextView estadoFoto;
    RadioButton si1, no1 , si3 , no3 ;
    EditText noPorque1,causas ,evitarAci;
    CheckBox checkBox4;
    Bitmap imageBitmap;
    InternetandVPN funciones = new InternetandVPN();
    Save savefile = new Save();
    UsuarioD usuario ;

    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    private Uri imageUri;
    String imageurl;


    public POInspeccionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v= inflater.inflate(R.layout.fragment_poinspeccion, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);
        usuario = D_B.obtenerUsuario();

       si1 = (RadioButton)v.findViewById(R.id.Inspc_radioB_Si);
        no1 = (RadioButton)v.findViewById(R.id.Inspc_radioB_No);
        noPorque1 = (EditText)v.findViewById(R.id.Inspc_editText_no);
        final CheckBox checkBox1 = (CheckBox)v.findViewById(R.id.Inspc_check1);
        final CheckBox checkBox2 = (CheckBox)v.findViewById(R.id.Inspc_check2);
        final CheckBox checkBox3 = (CheckBox)v.findViewById(R.id.Inspc_check3);
       checkBox4 = (CheckBox)v.findViewById(R.id.Inspc_check4);
        final ImageView adjuntar = (ImageView)v.findViewById(R.id.PO_fotoAdj);
        estadoFoto = (TextView)v.findViewById(R.id.Inspc_Texview_nombreArchivo);
        final RadioButton si2 = (RadioButton)v.findViewById(R.id.Inspc_radioB_Si2);
        final RadioButton no2 = (RadioButton)v.findViewById(R.id.Inspc_radioB_No2);
         si3 = (RadioButton)v.findViewById(R.id.Inspc_radioB_Si3);
         no3 = (RadioButton)v.findViewById(R.id.Inspc_radioB_No3);
        causas = (EditText)v.findViewById(R.id.Inspc_editText_CausasAcc);
        evitarAci = (EditText)v.findViewById(R.id.Inspc_edit_evitarAc);
        noPorque1.setEnabled(false);

        TextView mensaje = v.findViewById(R.id.textView32);
        String msl = "Si la respuesta es NO, NO LO USE, especifique el daño y reportelo inmediatamente al área de Safety";
        if(usuario.getPaisDescripcion().equals("MEXICO")){
            msl = msl + " (01800 253 0414):";
        }

        mensaje.setText(msl);

        //




            try{if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("SI")){si1.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("NO")){no1.setChecked(true);
                noPorque1.setText(preOrden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE());
                noPorque1.setEnabled(true);
            } }catch (Exception e){}
                try{  if(preOrden.getINSPEC_ACTIVIDADES().equals("Entrada en espacios confinado")){checkBox1.setChecked(true);} }catch (Exception e){}
                try{  if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en caliente")){checkBox2.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en equipos energizados (>50V)")){checkBox3.setChecked(true);} }catch (Exception e){}
                try{
                    if(preOrden.getINSPEC_ACTIVIDADES().equals("N/A") && preOrden.getINSPEC_ACTIVIDADES() != null){checkBox4.setChecked(true);
                adjuntar.setClickable(false);}
                }catch (Exception e){ checkBox4.setChecked(false); adjuntar.setClickable(false);}
                try{ if(preOrden.getINSPEC_TRABAJOS_ALTURA().equals("SI")){si2.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_TRABAJOS_ALTURA().equals("NO")){no2.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_CONDIC_INSEGURAS().equals("SI")){si3.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_CONDIC_INSEGURAS().equals("NO")){no3.setChecked(true);} }catch (Exception e){}
                try{ if(preOrden.getINSPEC_PERMISO_ARCHIVO().length()>10){
                    estadoFoto.setTextColor(Color.parseColor("#52D211"));} }catch (Exception e){}
                imagenString = preOrden.getINSPEC_PERMISO_ARCHIVO();


            //nombreArchivo.setText(preOrden.getINSPEC_PERMISO_ARCHIVO());
            causas.setText(preOrden.getINSPEC_CAUSAS_ACCIDENTES());
            evitarAci.setText(preOrden.getINSPEC_MEDIDAS_CORRECTIVAS());

            //region evenros
        adjuntar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox4.isChecked()) {
                    inicioF();
                }
            }
        });

        si1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noPorque1.setText("");
                noPorque1.setEnabled(false);
            }
        });
        no1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noPorque1.setText("");
                noPorque1.setEnabled(true);
            }
        });

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    adjuntar.setClickable(true);
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBox1.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox4.setChecked(false);
                    adjuntar.setClickable(true);
                }
            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBox2.setChecked(false);
                    checkBox1.setChecked(false);
                    checkBox4.setChecked(false);
                    adjuntar.setClickable(true);
                }
            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    checkBox2.setChecked(false);
                    checkBox3.setChecked(false);
                    checkBox1.setChecked(false);
                    adjuntar.setClickable(false);
                    estadoFoto.setTextColor(Color.parseColor("#000000"));
                    //imagenString = null;
                }
            }
        });
        si3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                causas.setEnabled(true);
                evitarAci.setEnabled(true);
            }
        });
        no3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                causas.setText("");
                causas.setEnabled(false);
                evitarAci.setText("");
                evitarAci.setEnabled(false);
            }
        });
            //endregion

        //region Botones
        Button cancelar = (Button)v.findViewById(R.id.Inspec_btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", preOrden.getFOLIO());

                CancelasDialogFragment dialog= new CancelasDialogFragment();
                dialog.setCancelable(true);
                dialog.setTargetFragment(POInspeccionFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.Inspec_btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                if(validaciones()) {
                    if (si1.isChecked()) {
                        preOrden.setINSPEC_CONDIC_OPTIMAS("SI");
                    }
                    if (no1.isChecked()) {
                        preOrden.setINSPEC_CONDIC_OPTIMAS("NO");
                        preOrden.setINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE(noPorque1.getText().toString());
                    }
                    if (checkBox1.isChecked()) {
                        preOrden.setINSPEC_ACTIVIDADES("Entrada en espacios confinado");
                    }
                    if (checkBox2.isChecked()) {
                        preOrden.setINSPEC_ACTIVIDADES("Trabajos en caliente");
                    }
                    if (checkBox3.isChecked()) {
                        preOrden.setINSPEC_ACTIVIDADES("Trabajos en equipos energizados (>50V)");
                    }
                    if (checkBox4.isChecked()) {
                        preOrden.setINSPEC_ACTIVIDADES("N/A");
                        preOrden.setINSPEC_PERMISO_ARCHIVO("");
                        imagenString = null;
                        //adjuntar.setClickable(false);
                    }
                    if (si2.isChecked()) {preOrden.setINSPEC_TRABAJOS_ALTURA("SI");}
                    if (no2.isChecked()) {
                        preOrden.setINSPEC_TRABAJOS_ALTURA("NO");
                    }
                    if (si3.isChecked()) {
                        preOrden.setINSPEC_CONDIC_INSEGURAS("SI");
                    }
                    if (no3.isChecked()) {
                        preOrden.setINSPEC_CONDIC_INSEGURAS("NO");
                    }
                    preOrden.setINSPEC_CAUSAS_ACCIDENTES(causas.getText().toString());
                    preOrden.setINSPEC_MEDIDAS_CORRECTIVAS(evitarAci.getText().toString());
                    preOrden.setINSPEC_PERMISO_ARCHIVO(imagenString);

                    D_B.guardarPreOrden(preOrden);
                    Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                    Intent siguiente = new Intent(getActivity(), MainActivity.class);
                    siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                    siguiente.putExtra("valorPaso", preOrden.getFOLIO());
                    startActivity(siguiente);
                }

            }
        });
        //endregion

        checkExternalStoragePermission();

        return  v;
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

                    imagenString = savefile.SaveImage(getContext(), imageBitmap);
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






    public  Boolean validaciones(){
        Boolean paso = true;
        if(no1.isChecked() && noPorque1.getText().toString().trim().length()==0){
            alerta("Falta especifica condiciones óptimas");
        paso = false;
        }
        if(!checkBox4.isChecked() && imagenString.isEmpty()){alerta("Falta adjuntar permiso.");
            paso = false;}
        if(si3.isChecked() && causas.getText().toString().trim().length()==0){alerta("Falta especificar causas de condiciones inseguras.");
            paso = false;}
        if(si3.isChecked() && evitarAci.getText().toString().trim().length()==0){alerta("Falta especificar Medidas Correctivas.");
            paso = false;}
        return paso;
    }

    public void alerta(String mensaje){
        Bundle valores = new Bundle();
        valores.putString("titulo","Información Faltante");
        valores.putString("mensaje",mensaje);

        AlertaGenerica alerta = new AlertaGenerica();
        alerta.setArguments(valores);
        alerta.setCancelable(false);
        alerta.show(getFragmentManager(), "a");

    }
}
