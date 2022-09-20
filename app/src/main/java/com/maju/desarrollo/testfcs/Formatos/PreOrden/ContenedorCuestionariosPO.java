package com.maju.desarrollo.testfcs.Formatos.PreOrden;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.maju.desarrollo.testfcs.R;

public class ContenedorCuestionariosPO extends AppCompatActivity {
    String pantallaView;
    Fragment Cuestionario;
    String folioVertivPregorden,queFirma;
    LinearLayout cabeceroPO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c0ntenedor_cuestionarios_po);

        getSupportActionBar().hide();
        cabeceroPO = (LinearLayout)findViewById(R.id.cabeceroPretabajo);

        Bundle pantalla = this.getIntent().getExtras();
        pantallaView = pantalla.getString("FormilarioPO");
        folioVertivPregorden = pantalla.getString("key_idPreOrden");
        queFirma = pantalla.getString("Firma_aGuardar");

        switch (pantallaView){
            case "Generales":
                Cuestionario = new PO_GeneralesFragment();
            break;
            case "Actividades":
                Cuestionario = new POActividamesFragment();
                break;
            case "Herramientas":
                Cuestionario = new POHerramientasFragment();
                break;
            case "Riesgos":
                Cuestionario = new PORiesgosFragment();
                break;
            case "Prevencion":
                Cuestionario = new POPrevencionFragment();
                break;
            case "EPP":
                Cuestionario = new POeppFragment();
                break;
            case "Emergencia":
                Cuestionario = new POEmergenciasFragment();
                break;
            case "Inspeccion":
                Cuestionario = new POInspeccionFragment();
                break;
            case "Firmas":
                Cuestionario = new POFirmasFragment();
                break;
            case "AreaFirmas":
                Cuestionario = new PO_FirmasLienzo();
                break;

        }
        Bundle args = new Bundle();
        args.putString("key_idPreOrden", folioVertivPregorden);

        if(pantallaView.equals("AreaFirmas")){
            cabeceroPO.setVisibility(View.GONE);
            args.putString("Firma_aGuardar", queFirma);
        }

            Cuestionario.setArguments(args);


            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_placeholder_PO, Cuestionario);

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();



    }

    @Override
    public void onBackPressed (){
        //super.onBackPressed();
        //super.onBackPressed();
    }

    protected void ocultarCabecera(){
        cabeceroPO.setVisibility(View.GONE);
    }
}
