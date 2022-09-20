package com.maju.desarrollo.testfcs.Formatos.DCPower;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.maju.desarrollo.testfcs.Formatos.CancelasDialogFragmentMismoActivity;
import com.maju.desarrollo.testfcs.MainActivity;
import com.maju.desarrollo.testfcs.R;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.OperacionesDB;


public class PowerParametrosFragment extends Fragment {

    OperacionesDB D_B;
    String id_formato;
    DCPower dcPower;
    DCPower2 dcPower2;
    boolean ActivoM1 = false,ActivoM2 = false,ActivoM3 = false,ActivoM4 = false,ActivoM5 = false,
            ActivoM6 = false,ActivoM7 = false,ActivoM8 = false,ActivoM9 = false,ActivoM10 = false,
            ActivoM11 = false;

    public PowerParametrosFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_power_parametros, container, false);
        ((MainActivity) getActivity()).ocultarCabecera();
        D_B = OperacionesDB.obtenerInstancia(getContext());
        id_formato= getArguments().getString("key_idFormato");
        dcPower = D_B.obtenerDcPower_id(id_formato);
        dcPower2 = D_B.obtenerDcPower2_id(id_formato);

        Button Btn_M1 = (Button)v.findViewById(R.id.Modulo1);
        Button Btn_M2 = (Button)v.findViewById(R.id.Modulo2);
        Button Btn_M3 = (Button)v.findViewById(R.id.Modulo3);
        Button Btn_M4 = (Button)v.findViewById(R.id.Modulo4);
        Button Btn_M5 = (Button)v.findViewById(R.id.Modulo5);
        Button Btn_M6 = (Button)v.findViewById(R.id.Modulo6);
        Button Btn_M7 = (Button)v.findViewById(R.id.Modulo7);
        Button Btn_M8 = (Button)v.findViewById(R.id.Modulo8);
        Button Btn_M9 = (Button)v.findViewById(R.id.Modulo9);
        Button Btn_M10 = (Button)v.findViewById(R.id.Modulo10);
        Button Btn_M11 = (Button)v.findViewById(R.id.Modulo11);

        final LinearLayout content_M1 = (LinearLayout)v.findViewById(R.id.contenedorM1);
        final LinearLayout content_M2 = (LinearLayout)v.findViewById(R.id.contenedorM2);
        final LinearLayout content_M3 = (LinearLayout)v.findViewById(R.id.contenedorM3);
        final LinearLayout content_M4 = (LinearLayout)v.findViewById(R.id.contenedorM4);
        final LinearLayout content_M5 = (LinearLayout)v.findViewById(R.id.contenedorM5);
        final LinearLayout content_M6 = (LinearLayout)v.findViewById(R.id.contenedorM6);
        final LinearLayout content_M7 = (LinearLayout)v.findViewById(R.id.contenedorM7);
        final LinearLayout content_M8 = (LinearLayout)v.findViewById(R.id.contenedorM8);
        final LinearLayout content_M9 = (LinearLayout)v.findViewById(R.id.contenedorM9);
        final LinearLayout content_M10 = (LinearLayout)v.findViewById(R.id.contenedorM10);
        final LinearLayout content_M11 = (LinearLayout)v.findViewById(R.id.contenedorM11);

        final LinearLayout clickcampo1 = (LinearLayout)v.findViewById(R.id.clickcampo1);
        final LinearLayout clickcampo2 = (LinearLayout)v.findViewById(R.id.clickcampo2);
        final LinearLayout pestana1M1 = (LinearLayout)v.findViewById(R.id.pestana1M1);
        final LinearLayout pestana2M1 = (LinearLayout)v.findViewById(R.id.pestana2M1);
        final LinearLayout clickcampo1M5 = (LinearLayout)v.findViewById(R.id.clickcampo1M5);
        final LinearLayout clickcampo2M5 = (LinearLayout)v.findViewById(R.id.clickcampo2M5);
        final LinearLayout pestana1M5 = (LinearLayout)v.findViewById(R.id.pestana1M5);
        final LinearLayout pestana2M5 = (LinearLayout)v.findViewById(R.id.pestana2M5);
        final LinearLayout clickcampo1M10 = (LinearLayout)v.findViewById(R.id.clickcampo1M10);
        final LinearLayout clickcampo2M10 = (LinearLayout)v.findViewById(R.id.clickcampo2M10);
        final LinearLayout clickcampo3M10 = (LinearLayout)v.findViewById(R.id.clickcampo3M10);
        final LinearLayout pestana1M10 = (LinearLayout)v.findViewById(R.id.pestana1M10);
        final LinearLayout pestana2M10 = (LinearLayout)v.findViewById(R.id.pestana2M10);
        final LinearLayout pestana3M10 = (LinearLayout)v.findViewById(R.id.pestana3M10);
        final LinearLayout clickcampo1M11 = (LinearLayout)v.findViewById(R.id.clickcampo1M11);
        final LinearLayout clickcampo2M11 = (LinearLayout)v.findViewById(R.id.clickcampo2M11);
        final LinearLayout pestana1M11 = (LinearLayout)v.findViewById(R.id.pestana1M11);
        final LinearLayout pestana2M11 = (LinearLayout)v.findViewById(R.id.pestana2M11);


        //region realcion interfaz
        final EditText PGSDCTipo=(EditText)v.findViewById(R.id.PGSDCTipo);
        final EditText  PGSDCNoEspecificacion=(EditText)v.findViewById(R.id.PGSDCNoEspecificacion);
        final EditText  PGSDCCapacidad=(EditText)v.findViewById(R.id.PGSDCCapacidad);
        final EditText  PGSDCConfiguracion=(EditText)v.findViewById(R.id.PGSDCConfiguracion);
        final EditText  PGSDCCargaActual=(EditText)v.findViewById(R.id.PGSDCCargaActual);
        final EditText  PGIRModelo=(EditText)v.findViewById(R.id.PGIRModelo);
        final EditText  PGIRNoEspecificacion=(EditText)v.findViewById(R.id.PGIRNoEspecificacion);
        final EditText  PGIRCapacidad=(EditText)v.findViewById(R.id.PGIRCapacidad);
        final EditText  NoSerieR1=(EditText)v.findViewById(R.id.NoSerieR1);
        final EditText  NoSerieR2=(EditText)v.findViewById(R.id.NoSerieR2);
        final EditText  NoSerieR3=(EditText)v.findViewById(R.id.NoSerieR3);
        final EditText  NoSerieR4=(EditText)v.findViewById(R.id.NoSerieR4);
        final EditText  NoSerieR5=(EditText)v.findViewById(R.id.NoSerieR5);
        final EditText  NoSerieR6=(EditText)v.findViewById(R.id.NoSerieR6);
        final EditText  NoSerieR7=(EditText)v.findViewById(R.id.NoSerieR7);
        final EditText  NoSerieR8=(EditText)v.findViewById(R.id.NoSerieR8);
        final EditText  NoSerieR9=(EditText)v.findViewById(R.id.NoSerieR9);
        final EditText  NoSerieR10=(EditText)v.findViewById(R.id.NoSerieR10);
        final EditText  NoSerieR11=(EditText)v.findViewById(R.id.NoSerieR11);
        final EditText  NoSerieR12=(EditText)v.findViewById(R.id.NoSerieR12);
        final EditText  NoSerieR13=(EditText)v.findViewById(R.id.NoSerieR13);
        final EditText  NoSerieR14=(EditText)v.findViewById(R.id.NoSerieR14);
        final EditText  NoSerieR15=(EditText)v.findViewById(R.id.NoSerieR15);
        final EditText  NoSerieR16=(EditText)v.findViewById(R.id.NoSerieR16);
        final EditText  NoSerieR17=(EditText)v.findViewById(R.id.NoSerieR17);
        final EditText  NoSerieR18=(EditText)v.findViewById(R.id.NoSerieR18);
        final EditText  NoSerieR19=(EditText)v.findViewById(R.id.NoSerieR19);
        final EditText  NoSerieR20=(EditText)v.findViewById(R.id.NoSerieR20);
        final EditText  NoSerieR21=(EditText)v.findViewById(R.id.NoSerieR21);
        final EditText  NoSerieR22=(EditText)v.findViewById(R.id.NoSerieR22);
        final EditText  NoSerieR23=(EditText)v.findViewById(R.id.NoSerieR23);
        final EditText  NoSerieR24=(EditText)v.findViewById(R.id.NoSerieR24);
        final EditText  NoSerieR25=(EditText)v.findViewById(R.id.NoSerieR25);
        final EditText  NoSerieR26=(EditText)v.findViewById(R.id.NoSerieR26);
        final EditText  NoSerieR27=(EditText)v.findViewById(R.id.NoSerieR27);
        final EditText  NoSerieR28=(EditText)v.findViewById(R.id.NoSerieR28);
        final EditText  NoSerieR29=(EditText)v.findViewById(R.id.NoSerieR29);
        final EditText  NoSerieR30=(EditText)v.findViewById(R.id.NoSerieR30);
        final EditText  NoSerieR31=(EditText)v.findViewById(R.id.NoSerieR31);
        final EditText  NoSerieR32=(EditText)v.findViewById(R.id.NoSerieR32);

        final CheckBox PGIGRPDC=(CheckBox)v.findViewById(R.id.PGIGRPDC);
        final EditText  EspecificacionesGabinete=(EditText)v.findViewById(R.id.EspecificacionesGabinete);
        final EditText  NoSerieGab1=(EditText)v.findViewById(R.id.NoSerieGab1);
        final EditText  NoSerieGab2=(EditText)v.findViewById(R.id.NoSerieGab2);
        final EditText  NoSerieGab3=(EditText)v.findViewById(R.id.NoSerieGab3);
        final EditText  NoSerieGab4=(EditText)v.findViewById(R.id.NoSerieGab4);
        final EditText  NoSerieGab5=(EditText)v.findViewById(R.id.NoSerieGab5);
        final EditText  NoSerieGab6=(EditText)v.findViewById(R.id.NoSerieGab6);

        final CheckBox  VISPTVSSTAF=(CheckBox)v.findViewById(R.id.VISPTVSSTAF);
        final EditText  VISPTVSSTAFModelo=(EditText)v.findViewById(R.id.VISPTVSSTAFModelo);
        final EditText  VISPTVSSTAFMarca=(EditText)v.findViewById(R.id.VISPTVSSTAFMarca);
        final EditText  InstalacionMecanicaGabineteTVSS=(EditText)v.findViewById(R.id.InstalacionMecanicaGabineteTVSS);
        final EditText  CalibreCableadoConexionTVSS=(EditText)v.findViewById(R.id.CalibreCableadoConexionTVSS);
        final EditText  CapacidadbreakersConexionTableroAc=(EditText)v.findViewById(R.id.CapacidadbreakersConexionTableroAc);
        final EditText  CalibreCableTierrasTVSS=(EditText)v.findViewById(R.id.CalibreCableTierrasTVSS);
        final EditText  PAPFDCVoltajeAlimentacion=(EditText)v.findViewById(R.id.PAPFDCVoltajeAlimentacion);
        final EditText  PAPFDCVoltajeFaseAB=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseAB);
        final EditText  PAPFDCVoltajeFaseBC=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseBC);
        final EditText  PAPFDCVoltajeFaseCA=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseCA);
        final EditText  PAPFDCVoltajeFaseAN=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseAN);
        final EditText  PAPFDCVoltajeFaseBN=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseBN);
        final EditText  PAPFDCVoltajeFaseCN=(EditText)v.findViewById(R.id.PAPFDCVoltajeFaseCN);
        final EditText  PAPFDCVoltajeNeutroGND=(EditText)v.findViewById(R.id.PAPFDCVoltajeNeutroGND);
        final EditText  POPFDCVoltajeFlotacion=(EditText)v.findViewById(R.id.POPFDCVoltajeFlotacion);
        final EditText  POPFDCVoltajeIgualacion=(EditText)v.findViewById(R.id.POPFDCVoltajeIgualacion);
        final EditText  POPFDCAlarmaBajoVoltaje1=(EditText)v.findViewById(R.id.POPFDCAlarmaBajoVoltaje1);
        final EditText  POPFDCAlarmaBajoVoltaje2=(EditText)v.findViewById(R.id.POPFDCAlarmaBajoVoltaje2);
        final EditText  POPFDCDesconexionBajoVoltaje=(EditText)v.findViewById(R.id.POPFDCDesconexionBajoVoltaje);
        final EditText  POPFDCAlarmaAltoVoltaje1=(EditText)v.findViewById(R.id.POPFDCAlarmaAltoVoltaje1);
        final EditText  POPFDCAlarmaAltoVoltaje2=(EditText)v.findViewById(R.id.POPFDCAlarmaAltoVoltaje2);
        final EditText  POPFDCAlarmaFusibleCargaAbierto=(EditText)v.findViewById(R.id.POPFDCAlarmaFusibleCargaAbierto);
        final EditText  POPFDCAlarmaFusibleBateriasAbierto=(EditText)v.findViewById(R.id.POPFDCAlarmaFusibleBateriasAbierto);
        final EditText  POPFDCAlarmaFallaRectificador=(EditText)v.findViewById(R.id.POPFDCAlarmaFallaRectificador);
        final EditText  LimpiezaInternaExternaComen=(EditText)v.findViewById(R.id.LimpiezaInternaExterna);
        final EditText  LimpiezaIndividualComen=(EditText)v.findViewById(R.id.LimpiezaIndividual);
        final EditText  VerificacionTorquesComen=(EditText)v.findViewById(R.id.VerificacionTorques);

        final CheckBox  STPFDC=(CheckBox)v.findViewById(R.id.STPFDC);
        final EditText  VerificacionConexionesTerminalesComen=(EditText)v.findViewById(R.id.VerificacionConexionesTerminalesComen);
        final EditText  DebeSerZaptaComen=(EditText)v.findViewById(R.id.DebeSerZaptaComen);
        final EditText  VerificacionEtiquetadoTierrasComen=(EditText)v.findViewById(R.id.VerificacionEtiquetadoTierrasComen);
        final EditText  VerificacionCableadoExistenteTierrasComen=(EditText)v.findViewById(R.id.VerificacionCableadoExistenteTierrasComen);
        final EditText  VerificacionCableadoTierradeGabineteLVDBcomen=(EditText)v.findViewById(R.id.VerificacionCableadoTierradeGabineteLVDBcomen);


        final EditText  MidaResistenciaComen=(EditText)v.findViewById(R.id.MidaResistenciaComen);
        final EditText  CablesGabineteComen=(EditText)v.findViewById(R.id.CablesGabineteComen);
        final EditText  CableadoEstantesComen=(EditText)v.findViewById(R.id.CableadoEstantesComen);
        //final EditText  CableBusPositivoComen=(EditText)v.findViewById(R.id.CableBusPositivoComen);

        final CheckBox  ITPF=(CheckBox) v.findViewById(R.id.ITPF);
        final EditText  RetireTapasEquiposComen=(EditText)v.findViewById(R.id.RetireTapasEquiposComen);
        final EditText  VisualmenteInspeccionComen=(EditText)v.findViewById(R.id.VisualmenteInspeccionComen);
        final EditText  AyudaCamaraTermograficaComen=(EditText)v.findViewById(R.id.AyudaCamaraTermograficaComen);
        final EditText  EtapaRectificadoresComen=(EditText)v.findViewById(R.id.EtapaRectificadoresComen);
        final EditText  PanelGabineteACComen=(EditText)v.findViewById(R.id.PanelGabineteACComen);
        final EditText  PanelGabineteDCComen=(EditText)v.findViewById(R.id.PanelGabineteDCComen);
        final EditText  CableadoAlimentacionComen=(EditText)v.findViewById(R.id.CableadoAlimentacionComen);

        final CheckBox  PGSDDC = (CheckBox)v.findViewById(R.id.PGSDDC);
        final EditText  PGSDDCModelo=(EditText)v.findViewById(R.id.PGSDDCModelo);
        final EditText  PGSDDCMarca=(EditText)v.findViewById(R.id.PGSDDCMarca);
        final EditText  PGSDDCNoEspecifico=(EditText)v.findViewById(R.id.PGSDDCNoEspecifico);
        final EditText  PGSDDCCapacidad=(EditText)v.findViewById(R.id.PGSDDCCapacidad);
        final EditText  PGSDDCConfiguracion=(EditText)v.findViewById(R.id.PGSDDCConfiguracion);

        final CheckBox  PGIDR = (CheckBox)v.findViewById(R.id.PGIDR);
        final EditText  LimpiezaGabinete=(EditText)v.findViewById(R.id.LimpiezaGabinete);
        final EditText  VerificacionEstado=(EditText)v.findViewById(R.id.VerificacionEstado);
        final EditText  VerificarGabinete=(EditText)v.findViewById(R.id.VerificarGabinete);
        final EditText  VerificarTorque=(EditText)v.findViewById(R.id.VerificarTorque);
        final EditText  VerificarTorqueCargasGabinete=(EditText)v.findViewById(R.id.VerificarTorqueCargasGabinete);
        final EditText  VerificarModulos=(EditText)v.findViewById(R.id.VerificarModulos);
        final EditText  ValoresReales=(EditText)v.findViewById(R.id.ValoresReales);
        final EditText  Breakers1=(EditText)v.findViewById(R.id.Breakers1);
        final EditText  Breakers2=(EditText)v.findViewById(R.id.Breakers2);
        final EditText  Breakers3=(EditText)v.findViewById(R.id.Breakers3);
        final EditText  Breakers4=(EditText)v.findViewById(R.id.Breakers4);
        final EditText  Breakers5=(EditText)v.findViewById(R.id.Breakers5);
        final EditText  Breakers6=(EditText)v.findViewById(R.id.Breakers6);
        final EditText  Breakers7=(EditText)v.findViewById(R.id.Breakers7);
        final EditText  Breakers8=(EditText)v.findViewById(R.id.Breakers8);

        final CheckBox  PGSI=(CheckBox)v.findViewById(R.id.PGSI);
        final EditText  PGSIModelo=(EditText)v.findViewById(R.id.PGSIModelo);
        final EditText  PGSIMarca=(EditText)v.findViewById(R.id.PGSIMarca);
        final EditText  PGSINoEspecificacion=(EditText)v.findViewById(R.id.PGSINoEspecificacion);
        final EditText  PGSICapacidad=(EditText)v.findViewById(R.id.PGSICapacidad);
        final EditText  PGSIConfiguracion=(EditText)v.findViewById(R.id.PGSIConfiguracion);
        final EditText  NoSerieLineaAINV1=(EditText)v.findViewById(R.id.NoSerieLineaAINV1);
        final EditText  NoSerieLineaAINV2=(EditText)v.findViewById(R.id.NoSerieLineaAINV2);
        final EditText  NoSerieLineaAINV3=(EditText)v.findViewById(R.id.NoSerieLineaAINV3);
        final EditText  NoSerieLineaAINV4=(EditText)v.findViewById(R.id.NoSerieLineaAINV4);
        final EditText  NoSerieLineaAINV5=(EditText)v.findViewById(R.id.NoSerieLineaAINV5);
        final EditText  NoSerieLineaAINV6=(EditText)v.findViewById(R.id.NoSerieLineaAINV6);
        final EditText  NoSerieLineaAINV7=(EditText)v.findViewById(R.id.NoSerieLineaAINV7);
        final EditText  NoSerieLineaAINV8=(EditText)v.findViewById(R.id.NoSerieLineaAINV8);
        final EditText  NoSerieLineaAINV9=(EditText)v.findViewById(R.id.NoSerieLineaAINV9);
        final EditText  NoSerieLineaAINV10=(EditText)v.findViewById(R.id.NoSerieLineaAINV10);
        final EditText  NoSerieLineaBINV1=(EditText)v.findViewById(R.id.NoSerieLineaBINV1);
        final EditText  NoSerieLineaBINV2=(EditText)v.findViewById(R.id.NoSerieLineaBINV2);
        final EditText  NoSerieLineaBINV3=(EditText)v.findViewById(R.id.NoSerieLineaBINV3);
        final EditText  NoSerieLineaBINV4=(EditText)v.findViewById(R.id.NoSerieLineaBINV4);
        final EditText  NoSerieLineaBINV5=(EditText)v.findViewById(R.id.NoSerieLineaBINV5);
        final EditText  NoSerieLineaBINV6=(EditText)v.findViewById(R.id.NoSerieLineaBINV6);
        final EditText  NoSerieLineaBINV7=(EditText)v.findViewById(R.id.NoSerieLineaBINV7);
        final EditText  NoSerieLineaBINV8=(EditText)v.findViewById(R.id.NoSerieLineaBINV8);
        final EditText  NoSerieLineaBINV9=(EditText)v.findViewById(R.id.NoSerieLineaBINV9);
        final EditText  NoSerieLineaBINV10=(EditText)v.findViewById(R.id.NoSerieLineaBINV10);
        final EditText  NoSerieLineaCINV1=(EditText)v.findViewById(R.id.NoSerieLineaCINV1);
        final EditText  NoSerieLineaCINV2=(EditText)v.findViewById(R.id.NoSerieLineaCINV2);
        final EditText  NoSerieLineaCINV3=(EditText)v.findViewById(R.id.NoSerieLineaCINV3);
        final EditText  NoSerieLineaCINV4=(EditText)v.findViewById(R.id.NoSerieLineaCINV4);
        final EditText  NoSerieLineaCINV5=(EditText)v.findViewById(R.id.NoSerieLineaCINV5);
        final EditText  NoSerieLineaCINV6=(EditText)v.findViewById(R.id.NoSerieLineaCINV6);
        final EditText  NoSerieLineaCINV7=(EditText)v.findViewById(R.id.NoSerieLineaCINV7);
        final EditText  NoSerieLineaCINV8=(EditText)v.findViewById(R.id.NoSerieLineaCINV8);
        final EditText  NoSerieLineaCINV9=(EditText)v.findViewById(R.id.NoSerieLineaCINV9);
        final EditText  NoSerieLineaCINV10=(EditText)v.findViewById(R.id.NoSerieLineaCINV10);

        final EditText  VerificarManeraVisualComen=(EditText)v.findViewById(R.id.VerificarManeraVisualComen);
        final EditText  VerificarReportarExistenciaComen=(EditText)v.findViewById(R.id.VerificarReportarExistenciaComen);
        final EditText  VerificacionBuenEstadoComen=(EditText)v.findViewById(R.id.VerificacionBuenEstadoComen);
        final EditText  VerificarRegistrarCargaComen=(EditText)v.findViewById(R.id.VerificarRegistrarCargaComen);
        final EditText  LimpiezaGabineteInversor=(EditText)v.findViewById(R.id.LimpiezaGabineteInversor);
        //EditText  ExtraerModulo=(EditText)v.findViewById(R.id.ExtraerModulo);
        final EditText  ExtraerModuloCom=(EditText)v.findViewById(R.id.ExtraerModuloCom);
        //EditText  ColocarModuloInversorPosicionOriginal=(EditText)v.findViewById(R.id.ColocarModuloInversorPosicionOriginal);
        final EditText  ColocarModuloInversorPosicionOriginalCom=(EditText)v.findViewById(R.id.ColocarModuloInversorPosicionOriginalCom);
        //EditText  VerificarGabineteConectadoSistemaTierras=(EditText)v.findViewById(R.id.VerificarGabineteConectadoSistemaTierras);
        //EditText  VerificarTorqueCableadoEntradaSalidaSistemaInversor=(EditText)v.findViewById(R.id.VerificarTorqueCableadoEntradaSalidaSistemaInversor);
        //EditText  ExtrerComputadoraInventarioSistema=(EditText)v.findViewById(R.id.ExtrerComputadoraInventarioSistema);
        //EditText  VerificarModulosSupervisionSistema=(EditText)v.findViewById(R.id.VerificarModulosSupervisionSistema);
        final EditText  VerificarGabineteConectadoSistemaTierrasCom=(EditText)v.findViewById(R.id.VerificarGabineteConectadoSistemaTierrasCom);
        final EditText  VerificarTorqueCableadoEntradaSalidaSistemaInversorcom=(EditText)v.findViewById(R.id.VerificarTorqueCableadoEntradaSalidaSistemaInversorcom);
        final EditText  ExtrerComputadoraInventarioSistemaCom=(EditText)v.findViewById(R.id.ExtrerComputadoraInventarioSistemaCom);
        final EditText  VerificarModulosSupervisionSistemaCom=(EditText)v.findViewById(R.id.VerificarModulosSupervisionSistemaCom);
        final EditText  VoltajeFaseABVac=(EditText)v.findViewById(R.id.VoltajeFaseABVac);
        final EditText  VoltajeFaseBCVac=(EditText)v.findViewById(R.id.VoltajeFaseBCVac);
        final EditText  VoltajeFaseCAVac=(EditText)v.findViewById(R.id.VoltajeFaseCAVac);
        final EditText  VoltajeFaseANVac=(EditText)v.findViewById(R.id.VoltajeFaseANVac);
        final EditText  VoltajeFaseBNVac=(EditText)v.findViewById(R.id.VoltajeFaseBNVac);
        final EditText  VoltajeFaseCNVac=(EditText)v.findViewById(R.id.VoltajeFaseCNVac);
        final EditText  VoltajeNeutroVac=(EditText)v.findViewById(R.id.VoltajeNeutroVac);
        final EditText  VoltajeAlimentacionDCVAC=(EditText)v.findViewById(R.id.VoltajeAlimentacionDCVAC);
        final EditText  VoltajeFaseABSVac=(EditText)v.findViewById(R.id.VoltajeFaseABSVac);
        final EditText  VoltajeFaseBCSVac=(EditText)v.findViewById(R.id.VoltajeFaseBCSVac);
        final EditText  VoltajeFaseCASVac=(EditText)v.findViewById(R.id.VoltajeFaseCASVac);
        final EditText  VoltajeFaseANSVac=(EditText)v.findViewById(R.id.VoltajeFaseANSVac);
        final EditText  VoltajeFaseBNSVac=(EditText)v.findViewById(R.id.VoltajeFaseBNSVac);
        final EditText  VoltajeFaseCNSVac=(EditText)v.findViewById(R.id.VoltajeFaseCNSVac);
        final EditText  VoltajeNeutroSVac=(EditText)v.findViewById(R.id.VoltajeNeutroSVac);
        final EditText  VoltajeFaseABACA=(EditText)v.findViewById(R.id.VoltajeFaseABACA);
        final EditText  VoltajeFaseBCACA=(EditText)v.findViewById(R.id.VoltajeFaseBCACA);
        final EditText  VoltajeFaseCAACA=(EditText)v.findViewById(R.id.VoltajeFaseCAACA);
        final EditText  VoltajeFaseANACA=(EditText)v.findViewById(R.id.VoltajeFaseANACA);
        final EditText  VoltajeFaseBNACA=(EditText)v.findViewById(R.id.VoltajeFaseBNACA);
        final EditText  VoltajeFaseCNACA=(EditText)v.findViewById(R.id.VoltajeFaseCNACA);
        final EditText  VoltajeNeutroACA=(EditText)v.findViewById(R.id.VoltajeNeutroACA);
        final EditText  VoltajeAlimentacionDCACA=(EditText)v.findViewById(R.id.VoltajeAlimentacionDCACA);
        final EditText  VoltajeFaseABSACA=(EditText)v.findViewById(R.id.VoltajeFaseABSACA);
        final EditText  VoltajeFaseBCSACA=(EditText)v.findViewById(R.id.VoltajeFaseBCSACA);
        final EditText  VoltajeFaseCASACA=(EditText)v.findViewById(R.id.VoltajeFaseCASACA);
        final EditText  VoltajeFaseANSACA=(EditText)v.findViewById(R.id.VoltajeFaseANSACA);
        final EditText  VoltajeFaseBNSACA=(EditText)v.findViewById(R.id.VoltajeFaseBNSACA);
        final EditText  VoltajeFaseCNSACA=(EditText)v.findViewById(R.id.VoltajeFaseCNSACA);
        final EditText  VoltajeNeutroSACA=(EditText)v.findViewById(R.id.VoltajeNeutroSACA);




        //endregion

        //region Mostrar datos guardados
        try{PGSDCTipo.setText(dcPower.getPARAM_GRALS_TIPO().toString());}catch (Exception e){}
        try{PGSDCNoEspecificacion.setText(dcPower.getPARAM_GRALS_N_ESPECIFICAION().toString());}catch (Exception e){}
        try{PGSDCCapacidad.setText(dcPower.getPARAM_GRALS_CAPACIDAD().toString());}catch (Exception e){}
        try{PGSDCConfiguracion.setText(dcPower.getPARAM_GRALS_CONFIGURACION().toString());}catch (Exception e){}
        try{PGSDCCargaActual.setText(dcPower.getPARAM_GRALS_CARGA_ACT().toString());}catch (Exception e){}
        try{PGIRModelo.setText(dcPower.getPARAM_INV_RECTIFICADORES_MODELO().toString());}catch (Exception e){}
        try{PGIRNoEspecificacion.setText(dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION().toString());}catch (Exception e){}
        try{PGIRCapacidad.setText(dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD().toString());}catch (Exception e){}
        try{NoSerieR1.setText(dcPower.getPARAM_INV_RECTIFICADORES_R1().toString());}catch (Exception e){}
        try{NoSerieR2.setText(dcPower.getPARAM_INV_RECTIFICADORES_R2().toString());}catch (Exception e){}
        try{NoSerieR3.setText(dcPower.getPARAM_INV_RECTIFICADORES_R3().toString());}catch (Exception e){}
        try{NoSerieR4.setText(dcPower.getPARAM_INV_RECTIFICADORES_R4().toString());}catch (Exception e){}
        try{NoSerieR5.setText(dcPower.getPARAM_INV_RECTIFICADORES_R5().toString());}catch (Exception e){}
        try{NoSerieR6.setText(dcPower.getPARAM_INV_RECTIFICADORES_R6().toString());}catch (Exception e){}
        try{NoSerieR7.setText(dcPower.getPARAM_INV_RECTIFICADORES_R7().toString());}catch (Exception e){}
        try{NoSerieR8.setText(dcPower.getPARAM_INV_RECTIFICADORES_R8().toString());}catch (Exception e){}
        try{NoSerieR9.setText(dcPower.getPARAM_INV_RECTIFICADORES_R9().toString());}catch (Exception e){}
        try{NoSerieR10.setText(dcPower.getPARAM_INV_RECTIFICADORES_R10().toString());}catch (Exception e){}
        try{NoSerieR11.setText(dcPower.getPARAM_INV_RECTIFICADORES_R11().toString());}catch (Exception e){}
        try{NoSerieR12.setText(dcPower.getPARAM_INV_RECTIFICADORES_R12().toString());}catch (Exception e){}
        try{NoSerieR13.setText(dcPower.getPARAM_INV_RECTIFICADORES_R13().toString());}catch (Exception e){}
        try{NoSerieR14.setText(dcPower.getPARAM_INV_RECTIFICADORES_R14().toString());}catch (Exception e){}
        try{NoSerieR15.setText(dcPower.getPARAM_INV_RECTIFICADORES_R15().toString());}catch (Exception e){}
        try{NoSerieR16.setText(dcPower.getPARAM_INV_RECTIFICADORES_R16().toString());}catch (Exception e){}
        try{NoSerieR17.setText(dcPower.getPARAM_INV_RECTIFICADORES_R17().toString());}catch (Exception e){}
        try{NoSerieR18.setText(dcPower.getPARAM_INV_RECTIFICADORES_R18().toString());}catch (Exception e){}
        try{NoSerieR19.setText(dcPower.getPARAM_INV_RECTIFICADORES_R19().toString());}catch (Exception e){}
        try{NoSerieR20.setText(dcPower.getPARAM_INV_RECTIFICADORES_R20().toString());}catch (Exception e){}
        try{NoSerieR21.setText(dcPower.getPARAM_INV_RECTIFICADORES_R21().toString());}catch (Exception e){}
        try{NoSerieR22.setText(dcPower.getPARAM_INV_RECTIFICADORES_R22().toString());}catch (Exception e){}
        try{NoSerieR23.setText(dcPower.getPARAM_INV_RECTIFICADORES_R23().toString());}catch (Exception e){}
        try{NoSerieR24.setText(dcPower.getPARAM_INV_RECTIFICADORES_R24().toString());}catch (Exception e){}
        try{NoSerieR25.setText(dcPower.getPARAM_INV_RECTIFICADORES_R25().toString());}catch (Exception e){}
        try{NoSerieR26.setText(dcPower.getPARAM_INV_RECTIFICADORES_R26().toString());}catch (Exception e){}
        try{NoSerieR27.setText(dcPower.getPARAM_INV_RECTIFICADORES_R27().toString());}catch (Exception e){}
        try{NoSerieR28.setText(dcPower.getPARAM_INV_RECTIFICADORES_R28().toString());}catch (Exception e){}
        try{NoSerieR29.setText(dcPower.getPARAM_INV_RECTIFICADORES_R29().toString());}catch (Exception e){}
        try{NoSerieR30.setText(dcPower.getPARAM_INV_RECTIFICADORES_R30().toString());}catch (Exception e){}
        try{NoSerieR31.setText(dcPower.getPARAM_INV_RECTIFICADORES_R31().toString());}catch (Exception e){}
        try{NoSerieR32.setText(dcPower.getPARAM_INV_RECTIFICADORES_R32().toString());}catch (Exception e){}

        //PGIGRPDC.setText(dcPower.getPARAM_INV_GABINETES_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_INV_GABINETES_NA().equals("true")){PGIGRPDC.setChecked(true);}}catch (Exception e){}
        try{EspecificacionesGabinete.setText(dcPower.getPARAM_INV_GABINETES_ESPECIFIC().toString());}catch (Exception e){}
        try{NoSerieGab1.setText(dcPower.getPARAM_INV_GABINETES_GAB1().toString());}catch (Exception e){}
        try{NoSerieGab2.setText(dcPower.getPARAM_INV_GABINETES_GAB2().toString());}catch (Exception e){}
        try{NoSerieGab3.setText(dcPower.getPARAM_INV_GABINETES_GAB3().toString());}catch (Exception e){}
        try{NoSerieGab4.setText(dcPower.getPARAM_INV_GABINETES_GAB4().toString());}catch (Exception e){}
        try{NoSerieGab5.setText(dcPower.getPARAM_INV_GABINETES_GAB5().toString());}catch (Exception e){}
        try{NoSerieGab6.setText(dcPower.getPARAM_INV_GABINETES_GAB6().toString());}catch (Exception e){}

        //VISPTVSSTAF.setText(dcPower.getPARAM_SUPRESOR_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_SUPRESOR_NA().equals("true")){VISPTVSSTAF.setChecked(true);}}catch (Exception e){}
        try{VISPTVSSTAFModelo.setText(dcPower.getPARAM_SUPRESOR_MODELO().toString());}catch (Exception e){}
        try{VISPTVSSTAFMarca.setText(dcPower.getPARAM_SUPRESOR_MARCA().toString());}catch (Exception e){}
        try{InstalacionMecanicaGabineteTVSS.setText(dcPower.getPARAM_SUPRESOR_INSTALAC().toString());}catch (Exception e){}
        try{CalibreCableadoConexionTVSS.setText(dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION().toString());}catch (Exception e){}
        try{CapacidadbreakersConexionTableroAc.setText(dcPower.getPARAM_SUPRESOR_CAPACIDAD().toString());}catch (Exception e){}
        try{CalibreCableTierrasTVSS.setText(dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS().toString());}catch (Exception e){}
        try{PAPFDCVoltajeAlimentacion.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseAB.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseBC.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseCA.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseAN.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseBN.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6().toString());}catch (Exception e){}
        try{PAPFDCVoltajeFaseCN.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7().toString());}catch (Exception e){}
        try{PAPFDCVoltajeNeutroGND.setText(dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8().toString());}catch (Exception e){}
        try{POPFDCVoltajeFlotacion.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M1().toString());}catch (Exception e){}
        try{POPFDCVoltajeIgualacion.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M2().toString());}catch (Exception e){}
        try{POPFDCAlarmaBajoVoltaje1.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M3().toString());}catch (Exception e){}
        try{POPFDCAlarmaBajoVoltaje2.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M4().toString());}catch (Exception e){}
        try{POPFDCDesconexionBajoVoltaje.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M5().toString());}catch (Exception e){}
        try{POPFDCAlarmaAltoVoltaje1.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M6().toString());}catch (Exception e){}
        try{POPFDCAlarmaAltoVoltaje2.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M7().toString());}catch (Exception e){}
        try{POPFDCAlarmaFusibleCargaAbierto.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M8().toString());}catch (Exception e){}
        try{POPFDCAlarmaFusibleBateriasAbierto.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M9().toString());}catch (Exception e){}
        try{POPFDCAlarmaFallaRectificador.setText(dcPower.getPARAM_AyO_PLANTA_OPERAC_M10().toString());}catch (Exception e){}
        try{LimpiezaInternaExternaComen.setText(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA().toString());}catch (Exception e){}
        try{LimpiezaIndividualComen.setText(dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV().toString());}catch (Exception e){}
        try{VerificacionTorquesComen.setText(dcPower.getPARAM_AyO_PLANTA_VERIFICAION().toString());}catch (Exception e){}

        //STPFDC.setText(dcPower.getPARAM_STPF_DC_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_STPF_DC_NA().equals("true")){STPFDC.setChecked(true);}}catch (Exception e){}
        try{VerificacionConexionesTerminalesComen.setText(dcPower.getPARAM_STPF_DC_CAMP1().toString());}catch (Exception e){}
        try{DebeSerZaptaComen.setText(dcPower.getPARAM_STPF_DC_CAMP2().toString());}catch (Exception e){}
        try{VerificacionEtiquetadoTierrasComen.setText(dcPower.getPARAM_STPF_DC_CAMP3().toString());}catch (Exception e){}
        try{VerificacionCableadoExistenteTierrasComen.setText(dcPower.getPARAM_STPF_DC_CAMP4().toString());}catch (Exception e){}
        try{MidaResistenciaComen.setText(dcPower.getPARAM_STPF_DC_CAMP5().toString());}catch (Exception e){}
        try{CablesGabineteComen.setText(dcPower.getPARAM_STPF_DC_CAMP6().toString());}catch (Exception e){}
        try{CableadoEstantesComen.setText(dcPower.getPARAM_STPF_DC_CAMP7().toString());}catch (Exception e){}
        try{VerificacionCableadoTierradeGabineteLVDBcomen.setText(dcPower.getPARAM_STPF_DC_CAMP8().toString());}catch (Exception e){}

        //ITPF.setText(dcPower.getPARAM_ITPF_DC_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_ITPF_DC_NA().equals("true")){ITPF.setChecked(true);}}catch (Exception e){}
        try{RetireTapasEquiposComen.setText(dcPower.getPARAM_ITPF_DC_CAMP1().toString());}catch (Exception e){}
        try{VisualmenteInspeccionComen.setText(dcPower.getPARAM_ITPF_DC_CAMP2().toString());}catch (Exception e){}
        try{AyudaCamaraTermograficaComen.setText(dcPower.getPARAM_ITPF_DC_CAMP3().toString());}catch (Exception e){}
        try{EtapaRectificadoresComen.setText(dcPower.getPARAM_ITPF_DC_CAMP4().toString());}catch (Exception e){}
        try{PanelGabineteACComen.setText(dcPower.getPARAM_ITPF_DC_CAMP5().toString());}catch (Exception e){}
        try{PanelGabineteDCComen.setText(dcPower.getPARAM_ITPF_DC_CAMP6().toString());}catch (Exception e){}
        try{CableadoAlimentacionComen.setText(dcPower.getPARAM_ITPF_DC_CAMP7().toString());}catch (Exception e){}

        //PGSDDC.setText(dcPower.getPARAM_SISTEMA_DIST_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_SISTEMA_DIST_NA().equals("true")){PGSDDC.setChecked(true);}}catch (Exception e){}
        try{PGSDDCModelo.setText(dcPower.getPARAM_SISTEMA_DIST_MODELO().toString());}catch (Exception e){}
        try{PGSDDCMarca.setText(dcPower.getPARAM_SISTEMA_DIST_MARCA().toString());}catch (Exception e){}
        try{PGSDDCNoEspecifico.setText(dcPower.getPARAM_SISTEMA_DIST_ESPECIFI().toString());}catch (Exception e){}
        try{PGSDDCCapacidad.setText(dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD().toString());}catch (Exception e){}
        try{PGSDDCConfiguracion.setText(dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION().toString());}catch (Exception e){}

        //PGIDR.setText(dcPower.getPARAM_IDR_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_IDR_NA().equals("true")){PGIDR.setChecked(true);}}catch (Exception e){}
        try{LimpiezaGabinete.setText(dcPower.getPARAM_IDR_CAMP1().toString());}catch (Exception e){}
        try{ VerificacionEstado.setText(dcPower.getPARAM_IDR_CAMP2().toString());}catch (Exception e){}
        try{ VerificarGabinete.setText(dcPower.getPARAM_IDR_CAMP3().toString());}catch (Exception e){}
        try{ VerificarTorque.setText(dcPower.getPARAM_IDR_CAMP4().toString());}catch (Exception e){}
        try{ VerificarTorqueCargasGabinete.setText(dcPower.getPARAM_IDR_CAMP5().toString());}catch (Exception e){}
        try{ VerificarModulos.setText(dcPower.getPARAM_IDR_CAMP6().toString());}catch (Exception e){}
        try{ ValoresReales.setText(dcPower.getPARAM_IDR_CAMP7().toString());}catch (Exception e){}
        try{ Breakers1.setText(dcPower.getPARAM_IDR_BREAK1().toString());}catch (Exception e){}
        try{ Breakers2.setText(dcPower.getPARAM_IDR_BREAK2().toString());}catch (Exception e){}
        try{ Breakers3.setText(dcPower.getPARAM_IDR_BREAK3().toString());}catch (Exception e){}
        try{ Breakers4.setText(dcPower.getPARAM_IDR_BREAK4().toString());}catch (Exception e){}
        try{ Breakers5.setText(dcPower.getPARAM_IDR_BREAK5().toString());}catch (Exception e){}
        try{ Breakers6.setText(dcPower.getPARAM_IDR_BREAK6().toString());}catch (Exception e){}
        try{ Breakers7.setText(dcPower.getPARAM_IDR_BREAK7().toString());}catch (Exception e){}
        try{ Breakers8.setText(dcPower.getPARAM_IDR_BREAK8().toString());}catch (Exception e){}

        // try{ PGSI.setText(dcPower.getPARAM_SISTEMA_INVER_NA().toString());}catch (Exception e){}
        try{if(dcPower.getPARAM_SISTEMA_INVER_NA().equals("true")){PGSI.setChecked(true);}}catch (Exception e){}
        try{ PGSIModelo.setText(dcPower.getPARAM_SISTEMA_INVER_MODELO().toString());}catch (Exception e){}
        try{ PGSIMarca.setText(dcPower.getPARAM_SISTEMA_INVER_MARCA().toString());}catch (Exception e){}
        try{ PGSINoEspecificacion.setText(dcPower.getPARAM_SISTEMA_INVER_ESPEC().toString());}catch (Exception e){}
        try{ PGSICapacidad.setText(dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD().toString());}catch (Exception e){}
        try{ PGSIConfiguracion.setText(dcPower.getPARAM_SISTEMA_INVER_CONFG().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV1.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV1().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV2.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV2().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV3.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV3().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV4.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV4().toString());}catch (Exception e){}
        try{  NoSerieLineaAINV5.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV5().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV6.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV6().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV7.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV7().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV8.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV8().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV9.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV9().toString());}catch (Exception e){}
        try{ NoSerieLineaAINV10.setText(dcPower.getPARAM_SISTEMA_INVER_A_INV10().toString());}catch (Exception e){}
        try{  NoSerieLineaBINV1.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV1().toString());}catch (Exception e){}
        try{  NoSerieLineaBINV2.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV2().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV3.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV3().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV4.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV4().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV5.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV5().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV6.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV6().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV7.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV7().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV8.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV8().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV9.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV9().toString());}catch (Exception e){}
        try{ NoSerieLineaBINV10.setText(dcPower.getPARAM_SISTEMA_INVER_B_INV10().toString());}catch (Exception e){}
        try{ NoSerieLineaCINV1.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV1().toString());}catch (Exception e){}
        try{ NoSerieLineaCINV2.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV2().toString());}catch (Exception e){}
        try{ NoSerieLineaCINV3.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV3().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV4.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV4().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV5.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV5().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV6.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV6().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV7.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV7().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV8.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV8().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV9.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV9().toString());}catch (Exception e){}
        try{  NoSerieLineaCINV10.setText(dcPower.getPARAM_SISTEMA_INVER_C_INV10().toString());}catch (Exception e){}
        try{  VerificarManeraVisualComen.setText(dcPower.getPARAM_SISTEMA_INVER_VERI1().toString());}catch (Exception e){}
        try{  VerificarReportarExistenciaComen.setText(dcPower.getPARAM_SISTEMA_INVER_VERI2().toString());}catch (Exception e){}
        try{  VerificacionBuenEstadoComen.setText(dcPower.getPARAM_SISTEMA_INVER_VERI3().toString());}catch (Exception e){}
        try{  VerificarRegistrarCargaComen.setText(dcPower.getPARAM_SISTEMA_INVER_VERI4().toString());}catch (Exception e){}
        try{  LimpiezaGabineteInversor.setText(dcPower.getPARAM_SISTEMA_INVER_LIMP().toString());}catch (Exception e){}
        try{ VoltajeFaseABVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC1().toString());}catch (Exception e){}
        try{ VoltajeFaseBCVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC2().toString());}catch (Exception e){}
        try{  VoltajeFaseCAVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC3().toString());}catch (Exception e){}
        try{  VoltajeFaseANVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC4().toString());}catch (Exception e){}
        try{ VoltajeFaseBNVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC5().toString());}catch (Exception e){}
        try{ VoltajeFaseCNVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC6().toString());}catch (Exception e){}
        try{ VoltajeNeutroVac.setText(dcPower.getPARAM_NAyS_ALIM_VAC7().toString());}catch (Exception e){}
        try{ VoltajeAlimentacionDCVAC.setText(dcPower.getPARAM_NAyS_ALIM_VAC8().toString());}catch (Exception e){}
        try{ VoltajeFaseABSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC1().toString());}catch (Exception e){}
        try{ VoltajeFaseBCSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC2().toString());}catch (Exception e){}
        try{ VoltajeFaseCASVac.setText(dcPower.getPARAM_NAyS_SALID_VAC3().toString());}catch (Exception e){}
        try{ VoltajeFaseANSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC4().toString());}catch (Exception e){}
        try{ VoltajeFaseBNSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC5().toString());}catch (Exception e){}
        try{  VoltajeFaseCNSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC6().toString());}catch (Exception e){}
        try{  VoltajeNeutroSVac.setText(dcPower.getPARAM_NAyS_SALID_VAC7().toString());}catch (Exception e){}
        try{ VoltajeFaseABACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA1().toString());}catch (Exception e){}
        try{ VoltajeFaseBCACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA2().toString());}catch (Exception e){}
        try{ VoltajeFaseCAACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA3().toString());}catch (Exception e){}
        try{ VoltajeFaseANACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA4().toString());}catch (Exception e){}
        try{ VoltajeFaseBNACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA5().toString());}catch (Exception e){}
        try{ VoltajeFaseCNACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA6().toString());}catch (Exception e){}
        try{ VoltajeNeutroACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA7().toString());}catch (Exception e){}
        try{  VoltajeAlimentacionDCACA.setText(dcPower.getPARAM_NAyS_ALIM_ACA8().toString());}catch (Exception e){}
        try{ VoltajeFaseABSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA1().toString());}catch (Exception e){}
        try{ VoltajeFaseBCSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA2().toString());}catch (Exception e){}
        try{ VoltajeFaseCASACA.setText(dcPower.getPARAM_NAyS_SALID_ACA3().toString());}catch (Exception e){}
        try{ VoltajeFaseANSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA4().toString());}catch (Exception e){}
        try{ VoltajeFaseBNSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA5().toString());}catch (Exception e){}
        try{ VoltajeFaseCNSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA6().toString());}catch (Exception e){}
        try{ VoltajeNeutroSACA.setText(dcPower.getPARAM_NAyS_SALID_ACA7().toString());}catch (Exception e){}
        try{ ExtraerModuloCom.setText(dcPower2.getCampoextra1().toString());}catch (Exception e){}
        try{ ColocarModuloInversorPosicionOriginalCom.setText(dcPower2.getCampoextra2().toString());}catch (Exception e){}
        try{ VerificarGabineteConectadoSistemaTierrasCom.setText(dcPower2.getCampoextra3().toString());}catch (Exception e){}
        try{ VerificarTorqueCableadoEntradaSalidaSistemaInversorcom.setText(dcPower2.getCampoextra4().toString());}catch (Exception e){}
        try{ ExtrerComputadoraInventarioSistemaCom.setText(dcPower2.getCampoextra5().toString());}catch (Exception e){}
        try{ VerificarModulosSupervisionSistemaCom.setText(dcPower2.getCampoextra6().toString());}catch (Exception e){}


        //endregion

        //region VALIDACIONES

        //region Validacion M1
        int MV1 = 5;

        try{if(!dcPower.getPARAM_GRALS_TIPO().isEmpty()){ MV1 =  MV1-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_N_ESPECIFICAION().isEmpty()){ MV1 =  MV1-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CAPACIDAD().isEmpty()){ MV1 =  MV1-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CONFIGURACION().isEmpty()){ MV1 =  MV1-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_GRALS_CARGA_ACT().isEmpty()){ MV1 =  MV1-1;}}catch (Exception e){}
        if(MV1==0){Btn_M1.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(MV1<5){Btn_M1.setBackgroundColor(Color.parseColor("#FE5B1B"));}
//endregion

        //region ValidacionM2
        int MC2_A = 3;
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_MODELO().isEmpty()){ MC2_A =  MC2_A-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION().isEmpty()){ MC2_A =  MC2_A-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_CAPACIDAD().isEmpty()){ MC2_A =  MC2_A-1;}}catch (Exception e){}
       int MC2_B = 32;
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R1().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R2().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R3().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R4().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R5().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R6().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R7().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R8().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R9().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R10().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R11().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R12().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R13().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R14().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R15().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R16().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R17().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R18().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R19().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R20().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R21().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R22().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R23().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R24().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R25().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R26().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R27().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R28().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R29().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R30().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R31().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_INV_RECTIFICADORES_R32().isEmpty()){ MC2_B =  MC2_B-1;}}catch (Exception e){}
        if(MC2_A==0 && MC2_B < 32){
            Btn_M2.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(MC2_A<3 || MC2_B < 32){
            Btn_M2.setBackgroundColor(Color.parseColor("#FE5B1B"));}
//endregion

        //region ValidacionM3
        int MV3A = 1;
        int MV3B = 6;
        try{if(dcPower.getPARAM_INV_GABINETES_NA().equals("true")){
            Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));
        }else{
                try{if(!dcPower.getPARAM_INV_GABINETES_ESPECIFIC().isEmpty()){ MV3A =  MV3A-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB1().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB2().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB3().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB4().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB5().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
                try{if(!dcPower.getPARAM_INV_GABINETES_GAB6().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}

                if(MV3A==0 && MV3B < 6){
                    Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
                else if((MV3A==1 && MV3B < 6) || (MV3A<1 && MV3B == 6)){
                    Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}
            else{}
        }
        }catch (Exception m){
            try{if(!dcPower.getPARAM_INV_GABINETES_ESPECIFIC().isEmpty()){ MV3A =  MV3A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB1().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB2().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB3().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB4().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB5().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_INV_GABINETES_GAB6().isEmpty()){ MV3B =  MV3B-1;}}catch (Exception e){}

            if(MV3A==0 && MV3B < 6){
                Btn_M3.setBackgroundColor(Color.parseColor("#64A539"));}
            else if((MV3A==1 && MV3B < 6) || (MV3A<1 && MV3B == 6)){
                Btn_M3.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        }
//endregion

        //region ValidacionM4
        int MV4A = 2;
        int MV4B = 4;
        try{if(dcPower.getPARAM_SUPRESOR_NA().equals("true")){
            Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));
        }
        else{
            try{if(!dcPower.getPARAM_SUPRESOR_MODELO().isEmpty()){ MV4A =  MV4A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_MARCA().isEmpty()){ MV4A =  MV4A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_INSTALAC().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CAPACIDAD().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}

            if(MV4A==0 && MV4B < 4){
                Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV4A<2 || MV4B < 4){
                Btn_M4.setBackgroundColor(Color.parseColor("#FE5B1B"));}
            else{}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_SUPRESOR_MODELO().isEmpty()){ MV4A =  MV4A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_MARCA().isEmpty()){ MV4A =  MV4A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_INSTALAC().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_CONEXION().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CAPACIDAD().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SUPRESOR_CALIBRE_TIERRAS().isEmpty()){ MV4B =  MV4B-1;}}catch (Exception e){}

            if(MV4A==0 && MV4B < 4){
                Btn_M4.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV4A<2 || MV4B < 4){
                Btn_M4.setBackgroundColor(Color.parseColor("#FE5B1B"));}
            else{}
        }
        //endregion

        //region ValidacionM5
        int MV5 = 21;
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M1().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M2().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M3().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M4().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M5().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M6().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M7().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_ALIMENT_M8().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M1().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M2().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M3().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M4().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M5().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M6().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M7().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M8().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M9().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_OPERAC_M10().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_LIMPIEZA_INDV().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_AyO_PLANTA_VERIFICAION().isEmpty()){ MV5 =  MV5-1;}}catch (Exception e){}
        if(MV5==0){
            Btn_M5.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(MV5<21){
            Btn_M5.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        //endregion

        //region ValidacionM6
        int MV6 = 8;
        try{if(dcPower.getPARAM_STPF_DC_NA().equals("true")){
            Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));
        }else{
            try{if(!dcPower.getPARAM_STPF_DC_CAMP1().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP2().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP3().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP4().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP5().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP6().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP7().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP8().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            if(MV6<8){
                Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));}


        }}catch (Exception m){
            try{if(!dcPower.getPARAM_STPF_DC_CAMP1().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP2().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP3().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP4().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP5().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP6().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP7().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_STPF_DC_CAMP8().isEmpty()){ MV6 =  MV6-1;}}catch (Exception e){}
            if(MV6<8){
                Btn_M6.setBackgroundColor(Color.parseColor("#64A539"));}

        }
        //endregion

        //region ValidacionM7
        int MV7 = 7;
        try{if(dcPower.getPARAM_ITPF_DC_NA().equals("true")){
            Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));
        }else{

            try{if(!dcPower.getPARAM_ITPF_DC_CAMP1().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP2().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP3().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP4().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP5().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP6().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP7().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}

            if(MV7<7){
                Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP1().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP2().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP3().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP4().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP5().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP6().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_ITPF_DC_CAMP7().isEmpty()){ MV7 =  MV7-1;}}catch (Exception e){}

            if(MV7<7){
                Btn_M7.setBackgroundColor(Color.parseColor("#64A539"));}

        }
        //endregion

        //region ValidacionM8
        int MV8 = 5;
        try{if(dcPower.getPARAM_SISTEMA_DIST_NA().equals("true")){
            Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));
        }else{

            try{if(!dcPower.getPARAM_SISTEMA_DIST_MODELO().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MARCA().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_ESPECIFI().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            if(MV8==0){
                Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV8<5){
                Btn_M8.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        }}catch (Exception m){
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MODELO().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_MARCA().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_ESPECIFI().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CAPACIDAD().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_DIST_CONFIGURACION().isEmpty()){ MV8 =  MV8-1;}}catch (Exception e){}
            if(MV8==0){
                Btn_M8.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV8<5){
                Btn_M8.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        }
        //endregion

        //region ValidacionM9
        int MV9A = 7;
        int MV9B = 8;
        try{if(dcPower.getPARAM_IDR_NA().equals("true")){
            Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));
        }else{
            try{if(!dcPower.getPARAM_IDR_CAMP1().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP2().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP3().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP4().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP5().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP6().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP7().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK1().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK2().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK3().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK4().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK5().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK6().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK7().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK8().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            if(MV9A<7 && MV9B <8){
                Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));}
            else if((MV9A<7 && MV9B==8) ||(MV9A==7 && MV9B<8 ) ){
                Btn_M9.setBackgroundColor(Color.parseColor("#FE5B1B"));}else{}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_IDR_CAMP1().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP2().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP3().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP4().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP5().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP6().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_CAMP7().isEmpty()){ MV9A =  MV9A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK1().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK2().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK3().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK4().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK5().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK6().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK7().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_IDR_BREAK8().isEmpty()){ MV9B =  MV9B-1;}}catch (Exception e){}
            if(MV9A<7 && MV9B <8){
                Btn_M9.setBackgroundColor(Color.parseColor("#64A539"));}
            else if((MV9A<7 && MV9B==8) ||(MV9A==7 && MV9B<8 ) ){
                Btn_M9.setBackgroundColor(Color.parseColor("#FE5B1B"));}else{}
        }
