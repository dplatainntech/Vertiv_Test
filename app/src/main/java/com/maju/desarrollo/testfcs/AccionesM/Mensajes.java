package com.maju.desarrollo.testfcs.AccionesM;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Adapter.adaperMensajes;
import com.maju.desarrollo.testfcs.Class.MensajesCls;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Mensajes extends Fragment implements View.OnClickListener,View.OnLongClickListener, SwipeRefreshLayout.OnRefreshListener {
    OperacionesDB D_B;
   List<MensajesCls> listaMensaj = new ArrayList<MensajesCls>();
    ListView listaDatos;
    private adaperMensajes miAdaptador;

    public Mensajes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_mensajes, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        listaDatos = (ListView)  v.findViewById(R.id.listaMensajes);

        listaMensaj = D_B.obtenerListaEMsajes();

        //= operacionesBD.obtenerListaPedidos();

        listaDatos.setTextFilterEnabled(true);
        miAdaptador= new adaperMensajes(listaMensaj,getActivity().getApplicationContext());

        listaDatos.setAdapter(miAdaptador);

        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

               ArrayList<String> Datos = miAdaptador.getmensaje(i);
               if(!Datos.get(0).equals("NO")){

                   FotosFormato foto = D_B.ObtenerFoto(Datos.get(1),Datos.get(0));
                   String men = "No se pudo enviar la imagen |" + Datos.get(0)+"|"+Datos.get(1) + "| \n -->REENVIAR";
                   D_B.borrarmensajeError(men);

                   ((MainActivity) getActivity()).imagenesEnvio(foto);

                   Toast.makeText(getContext(),"Reenviando " + Datos.get(0) + " para Folio: " + Datos.get(1),Toast.LENGTH_LONG).show();
                   listaMensaj.remove(i);
                   miAdaptador.notifyDataSetChanged();

               }
            }
        });


        return v;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public boolean onLongClick(View v) {
        return false;
    }
}
