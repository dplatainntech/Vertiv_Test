package com.maju.desarrollo.testfcs.Formatos.DCPower;


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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.maju.desarrollo.testfcs.Adapter.adapterFoliosPT;
import com.maju.desarrollo.testfcs.Adapter.adapterSR;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Dialog.DatePickerFragment;
import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PowerGeneralesFragment extends Fragment {
    OperacionesDB D_B;
    String id_formato;
    DCPower Formato;
    TextView fechainicio, fechafin;
    UsuarioD usuario;
    LinearLayout areaLista,areaListaSr;
    ListView listafiltros1,listafiltros2;
    EditText filtroT1,filtroT2;
    adapterSR miAdaptador2;
    InternetandVPN validaciones = new InternetandVPN();

    public PowerGeneralesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_power_generales, container, false);

        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        Formato = D_B.obtenerDcPower_id(id_formato);

        Bundle valores = new Bundle();
        valores.putString("titulo", "¡Advertencia!");
        valores.putString("mensaje", "El uso de Equipo de Protección Personal (EPP) es obligatorio durante la operación de las unidades y el manejo de los apartados adecuados de la medición.");

        AlertaGenerica alerta = new AlertaGenerica();
        alerta.setArguments(valores);
        alerta.setCancelable(false);
        alerta.show(getFragmentManager(), "a");

        final TextView cliente = (TextView)v.findViewById(R.id.cliente);
        final TextView direccion = (TextView)v.findViewById(R.id.direccion);
        final EditText contacto = (EditText)v.findViewById(R.id.contacto);
        final EditText telefono = (EditText)v.findViewById(R.id.telefono);
        final EditText mail = (EditText)v.findViewById(R.id.mail);
        final TextView proyecto = (TextView)v.findViewById(R.id.proyecto);
        final EditText referencia = (EditText)v.findViewById(R.id.referencia);
        final TextView sr = (TextView)v.findViewById(R.id.sr);
        final TextView task = (TextView)v.findViewById(R.id.task);
        final EditText tiposervicioespe = (EditText)v.findViewById(R.id.tiposervicioespe);
        final EditText ntag = (EditText)v.findViewById(R.id.ntag);
        final EditText modelo = (EditText)v.findViewById(R.id.modelo);
        final EditText nserie = (EditText)v.findViewById(R.id.serie1);
        final EditText nserie2 = (EditText)v.findViewById(R.id.serie2);
        fechainicio = (TextView)v.findViewById(R.id.fechainicio);
        fechafin = (TextView)v.findViewById(R.id.fechafin);
        final RadioButton siRB = (RadioButton)v.findViewById(R.id.siRB);
        final RadioButton noRB = (RadioButton)v.findViewById(R.id.noRB);
        final Spinner tiposerv = (Spinner)v.findViewById(R.id.tiposerv);
        final Spinner frecuencia = (Spinner)v.findViewById(R.id.frecuancia);
        ImageView Button_fecha1 = (ImageView)v.findViewById(R.id.Button_fecha1);
        ImageView Button_fecha2 = (ImageView)v.findViewById(R.id.Button_fecha2);
        areaListaSr = (LinearLayout)v.findViewById(R.id.autocompliteLY2);

        siRB.setEnabled(false);
        noRB.setEnabled(false);
        //modelo.setEnabled(false);
        //nserie.setEnabled(false);
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

        filtroT2 = (EditText)v.findViewById(R.id.filtro2);
        ImageView cerrarlista2 = (ImageView)v.findViewById(R.id.cerrarlista2);
        listafiltros2 = (ListView) v.findViewById(R.id.listaAuto2);
        areaListaSr.setVisibility(View.GONE);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tiposervicioDCpower, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tiposerv.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getActivity(),
                R.array.frecuenciaGral, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        frecuencia.setAdapter(adapter2);



        try{cliente.setText(Formato.getGRAL_CLIENTE().toString());}catch (Exception e){}
        try{direccion.setText(Formato.getGRAL_DIRECCION().toString());}catch (Exception e){}
        try{contacto.setText(Formato.getGRAL_CONTACTO().toString());}catch (Exception e){}
        try{telefono.setText(Formato.getGRAL_TELEFONO().toString());}catch (Exception e){}
        try{mail.setText(Formato.getGRAL_MAIL().toString());}catch (Exception e){}
        try{proyecto.setText(Formato.getGRAL_PROYECTO().toString());}catch (Exception e){}
        try{referencia.setText(Formato.getGRAL_REFERENCIA().toString());}catch (Exception e){}
        try{sr.setText(Formato.getGRAL_SR().toString());}catch (Exception e){}
        try{task.setText(Formato.getGRAL_TASK().toString());}catch (Exception e){}
        //try{tiposervicioespe.setText(Formato.gett().toString());}catch (Exception e){}

        try{ntag.setText(Formato.getGRAL_NTAG().toString());}catch (Exception e){}
        try{modelo.setText(Formato.getGRAL_MODELO().toString());}catch (Exception e){}
        try{nserie.setText(Formato.getGRAL_SERIE().toString());}catch (Exception e){}
        try{nserie2.setText(Formato.getGRAL_SERIE2().toString());}catch (Exception e){}
        try{fechainicio.setText(Formato.getGRAL_FECHA_INICIO().toString());}catch (Exception e){}
        try{fechafin.setText(Formato.getGRAL_FECHA_FIN().toString());}catch (Exception e){}


        try{tiposerv.setSelection(adapter.getPosition(Formato.getGRAL_TIPO_SERVICIO()));}catch (Exception e){}
        try{frecuencia.setSelection(adapter2.getPosition(Formato.getGRAL_FRECUENCIA()));}catch (Exception e){}

        filtroT1 = (EditText)v.findViewById(R.id.filtro1);
        ImageView cerrarlista1 = (ImageView)v.findViewById(R.id.cerrarlista1);
        listafiltros1 = (ListView) v.findViewById(R.id.listaAuto1);
        areaLista = (LinearLayout)v.findViewById(R.id.autocompliteLY);
        final TextView FoliosTV = (TextView)v.findViewById(R.id.folioPT);
        areaLista.setVisibility(View.GONE);

        FoliosTV.setText(Formato.getGRAL_FOLIO_PRETRABAJO());
         /*try{if(Formato.getGRAL_proyecto().length()>1 ||
                (Formato.getGRAL_sr().length()>1 && Formato.getGRAL_task().length()>1)){
            modelo.setEnabled(true);
            nserie.setEnabled(true);
        }else{
            modelo.setEnabled(false);
            nserie.setEnabled(false);
        }}catch (Exception e){
            modelo.setEnabled(false);
            nserie.setEnabled(false);
        }*/
        //region FILTROS FOLIOS PT
        List<CatFolios> ListaTask_SR = new ArrayList<>();
        String bsccliente = " ";
        try{bsccliente = Formato.getGRAL_CLIENTE();}catch (Exception e){}
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
                direccion.setText(item.getDireccionSitio());
                 /*try{if(Formato.getGRAL_proyecto().length()>1 ||
                (Formato.getGRAL_sr().length()>1 && Formato.getGRAL_task().length()>1)){
            modelo.setEnabled(true);
            nserie.setEnabled(true);
        }else{
            modelo.setEnabled(false);
            nserie.setEnabled(false);
        }}catch (Exception e){
            modelo.setEnabled(false);
            nserie.setEnabled(false);
        }*/


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
                modelo.setText(item.getItem_Number());
                nserie.setText(item.getSerial_Number());
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



        
        
        

        tiposerv.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> spn,
                                               android.view.View v,
                                               int posicion,
                                               long id) {

                        if (spn.getItemAtPosition(posicion).toString().equals("Arranque (30)")) {
                            siRB.setEnabled(true);
                            noRB.setEnabled(true);
                        }
                        else if (spn.getItemAtPosition(posicion).toString().equals("Arranque Equipo no Vertiv (31)")) {
                            siRB.setEnabled(true);
                            noRB.setEnabled(true);
                        }else{
                            siRB.setEnabled(true);
                            noRB.setEnabled(true);
                            siRB.setChecked(false);
                            noRB.setChecked(false);
                        }
                    }
                    public void onNothingSelected(AdapterView<?> spn) {
                    }
                });


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
                args.putString("OtraPantalla", "DCPower");
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

                Formato.setGRAL_FOLIO_PRETRABAJO(FoliosTV.getText().toString());
                Formato.setGRAL_CLIENTE(cliente.getText().toString());
                Formato.setGRAL_DIRECCION(direccion.getText().toString());
                Formato.setGRAL_CONTACTO(contacto.getText().toString());
                Formato.setGRAL_TELEFONO(telefono.getText().toString());
                Formato.setGRAL_MAIL(mail.getText().toString());
                Formato.setGRAL_PROYECTO(proyecto.getText().toString());
                Formato.setGRAL_REFERENCIA(referencia.getText().toString());
                Formato.setGRAL_SR(sr.getText().toString());
                Formato.setGRAL_TASK(task.getText().toString());
                Formato.setGRAL_TIPO_SERVICIO(tiposerv.getSelectedItem().toString());
                //Formato.setESPECICIFACION(tiposervicioespe.getText().toString());

                Formato.setGRAL_FRECUENCIA(frecuencia.getSelectedItem().toString());
                Formato.setGRAL_NTAG(ntag.getText().toString());
                Formato.setGRAL_MODELO(modelo.getText().toString());
                Formato.setGRAL_SERIE(nserie.getText().toString());
                Formato.setGRAL_SERIE2(nserie2.getText().toString());
                Formato.setGRAL_FECHA_INICIO(fechainicio.getText().toString());
                Formato.setGRAL_FECHA_FIN(fechafin.getText().toString());

                D_B.guardarPower1(Formato);

                DatGeneralesF item2 = new DatGeneralesF();
                item2.setID_FORMATO(id_formato);
                item2.setFOLIO_PRETRABAJO(FoliosTV.getText().toString());
                item2.setFECHA_MODIFICACION(new Date().toString());
                item2.setCLIENTE(cliente.getText().toString());
                item2.setSR(sr.getText().toString());
                item2.setTASK(task.getText().toString());
                item2.setITEM(modelo.getText().toString());
                item2.setSERIE(nserie.getText().toString());
                D_B.actualizarInfoformato(item2);

                Bundle args = new Bundle();
                args.putString("key_idFormato", id_formato);
                MenuPowerFragment myfargment = new MenuPowerFragment();
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
                final String selectedDate = year  + "-" +mes + "-" +dia  ;
                fechainicio.setText(selectedDate);
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
                fechafin.setText(selectedDate);
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
                fechainicio.setText(fechainicio.getText() + " " + horaFormateada + DOS_PUNTOS + minutoFormateado + ":00");
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
                fechafin.setText(fechafin.getText() + " " + horaFormateada + DOS_PUNTOS + minutoFormateado + ":00");
                //etHora.setText();
            }
            //Estos valores deben ir en ese orden
            //Al colocar en false se muestra en formato 12 horas y true en formato 24 horas
            //Pero el sistema devuelve la hora en formato 24 horas
        }, hora, minuto, false);

        recogerHora.show();
    }

}
