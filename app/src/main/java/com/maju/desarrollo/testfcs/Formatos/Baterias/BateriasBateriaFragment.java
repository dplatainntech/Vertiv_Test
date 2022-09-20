package com.maju.desarrollo.testfcs.Formatos.Baterias;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;

/**
 * A simple {@link Fragment} subclass.
 */
public class BateriasBateriaFragment extends Fragment {
    Spinner spinner1,spinner2,spinner3;
    OperacionesDB D_B;
    String id_formato;
    Baterias Formato;
    ImageView imf1,imf2,imf3;
    boolean ActivoM1 = true,ActivoM2 = false,ActivoM3 = false;


    public BateriasBateriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_baterias_bateria, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerBateriaF_id(id_formato);

        //region RELACION CAMPOS
        final LinearLayout Btn_M1 = (LinearLayout)v.findViewById(R.id.clickcampo1);
        final LinearLayout Btn_M2 = (LinearLayout)v.findViewById(R.id.clickcampo2);
        final LinearLayout Btn_M3 = (LinearLayout)v.findViewById(R.id.clickcampo3);

        final LinearLayout content_M1 = (LinearLayout)v.findViewById(R.id.Modulo1);
        final LinearLayout content_M2 = (LinearLayout)v.findViewById(R.id.Modulo2);
        final LinearLayout content_M3 = (LinearLayout)v.findViewById(R.id.Modulo3);

