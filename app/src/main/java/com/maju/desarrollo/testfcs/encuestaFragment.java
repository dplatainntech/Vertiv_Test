package com.maju.desarrollo.testfcs;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class encuestaFragment extends Fragment {

    ImageButton adelante, atras;

    public encuestaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_encuesta, container, false);

        adelante = (ImageButton) v.findViewById(R.id.imageButtonAdelante);
        atras = (ImageButton) v.findViewById(R.id.imageButtonAtras);

        adelante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* encuestaFragment containerFragment = new encuestaFragment();
                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, containerFragment).addToBackStack(null).commit();
*/
                Intent siguiente2 = new Intent(getActivity(), Fotografia.class);
                getActivity().startActivity(siguiente2);
                getActivity().overridePendingTransition(R.anim.left_in, R.anim.left_out);
            }
        });

        atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        return v;


    }

}
