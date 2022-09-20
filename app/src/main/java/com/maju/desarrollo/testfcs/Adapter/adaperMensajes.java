package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.maju.desarrollo.testfcs.Class.MensajesCls;
import com.maju.desarrollo.testfcs.R;

import java.util.ArrayList;
import java.util.List;

public class adaperMensajes extends BaseAdapter {
    private List<MensajesCls> listaEmsajes = new ArrayList<MensajesCls>();
    private Context mContex;
    TextView mensajeTexto;

    public adaperMensajes(List<MensajesCls> listaEmsajes, Context mContex) {
        this.listaEmsajes = listaEmsajes;
        this.mContex = mContex;
    }

    @Override
    public int getCount() {
        return listaEmsajes.size();
    }

    @Override
    public Object getItem(int position) {
        return listaEmsajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Integer.parseInt(listaEmsajes.get(position).getIdMensaje());
    }

    public ArrayList<String> getmensaje(int position){
        ArrayList<String> mD = new ArrayList<>();
        if(listaEmsajes.get(position).getMensaje().contains(".jpg|VRT")) {
           String [] m = listaEmsajes.get(position).getMensaje().replace("|","&").split("&");
            mD.add(m[1]);
            mD.add(m[2]);

        }else{
            mD.add("NO");
        }
        return mD;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(mContex);
        vista=inflate.inflate(R.layout.item_mensajes,null);

        mensajeTexto = (TextView) vista.findViewById(R.id.mensajesEtiqueta);
        mensajeTexto.setText(listaEmsajes.get(position).getMensaje());

        return vista;
    }
}
