package com.maju.desarrollo.testfcs.Formatos.Refaccion;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.maju.desarrollo.testfcs.Adapter.AdapterOrden;
import com.maju.desarrollo.testfcs.Adapter.AdapterOrdenRefac;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Class.RefaccionCls;
import com.maju.desarrollo.testfcs.Dialog.opcionOrden;
import com.maju.desarrollo.testfcs.Home.Home_VistaFolios;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.MenuFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.RefaccionesHeader;
import com.maju.desarrollo.testfcs.SQLite.Model.RefaccionesLinea;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.PreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class RefaMenu extends Fragment {

    OperacionesDB D_B;
    List<RefaccionesHeader> Lista, server;
    private AdapterOrdenRefac miAdaptador;
    ListView listaDatos;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    IMyAPI iMyAPI;
    UsuarioD usuario;
    String filtro = "";

    public RefaMenu() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_refacciones, container, false);
        ((MainActivity) getActivity()).verCabecera();

        D_B = OperacionesDB.obtenerInstancia(getContext());
        usuario = D_B.obtenerUsuario();

        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);

        listaDatos = (ListView)  v.findViewById(R.id.lista1);

        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String idhead =  miAdaptador.getIdHeader(position);
                String estat = miAdaptador.getEstatus(position);
                Bundle valores = new Bundle();
                valores.putString("key_idFormato", idhead);
                valores.putString("Estatus", estat);
                valores.putString("id_SR", "");

                RefaccionesHeader idReFac = D_B.obtenerREFACCION(idhead);
                String idV =idReFac.getIDHEADER();
                if(idV != null){
                    RefaNueva myfargment = new RefaNueva();
                    myfargment.setArguments(valores);
                    FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                } else {
                    RefaNuevaLista myfargment = new RefaNuevaLista();
                    myfargment.setArguments(valores);
                    FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                }
            }
        });

        Spinner porStatus = (Spinner)v.findViewById(R.id.FiltroStatus);
        //Button porFolio = (Button)v.findViewById(R.id.btn_PorFolio);

        ArrayAdapter<CharSequence> adapterSpinner = ArrayAdapter.createFromResource(getActivity(),
                R.array.porEstatus, android.R.layout.simple_spinner_item);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        porStatus.setAdapter(adapterSpinner);


        /*porFolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(filtro.equals("") || filtro.equals("D")){
                    porFolio.setBackgroundResource(R.drawable.folioascrefa);
                    filtro = "A";
                }else {
                    porFolio.setBackgroundResource(R.drawable.foliodescrefa);
                    filtro = "D";
                }

            }
        });*/

       porStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               if(porStatus.getSelectedItem().equals("Borrador")){
                   Lista = D_B.obtenerListaREFACCIONES();
                   listaDatos.setTextFilterEnabled(true);
                   miAdaptador = new AdapterOrdenRefac(Lista,getActivity().getApplicationContext());

                   listaDatos.setAdapter(miAdaptador);
               }else {

                   RefaccionesHeader envio = new RefaccionesHeader();
                   envio.setIDSOLICITANTE(usuario.getID_USER());
                   envio.setESTATUS(porStatus.getSelectedItem().toString());
                   compositeDisposable.add(iMyAPI.ListaRefa(envio).subscribeOn(Schedulers.io())
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Consumer<List<RefaccionesHeader>>() {
                               @Override
                               public void accept(List<RefaccionesHeader> ListaR) throws Exception {
                                   if(ListaR.size() > 0){

                                       listaDatos.setTextFilterEnabled(true);
                                       miAdaptador = new AdapterOrdenRefac(ListaR,getActivity().getApplicationContext());

                                       listaDatos.setAdapter(miAdaptador);

                                   }else {
                                       listaDatos.setAdapter(null);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {

                               }
                           })
                   );
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });




        return v;
    }


}
