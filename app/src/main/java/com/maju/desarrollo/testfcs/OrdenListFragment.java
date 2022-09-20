package com.maju.desarrollo.testfcs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.maju.desarrollo.testfcs.Adapter.AdapterOrden;
import com.maju.desarrollo.testfcs.Dialog.opcionOrden;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class OrdenListFragment extends Fragment implements View.OnClickListener,View.OnLongClickListener, SwipeRefreshLayout.OnRefreshListener{

    ListView listaDatos;
    List<CatAsignaCliente> Lista;
    OperacionesDB D_B;
    private AdapterOrden miAdaptador;

    public OrdenListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_orden_list, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());

        listaDatos = (ListView)  v.findViewById(R.id.lista1);
        Lista = D_B.obtenerTask();

        listaDatos.setTextFilterEnabled(true);
        miAdaptador= new AdapterOrden(Lista,getActivity().getApplicationContext());

        listaDatos.setAdapter(miAdaptador);
        //region click sostenido

        /*
        listaDatos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(miAdaptador.getItemId(position)), Toast.LENGTH_SHORT).show();
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getContext());
                dialogo1.setTitle("Importante");
                dialogo1.setMessage("¿Que acción se desea hacer con el pedido?");
                dialogo1.setCancelable(true);
                dialogo1.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        Lista.get(position).getId();
                        if (Lista.get(position).getStatus().equals("PENDIENTE")) {
                            Cursor pedidoDelete = operacionesBD.ontenerPedido(Lista.get(position).getId());
                            if (pedidoDelete.moveToFirst()) {
                                operacionesBD.borrarPedido(pedidoDelete.getString(0), pedidoDelete.getString(9));
                                Lista.remove(position);
                                miAdaptador.notifyDataSetChanged();
                                Lista= operacionesBD.obtenerListaPedidos();
                                if(Lista.size()>=1){
                                    sin_pedido.setVisibility(View.GONE);
                                    cargarlista();
                                }else{
                                    sin_pedido.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                        else{
                            Toast.makeText(getActivity().getApplicationContext(), "El pedido no se puede eliminar. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialogo1.setNegativeButton("Copiar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogo1, int id) {
                        //Lista.add(Lista.get(position));
                        operacionesBD.copiarPedido(Lista.get(position).getId());
                        miAdaptador.notifyDataSetChanged();
                        cargarlista();
                    }
                });

                dialogo1.show();

                return true;
            }
        });
*/
        //endregion
        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MenuFragment containerFragment = new MenuFragment();

                String idSR =  miAdaptador.getIdSR(position);
                Bundle valores = new Bundle();
                valores.putString("idSR",idSR);

                opcionOrden alerta = new opcionOrden();
                alerta.setCancelable(true);
                alerta.setArguments(valores);
                alerta.show(getFragmentManager(),"a");
            }
        });



        return v;
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
