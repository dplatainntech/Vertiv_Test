package com.maju.desarrollo.testfcs.Remote;

import com.maju.desarrollo.testfcs.ServiceClass.BateriaS;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;
import com.maju.desarrollo.testfcs.ServiceClass.CambioContrasena;
import com.maju.desarrollo.testfcs.ServiceClass.CierrePreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.DCPower;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;
import com.maju.desarrollo.testfcs.ServiceClass.FoliosReporte;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
import com.maju.desarrollo.testfcs.ServiceClass.Garantia;
import com.maju.desarrollo.testfcs.ServiceClass.GeneralServicios;
import com.maju.desarrollo.testfcs.ServiceClass.PDU;
import com.maju.desarrollo.testfcs.ServiceClass.RecuperarContrasena;
import com.maju.desarrollo.testfcs.ServiceClass.STS2;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatEstado;
import com.maju.desarrollo.testfcs.ServiceClass.PreTrabajo;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatAsignaCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatBestel;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatCliente;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatFolios;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.CatRegion;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.FoliosCierre;
import com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones.Login;
import com.maju.desarrollo.testfcs.ServiceClass.Test;
import com.maju.desarrollo.testfcs.ServiceClass.ThermalManagagementS;
import com.maju.desarrollo.testfcs.ServiceClass.UPS;
import com.maju.desarrollo.testfcs.ServiceClass.Vigencia;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.HTTP;
import retrofit2.http.POST;

public interface IMyAPI {

    @POST("api/Test")
    Observable<Test> Test(@Body Test test);

    @POST("api/PreTrabajo")
    Observable<PreTrabajo> EnvioPreoTrabajo(@Body PreTrabajo pretrabajo);

    @POST("api/Login")
    Observable<Login> Login(@Body Login usuario);

    @POST("api/CatAsignaCliente")
    Observable<List<CatAsignaCliente>> obtenerTask(@Body CatAsignaCliente asignaciones);

    @POST("api/CatClientes")
    Observable<List<CatCliente>> obtenerClientes(@Body CatCliente cliente);

    @POST("api/CatFolios")
    Observable<List<CatFolios>> obtenerfoliosPT(@Body CatFolios folio);

    @POST("api/CatBestel")
    Observable<List<CatBestel>> obtenerDireccionesBestel(@Body CatBestel bestel);

    @POST("api/CatRegion")
    Observable<List<CatRegion>> catRegiones(@Body CatRegion bestel);

    @POST("api/CatEstado")
    Observable<List<CatEstado>> catEstado(@Body CatEstado bestel);

    @POST("api/Bestel1")
    Observable<Bestel1> enviarBestel1(@Body Bestel1 bestel);

    @POST("api/Bestel2")
    Observable<Bestel2> enviarBestel2(@Body Bestel2 bestel);

    @POST("api/EncuestaCalidadServicio")
    Observable<EncuestaCalidadServicio> enviarCalidad(@Body EncuestaCalidadServicio bestel);

    @POST("api/GeneralServicios")
    Observable<GeneralServicios> enviarGServicios(@Body GeneralServicios servicios);

    @POST("api/CierrePreTrabajo")
    Observable<CierrePreTrabajo> GenerarPaquete(@Body CierrePreTrabajo servicios);

    @POST("api/FoliosCierre")
    Observable<FoliosCierre> obtenerFormatosFolio(@Body FoliosCierre servicios);

    @POST("api/UPS")
    Observable<UPS> enviarUPS(@Body UPS formato);

    @POST("api/Garantia")
    Observable<Garantia> enviarGarantia(@Body Garantia formato);

    @POST("api/ThermalManagagement")
    Observable<ThermalManagagementS> enviarThermal(@Body ThermalManagagementS formato);

    @POST("api/PDU")
    Observable<PDU> enviarPDU(@Body PDU formato);

    @POST("api/STS2")
    Observable<STS2> enviarSTS2(@Body STS2 formato);

    @POST("api/Bateria")
    Observable<BateriaS> enviarBaterias(@Body BateriaS formato);

    @POST("api/FoliosReporte")
    Observable<List<FoliosReporte>> consultaFolios(@Body FoliosReporte formato);

    @POST("api/DCPower")
    Observable<DCPower> enviarDCPower(@Body DCPower formato);

    @POST("api/Vigencia")
    Observable<Vigencia> vigenciaUsuario(@Body Vigencia formato);

    //@POST("api/RecuperaContraseña")
    //Observable<RecuperarContrasena> (@Body RecuperarContrasena formato);

    @POST("api/CambioContraseña")
    Observable<CambioContrasena> cambioContraseña(@Body CambioContrasena formato);

    @HTTP(method = "POST", path = "api/RecuperaContraseña", hasBody = true)
    Observable<RecuperarContrasena> recuperarContraseña(@Body RecuperarContrasena usuario);

    //@HTTP(method = "POST", path = "api/FoliosReporte", hasBody = true)
    //Observable<FoliosReporte> consultaFolios(@Body FoliosReporte usuario);

    @HTTP(method = "POST", path = "api/CatEstado", hasBody = true)
    Observable<CatEstado> TestEstado(@Body CatEstado usuario);

    @POST("api/Fotos")
    Observable<FotosFormato> enviarFoto(@Body FotosFormato item);

}
