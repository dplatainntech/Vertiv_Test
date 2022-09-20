package com.maju.desarrollo.testfcs.Formatos.IN1;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;

import com.maju.desarrollo.testfcs.Adapter.adapterDireccionesBestel;
import com.maju.desarrollo.testfcs.Adapter.adapterFoliosPT;
import com.maju.desarrollo.testfcs.Adapter.adapterSR;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Class.spinnerDosCampos;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class IN1Generales extends Fragment {
    OperacionesDB D_B;
    String id_Formato;
    String idRegion,idEstado,TipoB;
    UsuarioD usuario;
    LinearLayout areaLista,areaListaSr;
    ListView listafiltros1,listafiltros2;
    EditText filtroT1,filtroT2;
    Spinner spinner1;
    TextView fecha1, fecha2;
    Bestel1 item;
    adapterDireccionesBestel adapterRegiones,adapterEstado,adapterTipoSitio;
    String Camp_region,CampEstado, Camp_tipoS, Camp_NombreS;
    adapterSR miAdaptador2;
    InternetandVPN validaciones = new InternetandVPN();
    //adapterDireccionesBestel

    public IN1Generales() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_in1_generales, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_Formato= getArguments().getString("key_idFormato");
        usuario = D_B.obtenerUsuario();

        item = D_B.obtenerBestel1(id_Formato);

        final Spinner region = (Spinner)v.findViewById(R.id.region);
        final Spinner estado = (Spinner)v.findViewById(R.id.estado);
        final Spinner tipositio = (Spinner)v.findViewById(R.id.tipositio);
        final Spinner nombresitio = (Spinner)v.findViewById(R.id.nombresitio);
        final TextView direccion = (TextView)v.findViewById(R.id.direccion);
        final TextView cliente = (TextView)v.findViewById(R.id.cliente);
        final TextView proyecto = (TextView)v.findViewById(R.id.proyecto);
        final TextView sr = (TextView)v.findViewById(R.id.sr);
        final TextView task = (TextView)v.findViewById(R.id.task);
        spinner1 = (Spinner) v.findViewById(R.id.tipoServ);
        final TextView frecuencia = (TextView) v.findViewById(R.id.freciencia);
        final EditText contacto = (EditText)v.findViewById(R.id.contacto);
        final EditText telefono= (EditText)v.findViewById(R.id.telefono);
        final EditText mail= (EditText)v.findViewById(R.id.mail);
        final EditText referencia= (EditText)v.findViewById(R.id.referencia);
        final EditText noTag= (EditText)v.findViewById(R.id.noTag);
        final EditText modelo= (EditText)v.findViewById(R.id.modelo);
        final TextView textoRegion = (TextView)v.findViewById(R.id.textoRegion);
        final TextView textoEstado = (TextView)v.findViewById(R.id.textoEstado);
        final TextView textotipositio = (TextView)v.findViewById(R.id.textotipositio);
        final TextView textonombresitio = (TextView)v.findViewById(R.id.textonombresitio);
        areaListaSr = (LinearLayout)v.findViewById(R.id.autocompliteLY2);

        fecha1= (TextView)v.findViewById(R.id.fecha1);
        fecha2= (TextView)v.findViewById(R.id.techa2);
        ImageView Button_fecha1= (ImageView)v.findViewById(R.id.Button_fecha1);
        ImageView Button_fecha2= (ImageView)v.findViewById(R.id.Button_fecha2);
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


        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.TipoServicioBN1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter);

        //String[] test = new Array[2];

        filtroT1 = (EditText)v.findViewById(R.id.filtro1);
        ImageView cerrarlista1 = (ImageView)v.findViewById(R.id.cerrarlista1);
        listafiltros1 = (ListView) v.findViewById(R.id.listaAuto1);
        areaLista = (LinearLayout)v.findViewById(R.id.autocompliteLY);
        final TextView FoliosTV = (TextView)v.findViewById(R.id.folioPT);
        areaLista.setVisibility(View.GONE);

        filtroT2 = (EditText)v.findViewById(R.id.filtro2);
        ImageView cerrarlista2 = (ImageView)v.findViewById(R.id.cerrarlista2);
        listafiltros2 = (ListView) v.findViewById(R.id.listaAuto2);
        areaListaSr.setVisibility(View.GONE);

        //region FILTROS FOLIOS PT
        List<CatFolios> ListaTask_SR = new ArrayList<>();
        String bsccliente = " ";
        try{bsccliente = item.getCliente();}catch (Exception e){}
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
                //sr.setText(item.getGEN_SR());
                //task.setText(item.getGEN_TASK());

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


        /*sr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(proyecto.getText().toString().trim().length()==0){
                    filtroT2.requestFocus();
                    areaListaSr.setVisibility(View.VISIBLE);
                }else{}
            }
        });
*/


        listafiltros2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CatAsignaCliente item = miAdaptador2.obtenerItem(position);
                // listaBDClientes.get(position);

                sr.setText(item.getSr_Nbr());
                task.setText(item.getTask_Nbr());
                areaListaSr.setVisibility(View.GONE);
            }
        });
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

        spinner1.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                       String text = spinner1.getSelectedItem().toString();
                        if(text.equals("Orden de Trabajo (24)")){
                            frecuencia.setText("Evento único");
                        }
                        if(text.equals("Incidencia (100)")){
                            frecuencia.setText("Evento único");
                        }
                        if(spn.getItemAtPosition(posicion).toString().equals("Mantenimiento Preventivo (20)")){
                            if(textotipositio.getText().equals("Tipo A")){frecuencia.setText("24-7");}
                            if(textotipositio.getText().equals("Tipo B")){frecuencia.setText("Mensual");}
                            if(textotipositio.getText().toString().equals("Tipo C")){frecuencia.setText("Bimestral");}
                            if(textotipositio.getText().toString().equals("Tipo D")){frecuencia.setText("Semestral");}

                           // frecuencia.setText("tres");
                        }
                        /*
                         Toast.makeText(spn.getContext(), "Has seleccionado " +
                                        text,
                                Toast.LENGTH_LONG).show();
                         */

                    }
                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });


        final List<spinnerDosCampos> items =  D_B.obtenerRegiones();
        //final List<spinnerDosCampos> itemsEstados = null;

        adapterRegiones =new adapterDireccionesBestel(getContext(),items);
        region.setAdapter(adapterRegiones);
        region.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                idRegion = ((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo1();
                //Camp_region = ((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2();

                if(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2().length()>3) {
                    textoRegion.setText(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2());
                }

                List<spinnerDosCampos> itemsEstados =  D_B.obtenerEstados(idRegion);
                adapterEstado = new adapterDireccionesBestel(getContext(),itemsEstados);
                estado.setAdapter(adapterEstado);
                //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //nothing
            }
        });

        estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                idEstado = ((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo1();
                //CampEstado = ((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2();
                if(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2().length()>3) {
                    textoEstado.setText(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2());
                }

                List<spinnerDosCampos> itemsEstados =  D_B.obtenerTipo(idRegion,idEstado);
                adapterTipoSitio = new adapterDireccionesBestel(getContext(),itemsEstados);
                tipositio.setAdapter(adapterTipoSitio);
                //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //nothing
            }
        });

        tipositio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                TipoB = ((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2();
                //Camp_tipoS=((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2();
                if(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2().length()>3) {
                    textotipositio.setText(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2());
                }
                List<spinnerDosCampos> itemsEstados =  D_B.obtenerSitiosBestel(idRegion,idEstado,TipoB);
                nombresitio.setAdapter(new adapterDireccionesBestel(getContext(),itemsEstados));
                //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();

                //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //nothing
            }
        });

        nombresitio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id)
            {
                Camp_NombreS=((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2();
                if(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2().length()>3) {
                    textonombresitio.setText(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo2());
                    direccion.setText(((spinnerDosCampos) adapterView.getItemAtPosition(position)).getCampo3());
                }
               //Toast.makeText(adapterView.getContext(), ((SocialNetwork) adapterView.getItemAtPosition(position)).getNombre(), Toast.LENGTH_SHORT).show();
              }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView)
            {
                //nothing
            }
        });



        textoRegion.setText(item.getRegion());
        textoEstado.setText(item.getEstado());
        textotipositio.setText(item.getTipoSitio());
        textonombresitio.setText(item.getNombreSitio());
        //region Mostrar datos Despues de cargar las listas de Datos
        try{FoliosTV.setText(item.getFolioPreTrabajo().toString());}catch(Exception e){}
        try{cliente.setText(item.getCliente().toString());}catch(Exception e){}
        //region.setSelection(adapterRegiones.getPosition(item.getIdRegion().toString());
        //estado.setSelection(adapter.getPosition(item.getIdEstado().toString());
        //tipositio.setSelection(adapter.getPosition(item.getTipoSitio().toString());
        //nombresitio.setSelection(adapter.getPosition(item.getNombreSitio().toString());
        try{direccion.setText(item.getDireccionSitio().toString());}catch(Exception e){}
        try{contacto.setText(item.getContactoCliente().toString());}catch(Exception e){}
        try{telefono.setText(item.getTelefono().toString());}catch(Exception e){}
        try{mail.setText(item.getEmail().toString());}catch(Exception e){}
        try{sr.setText(item.getSRProyecto().toString());}catch(Exception e){}
        try{proyecto.setText(item.getProyecto().toString());}catch(Exception e){}
        try{referencia.setText(item.getReferencia().toString());}catch(Exception e){}
        try{task.setText(item.getTask().toString());}catch(Exception e){}
        try{spinner1.setSelection(adapter.getPosition(item.getTipoServicio().toString()));}catch(Exception e){}
        try{frecuencia.setText(item.getFrecuencia().toString());}catch(Exception e){}
        try{noTag.setText(item.getNoTag().toString());}catch(Exception e){}
        try{modelo.setText(item.getModelo().toString());}catch(Exception e){}
        try{fecha1.setText(item.getFechaInicio().toString());}catch(Exception e){}
        try{fecha2.setText(item.getFechaTermino().toString());}catch(Exception e){}


        //endregion



        //region bbotones

        Button_fecha1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog1();
            }
        });
        Button_fecha2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog2();
            }
        });

        Button cancelar = (Button)v.findViewById(R.id.btn_Cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //region
                //getActivity().onBackPressed();
                Bundle args = new Bundle();
                args.putString("OtraPantalla", "IN1");
                args.putString("valorPaso", id_Formato);

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
                //region preparar guardado

                item.setIdUsuario(usuario.getID_USER());

                //item.setIdBestelNivel1();
                item.setFolioPreTrabajo(FoliosTV.getText().toString());
                item.setCliente(cliente.getText().toString());
                item.setRegion(textoRegion.getText().toString()); //region.getSelectedItem().toString()
                item.setEstado(textoEstado.getText().toString()); //estado.getSelectedItem().toString()
                item.setTipoSitio(textotipositio.getText().toString()); //tipositio.getSelectedItem().toString()
                item.setNombreSitio(textonombresitio.getText().toString()); //nombresitio.getSelectedItem().toString()
                item.setDireccionSitio(direccion.getText().toString());
                item.setContactoCliente(contacto.getText().toString());
                item.setTelefono(telefono.getText().toString());
                item.setEmail(mail.getText().toString());
                item.setSRProyecto(sr.getText().toString());
                item.setProyecto(proyecto.getText().toString());
                item.setReferencia(referencia.getText().toString());
                item.setTask(task.getText().toString());
                item.setTipoServicio(spinner1.getSelectedItem().toString());
                item.setFrecuencia(frecuencia.getText().toString());
                item.setNoTag(noTag.getText().toString());
                item.setModelo(modelo.getText().toString());
                item.setFechaInicio(fecha1.getText().toString());
                item.setFechaTermino(fecha2.getText().toString());

                //endregion
                D_B.guardarBestel1(item);

                DatGeneralesF item2 = new DatGeneralesF();
                item2.setID_FORMATO(id_Formato);
                item2.setFOLIO_PRETRABAJO(FoliosTV.getText().toString());
                item2.setFECHA_MODIFICACION(new Date().toString());
                item2.setCLIENTE(cliente.getText().toString());
                item2.setSR(sr.getText().toString());
                item2.setTASK(task.getText().toString());
                //item.setITEM;
                //item.setSERIE;
                D_B.actualizarInfoformato(item2);


                //Toast.makeText(getContext(), "Datos Guardados", Toast.LENGTH_LONG).show();

                Bundle args = new Bundle();
                args.putString("key_idFormato",id_Formato);
                IN1Menu myfargment = new IN1Menu();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();

            }
        });
        //endregiones
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
                fecha1.setText(selectedDate);
                obtenerHora1();
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }

    private void showDatePickerDialog2() {
        DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // +1 because january is zero

                String dia,mes;
                if(day<10) {dia="0"+day;}else{dia=""+day;}
                if((month+1)<10) {mes="0"+(month+1);}else{mes=""+(month+1);}
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fecha2.setText(selectedDate);
                obtenerHora2();
            }
        });
        newFragment.show(getActivity().getSupportFragmentManager(), "datePicker");

    }
    private static final String CERO = "0";
    private static final String DOS_PUNTOS = ":";
    //Calendario para obtener fecha & hora
    public final Calendar c = Calendar.getInstance();
    //Variables para obtener la hora hora
    final int hora = c.get(Calendar.HOUR_OF_DAY);
    final int minuto = c.get(Calendar.MINUTE);
    private void obtenerHora1(){
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                fecha1.setText(fecha1.getText() + " " + horaFormateada + DOS_PUNTOS + minutoFormateado + ":00");
                //etHora.setText();
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

    private void obtenerHora2(){
        TimePickerDialog recogerHora = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                //Formateo el hora obtenido: antepone el 0 si son menores de 10
                String horaFormateada =  (hourOfDay < 10)? String.valueOf(CERO + hourOfDay) : String.valueOf(hourOfDay);
                //Formateo el minuto obtenido: antepone el 0 si son menores de 10
                String minutoFormateado = (minute < 10)? String.valueOf(CERO + minute):String.valueOf(minute);
                //Obtengo el valor a.m. o p.m., dependiendo de la selección del usuario
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                //Muestro la hora con el formato deseado
                fecha2.setText(fecha2.getText() + " " + horaFormateada + DOS_PUNTOS + minutoFormateado + ":00");
                //etHora.setText();
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

}
