package com.maju.desarrollo.testfcs;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 */
public class MenuFragment extends Fragment {

    LinearLayout preOrden, mantenimiento, encuesta;
    ImageButton inicio, menu;

    public MenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_menu, container, false);

        preOrden = (LinearLayout) v.findViewById(R.id.LLpreordenclick);
        mantenimiento = (LinearLayout) v.findViewById(R.id.LLservicioManclick);
        encuesta = (LinearLayout) v.findViewById(R.id.LLencuestaCclick);
        menu = (ImageButton) v.findViewById(R.id.Menuhamburger);
        inicio = (ImageButton) v.findViewById(R.id.butonHome);

        preOrden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mantenimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TipoFragment containerFragment = new TipoFragment();

             /* Bundle args = new Bundle();
              args.putString("key_numPedido", Integer.toString(v));
              containerFragment.setArguments(args);
             */
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();

            }
        });
        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OrdenListFragment containerFragment = new OrdenListFragment();
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();

            }
        });



        return v;
    }

}
