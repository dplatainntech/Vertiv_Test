package com.maju.desarrollo.testfcs.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.ServiceClass.FoliosReporte;

import java.util.ArrayList;
import java.util.List;


public class adapterBusquedaFolioG  extends BaseAdapter {
    List<FoliosReporte>  lista = new ArrayList<>();
    Context mContex;
    String formatoid;

    public adapterBusquedaFolioG(List<FoliosReporte> lista, Context mContex){
        this.lista = lista;
        this.mContex = mContex;
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
        vista=inflate.inflate(R.layout.fragment_adapter_busqueda_folio_g,null);

        TextView folioPT =(TextView) vista.findViewById(R.id.folioPT);
        TextView formato=(TextView) vista.findViewById(R.id.formato);
        TextView status =(TextView) vista.findViewById(R.id.status);
        TextView cliente=(TextView) vista.findViewById(R.id.cliente);
        TextView SR=(TextView) vista.findViewById(R.id.Gral_SR);
        TextView task=(TextView) vista.findViewById(R.id.Gral_task);
        TextView proyecto=(TextView) vista.findViewById(R.id.item);
        TextView sitio=(TextView) vista.findViewById(R.id.sitio);
        TextView ingeniero=(TextView) vista.findViewById(R.id.ingeniero);
        String Nombre_Formato="";

       /* switch (lista.get(position).getFormato())
        {
            case "1":
                Nombre_Formato="Pre-Trabajo";
                break;
            case "6":
                Nombre_Formato="Enc. Calidad";
                break;
            case "4":
                Nombre_Formato="Baterias";
                break;
            case "5":
                Nombre_Formato="DCPower";
                break;
            case "7":
                Nombre_Formato="Garantias";
                break;
            case "2":
                Nombre_Formato="Bestel N1";
                break;
            case "3":
                Nombre_Formato="Bestel N2";
                break;
            case "9":
                Nombre_Formato="PDU";
                break;
            case "8":
                Nombre_Formato="Gral. Servicios";
                break;
            case "10":
                Nombre_Formato="STS2";
                break;
            case "11":
                Nombre_Formato="Thermal";
                break;
            case "12":
                Nombre_Formato="UPS";
                break;

        }*/
        folioPT.setText(lista.get(position).getFolioPretrabajo());
        formato.setText(lista.get(position).getFormato());
        status.setText(lista.get(position).getTerminado());
        cliente.setText(lista.get(position).getCliente());
        SR.setText(lista.get(position).getSR());
        proyecto.setText(lista.get(position).getProyecto());
        try{if(lista.get(position).getTask().length()>1){
            proyecto.setText("");
        }}catch (Exception e){ proyecto.setText("");}
        task.setText(lista.get(position).getTask());
        sitio.setText(lista.get(position).getSitio());

        ingeniero.setText(lista.get(position).getIngeniero());

        return vista;
    }
}