//endregion

        //region ValidacionM10
        int MV10 = 5;
        int MV10A = 10;
        int MV10C = 10;
        int MV10B = 10;
        int MV10P = 5;
        try{if(dcPower.getPARAM_SISTEMA_INVER_NA().equals("true")){
            Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));

        }else{
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MODELO().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MARCA().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_ESPEC().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CONFG().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV1().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV2().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV3().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV4().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV5().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV6().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV7().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV8().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV9().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV10().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV1().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV2().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV3().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV4().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV5().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV6().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV7().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV8().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV9().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV10().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV1().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV2().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV3().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV4().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV5().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV6().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV7().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV8().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV9().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV10().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI1().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI2().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI3().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI4().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_LIMP().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}

            if(MV10 == 0 && MV10A<10 && MV10B <10 && MV10C <10 && MV10P <5){
                Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV10 < 5 || MV10A<10 || MV10B <10 || MV10C <10 ||MV10P <5){
                Btn_M10.setBackgroundColor(Color.parseColor("#FE5B1B"));}

        }}catch (Exception m){
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MODELO().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_MARCA().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_ESPEC().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CAPACIDAD().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_CONFG().isEmpty()){ MV10 =  MV10-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV1().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV2().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV3().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV4().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV5().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV6().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV7().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV8().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV9().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_A_INV10().isEmpty()){ MV10A =  MV10A-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV1().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV2().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV3().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV4().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV5().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV6().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV7().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV8().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV9().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_B_INV10().isEmpty()){ MV10B =  MV10B-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV1().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV2().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV3().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV4().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV5().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV6().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV7().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV8().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV9().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_C_INV10().isEmpty()){ MV10C =  MV10C-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI1().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI2().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI3().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_VERI4().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}
            try{if(!dcPower.getPARAM_SISTEMA_INVER_LIMP().isEmpty()){ MV10P =  MV10P-1;}}catch (Exception e){}

            if(MV10 == 0 && MV10A<10 && MV10B <10 && MV10C <10 && MV10P <5){
                Btn_M10.setBackgroundColor(Color.parseColor("#64A539"));}
            else if(MV10 < 5 || MV10A<10 || MV10B <10 || MV10C <10 ||MV10P <5){
                Btn_M10.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        }
        //endregion

        //region ValidacionM11
        int MV11 = 30;
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC1().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC2().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC3().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC4().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC5().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC6().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC7().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_VAC8().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC1().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC2().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC3().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC4().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC5().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC6().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_VAC7().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA1().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA2().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA3().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA4().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA5().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA6().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA7().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_ALIM_ACA8().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA1().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA2().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA3().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA4().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA5().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA6().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        try{if(!dcPower.getPARAM_NAyS_SALID_ACA7().isEmpty()){ MV11 =  MV11-1;}}catch (Exception e){}
        if(MV11 == 0){
            Btn_M11.setBackgroundColor(Color.parseColor("#64A539"));}
        else if(MV11  < 30){
            Btn_M11.setBackgroundColor(Color.parseColor("#FE5B1B"));}
        // endregion

      
        //endregion
        
        
        //region Pestañas M2
        clickcampo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana1M1.setVisibility(View.VISIBLE);
                pestana2M1.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo1.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo2.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });

        clickcampo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana2M1.setVisibility(View.VISIBLE);
                pestana1M1.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo2.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo1.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion
        //region Pestañas M5
        clickcampo1M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana1M5.setVisibility(View.VISIBLE);
                pestana2M5.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo1M5.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo2M5.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });

        clickcampo2M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana2M5.setVisibility(View.VISIBLE);
                pestana1M5.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo2M5.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo1M5.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion
        //region Pestañas M10
        clickcampo1M10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana1M10.setVisibility(View.VISIBLE);
                pestana2M10.setVisibility(View.GONE);
                pestana3M10.setVisibility(View.GONE);

                clickcampo1M10.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo2M10.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampo3M10.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });

        clickcampo2M10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana2M10.setVisibility(View.VISIBLE);
                pestana1M10.setVisibility(View.GONE);
                pestana3M10.setVisibility(View.GONE);

                clickcampo2M10.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo1M10.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampo3M10.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });

        clickcampo3M10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana3M10.setVisibility(View.VISIBLE);
                pestana1M10.setVisibility(View.GONE);
                pestana2M10.setVisibility(View.GONE);

                clickcampo3M10.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo1M10.setBackgroundColor(Color.parseColor("#E5E3E3"));
                clickcampo2M10.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion
        //region Pestañas M11
        clickcampo1M11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana1M11.setVisibility(View.VISIBLE);
                pestana2M11.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo1M11.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo2M11.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });

        clickcampo2M11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pestana2M11.setVisibility(View.VISIBLE);
                pestana1M11.setVisibility(View.GONE);
                //content_M3.setVisibility(View.GONE);

                clickcampo2M11.setBackgroundColor(Color.parseColor("#B7B5B5"));
                clickcampo1M11.setBackgroundColor(Color.parseColor("#E5E3E3"));
                //Btn_M3.setBackgroundColor(Color.parseColor("#E5E3E3"));

            }
        });
        //endregion





        //region BTN M1
        Btn_M1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM1){
                    content_M1.setVisibility(View.VISIBLE);
                    ActivoM1 = true;
                }
                else{
                    content_M1.setVisibility(View.GONE);
                    ActivoM1 = false;
                }
            }
        });
        //endregion

        //region BTN M2
        Btn_M2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM2){
                    content_M2.setVisibility(View.VISIBLE);
                    ActivoM2 = true;
                }
                else{
                    content_M2.setVisibility(View.GONE);
                    ActivoM2 = false;
                }
            }
        });
        //endregion

        //region BTN M3
        Btn_M3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM3){
                    content_M3.setVisibility(View.VISIBLE);
                    ActivoM3 = true;
                }
                else{
                    content_M3.setVisibility(View.GONE);
                    ActivoM3 = false;
                }
            }
        });
        //endregion

        //region BTN M4
        Btn_M4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM4){
                    content_M4.setVisibility(View.VISIBLE);
                    ActivoM4 = true;
                }
                else{
                    content_M4.setVisibility(View.GONE);
                    ActivoM4 = false;
                }
            }
        });
        //endregion

        //region BTN M5
        Btn_M5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM5){
                    content_M5.setVisibility(View.VISIBLE);
                    ActivoM5 = true;
                }
                else{
                    content_M5.setVisibility(View.GONE);
                    ActivoM5 = false;
                }
            }
        });
        //endregion

        //region BTN M6
        Btn_M6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM6){
                    content_M6.setVisibility(View.VISIBLE);
                    ActivoM6 = true;
                }
                else{
                    content_M6.setVisibility(View.GONE);
                    ActivoM6 = false;
                }
            }
        });
        //endregion

        //region BTN M7
        Btn_M7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM7){
                    content_M7.setVisibility(View.VISIBLE);
                    ActivoM7 = true;
                }
                else{
                    content_M7.setVisibility(View.GONE);
                    ActivoM7 = false;
                }
            }
        });
        //endregion

        //region BTN M8
        Btn_M8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM8){
                    content_M8.setVisibility(View.VISIBLE);
                    ActivoM8 = true;
                }
                else{
                    content_M8.setVisibility(View.GONE);
                    ActivoM8 = false;
                }
            }
        });
        //endregion

        //region BTN M9
        Btn_M9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM9){
                    content_M9.setVisibility(View.VISIBLE);
                    ActivoM9 = true;
                }
                else{
                    content_M9.setVisibility(View.GONE);
                    ActivoM9 = false;
                }
            }
        });
        //endregion

        //region BTN M10
        Btn_M10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM10){
                    content_M10.setVisibility(View.VISIBLE);
                    ActivoM10 = true;
                }
                else{
                    content_M10.setVisibility(View.GONE);
                    ActivoM10 = false;
                }
            }
        });
        //endregion

        //region BTN M11
        Btn_M11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ActivoM11){
                    content_M11.setVisibility(View.VISIBLE);
                    ActivoM11 = true;
                }
                else{
                    content_M11.setVisibility(View.GONE);
                    ActivoM11 = false;
                }
            }
        });
        //endregion


        //region Botones
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
                //region Mostrar datos guardados

                //region Modulo 1
                //dcPower.setPARAM_INV_GABINETES_NA(PGIGRPDC.getText().toString());
                dcPower.setPARAM_GRALS_TIPO(PGSDCTipo.getText().toString());
                dcPower.setPARAM_GRALS_N_ESPECIFICAION(PGSDCNoEspecificacion.getText().toString());
                dcPower.setPARAM_GRALS_CAPACIDAD(PGSDCCapacidad.getText().toString());
                dcPower.setPARAM_GRALS_CONFIGURACION(PGSDCConfiguracion.getText().toString());
                dcPower.setPARAM_GRALS_CARGA_ACT(PGSDCCargaActual.getText().toString());
                //endregion

                //region Modulo 2
                dcPower.setPARAM_INV_RECTIFICADORES_MODELO(PGIRModelo.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_N_ESPECIFICACION(PGIRNoEspecificacion.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_CAPACIDAD(PGIRCapacidad.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R1(NoSerieR1.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R2(NoSerieR2.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R3(NoSerieR3.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R4(NoSerieR4.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R5(NoSerieR5.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R6(NoSerieR6.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R7(NoSerieR7.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R8(NoSerieR8.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R9(NoSerieR9.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R10(NoSerieR10.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R11(NoSerieR11.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R12(NoSerieR12.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R13(NoSerieR13.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R14(NoSerieR14.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R15(NoSerieR15.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R16(NoSerieR16.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R17(NoSerieR17.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R18(NoSerieR18.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R19(NoSerieR19.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R20(NoSerieR20.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R21(NoSerieR21.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R22(NoSerieR22.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R23(NoSerieR23.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R24(NoSerieR24.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R25(NoSerieR25.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R26(NoSerieR26.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R27(NoSerieR27.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R28(NoSerieR28.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R29(NoSerieR29.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R30(NoSerieR30.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R31(NoSerieR31.getText().toString());
                dcPower.setPARAM_INV_RECTIFICADORES_R32(NoSerieR32.getText().toString());
                //endregion

                //region Modulo 3
                //dcPower.setPARAM_SUPRESOR_NA(PGIGRPDC.getText().toString());
                if(PGIGRPDC.isChecked()){dcPower.setPARAM_INV_GABINETES_NA("true");}
                else{dcPower.setPARAM_INV_GABINETES_NA("");
                dcPower.setPARAM_INV_GABINETES_ESPECIFIC(EspecificacionesGabinete.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB1(NoSerieGab1.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB2(NoSerieGab2.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB3(NoSerieGab3.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB4(NoSerieGab4.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB5(NoSerieGab5.getText().toString());
                dcPower.setPARAM_INV_GABINETES_GAB6(NoSerieGab6.getText().toString());}
                //endregion

                //region Modulo 4
                //dcPower.setPARAM_SUPRESOR_NA(VISPTVSSTAF.getText().toString());
                if(VISPTVSSTAF.isChecked()){dcPower.setPARAM_SUPRESOR_NA("true");}
                else {
                    dcPower.setPARAM_SUPRESOR_NA("");
                    dcPower.setPARAM_SUPRESOR_MODELO(VISPTVSSTAFModelo.getText().toString());
                    dcPower.setPARAM_SUPRESOR_MARCA(VISPTVSSTAFMarca.getText().toString());
                    dcPower.setPARAM_SUPRESOR_INSTALAC(InstalacionMecanicaGabineteTVSS.getText().toString());
                    dcPower.setPARAM_SUPRESOR_CALIBRE_CONEXION(CalibreCableadoConexionTVSS.getText().toString());
                    dcPower.setPARAM_SUPRESOR_CAPACIDAD(CapacidadbreakersConexionTableroAc.getText().toString());
                    dcPower.setPARAM_SUPRESOR_CALIBRE_TIERRAS(CalibreCableTierrasTVSS.getText().toString());
                }
                //endregion

                //region Modulo 5
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M1(PAPFDCVoltajeAlimentacion.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M2(PAPFDCVoltajeFaseAB.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M3(PAPFDCVoltajeFaseBC.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M4(PAPFDCVoltajeFaseCA.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M5(PAPFDCVoltajeFaseAN.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M6(PAPFDCVoltajeFaseBN.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M7(PAPFDCVoltajeFaseCN.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_ALIMENT_M8(PAPFDCVoltajeNeutroGND.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M1(POPFDCVoltajeFlotacion.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M2(POPFDCVoltajeIgualacion.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M3(POPFDCAlarmaBajoVoltaje1.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M4(POPFDCAlarmaBajoVoltaje2.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M5(POPFDCDesconexionBajoVoltaje.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M6(POPFDCAlarmaAltoVoltaje1.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M7(POPFDCAlarmaAltoVoltaje2.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M8(POPFDCAlarmaFusibleCargaAbierto.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M9(POPFDCAlarmaFusibleBateriasAbierto.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_OPERAC_M10(POPFDCAlarmaFallaRectificador.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_LIMPIEZA_INTERNA(LimpiezaInternaExternaComen.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_LIMPIEZA_INDV(LimpiezaIndividualComen.getText().toString());
                dcPower.setPARAM_AyO_PLANTA_VERIFICAION(VerificacionTorquesComen.getText().toString());
                //endregion

                //region Modulo 6
                //dcPower.setPARAM_STPF_DC_NA(STPFDC.getText().toString());
                if(STPFDC.isChecked()){dcPower.setPARAM_STPF_DC_NA("true");}else {
                    dcPower.setPARAM_STPF_DC_NA("");
                    dcPower.setPARAM_STPF_DC_CAMP1(VerificacionConexionesTerminalesComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP2(DebeSerZaptaComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP3(VerificacionEtiquetadoTierrasComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP4(VerificacionCableadoExistenteTierrasComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP5(MidaResistenciaComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP6(CablesGabineteComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP7(CableadoEstantesComen.getText().toString());
                    dcPower.setPARAM_STPF_DC_CAMP8(VerificacionCableadoTierradeGabineteLVDBcomen.getText().toString());

                    //dcPower.setPARAM_STPF_DC_CAMP8(CableBusPositivoComen.getText().toString());
                }
                //endregion

                //region Modulo 7
                //dcPower.setPARAM_ITPF_DC_NA(ITPF.getText().toString());
                if(ITPF.isChecked()){dcPower.setPARAM_ITPF_DC_NA("true");}else {
                    dcPower.setPARAM_ITPF_DC_NA("");
                dcPower.setPARAM_ITPF_DC_CAMP1(RetireTapasEquiposComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP2(VisualmenteInspeccionComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP3(AyudaCamaraTermograficaComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP4(EtapaRectificadoresComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP5(PanelGabineteACComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP6(PanelGabineteDCComen.getText().toString());
                dcPower.setPARAM_ITPF_DC_CAMP7(CableadoAlimentacionComen.getText().toString());}
                //endregion

                //region Modulo 8
                //dcPower.setPARAM_SISTEMA_DIST_NA(PGSDDC.getText().toString());
                if(PGSDDC.isChecked()){dcPower.setPARAM_SISTEMA_DIST_NA("true");}else {
                    dcPower.setPARAM_SISTEMA_DIST_NA("");
                dcPower.setPARAM_SISTEMA_DIST_MODELO(PGSDDCModelo.getText().toString());
                dcPower.setPARAM_SISTEMA_DIST_MARCA(PGSDDCMarca.getText().toString());
                dcPower.setPARAM_SISTEMA_DIST_ESPECIFI(PGSDDCNoEspecifico.getText().toString());
                dcPower.setPARAM_SISTEMA_DIST_CAPACIDAD(PGSDDCCapacidad.getText().toString());
                dcPower.setPARAM_SISTEMA_DIST_CONFIGURACION(PGSDDCConfiguracion.getText().toString());}
                //endregion

                //region Modulo 9
                //dcPower.setPARAM_IDR_NA(PGIDR.getText().toString());
                if(PGIDR.isChecked()){dcPower.setPARAM_IDR_NA("true");}else {
                    dcPower.setPARAM_IDR_NA("");
                dcPower.setPARAM_IDR_CAMP1(LimpiezaGabinete.getText().toString());
                dcPower.setPARAM_IDR_CAMP2(VerificacionEstado.getText().toString());
                dcPower.setPARAM_IDR_CAMP3(VerificarGabinete.getText().toString());
                dcPower.setPARAM_IDR_CAMP4(VerificarTorque.getText().toString());
                dcPower.setPARAM_IDR_CAMP5(VerificarTorqueCargasGabinete.getText().toString());
                dcPower.setPARAM_IDR_CAMP6(VerificarModulos.getText().toString());
                dcPower.setPARAM_IDR_CAMP7(ValoresReales.getText().toString());
                dcPower.setPARAM_IDR_BREAK1(Breakers1.getText().toString());
                dcPower.setPARAM_IDR_BREAK2(Breakers2.getText().toString());
                dcPower.setPARAM_IDR_BREAK3(Breakers3.getText().toString());
                dcPower.setPARAM_IDR_BREAK4(Breakers4.getText().toString());
                dcPower.setPARAM_IDR_BREAK5(Breakers5.getText().toString());
                dcPower.setPARAM_IDR_BREAK6(Breakers6.getText().toString());
                dcPower.setPARAM_IDR_BREAK7(Breakers7.getText().toString());
                dcPower.setPARAM_IDR_BREAK8(Breakers8.getText().toString());}
                //endregion

                //region Modulo 10
                //dcPower.setPARAM_SISTEMA_INVER_NA(PGSI.getText().toString());
                if(PGSI.isChecked()){dcPower.setPARAM_SISTEMA_INVER_NA("true");}else {
                    dcPower.setPARAM_SISTEMA_INVER_NA("");
                    dcPower.setPARAM_SISTEMA_INVER_MODELO(PGSIModelo.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_MARCA(PGSIMarca.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_ESPEC(PGSINoEspecificacion.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_CAPACIDAD(PGSICapacidad.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_CONFG(PGSIConfiguracion.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV1(NoSerieLineaAINV1.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV2(NoSerieLineaAINV2.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV3(NoSerieLineaAINV3.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV4(NoSerieLineaAINV4.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV5(NoSerieLineaAINV5.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV6(NoSerieLineaAINV6.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV7(NoSerieLineaAINV7.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV8(NoSerieLineaAINV8.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV9(NoSerieLineaAINV9.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_A_INV10(NoSerieLineaAINV10.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV1(NoSerieLineaBINV1.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV2(NoSerieLineaBINV2.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV3(NoSerieLineaBINV3.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV4(NoSerieLineaBINV4.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV5(NoSerieLineaBINV5.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV6(NoSerieLineaBINV6.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV7(NoSerieLineaBINV7.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV8(NoSerieLineaBINV8.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV9(NoSerieLineaBINV9.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_B_INV10(NoSerieLineaBINV10.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV1(NoSerieLineaCINV1.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV2(NoSerieLineaCINV2.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV3(NoSerieLineaCINV3.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV4(NoSerieLineaCINV4.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV5(NoSerieLineaCINV5.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV6(NoSerieLineaCINV6.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV7(NoSerieLineaCINV7.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV8(NoSerieLineaCINV8.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV9(NoSerieLineaCINV9.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_C_INV10(NoSerieLineaCINV10.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_VERI1(VerificarManeraVisualComen.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_VERI2(VerificarReportarExistenciaComen.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_VERI3(VerificacionBuenEstadoComen.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_VERI4(VerificarRegistrarCargaComen.getText().toString());
                    dcPower.setPARAM_SISTEMA_INVER_LIMP(LimpiezaGabineteInversor.getText().toString());
                    dcPower2.setCampoextra1(ExtraerModuloCom.getText().toString());
                    dcPower2.setCampoextra2(ColocarModuloInversorPosicionOriginalCom.getText().toString());
                    dcPower2.setCampoextra3(VerificarGabineteConectadoSistemaTierrasCom.getText().toString());
                    dcPower2.setCampoextra4(VerificarTorqueCableadoEntradaSalidaSistemaInversorcom.getText().toString());
                    dcPower2.setCampoextra5(ExtrerComputadoraInventarioSistemaCom.getText().toString());
                    dcPower2.setCampoextra6(VerificarModulosSupervisionSistemaCom.getText().toString());
                }
                //endregion

                //region Modulo 11
                dcPower.setPARAM_NAyS_ALIM_VAC1(VoltajeFaseABVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC2(VoltajeFaseBCVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC3(VoltajeFaseCAVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC4(VoltajeFaseANVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC5(VoltajeFaseBNVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC6(VoltajeFaseCNVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC7(VoltajeNeutroVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_VAC8(VoltajeAlimentacionDCVAC.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC1(VoltajeFaseABSVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC2(VoltajeFaseBCSVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC3(VoltajeFaseCASVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC4(VoltajeFaseANSVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC5(VoltajeFaseBNSVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC6(VoltajeFaseCNSVac.getText().toString());
                dcPower.setPARAM_NAyS_SALID_VAC7(VoltajeNeutroSVac.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA1(VoltajeFaseABACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA2(VoltajeFaseBCACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA3(VoltajeFaseCAACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA4(VoltajeFaseANACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA5(VoltajeFaseBNACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA6(VoltajeFaseCNACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA7(VoltajeNeutroACA.getText().toString());
                dcPower.setPARAM_NAyS_ALIM_ACA8(VoltajeAlimentacionDCACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA1(VoltajeFaseABSACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA2(VoltajeFaseBCSACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA3(VoltajeFaseCASACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA4(VoltajeFaseANSACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA5(VoltajeFaseBNSACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA6(VoltajeFaseCNSACA.getText().toString());
                dcPower.setPARAM_NAyS_SALID_ACA7(VoltajeNeutroSACA.getText().toString());
                //endregion
                //endregion


                D_B.guardarPower1(dcPower);


                D_B.guardarPower2(dcPower2, id_formato);



                Bundle args = new Bundle();
                args.putString("key_idFormato", dcPower.getID_fORMATO());
                MenuPowerFragment myfargment = new MenuPowerFragment();
                myfargment.setArguments(args);
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main_fragment_placeholder, myfargment);

                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });
        //endregion




        return v;
    }


}