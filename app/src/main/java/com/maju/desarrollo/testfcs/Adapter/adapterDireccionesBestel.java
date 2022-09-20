package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Class.spinnerDosCampos;
import com.maju.desarrollo.testfcs.R;

import java.util.List;

public class adapterDireccionesBestel extends BaseAdapter{

    Context contexto;


    List<spinnerDosCampos> listaObjetos;
    adapterSitio.CustomFilter cs;

    public adapterDireccionesBestel(Context contexto, List<spinnerDosCampos> listaObjetos) {
        this.contexto = contexto;
        this.listaObjetos = listaObjetos;
    }

    @Override
    public int getCount() {
        return listaObjetos.size();
    }



    @Override
    public Object getItem(int position) {
        return listaObjetos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //return null;
        View vista= convertView;
        LayoutInflater inflate = LayoutInflater.from(contexto);
        vista=inflate.inflate(R.layout.fragment_adapter_genericospiner,null);

        TextView nomCliente = (TextView) vista.findViewById(R.id.clientenombre);

        nomCliente.setText(listaObjetos.get(position).getCampo2());
        //sitio.setText(listaObjetos.get(position).getNombreSitio());
        return vista;
    }


}
