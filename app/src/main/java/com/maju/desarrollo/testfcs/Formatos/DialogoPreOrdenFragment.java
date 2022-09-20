package com.maju.desarrollo.testfcs.Formatos;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maju.desarrollo.testfcs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DialogoPreOrdenFragment extends Fragment {


    public DialogoPreOrdenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialogo_pre_orden, container, false);
    }

}
