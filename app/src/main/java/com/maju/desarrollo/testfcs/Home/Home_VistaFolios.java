package com.maju.desarrollo.testfcs.Home;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Adapter.RViewAdapter;
import com.maju.desarrollo.testfcs.Adapter.RecyclerItemTouchHelper;
import com.maju.desarrollo.testfcs.Adapter.adapterBusquedaFolios;
import com.maju.desarrollo.testfcs.Formatos.Baterias.BateriasMenuFragment;
import com.maju.desarrollo.testfcs.Formatos.Calidad.CalidadMenu;
import com.maju.desarrollo.testfcs.Formatos.DCPower.MenuPowerFragment;
import com.maju.desarrollo.testfcs.Formatos.Garantias.GarantiasMenu;
import com.maju.desarrollo.testfcs.Formatos.IN1.IN1Menu;
import com.maju.desarrollo.testfcs.Formatos.IN2.IN2Menu;
import com.maju.desarrollo.testfcs.Formatos.PDU.PDUMenu;
import com.maju.desarrollo.testfcs.Formatos.PreOrden.MenuPOFragment;
import com.maju.desarrollo.testfcs.Formatos.STS2.STS2MenuFragment;
import com.maju.desarrollo.testfcs.Formatos.Servicios.ServiciosMenu;
import com.maju.desarrollo.testfcs.Formatos.Thermal.ThermMenuFragment;
import com.maju.desarrollo.testfcs.Formatos.UPS.UpsMenuFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_VistaFolios extends Fragment implements
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener, RViewAdapter.onSkuListener {

    String estatus,idFormato;
    ListView listaDatos;
    List<DatGeneralesF> lista;
    OperacionesDB D_B;
    private adapterBusquedaFolios miAdaptador;

    private RecyclerView recyclerView;
    private RViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    public Home_VistaFolios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home__vista_folios, container, false);
        estatus= getArguments().getString("estatuslista");
        idFormato= getArguments().getString("id_formato");

        D_B = OperacionesDB.obtenerInstancia(getContext());
        lista = D_B.formatosEnProcesoLista(estatus,idFormato);  //estatus,idformato

        TextView terminadosTxt = (TextView)v.findViewById(R.id.etiquetaDocTerminados);
        TextView procesoTxt = (TextView)v.findViewById(R.id.etiquetaDocProceso);

        //Recycler view
        this.recyclerView = v.findViewById(R.id.recycler_view);
        this.layoutManager = new LinearLayoutManager(getContext());
        this.adapter = new RViewAdapter(lista, getActivity().getApplicationContext(),idFormato, Home_VistaFolios.this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        ItemTouchHelper.SimpleCallback simpleCallback =
                new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, Home_VistaFolios.this);

        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);

        //listaDatos = (ListView)  v.findViewById(R.id.lista1);
        //miAdaptador= new adapterBusquedaFolios(lista,getActivity().getApplicationContext(),idFormato);
        //listaDatos.setAdapter(miAdaptador);

        if(estatus.equals("1")){terminadosTxt.setVisibility(View.GONE);}else{
            procesoTxt.setVisibility(View.GONE);
        }




        /*
        listaDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //region COntenido
                String ifFormato =  miAdaptador.getIdFormafo(position);
                String tipoFormato = miAdaptador.getTipoFormato(position);

                if(tipoFormato.equals("0")) {
                    MenuPOFragment menu = new MenuPOFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idPreOrden", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("1")) {
                    CalidadMenu menu = new CalidadMenu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("2")) {
                    BateriasMenuFragment menu = new BateriasMenuFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("3")) {
                    MenuPowerFragment menu = new MenuPowerFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("4")) {
                    GarantiasMenu menu = new GarantiasMenu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("5")) {
                    IN1Menu menu = new IN1Menu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("6")) {
                    IN2Menu menu = new IN2Menu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("7")) {
                    PDUMenu menu = new PDUMenu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("8")) {
                    ServiciosMenu menu = new ServiciosMenu();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("9")) {
                    STS2MenuFragment menu = new STS2MenuFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("10")) {
                    ThermMenuFragment menu = new ThermMenuFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }
                if(tipoFormato.equals("11")) {
                    UpsMenuFragment menu = new UpsMenuFragment();
                    Bundle args = new Bundle();
                    args.putString("key_idFormato", ifFormato);
                    menu.setArguments(args);

                    FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
                }

                //endregion

            }
        });
*/
        return v;
    }

    @Override
    public void onSwipe(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if(viewHolder instanceof RViewAdapter.DataObjectHolder){

            //String nombre = getListaCoche().get(viewHolder.getAdapterPosition()).getNombre();
            //final Coche cocheBorrado = getListaCoche().get(viewHolder.getAdapterPosition());
            //final int deletedIntex = viewHolder.getAdapterPosition();
            String ifFormato = adapter.getIdFormafo(position);
            String tipoFormato = adapter.getTipoFormato(position);

            D_B.borrarFormato(ifFormato,tipoFormato);

            adapter.removeItem(viewHolder.getAdapterPosition());

            //recuperarCocheBorrado(viewHolder,nombre,cocheBorrado,deletedIntex);

        }
    }

    @Override
    public void onSkuClick(int position) {

        //String ifFormato =  miAdaptador.getIdFormafo(position);
        //String tipoFormato = miAdaptador.getTipoFormato(position);
        String ifFormato = adapter.getIdFormafo(position);
        String tipoFormato = adapter.getTipoFormato(position);

        if(tipoFormato.equals("0")) {
            MenuPOFragment menu = new MenuPOFragment();
            Bundle args = new Bundle();
            args.putString("key_idPreOrden", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("1")) {
            CalidadMenu menu = new CalidadMenu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("2")) {
            BateriasMenuFragment menu = new BateriasMenuFragment();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("3")) {
            MenuPowerFragment menu = new MenuPowerFragment();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("4")) {
            GarantiasMenu menu = new GarantiasMenu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("5")) {
            IN1Menu menu = new IN1Menu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("6")) {
            IN2Menu menu = new IN2Menu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("7")) {
            PDUMenu menu = new PDUMenu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("8")) {
            ServiciosMenu menu = new ServiciosMenu();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("9")) {
            STS2MenuFragment menu = new STS2MenuFragment();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("10")) {
            ThermMenuFragment menu = new ThermMenuFragment();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }
        if(tipoFormato.equals("11")) {
            UpsMenuFragment menu = new UpsMenuFragment();
            Bundle args = new Bundle();
            args.putString("key_idFormato", ifFormato);
            menu.setArguments(args);

            FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, menu).addToBackStack(null).commit();
        }

    }
}
