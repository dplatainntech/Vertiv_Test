package com.maju.desarrollo.testfcs.Formatos.Thermal;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.maju.desarrollo.testfcs.AccionesM.CContrasena;
import com.maju.desarrollo.testfcs.Alerts.AlertaGenerica;
import com.maju.desarrollo.testfcs.Alerts.AlertaOk;
import com.maju.desarrollo.testfcs.Alerts.progresoLoad;
import com.maju.desarrollo.testfcs.Check.ImgB64;
import com.maju.desarrollo.testfcs.Check.InternetandVPN;
import com.maju.desarrollo.testfcs.Formatos.UPS.UpsAdicionalFragment;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.MenuPrincipalFragment;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.Remote.IMyAPI;
import com.maju.desarrollo.testfcs.Remote.RetrofitClient;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ServiceClass.ThermalManagagementS;
import com.maju.desarrollo.testfcs.ui.login.LoginActivity;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class ThermMenuFragment extends Fragment {
  OperacionesDB D_B;
  String id_formato;
  IMyAPI iMyAPI;
  CompositeDisposable compositeDisposable = new CompositeDisposable();
  ThermalManagagementS Formato;
  ImageView im1 ,im2,im3,im4 ,im5,im6,im7,im8;
  int completo;
  Button Terminar;
  InternetandVPN validaciones = new InternetandVPN();
  UsuarioD usuario;
  ImgB64 b64 = new ImgB64();
  progresoLoad alerta = new progresoLoad();

  public ThermMenuFragment() {
    // Required empty public constructor
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View v = inflater.inflate(R.layout.fragment_therm_menu, container, false);
    ((MainActivity) getActivity()).verCabecera();
    D_B = OperacionesDB.obtenerInstancia(getContext());
    iMyAPI = RetrofitClient.getInstance().create(IMyAPI.class);
    id_formato= getArguments().getString("key_idFormato");
    usuario = D_B.obtenerUsuario();
    if(id_formato.equals("NUEVO")){
      id_formato = D_B.newThermal();  // = nuevaPreOrden()
      D_B.nuevoRegistroFormato(id_formato,"10"); //crear registrode formato. Thermal
      Formato = D_B.obtenerThermal(id_formato);
    }else{
      Formato = D_B.obtenerThermal(id_formato);
    }


    LinearLayout M1 = (LinearLayout)v.findViewById(R.id.MN_M1);
    LinearLayout M2 = (LinearLayout)v.findViewById(R.id.MN_M2);
    LinearLayout M3 = (LinearLayout)v.findViewById(R.id.MN_M3);
    LinearLayout M4 = (LinearLayout)v.findViewById(R.id.MN_M4);
    LinearLayout M5 = (LinearLayout)v.findViewById(R.id.MN_M5);
    LinearLayout M6 = (LinearLayout)v.findViewById(R.id.MN_M6);
    LinearLayout M7 = (LinearLayout)v.findViewById(R.id.MN_M7);
    LinearLayout M8 = (LinearLayout)v.findViewById(R.id.MN_M8);
    im1 = (ImageView)v.findViewById(R.id.imgM1);
    im2 = (ImageView)v.findViewById(R.id.imgM2);
    im3 = (ImageView)v.findViewById(R.id.imgM3);
    im4 = (ImageView)v.findViewById(R.id.imgM4);
    im5 = (ImageView)v.findViewById(R.id.imgM5);
    im6 = (ImageView)v.findViewById(R.id.imgM6);
    im7 = (ImageView)v.findViewById(R.id.imgM7);
    im8 = (ImageView)v.findViewById(R.id.imgM8);

    //region Botones acciones
    M1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermGeneralFragment myfargment = new ThermGeneralFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();


      }
    });
    M2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermParametrosFragment myfargment = new ThermParametrosFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();

      }
    });
    M3.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermComentariosFragment myfargment = new ThermComentariosFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
      }
    });

    M4.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermMaterialesFragment myfargment = new ThermMaterialesFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();


      }
    });
    M5.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermFotosFragment myfargment = new ThermFotosFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
      }
    });
    M6.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermFirmasFragment myfargment = new ThermFirmasFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
      }
    });
    M7.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        ThermAdicionalFragment myfargment = new ThermAdicionalFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
      }
    });
    M8.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Bundle args = new Bundle();
        args.putString("key_idFormato", id_formato);

        UpsAdicionalFragment myfargment = new UpsAdicionalFragment();
        myfargment.setArguments(args);
        FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
      }
    });



    //region Terminar Boton
    Terminar = (Button)v.findViewById(R.id.button2);
    Terminar.setEnabled(false);
    Terminar.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //region validaciones y envio de Formato
        alerta.setCancelable(true);
        alerta.show(getFragmentManager(), "a");

        try {
          if(validaciones.webservise()){
            if(validaciones.vpnOn()){
              // Login activo = validaciones.activo(usuario.getID_USER(), usuario.getCONTRASEÑA(), iMyAPI);
              Login itemAct = new Login();
              itemAct.setUsuario(usuario.getCORREO());
              itemAct.setContraseña(usuario.getCONTRASEÑA());

              compositeDisposable.add(iMyAPI.Login(itemAct).subscribeOn(Schedulers.io())
                      .observeOn(AndroidSchedulers.mainThread())
                      .subscribe(new Consumer<Login>() {
                        @Override
                        public void accept(Login item) throws Exception {
                          if(item.getExitoso().equals("1") || item.getExitoso().equals("4")){
                            if(item.getExitoso().equals("4")){
                              alertaMensajes("Importante", item.getError());
                            }
                            enviarFormato();
                          }
                          else{
                            alerta.dismiss();
                            if(item.getExitoso().equals("0") || item.getExitoso().equals("3")){
                              D_B.CambioSesion(usuario,"INACTIVO");
                              alertaMensajes("Importante", item.getError());

                              Intent siguiente2 = new Intent(getActivity(), LoginActivity.class);
                              startActivity(siguiente2);
                              overridePendingTransition(R.anim.left_in, R.anim.left_out);

                            }
                            if(item.getExitoso().equals("2")){
                              alertaMensajes("Importante", item.getError());
                              Intent siguiente2 = new Intent(getActivity(), CContrasena.class);
                              startActivity(siguiente2);
                            }

                          }

                        }
                      }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                          //Toast.makeText(this(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                          //alerta(throwable.getMessage());
                        }
                      })
              );

            }
            else {
              alerta.dismiss();
              alertaMensajes("Alerta", "No hay conexión a la VPN");
            }
          }
          else{
            alertaMensajes("Importante", "No se establecio conexión a internet, formato guardado localmente");

            MenuPrincipalFragment myfargment = new MenuPrincipalFragment();
            FragmentManager fragmentManager = (getActivity()).getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_fragment_placeholder, myfargment).addToBackStack(null).commit();
            alerta.dismiss();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
          alerta.dismiss();
        } catch (IOException e) {
          e.printStackTrace();
          alerta.dismiss();
        }
        //endregion
      }
    });
