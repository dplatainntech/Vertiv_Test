package com.maju.desarrollo.testfcs.Formatos.PreOrden;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Adapter.adapterCliente;
import com.maju.desarrollo.testfcs.Adapter.adapterSR;
import com.maju.desarrollo.testfcs.Adapter.adapterSitio;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.Model.searchCliente;
import com.maju.desarrollo.testfcs.SQLite.Model.searchSitio;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatCliente;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PO_GeneralesFragment extends Fragment{

    TextView task,cliente,sitio,direccion,fecha,sr;
    OperacionesDB D_B;
    String id_PreOrden;
    PreOrden preOrden;
    EditText referencia,usuaioFinal,liderG,filtroT,proyecto,filtroT1,filtroT3;
    ListView listafiltros,listafiltros1,listafiltros3;

    LinearLayout areaLista,areaLista1,areaLista3;
    List<searchSitio> listaSitios = null;
    adapterSitio miAdaptadorSitio;
    boolean editar = false;

    public PO_GeneralesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //region Relacion Interfaz
        View v = inflater.inflate(R.layout.fragment_po__generales, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        fecha = (TextView)v.findViewById(R.id.po_tv_fecha);
        id_PreOrden= getArguments().getString("key_idPreOrden");

        preOrden = D_B.obtenerPreOrden_id(id_PreOrden);

        sr = (TextView)v.findViewById(R.id.Gral_SR);
        task = (TextView)v.findViewById(R.id.Gral_task);
        cliente = (EditText)v.findViewById(R.id.Gral_cliente);
        sitio = (EditText)v.findViewById(R.id.Gral_sitio);
        direccion = (EditText)v.findViewById(R.id.Gral_direccion);
        referencia = (EditText)v.findViewById(R.id.Gral_referencia);
        usuaioFinal = (EditText)v.findViewById(R.id.Gral_usuarioF);
        liderG = (EditText)v.findViewById(R.id.Gral_liderG);
        listafiltros = (ListView) v.findViewById(R.id.listaAutoC);
        listafiltros1 = (ListView) v.findViewById(R.id.listaAuto1);
        listafiltros3 = (ListView) v.findViewById(R.id.listaAuto3);
        ImageView cerrarlista = (ImageView)v.findViewById(R.id.cerrarlista);
        ImageView cerrarlista1 = (ImageView)v.findViewById(R.id.cerrarlista1);
        ImageView cerrarlista3 = (ImageView)v.findViewById(R.id.cerrarlista3);
        filtroT = (EditText)v.findViewById(R.id.filtro);
        filtroT1 = (EditText)v.findViewById(R.id.filtro1);
        filtroT3 = (EditText)v.findViewById(R.id.filtro3);
        areaLista = (LinearLayout)v.findViewById(R.id.autocompliteLY);
        areaLista1 = (LinearLayout)v.findViewById(R.id.autocompliteLY1);
        areaLista3 = (LinearLayout)v.findViewById(R.id.autocompliteLY3);
        proyecto = (EditText)v.findViewById(R.id.Gral_proyecto);

        areaLista.setVisibility(View.GONE);
        areaLista1.setVisibility(View.GONE);
        areaLista3.setVisibility(View.GONE);

        cliente.setEnabled(false);
        sitio.setEnabled(false);
        direccion.setEnabled(false);


        proyecto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(sr.getText().length()>0) {
                    sr.setText("");
                    task.setText("");
                    cliente.setText("");
                    sitio.setText("");
                    direccion.setText("");
                }
                if(count>0){
                    cliente.setEnabled(true);
                    sitio.setEnabled(true);
                    direccion.setEnabled(true);
                }else{
                    cliente.setEnabled(false);
                    sitio.setEnabled(false);
                    direccion.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //region FILTROS SR
        final List<CatAsignaCliente> ListaTask_SR = D_B.obtenerTask();
        listafiltros1.setTextFilterEnabled(true);
        final adapterSR miAdaptador1= new adapterSR(getActivity().getApplicationContext(),ListaTask_SR);

        listafiltros1.setAdapter(miAdaptador1);

        listafiltros1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatAsignaCliente item = miAdaptador1.obtenerItem(position);
                // listaBDClientes.get(position);

                sr.setText(item.getSr_Nbr());
                task.setText(item.getTask_Nbr());
                cliente.setText(item.getInstall_Party_Name());
                sitio.setText(item.getSite());
                direccion.setText(item.getAdress());


                areaLista1.setVisibility(View.GONE);
            }
        });
        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().toString().trim().length()==0){
                    filtroT1.requestFocus();
                    areaLista1.setVisibility(View.VISIBLE);

                }else{}

            }
        });

        cerrarlista1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaLista1.setVisibility(View.GONE);
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

        //region FILTROS CLIENTE
       final List<searchCliente> listaBDClientes = new ArrayList<>();//D_B.obtenerClientesLista();


        listafiltros.setTextFilterEnabled(true);
        final adapterCliente miAdaptador= new adapterCliente(getActivity().getApplicationContext(),listaBDClientes);

        listafiltros.setAdapter(miAdaptador);

        listafiltros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchCliente item = miAdaptador.obtenerItem(position);
                       // listaBDClientes.get(position);
                /*if(item.getNombreCliente().equals("BESTEL")){
                    cliente.setText("BESTEL");
                    sitio.setText("");
                    direccion.setText("");
                }
                else{
                    cliente.setText(item.getNombreCliente());
                    sitio.setText(item.getNombreSitio());
                    direccion.setText(item.getDireccion());
                }*/
                cliente.setText(item.getCLiente());
                sitio.setText("");
                direccion.setText("");
                areaLista.setVisibility(View.GONE);

                listaSitios = new ArrayList<>();//D_B.obtenerSitiosLista(item.getBase(),item.getCLiente());
                listafiltros3.setTextFilterEnabled(true);
                miAdaptadorSitio= new adapterSitio(getActivity().getApplicationContext(),listaSitios);

                listafiltros3.setAdapter(miAdaptadorSitio);
                areaLista3.setVisibility(View.VISIBLE);
            }
        });
       cliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().length()>1) {
                    areaLista.setVisibility(View.VISIBLE);
                    filtroT.requestFocus();
                }
            }
        });

        cerrarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaLista.setVisibility(View.GONE);
            }
        });

        filtroT.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //PedidosFragment.this.miAdaptador.getView();
                miAdaptador.getFilter().filter(s);
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

        //region FILTROS SITIO



        listafiltros3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                searchSitio item = miAdaptadorSitio.obtenerItem(position);
                // listaBDClientes.get(position);
                sitio.setText(item.getSitio());
                direccion.setText(item.getDireccion());
                areaLista3.setVisibility(View.GONE);
            }
        });
        sitio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().length()>1) {
                    if (cliente.getText().toString().trim().length() > 5) {
                        areaLista3.setVisibility(View.VISIBLE);
                    } else {
                    }
                }
            }
        });

        cerrarlista3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaLista3.setVisibility(View.GONE);
            }
        });

        filtroT3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //PedidosFragment.this.miAdaptador.getView();
                miAdaptadorSitio.getFilter().filter(s);
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


        List<CatCliente> l1 = D_B.obtenerClientes();

        proyecto.setText(preOrden.getGEN_PROYECTO());
        sr.setText(preOrden.getGEN_SR());
        task.setText(preOrden.getGEN_TASK());
        cliente.setText(preOrden.getGEN_CLIENTE());
        sitio.setText(preOrden.getGEN_SITIO());
        direccion.setText(preOrden.getGEN_DIRECCION());
        referencia.setText(preOrden.getGEN_REFERENCIA());
        usuaioFinal.setText(preOrden.getGEN_USUARIO_FINAL());
        liderG.setText(preOrden.getGEN_LIDER_GRUPO());
        fecha.setText(preOrden.getGEN_FECHA());

        try{if(preOrden.getGEN_SR().length()>1){
            proyecto.setEnabled(false);
        }}catch (Exception e){}


        //endregion

        Button cancelar = (Button)v.findViewById(R.id.EHM_btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "MenuPreOrden");
                args.putString("valorPaso", preOrden.getFOLIO());

                CancelasDialogFragment dialog= new CancelasDialogFragment();
                dialog.setCancelable(true);
                dialog.setTargetFragment(PO_GeneralesFragment.this,1);
                dialog.setArguments(args);
                dialog.show(getFragmentManager(),"tConfirmacionFragment");
                //endregion
            }
        });
        Button guardar = (Button)v.findViewById(R.id.buttonGeberalesGuardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getActivity().onBackPressed();
                preOrden.setGEN_PROYECTO(proyecto.getText().toString());
                preOrden.setGEN_SR(sr.getText().toString());
                preOrden.setGEN_TASK(task.getText().toString());
                preOrden.setGEN_CLIENTE(cliente.getText().toString());
                preOrden.setGEN_SITIO(sitio.getText().toString());
                preOrden.setGEN_DIRECCION(direccion.getText().toString());
                preOrden.setGEN_REFERENCIA(referencia.getText().toString());
                preOrden.setGEN_USUARIO_FINAL(usuaioFinal.getText().toString());
                preOrden.setGEN_LIDER_GRUPO(liderG.getText().toString());
                preOrden.setGEN_FECHA(fecha.getText().toString());

                DatGeneralesF item = new DatGeneralesF();
                item.setID_FORMATO(id_PreOrden);
                item.setFECHA_MODIFICACION(new Date().toString());
                item.setCLIENTE(cliente.getText().toString());
                item.setSR(sr.getText().toString());
                item.setTASK(task.getText().toString());
                //item.setITEM;
                //item.setSERIE;
                D_B.actualizarInfoformato(item);

                D_B.guardarPreOrden(preOrden);
                Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Intent siguiente = new Intent(getActivity(), MainActivity.class);
                siguiente.putExtra("OtraPantalla", "MenuPreOrden");
                siguiente.putExtra("valorPaso", preOrden.getFOLIO());
                startActivity(siguiente);

            }
        });
        //buttonGeberalesGuardar


        ImageView getFecha = (ImageView)v.findViewById(R.id.po_Button_fecha);
        getFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog1();
            }
        });

        return v;
    }


    private void showDatePickerDialog1() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fecha.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }



}
