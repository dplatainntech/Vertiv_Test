package com.maju.desarrollo.testfcs.AccionesM;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.ImgB64;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.Model.SGarantias;
import com.maju.desarrollo.testfcs.SQLite.Model.Servicios;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.BateriaS;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Garantia;
import com.maju.desarrollo.testfcs.ServiceClass.GeneralServicios;
import com.maju.desarrollo.testfcs.ServiceClass.PDU;
import com.maju.desarrollo.testfcs.ServiceClass.STS2;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatEstado;
import com.maju.desarrollo.testfcs.ServiceClass.PreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatBestel;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatRegion;
import com.maju.desarrollo.testfcs.ServiceClass.ThermalManagagementS;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sincronizar extends Fragment {
    OperacionesDB D_B;
    IMyAPI iMyAPI;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    UsuarioD usuario;
    TextView fecha;
    ImgB64 b64 = new ImgB64();

    public Sincronizar() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_sincronizar, container, false);
        D_B = OperacionesDB.obtenerInstancia(getContext());
        iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
        final CheckBox task = (CheckBox)v.findViewById(R.id.checkBoxtask);
        final CheckBox foliosPT = (CheckBox)v.findViewById(R.id.checkBoxpt);
        final CheckBox Clientes = (CheckBox)v.findViewById(R.id.checkBoxcliente);
        final CheckBox bestel = (CheckBox)v.findViewById(R.id.checkBoxbestel);
        final CheckBox cbusuario = (CheckBox)v.findViewById(R.id.checkBox11);
        final CheckBox formatosT = (CheckBox)v.findViewById(R.id.checkBoxFT);
        CheckBox checkBoxTodos = (CheckBox)v.findViewById(R.id.checkBoxTodos);
        fecha = (TextView)v.findViewById(R.id.ultimaSinc);


        fecha.setText(D_B.ultimaFechaSinc());
        usuario = D_B.obtenerUsuario();

        if(!usuario.getPaisDescripcion().equals("MEXICO")){
            bestel.setVisibility(View.GONE);
            cbusuario.setVisibility(View.GONE);
        }


        checkBoxTodos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    task.setChecked(true);
                    foliosPT.setChecked(true);
                    Clientes.setChecked(true);
                    bestel.setChecked(true);
                    cbusuario.setChecked(true);
                    formatosT.setChecked(true);
                }
                else{
                    task.setChecked(false);
                    foliosPT.setChecked(false);
                    Clientes.setChecked(false);
                    bestel.setChecked(false);
                    cbusuario.setChecked(false);
                    formatosT.setChecked(false);
                }
            }
        });


        Button sinc = (Button)v.findViewById(R.id.btoSincronizar);

        usuario = D_B.obtenerUsuario();


        sinc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // ((MainActivity) getActivity()).perogreso(3000);
                final progresoLoad alerta = new progresoLoad();
                alerta.setCancelable(false);
                final progresoLoad alerta2 = new progresoLoad();
                alerta2.setCancelable(false);
                final progresoLoad alerta3 = new progresoLoad();
                alerta3.setCancelable(false);
                final progresoLoad alerta4 = new progresoLoad();
                alerta4.setCancelable(false);
                final progresoLoad alerta5 = new progresoLoad();
                alerta5.setCancelable(false);

             /*   final progresoLoad alerta = new progresoLoad();
                alerta.setCancelable(true);
                alerta.show(getFragmentManager(), "a");

                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        alerta.dismiss();
                        //finish();
                    }
                }, 4000);
*/

             //region SINC FOLIOS PRE TRABAJO
                if (foliosPT.isChecked()) {
                    D_B.newMensaje("SINCRONIZACION DE FOLIOS PRE-TRABAJO");
                    D_B.actualizarFechaSinc();
                    alerta.show(getFragmentManager(), "a");
                    CatFolios task = new CatFolios();
                    task.setUsuario(usuario.getID_USER());
                    task.setExitoso("0");
                    D_B.borrarTablasSinc("folios");
                    task.setPais(usuario.getPAIS());
                    compositeDisposable.add(iMyAPI.obtenerfoliosPT(task).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatFolios>>() {
                                @Override
                                public void accept(List<CatFolios> lista) throws Exception {
                                    //lista.get(0).getItem_Number();
                                    if(lista.size()==0){
                                        alerta.dismiss();
                                        //alerta("No se obtuvieron Folios Pre Trabajo.");
                                        //Toast.makeText(getContext(), "Clientes Guardados", Toast.LENGTH_SHORT).show();
                                    }else {
                                        D_B.guardarFoliosPT(lista);
                                        alerta.dismiss();
                                        Toast.makeText(getContext(), "Folios guardados", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    alerta.dismiss();
                                }
                            })

                    );
                    mostrarFechas();
                }

                //endregion

             //region SINC DIRECCIONES
                CatEstado catEstado = new CatEstado();
                catEstado.setExitoso("1");

           //endregion

                //region SINC TASX
                if (task.isChecked()) {
                    alerta2.show(getFragmentManager(), "a");
                    CatAsignaCliente task = new CatAsignaCliente();
                    task.setEmail(usuario.getCORREO());

                    D_B.borrarTablasSinc("tareas");
                    D_B.newMensaje("SINCRONIZACION DE ASIGNACIONES");
                    D_B.actualizarFechaSinc();
                    task.setPais(usuario.getPAIS());
                    compositeDisposable.add(iMyAPI.obtenerTask(task).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatAsignaCliente>>() {
                                @Override
                                public void accept(List<CatAsignaCliente> lista) throws Exception {
                                    //lista.get(0).getItem_Number();

                                    D_B.guardarTask(lista);
                                    alerta2.dismiss();
                                    Toast.makeText(getContext(), "Tareas guardadas", Toast.LENGTH_SHORT).show();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    alerta2.dismiss();
                                }
                            })

                    );
                    mostrarFechas();
                }

                //endregion

                //region SINC CLIENTES
                if (Clientes.isChecked()) {
                    alerta3.show(getFragmentManager(), "a");
                    CatCliente cliente = new CatCliente();
                    D_B.borrarTablasSinc("clientes");
                    D_B.newMensaje("SINCRONIZACION DE CATALOGO DE CLIENTES");
                    D_B.actualizarFechaSinc();
                    cliente.setPais(usuario.getPAIS());
                    compositeDisposable.add(iMyAPI.obtenerClientes(cliente).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatCliente>>() {
                                @Override
                                public void accept(List<CatCliente> lista) throws Exception {
                                    D_B.guardarCLientes(lista);
                                    alerta3.dismiss();
                                    Toast.makeText(getContext(), "Clientes Guardados", Toast.LENGTH_SHORT).show();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                     alerta3.dismiss();
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            })

                    );
                    mostrarFechas();
                }
                //endregion

                //region SINC BESTEL
                if (bestel.isChecked()) {
                    alerta4.show(getFragmentManager(), "a");
                    CatBestel task = new CatBestel();

                    D_B.borrarTablasSinc("bestel");
                    D_B.newMensaje("SINCRONIZACION DE DIRECCIONES BESTEL");
                    D_B.actualizarFechaSinc();
                    compositeDisposable.add(iMyAPI.obtenerDireccionesBestel(task).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatBestel>>() {
                                @Override
                                public void accept(List<CatBestel> lista) throws Exception {
                                    //lista.get(0).getItem_Number();
                                    D_B.guardardireccionesBestel(lista);
                                    alerta4.dismiss();
                                    Toast.makeText(getContext(), "Direcciones guardadas", Toast.LENGTH_SHORT).show();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    alerta4.dismiss();
                                }
                            })

                    );
                    mostrarFechas();
                }
                //endregion

                //region Catalogo direcciones
                if (cbusuario.isChecked()) {
                    alerta5.show(getFragmentManager(), "a");
                    CatEstado task = new CatEstado();

                    D_B.borrarTablasSinc("estados");
                    task.setPais(usuario.getPAIS());
                    compositeDisposable.add(iMyAPI.catEstado(task).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatEstado>>() {
                                @Override
                                public void accept(List<CatEstado> lista) throws Exception {
                                    D_B.guardarestados(lista);
                                    alerta5.dismiss();
                                    Toast.makeText(getContext(), "Catálogo guardado.", Toast.LENGTH_SHORT).show();
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    alerta5.dismiss();
                                }
                            })

                    );

                    CatRegion task2 = new CatRegion();

                    D_B.borrarTablasSinc("regiones");
                    task2.setPais(usuario.getPAIS());
                    compositeDisposable.add(iMyAPI.catRegiones(task2).subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<List<CatRegion>>() {
                                @Override
                                public void accept(List<CatRegion> lista) throws Exception {
                                    D_B.guardarregiones(lista);
                                    alerta5.dismiss();
                                    //Toast.makeText(getContext(), "Direcciones Bestel guardadas", Toast.LENGTH_SHORT).show();

                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    alerta5.dismiss();
                                }
                            })

                    );

                    mostrarFechas();
                }
                //endregion

                //region SINC FORMATOS TERMINADOS
                if (formatosT.isChecked()) {
                    metodoEjecutar();
                }
                //endregion
            }
        });

        return v;
    }

    public void mostrarFechas(){
        fecha.setText(D_B.ultimaFechaSinc());
    }

    public void alerta(String mensaje){
        Bundle valores = new Bundle();
        valores.putString("titulo","Importante");
        valores.putString("mensaje",mensaje);

        AlertaGenerica alerta = new AlertaGenerica();
        alerta.setArguments(valores);
        alerta.setCancelable(false);
        alerta.show(getFragmentManager(), "a");
    }


    //region Envio de Formatos Terminados

    private void metodoEjecutar() {
               /*
                0.-PT
                1.-Calidad
                2.-Baterias
                3.-DCPower
                4.-Garantias
                5.-IN1
                6.-IN2
                7.-PDU
                8.-Servicios
                9.-STS2
                10.-Thermal
                11.-UPS
                 */
        Cursor datos = D_B.formatosTerminadosSegundoplano();
        if (datos.moveToFirst()) {
            do{
                switch (datos.getString(1)){ //tipo de formato
                    case "0":
                        formato0(datos.getString(0)); //idFormato (Key)
                        break;
                    case "1":
                        formato1(datos.getString(0)); //idFormato (Key)
                        break;
                    case "2":
                        formato2(datos.getString(0)); //idFormato (Key)
                        break;
                    case "3":
                        formato3(datos.getString(0)); //idFormato (Key)
                        break;
                    case "4":
                        formato4(datos.getString(0)); //idFormato (Key)
                        break;
                    case "5":
                        formato5(datos.getString(0)); //idFormato (Key)
                        break;
                    case "6":
                        formato6(datos.getString(0)); //idFormato (Key)
                        break;
                    case "7":
                        formato7(datos.getString(0)); //idFormato (Key)
                        break;
                    case "8":
                        formato8(datos.getString(0)); //idFormato (Key)
                        break;
                    case "9":
                        formato9(datos.getString(0)); //idFormato (Key)
                        break;
                    case "10":
                        formato10(datos.getString(0)); //idFormato (Key)
                        break;
                    case "11":
                        formato11(datos.getString(0)); //idFormato (Key)
                        break;
                }
            }
            while (datos.moveToNext());
            Toast.makeText(getContext(), "Formatos enviados", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(getContext(), "No se encontraron formatos terminados", Toast.LENGTH_LONG).show();
            /*Bundle valores = new Bundle();
            valores.putString("titulo","Importante");
            valores.putString("mensaje","No se encontraron Formatos terminados.");

            AlertaGenerica alerta = new AlertaGenerica();
            alerta.setArguments(valores);
            alerta.setCancelable(false);
            alerta.show(getFragmentManager(), "a");

             */
        }
        //D_B.newMensaje("Enviando Folios Terinados");
        //Toast.makeText(this, "Enviando Formatos terminados", Toast.LENGTH_LONG).show();
    }

    public void formato0(final String id_formato){
        final progresoLoad alerta0 = new progresoLoad();
        alerta0.setCancelable(false);
        PreOrden preOrden = D_B.obtenerPreOrden_id(id_formato);
        //region Generar Pre trabajo
        final PreTrabajo item = D_B.obtenerPTPrelleno();
        if(preOrden.getGEN_SR().length()>1){
            String id =D_B.obtenerIDCliente(preOrden.getGEN_CLIENTE(),preOrden.getGEN_SITIO(),preOrden.getGEN_DIRECCION());
            item.setIdCliente(id);//obligatorios
        }
        else{
            item.setIdCliente(null);//obligatorios
        }
        final String idFormatoService = D_B.generarUID();
        item.setIdPretrabajo(idFormatoService);

        item.setIdCliente(preOrden.getGEN_CLIENTE());
        item.setGEN_SR(preOrden.getGEN_SR());
        item.setGEN_TASK(preOrden.getGEN_TASK());
        item.setGEN_PROYECTO(preOrden.getGEN_PROYECTO());
        item.setGEN_SITIO(preOrden.getGEN_SITIO());
        item.setGEN_REFERENCIA(preOrden.getGEN_REFERENCIA());
        item.setGEN_USUARIO_FINAL(preOrden.getGEN_USUARIO_FINAL());
        item.setNombreCliente(preOrden.getGEN_CLIENTE());
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setEPP_ELECTICO_GUANTES(true);}
        //item.setINSPEC_PERMISO_ARCHIVO(preOrden.getINSPEC_PERMISO_ARCHIVO());
        item.setFIRMA_1_CARGO(preOrden.getFIRMA_1_CARGO());
        item.setFIRMA_2_CARGO(preOrden.getFIRMA_2_CARGO());
        item.setFIRMA_3_CARGO(preOrden.getFIRMA_3_CARGO());
        item.setFIRMA_4_CARGO(preOrden.getFIRMA_4_CARGO());
        item.setFIRMA_5_CARGO(preOrden.getFIRMA_5_CARGO());
        item.setFIRMA_6_CARGO(preOrden.getFIRMA_6_CARGO());
        item.setIdUsuario(usuario.getID_USER());//obligatorios

        item.setNombreSitio(preOrden.getGEN_SITIO());
        item.setFecha(preOrden.getGEN_FECHA());
        item.setLiderGrupoCuadrilla(preOrden.getGEN_LIDER_GRUPO());
        item.setDireccionSitio(preOrden.getGEN_DIRECCION());
        item.setDescripcionActividad(preOrden.getACTV_DESCRIPCION_ACTIVIDADES());
        item.setEquiposHerramientasMateriales(preOrden.getHERRAM_DESCRIPCION_EHM());
        if(preOrden.getHERRAM_HOJAS_SEGURIDAD().length()>1){item.setHojasSeguridad(true);}
        if(preOrden.getHERRAM_DELIMITACION_AT().equals("Cinta de Precaución")){item.setCintaPrecaucion(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Conos/Pedestales")){item.setConosPedestales(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Mixta (Conos y Cinta.)")){item.setMixta(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else item.setOtrosArea(preOrden.getHERRAM_DELIMITACION_AT());
        if(preOrden.getRIESGO_PARTICULAS().length()>1){item.setProyeccionParticulas(true);}
        if(preOrden.getRIESGO_ATRAPAMIENTO().length()>1){item.setAtrapamiento(true);}
        if(preOrden.getRIESGO_GOLPES().length()>1){item.setGolpesCortes(true);}
        if(preOrden.getRIESGO_QUEMADURAS().length()>1){item.setQuemaduras(true);}
        if(preOrden.getRIESGO_CAIDA_MATE().length()>1){item.setCaidaMateriales(true);}
        if(preOrden.getRIESGO_CAIDA_MISMO_NIVEL().length()>1){item.setCaidaMismoNivel(true);}
        if(preOrden.getRIESGO_CAIDA_DIST_NIVEL().length()>1){item.setCaidaDistintoNivel(true);}
        if(preOrden.getRIESGO_LIMPIEZA_DEFI().length()>1){item.setOrdenLimpieza(true);}
        if(preOrden.getRIESGO_OTRO_PERSONAL().length()>1){item.setOtroPersonalTrabajando(true);}
        if(preOrden.getRIESGO_CHOQUE_ELECTRICO().length()>1){item.setChoqueElectrico(true);}
        if(preOrden.getRIESGO_ARCO_ELECT().length()>1){item.setArcoElectrico(true);}
        if(preOrden.getRIESGO_FUEGO().length()>1){item.setFuegoExplosion(true);}
        if(preOrden.getRIESGO_EXPO_RUIDO().length()>1){item.setExposicionRuido(true);}
        if(preOrden.getRIESGO_EXP_VIBRA().length()>1){item.setExposicionVibraciones(true);}
        if(preOrden.getRIESGO_FATIGA_VISUAL().length()>1){item.setFatigaVisual(true);}
        if(preOrden.getRIESGO_TEMPERATURAS().length()>1){item.setExposicionAltasBjasTemperaturas(true);}
        if(preOrden.getRIESGO_DEFI_OXIGENO().length()>1){item.setDeficienciaOxigeno(true);}
        if(preOrden.getRIESGO_GASES().length()>1){item.setExposiconGases(true);}
        if(preOrden.getRIESGO_POLVO().length()>1){item.setExposicionPolvo(true);}
        if(preOrden.getRIESGO_SOBRE_ESFUERZO().length()>1){item.setSobreEsfuerzo(true);}
        if(preOrden.getRIESGO_QUIMICOS().length()>1){item.setManipulacionProductosQuimicos(true);}
        if(preOrden.getRIESGO_RUIDO().length()>1){item.setRuido(true);}
        item.setOtrosRiesgos(preOrden.getRIESGO_OTRO());
        if(preOrden.getPREVEN_DISPO_MECANICA().length()>1){item.setUsarDispositivosElevacion(true);}
        if(preOrden.getPREVEN_SUST_QUIMICOS().length()>1){item.setSustituirQuimicosToxicos(true);}
        if(preOrden.getPREVEN_AISLAR_RUIDO().length()>1){item.setAislarRuidoGenerado(true);}
        if(preOrden.getPREVEN_PROTECTORES_MAQUINAS().length()>1){item.setColocarGuardasProtectoras(true);}
        if(preOrden.getPREVEN_PLATA_ANDAMIOS().length()>1){item.setInstalarPlataformas(true);}
        if(preOrden.getPREVEN_SIS_PNTS_ANCLAJE().length()>1){item.setInstalarSistemaPuntosAnclaje(true);}
        if(preOrden.getPREVEN_ILUMI_ART().length()>1){item.setInstalarIluminacion(true);}
        if(preOrden.getPREVEN_DISYUNTOR().length()>1){item.setInstalarDisyuntor(true);}
        if(preOrden.getPREVEN_SIST_PUESTA_TIERRA().length()>1){item.setInstalarSistemaPuestaTierra(true);}
        if(preOrden.getPREVEN_ORDEN_LIMPIEZA().length()>1){item.setMantenerOrden(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setSeñalizarAreaTrabajo(true);}
        if(preOrden.getPREVEN_BE_FUENTES_ENERGIA().length()>1){item.setBloquearEtiquetarFuentesEnergia(true);}
        if(preOrden.getPREVEN_MUROS_DERRAME().length()>1){item.setInstalarMurosContenerDerrames(true);}
        if(preOrden.getPREVEN_PERMISOS().length()>1){item.setPermisoTrabajo(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setProcedTrabajo(true);}
        if(preOrden.getPREVEN_SUPERVISION().length()>1){item.setSupervisionPermanente(true);}
        if(preOrden.getPREVEN_HERRAMI_AISLADAS().length()>1){item.setUsarHerramientaAislada(true);}
        if(preOrden.getPREVEN_EPP().length()>1){item.setEquipoProteccionPersonal(true);}
        item.setOtrosMedidasPrevension(preOrden.getPREVEN_OTRO());
        if(preOrden.getEPP_BASICO_CASCO().length()>1){item.setCasco(true);}
        if(preOrden.getEPP_BASICO_GAFAS().length()>1){item.setGafasProtectoras(true);}
        if(preOrden.getEPP_BASICO_TAPONES().length()>1){item.setProtectoresAuditores(true);}
        if(preOrden.getEPP_BASICO_ZAPATOS().length()>1){item.setZapatosSeguridad(true);}
        if(preOrden.getEPP_BASICO_GUANTES().length()>1){item.setGuantesTrabajo(true);}
        if(preOrden.getEPP_BASICO_BARBIQUEJO().length()>1){item.setBarbiquejo(true);}
        if(preOrden.getEPP_ELECTICO_GAFAS().length()>1){item.setGafasSeguridad(true);}
        if(preOrden.getEPP_ELECTICO_CASCO().length()>1){item.setCascoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_ZAPATOS().length()>1){item.setZapatosDielectricos(true);}
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setSobreguanteCuero(true);}
        if(preOrden.getEPP_ELECTICO_CARETA().length()>1){item.setCaretaArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_BALACLAVA().length()>1){item.setBalaClava(true);}
        if(preOrden.getEPP_ELECTICO_TRAJE().length()>1){item.setTrajeArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_PROTECTORES_AUDI().length()>1){item.setProtectoresAuditivos(true);}
        if(preOrden.getEPP_ELECTICO_MANGAS().length()>1){item.setMangasDielectricas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_CAIDAS().length()>1){item.setProteccionContraCaidas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_RESPITA().length()>1){item.setProteccionRespiratoria(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_SOLDAD().length()>1){item.setProteccionSoldadora(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_QUIMICOS().length()>1){item.setProteccionContraQuimicos(true);}
        item.setAdiconales(preOrden.getEPP_OTROS_ADICIONALES());
        if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("SI")){item.setInspeccionEPP(true);}
        else if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("NO")){item.setInspeccionEPP(false);}
        item.setEspecifiqueDano(preOrden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE());

        if(preOrden.getINSPEC_ACTIVIDADES().equals("Entrada en espacios confinado")){item.setEntradaEspaciosConfinados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en caliente")){item.setTrabajosCaliente(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en equipos energizados (>50V)")){item.setTrabajosEquiposEnergizados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("N/A")){item.setNA(true);}

        if(preOrden.getINSPEC_TRABAJOS_ALTURA().equals("SI")){item.setTrabajosAltura(true);}
        if(preOrden.getINSPEC_CONDIC_INSEGURAS().equals("SI")){item.setCondicionesInseguras(true);}
        item.setCondicionInsegura(preOrden.getINSPEC_CAUSAS_ACCIDENTES());
        item.setMedidasCorrectivas(preOrden.getINSPEC_MEDIDAS_CORRECTIVAS());
        item.setNombre1(preOrden.getFIRMA_1_NOMBRE());
        item.setArchivoFirma1(preOrden.getFIRMA_1_IMAGEN());
        item.setNombre2(preOrden.getFIRMA_2_NOMBRE());
        item.setArchivoFirma2(preOrden.getFIRMA_2_IMAGEN());
        item.setNombre3(preOrden.getFIRMA_3_NOMBRE());
        item.setArchivoFirma3(preOrden.getFIRMA_3_IMAGEN());
        item.setNombre4(preOrden.getFIRMA_4_NOMBRE());
        item.setArchivoFirma4(preOrden.getFIRMA_4_IMAGEN());
        item.setNombre5(preOrden.getFIRMA_5_NOMBRE());
        item.setArchivoFirma5(preOrden.getFIRMA_5_IMAGEN());
        item.setNombre6(preOrden.getFIRMA_6_NOMBRE());
        item.setArchivoFirma6(preOrden.getFIRMA_6_IMAGEN());
        item.setNumeroEmergencia(preOrden.getEMERG_EMERGENCIAS());
        item.setNumeroSupervisor(preOrden.getEMERG_SUPERVISOR_VERTIV());
        item.setHospitalCercano(preOrden.getEMERG_HOSPITAL());

        //endregion
        compositeDisposable.add(iMyAPI.EnvioPreoTrabajo(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PreTrabajo>() {
                    @Override
                    public void accept(PreTrabajo lista) throws Exception {
                        if(lista.getExitoso().equals("1")) {

                            try{if(item.getINSPEC_PERMISO_ARCHIVO().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setINSPEC_PERMISO_ARCHIVO(b64.ImgB64(item.getINSPEC_PERMISO_ARCHIVO()));
                                itemF.setFormato("1");
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}


                            D_B.actualizarEsatusFormato("3",id_formato);
                            D_B.newMensaje("Pre tranajo enviado, Folio:"+lista.getFolioPreTrabajo());
                            alerta0.dismiss();
                        }else{
                            D_B.newMensaje(lista.getError());
                            alerta0.dismiss();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alerta0.dismiss();
                    }
                })
        );
    }
    public void formato1(final String id_formato) {

        EncuestaCalidadServicio item = D_B.obtenerCalidad(id_formato);

        item.setIdUsuario(usuario.getID_USER());

        if (item.getTipoServicio().equals("Otro (Especifique)")) {
            item.setTipoServicio(item.getTiposervicioOtro());
        }
        compositeDisposable.add(iMyAPI.enviarCalidad(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EncuestaCalidadServicio>() {
                    @Override
                    public void accept(EncuestaCalidadServicio lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if(lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);

                        }else{
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato2(final String id_formato){
        Baterias bateria = D_B.obtenerBateriaF_id(id_formato);
        final BateriaS Formato = new BateriaS();

        //region
        if(bateria.getGRAL_proyecto().length()>1){ Formato.setSRProyecto(bateria.getGRAL_proyecto());}
        else {Formato.setSRProyecto(bateria.getGRAL_sr());}
        final String idFormatoService = Formato.getFolioPreTrabajo();//D_B.generarUID();
        Formato.setIdFormatoBateria(idFormatoService);
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setFolioPreTrabajo(bateria.getGRAL_folioPT());
        Formato.setFechaInicio(bateria.getGRAL_fecha1());
        Formato.setFechaTermino(bateria.getGRAL_fecha2());
        Formato.setContactoCliente(bateria.getGRAL_contacto());
        Formato.setTASK(bateria.getGRAL_task());
        Formato.setTelefono(bateria.getGRAL_telefono());
        Formato.setTipoServicio(bateria.getGRAL_tipoServ());
        Formato.setEMail(bateria.getGRAL_mail());
        Formato.setFrecuencia(bateria.getGRAL_freciencia());
        Formato.setModeloEquipo(bateria.getGRAL_modelo());
        Formato.setNoTag(bateria.getGRAL_noTag());
        Formato.setNoSerie(bateria.getGRAL_serie1());
        Formato.setDireccionSitio(bateria.getGRAL_direccion());
        Formato.setVDC1(bateria.getVDC1());
        Formato.setVDC2(bateria.getVDC2());
        Formato.setVDC3(bateria.getVDC3());
        Formato.setVDC4(bateria.getVDC4());
        Formato.setVDC5(bateria.getVDC5());
        Formato.setVDC6(bateria.getVDC6());
        Formato.setVDC7(bateria.getVDC7());
        Formato.setVDC8(bateria.getVDC8());
        Formato.setVDC9(bateria.getVDC9());
        Formato.setVDC10(bateria.getVDC10());
        Formato.setVDC11(bateria.getVDC11());
        Formato.setVDC12(bateria.getVDC12());
        Formato.setVDC13(bateria.getVDC13());
        Formato.setVDC14(bateria.getVDC14());
        Formato.setVDC15(bateria.getVDC15());
        Formato.setVDC16(bateria.getVDC16());
        Formato.setVDC17(bateria.getVDC17());
        Formato.setVDC18(bateria.getVDC18());
        Formato.setVDC19(bateria.getVDC19());
        Formato.setVDC20(bateria.getVDC20());
        Formato.setVDC21(bateria.getVDC21());
        Formato.setVDC22(bateria.getVDC22());
        Formato.setVDC23(bateria.getVDC23());
        Formato.setVDC24(bateria.getVDC24());
        Formato.setVDC25(bateria.getVDC25());
        Formato.setVDC26(bateria.getVDC26());
        Formato.setVDC27(bateria.getVDC27());
        Formato.setVDC28(bateria.getVDC28());
        Formato.setVDC29(bateria.getVDC29());
        Formato.setVDC30(bateria.getVDC30());
        Formato.setVDC31(bateria.getVDC31());
        Formato.setVDC32(bateria.getVDC32());
        Formato.setVDC33(bateria.getVDC33());
        Formato.setVDC34(bateria.getVDC34());
        Formato.setVDC35(bateria.getVDC35());
        Formato.setVDC36(bateria.getVDC36());
        Formato.setVDC37(bateria.getVDC37());
        Formato.setVDC38(bateria.getVDC38());
        Formato.setVDC39(bateria.getVDC39());
        Formato.setVDC40(bateria.getVDC40());
        Formato.setVDC41(bateria.getVDC41());
        Formato.setVDC42(bateria.getVDC42());
        Formato.setVDC43(bateria.getVDC43());
        Formato.setVDC44(bateria.getVDC44());
        Formato.setVDC45(bateria.getVDC45());
        Formato.setVDC46(bateria.getVDC46());
        Formato.setVDC47(bateria.getVDC47());
        Formato.setVDC48(bateria.getVDC48());
        Formato.setVDC49(bateria.getVDC49());
        Formato.setVDC50(bateria.getVDC50());
        Formato.setVDC51(bateria.getVDC51());
        Formato.setVDC52(bateria.getVDC52());
        Formato.setVDC53(bateria.getVDC53());
        Formato.setVDC54(bateria.getVDC54());
        Formato.setVDC55(bateria.getVDC55());
        Formato.setVDC56(bateria.getVDC56());
        Formato.setVDC57(bateria.getVDC57());
        Formato.setVDC58(bateria.getVDC58());
        Formato.setVDC59(bateria.getVDC59());
        Formato.setVDC60(bateria.getVDC60());
        Formato.setVDC61(bateria.getVDC61());
        Formato.setVDC62(bateria.getVDC62());
        Formato.setVDC63(bateria.getVDC63());
        Formato.setVDC64(bateria.getVDC64());
        Formato.setVDC65(bateria.getVDC65());
        Formato.setVDC66(bateria.getVDC66());
        Formato.setVDC67(bateria.getVDC67());
        Formato.setVDC68(bateria.getVDC68());
        Formato.setVDC69(bateria.getVDC69());
        Formato.setVDC70(bateria.getVDC70());
        Formato.setVDC71(bateria.getVDC71());
        Formato.setVDC72(bateria.getVDC72());
        Formato.setVDC73(bateria.getVDC73());
        Formato.setVDC74(bateria.getVDC74());
        Formato.setVDC75(bateria.getVDC75());
        Formato.setVDC76(bateria.getVDC76());
        Formato.setVDC77(bateria.getVDC77());
        Formato.setVDC78(bateria.getVDC78());
        Formato.setVDC79(bateria.getVDC79());
        Formato.setVDC80(bateria.getVDC80());
        Formato.setVDC81(bateria.getVDC81());
        Formato.setVDC82(bateria.getVDC82());
        Formato.setVDC83(bateria.getVDC83());
        Formato.setVDC84(bateria.getVDC84());
        Formato.setVDC85(bateria.getVDC85());
        Formato.setVDC86(bateria.getVDC86());
        Formato.setVDC87(bateria.getVDC87());
        Formato.setVDC88(bateria.getVDC88());
        Formato.setVDC89(bateria.getVDC89());
        Formato.setVDC90(bateria.getVDC90());
        Formato.setVDC91(bateria.getVDC91());
        Formato.setVDC92(bateria.getVDC92());
        Formato.setVDC93(bateria.getVDC93());
        Formato.setVDC94(bateria.getVDC94());
        Formato.setVDC95(bateria.getVDC95());
        Formato.setVDC96(bateria.getVDC96());
        Formato.setVDC97(bateria.getVDC97());
        Formato.setVDC98(bateria.getVDC98());
        Formato.setVDC99(bateria.getVDC99());
        Formato.setVDC100(bateria.getVDC100());
        Formato.setVDC101(bateria.getVDC101());
        Formato.setVDC102(bateria.getVDC102());
        Formato.setVDC103(bateria.getVDC103());
        Formato.setVDC104(bateria.getVDC104());
        Formato.setVDC105(bateria.getVDC105());
        Formato.setVDC106(bateria.getVDC106());
        Formato.setVDC107(bateria.getVDC107());
        Formato.setVDC108(bateria.getVDC108());
        Formato.setVDC109(bateria.getVDC109());
        Formato.setVDC110(bateria.getVDC110());
        Formato.setVDC111(bateria.getVDC111());
        Formato.setVDC112(bateria.getVDC112());
        Formato.setVDC113(bateria.getVDC113());
        Formato.setVDC114(bateria.getVDC114());
        Formato.setVDC115(bateria.getVDC115());
        Formato.setVDC116(bateria.getVDC116());
        Formato.setVDC117(bateria.getVDC117());
        Formato.setVDC118(bateria.getVDC118());
        Formato.setVDC119(bateria.getVDC119());
        Formato.setVDC120(bateria.getVDC120());
        Formato.setVAC1(bateria.getVAC1());
        Formato.setVAC2(bateria.getVAC2());
        Formato.setVAC3(bateria.getVAC3());
        Formato.setVAC4(bateria.getVAC4());
        Formato.setVAC5(bateria.getVAC5());
        Formato.setVAC6(bateria.getVAC6());
        Formato.setVAC7(bateria.getVAC7());
        Formato.setVAC8(bateria.getVAC8());
        Formato.setVAC9(bateria.getVAC9());
        Formato.setVAC10(bateria.getVAC10());
        Formato.setVAC11(bateria.getVAC11());
        Formato.setVAC12(bateria.getVAC12());
        Formato.setVAC13(bateria.getVAC13());
        Formato.setVAC14(bateria.getVAC14());
        Formato.setVAC15(bateria.getVAC15());
        Formato.setVAC16(bateria.getVAC16());
        Formato.setVAC17(bateria.getVAC17());
        Formato.setVAC18(bateria.getVAC18());
        Formato.setVAC19(bateria.getVAC19());
        Formato.setVAC20(bateria.getVAC20());
        Formato.setVAC21(bateria.getVAC21());
        Formato.setVAC22(bateria.getVAC22());
        Formato.setVAC23(bateria.getVAC23());
        Formato.setVAC24(bateria.getVAC24());
        Formato.setVAC25(bateria.getVAC25());
        Formato.setVAC26(bateria.getVAC26());
        Formato.setVAC27(bateria.getVAC27());
        Formato.setVAC28(bateria.getVAC28());
        Formato.setVAC29(bateria.getVAC29());
        Formato.setVAC30(bateria.getVAC30());
        Formato.setVAC31(bateria.getVAC31());
        Formato.setVAC32(bateria.getVAC32());
        Formato.setVAC33(bateria.getVAC33());
        Formato.setVAC34(bateria.getVAC34());
        Formato.setVAC35(bateria.getVAC35());
        Formato.setVAC36(bateria.getVAC36());
        Formato.setVAC37(bateria.getVAC37());
        Formato.setVAC38(bateria.getVAC38());
        Formato.setVAC39(bateria.getVAC39());
        Formato.setVAC40(bateria.getVAC40());
        Formato.setVAC41(bateria.getVAC41());
        Formato.setVAC42(bateria.getVAC42());
        Formato.setVAC43(bateria.getVAC43());
        Formato.setVAC44(bateria.getVAC44());
        Formato.setVAC45(bateria.getVAC45());
        Formato.setVAC46(bateria.getVAC46());
        Formato.setVAC47(bateria.getVAC47());
        Formato.setVAC48(bateria.getVAC48());
        Formato.setVAC49(bateria.getVAC49());
        Formato.setVAC50(bateria.getVAC50());
        Formato.setVAC51(bateria.getVAC51());
        Formato.setVAC52(bateria.getVAC52());
        Formato.setVAC53(bateria.getVAC53());
        Formato.setVAC54(bateria.getVAC54());
        Formato.setVAC55(bateria.getVAC55());
        Formato.setVAC56(bateria.getVAC56());
        Formato.setVAC57(bateria.getVAC57());
        Formato.setVAC58(bateria.getVAC58());
        Formato.setVAC59(bateria.getVAC59());
        Formato.setVAC60(bateria.getVAC60());
        Formato.setVAC61(bateria.getVAC61());
        Formato.setVAC62(bateria.getVAC62());
        Formato.setVAC63(bateria.getVAC63());
        Formato.setVAC64(bateria.getVAC64());
        Formato.setVAC65(bateria.getVAC65());
        Formato.setVAC66(bateria.getVAC66());
        Formato.setVAC67(bateria.getVAC67());
        Formato.setVAC68(bateria.getVAC68());
        Formato.setVAC69(bateria.getVAC69());
        Formato.setVAC70(bateria.getVAC70());
        Formato.setVAC71(bateria.getVAC71());
        Formato.setVAC72(bateria.getVAC72());
        Formato.setVAC73(bateria.getVAC73());
        Formato.setVAC74(bateria.getVAC74());
        Formato.setVAC75(bateria.getVAC75());
        Formato.setVAC76(bateria.getVAC76());
        Formato.setVAC77(bateria.getVAC77());
        Formato.setVAC78(bateria.getVAC78());
        Formato.setVAC79(bateria.getVAC79());
        Formato.setVAC80(bateria.getVAC80());
        Formato.setVAC81(bateria.getVAC81());
        Formato.setVAC82(bateria.getVAC82());
        Formato.setVAC83(bateria.getVAC83());
        Formato.setVAC84(bateria.getVAC84());
        Formato.setVAC85(bateria.getVAC85());
        Formato.setVAC86(bateria.getVAC86());
        Formato.setVAC87(bateria.getVAC87());
        Formato.setVAC88(bateria.getVAC88());
        Formato.setVAC89(bateria.getVAC89());
        Formato.setVAC90(bateria.getVAC90());
        Formato.setVAC91(bateria.getVAC91());
        Formato.setVAC92(bateria.getVAC92());
        Formato.setVAC93(bateria.getVAC93());
        Formato.setVAC94(bateria.getVAC94());
        Formato.setVAC95(bateria.getVAC95());
        Formato.setVAC96(bateria.getVAC96());
        Formato.setVAC97(bateria.getVAC97());
        Formato.setVAC98(bateria.getVAC98());
        Formato.setVAC99(bateria.getVAC99());
        Formato.setVAC100(bateria.getVAC100());
        Formato.setVAC101(bateria.getVAC101());
        Formato.setVAC102(bateria.getVAC102());
        Formato.setVAC103(bateria.getVAC103());
        Formato.setVAC104(bateria.getVAC104());
        Formato.setVAC105(bateria.getVAC105());
        Formato.setVAC106(bateria.getVAC106());
        Formato.setVAC107(bateria.getVAC107());
        Formato.setVAC108(bateria.getVAC108());
        Formato.setVAC109(bateria.getVAC109());
        Formato.setVAC110(bateria.getVAC110());
        Formato.setVAC111(bateria.getVAC111());
        Formato.setVAC112(bateria.getVAC112());
        Formato.setVAC113(bateria.getVAC113());
        Formato.setVAC114(bateria.getVAC114());
        Formato.setVAC115(bateria.getVAC115());
        Formato.setVAC116(bateria.getVAC116());
        Formato.setVAC117(bateria.getVAC117());
        Formato.setVAC118(bateria.getVAC118());
        Formato.setVAC119(bateria.getVAC119());
        Formato.setVAC120(bateria.getVAC120());
        Formato.setModeloMarca(bateria.getModeloMarca());
        Formato.setAparenciaLimpieza(bateria.getAparenciaLimpieza());
        Formato.setCodigoFecha(bateria.getCodigoFecha());
        Formato.setAñosServicio(bateria.getAñosServicio());
        Formato.setConectoresTornillos(bateria.getConectoresTornillos());
        Formato.setJarrasCubiertasSellado(bateria.getJarrasCubiertasSellado());
        Formato.setVoltajeFlotacionVDC(bateria.getVoltajeFlotacionVDC());
        Formato.setTemperaturaBaterias(bateria.getTemperaturaBaterias());
        Formato.setCorrienteFlotacion(bateria.getCorrienteFlotacion());
        Formato.setTemperaturaAmbiente(bateria.getTemperaturaAmbiente());
        Formato.setCorrienteRizo(bateria.getCorrienteRizo());
        Formato.setTorque(bateria.getTorque());
        Formato.setVoltajeRizo(bateria.getVoltajeRizo());
        Formato.setTerminales(bateria.getTerminales());
        Formato.setComentarios(bateria.getCOMENT_cometarios());
        Formato.setPMUCantidad1(bateria.getMATE_cantidad1());
        Formato.setPMUCantidad2(bateria.getMATE_cantidad2());
        Formato.setPMUCantidad3(bateria.getMATE_cantidad3());
        Formato.setPMUCantidad4(bateria.getMATE_cantidad4());
        Formato.setPMUCantidad5(bateria.getMATE_cantidad5());
        Formato.setPMUCantidad6(bateria.getMATE_cantidad6());
        Formato.setPMUCantidad7(bateria.getMATE_cantidad7());
        Formato.setPMUNParte1(bateria.getMATE_parte1());
        Formato.setPMUNParte2(bateria.getMATE_parte2());
        Formato.setPMUNParte3(bateria.getMATE_parte3());
        Formato.setPMUNParte4(bateria.getMATE_parte4());
        Formato.setPMUNParte5(bateria.getMATE_parte5());
        Formato.setPMUNParte6(bateria.getMATE_parte6());
        Formato.setPMUNParte7(bateria.getMATE_parte7());
        Formato.setPMUDescripcion1(bateria.getMATE_especifica1());
        Formato.setPMUDescripcion2(bateria.getMATE_especifica2());
        Formato.setPMUDescripcion3(bateria.getMATE_especifica3());
        Formato.setPMUDescripcion4(bateria.getMATE_especifica4());
        Formato.setPMUDescripcion5(bateria.getMATE_especifica5());
        Formato.setPMUDescripcion6(bateria.getMATE_especifica6());
        Formato.setPMUDescripcion7(bateria.getMATE_especifica7());
        Formato.setEMEquipo1(bateria.getMATE_equipo1());
        Formato.setEMEquipo2(bateria.getMATE_equipo2());
        Formato.setEMEquipo3(bateria.getMATE_equipo3());
        Formato.setEMEquipo4(bateria.getMATE_equipo4());
        Formato.setEMEquipo5(bateria.getMATE_equipo5());
        Formato.setEMEquipo6(bateria.getMATE_equipo6());
        //Formato.setEMEquipo7(bateria.getequipo7);
        Formato.setNoID1(bateria.getMATE_nid1());
        Formato.setNoID2(bateria.getMATE_nid2());
        Formato.setNoID3(bateria.getMATE_nid3());
        Formato.setNoID4(bateria.getMATE_nid4());
        Formato.setNoID5(bateria.getMATE_nid5());
        Formato.setNoID6(bateria.getMATE_nid6());
        // Formato.setNoID7(bateria.getn);
        Formato.setFechaVencimiento1(bateria.getMATE_fecha1());
        Formato.setFechaVencimiento2(bateria.getMATE_fecha2());
        Formato.setFechaVencimiento3(bateria.getMATE_fecha3());
        Formato.setFechaVencimiento4(bateria.getMATE_fecha4());
        Formato.setFechaVencimiento5(bateria.getMATE_fecha5());
        Formato.setFechaVencimiento6(bateria.getMATE_fecha6());
        //Formato.setFechaVencimiento7(bateria.ge);
        /*
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
        */

        Formato.setFirmaCliente(bateria.getFIRMA_IMG1());
        Formato.setFirmaVertiv(bateria.getFIRMA_IMG2());
        Formato.setFirmaClienteFinal(bateria.getFIRMA_IMG3());
        //endregion
        compositeDisposable.add(iMyAPI.enviarBaterias(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BateriaS>() {
                    @Override
                    public void accept(BateriaS lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
                                item.setFormato(Formato.getAntesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
                                item.setFormato(Formato.getAntesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
                                item.setFormato(Formato.getAntesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
                                item.setFormato(Formato.getAntesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
                                item.setFormato(Formato.getAntesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
                                item.setFormato(Formato.getAntesFoto6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
                                item.setFormato(Formato.getDespuesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
                                item.setFormato(Formato.getDespuesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
                                item.setFormato(Formato.getDespuesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
                                item.setFormato(Formato.getDespuesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
                                item.setFormato(Formato.getDespuesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
                                item.setFormato(Formato.getDespuesFoto6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion

                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );

    }
    public void formato3(final String id_formato){
        DCPower dcPower = D_B.obtenerDcPower_id(id_formato);
        DCPower2 dcPower2= D_B.obtenerDcPower2_id(id_formato);


        final com.maju.desarrollo.testfcs.ServiceClass.DCPower Formato = new com.maju.desarrollo.testfcs.ServiceClass.DCPower();
        //region
        if(dcPower.getGRAL_PROYECTO().length()>1){ Formato.setSRProyecto(dcPower.getGRAL_PROYECTO());}
        else {Formato.setSRProyecto(dcPower.getGRAL_SR());}
        final String idFormatoService = dcPower.getGRAL_FOLIO_PRETRABAJO();//D_B.generarUID();
        Formato.setIdDCPower(idFormatoService);

        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setIdCliente(dcPower.getGRAL_CLIENTE().toString());
        Formato.setFolioPreTrabajo(dcPower.getGRAL_FOLIO_PRETRABAJO());
        Formato.setFechaInicio(dcPower.getGRAL_FECHA_INICIO());
        Formato.setFechaTermino(dcPower.getGRAL_FECHA_FIN());
        Formato.setContactoCliente(dcPower.getGRAL_CONTACTO());
        Formato.setTASK(dcPower.getGRAL_TASK());
        Formato.setTelefono(dcPower.getGRAL_TELEFONO());
        Formato.setTipoServicio(dcPower.getGRAL_TIPO_SERVICIO());
        Formato.setEMail(dcPower.getGRAL_MAIL());
        Formato.setFreecuencia(dcPower.getGRAL_FRECUENCIA());
        Formato.setModeloEquipo(dcPower.getGRAL_MODELO());
        Formato.setNoTag(dcPower.getGRAL_NTAG());
        Formato.setNoSerie(dcPower.getGRAL_SERIE());
        Formato.setDireccionSitio(dcPower.getGRAL_DIRECCION());

        //Modulo 2
        Formato.setPGSDCTipo(dcPower.getPARAM_GRALS_TIPO());
        Formato.setPGSDCNoEspecificacion(dcPower.getPARAM_GRALS_N_ESPECIFICAION());
        Formato.setPGSDCCapacidad(dcPower.getPARAM_GRALS_CAPACIDAD());
        Formato.setPGSDCConfiguracion(dcPower.getPARAM_GRALS_CONFIGURACION());
        Formato.setPGSDCCargaActual(dcPower.getPARAM_GRALS_CARGA_ACT());
        Formato.setPGIRModelo(dcPower.getPARAM_INV_RECTIFICADORES_MODELO());
        Formato.setPGIRNoEspecificacion(dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION());
        Formato.setPGIRCapacidad(dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD());
        Formato.setNoSerieR1(dcPower.getPARAM_INV_RECTIFICADORES_R1());
        Formato.setNoSerieR2(dcPower.getPARAM_INV_RECTIFICADORES_R2());
        Formato.setNoSerieR3(dcPower.getPARAM_INV_RECTIFICADORES_R3());
        Formato.setNoSerieR4(dcPower.getPARAM_INV_RECTIFICADORES_R4());
        Formato.setNoSerieR5(dcPower.getPARAM_INV_RECTIFICADORES_R5());
        Formato.setNoSerieR6(dcPower.getPARAM_INV_RECTIFICADORES_R6());
        Formato.setNoSerieR7(dcPower.getPARAM_INV_RECTIFICADORES_R7());
        Formato.setNoSerieR8(dcPower.getPARAM_INV_RECTIFICADORES_R8());
        Formato.setNoSerieR9(dcPower.getPARAM_INV_RECTIFICADORES_R9());
        Formato.setNoSerieR10(dcPower.getPARAM_INV_RECTIFICADORES_R10());
        Formato.setNoSerieR11(dcPower.getPARAM_INV_RECTIFICADORES_R11());
        Formato.setNoSerieR12(dcPower.getPARAM_INV_RECTIFICADORES_R12());
        Formato.setNoSerieR13(dcPower.getPARAM_INV_RECTIFICADORES_R13());
        Formato.setNoSerieR14(dcPower.getPARAM_INV_RECTIFICADORES_R14());
        Formato.setNoSerieR15(dcPower.getPARAM_INV_RECTIFICADORES_R15());
        Formato.setNoSerieR16(dcPower.getPARAM_INV_RECTIFICADORES_R16());
        Formato.setNoSerieR17(dcPower.getPARAM_INV_RECTIFICADORES_R17());
        Formato.setNoSerieR18(dcPower.getPARAM_INV_RECTIFICADORES_R18());
        Formato.setNoSerieR19(dcPower.getPARAM_INV_RECTIFICADORES_R19());
        Formato.setNoSerieR20(dcPower.getPARAM_INV_RECTIFICADORES_R20());
        Formato.setNoSerieR21(dcPower.getPARAM_INV_RECTIFICADORES_R21());
        Formato.setNoSerieR22(dcPower.getPARAM_INV_RECTIFICADORES_R22());
        Formato.setNoSerieR23(dcPower.getPARAM_INV_RECTIFICADORES_R23());
        Formato.setNoSerieR24(dcPower.getPARAM_INV_RECTIFICADORES_R24());
        Formato.setNoSerieR25(dcPower.getPARAM_INV_RECTIFICADORES_R25());
        Formato.setNoSerieR26(dcPower.getPARAM_INV_RECTIFICADORES_R26());
        Formato.setNoSerieR27(dcPower.getPARAM_INV_RECTIFICADORES_R27());
        Formato.setNoSerieR28(dcPower.getPARAM_INV_RECTIFICADORES_R28());
        Formato.setNoSerieR29(dcPower.getPARAM_INV_RECTIFICADORES_R29());
        Formato.setNoSerieR30(dcPower.getPARAM_INV_RECTIFICADORES_R30());
        Formato.setNoSerieR31(dcPower.getPARAM_INV_RECTIFICADORES_R31());
        Formato.setNoSerieR32(dcPower.getPARAM_INV_RECTIFICADORES_R32());
//PGIGRPDC(dcPower.getPARAM_INV_GABINETES_NA());
        Formato.setEspecificacionesGabinete(dcPower.getPARAM_INV_GABINETES_ESPECIFIC());
        Formato.setNoSerieGab1(dcPower.getPARAM_INV_GABINETES_GAB1());
        Formato.setNoSerieGab2(dcPower.getPARAM_INV_GABINETES_GAB2());
        Formato.setNoSerieGab3(dcPower.getPARAM_INV_GABINETES_GAB3());
        Formato.setNoSerieGab4(dcPower.getPARAM_INV_GABINETES_GAB4());
        Formato.setNoSerieGab5(dcPower.getPARAM_INV_GABINETES_GAB5());
        Formato.setNoSerieGab6(dcPower.getPARAM_INV_GABINETES_GAB6());
//VISPTVSSTAF(dcPower.getPARAM_SUPRESOR_NA());
        Formato.setVISPTVSSTAFModelo(dcPower.getPARAM_SUPRESOR_MODELO());
        Formato.setVISPTVSSTAFMarca(dcPower.getPARAM_SUPRESOR_MARCA());
        Formato.setInstalacionMecanicaGabineteTVSS(dcPower.getPARAM_SUPRESOR_INSTALAC());
        Formato.setCalibreCableadoConexionTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION());
        Formato.setCapacidadbreakersConexionTableroAc(dcPower.getPARAM_SUPRESOR_CAPACIDAD());
        Formato.setCalibreCableTierrasTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS());
        Formato.setPAPFDCVoltajeAlimentacion(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1());
        Formato.setPAPFDCVoltajeFaseAB(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2());
        Formato.setPAPFDCVoltajeFaseBC(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3());
        Formato.setPAPFDCVoltajeFaseCA(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4());
        Formato.setPAPFDCVoltajeFaseAN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5());
        Formato.setPAPFDCVoltajeFaseBN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6());
        Formato.setPAPFDCVoltajeFaseCN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7());
        Formato.setPAPFDCVoltajeNeutroGND(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8());
        Formato.setPOPFDCVoltajeFlotacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M1());
        Formato.setPOPFDCVoltajeIgualacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M2());
        Formato.setPOPFDCAlarmaBajoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M3());
        Formato.setPOPFDCAlarmaBajoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M4());
        Formato.setPOPFDCDesconexionBajoVoltaje(dcPower.getPARAM_AyO_PLANTA_OPERAC_M5());
        Formato.setPOPFDCAlarmaAltoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M6());
        Formato.setPOPFDCAlarmaAltoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M7());
        Formato.setPOPFDCAlarmaFusibleCargaAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M8());
        Formato.setPOPFDCAlarmaFusibleBateriasAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M9());
        Formato.setPOPFDCAlarmaFallaRectificador(dcPower.getPARAM_AyO_PLANTA_OPERAC_M10());
        Formato.setLimpiezaInternaExternaComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA());
        Formato.setLimpiezaIndividualComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV());
        Formato.setVerificacionTorquesComen(dcPower.getPARAM_AyO_PLANTA_VERIFICAION());
//STPFDC(dcPower.getPARAM_STPF_DC_NA());
        Formato.setVerificacionConexionesTerminalesComen(dcPower.getPARAM_STPF_DC_CAMP1());
        Formato.setDebeSerZaptaComen(dcPower.getPARAM_STPF_DC_CAMP2());
        Formato.setVerificacionEtiquetadoTierrasComen(dcPower.getPARAM_STPF_DC_CAMP3());
        Formato.setVerificacionCableadoExistenteTierrasComen(dcPower.getPARAM_STPF_DC_CAMP4());
        Formato.setMidaResistenciaComen(dcPower.getPARAM_STPF_DC_CAMP5());
        Formato.setCablesGabineteComen(dcPower.getPARAM_STPF_DC_CAMP6());
        Formato.setCableadoEstantesComen(dcPower.getPARAM_STPF_DC_CAMP7());
        Formato.setRetireTapasEquiposComen(dcPower.getPARAM_ITPF_DC_CAMP1());
        Formato.setVisualmenteInspeccionComen(dcPower.getPARAM_ITPF_DC_CAMP2());
        Formato.setAyudaCamaraTermograficaComen(dcPower.getPARAM_ITPF_DC_CAMP3());
        Formato.setEtapaRectificadoresComen(dcPower.getPARAM_ITPF_DC_CAMP4());
        Formato.setPanelGabineteACComen(dcPower.getPARAM_ITPF_DC_CAMP5());
        Formato.setPanelGabineteDCComen(dcPower.getPARAM_ITPF_DC_CAMP6());
        Formato.setCableadoAlimentacionComen(dcPower.getPARAM_ITPF_DC_CAMP7());
        Formato.setPGSDDCModelo(dcPower.getPARAM_SISTEMA_DIST_MODELO());
        Formato.setPGSDDCMarca(dcPower.getPARAM_SISTEMA_DIST_MARCA());
        Formato.setPGSDDCNoEspecifico(dcPower.getPARAM_SISTEMA_DIST_ESPECIFI());
        Formato.setPGSDDCCapacidad(dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD());
        Formato.setPGSDDCConfiguracion(dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION());
        Formato.setLimpiezaGabinete(dcPower.getPARAM_IDR_CAMP1());
        Formato.setVerificacionEstado(dcPower.getPARAM_IDR_CAMP2());
        Formato.setVerificarGabinete(dcPower.getPARAM_IDR_CAMP3());
        Formato.setVerificarTorque(dcPower.getPARAM_IDR_CAMP4());
        Formato.setVerificarTorqueCargasGabinete(dcPower.getPARAM_IDR_CAMP5());
        Formato.setVerificarModulos(dcPower.getPARAM_IDR_CAMP6());
        Formato.setValoresReales(dcPower.getPARAM_IDR_CAMP7());
        Formato.setBreakers1(dcPower.getPARAM_IDR_BREAK1());
        Formato.setBreakers2(dcPower.getPARAM_IDR_BREAK2());
        Formato.setBreakers3(dcPower.getPARAM_IDR_BREAK3());
        Formato.setBreakers4(dcPower.getPARAM_IDR_BREAK4());
        Formato.setBreakers5(dcPower.getPARAM_IDR_BREAK5());
        Formato.setBreakers6(dcPower.getPARAM_IDR_BREAK6());
        Formato.setBreakers7(dcPower.getPARAM_IDR_BREAK7());
        Formato.setBreakers8(dcPower.getPARAM_IDR_BREAK8());
        Formato.setPGSIModelo(dcPower.getPARAM_SISTEMA_INVER_MODELO());
        Formato.setPGSIMarca(dcPower.getPARAM_SISTEMA_INVER_MARCA());
        Formato.setPGSINoEspecificacion(dcPower.getPARAM_SISTEMA_INVER_ESPEC());
        Formato.setPGSICapacidad(dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD());
        Formato.setPGSIConfiguracion(dcPower.getPARAM_SISTEMA_INVER_CONFG());
        Formato.setNoSerieLineaAINV1(dcPower.getPARAM_SISTEMA_INVER_A_INV1());
        Formato.setNoSerieLineaAINV2(dcPower.getPARAM_SISTEMA_INVER_A_INV2());
        Formato.setNoSerieLineaAINV3(dcPower.getPARAM_SISTEMA_INVER_A_INV3());
        Formato.setNoSerieLineaAINV4(dcPower.getPARAM_SISTEMA_INVER_A_INV4());
        Formato.setNoSerieLineaAINV5(dcPower.getPARAM_SISTEMA_INVER_A_INV5());
        Formato.setNoSerieLineaAINV6(dcPower.getPARAM_SISTEMA_INVER_A_INV6());
        Formato.setNoSerieLineaAINV7(dcPower.getPARAM_SISTEMA_INVER_A_INV7());
        Formato.setNoSerieLineaAINV8(dcPower.getPARAM_SISTEMA_INVER_A_INV8());
        Formato.setNoSerieLineaAINV9(dcPower.getPARAM_SISTEMA_INVER_A_INV9());
        Formato.setNoSerieLineaAINV10(dcPower.getPARAM_SISTEMA_INVER_A_INV10());
        Formato.setNoSerieLineaBINV1(dcPower.getPARAM_SISTEMA_INVER_B_INV1());
        Formato.setNoSerieLineaBINV2(dcPower.getPARAM_SISTEMA_INVER_B_INV2());
        Formato.setNoSerieLineaBINV3(dcPower.getPARAM_SISTEMA_INVER_B_INV3());
        Formato.setNoSerieLineaBINV4(dcPower.getPARAM_SISTEMA_INVER_B_INV4());
        Formato.setNoSerieLineaBINV5(dcPower.getPARAM_SISTEMA_INVER_B_INV5());
        Formato.setNoSerieLineaBINV6(dcPower.getPARAM_SISTEMA_INVER_B_INV6());
        Formato.setNoSerieLineaBINV7(dcPower.getPARAM_SISTEMA_INVER_B_INV7());
        Formato.setNoSerieLineaBINV8(dcPower.getPARAM_SISTEMA_INVER_B_INV8());
        Formato.setNoSerieLineaBINV9(dcPower.getPARAM_SISTEMA_INVER_B_INV9());
        Formato.setNoSerieLineaBINV10(dcPower.getPARAM_SISTEMA_INVER_B_INV10());
        Formato.setNoSerieLineaCINV1(dcPower.getPARAM_SISTEMA_INVER_C_INV1());
        Formato.setNoSerieLineaCINV2(dcPower.getPARAM_SISTEMA_INVER_C_INV2());
        Formato.setNoSerieLineaCINV3(dcPower.getPARAM_SISTEMA_INVER_C_INV3());
        Formato.setNoSerieLineaCINV4(dcPower.getPARAM_SISTEMA_INVER_C_INV4());
        Formato.setNoSerieLineaCINV5(dcPower.getPARAM_SISTEMA_INVER_C_INV5());
        Formato.setNoSerieLineaCINV6(dcPower.getPARAM_SISTEMA_INVER_C_INV6());
        Formato.setNoSerieLineaCINV7(dcPower.getPARAM_SISTEMA_INVER_C_INV7());
        Formato.setNoSerieLineaCINV8(dcPower.getPARAM_SISTEMA_INVER_C_INV8());
        Formato.setNoSerieLineaCINV9(dcPower.getPARAM_SISTEMA_INVER_C_INV9());
        Formato.setNoSerieLineaCINV10(dcPower.getPARAM_SISTEMA_INVER_C_INV10());
        Formato.setVerificarManeraVisualComen(dcPower.getPARAM_SISTEMA_INVER_VERI1());
        Formato.setVerificarReportarExistenciaComen(dcPower.getPARAM_SISTEMA_INVER_VERI2());
        Formato.setVerificacionBuenEstadoComen(dcPower.getPARAM_SISTEMA_INVER_VERI3());
        Formato.setVerificarRegistrarCargaComen(dcPower.getPARAM_SISTEMA_INVER_VERI4());
        Formato.setLimpiezaGabineteInversor(dcPower.getPARAM_SISTEMA_INVER_LIMP());
        Formato.setExtraerModuloCom(dcPower2.getCampoextra1());
        Formato.setColocarModuloInversorPosicionOriginalCom(dcPower2.getCampoextra2());
        Formato.setVerificarGabineteConectadoSistemaTierrasCom(dcPower2.getCampoextra3());
        Formato.setVerificarTorqueCableadoEntradaSalidaSistemaInversorcom(dcPower2.getCampoextra4());
        Formato.setExtrerComputadoraInventarioSistemaCom(dcPower2.getCampoextra5());
        Formato.setVerificarModulosSupervisionSistemaCom(dcPower2.getCampoextra6());
        Formato.setVoltajeFaseABVac(dcPower.getPARAM_NAyS_ALIM_VAC1());
        Formato.setVoltajeFaseBCVac(dcPower.getPARAM_NAyS_ALIM_VAC2());
        Formato.setVoltajeFaseCAVac(dcPower.getPARAM_NAyS_ALIM_VAC3());
        Formato.setVoltajeFaseANVac(dcPower.getPARAM_NAyS_ALIM_VAC4());
        Formato.setVoltajeFaseBNVac(dcPower.getPARAM_NAyS_ALIM_VAC5());
        Formato.setVoltajeFaseCNVac(dcPower.getPARAM_NAyS_ALIM_VAC6());
        Formato.setVoltajeNeutroVac(dcPower.getPARAM_NAyS_ALIM_VAC7());
        Formato.setVoltajeAlimentacionDCVAC(dcPower.getPARAM_NAyS_ALIM_VAC8());
        Formato.setVoltajeFaseABSVac(dcPower.getPARAM_NAyS_SALID_VAC1());
        Formato.setVoltajeFaseBCSVac(dcPower.getPARAM_NAyS_SALID_VAC2());
        Formato.setVoltajeFaseCASVac(dcPower.getPARAM_NAyS_SALID_VAC3());
        Formato.setVoltajeFaseANSVac(dcPower.getPARAM_NAyS_SALID_VAC4());
        Formato.setVoltajeFaseBNSVac(dcPower.getPARAM_NAyS_SALID_VAC5());
        Formato.setVoltajeFaseCNSVac(dcPower.getPARAM_NAyS_SALID_VAC6());
        Formato.setVoltajeNeutroSVac(dcPower.getPARAM_NAyS_SALID_VAC7());
        Formato.setVoltajeFaseABACA(dcPower.getPARAM_NAyS_ALIM_ACA1());
        Formato.setVoltajeFaseBCACA(dcPower.getPARAM_NAyS_ALIM_ACA2());
        Formato.setVoltajeFaseCAACA(dcPower.getPARAM_NAyS_ALIM_ACA3());
        Formato.setVoltajeFaseANACA(dcPower.getPARAM_NAyS_ALIM_ACA4());
        Formato.setVoltajeFaseBNACA(dcPower.getPARAM_NAyS_ALIM_ACA5());
        Formato.setVoltajeFaseCNACA(dcPower.getPARAM_NAyS_ALIM_ACA6());
        Formato.setVoltajeNeutroACA(dcPower.getPARAM_NAyS_ALIM_ACA7());
        Formato.setVoltajeAlimentacionDCACA(dcPower.getPARAM_NAyS_ALIM_ACA8());
        Formato.setVoltajeFaseABSACA(dcPower.getPARAM_NAyS_SALID_ACA1());
        Formato.setVoltajeFaseBCSACA(dcPower.getPARAM_NAyS_SALID_ACA2());
        Formato.setVoltajeFaseCASACA(dcPower.getPARAM_NAyS_SALID_ACA3());
        Formato.setVoltajeFaseANSACA(dcPower.getPARAM_NAyS_SALID_ACA4());
        Formato.setVoltajeFaseBNSACA(dcPower.getPARAM_NAyS_SALID_ACA5());
        Formato.setVoltajeFaseCNSACA(dcPower.getPARAM_NAyS_SALID_ACA6());
        Formato.setVoltajeNeutroSACA(dcPower.getPARAM_NAyS_SALID_ACA7());



        //Modulo 3
        Formato.setActividad(dcPower2.getCOMENTA_ACTIVIDADES());
        Formato.setDiagnostico(dcPower2.getCOMENTA_DIAGNOSTICO());
        Formato.setAccionCorrectiva(dcPower2.getCOMENTA_ACCION());
        Formato.setComentariosRecomendaciones(dcPower2.getCOMENTA_COMENTARIOS());

        //Modulo 4
        Formato.setCantidad1(dcPower2.getMATERIA_CANTIDAD1());
        Formato.setCantidad2(dcPower2.getMATERIA_CANTIDAD2());
        Formato.setCantidad3(dcPower2.getMATERIA_CANTIDAD3());
        Formato.setCantidad4(dcPower2.getMATERIA_CANTIDAD4());
        Formato.setCantidad5(dcPower2.getMATERIA_CANTIDAD5());
        Formato.setCantidad6(dcPower2.getMATERIA_CANTIDAD6());
        Formato.setCantidad7(dcPower2.getMATERIA_CANTIDAD7());
        Formato.setNoParte1(dcPower2.getMATERIA_N_PARTE1());
        Formato.setNoParte2(dcPower2.getMATERIA_N_PARTE2());
        Formato.setNoParte3(dcPower2.getMATERIA_N_PARTE3());
        Formato.setNoParte4(dcPower2.getMATERIA_N_PARTE4());
        Formato.setNoParte5(dcPower2.getMATERIA_N_PARTE5());
        Formato.setNoParte6(dcPower2.getMATERIA_N_PARTE6());
        Formato.setNoParte7(dcPower2.getMATERIA_N_PARTE7());
        Formato.setDescripcion1(dcPower2.getMATERIA_DESC1());
        Formato.setDescripcion2(dcPower2.getMATERIA_DESC2());
        Formato.setDescripcion3(dcPower2.getMATERIA_DESC3());
        Formato.setDescripcion4(dcPower2.getMATERIA_DESC4());
        Formato.setDescripcion5(dcPower2.getMATERIA_DESC5());
        Formato.setDescripcion6(dcPower2.getMATERIA_DESC6());
        Formato.setDescripcion7(dcPower2.getMATERIA_DESC7());
        Formato.setEquipo1(dcPower2.getMATERIA_EQUIPO_EQUIPO1());
        Formato.setEquipo2(dcPower2.getMATERIA_EQUIPO_EQUIPO2());
        Formato.setEquipo3(dcPower2.getMATERIA_EQUIPO_EQUIPO3());
        Formato.setEquipo4(dcPower2.getMATERIA_EQUIPO_EQUIPO4());
        Formato.setEquipo5(dcPower2.getMATERIA_EQUIPO_EQUIPO5());
        //Formato.setEMEquipo6(dcPower2.getMATERIA_EQUIPO_EQUIPO6());
        //Formato.setEMEquipo7(dcPower2.getequipo7);
        Formato.setNoId1(dcPower2.getMATERIA_EQUIPO_ID1());
        Formato.setNoId2(dcPower2.getMATERIA_EQUIPO_ID2());
        Formato.setNoId3(dcPower2.getMATERIA_EQUIPO_ID3());
        Formato.setNoId4(dcPower2.getMATERIA_EQUIPO_ID4());
        Formato.setNoId5(dcPower2.getMATERIA_EQUIPO_ID5());
        //Formato.setNoID6(dcPower2.getMATERIA_EQUIPO_ID6());
        // Formato.setNoID7(dcPower2.getn);
        Formato.setFechaVencimiento1(dcPower2.getMATERIA_EQUIPO_FEVHA1());
        Formato.setFechaVencimiento2(dcPower2.getMATERIA_EQUIPO_FEVHA2());
        Formato.setFechaVencimiento3(dcPower2.getMATERIA_EQUIPO_FEVHA3());
        Formato.setFechaVencimiento4(dcPower2.getMATERIA_EQUIPO_FEVHA4());
        Formato.setFechaVencimiento5(dcPower2.getMATERIA_EQUIPO_FEVHA5());
        //Formato.setFechaVencimiento6(dcPower2.getMATERIA_EQUIPO_FEVHA6());
        //Formato.setFechaVencimiento7(cdPower2ge);

        //Modulo 5
        /*
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
        */

        //Modulo 7
        Formato.setFirmaCliente(dcPower2.getFIRMA_FIRMA1());
        Formato.setFirmaVertiv(dcPower2.getFIRMA_FIRMA2());
        Formato.setFirmaClienteFinal(dcPower2.getFIRMA_FIRMA3());
        Formato.setNombreFirmaCliente(dcPower2.getFIRMA_NOMBRE1());
        Formato.setNombreFirmaVertiv(dcPower2.getFIRMA_NOMBRE2());
        Formato.setNombreFirmaClienteFinal(dcPower2.getFIRMA_NOMBRE3());
        Formato.setCLIENTEFINAL_EMPRESA(dcPower2.getFIRMA_EMPRESA());
        Formato.setCLIENTEFINAL_TELEFONO(dcPower2.getFIRMA_TELEFONO());
        Formato.setCLIENTEFINAL_CORREO(dcPower2.getFIRMA_CORREO());

        //Modulo 8
        Formato.setAD_NOMBRE1(dcPower2.getADICIONALES_NOMBRE1());
        Formato.setAD_CORREO1(dcPower2.getFIRMA_CORREO1());
        Formato.setAD_NOMBRE2(dcPower2.getADICIONALES_NOMBRE2());
        Formato.setAD_CORREO2(dcPower2.getFIRMA_CORREO2());
        Formato.setAD_NOMBRE3(dcPower2.getADICIONALES_NOMBRE3());
        Formato.setAD_CORREO3(dcPower2.getFIRMA_CORREO3());
        Formato.setAD_NOMBRE4(dcPower2.getADICIONALES_NOMBRE4());
        Formato.setAD_CORREO4(dcPower2.getFIRMA_CORREO4());
        Formato.setAD_NOMBRE5(dcPower2.getADICIONALES_NOMBRE5());
        Formato.setAD_CORREO5(dcPower2.getFIRMA_CORREO5());
        //endregion
        compositeDisposable.add(iMyAPI.enviarDCPower(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<com.maju.desarrollo.testfcs.ServiceClass.DCPower>() {
                    @Override
                    public void accept(com.maju.desarrollo.testfcs.ServiceClass.DCPower lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
                                item.setFormato(Formato.getAntesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
                                item.setFormato(Formato.getAntesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
                                item.setFormato(Formato.getAntesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
                                item.setFormato(Formato.getAntesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
                                item.setFormato(Formato.getAntesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
                                item.setFormato(Formato.getAntesFoto6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
                                item.setFormato(Formato.getDespuesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
                                item.setFormato(Formato.getDespuesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
                                item.setFormato(Formato.getDespuesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
                                item.setFormato(Formato.getDespuesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
                                item.setFormato(Formato.getDespuesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
                                item.setFormato(Formato.getDespuesFoto6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })
        );
    }
    public void formato4(final String id_formato){
        final SGarantias Formato = D_B.obtenerGarantia_id(id_formato);

        final Garantia item = new Garantia();
        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{
            item.setSRProyecto(Formato.getSR());
        }

        //String  ArchivoGarantia= "imagen"+df.format(new Date().getTime()+".jpg");
        //Formato.setGarantiaArchivo(ArchivoGarantia);

        final String idFormatoService = Formato.getFolioPreTrabajo();//D_B.generarUID();
        item.setIdGarantia(idFormatoService);
        item.setFolioPreTrabajo(Formato.getFolioPreTrabajo());
        item.setFechaInicio(Formato.getFECHAINICIO());
        item.setFechaTermino(Formato.getFECHAFIN());
        item.setIdCliente(Formato.getCliente());
        //item.setSRProyecto(Formato.getSR());
        item.setTASK(Formato.getTASK());
        item.setContactoCliente(Formato.getContacto());
        item.setTipoServicio(Formato.getTIPOSERVICIO());
        item.setTelefono(Formato.getTeléfono());
        item.setFreecuencia(Formato.getFRECUENCIA());
        item.setEMail(Formato.getMail());
        item.setNoTag(Formato.getNTAG());
        item.setModeloEquipo(Formato.getMODELO());
        item.setNoSerie(Formato.getNSERIE());
        item.setDireccionSitio(Formato.getDirección());
        item.setTipoGararntia(Formato.getTIPOGARANTIA());
        item.setFolioCCC(Formato.getFOLIOCCC());
        item.setFehcaLote(Formato.getFECHALOTE());
        item.setLotePedimento(Formato.getLOTE());
        item.setAduana(Formato.getADUANA());
        item.setNuevoSERIAL(Formato.getNUEVOSERIAL());
        item.setReporteFalla(Formato.getREPORTEFALLA());
        item.setComentariosRecomendaciones(Formato.getCOMENTARIOS());
        item.setCantidad1(Formato.getCANTIDAD1());
        item.setCantidad2(Formato.getCANTIDAD2());
        item.setCantidad3(Formato.getCANTIDAD3());
        item.setCantidad4(Formato.getCANTIDAD4());
        item.setCantidad5(Formato.getCANTIDAD5());
        item.setCantidad6(Formato.getCANTIDAD6());
        item.setCantidad7(Formato.getCANTIDAD7());
        item.setCantidad8(Formato.getCANTIDAD8());
        item.setCantidad9(Formato.getCANTIDAD9());
        item.setNoParte1(Formato.getNPARTE1());
        item.setNoParte2(Formato.getNPARTE2());
        item.setNoParte3(Formato.getNPARTE3());
        item.setNoParte4(Formato.getNPARTE4());
        item.setNoParte5(Formato.getNPARTE5());
        item.setNoParte6(Formato.getNPARTE6());
        item.setNoParte7(Formato.getNPARTE7());
        item.setNoParte8(Formato.getNPARTE8());
        item.setNoParte9(Formato.getNPARTE9());
        item.setDescripcion1(Formato.getESPECIFICACION1());
        item.setDescripcion2(Formato.getESPECIFICACION2());
        item.setDescripcion3(Formato.getESPECIFICACION3());
        item.setDescripcion4(Formato.getESPECIFICACION4());
        item.setDescripcion5(Formato.getESPECIFICACION5());
        item.setDescripcion6(Formato.getESPECIFICACION6());
        item.setDescripcion7(Formato.getESPECIFICACION7());
        item.setDescripcion8(Formato.getESPECIFICACION8());
        item.setDescripcion9(Formato.getESPECIFICACION9());
        item.setEquipo1(Formato.getEQUIPO1());
        item.setEquipo2(Formato.getEQUIPO2());
        item.setEquipo3(Formato.getEQUIPO3());
        item.setEquipo4(Formato.getEQUIPO4());
        item.setEquipo5(Formato.getEQUIPO5());

        item.setNoId1(Formato.getNoID1());
        item.setNoId2(Formato.getNoID2());
        item.setNoId3(Formato.getNoID3());
        item.setNoId4(Formato.getNoID4());
        item.setNoId5(Formato.getNoID5());

        item.setFechaVencimiento1(Formato.getFECHA1());
        item.setFechaVencimiento2(Formato.getFECHA2());
        item.setFechaVencimiento3(Formato.getFECHA3());
        item.setFechaVencimiento4(Formato.getFECHA4());
        item.setFechaVencimiento5(Formato.getFECHA5());


        /*try{if(item.getAntesFoto1().contains(".jpg")){
            item.setAntesFoto1(b64.ImgB64(item.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto2().contains(".jpg")){
            item.setAntesFoto2(b64.ImgB64(item.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto3().contains(".jpg")){
            item.setAntesFoto3(b64.ImgB64(item.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto4().contains(".jpg")){
            item.setAntesFoto4(b64.ImgB64(item.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto5().contains(".jpg")){
            item.setAntesFoto5(b64.ImgB64(item.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto6().contains(".jpg")){
            item.setAntesFoto6(b64.ImgB64(item.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto1().contains(".jpg")){
            item.setDespuesFoto1(b64.ImgB64(item.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto2().contains(".jpg")){
            item.setDespuesFoto2(b64.ImgB64(item.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto3().contains(".jpg")){
            item.setDespuesFoto3(b64.ImgB64(item.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto4().contains(".jpg")){
            item.setDespuesFoto4(b64.ImgB64(item.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto5().contains(".jpg")){
            item.setDespuesFoto5(b64.ImgB64(item.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto6().contains(".jpg")){
            item.setDespuesFoto6(b64.ImgB64(item.getDespuesFoto6()));
        }}catch (Exception e){}
        */

        item.setFirmaCliente(Formato.getIMAGENFIRMA1());
        item.setFirmaVertiv(Formato.getIMAGENFIRMA2());
        item.setFirmaClienteFinal(Formato.getIMAGENFIRMA3());

        item.setNombreFirmaCliente(Formato.getFIRMA1());
        item.setNombreFirmaVertiv(Formato.getFIRMA2());
        item.setNombreFirmaClienteFinal(Formato.getFIRMA13());
        item.setAD_NOMBRE1(Formato.getAD_NOMBRE1());
        item.setAD_CORREO1(Formato.getAD_CORREO1());
        item.setAD_NOMBRE2(Formato.getAD_NOMBRE2());
        item.setAD_CORREO2(Formato.getAD_CORREO2());
        item.setAD_NOMBRE3(Formato.getAD_NOMBRE3());
        item.setAD_CORREO3(Formato.getAD_CORREO3());
        item.setAD_NOMBRE4(Formato.getAD_NOMBRE4());
        item.setAD_CORREO4(Formato.getAD_CORREO4());
        item.setAD_NOMBRE5(Formato.getAD_NOMBRE5());
        item.setAD_CORREO5(Formato.getAD_CORREO5());
        item.setCLIENTEFINAL_EMPRESA(Formato.getCLIENTEFINAL_EMPRESA());
        item.setCLIENTEFINAL_TELEFONO(Formato.getcLIENTEFINAL_TELEFONO());
        item.setCLIENTEFINAL_CORREO(Formato.getCLIENTEFINAL_CORREO());
        item.setGarantiaArchivo(Formato.getGarantiaArchivo());
        item.setACCIONCORRECTIVA(Formato.getACCIONCORRECTIVA());
        item.setAntesFoto1((Formato.getFOTOANTES1()));
        item.setAntesFoto2((Formato.getFOTOANTES2()));
        item.setAntesFoto3((Formato.getFOTOANTES3()));
        item.setAntesFoto4((Formato.getFOTOANTES4()));
        item.setAntesFoto5((Formato.getFOTOANTES5()));
        item.setAntesFoto6((Formato.getFOTOANTES6()));
        item.setDespuesFoto1((Formato.getFOTODESPUES1()));
        item.setDespuesFoto2((Formato.getFOTODESPUES2()));
        item.setDespuesFoto3((Formato.getFOTODESPUES3()));
        item.setDespuesFoto4((Formato.getFOTODESPUES4()));
        item.setDespuesFoto5((Formato.getFOTODESPUES5()));
        item.setDespuesFoto6((Formato.getFOTODESPUES6()));

        item.setIdUsuario(usuario.getID_USER());

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        compositeDisposable.add(iMyAPI.enviarGarantia(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Garantia>() {
                    @Override
                    public void accept(Garantia lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(item.getAntesFoto1().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto1(b64.ImgB64(item.getAntesFoto1()));
                                itemF.setFormato(item.getAntesFoto1());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getAntesFoto2().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto2(b64.ImgB64(item.getAntesFoto2()));
                                itemF.setFormato(item.getAntesFoto2());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getAntesFoto3().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto3(b64.ImgB64(item.getAntesFoto3()));
                                itemF.setFormato(item.getAntesFoto3());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getAntesFoto4().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto4(b64.ImgB64(item.getAntesFoto4()));
                                itemF.setFormato(item.getAntesFoto4());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getAntesFoto5().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto5(b64.ImgB64(item.getAntesFoto5()));
                                itemF.setFormato(item.getAntesFoto5());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getAntesFoto6().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setAntesFoto6(b64.ImgB64(item.getAntesFoto6()));
                                itemF.setFormato(item.getAntesFoto6());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto1().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto1(b64.ImgB64(item.getDespuesFoto1()));
                                itemF.setFormato(item.getDespuesFoto1());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto2().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto2(b64.ImgB64(item.getDespuesFoto2()));
                                itemF.setFormato(item.getDespuesFoto2());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto3().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto3(b64.ImgB64(item.getDespuesFoto3()));
                                itemF.setFormato(item.getDespuesFoto3());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto4().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto4(b64.ImgB64(item.getDespuesFoto4()));
                                itemF.setFormato(item.getDespuesFoto4());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto5().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto5(b64.ImgB64(item.getDespuesFoto5()));
                                itemF.setFormato(item.getDespuesFoto5());
                                imagenesEnvio(itemF);
                            }}catch (Exception e){}
                            try{if(item.getDespuesFoto6().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setDespuesFoto6(b64.ImgB64(item.getDespuesFoto6()));
                                itemF.setFormato(item.getDespuesFoto6());
                                imagenesEnvio(itemF);

                            }}catch (Exception e){}

                            try{if(Formato.getGarantiaArchivo().contains(".jpg")){
                                FotosFormato itemF = new FotosFormato();
                                itemF.setId(idFormatoService);
                                itemF.setGarantiaArchivo(b64.ImgB64(Formato.getGarantiaArchivo()));
                                itemF.setFormato(Formato.getGarantiaArchivo());
                                imagenesEnvio(itemF);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );

    }
    public void formato5(final String id_formato){
        final Bestel1 Formato  = D_B.obtenerBestel1(id_formato);

        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        final String idFormatoService = Formato.getFolioPreTrabajo();//D_B.generarUID();
        Formato.setIdBestelNivel1(idFormatoService);
        /*
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
        */

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        compositeDisposable.add(iMyAPI.enviarBestel1(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bestel1>() {
                    @Override
                    public void accept(Bestel1 lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
                                item.setFormato(Formato.getAntesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
                                item.setFormato(Formato.getAntesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
                                item.setFormato(Formato.getAntesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
                                item.setFormato(Formato.getAntesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
                                item.setFormato(Formato.getAntesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
                                item.setFormato(Formato.getAntesFoto6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
                                item.setFormato(Formato.getDespuesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
                                item.setFormato(Formato.getDespuesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
                                item.setFormato(Formato.getDespuesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
                                item.setFormato(Formato.getDespuesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
                                item.setFormato(Formato.getDespuesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
                                item.setFormato(Formato.getDespuesFoto6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);
                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato6(final String id_formato){
        final Bestel2 Formato= D_B.obtenerBestel2(id_formato);
        final String idFormatoService = Formato.getFolioPreTrabajo();//D_B.generarUID();

        Formato.setIdBestelNivel2(idFormatoService);
        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();

        /*try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
        */


        compositeDisposable.add(iMyAPI.enviarBestel2(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Bestel2>() {
                    @Override
                    public void accept(Bestel2 lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
                                item.setFormato(Formato.getAntesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
                                item.setFormato(Formato.getAntesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
                                item.setFormato(Formato.getAntesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
                                item.setFormato(Formato.getAntesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
                                item.setFormato(Formato.getAntesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
                                item.setFormato(Formato.getAntesFoto6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
                                item.setFormato(Formato.getDespuesFoto1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
                                item.setFormato(Formato.getDespuesFoto2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
                                item.setFormato(Formato.getDespuesFoto3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
                                item.setFormato(Formato.getDespuesFoto4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
                                item.setFormato(Formato.getDespuesFoto5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespuesFoto6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
                                item.setFormato(Formato.getDespuesFoto6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato7(final String id_formato){
        final PDU Formato = D_B.obtenerPDU(id_formato);

        final String idFormatoService = Formato.getFolioPreTrabajo(); //D_B.generarUID();
        Formato.setIdFormatoPDU(idFormatoService);
        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();

        /*try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
        */

        compositeDisposable.add(iMyAPI.enviarPDU(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PDU>() {
                    @Override
                    public void accept(PDU lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntes1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes1(b64.ImgB64(Formato.getAntes1()));
                                item.setFormato(Formato.getAntes1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes2(b64.ImgB64(Formato.getAntes2()));
                                item.setFormato(Formato.getAntes2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes3(b64.ImgB64(Formato.getAntes3()));
                                item.setFormato(Formato.getAntes3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes4(b64.ImgB64(Formato.getAntes4()));
                                item.setFormato(Formato.getAntes4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes5(b64.ImgB64(Formato.getAntes5()));
                                item.setFormato(Formato.getAntes5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes6(b64.ImgB64(Formato.getAntes6()));
                                item.setFormato(Formato.getAntes6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues1(b64.ImgB64(Formato.getDespues1()));
                                item.setFormato(Formato.getDespues1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues2(b64.ImgB64(Formato.getDespues2()));
                                item.setFormato(Formato.getDespues2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues3(b64.ImgB64(Formato.getDespues3()));
                                item.setFormato(Formato.getDespues3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues4(b64.ImgB64(Formato.getDespues4()));
                                item.setFormato(Formato.getDespues4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues5(b64.ImgB64(Formato.getDespues5()));
                                item.setFormato(Formato.getDespues5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues6(b64.ImgB64(Formato.getDespues6()));
                                item.setFormato(Formato.getDespues6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato8(final String id_formato){
        final Servicios Formato= D_B.obtenerServicio(id_formato);

        //Bestel1 item = D_B.obtenerBestel1Prellenado();

        GeneralServicios item =D_B.generarServiciosparaEnvioServer(Formato);
        final String idFormatoService = Formato.getFolioPreTrabajo(); //D_B.generarUID();
        item.setIdFormatoGeneralServicios(idFormatoService);
        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{item.setSRProyecto(Formato.getSR());}

        /*try{if(Formato.getFOTOANTES1().contains(".jpg")){
            item.setAntes1(b64.ImgB64(Formato.getFOTOANTES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES2().contains(".jpg")){
            item.setAntes2(b64.ImgB64(Formato.getFOTOANTES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES3().contains(".jpg")){
            item.setAntes3(b64.ImgB64(Formato.getFOTOANTES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES4().contains(".jpg")){
            item.setAntes4(b64.ImgB64(Formato.getFOTOANTES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES5().contains(".jpg")){
            item.setAntes5(b64.ImgB64(Formato.getFOTOANTES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES6().contains(".jpg")){
            item.setAntes6(b64.ImgB64(Formato.getFOTOANTES6()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES1().contains(".jpg")){
            item.setDespues1(b64.ImgB64(Formato.getFOTODESPUES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES2().contains(".jpg")){
            item.setDespues2(b64.ImgB64(Formato.getFOTODESPUES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES3().contains(".jpg")){
            item.setDespues3(b64.ImgB64(Formato.getFOTODESPUES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES4().contains(".jpg")){
            item.setDespues4(b64.ImgB64(Formato.getFOTODESPUES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES5().contains(".jpg")){
            item.setDespues5(b64.ImgB64(Formato.getFOTODESPUES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES6().contains(".jpg")){
            item.setDespues6(b64.ImgB64(Formato.getFOTODESPUES6()));
        }}catch (Exception e){}
        */

        compositeDisposable.add(iMyAPI.enviarGServicios(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GeneralServicios>() {
                    @Override
                    public void accept(GeneralServicios lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if(lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getFOTOANTES1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes1(b64.ImgB64(Formato.getFOTOANTES1()));
                                item.setFormato(Formato.getFOTOANTES1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTOANTES2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes2(b64.ImgB64(Formato.getFOTOANTES2()));
                                item.setFormato(Formato.getFOTOANTES2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTOANTES3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes3(b64.ImgB64(Formato.getFOTOANTES3()));
                                item.setFormato(Formato.getFOTOANTES3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTOANTES4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes4(b64.ImgB64(Formato.getFOTOANTES4()));
                                item.setFormato(Formato.getFOTOANTES4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTOANTES5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes5(b64.ImgB64(Formato.getFOTOANTES5()));
                                item.setFormato(Formato.getFOTOANTES5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTOANTES6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes6(b64.ImgB64(Formato.getFOTOANTES6()));
                                item.setFormato(Formato.getFOTOANTES6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues1(b64.ImgB64(Formato.getFOTODESPUES1()));
                                item.setFormato(Formato.getFOTODESPUES1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues2(b64.ImgB64(Formato.getFOTODESPUES2()));
                                item.setFormato(Formato.getFOTODESPUES2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues3(b64.ImgB64(Formato.getFOTODESPUES3()));
                                item.setFormato(Formato.getFOTODESPUES3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues4(b64.ImgB64(Formato.getFOTODESPUES4()));
                                item.setFormato(Formato.getFOTODESPUES4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues5(b64.ImgB64(Formato.getFOTODESPUES5()));
                                item.setFormato(Formato.getFOTODESPUES5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getFOTODESPUES6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues6(b64.ImgB64(Formato.getFOTODESPUES6()));
                                item.setFormato(Formato.getFOTODESPUES6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        }else{

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato9(final String id_formato){
        final STS2 Formato= D_B.obtenerSTS2(id_formato);

        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        final String idFormatoService = Formato.getFolioPreTrabajo();//D_B.generarUID();
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setIdFormatoSTS2(idFormatoService);

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        /*
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
        */

        compositeDisposable.add(iMyAPI.enviarSTS2(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<STS2>() {
                    @Override
                    public void accept(STS2 lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntes1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes1(b64.ImgB64(Formato.getAntes1()));
                                item.setFormato(Formato.getAntes1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes2(b64.ImgB64(Formato.getAntes2()));
                                item.setFormato(Formato.getAntes2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes3(b64.ImgB64(Formato.getAntes3()));
                                item.setFormato(Formato.getAntes3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes4(b64.ImgB64(Formato.getAntes4()));
                                item.setFormato(Formato.getAntes4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes5(b64.ImgB64(Formato.getAntes5()));
                                item.setFormato(Formato.getAntes5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes6(b64.ImgB64(Formato.getAntes6()));
                                item.setFormato(Formato.getAntes6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues1(b64.ImgB64(Formato.getDespues1()));
                                item.setFormato(Formato.getDespues1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues2(b64.ImgB64(Formato.getDespues2()));
                                item.setFormato(Formato.getDespues2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues3(b64.ImgB64(Formato.getDespues3()));
                                item.setFormato(Formato.getDespues3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues4(b64.ImgB64(Formato.getDespues4()));
                                item.setFormato(Formato.getDespues4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues5(b64.ImgB64(Formato.getDespues5()));
                                item.setFormato(Formato.getDespues5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues6(b64.ImgB64(Formato.getDespues6()));
                                item.setFormato(Formato.getDespues6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
        //endregion


    }
    public void formato10(final String id_formato){
        final ThermalManagagementS Formato= D_B.obtenerThermal(id_formato);

        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        final String idFormatoService = Formato.getFolioPreTrabajo(); // D_B.generarUID();
        Formato.setIdFormatoThermalManagagement(idFormatoService);
        /*
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
        */
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        compositeDisposable.add(iMyAPI.enviarThermal(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ThermalManagagementS>() {
                    @Override
                    public void accept(ThermalManagagementS lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntes1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes1(b64.ImgB64(Formato.getAntes1()));
                                item.setFormato(Formato.getAntes1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes2(b64.ImgB64(Formato.getAntes2()));
                                item.setFormato(Formato.getAntes2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes3(b64.ImgB64(Formato.getAntes3()));
                                item.setFormato(Formato.getAntes3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes4(b64.ImgB64(Formato.getAntes4()));
                                item.setFormato(Formato.getAntes4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes5(b64.ImgB64(Formato.getAntes5()));
                                item.setFormato(Formato.getAntes5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes6(b64.ImgB64(Formato.getAntes6()));
                                item.setFormato(Formato.getAntes6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues1(b64.ImgB64(Formato.getDespues1()));
                                item.setFormato(Formato.getDespues1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues2(b64.ImgB64(Formato.getDespues2()));
                                item.setFormato(Formato.getDespues2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues3(b64.ImgB64(Formato.getDespues3()));
                                item.setFormato(Formato.getDespues3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues4(b64.ImgB64(Formato.getDespues4()));
                                item.setFormato(Formato.getDespues4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues5(b64.ImgB64(Formato.getDespues5()));
                                item.setFormato(Formato.getDespues5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues6(b64.ImgB64(Formato.getDespues6()));
                                item.setFormato(Formato.getDespues6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());

                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }
    public void formato11(final String id_formato){
        final UPS Formato= D_B.obtenerUPS(id_formato);

        Formato.setIdUsuario(usuario.getID_USER());
        try{if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}}catch (Exception e){}
        final String idFormatoService = Formato.getFolioPreTrabajo(); //D_B.generarUID();
        Formato.setIdFormatoUPS(idFormatoService);

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        /*try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
*/
        compositeDisposable.add(iMyAPI.enviarUPS(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UPS>() {
                    @Override
                    public void accept(UPS lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        if (lista.getExitoso().equals("1")) {
                            //region Enviar_Fotos

                            try{if(Formato.getAntes1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes1(b64.ImgB64(Formato.getAntes1()));
                                item.setFormato(Formato.getAntes1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes2(b64.ImgB64(Formato.getAntes2()));
                                item.setFormato(Formato.getAntes2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes3(b64.ImgB64(Formato.getAntes3()));
                                item.setFormato(Formato.getAntes3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes4(b64.ImgB64(Formato.getAntes4()));
                                item.setFormato(Formato.getAntes4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes5(b64.ImgB64(Formato.getAntes5()));
                                item.setFormato(Formato.getAntes5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getAntes6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setAntes6(b64.ImgB64(Formato.getAntes6()));
                                item.setFormato(Formato.getAntes6());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues1().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues1(b64.ImgB64(Formato.getDespues1()));
                                item.setFormato(Formato.getDespues1());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues2().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues2(b64.ImgB64(Formato.getDespues2()));
                                item.setFormato(Formato.getDespues2());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues3().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues3(b64.ImgB64(Formato.getDespues3()));
                                item.setFormato(Formato.getDespues3());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues4().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues4(b64.ImgB64(Formato.getDespues4()));
                                item.setFormato(Formato.getDespues4());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues5().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues5(b64.ImgB64(Formato.getDespues5()));
                                item.setFormato(Formato.getDespues5());
                                imagenesEnvio(item);
                            }}catch (Exception e){}
                            try{if(Formato.getDespues6().contains(".jpg")){
                                FotosFormato item = new FotosFormato();
                                item.setId(idFormatoService);
                                item.setDespues6(b64.ImgB64(Formato.getDespues6()));
                                item.setFormato(Formato.getDespues6());
                                imagenesEnvio(item);

                            }}catch (Exception e){}
                            //endregion
                            D_B.actualizarEsatusFormato("3",id_formato);
                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                })

        );
    }

    public void imagenesEnvio(FotosFormato item){

        item.setPais(usuario.getPAIS());
        compositeDisposable.add(iMyAPI.enviarFoto(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<FotosFormato>() {
                    @Override
                    public void accept(FotosFormato respuesta) throws Exception {
                        try{
                            if(respuesta.getExitoso().equals("1")){
                                Log.i( "Foto: ", "enviada");
                            }else{
                                Log.e( "Foto: ", "No enviada, " + respuesta.getError());
                            }
                        }
                        catch (Exception ex){
                            Log.e( "Foto: ", "No enviada, " + ex.getMessage());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e( "Foto: ", "No enviada, " + throwable.getMessage());
                    }
                })
        );
    }






/*
    public void formato0(final String id_formato){
        final progresoLoad alerta0 = new progresoLoad();
        alerta0.setCancelable(false);
        PreOrden preOrden = D_B.obtenerPreOrden_id(id_formato);
        //region Generar Pre trabajo
        PreTrabajo item = D_B.obtenerPTPrelleno();
        if(preOrden.getGEN_SR().length()>1){
            String id =D_B.obtenerIDCliente(preOrden.getGEN_CLIENTE(),preOrden.getGEN_SITIO(),preOrden.getGEN_DIRECCION());
            item.setIdCliente(id);//obligatorios
        }
        else{
            item.setIdCliente(null);//obligatorios
        }
        item.setIdCliente(preOrden.getGEN_CLIENTE());
        item.setGEN_SR(preOrden.getGEN_SR());
        item.setGEN_TASK(preOrden.getGEN_TASK());
        item.setGEN_PROYECTO(preOrden.getGEN_PROYECTO());
        item.setGEN_SITIO(preOrden.getGEN_SITIO());
        item.setGEN_REFERENCIA(preOrden.getGEN_REFERENCIA());
        item.setGEN_USUARIO_FINAL(preOrden.getGEN_USUARIO_FINAL());
        item.setNombreCliente(preOrden.getGEN_CLIENTE());
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setEPP_ELECTICO_GUANTES(true);}
        item.setINSPEC_PERMISO_ARCHIVO(preOrden.getINSPEC_PERMISO_ARCHIVO());
        item.setFIRMA_1_CARGO(preOrden.getFIRMA_1_CARGO());
        item.setFIRMA_2_CARGO(preOrden.getFIRMA_2_CARGO());
        item.setFIRMA_3_CARGO(preOrden.getFIRMA_3_CARGO());
        item.setFIRMA_4_CARGO(preOrden.getFIRMA_4_CARGO());
        item.setFIRMA_5_CARGO(preOrden.getFIRMA_5_CARGO());
        item.setFIRMA_6_CARGO(preOrden.getFIRMA_6_CARGO());
        item.setIdUsuario(usuario.getID_USER());//obligatorios

        item.setNombreSitio(preOrden.getGEN_SITIO());
        item.setFecha(preOrden.getGEN_FECHA());
        item.setLiderGrupoCuadrilla(preOrden.getGEN_LIDER_GRUPO());
        item.setDireccionSitio(preOrden.getGEN_DIRECCION());
        item.setDescripcionActividad(preOrden.getACTV_DESCRIPCION_ACTIVIDADES());
        item.setEquiposHerramientasMateriales(preOrden.getHERRAM_DESCRIPCION_EHM());
        if(preOrden.getHERRAM_HOJAS_SEGURIDAD().length()>1){item.setHojasSeguridad(true);}
        if(preOrden.getHERRAM_DELIMITACION_AT().equals("Cinta de Precaución")){item.setCintaPrecaucion(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Conos/Pedestales")){item.setConosPedestales(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Mixta (Conos y Cinta.)")){item.setMixta(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else if(preOrden.getHERRAM_DELIMITACION_AT().equals("Malla")){item.setMalla(true);}
        else item.setOtrosArea(preOrden.getHERRAM_DELIMITACION_AT());
        if(preOrden.getRIESGO_PARTICULAS().length()>1){item.setProyeccionParticulas(true);}
        if(preOrden.getRIESGO_ATRAPAMIENTO().length()>1){item.setAtrapamiento(true);}
        if(preOrden.getRIESGO_GOLPES().length()>1){item.setGolpesCortes(true);}
        if(preOrden.getRIESGO_QUEMADURAS().length()>1){item.setQuemaduras(true);}
        if(preOrden.getRIESGO_CAIDA_MATE().length()>1){item.setCaidaMateriales(true);}
        if(preOrden.getRIESGO_CAIDA_MISMO_NIVEL().length()>1){item.setCaidaMismoNivel(true);}
        if(preOrden.getRIESGO_CAIDA_DIST_NIVEL().length()>1){item.setCaidaDistintoNivel(true);}
        if(preOrden.getRIESGO_LIMPIEZA_DEFI().length()>1){item.setOrdenLimpieza(true);}
        if(preOrden.getRIESGO_OTRO_PERSONAL().length()>1){item.setOtroPersonalTrabajando(true);}
        if(preOrden.getRIESGO_CHOQUE_ELECTRICO().length()>1){item.setChoqueElectrico(true);}
        if(preOrden.getRIESGO_ARCO_ELECT().length()>1){item.setArcoElectrico(true);}
        if(preOrden.getRIESGO_FUEGO().length()>1){item.setFuegoExplosion(true);}
        if(preOrden.getRIESGO_EXPO_RUIDO().length()>1){item.setExposicionRuido(true);}
        if(preOrden.getRIESGO_EXP_VIBRA().length()>1){item.setExposicionVibraciones(true);}
        if(preOrden.getRIESGO_FATIGA_VISUAL().length()>1){item.setFatigaVisual(true);}
        if(preOrden.getRIESGO_TEMPERATURAS().length()>1){item.setExposicionAltasBjasTemperaturas(true);}
        if(preOrden.getRIESGO_DEFI_OXIGENO().length()>1){item.setDeficienciaOxigeno(true);}
        if(preOrden.getRIESGO_GASES().length()>1){item.setExposiconGases(true);}
        if(preOrden.getRIESGO_POLVO().length()>1){item.setExposicionPolvo(true);}
        if(preOrden.getRIESGO_SOBRE_ESFUERZO().length()>1){item.setSobreEsfuerzo(true);}
        if(preOrden.getRIESGO_QUIMICOS().length()>1){item.setManipulacionProductosQuimicos(true);}
        if(preOrden.getRIESGO_RUIDO().length()>1){item.setRuido(true);}
        item.setOtrosRiesgos(preOrden.getRIESGO_OTRO());
        if(preOrden.getPREVEN_DISPO_MECANICA().length()>1){item.setUsarDispositivosElevacion(true);}
        if(preOrden.getPREVEN_SUST_QUIMICOS().length()>1){item.setSustituirQuimicosToxicos(true);}
        if(preOrden.getPREVEN_AISLAR_RUIDO().length()>1){item.setAislarRuidoGenerado(true);}
        if(preOrden.getPREVEN_PROTECTORES_MAQUINAS().length()>1){item.setColocarGuardasProtectoras(true);}
        if(preOrden.getPREVEN_PLATA_ANDAMIOS().length()>1){item.setInstalarPlataformas(true);}
        if(preOrden.getPREVEN_SIS_PNTS_ANCLAJE().length()>1){item.setInstalarSistemaPuntosAnclaje(true);}
        if(preOrden.getPREVEN_ILUMI_ART().length()>1){item.setInstalarIluminacion(true);}
        if(preOrden.getPREVEN_DISYUNTOR().length()>1){item.setInstalarDisyuntor(true);}
        if(preOrden.getPREVEN_SIST_PUESTA_TIERRA().length()>1){item.setInstalarSistemaPuestaTierra(true);}
        if(preOrden.getPREVEN_ORDEN_LIMPIEZA().length()>1){item.setMantenerOrden(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setSeñalizarAreaTrabajo(true);}
        if(preOrden.getPREVEN_BE_FUENTES_ENERGIA().length()>1){item.setBloquearEtiquetarFuentesEnergia(true);}
        if(preOrden.getPREVEN_MUROS_DERRAME().length()>1){item.setInstalarMurosContenerDerrames(true);}
        if(preOrden.getPREVEN_PERMISOS().length()>1){item.setPermisoTrabajo(true);}
        if(preOrden.getPREVEN_AREA_TRABAJO().length()>1){item.setProcedTrabajo(true);}
        if(preOrden.getPREVEN_SUPERVISION().length()>1){item.setSupervisionPermanente(true);}
        if(preOrden.getPREVEN_HERRAMI_AISLADAS().length()>1){item.setUsarHerramientaAislada(true);}
        if(preOrden.getPREVEN_EPP().length()>1){item.setEquipoProteccionPersonal(true);}
        item.setOtrosMedidasPrevension(preOrden.getPREVEN_OTRO());
        if(preOrden.getEPP_BASICO_CASCO().length()>1){item.setCasco(true);}
        if(preOrden.getEPP_BASICO_GAFAS().length()>1){item.setGafasProtectoras(true);}
        if(preOrden.getEPP_BASICO_TAPONES().length()>1){item.setProtectoresAuditores(true);}
        if(preOrden.getEPP_BASICO_ZAPATOS().length()>1){item.setZapatosSeguridad(true);}
        if(preOrden.getEPP_BASICO_GUANTES().length()>1){item.setGuantesTrabajo(true);}
        if(preOrden.getEPP_BASICO_BARBIQUEJO().length()>1){item.setBarbiquejo(true);}
        if(preOrden.getEPP_ELECTICO_GAFAS().length()>1){item.setGafasSeguridad(true);}
        if(preOrden.getEPP_ELECTICO_CASCO().length()>1){item.setCascoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_ZAPATOS().length()>1){item.setZapatosDielectricos(true);}
        if(preOrden.getEPP_ELECTICO_GUANTES().length()>1){item.setSobreguanteCuero(true);}
        if(preOrden.getEPP_ELECTICO_CARETA().length()>1){item.setCaretaArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_BALACLAVA().length()>1){item.setBalaClava(true);}
        if(preOrden.getEPP_ELECTICO_TRAJE().length()>1){item.setTrajeArcoElectrico(true);}
        if(preOrden.getEPP_ELECTICO_PROTECTORES_AUDI().length()>1){item.setProtectoresAuditivos(true);}
        if(preOrden.getEPP_ELECTICO_MANGAS().length()>1){item.setMangasDielectricas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_CAIDAS().length()>1){item.setProteccionContraCaidas(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_RESPITA().length()>1){item.setProteccionRespiratoria(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_SOLDAD().length()>1){item.setProteccionSoldadora(true);}
        if(preOrden.getEPP_OTROS_PROTECCION_QUIMICOS().length()>1){item.setProteccionContraQuimicos(true);}
        item.setAdiconales(preOrden.getEPP_OTROS_ADICIONALES());
        if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("SI")){item.setInspeccionEPP(true);}
        else if(preOrden.getINSPEC_CONDIC_OPTIMAS().equals("NO")){item.setInspeccionEPP(false);}
        item.setEspecifiqueDano(preOrden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE());

        if(preOrden.getINSPEC_ACTIVIDADES().equals("Entrada en espacios confinado")){item.setEntradaEspaciosConfinados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en caliente")){item.setTrabajosCaliente(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("Trabajos en equipos energizados (>50V)")){item.setTrabajosEquiposEnergizados(true);}
        if(preOrden.getINSPEC_ACTIVIDADES().equals("N/A")){item.setNA(true);}

        if(preOrden.getINSPEC_TRABAJOS_ALTURA().equals("SI")){item.setTrabajosAltura(true);}
        if(preOrden.getINSPEC_CONDIC_INSEGURAS().equals("SI")){item.setCondicionesInseguras(true);}
        item.setCondicionInsegura(preOrden.getINSPEC_CAUSAS_ACCIDENTES());
        item.setMedidasCorrectivas(preOrden.getINSPEC_MEDIDAS_CORRECTIVAS());
        item.setNombre1(preOrden.getFIRMA_1_NOMBRE());
        item.setArchivoFirma1(preOrden.getFIRMA_1_IMAGEN());
        item.setNombre2(preOrden.getFIRMA_2_NOMBRE());
        item.setArchivoFirma2(preOrden.getFIRMA_2_IMAGEN());
        item.setNombre3(preOrden.getFIRMA_3_NOMBRE());
        item.setArchivoFirma3(preOrden.getFIRMA_3_IMAGEN());
        item.setNombre4(preOrden.getFIRMA_4_NOMBRE());
        item.setArchivoFirma4(preOrden.getFIRMA_4_IMAGEN());
        item.setNombre5(preOrden.getFIRMA_5_NOMBRE());
        item.setArchivoFirma5(preOrden.getFIRMA_5_IMAGEN());
        item.setNombre6(preOrden.getFIRMA_6_NOMBRE());
        item.setArchivoFirma6(preOrden.getFIRMA_6_IMAGEN());
        item.setNumeroEmergencia(preOrden.getEMERG_EMERGENCIAS());
        item.setNumeroSupervisor(preOrden.getEMERG_SUPERVISOR_VERTIV());
        item.setHospitalCercano(preOrden.getEMERG_HOSPITAL());

        //endregion
        compositeDisposable.add(iMyAPI.EnvioPreoTrabajo(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PreTrabajo>() {
                    @Override
                    public void accept(PreTrabajo lista) throws Exception {
                        if(lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);
                            D_B.newMensaje("Pre tranajo enviado, Folio:"+lista.getFolioPreTrabajo());
                            alerta0.dismiss();
                        }else{
                            D_B.newMensaje(lista.getError());
                            alerta0.dismiss();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alerta0.dismiss();
                    }
                })
        );
    }
    public void formato1(final String id_formato) {

        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        EncuestaCalidadServicio item = D_B.obtenerCalidad(id_formato);

        item.setIdUsuario(usuario.getID_USER());

        if (item.getTipoServicio().equals("Otro (Especifique)")) {
            item.setTipoServicio(item.getTiposervicioOtro());
        }
        compositeDisposable.add(iMyAPI.enviarCalidad(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<EncuestaCalidadServicio>() {
                    @Override
                    public void accept(EncuestaCalidadServicio lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if(lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);
                            Bundle valores = new Bundle();
                            valores.putString("mensaje","Folio:" + lista.getFolioPreTrabajo().toString());
                            AlertaOk alerta = new AlertaOk();
                            alerta.setArguments(valores);
                            alerta.setCancelable(false);
                            alerta.show(getFragmentManager(), "a");

                            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
                            FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
                            //Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

                        }else{
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alerta.dismiss();
                    }
                })

        );
    }
    public void formato2(final String id_formato){
       Baterias bateria = D_B.obtenerBateriaF_id(id_formato);
        BateriaS Formato = new BateriaS();
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");
        //region
        if(bateria.getGRAL_proyecto().length()>1){ Formato.setSRProyecto(bateria.getGRAL_proyecto());}
        else {Formato.setSRProyecto(bateria.getGRAL_sr());}
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setFolioPreTrabajo(bateria.getGRAL_folioPT());
        Formato.setFechaInicio(bateria.getGRAL_fecha1());
        Formato.setFechaTermino(bateria.getGRAL_fecha2());
        Formato.setContactoCliente(bateria.getGRAL_contacto());
        Formato.setTASK(bateria.getGRAL_task());
        Formato.setTelefono(bateria.getGRAL_telefono());
        Formato.setTipoServicio(bateria.getGRAL_tipoServ());
        Formato.setEMail(bateria.getGRAL_mail());
        Formato.setFrecuencia(bateria.getGRAL_freciencia());
        Formato.setModeloEquipo(bateria.getGRAL_modelo());
        Formato.setNoTag(bateria.getGRAL_noTag());
        Formato.setNoSerie(bateria.getGRAL_serie1());
        Formato.setDireccionSitio(bateria.getGRAL_direccion());
        Formato.setVDC1(bateria.getVDC1());
        Formato.setVDC2(bateria.getVDC2());
        Formato.setVDC3(bateria.getVDC3());
        Formato.setVDC4(bateria.getVDC4());
        Formato.setVDC5(bateria.getVDC5());
        Formato.setVDC6(bateria.getVDC6());
        Formato.setVDC7(bateria.getVDC7());
        Formato.setVDC8(bateria.getVDC8());
        Formato.setVDC9(bateria.getVDC9());
        Formato.setVDC10(bateria.getVDC10());
        Formato.setVDC11(bateria.getVDC11());
        Formato.setVDC12(bateria.getVDC12());
        Formato.setVDC13(bateria.getVDC13());
        Formato.setVDC14(bateria.getVDC14());
        Formato.setVDC15(bateria.getVDC15());
        Formato.setVDC16(bateria.getVDC16());
        Formato.setVDC17(bateria.getVDC17());
        Formato.setVDC18(bateria.getVDC18());
        Formato.setVDC19(bateria.getVDC19());
        Formato.setVDC20(bateria.getVDC20());
        Formato.setVDC21(bateria.getVDC21());
        Formato.setVDC22(bateria.getVDC22());
        Formato.setVDC23(bateria.getVDC23());
        Formato.setVDC24(bateria.getVDC24());
        Formato.setVDC25(bateria.getVDC25());
        Formato.setVDC26(bateria.getVDC26());
        Formato.setVDC27(bateria.getVDC27());
        Formato.setVDC28(bateria.getVDC28());
        Formato.setVDC29(bateria.getVDC29());
        Formato.setVDC30(bateria.getVDC30());
        Formato.setVDC31(bateria.getVDC31());
        Formato.setVDC32(bateria.getVDC32());
        Formato.setVDC33(bateria.getVDC33());
        Formato.setVDC34(bateria.getVDC34());
        Formato.setVDC35(bateria.getVDC35());
        Formato.setVDC36(bateria.getVDC36());
        Formato.setVDC37(bateria.getVDC37());
        Formato.setVDC38(bateria.getVDC38());
        Formato.setVDC39(bateria.getVDC39());
        Formato.setVDC40(bateria.getVDC40());
        Formato.setVDC41(bateria.getVDC41());
        Formato.setVDC42(bateria.getVDC42());
        Formato.setVDC43(bateria.getVDC43());
        Formato.setVDC44(bateria.getVDC44());
        Formato.setVDC45(bateria.getVDC45());
        Formato.setVDC46(bateria.getVDC46());
        Formato.setVDC47(bateria.getVDC47());
        Formato.setVDC48(bateria.getVDC48());
        Formato.setVDC49(bateria.getVDC49());
        Formato.setVDC50(bateria.getVDC50());
        Formato.setVDC51(bateria.getVDC51());
        Formato.setVDC52(bateria.getVDC52());
        Formato.setVDC53(bateria.getVDC53());
        Formato.setVDC54(bateria.getVDC54());
        Formato.setVDC55(bateria.getVDC55());
        Formato.setVDC56(bateria.getVDC56());
        Formato.setVDC57(bateria.getVDC57());
        Formato.setVDC58(bateria.getVDC58());
        Formato.setVDC59(bateria.getVDC59());
        Formato.setVDC60(bateria.getVDC60());
        Formato.setVDC61(bateria.getVDC61());
        Formato.setVDC62(bateria.getVDC62());
        Formato.setVDC63(bateria.getVDC63());
        Formato.setVDC64(bateria.getVDC64());
        Formato.setVDC65(bateria.getVDC65());
        Formato.setVDC66(bateria.getVDC66());
        Formato.setVDC67(bateria.getVDC67());
        Formato.setVDC68(bateria.getVDC68());
        Formato.setVDC69(bateria.getVDC69());
        Formato.setVDC70(bateria.getVDC70());
        Formato.setVDC71(bateria.getVDC71());
        Formato.setVDC72(bateria.getVDC72());
        Formato.setVDC73(bateria.getVDC73());
        Formato.setVDC74(bateria.getVDC74());
        Formato.setVDC75(bateria.getVDC75());
        Formato.setVDC76(bateria.getVDC76());
        Formato.setVDC77(bateria.getVDC77());
        Formato.setVDC78(bateria.getVDC78());
        Formato.setVDC79(bateria.getVDC79());
        Formato.setVDC80(bateria.getVDC80());
        Formato.setVDC81(bateria.getVDC81());
        Formato.setVDC82(bateria.getVDC82());
        Formato.setVDC83(bateria.getVDC83());
        Formato.setVDC84(bateria.getVDC84());
        Formato.setVDC85(bateria.getVDC85());
        Formato.setVDC86(bateria.getVDC86());
        Formato.setVDC87(bateria.getVDC87());
        Formato.setVDC88(bateria.getVDC88());
        Formato.setVDC89(bateria.getVDC89());
        Formato.setVDC90(bateria.getVDC90());
        Formato.setVDC91(bateria.getVDC91());
        Formato.setVDC92(bateria.getVDC92());
        Formato.setVDC93(bateria.getVDC93());
        Formato.setVDC94(bateria.getVDC94());
        Formato.setVDC95(bateria.getVDC95());
        Formato.setVDC96(bateria.getVDC96());
        Formato.setVDC97(bateria.getVDC97());
        Formato.setVDC98(bateria.getVDC98());
        Formato.setVDC99(bateria.getVDC99());
        Formato.setVDC100(bateria.getVDC100());
        Formato.setVDC101(bateria.getVDC101());
        Formato.setVDC102(bateria.getVDC102());
        Formato.setVDC103(bateria.getVDC103());
        Formato.setVDC104(bateria.getVDC104());
        Formato.setVDC105(bateria.getVDC105());
        Formato.setVDC106(bateria.getVDC106());
        Formato.setVDC107(bateria.getVDC107());
        Formato.setVDC108(bateria.getVDC108());
        Formato.setVDC109(bateria.getVDC109());
        Formato.setVDC110(bateria.getVDC110());
        Formato.setVDC111(bateria.getVDC111());
        Formato.setVDC112(bateria.getVDC112());
        Formato.setVDC113(bateria.getVDC113());
        Formato.setVDC114(bateria.getVDC114());
        Formato.setVDC115(bateria.getVDC115());
        Formato.setVDC116(bateria.getVDC116());
        Formato.setVDC117(bateria.getVDC117());
        Formato.setVDC118(bateria.getVDC118());
        Formato.setVDC119(bateria.getVDC119());
        Formato.setVDC120(bateria.getVDC120());
        Formato.setVAC1(bateria.getVAC1());
        Formato.setVAC2(bateria.getVAC2());
        Formato.setVAC3(bateria.getVAC3());
        Formato.setVAC4(bateria.getVAC4());
        Formato.setVAC5(bateria.getVAC5());
        Formato.setVAC6(bateria.getVAC6());
        Formato.setVAC7(bateria.getVAC7());
        Formato.setVAC8(bateria.getVAC8());
        Formato.setVAC9(bateria.getVAC9());
        Formato.setVAC10(bateria.getVAC10());
        Formato.setVAC11(bateria.getVAC11());
        Formato.setVAC12(bateria.getVAC12());
        Formato.setVAC13(bateria.getVAC13());
        Formato.setVAC14(bateria.getVAC14());
        Formato.setVAC15(bateria.getVAC15());
        Formato.setVAC16(bateria.getVAC16());
        Formato.setVAC17(bateria.getVAC17());
        Formato.setVAC18(bateria.getVAC18());
        Formato.setVAC19(bateria.getVAC19());
        Formato.setVAC20(bateria.getVAC20());
        Formato.setVAC21(bateria.getVAC21());
        Formato.setVAC22(bateria.getVAC22());
        Formato.setVAC23(bateria.getVAC23());
        Formato.setVAC24(bateria.getVAC24());
        Formato.setVAC25(bateria.getVAC25());
        Formato.setVAC26(bateria.getVAC26());
        Formato.setVAC27(bateria.getVAC27());
        Formato.setVAC28(bateria.getVAC28());
        Formato.setVAC29(bateria.getVAC29());
        Formato.setVAC30(bateria.getVAC30());
        Formato.setVAC31(bateria.getVAC31());
        Formato.setVAC32(bateria.getVAC32());
        Formato.setVAC33(bateria.getVAC33());
        Formato.setVAC34(bateria.getVAC34());
        Formato.setVAC35(bateria.getVAC35());
        Formato.setVAC36(bateria.getVAC36());
        Formato.setVAC37(bateria.getVAC37());
        Formato.setVAC38(bateria.getVAC38());
        Formato.setVAC39(bateria.getVAC39());
        Formato.setVAC40(bateria.getVAC40());
        Formato.setVAC41(bateria.getVAC41());
        Formato.setVAC42(bateria.getVAC42());
        Formato.setVAC43(bateria.getVAC43());
        Formato.setVAC44(bateria.getVAC44());
        Formato.setVAC45(bateria.getVAC45());
        Formato.setVAC46(bateria.getVAC46());
        Formato.setVAC47(bateria.getVAC47());
        Formato.setVAC48(bateria.getVAC48());
        Formato.setVAC49(bateria.getVAC49());
        Formato.setVAC50(bateria.getVAC50());
        Formato.setVAC51(bateria.getVAC51());
        Formato.setVAC52(bateria.getVAC52());
        Formato.setVAC53(bateria.getVAC53());
        Formato.setVAC54(bateria.getVAC54());
        Formato.setVAC55(bateria.getVAC55());
        Formato.setVAC56(bateria.getVAC56());
        Formato.setVAC57(bateria.getVAC57());
        Formato.setVAC58(bateria.getVAC58());
        Formato.setVAC59(bateria.getVAC59());
        Formato.setVAC60(bateria.getVAC60());
        Formato.setVAC61(bateria.getVAC61());
        Formato.setVAC62(bateria.getVAC62());
        Formato.setVAC63(bateria.getVAC63());
        Formato.setVAC64(bateria.getVAC64());
        Formato.setVAC65(bateria.getVAC65());
        Formato.setVAC66(bateria.getVAC66());
        Formato.setVAC67(bateria.getVAC67());
        Formato.setVAC68(bateria.getVAC68());
        Formato.setVAC69(bateria.getVAC69());
        Formato.setVAC70(bateria.getVAC70());
        Formato.setVAC71(bateria.getVAC71());
        Formato.setVAC72(bateria.getVAC72());
        Formato.setVAC73(bateria.getVAC73());
        Formato.setVAC74(bateria.getVAC74());
        Formato.setVAC75(bateria.getVAC75());
        Formato.setVAC76(bateria.getVAC76());
        Formato.setVAC77(bateria.getVAC77());
        Formato.setVAC78(bateria.getVAC78());
        Formato.setVAC79(bateria.getVAC79());
        Formato.setVAC80(bateria.getVAC80());
        Formato.setVAC81(bateria.getVAC81());
        Formato.setVAC82(bateria.getVAC82());
        Formato.setVAC83(bateria.getVAC83());
        Formato.setVAC84(bateria.getVAC84());
        Formato.setVAC85(bateria.getVAC85());
        Formato.setVAC86(bateria.getVAC86());
        Formato.setVAC87(bateria.getVAC87());
        Formato.setVAC88(bateria.getVAC88());
        Formato.setVAC89(bateria.getVAC89());
        Formato.setVAC90(bateria.getVAC90());
        Formato.setVAC91(bateria.getVAC91());
        Formato.setVAC92(bateria.getVAC92());
        Formato.setVAC93(bateria.getVAC93());
        Formato.setVAC94(bateria.getVAC94());
        Formato.setVAC95(bateria.getVAC95());
        Formato.setVAC96(bateria.getVAC96());
        Formato.setVAC97(bateria.getVAC97());
        Formato.setVAC98(bateria.getVAC98());
        Formato.setVAC99(bateria.getVAC99());
        Formato.setVAC100(bateria.getVAC100());
        Formato.setVAC101(bateria.getVAC101());
        Formato.setVAC102(bateria.getVAC102());
        Formato.setVAC103(bateria.getVAC103());
        Formato.setVAC104(bateria.getVAC104());
        Formato.setVAC105(bateria.getVAC105());
        Formato.setVAC106(bateria.getVAC106());
        Formato.setVAC107(bateria.getVAC107());
        Formato.setVAC108(bateria.getVAC108());
        Formato.setVAC109(bateria.getVAC109());
        Formato.setVAC110(bateria.getVAC110());
        Formato.setVAC111(bateria.getVAC111());
        Formato.setVAC112(bateria.getVAC112());
        Formato.setVAC113(bateria.getVAC113());
        Formato.setVAC114(bateria.getVAC114());
        Formato.setVAC115(bateria.getVAC115());
        Formato.setVAC116(bateria.getVAC116());
        Formato.setVAC117(bateria.getVAC117());
        Formato.setVAC118(bateria.getVAC118());
        Formato.setVAC119(bateria.getVAC119());
        Formato.setVAC120(bateria.getVAC120());
        Formato.setModeloMarca(bateria.getModeloMarca());
        Formato.setAparenciaLimpieza(bateria.getAparenciaLimpieza());
        Formato.setCodigoFecha(bateria.getCodigoFecha());
        Formato.setAñosServicio(bateria.getAñosServicio());
        Formato.setConectoresTornillos(bateria.getConectoresTornillos());
        Formato.setJarrasCubiertasSellado(bateria.getJarrasCubiertasSellado());
        Formato.setVoltajeFlotacionVDC(bateria.getVoltajeFlotacionVDC());
        Formato.setTemperaturaBaterias(bateria.getTemperaturaBaterias());
        Formato.setCorrienteFlotacion(bateria.getCorrienteFlotacion());
        Formato.setTemperaturaAmbiente(bateria.getTemperaturaAmbiente());
        Formato.setCorrienteRizo(bateria.getCorrienteRizo());
        Formato.setTorque(bateria.getTorque());
        Formato.setVoltajeRizo(bateria.getVoltajeRizo());
        Formato.setTerminales(bateria.getTerminales());
        Formato.setComentarios(bateria.getCOMENT_cometarios());
        Formato.setPMUCantidad1(bateria.getMATE_cantidad1());
        Formato.setPMUCantidad2(bateria.getMATE_cantidad2());
        Formato.setPMUCantidad3(bateria.getMATE_cantidad3());
        Formato.setPMUCantidad4(bateria.getMATE_cantidad4());
        Formato.setPMUCantidad5(bateria.getMATE_cantidad5());
        Formato.setPMUCantidad6(bateria.getMATE_cantidad6());
        Formato.setPMUCantidad7(bateria.getMATE_cantidad7());
        Formato.setPMUNParte1(bateria.getMATE_parte1());
        Formato.setPMUNParte2(bateria.getMATE_parte2());
        Formato.setPMUNParte3(bateria.getMATE_parte3());
        Formato.setPMUNParte4(bateria.getMATE_parte4());
        Formato.setPMUNParte5(bateria.getMATE_parte5());
        Formato.setPMUNParte6(bateria.getMATE_parte6());
        Formato.setPMUNParte7(bateria.getMATE_parte7());
        Formato.setPMUDescripcion1(bateria.getMATE_especifica1());
        Formato.setPMUDescripcion2(bateria.getMATE_especifica2());
        Formato.setPMUDescripcion3(bateria.getMATE_especifica3());
        Formato.setPMUDescripcion4(bateria.getMATE_especifica4());
        Formato.setPMUDescripcion5(bateria.getMATE_especifica5());
        Formato.setPMUDescripcion6(bateria.getMATE_especifica6());
        Formato.setPMUDescripcion7(bateria.getMATE_especifica7());
        Formato.setEMEquipo1(bateria.getMATE_equipo1());
        Formato.setEMEquipo2(bateria.getMATE_equipo2());
        Formato.setEMEquipo3(bateria.getMATE_equipo3());
        Formato.setEMEquipo4(bateria.getMATE_equipo4());
        Formato.setEMEquipo5(bateria.getMATE_equipo5());
        Formato.setEMEquipo6(bateria.getMATE_equipo6());
        //Formato.setEMEquipo7(bateria.getequipo7);
        Formato.setNoID1(bateria.getMATE_nid1());
        Formato.setNoID2(bateria.getMATE_nid2());
        Formato.setNoID3(bateria.getMATE_nid3());
        Formato.setNoID4(bateria.getMATE_nid4());
        Formato.setNoID5(bateria.getMATE_nid5());
        Formato.setNoID6(bateria.getMATE_nid6());
        // Formato.setNoID7(bateria.getn);
        Formato.setFechaVencimiento1(bateria.getMATE_fecha1());
        Formato.setFechaVencimiento2(bateria.getMATE_fecha2());
        Formato.setFechaVencimiento3(bateria.getMATE_fecha3());
        Formato.setFechaVencimiento4(bateria.getMATE_fecha4());
        Formato.setFechaVencimiento5(bateria.getMATE_fecha5());
        Formato.setFechaVencimiento6(bateria.getMATE_fecha6());
        //Formato.setFechaVencimiento7(bateria.ge);
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}
        Formato.setFirmaCliente(bateria.getFIRMA_IMG1());
        Formato.setFirmaVertiv(bateria.getFIRMA_IMG2());
        Formato.setFirmaClienteFinal(bateria.getFIRMA_IMG3());
        //endregion
        compositeDisposable.add(iMyAPI.enviarBaterias(Formato).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BateriaS>() {
                    @Override
                    public void accept(BateriaS lista) throws Exception {
                        //lista.get(0).getItem_Number();
                        alerta.dismiss();
                        if (lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);

                        } else {
                            D_B.newMensaje(lista.getError());
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alerta.dismiss();
                    }
                })

        );

    }
    public void formato3(final String id_formato){
        DCPower dcPower = D_B.obtenerDcPower_id(id_formato);
        DCPower2 dcPower2= D_B.obtenerDcPower2_id(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        com.maju.desarrollo.testfcs.ServiceClass.DCPower Formato = new com.maju.desarrollo.testfcs.ServiceClass.DCPower();
        //region
        if(dcPower.getGRAL_PROYECTO().length()>1){ Formato.setSRProyecto(dcPower.getGRAL_PROYECTO());}
        else {Formato.setSRProyecto(dcPower.getGRAL_SR());}
        Formato.setIdUsuario(usuario.getID_USER());
        Formato.setIdCliente(dcPower.getGRAL_CLIENTE().toString());
        Formato.setFolioPreTrabajo(dcPower.getGRAL_FOLIO_PRETRABAJO());
        Formato.setFechaInicio(dcPower.getGRAL_FECHA_INICIO());
        Formato.setFechaTermino(dcPower.getGRAL_FECHA_FIN());
        Formato.setContactoCliente(dcPower.getGRAL_CONTACTO());
        Formato.setTASK(dcPower.getGRAL_TASK());
        Formato.setTelefono(dcPower.getGRAL_TELEFONO());
        Formato.setTipoServicio(dcPower.getGRAL_TIPO_SERVICIO());
        Formato.setEMail(dcPower.getGRAL_MAIL());
        Formato.setFreecuencia(dcPower.getGRAL_FRECUENCIA());
        Formato.setModeloEquipo(dcPower.getGRAL_MODELO());
        Formato.setNoTag(dcPower.getGRAL_NTAG());
        Formato.setNoSerie(dcPower.getGRAL_SERIE());
        Formato.setDireccionSitio(dcPower.getGRAL_DIRECCION());

        //Modulo 2
        Formato.setPGSDCTipo(dcPower.getPARAM_GRALS_TIPO());
        Formato.setPGSDCNoEspecificacion(dcPower.getPARAM_GRALS_N_ESPECIFICAION());
        Formato.setPGSDCCapacidad(dcPower.getPARAM_GRALS_CAPACIDAD());
        Formato.setPGSDCConfiguracion(dcPower.getPARAM_GRALS_CONFIGURACION());
        Formato.setPGSDCCargaActual(dcPower.getPARAM_GRALS_CARGA_ACT());
        Formato.setPGIRModelo(dcPower.getPARAM_INV_RECTIFICADORES_MODELO());
        Formato.setPGIRNoEspecificacion(dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION());
        Formato.setPGIRCapacidad(dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD());
        Formato.setNoSerieR1(dcPower.getPARAM_INV_RECTIFICADORES_R1());
        Formato.setNoSerieR2(dcPower.getPARAM_INV_RECTIFICADORES_R2());
        Formato.setNoSerieR3(dcPower.getPARAM_INV_RECTIFICADORES_R3());
        Formato.setNoSerieR4(dcPower.getPARAM_INV_RECTIFICADORES_R4());
        Formato.setNoSerieR5(dcPower.getPARAM_INV_RECTIFICADORES_R5());
        Formato.setNoSerieR6(dcPower.getPARAM_INV_RECTIFICADORES_R6());
        Formato.setNoSerieR7(dcPower.getPARAM_INV_RECTIFICADORES_R7());
        Formato.setNoSerieR8(dcPower.getPARAM_INV_RECTIFICADORES_R8());
        Formato.setNoSerieR9(dcPower.getPARAM_INV_RECTIFICADORES_R9());
        Formato.setNoSerieR10(dcPower.getPARAM_INV_RECTIFICADORES_R10());
        Formato.setNoSerieR11(dcPower.getPARAM_INV_RECTIFICADORES_R11());
        Formato.setNoSerieR12(dcPower.getPARAM_INV_RECTIFICADORES_R12());
        Formato.setNoSerieR13(dcPower.getPARAM_INV_RECTIFICADORES_R13());
        Formato.setNoSerieR14(dcPower.getPARAM_INV_RECTIFICADORES_R14());
        Formato.setNoSerieR15(dcPower.getPARAM_INV_RECTIFICADORES_R15());
        Formato.setNoSerieR16(dcPower.getPARAM_INV_RECTIFICADORES_R16());
        Formato.setNoSerieR17(dcPower.getPARAM_INV_RECTIFICADORES_R17());
        Formato.setNoSerieR18(dcPower.getPARAM_INV_RECTIFICADORES_R18());
        Formato.setNoSerieR19(dcPower.getPARAM_INV_RECTIFICADORES_R19());
        Formato.setNoSerieR20(dcPower.getPARAM_INV_RECTIFICADORES_R20());
        Formato.setNoSerieR21(dcPower.getPARAM_INV_RECTIFICADORES_R21());
        Formato.setNoSerieR22(dcPower.getPARAM_INV_RECTIFICADORES_R22());
        Formato.setNoSerieR23(dcPower.getPARAM_INV_RECTIFICADORES_R23());
        Formato.setNoSerieR24(dcPower.getPARAM_INV_RECTIFICADORES_R24());
        Formato.setNoSerieR25(dcPower.getPARAM_INV_RECTIFICADORES_R25());
        Formato.setNoSerieR26(dcPower.getPARAM_INV_RECTIFICADORES_R26());
        Formato.setNoSerieR27(dcPower.getPARAM_INV_RECTIFICADORES_R27());
        Formato.setNoSerieR28(dcPower.getPARAM_INV_RECTIFICADORES_R28());
        Formato.setNoSerieR29(dcPower.getPARAM_INV_RECTIFICADORES_R29());
        Formato.setNoSerieR30(dcPower.getPARAM_INV_RECTIFICADORES_R30());
        Formato.setNoSerieR31(dcPower.getPARAM_INV_RECTIFICADORES_R31());
        Formato.setNoSerieR32(dcPower.getPARAM_INV_RECTIFICADORES_R32());
//PGIGRPDC(dcPower.getPARAM_INV_GABINETES_NA());
        Formato.setEspecificacionesGabinete(dcPower.getPARAM_INV_GABINETES_ESPECIFIC());
        Formato.setNoSerieGab1(dcPower.getPARAM_INV_GABINETES_GAB1());
        Formato.setNoSerieGab2(dcPower.getPARAM_INV_GABINETES_GAB2());
        Formato.setNoSerieGab3(dcPower.getPARAM_INV_GABINETES_GAB3());
        Formato.setNoSerieGab4(dcPower.getPARAM_INV_GABINETES_GAB4());
        Formato.setNoSerieGab5(dcPower.getPARAM_INV_GABINETES_GAB5());
        Formato.setNoSerieGab6(dcPower.getPARAM_INV_GABINETES_GAB6());
//VISPTVSSTAF(dcPower.getPARAM_SUPRESOR_NA());
        Formato.setVISPTVSSTAFModelo(dcPower.getPARAM_SUPRESOR_MODELO());
        Formato.setVISPTVSSTAFMarca(dcPower.getPARAM_SUPRESOR_MARCA());
        Formato.setInstalacionMecanicaGabineteTVSS(dcPower.getPARAM_SUPRESOR_INSTALAC());
        Formato.setCalibreCableadoConexionTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION());
        Formato.setCapacidadbreakersConexionTableroAc(dcPower.getPARAM_SUPRESOR_CAPACIDAD());
        Formato.setCalibreCableTierrasTVSS(dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS());
        Formato.setPAPFDCVoltajeAlimentacion(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1());
        Formato.setPAPFDCVoltajeFaseAB(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2());
        Formato.setPAPFDCVoltajeFaseBC(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3());
        Formato.setPAPFDCVoltajeFaseCA(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4());
        Formato.setPAPFDCVoltajeFaseAN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5());
        Formato.setPAPFDCVoltajeFaseBN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6());
        Formato.setPAPFDCVoltajeFaseCN(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7());
        Formato.setPAPFDCVoltajeNeutroGND(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8());
        Formato.setPOPFDCVoltajeFlotacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M1());
        Formato.setPOPFDCVoltajeIgualacion(dcPower.getPARAM_AyO_PLANTA_OPERAC_M2());
        Formato.setPOPFDCAlarmaBajoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M3());
        Formato.setPOPFDCAlarmaBajoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M4());
        Formato.setPOPFDCDesconexionBajoVoltaje(dcPower.getPARAM_AyO_PLANTA_OPERAC_M5());
        Formato.setPOPFDCAlarmaAltoVoltaje1(dcPower.getPARAM_AyO_PLANTA_OPERAC_M6());
        Formato.setPOPFDCAlarmaAltoVoltaje2(dcPower.getPARAM_AyO_PLANTA_OPERAC_M7());
        Formato.setPOPFDCAlarmaFusibleCargaAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M8());
        Formato.setPOPFDCAlarmaFusibleBateriasAbierto(dcPower.getPARAM_AyO_PLANTA_OPERAC_M9());
        Formato.setPOPFDCAlarmaFallaRectificador(dcPower.getPARAM_AyO_PLANTA_OPERAC_M10());
        Formato.setLimpiezaInternaExternaComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA());
        Formato.setLimpiezaIndividualComen(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV());
        Formato.setVerificacionTorquesComen(dcPower.getPARAM_AyO_PLANTA_VERIFICAION());
//STPFDC(dcPower.getPARAM_STPF_DC_NA());
        Formato.setVerificacionConexionesTerminalesComen(dcPower.getPARAM_STPF_DC_CAMP1());
        Formato.setDebeSerZaptaComen(dcPower.getPARAM_STPF_DC_CAMP2());
        Formato.setVerificacionEtiquetadoTierrasComen(dcPower.getPARAM_STPF_DC_CAMP3());
        Formato.setVerificacionCableadoExistenteTierrasComen(dcPower.getPARAM_STPF_DC_CAMP4());
        Formato.setMidaResistenciaComen(dcPower.getPARAM_STPF_DC_CAMP5());
        Formato.setCablesGabineteComen(dcPower.getPARAM_STPF_DC_CAMP6());
        Formato.setCableadoEstantesComen(dcPower.getPARAM_STPF_DC_CAMP7());
        Formato.setRetireTapasEquiposComen(dcPower.getPARAM_ITPF_DC_CAMP1());
        Formato.setVisualmenteInspeccionComen(dcPower.getPARAM_ITPF_DC_CAMP2());
        Formato.setAyudaCamaraTermograficaComen(dcPower.getPARAM_ITPF_DC_CAMP3());
        Formato.setEtapaRectificadoresComen(dcPower.getPARAM_ITPF_DC_CAMP4());
        Formato.setPanelGabineteACComen(dcPower.getPARAM_ITPF_DC_CAMP5());
        Formato.setPanelGabineteDCComen(dcPower.getPARAM_ITPF_DC_CAMP6());
        Formato.setCableadoAlimentacionComen(dcPower.getPARAM_ITPF_DC_CAMP7());
        Formato.setPGSDDCModelo(dcPower.getPARAM_SISTEMA_DIST_MODELO());
        Formato.setPGSDDCMarca(dcPower.getPARAM_SISTEMA_DIST_MARCA());
        Formato.setPGSDDCNoEspecifico(dcPower.getPARAM_SISTEMA_DIST_ESPECIFI());
        Formato.setPGSDDCCapacidad(dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD());
        Formato.setPGSDDCConfiguracion(dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION());
        Formato.setLimpiezaGabinete(dcPower.getPARAM_IDR_CAMP1());
        Formato.setVerificacionEstado(dcPower.getPARAM_IDR_CAMP2());
        Formato.setVerificarGabinete(dcPower.getPARAM_IDR_CAMP3());
        Formato.setVerificarTorque(dcPower.getPARAM_IDR_CAMP4());
        Formato.setVerificarTorqueCargasGabinete(dcPower.getPARAM_IDR_CAMP5());
        Formato.setVerificarModulos(dcPower.getPARAM_IDR_CAMP6());
        Formato.setValoresReales(dcPower.getPARAM_IDR_CAMP7());
        Formato.setBreakers1(dcPower.getPARAM_IDR_BREAK1());
        Formato.setBreakers2(dcPower.getPARAM_IDR_BREAK2());
        Formato.setBreakers3(dcPower.getPARAM_IDR_BREAK3());
        Formato.setBreakers4(dcPower.getPARAM_IDR_BREAK4());
        Formato.setBreakers5(dcPower.getPARAM_IDR_BREAK5());
        Formato.setBreakers6(dcPower.getPARAM_IDR_BREAK6());
        Formato.setBreakers7(dcPower.getPARAM_IDR_BREAK7());
        Formato.setBreakers8(dcPower.getPARAM_IDR_BREAK8());
        Formato.setPGSIModelo(dcPower.getPARAM_SISTEMA_INVER_MODELO());
        Formato.setPGSIMarca(dcPower.getPARAM_SISTEMA_INVER_MARCA());
        Formato.setPGSINoEspecificacion(dcPower.getPARAM_SISTEMA_INVER_ESPEC());
        Formato.setPGSICapacidad(dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD());
        Formato.setPGSIConfiguracion(dcPower.getPARAM_SISTEMA_INVER_CONFG());
        Formato.setNoSerieLineaAINV1(dcPower.getPARAM_SISTEMA_INVER_A_INV1());
        Formato.setNoSerieLineaAINV2(dcPower.getPARAM_SISTEMA_INVER_A_INV2());
        Formato.setNoSerieLineaAINV3(dcPower.getPARAM_SISTEMA_INVER_A_INV3());
        Formato.setNoSerieLineaAINV4(dcPower.getPARAM_SISTEMA_INVER_A_INV4());
        Formato.setNoSerieLineaAINV5(dcPower.getPARAM_SISTEMA_INVER_A_INV5());
        Formato.setNoSerieLineaAINV6(dcPower.getPARAM_SISTEMA_INVER_A_INV6());
        Formato.setNoSerieLineaAINV7(dcPower.getPARAM_SISTEMA_INVER_A_INV7());
        Formato.setNoSerieLineaAINV8(dcPower.getPARAM_SISTEMA_INVER_A_INV8());
        Formato.setNoSerieLineaAINV9(dcPower.getPARAM_SISTEMA_INVER_A_INV9());
        Formato.setNoSerieLineaAINV10(dcPower.getPARAM_SISTEMA_INVER_A_INV10());
        Formato.setNoSerieLineaBINV1(dcPower.getPARAM_SISTEMA_INVER_B_INV1());
        Formato.setNoSerieLineaBINV2(dcPower.getPARAM_SISTEMA_INVER_B_INV2());
        Formato.setNoSerieLineaBINV3(dcPower.getPARAM_SISTEMA_INVER_B_INV3());
        Formato.setNoSerieLineaBINV4(dcPower.getPARAM_SISTEMA_INVER_B_INV4());
        Formato.setNoSerieLineaBINV5(dcPower.getPARAM_SISTEMA_INVER_B_INV5());
        Formato.setNoSerieLineaBINV6(dcPower.getPARAM_SISTEMA_INVER_B_INV6());
        Formato.setNoSerieLineaBINV7(dcPower.getPARAM_SISTEMA_INVER_B_INV7());
        Formato.setNoSerieLineaBINV8(dcPower.getPARAM_SISTEMA_INVER_B_INV8());
        Formato.setNoSerieLineaBINV9(dcPower.getPARAM_SISTEMA_INVER_B_INV9());
        Formato.setNoSerieLineaBINV10(dcPower.getPARAM_SISTEMA_INVER_B_INV10());
        Formato.setNoSerieLineaCINV1(dcPower.getPARAM_SISTEMA_INVER_C_INV1());
        Formato.setNoSerieLineaCINV2(dcPower.getPARAM_SISTEMA_INVER_C_INV2());
        Formato.setNoSerieLineaCINV3(dcPower.getPARAM_SISTEMA_INVER_C_INV3());
        Formato.setNoSerieLineaCINV4(dcPower.getPARAM_SISTEMA_INVER_C_INV4());
        Formato.setNoSerieLineaCINV5(dcPower.getPARAM_SISTEMA_INVER_C_INV5());
        Formato.setNoSerieLineaCINV6(dcPower.getPARAM_SISTEMA_INVER_C_INV6());
        Formato.setNoSerieLineaCINV7(dcPower.getPARAM_SISTEMA_INVER_C_INV7());
        Formato.setNoSerieLineaCINV8(dcPower.getPARAM_SISTEMA_INVER_C_INV8());
        Formato.setNoSerieLineaCINV9(dcPower.getPARAM_SISTEMA_INVER_C_INV9());
        Formato.setNoSerieLineaCINV10(dcPower.getPARAM_SISTEMA_INVER_C_INV10());
        Formato.setVerificarManeraVisualComen(dcPower.getPARAM_SISTEMA_INVER_VERI1());
        Formato.setVerificarReportarExistenciaComen(dcPower.getPARAM_SISTEMA_INVER_VERI2());
        Formato.setVerificacionBuenEstadoComen(dcPower.getPARAM_SISTEMA_INVER_VERI3());
        Formato.setVerificarRegistrarCargaComen(dcPower.getPARAM_SISTEMA_INVER_VERI4());
        Formato.setLimpiezaGabineteInversor(dcPower.getPARAM_SISTEMA_INVER_LIMP());
        Formato.setExtraerModuloCom(dcPower2.getCampoextra1());
        Formato.setColocarModuloInversorPosicionOriginalCom(dcPower2.getCampoextra2());
        Formato.setVerificarGabineteConectadoSistemaTierrasCom(dcPower2.getCampoextra3());
        Formato.setVerificarTorqueCableadoEntradaSalidaSistemaInversorcom(dcPower2.getCampoextra4());
        Formato.setExtrerComputadoraInventarioSistemaCom(dcPower2.getCampoextra5());
        Formato.setVerificarModulosSupervisionSistemaCom(dcPower2.getCampoextra6());
        Formato.setVoltajeFaseABVac(dcPower.getPARAM_NAyS_ALIM_VAC1());
        Formato.setVoltajeFaseBCVac(dcPower.getPARAM_NAyS_ALIM_VAC2());
        Formato.setVoltajeFaseCAVac(dcPower.getPARAM_NAyS_ALIM_VAC3());
        Formato.setVoltajeFaseANVac(dcPower.getPARAM_NAyS_ALIM_VAC4());
        Formato.setVoltajeFaseBNVac(dcPower.getPARAM_NAyS_ALIM_VAC5());
        Formato.setVoltajeFaseCNVac(dcPower.getPARAM_NAyS_ALIM_VAC6());
        Formato.setVoltajeNeutroVac(dcPower.getPARAM_NAyS_ALIM_VAC7());
        Formato.setVoltajeAlimentacionDCVAC(dcPower.getPARAM_NAyS_ALIM_VAC8());
        Formato.setVoltajeFaseABSVac(dcPower.getPARAM_NAyS_SALID_VAC1());
        Formato.setVoltajeFaseBCSVac(dcPower.getPARAM_NAyS_SALID_VAC2());
        Formato.setVoltajeFaseCASVac(dcPower.getPARAM_NAyS_SALID_VAC3());
        Formato.setVoltajeFaseANSVac(dcPower.getPARAM_NAyS_SALID_VAC4());
        Formato.setVoltajeFaseBNSVac(dcPower.getPARAM_NAyS_SALID_VAC5());
        Formato.setVoltajeFaseCNSVac(dcPower.getPARAM_NAyS_SALID_VAC6());
        Formato.setVoltajeNeutroSVac(dcPower.getPARAM_NAyS_SALID_VAC7());
        Formato.setVoltajeFaseABACA(dcPower.getPARAM_NAyS_ALIM_ACA1());
        Formato.setVoltajeFaseBCACA(dcPower.getPARAM_NAyS_ALIM_ACA2());
        Formato.setVoltajeFaseCAACA(dcPower.getPARAM_NAyS_ALIM_ACA3());
        Formato.setVoltajeFaseANACA(dcPower.getPARAM_NAyS_ALIM_ACA4());
        Formato.setVoltajeFaseBNACA(dcPower.getPARAM_NAyS_ALIM_ACA5());
        Formato.setVoltajeFaseCNACA(dcPower.getPARAM_NAyS_ALIM_ACA6());
        Formato.setVoltajeNeutroACA(dcPower.getPARAM_NAyS_ALIM_ACA7());
        Formato.setVoltajeAlimentacionDCACA(dcPower.getPARAM_NAyS_ALIM_ACA8());
        Formato.setVoltajeFaseABSACA(dcPower.getPARAM_NAyS_SALID_ACA1());
        Formato.setVoltajeFaseBCSACA(dcPower.getPARAM_NAyS_SALID_ACA2());
        Formato.setVoltajeFaseCASACA(dcPower.getPARAM_NAyS_SALID_ACA3());
        Formato.setVoltajeFaseANSACA(dcPower.getPARAM_NAyS_SALID_ACA4());
        Formato.setVoltajeFaseBNSACA(dcPower.getPARAM_NAyS_SALID_ACA5());
        Formato.setVoltajeFaseCNSACA(dcPower.getPARAM_NAyS_SALID_ACA6());
        Formato.setVoltajeNeutroSACA(dcPower.getPARAM_NAyS_SALID_ACA7());



        //Modulo 3
        Formato.setActividad(dcPower2.getCOMENTA_ACTIVIDADES());
        Formato.setDiagnostico(dcPower2.getCOMENTA_DIAGNOSTICO());
        Formato.setAccionCorrectiva(dcPower2.getCOMENTA_ACCION());
        Formato.setComentariosRecomendaciones(dcPower2.getCOMENTA_COMENTARIOS());

        //Modulo 4
        Formato.setCantidad1(dcPower2.getMATERIA_CANTIDAD1());
        Formato.setCantidad2(dcPower2.getMATERIA_CANTIDAD2());
        Formato.setCantidad3(dcPower2.getMATERIA_CANTIDAD3());
        Formato.setCantidad4(dcPower2.getMATERIA_CANTIDAD4());
        Formato.setCantidad5(dcPower2.getMATERIA_CANTIDAD5());
        Formato.setCantidad6(dcPower2.getMATERIA_CANTIDAD6());
        Formato.setCantidad7(dcPower2.getMATERIA_CANTIDAD7());
        Formato.setNoParte1(dcPower2.getMATERIA_N_PARTE1());
        Formato.setNoParte2(dcPower2.getMATERIA_N_PARTE2());
        Formato.setNoParte3(dcPower2.getMATERIA_N_PARTE3());
        Formato.setNoParte4(dcPower2.getMATERIA_N_PARTE4());
        Formato.setNoParte5(dcPower2.getMATERIA_N_PARTE5());
        Formato.setNoParte6(dcPower2.getMATERIA_N_PARTE6());
        Formato.setNoParte7(dcPower2.getMATERIA_N_PARTE7());
        Formato.setDescripcion1(dcPower2.getMATERIA_DESC1());
        Formato.setDescripcion2(dcPower2.getMATERIA_DESC2());
        Formato.setDescripcion3(dcPower2.getMATERIA_DESC3());
        Formato.setDescripcion4(dcPower2.getMATERIA_DESC4());
        Formato.setDescripcion5(dcPower2.getMATERIA_DESC5());
        Formato.setDescripcion6(dcPower2.getMATERIA_DESC6());
        Formato.setDescripcion7(dcPower2.getMATERIA_DESC7());
        Formato.setEquipo1(dcPower2.getMATERIA_EQUIPO_EQUIPO1());
        Formato.setEquipo2(dcPower2.getMATERIA_EQUIPO_EQUIPO2());
        Formato.setEquipo3(dcPower2.getMATERIA_EQUIPO_EQUIPO3());
        Formato.setEquipo4(dcPower2.getMATERIA_EQUIPO_EQUIPO4());
        Formato.setEquipo5(dcPower2.getMATERIA_EQUIPO_EQUIPO5());
        //Formato.setEMEquipo6(dcPower2.getMATERIA_EQUIPO_EQUIPO6());
        //Formato.setEMEquipo7(dcPower2.getequipo7);
        Formato.setNoId1(dcPower2.getMATERIA_EQUIPO_ID1());
        Formato.setNoId2(dcPower2.getMATERIA_EQUIPO_ID2());
        Formato.setNoId3(dcPower2.getMATERIA_EQUIPO_ID3());
        Formato.setNoId4(dcPower2.getMATERIA_EQUIPO_ID4());
        Formato.setNoId5(dcPower2.getMATERIA_EQUIPO_ID5());
        //Formato.setNoID6(dcPower2.getMATERIA_EQUIPO_ID6());
        // Formato.setNoID7(dcPower2.getn);
        Formato.setFechaVencimiento1(dcPower2.getMATERIA_EQUIPO_FEVHA1());
        Formato.setFechaVencimiento2(dcPower2.getMATERIA_EQUIPO_FEVHA2());
        Formato.setFechaVencimiento3(dcPower2.getMATERIA_EQUIPO_FEVHA3());
        Formato.setFechaVencimiento4(dcPower2.getMATERIA_EQUIPO_FEVHA4());
        Formato.setFechaVencimiento5(dcPower2.getMATERIA_EQUIPO_FEVHA5());
        //Formato.setFechaVencimiento6(dcPower2.getMATERIA_EQUIPO_FEVHA6());
        //Formato.setFechaVencimiento7(cdPower2ge);

        //Modulo 5
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}

        //Modulo 7
        Formato.setFirmaCliente(dcPower2.getFIRMA_FIRMA1());
        Formato.setFirmaVertiv(dcPower2.getFIRMA_FIRMA2());
        Formato.setFirmaClienteFinal(dcPower2.getFIRMA_FIRMA3());
        Formato.setNombreFirmaCliente(dcPower2.getFIRMA_NOMBRE1());
        Formato.setNombreFirmaVertiv(dcPower2.getFIRMA_NOMBRE2());
        Formato.setNombreFirmaClienteFinal(dcPower2.getFIRMA_NOMBRE3());
        Formato.setCLIENTEFINAL_EMPRESA(dcPower2.getFIRMA_EMPRESA());
        Formato.setCLIENTEFINAL_TELEFONO(dcPower2.getFIRMA_TELEFONO());
        Formato.setCLIENTEFINAL_CORREO(dcPower2.getFIRMA_CORREO());

        //Modulo 8
        Formato.setAD_NOMBRE1(dcPower2.getADICIONALES_NOMBRE1());
        Formato.setAD_CORREO1(dcPower2.getFIRMA_CORREO1());
        Formato.setAD_NOMBRE2(dcPower2.getADICIONALES_NOMBRE2());
        Formato.setAD_CORREO2(dcPower2.getFIRMA_CORREO2());
        Formato.setAD_NOMBRE3(dcPower2.getADICIONALES_NOMBRE3());
        Formato.setAD_CORREO3(dcPower2.getFIRMA_CORREO3());
        Formato.setAD_NOMBRE4(dcPower2.getADICIONALES_NOMBRE4());
        Formato.setAD_CORREO4(dcPower2.getFIRMA_CORREO4());
        Formato.setAD_NOMBRE5(dcPower2.getADICIONALES_NOMBRE5());
        Formato.setAD_CORREO5(dcPower2.getFIRMA_CORREO5());
        //endregion
        compositeDisposable.add(iMyAPI.enviarDCPower(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<com.maju.desarrollo.testfcs.ServiceClass.DCPower>() {
                            @Override
                            public void accept(com.maju.desarrollo.testfcs.ServiceClass.DCPower lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })
                );
    }
    public void formato4(final String id_formato){
        SGarantias Formato = D_B.obtenerGarantia_id(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");
        Garantia item = new Garantia();
        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{
            item.setSRProyecto(Formato.getSR());
        }
        item.setFolioPreTrabajo(Formato.getFolioPreTrabajo());
        item.setFechaInicio(Formato.getFECHAINICIO());
        item.setFechaTermino(Formato.getFECHAFIN());
        item.setIdCliente(Formato.getCliente());
        //item.setSRProyecto(Formato.getSR());
        item.setTASK(Formato.getTASK());
        item.setContactoCliente(Formato.getContacto());
        item.setTipoServicio(Formato.getTIPOSERVICIO());
        item.setTelefono(Formato.getTeléfono());
        item.setFreecuencia(Formato.getFRECUENCIA());
        item.setEMail(Formato.getMail());
        item.setNoTag(Formato.getNTAG());
        item.setModeloEquipo(Formato.getMODELO());
        item.setNoSerie(Formato.getNSERIE());
        item.setDireccionSitio(Formato.getDirección());
        item.setTipoGararntia(Formato.getTIPOGARANTIA());
        item.setFolioCCC(Formato.getFOLIOCCC());
        item.setFehcaLote(Formato.getFECHALOTE());
        item.setLotePedimento(Formato.getLOTE());
        item.setAduana(Formato.getADUANA());
        item.setNuevoSERIAL(Formato.getNUEVOSERIAL());
        item.setReporteFalla(Formato.getREPORTEFALLA());
        item.setComentariosRecomendaciones(Formato.getCOMENTARIOS());
        item.setCantidad1(Formato.getCANTIDAD1());
        item.setCantidad2(Formato.getCANTIDAD2());
        item.setCantidad3(Formato.getCANTIDAD3());
        item.setCantidad4(Formato.getCANTIDAD4());
        item.setCantidad5(Formato.getCANTIDAD5());
        item.setCantidad6(Formato.getCANTIDAD6());
        item.setCantidad7(Formato.getCANTIDAD7());
        item.setCantidad8(Formato.getCANTIDAD8());
        item.setCantidad9(Formato.getCANTIDAD9());
        item.setNoParte1(Formato.getNPARTE1());
        item.setNoParte2(Formato.getNPARTE2());
        item.setNoParte3(Formato.getNPARTE3());
        item.setNoParte4(Formato.getNPARTE4());
        item.setNoParte5(Formato.getNPARTE5());
        item.setNoParte6(Formato.getNPARTE6());
        item.setNoParte7(Formato.getNPARTE7());
        item.setNoParte8(Formato.getNPARTE8());
        item.setNoParte9(Formato.getNPARTE9());
        item.setDescripcion1(Formato.getESPECIFICACION1());
        item.setDescripcion2(Formato.getESPECIFICACION2());
        item.setDescripcion3(Formato.getESPECIFICACION3());
        item.setDescripcion4(Formato.getESPECIFICACION4());
        item.setDescripcion5(Formato.getESPECIFICACION5());
        item.setDescripcion6(Formato.getESPECIFICACION6());
        item.setDescripcion7(Formato.getESPECIFICACION7());
        item.setDescripcion8(Formato.getESPECIFICACION8());
        item.setDescripcion9(Formato.getESPECIFICACION9());
        item.setEquipo1(Formato.getEQUIPO1());
        item.setEquipo2(Formato.getEQUIPO2());
        item.setEquipo3(Formato.getEQUIPO3());
        item.setEquipo4(Formato.getEQUIPO4());
        item.setEquipo5(Formato.getEQUIPO5());

        item.setNoId1(Formato.getNoID1());
        item.setNoId2(Formato.getNoID2());
        item.setNoId3(Formato.getNoID3());
        item.setNoId4(Formato.getNoID4());
        item.setNoId5(Formato.getNoID5());

        item.setFechaVencimiento1(Formato.getFECHA1());
        item.setFechaVencimiento2(Formato.getFECHA2());
        item.setFechaVencimiento3(Formato.getFECHA3());
        item.setFechaVencimiento4(Formato.getFECHA4());
        item.setFechaVencimiento5(Formato.getFECHA5());

        try{if(item.getAntesFoto1().contains(".jpg")){
            item.setAntesFoto1(b64.ImgB64(item.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto2().contains(".jpg")){
            item.setAntesFoto2(b64.ImgB64(item.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto3().contains(".jpg")){
            item.setAntesFoto3(b64.ImgB64(item.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto4().contains(".jpg")){
            item.setAntesFoto4(b64.ImgB64(item.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto5().contains(".jpg")){
            item.setAntesFoto5(b64.ImgB64(item.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(item.getAntesFoto6().contains(".jpg")){
            item.setAntesFoto6(b64.ImgB64(item.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto1().contains(".jpg")){
            item.setDespuesFoto1(b64.ImgB64(item.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto2().contains(".jpg")){
            item.setDespuesFoto2(b64.ImgB64(item.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto3().contains(".jpg")){
            item.setDespuesFoto3(b64.ImgB64(item.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto4().contains(".jpg")){
            item.setDespuesFoto4(b64.ImgB64(item.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto5().contains(".jpg")){
            item.setDespuesFoto5(b64.ImgB64(item.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(item.getDespuesFoto6().contains(".jpg")){
            item.setDespuesFoto6(b64.ImgB64(item.getDespuesFoto6()));
        }}catch (Exception e){}

        item.setFirmaCliente(Formato.getIMAGENFIRMA1());
        item.setFirmaVertiv(Formato.getIMAGENFIRMA2());
        item.setFirmaClienteFinal(Formato.getIMAGENFIRMA3());

        item.setNombreFirmaCliente(Formato.getFIRMA1());
        item.setNombreFirmaVertiv(Formato.getFIRMA2());
        item.setNombreFirmaClienteFinal(Formato.getFIRMA13());
        item.setAD_NOMBRE1(Formato.getAD_NOMBRE1());
        item.setAD_CORREO1(Formato.getAD_CORREO1());
        item.setAD_NOMBRE2(Formato.getAD_NOMBRE2());
        item.setAD_CORREO2(Formato.getAD_CORREO2());
        item.setAD_NOMBRE3(Formato.getAD_NOMBRE3());
        item.setAD_CORREO3(Formato.getAD_CORREO3());
        item.setAD_NOMBRE4(Formato.getAD_NOMBRE4());
        item.setAD_CORREO4(Formato.getAD_CORREO4());
        item.setAD_NOMBRE5(Formato.getAD_NOMBRE5());
        item.setAD_CORREO5(Formato.getAD_CORREO5());
        item.setCLIENTEFINAL_EMPRESA(Formato.getCLIENTEFINAL_EMPRESA());
        item.setCLIENTEFINAL_TELEFONO(Formato.getcLIENTEFINAL_TELEFONO());
        item.setCLIENTEFINAL_CORREO(Formato.getCLIENTEFINAL_CORREO());
        item.setGarantiaArchivo(Formato.getGarantiaArchivo());
        item.setACCIONCORRECTIVA(Formato.getACCIONCORRECTIVA());

        item.setIdUsuario(usuario.getID_USER());

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
            compositeDisposable.add(iMyAPI.enviarGarantia(item).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Garantia>() {
                            @Override
                            public void accept(Garantia lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );

    }
    public void formato5(final String id_formato){
        Bestel1 Formato  = D_B.obtenerBestel1(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}

        //Bestel1 item = D_B.obtenerBestel1Prellenado();
           compositeDisposable.add(iMyAPI.enviarBestel1(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Bestel1>() {
                            @Override
                            public void accept(Bestel1 lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);
                                } else {
                                    D_B.newMensaje(lista.getError());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );
    }
    public void formato6(final String id_formato){
        Bestel2 Formato= D_B.obtenerBestel2(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        try{if(Formato.getAntesFoto1().contains(".jpg")){
            Formato.setAntesFoto1(b64.ImgB64(Formato.getAntesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto2().contains(".jpg")){
            Formato.setAntesFoto2(b64.ImgB64(Formato.getAntesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto3().contains(".jpg")){
            Formato.setAntesFoto3(b64.ImgB64(Formato.getAntesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto4().contains(".jpg")){
            Formato.setAntesFoto4(b64.ImgB64(Formato.getAntesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto5().contains(".jpg")){
            Formato.setAntesFoto5(b64.ImgB64(Formato.getAntesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getAntesFoto6().contains(".jpg")){
            Formato.setAntesFoto6(b64.ImgB64(Formato.getAntesFoto6()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto1().contains(".jpg")){
            Formato.setDespuesFoto1(b64.ImgB64(Formato.getDespuesFoto1()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto2().contains(".jpg")){
            Formato.setDespuesFoto2(b64.ImgB64(Formato.getDespuesFoto2()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto3().contains(".jpg")){
            Formato.setDespuesFoto3(b64.ImgB64(Formato.getDespuesFoto3()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto4().contains(".jpg")){
            Formato.setDespuesFoto4(b64.ImgB64(Formato.getDespuesFoto4()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto5().contains(".jpg")){
            Formato.setDespuesFoto5(b64.ImgB64(Formato.getDespuesFoto5()));
        }}catch (Exception e){}
        try{if(Formato.getDespuesFoto6().contains(".jpg")){
            Formato.setDespuesFoto6(b64.ImgB64(Formato.getDespuesFoto6()));
        }}catch (Exception e){}


        compositeDisposable.add(iMyAPI.enviarBestel2(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Bestel2>() {
                            @Override
                            public void accept(Bestel2 lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );
    }
    public void formato7(final String id_formato){
        PDU Formato = D_B.obtenerPDU(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}

         compositeDisposable.add(iMyAPI.enviarPDU(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<PDU>() {
                            @Override
                            public void accept(PDU lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );
    }
    public void formato8(final String id_formato){
        Servicios Formato= D_B.obtenerServicio(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        //Bestel1 item = D_B.obtenerBestel1Prellenado();

        GeneralServicios item =D_B.generarServiciosparaEnvioServer(Formato);
        if(Formato.getProyecto().length()>1){item.setSRProyecto(Formato.getProyecto());}
        else{item.setSRProyecto(Formato.getSR());}
        try{if(Formato.getFOTOANTES1().contains(".jpg")){
            item.setAntes1(b64.ImgB64(Formato.getFOTOANTES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES2().contains(".jpg")){
            item.setAntes2(b64.ImgB64(Formato.getFOTOANTES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES3().contains(".jpg")){
            item.setAntes3(b64.ImgB64(Formato.getFOTOANTES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES4().contains(".jpg")){
            item.setAntes4(b64.ImgB64(Formato.getFOTOANTES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES5().contains(".jpg")){
            item.setAntes5(b64.ImgB64(Formato.getFOTOANTES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTOANTES6().contains(".jpg")){
            item.setAntes6(b64.ImgB64(Formato.getFOTOANTES6()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES1().contains(".jpg")){
            item.setDespues1(b64.ImgB64(Formato.getFOTODESPUES1()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES2().contains(".jpg")){
            item.setDespues2(b64.ImgB64(Formato.getFOTODESPUES2()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES3().contains(".jpg")){
            item.setDespues3(b64.ImgB64(Formato.getFOTODESPUES3()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES4().contains(".jpg")){
            item.setDespues4(b64.ImgB64(Formato.getFOTODESPUES4()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES5().contains(".jpg")){
            item.setDespues5(b64.ImgB64(Formato.getFOTODESPUES5()));
        }}catch (Exception e){}
        try{if(Formato.getFOTODESPUES6().contains(".jpg")){
            item.setDespues6(b64.ImgB64(Formato.getFOTODESPUES6()));
        }}catch (Exception e){}

        compositeDisposable.add(iMyAPI.enviarGServicios(item).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<GeneralServicios>() {
                    @Override
                    public void accept(GeneralServicios lista) throws Exception {
                        //lista.get(0).getItem_Number();

                        if(lista.getExitoso().equals("1")) {
                            D_B.actualizarEsatusFormato("3",id_formato);

                            alerta.dismiss();

                        }else{
                            alerta.dismiss();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        alerta.dismiss();
                    }
                })

        );
    }
    public void formato9(final String id_formato){
        STS2 Formato= D_B.obtenerSTS2(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}

         compositeDisposable.add(iMyAPI.enviarSTS2(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<STS2>() {
                            @Override
                            public void accept(STS2 lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );
                //endregion


        }
    public void formato10(final String id_formato){
        ThermalManagagementS Formato= D_B.obtenerThermal(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        Formato.setIdUsuario(usuario.getID_USER());
        if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
         compositeDisposable.add(iMyAPI.enviarThermal(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<ThermalManagagementS>() {
                            @Override
                            public void accept(ThermalManagagementS lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                alerta.dismiss();
                            }
                        })

                );
    }
    public void formato11(final String id_formato){
        UPS Formato= D_B.obtenerUPS(id_formato);
        final progresoLoad alerta = new progresoLoad();
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        Formato.setIdUsuario(usuario.getID_USER());
        try{if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}}catch (Exception e){}
        //Bestel1 item = D_B.obtenerBestel1Prellenado();
        try{if(Formato.getAntes1().contains(".jpg")){
            Formato.setAntes1(b64.ImgB64(Formato.getAntes1()));
        }}catch (Exception e){}
        try{if(Formato.getAntes2().contains(".jpg")){
            Formato.setAntes2(b64.ImgB64(Formato.getAntes2()));
        }}catch (Exception e){}
        try{if(Formato.getAntes3().contains(".jpg")){
            Formato.setAntes3(b64.ImgB64(Formato.getAntes3()));
        }}catch (Exception e){}
        try{if(Formato.getAntes4().contains(".jpg")){
            Formato.setAntes4(b64.ImgB64(Formato.getAntes4()));
        }}catch (Exception e){}
        try{if(Formato.getAntes5().contains(".jpg")){
            Formato.setAntes5(b64.ImgB64(Formato.getAntes5()));
        }}catch (Exception e){}
        try{if(Formato.getAntes6().contains(".jpg")){
            Formato.setAntes6(b64.ImgB64(Formato.getAntes6()));
        }}catch (Exception e){}
        try{if(Formato.getDespues1().contains(".jpg")){
            Formato.setDespues1(b64.ImgB64(Formato.getDespues1()));
        }}catch (Exception e){}
        try{if(Formato.getDespues2().contains(".jpg")){
            Formato.setDespues2(b64.ImgB64(Formato.getDespues2()));
        }}catch (Exception e){}
        try{if(Formato.getDespues3().contains(".jpg")){
            Formato.setDespues3(b64.ImgB64(Formato.getDespues3()));
        }}catch (Exception e){}
        try{if(Formato.getDespues4().contains(".jpg")){
            Formato.setDespues4(b64.ImgB64(Formato.getDespues4()));
        }}catch (Exception e){}
        try{if(Formato.getDespues5().contains(".jpg")){
            Formato.setDespues5(b64.ImgB64(Formato.getDespues5()));
        }}catch (Exception e){}
        try{if(Formato.getDespues6().contains(".jpg")){
            Formato.setDespues6(b64.ImgB64(Formato.getDespues6()));
        }}catch (Exception e){}

          compositeDisposable.add(iMyAPI.enviarUPS(Formato).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<UPS>() {
                            @Override
                            public void accept(UPS lista) throws Exception {
                                //lista.get(0).getItem_Number();
                                alerta.dismiss();
                                if (lista.getExitoso().equals("1")) {
                                    D_B.actualizarEsatusFormato("3",id_formato);

                                } else {
                                    D_B.newMensaje(lista.getError());

                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                               alerta.dismiss();
                            }
                        })
                );
    }
*/



    //endregion


}

