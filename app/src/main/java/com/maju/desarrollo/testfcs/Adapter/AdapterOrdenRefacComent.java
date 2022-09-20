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
import com.maju.desarrollo.testfcs.SQLite.Model.RefaccionesLinea;

import java.util.List;

public class AdapterOrdenRefacLine extends BaseAdapter {
    private List<RefaccionesLinea> listaOrden = null;
    private Context mContex;
    TextView deescricpion, cantidad, unidad;
    ImageView img;

    public AdapterOrdenRefacLine(List<RefaccionesLinea> listaOrden, Context mContex){
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
        return 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(mContex);
        vista = inflate.inflate(R.layout.item_orden_refaccion_lineas,null);

        deescricpion = (TextView) vista.findViewById(R.id.RefaccionDescripcion);
        cantidad = (TextView) vista.findViewById(R.id.RefacionCantidad);
        unidad = (TextView) vista.findViewById(R.id.RefaUnidadMedida);

        deescricpion.setText(listaOrden.get(position).getDESCRIPCION());
        cantidad.setText(listaOrden.get(position).getCANTIDAD());
        unidad.setText(listaOrden.get(position).getUNIDADMEDIDA());

        return vista;
    }

    public String getIdHeader(int position){
        return listaOrden.get(position).getIDLINE();
    }

}