package com.maju.desarrollo.testfcs.Home;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Adapter.adapterFoliosPT;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Paquete extends Fragment {
    OperacionesDB D_B;
    String id_Formato;
    ListView listafiltros1;
    IMyAPI iMyAPI;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public Home_Paquete() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_home__paquete, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);

        final EditText filtroT1 = (EditText)v.findViewById(R.id.filtro1);
        final EditText cliente = (EditText)v.findViewById(R.id.clientPQT);
        listafiltros1 = (ListView) v.findViewById(R.id.listaAuto1);


        Bundle valores = new Bundle();
        valores.putString("titulo","");
        valores.putString("mensaje","Antes de generar el paquete de formatos a enviar, recuerda colocar el folio Pre-Trabajo en cada uno de ellos.");

        AlertaGenerica alerta = new AlertaGenerica();
            alerta.setArguments(valores);
            alerta.setCancelable(false);
            alerta.show(getFragmentManager(), "a");



        //region FILTROS FOLIOS PT
        final List<CatFolios> ListaTask_SR = D_B.obtenerFoliosPT();
        listafiltros1.setTextFilterEnabled(true);
        final adapterFoliosPT miAdaptador1= new adapterFoliosPT(getActivity().getApplicationContext(),ListaTask_SR);

        listafiltros1.setAdapter(miAdaptador1);
        listafiltros1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CatFolios FolioPT = miAdaptador1.obtenerItem(position);
                Bundle args = new Bundle();
                args.putString("FolioPT",FolioPT.getFolioPreTrabajo());
                Home_GenerarPaquete myfargment = new Home_GenerarPaquete();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        filtroT1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //PedidosFragment.this.miAdaptador.getView();
                miAdaptador1.getFilter().filter(s);
                //Toast.makeText(getActivity().getApplicationContext(), String.valueOf(s), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if(s.length()>0)
                    clearBox.setVisibility(View.VISIBLE);
                else
                    clearBox.setVisibility(View.GONE); */
            }
        });

        //endregion



        Button buscar = (Button)v.findViewById(R.id.buttonGENERAR);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!filtroT1.getText().toString().trim().isEmpty() && !cliente.getText().toString().trim().isEmpty()) {
                    final progresoLoad alerta = new progresoLoad();
                    alerta.setCancelable(true);
                    alerta.show(getFragmentManager(), "a");

                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            alerta.dismiss();
                            //finish();
                        }
                    }, 4000);
                }else{
                    Toast.makeText(getContext(), "Coloca los datos necesarios.", Toast.LENGTH_LONG).show();
                }
            }
        });

        return v;
    }

}