//endregion
    verAcencesModulos();
    return v;
  }

  public void verAcencesModulos(){
    completo = 7;

    //region Modulo  Generales
    int M1_generales = 11;

    try{if(!Formato.getFolioPreTrabajo().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getDireccionSitio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getProyecto().isEmpty() || !Formato.getTASK().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getTipoServicio().isEmpty()){
          if(Formato.getTipoServicio().equals("Otro (Especifique)")){
            if(!Formato.getTipoServicioOtro().isEmpty()){M1_generales = M1_generales - 1;}
          }else{
            M1_generales = M1_generales - 1;
          }
    }
    }catch (Exception e){}
    try{if(!Formato.getFreecuencia().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getFechaInicio().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getFechaTermino().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getContactoCliente().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getTelefono().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}
    try{if(!Formato.getEMail().isEmpty()){M1_generales = M1_generales - 1;}}catch (Exception e){}

    if(M1_generales == 0){ im1.setImageResource(R.drawable.generalverd);
      completo = completo-1;}
    else if(M1_generales < 11){im1.setImageResource(R.drawable.generalnar);}
    else {im1.setImageResource(R.drawable.generalgris);}

    //endregion

    //region Modulo 2

    //region Validaciones Colores en boton
    int valorPr=0;
    int m1 = 8;

    try{if(Formato.getSETTEMP().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getSETHUM().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getSENS().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getSENS2().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getHITEMP().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getHIHUM().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getLOTEMP().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getLOHUM().length()>0){m1= m1-1;valorPr++;}}catch(Exception e){}


    int m2 = 7;
    try{if(Formato.getMarcaUniMane().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getTipoEvaporadorUniMane().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModeloUniMane().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVoltajeGeneralL1L2().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVoltajeGeneralL2L3().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVoltajeGeneralL3L1().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getNoSerieUniMane().length()>0){m2= m2-1;valorPr++;}}catch(Exception e){}

    int m3 = 12;
    try{if(Formato.getNoResistencias().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getEstadoFísico().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVerificación().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getConductores().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getContactores().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getFusibles().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia1L1().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia1L2().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia2L1().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia2L2().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia3L1().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpResistencia3L2().length()>0){m3= m3-1;valorPr++;}}catch(Exception e){}

    int m4= 14;

    try{if(Formato.getMotorMarca().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModelo().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVoltsPlaca().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getPoleaMotor().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getPoleaTurbina().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getReqCambioBanda().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModeloBandas().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpMotorL1().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpMotorL2().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpMotorL3().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getNumeroBandas().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getChumacerasLubricadas().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAjustesPoleas().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRotacion().length()>0){m4= m4-1;valorPr++;}}catch(Exception e){}

    int m7 = 12;

    try{if(Formato.getMarca1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModelo1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getNoSerie1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAceiteNivel1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getTempValvulaServ1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getTempValvulaExp1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionBajaLbs1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionBajaCorte1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionAlta1().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL11().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL21().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL31().length()>0){m7= m7-1;valorPr++;}}catch(Exception e){}

    int m9=23;

    try{if(Formato.getMarcaConde().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModeloConde().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getNoSerieConde().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getMarcaMotores().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModeloMotVariable().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getModeloMotConstante().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpsPlaca().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getVoltsPlaca1().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getL12().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getL23().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getL31().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorUnoL1().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorUnoL2().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorUnoL3().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorDosL1().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorDosL2().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorDosL3().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorTres1().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorTres2().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorTres3().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorCuatroL1().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorCuatroL2().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getAMotorCuatroL3().length()>0){m9= m9-1;valorPr++;}}catch(Exception e){}

    int m10 = 15;
    try{if(Formato.getLavadoSerpentinesCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getReaprieteTornilleríaGeneralCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisionFusiblesCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisionContactoresCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getLavadoAspasProteccionesCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getLimpiezaInternaExternaCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getLimpiezaCharolaHumidificacionCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarEstadoLamparaCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarSedimentacionMineraCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarCondicionesBandasCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarAmortiguadoresCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarLubricacionBalerosCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarFlechaBaseMotorCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getRevisarAbrazaderasSoportesCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}
    try{if(Formato.getCambiosFiltrosAireCom().length()>0){m10= m10-1;valorPr++;}}catch(Exception e){}

    //-------Modilos opcionales
    try{if(Formato.getVAC24().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getVAC5().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getObservacionesMicroprocesador().length()>0){valorPr++;}}catch(Exception e){}

    try{if(Formato.getHumificadorTipo().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getCondiciones().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getSensor().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getSelenoideVolts().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getNoLamparas().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getLíneaAguaDrenaje().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLineaL1().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLineaL2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLineaL3().length()>0){valorPr++;}}catch(Exception e){}

    try{if(Formato.getMarca2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getModelo2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getNoSerie2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAceiteNivel2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getTempValvulaServ2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getTempValvulaExp2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionBajaLbs2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionBajaCorte2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getPresionAlta2().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL12().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL22().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Formato.getAmpLíneaL32().length()>0){valorPr++;}}catch(Exception e){}
    try{if(Boolean.parseBoolean(Formato.getMiraIndicadoraSeco2()) || Boolean.parseBoolean(Formato.getMiraIndicadoraHumedo2())){valorPr++;}}catch(Exception e){}


    //----------------------

    //endregion


/*
    try{if(!Formato.getSETTEMP().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getSETHUM().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getSENS().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getSENS2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getHITEMP().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getHIHUM().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLOTEMP().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLOHUM().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getINYECCION().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRETORNO().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTIPOMICROPROCESADOR().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMarcaUniMane().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTipoEvaporadorUniMane().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModeloUniMane().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVoltajeGeneralL1L2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVoltajeGeneralL2L3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVoltajeGeneralL3L1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoSerieUniMane().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoResistencias().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getEstadoFísico().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVerificación().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getConductores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getContactores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getFusibles().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia1L1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia1L2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia2L1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia2L2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia3L1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpResistencia3L2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMotorMarca().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModelo().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVoltsPlaca().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPoleaMotor().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPoleaTurbina().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getReqCambioBanda().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModeloBandas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpMotorL1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpMotorL2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpMotorL3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNumeroBandas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getChumacerasLubricadas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAjustesPoleas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRotacion().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVAC24().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVAC5().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getObservacionesMicroprocesador().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getHumificadorTipo().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getCondiciones().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getSensor().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getSelenoideVolts().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoLamparas().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLíneaAguaDrenaje().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLineaL1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLineaL2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLineaL3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMarca1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModelo1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoSerie1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAceiteNivel1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTempValvulaServ1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTempValvulaExp1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionBajaLbs1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionBajaCorte1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionAlta1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL11().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL21().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL31().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMarca2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModelo2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoSerie2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAceiteNivel2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTempValvulaServ2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getTempValvulaExp2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionBajaLbs2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionBajaCorte2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getPresionAlta2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL12().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL22().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpLíneaL32().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMarcaConde().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModeloConde().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getNoSerieConde().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getMarcaMotores().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModeloMotVariable().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getModeloMotConstante().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAmpsPlaca().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getVoltsPlaca1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getL12().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getL23().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getL31().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorUnoL1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorUnoL2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorUnoL3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorDosL1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorDosL2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorDosL3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorTres1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorTres2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorTres3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorCuatroL1().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorCuatroL2().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getAMotorCuatroL3().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLavadoSerpentinesCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getReaprieteTornilleríaGeneralCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRevisionFusiblesCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRevisionContactoresCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLavadoAspasProteccionesCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLimpiezaInternaExternaCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getLimpiezaCharolaHumidificacionCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRevisarEstadoLamparaCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
    try{if(!Formato.getRevisarSedimentacionMineraCom().isEmpty()){M2_I =M2_I - 1;}}catch (Exception e){};
*/


    if(m1+m2+m3+m4+m7+m9+m10 == 0){im2.setImageResource(R.drawable.lectura2verde);
      completo = completo-1;
    }else if(m1+m2+m3+m4+m7+m9+m10 < 91){im2.setImageResource(R.drawable.lectura2nar);}
    else {
      if(valorPr>0){
        im2.setImageResource(R.drawable.lectura2nar);
      }
      else {
        im2.setImageResource(R.drawable.lectura2gris);
      }}


    //endregion

    //region Modulo Comentarios
    int M_ = 1;
    try{if(!Formato.getComentariosRecomendaciones().isEmpty()){M_ = M_- 1;}}catch (Exception e){}
    if(M_ == 0){ im3.setImageResource(R.drawable.comentariosverde);
      completo = completo-1;}
    else {im3.setImageResource(R.drawable.comentariosgris);}

    //endregion

    //region Modulo Materiales
    int M5_A = 27;
    int M5_B = 15;

    try{if(Formato.getCantidad1().equals(" ") &&
            Formato.getNoParte1().equals(" ") &&
            Formato.getDescripcion1().equals(" ")){
      M5_A = M5_A - 27;
    }
    else{
      try{if(!Formato.getCantidad1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion1().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion2().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion3().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion4().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion5().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion6().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion7().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion8().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getCantidad9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getNoParte9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}
      try{if(!Formato.getDescripcion9().isEmpty()){M5_A = M5_A- 1;}}catch (Exception e){}

    }}catch(Exception e){}
    try{if(Formato.getEquipo1().equals(" ") &&
            Formato.getNoId1().equals(" ") &&
            Formato.getFechaVencimiento1().equals(" ")){
      M5_B = M5_B - 15;
    }
    else{
      try{if(!Formato.getEquipo1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getNoId1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getFechaVencimiento1().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getEquipo2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getNoId2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getFechaVencimiento2().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getEquipo3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getNoId3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getFechaVencimiento3().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getEquipo4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getNoId4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getFechaVencimiento4().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getEquipo5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getNoId5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
      try{if(!Formato.getFechaVencimiento5().isEmpty()){M5_B = M5_B- 1;}}catch (Exception e){}
    }}catch(Exception e){}

    if((M5_A + M5_B) == 0){ im4.setImageResource(R.drawable.materialesverde);
      completo = completo-1;}
    else if((M5_A < 27 && M5_B==15) || (M5_A == 27 && M5_B<15) ){
      im4.setImageResource(R.drawable.materialenar);
    }
    else if(M5_A <27 && M5_B <15){
      im4.setImageResource(R.drawable.materialesverde);
      completo = completo-1;
    }
    else {im4.setImageResource(R.drawable.materialesgris);}

    //endregion

    //region Modulo Fotos
    int M6_ = 12;
    int M_Foto1 = 2;
    int M_Foto2 = 2;
    int M_Foto3 = 2;
    int M_Foto4 = 2;
    int M_Foto5 = 2;
    int M_Foto6 = 2;

    try{if(!Formato.getDespues1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
    try{if(!Formato.getDespues2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
    try{if(!Formato.getDespues3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
    try{if(!Formato.getDespues4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
    try{if(!Formato.getDespues5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
    try{if(!Formato.getDespues6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes1().isEmpty()){M_Foto1 = M_Foto1- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes2().isEmpty()){M_Foto2 = M_Foto2- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes3().isEmpty()){M_Foto3 = M_Foto3- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes4().isEmpty()){M_Foto4 = M_Foto4- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes5().isEmpty()){M_Foto5 = M_Foto5- 1;}}catch (Exception e){}
    try{if(!Formato.getAntes6().isEmpty()){M_Foto6 = M_Foto6- 1;}}catch (Exception e){}

    int constante1 = M_Foto1 + M_Foto2+ M_Foto3 +M_Foto4+M_Foto5+M_Foto6;
    if(constante1 == 12){
      im5.setImageResource(R.drawable.fotogris);
    }else{
      if(constante1== 0){
        im5.setImageResource(R.drawable.fotoverde);
        completo = completo-1;
      }else{
        int terinado = 1;
        if(M_Foto1 > 0 & M_Foto1<2){terinado = terinado +1;}
        if(M_Foto2 > 0 & M_Foto2<2){terinado = terinado +1;}
        if(M_Foto3 > 0 & M_Foto3<2){terinado = terinado +1;}
        if(M_Foto4 > 0 & M_Foto4<2){terinado = terinado +1;}
        if(M_Foto5 > 0 & M_Foto5<2){terinado = terinado +1;}
        if(M_Foto6 > 0 & M_Foto6<2){terinado = terinado +1;}
        if(terinado==1){
          im5.setImageResource(R.drawable.fotoverde);
          completo = completo-1;
        }else {
          im5.setImageResource(R.drawable.fotonar);
        }
      }
    }

    //endregion

    //region Modulo Firmas
    int M7_ = 2;
    int obliga =2;
    int M7_Fr = 0;

    try{if(!Formato.getFirmaClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getNomnbreFirmaClienteFinal().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getFirmaCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getNombreFirmaCliente().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getFirmaVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getNombreFirmaVertiv().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getCLIENTEFINAL_EMPRESA().isEmpty()){M7_Fr++;}}catch (Exception e){}
    try{if(!Formato.getCLIENTEFINAL_TELEFONO().isEmpty()){M7_Fr++;}}catch (Exception e){}

    try{
      if(!Formato.getFirmaClienteFinal().isEmpty() && !Formato.getNomnbreFirmaClienteFinal().isEmpty()){
        try{if(!Formato.getCLIENTEFINAL_CORREO().isEmpty()&&
                !Formato.getCLIENTEFINAL_EMPRESA().isEmpty()&&
                !Formato.getCLIENTEFINAL_TELEFONO().isEmpty()){M7_ = M7_-1;}
        }catch (Exception e){}{
        }
      }
    }catch (Exception e){}

    try {
      if (!Formato.getFirmaCliente().isEmpty()&& !Formato.getFirmaCliente().isEmpty()){ M7_ = M7_-1;   }
    } catch (Exception e) {
    }

    try {
      if (!Formato.getFirmaVertiv().isEmpty()){ obliga = obliga-1;   }
      if (!Formato.getNombreFirmaVertiv().isEmpty()){ obliga = obliga-1;   }
    } catch (Exception e) {
    }

    if(obliga == 0){
      if(M7_== 0 || M7_ == 1 ){im6.setImageResource(R.drawable.firmaverde);
        completo = completo-1;}
      else if(M7_ < 3){im6.setImageResource(R.drawable.firmanar);}
      else {im6.setImageResource(R.drawable.firmagris);}
    }
    else if(M7_ < 2){im6.setImageResource(R.drawable.firmanar);}
    else {
      if(M7_Fr>0){
        im6.setImageResource(R.drawable.firmanar);
      }else{
        if(M7_Fr>0){
          im6.setImageResource(R.drawable.firmanar);
        }else{
          im6.setImageResource(R.drawable.firmagris);
        }
      }
    }


        /*
        if (!Formato.getIMAGENFIRMA1().isEmpty() && !Formato.getFIRMA1().isEmpty()) {
            M7_ = M7_ - 1;
        }

        if((CLienteF == 1 || CLienteF == 3)) {
            try {
                if(CLienteF == 1){ M7_ = M7_ - 1;}
                if(otraF ==0){ M7_ = M7_ - 1;}
                if (!Formato.getIMAGENFIRMA1().isEmpty() && !Formato.getFIRMA1().isEmpty()) {
                    M7_ = M7_ - 1;
                }
            } catch (Exception e) {
            }

            if(M7_== 0 && M7_ ==1){im5.setImageResource(R.drawable.firmaverde);
                completo = completo-1;}
            else if(M7_ < 3){im5.setImageResource(R.drawable.firmanar);}
            else {im5.setImageResource(R.drawable.firmagris);}
        }
        else{
            im5.setImageResource(R.drawable.firmanar);
        }
*/



    //endregion

    //region Modulo Adicionales
    int M8_ = 10;

    //try{if(!Formato..getADICI_NOMBRE1().isEmpty()){M7_ = M7_- 1;}}catch (Exception e){}
    try{if(!Formato.getAD_CORREO1().isEmpty() && !Formato.getAD_NOMBRE1().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
    try{if(!Formato.getAD_CORREO2().isEmpty() && !Formato.getAD_NOMBRE2().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
    try{if(!Formato.getAD_CORREO3().isEmpty() && !Formato.getAD_NOMBRE3().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
    try{if(!Formato.getAD_CORREO4().isEmpty() && !Formato.getAD_NOMBRE4().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}
    try{if(!Formato.getAD_CORREO5().isEmpty() && !Formato.getAD_NOMBRE5().isEmpty()){M8_ = M8_- 1;}}catch (Exception e){}


    if(M8_ < 10){im7.setImageResource(R.drawable.notificaverde);
      completo = completo-1;}
    else {im7.setImageResource(R.drawable.notificagris);}
    //endregion



    if(completo == 0){
      D_B.actualizarEsatusFormato("0",id_formato);
      Terminar.setBackgroundResource(R.drawable.btnnaranja);
      Terminar.setEnabled(true);
    }else{
      D_B.actualizarEsatusFormato("1",id_formato);
    }

  }

  private void overridePendingTransition(int left_in, int left_out) {
  }
  public void alertaMensajes(String titulo, String mensaje){
    Bundle valores = new Bundle();
    valores.putString("titulo",titulo);
    valores.putString("mensaje",mensaje);
    AlertaGenerica alertamnsaje = new AlertaGenerica();
    alertamnsaje.setArguments(valores);
    alertamnsaje.setCancelable(false);
    alertamnsaje.show(getFragmentManager(), "a");
  }

  public void enviarFormato(){
    Formato.setIdUsuario(usuario.getID_USER());
    if(Formato.getProyecto().length()>1){Formato.setSRProyecto(Formato.getProyecto());}
    final String idFormatoService = D_B.generarUID();
    Formato.setIdFormatoThermalManagagement(idFormatoService);
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

    //region enviar formato
    Formato.setPais(usuario.getPAIS());
    compositeDisposable.add(iMyAPI.enviarThermal(Formato).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Consumer<ThermalManagagementS>() {
              @Override
              public void accept(ThermalManagagementS lista) throws Exception {
                //lista.get(0).getItem_Number();
                alerta.dismiss();
                if (lista.getExitoso().equals("1")) {
                  enviarFotos(lista.getFolioPreTrabajo());
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

                  Toast.makeText(getContext(), "Se envio" + lista.getFolioPreTrabajo(), Toast.LENGTH_SHORT).show();

                } else {
                  D_B.newMensaje(lista.getError());
                  Bundle valores = new Bundle();
                  valores.putString("titulo", "Error");
                  valores.putString("mensaje", "Se genero un error: " + lista.getError());

                  AlertaGenerica alerta = new AlertaGenerica();
                  alerta.setArguments(valores);
                  alerta.setCancelable(false);
                  alerta.show(getFragmentManager(), "a");
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
    //endregion
  }

  public void enviarFotos(String idFormatoService){
    //region Enviar_Fotos

    try{if(Formato.getAntes1().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes1(b64.ImgB64(Formato.getAntes1()));
      item.setFormato(Formato.getAntes1());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getAntes2().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes2(b64.ImgB64(Formato.getAntes2()));
      item.setFormato(Formato.getAntes2());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getAntes3().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes3(b64.ImgB64(Formato.getAntes3()));
      item.setFormato(Formato.getAntes3());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getAntes4().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes4(b64.ImgB64(Formato.getAntes4()));
      item.setFormato(Formato.getAntes4());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getAntes5().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes5(b64.ImgB64(Formato.getAntes5()));
      item.setFormato(Formato.getAntes5());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getAntes6().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setAntes6(b64.ImgB64(Formato.getAntes6()));
      item.setFormato(Formato.getAntes6());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues1().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues1(b64.ImgB64(Formato.getDespues1()));
      item.setFormato(Formato.getDespues1());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues2().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues2(b64.ImgB64(Formato.getDespues2()));
      item.setFormato(Formato.getDespues2());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues3().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues3(b64.ImgB64(Formato.getDespues3()));
      item.setFormato(Formato.getDespues3());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues4().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues4(b64.ImgB64(Formato.getDespues4()));
      item.setFormato(Formato.getDespues4());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues5().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues5(b64.ImgB64(Formato.getDespues5()));
      item.setFormato(Formato.getDespues5());
      ((MainActivity) getActivity()).imagenesEnvio(item);
    }}catch (Exception e){}
    try{if(Formato.getDespues6().contains(".jpg")){
      FotosFormato item = new FotosFormato();
      item.setId(idFormatoService);
      item.setDespues6(b64.ImgB64(Formato.getDespues6()));
      item.setFormato(Formato.getDespues6());
      ((MainActivity) getActivity()).imagenesEnvio(item);

    }}catch (Exception e){}
    //endregion
  }

}
