package com.maju.desarrollo.testfcs.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.searchSitio;

import java.util.ArrayList;
import java.util.List;

public class adapterSitio extends BaseAdapter implements Filterable {

    Context contexto;
    List<searchSitio> listaObjetos,listaFiltrada;
    adapterSitio.CustomFilter cs;

    public adapterSitio(Context contexto, List<searchSitio> listaObjetos) {
        this.contexto = contexto;
        this.listaObjetos = listaObjetos;
        this.listaFiltrada = listaObjetos;
    }


    public searchSitio obtenerItem(int position){
        return listaObjetos.get(position);
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
        vista=inflate.inflate(R.layout.fragment_adapter_cliente,null);


        TextView sitio=(TextView) vista.findViewById(R.id.clientesitio);
        TextView nomCliente = (TextView) vista.findViewById(R.id.clientenombre);


        nomCliente.setText(listaObjetos.get(position).getSitio());
        //sitio.setText(listaObjetos.get(position).getNombreSitio());
        return vista;
    }

    @Override
    public Filter getFilter() {
        if(cs==null){
            cs= new adapterSitio.CustomFilter();
        }
        return cs;
    }

    class CustomFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();

            if(constraint!=null && constraint.length()>0){
                constraint= constraint.toString().toUpperCase();
                ArrayList<searchSitio> filtrados = new ArrayList<>();

                for(int i=0;i<listaFiltrada.size();i++){
                    if(listaFiltrada.get(i).getSitio().toUpperCase().contains(constraint)){
                        searchSitio item= new searchSitio();
                        item = listaFiltrada.get(i);
                        filtrados.add(item);
                    }
                }
                results.count=filtrados.size();
                results.values=filtrados;
            }
            else{
                results.count=listaObjetos.size();
                results.values=listaObjetos;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaObjetos= (ArrayList<searchSitio>)results.values;
            notifyDataSetChanged();
        }
    }

}