        final EditText VDC1 = (EditText)v.findViewById(R.id.VDC1);
        final EditText VDC2 = (EditText)v.findViewById(R.id.VDC2);
        final EditText VDC3 = (EditText)v.findViewById(R.id.VDC3);
        final EditText VDC4 = (EditText)v.findViewById(R.id.VDC4);
        final EditText VDC5 = (EditText)v.findViewById(R.id.VDC5);
        final EditText VDC6 = (EditText)v.findViewById(R.id.VDC6);
        final EditText VDC7 = (EditText)v.findViewById(R.id.VDC7);
        final EditText VDC8 = (EditText)v.findViewById(R.id.VDC8);
        final EditText VDC9 = (EditText)v.findViewById(R.id.VDC9);
        final EditText VDC10 = (EditText)v.findViewById(R.id.VDC10);
        final EditText VDC11 = (EditText)v.findViewById(R.id.VDC11);
        final EditText VDC12 = (EditText)v.findViewById(R.id.VDC12);
        final EditText VDC13 = (EditText)v.findViewById(R.id.VDC13);
        final EditText VDC14 = (EditText)v.findViewById(R.id.VDC14);
        final EditText VDC15 = (EditText)v.findViewById(R.id.VDC15);
        final EditText VDC16 = (EditText)v.findViewById(R.id.VDC16);
        final EditText VDC17 = (EditText)v.findViewById(R.id.VDC17);
        final EditText VDC18 = (EditText)v.findViewById(R.id.VDC18);
        final EditText VDC19 = (EditText)v.findViewById(R.id.VDC19);
        final EditText VDC20 = (EditText)v.findViewById(R.id.VDC20);
        final EditText VDC21 = (EditText)v.findViewById(R.id.VDC21);
        final EditText VDC22 = (EditText)v.findViewById(R.id.VDC22);
        final EditText VDC23 = (EditText)v.findViewById(R.id.VDC23);
        final EditText VDC24 = (EditText)v.findViewById(R.id.VDC24);
        final EditText VDC25 = (EditText)v.findViewById(R.id.VDC25);
        final EditText VDC26 = (EditText)v.findViewById(R.id.VDC26);
        final EditText VDC27 = (EditText)v.findViewById(R.id.VDC27);
        final EditText VDC28 = (EditText)v.findViewById(R.id.VDC28);
        final EditText VDC29 = (EditText)v.findViewById(R.id.VDC29);
        final EditText VDC30 = (EditText)v.findViewById(R.id.VDC30);
        final EditText VDC31 = (EditText)v.findViewById(R.id.VDC31);
        final EditText VDC32 = (EditText)v.findViewById(R.id.VDC32);
        final EditText VDC33 = (EditText)v.findViewById(R.id.VDC33);
        final EditText VDC34 = (EditText)v.findViewById(R.id.VDC34);
        final EditText VDC35 = (EditText)v.findViewById(R.id.VDC35);
        final EditText VDC36 = (EditText)v.findViewById(R.id.VDC36);
        final EditText VDC37 = (EditText)v.findViewById(R.id.VDC37);
        final EditText VDC38 = (EditText)v.findViewById(R.id.VDC38);
        final EditText VDC39 = (EditText)v.findViewById(R.id.VDC39);
        final EditText VDC40 = (EditText)v.findViewById(R.id.VDC40);
        final EditText VDC41 = (EditText)v.findViewById(R.id.VDC41);
        final EditText VDC42 = (EditText)v.findViewById(R.id.VDC42);
        final EditText VDC43 = (EditText)v.findViewById(R.id.VDC43);
        final EditText VDC44 = (EditText)v.findViewById(R.id.VDC44);
        final EditText VDC45 = (EditText)v.findViewById(R.id.VDC45);
        final EditText VDC46 = (EditText)v.findViewById(R.id.VDC46);
        final EditText VDC47 = (EditText)v.findViewById(R.id.VDC47);
        final EditText VDC48 = (EditText)v.findViewById(R.id.VDC48);
        final EditText VDC49 = (EditText)v.findViewById(R.id.VDC49);
        final EditText VDC50 = (EditText)v.findViewById(R.id.VDC50);
        final EditText VDC51 = (EditText)v.findViewById(R.id.VDC51);
        final EditText VDC52 = (EditText)v.findViewById(R.id.VDC52);
        final EditText VDC53 = (EditText)v.findViewById(R.id.VDC53);
        final EditText VDC54 = (EditText)v.findViewById(R.id.VDC54);
        final EditText VDC55 = (EditText)v.findViewById(R.id.VDC55);
        final EditText VDC56 = (EditText)v.findViewById(R.id.VDC56);
        final EditText VDC57 = (EditText)v.findViewById(R.id.VDC57);
        final EditText VDC58 = (EditText)v.findViewById(R.id.VDC58);
        final EditText VDC59 = (EditText)v.findViewById(R.id.VDC59);
        final EditText VDC60 = (EditText)v.findViewById(R.id.VDC60);
        final EditText VDC61 = (EditText)v.findViewById(R.id.VDC61);
        final EditText VDC62 = (EditText)v.findViewById(R.id.VDC62);
        final EditText VDC63 = (EditText)v.findViewById(R.id.VDC63);
        final EditText VDC64 = (EditText)v.findViewById(R.id.VDC64);
        final EditText VDC65 = (EditText)v.findViewById(R.id.VDC65);
        final EditText VDC66 = (EditText)v.findViewById(R.id.VDC66);
        final EditText VDC67 = (EditText)v.findViewById(R.id.VDC67);
        final EditText VDC68 = (EditText)v.findViewById(R.id.VDC68);
        final EditText VDC69 = (EditText)v.findViewById(R.id.VDC69);
        final EditText VDC70 = (EditText)v.findViewById(R.id.VDC70);
        final EditText VDC71 = (EditText)v.findViewById(R.id.VDC71);
        final EditText VDC72 = (EditText)v.findViewById(R.id.VDC72);
        final EditText VDC73 = (EditText)v.findViewById(R.id.VDC73);
        final EditText VDC74 = (EditText)v.findViewById(R.id.VDC74);
        final EditText VDC75 = (EditText)v.findViewById(R.id.VDC75);
        final EditText VDC76 = (EditText)v.findViewById(R.id.VDC76);
        final EditText VDC77 = (EditText)v.findViewById(R.id.VDC77);
        final EditText VDC78 = (EditText)v.findViewById(R.id.VDC78);
        final EditText VDC79 = (EditText)v.findViewById(R.id.VDC79);
        final EditText VDC80 = (EditText)v.findViewById(R.id.VDC80);
        final EditText VDC81 = (EditText)v.findViewById(R.id.VDC81);
        final EditText VDC82 = (EditText)v.findViewById(R.id.VDC82);
        final EditText VDC83 = (EditText)v.findViewById(R.id.VDC83);
        final EditText VDC84 = (EditText)v.findViewById(R.id.VDC84);
        final EditText VDC85 = (EditText)v.findViewById(R.id.VDC85);
        final EditText VDC86 = (EditText)v.findViewById(R.id.VDC86);
        final EditText VDC87 = (EditText)v.findViewById(R.id.VDC87);
        final EditText VDC88 = (EditText)v.findViewById(R.id.VDC88);
        final EditText VDC89 = (EditText)v.findViewById(R.id.VDC89);
        final EditText VDC90 = (EditText)v.findViewById(R.id.VDC90);
        final EditText VDC91 = (EditText)v.findViewById(R.id.VDC91);
        final EditText VDC92 = (EditText)v.findViewById(R.id.VDC92);
        final EditText VDC93 = (EditText)v.findViewById(R.id.VDC93);
        final EditText VDC94 = (EditText)v.findViewById(R.id.VDC94);
        final EditText VDC95 = (EditText)v.findViewById(R.id.VDC95);
        final EditText VDC96 = (EditText)v.findViewById(R.id.VDC96);
        final EditText VDC97 = (EditText)v.findViewById(R.id.VDC97);
        final EditText VDC98 = (EditText)v.findViewById(R.id.VDC98);
        final EditText VDC99 = (EditText)v.findViewById(R.id.VDC99);
        final EditText VDC100 = (EditText)v.findViewById(R.id.VDC100);
        final EditText VDC101 = (EditText)v.findViewById(R.id.VDC101);
        final EditText VDC102 = (EditText)v.findViewById(R.id.VDC102);
        final EditText VDC103 = (EditText)v.findViewById(R.id.VDC103);
        final EditText VDC104 = (EditText)v.findViewById(R.id.VDC104);
        final EditText VDC105 = (EditText)v.findViewById(R.id.VDC105);
        final EditText VDC106 = (EditText)v.findViewById(R.id.VDC106);
        final EditText VDC107 = (EditText)v.findViewById(R.id.VDC107);
        final EditText VDC108 = (EditText)v.findViewById(R.id.VDC108);
        final EditText VDC109 = (EditText)v.findViewById(R.id.VDC109);
        final EditText VDC110 = (EditText)v.findViewById(R.id.VDC110);
        final EditText VDC111 = (EditText)v.findViewById(R.id.VDC111);
        final EditText VDC112 = (EditText)v.findViewById(R.id.VDC112);
        final EditText VDC113 = (EditText)v.findViewById(R.id.VDC113);
        final EditText VDC114 = (EditText)v.findViewById(R.id.VDC114);
        final EditText VDC115 = (EditText)v.findViewById(R.id.VDC115);
        final EditText VDC116 = (EditText)v.findViewById(R.id.VDC116);
        final EditText VDC117 = (EditText)v.findViewById(R.id.VDC117);
        final EditText VDC118 = (EditText)v.findViewById(R.id.VDC118);
        final EditText VDC119 = (EditText)v.findViewById(R.id.VDC119);
        final EditText VDC120 = (EditText)v.findViewById(R.id.VDC120);
        final EditText VAC1 = (EditText)v.findViewById(R.id.VAC1);
        final EditText VAC2 = (EditText)v.findViewById(R.id.VAC2);
        final EditText VAC3 = (EditText)v.findViewById(R.id.VAC3);
        final EditText VAC4 = (EditText)v.findViewById(R.id.VAC4);
        final EditText VAC5 = (EditText)v.findViewById(R.id.VAC5);
        final EditText VAC6 = (EditText)v.findViewById(R.id.VAC6);
        final EditText VAC7 = (EditText)v.findViewById(R.id.VAC7);
        final EditText VAC8 = (EditText)v.findViewById(R.id.VAC8);
        final EditText VAC9 = (EditText)v.findViewById(R.id.VAC9);
        final EditText VAC10 = (EditText)v.findViewById(R.id.VAC10);
        final EditText VAC11 = (EditText)v.findViewById(R.id.VAC11);
        final EditText VAC12 = (EditText)v.findViewById(R.id.VAC12);
        final EditText VAC13 = (EditText)v.findViewById(R.id.VAC13);
        final EditText VAC14 = (EditText)v.findViewById(R.id.VAC14);
        final EditText VAC15 = (EditText)v.findViewById(R.id.VAC15);
        final EditText VAC16 = (EditText)v.findViewById(R.id.VAC16);
        final EditText VAC17 = (EditText)v.findViewById(R.id.VAC17);
        final EditText VAC18 = (EditText)v.findViewById(R.id.VAC18);
        final EditText VAC19 = (EditText)v.findViewById(R.id.VAC19);
        final EditText VAC20 = (EditText)v.findViewById(R.id.VAC20);
        final EditText VAC21 = (EditText)v.findViewById(R.id.VAC21);
        final EditText VAC22 = (EditText)v.findViewById(R.id.VAC22);
        final EditText VAC23 = (EditText)v.findViewById(R.id.VAC23);
        final EditText VAC24 = (EditText)v.findViewById(R.id.VAC24);
        final EditText VAC25 = (EditText)v.findViewById(R.id.VAC25);
        final EditText VAC26 = (EditText)v.findViewById(R.id.VAC26);
        final EditText VAC27 = (EditText)v.findViewById(R.id.VAC27);
        final EditText VAC28 = (EditText)v.findViewById(R.id.VAC28);
        final EditText VAC29 = (EditText)v.findViewById(R.id.VAC29);
        final EditText VAC30 = (EditText)v.findViewById(R.id.VAC30);
        final EditText VAC31 = (EditText)v.findViewById(R.id.VAC31);
        final EditText VAC32 = (EditText)v.findViewById(R.id.VAC32);
        final EditText VAC33 = (EditText)v.findViewById(R.id.VAC33);
        final EditText VAC34 = (EditText)v.findViewById(R.id.VAC34);
        final EditText VAC35 = (EditText)v.findViewById(R.id.VAC35);
        final EditText VAC36 = (EditText)v.findViewById(R.id.VAC36);
        final EditText VAC37 = (EditText)v.findViewById(R.id.VAC37);
        final EditText VAC38 = (EditText)v.findViewById(R.id.VAC38);
        final EditText VAC39 = (EditText)v.findViewById(R.id.VAC39);
        final EditText VAC40 = (EditText)v.findViewById(R.id.VAC40);
        final EditText VAC41 = (EditText)v.findViewById(R.id.VAC41);
        final EditText VAC42 = (EditText)v.findViewById(R.id.VAC42);
        final EditText VAC43 = (EditText)v.findViewById(R.id.VAC43);
        final EditText VAC44 = (EditText)v.findViewById(R.id.VAC44);
        final EditText VAC45 = (EditText)v.findViewById(R.id.VAC45);
        final EditText VAC46 = (EditText)v.findViewById(R.id.VAC46);
        final EditText VAC47 = (EditText)v.findViewById(R.id.VAC47);
        final EditText VAC48 = (EditText)v.findViewById(R.id.VAC48);
        final EditText VAC49 = (EditText)v.findViewById(R.id.VAC49);
        final EditText VAC50 = (EditText)v.findViewById(R.id.VAC50);
        final EditText VAC51 = (EditText)v.findViewById(R.id.VAC51);
        final EditText VAC52 = (EditText)v.findViewById(R.id.VAC52);
        final EditText VAC53 = (EditText)v.findViewById(R.id.VAC53);
        final EditText VAC54 = (EditText)v.findViewById(R.id.VAC54);
        final EditText VAC55 = (EditText)v.findViewById(R.id.VAC55);
        final EditText VAC56 = (EditText)v.findViewById(R.id.VAC56);
        final EditText VAC57 = (EditText)v.findViewById(R.id.VAC57);
        final EditText VAC58 = (EditText)v.findViewById(R.id.VAC58);
        final EditText VAC59 = (EditText)v.findViewById(R.id.VAC59);
        final EditText VAC60 = (EditText)v.findViewById(R.id.VAC60);
        final EditText VAC61 = (EditText)v.findViewById(R.id.VAC61);
        final EditText VAC62 = (EditText)v.findViewById(R.id.VAC62);
        final EditText VAC63 = (EditText)v.findViewById(R.id.VAC63);
        final EditText VAC64 = (EditText)v.findViewById(R.id.VAC64);
        final EditText VAC65 = (EditText)v.findViewById(R.id.VAC65);
        final EditText VAC66 = (EditText)v.findViewById(R.id.VAC66);
        final EditText VAC67 = (EditText)v.findViewById(R.id.VAC67);
        final EditText VAC68 = (EditText)v.findViewById(R.id.VAC68);
        final EditText VAC69 = (EditText)v.findViewById(R.id.VAC69);
        final EditText VAC70 = (EditText)v.findViewById(R.id.VAC70);
        final EditText VAC71 = (EditText)v.findViewById(R.id.VAC71);
        final EditText VAC72 = (EditText)v.findViewById(R.id.VAC72);
        final EditText VAC73 = (EditText)v.findViewById(R.id.VAC73);
        final EditText VAC74 = (EditText)v.findViewById(R.id.VAC74);
        final EditText VAC75 = (EditText)v.findViewById(R.id.VAC75);
        final EditText VAC76 = (EditText)v.findViewById(R.id.VAC76);
        final EditText VAC77 = (EditText)v.findViewById(R.id.VAC77);
        final EditText VAC78 = (EditText)v.findViewById(R.id.VAC78);
        final EditText VAC79 = (EditText)v.findViewById(R.id.VAC79);
        final EditText VAC80 = (EditText)v.findViewById(R.id.VAC80);
        final EditText VAC81 = (EditText)v.findViewById(R.id.VAC81);
        final EditText VAC82 = (EditText)v.findViewById(R.id.VAC82);
        final EditText VAC83 = (EditText)v.findViewById(R.id.VAC83);
        final EditText VAC84 = (EditText)v.findViewById(R.id.VAC84);
        final EditText VAC85 = (EditText)v.findViewById(R.id.VAC85);
        final EditText VAC86 = (EditText)v.findViewById(R.id.VAC86);
        final EditText VAC87 = (EditText)v.findViewById(R.id.VAC87);
        final EditText VAC88 = (EditText)v.findViewById(R.id.VAC88);
        final EditText VAC89 = (EditText)v.findViewById(R.id.VAC89);
        final EditText VAC90 = (EditText)v.findViewById(R.id.VAC90);
        final EditText VAC91 = (EditText)v.findViewById(R.id.VAC91);
        final EditText VAC92 = (EditText)v.findViewById(R.id.VAC92);
        final EditText VAC93 = (EditText)v.findViewById(R.id.VAC93);
        final EditText VAC94 = (EditText)v.findViewById(R.id.VAC94);
        final EditText VAC95 = (EditText)v.findViewById(R.id.VAC95);
        final EditText VAC96 = (EditText)v.findViewById(R.id.VAC96);
        final EditText VAC97 = (EditText)v.findViewById(R.id.VAC97);
        final EditText VAC98 = (EditText)v.findViewById(R.id.VAC98);
        final EditText VAC99 = (EditText)v.findViewById(R.id.VAC99);
        final EditText VAC100 = (EditText)v.findViewById(R.id.VAC100);
        final EditText VAC101 = (EditText)v.findViewById(R.id.VAC101);
        final EditText VAC102 = (EditText)v.findViewById(R.id.VAC102);
        final EditText VAC103 = (EditText)v.findViewById(R.id.VAC103);
        final EditText VAC104 = (EditText)v.findViewById(R.id.VAC104);
        final EditText VAC105 = (EditText)v.findViewById(R.id.VAC105);
        final EditText VAC106 = (EditText)v.findViewById(R.id.VAC106);
        final EditText VAC107 = (EditText)v.findViewById(R.id.VAC107);
        final EditText VAC108 = (EditText)v.findViewById(R.id.VAC108);
        final EditText VAC109 = (EditText)v.findViewById(R.id.VAC109);
        final EditText VAC110 = (EditText)v.findViewById(R.id.VAC110);
        final EditText VAC111 = (EditText)v.findViewById(R.id.VAC111);
        final EditText VAC112 = (EditText)v.findViewById(R.id.VAC112);
        final EditText VAC113 = (EditText)v.findViewById(R.id.VAC113);
        final EditText VAC114 = (EditText)v.findViewById(R.id.VAC114);
        final EditText VAC115 = (EditText)v.findViewById(R.id.VAC115);
        final EditText VAC116 = (EditText)v.findViewById(R.id.VAC116);
        final EditText VAC117 = (EditText)v.findViewById(R.id.VAC117);
        final EditText VAC118 = (EditText)v.findViewById(R.id.VAC118);
        final EditText VAC119 = (EditText)v.findViewById(R.id.VAC119);
        final EditText VAC120 = (EditText)v.findViewById(R.id.VAC120);



