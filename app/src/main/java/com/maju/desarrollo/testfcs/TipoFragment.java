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
public class TipoFragment extends Fragment {

    LinearLayout termal, ups, aires, servicios;
    ImageButton inicio, menu;
    public TipoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_tipo, container, false);

        termal = (LinearLayout) v.findViewById(R.id.LLtermalclick);
        ups = (LinearLayout) v.findViewById(R.id.LLupsclick);
        aires = (LinearLayout) v.findViewById(R.id.LLairesclick);
        servicios = (LinearLayout) v.findViewById(R.id.LLserviciosclick);
        menu = (ImageButton) v.findViewById(R.id.MenuhamburgerTipo);
        inicio = (ImageButton) v.findViewById(R.id.butonHomeTipo);

        termal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check1Fragment containerFragment = new Check1Fragment();

             /* Bundle args = new Bundle();
              args.putString("key_numPedido", Integer.toString(v));
              containerFragment.setArguments(args);
             */
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();

            }
        });
        ups.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check1Fragment containerFragment = new Check1Fragment();

             /* Bundle args = new Bundle();
              args.putString("key_numPedido", Integer.toString(v));
              containerFragment.setArguments(args);
             */
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();

            }
        });

        aires.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check1Fragment containerFragment = new Check1Fragment();

             /* Bundle args = new Bundle();
              args.putString("key_numPedido", Integer.toString(v));
              containerFragment.setArguments(args);
             */
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();

            }
        });
        servicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check1Fragment containerFragment = new Check1Fragment();

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
