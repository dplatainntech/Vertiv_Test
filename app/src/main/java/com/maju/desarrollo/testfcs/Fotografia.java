package com.maju.desarrollo.testfcs;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Fotografia extends AppCompatActivity {

    private ImageView img;
    TextView datos_captura;
    private static final int PICK_IMAGE = 100;
    Uri imageUri;
    Bitmap imageBitmap;
    String bmFirma1 = "NO";
    String bmFirma2= "NO";
    ImageButton tomarfoto;
    private LocationManager locManager;
    private Location loc;
    String sLatitud = "0.0";
    String sLongitud = "0.0";
    int widthActivityPhone ;
    int height ;



    private static final int PICTURE_RESULT = 122 ;
    private ContentValues values;
    //private Uri imageUri;
    private Button myButton;
    private ImageView myImageView;
    private Bitmap thumbnail;

    String imageurl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotografia);

        getSupportActionBar().hide();

        img = (ImageView)findViewById(R.id.imageView3);
        tomarfoto=  (ImageButton)findViewById(R.id.imageButton4);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
         widthActivityPhone = metrics.widthPixels; // ancho absoluto en pixels
         height = metrics.heightPixels; // alto absoluto en pixels

        tomarfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                */
                //tomarFoto();


               values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, "MyPicture");
                values.put(MediaStore.Images.Media.DESCRIPTION, "Photo taken on " + System.currentTimeMillis());
                imageUri = getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, PICTURE_RESULT);

            }
        });

        //region Datos

        datos_captura = (TextView)findViewById(R.id.textView6);
       /* if(ContextCompat.checkSelfPermission(this,Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{
                    Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA
            },1000);
        }*/

        //endregion

        if (ContextCompat.checkSelfPermission(Fotografia.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Fotografia.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Fotografia.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1000);
        }

        ImageButton siguiente = (ImageButton)findViewById(R.id.homeActivityP);

        //region Evento Boton
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente2 = new Intent(Fotografia.this, FirmasPet.class);
                String s = "";
                siguiente2.putExtra("KetFoto", imageBitmap);
                siguiente2.putExtra("KetFirma1", "NO");
                siguiente2.putExtra("KetFirma2", "NO");
                startActivity(siguiente2);
                overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });
        //endregion

        ImageButton atras = (ImageButton)findViewById(R.id.imageButtonAtrasF);

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        //region localizacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
        } else {
            locationStart();
        }

        //checkExternalStoragePermission();

        //endregion



    }


  /*  @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");
        img.setImageBitmap(imageBitmap);

    } */


    private void locationStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        //Local.getMainActivity();
        Local.setMainActivity(this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);
        //latitud.setText("Localización agregada");
        //direccion.setText("");
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {

        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                locationStart();
                return;
            }
        }
    }

    /* Aqui empieza la Clase Localizacion */
    public class Localizacion implements LocationListener {
        Fotografia mainActivity;
        public Fotografia getMainActivity() {
            return mainActivity;
        }
        public void setMainActivity(Fotografia mainActivity) {
            this.mainActivity = mainActivity;
        }
        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            loc.getLongitude();
            sLatitud = String.valueOf(loc.getLatitude());
            sLongitud = String.valueOf(loc.getLongitude());
            //latitud.setText(sLatitud);
            //longitud.setText(sLongitud);
            Log.d("Latitud: ", sLatitud);
            Log.d("Longitud: ", sLongitud);
            //this.mainActivity.setLocation(loc);
            //datos_captura.setText(sLatitud + "\n" + sLongitud);
        }
        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //latitud.setText("GPS Desactivado");
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            //latitud.setText("GPS Activado");
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    //Método para crear un nombre único de cada fotografia
    String mCurrentPhotoPath;
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "Backup_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName, ".jpg", storageDir);

        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    //Método para tomar foto y crear el archivo
    static final int REQUEST_TAKE_PHOTO = 1;
    public void tomarFoto() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        /*if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
        }*/

        startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);


    }

    //Método para mostrar vista previa en un imageview de la foto tomada
    static final int REQUEST_IMAGE_CAPTURE = 1;
   /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
             //region guardar foto (no activada)
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,"com.example.android.fileprovider", photoFile);
                data.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
            }
            //endregion

            //Vista previa de foto
            DisplayMetrics metrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int widthActivityPhone = metrics.widthPixels; // ancho absoluto en pixels
            int heightActivityPhone = metrics.heightPixels; // alto absoluto en pixels

            int hitP = (int)(.70 * heightActivityPhone);
            int WitP = (int)(.70 * widthActivityPhone);


            tomarfoto.setVisibility(View.GONE);
            imageBitmap = (Bitmap) data.getExtras().get("data");

            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
            String horaStamp = new SimpleDateFormat("HH:mm").format(new Date());
            String ltd = sLatitud + "  " + sLongitud ;

            String fch = timeStamp + "  " + horaStamp + " Horas";

            Bitmap bitmap= Bitmap.createBitmap(WitP,hitP,Bitmap.Config.ARGB_8888);//(int)(.70 * widthActivityPhone),(int)(.80 * height),Bitmap.Config.ARGB_8888);

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();




            paint.setAntiAlias(true);
            paint.setTextSize(32.f);
            paint.setTextAlign(Paint.Align.LEFT);
            paint.setColor(Color.parseColor("#FD5B0A"));
            canvas.drawText(ltd,0,hitP - (int)(hitP *.15),paint);
            canvas.drawText(fch,0, hitP - (int)(hitP*.10),paint);

            canvas.drawBitmap(imageBitmap,10,10,paint);

            //datos_captura.setText();
            img.setImageBitmap(bitmap);

            //mostrar informacion de Foto (lat, log, fecha, hora)


        }
    }

*/

   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case PICTURE_RESULT:
                if (requestCode == PICTURE_RESULT)
                    if (resultCode == this.RESULT_OK) {
                        try {
                            imageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                            //myImageView.setImageBitmap(thumbnail);
                            tomarfoto.setVisibility(View.GONE);

                            String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                            String horaStamp = new SimpleDateFormat("HH:mm").format(new Date());
                            String ltd = sLatitud + "  " + sLongitud ;

                            String fch = timeStamp + "  " + horaStamp + " Horas";

                            Bitmap bitmap= Bitmap.createBitmap(imageBitmap.getWidth(),imageBitmap.getHeight(),Bitmap.Config.ARGB_8888);//(int)(.70 * widthActivityPhone),(int)(.80 * height),Bitmap.Config.ARGB_8888);

                            Canvas canvas = new Canvas(bitmap);
                            Paint paint = new Paint();


                            canvas.drawBitmap(imageBitmap,10,10,paint);

                            paint.setAntiAlias(true);
                            paint.setTextSize(140.f);
                            paint.setTextAlign(Paint.Align.LEFT);
                            paint.setColor(Color.parseColor("#FD5B0A"));
                            canvas.drawText(ltd,10,imageBitmap.getHeight() - (int)(imageBitmap.getHeight() *.15),paint);
                            canvas.drawText(fch,10, imageBitmap.getHeight() - (int)(imageBitmap.getHeight()*.10),paint);



                            //datos_captura.setText();
                            img.setImageBitmap(bitmap);





                            //img.setImageBitmap(thumbnail);
                            //Obtiene la ruta donde se encuentra guardada la imagen.

                            imageurl = getRealPathFromURI(imageUri);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
        }
    }

    public String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }

    private boolean checkExternalStoragePermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(
                this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            Log.i("Mensaje", "No se tiene permiso para leer.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        } else {
            Log.i("Mensaje", "Se tiene permiso para leer!");
            return true;
        }

        return false;
    }


}
