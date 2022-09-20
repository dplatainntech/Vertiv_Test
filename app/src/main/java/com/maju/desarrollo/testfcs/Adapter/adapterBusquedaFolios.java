package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;

import java.util.List;

public class adapterBusquedaFolios  extends BaseAdapter {
List<DatGeneralesF>  lista;
Context mContex;
String formatoid;



    public adapterBusquedaFolios(List<DatGeneralesF> lista, Context mContex, String formatoid){
        this.lista = lista;
        this.mContex = mContex;
        this.formatoid = formatoid;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
            return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(mContex);
        vista=inflate.inflate(R.layout.adaptervistafolios,null);

        TextView folioPT =(TextView) vista.findViewById(R.id.folioPT);
        TextView formato=(TextView) vista.findViewById(R.id.formato);
        TextView fecha =(TextView) vista.findViewById(R.id.fecha);
        TextView cliente=(TextView) vista.findViewById(R.id.cliente);
        TextView SR=(TextView) vista.findViewById(R.id.Gral_SR);
        TextView task=(TextView) vista.findViewById(R.id.Gral_task);
        TextView item=(TextView) vista.findViewById(R.id.item);
        TextView serie=(TextView) vista.findViewById(R.id.serie);
        String Nombre_Formato="";
/*
                0.-PT
                1.-Calidad
                2.-Baterias
                3.-DCPower
                4.-Garantias
                5.-IN1
                6.-IN2
                7.-PDU
                8.-Servicios
                9.-STS2
                10.-Thermal
                11.-UPS
                 */
        switch (lista.get(position).getID_TIPO_FORMATO())
        {
            case "0":
                Nombre_Formato="Pre-Trabajo";
                break;
            case "1":
                Nombre_Formato="Calidad";
                break;
            case "2":
                Nombre_Formato="Baterías";
                break;
            case "3":
                Nombre_Formato="DCPower";
                break;
            case "4":
                Nombre_Formato="Garantias";
                break;
            case "5":
                Nombre_Formato="Bestel N1";
                break;
            case "6":
                Nombre_Formato="Bestel N2";
                break;
            case "7":
                Nombre_Formato="PDU";
                break;
            case "8":
                Nombre_Formato="Gral. Servicios";
                break;
            case "9":
                Nombre_Formato="STS2";
                break;
            case "10":
                Nombre_Formato="Thermal";
                break;
            case "11":
                Nombre_Formato="UPS";
                break;

        }
        try{folioPT.setText(lista.get(position).getFOLIO_PRETRABAJO());}catch (Exception e){}
        try{formato.setText(Nombre_Formato);}catch (Exception e){}
        try{fecha.setText(lista.get(position).getFECHA_INICIO());}catch (Exception e){
            fecha.setText("S/D");
        }
        try{cliente.setText(lista.get(position).getCLIENTE());}catch (Exception e){}
        try{SR.setText(lista.get(position).getSR());}catch (Exception e){}
        try{task.setText(lista.get(position).getTASK());}catch (Exception e){}
        try{item.setText(lista.get(position).getITEM());}catch (Exception e){}
        try{serie.setText(lista.get(position).getSERIE());}catch (Exception e){}

        return vista;
    }

    public String getIdFormafo(int position){
        return lista.get(position).getID_FORMATO();
    }

    public String getTipoFormato(int position){
        return lista.get(position).getID_TIPO_FORMATO();
    }
}
