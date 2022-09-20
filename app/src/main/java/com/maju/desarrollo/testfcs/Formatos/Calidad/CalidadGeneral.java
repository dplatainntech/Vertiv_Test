package com.maju.desarrollo.testfcs.Formatos.Calidad;


import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.maju.desarrollo.testfcs.Adapter.adapterFoliosPT;
import com.maju.desarrollo.testfcs.Adapter.adapterSR;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CalidadGeneral extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    EncuestaCalidadServicio Formato;
    LinearLayout areaLista,areaListaSr;
    ListView listafiltros1,listafiltros2;
    EditText filtroT1,filtroT2;
    TextView fecha1;
    InternetandVPN validaciones = new InternetandVPN();
    adapterSR miAdaptador2;

    public CalidadGeneral() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calidad_general, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();

        //id_formato= getArguments().getString("key_idFormato");
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerCalidad(id_formato);


        final EditText contacto = (EditText)v.findViewById(R.id.contacto);
        final EditText contrato = (EditText)v.findViewById(R.id.contrato);
        final TextView cliente = (TextView)v.findViewById(R.id.cliente);
        final TextView sitio = (TextView)v.findViewById(R.id.sitio);
        final TextView direccion = (TextView)v.findViewById(R.id.direccion);
        final TextView cfinal = (TextView)v.findViewById(R.id.cfinal);
        final EditText telefono= (EditText)v.findViewById(R.id.telefono);
        final EditText mail= (EditText)v.findViewById(R.id.mail);
        final TextView proyecto = (TextView)v.findViewById(R.id.proyecto);
        final EditText referencia= (EditText)v.findViewById(R.id.referencia);
        final EditText tiposervOtro= (EditText)v.findViewById(R.id.tiposervOtro);
        final EditText modelo1= (EditText)v.findViewById(R.id.modelo1);
        final EditText modelo2= (EditText)v.findViewById(R.id.modelo2);
        final EditText serie1= (EditText)v.findViewById(R.id.serie1);
        final EditText serie2= (EditText)v.findViewById(R.id.serie2);
        final Spinner tipoServ = (Spinner) v.findViewById(R.id.tipoServ);
        final Spinner tequipo = (Spinner) v.findViewById(R.id.tequipo);
        fecha1= (TextView)v.findViewById(R.id.fecha1);

        final TextView sr = (TextView)v.findViewById(R.id.sr);
        final TextView task = (TextView)v.findViewById(R.id.task);
        areaListaSr = (LinearLayout)v.findViewById(R.id.autocompliteLY2);

        //modelo1.setEnabled(false);
        //serie1.setEnabled(false);
        mail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(validaciones.emailOk(s.toString())){
                    mail.setTextColor(Color.parseColor("#000000"));
                }else{
                    mail.setTextColor(Color.parseColor("#FF0000"));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
               /* if(s.length()>0)
                    clearBox.setVisibility(View.VISIBLE);
                else
                    clearBox.setVisibility(View.GONE); */
            }
        });

        ImageView Button_fecha1= (ImageView)v.findViewById(R.id.Button_fecha1);

        Button_fecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.calidadTiposervicio, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipoServ.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.calidadTipoEquipo, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tequipo.setAdapter(adapter2);


        filtroT2 = (EditText)v.findViewById(R.id.filtro2);
        ImageView cerrarlista2 = (ImageView)v.findViewById(R.id.cerrarlista2);
        listafiltros2 = (ListView) v.findViewById(R.id.listaAuto2);
        areaListaSr.setVisibility(View.GONE);


        //region FILTROS FOLIOS PT
        filtroT1 = (EditText)v.findViewById(R.id.filtro1);
        ImageView cerrarlista1 = (ImageView)v.findViewById(R.id.cerrarlista1);
        listafiltros1 = (ListView) v.findViewById(R.id.listaAuto1);
        areaLista = (LinearLayout)v.findViewById(R.id.autocompliteLY);
        final TextView FoliosTV = (TextView)v.findViewById(R.id.folioPT);
        areaLista.setVisibility(View.GONE);
        //final List<CatFolios> ListaTask_SR = D_B.obtenerFoliosPT();
        List<CatFolios> ListaTask_SR = new ArrayList<>();
        String bsccliente = "";
        try{bsccliente = Formato.getIdCliente();}catch (Exception e){}
        if(bsccliente!=null){ListaTask_SR=D_B.obtenerFoliosPTCliente(bsccliente);}
        else {
            ListaTask_SR =D_B.obtenerFoliosPT();
        }

        listafiltros1.setTextFilterEnabled(true);
        final adapterFoliosPT miAdaptador1= new adapterFoliosPT(getActivity().getApplicationContext(),ListaTask_SR);

        listafiltros1.setAdapter(miAdaptador1);

        listafiltros1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatFolios item = miAdaptador1.obtenerItem(position);
                // listaBDClientes.get(position);

                FoliosTV.setText(item.getFolioPreTrabajo());
                cliente.setText(item.getCliente());
                proyecto.setText(item.getGEN_PROYECTO());
                direccion.setText(item.getDireccionSitio());
                sitio.setText(item.getNombreSitio());

                /*try{if(item.getGEN_PROYECTO().length()>1){
                    modelo.setEnabled(true);
                    nserie.setEnabled(true);
                }else{
                    modelo.setEnabled(false);
                    nserie.setEnabled(false);
                }}catch (Exception e){
                    modelo.setEnabled(false);
                    nserie.setEnabled(false);
                }*/


               try {
                   CatAsignaCliente it = D_B.obtenerTaskId(item.getGEN_SR());
                   contrato.setText(it.getContract_Nbr());
               }catch (Exception e){}
                areaLista.setVisibility(View.GONE);
            }
        });
        FoliosTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaLista.setVisibility(View.VISIBLE);

            }
        });

        cerrarlista1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaLista.setVisibility(View.GONE);
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

        //region FILTROS SR


        sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().toString().trim().length()==0){
                    final List<CatAsignaCliente> ListaTask_SL = D_B.obtenerTaskClieteL(cliente.getText().toString());
                    listafiltros2.setTextFilterEnabled(true);
                    miAdaptador2= new adapterSR(getActivity().getApplicationContext(),ListaTask_SL);
                    listafiltros2.setAdapter(miAdaptador2);

                    filtroT2.requestFocus();
                    areaListaSr.setVisibility(View.VISIBLE);
                }else{}
            }
        });


        listafiltros2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatAsignaCliente item = miAdaptador2.obtenerItem(position);
                // listaBDClientes.get(position);

                sr.setText(item.getSr_Nbr());
                task.setText(item.getTask_Nbr());
                direccion.setText(item.getAdress());
                modelo1.setText(item.getItem_Number());
                serie1.setText(item.getSerial_Number());
                areaListaSr.setVisibility(View.GONE);
            }
        });
        /*sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().toString().trim().length()==0){
                    filtroT1.requestFocus();
                    areaListaSr.setVisibility(View.VISIBLE);

                }else{}

            }
        });
*/
        cerrarlista2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                areaListaSr.setVisibility(View.GONE);
            }
        });

        filtroT2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //PedidosFragment.this.miAdaptador.getView();
                miAdaptador2.getFilter().filter(s);
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

        FoliosTV.setText(Formato.getFolioPreTrabajo());
      /*  try{if(Formato.getProyecto().length()>1){
            modelo1.setEnabled(true);
            serie1.setEnabled(true);
        }else{
            modelo1.setEnabled(false);
            serie1.setEnabled(false);
        }}catch (Exception e){
            modelo1.setEnabled(false);
            serie1.setEnabled(false);
        }*/
        contacto.setText(Formato.getContactoCliente());
        cliente.setText(Formato.getIdCliente());
        sitio.setText(Formato.getNombreSitio());
        direccion.setText(Formato.getDireccionSitio());
        cfinal.setText(Formato.getClienteFinal());
        telefono.setText(Formato.getTelefono());
        mail.setText(Formato.getEMail());
        proyecto.setText(Formato.getProyecto());
        referencia.setText(Formato.getReferencia());
        contrato.setText(Formato.getContrato());
        tiposervOtro.setText(Formato.getTiposervicioOtro());
        modelo1.setText(Formato.getModeloEquipo());
        modelo2.setText(Formato.getModelo2());
        serie1.setText(Formato.getNoSerie());
        serie2.setText(Formato.getSerie2());
        fecha1.setText(Formato.getFecha());
        try{
            task.setText(Formato.getTASK().toString());
            if(Formato.getTASK().toString().length()>0){
                sr.setText(Formato.getSRProyecto().toString());
            }

        }catch (Exception e){}

        try{tequipo.setSelection(adapter2.getPosition(Formato.getTipoEquipo()));}catch (Exception e){}
        try{tipoServ.setSelection(adapter.getPosition(Formato.getTipoServicio()));}catch (Exception e){}



        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "Calidad");
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
                //getActivity().onBackPressed();
                //preOrden.setACTV_DESCRIPCION_ACTIVIDADES(descripcion.getText().toString());

                //D_B.guardarPower1(dcPower);
                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();
                Formato.setFolioPreTrabajo(FoliosTV.getText().toString());
                Formato.setContactoCliente(contacto.getText().toString());
                Formato.setIdCliente(cliente.getText().toString());
                Formato.setNombreSitio(sitio.getText().toString());
                Formato.setDireccionSitio(direccion.getText().toString());
                Formato.setClienteFinal(cfinal.getText().toString());
                Formato.setTelefono(telefono.getText().toString());
                Formato.setEMail(mail.getText().toString());
                Formato.setContrato(contrato.getText().toString());
                Formato.setTipoEquipo(tequipo.getSelectedItem().toString());
                Formato.setTipoServicio(tipoServ.getSelectedItem().toString());
                Formato.setModeloEquipo(modelo1.getText().toString());
                Formato.setTiposervicioOtro(tiposervOtro.getText().toString());
                Formato.setModelo2(modelo2.getText().toString());
                Formato.setNoSerie(serie1.getText().toString());
                Formato.setSerie2(serie2.getText().toString());
                Formato.setFecha(fecha1.getText().toString());
                Formato.setProyecto(proyecto.getText().toString());
                if(task.getText().equals("")){
                    Formato.setSRProyecto(proyecto.getText().toString());
                }else{
                    Formato.setSRProyecto(sr.getText().toString());
                    Formato.setTASK(task.getText().toString());
                }

                Formato.setReferencia(referencia.getText().toString());

                D_B.guardarCalidad(Formato);

                DatGeneralesF item2 = new DatGeneralesF();
                item2.setID_FORMATO(id_formato);
                item2.setFOLIO_PRETRABAJO(FoliosTV.getText().toString());
                item2.setFECHA_MODIFICACION(new Date().toString());
                item2.setCLIENTE(cliente.getText().toString());
                item2.setSR(sr.getText().toString());
                item2.setTASK(task.getText().toString());
                D_B.actualizarInfoformato(item2);


                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                CalidadMenu myfargment = new CalidadMenu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

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
                final String selectedDate = year  + "-" +mes + "-" + dia  ;
                fecha1.setText(selectedDate);
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }
}
