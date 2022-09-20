package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;

import java.util.List;

public class AdapterOrden extends BaseAdapter {
    private List<CatAsignaCliente> listaOrden = null;
    private Context mContex;
    TextView Cliente ,direccion,fecha,sr,task,item,serie;
    ImageView imagen;

    public AdapterOrden(List<CatAsignaCliente> listaOrden, Context mContex){
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
        return Integer.parseInt(listaOrden.get(position).getSr_Nbr());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(mContex);
        vista=inflate.inflate(R.layout.item_orden,null);
        String Mensaje="";

        sr = (TextView) vista.findViewById(R.id.sr);
        task = (TextView) vista.findViewById(R.id.task);
        Cliente = (TextView) vista.findViewById(R.id.adaptercliente);
        direccion =(TextView) vista.findViewById(R.id.adapterDireccion);
        fecha=(TextView) vista.findViewById(R.id.adapterFecha);
        item =(TextView) vista.findViewById(R.id.item);
        serie=(TextView) vista.findViewById(R.id.serie);
        //hora=(TextView) vista.findViewById(R.id.adapterHora);
        //imagen=(ImageView) vista.findViewById(R.id.imageViewOrderAdapter);

        sr.setText(listaOrden.get(position).getSr_Nbr());
        task.setText(listaOrden.get(position).getTask_Nbr());
        Cliente.setText(listaOrden.get(position).getInstall_Party_Name());
        direccion.setText(listaOrden.get(position).getAdress());
        fecha.setText(listaOrden.get(position).getTask_Start_Planned());
        item.setText(listaOrden.get(position).getItem_Number());
        serie.setText(listaOrden.get(position).getSerial_Number());
        //hora.setText(listaOrden.get(position).getHORA());
        //imagen.setImageResource(R.drawable.calendario);

        return vista;
    }

    public String getIdSR(int position){
        return listaOrden.get(position).getSr_Nbr();
    }

}