        //endregion

        //region MPOSTRAR DATOS GUARDADOS
        try{VDC1.setText(Formato.getVDC1());}catch (Exception e){}
        try{VDC2.setText(Formato.getVDC2());}catch (Exception e){}
        try{VDC3.setText(Formato.getVDC3());}catch (Exception e){}
        try{VDC4.setText(Formato.getVDC4());}catch (Exception e){}
        try{VDC5.setText(Formato.getVDC5());}catch (Exception e){}
        try{VDC6.setText(Formato.getVDC6());}catch (Exception e){}
        try{VDC7.setText(Formato.getVDC7());}catch (Exception e){}
        try{VDC8.setText(Formato.getVDC8());}catch (Exception e){}
        try{VDC9.setText(Formato.getVDC9());}catch (Exception e){}
        try{VDC10.setText(Formato.getVDC10());}catch (Exception e){}
        try{VDC11.setText(Formato.getVDC11());}catch (Exception e){}
        try{VDC12.setText(Formato.getVDC12());}catch (Exception e){}
        try{VDC13.setText(Formato.getVDC13());}catch (Exception e){}
        try{VDC14.setText(Formato.getVDC14());}catch (Exception e){}
        try{VDC15.setText(Formato.getVDC15());}catch (Exception e){}
        try{VDC16.setText(Formato.getVDC16());}catch (Exception e){}
        try{VDC17.setText(Formato.getVDC17());}catch (Exception e){}
        try{VDC18.setText(Formato.getVDC18());}catch (Exception e){}
        try{VDC19.setText(Formato.getVDC19());}catch (Exception e){}
        try{VDC20.setText(Formato.getVDC20());}catch (Exception e){}
        try{VDC21.setText(Formato.getVDC21());}catch (Exception e){}
        try{VDC22.setText(Formato.getVDC22());}catch (Exception e){}
        try{VDC23.setText(Formato.getVDC23());}catch (Exception e){}
        try{VDC24.setText(Formato.getVDC24());}catch (Exception e){}
        try{VDC25.setText(Formato.getVDC25());}catch (Exception e){}
        try{VDC26.setText(Formato.getVDC26());}catch (Exception e){}
        try{VDC27.setText(Formato.getVDC27());}catch (Exception e){}
        try{VDC28.setText(Formato.getVDC28());}catch (Exception e){}
        try{VDC29.setText(Formato.getVDC29());}catch (Exception e){}
        try{VDC30.setText(Formato.getVDC30());}catch (Exception e){}
        try{VDC31.setText(Formato.getVDC31());}catch (Exception e){}
        try{VDC32.setText(Formato.getVDC32());}catch (Exception e){}
        try{VDC33.setText(Formato.getVDC33());}catch (Exception e){}
        try{VDC34.setText(Formato.getVDC34());}catch (Exception e){}
        try{VDC35.setText(Formato.getVDC35());}catch (Exception e){}
        try{VDC36.setText(Formato.getVDC36());}catch (Exception e){}
        try{VDC37.setText(Formato.getVDC37());}catch (Exception e){}
        try{VDC38.setText(Formato.getVDC38());}catch (Exception e){}
        try{VDC39.setText(Formato.getVDC39());}catch (Exception e){}
        try{VDC40.setText(Formato.getVDC40());}catch (Exception e){}
        try{VDC41.setText(Formato.getVDC41());}catch (Exception e){}
        try{VDC42.setText(Formato.getVDC42());}catch (Exception e){}
        try{VDC43.setText(Formato.getVDC43());}catch (Exception e){}
        try{VDC44.setText(Formato.getVDC44());}catch (Exception e){}
        try{VDC45.setText(Formato.getVDC45());}catch (Exception e){}
        try{VDC46.setText(Formato.getVDC46());}catch (Exception e){}
        try{VDC47.setText(Formato.getVDC47());}catch (Exception e){}
        try{VDC48.setText(Formato.getVDC48());}catch (Exception e){}
        try{VDC49.setText(Formato.getVDC49());}catch (Exception e){}
        try{VDC50.setText(Formato.getVDC50());}catch (Exception e){}
        try{VDC51.setText(Formato.getVDC51());}catch (Exception e){}
        try{VDC52.setText(Formato.getVDC52());}catch (Exception e){}
        try{VDC53.setText(Formato.getVDC53());}catch (Exception e){}
        try{VDC54.setText(Formato.getVDC54());}catch (Exception e){}
        try{VDC55.setText(Formato.getVDC55());}catch (Exception e){}
        try{VDC56.setText(Formato.getVDC56());}catch (Exception e){}
        try{VDC57.setText(Formato.getVDC57());}catch (Exception e){}
        try{VDC58.setText(Formato.getVDC58());}catch (Exception e){}
        try{VDC59.setText(Formato.getVDC59());}catch (Exception e){}
        try{VDC60.setText(Formato.getVDC60());}catch (Exception e){}
        try{VDC61.setText(Formato.getVDC61());}catch (Exception e){}
        try{VDC62.setText(Formato.getVDC62());}catch (Exception e){}
        try{VDC63.setText(Formato.getVDC63());}catch (Exception e){}
        try{VDC64.setText(Formato.getVDC64());}catch (Exception e){}
        try{VDC65.setText(Formato.getVDC65());}catch (Exception e){}
        try{VDC66.setText(Formato.getVDC66());}catch (Exception e){}
        try{VDC67.setText(Formato.getVDC67());}catch (Exception e){}
        try{VDC68.setText(Formato.getVDC68());}catch (Exception e){}
        try{VDC69.setText(Formato.getVDC69());}catch (Exception e){}
        try{VDC70.setText(Formato.getVDC70());}catch (Exception e){}
        try{VDC71.setText(Formato.getVDC71());}catch (Exception e){}
        try{VDC72.setText(Formato.getVDC72());}catch (Exception e){}
        try{VDC73.setText(Formato.getVDC73());}catch (Exception e){}
        try{VDC74.setText(Formato.getVDC74());}catch (Exception e){}
        try{VDC75.setText(Formato.getVDC75());}catch (Exception e){}
        try{VDC76.setText(Formato.getVDC76());}catch (Exception e){}
        try{VDC77.setText(Formato.getVDC77());}catch (Exception e){}
        try{VDC78.setText(Formato.getVDC78());}catch (Exception e){}
        try{VDC79.setText(Formato.getVDC79());}catch (Exception e){}
        try{VDC80.setText(Formato.getVDC80());}catch (Exception e){}
        try{VDC81.setText(Formato.getVDC81());}catch (Exception e){}
        try{VDC82.setText(Formato.getVDC82());}catch (Exception e){}
        try{VDC83.setText(Formato.getVDC83());}catch (Exception e){}
        try{VDC84.setText(Formato.getVDC84());}catch (Exception e){}
        try{VDC85.setText(Formato.getVDC85());}catch (Exception e){}
        try{VDC86.setText(Formato.getVDC86());}catch (Exception e){}
        try{VDC87.setText(Formato.getVDC87());}catch (Exception e){}
        try{VDC88.setText(Formato.getVDC88());}catch (Exception e){}
        try{VDC89.setText(Formato.getVDC89());}catch (Exception e){}
        try{VDC90.setText(Formato.getVDC90());}catch (Exception e){}
        try{VDC91.setText(Formato.getVDC91());}catch (Exception e){}
        try{VDC92.setText(Formato.getVDC92());}catch (Exception e){}
        try{VDC93.setText(Formato.getVDC93());}catch (Exception e){}
        try{VDC94.setText(Formato.getVDC94());}catch (Exception e){}
        try{VDC95.setText(Formato.getVDC95());}catch (Exception e){}
        try{VDC96.setText(Formato.getVDC96());}catch (Exception e){}
        try{VDC97.setText(Formato.getVDC97());}catch (Exception e){}
        try{VDC98.setText(Formato.getVDC98());}catch (Exception e){}
        try{VDC99.setText(Formato.getVDC99());}catch (Exception e){}
        try{VDC100.setText(Formato.getVDC100());}catch (Exception e){}
        try{VDC101.setText(Formato.getVDC101());}catch (Exception e){}
        try{VDC102.setText(Formato.getVDC102());}catch (Exception e){}
        try{VDC103.setText(Formato.getVDC103());}catch (Exception e){}
        try{VDC104.setText(Formato.getVDC104());}catch (Exception e){}
        try{VDC105.setText(Formato.getVDC105());}catch (Exception e){}
        try{VDC106.setText(Formato.getVDC106());}catch (Exception e){}
        try{VDC107.setText(Formato.getVDC107());}catch (Exception e){}
        try{VDC108.setText(Formato.getVDC108());}catch (Exception e){}
        try{VDC109.setText(Formato.getVDC109());}catch (Exception e){}
        try{VDC110.setText(Formato.getVDC110());}catch (Exception e){}
        try{VDC111.setText(Formato.getVDC111());}catch (Exception e){}
        try{VDC112.setText(Formato.getVDC112());}catch (Exception e){}
        try{VDC113.setText(Formato.getVDC113());}catch (Exception e){}
        try{VDC114.setText(Formato.getVDC114());}catch (Exception e){}
        try{VDC115.setText(Formato.getVDC115());}catch (Exception e){}
        try{VDC116.setText(Formato.getVDC116());}catch (Exception e){}
        try{VDC117.setText(Formato.getVDC117());}catch (Exception e){}
        try{VDC118.setText(Formato.getVDC118());}catch (Exception e){}
        try{VDC119.setText(Formato.getVDC119());}catch (Exception e){}
        try{VDC120.setText(Formato.getVDC120());}catch (Exception e){}
        try{VAC1.setText(Formato.getVAC1());}catch (Exception e){}
        try{VAC2.setText(Formato.getVAC2());}catch (Exception e){}
        try{VAC3.setText(Formato.getVAC3());}catch (Exception e){}
        try{VAC4.setText(Formato.getVAC4());}catch (Exception e){}
        try{VAC5.setText(Formato.getVAC5());}catch (Exception e){}
        try{VAC6.setText(Formato.getVAC6());}catch (Exception e){}
        try{VAC7.setText(Formato.getVAC7());}catch (Exception e){}
        try{VAC8.setText(Formato.getVAC8());}catch (Exception e){}
        try{VAC9.setText(Formato.getVAC9());}catch (Exception e){}
        try{VAC10.setText(Formato.getVAC10());}catch (Exception e){}
        try{VAC11.setText(Formato.getVAC11());}catch (Exception e){}
        try{VAC12.setText(Formato.getVAC12());}catch (Exception e){}
        try{VAC13.setText(Formato.getVAC13());}catch (Exception e){}
        try{VAC14.setText(Formato.getVAC14());}catch (Exception e){}
        try{VAC15.setText(Formato.getVAC15());}catch (Exception e){}
        try{VAC16.setText(Formato.getVAC16());}catch (Exception e){}
        try{VAC17.setText(Formato.getVAC17());}catch (Exception e){}
        try{VAC18.setText(Formato.getVAC18());}catch (Exception e){}
        try{VAC19.setText(Formato.getVAC19());}catch (Exception e){}
        try{VAC20.setText(Formato.getVAC20());}catch (Exception e){}
        try{VAC21.setText(Formato.getVAC21());}catch (Exception e){}
        try{VAC22.setText(Formato.getVAC22());}catch (Exception e){}
        try{VAC23.setText(Formato.getVAC23());}catch (Exception e){}
        try{VAC24.setText(Formato.getVAC24());}catch (Exception e){}
        try{VAC25.setText(Formato.getVAC25());}catch (Exception e){}
        try{VAC26.setText(Formato.getVAC26());}catch (Exception e){}
        try{VAC27.setText(Formato.getVAC27());}catch (Exception e){}
        try{VAC28.setText(Formato.getVAC28());}catch (Exception e){}
        try{VAC29.setText(Formato.getVAC29());}catch (Exception e){}
        try{VAC30.setText(Formato.getVAC30());}catch (Exception e){}
        try{VAC31.setText(Formato.getVAC31());}catch (Exception e){}
        try{VAC32.setText(Formato.getVAC32());}catch (Exception e){}
        try{VAC33.setText(Formato.getVAC33());}catch (Exception e){}
        try{VAC34.setText(Formato.getVAC34());}catch (Exception e){}
        try{VAC35.setText(Formato.getVAC35());}catch (Exception e){}
        try{VAC36.setText(Formato.getVAC36());}catch (Exception e){}
        try{VAC37.setText(Formato.getVAC37());}catch (Exception e){}
        try{VAC38.setText(Formato.getVAC38());}catch (Exception e){}
        try{VAC39.setText(Formato.getVAC39());}catch (Exception e){}
        try{VAC40.setText(Formato.getVAC40());}catch (Exception e){}
        try{VAC41.setText(Formato.getVAC41());}catch (Exception e){}
        try{VAC42.setText(Formato.getVAC42());}catch (Exception e){}
        try{VAC43.setText(Formato.getVAC43());}catch (Exception e){}
        try{VAC44.setText(Formato.getVAC44());}catch (Exception e){}
        try{VAC45.setText(Formato.getVAC45());}catch (Exception e){}
        try{VAC46.setText(Formato.getVAC46());}catch (Exception e){}
        try{VAC47.setText(Formato.getVAC47());}catch (Exception e){}
        try{VAC48.setText(Formato.getVAC48());}catch (Exception e){}
        try{VAC49.setText(Formato.getVAC49());}catch (Exception e){}
        try{VAC50.setText(Formato.getVAC50());}catch (Exception e){}
        try{VAC51.setText(Formato.getVAC51());}catch (Exception e){}
        try{VAC52.setText(Formato.getVAC52());}catch (Exception e){}
        try{VAC53.setText(Formato.getVAC53());}catch (Exception e){}
        try{VAC54.setText(Formato.getVAC54());}catch (Exception e){}
        try{VAC55.setText(Formato.getVAC55());}catch (Exception e){}
        try{VAC56.setText(Formato.getVAC56());}catch (Exception e){}
        try{VAC57.setText(Formato.getVAC57());}catch (Exception e){}
        try{VAC58.setText(Formato.getVAC58());}catch (Exception e){}
        try{VAC59.setText(Formato.getVAC59());}catch (Exception e){}
        try{VAC60.setText(Formato.getVAC60());}catch (Exception e){}
        try{VAC61.setText(Formato.getVAC61());}catch (Exception e){}
        try{VAC62.setText(Formato.getVAC62());}catch (Exception e){}
        try{VAC63.setText(Formato.getVAC63());}catch (Exception e){}
        try{VAC64.setText(Formato.getVAC64());}catch (Exception e){}
        try{VAC65.setText(Formato.getVAC65());}catch (Exception e){}
        try{VAC66.setText(Formato.getVAC66());}catch (Exception e){}
        try{VAC67.setText(Formato.getVAC67());}catch (Exception e){}
        try{VAC68.setText(Formato.getVAC68());}catch (Exception e){}
        try{VAC69.setText(Formato.getVAC69());}catch (Exception e){}
        try{VAC70.setText(Formato.getVAC70());}catch (Exception e){}
        try{VAC71.setText(Formato.getVAC71());}catch (Exception e){}
        try{VAC72.setText(Formato.getVAC72());}catch (Exception e){}
        try{VAC73.setText(Formato.getVAC73());}catch (Exception e){}
        try{VAC74.setText(Formato.getVAC74());}catch (Exception e){}
        try{VAC75.setText(Formato.getVAC75());}catch (Exception e){}
        try{VAC76.setText(Formato.getVAC76());}catch (Exception e){}
        try{VAC77.setText(Formato.getVAC77());}catch (Exception e){}
        try{VAC78.setText(Formato.getVAC78());}catch (Exception e){}
        try{VAC79.setText(Formato.getVAC79());}catch (Exception e){}
        try{VAC80.setText(Formato.getVAC80());}catch (Exception e){}
        try{VAC81.setText(Formato.getVAC81());}catch (Exception e){}
        try{VAC82.setText(Formato.getVAC82());}catch (Exception e){}
        try{VAC83.setText(Formato.getVAC83());}catch (Exception e){}
        try{VAC84.setText(Formato.getVAC84());}catch (Exception e){}
        try{VAC85.setText(Formato.getVAC85());}catch (Exception e){}
        try{VAC86.setText(Formato.getVAC86());}catch (Exception e){}
        try{VAC87.setText(Formato.getVAC87());}catch (Exception e){}
        try{VAC88.setText(Formato.getVAC88());}catch (Exception e){}
        try{VAC89.setText(Formato.getVAC89());}catch (Exception e){}
        try{VAC90.setText(Formato.getVAC90());}catch (Exception e){}
        try{VAC91.setText(Formato.getVAC91());}catch (Exception e){}
        try{VAC92.setText(Formato.getVAC92());}catch (Exception e){}
        try{VAC93.setText(Formato.getVAC93());}catch (Exception e){}
        try{VAC94.setText(Formato.getVAC94());}catch (Exception e){}
        try{VAC95.setText(Formato.getVAC95());}catch (Exception e){}
        try{VAC96.setText(Formato.getVAC96());}catch (Exception e){}
        try{VAC97.setText(Formato.getVAC97());}catch (Exception e){}
        try{VAC98.setText(Formato.getVAC98());}catch (Exception e){}
        try{VAC99.setText(Formato.getVAC99());}catch (Exception e){}
        try{VAC100.setText(Formato.getVAC100());}catch (Exception e){}
        try{VAC101.setText(Formato.getVAC101());}catch (Exception e){}
        try{VAC102.setText(Formato.getVAC102());}catch (Exception e){}
        try{VAC103.setText(Formato.getVAC103());}catch (Exception e){}
        try{VAC104.setText(Formato.getVAC104());}catch (Exception e){}
        try{VAC105.setText(Formato.getVAC105());}catch (Exception e){}
        try{VAC106.setText(Formato.getVAC106());}catch (Exception e){}
        try{VAC107.setText(Formato.getVAC107());}catch (Exception e){}
        try{VAC108.setText(Formato.getVAC108());}catch (Exception e){}
        try{VAC109.setText(Formato.getVAC109());}catch (Exception e){}
        try{VAC110.setText(Formato.getVAC110());}catch (Exception e){}
        try{VAC111.setText(Formato.getVAC111());}catch (Exception e){}
        try{VAC112.setText(Formato.getVAC112());}catch (Exception e){}
        try{VAC113.setText(Formato.getVAC113());}catch (Exception e){}
        try{VAC114.setText(Formato.getVAC114());}catch (Exception e){}
        try{VAC115.setText(Formato.getVAC115());}catch (Exception e){}
        try{VAC116.setText(Formato.getVAC116());}catch (Exception e){}
        try{VAC117.setText(Formato.getVAC117());}catch (Exception e){}
        try{VAC118.setText(Formato.getVAC118());}catch (Exception e){}
        try{VAC119.setText(Formato.getVAC119());}catch (Exception e){}
        try{VAC120.setText(Formato.getVAC120());}catch (Exception e){}

