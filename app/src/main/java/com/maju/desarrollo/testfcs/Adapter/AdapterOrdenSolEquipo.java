package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.RefaccionesHeader;

import java.util.List;

public class AdapterOrdenSolEquipo extends BaseAdapter {
    private List<RefaccionesHeader> listaOrden = null;
    private Context mContex;
    TextView Folio, Estatus, dias, Cliente, Comentario;
    ImageView img;

    public AdapterOrdenSolEquipo(List<RefaccionesHeader> listaOrden, Context mContex){
        this.listaOrden = listaOrden;
        this.mContex = mContex;
    }

    @Override
    public int getCount() {
        return listaOrden.size();
    }

    @Override
    public Object getItem(int position) {
        return listaOrden.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaOrden.get(position).getID();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(mContex);
        vista = inflate.inflate(R.layout.item_orden_refaccion,null);

        Folio = (TextView) vista.findViewById(R.id.RefFolio);
        Estatus = (TextView) vista.findViewById(R.id.ReEstatus);
        dias = (TextView) vista.findViewById(R.id.ReDias);
        Cliente = (TextView)vista.findViewById(R.id.RefCliente);
        Comentario = (TextView)vista.findViewById(R.id.ComentarioRefa);
        img = (ImageView) vista.findViewById(R.id.imgRefa);

        Folio.setText(listaOrden.get(position).getFOLIO());
        Estatus.setText(listaOrden.get(position).getESTATUS());
        dias.setText(listaOrden.get(position).getDIAS());
        Cliente.setText(listaOrden.get(position).getCLIENTE());
        Comentario.setText(listaOrden.get(position).getCOMENTARIO());

        int diasi = 0;
        try{diasi = Integer.parseInt(listaOrden.get(position).getDIAS());}catch(Exception ex){};
        if(diasi >= 3 && diasi <= 4 ){
            img.setImageResource(R.drawable.amarillo);
        }else if (diasi > 4){
            img.setImageResource(R.drawable.rojo);
        }

        return vista;
    }

    public String getIdHeader(int position){
        return listaOrden.get(position).getIDHEADER();
    }
    public String getEstatus(int position){
        return listaOrden.get(position).getESTATUS();
    }

}