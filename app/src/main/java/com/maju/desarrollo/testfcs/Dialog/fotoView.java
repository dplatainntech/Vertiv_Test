package com.maju.desarrollo.testfcs.Dialog;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.maju.desarrollo.testfcs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class fotoView extends DialogFragment {

    String stringFoto;

    public fotoView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_foto_view, container, false);

        stringFoto= getArguments().getString("Foto");

        Button continuar = (Button)v.findViewById(R.id.buttonOK);
        ImageView contenedorimg = (ImageView)v.findViewById(R.id.imageViewFoto);

        //contenedorimg.setImageBitmap(bitmap);

        byte[]  imageBytes = Base64.decode(stringFoto, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        contenedorimg.setImageBitmap(decodedImage);

        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        return v;
    }

}