        //endregion


        //region BTN M1
        Btn_M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    content_M1.setVisibility(View.VISIBLE);
                    content_M2.setVisibility(View.GONE);
                    content_M3.setVisibility(View.GONE);

                Btn_M1.setBackgroundColor(Color.parseColor("#B7B5B5"));
                Btn_M2.setBackgroundColor(Color.parseColor("#E5E3E3"));
                Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion

        //region BTN M2
        Btn_M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_M1.setVisibility(View.GONE);
                content_M2.setVisibility(View.VISIBLE);
                content_M3.setVisibility(View.GONE);
                Btn_M1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                Btn_M2.setBackgroundColor(Color.parseColor("#B7B5B5"));
                Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));
            }
        });
        //endregion

        //region BTN M3
        Btn_M3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_M1.setVisibility(View.GONE);
                content_M2.setVisibility(View.GONE);
                content_M3.setVisibility(View.VISIBLE);
                Btn_M1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                Btn_M2.setBackgroundColor(Color.parseColor("#E5E3E3"));
                Btn_M3.setBackgroundColor(Color.parseColor("#B7B5B5"));
            }
        });
        //endregion


        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Baterias");
                args.putString("valorPaso", id_formato);

                CancelasDialogFragmentMismoActivity dialog= new CancelasDialogFragmentMismoActivity();
                dialog.setCancelable(true);
                dialog.setTargetFragment(getParentFragment(),1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.btn_Guardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region Datos a guardar
                Formato.setVDC1(VDC1.getText().toString());
                Formato.setVDC2(VDC2.getText().toString());
                Formato.setVDC3(VDC3.getText().toString());
                Formato.setVDC4(VDC4.getText().toString());
                Formato.setVDC5(VDC5.getText().toString());
                Formato.setVDC6(VDC6.getText().toString());
                Formato.setVDC7(VDC7.getText().toString());
                Formato.setVDC8(VDC8.getText().toString());
                Formato.setVDC9(VDC9.getText().toString());
                Formato.setVDC10(VDC10.getText().toString());
                Formato.setVDC11(VDC11.getText().toString());
                Formato.setVDC12(VDC12.getText().toString());
                Formato.setVDC13(VDC13.getText().toString());
                Formato.setVDC14(VDC14.getText().toString());
                Formato.setVDC15(VDC15.getText().toString());
                Formato.setVDC16(VDC16.getText().toString());
                Formato.setVDC17(VDC17.getText().toString());
                Formato.setVDC18(VDC18.getText().toString());
                Formato.setVDC19(VDC19.getText().toString());
                Formato.setVDC20(VDC20.getText().toString());
                Formato.setVDC21(VDC21.getText().toString());
                Formato.setVDC22(VDC22.getText().toString());
                Formato.setVDC23(VDC23.getText().toString());
                Formato.setVDC24(VDC24.getText().toString());
                Formato.setVDC25(VDC25.getText().toString());
                Formato.setVDC26(VDC26.getText().toString());
                Formato.setVDC27(VDC27.getText().toString());
                Formato.setVDC28(VDC28.getText().toString());
                Formato.setVDC29(VDC29.getText().toString());
                Formato.setVDC30(VDC30.getText().toString());
                Formato.setVDC31(VDC31.getText().toString());
                Formato.setVDC32(VDC32.getText().toString());
                Formato.setVDC33(VDC33.getText().toString());
                Formato.setVDC34(VDC34.getText().toString());
                Formato.setVDC35(VDC35.getText().toString());
                Formato.setVDC36(VDC36.getText().toString());
                Formato.setVDC37(VDC37.getText().toString());
                Formato.setVDC38(VDC38.getText().toString());
                Formato.setVDC39(VDC39.getText().toString());
                Formato.setVDC40(VDC40.getText().toString());
                Formato.setVDC41(VDC41.getText().toString());
                Formato.setVDC42(VDC42.getText().toString());
                Formato.setVDC43(VDC43.getText().toString());
                Formato.setVDC44(VDC44.getText().toString());
                Formato.setVDC45(VDC45.getText().toString());
                Formato.setVDC46(VDC46.getText().toString());
                Formato.setVDC47(VDC47.getText().toString());
                Formato.setVDC48(VDC48.getText().toString());
                Formato.setVDC49(VDC49.getText().toString());
                Formato.setVDC50(VDC50.getText().toString());
                Formato.setVDC51(VDC51.getText().toString());
                Formato.setVDC52(VDC52.getText().toString());
                Formato.setVDC53(VDC53.getText().toString());
                Formato.setVDC54(VDC54.getText().toString());
                Formato.setVDC55(VDC55.getText().toString());
                Formato.setVDC56(VDC56.getText().toString());
                Formato.setVDC57(VDC57.getText().toString());
                Formato.setVDC58(VDC58.getText().toString());
                Formato.setVDC59(VDC59.getText().toString());
                Formato.setVDC60(VDC60.getText().toString());
                Formato.setVDC61(VDC61.getText().toString());
                Formato.setVDC62(VDC62.getText().toString());
                Formato.setVDC63(VDC63.getText().toString());
                Formato.setVDC64(VDC64.getText().toString());
                Formato.setVDC65(VDC65.getText().toString());
                Formato.setVDC66(VDC66.getText().toString());
                Formato.setVDC67(VDC67.getText().toString());
                Formato.setVDC68(VDC68.getText().toString());
                Formato.setVDC69(VDC69.getText().toString());
                Formato.setVDC70(VDC70.getText().toString());
                Formato.setVDC71(VDC71.getText().toString());
                Formato.setVDC72(VDC72.getText().toString());
                Formato.setVDC73(VDC73.getText().toString());
                Formato.setVDC74(VDC74.getText().toString());
                Formato.setVDC75(VDC75.getText().toString());
                Formato.setVDC76(VDC76.getText().toString());
                Formato.setVDC77(VDC77.getText().toString());
                Formato.setVDC78(VDC78.getText().toString());
                Formato.setVDC79(VDC79.getText().toString());
                Formato.setVDC80(VDC80.getText().toString());
                Formato.setVDC81(VDC81.getText().toString());
                Formato.setVDC82(VDC82.getText().toString());
                Formato.setVDC83(VDC83.getText().toString());
                Formato.setVDC84(VDC84.getText().toString());
                Formato.setVDC85(VDC85.getText().toString());
                Formato.setVDC86(VDC86.getText().toString());
                Formato.setVDC87(VDC87.getText().toString());
                Formato.setVDC88(VDC88.getText().toString());
                Formato.setVDC89(VDC89.getText().toString());
                Formato.setVDC90(VDC90.getText().toString());
                Formato.setVDC91(VDC91.getText().toString());
                Formato.setVDC92(VDC92.getText().toString());
                Formato.setVDC93(VDC93.getText().toString());
                Formato.setVDC94(VDC94.getText().toString());
                Formato.setVDC95(VDC95.getText().toString());
                Formato.setVDC96(VDC96.getText().toString());
                Formato.setVDC97(VDC97.getText().toString());
                Formato.setVDC98(VDC98.getText().toString());
                Formato.setVDC99(VDC99.getText().toString());
                Formato.setVDC100(VDC100.getText().toString());
                Formato.setVDC101(VDC101.getText().toString());
                Formato.setVDC102(VDC102.getText().toString());
                Formato.setVDC103(VDC103.getText().toString());
                Formato.setVDC104(VDC104.getText().toString());
                Formato.setVDC105(VDC105.getText().toString());
                Formato.setVDC106(VDC106.getText().toString());
                Formato.setVDC107(VDC107.getText().toString());
                Formato.setVDC108(VDC108.getText().toString());
                Formato.setVDC109(VDC109.getText().toString());
                Formato.setVDC110(VDC110.getText().toString());
                Formato.setVDC111(VDC111.getText().toString());
                Formato.setVDC112(VDC112.getText().toString());
                Formato.setVDC113(VDC113.getText().toString());
                Formato.setVDC114(VDC114.getText().toString());
                Formato.setVDC115(VDC115.getText().toString());
                Formato.setVDC116(VDC116.getText().toString());
                Formato.setVDC117(VDC117.getText().toString());
                Formato.setVDC118(VDC118.getText().toString());
                Formato.setVDC119(VDC119.getText().toString());
                Formato.setVDC120(VDC120.getText().toString());
                Formato.setVAC1(VAC1.getText().toString());
                Formato.setVAC2(VAC2.getText().toString());
                Formato.setVAC3(VAC3.getText().toString());
                Formato.setVAC4(VAC4.getText().toString());
                Formato.setVAC5(VAC5.getText().toString());
                Formato.setVAC6(VAC6.getText().toString());
                Formato.setVAC7(VAC7.getText().toString());
                Formato.setVAC8(VAC8.getText().toString());
                Formato.setVAC9(VAC9.getText().toString());
                Formato.setVAC10(VAC10.getText().toString());
                Formato.setVAC11(VAC11.getText().toString());
                Formato.setVAC12(VAC12.getText().toString());
                Formato.setVAC13(VAC13.getText().toString());
                Formato.setVAC14(VAC14.getText().toString());
                Formato.setVAC15(VAC15.getText().toString());
                Formato.setVAC16(VAC16.getText().toString());
                Formato.setVAC17(VAC17.getText().toString());
                Formato.setVAC18(VAC18.getText().toString());
                Formato.setVAC19(VAC19.getText().toString());
                Formato.setVAC20(VAC20.getText().toString());
                Formato.setVAC21(VAC21.getText().toString());
                Formato.setVAC22(VAC22.getText().toString());
                Formato.setVAC23(VAC23.getText().toString());
                Formato.setVAC24(VAC24.getText().toString());
                Formato.setVAC25(VAC25.getText().toString());
                Formato.setVAC26(VAC26.getText().toString());
                Formato.setVAC27(VAC27.getText().toString());
                Formato.setVAC28(VAC28.getText().toString());
                Formato.setVAC29(VAC29.getText().toString());
                Formato.setVAC30(VAC30.getText().toString());
                Formato.setVAC31(VAC31.getText().toString());
                Formato.setVAC32(VAC32.getText().toString());
                Formato.setVAC33(VAC33.getText().toString());
                Formato.setVAC34(VAC34.getText().toString());
                Formato.setVAC35(VAC35.getText().toString());
                Formato.setVAC36(VAC36.getText().toString());
                Formato.setVAC37(VAC37.getText().toString());
                Formato.setVAC38(VAC38.getText().toString());
                Formato.setVAC39(VAC39.getText().toString());
                Formato.setVAC40(VAC40.getText().toString());
                Formato.setVAC41(VAC41.getText().toString());
                Formato.setVAC42(VAC42.getText().toString());
                Formato.setVAC43(VAC43.getText().toString());
                Formato.setVAC44(VAC44.getText().toString());
                Formato.setVAC45(VAC45.getText().toString());
                Formato.setVAC46(VAC46.getText().toString());
                Formato.setVAC47(VAC47.getText().toString());
                Formato.setVAC48(VAC48.getText().toString());
                Formato.setVAC49(VAC49.getText().toString());
                Formato.setVAC50(VAC50.getText().toString());
                Formato.setVAC51(VAC51.getText().toString());
                Formato.setVAC52(VAC52.getText().toString());
                Formato.setVAC53(VAC53.getText().toString());
                Formato.setVAC54(VAC54.getText().toString());
                Formato.setVAC55(VAC55.getText().toString());
                Formato.setVAC56(VAC56.getText().toString());
                Formato.setVAC57(VAC57.getText().toString());
                Formato.setVAC58(VAC58.getText().toString());
                Formato.setVAC59(VAC59.getText().toString());
                Formato.setVAC60(VAC60.getText().toString());
                Formato.setVAC61(VAC61.getText().toString());
                Formato.setVAC62(VAC62.getText().toString());
                Formato.setVAC63(VAC63.getText().toString());
                Formato.setVAC64(VAC64.getText().toString());
                Formato.setVAC65(VAC65.getText().toString());
                Formato.setVAC66(VAC66.getText().toString());
                Formato.setVAC67(VAC67.getText().toString());
                Formato.setVAC68(VAC68.getText().toString());
                Formato.setVAC69(VAC69.getText().toString());
                Formato.setVAC70(VAC70.getText().toString());
                Formato.setVAC71(VAC71.getText().toString());
                Formato.setVAC72(VAC72.getText().toString());
                Formato.setVAC73(VAC73.getText().toString());
                Formato.setVAC74(VAC74.getText().toString());
                Formato.setVAC75(VAC75.getText().toString());
                Formato.setVAC76(VAC76.getText().toString());
                Formato.setVAC77(VAC77.getText().toString());
                Formato.setVAC78(VAC78.getText().toString());
                Formato.setVAC79(VAC79.getText().toString());
                Formato.setVAC80(VAC80.getText().toString());
                Formato.setVAC81(VAC81.getText().toString());
                Formato.setVAC82(VAC82.getText().toString());
                Formato.setVAC83(VAC83.getText().toString());
                Formato.setVAC84(VAC84.getText().toString());
                Formato.setVAC85(VAC85.getText().toString());
                Formato.setVAC86(VAC86.getText().toString());
                Formato.setVAC87(VAC87.getText().toString());
                Formato.setVAC88(VAC88.getText().toString());
                Formato.setVAC89(VAC89.getText().toString());
                Formato.setVAC90(VAC90.getText().toString());
                Formato.setVAC91(VAC91.getText().toString());
                Formato.setVAC92(VAC92.getText().toString());
                Formato.setVAC93(VAC93.getText().toString());
                Formato.setVAC94(VAC94.getText().toString());
                Formato.setVAC95(VAC95.getText().toString());
                Formato.setVAC96(VAC96.getText().toString());
                Formato.setVAC97(VAC97.getText().toString());
                Formato.setVAC98(VAC98.getText().toString());
                Formato.setVAC99(VAC99.getText().toString());
                Formato.setVAC100(VAC100.getText().toString());
                Formato.setVAC101(VAC101.getText().toString());
                Formato.setVAC102(VAC102.getText().toString());
                Formato.setVAC103(VAC103.getText().toString());
                Formato.setVAC104(VAC104.getText().toString());
                Formato.setVAC105(VAC105.getText().toString());
                Formato.setVAC106(VAC106.getText().toString());
                Formato.setVAC107(VAC107.getText().toString());
                Formato.setVAC108(VAC108.getText().toString());
                Formato.setVAC109(VAC109.getText().toString());
                Formato.setVAC110(VAC110.getText().toString());
                Formato.setVAC111(VAC111.getText().toString());
                Formato.setVAC112(VAC112.getText().toString());
                Formato.setVAC113(VAC113.getText().toString());
                Formato.setVAC114(VAC114.getText().toString());
                Formato.setVAC115(VAC115.getText().toString());
                Formato.setVAC116(VAC116.getText().toString());
                Formato.setVAC117(VAC117.getText().toString());
                Formato.setVAC118(VAC118.getText().toString());
                Formato.setVAC119(VAC119.getText().toString());
                Formato.setVAC120(VAC120.getText().toString());


                //endregion

                D_B.guardarBatrias(Formato,id_formato);

                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                BateriasMenuFragment myfargment = new BateriasMenuFragment();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });

        return v;
    }

}
