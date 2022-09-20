package com.maju.desarrollo.testfcs.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.maju.desarrollo.testfcs.Class.MensajesCls;
import com.maju.desarrollo.testfcs.Class.spinnerDosCampos;
import com.maju.desarrollo.testfcs.SQLite.Model.Baterias;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower;
import com.maju.desarrollo.testfcs.SQLite.Model.DCPower2;
import com.maju.desarrollo.testfcs.SQLite.Model.DatGeneralesF;
import com.maju.desarrollo.testfcs.SQLite.Model.PreOrden;
import com.maju.desarrollo.testfcs.SQLite.Model.SGarantias;
import com.maju.desarrollo.testfcs.SQLite.Model.Servicios;
import com.maju.desarrollo.testfcs.SQLite.Model.UsuarioD;
import com.maju.desarrollo.testfcs.SQLite.Model.searchCliente;
import com.maju.desarrollo.testfcs.SQLite.Model.searchSitio;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel1;
import com.maju.desarrollo.testfcs.ServiceClass.Bestel2;
import com.maju.desarrollo.testfcs.ServiceClass.EncuestaCalidadServicio;
import com.maju.desarrollo.testfcs.ServiceClass.FotosFormato;
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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class OperacionesDB {
    private static BaseDatos baseDatos;
    private static OperacionesDB instancia = new OperacionesDB();
    private static Context Contexto;
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    DateFormat dateFormat2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());

   /*
    ArrayList<Pedidos> Listapedidos;
    ArrayList<Producto> Listaproductos;
    ArrayList<Producto_Pedido> listadeProdctopsen_Pedido;
    ArrayList<Cliente> listaClientes;
    ArrayList<CampañaSelect> listaCampañas;
    Cursor fila, fila2, CampañaDetetalles, CampañaCombo;
    DetallePedido detalle;

    */

    public static OperacionesDB obtenerInstancia(Context contexto) {

        if (baseDatos == null) {
            baseDatos = new BaseDatos(contexto);
        }
        Contexto = contexto;
        return instancia;
    }

    public SQLiteDatabase getDb() {
        return baseDatos.getWritableDatabase();
    }


    // region OPERACIONES INSERT

    public void iniciioSesion(UsuarioD usuario){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(FilasDB.ColumnasUsuario.ID_USER, usuario.getID_USER());
        valores.put(FilasDB.ColumnasUsuario.NOMBRE, usuario.getNOMBRE());
        valores.put(FilasDB.ColumnasUsuario.PATERNO, usuario.getPATERNO());
        valores.put(FilasDB.ColumnasUsuario.MATERNO, usuario.getMATERNO());
        valores.put(FilasDB.ColumnasUsuario.TELEFONO, usuario.getTELEFONO());
        valores.put(FilasDB.ColumnasUsuario.CORREO, usuario.getCORREO());
        valores.put(FilasDB.ColumnasUsuario.CONTRASEÑA, usuario.getCONTRASEÑA());
        valores.put(FilasDB.ColumnasUsuario.PAIS, usuario.getPAIS());
        valores.put(FilasDB.ColumnasUsuario.PaisDescripcion, usuario.getPaisDescripcion());

        //valores.put("Fecha_PreOrden", dateFormat.format(new Date()));
        // Insertar Usuario
        db.insertOrThrow(BaseDatos.Tablas.USUARIO, null, valores);

        newSesion(usuario.getID_USER());

    }

    public void newSesion(String id){
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();
        ContentValues valores2 = new ContentValues();
        valores2.put(FilasDB.ColumnasSesion.ID_User, id);
        valores2.put(FilasDB.ColumnasSesion.SESION_ACTIVA, "ACTIVA");


        db2.insertOrThrow(BaseDatos.Tablas.SESION, null, valores2);
    }

    public void guardarregiones(List<CatRegion> lista){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        for(int x=0; x<lista.size();x++) {
            CatRegion region = lista.get(x);
            ContentValues valores = new ContentValues();

            valores.put(FilasDB.ColumnasRegiones.IdRegion,region.getIdRegion());
            valores.put(FilasDB.ColumnasRegiones.Nombre,region.getNombre());

            db.insertOrThrow(BaseDatos.Tablas.REGIONES, null, valores);
        }


       // db.insertOrThrow(BaseDatos.Tablas.SESION, null, valores2);
    }

    public void guardarestados(List<CatEstado> lista){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        for(int x=0; x<lista.size();x++) {
            CatEstado estado = lista.get(x);
            ContentValues valores = new ContentValues();

            valores.put(FilasDB.ColumnasEstados.IdEstado,estado.getIdEstado());
            valores.put(FilasDB.ColumnasEstados.IdRegion,estado.getIdRegion());
            valores.put(FilasDB.ColumnasEstados.Nombre,estado.getNombre());


            db.insertOrThrow(BaseDatos.Tablas.ESTADOS, null, valores);
        }
    }

    public String nuevaPreOrden(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasPreOrden.ID_PreOrden, Keyvertiv);
        valores.put("Fecha_PreOrden", dateFormat.format(new Date()));
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_PERMISO_ARCHIVO, "");

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.PRE_ORDEN, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public void newMensaje(String Mensaje){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasMensajes.fecha, dateFormat.format(new Date()));
        valores.put(FilasDB.ColumnasMensajes.mensaje, Mensaje);
        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.MENSAJES, null, valores);

    }

    public String newBestel1(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasBestel1.IDFormato, Keyvertiv);
        valores.put("Fecha_formato", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.BESTEL1, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }
    public String newBestel2(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasBestel2.IDFormato, Keyvertiv);
        valores.put("Fecha_formato", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.BESTEL2, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newCalidad(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasCaliad.IDFormato, Keyvertiv);
        valores.put("Fecha_formato", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.CALIDAD, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newServicio(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasServicios.FolioFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasServicios.fechaCreacion, dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.SERVICIOS, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newGarantia(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasGarantias.FolioFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasGarantias.fechaCreacion, dateFormat.format(new Date()));
        valores.put(FilasDB.ColumnasGarantias.GarantiaArchivo, "");
        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.GARANTIAS, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newUPS(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasUPS.FolioFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasUPS.fechaCreacion, dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.UPS, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String nuevaPreOrden(CatAsignaCliente item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasPreOrden.ID_PreOrden, Keyvertiv);
        valores.put(FilasDB.ColumnasPreOrden.GEN_CLIENTE, item.getInstall_Party_Name());
        valores.put(FilasDB.ColumnasPreOrden.GEN_DIRECCION, item.getAdress());
        valores.put(FilasDB.ColumnasPreOrden.GEN_SR, item.getSr_Nbr());
        valores.put(FilasDB.ColumnasPreOrden.GEN_TASK, item.getTask_Nbr());
        valores.put(FilasDB.ColumnasPreOrden.GEN_SITIO, item.getSite());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_PERMISO_ARCHIVO, "");

        valores.put("Fecha_PreOrden", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.PRE_ORDEN, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String nuevaCalidad(CatAsignaCliente item){
        UsuarioD usu = obtenerUsuario();
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasCaliad.IDFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasCaliad.IdCliente, item.getInstall_Party_Name());
        valores.put(FilasDB.ColumnasCaliad.DireccionSitio, item.getAdress());
        valores.put(FilasDB.ColumnasCaliad.SRProyecto, item.getSr_Nbr());
        valores.put(FilasDB.ColumnasCaliad.TASK, item.getTask_Nbr());
        valores.put(FilasDB.ColumnasCaliad.NombreSitio, item.getSite());
        valores.put(FilasDB.ColumnasCaliad.IdUsuario, usu.getID_USER());
        valores.put(FilasDB.ColumnasCaliad.ModeloEquipo, item.getItem_Number());
        valores.put(FilasDB.ColumnasCaliad.NoSerie, item.getSerial_Number());
        valores.put(FilasDB.ColumnasCaliad.Contrato, item.getContract_Nbr());

        valores.put("Fecha_formato", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.CALIDAD, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String nuevoServicio(CatAsignaCliente item, String id_TipoFormato){
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
                 String idFormato = "";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s ='%s' and %s='%s' ",
                BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.TASK, item.getTask_Nbr(),
                FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO,id_TipoFormato);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            idFormato = fila.getString(0);
        }else {
            SQLiteDatabase db2 = baseDatos.getWritableDatabase();
            // Generar Pk
            idFormato = FilasDB.key_Vertiv.generarKeyVertiv();
            nuevoRegistroFormato(idFormato,id_TipoFormato,item);

            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.IN1)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasBestel1.IDFormato, idFormato);
                valores.put(FilasDB.ColumnasBestel1.IdCliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasBestel1.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasBestel1.Task, item.getTask_Nbr());
                valores.put("Fecha_formato", dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.BESTEL1, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.IN2)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasBestel2.IDFormato, idFormato);
                valores.put(FilasDB.ColumnasBestel2.IdCliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasBestel2.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasBestel2.Task, item.getTask_Nbr());
                valores.put("Fecha_formato", dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.BESTEL2, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.Servicios)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasServicios.FolioFormato, idFormato);
                valores.put(FilasDB.ColumnasServicios.Cliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasServicios.Dirección, item.getAdress());
                valores.put(FilasDB.ColumnasServicios.SR, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasServicios.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasServicios.MODELO, item.getItem_Number());
                valores.put(FilasDB.ColumnasServicios.NSERIE, item.getSerial_Number());
                valores.put(FilasDB.ColumnasServicios.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.SERVICIOS, null, valores);

            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.UPS)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasUPS.FolioFormato, idFormato);
                valores.put(FilasDB.ColumnasUPS.IdCliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasUPS.DireccionSitio, item.getAdress());
                valores.put(FilasDB.ColumnasUPS.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasUPS.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasUPS.ModeloEquipo, item.getItem_Number());
                valores.put(FilasDB.ColumnasUPS.NoSerie, item.getSerial_Number());
                valores.put(FilasDB.ColumnasUPS.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.UPS, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.Garantias)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasGarantias.FolioFormato, idFormato);
                valores.put(FilasDB.ColumnasGarantias.Cliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasGarantias.Dirección, item.getAdress());
                valores.put(FilasDB.ColumnasGarantias.SR, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasGarantias.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasGarantias.MODELO, item.getItem_Number());
                valores.put(FilasDB.ColumnasGarantias.NSERIE, item.getSerial_Number());
                valores.put(FilasDB.ColumnasGarantias.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.GARANTIAS, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.Thermal)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasThermal.IDFormato, idFormato);
                valores.put(FilasDB.ColumnasThermal.Cliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasThermal.DireccionSitio, item.getAdress());
                valores.put(FilasDB.ColumnasThermal.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasThermal.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasThermal.ModeloEquipo, item.getItem_Number());
                valores.put(FilasDB.ColumnasThermal.NoSerie, item.getSerial_Number());
                valores.put(FilasDB.ColumnasThermal.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.TERMAL, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.PDU)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasPDU.IDFormato, idFormato);
                valores.put(FilasDB.ColumnasPDU.IdCliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasPDU.DireccionSitio, item.getAdress());
                valores.put(FilasDB.ColumnasPDU.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasPDU.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasPDU.ModeloEquipo, item.getItem_Number());
                valores.put(FilasDB.ColumnasPDU.NoSerie, item.getSerial_Number());
                valores.put(FilasDB.ColumnasPDU.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.PDU, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.Baterias)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasBaterias.ID_fORMATO, idFormato);
                valores.put(FilasDB.ColumnasBaterias.GRAL_cliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasBaterias.GRAL_direccion, item.getAdress());
                valores.put(FilasDB.ColumnasBaterias.GRAL_sr, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasBaterias.GRAL_task, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasBaterias.GRAL_modelo, item.getItem_Number());
                valores.put(FilasDB.ColumnasBaterias.GRAL_serie1, item.getSerial_Number());
                //valores.put(FilasDB.ColumnasBaterias.GRAL_F, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.BATERIAS, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.DCPower)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasDCPwer.ID_fORMATO, idFormato);
                valores.put(FilasDB.ColumnasDCPwer.GRAL_CLIENTE, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasDCPwer.GRAL_DIRECCION, item.getAdress());
                if(item.getTask_Nbr().length()>2){
                    valores.put(FilasDB.ColumnasDCPwer.GRAL_SR, item.getSr_Nbr());
                }else{
                    valores.put(FilasDB.ColumnasDCPwer.GRAL_PROYECTO, item.getSr_Nbr());
                }
                valores.put(FilasDB.ColumnasDCPwer.GRAL_TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasDCPwer.GRAL_MODELO, item.getItem_Number());
                valores.put(FilasDB.ColumnasDCPwer.GRAL_SERIE, item.getSerial_Number());
                //valores.put(FilasDB.ColumnasDCPwer.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.DC_POWER, null, valores);
            }
            if(id_TipoFormato.equals(FilasDB.id_tipoFormato.STS2)){
                ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasSTS2.IDFormato, idFormato);
                valores.put(FilasDB.ColumnasSTS2.IdCliente, item.getInstall_Party_Name());
                valores.put(FilasDB.ColumnasSTS2.DireccionSitio, item.getAdress());
                valores.put(FilasDB.ColumnasSTS2.SRProyecto, item.getSr_Nbr());
                valores.put(FilasDB.ColumnasSTS2.TASK, item.getTask_Nbr());
                valores.put(FilasDB.ColumnasSTS2.ModeloEquipo, item.getItem_Number());
                valores.put(FilasDB.ColumnasSTS2.NoSerie, item.getSerial_Number());
                valores.put(FilasDB.ColumnasSTS2.fechaCreacion, dateFormat.format(new Date()));
                // Insertar cabecera
                db2.insertOrThrow(BaseDatos.Tablas.STS2, null, valores);
            }
        }
        nuevoInsert();

        return idFormato;
    }

    public String nuevoDCPower(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasDCPwer.ID_fORMATO, Keyvertiv);
        valores.put("Fecha_formato", dateFormat.format(new Date()));

        // Insertar cabecera
        db.insertOrThrow(BaseDatos.Tablas.DC_POWER, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String nuevoBateria(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();
        String time = dateFormat.format(new Date());
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasBaterias.ID_fORMATO, Keyvertiv);
        valores.put("Fecha_formato", time);

        db.insertOrThrow(BaseDatos.Tablas.BATERIAS, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newThermal(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();
        String time = dateFormat.format(new Date());
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasThermal.IDFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasThermal.fechaCreacion, time);

        db.insertOrThrow(BaseDatos.Tablas.TERMAL, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newPDU(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();
        String time = dateFormat.format(new Date());
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasPDU.IDFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasPDU.fechaCreacion, time);

        db.insertOrThrow(BaseDatos.Tablas.PDU, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public String newSTS2(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String Keyvertiv = FilasDB.key_Vertiv.generarKeyVertiv();
        String time = dateFormat.format(new Date());
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasSTS2.IDFormato, Keyvertiv);
        valores.put(FilasDB.ColumnasSTS2.fechaCreacion, time);

        db.insertOrThrow(BaseDatos.Tablas.STS2, null, valores);

        nuevoInsert();

        return Keyvertiv;
    }

    public void guardarTask (List<CatAsignaCliente> lista){

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(int x=0; x<lista.size();x++) {
            CatAsignaCliente task = lista.get(x);
            ContentValues valores = new ContentValues();

            valores.put(FilasDB.ColumnasCatalogoTask.IdCtlAsignaciones,task.getIdCtlAsignaciones());
            valores.put(FilasDB.ColumnasCatalogoTask.Task_Assignee,task.getTask_Assignee());
            valores.put(FilasDB.ColumnasCatalogoTask.Sr_Nbr,task.getSr_Nbr());
            valores.put(FilasDB.ColumnasCatalogoTask.Sr_Problem_Type,task.getSr_Problem_Type());
            valores.put(FilasDB.ColumnasCatalogoTask.Item_Number,task.getItem_Number());
            valores.put(FilasDB.ColumnasCatalogoTask.Serial_Number,task.getSerial_Number());
            valores.put(FilasDB.ColumnasCatalogoTask.Install_Party_Name,task.getInstall_Party_Name());
            valores.put(FilasDB.ColumnasCatalogoTask.Install_Party_Site_Nbr,task.getInstall_Party_Site_Nbr());
            valores.put(FilasDB.ColumnasCatalogoTask.Task_Status,task.getTask_Status());
            valores.put(FilasDB.ColumnasCatalogoTask.Task_Nbr,task.getTask_Nbr());
            valores.put(FilasDB.ColumnasCatalogoTask.Task_Start_Planned,task.getTask_Start_Planned());
            valores.put(FilasDB.ColumnasCatalogoTask.Contract_Nbr,task.getContract_Nbr());
            valores.put(FilasDB.ColumnasCatalogoTask.Customer,task.getCustomer());
            valores.put(FilasDB.ColumnasCatalogoTask.Site,task.getSite());
            valores.put(FilasDB.ColumnasCatalogoTask.Adress,task.getAdress());

            db.insertOrThrow(BaseDatos.Tablas.TASK, null, valores);
        }

        //actualizar tabla de mensajes.
    }

    public void guardarCLientes (List<CatCliente> lista){

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(int x=0; x<lista.size();x++) {
            CatCliente cliente = lista.get(x);
            ContentValues valores = new ContentValues();

            valores.put(FilasDB.ColumnasClientes.IdClientes,cliente.getIdClientes());
            valores.put(FilasDB.ColumnasClientes.NombreCliente,cliente.getNombreCliente());
            valores.put(FilasDB.ColumnasClientes.NombreSitio,cliente.getNombreSitio());
            valores.put(FilasDB.ColumnasClientes.Direccion,cliente.getDireccion());

            db.insertOrThrow(BaseDatos.Tablas.CLIENTEs, null, valores);
        }

        //actualizar tabla de mensajes.
    }

    public void guardarFoliosPT (List<CatFolios> lista){

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(int x=0; x<lista.size();x++) {
            CatFolios item = lista.get(x);
            ContentValues valores = new ContentValues();

            valores.put(FilasDB.ColumnasFOliosPretrabajo.Usuario,item.getUsuario());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.Cliente,item.getCliente());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.GEN_PROYECTO,item.getGEN_PROYECTO());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.GEN_SR,item.getGEN_SR());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.GEN_TASK,item.getGEN_TASK());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.FolioPreTrabajo,item.getFolioPreTrabajo());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.NombreSitio,item.getNombreSitio());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.DireccionSitio,item.getDireccionSitio());
            valores.put(FilasDB.ColumnasFOliosPretrabajo.LiderGrupoCuadrilla,item.getLiderGrupoCuadrilla());

            db.insertOrThrow(BaseDatos.Tablas.CAT_FOLIOS_PRET, null, valores);
        }

        //actualizar tabla de mensajes.
    }

    public void guardardireccionesBestel (List<CatBestel> lista){

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        for(int x=0; x<lista.size();x++) {
            CatBestel item = lista.get(x);
            ContentValues valores = new ContentValues();
            valores.put(FilasDB.ColumnasDireccionesBestel.IdBestel,item.getIdBestel());
            valores.put(FilasDB.ColumnasDireccionesBestel.IdRegion,item.getIdRegion());
            valores.put(FilasDB.ColumnasDireccionesBestel.IdEstado,item.getIdEstado());
            valores.put(FilasDB.ColumnasDireccionesBestel.Clasificacion,item.getClasificacion());
            valores.put(FilasDB.ColumnasDireccionesBestel.NombreSitio,item.getNombreSitio());
            valores.put(FilasDB.ColumnasDireccionesBestel.Direccion,item.getDireccion());
            valores.put(FilasDB.ColumnasDireccionesBestel.Cliente,item.getCliente());

            db.insertOrThrow(BaseDatos.Tablas.BESTEL, null, valores);
        }


        //actualizar tabla de mensajes.
    }

    public void nuevoRegistroFormato(String id_vertiv, String idTFormato){
        Date fecha = new Date();
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasFormatosControl.ID_FORMATO, id_vertiv);
        valores.put(FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO, idTFormato);
        valores.put(FilasDB.ColumnasFormatosControl.ESTATUS, "1");
        valores.put(FilasDB.ColumnasFormatosControl.FECHA_INICIO, dateFormat.format(new Date()));

        db2.insertOrThrow(BaseDatos.Tablas.CONTROL_FORMATOS, null, valores);

    }

    public void nuevoRegistroFormato(String id_vertiv, String idTFormato,CatAsignaCliente item ){
        Date fecha = new Date();
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasFormatosControl.ID_FORMATO, id_vertiv);
        valores.put(FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO, idTFormato);
        valores.put(FilasDB.ColumnasFormatosControl.ESTATUS, "1");
        valores.put(FilasDB.ColumnasFormatosControl.ITEM, item.getItem_Number());
        valores.put(FilasDB.ColumnasFormatosControl.CLIENTE, item.getInstall_Party_Name());
        valores.put(FilasDB.ColumnasFormatosControl.SERIE, item.getSerial_Number());
        valores.put(FilasDB.ColumnasFormatosControl.SR, item.getSr_Nbr());
        valores.put(FilasDB.ColumnasFormatosControl.TASK, item.getTask_Nbr());
        valores.put(FilasDB.ColumnasFormatosControl.FECHA_INICIO, dateFormat.format(new Date()));

        db2.insertOrThrow(BaseDatos.Tablas.CONTROL_FORMATOS, null, valores);

    }

    public void guardarImagenError(FotosFormato item){
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
                valores.put(FilasDB.ColumnasImagenes.Id	, item.getId());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto1	,item.getAntesFoto1());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto2	,item.getAntesFoto2());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto3	,item.getAntesFoto3());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto4	,item.getAntesFoto4());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto5	,item.getAntesFoto5());
                valores.put(FilasDB.ColumnasImagenes.AntesFoto6	,item.getAntesFoto6());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto1	,item.getDespuesFoto1());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto2	,item.getDespuesFoto2());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto3	,item.getDespuesFoto3());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto4	,item.getDespuesFoto4());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto5	,item.getDespuesFoto5());
                valores.put(FilasDB.ColumnasImagenes.DespuesFoto6	,item.getDespuesFoto6());
                valores.put(FilasDB.ColumnasImagenes.Antes1	,item.getAntes1());
                valores.put(FilasDB.ColumnasImagenes.Antes2	,item.getAntes2());
                valores.put(FilasDB.ColumnasImagenes.Antes3	,item.getAntes3());
                valores.put(FilasDB.ColumnasImagenes.Antes4	,item.getAntes4());
                valores.put(FilasDB.ColumnasImagenes.Antes5	,item.getAntes5());
                valores.put(FilasDB.ColumnasImagenes.Antes6	,item.getAntes6());
                valores.put(FilasDB.ColumnasImagenes.Despues1	,item.getDespues1());
                valores.put(FilasDB.ColumnasImagenes.Despues2	,item.getDespues2());
                valores.put(FilasDB.ColumnasImagenes.Despues3	,item.getDespues3());
                valores.put(FilasDB.ColumnasImagenes.Despues4	,item.getDespues4());
                valores.put(FilasDB.ColumnasImagenes.Despues5	,item.getDespues5());
                valores.put(FilasDB.ColumnasImagenes.Despues6	,item.getDespues6());
                valores.put(FilasDB.ColumnasImagenes.GarantiaArchivo,item.getGarantiaArchivo());
                valores.put(FilasDB.ColumnasImagenes.Formato	,item.getFormato());
                valores.put(FilasDB.ColumnasImagenes.INSPEC_PERMISO_ARCHIVO,item.getINSPEC_PERMISO_ARCHIVO());
        db2.insertOrThrow(BaseDatos.Tablas.imagenes, null, valores);
    }




// endregion

    //region OPERACIONES SELECT
    public void nuevoInsert(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("select last_insert_rowid()");
       Cursor a = db.rawQuery(sql, null);

        Log.d("Parametros","Parametros");
        DatabaseUtils.dumpCursor(a);
        return ;
    }

    public UsuarioD obtenerUsuario() {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        UsuarioD usuarioD = null;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.USUARIO);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            usuarioD =  new UsuarioD(
                    fila.getString(1),
                    fila.getString(2),
                    fila.getString(3),
                    fila.getString(4),
                    fila.getString(5),
                    fila.getString(6),
                    fila.getString(7),
                    fila.getString(8),
                    fila.getString(9));

        }else{
            usuarioD =  new UsuarioD(" "," "," "," "," "," "," ","","");
        }
        Log.d("USUARIO","DATOS");
        DatabaseUtils.dumpCursor(fila);


        return usuarioD;
    }

    public String sesionActiva(){
        String sesion = "";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.SESION);
        Cursor fila = db.rawQuery(sql, null);

        if (fila.moveToFirst()) {
            sesion = fila.getString(2);

        }else{
            sesion = "NUEVA";
        }
        Log.d("Sesion","DATOS");
        DatabaseUtils.dumpCursor(fila);
        return sesion;
    }
    public String ultimaFechaSinc(){
        String fecha = "";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.ACTUALIZACIONES);
        Cursor fila = db.rawQuery(sql, null);

        if (fila.moveToFirst()) {
            fecha = fila.getString(1);

        }else{
            fecha = "Sin sincronizaciones";
        }
        Log.d("Fechas sinc","Fecha");
        DatabaseUtils.dumpCursor(fila);
        return fecha;
    }

    public EncuestaCalidadServicio obtenerCalidad(String Key_Formato) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        EncuestaCalidadServicio item = new EncuestaCalidadServicio();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.CALIDAD, FilasDB.ColumnasCaliad.IDFormato, Key_Formato);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            Log.d("Tabla","Calidad");
            /*
             _id=3
       Fecha_formato=2020-01-18 16:06:38
       IDFormato=Key-4c5400c5-4421-4ac0-9019-d1eeb360bfa3
       IdUsuario=null
       IdCliente=null
       FolioPreTrabajo=null
       Fecha=null
       Contrato=null
       NombreCliente=null
       ClienteFinal=null
       ContactoCliente=null
       TipoServicio=null
       TipoEquipo=null
       Telefono=null
       EMail=null
       ModeloEquipo=null
       NoSerie=null
       DireccionSitio=null
       NombreSitio=null
       I1=null
       I2=null
       I3=null
       IComentarios=null
       II1=null
       II2=null
       II3=null
       II4=null
       IIComentarios=null
       III1=null
       III2=null
       III3=null
       III4=null
       IIIComentarios=null
       FirmaCliente=null
       FirmaVertiv=null
       FirmaClienteFinal=null
       FNombreCliente=null
       FNombreVertiv=null
             */
            DatabaseUtils.dumpCursor(fila);
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setIdCliente(fila.getString(4));
            item.setFolioPreTrabajo(fila.getString(5));
            item.setFecha(fila.getString(6));
            item.setContrato(fila.getString(7));
            item.setNombreCliente(fila.getString(8));
            item.setClienteFinal(fila.getString(9));
            item.setContactoCliente(fila.getString(10));
            item.setTipoServicio(fila.getString(11));
            item.setTipoEquipo(fila.getString(12));
            item.setTelefono(fila.getString(13));
            item.setEMail(fila.getString(14));
            item.setModeloEquipo(fila.getString(15));
            item.setNoSerie(fila.getString(16));
            item.setDireccionSitio(fila.getString(17));
            item.setNombreSitio(fila.getString(18));
            item.setI1(fila.getString(19));
            item.setI2(fila.getString(20));
            item.setI3(fila.getString(21));
            item.setIComentarios(fila.getString(22));
            item.setII1(fila.getString(23));
            item.setII2(fila.getString(24));
            item.setII3(fila.getString(25));
            item.setII4(fila.getString(26));
            item.setIIComentarios(fila.getString(27));
            item.setIII1(fila.getString(28));
            item.setIII2(fila.getString(29));
            item.setIII3(fila.getString(30));
            item.setIII4(fila.getString(31));
            item.setIIIComentarios(fila.getString(32));
            item.setFirmaCliente(fila.getString(33));
            item.setFirmaVertiv(fila.getString(34));
            item.setFirmaClienteFinal(fila.getString(35));
            item.setFNombreCliente(fila.getString(36));
            item.setFNombreVertiv(fila.getString(37));
            item.setFNombreClienteFinal(fila.getString(38));
            item.setProyecto(fila.getString(39));
            item.setReferencia(fila.getString(40));
            item.setTiposervicioOtro(fila.getString(41));
            item.setModelo2(fila.getString(42));
            item.setSerie2(fila.getString(43));

            item.setSRProyecto(fila.getString(44));
            item.setTASK(fila.getString(45));
            item.setRecomienda(fila.getString(46));
            item.setCRCNA(fila.getString(47));
            item.setCRC1(fila.getString(48));
            item.setCRC2(fila.getString(49));
            item.setCRC3(fila.getString(50));
            item.setCRCComentario(fila.getString(51));
        }

        return item;
    }

    public Bestel1 obtenerBestel1(String Key_Formato) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        Bestel1 item = new Bestel1();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.BESTEL1, FilasDB.ColumnasBestel1.IDFormato, Key_Formato);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {

            Log.d("Tabla","Bestel1");
            DatabaseUtils.dumpCursor(fila);
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setCliente(fila.getString(4));
            item.setExitoso(fila.getString(5));
            item.setError(fila.getString(6));
            item.setIdBestelNivel1(fila.getString(7));
            item.setFolioPreTrabajo(fila.getString(8));
            item.setFechaInicio(fila.getString(9));
            item.setFechaTermino(fila.getString(10));
            item.setSRProyecto(fila.getString(11));
            item.setRegion(fila.getString(12));
            item.setEstado(fila.getString(13));
            item.setTipoSitio(fila.getString(14));
            item.setNombreSitio(fila.getString(15));
            item.setDireccionSitio(fila.getString(16));
            item.setContactoCliente(fila.getString(17));
            item.setTelefono(fila.getString(18));
            item.setTask(fila.getString(19));
            item.setTipoServicio(fila.getString(20));
            item.setFrecuencia(fila.getString(21));
            item.setNoTag(fila.getString(22));
            item.setEmail(fila.getString(23));
            item.setAATemperaturaEstatus(fila.getString(24));
            item.setAATemperaturaComentarios(fila.getString(25));
            item.setAACondensadoraEstatus(fila.getString(26));
            item.setAACondensadoraComentarios(fila.getString(27));
            item.setAAEvaporadoraEstatus(fila.getString(28));
            item.setAAEvaporadoraComentarios(fila.getString(29));
            item.setAASerpentinEstatus(fila.getString(30));
            item.setAASerpentinComentarios(fila.getString(31));
            item.setAAFugaGasEstatus(fila.getString(32));
            item.setAAFugaGasComentarios(fila.getString(33));
            item.setAAAlimentacionEstatus(fila.getString(34));
            item.setAAAlimentacionComentarios(fila.getString(35));
            item.setAAFiltrosEstatus(fila.getString(36));
            item.setAAFiltrosComentarios(fila.getString(37));
            item.setAALimpiezaGeneralEstatus(fila.getString(38));
            item.setAALimpiezaGeneralComentarios(fila.getString(39));
            item.setECIluminaciionEstatus(fila.getString(40));
            item.setECIluminaciionComentarios(fila.getString(41));
            item.setECPinturaEstatus(fila.getString(42));
            item.setECPinturaComentarios(fila.getString(43));
            item.setECPisosEstatus(fila.getString(44));
            item.setECPisosComentarios(fila.getString(45));
            item.setECImpermeEstatus(fila.getString(46));
            item.setECImpermeComentarios(fila.getString(47));
            item.setECHidrosanitarioEstatus(fila.getString(48));
            item.setECHidrosanitarioComentarios(fila.getString(49));
            item.setECHerrejesEstatus(fila.getString(50));
            item.setECHerrejesComentarios(fila.getString(51));
            item.setECLimpiezaGeneralEstatus(fila.getString(52));
            item.setECLimpiezaGeneralComentarios(fila.getString(53));
            item.setPFBAmperajeEstatus(fila.getString(54));
            item.setPFBAmperajeComentarios(fila.getString(55));
            item.setPFBConsumoPlantaEstatus(fila.getString(56));
            item.setPFBConsumoPlantaComentarios(fila.getString(57));
            item.setPFBRectificadoresEstatus(fila.getString(58));
            item.setPFBRectificadoresComentarios(fila.getString(59));
            item.setPFBSistemaInversorEstatus(fila.getString(60));
            item.setPFBSistemaInversorComentarios(fila.getString(61));
            item.setPFBBancosBateriasEstatus(fila.getString(62));
            item.setPFBBancosBateriasComentarios(fila.getString(63));
            item.setPFBTablerosEstatus(fila.getString(64));
            item.setPFBTablerosComentarios(fila.getString(65));
            item.setSUAlimentacionElectricaEstatus(fila.getString(66));
            item.setSUAlimentacionElectricaComentarios(fila.getString(67));
            item.setSUAlarmasEstatus(fila.getString(68));
            item.setSUAlarmasComentarios(fila.getString(69));
            item.setSUCargaEstatus(fila.getString(70));
            item.setSUCargaComentarios(fila.getString(71));
            item.setSUDescargaEstatus(fila.getString(72));
            item.setSUDescargaComentarios(fila.getString(73));
            item.setSCISistemaEstatus(fila.getString(74));
            item.setSCISistemaComentarios(fila.getString(75));
            item.setSCIDetectoresEstatus(fila.getString(76));
            item.setSCIDetectoresComentarios(fila.getString(77));
            item.setSCIExtintoresEstatus(fila.getString(78));
            item.setSCIExtintoresComentarios(fila.getString(79));
            item.setSCIGranadaTanquesEstatus(fila.getString(80));
            item.setSCIGranadaTanquesComentarios(fila.getString(81));
            item.setSCIFechaCaducidadEstatus(fila.getString(82));
            item.setSCIFechaCaducidadComentraios(fila.getString(83));
            item.setPEFugasAceiteEstatus(fila.getString(84));
            item.setPEFugasAceiteComentarios(fila.getString(85));
            item.setPEFiltrosEstatus(fila.getString(86));
            item.setPEFiltrosComentarios(fila.getString(87));
            item.setPETemperaturaEstatus(fila.getString(88));
            item.setPETemperaturaComentarios(fila.getString(89));
            item.setPEBandasEstatus(fila.getString(90));
            item.setPEBandasComentarios(fila.getString(91));
            item.setPEBateriasEstatus(fila.getString(92));
            item.setPEBateriasComentarios(fila.getString(93));
            item.setPELubricacionEstatus(fila.getString(94));
            item.setPELubricacionComentarios(fila.getString(95));
            item.setPECombustibleEstatus(fila.getString(96));
            item.setPECombustibleComentarios(fila.getString(97));
            item.setPEArranqueManualEstatus(fila.getString(98));
            item.setPEArranqueManualComentarios(fila.getString(99));
            item.setPELimpiezaGenetalEstatus(fila.getString(100));
            item.setPELimpiezaGenetalComentarios(fila.getString(101));
            item.setSALectorasEstatus(fila.getString(102));
            item.setSALectorasComentarios(fila.getString(103));
            item.setSATablerosControlEstatus(fila.getString(104));
            item.setSATablerosControlComentarios(fila.getString(105));
            item.setSSBarrasPuestaTierraEstatus(fila.getString(106));
            item.setSSBarrasPuestaTierraComentarios(fila.getString(107));
            item.setSSConexionPuestaTierraEstatus(fila.getString(108));
            item.setSSConexionPuestaTierraComentarios(fila.getString(109));
            item.setSSTransformadorEstatus(fila.getString(110));
            item.setSSTransformadorComentarios(fila.getString(111));
            item.setSSFusiblesEstatus(fila.getString(112));
            item.setSSFusiblesComentarios(fila.getString(113));
            item.setSSTemperaturaEstatus(fila.getString(114));
            item.setSSTemperaturaComentarios(fila.getString(115));
            item.setSSCuchillasEstatus(fila.getString(116));
            item.setSSCuchillasComentarios(fila.getString(117));
            item.setSSInterruptoresEstatus(fila.getString(118));
            item.setSSInterruptoresComentarios(fila.getString(119));
            item.setTELimpizaGeneralEstatus(fila.getString(120));
            item.setTELimpizaGeneralComentarios(fila.getString(121));
            item.setTEAnclasRetenidosEstatus(fila.getString(122));
            item.setTEAnclasRetenidosComentarios(fila.getString(123));
            item.setTELucesObstruccionEstatus(fila.getString(124));
            item.setTELucesObstruccionComentarios(fila.getString(125));
            item.setTETornilleriaEstatus(fila.getString(126));
            item.setTETornilleriaComentarios(fila.getString(127));
            item.setTEPuestaTierraEstatus(fila.getString(128));
            item.setTEPuestaTierraComentarios(fila.getString(129));
            item.setTESistemaApartaRayosEststus(fila.getString(130));
            item.setTESistemaApartaRayosComentarios(fila.getString(131));
            item.setHFugasEstatus(fila.getString(132));
            item.setHFugasComentarios(fila.getString(133));
            item.setHBombasEstatus(fila.getString(134));
            item.setHBombasComentarios(fila.getString(135));
            item.setOtros(fila.getString(136));
            item.setComentarios(fila.getString(137));
            item.setMateriales(fila.getString(138));
            item.setEDSRTiempo(fila.getString(139));
            item.setEDSRCalidad(fila.getString(140));
            item.setEDSREvalucionSitio(fila.getString(141));
            item.setAntesFoto1(fila.getString(142));
            item.setAntesFoto2(fila.getString(143));
            item.setAntesFoto3(fila.getString(144));
            item.setAntesFoto4(fila.getString(145));
            item.setAntesFoto5(fila.getString(146));
            item.setAntesFoto6(fila.getString(147));
            item.setDespuesFoto1(fila.getString(148));
            item.setDespuesFoto2(fila.getString(149));
            item.setDespuesFoto3(fila.getString(150));
            item.setDespuesFoto4(fila.getString(151));
            item.setDespuesFoto5(fila.getString(152));
            item.setDespuesFoto6(fila.getString(153));
            item.setFirmaCliente(fila.getString(154));
            item.setFirmaVertiv(fila.getString(155));
            item.setProyecto(fila.getString(156));
            item.setReferencia(fila.getString(157));
            item.setModelo(fila.getString(158));
            item.setSSL1(fila.getString(159));
            item.setSSL2(fila.getString(160));
            item.setSSL3(fila.getString(161));
            item.setSSA1(fila.getString(162));
            item.setSSA2(fila.getString(163));
            item.setSSA3(fila.getString(164));
            item.setOtros1(fila.getString(165));
            item.setComentarios1(fila.getString(166));
            item.setOtros2(fila.getString(167));
            item.setComentarios2(fila.getString(168));
            item.setOtros3(fila.getString(169));
            item.setComentarios3(fila.getString(170));
            item.setOtros4(fila.getString(171));
            item.setComentarios4(fila.getString(172));
            item.setOtros5(fila.getString(173));
            item.setComentarios5(fila.getString(174));
            item.setOtros6(fila.getString(175));
            item.setComentarios6(fila.getString(176));
            item.setOtros7(fila.getString(177));
            item.setComentarios7(fila.getString(178));
            item.setNombreFirmaVertiv(fila.getString(179));
            item.setNombreFirmaCliente(fila.getString(180));
            item.setAD_NOMBRE1(fila.getString(181));
            item.setAD_CORREO1(fila.getString(182));
            item.setAD_NOMBRE2(fila.getString(183));
            item.setAD_CORREO2(fila.getString(184));
            item.setAD_NOMBRE3(fila.getString(185));
            item.setAD_CORREO3(fila.getString(186));
            item.setAD_NOMBRE4(fila.getString(187));
            item.setAD_CORREO4(fila.getString(188));
            item.setAD_NOMBRE5(fila.getString(189));
            item.setAD_CORREO5(fila.getString(190));
        }

        return item;
    }

    public Bestel2 obtenerBestel2(String Key_Formato) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        Bestel2 item = new Bestel2();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.BESTEL2, FilasDB.ColumnasBestel2.IDFormato, Key_Formato);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {

            Log.d("Tabla","Bestel1");
            DatabaseUtils.dumpCursor(fila);
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setIdCliente(fila.getString(4));
            item.setExitoso(fila.getString(5));
            item.setError(fila.getString(6));
            item.setIdBestelNivel2(fila.getString(7));
            item.setFolioPreTrabajo(fila.getString(8));
            item.setFechaInicio(fila.getString(9));
            item.setFechaTermino(fila.getString(10));
            item.setSRProyecto(fila.getString(11));
            item.setTask(fila.getString(12));
            item.setIdRegion(fila.getString(13));
            item.setTipoServicio(fila.getString(14));
            item.setIdEstado(fila.getString(15));
            item.setFrecuencia(fila.getString(16));
            item.setTipoSitio(fila.getString(17));
            item.setNoTag(fila.getString(18));
            item.setNombreSitio(fila.getString(19));
            item.setEmail(fila.getString(20));
            item.setDireccionSitio(fila.getString(21));
            item.setContactoCliente(fila.getString(22));
            item.setTelefono(fila.getString(23));
            item.setAATemperatura(fila.getString(24));
            item.setAACondensadora(fila.getString(25));
            item.setAAEvaporadora(fila.getString(26));
            item.setAASerpentin(fila.getString(27));
            item.setAAFugaGas(fila.getString(28));
            item.setAAVoltajeL1(fila.getString(29));
            item.setAAVoltajeL2(fila.getString(30));
            item.setAAVoltajeL3(fila.getString(31));
            item.setAAAmperajeL1(fila.getString(32));
            item.setAAAmperajeL2(fila.getString(33));
            item.setAAAmperajeL3(fila.getString(34));
            item.setAAValvulas(fila.getString(35));
            item.setAATermostatos(fila.getString(36));
            item.setAABombas(fila.getString(37));
            item.setAAPresionAlta(fila.getString(38));
            item.setAAPresionBaja(fila.getString(39));
            item.setAAFiltros(fila.getString(40));
            item.setAALimpiezaGeneral(fila.getString(41));
            item.setECIliminacion(fila.getString(42));
            item.setECPinturaMuros(fila.getString(43));
            item.setECPisos(fila.getString(44));
            item.setECImpermeabilizacion(fila.getString(45));
            item.setECHidrosanitario(fila.getString(46));
            item.setECHerrejes(fila.getString(47));
            item.setECLimpiezaGeneral(fila.getString(48));
            item.setPFVoltajeSalida(fila.getString(49));
            item.setPFCorrienteDisplay(fila.getString(50));
            item.setPFTensioDispaly(fila.getString(51));
            item.setPFTemSalas(fila.getString(52));
            item.setPFTensionl1l2(fila.getString(53));
            item.setPFTensionl2l3(fila.getString(54));
            item.setPFTensionl1l3(fila.getString(55));
            item.setPFCorrientesL1(fila.getString(56));
            item.setPFCorrientesL2(fila.getString(57));
            item.setPFCorrientesL3(fila.getString(58));
            item.setPFVoltajeNL1(fila.getString(59));
            item.setPFVoltajeNL2(fila.getString(60));
            item.setPFVoltajeNL3(fila.getString(61));
            item.setPFVoltajeFaseNeutro(fila.getString(62));
            item.setPFRectificadoresTemp(fila.getString(63));
            item.setPFFechaControl(fila.getString(64));
            item.setPFLimpieza(fila.getString(65));
            item.setSCSistema(fila.getString(66));
            item.setSCDetectores(fila.getString(67));
            item.setSCExtintores(fila.getString(68));
            item.setSCBotesAreneros(fila.getString(69));
            item.setSCFechaCaducidad(fila.getString(70));
            item.setSALectoras(fila.getString(71));
            item.setSASupervisoresPuertas(fila.getString(72));
            item.setSACCTV(fila.getString(73));
            item.setSABaterias(fila.getString(74));
            item.setSATablerosControl(fila.getString(75));
            item.setSUAlarmas(fila.getString(76));
            item.setSUTemperatura(fila.getString(77));
            item.setSUCapacitores(fila.getString(78));
            item.setSUVoltajeEntradaL1(fila.getString(79));
            item.setSUVoltajeEntradaL2(fila.getString(80));
            item.setSUVoltajeEntradaL3(fila.getString(81));
            item.setSUVoltajeByPassL1(fila.getString(82));
            item.setSUVoltajeByPassL2(fila.getString(83));
            item.setSUVoltajeByPassL3(fila.getString(84));
            item.setSUVoltajeSalidaL1(fila.getString(85));
            item.setSUVoltajeSalidaL2(fila.getString(86));
            item.setSUVoltajeSalidaL3(fila.getString(87));
            item.setSUCorrienteSalidaL1(fila.getString(88));
            item.setSUCorrienteSalidaL2(fila.getString(89));
            item.setSUCorrienteSalidaL3(fila.getString(90));
            item.setSUVoltajeTotalBaterias(fila.getString(91));
            item.setSUVerificacionVentiladiores(fila.getString(92));
            item.setSUReapreteConexiones(fila.getString(93));
            item.setSUCorrienteCargaBaterias(fila.getString(94));
            item.setSUCorrienteDescargaBaterias(fila.getString(95));
            item.setSUTorqueBaterias(fila.getString(96));
            item.setSUVoltajeEntreTierra(fila.getString(97));
            item.setSULimpieza(fila.getString(98));
            item.setPEFugasAceite(fila.getString(99));
            item.setPEAmperajeL1(fila.getString(100));
            item.setPEAmperajeL2(fila.getString(101));
            item.setPEAmperajeL3(fila.getString(102));
            item.setPEVoltajeL1(fila.getString(103));
            item.setPEVoltajeL2(fila.getString(104));
            item.setPEVoltajeL3(fila.getString(105));
            item.setPEHorasOperacion(fila.getString(106));
            item.setPEBaterias(fila.getString(107));
            item.setPENivelDisel(fila.getString(108));
            item.setPENivelAnticongelante(fila.getString(109));
            item.setPEManguerasGeneral(fila.getString(110));
            item.setPERuidosExtraños(fila.getString(111));
            item.setPETableroTransparencia(fila.getString(112));
            item.setPEPrecalentador(fila.getString(113));
            item.setPEFiltros(fila.getString(114));
            item.setPETemperatura(fila.getString(115));
            item.setPEBandas(fila.getString(116));
            item.setPEBateriasLiquido(fila.getString(117));
            item.setPELubricacion(fila.getString(118));
            item.setPEArranqueManualSinCarga(fila.getString(119));
            item.setPELimpiezaGeneral(fila.getString(120));
            item.setOPTEM(fila.getString(121));
            item.setOPL1L2(fila.getString(122));
            item.setOPL2L3(fila.getString(123));
            item.setOPL3L1(fila.getString(124));
            item.setOPP1W(fila.getString(125));
            item.setOPP1VAR(fila.getString(126));
            item.setOPP1VA(fila.getString(127));
            item.setOPL1N(fila.getString(128));
            item.setOPL2N(fila.getString(129));
            item.setOPL3N(fila.getString(130));
            item.setOPPF1(fila.getString(131));
            item.setOPPF2(fila.getString(132));
            item.setOPPF3(fila.getString(133));
            item.setOPHZ(fila.getString(134));
            item.setOPREVRPM(fila.getString(135));
            item.setSSESobreCalentamientoPorFase(fila.getString(136));
            item.setSSESobreCalentamientoPorFaseL1(fila.getString(137));
            item.setSSEBarrasPuestaTierra(fila.getString(138));
            item.setSSEBarrasPuestaTierraL2(fila.getString(139));
            item.setSSETransformador(fila.getString(140));
            item.setSSETransformadorNT(fila.getString(141));
            item.setSSEFusibles(fila.getString(142));
            item.setSSEFusiblesA1(fila.getString(143));
            item.setSSETemperatura(fila.getString(144));
            item.setSSETemperaturaA2(fila.getString(145));
            item.setSSECuchillas(fila.getString(146));
            item.setSSECuchillasA3(fila.getString(147));
            item.setSSEInterruptores(fila.getString(148));
            item.setSSEInterruptoresNT(fila.getString(149));
            item.setTTLimpiezaGeneral(fila.getString(150));
            item.setTTAnclasRetenidos(fila.getString(151));
            item.setTTLucesObstruccion(fila.getString(152));
            item.setTTTornilleriaHerraje(fila.getString(153));
            item.setTTPuestaTierra(fila.getString(154));
            item.setTTSistemaApartaRayos(fila.getString(155));
            item.setHFugasGeneral(fila.getString(156));
            item.setHHidroneumaticos(fila.getString(157));
            item.setHBaños(fila.getString(158));
            item.setHCisternasTanques(fila.getString(159));
            item.setHBombas(fila.getString(160));
            item.setHEmpaques(fila.getString(161));
            item.setHAccesorios(fila.getString(162));
            item.setOTROS(fila.getString(163));
            item.setAccionesTomadas(fila.getString(164));
            item.setMaterialesActividades(fila.getString(165));
            item.setAntesFoto1(fila.getString(166));
            item.setAntesFoto2(fila.getString(167));
            item.setAntesFoto3(fila.getString(168));
            item.setAntesFoto4(fila.getString(169));
            item.setAntesFoto5(fila.getString(170));
            item.setAntesFoto6(fila.getString(171));
            item.setDespuesFoto1(fila.getString(172));
            item.setDespuesFoto2(fila.getString(173));
            item.setDespuesFoto3(fila.getString(174));
            item.setDespuesFoto4(fila.getString(175));
            item.setDespuesFoto5(fila.getString(176));
            item.setDespuesFoto6(fila.getString(177));
            item.setFirmaCliente(fila.getString(178));
            item.setFirmaVertiv(fila.getString(179));
            item.setProyecto(fila.getString(180));
            item.setReferencia(fila.getString(181));
            item.setModelo(fila.getString(182));
            item.setOtros2(fila.getString(183));
            item.setComentarios2(fila.getString(184));
            item.setOtros3(fila.getString(185));
            item.setComentarios3(fila.getString(186));
            item.setOtros4(fila.getString(187));
            item.setComentarios4(fila.getString(188));
            item.setOtros5(fila.getString(189));
            item.setComentarios5(fila.getString(190));
            item.setOtros6(fila.getString(191));
            item.setComentarios6(fila.getString(192));
            item.setOtros7(fila.getString(193));
            item.setComentarios7(fila.getString(194));
            item.setOtros8(fila.getString(195));
            item.setComentarios8(fila.getString(196));
            item.setNombreFirmaVertiv(fila.getString(197));
            item.setNombreFirmaCliente(fila.getString(198));
            item.setAD_NOMBRE1(fila.getString(199));
            item.setAD_CORREO1(fila.getString(200));
            item.setAD_NOMBRE2(fila.getString(201));
            item.setAD_CORREO2(fila.getString(202));
            item.setAD_NOMBRE3(fila.getString(203));
            item.setAD_CORREO3(fila.getString(204));
            item.setAD_NOMBRE4(fila.getString(205));
            item.setAD_CORREO4(fila.getString(206));
            item.setAD_NOMBRE5(fila.getString(207));
            item.setAD_CORREO5(fila.getString(208));
            item.setSSEConexionPuestaTierra(fila.getString(209));
            item.setSSEConexionPuestaTierraL3(fila.getString(210));
        }

        return item;
    }

    public DCPower obtenerDcPower_id(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        DCPower Power = null;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.DC_POWER, FilasDB.ColumnasDCPwer.ID_fORMATO, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","DC_Power");
            DatabaseUtils.dumpCursor(fila);

            //region power
            Power = new DCPower(fila.getString(2),
                    fila.getString(3),
                    fila.getString(4),
                    fila.getString(5),
                    fila.getString(6),
                    fila.getString(7),
                    fila.getString(8),
                    fila.getString(9),
                    fila.getString(10),
                    fila.getString(11),
                    fila.getString(12),
                    fila.getString(13),
                    fila.getString(14),
                    fila.getString(15),
                    fila.getString(16),
                    fila.getString(17),
                    fila.getString(18),
                    fila.getString(19),
                    fila.getString(20),
                    fila.getString(21),
                    fila.getString(22),
                    fila.getString(23),
                    fila.getString(24),
                    fila.getString(25),
                    fila.getString(26),
                    fila.getString(27),
                    fila.getString(28),
                    fila.getString(29),
                    fila.getString(30),
                    fila.getString(31),
                    fila.getString(32),
                    fila.getString(33),
                    fila.getString(34),
                    fila.getString(35),
                    fila.getString(36),
                    fila.getString(37),
                    fila.getString(38),
                    fila.getString(39),
                    fila.getString(40),
                    fila.getString(41),
                    fila.getString(42),
                    fila.getString(43),
                    fila.getString(44),
                    fila.getString(45),
                    fila.getString(46),
                    fila.getString(47),
                    fila.getString(48),
                    fila.getString(49),
                    fila.getString(50),
                    fila.getString(51),
                    fila.getString(52),
                    fila.getString(53),
                    fila.getString(54),
                    fila.getString(55),
                    fila.getString(56),
                    fila.getString(57),
                    fila.getString(58),
                    fila.getString(59),
                    fila.getString(60),
                    fila.getString(61),
                    fila.getString(62),
                    fila.getString(63),
                    fila.getString(64),
                    fila.getString(65),
                    fila.getString(66),
                    fila.getString(67),
                    fila.getString(68),
                    fila.getString(69),
                    fila.getString(70),
                    fila.getString(71),
                    fila.getString(72),
                    fila.getString(73),
                    fila.getString(74),
                    fila.getString(75),
                    fila.getString(76),
                    fila.getString(77),
                    fila.getString(78),
                    fila.getString(79),
                    fila.getString(80),
                    fila.getString(81),
                    fila.getString(82),
                    fila.getString(83),
                    fila.getString(84),
                    fila.getString(85),
                    fila.getString(86),
                    fila.getString(87),
                    fila.getString(88),
                    fila.getString(89),
                    fila.getString(90),
                    fila.getString(91),
                    fila.getString(92),
                    fila.getString(93),
                    fila.getString(94),
                    fila.getString(95),
                    fila.getString(96),
                    fila.getString(97),
                    fila.getString(98),
                    fila.getString(99),
                    fila.getString(100),
                    fila.getString(101),
                    fila.getString(102),
                    fila.getString(103),
                    fila.getString(104),
                    fila.getString(105),
                    fila.getString(106),
                    fila.getString(107),
                    fila.getString(108),
                    fila.getString(109),
                    fila.getString(110),
                    fila.getString(111),
                    fila.getString(112),
                    fila.getString(113),
                    fila.getString(114),
                    fila.getString(115),
                    fila.getString(116),
                    fila.getString(117),
                    fila.getString(118),
                    fila.getString(119),
                    fila.getString(120),
                    fila.getString(121),
                    fila.getString(122),
                    fila.getString(123),
                    fila.getString(124),
                    fila.getString(125),
                    fila.getString(126),
                    fila.getString(127),
                    fila.getString(128),
                    fila.getString(129),
                    fila.getString(130),
                    fila.getString(131),
                    fila.getString(132),
                    fila.getString(133),
                    fila.getString(134),
                    fila.getString(135),
                    fila.getString(136),
                    fila.getString(137),
                    fila.getString(138),
                    fila.getString(139),
                    fila.getString(140),
                    fila.getString(141),
                    fila.getString(142),
                    fila.getString(143),
                    fila.getString(144),
                    fila.getString(145),
                    fila.getString(146),
                    fila.getString(147),
                    fila.getString(148),
                    fila.getString(149),
                    fila.getString(150),
                    fila.getString(151),
                    fila.getString(152),
                    fila.getString(153),
                    fila.getString(154),
                    fila.getString(155),
                    fila.getString(156),
                    fila.getString(157),
                    fila.getString(158),
                    fila.getString(159),
                    fila.getString(160),
                    fila.getString(161),
                    fila.getString(162),
                    fila.getString(163),
                    fila.getString(164),
                    fila.getString(165),
                    fila.getString(166),
                    fila.getString(167),
                    fila.getString(168),
                    fila.getString(169),
                    fila.getString(170),
                    fila.getString(171),
                    fila.getString(172),
                    fila.getString(173),
                    fila.getString(174),
                    fila.getString(175),
                    fila.getString(176),
                    fila.getString(177),
                    fila.getString(178),
                    fila.getString(179),
                    fila.getString(180),
                    fila.getString(181),
                    fila.getString(182),
                    fila.getString(183),
                    fila.getString(184),
                    fila.getString(185),
                    fila.getString(186),
                    fila.getString(187),
                    fila.getString(188),
                    fila.getString(189),
                    fila.getString(190),
                    fila.getString(191),
                    fila.getString(192),
                    fila.getString(193),
                    fila.getString(194),
                    fila.getString(195),
                    fila.getString(196),
                    fila.getString(197),
                    fila.getString(198),
                    fila.getString(199),
                    fila.getString(200),
                    fila.getString(201),
                    fila.getString(202),
                    fila.getString(203),
                    fila.getString(204),
                    fila.getString(205),
                    fila.getString(206),
                    fila.getString(207),
                    fila.getString(208),
                    fila.getString(209),
                    fila.getString(210),
                    fila.getString(211),
                    fila.getString(212));
                    //endregion


        }

        return Power;
    }

    public DCPower2 obtenerDcPower2_id(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        DCPower2 Power = null;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.DC_POWER, FilasDB.ColumnasDCPwer.ID_fORMATO, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","DC_Power");
            DatabaseUtils.dumpCursor(fila);

            //region power
            Power = new DCPower2(fila.getString(213),
                    fila.getString(214),
                    fila.getString(215),
                    fila.getString(216),
                    fila.getString(217),
                    fila.getString(218),
                    fila.getString(219),
                    fila.getString(220),
                    fila.getString(221),
                    fila.getString(222),
                    fila.getString(223),
                    fila.getString(224),
                    fila.getString(225),
                    fila.getString(226),
                    fila.getString(227),
                    fila.getString(228),
                    fila.getString(229),
                    fila.getString(230),
                    fila.getString(231),
                    fila.getString(232),
                    fila.getString(233),
                    fila.getString(234),
                    fila.getString(235),
                    fila.getString(236),
                    fila.getString(237),
                    fila.getString(238),
                    fila.getString(239),
                    fila.getString(240),
                    fila.getString(241),
                    fila.getString(242),
                    fila.getString(243),
                    fila.getString(244),
                    fila.getString(245),
                    fila.getString(246),
                    fila.getString(247),
                    fila.getString(248),
                    fila.getString(249),
                    fila.getString(250),
                    fila.getString(251),
                    fila.getString(252),
                    fila.getString(253),
                    fila.getString(254),
                    fila.getString(255),
                    fila.getString(256),
                    fila.getString(257),
                    fila.getString(258),
                    fila.getString(259),
                    fila.getString(260),
                    fila.getString(261),
                    fila.getString(262),
                    fila.getString(263),
                    fila.getString(264),
                    fila.getString(265),
                    fila.getString(266),
                    fila.getString(267),
                    fila.getString(268),
                    fila.getString(269),
                    fila.getString(270),
                    fila.getString(271),
                    fila.getString(272),
                    fila.getString(273),
                    fila.getString(274),
                    fila.getString(275),
                    fila.getString(276),
                    fila.getString(277),
                    fila.getString(278),
                    fila.getString(279),
                    fila.getString(280),
                    fila.getString(281),
                    fila.getString(282),
                    fila.getString(283),
                    fila.getString(284),
                    fila.getString(285),
                    fila.getString(286),
                    fila.getString(287),
                    fila.getString(288),
                    fila.getString(289),
                    fila.getString(290),
                    fila.getString(291),
                    fila.getString(292),
                    fila.getString(293),
                    fila.getString(294),
                    fila.getString(295),
                    fila.getString(296),
                    fila.getString(297));
            /*
             fila.getString(292),
                    fila.getString(293),
                    fila.getString(294),
                    fila.getString(295),
                    fila.getString(296),
                    fila.getString(297)
                     "",
                    "",
                    "",
                    "",
                    "",
                    ""
             */

            //endregion


        }

        return Power;
    }

    public SGarantias obtenerGarantia_id(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SGarantias item = new SGarantias();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.GARANTIAS, FilasDB.ColumnasGarantias.FolioFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Garantias");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setFolioFormato(fila.getString(2));
            item.setFolioPreTrabajo(fila.getString(3));
            item.setCliente(fila.getString(4));
            item.setDirección(fila.getString(5));
            item.setContacto(fila.getString(6));
            item.setTeléfono(fila.getString(7));
            item.setMail(fila.getString(8));
            item.setProyecto(fila.getString(9));
            item.setReferencia(fila.getString(10));
            item.setSR(fila.getString(11));
            item.setTASK(fila.getString(12));
            item.setTIPOSERVICIO(fila.getString(13));
            item.setFRECUENCIA(fila.getString(14));
            item.setNTAG(fila.getString(15));
            item.setMODELO(fila.getString(16));
            item.setNSERIE(fila.getString(17));
            item.setNSERIE2(fila.getString(18));
            item.setFECHAINICIO(fila.getString(19));
            item.setFECHAFIN(fila.getString(20));
            item.setTIPOGARANTIA(fila.getString(21));
            item.setFOLIOCCC(fila.getString(22));
            item.setFECHALOTE(fila.getString(23));
            item.setLOTE(fila.getString(24));
            item.setADUANA(fila.getString(25));
            item.setNUEVOSERIAL(fila.getString(26));
            item.setREPORTEFALLA(fila.getString(27));
            item.setACCIONCORRECTIVA(fila.getString(28));
            item.setCOMENTARIOS(fila.getString(29));
            item.setMATERIALESUTILIZADOS(fila.getString(30));
            item.setCANTIDAD1(fila.getString(31));
            item.setNPARTE1(fila.getString(32));
            item.setESPECIFICACION1(fila.getString(33));
            item.setCANTIDAD2(fila.getString(34));
            item.setNPARTE2(fila.getString(35));
            item.setESPECIFICACION2(fila.getString(36));
            item.setCANTIDAD3(fila.getString(37));
            item.setNPARTE3(fila.getString(38));
            item.setESPECIFICACION3(fila.getString(39));
            item.setCANTIDAD4(fila.getString(40));
            item.setNPARTE4(fila.getString(41));
            item.setESPECIFICACION4(fila.getString(42));
            item.setCANTIDAD5(fila.getString(43));
            item.setNPARTE5(fila.getString(44));
            item.setESPECIFICACION5(fila.getString(45));
            item.setCANTIDAD6(fila.getString(46));
            item.setNPARTE6(fila.getString(47));
            item.setESPECIFICACION6(fila.getString(48));
            item.setCANTIDAD7(fila.getString(49));
            item.setNPARTE7(fila.getString(50));
            item.setESPECIFICACION7(fila.getString(51));
            item.setCANTIDAD8(fila.getString(52));
            item.setNPARTE8(fila.getString(53));
            item.setESPECIFICACION8(fila.getString(54));
            item.setCANTIDAD9(fila.getString(55));
            item.setNPARTE9(fila.getString(56));
            item.setESPECIFICACION9(fila.getString(57));
            item.setEQUIPOMEDICION(fila.getString(58));
            item.setEQUIPO1(fila.getString(59));
            item.setNoID1(fila.getString(60));
            item.setFECHA1(fila.getString(61));
            item.setEQUIPO2(fila.getString(62));
            item.setNoID2(fila.getString(63));
            item.setFECHA2(fila.getString(64));
            item.setEQUIPO3(fila.getString(65));
            item.setNoID3(fila.getString(66));
            item.setFECHA3(fila.getString(67));
            item.setEQUIPO4(fila.getString(68));
            item.setNoID4(fila.getString(69));
            item.setFECHA4(fila.getString(70));
            item.setEQUIPO5(fila.getString(71));
            item.setNoID5(fila.getString(72));
            item.setFECHA5(fila.getString(73));
            item.setFOTOANTES1(fila.getString(74));
            item.setFOTODESPUES1(fila.getString(75));
            item.setFOTOANTES2(fila.getString(76));
            item.setFOTODESPUES2(fila.getString(77));
            item.setFOTOANTES3(fila.getString(78));
            item.setFOTODESPUES3(fila.getString(79));
            item.setFOTOANTES4(fila.getString(80));
            item.setFOTODESPUES4(fila.getString(81));
            item.setFOTOANTES5(fila.getString(82));
            item.setFOTODESPUES5(fila.getString(83));
            item.setFOTOANTES6(fila.getString(84));
            item.setFOTODESPUES6(fila.getString(85));
            item.setFIRMA1(fila.getString(86));
            item.setIMAGENFIRMA1(fila.getString(87));
            item.setFIRMA2(fila.getString(88));
            item.setIMAGENFIRMA2(fila.getString(89));
            item.setFIRMA13(fila.getString(90));
            item.setIMAGENFIRMA3(fila.getString(91));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(92));
            item.setcLIENTEFINAL_TELEFONO(fila.getString(93));
            item.setCLIENTEFINAL_CORREO(fila.getString(94));
            item.setAD_NOMBRE1(fila.getString(95));
            item.setAD_CORREO1(fila.getString(96));
            item.setAD_NOMBRE2(fila.getString(97));
            item.setAD_CORREO2(fila.getString(98));
            item.setAD_NOMBRE3(fila.getString(99));
            item.setAD_CORREO3(fila.getString(100));
            item.setAD_NOMBRE4(fila.getString(101));
            item.setAD_CORREO4(fila.getString(102));
            item.setAD_NOMBRE5(fila.getString(103));
            item.setAD_CORREO5(fila.getString(104));
            item.setGarantiaArchivo(fila.getString(105));


            //endregion

             }

        return item;
    }

    public Servicios obtenerServicio(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        Servicios item = new Servicios();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.SERVICIOS, FilasDB.ColumnasServicios.FolioFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Servicios");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setFolioFormato(fila.getString(2));
            item.setFolioPreTrabajo(fila.getString(3));
            item.setCliente(fila.getString(4));
            item.setDirección(fila.getString(5));
            item.setContacto(fila.getString(6));
            item.setTelefono(fila.getString(7));
            item.setMail(fila.getString(8));
            item.setProyecto(fila.getString(9));
            item.setReferencia(fila.getString(10));
            item.setSR(fila.getString(11));
            item.setTASK(fila.getString(12));
            item.setTIPOSERVICIO(fila.getString(13));
            item.setESPECICIFACION(fila.getString(14));
            item.setARRANCOVERTIV(fila.getString(15));
            item.setFRECUENCIA(fila.getString(16));
            item.setNTAG(fila.getString(17));
            item.setMODELO(fila.getString(18));
            item.setNSERIE(fila.getString(19));
            item.setNSERIE2(fila.getString(20));
            item.setFECHAINICIO(fila.getString(21));
            item.setFECHAFIN(fila.getString(22));
            item.setACTIVIDAD(fila.getString(23));
            item.setDIAGNOSTICO(fila.getString(24));
            item.setACCIONCORRECTIVA(fila.getString(25));
            item.setCOMENTARIOS(fila.getString(26));
            item.setMATERIALESUTILIZADOS(fila.getString(27));
            item.setCANTIDAD1(fila.getString(28));
            item.setNPARTE1(fila.getString(29));
            item.setESPECIFICACION1(fila.getString(30));
            item.setCANTIDAD2(fila.getString(31));
            item.setNPARTE2(fila.getString(32));
            item.setESPECIFICACION2(fila.getString(33));
            item.setCANTIDAD3(fila.getString(34));
            item.setNPARTE3(fila.getString(35));
            item.setESPECIFICACION3(fila.getString(36));
            item.setCANTIDAD4(fila.getString(37));
            item.setNPARTE4(fila.getString(38));
            item.setESPECIFICACION4(fila.getString(39));
            item.setCANTIDAD5(fila.getString(40));
            item.setNPARTE5(fila.getString(41));
            item.setESPECIFICACION5(fila.getString(42));
            item.setCANTIDAD6(fila.getString(43));
            item.setNPARTE6(fila.getString(44));
            item.setESPECIFICACION6(fila.getString(45));
            item.setCANTIDAD7(fila.getString(46));
            item.setNPARTE7(fila.getString(47));
            item.setESPECIFICACION7(fila.getString(48));
            item.setCANTIDAD8(fila.getString(49));
            item.setNPARTE8(fila.getString(50));
            item.setESPECIFICACION8(fila.getString(51));
            item.setCANTIDAD9(fila.getString(52));
            item.setNPARTE9(fila.getString(53));
            item.setESPECIFICACION9(fila.getString(54));
            item.setEQUIPOMEDICION(fila.getString(55));
            item.setEQUIPO1(fila.getString(56));
            item.setNoID1(fila.getString(57));
            item.setFECHA1(fila.getString(58));
            item.setEQUIPO2(fila.getString(59));
            item.setNoID2(fila.getString(60));
            item.setFECHA2(fila.getString(61));
            item.setEQUIPO3(fila.getString(62));
            item.setNoID3(fila.getString(63));
            item.setFECHA3(fila.getString(64));
            item.setEQUIPO4(fila.getString(65));
            item.setNoID4(fila.getString(66));
            item.setFECHA4(fila.getString(67));
            item.setEQUIPO5(fila.getString(68));
            item.setNoID5(fila.getString(69));
            item.setFECHA5(fila.getString(70));
            item.setFOTOANTES1(fila.getString(71));
            item.setFOTODESPUES1(fila.getString(72));
            item.setFOTOANTES2(fila.getString(73));
            item.setFOTODESPUES2(fila.getString(74));
            item.setFOTOANTES3(fila.getString(75));
            item.setFOTODESPUES3(fila.getString(76));
            item.setFOTOANTES4(fila.getString(77));
            item.setFOTODESPUES4(fila.getString(78));
            item.setFOTOANTES5(fila.getString(79));
            item.setFOTODESPUES5(fila.getString(80));
            item.setFOTOANTES6(fila.getString(81));
            item.setFOTODESPUES6(fila.getString(82));
            item.setFIRMA1(fila.getString(83));
            item.setIMAGENFIRMA1(fila.getString(84));
            item.setFIRMA2(fila.getString(85));
            item.setIMAGENFIRMA2(fila.getString(86));
            item.setFIRMA13(fila.getString(87));
            item.setIMAGENFIRMA3(fila.getString(88));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(89));
            item.setcLIENTEFINAL_TELEFONO(fila.getString(90));
            item.setCLIENTEFINAL_CORREO(fila.getString(91));
            item.setAD_NOMBRE1(fila.getString(92));
            item.setAD_CORREO1(fila.getString(93));
            item.setAD_NOMBRE2(fila.getString(94));
            item.setAD_CORREO2(fila.getString(95));
            item.setAD_NOMBRE3(fila.getString(96));
            item.setAD_CORREO3(fila.getString(97));
            item.setAD_NOMBRE4(fila.getString(98));
            item.setAD_CORREO4(fila.getString(99));
            item.setAD_NOMBRE5(fila.getString(100));
            item.setAD_CORREO5(fila.getString(101));

            //endregion

        }

        return item;
    }

    public Baterias obtenerBateriaF_id(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        Baterias baterias = new Baterias();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.BATERIAS, FilasDB.ColumnasBaterias.ID_fORMATO, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Baterias");
            DatabaseUtils.dumpCursor(fila);
            //region
            baterias.setGRAL_folioPT(fila.getString(3));
            baterias.setGRAL_cliente(fila.getString(4));
            baterias.setGRAL_direccion(fila.getString(5));
            baterias.setGRAL_contacto(fila.getString(6));
            baterias.setGRAL_telefono(fila.getString(7));
            baterias.setGRAL_mail(fila.getString(8));
            baterias.setGRAL_proyecto(fila.getString(9));
            baterias.setGRAL_eferencia(fila.getString(10));
            baterias.setGRAL_sr(fila.getString(11));
            baterias.setGRAL_task(fila.getString(12));
            baterias.setGRAL_tipoServ(fila.getString(13));
            baterias.setGRAL_tiposervOtro(fila.getString(14));
            baterias.setGRAL_freciencia(fila.getString(15));
            baterias.setGRAL_noTag(fila.getString(16));
            baterias.setGRAL_modelo(fila.getString(17));
            baterias.setGRAL_serie1(fila.getString(18));
            baterias.setGRAL_serie2(fila.getString(19));
            baterias.setGRAL_fecha1(fila.getString(20));
            baterias.setGRAL_fecha2(fila.getString(21));
            baterias.setVDC1(fila.getString(22));
            baterias.setVDC2(fila.getString(23));
            baterias.setVDC3(fila.getString(24));
            baterias.setVDC4(fila.getString(25));
            baterias.setVDC5(fila.getString(26));
            baterias.setVDC6(fila.getString(27));
            baterias.setVDC7(fila.getString(28));
            baterias.setVDC8(fila.getString(29));
            baterias.setVDC9(fila.getString(30));
            baterias.setVDC10(fila.getString(31));
            baterias.setVDC11(fila.getString(32));
            baterias.setVDC12(fila.getString(33));
            baterias.setVDC13(fila.getString(34));
            baterias.setVDC14(fila.getString(35));
            baterias.setVDC15(fila.getString(36));
            baterias.setVDC16(fila.getString(37));
            baterias.setVDC17(fila.getString(38));
            baterias.setVDC18(fila.getString(39));
            baterias.setVDC19(fila.getString(40));
            baterias.setVDC20(fila.getString(41));
            baterias.setVDC21(fila.getString(42));
            baterias.setVDC22(fila.getString(43));
            baterias.setVDC23(fila.getString(44));
            baterias.setVDC24(fila.getString(45));
            baterias.setVDC25(fila.getString(46));
            baterias.setVDC26(fila.getString(47));
            baterias.setVDC27(fila.getString(48));
            baterias.setVDC28(fila.getString(49));
            baterias.setVDC29(fila.getString(50));
            baterias.setVDC30(fila.getString(51));
            baterias.setVDC31(fila.getString(52));
            baterias.setVDC32(fila.getString(53));
            baterias.setVDC33(fila.getString(54));
            baterias.setVDC34(fila.getString(55));
            baterias.setVDC35(fila.getString(56));
            baterias.setVDC36(fila.getString(57));
            baterias.setVDC37(fila.getString(58));
            baterias.setVDC38(fila.getString(59));
            baterias.setVDC39(fila.getString(60));
            baterias.setVDC40(fila.getString(61));
            baterias.setVDC41(fila.getString(62));
            baterias.setVDC42(fila.getString(63));
            baterias.setVDC43(fila.getString(64));
            baterias.setVDC44(fila.getString(65));
            baterias.setVDC45(fila.getString(66));
            baterias.setVDC46(fila.getString(67));
            baterias.setVDC47(fila.getString(68));
            baterias.setVDC48(fila.getString(69));
            baterias.setVDC49(fila.getString(70));
            baterias.setVDC50(fila.getString(71));
            baterias.setVDC51(fila.getString(72));
            baterias.setVDC52(fila.getString(73));
            baterias.setVDC53(fila.getString(74));
            baterias.setVDC54(fila.getString(75));
            baterias.setVDC55(fila.getString(76));
            baterias.setVDC56(fila.getString(77));
            baterias.setVDC57(fila.getString(78));
            baterias.setVDC58(fila.getString(79));
            baterias.setVDC59(fila.getString(80));
            baterias.setVDC60(fila.getString(81));
            baterias.setVDC61(fila.getString(82));
            baterias.setVDC62(fila.getString(83));
            baterias.setVDC63(fila.getString(84));
            baterias.setVDC64(fila.getString(85));
            baterias.setVDC65(fila.getString(86));
            baterias.setVDC66(fila.getString(87));
            baterias.setVDC67(fila.getString(88));
            baterias.setVDC68(fila.getString(89));
            baterias.setVDC69(fila.getString(90));
            baterias.setVDC70(fila.getString(91));
            baterias.setVDC71(fila.getString(92));
            baterias.setVDC72(fila.getString(93));
            baterias.setVDC73(fila.getString(94));
            baterias.setVDC74(fila.getString(95));
            baterias.setVDC75(fila.getString(96));
            baterias.setVDC76(fila.getString(97));
            baterias.setVDC77(fila.getString(98));
            baterias.setVDC78(fila.getString(99));
            baterias.setVDC79(fila.getString(100));
            baterias.setVDC80(fila.getString(101));
            baterias.setVDC81(fila.getString(102));
            baterias.setVDC82(fila.getString(103));
            baterias.setVDC83(fila.getString(104));
            baterias.setVDC84(fila.getString(105));
            baterias.setVDC85(fila.getString(106));
            baterias.setVDC86(fila.getString(107));
            baterias.setVDC87(fila.getString(108));
            baterias.setVDC88(fila.getString(109));
            baterias.setVDC89(fila.getString(110));
            baterias.setVDC90(fila.getString(111));
            baterias.setVDC91(fila.getString(112));
            baterias.setVDC92(fila.getString(113));
            baterias.setVDC93(fila.getString(114));
            baterias.setVDC94(fila.getString(115));
            baterias.setVDC95(fila.getString(116));
            baterias.setVDC96(fila.getString(117));
            baterias.setVDC97(fila.getString(118));
            baterias.setVDC98(fila.getString(119));
            baterias.setVDC99(fila.getString(120));
            baterias.setVDC100(fila.getString(121));
            baterias.setVDC101(fila.getString(122));
            baterias.setVDC102(fila.getString(123));
            baterias.setVDC103(fila.getString(124));
            baterias.setVDC104(fila.getString(125));
            baterias.setVDC105(fila.getString(126));
            baterias.setVDC106(fila.getString(127));
            baterias.setVDC107(fila.getString(128));
            baterias.setVDC108(fila.getString(129));
            baterias.setVDC109(fila.getString(130));
            baterias.setVDC110(fila.getString(131));
            baterias.setVDC111(fila.getString(132));
            baterias.setVDC112(fila.getString(133));
            baterias.setVDC113(fila.getString(134));
            baterias.setVDC114(fila.getString(135));
            baterias.setVDC115(fila.getString(136));
            baterias.setVDC116(fila.getString(137));
            baterias.setVDC117(fila.getString(138));
            baterias.setVDC118(fila.getString(139));
            baterias.setVDC119(fila.getString(140));
            baterias.setVDC120(fila.getString(141));
            baterias.setVAC1(fila.getString(142));
            baterias.setVAC2(fila.getString(143));
            baterias.setVAC3(fila.getString(144));
            baterias.setVAC4(fila.getString(145));
            baterias.setVAC5(fila.getString(146));
            baterias.setVAC6(fila.getString(147));
            baterias.setVAC7(fila.getString(148));
            baterias.setVAC8(fila.getString(149));
            baterias.setVAC9(fila.getString(150));
            baterias.setVAC10(fila.getString(151));
            baterias.setVAC11(fila.getString(152));
            baterias.setVAC12(fila.getString(153));
            baterias.setVAC13(fila.getString(154));
            baterias.setVAC14(fila.getString(155));
            baterias.setVAC15(fila.getString(156));
            baterias.setVAC16(fila.getString(157));
            baterias.setVAC17(fila.getString(158));
            baterias.setVAC18(fila.getString(159));
            baterias.setVAC19(fila.getString(160));
            baterias.setVAC20(fila.getString(161));
            baterias.setVAC21(fila.getString(162));
            baterias.setVAC22(fila.getString(163));
            baterias.setVAC23(fila.getString(164));
            baterias.setVAC24(fila.getString(165));
            baterias.setVAC25(fila.getString(166));
            baterias.setVAC26(fila.getString(167));
            baterias.setVAC27(fila.getString(168));
            baterias.setVAC28(fila.getString(169));
            baterias.setVAC29(fila.getString(170));
            baterias.setVAC30(fila.getString(171));
            baterias.setVAC31(fila.getString(172));
            baterias.setVAC32(fila.getString(173));
            baterias.setVAC33(fila.getString(174));
            baterias.setVAC34(fila.getString(175));
            baterias.setVAC35(fila.getString(176));
            baterias.setVAC36(fila.getString(177));
            baterias.setVAC37(fila.getString(178));
            baterias.setVAC38(fila.getString(179));
            baterias.setVAC39(fila.getString(180));
            baterias.setVAC40(fila.getString(181));
            baterias.setVAC41(fila.getString(182));
            baterias.setVAC42(fila.getString(183));
            baterias.setVAC43(fila.getString(184));
            baterias.setVAC44(fila.getString(185));
            baterias.setVAC45(fila.getString(186));
            baterias.setVAC46(fila.getString(187));
            baterias.setVAC47(fila.getString(188));
            baterias.setVAC48(fila.getString(189));
            baterias.setVAC49(fila.getString(190));
            baterias.setVAC50(fila.getString(191));
            baterias.setVAC51(fila.getString(192));
            baterias.setVAC52(fila.getString(193));
            baterias.setVAC53(fila.getString(194));
            baterias.setVAC54(fila.getString(195));
            baterias.setVAC55(fila.getString(196));
            baterias.setVAC56(fila.getString(197));
            baterias.setVAC57(fila.getString(198));
            baterias.setVAC58(fila.getString(199));
            baterias.setVAC59(fila.getString(200));
            baterias.setVAC60(fila.getString(201));
            baterias.setVAC61(fila.getString(202));
            baterias.setVAC62(fila.getString(203));
            baterias.setVAC63(fila.getString(204));
            baterias.setVAC64(fila.getString(205));
            baterias.setVAC65(fila.getString(206));
            baterias.setVAC66(fila.getString(207));
            baterias.setVAC67(fila.getString(208));
            baterias.setVAC68(fila.getString(209));
            baterias.setVAC69(fila.getString(210));
            baterias.setVAC70(fila.getString(211));
            baterias.setVAC71(fila.getString(212));
            baterias.setVAC72(fila.getString(213));
            baterias.setVAC73(fila.getString(214));
            baterias.setVAC74(fila.getString(215));
            baterias.setVAC75(fila.getString(216));
            baterias.setVAC76(fila.getString(217));
            baterias.setVAC77(fila.getString(218));
            baterias.setVAC78(fila.getString(219));
            baterias.setVAC79(fila.getString(220));
            baterias.setVAC80(fila.getString(221));
            baterias.setVAC81(fila.getString(222));
            baterias.setVAC82(fila.getString(223));
            baterias.setVAC83(fila.getString(224));
            baterias.setVAC84(fila.getString(225));
            baterias.setVAC85(fila.getString(226));
            baterias.setVAC86(fila.getString(227));
            baterias.setVAC87(fila.getString(228));
            baterias.setVAC88(fila.getString(229));
            baterias.setVAC89(fila.getString(230));
            baterias.setVAC90(fila.getString(231));
            baterias.setVAC91(fila.getString(232));
            baterias.setVAC92(fila.getString(233));
            baterias.setVAC93(fila.getString(234));
            baterias.setVAC94(fila.getString(235));
            baterias.setVAC95(fila.getString(236));
            baterias.setVAC96(fila.getString(237));
            baterias.setVAC97(fila.getString(238));
            baterias.setVAC98(fila.getString(239));
            baterias.setVAC99(fila.getString(240));
            baterias.setVAC100(fila.getString(241));
            baterias.setVAC101(fila.getString(242));
            baterias.setVAC102(fila.getString(243));
            baterias.setVAC103(fila.getString(244));
            baterias.setVAC104(fila.getString(245));
            baterias.setVAC105(fila.getString(246));
            baterias.setVAC106(fila.getString(247));
            baterias.setVAC107(fila.getString(248));
            baterias.setVAC108(fila.getString(249));
            baterias.setVAC109(fila.getString(250));
            baterias.setVAC110(fila.getString(251));
            baterias.setVAC111(fila.getString(252));
            baterias.setVAC112(fila.getString(253));
            baterias.setVAC113(fila.getString(254));
            baterias.setVAC114(fila.getString(255));
            baterias.setVAC115(fila.getString(256));
            baterias.setVAC116(fila.getString(257));
            baterias.setVAC117(fila.getString(258));
            baterias.setVAC118(fila.getString(259));
            baterias.setVAC119(fila.getString(260));
            baterias.setVAC120(fila.getString(261));
            baterias.setModeloMarca(fila.getString(262));
            baterias.setAparenciaLimpieza(fila.getString(263));
            baterias.setJarrasCubiertasSellado(fila.getString(264));
            baterias.setTemperaturaBaterias(fila.getString(265));
            baterias.setTemperaturaAmbiente(fila.getString(266));
            baterias.setTorque(fila.getString(267));
            baterias.setTerminales(fila.getString(268));
            baterias.setCodigoFecha(fila.getString(269));
            baterias.setAñosServicio(fila.getString(270));
            baterias.setConectoresTornillos(fila.getString(271));
            baterias.setVoltajeFlotacionVDC(fila.getString(272));
            baterias.setCorrienteFlotacion(fila.getString(273));
            baterias.setCorrienteRizo(fila.getString(274));
            baterias.setVoltajeRizo(fila.getString(275));
            baterias.setCOMENT_cometarios(fila.getString(276));
            baterias.setMATE_cantidad1(fila.getString(277));
            baterias.setMATE_parte1(fila.getString(278));
            baterias.setMATE_especifica1(fila.getString(279));
            baterias.setMATE_cantidad2(fila.getString(280));
            baterias.setMATE_parte2(fila.getString(281));
            baterias.setMATE_especifica2(fila.getString(282));
            baterias.setMATE_cantidad3(fila.getString(283));
            baterias.setMATE_parte3(fila.getString(284));
            baterias.setMATE_especifica3(fila.getString(285));
            baterias.setMATE_cantidad4(fila.getString(286));
            baterias.setMATE_parte4(fila.getString(287));
            baterias.setMATE_especifica4(fila.getString(288));
            baterias.setMATE_cantidad5(fila.getString(289));
            baterias.setMATE_parte5(fila.getString(290));
            baterias.setMATE_especifica5(fila.getString(291));
            baterias.setMATE_cantidad6(fila.getString(292));
            baterias.setMATE_parte6(fila.getString(293));
            baterias.setMATE_especifica6(fila.getString(294));
            baterias.setMATE_cantidad7(fila.getString(295));
            baterias.setMATE_parte7(fila.getString(296));
            baterias.setMATE_especifica7(fila.getString(297));
            baterias.setMATE_cantidad8(fila.getString(298));
            baterias.setMATE_parte8(fila.getString(299));
            baterias.setMATE_especifica8(fila.getString(300));
            baterias.setMATE_equipo1(fila.getString(301));
            baterias.setMATE_nid1(fila.getString(302));
            baterias.setMATE_fecha1(fila.getString(303));
            baterias.setMATE_equipo2(fila.getString(304));
            baterias.setMATE_nid2(fila.getString(305));
            baterias.setMATE_fecha2(fila.getString(306));
            baterias.setMATE_equipo3(fila.getString(307));
            baterias.setMATE_nid3(fila.getString(308));
            baterias.setMATE_fecha3(fila.getString(309));
            baterias.setMATE_equipo4(fila.getString(310));
            baterias.setMATE_nid4(fila.getString(311));
            baterias.setMATE_fecha4(fila.getString(312));
            baterias.setMATE_equipo5(fila.getString(313));
            baterias.setMATE_nid5(fila.getString(314));
            baterias.setMATE_fecha5(fila.getString(315));
            baterias.setMATE_equipo6(fila.getString(316));
            baterias.setMATE_nid6(fila.getString(317));
            baterias.setMATE_fecha6(fila.getString(318));
            baterias.setFOTOS_A1(fila.getString(319));
            baterias.setFOTOS_A2(fila.getString(320));
            baterias.setFOTOS_A3(fila.getString(321));
            baterias.setFOTOS_A4(fila.getString(322));
            baterias.setFOTOS_A5(fila.getString(323));
            baterias.setFOTOS_A6(fila.getString(324));
            baterias.setFOTOS_D1(fila.getString(325));
            baterias.setFOTOS_D2(fila.getString(326));
            baterias.setFOTOS_D3(fila.getString(327));
            baterias.setFOTOS_D4(fila.getString(328));
            baterias.setFOTOS_D5(fila.getString(329));
            baterias.setFOTOS_D6(fila.getString(330));
            baterias.setFIRMA_NOMBRE1(fila.getString(331));
            baterias.setFIRMA_CARGO1(fila.getString(332));
            baterias.setFIRMA_IMG1(fila.getString(333));
            baterias.setFIRMA_NOMBRE2(fila.getString(334));
            baterias.setFIRMA_CARGO2(fila.getString(335));
            baterias.setFIRMA_IMG2(fila.getString(336));
            baterias.setFIRMA_NOMBRE3(fila.getString(337));
            baterias.setFIRMA_CARGO3(fila.getString(338));
            baterias.setFIRMA_IMG3(fila.getString(339));
            baterias.setFIRMA_EMPRESA(fila.getString(340));
            baterias.setFIRMA_TELEFONO(fila.getString(341));
            baterias.setFIRMA_CORREO(fila.getString(342));
            baterias.setADICI_NOMBRE1(fila.getString(343));
            baterias.setADICI_CORREO1(fila.getString(344));
            baterias.setADICI_NOMBRE2(fila.getString(345));
            baterias.setADICI_CORREO2(fila.getString(346));
            baterias.setADICI_NOMBRE3(fila.getString(347));
            baterias.setADICI_CORREO3(fila.getString(348));
            baterias.setADICI_NOMBRE4(fila.getString(349));
            baterias.setADICI_CORREO4(fila.getString(350));
            baterias.setADICI_NOMBRE5(fila.getString(351));
            baterias.setADICI_CORREO5(fila.getString(352));
            //endregion

        }

        return baterias;
    }

    public PreOrden obtenerPreOrden_id(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        PreOrden p_o = null;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.PRE_ORDEN, FilasDB.ColumnasPreOrden.ID_PreOrden, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","PreOrden");
            DatabaseUtils.dumpCursor(fila);
            p_o = new PreOrden(fila.getString(1),fila.getString(3),fila.getString(4),fila.getString(5),fila.getString(6),fila.getString(7),fila.getString(8),
                    fila.getString(9),fila.getString(10),fila.getString(11),fila.getString(12),fila.getString(13),fila.getString(14),fila.getString(15),
                    fila.getString(16),fila.getString(17),fila.getString(18),fila.getString(19),fila.getString(20),fila.getString(21),fila.getString(22),
                    fila.getString(23),fila.getString(24),fila.getString(25),fila.getString(26),fila.getString(27),fila.getString(28),fila.getString(29),
                    fila.getString(30),fila.getString(31),fila.getString(32),fila.getString(33),fila.getString(34),fila.getString(35),fila.getString(36),
                    fila.getString(37),fila.getString(38),fila.getString(39),fila.getString(40),fila.getString(41),fila.getString(42),fila.getString(43),
                    fila.getString(44),fila.getString(45),fila.getString(46),fila.getString(47),fila.getString(48),fila.getString(49),fila.getString(50),
                    fila.getString(51),fila.getString(52),fila.getString(53),fila.getString(54),fila.getString(55),fila.getString(56),fila.getString(57),
                    fila.getString(58),fila.getString(59),fila.getString(60),fila.getString(61),fila.getString(62),fila.getString(63),fila.getString(64),
                    fila.getString(65),fila.getString(66),fila.getString(67),fila.getString(68),fila.getString(69),fila.getString(70),fila.getString(71),
                    fila.getString(72),fila.getString(73),fila.getString(74),fila.getString(75),fila.getString(76),fila.getString(77),fila.getString(78),
                    fila.getString(79),fila.getString(80),fila.getString(81),fila.getString(82),fila.getString(83),fila.getString(84),fila.getString(85),
                    fila.getString(86),fila.getString(87),fila.getString(88),fila.getString(89),fila.getString(90),fila.getString(91),fila.getString(92),
                    fila.getString(93),fila.getString(94),fila.getString(95),fila.getString(96),fila.getString(97),fila.getString(98),fila.getString(99),
                    fila.getString(100),fila.getString(101),fila.getString(102),fila.getString(103),fila.getString(104),fila.getString(105),fila.getString(106),
                    fila.getString(107),fila.getString(108),fila.getString(109));
        }

        return p_o;
    }

    public UPS obtenerUPS(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        UPS item = new UPS();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.UPS, FilasDB.ColumnasUPS.FolioFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","UPS");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setIdFotmato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setFolioPreTrabajo(fila.getString(4));
            item.setFechaInicio(fila.getString(5));
            item.setFechaTermino(fila.getString(6));
            item.setIdCliente(fila.getString(7));
            item.setSRProyecto(fila.getString(8));
            item.setTASK(fila.getString(9));
            item.setContactoCliente(fila.getString(10));
            item.setTipoServicio(fila.getString(11));
            item.setTelefono(fila.getString(12));
            item.setFreecuencia(fila.getString(13));
            item.setEMail(fila.getString(14));
            item.setNoTag(fila.getString(15));
            item.setModeloEquipo(fila.getString(16));
            item.setNoSerie(fila.getString(17));
            item.setDireccionSitio(fila.getString(18));
            item.setVoltajeEntradaLLFaseA(fila.getString(19));
            item.setVoltajeEntradaLLFaseB(fila.getString(20));
            item.setVoltajeEntradaLLFaseC(fila.getString(21));
            item.setVoltajeEntradaLNFaseA(fila.getString(22));
            item.setVoltajeEntradaLNFaseB(fila.getString(23));
            item.setVoltajeEntradaLNFaseC(fila.getString(24));
            item.setCorrienteEntradaFaseA(fila.getString(25));
            item.setCorrienteEntradaFaseB(fila.getString(26));
            item.setCorrienteEntradaFaseC(fila.getString(27));
            item.setCorrienteFiltroEntradaFaseA(fila.getString(28));
            item.setCorrienteFiltroEntradaFaseB(fila.getString(29));
            item.setCorrienteFiltroEntradaFaseC(fila.getString(30));
            item.setVoltajeSalidaUPSLLFaseA(fila.getString(31));
            item.setVoltajeSalidaUPSLLFaseB(fila.getString(32));
            item.setVoltajeSalidaUPSLLFaseC(fila.getString(33));
            item.setVoltajeSalidaUPSLNFaseA(fila.getString(34));
            item.setVoltajeSalidaUPSLNFaseB(fila.getString(35));
            item.setVoltajeSalidaUPSLNFaseC(fila.getString(36));
            item.setVoltajeBypassFaseA(fila.getString(37));
            item.setVoltajeBypassFaseB(fila.getString(38));
            item.setVoltajeBypassFaseC(fila.getString(39));
            item.setCorrienteSalidaFaseA(fila.getString(40));
            item.setCorrienteSalidaFaseB(fila.getString(41));
            item.setCorrienteSalidaFaseC(fila.getString(42));
            item.setCorrienteFiltroSalidaFaseA(fila.getString(43));
            item.setVoltajeBusDCFaseA(fila.getString(44));
            item.setCorrienteBusDCFaseA(fila.getString(45));
            item.setVoltajeRizoACFaseA(fila.getString(46));
            item.setCorrienteRizoACFaseA(fila.getString(47));
            item.setSincronoscopioFaseA(fila.getString(48));
            item.setPotenciaKWFaseA(fila.getString(49));
            item.setPotenciaKVAFaseA(fila.getString(50));
            item.setFreqEntrada(fila.getString(51));
            item.setFreqUPSSalida(fila.getString(52));
            item.setPorcentajeCarga(fila.getString(53));
            item.setRectificador(fila.getString(54));
            item.setInversor(fila.getString(55));
            item.setSwitchEstatico(fila.getString(56));
            item.setTransformador(fila.getString(57));
            item.setCapacitoresInflados(fila.getString(58));
            item.setCapacitoresValvulas(fila.getString(59));
            item.setRevisarAlarmas(fila.getString(60));
            item.setInspeccionCables(fila.getString(61));
            item.setCalibracionMedidor(fila.getString(62));
            item.setAjusteEcualizacion(fila.getString(63));
            item.setPantallaInformeEstado(fila.getString(64));
            item.setLimpiezaUPS(fila.getString(65));
            item.setSnubberSobretemperatura(fila.getString(66));
            item.setAjusteLímites(fila.getString(67));
            item.setPantallaProcedimiento(fila.getString(68));
            item.setPantallaBaterías(fila.getString(69));
            item.setRevisionModulosDaños(fila.getString(70));
            item.setInspeccionGeneral(fila.getString(71));
            item.setRevSoftware(fila.getString(72));
            item.setComentariosRecomendaciones(fila.getString(73));
            item.setCantidad1(fila.getString(74));
            item.setCantidad2(fila.getString(75));
            item.setCantidad3(fila.getString(76));
            item.setCantidad4(fila.getString(77));
            item.setCantidad5(fila.getString(78));
            item.setCantidad6(fila.getString(79));
            item.setCantidad7(fila.getString(80));
            item.setCantidad8(fila.getString(81));
            item.setCantidad9(fila.getString(82));
            item.setNoParte1(fila.getString(83));
            item.setNoParte2(fila.getString(84));
            item.setNoParte3(fila.getString(85));
            item.setNoParte4(fila.getString(86));
            item.setNoParte5(fila.getString(87));
            item.setNoParte6(fila.getString(88));
            item.setNoParte7(fila.getString(89));
            item.setNoParte8(fila.getString(90));
            item.setNoParte9(fila.getString(91));
            item.setDescripcion1(fila.getString(92));
            item.setDescripcion2(fila.getString(93));
            item.setDescripcion3(fila.getString(94));
            item.setDescripcion4(fila.getString(95));
            item.setDescripcion5(fila.getString(96));
            item.setDescripcion6(fila.getString(97));
            item.setDescripcion7(fila.getString(98));
            item.setDescripcion8(fila.getString(99));
            item.setDescripcion9(fila.getString(100));
            item.setEquipo1(fila.getString(101));
            item.setEquipo2(fila.getString(102));
            item.setEquipo3(fila.getString(103));
            item.setEquipo4(fila.getString(104));
            item.setEquipo5(fila.getString(105));
            item.setEquipo6(fila.getString(106));
            item.setEquipo7(fila.getString(107));
            item.setEquipo8(fila.getString(108));
            item.setEquipo9(fila.getString(109));
            item.setNoId1(fila.getString(110));
            item.setNoId2(fila.getString(111));
            item.setNoId3(fila.getString(112));
            item.setNoId4(fila.getString(113));
            item.setNoId5(fila.getString(114));
            item.setNoId6(fila.getString(115));
            item.setNoId7(fila.getString(116));
            item.setNoId8(fila.getString(117));
            item.setNoId9(fila.getString(118));
            item.setFechaVencimiento1(fila.getString(119));
            item.setFechaVencimiento2(fila.getString(120));
            item.setFechaVencimiento3(fila.getString(121));
            item.setFechaVencimiento4(fila.getString(122));
            item.setFechaVencimiento5(fila.getString(123));
            item.setFechaVencimiento6(fila.getString(124));
            item.setFechaVencimiento7(fila.getString(125));
            item.setFechaVencimiento8(fila.getString(126));
            item.setFechaVencimiento9(fila.getString(127));
            item.setAntes1(fila.getString(128));
            item.setAntes2(fila.getString(129));
            item.setAntes3(fila.getString(130));
            item.setAntes4(fila.getString(131));
            item.setAntes5(fila.getString(132));
            item.setAntes6(fila.getString(133));
            item.setDespues1(fila.getString(134));
            item.setDespues2(fila.getString(135));
            item.setDespues3(fila.getString(136));
            item.setDespues4(fila.getString(137));
            item.setDespues5(fila.getString(138));
            item.setDespues6(fila.getString(139));
            item.setFirmaCliente(fila.getString(140));
            item.setFirmaVertiv(fila.getString(141));
            item.setFirmaClienteFinal(fila.getString(142));
            item.setNombreFirmaCliente(fila.getString(143));
            item.setNombreFirmaVertiv(fila.getString(144));
            item.setNombreFirmaClienteFinal(fila.getString(145));
            item.setAD_NOMBRE1(fila.getString(146));
            item.setAD_CORREO1(fila.getString(147));
            item.setAD_NOMBRE2(fila.getString(148));
            item.setAD_CORREO2(fila.getString(149));
            item.setAD_NOMBRE3(fila.getString(150));
            item.setAD_CORREO3(fila.getString(151));
            item.setAD_NOMBRE4(fila.getString(152));
            item.setAD_CORREO4(fila.getString(153));
            item.setAD_NOMBRE5(fila.getString(154));
            item.setAD_CORREO5(fila.getString(155));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(156));
            item.setCLIENTEFINAL_TELEFONO(fila.getString(157));
            item.setCLIENTEFINAL_CORREO(fila.getString(158));
            item.setNSERIE2(fila.getString(159));
            item.setReferrencia(fila.getString(160));

            //endregion

        }

        return item;
    }

    public ThermalManagagementS obtenerThermal(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        ThermalManagagementS item = new ThermalManagagementS();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.TERMAL, FilasDB.ColumnasThermal.IDFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","THERMAL");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setFolioPreTrabajo(fila.getString(4));
            item.setFechaInicio(fila.getString(5));
            item.setFechaTermino(fila.getString(6));
            item.setCliente(fila.getString(7));
            item.setSRProyecto(fila.getString(8));
            item.setTASK(fila.getString(9));
            item.setContactoCliente(fila.getString(10));
            item.setTipoServicio(fila.getString(11));
            item.setTelefono(fila.getString(12));
            item.setFreecuencia(fila.getString(13));
            item.setEMail(fila.getString(14));
            item.setNoTag(fila.getString(15));
            item.setModeloEquipo(fila.getString(16));
            item.setNoSerie(fila.getString(17));
            item.setDireccionSitio(fila.getString(18));
            item.setContacto(fila.getString(19));
            item.setProyecto(fila.getString(20));
            item.setReferencia(fila.getString(21));
            item.setTiposervicioEspecificacion(fila.getString(22));
            item.setGNoSerie2(fila.getString(23));
            item.setSETTEMP(fila.getString(24));
            item.setSENS(fila.getString(25));
            item.setSETHUM(fila.getString(26));
            item.setSENS2(fila.getString(27));
            item.setHITEMP(fila.getString(28));
            item.setLOTEMP(fila.getString(29));
            item.setHIHUM(fila.getString(30));
            item.setLOHUM(fila.getString(31));
            item.setINYECCION(fila.getString(32));
            item.setRETORNO(fila.getString(33));
            item.setTIPOMICROPROCESADOR(fila.getString(34));
            item.setMarcaUniMane(fila.getString(35));
            item.setTipoEvaporadorUniMane(fila.getString(36));
            item.setModeloUniMane(fila.getString(37));
            item.setVoltajeGeneralL1L2(fila.getString(38));
            item.setVoltajeGeneralL2L3(fila.getString(39));
            item.setVoltajeGeneralL3L1(fila.getString(40));
            item.setNoSerieUniMane(fila.getString(41));
            item.setNoResistencias(fila.getString(42));
            item.setEstadoFísico(fila.getString(43));
            item.setVerificación(fila.getString(44));
            item.setConductores(fila.getString(45));
            item.setContactores(fila.getString(46));
            item.setFusibles(fila.getString(47));
            item.setAmpResistencia1L1(fila.getString(48));
            item.setAmpResistencia1L2(fila.getString(49));
            item.setAmpResistencia2L1(fila.getString(50));
            item.setAmpResistencia2L2(fila.getString(51));
            item.setAmpResistencia3L1(fila.getString(52));
            item.setAmpResistencia3L2(fila.getString(53));
            item.setMotorMarca(fila.getString(54));
            item.setModelo(fila.getString(55));
            item.setVoltsPlaca(fila.getString(56));
            item.setPoleaMotor(fila.getString(57));
            item.setPoleaTurbina(fila.getString(58));
            item.setReqCambioBanda(fila.getString(59));
            item.setModeloBandas(fila.getString(60));
            item.setAmpMotorL1(fila.getString(61));
            item.setAmpMotorL2(fila.getString(62));
            item.setAmpMotorL3(fila.getString(63));
            item.setNumeroBandas(fila.getString(64));
            item.setChumacerasLubricadas(fila.getString(65));
            item.setAjustesPoleas(fila.getString(66));
            item.setRotacion(fila.getString(67));
            item.setVAC24(fila.getString(68));
            item.setVAC5(fila.getString(69));
            item.setObservacionesMicroprocesador(fila.getString(70));
            item.setHumificadorTipo(fila.getString(71));
            item.setCondiciones(fila.getString(72));
            item.setSensor(fila.getString(73));
            item.setSelenoideVolts(fila.getString(74));
            item.setNoLamparas(fila.getString(75));
            item.setLíneaAguaDrenaje(fila.getString(76));
            item.setAmpLineaL1(fila.getString(77));
            item.setAmpLineaL2(fila.getString(78));
            item.setAmpLineaL3(fila.getString(79));
            item.setMarca1(fila.getString(80));
            item.setModelo1(fila.getString(81));
            item.setNoSerie1(fila.getString(82));
            item.setMiraIndicadoraSeco1(fila.getString(83));
            item.setMiraIndicadoraHumedo1(fila.getString(84));
            item.setAceiteNivel1(fila.getString(85));
            item.setTempValvulaServ1(fila.getString(86));
            item.setTempValvulaExp1(fila.getString(87));
            item.setPresionBajaLbs1(fila.getString(88));
            item.setPresionBajaCorte1(fila.getString(89));
            item.setPresionAlta1(fila.getString(90));
            item.setAmpLíneaL11(fila.getString(91));
            item.setAmpLíneaL21(fila.getString(92));
            item.setAmpLíneaL31(fila.getString(93));
            item.setMarca2(fila.getString(94));
            item.setModelo2(fila.getString(95));
            item.setNoSerie2(fila.getString(96));
            item.setMiraIndicadoraSeco2(fila.getString(97));
            item.setMiraIndicadoraHumedo2(fila.getString(98));
            item.setAceiteNivel2(fila.getString(99));
            item.setTempValvulaServ2(fila.getString(100));
            item.setTempValvulaExp2(fila.getString(101));
            item.setPresionBajaLbs2(fila.getString(102));
            item.setPresionBajaCorte2(fila.getString(103));
            item.setPresionAlta2(fila.getString(104));
            item.setAmpLíneaL12(fila.getString(105));
            item.setAmpLíneaL22(fila.getString(106));
            item.setAmpLíneaL32(fila.getString(107));
            item.setMarcaConde(fila.getString(108));
            item.setModeloConde(fila.getString(109));
            item.setNoSerieConde(fila.getString(110));
            item.setMarcaMotores(fila.getString(111));
            item.setModeloMotVariable(fila.getString(112));
            item.setModeloMotConstante(fila.getString(113));
            item.setAmpsPlaca(fila.getString(114));
            item.setAmpsPlaca1(fila.getString(115));
            item.setVoltsPlaca1(fila.getString(116));
            item.setVoltajeGeneral(fila.getString(117));
            item.setL12(fila.getString(118));
            item.setL23(fila.getString(119));
            item.setL31(fila.getString(120));
            item.setAMotorUnoL1(fila.getString(121));
            item.setAMotorUnoL2(fila.getString(122));
            item.setAMotorUnoL3(fila.getString(123));
            item.setAMotorDosL1(fila.getString(124));
            item.setAMotorDosL2(fila.getString(125));
            item.setAMotorDosL3(fila.getString(126));
            item.setAMotorTres1(fila.getString(127));
            item.setAMotorTres2(fila.getString(128));
            item.setAMotorTres3(fila.getString(129));
            item.setAMotorCuatroL1(fila.getString(130));
            item.setAMotorCuatroL2(fila.getString(131));
            item.setAMotorCuatroL3(fila.getString(132));
            item.setLavadoSerpentines(fila.getString(133));
            item.setReaprieteTornilleríaGeneral(fila.getString(134));
            item.setRevisionFusibles(fila.getString(135));
            item.setRevisionContactores(fila.getString(136));
            item.setLavadoAspasProtecciones(fila.getString(137));
            item.setLimpiezaInternaExterna(fila.getString(138));
            item.setLimpiezaCharolaHumidificacion(fila.getString(139));
            item.setRevisarEstadoLampara(fila.getString(140));
            item.setRevisarSedimentacionMinera(fila.getString(141));
            item.setRevisarCondicionesBandas(fila.getString(142));
            item.setRevisarAmortiguadores(fila.getString(143));
            item.setRevisarLubricacionBaleros(fila.getString(144));
            item.setRevisarFlechaBaseMotor(fila.getString(145));
            item.setRevisarAbrazaderasSoportes(fila.getString(146));
            item.setCambiosFiltrosAire(fila.getString(147));
            item.setLavadoSerpentinesCom(fila.getString(148));
            item.setReaprieteTornilleríaGeneralCom(fila.getString(149));
            item.setRevisionFusiblesCom(fila.getString(150));
            item.setRevisionContactoresCom(fila.getString(151));
            item.setLavadoAspasProteccionesCom(fila.getString(152));
            item.setLimpiezaInternaExternaCom(fila.getString(153));
            item.setLimpiezaCharolaHumidificacionCom(fila.getString(154));
            item.setRevisarEstadoLamparaCom(fila.getString(155));
            item.setRevisarSedimentacionMineraCom(fila.getString(156));
            item.setRevisarCondicionesBandasCom(fila.getString(157));
            item.setRevisarAmortiguadoresCom(fila.getString(158));
            item.setRevisarLubricacionBalerosCom(fila.getString(159));
            item.setRevisarFlechaBaseMotorCom(fila.getString(160));
            item.setRevisarAbrazaderasSoportesCom(fila.getString(161));
            item.setCambiosFiltrosAireCom(fila.getString(162));
            item.setComentariosRecomendaciones(fila.getString(163));
            item.setCantidad1(fila.getString(164));
            item.setCantidad2(fila.getString(165));
            item.setCantidad3(fila.getString(166));
            item.setCantidad4(fila.getString(167));
            item.setCantidad5(fila.getString(168));
            item.setCantidad6(fila.getString(169));
            item.setCantidad7(fila.getString(170));
            item.setCantidad8(fila.getString(171));
            item.setCantidad9(fila.getString(172));
            item.setNoParte1(fila.getString(173));
            item.setNoParte2(fila.getString(174));
            item.setNoParte3(fila.getString(175));
            item.setNoParte4(fila.getString(176));
            item.setNoParte5(fila.getString(177));
            item.setNoParte6(fila.getString(178));
            item.setNoParte7(fila.getString(179));
            item.setNoParte8(fila.getString(180));
            item.setNoParte9(fila.getString(181));
            item.setDescripcion1(fila.getString(182));
            item.setDescripcion2(fila.getString(183));
            item.setDescripcion3(fila.getString(184));
            item.setDescripcion4(fila.getString(185));
            item.setDescripcion5(fila.getString(186));
            item.setDescripcion6(fila.getString(187));
            item.setDescripcion7(fila.getString(188));
            item.setDescripcion8(fila.getString(189));
            item.setDescripcion9(fila.getString(190));
            item.setEquipo1(fila.getString(191));
            item.setEquipo2(fila.getString(192));
            item.setEquipo3(fila.getString(193));
            item.setEquipo4(fila.getString(194));
            item.setEquipo5(fila.getString(195));
            item.setEquipo6(fila.getString(196));
            item.setEquipo7(fila.getString(197));
            item.setEquipo8(fila.getString(198));
            item.setEquipo9(fila.getString(199));
            item.setNoId1(fila.getString(200));
            item.setNoId2(fila.getString(201));
            item.setNoId3(fila.getString(202));
            item.setNoId4(fila.getString(203));
            item.setNoId5(fila.getString(204));
            item.setNoId6(fila.getString(205));
            item.setNoId7(fila.getString(206));
            item.setNoId8(fila.getString(207));
            item.setNoId9(fila.getString(208));
            item.setFechaVencimiento1(fila.getString(209));
            item.setFechaVencimiento2(fila.getString(210));
            item.setFechaVencimiento3(fila.getString(211));
            item.setFechaVencimiento4(fila.getString(212));
            item.setFechaVencimiento5(fila.getString(213));
            item.setFechaVencimiento6(fila.getString(214));
            item.setFechaVencimiento7(fila.getString(215));
            item.setFechaVencimiento8(fila.getString(216));
            item.setFechaVencimiento9(fila.getString(217));
            item.setAntes1(fila.getString(218));
            item.setAntes2(fila.getString(219));
            item.setAntes3(fila.getString(220));
            item.setAntes4(fila.getString(221));
            item.setAntes5(fila.getString(222));
            item.setAntes6(fila.getString(223));
            item.setDespues1(fila.getString(224));
            item.setDespues2(fila.getString(225));
            item.setDespues3(fila.getString(226));
            item.setDespues4(fila.getString(227));
            item.setDespues5(fila.getString(228));
            item.setDespues6(fila.getString(229));
            item.setFirmaCliente(fila.getString(230));
            item.setFirmaVertiv(fila.getString(231));
            item.setFirmaClienteFinal(fila.getString(232));
            item.setNombreFirmaCliente(fila.getString(233));
            item.setNombreFirmaVertiv(fila.getString(234));
            item.setNomnbreFirmaClienteFinal(fila.getString(235));
            item.setAD_NOMBRE1(fila.getString(236));
            item.setAD_CORREO1(fila.getString(237));
            item.setAD_NOMBRE2(fila.getString(238));
            item.setAD_CORREO2(fila.getString(239));
            item.setAD_NOMBRE3(fila.getString(240));
            item.setAD_CORREO3(fila.getString(241));
            item.setAD_NOMBRE4(fila.getString(242));
            item.setAD_CORREO4(fila.getString(243));
            item.setAD_NOMBRE5(fila.getString(244));
            item.setAD_CORREO5(fila.getString(245));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(246));
            item.setCLIENTEFINAL_TELEFONO(fila.getString(247));
            item.setCLIENTEFINAL_CORREO(fila.getString(248));
            item.setTipoServicioOtro(fila.getString(249));




            //endregion

        }

        return item;
    }

    public PDU obtenerPDU(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        PDU item = new PDU();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.PDU, FilasDB.ColumnasPDU.IDFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","PDU");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setFolioPreTrabajo(fila.getString(4));
            item.setFechaInicio(fila.getString(5));
            item.setFechaTermino(fila.getString(6));
            item.setIdCliente(fila.getString(7));
            item.setSRProyecto(fila.getString(8));
            item.setTASK(fila.getString(9));
            item.setContactoCliente(fila.getString(10));
            item.setTipoServicio(fila.getString(11));
            item.setTelefono(fila.getString(12));
            item.setFreecuencia(fila.getString(13));
            item.setEMail(fila.getString(14));
            item.setNoTag(fila.getString(15));
            item.setModeloEquipo(fila.getString(16));
            item.setNoSerie(fila.getString(17));
            item.setDireccionSitio(fila.getString(18));
            item.setRevisionEstadoTransformador(fila.getString(19));
            item.setRevisionConexionesTransformador(fila.getString(20));
            item.setRevisionCableadoPotencia(fila.getString(21));
            item.setRevisionTarjetaControl(fila.getString(22));
            item.setRevisionDisplay(fila.getString(23));
            item.setRevisionBotonesPanel(fila.getString(24));
            item.setVoltajeEAB(fila.getString(25));
            item.setVoltajeEBC(fila.getString(26));
            item.setVoltajeECA(fila.getString(27));
            item.setCorrienteEA(fila.getString(28));
            item.setCorrienteEB(fila.getString(29));
            item.setCorrienteEC(fila.getString(30));
            item.setTemperatura(fila.getString(31));
            item.setRotacion(fila.getString(32));
            item.setTaps(fila.getString(33));
            item.setVoltajeSAB(fila.getString(34));
            item.setVoltajeSBC(fila.getString(35));
            item.setVoltajeSCA(fila.getString(36));
            item.setVoltajeSAN(fila.getString(37));
            item.setVoltajeSBN(fila.getString(38));
            item.setVoltajeSCN(fila.getString(39));
            item.setCorrienteSAB(fila.getString(40));
            item.setCorrienteSBC(fila.getString(41));
            item.setCorrienteSCA(fila.getString(42));
            item.setCorrienteSAN(fila.getString(43));
            item.setCorrienteSBN(fila.getString(44));
            item.setCorrienteSCN(fila.getString(45));
            item.setVTHDA(fila.getString(46));
            item.setVTHDB(fila.getString(47));
            item.setVTHDC(fila.getString(48));
            item.setITHDA(fila.getString(49));
            item.setITHDB(fila.getString(50));
            item.setITHDC(fila.getString(51));
            item.setKFACTORA(fila.getString(52));
            item.setKFACTORB(fila.getString(53));
            item.setKFACTORC(fila.getString(54));
            item.setPEAKRMSA(fila.getString(55));
            item.setPEAKRMSB(fila.getString(56));
            item.setPEAKRMSC(fila.getString(57));
            item.setKVA(fila.getString(58));
            item.setKW(fila.getString(59));
            item.setFP(fila.getString(60));
            item.setPorcentajeCarga(fila.getString(61));
            item.setKWHR(fila.getString(62));
            item.setComentariosRecomendaciones(fila.getString(63));
            item.setCantidad1(fila.getString(64));
            item.setCantidad2(fila.getString(65));
            item.setCantidad3(fila.getString(66));
            item.setCantidad4(fila.getString(67));
            item.setCantidad5(fila.getString(68));
            item.setCantidad6(fila.getString(69));
            item.setCantidad7(fila.getString(70));
            item.setCantidad8(fila.getString(71));
            item.setCantidad9(fila.getString(72));
            item.setNoParte1(fila.getString(73));
            item.setNoParte2(fila.getString(74));
            item.setNoParte3(fila.getString(75));
            item.setNoParte4(fila.getString(76));
            item.setNoParte5(fila.getString(77));
            item.setNoParte6(fila.getString(78));
            item.setNoParte7(fila.getString(79));
            item.setNoParte8(fila.getString(80));
            item.setNoParte9(fila.getString(81));
            item.setDescripcion1(fila.getString(82));
            item.setDescripcion2(fila.getString(83));
            item.setDescripcion3(fila.getString(84));
            item.setDescripcion4(fila.getString(85));
            item.setDescripcion5(fila.getString(86));
            item.setDescripcion6(fila.getString(87));
            item.setDescripcion7(fila.getString(88));
            item.setDescripcion8(fila.getString(89));
            item.setDescripcion9(fila.getString(90));
            item.setEquipo1(fila.getString(91));
            item.setEquipo2(fila.getString(92));
            item.setEquipo3(fila.getString(93));
            item.setEquipo4(fila.getString(94));
            item.setEquipo5(fila.getString(95));
            item.setEquipo6(fila.getString(96));
            item.setEquipo7(fila.getString(97));
            item.setEquipo8(fila.getString(98));
            item.setEquipo9(fila.getString(99));
            item.setNoId1(fila.getString(100));
            item.setNoId2(fila.getString(101));
            item.setNoId3(fila.getString(102));
            item.setNoId4(fila.getString(103));
            item.setNoId5(fila.getString(104));
            item.setNoId6(fila.getString(105));
            item.setNoId7(fila.getString(106));
            item.setNoId8(fila.getString(107));
            item.setNoId9(fila.getString(108));
            item.setFechaVencimiento1(fila.getString(109));
            item.setFechaVencimiento2(fila.getString(110));
            item.setFechaVencimiento3(fila.getString(111));
            item.setFechaVencimiento4(fila.getString(112));
            item.setFechaVencimiento5(fila.getString(113));
            item.setFechaVencimiento6(fila.getString(114));
            item.setFechaVencimiento7(fila.getString(115));
            item.setFechaVencimiento8(fila.getString(116));
            item.setFechaVencimiento9(fila.getString(117));
            item.setAntes1(fila.getString(118));
            item.setAntes2(fila.getString(119));
            item.setAntes3(fila.getString(120));
            item.setAntes4(fila.getString(121));
            item.setAntes5(fila.getString(122));
            item.setAntes6(fila.getString(123));
            item.setDespues1(fila.getString(124));
            item.setDespues2(fila.getString(125));
            item.setDespues3(fila.getString(126));
            item.setDespues4(fila.getString(127));
            item.setDespues5(fila.getString(128));
            item.setDespues6(fila.getString(129));
            item.setFirmaCliente(fila.getString(130));
            item.setFirmaVertiv(fila.getString(131));
            item.setFirmaClienteFinal(fila.getString(132));
            item.setNombreFirmaCliente(fila.getString(133));
            item.setNombreFirmaVertiv(fila.getString(134));
            item.setNombreFirmaClienteFinal(fila.getString(135));
            item.setAD_NOMBRE1(fila.getString(136));
            item.setAD_CORREO1(fila.getString(137));
            item.setAD_NOMBRE2(fila.getString(138));
            item.setAD_CORREO2(fila.getString(139));
            item.setAD_NOMBRE3(fila.getString(140));
            item.setAD_CORREO3(fila.getString(141));
            item.setAD_NOMBRE4(fila.getString(142));
            item.setAD_CORREO4(fila.getString(143));
            item.setAD_NOMBRE5(fila.getString(144));
            item.setAD_CORREO5(fila.getString(145));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(146));
            item.setCLIENTEFINAL_TELEFONO(fila.getString(147));
            item.setCLIENTEFINAL_CORREO(fila.getString(148));
            item.setProyecto(fila.getString(149));
            item.setReferencia(fila.getString(150));
            item.setTipoServicioEspecif(fila.getString(151));
            item.setSerie2(fila.getString(152));
            //endregion

        }

        return item;
    }

    public STS2 obtenerSTS2(String Key_vertiv) {
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        STS2 item = new STS2();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s'",
                BaseDatos.Tablas.STS2, FilasDB.ColumnasSTS2.IDFormato, Key_vertiv);
        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","STS2");
            DatabaseUtils.dumpCursor(fila);
            //region
            item.setIDFormato(fila.getString(2));
            item.setIdUsuario(fila.getString(3));
            item.setFolioPreTrabajo(fila.getString(4));
            item.setFechaInicio(fila.getString(5));
            item.setFechaTermino(fila.getString(6));
            item.setIdCliente(fila.getString(7));
            item.setSRProyecto(fila.getString(8));
            item.setTASK(fila.getString(9));
            item.setContactoCliente(fila.getString(10));
            item.setTipoServicio(fila.getString(11));
            item.setTelefono(fila.getString(12));
            item.setFreecuencia(fila.getString(13));
            item.setEMail(fila.getString(14));
            item.setNoTag(fila.getString(15));
            item.setModeloEquipo(fila.getString(16));
            item.setNoSerie(fila.getString(17));
            item.setDireccionSitio(fila.getString(18));
            item.setRevisionCableadoPotencia(fila.getString(19));
            item.setRevisionConexiones(fila.getString(20));
            item.setRevisionComponentesPotencia(fila.getString(21));
            item.setRevisionSCR(fila.getString(22));
            item.setRevisarHistorialAlarmas(fila.getString(23));
            item.setRevisionTarjetas(fila.getString(24));
            item.setRevisionPanel(fila.getString(25));
            item.setRevisionCapacitoresPotencia(fila.getString(26));
            item.setRevisionFuenteAlimentacion(fila.getString(27));
            item.setRevisionPoints(fila.getString(28));
            item.setVoltajePreAB(fila.getString(29));
            item.setVoltajePreBC(fila.getString(30));
            item.setVoltajePreCA(fila.getString(31));
            item.setCorrientePreA(fila.getString(32));
            item.setCorrientePreB(fila.getString(33));
            item.setCorrientePreC(fila.getString(34));
            item.setVoltajeEmeAB(fila.getString(35));
            item.setVoltajeEmeBC(fila.getString(36));
            item.setVoltajeEmeCA(fila.getString(37));
            item.setCorrienteEmeA(fila.getString(38));
            item.setCorrienteEmeB(fila.getString(39));
            item.setCorrienteEmeC(fila.getString(40));
            item.setTemperaturaSCR(fila.getString(41));
            item.setNoTransferencias(fila.getString(42));
            item.setLimpiezaGeneral(fila.getString(43));
            item.setAjusteHorarios(fila.getString(44));
            item.setA48VDCTP1(fila.getString(45));
            item.setA24VDCTP5(fila.getString(46));
            item.setA74VDCTP7(fila.getString(47));
            item.setA5VDCTP8(fila.getString(48));
            item.setA33VDCTP6(fila.getString(49));
            item.setB24VDCTP5(fila.getString(50));
            item.setB74VDCTP7(fila.getString(51));
            item.setB5VDCTP8(fila.getString(52));
            item.setKVA(fila.getString(53));
            item.setKW(fila.getString(54));
            item.setCargaFuenteA(fila.getString(55));
            item.setCargaFuenteB(fila.getString(56));
            item.setComentariosRecomendaciones(fila.getString(57));
            item.setCantidad1(fila.getString(58));
            item.setCantidad2(fila.getString(59));
            item.setCantidad3(fila.getString(60));
            item.setCantidad4(fila.getString(61));
            item.setCantidad5(fila.getString(62));
            item.setCantidad6(fila.getString(63));
            item.setCantidad7(fila.getString(64));
            item.setCantidad8(fila.getString(65));
            item.setCantidad9(fila.getString(66));
            item.setNoParte1(fila.getString(67));
            item.setNoParte2(fila.getString(68));
            item.setNoParte3(fila.getString(69));
            item.setNoParte4(fila.getString(70));
            item.setNoParte5(fila.getString(71));
            item.setNoParte6(fila.getString(72));
            item.setNoParte7(fila.getString(73));
            item.setNoParte8(fila.getString(74));
            item.setNoParte9(fila.getString(75));
            item.setDescripcion1(fila.getString(76));
            item.setDescripcion2(fila.getString(77));
            item.setDescripcion3(fila.getString(78));
            item.setDescripcion4(fila.getString(79));
            item.setDescripcion5(fila.getString(80));
            item.setDescripcion6(fila.getString(81));
            item.setDescripcion7(fila.getString(82));
            item.setDescripcion8(fila.getString(83));
            item.setDescripcion9(fila.getString(84));
            item.setEquipo1(fila.getString(85));
            item.setEquipo2(fila.getString(86));
            item.setEquipo3(fila.getString(87));
            item.setEquipo4(fila.getString(88));
            item.setEquipo5(fila.getString(89));
            item.setEquipo6(fila.getString(90));
            item.setEquipo7(fila.getString(91));
            item.setEquipo8(fila.getString(92));
            item.setEquipo9(fila.getString(93));
            item.setNoId1(fila.getString(94));
            item.setNoId2(fila.getString(95));
            item.setNoId3(fila.getString(96));
            item.setNoId4(fila.getString(97));
            item.setNoId5(fila.getString(98));
            item.setNoId6(fila.getString(99));
            item.setNoId7(fila.getString(100));
            item.setNoId8(fila.getString(101));
            item.setNoId9(fila.getString(102));
            item.setFechaVencimiento1(fila.getString(103));
            item.setFechaVencimiento2(fila.getString(104));
            item.setFechaVencimiento3(fila.getString(105));
            item.setFechaVencimiento4(fila.getString(106));
            item.setFechaVencimiento5(fila.getString(107));
            item.setFechaVencimiento6(fila.getString(108));
            item.setFechaVencimiento7(fila.getString(109));
            item.setFechaVencimiento8(fila.getString(110));
            item.setFechaVencimiento9(fila.getString(111));
            item.setAntes1(fila.getString(112));
            item.setAntes2(fila.getString(113));
            item.setAntes3(fila.getString(114));
            item.setAntes4(fila.getString(115));
            item.setAntes5(fila.getString(116));
            item.setAntes6(fila.getString(117));
            item.setDespues1(fila.getString(118));
            item.setDespues2(fila.getString(119));
            item.setDespues3(fila.getString(120));
            item.setDespues4(fila.getString(121));
            item.setDespues5(fila.getString(122));
            item.setDespues6(fila.getString(123));
            item.setFirmaCliente(fila.getString(124));
            item.setFirmaVertiv(fila.getString(125));
            item.setFirmaClienteFinal(fila.getString(126));
            item.setNombreFirmaCliente(fila.getString(127));
            item.setNombreFirmaVertiv(fila.getString(128));
            item.setNombreFirmaClienteFinal(fila.getString(129));
            item.setAD_NOMBRE1(fila.getString(130));
            item.setAD_CORREO1(fila.getString(131));
            item.setAD_NOMBRE2(fila.getString(132));
            item.setAD_CORREO2(fila.getString(133));
            item.setAD_NOMBRE3(fila.getString(134));
            item.setAD_CORREO3(fila.getString(135));
            item.setAD_NOMBRE4(fila.getString(136));
            item.setAD_CORREO4(fila.getString(137));
            item.setAD_NOMBRE5(fila.getString(138));
            item.setAD_CORREO5(fila.getString(139));
            item.setCLIENTEFINAL_EMPRESA(fila.getString(140));
            item.setCLIENTEFINAL_TELEFONO(fila.getString(141));
            item.setCLIENTEFINAL_CORREO(fila.getString(142));
            item.setProyecto(fila.getString(143));
            item.setReferencia(fila.getString(144));
            item.setTipoServicioEspecif(fila.getString(145));
            item.setSerie2(fila.getString(146));
            //endregion

        }

        return item;
    }

    public List<MensajesCls> obtenerListaEMsajes() {
        ArrayList<MensajesCls> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s order by %s desc",
                BaseDatos.Tablas.MENSAJES, FilasDB.ColumnasMensajes.fecha);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                MensajesCls item = new MensajesCls();
                item.setIdMensaje(fila.getString(0));
                item.setMensaje(fila.getString(1) + "\n" + fila.getString(2));
                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Mansajes Sistema");
            DatabaseUtils.dumpCursor(fila);
        }

        return lista;
    }

    public List<CatAsignaCliente> obtenerTaskClieteL(String Cliente) {
        ArrayList<CatAsignaCliente> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s'",
                BaseDatos.Tablas.TASK,FilasDB.ColumnasCatalogoTask.Install_Party_Name,Cliente);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            Log.d("Tabla","obtenerTaskClieteL");
            DatabaseUtils.dumpCursor(fila);
            do{
                CatAsignaCliente item = new CatAsignaCliente();

                item.setIdCtlAsignaciones(fila.getString(1));
                item.setTask_Assignee(fila.getString(2));
                item.setSr_Nbr(fila.getString(3));
                item.setSr_Problem_Type(fila.getString(4));
                item.setItem_Number(fila.getString(5));
                item.setSerial_Number(fila.getString(6));
                item.setInstall_Party_Name(fila.getString(7));
                item.setInstall_Party_Site_Nbr(fila.getString(8));
                item.setTask_Status(fila.getString(9));
                item.setTask_Nbr(fila.getString(10));
                item.setTask_Start_Planned(fila.getString(11));
                item.setContract_Nbr(fila.getString(12));
                item.setCustomer(fila.getString(13));
                item.setSite(fila.getString(14));
                item.setAdress(fila.getString(15));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);

        }

        return lista;
    }

    public List<CatAsignaCliente> obtenerTask() {
        ArrayList<CatAsignaCliente> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.TASK);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                CatAsignaCliente item = new CatAsignaCliente();

                item.setIdCtlAsignaciones(fila.getString(1));
                item.setTask_Assignee(fila.getString(2));
                item.setSr_Nbr(fila.getString(3));
                item.setSr_Problem_Type(fila.getString(4));
                item.setItem_Number(fila.getString(5));
                item.setSerial_Number(fila.getString(6));
                item.setInstall_Party_Name(fila.getString(7));
                item.setInstall_Party_Site_Nbr(fila.getString(8));
                item.setTask_Status(fila.getString(9));
                item.setTask_Nbr(fila.getString(10));
                item.setTask_Start_Planned(fila.getString(11));
                item.setContract_Nbr(fila.getString(12));
                item.setCustomer(fila.getString(13));
                item.setSite(fila.getString(14));
                item.setAdress(fila.getString(15));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Baterias");
            DatabaseUtils.dumpCursor(fila);
        }

        return lista;
    }

    public CatAsignaCliente obtenerTaskId(String id_SR) {
        CatAsignaCliente item = new CatAsignaCliente();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s = '%s'",
                BaseDatos.Tablas.TASK, FilasDB.ColumnasCatalogoTask.Sr_Nbr,id_SR);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                item.setIdCtlAsignaciones(fila.getString(1));
                item.setTask_Assignee(fila.getString(2));
                item.setSr_Nbr(fila.getString(3));
                item.setSr_Problem_Type(fila.getString(4));
                item.setItem_Number(fila.getString(5));
                item.setSerial_Number(fila.getString(6));
                item.setInstall_Party_Name(fila.getString(7));
                item.setInstall_Party_Site_Nbr(fila.getString(8));
                item.setTask_Status(fila.getString(9));
                item.setTask_Nbr(fila.getString(10));
                item.setTask_Start_Planned(fila.getString(11));
                item.setContract_Nbr(fila.getString(12));
                item.setCustomer(fila.getString(13));
                item.setSite(fila.getString(14));
                item.setAdress(fila.getString(15));
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Taks ID");
            DatabaseUtils.dumpCursor(fila);
        }

        return item;
    }

    public List<CatCliente> obtenerClientes() {
        ArrayList<CatCliente> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.CLIENTEs);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                CatCliente item = new CatCliente();
                item.setIdClientes(fila.getString(0));
                item.setNombreCliente(fila.getString(1));
                item.setNombreSitio(fila.getString(2));
                item.setDireccion(fila.getString(3));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Clientes");
            DatabaseUtils.dumpCursor(fila);
        }
        CatCliente bestel = new CatCliente();
        bestel.setNombreCliente("BESTEL");
        bestel.setNombreSitio("ESPECIFICAR");

        lista.add(bestel);


        return lista;
    }

    public List<CatFolios> obtenerFoliosPT() {
        ArrayList<CatFolios> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s order by %s desc",
                BaseDatos.Tablas.CAT_FOLIOS_PRET, FilasDB.ColumnasFOliosPretrabajo.FolioPreTrabajo);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                CatFolios item = new CatFolios();

                item.setUsuario(fila.getString(0));
                        item.setCliente(fila.getString(1));
                        item.setGEN_SR(fila.getString(2));
                        item.setGEN_TASK(fila.getString(3));
                        item.setGEN_PROYECTO(fila.getString(4));
                        item.setFolioPreTrabajo(fila.getString(5));
                        item.setNombreSitio(fila.getString(6));
                        item.setDireccionSitio(fila.getString(7));
                        item.setLiderGrupoCuadrilla(fila.getString(8));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Folios PT");
            DatabaseUtils.dumpCursor(fila);
        }

        return lista;
    }

    public List<CatFolios> obtenerFoliosPTCliente(String cliente) {
        ArrayList<CatFolios> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        if(cliente.length()<=1){
            lista = (ArrayList<CatFolios>) obtenerFoliosPT();
        }else {

            SQLiteDatabase db = baseDatos.getReadableDatabase();
            String sql = String.format("SELECT * FROM %s where %s='%s' order by %s desc",
                    BaseDatos.Tablas.CAT_FOLIOS_PRET, FilasDB.ColumnasFOliosPretrabajo.Cliente, cliente,
                    FilasDB.ColumnasFOliosPretrabajo.FolioPreTrabajo);

            Cursor fila = db.rawQuery(sql, null);
            if (fila.moveToFirst()) {
                do {
                    CatFolios item = new CatFolios();

                    item.setUsuario(fila.getString(0));
                    item.setCliente(fila.getString(1));
                    item.setGEN_SR(fila.getString(2));
                    item.setGEN_TASK(fila.getString(3));
                    item.setGEN_PROYECTO(fila.getString(4));
                    item.setFolioPreTrabajo(fila.getString(5));
                    item.setNombreSitio(fila.getString(6));
                    item.setDireccionSitio(fila.getString(7));
                    item.setLiderGrupoCuadrilla(fila.getString(8));

                    lista.add(item);
                }
                while (fila.moveToNext());
                // p_o =  new PreOrden(fila.getString(3),);
                Log.d("Tabla", "Folios PT");
                DatabaseUtils.dumpCursor(fila);
            }
        }
        return lista;
    }

    public CatFolios obtenerFolioPT(String folioPT) {
        CatFolios item = new CatFolios();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s'",
                BaseDatos.Tablas.CAT_FOLIOS_PRET,FilasDB.ColumnasFOliosPretrabajo.FolioPreTrabajo,folioPT);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
                item.setUsuario(fila.getString(0));
                item.setCliente(fila.getString(1));
                item.setGEN_SR(fila.getString(2));
                item.setGEN_TASK(fila.getString(3));
                item.setGEN_PROYECTO(fila.getString(4));
                item.setFolioPreTrabajo(fila.getString(5));
                item.setNombreSitio(fila.getString(6));
                item.setDireccionSitio(fila.getString(7));
                item.setLiderGrupoCuadrilla(fila.getString(8));
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Folios PT");
            DatabaseUtils.dumpCursor(fila);
        }

        return item;
    }

    public List<CatCliente> obtenerSRs() {
        ArrayList<CatCliente> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.CLIENTEs);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                CatCliente item = new CatCliente();
                item.setIdClientes(fila.getString(0));
                item.setNombreCliente(fila.getString(1));
                item.setNombreSitio(fila.getString(2));
                item.setDireccion(fila.getString(3));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Clientes");
            DatabaseUtils.dumpCursor(fila);
        }
        CatCliente bestel = new CatCliente();
        bestel.setNombreCliente("BESTEL");
        bestel.setNombreSitio("ESPECIFICAR");

        lista.add(bestel);


        return lista;
    }

    public List<CatBestel> obtenerDireccionesBestel() {
        ArrayList<CatBestel> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.BESTEL);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                CatBestel item = new CatBestel();
                 item.setIdBestel(fila.getString(0));
                 item.setIdRegion(fila.getString(1));
                 item.setIdEstado(fila.getString(2));
                 item.setClasificacion(fila.getString(3));
                 item.setNombreSitio(fila.getString(4));
                 item.setDireccion(fila.getString(5));
                 item.setCliente(fila.getString(6));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Direcciones Bestel");
            DatabaseUtils.dumpCursor(fila);
        }


        return lista;
    }

    public List<searchCliente> obtenerClientesLista() {
        ArrayList<searchCliente> lista = new ArrayList<>();
        lista = listabestel();
        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();

        SQLiteDatabase db2 = baseDatos.getReadableDatabase();
        String sql2 = String.format("SELECT DISTINCT %s  FROM %s ",
                FilasDB.ColumnasClientes.NombreCliente, BaseDatos.Tablas.CLIENTEs);

        Cursor fila2 = db2.rawQuery(sql2, null);
        if (fila2.moveToFirst()) {
            Log.d("cLientes","Direcciones Bestel");
            DatabaseUtils.dumpCursor(fila2);
            do{
                searchCliente item = new searchCliente();
                item.setCLiente(fila2.getString(0));
                item.setBase("1");
                lista.add(item);
            }
            while (fila2.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Direcciones Clientes");
            DatabaseUtils.dumpCursor(fila2);
        }




        //---------------------------------
        return lista;
    }

    public ArrayList<searchCliente> listabestel(){

        ArrayList<searchCliente> lista = new ArrayList<>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT DISTINCT %s FROM %s ", FilasDB.ColumnasDireccionesBestel.Cliente,
                BaseDatos.Tablas.BESTEL);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                searchCliente item = new searchCliente();
                item.setCLiente(fila.getString(0));
                item.setBase("2");

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Direcciones Bestel");
            DatabaseUtils.dumpCursor(fila);
        }
        return lista;

    }

    public List<searchSitio> obtenerSitiosLista(String base, String cliente) {
        ArrayList<searchSitio> lista = new ArrayList<>();

        //listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        if (base.equals("2")) {
            SQLiteDatabase db = baseDatos.getReadableDatabase();
            String sql = String.format("SELECT * FROM %s where %s = '%s'",
                    BaseDatos.Tablas.BESTEL, FilasDB.ColumnasDireccionesBestel.Cliente, cliente);

            Cursor fila = db.rawQuery(sql, null);
            if (fila.moveToFirst()) {
                do {
                    searchSitio item = new searchSitio();
                    item.setSitio(fila.getString(4));
                    item.setDireccion(fila.getString(5));

                    lista.add(item);
                }
                while (fila.moveToNext());
            }
        }
        if(base.equals("1")) {
            SQLiteDatabase db2 = baseDatos.getReadableDatabase();
            String sql2 = String.format("SELECT * FROM %s where %s = '%s'",
                    BaseDatos.Tablas.CLIENTEs, FilasDB.ColumnasClientes.NombreCliente,cliente);

            Cursor fila2 = db2.rawQuery(sql2, null);
            if (fila2.moveToFirst()) {
                do {
                    searchSitio item = new searchSitio();
                    item.setSitio(fila2.getString(2));
                    item.setDireccion(fila2.getString(3));

                    lista.add(item);

                }
                while (fila2.moveToNext());
            }

        }


        return lista;
    }

    public List<spinnerDosCampos> obtenerRegiones() {
        ArrayList<spinnerDosCampos> lista = new ArrayList<>();

         SQLiteDatabase db = baseDatos.getReadableDatabase();
            String sql = String.format("SELECT * FROM %s ",
                    BaseDatos.Tablas.REGIONES);
        spinnerDosCampos item0 = new spinnerDosCampos();
        item0.setCampo1("nada");
        item0.setCampo2("");
        lista.add(item0);
            Cursor fila = db.rawQuery(sql, null);
            if (fila.moveToFirst()) {
                do {
                    spinnerDosCampos item = new spinnerDosCampos();
                    item.setCampo1(fila.getString(0));
                    item.setCampo2(fila.getString(1));

                    lista.add(item);
                }
                while (fila.moveToNext());
            }
        return lista;
    }

    public List<spinnerDosCampos> obtenerEstados(String idRegion) {
        ArrayList<spinnerDosCampos> lista = new ArrayList<>();
        spinnerDosCampos item0 = new spinnerDosCampos();
        item0.setCampo1("nada");
        item0.setCampo2("");
        lista.add(item0);

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s = '%s'",
                BaseDatos.Tablas.ESTADOS, FilasDB.ColumnasEstados.IdRegion, idRegion);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                spinnerDosCampos item = new spinnerDosCampos();
                item.setCampo1(fila.getString(0));
                item.setCampo2(fila.getString(2));
                item.setCampo3(fila.getString(1));

                lista.add(item);
            }
            while (fila.moveToNext());
        }
        return lista;
    }

    public List<spinnerDosCampos> obtenerTipo(String idRegion, String idEstado) {
        ArrayList<spinnerDosCampos> lista = new ArrayList<>();
        spinnerDosCampos item0 = new spinnerDosCampos();
        item0.setCampo1("nada");
        item0.setCampo2("");
        lista.add(item0);
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT DISTINCT %s FROM %s where %s = '%s' and %s='%s'",
                FilasDB.ColumnasDireccionesBestel.Clasificacion,BaseDatos.Tablas.BESTEL,
                FilasDB.ColumnasDireccionesBestel.IdRegion,idRegion,
                FilasDB.ColumnasDireccionesBestel.IdEstado,idEstado);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                spinnerDosCampos item = new spinnerDosCampos();
                item.setCampo1("");
                item.setCampo2(fila.getString(0));
                lista.add(item);
            }
            while (fila.moveToNext());
        }
        return lista;
    }

    public List<spinnerDosCampos> obtenerSitiosBestel(String idRegion, String idEstado, String Tipo) {
        ArrayList<spinnerDosCampos> lista = new ArrayList<>();
        spinnerDosCampos item0 = new spinnerDosCampos();
        item0.setCampo1("nada");
        item0.setCampo2("");
        lista.add(item0);
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT %s, %s FROM %s where %s = '%s' and %s='%s' and %s='%s'",

                FilasDB.ColumnasDireccionesBestel.NombreSitio,FilasDB.ColumnasDireccionesBestel.Direccion,BaseDatos.Tablas.BESTEL,
                FilasDB.ColumnasDireccionesBestel.IdRegion,idRegion,
                FilasDB.ColumnasDireccionesBestel.IdEstado,idEstado,
                FilasDB.ColumnasDireccionesBestel.Clasificacion,Tipo);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                spinnerDosCampos item = new spinnerDosCampos();
                item.setCampo1("");
                item.setCampo2(fila.getString(0));
                item.setCampo3(fila.getString(1));
                //item.setCampo3(fila.getString(1));
                lista.add(item);
            }
            while (fila.moveToNext());
        }
        return lista;
    }

    public String obtenerIDCliente(String cliente, String sitio, String direccion) {
        String idCliente="";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT %s FROM %s where %s = '%s' and %s='%s' and %s='%s'",

                FilasDB.ColumnasClientes.IdClientes,BaseDatos.Tablas.CLIENTEs,
                FilasDB.ColumnasClientes.NombreCliente,cliente,
                FilasDB.ColumnasClientes.NombreSitio,sitio,
                FilasDB.ColumnasClientes.Direccion,direccion);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                spinnerDosCampos item = new spinnerDosCampos();
                item.setCampo1("");
                idCliente = fila.getString(0);
            }
            while (fila.moveToNext());
        }
        return idCliente;
    }

    public void consultarCLientes(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.CLIENTEs);

        Cursor fila = db.rawQuery(sql, null);
        Log.d("Tabla","Direcciones Clientes");
        DatabaseUtils.dumpCursor(fila);
    }

    public List<spinnerDosCampos> obtenerSitioTipo(String idTipo) {
        ArrayList<spinnerDosCampos> lista = new ArrayList<>();

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s = '%s'",
                BaseDatos.Tablas.ESTADOS, FilasDB.ColumnasEstados.IdRegion, idTipo);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                spinnerDosCampos item = new spinnerDosCampos();
                item.setCampo1(fila.getString(0));
                item.setCampo2(fila.getString(2));
                item.setCampo3(fila.getString(1));

                lista.add(item);
            }
            while (fila.moveToNext());
        }
        return lista;
    }

    public ArrayList<Integer> formatosEnProceso(){
        ArrayList<Integer> lista = new ArrayList<>();
        int _0 = 0,_1 = 0,_2 = 0,_3 = 0,_4 = 0,_5 = 0,_6 = 0,_7 = 0,_8 = 0,_9 = 0,_10 = 0,_11 = 0;

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


        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s ='1' ",
                BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.ESTATUS);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                if(fila.getString(1).equals("0")){_0 = _0 +1;}
                if(fila.getString(1).equals("1")){_1=_1+1;}
                if(fila.getString(1).equals("2")){_2=_2+1;}
                if(fila.getString(1).equals("3")){_3=_3+1;}
                if(fila.getString(1).equals("4")){_4=_4+1;}
                if(fila.getString(1).equals("5")){_5= _5+1;}
                if(fila.getString(1).equals("6")){_6=_6+1;}
                if(fila.getString(1).equals("7")){_7=_7+1;}
                if(fila.getString(1).equals("8")){_8=_8+1;}
                if(fila.getString(1).equals("9")){_9=_9+1;}
                if(fila.getString(1).equals("10")){_10=_10+1;}
                if(fila.getString(1).equals("11")){_11=_11+1;}
            }
            while (fila.moveToNext());



            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Formatos en Proceso");
            DatabaseUtils.dumpCursor(fila);
        }


        lista.add(_0);
        lista.add(_1);
        lista.add(_2);
        lista.add(_3);
        lista.add(_4);
        lista.add(_5);
        lista.add(_6);
        lista.add(_7);
        lista.add(_8);
        lista.add(_9);
        lista.add(_10);
        lista.add(_11);

        return lista;
    }

    public ArrayList<Integer> formatosTerminados(){
        ArrayList<Integer> lista = new ArrayList<>();
        int _0 = 0,_1 = 0,_2 = 0,_3 = 0,_4 = 0,_5 = 0,_6 = 0,_7 = 0,_8 = 0,_9 = 0,_10 = 0,_11 = 0;

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


        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s ='0' ",
                BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.ESTATUS);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                if(fila.getString(1).equals("0")){_0 = _0 +1;}
                if(fila.getString(1).equals("1")){_1=_1+1;}
                if(fila.getString(1).equals("2")){_2=_2+1;}
                if(fila.getString(1).equals("3")){_3=_3+1;}
                if(fila.getString(1).equals("4")){_4=_4+1;}
                if(fila.getString(1).equals("5")){_5=_5+1;}
                if(fila.getString(1).equals("6")){_6=_6+1;}
                if(fila.getString(1).equals("7")){_7=_7+1;}
                if(fila.getString(1).equals("8")){_8=_8+1;}
                if(fila.getString(1).equals("9")){_9=_9+1;}
                if(fila.getString(1).equals("10")){_10=_10+1;}
                if(fila.getString(1).equals("11")){_11=_11+1;}
            }
            while (fila.moveToNext());



            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Formatos Terminados");
            DatabaseUtils.dumpCursor(fila);
        }


        lista.add(_0);
        lista.add(_1);
        lista.add(_2);
        lista.add(_3);
        lista.add(_4);
        lista.add(_5);
        lista.add(_6);
        lista.add(_7);
        lista.add(_8);
        lista.add(_9);
        lista.add(_10);
        lista.add(_11);

        return lista;
    }

    public Cursor formatosTerminadosSegundoplano(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s ='0' ",
                BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.ESTATUS);
        Cursor fila = db.rawQuery(sql, null);

        return fila;
    }


    public List<DatGeneralesF> formatosEnProcesoLista(String status, String idFormato){
        ArrayList<DatGeneralesF> lista = new ArrayList<>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = "";
        if(!idFormato.equals("Servicios")) {
             sql = String.format("SELECT * FROM %s where %s ='%s' and %s='%s' ",
                    BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.ESTATUS, status,
                    FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO, idFormato);
        }else{
            sql = String.format("SELECT * FROM %s where %s ='%s' and %s <> '0' and %s <> 1 ",
                    BaseDatos.Tablas.CONTROL_FORMATOS, FilasDB.ColumnasFormatosControl.ESTATUS, status,
                    FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO,FilasDB.ColumnasFormatosControl.ID_TIPO_FORMATO);
        }

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do{
                DatGeneralesF item = new DatGeneralesF();
                item.setID_FORMATO(fila.getString(0));
                item.setID_TIPO_FORMATO(fila.getString(1));
                item.setESTATUS(fila.getString(2));
                item.setFECHA_MODIFICACION(fila.getString(3));
                item.setFOLIO_PRETRABAJO(fila.getString(4));
                item.setFECHA_INICIO(fila.getString(5));
                item.setCLIENTE(fila.getString(6));
                item.setSR(fila.getString(7));
                item.setTASK(fila.getString(8));
                item.setITEM(fila.getString(9));
                item.setSERIE(fila.getString(10));

                lista.add(item);
            }
            while (fila.moveToNext());
            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Formatos");
            DatabaseUtils.dumpCursor(fila);
        }

        return lista;
    }

    public FotosFormato ObtenerFoto(String folio, String Imagen){

        FotosFormato item = new FotosFormato();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s ='%s' and %s ='%s' ",
                BaseDatos.Tablas.imagenes, FilasDB.ColumnasImagenes.Id,folio, FilasDB.ColumnasImagenes.Formato,Imagen);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            item.setId(fila.getString(0));
            item.setAntesFoto1(fila.getString(1));
            item.setAntesFoto2(fila.getString(2));
            item.setAntesFoto3(fila.getString(3));
            item.setAntesFoto4(fila.getString(4));
            item.setAntesFoto5(fila.getString(5));
            item.setAntesFoto6(fila.getString(6));
            item.setDespuesFoto1(fila.getString(7));
            item.setDespuesFoto2(fila.getString(8));
            item.setDespuesFoto3(fila.getString(9));
            item.setDespuesFoto4(fila.getString(10));
            item.setDespuesFoto5(fila.getString(11));
            item.setDespuesFoto6(fila.getString(12));
            item.setAntes1(fila.getString(13));
            item.setAntes2(fila.getString(14));
            item.setAntes3(fila.getString(15));
            item.setAntes4(fila.getString(16));
            item.setAntes5(fila.getString(17));
            item.setAntes6(fila.getString(18));
            item.setDespues1(fila.getString(19));
            item.setDespues2(fila.getString(20));
            item.setDespues3(fila.getString(21));
            item.setDespues4(fila.getString(22));
            item.setDespues5(fila.getString(23));
            item.setDespues6(fila.getString(24));
            item.setGarantiaArchivo(fila.getString(25))	;
            item.setFormato(fila.getString(26));
            item.setINSPEC_PERMISO_ARCHIVO(fila.getString(27));


            // p_o =  new PreOrden(fila.getString(3),);
            Log.d("Tabla","Fotos Errores");
            DatabaseUtils.dumpCursor(fila);
        }

        return  item;
    }

    public String ObtenerMensajes(){

        String item = "";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT COUNT(*) FROM %s",
                BaseDatos.Tablas.imagenes);

        Cursor fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            item = fila.getString(0);
            Log.d("Numero Errores","Fotos Errores");
            DatabaseUtils.dumpCursor(fila);
        }

        return  item;
    }

    //endregion

    //region OPERACIONES UPDATE
    public boolean CambioSesion(UsuarioD usu, String estatus){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasSesion.SESION_ACTIVA,estatus);

        String whereClause = String.format("%s=? ", FilasDB.ColumnasSesion.ID_User);
        String[] whereArgs = {usu.getID_USER()};

        int resultado = db.update(BaseDatos.Tablas.SESION, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean Cambiocontraseña(UsuarioD usu){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasUsuario.CONTRASEÑA,
                usu.getCONTRASEÑA());

        String whereClause = String.format("%s=? ", FilasDB.ColumnasUsuario.ID_USER);
        String[] whereArgs = {usu.getID_USER()};

        int resultado = db.update(BaseDatos.Tablas.USUARIO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public void actualizarFechaSinc(){
        SQLiteDatabase db1 = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ",
                BaseDatos.Tablas.ACTUALIZACIONES);
        Cursor fila = db1.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            SQLiteDatabase db3 = baseDatos.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put(FilasDB.ColumnasFechasActualizaciones.Fecha,dateFormat2.format(new Date()));
            //String whereClause = String.format("%s=? ", FilasDB.ColumnasSesion.ID_User);
            //String[] whereArgs = {usu.getID_USER()};
            int resultado = db3.update(BaseDatos.Tablas.ACTUALIZACIONES, valores, null, null);
        }else{
            SQLiteDatabase db2 = baseDatos.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(FilasDB.ColumnasFechasActualizaciones.Fecha,dateFormat2.format(new Date()));
            db2.insertOrThrow(BaseDatos.Tablas.ACTUALIZACIONES, null, valores);
        }
    }

    public boolean guardarPreOrden(PreOrden preorden){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasPreOrden.GEN_SR,preorden.getGEN_SR());
        valores.put(FilasDB.ColumnasPreOrden.GEN_TASK,preorden.getGEN_TASK());
        valores.put(FilasDB.ColumnasPreOrden.GEN_CLIENTE,preorden.getGEN_CLIENTE());
        valores.put(FilasDB.ColumnasPreOrden.GEN_SITIO,preorden.getGEN_SITIO());//GEN_SITIO";
        valores.put(FilasDB.ColumnasPreOrden.GEN_DIRECCION,preorden.getGEN_DIRECCION());//GEN_DIRECCION";
        valores.put(FilasDB.ColumnasPreOrden.GEN_REFERENCIA,preorden.getGEN_REFERENCIA());//GEN_REFERENCIA";
        valores.put(FilasDB.ColumnasPreOrden.GEN_FECHA,preorden.getGEN_FECHA());//GEN_FECHA";
        valores.put(FilasDB.ColumnasPreOrden.GEN_USUARIO_FINAL,preorden.getGEN_USUARIO_FINAL());//GEN_USUARIO_FINAL";
        valores.put(FilasDB.ColumnasPreOrden.GEN_LIDER_GRUPO,preorden.getGEN_LIDER_GRUPO());//GEN_LIDER_GRUPO";
        valores.put(FilasDB.ColumnasPreOrden.ACTV_DESCRIPCION_ACTIVIDADES,preorden.getACTV_DESCRIPCION_ACTIVIDADES());//ACTV_DESCRIPCION_ACTIVIDADES";
        valores.put(FilasDB.ColumnasPreOrden.HERRAM_DESCRIPCION_EHM,preorden.getHERRAM_DESCRIPCION_EHM());//HERRAM_DESCRIPCION_EHM";
        valores.put(FilasDB.ColumnasPreOrden.HERRAM_HOJAS_SEGURIDAD,preorden.getHERRAM_HOJAS_SEGURIDAD());//HERRAM_HOJAS_SEGURIDAD";
        valores.put(FilasDB.ColumnasPreOrden.HERRAM_DELIMITACION_AT,preorden.getHERRAM_DELIMITACION_AT());//HERRAM_DELIMITACION_AT";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_PARTICULAS,preorden.getRIESGO_PARTICULAS());//RIESGO_PARTICULAS";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_ATRAPAMIENTO,preorden.getRIESGO_ATRAPAMIENTO());//RIESGO_ATRAPAMIENTO";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_GOLPES,preorden.getRIESGO_GOLPES());//RIESGO_GOLPES";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_QUEMADURAS,preorden.getRIESGO_QUEMADURAS());//RIESGO_QUEMADURAS";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_CAIDA_MATE,preorden.getRIESGO_CAIDA_MATE());//RIESGO_CAIDA_MATE";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_CAIDA_MISMO_NIVEL,preorden.getRIESGO_CAIDA_MISMO_NIVEL());//RIESGO_CAIDA_MISMO_NIVEL";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_CAIDA_DIST_NIVEL,preorden.getRIESGO_CAIDA_DIST_NIVEL());//RIESGO_CAIDA_DIST_NIVEL";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_LIMPIEZA_DEFI,preorden.getRIESGO_LIMPIEZA_DEFI());//RIESGO_LIMPIEZA_DEFI";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_OTRO_PERSONAL,preorden.getRIESGO_OTRO_PERSONAL());//RIESGO_OTRO_PERSONAL";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_CHOQUE_ELECTRICO,preorden.getRIESGO_CHOQUE_ELECTRICO());//RIESGO_CHOQUE_ELECTRICO";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_ARCO_ELECT,preorden.getRIESGO_ARCO_ELECT());//RIESGO_ARCO_ELECT";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_FUEGO,preorden.getRIESGO_FUEGO());//RIESGO_FUEGO";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_EXPO_RUIDO,preorden.getRIESGO_EXPO_RUIDO());//RIESGO_EXPO_RUIDO";
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_EXP_VIBRA,preorden.getRIESGO_EXP_VIBRA());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_FATIGA_VISUAL,preorden.getRIESGO_FATIGA_VISUAL());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_TEMPERATURAS,preorden.getRIESGO_TEMPERATURAS());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_DEFI_OXIGENO,preorden.getRIESGO_DEFI_OXIGENO());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_GASES,preorden.getRIESGO_GASES());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_POLVO,preorden.getRIESGO_POLVO());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_SOBRE_ESFUERZO,preorden.getRIESGO_SOBRE_ESFUERZO());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_QUIMICOS,preorden.getRIESGO_QUIMICOS());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_RUIDO,preorden.getRIESGO_RUIDO());
        valores.put(FilasDB.ColumnasPreOrden.RIESGO_OTRO,preorden.getRIESGO_OTRO());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_DISPO_MECANICA,preorden.getPREVEN_DISPO_MECANICA());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_SUST_QUIMICOS,preorden.getPREVEN_SUST_QUIMICOS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_AISLAR_RUIDO,preorden.getPREVEN_AISLAR_RUIDO());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_PROTECTORES_MAQUINAS,preorden.getPREVEN_PROTECTORES_MAQUINAS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_PLATA_ANDAMIOS,preorden.getPREVEN_PLATA_ANDAMIOS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_SIS_PNTS_ANCLAJE,preorden.getPREVEN_SIS_PNTS_ANCLAJE());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_ILUMI_ART,preorden.getPREVEN_ILUMI_ART());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_DISYUNTOR,preorden.getPREVEN_DISYUNTOR());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_SIST_PUESTA_TIERRA,preorden.getPREVEN_SIST_PUESTA_TIERRA());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_ORDEN_LIMPIEZA,preorden.getPREVEN_ORDEN_LIMPIEZA());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_AREA_TRABAJO,preorden.getPREVEN_AREA_TRABAJO());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_BE_FUENTES_ENERGIA,preorden.getPREVEN_BE_FUENTES_ENERGIA());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_MUROS_DERRAME,preorden.getPREVEN_MUROS_DERRAME());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_PERMISOS,preorden.getPREVEN_PERMISOS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_INSTRUCTIVOS,preorden.getPREVEN_INSTRUCTIVOS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_SUPERVISION,preorden.getPREVEN_SUPERVISION());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_HERRAMI_AISLADAS,preorden.getPREVEN_HERRAMI_AISLADAS());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_EPP,preorden.getPREVEN_EPP());
        valores.put(FilasDB.ColumnasPreOrden.PREVEN_OTRO,preorden.getPREVEN_OTRO());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_CASCO,preorden.getEPP_BASICO_CASCO());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_GAFAS,preorden.getEPP_BASICO_GAFAS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_TAPONES,preorden.getEPP_BASICO_TAPONES());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_ZAPATOS,preorden.getEPP_BASICO_ZAPATOS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_GUANTES,preorden.getEPP_BASICO_GUANTES());
        valores.put(FilasDB.ColumnasPreOrden.EPP_BASICO_BARBIQUEJO,preorden.getEPP_BASICO_BARBIQUEJO());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_GAFAS,preorden.getEPP_ELECTICO_GAFAS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_CASCO,preorden.getEPP_ELECTICO_CASCO());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_ZAPATOS,preorden.getEPP_ELECTICO_ZAPATOS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_GUANTES,preorden.getEPP_ELECTICO_GUANTES());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_SOBREGUANTE,preorden.getEPP_ELECTICO_SOBREGUANTE());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_CARETA,preorden.getEPP_ELECTICO_CARETA());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_BALACLAVA,preorden.getEPP_ELECTICO_BALACLAVA());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_TRAJE,preorden.getEPP_ELECTICO_TRAJE());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_PROTECTORES_AUDI,preorden.getEPP_ELECTICO_PROTECTORES_AUDI());
        valores.put(FilasDB.ColumnasPreOrden.EPP_ELECTICO_MANGAS,preorden.getEPP_ELECTICO_MANGAS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_OTROS_PROTECCION_CAIDAS,preorden.getEPP_OTROS_PROTECCION_CAIDAS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_OTROS_PROTECCION_RESPITA,preorden.getEPP_OTROS_PROTECCION_RESPITA());
        valores.put(FilasDB.ColumnasPreOrden.EPP_OTROS_PROTECCION_SOLDAD,preorden.getEPP_OTROS_PROTECCION_SOLDAD());
        valores.put(FilasDB.ColumnasPreOrden.EPP_OTROS_PROTECCION_QUIMICOS,preorden.getEPP_OTROS_PROTECCION_QUIMICOS());
        valores.put(FilasDB.ColumnasPreOrden.EPP_OTROS_ADICIONALES,preorden.getEPP_OTROS_ADICIONALES());
        valores.put(FilasDB.ColumnasPreOrden.EMERG_EMERGENCIAS,preorden.getEMERG_EMERGENCIAS());
        valores.put(FilasDB.ColumnasPreOrden.EMERG_SUPERVISOR_VERTIV,preorden.getEMERG_SUPERVISOR_VERTIV());
        valores.put(FilasDB.ColumnasPreOrden.EMERG_HOSPITAL,preorden.getEMERG_HOSPITAL());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_CONDIC_OPTIMAS,preorden.getINSPEC_CONDIC_OPTIMAS());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_CONDIC_OPTIMAS_NO,preorden.getINSPEC_CONDIC_OPTIMAS_NO());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE,preorden.getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_ACTIVIDADES,preorden.getINSPEC_ACTIVIDADES());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_PERMISO_ARCHIVO,preorden.getINSPEC_PERMISO_ARCHIVO());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_TRABAJOS_ALTURA,preorden.getINSPEC_TRABAJOS_ALTURA());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_CONDIC_INSEGURAS,preorden.getINSPEC_CONDIC_INSEGURAS());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_CAUSAS_ACCIDENTES,preorden.getINSPEC_CAUSAS_ACCIDENTES());
        valores.put(FilasDB.ColumnasPreOrden.INSPEC_MEDIDAS_CORRECTIVAS,preorden.getINSPEC_MEDIDAS_CORRECTIVAS());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_1_NOMBRE,preorden.getFIRMA_1_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_1_CARGO,preorden.getFIRMA_1_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_1_IMAGEN,preorden.getFIRMA_1_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_2_NOMBRE,preorden.getFIRMA_2_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_2_CARGO,preorden.getFIRMA_2_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_2_IMAGEN,preorden.getFIRMA_2_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_3_NOMBRE,preorden.getFIRMA_3_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_3_CARGO,preorden.getFIRMA_3_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_3_IMAGEN,preorden.getFIRMA_3_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_4_NOMBRE,preorden.getFIRMA_4_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_4_CARGO,preorden.getFIRMA_4_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_4_IMAGEN,preorden.getFIRMA_4_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_5_NOMBRE,preorden.getFIRMA_5_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_5_CARGO,preorden.getFIRMA_5_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_5_IMAGEN,preorden.getFIRMA_5_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_6_NOMBRE,preorden.getFIRMA_6_NOMBRE());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_6_CARGO,preorden.getFIRMA_6_CARGO());
        valores.put(FilasDB.ColumnasPreOrden.FIRMA_6_IMAGEN,preorden.getFIRMA_6_IMAGEN());
        valores.put(FilasDB.ColumnasPreOrden.GEN_PROYECTO,preorden.getGEN_PROYECTO());
//endregion
        String whereClause = String.format("%s=? ", FilasDB.ColumnasPreOrden.ID_PreOrden);
        String[] whereArgs = {preorden.getFOLIO()};

        int resultado = db.update(BaseDatos.Tablas.PRE_ORDEN, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarGarantias(SGarantias item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasGarantias.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasGarantias.Cliente,item.getCliente());
        valores.put(FilasDB.ColumnasGarantias.Dirección,item.getDirección());
        valores.put(FilasDB.ColumnasGarantias.Contacto,item.getContacto());
        valores.put(FilasDB.ColumnasGarantias.Teléfono,item.getTeléfono());
        valores.put(FilasDB.ColumnasGarantias.Mail,item.getMail());
        valores.put(FilasDB.ColumnasGarantias.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasGarantias.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasGarantias.SR,item.getSR());
        valores.put(FilasDB.ColumnasGarantias.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasGarantias.TIPOSERVICIO,item.getTIPOSERVICIO());
        valores.put(FilasDB.ColumnasGarantias.FRECUENCIA,item.getFRECUENCIA());
        valores.put(FilasDB.ColumnasGarantias.NTAG,item.getNTAG());
        valores.put(FilasDB.ColumnasGarantias.MODELO,item.getMODELO());
        valores.put(FilasDB.ColumnasGarantias.NSERIE,item.getNSERIE());
        valores.put(FilasDB.ColumnasGarantias.NSERIE2,item.getNSERIE2());
        valores.put(FilasDB.ColumnasGarantias.FECHAINICIO,item.getFECHAINICIO());
        valores.put(FilasDB.ColumnasGarantias.FECHAFIN,item.getFECHAFIN());
        valores.put(FilasDB.ColumnasGarantias.TIPOGARANTIA,item.getTIPOGARANTIA());
        valores.put(FilasDB.ColumnasGarantias.FOLIOCCC,item.getFOLIOCCC());
        valores.put(FilasDB.ColumnasGarantias.FECHALOTE,item.getFECHALOTE());
        valores.put(FilasDB.ColumnasGarantias.LOTE,item.getLOTE());
        valores.put(FilasDB.ColumnasGarantias.ADUANA,item.getADUANA());
        valores.put(FilasDB.ColumnasGarantias.NUEVOSERIAL,item.getNUEVOSERIAL());
        valores.put(FilasDB.ColumnasGarantias.REPORTEFALLA,item.getREPORTEFALLA());
        valores.put(FilasDB.ColumnasGarantias.ACCIONCORRECTIVA,item.getACCIONCORRECTIVA());
        valores.put(FilasDB.ColumnasGarantias.COMENTARIOS,item.getCOMENTARIOS());
        valores.put(FilasDB.ColumnasGarantias.MATERIALESUTILIZADOS,item.getMATERIALESUTILIZADOS());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD1,item.getCANTIDAD1());
        valores.put(FilasDB.ColumnasGarantias.NPARTE1,item.getNPARTE1());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION1,item.getESPECIFICACION1());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD2,item.getCANTIDAD2());
        valores.put(FilasDB.ColumnasGarantias.NPARTE2,item.getNPARTE2());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION2,item.getESPECIFICACION2());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD3,item.getCANTIDAD3());
        valores.put(FilasDB.ColumnasGarantias.NPARTE3,item.getNPARTE3());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION3,item.getESPECIFICACION3());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD4,item.getCANTIDAD4());
        valores.put(FilasDB.ColumnasGarantias.NPARTE4,item.getNPARTE4());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION4,item.getESPECIFICACION4());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD5,item.getCANTIDAD5());
        valores.put(FilasDB.ColumnasGarantias.NPARTE5,item.getNPARTE5());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION5,item.getESPECIFICACION5());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD6,item.getCANTIDAD6());
        valores.put(FilasDB.ColumnasGarantias.NPARTE6,item.getNPARTE6());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION6,item.getESPECIFICACION6());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD7,item.getCANTIDAD7());
        valores.put(FilasDB.ColumnasGarantias.NPARTE7,item.getNPARTE7());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION7,item.getESPECIFICACION7());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD8,item.getCANTIDAD8());
        valores.put(FilasDB.ColumnasGarantias.NPARTE8,item.getNPARTE8());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION8,item.getESPECIFICACION8());
        valores.put(FilasDB.ColumnasGarantias.CANTIDAD9,item.getCANTIDAD9());
        valores.put(FilasDB.ColumnasGarantias.NPARTE9,item.getNPARTE9());
        valores.put(FilasDB.ColumnasGarantias.ESPECIFICACION9,item.getESPECIFICACION9());
        valores.put(FilasDB.ColumnasGarantias.EQUIPOMEDICION,item.getEQUIPOMEDICION());
        valores.put(FilasDB.ColumnasGarantias.EQUIPO1,item.getEQUIPO1());
        valores.put(FilasDB.ColumnasGarantias.NoID1,item.getNoID1());
        valores.put(FilasDB.ColumnasGarantias.FECHA1,item.getFECHA1());
        valores.put(FilasDB.ColumnasGarantias.EQUIPO2,item.getEQUIPO2());
        valores.put(FilasDB.ColumnasGarantias.NoID2,item.getNoID2());
        valores.put(FilasDB.ColumnasGarantias.FECHA2,item.getFECHA2());
        valores.put(FilasDB.ColumnasGarantias.EQUIPO3,item.getEQUIPO3());
        valores.put(FilasDB.ColumnasGarantias.NoID3,item.getNoID3());
        valores.put(FilasDB.ColumnasGarantias.FECHA3,item.getFECHA3());
        valores.put(FilasDB.ColumnasGarantias.EQUIPO4,item.getEQUIPO4());
        valores.put(FilasDB.ColumnasGarantias.NoID4,item.getNoID4());
        valores.put(FilasDB.ColumnasGarantias.FECHA4,item.getFECHA4());
        valores.put(FilasDB.ColumnasGarantias.EQUIPO5,item.getEQUIPO5());
        valores.put(FilasDB.ColumnasGarantias.NoID5,item.getNoID5());
        valores.put(FilasDB.ColumnasGarantias.FECHA5,item.getFECHA5());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES1,item.getFOTOANTES1());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES1,item.getFOTODESPUES1());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES2,item.getFOTOANTES2());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES2,item.getFOTODESPUES2());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES3,item.getFOTOANTES3());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES3,item.getFOTODESPUES3());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES4,item.getFOTOANTES4());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES4,item.getFOTODESPUES4());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES5,item.getFOTOANTES5());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES5,item.getFOTODESPUES5());
        valores.put(FilasDB.ColumnasGarantias.FOTOANTES6,item.getFOTOANTES6());
        valores.put(FilasDB.ColumnasGarantias.FOTODESPUES6,item.getFOTODESPUES6());
        valores.put(FilasDB.ColumnasGarantias.FIRMA1,item.getFIRMA1());
        valores.put(FilasDB.ColumnasGarantias.IMAGENFIRMA1,item.getIMAGENFIRMA1());
        valores.put(FilasDB.ColumnasGarantias.FIRMA2,item.getFIRMA2());
        valores.put(FilasDB.ColumnasGarantias.IMAGENFIRMA2,item.getIMAGENFIRMA2());
        valores.put(FilasDB.ColumnasGarantias.FIRMA13,item.getFIRMA13());
        valores.put(FilasDB.ColumnasGarantias.IMAGENFIRMA3,item.getIMAGENFIRMA3());
        valores.put(FilasDB.ColumnasGarantias.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasGarantias.cLIENTEFINAL_TELEFONO,item.getcLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasGarantias.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasGarantias.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasGarantias.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasGarantias.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasGarantias.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasGarantias.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasGarantias.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasGarantias.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasGarantias.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasGarantias.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasGarantias.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasGarantias.GarantiaArchivo,item.getGarantiaArchivo());

        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasGarantias.FolioFormato);
        String[] whereArgs = {item.getFolioFormato()};

        int resultado = db.update(BaseDatos.Tablas.GARANTIAS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarservicios(Servicios item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasServicios.FolioFormato,item.getFolioFormato());
        valores.put(FilasDB.ColumnasServicios.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasServicios.Cliente,item.getCliente());
        valores.put(FilasDB.ColumnasServicios.Dirección,item.getDirección());
        valores.put(FilasDB.ColumnasServicios.Contacto,item.getContacto());
        valores.put(FilasDB.ColumnasServicios.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasServicios.Mail,item.getMail());
        valores.put(FilasDB.ColumnasServicios.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasServicios.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasServicios.SR,item.getSR());
        valores.put(FilasDB.ColumnasServicios.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasServicios.TIPOSERVICIO,item.getTIPOSERVICIO());
        valores.put(FilasDB.ColumnasServicios.ESPECICIFACION,item.getESPECICIFACION());
        valores.put(FilasDB.ColumnasServicios.ARRANCOVERTIV,item.getARRANCOVERTIV());
        valores.put(FilasDB.ColumnasServicios.FRECUENCIA,item.getFRECUENCIA());
        valores.put(FilasDB.ColumnasServicios.NTAG,item.getNTAG());
        valores.put(FilasDB.ColumnasServicios.MODELO,item.getMODELO());
        valores.put(FilasDB.ColumnasServicios.NSERIE,item.getNSERIE());
        valores.put(FilasDB.ColumnasServicios.NSERIE2,item.getNSERIE2());
        valores.put(FilasDB.ColumnasServicios.FECHAINICIO,item.getFECHAINICIO());
        valores.put(FilasDB.ColumnasServicios.FECHAFIN,item.getFECHAFIN());
        valores.put(FilasDB.ColumnasServicios.ACTIVIDAD,item.getACTIVIDAD());
        valores.put(FilasDB.ColumnasServicios.DIAGNOSTICO,item.getDIAGNOSTICO());
        valores.put(FilasDB.ColumnasServicios.ACCIONCORRECTIVA,item.getACCIONCORRECTIVA());
        valores.put(FilasDB.ColumnasServicios.COMENTARIOS,item.getCOMENTARIOS());
        valores.put(FilasDB.ColumnasServicios.MATERIALESUTILIZADOS,item.getMATERIALESUTILIZADOS());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD1,item.getCANTIDAD1());
        valores.put(FilasDB.ColumnasServicios.NPARTE1,item.getNPARTE1());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION1,item.getESPECIFICACION1());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD2,item.getCANTIDAD2());
        valores.put(FilasDB.ColumnasServicios.NPARTE2,item.getNPARTE2());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION2,item.getESPECIFICACION2());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD3,item.getCANTIDAD3());
        valores.put(FilasDB.ColumnasServicios.NPARTE3,item.getNPARTE3());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION3,item.getESPECIFICACION3());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD4,item.getCANTIDAD4());
        valores.put(FilasDB.ColumnasServicios.NPARTE4,item.getNPARTE4());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION4,item.getESPECIFICACION4());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD5,item.getCANTIDAD5());
        valores.put(FilasDB.ColumnasServicios.NPARTE5,item.getNPARTE5());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION5,item.getESPECIFICACION5());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD6,item.getCANTIDAD6());
        valores.put(FilasDB.ColumnasServicios.NPARTE6,item.getNPARTE6());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION6,item.getESPECIFICACION6());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD7,item.getCANTIDAD7());
        valores.put(FilasDB.ColumnasServicios.NPARTE7,item.getNPARTE7());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION7,item.getESPECIFICACION7());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD8,item.getCANTIDAD8());
        valores.put(FilasDB.ColumnasServicios.NPARTE8,item.getNPARTE8());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION8,item.getESPECIFICACION8());
        valores.put(FilasDB.ColumnasServicios.CANTIDAD9,item.getCANTIDAD9());
        valores.put(FilasDB.ColumnasServicios.NPARTE9,item.getNPARTE9());
        valores.put(FilasDB.ColumnasServicios.ESPECIFICACION9,item.getESPECIFICACION9());
        valores.put(FilasDB.ColumnasServicios.EQUIPOMEDICION,item.getEQUIPOMEDICION());
        valores.put(FilasDB.ColumnasServicios.EQUIPO1,item.getEQUIPO1());
        valores.put(FilasDB.ColumnasServicios.NoID1,item.getNoID1());
        valores.put(FilasDB.ColumnasServicios.FECHA1,item.getFECHA1());
        valores.put(FilasDB.ColumnasServicios.EQUIPO2,item.getEQUIPO2());
        valores.put(FilasDB.ColumnasServicios.NoID2,item.getNoID2());
        valores.put(FilasDB.ColumnasServicios.FECHA2,item.getFECHA2());
        valores.put(FilasDB.ColumnasServicios.EQUIPO3,item.getEQUIPO3());
        valores.put(FilasDB.ColumnasServicios.NoID3,item.getNoID3());
        valores.put(FilasDB.ColumnasServicios.FECHA3,item.getFECHA3());
        valores.put(FilasDB.ColumnasServicios.EQUIPO4,item.getEQUIPO4());
        valores.put(FilasDB.ColumnasServicios.NoID4,item.getNoID4());
        valores.put(FilasDB.ColumnasServicios.FECHA4,item.getFECHA4());
        valores.put(FilasDB.ColumnasServicios.EQUIPO5,item.getEQUIPO5());
        valores.put(FilasDB.ColumnasServicios.NoID5,item.getNoID5());
        valores.put(FilasDB.ColumnasServicios.FECHA5,item.getFECHA5());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES1,item.getFOTOANTES1());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES1,item.getFOTODESPUES1());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES2,item.getFOTOANTES2());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES2,item.getFOTODESPUES2());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES3,item.getFOTOANTES3());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES3,item.getFOTODESPUES3());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES4,item.getFOTOANTES4());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES4,item.getFOTODESPUES4());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES5,item.getFOTOANTES5());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES5,item.getFOTODESPUES5());
        valores.put(FilasDB.ColumnasServicios.FOTOANTES6,item.getFOTOANTES6());
        valores.put(FilasDB.ColumnasServicios.FOTODESPUES6,item.getFOTODESPUES6());
        valores.put(FilasDB.ColumnasServicios.FIRMA1,item.getFIRMA1());
        valores.put(FilasDB.ColumnasServicios.IMAGENFIRMA1,item.getIMAGENFIRMA1());
        valores.put(FilasDB.ColumnasServicios.FIRMA2,item.getFIRMA2());
        valores.put(FilasDB.ColumnasServicios.IMAGENFIRMA2,item.getIMAGENFIRMA2());
        valores.put(FilasDB.ColumnasServicios.FIRMA13,item.getFIRMA13());
        valores.put(FilasDB.ColumnasServicios.IMAGENFIRMA3,item.getIMAGENFIRMA3());
        valores.put(FilasDB.ColumnasServicios.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasServicios.cLIENTEFINAL_TELEFONO,item.getcLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasServicios.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasServicios.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasServicios.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasServicios.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasServicios.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasServicios.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasServicios.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasServicios.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasServicios.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasServicios.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasServicios.AD_CORREO5,item.getAD_CORREO5());



        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasServicios.FolioFormato);
        String[] whereArgs = {item.getFolioFormato()};

        int resultado = db.update(BaseDatos.Tablas.SERVICIOS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarInfoformato(DatGeneralesF item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasFormatosControl.FECHA_MODIFICACION,item.getFECHA_MODIFICACION());
        valores.put(FilasDB.ColumnasFormatosControl.FOLIO_PRETRABAJO,item.getFOLIO_PRETRABAJO());
        valores.put(FilasDB.ColumnasFormatosControl.CLIENTE,item.getCLIENTE());
        valores.put(FilasDB.ColumnasFormatosControl.SR,item.getSR());
        valores.put(FilasDB.ColumnasFormatosControl.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasFormatosControl.ITEM,item.getITEM());
        valores.put(FilasDB.ColumnasFormatosControl.SERIE,item.getSERIE());

        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasFormatosControl.ID_FORMATO);
        String[] whereArgs = {item.getID_FORMATO()};

        int resultado = db.update(BaseDatos.Tablas.CONTROL_FORMATOS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarEsatusFormato(String status, String Key){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasFormatosControl.ESTATUS,status);
        valores.put(FilasDB.ColumnasFormatosControl.FECHA_MODIFICACION,new Date().toString());

        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasFormatosControl.ID_FORMATO);
        String[] whereArgs = {Key};

        int resultado = db.update(BaseDatos.Tablas.CONTROL_FORMATOS, valores, whereClause, whereArgs);

        return resultado > 0;
    }


    public boolean guardarPower1(DCPower power){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasDCPwer.ID_fORMATO,power.getID_fORMATO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_FOLIO_PRETRABAJO,power.getGRAL_FOLIO_PRETRABAJO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_CLIENTE,power.getGRAL_CLIENTE());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_DIRECCION,power.getGRAL_DIRECCION());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_CONTACTO,power.getGRAL_CONTACTO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_TELEFONO,power.getGRAL_TELEFONO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_MAIL,power.getGRAL_MAIL());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_PROYECTO,power.getGRAL_PROYECTO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_REFERENCIA,power.getGRAL_REFERENCIA());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_SR,power.getGRAL_SR());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_TASK,power.getGRAL_TASK());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_TIPO_SERVICIO,power.getGRAL_TIPO_SERVICIO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_ESPECIFICACION,power.getGRAL_ESPECIFICACION());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_FRECUENCIA,power.getGRAL_FRECUENCIA());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_NTAG,power.getGRAL_NTAG());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_MODELO,power.getGRAL_MODELO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_SERIE,power.getGRAL_SERIE());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_SERIE2,power.getGRAL_SERIE2());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_FECHA_INICIO,power.getGRAL_FECHA_INICIO());
        valores.put(FilasDB.ColumnasDCPwer.GRAL_FECHA_FIN,power.getGRAL_FECHA_FIN());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_GRALS_TIPO,power.getPARAM_GRALS_TIPO());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_GRALS_N_ESPECIFICAION,power.getPARAM_GRALS_N_ESPECIFICAION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_GRALS_CAPACIDAD,power.getPARAM_GRALS_CAPACIDAD());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_GRALS_CONFIGURACION,power.getPARAM_GRALS_CONFIGURACION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_GRALS_CARGA_ACT,power.getPARAM_GRALS_CARGA_ACT());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_MODELO,power.getPARAM_INV_RECTIFICADORES_MODELO());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_N_ESPECIFICACION,power.getPARAM_INV_RECTIFICADORES_N_ESPECIFICACION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_CAPACIDAD,power.getPARAM_INV_RECTIFICADORES_CAPACIDAD());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R1,power.getPARAM_INV_RECTIFICADORES_R1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R2,power.getPARAM_INV_RECTIFICADORES_R2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R3,power.getPARAM_INV_RECTIFICADORES_R3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R4,power.getPARAM_INV_RECTIFICADORES_R4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R5,power.getPARAM_INV_RECTIFICADORES_R5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R6,power.getPARAM_INV_RECTIFICADORES_R6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R7,power.getPARAM_INV_RECTIFICADORES_R7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R8,power.getPARAM_INV_RECTIFICADORES_R8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R9,power.getPARAM_INV_RECTIFICADORES_R9());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R10,power.getPARAM_INV_RECTIFICADORES_R10());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R11,power.getPARAM_INV_RECTIFICADORES_R11());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R12,power.getPARAM_INV_RECTIFICADORES_R12());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R13,power.getPARAM_INV_RECTIFICADORES_R13());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R14,power.getPARAM_INV_RECTIFICADORES_R14());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R15,power.getPARAM_INV_RECTIFICADORES_R15());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R16,power.getPARAM_INV_RECTIFICADORES_R16());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R17,power.getPARAM_INV_RECTIFICADORES_R17());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R18,power.getPARAM_INV_RECTIFICADORES_R18());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R19,power.getPARAM_INV_RECTIFICADORES_R19());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R20,power.getPARAM_INV_RECTIFICADORES_R20());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R21,power.getPARAM_INV_RECTIFICADORES_R21());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R22,power.getPARAM_INV_RECTIFICADORES_R22());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R23,power.getPARAM_INV_RECTIFICADORES_R23());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R24,power.getPARAM_INV_RECTIFICADORES_R24());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R25,power.getPARAM_INV_RECTIFICADORES_R25());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R26,power.getPARAM_INV_RECTIFICADORES_R26());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R27,power.getPARAM_INV_RECTIFICADORES_R27());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R28,power.getPARAM_INV_RECTIFICADORES_R28());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R29,power.getPARAM_INV_RECTIFICADORES_R29());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R30,power.getPARAM_INV_RECTIFICADORES_R30());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R31,power.getPARAM_INV_RECTIFICADORES_R31());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_RECTIFICADORES_R32,power.getPARAM_INV_RECTIFICADORES_R32());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_NA,power.getPARAM_INV_GABINETES_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_ESPECIFIC,power.getPARAM_INV_GABINETES_ESPECIFIC());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB1,power.getPARAM_INV_GABINETES_GAB1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB2,power.getPARAM_INV_GABINETES_GAB2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB3,power.getPARAM_INV_GABINETES_GAB3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB4,power.getPARAM_INV_GABINETES_GAB4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB5,power.getPARAM_INV_GABINETES_GAB5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_INV_GABINETES_GAB6,power.getPARAM_INV_GABINETES_GAB6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_NA,power.getPARAM_SUPRESOR_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_MARCA,power.getPARAM_SUPRESOR_MARCA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_MODELO,power.getPARAM_SUPRESOR_MODELO());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_INSTALAC,power.getPARAM_SUPRESOR_INSTALAC());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_CALIBRE_CONEXION,power.getPARAM_SUPRESOR_CALIBRE_CONEXION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_CAPACIDAD,power.getPARAM_SUPRESOR_CAPACIDAD());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SUPRESOR_CALIBRE_TIERRAS,power.getPARAM_SUPRESOR_CALIBRE_TIERRAS());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M1,power.getPARAM_AyO_PLANTA_ALIMENT_M1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M2,power.getPARAM_AyO_PLANTA_ALIMENT_M2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M3,power.getPARAM_AyO_PLANTA_ALIMENT_M3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M4,power.getPARAM_AyO_PLANTA_ALIMENT_M4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M5,power.getPARAM_AyO_PLANTA_ALIMENT_M5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M6,power.getPARAM_AyO_PLANTA_ALIMENT_M6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M7,power.getPARAM_AyO_PLANTA_ALIMENT_M7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_ALIMENT_M8,power.getPARAM_AyO_PLANTA_ALIMENT_M8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M1,power.getPARAM_AyO_PLANTA_OPERAC_M1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M2,power.getPARAM_AyO_PLANTA_OPERAC_M2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M3,power.getPARAM_AyO_PLANTA_OPERAC_M3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M4,power.getPARAM_AyO_PLANTA_OPERAC_M4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M5,power.getPARAM_AyO_PLANTA_OPERAC_M5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M6,power.getPARAM_AyO_PLANTA_OPERAC_M6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M7,power.getPARAM_AyO_PLANTA_OPERAC_M7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M8,power.getPARAM_AyO_PLANTA_OPERAC_M8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M9,power.getPARAM_AyO_PLANTA_OPERAC_M9());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_OPERAC_M10,power.getPARAM_AyO_PLANTA_OPERAC_M10());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_LIMPIEZA_INTERNA,power.getPARAM_AyO_PLANTA_LIMPIEZA_INTERNA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_LIMPIEZA_INDV,power.getPARAM_AyO_PLANTA_LIMPIEZA_INDV());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_AyO_PLANTA_VERIFICAION,power.getPARAM_AyO_PLANTA_VERIFICAION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_NA,power.getPARAM_STPF_DC_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP1,power.getPARAM_STPF_DC_CAMP1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP2,power.getPARAM_STPF_DC_CAMP2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP3,power.getPARAM_STPF_DC_CAMP3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP4,power.getPARAM_STPF_DC_CAMP4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP5,power.getPARAM_STPF_DC_CAMP5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP6,power.getPARAM_STPF_DC_CAMP6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP7,power.getPARAM_STPF_DC_CAMP7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_STPF_DC_CAMP8,power.getPARAM_STPF_DC_CAMP8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_NA,power.getPARAM_ITPF_DC_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP1,power.getPARAM_ITPF_DC_CAMP1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP2,power.getPARAM_ITPF_DC_CAMP2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP3,power.getPARAM_ITPF_DC_CAMP3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP4,power.getPARAM_ITPF_DC_CAMP4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP5,power.getPARAM_ITPF_DC_CAMP5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP6,power.getPARAM_ITPF_DC_CAMP6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_ITPF_DC_CAMP7,power.getPARAM_ITPF_DC_CAMP7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_NA,power.getPARAM_SISTEMA_DIST_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_MODELO,power.getPARAM_SISTEMA_DIST_MODELO());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_MARCA,power.getPARAM_SISTEMA_DIST_MARCA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_ESPECIFI,power.getPARAM_SISTEMA_DIST_ESPECIFI());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_CAPACIDAD,power.getPARAM_SISTEMA_DIST_CAPACIDAD());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_DIST_CONFIGURACION,power.getPARAM_SISTEMA_DIST_CONFIGURACION());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_NA,power.getPARAM_IDR_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP1,power.getPARAM_IDR_CAMP1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP2,power.getPARAM_IDR_CAMP2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP3,power.getPARAM_IDR_CAMP3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP4,power.getPARAM_IDR_CAMP4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP5,power.getPARAM_IDR_CAMP5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP6,power.getPARAM_IDR_CAMP6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_CAMP7,power.getPARAM_IDR_CAMP7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK1,power.getPARAM_IDR_BREAK1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK2,power.getPARAM_IDR_BREAK2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK3,power.getPARAM_IDR_BREAK3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK4,power.getPARAM_IDR_BREAK4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK5,power.getPARAM_IDR_BREAK5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK6,power.getPARAM_IDR_BREAK6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK7,power.getPARAM_IDR_BREAK7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_IDR_BREAK8,power.getPARAM_IDR_BREAK8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_NA,power.getPARAM_SISTEMA_INVER_NA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_MODELO,power.getPARAM_SISTEMA_INVER_MODELO());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_MARCA,power.getPARAM_SISTEMA_INVER_MARCA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_ESPEC,power.getPARAM_SISTEMA_INVER_ESPEC());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_CAPACIDAD,power.getPARAM_SISTEMA_INVER_CAPACIDAD());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_CONFG,power.getPARAM_SISTEMA_INVER_CONFG());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_PIEZA,power.getPARAM_SISTEMA_INVER_A_PIEZA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV1,power.getPARAM_SISTEMA_INVER_A_INV1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV2,power.getPARAM_SISTEMA_INVER_A_INV2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV3,power.getPARAM_SISTEMA_INVER_A_INV3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV4,power.getPARAM_SISTEMA_INVER_A_INV4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV5,power.getPARAM_SISTEMA_INVER_A_INV5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV6,power.getPARAM_SISTEMA_INVER_A_INV6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV7,power.getPARAM_SISTEMA_INVER_A_INV7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV8,power.getPARAM_SISTEMA_INVER_A_INV8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV9,power.getPARAM_SISTEMA_INVER_A_INV9());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_A_INV10,power.getPARAM_SISTEMA_INVER_A_INV10());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_PIEZA,power.getPARAM_SISTEMA_INVER_B_PIEZA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV1,power.getPARAM_SISTEMA_INVER_B_INV1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV2,power.getPARAM_SISTEMA_INVER_B_INV2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV3,power.getPARAM_SISTEMA_INVER_B_INV3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV4,power.getPARAM_SISTEMA_INVER_B_INV4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV5,power.getPARAM_SISTEMA_INVER_B_INV5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV6,power.getPARAM_SISTEMA_INVER_B_INV6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV7,power.getPARAM_SISTEMA_INVER_B_INV7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV8,power.getPARAM_SISTEMA_INVER_B_INV8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV9,power.getPARAM_SISTEMA_INVER_B_INV9());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_B_INV10,power.getPARAM_SISTEMA_INVER_B_INV10());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_PIEZA,power.getPARAM_SISTEMA_INVER_C_PIEZA());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV1,power.getPARAM_SISTEMA_INVER_C_INV1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV2,power.getPARAM_SISTEMA_INVER_C_INV2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV3,power.getPARAM_SISTEMA_INVER_C_INV3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV4,power.getPARAM_SISTEMA_INVER_C_INV4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV5,power.getPARAM_SISTEMA_INVER_C_INV5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV6,power.getPARAM_SISTEMA_INVER_C_INV6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV7,power.getPARAM_SISTEMA_INVER_C_INV7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV8,power.getPARAM_SISTEMA_INVER_C_INV8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV9,power.getPARAM_SISTEMA_INVER_C_INV9());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_C_INV10,power.getPARAM_SISTEMA_INVER_C_INV10());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_VERI1,power.getPARAM_SISTEMA_INVER_VERI1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_VERI2,power.getPARAM_SISTEMA_INVER_VERI2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_VERI3,power.getPARAM_SISTEMA_INVER_VERI3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_VERI4,power.getPARAM_SISTEMA_INVER_VERI4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_SISTEMA_INVER_LIMP,power.getPARAM_SISTEMA_INVER_LIMP());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC1,power.getPARAM_NAyS_ALIM_VAC1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC2,power.getPARAM_NAyS_ALIM_VAC2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC3,power.getPARAM_NAyS_ALIM_VAC3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC4,power.getPARAM_NAyS_ALIM_VAC4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC5,power.getPARAM_NAyS_ALIM_VAC5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC6,power.getPARAM_NAyS_ALIM_VAC6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC7,power.getPARAM_NAyS_ALIM_VAC7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_VAC8,power.getPARAM_NAyS_ALIM_VAC8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA1,power.getPARAM_NAyS_ALIM_ACA1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA2,power.getPARAM_NAyS_ALIM_ACA2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA3,power.getPARAM_NAyS_ALIM_ACA3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA4,power.getPARAM_NAyS_ALIM_ACA4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA5,power.getPARAM_NAyS_ALIM_ACA5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA6,power.getPARAM_NAyS_ALIM_ACA6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA7,power.getPARAM_NAyS_ALIM_ACA7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_ALIM_ACA8,power.getPARAM_NAyS_ALIM_ACA8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC1,power.getPARAM_NAyS_SALID_VAC1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC2,power.getPARAM_NAyS_SALID_VAC2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC3,power.getPARAM_NAyS_SALID_VAC3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC4,power.getPARAM_NAyS_SALID_VAC4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC5,power.getPARAM_NAyS_SALID_VAC5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC6,power.getPARAM_NAyS_SALID_VAC6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC7,power.getPARAM_NAyS_SALID_VAC7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_VAC8,power.getPARAM_NAyS_SALID_VAC8());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA1,power.getPARAM_NAyS_SALID_ACA1());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA2,power.getPARAM_NAyS_SALID_ACA2());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA3,power.getPARAM_NAyS_SALID_ACA3());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA4,power.getPARAM_NAyS_SALID_ACA4());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA5,power.getPARAM_NAyS_SALID_ACA5());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA6,power.getPARAM_NAyS_SALID_ACA6());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA7,power.getPARAM_NAyS_SALID_ACA7());
        valores.put(FilasDB.ColumnasDCPwer.PARAM_NAyS_SALID_ACA8    ,power.getPARAM_NAyS_SALID_ACA8    ());

        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasDCPwer.ID_fORMATO);
        String[] whereArgs = {power.getID_fORMATO()};

        int resultado = db.update(BaseDatos.Tablas.DC_POWER, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarPower2(DCPower2 power, String idFormato){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasDCPwer.COMENTA_ACTIVIDADES,power.getCOMENTA_ACTIVIDADES());
        valores.put(FilasDB.ColumnasDCPwer.COMENTA_DIAGNOSTICO,power.getCOMENTA_DIAGNOSTICO());
        valores.put(FilasDB.ColumnasDCPwer.COMENTA_ACCION,power.getCOMENTA_ACCION());
        valores.put(FilasDB.ColumnasDCPwer.COMENTA_COMENTARIOS,power.getCOMENTA_COMENTARIOS());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_PMU_NA,power.getMATERIA_PMU_NA());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD1,power.getMATERIA_CANTIDAD1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE1,power.getMATERIA_N_PARTE1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC1,power.getMATERIA_DESC1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD2,power.getMATERIA_CANTIDAD2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE2,power.getMATERIA_N_PARTE2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC2,power.getMATERIA_DESC2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD3,power.getMATERIA_CANTIDAD3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE3,power.getMATERIA_N_PARTE3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC3,power.getMATERIA_DESC3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD4,power.getMATERIA_CANTIDAD4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE4,power.getMATERIA_N_PARTE4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC4,power.getMATERIA_DESC4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD5,power.getMATERIA_CANTIDAD5());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE5,power.getMATERIA_N_PARTE5());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC5,power.getMATERIA_DESC5());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD6,power.getMATERIA_CANTIDAD6());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE6,power.getMATERIA_N_PARTE6());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC6,power.getMATERIA_DESC6());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD7,power.getMATERIA_CANTIDAD7());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE7,power.getMATERIA_N_PARTE7());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC7,power.getMATERIA_DESC7());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_CANTIDAD8,power.getMATERIA_CANTIDAD8());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_N_PARTE8,power.getMATERIA_N_PARTE8());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_DESC8,power.getMATERIA_DESC8());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_NA,power.getMATERIA_EQUIPO_NA());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_EQUIPO1,power.getMATERIA_EQUIPO_EQUIPO1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_ID1,power.getMATERIA_EQUIPO_ID1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_FEVHA1,power.getMATERIA_EQUIPO_FEVHA1());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_EQUIPO2,power.getMATERIA_EQUIPO_EQUIPO2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_ID2,power.getMATERIA_EQUIPO_ID2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_FEVHA2,power.getMATERIA_EQUIPO_FEVHA2());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_EQUIPO3,power.getMATERIA_EQUIPO_EQUIPO3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_ID3,power.getMATERIA_EQUIPO_ID3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_FEVHA3,power.getMATERIA_EQUIPO_FEVHA3());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_EQUIPO4,power.getMATERIA_EQUIPO_EQUIPO4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_ID4,power.getMATERIA_EQUIPO_ID4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_FEVHA4,power.getMATERIA_EQUIPO_FEVHA4());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_EQUIPO5,power.getMATERIA_EQUIPO_EQUIPO5());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_ID5,power.getMATERIA_EQUIPO_ID5());
        valores.put(FilasDB.ColumnasDCPwer.MATERIA_EQUIPO_FEVHA5,power.getMATERIA_EQUIPO_FEVHA5());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES1,power.getFOTOS_ANTES1());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES2,power.getFOTOS_ANTES2());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES3,power.getFOTOS_ANTES3());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES4,power.getFOTOS_ANTES4());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES5,power.getFOTOS_ANTES5());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_ANTES6,power.getFOTOS_ANTES6());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES1,power.getFOTOS_DESPUES1());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES2,power.getFOTOS_DESPUES2());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES3,power.getFOTOS_DESPUES3());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES4,power.getFOTOS_DESPUES4());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES5,power.getFOTOS_DESPUES5());
        valores.put(FilasDB.ColumnasDCPwer.FOTOS_DESPUES6,power.getFOTOS_DESPUES6());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_NOMBRE1,power.getFIRMA_NOMBRE1());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CLIENTE1,power.getFIRMA_CLIENTE1());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_FIRMA1,power.getFIRMA_FIRMA1());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_NOMBRE2,power.getFIRMA_NOMBRE2());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CLIENTE2,power.getFIRMA_CLIENTE2());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_FIRMA2,power.getFIRMA_FIRMA2());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_NOMBRE3,power.getFIRMA_NOMBRE3());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CLIENTE3,power.getFIRMA_CLIENTE3());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_FIRMA3,power.getFIRMA_FIRMA3());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_EMPRESA,power.getFIRMA_EMPRESA());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_TELEFONO,power.getFIRMA_TELEFONO());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO,power.getFIRMA_CORREO());
        valores.put(FilasDB.ColumnasDCPwer.ADICIONALES_NOMBRE1,power.getADICIONALES_NOMBRE1());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO1,power.getFIRMA_CORREO1());
        valores.put(FilasDB.ColumnasDCPwer.ADICIONALES_NOMBRE2,power.getADICIONALES_NOMBRE2());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO2,power.getFIRMA_CORREO2());
        valores.put(FilasDB.ColumnasDCPwer.ADICIONALES_NOMBRE3,power.getADICIONALES_NOMBRE3());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO3,power.getFIRMA_CORREO3());
        valores.put(FilasDB.ColumnasDCPwer.ADICIONALES_NOMBRE4,power.getADICIONALES_NOMBRE4());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO4,power.getFIRMA_CORREO4());
        valores.put(FilasDB.ColumnasDCPwer.ADICIONALES_NOMBRE5,power.getADICIONALES_NOMBRE5());
        valores.put(FilasDB.ColumnasDCPwer.FIRMA_CORREO5,power.getFIRMA_CORREO5());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra1,power.getCampoextra1());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra2,power.getCampoextra2());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra3,power.getCampoextra3());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra4,power.getCampoextra4());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra5,power.getCampoextra5());
        valores.put(FilasDB.ColumnasDCPwer.Campoextra6,power.getCampoextra6());

        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasDCPwer.ID_fORMATO);
        String[] whereArgs = {idFormato};

        int resultado = db.update(BaseDatos.Tablas.DC_POWER, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarBatrias(Baterias bateria, String keyFormato){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        //region
        valores.put(FilasDB.ColumnasBaterias.GRAL_folioPT,bateria.getGRAL_folioPT());
        valores.put(FilasDB.ColumnasBaterias.GRAL_cliente,bateria.getGRAL_cliente());
        valores.put(FilasDB.ColumnasBaterias.GRAL_direccion,bateria.getGRAL_direccion());
        valores.put(FilasDB.ColumnasBaterias.GRAL_contacto,bateria.getGRAL_contacto());
        valores.put(FilasDB.ColumnasBaterias.GRAL_telefono,bateria.getGRAL_telefono());
        valores.put(FilasDB.ColumnasBaterias.GRAL_mail,bateria.getGRAL_mail());
        valores.put(FilasDB.ColumnasBaterias.GRAL_proyecto,bateria.getGRAL_proyecto());
        valores.put(FilasDB.ColumnasBaterias.GRAL_eferencia,bateria.getGRAL_eferencia());
        valores.put(FilasDB.ColumnasBaterias.GRAL_sr,bateria.getGRAL_sr());
        valores.put(FilasDB.ColumnasBaterias.GRAL_task,bateria.getGRAL_task());
        valores.put(FilasDB.ColumnasBaterias.GRAL_tipoServ,bateria.getGRAL_tipoServ());
        valores.put(FilasDB.ColumnasBaterias.GRAL_tiposervOtro,bateria.getGRAL_tiposervOtro());
        valores.put(FilasDB.ColumnasBaterias.GRAL_freciencia,bateria.getGRAL_freciencia());
        valores.put(FilasDB.ColumnasBaterias.GRAL_noTag,bateria.getGRAL_noTag());
        valores.put(FilasDB.ColumnasBaterias.GRAL_modelo,bateria.getGRAL_modelo());
        valores.put(FilasDB.ColumnasBaterias.GRAL_serie1,bateria.getGRAL_serie1());
        valores.put(FilasDB.ColumnasBaterias.GRAL_serie2,bateria.getGRAL_serie2());
        valores.put(FilasDB.ColumnasBaterias.GRAL_fecha1,bateria.getGRAL_fecha1());
        valores.put(FilasDB.ColumnasBaterias.GRAL_fecha2,bateria.getGRAL_fecha2());
        valores.put(FilasDB.ColumnasBaterias.VDC1,bateria.getVDC1());
        valores.put(FilasDB.ColumnasBaterias.VDC2,bateria.getVDC2());
        valores.put(FilasDB.ColumnasBaterias.VDC3,bateria.getVDC3());
        valores.put(FilasDB.ColumnasBaterias.VDC4,bateria.getVDC4());
        valores.put(FilasDB.ColumnasBaterias.VDC5,bateria.getVDC5());
        valores.put(FilasDB.ColumnasBaterias.VDC6,bateria.getVDC6());
        valores.put(FilasDB.ColumnasBaterias.VDC7,bateria.getVDC7());
        valores.put(FilasDB.ColumnasBaterias.VDC8,bateria.getVDC8());
        valores.put(FilasDB.ColumnasBaterias.VDC9,bateria.getVDC9());
        valores.put(FilasDB.ColumnasBaterias.VDC10,bateria.getVDC10());
        valores.put(FilasDB.ColumnasBaterias.VDC11,bateria.getVDC11());
        valores.put(FilasDB.ColumnasBaterias.VDC12,bateria.getVDC12());
        valores.put(FilasDB.ColumnasBaterias.VDC13,bateria.getVDC13());
        valores.put(FilasDB.ColumnasBaterias.VDC14,bateria.getVDC14());
        valores.put(FilasDB.ColumnasBaterias.VDC15,bateria.getVDC15());
        valores.put(FilasDB.ColumnasBaterias.VDC16,bateria.getVDC16());
        valores.put(FilasDB.ColumnasBaterias.VDC17,bateria.getVDC17());
        valores.put(FilasDB.ColumnasBaterias.VDC18,bateria.getVDC18());
        valores.put(FilasDB.ColumnasBaterias.VDC19,bateria.getVDC19());
        valores.put(FilasDB.ColumnasBaterias.VDC20,bateria.getVDC20());
        valores.put(FilasDB.ColumnasBaterias.VDC21,bateria.getVDC21());
        valores.put(FilasDB.ColumnasBaterias.VDC22,bateria.getVDC22());
        valores.put(FilasDB.ColumnasBaterias.VDC23,bateria.getVDC23());
        valores.put(FilasDB.ColumnasBaterias.VDC24,bateria.getVDC24());
        valores.put(FilasDB.ColumnasBaterias.VDC25,bateria.getVDC25());
        valores.put(FilasDB.ColumnasBaterias.VDC26,bateria.getVDC26());
        valores.put(FilasDB.ColumnasBaterias.VDC27,bateria.getVDC27());
        valores.put(FilasDB.ColumnasBaterias.VDC28,bateria.getVDC28());
        valores.put(FilasDB.ColumnasBaterias.VDC29,bateria.getVDC29());
        valores.put(FilasDB.ColumnasBaterias.VDC30,bateria.getVDC30());
        valores.put(FilasDB.ColumnasBaterias.VDC31,bateria.getVDC31());
        valores.put(FilasDB.ColumnasBaterias.VDC32,bateria.getVDC32());
        valores.put(FilasDB.ColumnasBaterias.VDC33,bateria.getVDC33());
        valores.put(FilasDB.ColumnasBaterias.VDC34,bateria.getVDC34());
        valores.put(FilasDB.ColumnasBaterias.VDC35,bateria.getVDC35());
        valores.put(FilasDB.ColumnasBaterias.VDC36,bateria.getVDC36());
        valores.put(FilasDB.ColumnasBaterias.VDC37,bateria.getVDC37());
        valores.put(FilasDB.ColumnasBaterias.VDC38,bateria.getVDC38());
        valores.put(FilasDB.ColumnasBaterias.VDC39,bateria.getVDC39());
        valores.put(FilasDB.ColumnasBaterias.VDC40,bateria.getVDC40());
        valores.put(FilasDB.ColumnasBaterias.VDC41,bateria.getVDC41());
        valores.put(FilasDB.ColumnasBaterias.VDC42,bateria.getVDC42());
        valores.put(FilasDB.ColumnasBaterias.VDC43,bateria.getVDC43());
        valores.put(FilasDB.ColumnasBaterias.VDC44,bateria.getVDC44());
        valores.put(FilasDB.ColumnasBaterias.VDC45,bateria.getVDC45());
        valores.put(FilasDB.ColumnasBaterias.VDC46,bateria.getVDC46());
        valores.put(FilasDB.ColumnasBaterias.VDC47,bateria.getVDC47());
        valores.put(FilasDB.ColumnasBaterias.VDC48,bateria.getVDC48());
        valores.put(FilasDB.ColumnasBaterias.VDC49,bateria.getVDC49());
        valores.put(FilasDB.ColumnasBaterias.VDC50,bateria.getVDC50());
        valores.put(FilasDB.ColumnasBaterias.VDC51,bateria.getVDC51());
        valores.put(FilasDB.ColumnasBaterias.VDC52,bateria.getVDC52());
        valores.put(FilasDB.ColumnasBaterias.VDC53,bateria.getVDC53());
        valores.put(FilasDB.ColumnasBaterias.VDC54,bateria.getVDC54());
        valores.put(FilasDB.ColumnasBaterias.VDC55,bateria.getVDC55());
        valores.put(FilasDB.ColumnasBaterias.VDC56,bateria.getVDC56());
        valores.put(FilasDB.ColumnasBaterias.VDC57,bateria.getVDC57());
        valores.put(FilasDB.ColumnasBaterias.VDC58,bateria.getVDC58());
        valores.put(FilasDB.ColumnasBaterias.VDC59,bateria.getVDC59());
        valores.put(FilasDB.ColumnasBaterias.VDC60,bateria.getVDC60());
        valores.put(FilasDB.ColumnasBaterias.VDC61,bateria.getVDC61());
        valores.put(FilasDB.ColumnasBaterias.VDC62,bateria.getVDC62());
        valores.put(FilasDB.ColumnasBaterias.VDC63,bateria.getVDC63());
        valores.put(FilasDB.ColumnasBaterias.VDC64,bateria.getVDC64());
        valores.put(FilasDB.ColumnasBaterias.VDC65,bateria.getVDC65());
        valores.put(FilasDB.ColumnasBaterias.VDC66,bateria.getVDC66());
        valores.put(FilasDB.ColumnasBaterias.VDC67,bateria.getVDC67());
        valores.put(FilasDB.ColumnasBaterias.VDC68,bateria.getVDC68());
        valores.put(FilasDB.ColumnasBaterias.VDC69,bateria.getVDC69());
        valores.put(FilasDB.ColumnasBaterias.VDC70,bateria.getVDC70());
        valores.put(FilasDB.ColumnasBaterias.VDC71,bateria.getVDC71());
        valores.put(FilasDB.ColumnasBaterias.VDC72,bateria.getVDC72());
        valores.put(FilasDB.ColumnasBaterias.VDC73,bateria.getVDC73());
        valores.put(FilasDB.ColumnasBaterias.VDC74,bateria.getVDC74());
        valores.put(FilasDB.ColumnasBaterias.VDC75,bateria.getVDC75());
        valores.put(FilasDB.ColumnasBaterias.VDC76,bateria.getVDC76());
        valores.put(FilasDB.ColumnasBaterias.VDC77,bateria.getVDC77());
        valores.put(FilasDB.ColumnasBaterias.VDC78,bateria.getVDC78());
        valores.put(FilasDB.ColumnasBaterias.VDC79,bateria.getVDC79());
        valores.put(FilasDB.ColumnasBaterias.VDC80,bateria.getVDC80());
        valores.put(FilasDB.ColumnasBaterias.VDC81,bateria.getVDC81());
        valores.put(FilasDB.ColumnasBaterias.VDC82,bateria.getVDC82());
        valores.put(FilasDB.ColumnasBaterias.VDC83,bateria.getVDC83());
        valores.put(FilasDB.ColumnasBaterias.VDC84,bateria.getVDC84());
        valores.put(FilasDB.ColumnasBaterias.VDC85,bateria.getVDC85());
        valores.put(FilasDB.ColumnasBaterias.VDC86,bateria.getVDC86());
        valores.put(FilasDB.ColumnasBaterias.VDC87,bateria.getVDC87());
        valores.put(FilasDB.ColumnasBaterias.VDC88,bateria.getVDC88());
        valores.put(FilasDB.ColumnasBaterias.VDC89,bateria.getVDC89());
        valores.put(FilasDB.ColumnasBaterias.VDC90,bateria.getVDC90());
        valores.put(FilasDB.ColumnasBaterias.VDC91,bateria.getVDC91());
        valores.put(FilasDB.ColumnasBaterias.VDC92,bateria.getVDC92());
        valores.put(FilasDB.ColumnasBaterias.VDC93,bateria.getVDC93());
        valores.put(FilasDB.ColumnasBaterias.VDC94,bateria.getVDC94());
        valores.put(FilasDB.ColumnasBaterias.VDC95,bateria.getVDC95());
        valores.put(FilasDB.ColumnasBaterias.VDC96,bateria.getVDC96());
        valores.put(FilasDB.ColumnasBaterias.VDC97,bateria.getVDC97());
        valores.put(FilasDB.ColumnasBaterias.VDC98,bateria.getVDC98());
        valores.put(FilasDB.ColumnasBaterias.VDC99,bateria.getVDC99());
        valores.put(FilasDB.ColumnasBaterias.VDC100,bateria.getVDC100());
        valores.put(FilasDB.ColumnasBaterias.VDC101,bateria.getVDC101());
        valores.put(FilasDB.ColumnasBaterias.VDC102,bateria.getVDC102());
        valores.put(FilasDB.ColumnasBaterias.VDC103,bateria.getVDC103());
        valores.put(FilasDB.ColumnasBaterias.VDC104,bateria.getVDC104());
        valores.put(FilasDB.ColumnasBaterias.VDC105,bateria.getVDC105());
        valores.put(FilasDB.ColumnasBaterias.VDC106,bateria.getVDC106());
        valores.put(FilasDB.ColumnasBaterias.VDC107,bateria.getVDC107());
        valores.put(FilasDB.ColumnasBaterias.VDC108,bateria.getVDC108());
        valores.put(FilasDB.ColumnasBaterias.VDC109,bateria.getVDC109());
        valores.put(FilasDB.ColumnasBaterias.VDC110,bateria.getVDC110());
        valores.put(FilasDB.ColumnasBaterias.VDC111,bateria.getVDC111());
        valores.put(FilasDB.ColumnasBaterias.VDC112,bateria.getVDC112());
        valores.put(FilasDB.ColumnasBaterias.VDC113,bateria.getVDC113());
        valores.put(FilasDB.ColumnasBaterias.VDC114,bateria.getVDC114());
        valores.put(FilasDB.ColumnasBaterias.VDC115,bateria.getVDC115());
        valores.put(FilasDB.ColumnasBaterias.VDC116,bateria.getVDC116());
        valores.put(FilasDB.ColumnasBaterias.VDC117,bateria.getVDC117());
        valores.put(FilasDB.ColumnasBaterias.VDC118,bateria.getVDC118());
        valores.put(FilasDB.ColumnasBaterias.VDC119,bateria.getVDC119());
        valores.put(FilasDB.ColumnasBaterias.VDC120,bateria.getVDC120());
        valores.put(FilasDB.ColumnasBaterias.VAC1,bateria.getVAC1());
        valores.put(FilasDB.ColumnasBaterias.VAC2,bateria.getVAC2());
        valores.put(FilasDB.ColumnasBaterias.VAC3,bateria.getVAC3());
        valores.put(FilasDB.ColumnasBaterias.VAC4,bateria.getVAC4());
        valores.put(FilasDB.ColumnasBaterias.VAC5,bateria.getVAC5());
        valores.put(FilasDB.ColumnasBaterias.VAC6,bateria.getVAC6());
        valores.put(FilasDB.ColumnasBaterias.VAC7,bateria.getVAC7());
        valores.put(FilasDB.ColumnasBaterias.VAC8,bateria.getVAC8());
        valores.put(FilasDB.ColumnasBaterias.VAC9,bateria.getVAC9());
        valores.put(FilasDB.ColumnasBaterias.VAC10,bateria.getVAC10());
        valores.put(FilasDB.ColumnasBaterias.VAC11,bateria.getVAC11());
        valores.put(FilasDB.ColumnasBaterias.VAC12,bateria.getVAC12());
        valores.put(FilasDB.ColumnasBaterias.VAC13,bateria.getVAC13());
        valores.put(FilasDB.ColumnasBaterias.VAC14,bateria.getVAC14());
        valores.put(FilasDB.ColumnasBaterias.VAC15,bateria.getVAC15());
        valores.put(FilasDB.ColumnasBaterias.VAC16,bateria.getVAC16());
        valores.put(FilasDB.ColumnasBaterias.VAC17,bateria.getVAC17());
        valores.put(FilasDB.ColumnasBaterias.VAC18,bateria.getVAC18());
        valores.put(FilasDB.ColumnasBaterias.VAC19,bateria.getVAC19());
        valores.put(FilasDB.ColumnasBaterias.VAC20,bateria.getVAC20());
        valores.put(FilasDB.ColumnasBaterias.VAC21,bateria.getVAC21());
        valores.put(FilasDB.ColumnasBaterias.VAC22,bateria.getVAC22());
        valores.put(FilasDB.ColumnasBaterias.VAC23,bateria.getVAC23());
        valores.put(FilasDB.ColumnasBaterias.VAC24,bateria.getVAC24());
        valores.put(FilasDB.ColumnasBaterias.VAC25,bateria.getVAC25());
        valores.put(FilasDB.ColumnasBaterias.VAC26,bateria.getVAC26());
        valores.put(FilasDB.ColumnasBaterias.VAC27,bateria.getVAC27());
        valores.put(FilasDB.ColumnasBaterias.VAC28,bateria.getVAC28());
        valores.put(FilasDB.ColumnasBaterias.VAC29,bateria.getVAC29());
        valores.put(FilasDB.ColumnasBaterias.VAC30,bateria.getVAC30());
        valores.put(FilasDB.ColumnasBaterias.VAC31,bateria.getVAC31());
        valores.put(FilasDB.ColumnasBaterias.VAC32,bateria.getVAC32());
        valores.put(FilasDB.ColumnasBaterias.VAC33,bateria.getVAC33());
        valores.put(FilasDB.ColumnasBaterias.VAC34,bateria.getVAC34());
        valores.put(FilasDB.ColumnasBaterias.VAC35,bateria.getVAC35());
        valores.put(FilasDB.ColumnasBaterias.VAC36,bateria.getVAC36());
        valores.put(FilasDB.ColumnasBaterias.VAC37,bateria.getVAC37());
        valores.put(FilasDB.ColumnasBaterias.VAC38,bateria.getVAC38());
        valores.put(FilasDB.ColumnasBaterias.VAC39,bateria.getVAC39());
        valores.put(FilasDB.ColumnasBaterias.VAC40,bateria.getVAC40());
        valores.put(FilasDB.ColumnasBaterias.VAC41,bateria.getVAC41());
        valores.put(FilasDB.ColumnasBaterias.VAC42,bateria.getVAC42());
        valores.put(FilasDB.ColumnasBaterias.VAC43,bateria.getVAC43());
        valores.put(FilasDB.ColumnasBaterias.VAC44,bateria.getVAC44());
        valores.put(FilasDB.ColumnasBaterias.VAC45,bateria.getVAC45());
        valores.put(FilasDB.ColumnasBaterias.VAC46,bateria.getVAC46());
        valores.put(FilasDB.ColumnasBaterias.VAC47,bateria.getVAC47());
        valores.put(FilasDB.ColumnasBaterias.VAC48,bateria.getVAC48());
        valores.put(FilasDB.ColumnasBaterias.VAC49,bateria.getVAC49());
        valores.put(FilasDB.ColumnasBaterias.VAC50,bateria.getVAC50());
        valores.put(FilasDB.ColumnasBaterias.VAC51,bateria.getVAC51());
        valores.put(FilasDB.ColumnasBaterias.VAC52,bateria.getVAC52());
        valores.put(FilasDB.ColumnasBaterias.VAC53,bateria.getVAC53());
        valores.put(FilasDB.ColumnasBaterias.VAC54,bateria.getVAC54());
        valores.put(FilasDB.ColumnasBaterias.VAC55,bateria.getVAC55());
        valores.put(FilasDB.ColumnasBaterias.VAC56,bateria.getVAC56());
        valores.put(FilasDB.ColumnasBaterias.VAC57,bateria.getVAC57());
        valores.put(FilasDB.ColumnasBaterias.VAC58,bateria.getVAC58());
        valores.put(FilasDB.ColumnasBaterias.VAC59,bateria.getVAC59());
        valores.put(FilasDB.ColumnasBaterias.VAC60,bateria.getVAC60());
        valores.put(FilasDB.ColumnasBaterias.VAC61,bateria.getVAC61());
        valores.put(FilasDB.ColumnasBaterias.VAC62,bateria.getVAC62());
        valores.put(FilasDB.ColumnasBaterias.VAC63,bateria.getVAC63());
        valores.put(FilasDB.ColumnasBaterias.VAC64,bateria.getVAC64());
        valores.put(FilasDB.ColumnasBaterias.VAC65,bateria.getVAC65());
        valores.put(FilasDB.ColumnasBaterias.VAC66,bateria.getVAC66());
        valores.put(FilasDB.ColumnasBaterias.VAC67,bateria.getVAC67());
        valores.put(FilasDB.ColumnasBaterias.VAC68,bateria.getVAC68());
        valores.put(FilasDB.ColumnasBaterias.VAC69,bateria.getVAC69());
        valores.put(FilasDB.ColumnasBaterias.VAC70,bateria.getVAC70());
        valores.put(FilasDB.ColumnasBaterias.VAC71,bateria.getVAC71());
        valores.put(FilasDB.ColumnasBaterias.VAC72,bateria.getVAC72());
        valores.put(FilasDB.ColumnasBaterias.VAC73,bateria.getVAC73());
        valores.put(FilasDB.ColumnasBaterias.VAC74,bateria.getVAC74());
        valores.put(FilasDB.ColumnasBaterias.VAC75,bateria.getVAC75());
        valores.put(FilasDB.ColumnasBaterias.VAC76,bateria.getVAC76());
        valores.put(FilasDB.ColumnasBaterias.VAC77,bateria.getVAC77());
        valores.put(FilasDB.ColumnasBaterias.VAC78,bateria.getVAC78());
        valores.put(FilasDB.ColumnasBaterias.VAC79,bateria.getVAC79());
        valores.put(FilasDB.ColumnasBaterias.VAC80,bateria.getVAC80());
        valores.put(FilasDB.ColumnasBaterias.VAC81,bateria.getVAC81());
        valores.put(FilasDB.ColumnasBaterias.VAC82,bateria.getVAC82());
        valores.put(FilasDB.ColumnasBaterias.VAC83,bateria.getVAC83());
        valores.put(FilasDB.ColumnasBaterias.VAC84,bateria.getVAC84());
        valores.put(FilasDB.ColumnasBaterias.VAC85,bateria.getVAC85());
        valores.put(FilasDB.ColumnasBaterias.VAC86,bateria.getVAC86());
        valores.put(FilasDB.ColumnasBaterias.VAC87,bateria.getVAC87());
        valores.put(FilasDB.ColumnasBaterias.VAC88,bateria.getVAC88());
        valores.put(FilasDB.ColumnasBaterias.VAC89,bateria.getVAC89());
        valores.put(FilasDB.ColumnasBaterias.VAC90,bateria.getVAC90());
        valores.put(FilasDB.ColumnasBaterias.VAC91,bateria.getVAC91());
        valores.put(FilasDB.ColumnasBaterias.VAC92,bateria.getVAC92());
        valores.put(FilasDB.ColumnasBaterias.VAC93,bateria.getVAC93());
        valores.put(FilasDB.ColumnasBaterias.VAC94,bateria.getVAC94());
        valores.put(FilasDB.ColumnasBaterias.VAC95,bateria.getVAC95());
        valores.put(FilasDB.ColumnasBaterias.VAC96,bateria.getVAC96());
        valores.put(FilasDB.ColumnasBaterias.VAC97,bateria.getVAC97());
        valores.put(FilasDB.ColumnasBaterias.VAC98,bateria.getVAC98());
        valores.put(FilasDB.ColumnasBaterias.VAC99,bateria.getVAC99());
        valores.put(FilasDB.ColumnasBaterias.VAC100,bateria.getVAC100());
        valores.put(FilasDB.ColumnasBaterias.VAC101,bateria.getVAC101());
        valores.put(FilasDB.ColumnasBaterias.VAC102,bateria.getVAC102());
        valores.put(FilasDB.ColumnasBaterias.VAC103,bateria.getVAC103());
        valores.put(FilasDB.ColumnasBaterias.VAC104,bateria.getVAC104());
        valores.put(FilasDB.ColumnasBaterias.VAC105,bateria.getVAC105());
        valores.put(FilasDB.ColumnasBaterias.VAC106,bateria.getVAC106());
        valores.put(FilasDB.ColumnasBaterias.VAC107,bateria.getVAC107());
        valores.put(FilasDB.ColumnasBaterias.VAC108,bateria.getVAC108());
        valores.put(FilasDB.ColumnasBaterias.VAC109,bateria.getVAC109());
        valores.put(FilasDB.ColumnasBaterias.VAC110,bateria.getVAC110());
        valores.put(FilasDB.ColumnasBaterias.VAC111,bateria.getVAC111());
        valores.put(FilasDB.ColumnasBaterias.VAC112,bateria.getVAC112());
        valores.put(FilasDB.ColumnasBaterias.VAC113,bateria.getVAC113());
        valores.put(FilasDB.ColumnasBaterias.VAC114,bateria.getVAC114());
        valores.put(FilasDB.ColumnasBaterias.VAC115,bateria.getVAC115());
        valores.put(FilasDB.ColumnasBaterias.VAC116,bateria.getVAC116());
        valores.put(FilasDB.ColumnasBaterias.VAC117,bateria.getVAC117());
        valores.put(FilasDB.ColumnasBaterias.VAC118,bateria.getVAC118());
        valores.put(FilasDB.ColumnasBaterias.VAC119,bateria.getVAC119());
        valores.put(FilasDB.ColumnasBaterias.VAC120,bateria.getVAC120());
        valores.put(FilasDB.ColumnasBaterias.ModeloMarca,bateria.getModeloMarca());
        valores.put(FilasDB.ColumnasBaterias.AparenciaLimpieza,bateria.getAparenciaLimpieza());
        valores.put(FilasDB.ColumnasBaterias.JarrasCubiertasSellado,bateria.getJarrasCubiertasSellado());
        valores.put(FilasDB.ColumnasBaterias.TemperaturaBaterias,bateria.getTemperaturaBaterias());
        valores.put(FilasDB.ColumnasBaterias.TemperaturaAmbiente,bateria.getTemperaturaAmbiente());
        valores.put(FilasDB.ColumnasBaterias.Torque,bateria.getTorque());
        valores.put(FilasDB.ColumnasBaterias.Terminales,bateria.getTerminales());
        valores.put(FilasDB.ColumnasBaterias.CodigoFecha,bateria.getCodigoFecha());
        valores.put(FilasDB.ColumnasBaterias.AñosServicio,bateria.getAñosServicio());
        valores.put(FilasDB.ColumnasBaterias.ConectoresTornillos,bateria.getConectoresTornillos());
        valores.put(FilasDB.ColumnasBaterias.VoltajeFlotacionVDC,bateria.getVoltajeFlotacionVDC());
        valores.put(FilasDB.ColumnasBaterias.CorrienteFlotacion,bateria.getCorrienteFlotacion());
        valores.put(FilasDB.ColumnasBaterias.CorrienteRizo,bateria.getCorrienteRizo());
        valores.put(FilasDB.ColumnasBaterias.VoltajeRizo,bateria.getVoltajeRizo());
        valores.put(FilasDB.ColumnasBaterias.COMENT_cometarios,bateria.getCOMENT_cometarios());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad1,bateria.getMATE_cantidad1());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte1,bateria.getMATE_parte1());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica1,bateria.getMATE_especifica1());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad2,bateria.getMATE_cantidad2());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte2,bateria.getMATE_parte2());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica2,bateria.getMATE_especifica2());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad3,bateria.getMATE_cantidad3());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte3,bateria.getMATE_parte3());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica3,bateria.getMATE_especifica3());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad4,bateria.getMATE_cantidad4());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte4,bateria.getMATE_parte4());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica4,bateria.getMATE_especifica4());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad5,bateria.getMATE_cantidad5());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte5,bateria.getMATE_parte5());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica5,bateria.getMATE_especifica5());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad6,bateria.getMATE_cantidad6());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte6,bateria.getMATE_parte6());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica6,bateria.getMATE_especifica6());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad7,bateria.getMATE_cantidad7());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte7,bateria.getMATE_parte7());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica7,bateria.getMATE_especifica7());
        valores.put(FilasDB.ColumnasBaterias.MATE_cantidad8,bateria.getMATE_cantidad8());
        valores.put(FilasDB.ColumnasBaterias.MATE_parte8,bateria.getMATE_parte8());
        valores.put(FilasDB.ColumnasBaterias.MATE_especifica8,bateria.getMATE_especifica8());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo1,bateria.getMATE_equipo1());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid1,bateria.getMATE_nid1());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha1,bateria.getMATE_fecha1());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo2,bateria.getMATE_equipo2());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid2,bateria.getMATE_nid2());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha2,bateria.getMATE_fecha2());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo3,bateria.getMATE_equipo3());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid3,bateria.getMATE_nid3());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha3,bateria.getMATE_fecha3());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo4,bateria.getMATE_equipo4());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid4,bateria.getMATE_nid4());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha4,bateria.getMATE_fecha4());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo5,bateria.getMATE_equipo5());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid5,bateria.getMATE_nid5());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha5,bateria.getMATE_fecha5());
        valores.put(FilasDB.ColumnasBaterias.MATE_equipo6,bateria.getMATE_equipo6());
        valores.put(FilasDB.ColumnasBaterias.MATE_nid6,bateria.getMATE_nid6());
        valores.put(FilasDB.ColumnasBaterias.MATE_fecha6,bateria.getMATE_fecha6());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A1,bateria.getFOTOS_A1());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A2,bateria.getFOTOS_A2());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A3,bateria.getFOTOS_A3());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A4,bateria.getFOTOS_A4());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A5,bateria.getFOTOS_A5());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_A6,bateria.getFOTOS_A6());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D1,bateria.getFOTOS_D1());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D2,bateria.getFOTOS_D2());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D3,bateria.getFOTOS_D3());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D4,bateria.getFOTOS_D4());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D5,bateria.getFOTOS_D5());
        valores.put(FilasDB.ColumnasBaterias.FOTOS_D6,bateria.getFOTOS_D6());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_NOMBRE1,bateria.getFIRMA_NOMBRE1());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_CARGO1,bateria.getFIRMA_CARGO1());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_IMG1,bateria.getFIRMA_IMG1());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_NOMBRE2,bateria.getFIRMA_NOMBRE2());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_CARGO2,bateria.getFIRMA_CARGO2());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_IMG2,bateria.getFIRMA_IMG2());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_NOMBRE3,bateria.getFIRMA_NOMBRE3());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_CARGO3,bateria.getFIRMA_CARGO3());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_IMG3,bateria.getFIRMA_IMG3());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_EMPRESA,bateria.getFIRMA_EMPRESA());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_TELEFONO,bateria.getFIRMA_TELEFONO());
        valores.put(FilasDB.ColumnasBaterias.FIRMA_CORREO,bateria.getFIRMA_CORREO());
        valores.put(FilasDB.ColumnasBaterias.ADICI_NOMBRE1,bateria.getADICI_NOMBRE1());
        valores.put(FilasDB.ColumnasBaterias.ADICI_CORREO1,bateria.getADICI_CORREO1());
        valores.put(FilasDB.ColumnasBaterias.ADICI_NOMBRE2,bateria.getADICI_NOMBRE2());
        valores.put(FilasDB.ColumnasBaterias.ADICI_CORREO2,bateria.getADICI_CORREO2());
        valores.put(FilasDB.ColumnasBaterias.ADICI_NOMBRE3,bateria.getADICI_NOMBRE3());
        valores.put(FilasDB.ColumnasBaterias.ADICI_CORREO3,bateria.getADICI_CORREO3());
        valores.put(FilasDB.ColumnasBaterias.ADICI_NOMBRE4,bateria.getADICI_NOMBRE4());
        valores.put(FilasDB.ColumnasBaterias.ADICI_CORREO4,bateria.getADICI_CORREO4());
        valores.put(FilasDB.ColumnasBaterias.ADICI_NOMBRE5,bateria.getADICI_NOMBRE5());
        valores.put(FilasDB.ColumnasBaterias.ADICI_CORREO5,bateria.getADICI_CORREO5());





        //endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasBaterias.ID_fORMATO);
        String[] whereArgs = {keyFormato};

        int resultado = db.update(BaseDatos.Tablas.BATERIAS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarCalidad(EncuestaCalidadServicio item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasCaliad.IDFormato,item.getIDFormato());
        valores.put(FilasDB.ColumnasCaliad.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasCaliad.IdCliente,item.getIdCliente());
        valores.put(FilasDB.ColumnasCaliad.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasCaliad.Fecha,item.getFecha());
        valores.put(FilasDB.ColumnasCaliad.Contrato,item.getContrato());
        valores.put(FilasDB.ColumnasCaliad.NombreCliente,item.getNombreCliente());
        valores.put(FilasDB.ColumnasCaliad.ClienteFinal,item.getClienteFinal());
        valores.put(FilasDB.ColumnasCaliad.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasCaliad.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasCaliad.TipoEquipo,item.getTipoEquipo());
        valores.put(FilasDB.ColumnasCaliad.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasCaliad.EMail,item.getEMail());
        valores.put(FilasDB.ColumnasCaliad.ModeloEquipo,item.getModeloEquipo());
        valores.put(FilasDB.ColumnasCaliad.NoSerie,item.getNoSerie());
        valores.put(FilasDB.ColumnasCaliad.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasCaliad.NombreSitio,item.getNombreSitio());
        valores.put(FilasDB.ColumnasCaliad.I1,item.getI1());
        valores.put(FilasDB.ColumnasCaliad.I2,item.getI2());
        valores.put(FilasDB.ColumnasCaliad.I3,item.getI3());
        valores.put(FilasDB.ColumnasCaliad.IComentarios,item.getIComentarios());
        valores.put(FilasDB.ColumnasCaliad.II1,item.getII1());
        valores.put(FilasDB.ColumnasCaliad.II2,item.getII2());
        valores.put(FilasDB.ColumnasCaliad.II3,item.getII3());
        valores.put(FilasDB.ColumnasCaliad.II4,item.getII4());
        valores.put(FilasDB.ColumnasCaliad.IIComentarios,item.getIIComentarios());
        valores.put(FilasDB.ColumnasCaliad.III1,item.getIII1());
        valores.put(FilasDB.ColumnasCaliad.III2,item.getIII2());
        valores.put(FilasDB.ColumnasCaliad.III3,item.getIII3());
        valores.put(FilasDB.ColumnasCaliad.III4,item.getIII4());
        valores.put(FilasDB.ColumnasCaliad.IIIComentarios,item.getIIIComentarios());
        valores.put(FilasDB.ColumnasCaliad.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasCaliad.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasCaliad.FirmaClienteFinal,item.getFirmaClienteFinal());
        valores.put(FilasDB.ColumnasCaliad.FNombreCliente,item.getFNombreCliente());
        valores.put(FilasDB.ColumnasCaliad.FNombreVertiv,item.getFNombreVertiv());
        valores.put(FilasDB.ColumnasCaliad.FNombreClienteFinal,item.getFNombreClienteFinal());
        valores.put(FilasDB.ColumnasCaliad.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasCaliad.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasCaliad.TiposervicioOtro,item.getTiposervicioOtro());
        valores.put(FilasDB.ColumnasCaliad.Serie2,item.getSerie2());
        valores.put(FilasDB.ColumnasCaliad.Modelo2,item.getModelo2());

        valores.put(FilasDB.ColumnasCaliad.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasCaliad.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasCaliad.Recomienda,item.getRecomienda());
        valores.put(FilasDB.ColumnasCaliad.CRCNA,item.getCRCNA());
        valores.put(FilasDB.ColumnasCaliad.CRC1,item.getCRC1());
        valores.put(FilasDB.ColumnasCaliad.CRC2,item.getCRC2());
        valores.put(FilasDB.ColumnasCaliad.CRC3,item.getCRC3());
        valores.put(FilasDB.ColumnasCaliad.CRCComentario,item.getCRCComentario());



//endregion
        String whereClause = String.format("%s=? ", FilasDB.ColumnasCaliad.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.CALIDAD, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarBestel1(Bestel1 item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasBestel1.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasBestel1.IdCliente,item.getCliente());
        valores.put(FilasDB.ColumnasBestel1.Exitoso,item.getExitoso());
        valores.put(FilasDB.ColumnasBestel1.Error,item.getError());
        valores.put(FilasDB.ColumnasBestel1.IdBestelNivel1,item.getIdBestelNivel1());
        valores.put(FilasDB.ColumnasBestel1.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasBestel1.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasBestel1.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasBestel1.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasBestel1.IdRegion,item.getRegion());
        valores.put(FilasDB.ColumnasBestel1.IdEstado,item.getEstado());
        valores.put(FilasDB.ColumnasBestel1.TipoSitio,item.getTipoSitio());
        valores.put(FilasDB.ColumnasBestel1.NombreSitio,item.getNombreSitio());
        valores.put(FilasDB.ColumnasBestel1.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasBestel1.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasBestel1.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasBestel1.Task,item.getTask());
        valores.put(FilasDB.ColumnasBestel1.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasBestel1.Frecuencia,item.getFrecuencia());
        valores.put(FilasDB.ColumnasBestel1.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasBestel1.Email,item.getEmail());
        valores.put(FilasDB.ColumnasBestel1.AATemperaturaEstatus,item.getAATemperaturaEstatus());
        valores.put(FilasDB.ColumnasBestel1.AATemperaturaComentarios,item.getAATemperaturaComentarios());
        valores.put(FilasDB.ColumnasBestel1.AACondensadoraEstatus,item.getAACondensadoraEstatus());
        valores.put(FilasDB.ColumnasBestel1.AACondensadoraComentarios,item.getAACondensadoraComentarios());
        valores.put(FilasDB.ColumnasBestel1.AAEvaporadoraEstatus,item.getAAEvaporadoraEstatus());
        valores.put(FilasDB.ColumnasBestel1.AAEvaporadoraComentarios,item.getAAEvaporadoraComentarios());
        valores.put(FilasDB.ColumnasBestel1.AASerpentinEstatus,item.getAASerpentinEstatus());
        valores.put(FilasDB.ColumnasBestel1.AASerpentinComentarios,item.getAASerpentinComentarios());
        valores.put(FilasDB.ColumnasBestel1.AAFugaGasEstatus,item.getAAFugaGasEstatus());
        valores.put(FilasDB.ColumnasBestel1.AAFugaGasComentarios,item.getAAFugaGasComentarios());
        valores.put(FilasDB.ColumnasBestel1.AAAlimentacionEstatus,item.getAAAlimentacionEstatus());
        valores.put(FilasDB.ColumnasBestel1.AAAlimentacionComentarios,item.getAAAlimentacionComentarios());
        valores.put(FilasDB.ColumnasBestel1.AAFiltrosEstatus,item.getAAFiltrosEstatus());
        valores.put(FilasDB.ColumnasBestel1.AAFiltrosComentarios,item.getAAFiltrosComentarios());
        valores.put(FilasDB.ColumnasBestel1.AALimpiezaGeneralEstatus,item.getAALimpiezaGeneralEstatus());
        valores.put(FilasDB.ColumnasBestel1.AALimpiezaGeneralComentarios,item.getAALimpiezaGeneralComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECIluminaciionEstatus,item.getECIluminaciionEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECIluminaciionComentarios,item.getECIluminaciionComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECPinturaEstatus,item.getECPinturaEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECPinturaComentarios,item.getECPinturaComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECPisosEstatus,item.getECPisosEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECPisosComentarios,item.getECPisosComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECImpermeEstatus,item.getECImpermeEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECImpermeComentarios,item.getECImpermeComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECHidrosanitarioEstatus,item.getECHidrosanitarioEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECHidrosanitarioComentarios,item.getECHidrosanitarioComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECHerrejesEstatus,item.getECHerrejesEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECHerrejesComentarios,item.getECHerrejesComentarios());
        valores.put(FilasDB.ColumnasBestel1.ECLimpiezaGeneralEstatus,item.getECLimpiezaGeneralEstatus());
        valores.put(FilasDB.ColumnasBestel1.ECLimpiezaGeneralComentarios,item.getECLimpiezaGeneralComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBAmperajeEstatus,item.getPFBAmperajeEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBAmperajeComentarios,item.getPFBAmperajeComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBConsumoPlantaEstatus,item.getPFBConsumoPlantaEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBConsumoPlantaComentarios,item.getPFBConsumoPlantaComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBRectificadoresEstatus,item.getPFBRectificadoresEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBRectificadoresComentarios,item.getPFBRectificadoresComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBSistemaInversorEstatus,item.getPFBSistemaInversorEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBSistemaInversorComentarios,item.getPFBSistemaInversorComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBBancosBateriasEstatus,item.getPFBBancosBateriasEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBBancosBateriasComentarios,item.getPFBBancosBateriasComentarios());
        valores.put(FilasDB.ColumnasBestel1.PFBTablerosEstatus,item.getPFBTablerosEstatus());
        valores.put(FilasDB.ColumnasBestel1.PFBTablerosComentarios,item.getPFBTablerosComentarios());
        valores.put(FilasDB.ColumnasBestel1.SUAlimentacionElectricaEstatus,item.getSUAlimentacionElectricaEstatus());
        valores.put(FilasDB.ColumnasBestel1.SUAlimentacionElectricaComentarios,item.getSUAlimentacionElectricaComentarios());
        valores.put(FilasDB.ColumnasBestel1.SUAlarmasEstatus,item.getSUAlarmasEstatus());
        valores.put(FilasDB.ColumnasBestel1.SUAlarmasComentarios,item.getSUAlarmasComentarios());
        valores.put(FilasDB.ColumnasBestel1.SUCargaEstatus,item.getSUCargaEstatus());
        valores.put(FilasDB.ColumnasBestel1.SUCargaComentarios,item.getSUCargaComentarios());
        valores.put(FilasDB.ColumnasBestel1.SUDescargaEstatus,item.getSUDescargaEstatus());
        valores.put(FilasDB.ColumnasBestel1.SUDescargaComentarios,item.getSUDescargaComentarios());
        valores.put(FilasDB.ColumnasBestel1.SCISistemaEstatus,item.getSCISistemaEstatus());
        valores.put(FilasDB.ColumnasBestel1.SCISistemaComentarios,item.getSCISistemaComentarios());
        valores.put(FilasDB.ColumnasBestel1.SCIDetectoresEstatus,item.getSCIDetectoresEstatus());
        valores.put(FilasDB.ColumnasBestel1.SCIDetectoresComentarios,item.getSCIDetectoresComentarios());
        valores.put(FilasDB.ColumnasBestel1.SCIExtintoresEstatus,item.getSCIExtintoresEstatus());
        valores.put(FilasDB.ColumnasBestel1.SCIExtintoresComentarios,item.getSCIExtintoresComentarios());
        valores.put(FilasDB.ColumnasBestel1.SCIGranadaTanquesEstatus,item.getSCIGranadaTanquesEstatus());
        valores.put(FilasDB.ColumnasBestel1.SCIGranadaTanquesComentarios,item.getSCIGranadaTanquesComentarios());
        valores.put(FilasDB.ColumnasBestel1.SCIFechaCaducidadEstatus,item.getSCIFechaCaducidadEstatus());
        valores.put(FilasDB.ColumnasBestel1.SCIFechaCaducidadComentraios,item.getSCIFechaCaducidadComentraios());
        valores.put(FilasDB.ColumnasBestel1.PEFugasAceiteEstatus,item.getPEFugasAceiteEstatus());
        valores.put(FilasDB.ColumnasBestel1.PEFugasAceiteComentarios,item.getPEFugasAceiteComentarios());
        valores.put(FilasDB.ColumnasBestel1.PEFiltrosEstatus,item.getPEFiltrosEstatus());
        valores.put(FilasDB.ColumnasBestel1.PEFiltrosComentarios,item.getPEFiltrosComentarios());
        valores.put(FilasDB.ColumnasBestel1.PETemperaturaEstatus,item.getPETemperaturaEstatus());
        valores.put(FilasDB.ColumnasBestel1.PETemperaturaComentarios,item.getPETemperaturaComentarios());
        valores.put(FilasDB.ColumnasBestel1.PEBandasEstatus,item.getPEBandasEstatus());
        valores.put(FilasDB.ColumnasBestel1.PEBandasComentarios,item.getPEBandasComentarios());
        valores.put(FilasDB.ColumnasBestel1.PEBateriasEstatus,item.getPEBateriasEstatus());
        valores.put(FilasDB.ColumnasBestel1.PEBateriasComentarios,item.getPEBateriasComentarios());
        valores.put(FilasDB.ColumnasBestel1.PELubricacionEstatus,item.getPELubricacionEstatus());
        valores.put(FilasDB.ColumnasBestel1.PELubricacionComentarios,item.getPELubricacionComentarios());
        valores.put(FilasDB.ColumnasBestel1.PECombustibleEstatus,item.getPECombustibleEstatus());
        valores.put(FilasDB.ColumnasBestel1.PECombustibleComentarios,item.getPECombustibleComentarios());
        valores.put(FilasDB.ColumnasBestel1.PEArranqueManualEstatus,item.getPEArranqueManualEstatus());
        valores.put(FilasDB.ColumnasBestel1.PEArranqueManualComentarios,item.getPEArranqueManualComentarios());
        valores.put(FilasDB.ColumnasBestel1.PELimpiezaGenetalEstatus,item.getPELimpiezaGenetalEstatus());
        valores.put(FilasDB.ColumnasBestel1.PELimpiezaGenetalComentarios,item.getPELimpiezaGenetalComentarios());
        valores.put(FilasDB.ColumnasBestel1.SALectorasEstatus,item.getSALectorasEstatus());
        valores.put(FilasDB.ColumnasBestel1.SALectorasComentarios,item.getSALectorasComentarios());
        valores.put(FilasDB.ColumnasBestel1.SATablerosControlEstatus,item.getSATablerosControlEstatus());
        valores.put(FilasDB.ColumnasBestel1.SATablerosControlComentarios,item.getSATablerosControlComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSBarrasPuestaTierraEstatus,item.getSSBarrasPuestaTierraEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSBarrasPuestaTierraComentarios,item.getSSBarrasPuestaTierraComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSConexionPuestaTierraEstatus,item.getSSConexionPuestaTierraEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSConexionPuestaTierraComentarios,item.getSSConexionPuestaTierraComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSTransformadorEstatus,item.getSSTransformadorEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSTransformadorComentarios,item.getSSTransformadorComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSFusiblesEstatus,item.getSSFusiblesEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSFusiblesComentarios,item.getSSFusiblesComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSTemperaturaEstatus,item.getSSTemperaturaEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSTemperaturaComentarios,item.getSSTemperaturaComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSCuchillasEstatus,item.getSSCuchillasEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSCuchillasComentarios,item.getSSCuchillasComentarios());
        valores.put(FilasDB.ColumnasBestel1.SSInterruptoresEstatus,item.getSSInterruptoresEstatus());
        valores.put(FilasDB.ColumnasBestel1.SSInterruptoresComentarios,item.getSSInterruptoresComentarios());
        valores.put(FilasDB.ColumnasBestel1.TELimpizaGeneralEstatus,item.getTELimpizaGeneralEstatus());
        valores.put(FilasDB.ColumnasBestel1.TELimpizaGeneralComentarios,item.getTELimpizaGeneralComentarios());
        valores.put(FilasDB.ColumnasBestel1.TEAnclasRetenidosEstatus,item.getTEAnclasRetenidosEstatus());
        valores.put(FilasDB.ColumnasBestel1.TEAnclasRetenidosComentarios,item.getTEAnclasRetenidosComentarios());
        valores.put(FilasDB.ColumnasBestel1.TELucesObstruccionEstatus,item.getTELucesObstruccionEstatus());
        valores.put(FilasDB.ColumnasBestel1.TELucesObstruccionComentarios,item.getTELucesObstruccionComentarios());
        valores.put(FilasDB.ColumnasBestel1.TETornilleriaEstatus,item.getTETornilleriaEstatus());
        valores.put(FilasDB.ColumnasBestel1.TETornilleriaComentarios,item.getTETornilleriaComentarios());
        valores.put(FilasDB.ColumnasBestel1.TEPuestaTierraEstatus,item.getTEPuestaTierraEstatus());
        valores.put(FilasDB.ColumnasBestel1.TEPuestaTierraComentarios,item.getTEPuestaTierraComentarios());
        valores.put(FilasDB.ColumnasBestel1.TESistemaApartaRayosEststus,item.getTESistemaApartaRayosEststus());
        valores.put(FilasDB.ColumnasBestel1.TESistemaApartaRayosComentarios,item.getTESistemaApartaRayosComentarios());
        valores.put(FilasDB.ColumnasBestel1.HFugasEstatus,item.getHFugasEstatus());
        valores.put(FilasDB.ColumnasBestel1.HFugasComentarios,item.getHFugasComentarios());
        valores.put(FilasDB.ColumnasBestel1.HBombasEstatus,item.getHBombasEstatus());
        valores.put(FilasDB.ColumnasBestel1.HBombasComentarios,item.getHBombasComentarios());
        valores.put(FilasDB.ColumnasBestel1.Otros,item.getOtros());
        valores.put(FilasDB.ColumnasBestel1.Comentarios,item.getComentarios());
        valores.put(FilasDB.ColumnasBestel1.Materiales,item.getMateriales());
        valores.put(FilasDB.ColumnasBestel1.EDSRTiempo,item.getEDSRTiempo());
        valores.put(FilasDB.ColumnasBestel1.EDSRCalidad,item.getEDSRCalidad());
        valores.put(FilasDB.ColumnasBestel1.EDSREvalucionSitio,item.getEDSREvalucionSitio());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto1,item.getAntesFoto1());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto2,item.getAntesFoto2());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto3,item.getAntesFoto3());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto4,item.getAntesFoto4());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto5,item.getAntesFoto5());
        valores.put(FilasDB.ColumnasBestel1.AntesFoto6,item.getAntesFoto6());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto1,item.getDespuesFoto1());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto2,item.getDespuesFoto2());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto3,item.getDespuesFoto3());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto4,item.getDespuesFoto4());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto5,item.getDespuesFoto5());
        valores.put(FilasDB.ColumnasBestel1.DespuesFoto6,item.getDespuesFoto6());
        valores.put(FilasDB.ColumnasBestel1.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasBestel1.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasBestel1.proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasBestel1.referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasBestel1.modelo,item.getModelo());
        valores.put(FilasDB.ColumnasBestel1.L1,item.getL1());
        valores.put(FilasDB.ColumnasBestel1.L2,item.getSSL2());
        valores.put(FilasDB.ColumnasBestel1.L3,item.getSSL3());
        valores.put(FilasDB.ColumnasBestel1.A1,item.getSSA1());
        valores.put(FilasDB.ColumnasBestel1.A2,item.getSSA2());
        valores.put(FilasDB.ColumnasBestel1.A3,item.getSSA3());
        valores.put(FilasDB.ColumnasBestel1.Otros2,item.getOtros1());
        valores.put(FilasDB.ColumnasBestel1.Comentarios2,item.getComentarios1());
        valores.put(FilasDB.ColumnasBestel1.Otros3,item.getOtros2());
        valores.put(FilasDB.ColumnasBestel1.Comentarios3,item.getComentarios2());
        valores.put(FilasDB.ColumnasBestel1.Otros4,item.getOtros3());
        valores.put(FilasDB.ColumnasBestel1.Comentarios4,item.getComentarios3());
        valores.put(FilasDB.ColumnasBestel1.Otros5,item.getOtros4());
        valores.put(FilasDB.ColumnasBestel1.Comentarios5,item.getComentarios4());
        valores.put(FilasDB.ColumnasBestel1.Otros6,item.getOtros5());
        valores.put(FilasDB.ColumnasBestel1.Comentarios6,item.getComentarios5());
        valores.put(FilasDB.ColumnasBestel1.Otros7,item.getOtros6());
        valores.put(FilasDB.ColumnasBestel1.Comentarios7,item.getComentarios6());
        valores.put(FilasDB.ColumnasBestel1.Otros8,item.getOtros7());
        valores.put(FilasDB.ColumnasBestel1.Comentarios8,item.getComentarios7());
        valores.put(FilasDB.ColumnasBestel1.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasBestel1.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasBestel1.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasBestel1.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasBestel1.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasBestel1.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasBestel1.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasBestel1.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasBestel1.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasBestel1.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasBestel1.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasBestel1.AD_CORREO5,item.getAD_CORREO5());


//endregion
        String whereClause = String.format("%s=? ", FilasDB.ColumnasBestel1.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.BESTEL1, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarBestel2(Bestel2 item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasBestel2.dUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasBestel2.IdCliente,item.getIdCliente());
        valores.put(FilasDB.ColumnasBestel2.Exitoso,item.getExitoso());
        valores.put(FilasDB.ColumnasBestel2.Error,item.getError());
        valores.put(FilasDB.ColumnasBestel2.IdBestelNivel2,item.getIdBestelNivel2());
        valores.put(FilasDB.ColumnasBestel2.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasBestel2.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasBestel2.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasBestel2.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasBestel2.Task,item.getTask());
        valores.put(FilasDB.ColumnasBestel2.IdRegion,item.getIdRegion());
        valores.put(FilasDB.ColumnasBestel2.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasBestel2.IdEstado,item.getIdEstado());
        valores.put(FilasDB.ColumnasBestel2.Frecuencia,item.getFrecuencia());
        valores.put(FilasDB.ColumnasBestel2.TipoSitio,item.getTipoSitio());
        valores.put(FilasDB.ColumnasBestel2.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasBestel2.NombreSitio,item.getNombreSitio());
        valores.put(FilasDB.ColumnasBestel2.Email,item.getEmail());
        valores.put(FilasDB.ColumnasBestel2.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasBestel2.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasBestel2.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasBestel2.AATemperatura,item.getAATemperatura());
        valores.put(FilasDB.ColumnasBestel2.AACondensadora,item.getAACondensadora());
        valores.put(FilasDB.ColumnasBestel2.AAEvaporadora,item.getAAEvaporadora());
        valores.put(FilasDB.ColumnasBestel2.AASerpentin,item.getAASerpentin());
        valores.put(FilasDB.ColumnasBestel2.AAFugaGas,item.getAAFugaGas());
        valores.put(FilasDB.ColumnasBestel2.AAVoltajeL1,item.getAAVoltajeL1());
        valores.put(FilasDB.ColumnasBestel2.AAVoltajeL2,item.getAAVoltajeL2());
        valores.put(FilasDB.ColumnasBestel2.AAVoltajeL3,item.getAAVoltajeL3());
        valores.put(FilasDB.ColumnasBestel2.AAAmperajeL1,item.getAAAmperajeL1());
        valores.put(FilasDB.ColumnasBestel2.AAAmperajeL2,item.getAAAmperajeL2());
        valores.put(FilasDB.ColumnasBestel2.AAAmperajeL3,item.getAAAmperajeL3());
        valores.put(FilasDB.ColumnasBestel2.AAValvulas,item.getAAValvulas());
        valores.put(FilasDB.ColumnasBestel2.AATermostatos,item.getAATermostatos());
        valores.put(FilasDB.ColumnasBestel2.AABombas,item.getAABombas());
        valores.put(FilasDB.ColumnasBestel2.AAPresionAlta,item.getAAPresionAlta());
        valores.put(FilasDB.ColumnasBestel2.AAPresionBaja,item.getAAPresionBaja());
        valores.put(FilasDB.ColumnasBestel2.AAFiltros,item.getAAFiltros());
        valores.put(FilasDB.ColumnasBestel2.AALimpiezaGeneral,item.getAALimpiezaGeneral());
        valores.put(FilasDB.ColumnasBestel2.ECIliminacion,item.getECIliminacion());
        valores.put(FilasDB.ColumnasBestel2.ECPinturaMuros,item.getECPinturaMuros());
        valores.put(FilasDB.ColumnasBestel2.ECPisos,item.getECPisos());
        valores.put(FilasDB.ColumnasBestel2.ECImpermeabilizacion,item.getECImpermeabilizacion());
        valores.put(FilasDB.ColumnasBestel2.ECHidrosanitario,item.getECHidrosanitario());
        valores.put(FilasDB.ColumnasBestel2.ECHerrejes,item.getECHerrejes());
        valores.put(FilasDB.ColumnasBestel2.ECLimpiezaGeneral,item.getECLimpiezaGeneral());
        valores.put(FilasDB.ColumnasBestel2.PFVoltajeSalida,item.getPFVoltajeSalida());
        valores.put(FilasDB.ColumnasBestel2.PFCorrienteDisplay,item.getPFCorrienteDisplay());
        valores.put(FilasDB.ColumnasBestel2.PFTensioDispaly,item.getPFTensioDispaly());
        valores.put(FilasDB.ColumnasBestel2.PFTemSalas,item.getPFTemSalas());
        valores.put(FilasDB.ColumnasBestel2.PFTensionl1l2,item.getPFTensionl1l2());
        valores.put(FilasDB.ColumnasBestel2.PFTensionl2l3,item.getPFTensionl2l3());
        valores.put(FilasDB.ColumnasBestel2.PFTensionl1l3,item.getPFTensionl1l3());
        valores.put(FilasDB.ColumnasBestel2.PFCorrientesL1,item.getPFCorrientesL1());
        valores.put(FilasDB.ColumnasBestel2.PFCorrientesL2,item.getPFCorrientesL2());
        valores.put(FilasDB.ColumnasBestel2.PFCorrientesL3,item.getPFCorrientesL3());
        valores.put(FilasDB.ColumnasBestel2.PFVoltajeNL1,item.getPFVoltajeNL1());
        valores.put(FilasDB.ColumnasBestel2.PFVoltajeNL2,item.getPFVoltajeNL2());
        valores.put(FilasDB.ColumnasBestel2.PFVoltajeNL3,item.getPFVoltajeNL3());
        valores.put(FilasDB.ColumnasBestel2.PFVoltajeFaseNeutro,item.getPFVoltajeFaseNeutro());
        valores.put(FilasDB.ColumnasBestel2.PFRectificadoresTemp,item.getPFRectificadoresTemp());
        valores.put(FilasDB.ColumnasBestel2.PFFechaControl,item.getPFFechaControl());
        valores.put(FilasDB.ColumnasBestel2.PFLimpieza,item.getPFLimpieza());
        valores.put(FilasDB.ColumnasBestel2.SCSistema,item.getSCSistema());
        valores.put(FilasDB.ColumnasBestel2.SCDetectores,item.getSCDetectores());
        valores.put(FilasDB.ColumnasBestel2.SCExtintores,item.getSCExtintores());
        valores.put(FilasDB.ColumnasBestel2.SCBotesAreneros,item.getSCBotesAreneros());
        valores.put(FilasDB.ColumnasBestel2.SCFechaCaducidad,item.getSCFechaCaducidad());
        valores.put(FilasDB.ColumnasBestel2.SALectoras,item.getSALectoras());
        valores.put(FilasDB.ColumnasBestel2.SASupervisoresPuertas,item.getSASupervisoresPuertas());
        valores.put(FilasDB.ColumnasBestel2.SACCTV,item.getSACCTV());
        valores.put(FilasDB.ColumnasBestel2.SABaterias,item.getSABaterias());
        valores.put(FilasDB.ColumnasBestel2.SATablerosControl,item.getSATablerosControl());
        valores.put(FilasDB.ColumnasBestel2.SUAlarmas,item.getSUAlarmas());
        valores.put(FilasDB.ColumnasBestel2.SUTemperatura,item.getSUTemperatura());
        valores.put(FilasDB.ColumnasBestel2.SUCapacitores,item.getSUCapacitores());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeEntradaL1,item.getSUVoltajeEntradaL1());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeEntradaL2,item.getSUVoltajeEntradaL2());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeEntradaL3,item.getSUVoltajeEntradaL3());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeByPassL1,item.getSUVoltajeByPassL1());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeByPassL2,item.getSUVoltajeByPassL2());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeByPassL3,item.getSUVoltajeByPassL3());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeSalidaL1,item.getSUVoltajeSalidaL1());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeSalidaL2,item.getSUVoltajeSalidaL2());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeSalidaL3,item.getSUVoltajeSalidaL3());
        valores.put(FilasDB.ColumnasBestel2.SUCorrienteSalidaL1,item.getSUCorrienteSalidaL1());
        valores.put(FilasDB.ColumnasBestel2.SUCorrienteSalidaL2,item.getSUCorrienteSalidaL2());
        valores.put(FilasDB.ColumnasBestel2.SUCorrienteSalidaL3,item.getSUCorrienteSalidaL3());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeTotalBaterias,item.getSUVoltajeTotalBaterias());
        valores.put(FilasDB.ColumnasBestel2.SUVerificacionVentiladiores,item.getSUVerificacionVentiladiores());
        valores.put(FilasDB.ColumnasBestel2.SUReapreteConexiones,item.getSUReapreteConexiones());
        valores.put(FilasDB.ColumnasBestel2.SUCorrienteCargaBaterias,item.getSUCorrienteCargaBaterias());
        valores.put(FilasDB.ColumnasBestel2.SUCorrienteDescargaBaterias,item.getSUCorrienteDescargaBaterias());
        valores.put(FilasDB.ColumnasBestel2.SUTorqueBaterias,item.getSUTorqueBaterias());
        valores.put(FilasDB.ColumnasBestel2.SUVoltajeEntreTierra,item.getSUVoltajeEntreTierra());
        valores.put(FilasDB.ColumnasBestel2.SULimpieza,item.getSULimpieza());
        valores.put(FilasDB.ColumnasBestel2.PEFugasAceite,item.getPEFugasAceite());
        valores.put(FilasDB.ColumnasBestel2.PEAmperajeL1,item.getPEAmperajeL1());
        valores.put(FilasDB.ColumnasBestel2.PEAmperajeL2,item.getPEAmperajeL2());
        valores.put(FilasDB.ColumnasBestel2.PEAmperajeL3,item.getPEAmperajeL3());
        valores.put(FilasDB.ColumnasBestel2.PEVoltajeL1,item.getPEVoltajeL1());
        valores.put(FilasDB.ColumnasBestel2.PEVoltajeL2,item.getPEVoltajeL2());
        valores.put(FilasDB.ColumnasBestel2.PEVoltajeL3,item.getPEVoltajeL3());
        valores.put(FilasDB.ColumnasBestel2.PEHorasOperacion,item.getPEHorasOperacion());
        valores.put(FilasDB.ColumnasBestel2.PEBaterias,item.getPEBaterias());
        valores.put(FilasDB.ColumnasBestel2.PENivelDisel,item.getPENivelDisel());
        valores.put(FilasDB.ColumnasBestel2.PENivelAnticongelante,item.getPENivelAnticongelante());
        valores.put(FilasDB.ColumnasBestel2.PEManguerasGeneral,item.getPEManguerasGeneral());
        valores.put(FilasDB.ColumnasBestel2.PERuidosExtraños,item.getPERuidosExtraños());
        valores.put(FilasDB.ColumnasBestel2.PETableroTransparencia,item.getPETableroTransparencia());
        valores.put(FilasDB.ColumnasBestel2.PEPrecalentador,item.getPEPrecalentador());
        valores.put(FilasDB.ColumnasBestel2.PEFiltros,item.getPEFiltros());
        valores.put(FilasDB.ColumnasBestel2.PETemperatura,item.getPETemperatura());
        valores.put(FilasDB.ColumnasBestel2.PEBandas,item.getPEBandas());
        valores.put(FilasDB.ColumnasBestel2.PEBateriasLiquido,item.getPEBateriasLiquido());
        valores.put(FilasDB.ColumnasBestel2.PELubricacion,item.getPELubricacion());
        valores.put(FilasDB.ColumnasBestel2.PEArranqueManualSinCarga,item.getPEArranqueManualSinCarga());
        valores.put(FilasDB.ColumnasBestel2.PELimpiezaGeneral,item.getPELimpiezaGeneral());
        valores.put(FilasDB.ColumnasBestel2.OPTEM,item.getOPTEM());
        valores.put(FilasDB.ColumnasBestel2.OPL1L2,item.getOPL1L2());
        valores.put(FilasDB.ColumnasBestel2.OPL2L3,item.getOPL2L3());
        valores.put(FilasDB.ColumnasBestel2.OPL3L1,item.getOPL3L1());
        valores.put(FilasDB.ColumnasBestel2.OPP1W,item.getOPP1W());
        valores.put(FilasDB.ColumnasBestel2.OPP1VAR,item.getOPP1VAR());
        valores.put(FilasDB.ColumnasBestel2.OPP1VA,item.getOPP1VA());
        valores.put(FilasDB.ColumnasBestel2.OPL1N,item.getOPL1N());
        valores.put(FilasDB.ColumnasBestel2.OPL2N,item.getOPL2N());
        valores.put(FilasDB.ColumnasBestel2.OPL3N,item.getOPL3N());
        valores.put(FilasDB.ColumnasBestel2.OPPF1,item.getOPPF1());
        valores.put(FilasDB.ColumnasBestel2.OPPF2,item.getOPPF2());
        valores.put(FilasDB.ColumnasBestel2.OPPF3,item.getOPPF3());
        valores.put(FilasDB.ColumnasBestel2.OPHZ,item.getOPHZ());
        valores.put(FilasDB.ColumnasBestel2.OPREVRPM,item.getOPREVRPM());
        valores.put(FilasDB.ColumnasBestel2.SSESobreCalentamientoPorFase,item.getSSESobreCalentamientoPorFase());
        valores.put(FilasDB.ColumnasBestel2.SSESobreCalentamientoPorFaseL1,item.getSSESobreCalentamientoPorFaseL1());
        valores.put(FilasDB.ColumnasBestel2.SSEBarrasPuestaTierra,item.getSSEBarrasPuestaTierra());
        valores.put(FilasDB.ColumnasBestel2.SSEBarrasPuestaTierraL2,item.getSSEBarrasPuestaTierraL2());
        valores.put(FilasDB.ColumnasBestel2.SSETransformador,item.getSSETransformador());
        valores.put(FilasDB.ColumnasBestel2.SSETransformadorNT,item.getSSETransformadorNT());
        valores.put(FilasDB.ColumnasBestel2.SSEFusibles,item.getSSEFusibles());
        valores.put(FilasDB.ColumnasBestel2.SSEFusiblesA1,item.getSSEFusiblesA1());
        valores.put(FilasDB.ColumnasBestel2.SSETemperatura,item.getSSETemperatura());
        valores.put(FilasDB.ColumnasBestel2.SSETemperaturaA2,item.getSSETemperaturaA2());
        valores.put(FilasDB.ColumnasBestel2.SSECuchillas,item.getSSECuchillas());
        valores.put(FilasDB.ColumnasBestel2.SSECuchillasA3,item.getSSECuchillasA3());
        valores.put(FilasDB.ColumnasBestel2.SSEInterruptores,item.getSSEInterruptores());
        valores.put(FilasDB.ColumnasBestel2.SSEInterruptoresNT,item.getSSEInterruptoresNT());
        valores.put(FilasDB.ColumnasBestel2.TTLimpiezaGeneral,item.getTTLimpiezaGeneral());
        valores.put(FilasDB.ColumnasBestel2.TTAnclasRetenidos,item.getTTAnclasRetenidos());
        valores.put(FilasDB.ColumnasBestel2.TTLucesObstruccion,item.getTTLucesObstruccion());
        valores.put(FilasDB.ColumnasBestel2.TTTornilleriaHerraje,item.getTTTornilleriaHerraje());
        valores.put(FilasDB.ColumnasBestel2.TTPuestaTierra,item.getTTPuestaTierra());
        valores.put(FilasDB.ColumnasBestel2.TTSistemaApartaRayos,item.getTTSistemaApartaRayos());
        valores.put(FilasDB.ColumnasBestel2.HFugasGeneral,item.getHFugasGeneral());
        valores.put(FilasDB.ColumnasBestel2.HHidroneumaticos,item.getHHidroneumaticos());
        valores.put(FilasDB.ColumnasBestel2.HBaños,item.getHBaños());
        valores.put(FilasDB.ColumnasBestel2.HCisternasTanques,item.getHCisternasTanques());
        valores.put(FilasDB.ColumnasBestel2.HBombas,item.getHBombas());
        valores.put(FilasDB.ColumnasBestel2.HEmpaques,item.getHEmpaques());
        valores.put(FilasDB.ColumnasBestel2.HAccesorios,item.getHAccesorios());
        valores.put(FilasDB.ColumnasBestel2.OTROS,item.getOTROS());
        valores.put(FilasDB.ColumnasBestel2.AccionesTomadas,item.getAccionesTomadas());
        valores.put(FilasDB.ColumnasBestel2.MaterialesActividades,item.getMaterialesActividades());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto1,item.getAntesFoto1());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto2,item.getAntesFoto2());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto3,item.getAntesFoto3());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto4,item.getAntesFoto4());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto5,item.getAntesFoto5());
        valores.put(FilasDB.ColumnasBestel2.AntesFoto6,item.getAntesFoto6());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto1,item.getDespuesFoto1());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto2,item.getDespuesFoto2());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto3,item.getDespuesFoto3());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto4,item.getDespuesFoto4());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto5,item.getDespuesFoto5());
        valores.put(FilasDB.ColumnasBestel2.DespuesFoto6,item.getDespuesFoto6());
        valores.put(FilasDB.ColumnasBestel2.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasBestel2.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasBestel2.proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasBestel2.referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasBestel2.modelo,item.getModelo());
        valores.put(FilasDB.ColumnasBestel2.Otros2,item.getOtros2());
        valores.put(FilasDB.ColumnasBestel2.Comentarios2,item.getComentarios2());
        valores.put(FilasDB.ColumnasBestel2.Otros3,item.getOtros3());
        valores.put(FilasDB.ColumnasBestel2.Comentarios3,item.getComentarios3());
        valores.put(FilasDB.ColumnasBestel2.Otros4,item.getOtros4());
        valores.put(FilasDB.ColumnasBestel2.Comentarios4,item.getComentarios4());
        valores.put(FilasDB.ColumnasBestel2.Otros5,item.getOtros5());
        valores.put(FilasDB.ColumnasBestel2.Comentarios5,item.getComentarios5());
        valores.put(FilasDB.ColumnasBestel2.Otros6,item.getOtros6());
        valores.put(FilasDB.ColumnasBestel2.Comentarios6,item.getComentarios6());
        valores.put(FilasDB.ColumnasBestel2.Otros7,item.getOtros7());
        valores.put(FilasDB.ColumnasBestel2.Comentarios7,item.getComentarios7());
        valores.put(FilasDB.ColumnasBestel2.Otros8,item.getOtros8());
        valores.put(FilasDB.ColumnasBestel2.Comentarios8,item.getComentarios8());
        valores.put(FilasDB.ColumnasBestel2.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasBestel2.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasBestel2.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasBestel2.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasBestel2.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasBestel2.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasBestel2.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasBestel2.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasBestel2.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasBestel2.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasBestel2.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasBestel2.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasBestel2.SSEConexionPuestaTierra,item.getSSEConexionPuestaTierra());
        valores.put(FilasDB.ColumnasBestel2.SSEConexionPuestaTierraL3,item.getSSEConexionPuestaTierraL3());


//endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasBestel2.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.BESTEL2, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarUPS(UPS item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        //valores.put(FilasDB.ColumnasUPS.FolioFormato,item.getFolioFormato());
        valores.put(FilasDB.ColumnasUPS.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasUPS.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasUPS.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasUPS.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasUPS.IdCliente,item.getIdCliente());
        valores.put(FilasDB.ColumnasUPS.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasUPS.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasUPS.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasUPS.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasUPS.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasUPS.Freecuencia,item.getFreecuencia());
        valores.put(FilasDB.ColumnasUPS.EMail,item.getEMail());
        valores.put(FilasDB.ColumnasUPS.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasUPS.ModeloEquipo,item.getModeloEquipo());
        valores.put(FilasDB.ColumnasUPS.NoSerie,item.getNoSerie());
        valores.put(FilasDB.ColumnasUPS.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLLFaseA,item.getVoltajeEntradaLLFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLLFaseB,item.getVoltajeEntradaLLFaseB());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLLFaseC,item.getVoltajeEntradaLLFaseC());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLNFaseA,item.getVoltajeEntradaLNFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLNFaseB,item.getVoltajeEntradaLNFaseB());
        valores.put(FilasDB.ColumnasUPS.VoltajeEntradaLNFaseC,item.getVoltajeEntradaLNFaseC());
        valores.put(FilasDB.ColumnasUPS.CorrienteEntradaFaseA,item.getCorrienteEntradaFaseA());
        valores.put(FilasDB.ColumnasUPS.CorrienteEntradaFaseB,item.getCorrienteEntradaFaseB());
        valores.put(FilasDB.ColumnasUPS.CorrienteEntradaFaseC,item.getCorrienteEntradaFaseC());
        valores.put(FilasDB.ColumnasUPS.CorrienteFiltroEntradaFaseA,item.getCorrienteFiltroEntradaFaseA());
        valores.put(FilasDB.ColumnasUPS.CorrienteFiltroEntradaFaseB,item.getCorrienteFiltroEntradaFaseB());
        valores.put(FilasDB.ColumnasUPS.CorrienteFiltroEntradaFaseC,item.getCorrienteFiltroEntradaFaseC());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLLFaseA,item.getVoltajeSalidaUPSLLFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLLFaseB,item.getVoltajeSalidaUPSLLFaseB());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLLFaseC,item.getVoltajeSalidaUPSLLFaseC());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLNFaseA,item.getVoltajeSalidaUPSLNFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLNFaseB,item.getVoltajeSalidaUPSLNFaseB());
        valores.put(FilasDB.ColumnasUPS.VoltajeSalidaUPSLNFaseC,item.getVoltajeSalidaUPSLNFaseC());
        valores.put(FilasDB.ColumnasUPS.VoltajeBypassFaseA,item.getVoltajeBypassFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeBypassFaseB,item.getVoltajeBypassFaseB());
        valores.put(FilasDB.ColumnasUPS.VoltajeBypassFaseC,item.getVoltajeBypassFaseC());
        valores.put(FilasDB.ColumnasUPS.CorrienteSalidaFaseA,item.getCorrienteSalidaFaseA());
        valores.put(FilasDB.ColumnasUPS.CorrienteSalidaFaseB,item.getCorrienteSalidaFaseB());
        valores.put(FilasDB.ColumnasUPS.CorrienteSalidaFaseC,item.getCorrienteSalidaFaseC());
        valores.put(FilasDB.ColumnasUPS.CorrienteFiltroSalidaFaseA,item.getCorrienteFiltroSalidaFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeBusDCFaseA,item.getVoltajeBusDCFaseA());
        valores.put(FilasDB.ColumnasUPS.CorrienteBusDCFaseA,item.getCorrienteBusDCFaseA());
        valores.put(FilasDB.ColumnasUPS.VoltajeRizoACFaseA,item.getVoltajeRizoACFaseA());
        valores.put(FilasDB.ColumnasUPS.CorrienteRizoACFaseA,item.getCorrienteRizoACFaseA());
        valores.put(FilasDB.ColumnasUPS.SincronoscopioFaseA,item.getSincronoscopioFaseA());
        valores.put(FilasDB.ColumnasUPS.PotenciaKWFaseA,item.getPotenciaKWFaseA());
        valores.put(FilasDB.ColumnasUPS.PotenciaKVAFaseA,item.getPotenciaKVAFaseA());
        valores.put(FilasDB.ColumnasUPS.FreqEntrada,item.getFreqEntrada());
        valores.put(FilasDB.ColumnasUPS.FreqUPSSalida,item.getFreqUPSSalida());
        valores.put(FilasDB.ColumnasUPS.PorcentajeCarga,item.getPorcentajeCarga());
        valores.put(FilasDB.ColumnasUPS.Rectificador,item.getRectificador());
        valores.put(FilasDB.ColumnasUPS.Inversor,item.getInversor());
        valores.put(FilasDB.ColumnasUPS.SwitchEstatico,item.getSwitchEstatico());
        valores.put(FilasDB.ColumnasUPS.Transformador,item.getTransformador());
        valores.put(FilasDB.ColumnasUPS.CapacitoresInflados,item.getCapacitoresInflados());
        valores.put(FilasDB.ColumnasUPS.CapacitoresValvulas,item.getCapacitoresValvulas());
        valores.put(FilasDB.ColumnasUPS.RevisarAlarmas,item.getRevisarAlarmas());
        valores.put(FilasDB.ColumnasUPS.InspeccionCables,item.getInspeccionCables());
        valores.put(FilasDB.ColumnasUPS.CalibracionMedidor,item.getCalibracionMedidor());
        valores.put(FilasDB.ColumnasUPS.AjusteEcualizacion,item.getAjusteEcualizacion());
        valores.put(FilasDB.ColumnasUPS.PantallaInformeEstado,item.getPantallaInformeEstado());
        valores.put(FilasDB.ColumnasUPS.LimpiezaUPS,item.getLimpiezaUPS());
        valores.put(FilasDB.ColumnasUPS.SnubberSobretemperatura,item.getSnubberSobretemperatura());
        valores.put(FilasDB.ColumnasUPS.AjusteLímites,item.getAjusteLímites());
        valores.put(FilasDB.ColumnasUPS.PantallaProcedimiento,item.getPantallaProcedimiento());
        valores.put(FilasDB.ColumnasUPS.PantallaBaterías,item.getPantallaBaterías());
        valores.put(FilasDB.ColumnasUPS.RevisionModulosDaños,item.getRevisionModulosDaños());
        valores.put(FilasDB.ColumnasUPS.InspeccionGeneral,item.getInspeccionGeneral());
        valores.put(FilasDB.ColumnasUPS.RevSoftware,item.getRevSoftware());
        valores.put(FilasDB.ColumnasUPS.ComentariosRecomendaciones,item.getComentariosRecomendaciones());
        valores.put(FilasDB.ColumnasUPS.Cantidad1,item.getCantidad1());
        valores.put(FilasDB.ColumnasUPS.Cantidad2,item.getCantidad2());
        valores.put(FilasDB.ColumnasUPS.Cantidad3,item.getCantidad3());
        valores.put(FilasDB.ColumnasUPS.Cantidad4,item.getCantidad4());
        valores.put(FilasDB.ColumnasUPS.Cantidad5,item.getCantidad5());
        valores.put(FilasDB.ColumnasUPS.Cantidad6,item.getCantidad6());
        valores.put(FilasDB.ColumnasUPS.Cantidad7,item.getCantidad7());
        valores.put(FilasDB.ColumnasUPS.Cantidad8,item.getCantidad8());
        valores.put(FilasDB.ColumnasUPS.Cantidad9,item.getCantidad9());
        valores.put(FilasDB.ColumnasUPS.NoParte1,item.getNoParte1());
        valores.put(FilasDB.ColumnasUPS.NoParte2,item.getNoParte2());
        valores.put(FilasDB.ColumnasUPS.NoParte3,item.getNoParte3());
        valores.put(FilasDB.ColumnasUPS.NoParte4,item.getNoParte4());
        valores.put(FilasDB.ColumnasUPS.NoParte5,item.getNoParte5());
        valores.put(FilasDB.ColumnasUPS.NoParte6,item.getNoParte6());
        valores.put(FilasDB.ColumnasUPS.NoParte7,item.getNoParte7());
        valores.put(FilasDB.ColumnasUPS.NoParte8,item.getNoParte8());
        valores.put(FilasDB.ColumnasUPS.NoParte9,item.getNoParte9());
        valores.put(FilasDB.ColumnasUPS.Descripcion1,item.getDescripcion1());
        valores.put(FilasDB.ColumnasUPS.Descripcion2,item.getDescripcion2());
        valores.put(FilasDB.ColumnasUPS.Descripcion3,item.getDescripcion3());
        valores.put(FilasDB.ColumnasUPS.Descripcion4,item.getDescripcion4());
        valores.put(FilasDB.ColumnasUPS.Descripcion5,item.getDescripcion5());
        valores.put(FilasDB.ColumnasUPS.Descripcion6,item.getDescripcion6());
        valores.put(FilasDB.ColumnasUPS.Descripcion7,item.getDescripcion7());
        valores.put(FilasDB.ColumnasUPS.Descripcion8,item.getDescripcion8());
        valores.put(FilasDB.ColumnasUPS.Descripcion9,item.getDescripcion9());
        valores.put(FilasDB.ColumnasUPS.Equipo1,item.getEquipo1());
        valores.put(FilasDB.ColumnasUPS.Equipo2,item.getEquipo2());
        valores.put(FilasDB.ColumnasUPS.Equipo3,item.getEquipo3());
        valores.put(FilasDB.ColumnasUPS.Equipo4,item.getEquipo4());
        valores.put(FilasDB.ColumnasUPS.Equipo5,item.getEquipo5());
        valores.put(FilasDB.ColumnasUPS.Equipo6,item.getEquipo6());
        valores.put(FilasDB.ColumnasUPS.Equipo7,item.getEquipo7());
        valores.put(FilasDB.ColumnasUPS.Equipo8,item.getEquipo8());
        valores.put(FilasDB.ColumnasUPS.Equipo9,item.getEquipo9());
        valores.put(FilasDB.ColumnasUPS.NoId1,item.getNoId1());
        valores.put(FilasDB.ColumnasUPS.NoId2,item.getNoId2());
        valores.put(FilasDB.ColumnasUPS.NoId3,item.getNoId3());
        valores.put(FilasDB.ColumnasUPS.NoId4,item.getNoId4());
        valores.put(FilasDB.ColumnasUPS.NoId5,item.getNoId5());
        valores.put(FilasDB.ColumnasUPS.NoId6,item.getNoId6());
        valores.put(FilasDB.ColumnasUPS.NoId7,item.getNoId7());
        valores.put(FilasDB.ColumnasUPS.NoId8,item.getNoId8());
        valores.put(FilasDB.ColumnasUPS.NoId9,item.getNoId9());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento1,item.getFechaVencimiento1());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento2,item.getFechaVencimiento2());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento3,item.getFechaVencimiento3());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento4,item.getFechaVencimiento4());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento5,item.getFechaVencimiento5());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento6,item.getFechaVencimiento6());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento7,item.getFechaVencimiento7());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento8,item.getFechaVencimiento8());
        valores.put(FilasDB.ColumnasUPS.FechaVencimiento9,item.getFechaVencimiento9());
        valores.put(FilasDB.ColumnasUPS.Antes1,item.getAntes1());
        valores.put(FilasDB.ColumnasUPS.Antes2,item.getAntes2());
        valores.put(FilasDB.ColumnasUPS.Antes3,item.getAntes3());
        valores.put(FilasDB.ColumnasUPS.Antes4,item.getAntes4());
        valores.put(FilasDB.ColumnasUPS.Antes5,item.getAntes5());
        valores.put(FilasDB.ColumnasUPS.Antes6,item.getAntes6());
        valores.put(FilasDB.ColumnasUPS.Despues1,item.getDespues1());
        valores.put(FilasDB.ColumnasUPS.Despues2,item.getDespues2());
        valores.put(FilasDB.ColumnasUPS.Despues3,item.getDespues3());
        valores.put(FilasDB.ColumnasUPS.Despues4,item.getDespues4());
        valores.put(FilasDB.ColumnasUPS.Despues5,item.getDespues5());
        valores.put(FilasDB.ColumnasUPS.Despues6,item.getDespues6());
        valores.put(FilasDB.ColumnasUPS.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasUPS.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasUPS.FirmaClienteFinal,item.getFirmaClienteFinal());
        valores.put(FilasDB.ColumnasUPS.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasUPS.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasUPS.NombreFirmaClienteFinal,item.getNombreFirmaClienteFinal());
        valores.put(FilasDB.ColumnasUPS.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasUPS.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasUPS.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasUPS.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasUPS.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasUPS.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasUPS.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasUPS.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasUPS.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasUPS.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasUPS.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasUPS.CLIENTEFINAL_TELEFONO,item.getCLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasUPS.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasUPS.Serie2,item.getNSERIE2());
        valores.put(FilasDB.ColumnasUPS.Referencia,item.getReferrencia());



//endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasUPS.FolioFormato);
        String[] whereArgs = {item.getIdFotmato()};

        int resultado = db.update(BaseDatos.Tablas.UPS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarThrtmal(ThermalManagagementS item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        //valores.put(FilasDB.ColumnasUPS.FolioFormato,item.getFolioFormato());

        valores.put(FilasDB.ColumnasThermal.IDFormato,item.getIDFormato());
        valores.put(FilasDB.ColumnasThermal.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasThermal.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasThermal.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasThermal.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasThermal.Cliente,item.getCliente());
        valores.put(FilasDB.ColumnasThermal.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasThermal.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasThermal.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasThermal.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasThermal.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasThermal.Freecuencia,item.getFreecuencia());
        valores.put(FilasDB.ColumnasThermal.EMail,item.getEMail());
        valores.put(FilasDB.ColumnasThermal.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasThermal.ModeloEquipo,item.getModeloEquipo());
        valores.put(FilasDB.ColumnasThermal.NoSerie,item.getNoSerie());
        valores.put(FilasDB.ColumnasThermal.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasThermal.Contacto,item.getContacto());
        valores.put(FilasDB.ColumnasThermal.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasThermal.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasThermal.tiposervicioEspecificacion,item.getTiposervicioEspecificacion());
        valores.put(FilasDB.ColumnasThermal.GNoSerie2,item.getGNoSerie2());
        valores.put(FilasDB.ColumnasThermal.SETTEMP,item.getSETTEMP());
        valores.put(FilasDB.ColumnasThermal.SENS,item.getSENS());
        valores.put(FilasDB.ColumnasThermal.SETHUM,item.getSETHUM());
        valores.put(FilasDB.ColumnasThermal.SENS2,item.getSENS2());
        valores.put(FilasDB.ColumnasThermal.HITEMP,item.getHITEMP());
        valores.put(FilasDB.ColumnasThermal.LOTEMP,item.getLOTEMP());
        valores.put(FilasDB.ColumnasThermal.HIHUM,item.getHIHUM());
        valores.put(FilasDB.ColumnasThermal.LOHUM,item.getLOHUM());
        valores.put(FilasDB.ColumnasThermal.INYECCION,item.getINYECCION());
        valores.put(FilasDB.ColumnasThermal.RETORNO,item.getRETORNO());
        valores.put(FilasDB.ColumnasThermal.TIPOMICROPROCESADOR,item.getTIPOMICROPROCESADOR());
        valores.put(FilasDB.ColumnasThermal.MarcaUniMane,item.getMarcaUniMane());
        valores.put(FilasDB.ColumnasThermal.TipoEvaporadorUniMane,item.getTipoEvaporadorUniMane());
        valores.put(FilasDB.ColumnasThermal.ModeloUniMane,item.getModeloUniMane());
        valores.put(FilasDB.ColumnasThermal.VoltajeGeneralL1L2,item.getVoltajeGeneralL1L2());
        valores.put(FilasDB.ColumnasThermal.VoltajeGeneralL2L3,item.getVoltajeGeneralL2L3());
        valores.put(FilasDB.ColumnasThermal.VoltajeGeneralL3L1,item.getVoltajeGeneralL3L1());
        valores.put(FilasDB.ColumnasThermal.NoSerieUniMane,item.getNoSerieUniMane());
        valores.put(FilasDB.ColumnasThermal.NoResistencias,item.getNoResistencias());
        valores.put(FilasDB.ColumnasThermal.EstadoFísico,item.getEstadoFísico());
        valores.put(FilasDB.ColumnasThermal.Verificación,item.getVerificación());
        valores.put(FilasDB.ColumnasThermal.Conductores,item.getConductores());
        valores.put(FilasDB.ColumnasThermal.Contactores,item.getContactores());
        valores.put(FilasDB.ColumnasThermal.Fusibles,item.getFusibles());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia1L1,item.getAmpResistencia1L1());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia1L2,item.getAmpResistencia1L2());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia2L1,item.getAmpResistencia2L1());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia2L2,item.getAmpResistencia2L2());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia3L1,item.getAmpResistencia3L1());
        valores.put(FilasDB.ColumnasThermal.AmpResistencia3L2,item.getAmpResistencia3L2());
        valores.put(FilasDB.ColumnasThermal.MotorMarca,item.getMotorMarca());
        valores.put(FilasDB.ColumnasThermal.Modelo,item.getModelo());
        valores.put(FilasDB.ColumnasThermal.VoltsPlaca,item.getVoltsPlaca());
        valores.put(FilasDB.ColumnasThermal.PoleaMotor,item.getPoleaMotor());
        valores.put(FilasDB.ColumnasThermal.PoleaTurbina,item.getPoleaTurbina());
        valores.put(FilasDB.ColumnasThermal.ReqCambioBanda,item.getReqCambioBanda());
        valores.put(FilasDB.ColumnasThermal.ModeloBandas,item.getModeloBandas());
        valores.put(FilasDB.ColumnasThermal.AmpMotorL1,item.getAmpMotorL1());
        valores.put(FilasDB.ColumnasThermal.AmpMotorL2,item.getAmpMotorL2());
        valores.put(FilasDB.ColumnasThermal.AmpMotorL3,item.getAmpMotorL3());
        valores.put(FilasDB.ColumnasThermal.NumeroBandas,item.getNumeroBandas());
        valores.put(FilasDB.ColumnasThermal.ChumacerasLubricadas,item.getChumacerasLubricadas());
        valores.put(FilasDB.ColumnasThermal.AjustesPoleas,item.getAjustesPoleas());
        valores.put(FilasDB.ColumnasThermal.Rotacion,item.getRotacion());
        valores.put(FilasDB.ColumnasThermal.VAC24,item.getVAC24());
        valores.put(FilasDB.ColumnasThermal.VAC5,item.getVAC5());
        valores.put(FilasDB.ColumnasThermal.ObservacionesMicroprocesador,item.getObservacionesMicroprocesador());
        valores.put(FilasDB.ColumnasThermal.HumificadorTipo,item.getHumificadorTipo());
        valores.put(FilasDB.ColumnasThermal.Condiciones,item.getCondiciones());
        valores.put(FilasDB.ColumnasThermal.Sensor,item.getSensor());
        valores.put(FilasDB.ColumnasThermal.SelenoideVolts,item.getSelenoideVolts());
        valores.put(FilasDB.ColumnasThermal.NoLamparas,item.getNoLamparas());
        valores.put(FilasDB.ColumnasThermal.LíneaAguaDrenaje,item.getLíneaAguaDrenaje());
        valores.put(FilasDB.ColumnasThermal.AmpLineaL1,item.getAmpLineaL1());
        valores.put(FilasDB.ColumnasThermal.AmpLineaL2,item.getAmpLineaL2());
        valores.put(FilasDB.ColumnasThermal.AmpLineaL3,item.getAmpLineaL3());
        valores.put(FilasDB.ColumnasThermal.Marca1,item.getMarca1());
        valores.put(FilasDB.ColumnasThermal.Modelo1,item.getModelo1());
        valores.put(FilasDB.ColumnasThermal.NoSerie1,item.getNoSerie1());
        valores.put(FilasDB.ColumnasThermal.MiraIndicadoraSeco1,item.getMiraIndicadoraSeco1());
        valores.put(FilasDB.ColumnasThermal.MiraIndicadoraHumedo1,item.getMiraIndicadoraHumedo1());
        valores.put(FilasDB.ColumnasThermal.AceiteNivel1,item.getAceiteNivel1());
        valores.put(FilasDB.ColumnasThermal.TempValvulaServ1,item.getTempValvulaServ1());
        valores.put(FilasDB.ColumnasThermal.TempValvulaExp1,item.getTempValvulaExp1());
        valores.put(FilasDB.ColumnasThermal.PresionBajaLbs1,item.getPresionBajaLbs1());
        valores.put(FilasDB.ColumnasThermal.PresionBajaCorte1,item.getPresionBajaCorte1());
        valores.put(FilasDB.ColumnasThermal.PresionAlta1,item.getPresionAlta1());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL11,item.getAmpLíneaL11());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL21,item.getAmpLíneaL21());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL31,item.getAmpLíneaL31());
        valores.put(FilasDB.ColumnasThermal.Marca2,item.getMarca2());
        valores.put(FilasDB.ColumnasThermal.Modelo2,item.getModelo2());
        valores.put(FilasDB.ColumnasThermal.NoSerie2,item.getNoSerie2());
        valores.put(FilasDB.ColumnasThermal.MiraIndicadoraSeco2,item.getMiraIndicadoraSeco2());
        valores.put(FilasDB.ColumnasThermal.MiraIndicadoraHumedo2,item.getMiraIndicadoraHumedo2());
        valores.put(FilasDB.ColumnasThermal.AceiteNivel2,item.getAceiteNivel2());
        valores.put(FilasDB.ColumnasThermal.TempValvulaServ2,item.getTempValvulaServ2());
        valores.put(FilasDB.ColumnasThermal.TempValvulaExp2,item.getTempValvulaExp2());
        valores.put(FilasDB.ColumnasThermal.PresionBajaLbs2,item.getPresionBajaLbs2());
        valores.put(FilasDB.ColumnasThermal.PresionBajaCorte2,item.getPresionBajaCorte2());
        valores.put(FilasDB.ColumnasThermal.PresionAlta2,item.getPresionAlta2());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL12,item.getAmpLíneaL12());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL22,item.getAmpLíneaL22());
        valores.put(FilasDB.ColumnasThermal.AmpLíneaL32,item.getAmpLíneaL32());
        valores.put(FilasDB.ColumnasThermal.MarcaConde,item.getMarcaConde());
        valores.put(FilasDB.ColumnasThermal.ModeloConde,item.getModeloConde());
        valores.put(FilasDB.ColumnasThermal.NoSerieConde,item.getNoSerieConde());
        valores.put(FilasDB.ColumnasThermal.MarcaMotores,item.getMarcaMotores());
        valores.put(FilasDB.ColumnasThermal.ModeloMotVariable,item.getModeloMotVariable());
        valores.put(FilasDB.ColumnasThermal.ModeloMotConstante,item.getModeloMotConstante());
        valores.put(FilasDB.ColumnasThermal.AmpsPlaca,item.getAmpsPlaca());
        valores.put(FilasDB.ColumnasThermal.AmpsPlaca1,item.getAmpsPlaca1());
        valores.put(FilasDB.ColumnasThermal.VoltsPlaca1,item.getVoltsPlaca1());
        valores.put(FilasDB.ColumnasThermal.VoltajeGeneral,item.getVoltajeGeneral());
        valores.put(FilasDB.ColumnasThermal.L12,item.getL12());
        valores.put(FilasDB.ColumnasThermal.L23,item.getL23());
        valores.put(FilasDB.ColumnasThermal.L31,item.getL31());
        valores.put(FilasDB.ColumnasThermal.AMotorUnoL1,item.getAMotorUnoL1());
        valores.put(FilasDB.ColumnasThermal.AMotorUnoL2,item.getAMotorUnoL2());
        valores.put(FilasDB.ColumnasThermal.AMotorUnoL3,item.getAMotorUnoL3());
        valores.put(FilasDB.ColumnasThermal.AMotorDosL1,item.getAMotorDosL1());
        valores.put(FilasDB.ColumnasThermal.AMotorDosL2,item.getAMotorDosL2());
        valores.put(FilasDB.ColumnasThermal.AMotorDosL3,item.getAMotorDosL3());
        valores.put(FilasDB.ColumnasThermal.AMotorTres1,item.getAMotorTres1());
        valores.put(FilasDB.ColumnasThermal.AMotorTres2,item.getAMotorTres2());
        valores.put(FilasDB.ColumnasThermal.AMotorTres3,item.getAMotorTres3());
        valores.put(FilasDB.ColumnasThermal.AMotorCuatroL1,item.getAMotorCuatroL1());
        valores.put(FilasDB.ColumnasThermal.AMotorCuatroL2,item.getAMotorCuatroL2());
        valores.put(FilasDB.ColumnasThermal.AMotorCuatroL3,item.getAMotorCuatroL3());
        valores.put(FilasDB.ColumnasThermal.LavadoSerpentines,item.getLavadoSerpentines());
        valores.put(FilasDB.ColumnasThermal.ReaprieteTornilleríaGeneral,item.getReaprieteTornilleríaGeneral());
        valores.put(FilasDB.ColumnasThermal.RevisionFusibles,item.getRevisionFusibles());
        valores.put(FilasDB.ColumnasThermal.RevisionContactores,item.getRevisionContactores());
        valores.put(FilasDB.ColumnasThermal.LavadoAspasProtecciones,item.getLavadoAspasProtecciones());
        valores.put(FilasDB.ColumnasThermal.LimpiezaInternaExterna,item.getLimpiezaInternaExterna());
        valores.put(FilasDB.ColumnasThermal.LimpiezaCharolaHumidificacion,item.getLimpiezaCharolaHumidificacion());
        valores.put(FilasDB.ColumnasThermal.RevisarEstadoLampara,item.getRevisarEstadoLampara());
        valores.put(FilasDB.ColumnasThermal.RevisarSedimentacionMinera,item.getRevisarSedimentacionMinera());
        valores.put(FilasDB.ColumnasThermal.RevisarCondicionesBandas,item.getRevisarCondicionesBandas());
        valores.put(FilasDB.ColumnasThermal.RevisarAmortiguadores,item.getRevisarAmortiguadores());
        valores.put(FilasDB.ColumnasThermal.RevisarLubricacionBaleros,item.getRevisarLubricacionBaleros());
        valores.put(FilasDB.ColumnasThermal.RevisarFlechaBaseMotor,item.getRevisarFlechaBaseMotor());
        valores.put(FilasDB.ColumnasThermal.RevisarAbrazaderasSoportes,item.getRevisarAbrazaderasSoportes());
        valores.put(FilasDB.ColumnasThermal.CambiosFiltrosAire,item.getCambiosFiltrosAire());
        valores.put(FilasDB.ColumnasThermal.LavadoSerpentinesCom,item.getLavadoSerpentinesCom());
        valores.put(FilasDB.ColumnasThermal.ReaprieteTornilleríaGeneralCom,item.getReaprieteTornilleríaGeneralCom());
        valores.put(FilasDB.ColumnasThermal.RevisionFusiblesCom,item.getRevisionFusiblesCom());
        valores.put(FilasDB.ColumnasThermal.RevisionContactoresCom,item.getRevisionContactoresCom());
        valores.put(FilasDB.ColumnasThermal.LavadoAspasProteccionesCom,item.getLavadoAspasProteccionesCom());
        valores.put(FilasDB.ColumnasThermal.LimpiezaInternaExternaCom,item.getLimpiezaInternaExternaCom());
        valores.put(FilasDB.ColumnasThermal.LimpiezaCharolaHumidificacionCom,item.getLimpiezaCharolaHumidificacionCom());
        valores.put(FilasDB.ColumnasThermal.RevisarEstadoLamparaCom,item.getRevisarEstadoLamparaCom());
        valores.put(FilasDB.ColumnasThermal.RevisarSedimentacionMineraCom,item.getRevisarSedimentacionMineraCom());
        valores.put(FilasDB.ColumnasThermal.RevisarCondicionesBandasCom,item.getRevisarCondicionesBandasCom());
        valores.put(FilasDB.ColumnasThermal.RevisarAmortiguadoresCom,item.getRevisarAmortiguadoresCom());
        valores.put(FilasDB.ColumnasThermal.RevisarLubricacionBalerosCom,item.getRevisarLubricacionBalerosCom());
        valores.put(FilasDB.ColumnasThermal.RevisarFlechaBaseMotorCom,item.getRevisarFlechaBaseMotorCom());
        valores.put(FilasDB.ColumnasThermal.RevisarAbrazaderasSoportesCom,item.getRevisarAbrazaderasSoportesCom());
        valores.put(FilasDB.ColumnasThermal.CambiosFiltrosAireCom,item.getCambiosFiltrosAireCom());
        valores.put(FilasDB.ColumnasThermal.ComentariosRecomendaciones,item.getComentariosRecomendaciones());
        valores.put(FilasDB.ColumnasThermal.Cantidad1,item.getCantidad1());
        valores.put(FilasDB.ColumnasThermal.Cantidad2,item.getCantidad2());
        valores.put(FilasDB.ColumnasThermal.Cantidad3,item.getCantidad3());
        valores.put(FilasDB.ColumnasThermal.Cantidad4,item.getCantidad4());
        valores.put(FilasDB.ColumnasThermal.Cantidad5,item.getCantidad5());
        valores.put(FilasDB.ColumnasThermal.Cantidad6,item.getCantidad6());
        valores.put(FilasDB.ColumnasThermal.Cantidad7,item.getCantidad7());
        valores.put(FilasDB.ColumnasThermal.Cantidad8,item.getCantidad8());
        valores.put(FilasDB.ColumnasThermal.Cantidad9,item.getCantidad9());
        valores.put(FilasDB.ColumnasThermal.NoParte1,item.getNoParte1());
        valores.put(FilasDB.ColumnasThermal.NoParte2,item.getNoParte2());
        valores.put(FilasDB.ColumnasThermal.NoParte3,item.getNoParte3());
        valores.put(FilasDB.ColumnasThermal.NoParte4,item.getNoParte4());
        valores.put(FilasDB.ColumnasThermal.NoParte5,item.getNoParte5());
        valores.put(FilasDB.ColumnasThermal.NoParte6,item.getNoParte6());
        valores.put(FilasDB.ColumnasThermal.NoParte7,item.getNoParte7());
        valores.put(FilasDB.ColumnasThermal.NoParte8,item.getNoParte8());
        valores.put(FilasDB.ColumnasThermal.NoParte9,item.getNoParte9());
        valores.put(FilasDB.ColumnasThermal.Descripcion1,item.getDescripcion1());
        valores.put(FilasDB.ColumnasThermal.Descripcion2,item.getDescripcion2());
        valores.put(FilasDB.ColumnasThermal.Descripcion3,item.getDescripcion3());
        valores.put(FilasDB.ColumnasThermal.Descripcion4,item.getDescripcion4());
        valores.put(FilasDB.ColumnasThermal.Descripcion5,item.getDescripcion5());
        valores.put(FilasDB.ColumnasThermal.Descripcion6,item.getDescripcion6());
        valores.put(FilasDB.ColumnasThermal.Descripcion7,item.getDescripcion7());
        valores.put(FilasDB.ColumnasThermal.Descripcion8,item.getDescripcion8());
        valores.put(FilasDB.ColumnasThermal.Descripcion9,item.getDescripcion9());
        valores.put(FilasDB.ColumnasThermal.Equipo1,item.getEquipo1());
        valores.put(FilasDB.ColumnasThermal.Equipo2,item.getEquipo2());
        valores.put(FilasDB.ColumnasThermal.Equipo3,item.getEquipo3());
        valores.put(FilasDB.ColumnasThermal.Equipo4,item.getEquipo4());
        valores.put(FilasDB.ColumnasThermal.Equipo5,item.getEquipo5());
        valores.put(FilasDB.ColumnasThermal.Equipo6,item.getEquipo6());
        valores.put(FilasDB.ColumnasThermal.Equipo7,item.getEquipo7());
        valores.put(FilasDB.ColumnasThermal.Equipo8,item.getEquipo8());
        valores.put(FilasDB.ColumnasThermal.Equipo9,item.getEquipo9());
        valores.put(FilasDB.ColumnasThermal.NoId1,item.getNoId1());
        valores.put(FilasDB.ColumnasThermal.NoId2,item.getNoId2());
        valores.put(FilasDB.ColumnasThermal.NoId3,item.getNoId3());
        valores.put(FilasDB.ColumnasThermal.NoId4,item.getNoId4());
        valores.put(FilasDB.ColumnasThermal.NoId5,item.getNoId5());
        valores.put(FilasDB.ColumnasThermal.NoId6,item.getNoId6());
        valores.put(FilasDB.ColumnasThermal.NoId7,item.getNoId7());
        valores.put(FilasDB.ColumnasThermal.NoId8,item.getNoId8());
        valores.put(FilasDB.ColumnasThermal.NoId9,item.getNoId9());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento1,item.getFechaVencimiento1());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento2,item.getFechaVencimiento2());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento3,item.getFechaVencimiento3());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento4,item.getFechaVencimiento4());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento5,item.getFechaVencimiento5());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento6,item.getFechaVencimiento6());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento7,item.getFechaVencimiento7());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento8,item.getFechaVencimiento8());
        valores.put(FilasDB.ColumnasThermal.FechaVencimiento9,item.getFechaVencimiento9());
        valores.put(FilasDB.ColumnasThermal.Antes1,item.getAntes1());
        valores.put(FilasDB.ColumnasThermal.Antes2,item.getAntes2());
        valores.put(FilasDB.ColumnasThermal.Antes3,item.getAntes3());
        valores.put(FilasDB.ColumnasThermal.Antes4,item.getAntes4());
        valores.put(FilasDB.ColumnasThermal.Antes5,item.getAntes5());
        valores.put(FilasDB.ColumnasThermal.Antes6,item.getAntes6());
        valores.put(FilasDB.ColumnasThermal.Despues1,item.getDespues1());
        valores.put(FilasDB.ColumnasThermal.Despues2,item.getDespues2());
        valores.put(FilasDB.ColumnasThermal.Despues3,item.getDespues3());
        valores.put(FilasDB.ColumnasThermal.Despues4,item.getDespues4());
        valores.put(FilasDB.ColumnasThermal.Despues5,item.getDespues5());
        valores.put(FilasDB.ColumnasThermal.Despues6,item.getDespues6());
        valores.put(FilasDB.ColumnasThermal.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasThermal.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasThermal.FirmaClienteFinal,item.getFirmaClienteFinal());
        valores.put(FilasDB.ColumnasThermal.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasThermal.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasThermal.NomnbreFirmaClienteFinal,item.getNomnbreFirmaClienteFinal());
        valores.put(FilasDB.ColumnasThermal.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasThermal.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasThermal.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasThermal.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasThermal.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasThermal.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasThermal.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasThermal.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasThermal.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasThermal.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasThermal.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasThermal.CLIENTEFINAL_TELEFONO,item.getCLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasThermal.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasThermal.TipoServicioOtro,item.getTipoServicioOtro());

//endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasThermal.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.TERMAL, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarPDU(PDU item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();
        valores.put(FilasDB.ColumnasPDU.IDFormato,item.getIDFormato());
        valores.put(FilasDB.ColumnasPDU.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasPDU.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasPDU.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasPDU.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasPDU.IdCliente,item.getIdCliente());
        valores.put(FilasDB.ColumnasPDU.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasPDU.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasPDU.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasPDU.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasPDU.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasPDU.Freecuencia,item.getFreecuencia());
        valores.put(FilasDB.ColumnasPDU.EMail,item.getEMail());
        valores.put(FilasDB.ColumnasPDU.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasPDU.ModeloEquipo,item.getModeloEquipo());
        valores.put(FilasDB.ColumnasPDU.NoSerie,item.getNoSerie());
        valores.put(FilasDB.ColumnasPDU.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasPDU.RevisionEstadoTransformador,item.getRevisionEstadoTransformador());
        valores.put(FilasDB.ColumnasPDU.RevisionConexionesTransformador,item.getRevisionConexionesTransformador());
        valores.put(FilasDB.ColumnasPDU.RevisionCableadoPotencia,item.getRevisionCableadoPotencia());
        valores.put(FilasDB.ColumnasPDU.RevisionTarjetaControl,item.getRevisionTarjetaControl());
        valores.put(FilasDB.ColumnasPDU.RevisionDisplay,item.getRevisionDisplay());
        valores.put(FilasDB.ColumnasPDU.RevisionBotonesPanel,item.getRevisionBotonesPanel());
        valores.put(FilasDB.ColumnasPDU.VoltajeEAB,item.getVoltajeEAB());
        valores.put(FilasDB.ColumnasPDU.VoltajeEBC,item.getVoltajeEBC());
        valores.put(FilasDB.ColumnasPDU.VoltajeECA,item.getVoltajeECA());
        valores.put(FilasDB.ColumnasPDU.CorrienteEA,item.getCorrienteEA());
        valores.put(FilasDB.ColumnasPDU.CorrienteEB,item.getCorrienteEB());
        valores.put(FilasDB.ColumnasPDU.CorrienteEC,item.getCorrienteEC());
        valores.put(FilasDB.ColumnasPDU.Temperatura,item.getTemperatura());
        valores.put(FilasDB.ColumnasPDU.Rotacion,item.getRotacion());
        valores.put(FilasDB.ColumnasPDU.Taps,item.getTaps());
        valores.put(FilasDB.ColumnasPDU.VoltajeSAB,item.getVoltajeSAB());
        valores.put(FilasDB.ColumnasPDU.VoltajeSBC,item.getVoltajeSBC());
        valores.put(FilasDB.ColumnasPDU.VoltajeSCA,item.getVoltajeSCA());
        valores.put(FilasDB.ColumnasPDU.VoltajeSAN,item.getVoltajeSAN());
        valores.put(FilasDB.ColumnasPDU.VoltajeSBN,item.getVoltajeSBN());
        valores.put(FilasDB.ColumnasPDU.VoltajeSCN,item.getVoltajeSCN());
        valores.put(FilasDB.ColumnasPDU.CorrienteSAB,item.getCorrienteSAB());
        valores.put(FilasDB.ColumnasPDU.CorrienteSBC,item.getCorrienteSBC());
        valores.put(FilasDB.ColumnasPDU.CorrienteSCA,item.getCorrienteSCA());
        valores.put(FilasDB.ColumnasPDU.CorrienteSAN,item.getCorrienteSAN());
        valores.put(FilasDB.ColumnasPDU.CorrienteSBN,item.getCorrienteSBN());
        valores.put(FilasDB.ColumnasPDU.CorrienteSCN,item.getCorrienteSCN());
        valores.put(FilasDB.ColumnasPDU.VTHDA,item.getVTHDA());
        valores.put(FilasDB.ColumnasPDU.VTHDB,item.getVTHDB());
        valores.put(FilasDB.ColumnasPDU.VTHDC,item.getVTHDC());
        valores.put(FilasDB.ColumnasPDU.ITHDA,item.getITHDA());
        valores.put(FilasDB.ColumnasPDU.ITHDB,item.getITHDB());
        valores.put(FilasDB.ColumnasPDU.ITHDC,item.getITHDC());
        valores.put(FilasDB.ColumnasPDU.KFACTORA,item.getKFACTORA());
        valores.put(FilasDB.ColumnasPDU.KFACTORB,item.getKFACTORB());
        valores.put(FilasDB.ColumnasPDU.KFACTORC,item.getKFACTORC());
        valores.put(FilasDB.ColumnasPDU.PEAKRMSA,item.getPEAKRMSA());
        valores.put(FilasDB.ColumnasPDU.PEAKRMSB,item.getPEAKRMSB());
        valores.put(FilasDB.ColumnasPDU.PEAKRMSC,item.getPEAKRMSC());
        valores.put(FilasDB.ColumnasPDU.KVA,item.getKVA());
        valores.put(FilasDB.ColumnasPDU.KW,item.getKW());
        valores.put(FilasDB.ColumnasPDU.FP,item.getFP());
        valores.put(FilasDB.ColumnasPDU.PorcentajeCarga,item.getPorcentajeCarga());
        valores.put(FilasDB.ColumnasPDU.KWHR,item.getKWHR());
        valores.put(FilasDB.ColumnasPDU.ComentariosRecomendaciones,item.getComentariosRecomendaciones());
        valores.put(FilasDB.ColumnasPDU.Cantidad1,item.getCantidad1());
        valores.put(FilasDB.ColumnasPDU.Cantidad2,item.getCantidad2());
        valores.put(FilasDB.ColumnasPDU.Cantidad3,item.getCantidad3());
        valores.put(FilasDB.ColumnasPDU.Cantidad4,item.getCantidad4());
        valores.put(FilasDB.ColumnasPDU.Cantidad5,item.getCantidad5());
        valores.put(FilasDB.ColumnasPDU.Cantidad6,item.getCantidad6());
        valores.put(FilasDB.ColumnasPDU.Cantidad7,item.getCantidad7());
        valores.put(FilasDB.ColumnasPDU.Cantidad8,item.getCantidad8());
        valores.put(FilasDB.ColumnasPDU.Cantidad9,item.getCantidad9());
        valores.put(FilasDB.ColumnasPDU.NoParte1,item.getNoParte1());
        valores.put(FilasDB.ColumnasPDU.NoParte2,item.getNoParte2());
        valores.put(FilasDB.ColumnasPDU.NoParte3,item.getNoParte3());
        valores.put(FilasDB.ColumnasPDU.NoParte4,item.getNoParte4());
        valores.put(FilasDB.ColumnasPDU.NoParte5,item.getNoParte5());
        valores.put(FilasDB.ColumnasPDU.NoParte6,item.getNoParte6());
        valores.put(FilasDB.ColumnasPDU.NoParte7,item.getNoParte7());
        valores.put(FilasDB.ColumnasPDU.NoParte8,item.getNoParte8());
        valores.put(FilasDB.ColumnasPDU.NoParte9,item.getNoParte9());
        valores.put(FilasDB.ColumnasPDU.Descripcion1,item.getDescripcion1());
        valores.put(FilasDB.ColumnasPDU.Descripcion2,item.getDescripcion2());
        valores.put(FilasDB.ColumnasPDU.Descripcion3,item.getDescripcion3());
        valores.put(FilasDB.ColumnasPDU.Descripcion4,item.getDescripcion4());
        valores.put(FilasDB.ColumnasPDU.Descripcion5,item.getDescripcion5());
        valores.put(FilasDB.ColumnasPDU.Descripcion6,item.getDescripcion6());
        valores.put(FilasDB.ColumnasPDU.Descripcion7,item.getDescripcion7());
        valores.put(FilasDB.ColumnasPDU.Descripcion8,item.getDescripcion8());
        valores.put(FilasDB.ColumnasPDU.Descripcion9,item.getDescripcion9());
        valores.put(FilasDB.ColumnasPDU.Equipo1,item.getEquipo1());
        valores.put(FilasDB.ColumnasPDU.Equipo2,item.getEquipo2());
        valores.put(FilasDB.ColumnasPDU.Equipo3,item.getEquipo3());
        valores.put(FilasDB.ColumnasPDU.Equipo4,item.getEquipo4());
        valores.put(FilasDB.ColumnasPDU.Equipo5,item.getEquipo5());
        valores.put(FilasDB.ColumnasPDU.Equipo6,item.getEquipo6());
        valores.put(FilasDB.ColumnasPDU.Equipo7,item.getEquipo7());
        valores.put(FilasDB.ColumnasPDU.Equipo8,item.getEquipo8());
        valores.put(FilasDB.ColumnasPDU.Equipo9,item.getEquipo9());
        valores.put(FilasDB.ColumnasPDU.NoId1,item.getNoId1());
        valores.put(FilasDB.ColumnasPDU.NoId2,item.getNoId2());
        valores.put(FilasDB.ColumnasPDU.NoId3,item.getNoId3());
        valores.put(FilasDB.ColumnasPDU.NoId4,item.getNoId4());
        valores.put(FilasDB.ColumnasPDU.NoId5,item.getNoId5());
        valores.put(FilasDB.ColumnasPDU.NoId6,item.getNoId6());
        valores.put(FilasDB.ColumnasPDU.NoId7,item.getNoId7());
        valores.put(FilasDB.ColumnasPDU.NoId8,item.getNoId8());
        valores.put(FilasDB.ColumnasPDU.NoId9,item.getNoId9());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento1,item.getFechaVencimiento1());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento2,item.getFechaVencimiento2());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento3,item.getFechaVencimiento3());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento4,item.getFechaVencimiento4());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento5,item.getFechaVencimiento5());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento6,item.getFechaVencimiento6());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento7,item.getFechaVencimiento7());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento8,item.getFechaVencimiento8());
        valores.put(FilasDB.ColumnasPDU.FechaVencimiento9,item.getFechaVencimiento9());
        valores.put(FilasDB.ColumnasPDU.Antes1,item.getAntes1());
        valores.put(FilasDB.ColumnasPDU.Antes2,item.getAntes2());
        valores.put(FilasDB.ColumnasPDU.Antes3,item.getAntes3());
        valores.put(FilasDB.ColumnasPDU.Antes4,item.getAntes4());
        valores.put(FilasDB.ColumnasPDU.Antes5,item.getAntes5());
        valores.put(FilasDB.ColumnasPDU.Antes6,item.getAntes6());
        valores.put(FilasDB.ColumnasPDU.Despues1,item.getDespues1());
        valores.put(FilasDB.ColumnasPDU.Despues2,item.getDespues2());
        valores.put(FilasDB.ColumnasPDU.Despues3,item.getDespues3());
        valores.put(FilasDB.ColumnasPDU.Despues4,item.getDespues4());
        valores.put(FilasDB.ColumnasPDU.Despues5,item.getDespues5());
        valores.put(FilasDB.ColumnasPDU.Despues6,item.getDespues6());
        valores.put(FilasDB.ColumnasPDU.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasPDU.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasPDU.FirmaClienteFinal,item.getFirmaClienteFinal());
        valores.put(FilasDB.ColumnasPDU.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasPDU.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasPDU.NombreFirmaClienteFinal,item.getNombreFirmaClienteFinal());
        valores.put(FilasDB.ColumnasPDU.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasPDU.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasPDU.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasPDU.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasPDU.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasPDU.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasPDU.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasPDU.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasPDU.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasPDU.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasPDU.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasPDU.CLIENTEFINAL_TELEFONO,item.getCLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasPDU.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasPDU.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasPDU.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasPDU.TipoServicioEspecif,item.getTipoServicioEspecif());
        valores.put(FilasDB.ColumnasPDU.Serie2,item.getSerie2());



//endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasPDU.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.PDU, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean guardarSTS2(STS2 item){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        //region
        ContentValues valores = new ContentValues();

        valores.put(FilasDB.ColumnasSTS2.IDFormato,item.getIDFormato());
        valores.put(FilasDB.ColumnasSTS2.IdUsuario,item.getIdUsuario());
        valores.put(FilasDB.ColumnasSTS2.FolioPreTrabajo,item.getFolioPreTrabajo());
        valores.put(FilasDB.ColumnasSTS2.FechaInicio,item.getFechaInicio());
        valores.put(FilasDB.ColumnasSTS2.FechaTermino,item.getFechaTermino());
        valores.put(FilasDB.ColumnasSTS2.IdCliente,item.getIdCliente());
        valores.put(FilasDB.ColumnasSTS2.SRProyecto,item.getSRProyecto());
        valores.put(FilasDB.ColumnasSTS2.TASK,item.getTASK());
        valores.put(FilasDB.ColumnasSTS2.ContactoCliente,item.getContactoCliente());
        valores.put(FilasDB.ColumnasSTS2.TipoServicio,item.getTipoServicio());
        valores.put(FilasDB.ColumnasSTS2.Telefono,item.getTelefono());
        valores.put(FilasDB.ColumnasSTS2.Freecuencia,item.getFreecuencia());
        valores.put(FilasDB.ColumnasSTS2.EMail,item.getEMail());
        valores.put(FilasDB.ColumnasSTS2.NoTag,item.getNoTag());
        valores.put(FilasDB.ColumnasSTS2.ModeloEquipo,item.getModeloEquipo());
        valores.put(FilasDB.ColumnasSTS2.NoSerie,item.getNoSerie());
        valores.put(FilasDB.ColumnasSTS2.DireccionSitio,item.getDireccionSitio());
        valores.put(FilasDB.ColumnasSTS2.RevisionCableadoPotencia,item.getRevisionCableadoPotencia());
        valores.put(FilasDB.ColumnasSTS2.RevisionConexiones,item.getRevisionConexiones());
        valores.put(FilasDB.ColumnasSTS2.RevisionComponentesPotencia,item.getRevisionComponentesPotencia());
        valores.put(FilasDB.ColumnasSTS2.RevisionSCR,item.getRevisionSCR());
        valores.put(FilasDB.ColumnasSTS2.RevisarHistorialAlarmas,item.getRevisarHistorialAlarmas());
        valores.put(FilasDB.ColumnasSTS2.RevisionTarjetas,item.getRevisionTarjetas());
        valores.put(FilasDB.ColumnasSTS2.RevisionPanel,item.getRevisionPanel());
        valores.put(FilasDB.ColumnasSTS2.RevisionCapacitoresPotencia,item.getRevisionCapacitoresPotencia());
        valores.put(FilasDB.ColumnasSTS2.RevisionFuenteAlimentacion,item.getRevisionFuenteAlimentacion());
        valores.put(FilasDB.ColumnasSTS2.RevisionPoints,item.getRevisionPoints());
        valores.put(FilasDB.ColumnasSTS2.VoltajePreAB,item.getVoltajePreAB());
        valores.put(FilasDB.ColumnasSTS2.VoltajePreBC,item.getVoltajePreBC());
        valores.put(FilasDB.ColumnasSTS2.VoltajePreCA,item.getVoltajePreCA());
        valores.put(FilasDB.ColumnasSTS2.CorrientePreA,item.getCorrientePreA());
        valores.put(FilasDB.ColumnasSTS2.CorrientePreB,item.getCorrientePreB());
        valores.put(FilasDB.ColumnasSTS2.CorrientePreC,item.getCorrientePreC());
        valores.put(FilasDB.ColumnasSTS2.VoltajeEmeAB,item.getVoltajeEmeAB());
        valores.put(FilasDB.ColumnasSTS2.VoltajeEmeBC,item.getVoltajeEmeBC());
        valores.put(FilasDB.ColumnasSTS2.VoltajeEmeCA,item.getVoltajeEmeCA());
        valores.put(FilasDB.ColumnasSTS2.CorrienteEmeA,item.getCorrienteEmeA());
        valores.put(FilasDB.ColumnasSTS2.CorrienteEmeB,item.getCorrienteEmeB());
        valores.put(FilasDB.ColumnasSTS2.CorrienteEmeC,item.getCorrienteEmeC());
        valores.put(FilasDB.ColumnasSTS2.TemperaturaSCR,item.getTemperaturaSCR());
        valores.put(FilasDB.ColumnasSTS2.NoTransferencias,item.getNoTransferencias());
        valores.put(FilasDB.ColumnasSTS2.LimpiezaGeneral,item.getLimpiezaGeneral());
        valores.put(FilasDB.ColumnasSTS2.AjusteHorarios,item.getAjusteHorarios());
        valores.put(FilasDB.ColumnasSTS2.A48VDCTP1,item.getA48VDCTP1());
        valores.put(FilasDB.ColumnasSTS2.A24VDCTP5,item.getA24VDCTP5());
        valores.put(FilasDB.ColumnasSTS2.A74VDCTP7,item.getA74VDCTP7());
        valores.put(FilasDB.ColumnasSTS2.A5VDCTP8,item.getA5VDCTP8());
        valores.put(FilasDB.ColumnasSTS2.A33VDCTP6,item.getA33VDCTP6());
        valores.put(FilasDB.ColumnasSTS2.B24VDCTP5,item.getB24VDCTP5());
        valores.put(FilasDB.ColumnasSTS2.B74VDCTP7,item.getB74VDCTP7());
        valores.put(FilasDB.ColumnasSTS2.B5VDCTP8,item.getB5VDCTP8());
        valores.put(FilasDB.ColumnasSTS2.KVA,item.getKVA());
        valores.put(FilasDB.ColumnasSTS2.KW,item.getKW());
        valores.put(FilasDB.ColumnasSTS2.CargaFuenteA,item.getCargaFuenteA());
        valores.put(FilasDB.ColumnasSTS2.CargaFuenteB,item.getCargaFuenteB());
        valores.put(FilasDB.ColumnasSTS2.ComentariosRecomendaciones,item.getComentariosRecomendaciones());
        valores.put(FilasDB.ColumnasSTS2.Cantidad1,item.getCantidad1());
        valores.put(FilasDB.ColumnasSTS2.Cantidad2,item.getCantidad2());
        valores.put(FilasDB.ColumnasSTS2.Cantidad3,item.getCantidad3());
        valores.put(FilasDB.ColumnasSTS2.Cantidad4,item.getCantidad4());
        valores.put(FilasDB.ColumnasSTS2.Cantidad5,item.getCantidad5());
        valores.put(FilasDB.ColumnasSTS2.Cantidad6,item.getCantidad6());
        valores.put(FilasDB.ColumnasSTS2.Cantidad7,item.getCantidad7());
        valores.put(FilasDB.ColumnasSTS2.Cantidad8,item.getCantidad8());
        valores.put(FilasDB.ColumnasSTS2.Cantidad9,item.getCantidad9());
        valores.put(FilasDB.ColumnasSTS2.NoParte1,item.getNoParte1());
        valores.put(FilasDB.ColumnasSTS2.NoParte2,item.getNoParte2());
        valores.put(FilasDB.ColumnasSTS2.NoParte3,item.getNoParte3());
        valores.put(FilasDB.ColumnasSTS2.NoParte4,item.getNoParte4());
        valores.put(FilasDB.ColumnasSTS2.NoParte5,item.getNoParte5());
        valores.put(FilasDB.ColumnasSTS2.NoParte6,item.getNoParte6());
        valores.put(FilasDB.ColumnasSTS2.NoParte7,item.getNoParte7());
        valores.put(FilasDB.ColumnasSTS2.NoParte8,item.getNoParte8());
        valores.put(FilasDB.ColumnasSTS2.NoParte9,item.getNoParte9());
        valores.put(FilasDB.ColumnasSTS2.Descripcion1,item.getDescripcion1());
        valores.put(FilasDB.ColumnasSTS2.Descripcion2,item.getDescripcion2());
        valores.put(FilasDB.ColumnasSTS2.Descripcion3,item.getDescripcion3());
        valores.put(FilasDB.ColumnasSTS2.Descripcion4,item.getDescripcion4());
        valores.put(FilasDB.ColumnasSTS2.Descripcion5,item.getDescripcion5());
        valores.put(FilasDB.ColumnasSTS2.Descripcion6,item.getDescripcion6());
        valores.put(FilasDB.ColumnasSTS2.Descripcion7,item.getDescripcion7());
        valores.put(FilasDB.ColumnasSTS2.Descripcion8,item.getDescripcion8());
        valores.put(FilasDB.ColumnasSTS2.Descripcion9,item.getDescripcion9());
        valores.put(FilasDB.ColumnasSTS2.Equipo1,item.getEquipo1());
        valores.put(FilasDB.ColumnasSTS2.Equipo2,item.getEquipo2());
        valores.put(FilasDB.ColumnasSTS2.Equipo3,item.getEquipo3());
        valores.put(FilasDB.ColumnasSTS2.Equipo4,item.getEquipo4());
        valores.put(FilasDB.ColumnasSTS2.Equipo5,item.getEquipo5());
        valores.put(FilasDB.ColumnasSTS2.Equipo6,item.getEquipo6());
        valores.put(FilasDB.ColumnasSTS2.Equipo7,item.getEquipo7());
        valores.put(FilasDB.ColumnasSTS2.Equipo8,item.getEquipo8());
        valores.put(FilasDB.ColumnasSTS2.Equipo9,item.getEquipo9());
        valores.put(FilasDB.ColumnasSTS2.NoId1,item.getNoId1());
        valores.put(FilasDB.ColumnasSTS2.NoId2,item.getNoId2());
        valores.put(FilasDB.ColumnasSTS2.NoId3,item.getNoId3());
        valores.put(FilasDB.ColumnasSTS2.NoId4,item.getNoId4());
        valores.put(FilasDB.ColumnasSTS2.NoId5,item.getNoId5());
        valores.put(FilasDB.ColumnasSTS2.NoId6,item.getNoId6());
        valores.put(FilasDB.ColumnasSTS2.NoId7,item.getNoId7());
        valores.put(FilasDB.ColumnasSTS2.NoId8,item.getNoId8());
        valores.put(FilasDB.ColumnasSTS2.NoId9,item.getNoId9());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento1,item.getFechaVencimiento1());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento2,item.getFechaVencimiento2());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento3,item.getFechaVencimiento3());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento4,item.getFechaVencimiento4());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento5,item.getFechaVencimiento5());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento6,item.getFechaVencimiento6());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento7,item.getFechaVencimiento7());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento8,item.getFechaVencimiento8());
        valores.put(FilasDB.ColumnasSTS2.FechaVencimiento9,item.getFechaVencimiento9());
        valores.put(FilasDB.ColumnasSTS2.Antes1,item.getAntes1());
        valores.put(FilasDB.ColumnasSTS2.Antes2,item.getAntes2());
        valores.put(FilasDB.ColumnasSTS2.Antes3,item.getAntes3());
        valores.put(FilasDB.ColumnasSTS2.Antes4,item.getAntes4());
        valores.put(FilasDB.ColumnasSTS2.Antes5,item.getAntes5());
        valores.put(FilasDB.ColumnasSTS2.Antes6,item.getAntes6());
        valores.put(FilasDB.ColumnasSTS2.Despues1,item.getDespues1());
        valores.put(FilasDB.ColumnasSTS2.Despues2,item.getDespues2());
        valores.put(FilasDB.ColumnasSTS2.Despues3,item.getDespues3());
        valores.put(FilasDB.ColumnasSTS2.Despues4,item.getDespues4());
        valores.put(FilasDB.ColumnasSTS2.Despues5,item.getDespues5());
        valores.put(FilasDB.ColumnasSTS2.Despues6,item.getDespues6());
        valores.put(FilasDB.ColumnasSTS2.FirmaCliente,item.getFirmaCliente());
        valores.put(FilasDB.ColumnasSTS2.FirmaVertiv,item.getFirmaVertiv());
        valores.put(FilasDB.ColumnasSTS2.FirmaClienteFinal,item.getFirmaClienteFinal());
        valores.put(FilasDB.ColumnasSTS2.NombreFirmaCliente,item.getNombreFirmaCliente());
        valores.put(FilasDB.ColumnasSTS2.NombreFirmaVertiv,item.getNombreFirmaVertiv());
        valores.put(FilasDB.ColumnasSTS2.NombreFirmaClienteFinal,item.getNombreFirmaClienteFinal());
        valores.put(FilasDB.ColumnasSTS2.AD_NOMBRE1,item.getAD_NOMBRE1());
        valores.put(FilasDB.ColumnasSTS2.AD_CORREO1,item.getAD_CORREO1());
        valores.put(FilasDB.ColumnasSTS2.AD_NOMBRE2,item.getAD_NOMBRE2());
        valores.put(FilasDB.ColumnasSTS2.AD_CORREO2,item.getAD_CORREO2());
        valores.put(FilasDB.ColumnasSTS2.AD_NOMBRE3,item.getAD_NOMBRE3());
        valores.put(FilasDB.ColumnasSTS2.AD_CORREO3,item.getAD_CORREO3());
        valores.put(FilasDB.ColumnasSTS2.AD_NOMBRE4,item.getAD_NOMBRE4());
        valores.put(FilasDB.ColumnasSTS2.AD_CORREO4,item.getAD_CORREO4());
        valores.put(FilasDB.ColumnasSTS2.AD_NOMBRE5,item.getAD_NOMBRE5());
        valores.put(FilasDB.ColumnasSTS2.AD_CORREO5,item.getAD_CORREO5());
        valores.put(FilasDB.ColumnasSTS2.CLIENTEFINAL_EMPRESA,item.getCLIENTEFINAL_EMPRESA());
        valores.put(FilasDB.ColumnasSTS2.CLIENTEFINAL_TELEFONO,item.getCLIENTEFINAL_TELEFONO());
        valores.put(FilasDB.ColumnasSTS2.CLIENTEFINAL_CORREO,item.getCLIENTEFINAL_CORREO());
        valores.put(FilasDB.ColumnasSTS2.Proyecto,item.getProyecto());
        valores.put(FilasDB.ColumnasSTS2.Referencia,item.getReferencia());
        valores.put(FilasDB.ColumnasSTS2.TipoServicioEspecif,item.getTipoServicioEspecif());
        valores.put(FilasDB.ColumnasSTS2.Serie2,item.getSerie2());
//endregion

        String whereClause = String.format("%s=? ", FilasDB.ColumnasSTS2.IDFormato);
        String[] whereArgs = {item.getIDFormato()};

        int resultado = db.update(BaseDatos.Tablas.STS2, valores, whereClause, whereArgs);

        return resultado > 0;
    }

// endregion

    //region OPERACIONES DELETE

    public void borrarTablas(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.delete(BaseDatos.Tablas.USUARIO,null,null);
        db.delete(BaseDatos.Tablas.SESION,null,null);
        db.delete(BaseDatos.Tablas.PRE_ORDEN,null,null);
        db.delete(BaseDatos.Tablas.DC_POWER,null,null);
        db.delete(BaseDatos.Tablas.CONTROL_FORMATOS,null,null);
        db.delete(BaseDatos.Tablas.BATERIAS,null,null);
        db.delete(BaseDatos.Tablas.TASK,null,null);
        db.delete(BaseDatos.Tablas.BESTEL,null,null);
        db.delete(BaseDatos.Tablas.CAT_FOLIOS_PRET,null,null);
        db.close();
    }

    public void borrarTablasSinc(String tabla){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        if(tabla.equals("tareas")) {
            db.delete(BaseDatos.Tablas.TASK, null, null);
        }
        if(tabla.equals("folios")) {
            db.delete(BaseDatos.Tablas.CAT_FOLIOS_PRET, null, null);
        }
        if(tabla.equals("clientes")) {
            db.delete(BaseDatos.Tablas.CLIENTEs, null, null);
        }
        if(tabla.equals("bestel")) {
            db.delete(BaseDatos.Tablas.BESTEL, null, null);
        }
        if(tabla.equals("estados")) {
            db.delete(BaseDatos.Tablas.ESTADOS, null, null);
        }
        if(tabla.equals("regiones")) {
            db.delete(BaseDatos.Tablas.REGIONES, null, null);
        }


        db.close();
    }

    public void borrarFormatoSeguimiento(String idFormato){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatos.Tablas.CONTROL_FORMATOS;
        String whereClause = String.format("%s=?", FilasDB.ColumnasFormatosControl.ID_FORMATO);
        String[] whereArgs = new String[] {idFormato};
        db.delete(table, whereClause, whereArgs);
    }

    public void borrarFormato(String idFormato, String tipoformato){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatos.Tablas.CONTROL_FORMATOS;
        String whereClause = String.format("%s=?", FilasDB.ColumnasFormatosControl.ID_FORMATO);
        String[] whereArgs = new String[] {idFormato};
        db.delete(table, whereClause, whereArgs);


        SQLiteDatabase db2 = baseDatos.getWritableDatabase();
        String tableF = "";//BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO;
        String whereClauseF = "";//String.format("%s=? and %s=?", FilasBD.ColumnasProductosPedido.ID_PEDIDO,FilasBD.ColumnasProductosPedido.ID_PRODUCTO);
        String[] whereArgsF = null;//new String[] {""};

        switch (tipoformato){
            case "0":
                tableF = BaseDatos.Tablas.PRE_ORDEN;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasPreOrden.ID_PreOrden);
                whereArgsF = new String[] {idFormato};
                break;
            case "1":
                tableF = BaseDatos.Tablas.CALIDAD;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasCaliad.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "2":
                tableF = BaseDatos.Tablas.BATERIAS;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasBaterias.ID_fORMATO);
                whereArgsF = new String[] {idFormato};
                break;
            case "3":
                tableF = BaseDatos.Tablas.DC_POWER;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasDCPwer.ID_fORMATO);
                whereArgsF = new String[] {idFormato};
                break;
            case "4":
                tableF = BaseDatos.Tablas.GARANTIAS;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasGarantias.FolioFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "5":
                tableF = BaseDatos.Tablas.BESTEL1;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasBestel1.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "6":
                tableF = BaseDatos.Tablas.BESTEL2;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasBestel2.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "7":
                tableF = BaseDatos.Tablas.PDU;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasPDU.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "8":
                tableF = BaseDatos.Tablas.SERVICIOS;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasServicios.FolioFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "9":
                tableF = BaseDatos.Tablas.STS2;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasSTS2.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "10":
                tableF = BaseDatos.Tablas.TERMAL;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasThermal.IDFormato);
                whereArgsF = new String[] {idFormato};
                break;
            case "11":
                tableF = BaseDatos.Tablas.UPS;
                whereClauseF = String.format("%s=?", FilasDB.ColumnasUPS.FolioFormato);
                whereArgsF = new String[] {idFormato};
                break;
        }



        db2.delete(tableF, whereClauseF, whereArgsF);
    }

    public void borrarImagenError(String id, String formato){

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatos.Tablas.imagenes;
        String whereClause = String.format("%s=? and %s=?", FilasDB.ColumnasImagenes.Id, FilasDB.ColumnasImagenes.Formato);
        String[] whereArgs = new String[] {id, formato};
        db.delete(table, whereClause, whereArgs);
    }

    public void borrarmensajeError(String mensaje){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatos.Tablas.MENSAJES;
        String whereClause = String.format("%s=?", FilasDB.ColumnasMensajes.mensaje);
        String[] whereArgs = new String[] {mensaje};
        db.delete(table, whereClause, whereArgs);
/*
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = "DELETE FROM " + BaseDatos.Tablas.MENSAJES + " WHERE " + FilasDB.ColumnasMensajes.mensaje + " LIKE "+
               "'%" + mensaje + "%'";
        db.rawQuery(sql, null);
*/
    }


/*
    public void actualizarParametros(){


        SQLiteDatabase database = baseDatos.getWritableDatabase();
        database.delete(BaseDatosTransfer.Tablas.PARAMETROS_CONFIGURACION,null,null);
        database.close();
    }

    public void borrarTablas(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.delete(BaseDatosTransfer.Tablas.USUARIO,null,null);
        db.delete(BaseDatosTransfer.Tablas.SESION,null,null);
        db.delete(BaseDatosTransfer.Tablas.PEDIDO,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRECIOS_CLIENTE,null,null);
        db.delete(BaseDatosTransfer.Tablas.FARMACIAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE,null,null);
        db.delete(BaseDatosTransfer.Tablas.EXISTENCIAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.PEDIDO+"_recalcular",null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular",null,null);
        db.delete(BaseDatosTransfer.Tablas.SEGUIMIENTOENC,null,null);
        db.delete(BaseDatosTransfer.Tablas.SEGUIMIENTODET,null,null);

        fechasSinc("parametros","Sin sincronizar");
        fechasSinc("precios","Sin sincronizar");
        fechasSinc("clientes","Sin sincronizar");
        fechasSinc("existencias","Sin sincronizar");
        fechasSinc("productos","Sin sincronizar");
        fechasSinc("campañas","Sin sincronizar");
        fechasSinc("ultima","Sin sincronizar");

        // +"_recalcular"

        db.close();
    }


    public void borrarTablasCambioAmbiente(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.delete(BaseDatosTransfer.Tablas.PEDIDO,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRECIOS_CLIENTE,null,null);
        db.delete(BaseDatosTransfer.Tablas.FARMACIAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE,null,null);
        db.delete(BaseDatosTransfer.Tablas.EXISTENCIAS,null,null);
        db.delete(BaseDatosTransfer.Tablas.PEDIDO+"_recalcular",null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular",null,null);
        db.delete(BaseDatosTransfer.Tablas.SEGUIMIENTOENC,null,null);
        db.delete(BaseDatosTransfer.Tablas.SEGUIMIENTODET,null,null);

        fechasSinc("precios","Sin sincronizar");
        fechasSinc("clientes","Sin sincronizar");
        fechasSinc("existencias","Sin sincronizar");
        fechasSinc("productos","Sin sincronizar");
        fechasSinc("campañas","Sin sincronizar");
        fechasSinc("ultima","Sin sincronizar");

        // +"_recalcular"

        db.close();
    }

    public void borrarHistoricoPedidos(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.delete(BaseDatosTransfer.Tablas.PEDIDO,null,null);
        db.delete(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO,null,null);
        db.close();
    }

    public void borrarTablasActualizar(String tablas){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        if(tablas.contains("Parametros")){db.delete(BaseDatosTransfer.Tablas.PARAMETROS_CONFIGURACION,null,null);}
        //if(tablas.contains("Precios")){db.delete(BaseDatosTransfer.Tablas.FARMACIAS,null,null);}
        if(tablas.contains("Clientes")){db.delete(BaseDatosTransfer.Tablas.FARMACIAS,null,null);}
        if(tablas.contains("Existencias")){db.delete(BaseDatosTransfer.Tablas.EXISTENCIAS,null,null);}
        if(tablas.contains("Productos")){db.delete(BaseDatosTransfer.Tablas.PRODUCTOS,null,null);}

        if(tablas.contains("Campañas")){
            db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS,null,null);
            db.delete(BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE,null,null);
        }
        if(tablas.contains("OrdenListas")){
            db.delete("parametrosInternos",null,null);
        }
        db.close();
    }

    public void borrarproductodePedido(String idPedido, String IdProducto){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO;
        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasProductosPedido.ID_PEDIDO,FilasBD.ColumnasProductosPedido.ID_PRODUCTO);
        String[] whereArgs = new String[] {idPedido,IdProducto };
        db.delete(table, whereClause, whereArgs);
    }

    public void borrarPedido(String idPedido, String keyPedido){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String pedido = BaseDatosTransfer.Tablas.PEDIDO;
        String detallePedido = BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO;
        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = new String[] {idPedido,keyPedido };

        db.delete(pedido, whereClause, whereArgs);
        db.delete(detallePedido, whereClause, whereArgs);



    }

    public void borrarPedidoRecalculado(String idPedido, String keyPedido){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String pedido = BaseDatosTransfer.Tablas.PEDIDO+"_recalcular";
        String detallePedido = BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular";
        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = new String[] {idPedido,keyPedido };

        db.delete(pedido, whereClause, whereArgs);
        db.delete(detallePedido, whereClause, whereArgs);



    }

    public void borrarExistencias(String idAlmacen){
        //BORRAR EXISTENCIAS
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatosTransfer.Tablas.EXISTENCIAS;
        String whereClause = String.format("%s=?", FilasBD.ColumnasExistencias.IDALMACEN);
        String[] whereArgs = new String[] {idAlmacen};
        db.delete(table, whereClause, whereArgs);
    }

    private void borrarListaPrecios(String idfarmacia) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        String table = BaseDatosTransfer.Tablas.PRECIOS_CLIENTE;
        String whereClause = String.format("%s=?", FilasBD.ClolumnasPreciosCliente.IDFARMACIA);
        String[] whereArgs = new String[] {idfarmacia};
        db.delete(table, whereClause, whereArgs);
    }


*/
// endregion


    public PreTrabajo obtenerPTPrelleno(){
        PreTrabajo item = new PreTrabajo();
        /*item.setGEN_SR(" ");
         item.setGEN_TASK(" ");
         item.setGEN_PROYECTO(" ");
         item.setGEN_SITIO(" ");
         item.setGEN_REFERENCIA(" ");
         item.setGEN_USUARIO_FINAL(" ");
        item.setINSPEC_PERMISO_ARCHIVO("");
        item.setFIRMA_1_CARGO(" ");
        item.setFIRMA_2_CARGO(" ");
        item.setFIRMA_3_CARGO(" ");
        item.setFIRMA_4_CARGO(" ");
        item.setFIRMA_5_CARGO(" ");
        item.setFIRMA_6_CARGO(" ");
        item.setIdUsuario(" ");
        item.setIdCliente(" ");
        item.setExitoso(" ");
        item.setError(" ");
        item.setFolioPreTrabajo(" ");
        item.setNombreSitio(" ");
        item.setFecha(" ");
        item.setLiderGrupoCuadrilla(" ");
        item.setDireccionSitio(" ");
        item.setDescripcionActividad(" ");
        item.setEquiposHerramientasMateriales(" ");
        item.setOtrosArea(null);
        item.setOtrosRiesgos(null);
        item.setOtrosMedidasPrevension(null);
        item.setAdiconales(" ");
        item.setEspecifiqueDano(" ");
        item.setCondicionInsegura(" ");
        item.setMedidasCorrectivas(" ");
        item.setNombre1(" ");

        item.setNombre2(" ");

        item.setNombre3(" ");

        item.setNombre4(" ");

        item.setNombre5(" ");

        item.setNombre6(" ");

        item.setNumeroEmergencia(" ");
        item.setNumeroSupervisor(" ");
        item.setHospitalCercano(" ");

        item.setArchivoFirma1(" ");
        item.setArchivoFirma2(" ");
        item.setArchivoFirma3(" ");
        item.setArchivoFirma4(" ");
        item.setArchivoFirma5(" ");
        item.setArchivoFirma6(" ");



        item.setEPP_ELECTICO_GUANTES(false);
        item.setHojasSeguridad(false);
        item.setCintaPrecaucion(false);
        item.setConosPedestales(false);
        item.setMixta(false);
        item.setMalla(false);
        item.setProyeccionParticulas(false);
        item.setAtrapamiento(false);
        item.setGolpesCortes(false);
        item.setQuemaduras(false);
        item.setCaidaMateriales(false);
        item.setCaidaMismoNivel(false);
        item.setCaidaDistintoNivel(false);
        item.setOrdenLimpieza(false);
        item.setOtroPersonalTrabajando(false);
        item.setChoqueElectrico(false);
        item.setArcoElectrico(false);
        item.setFuegoExplosion(false);
        item.setExposicionRuido(false);
        item.setExposicionVibraciones(false);
        item.setFatigaVisual(false);
        item.setExposicionAltasBjasTemperaturas(false);
        item.setDeficienciaOxigeno(false);
        item.setExposiconGases(false);
        item.setExposicionPolvo(false);
        item.setSobreEsfuerzo(false);
        item.setManipulacionProductosQuimicos(false);
        item.setRuido(false);
        item.setUsarDispositivosElevacion(false);
        item.setSustituirQuimicosToxicos(false);
        item.setAislarRuidoGenerado(false);
        item.setColocarGuardasProtectoras(false);
        item.setInstalarPlataformas(false);
        item.setInstalarSistemaPuntosAnclaje(false);
        item.setInstalarIluminacion(false);
        item.setInstalarDisyuntor(false);
        item.setInstalarSistemaPuestaTierra(false);
        item.setMantenerOrden(false);
        item.setSeñalizarAreaTrabajo(false);
        item.setBloquearEtiquetarFuentesEnergia(false);
        item.setInstalarMurosContenerDerrames(false);
        item.setPermisoTrabajo(false);
        item.setProcedTrabajo(false);
        item.setSupervisionPermanente(false);
        item.setUsarHerramientaAislada(false);
        item.setEquipoProteccionPersonal(false);
        item.setCasco(false);
        item.setGafasProtectoras(false);
        item.setProtectoresAuditores(false);
        item.setZapatosSeguridad(false);
        item.setGuantesTrabajo(false);
        item.setBarbiquejo(false);
        item.setGafasSeguridad(false);
        item.setCascoElectrico(false);
        item.setZapatosDielectricos(false);
        item.setSobreguanteCuero(false);
        item.setCaretaArcoElectrico(false);
        item.setBalaClava(false);
        item.setTrajeArcoElectrico(false);
        item.setProtectoresAuditivos(false);
        item.setMangasDielectricas(false);
        item.setProteccionContraCaidas(false);
        item.setProteccionRespiratoria(false);
        item.setProteccionSoldadora(false);
        item.setProteccionContraQuimicos(false);
        item.setInspeccionEPP(false);
        item.setEntradaEspaciosConfinados(false);
        item.setTrabajosCaliente(false);
        item.setTrabajosEquiposEnergizados(false);
        item.setNA(false);
        item.setTrabajosAltura(false);
        item.setCondicionesInseguras(false);
*/
        return item;

    }

    public Bestel1 obtenerBestel1Prellenado(){
        Bestel1 item = new Bestel1();
        item.setIdUsuario("7ffea537-4c3c-4c60-bb31-a2a68be5712f");
        item.setCliente("71daac28-18b3-4d97-a144-f57d71190afc");
        item.setFolioPreTrabajo("VRT00000026");
        item.setFechaInicio("12/01/2020 10:28");
        item.setFechaTermino("12/01/2020 10:28");
        //item.setEDSRTiempo(false);
        //item.setEDSRCalidad(false);
        //item.setEDSREvalucionSitio(false);
        /*item.setExitoso(" ");
        item.setError(" ");
        item.setIdBestelNivel1(" ");
        item.setSRProyecto(" ");
        item.setIdRegion(" ");
        item.setIdEstado(" ");
        item.setTipoSitio(" ");
        item.setNombreSitio(" ");
        item.setDireccionSitio(" ");
        item.setContactoCliente(" ");
        item.setTelefono(" ");
        item.setTask(" ");
        item.setTipoServicio(" ");
        item.setFrecuencia(" ");
        item.setNoTag(" ");
        item.setEmail(" ");
        item.setAATemperaturaEstatus(" ");
        item.setAATemperaturaComentarios(" ");
        item.setAACondensadoraEstatus(" ");
        item.setAACondensadoraComentarios(" ");
        item.setAAEvaporadoraEstatus(" ");
        item.setAAEvaporadoraComentarios(" ");
        item.setAASerpentinEstatus(" ");
        item.setAASerpentinComentarios(" ");
        item.setAAFugaGasEstatus(" ");
        item.setAAFugaGasComentarios(" ");
        item.setAAAlimentacionEstatus(" ");
        item.setAAAlimentacionComentarios(" ");
        item.setAAFiltrosEstatus(" ");
        item.setAAFiltrosComentarios(" ");
        item.setAALimpiezaGeneralEstatus(" ");
        item.setAALimpiezaGeneralComentarios(" ");
        item.setECIluminaciionEstatus(" ");
        item.setECIluminaciionComentarios(" ");
        item.setECPinturaEstatus(" ");
        item.setECPinturaComentarios(" ");
        item.setECPisosEstatus(" ");
        item.setECPisosComentarios(" ");
        item.setECImpermeEstatus(" ");
        item.setECImpermeComentarios(" ");
        item.setECHidrosanitarioEstatus(" ");
        item.setECHidrosanitarioComentarios(" ");
        item.setECHerrejesEstatus(" ");
        item.setECHerrejesComentarios(" ");
        item.setECLimpiezaGeneralEstatus(" ");
        item.setECLimpiezaGeneralComentarios(" ");
        item.setPFBAmperajeEstatus(" ");
        item.setPFBAmperajeComentarios(" ");
        item.setPFBConsumoPlantaEstatus(" ");
        item.setPFBConsumoPlantaComentarios(" ");
        item.setPFBRectificadoresEstatus(" ");
        item.setPFBRectificadoresComentarios(" ");
        item.setPFBSistemaInversorEstatus(" ");
        item.setPFBSistemaInversorComentarios(" ");
        item.setPFBBancosBateriasEstatus(" ");
        item.setPFBBancosBateriasComentarios(" ");
        item.setPFBTablerosEstatus(" ");
        item.setPFBTablerosComentarios(" ");
        item.setSUAlimentacionElectricaEstatus(" ");
        item.setSUAlimentacionElectricaComentarios(" ");
        item.setSUAlarmasEstatus(" ");
        item.setSUAlarmasComentarios(" ");
        item.setSUCargaEstatus(" ");
        item.setSUCargaComentarios(" ");
        item.setSUDescargaEstatus(" ");
        item.setSUDescargaComentarios(" ");
        item.setSCISistemaEstatus(" ");
        item.setSCISistemaComentarios(" ");
        item.setSCIDetectoresEstatus(" ");
        item.setSCIDetectoresComentarios(" ");
        item.setSCIExtintoresEstatus(" ");
        item.setSCIExtintoresComentarios(" ");
        item.setSCIGranadaTanquesEstatus(" ");
        item.setSCIGranadaTanquesComentarios(" ");
        item.setSCIFechaCaducidadEstatus(" ");
        item.setSCIFechaCaducidadComentraios(" ");
        item.setPEFugasAceiteEstatus(" ");
        item.setPEFugasAceiteComentarios(" ");
        item.setPEFiltrosEstatus(" ");
        item.setPEFiltrosComentarios(" ");
        item.setPETemperaturaEstatus(" ");
        item.setPETemperaturaComentarios(" ");
        item.setPEBandasEstatus(" ");
        item.setPEBandasComentarios(" ");
        item.setPEBateriasEstatus(" ");
        item.setPEBateriasComentarios(" ");
        item.setPELubricacionEstatus(" ");
        item.setPELubricacionComentarios(" ");
        item.setPECombustibleEstatus(" ");
        item.setPECombustibleComentarios(" ");
        item.setPEArranqueManualEstatus(" ");
        item.setPEArranqueManualComentarios(" ");
        item.setPELimpiezaGenetalEstatus(" ");
        item.setPELimpiezaGenetalComentarios(" ");
        item.setSALectorasEstatus(" ");
        item.setSALectorasComentarios(" ");
        item.setSATablerosControlEstatus(" ");
        item.setSATablerosControlComentarios(" ");
        item.setSSBarrasPuestaTierraEstatus(" ");
        item.setSSBarrasPuestaTierraComentarios(" ");
        item.setSSConexionPuestaTierraEstatus(" ");
        item.setSSConexionPuestaTierraComentarios(" ");
        item.setSSTransformadorEstatus(" ");
        item.setSSTransformadorComentarios(" ");
        item.setSSFusiblesEstatus(" ");
        item.setSSFusiblesComentarios(" ");
        item.setSSTemperaturaEstatus(" ");
        item.setSSTemperaturaComentarios(" ");
        item.setSSCuchillasEstatus(" ");
        item.setSSCuchillasComentarios(" ");
        item.setSSInterruptoresEstatus(" ");
        item.setSSInterruptoresComentarios(" ");
        item.setTELimpizaGeneralEstatus(" ");
        item.setTELimpizaGeneralComentarios(" ");
        item.setTEAnclasRetenidosEstatus(" ");
        item.setTEAnclasRetenidosComentarios(" ");
        item.setTELucesObstruccionEstatus(" ");
        item.setTELucesObstruccionComentarios(" ");
        item.setTETornilleriaEstatus(" ");
        item.setTETornilleriaComentarios(" ");
        item.setTEPuestaTierraEstatus(" ");
        item.setTEPuestaTierraComentarios(" ");
        item.setTESistemaApartaRayosEststus(" ");
        item.setTESistemaApartaRayosComentarios(" ");
        item.setHFugasEstatus(" ");
        item.setHFugasComentarios(" ");
        item.setHBombasEstatus(" ");
        item.setHBombasComentarios(" ");
        item.setOtros(" ");
        item.setComentarios(" ");
        item.setMateriales(" ");

        item.setAntesFoto1(" ");
        item.setAntesFoto2(" ");
        item.setAntesFoto3(" ");
        item.setAntesFoto4(" ");
        item.setAntesFoto5(" ");
        item.setAntesFoto6(" ");
        item.setDespuesFoto1(" ");
        item.setDespuesFoto2(" ");
        item.setDespuesFoto3(" ");
        item.setDespuesFoto4(" ");
        item.setDespuesFoto5(" ");
        item.setDespuesFoto6(" ");
        item.setFirmaCliente(" ");
        item.setFirmaVertiv(" ");
*/


        return item;
    }

    public Bestel2 obtenerBestel2Prellenado(){
        Bestel2 item = new Bestel2();
        item.setIdUsuario(" ");
        item.setIdCliente(" ");
        item.setExitoso(" ");
        item.setError(" ");
        item.setIdBestelNivel2(" ");
        item.setFolioPreTrabajo(" ");
        item.setFechaInicio(" ");
        item.setFechaTermino(" ");
        item.setSRProyecto(" ");
        item.setTask(" ");
        item.setIdRegion(" ");
        item.setTipoServicio(" ");
        item.setIdEstado(" ");
        item.setFrecuencia(" ");
        item.setTipoSitio(" ");
        item.setNoTag(" ");
        item.setNombreSitio(" ");
        item.setEmail(" ");
        item.setDireccionSitio(" ");
        item.setContactoCliente(" ");
        item.setTelefono(" ");
        item.setAATemperatura(" ");
        item.setAACondensadora(" ");
        item.setAAEvaporadora(" ");
        item.setAASerpentin(" ");
        item.setAAFugaGas(" ");
        item.setAAVoltajeL1(" ");
        item.setAAVoltajeL2(" ");
        item.setAAVoltajeL3(" ");
        item.setAAAmperajeL1(" ");
        item.setAAAmperajeL2(" ");
        item.setAAAmperajeL3(" ");
        item.setAAValvulas(" ");
        item.setAATermostatos(" ");
        item.setAABombas(" ");
        item.setAAPresionAlta(" ");
        item.setAAPresionBaja(" ");
        item.setAAFiltros(" ");
        item.setAALimpiezaGeneral(" ");
        item.setECIliminacion(" ");
        item.setECPinturaMuros(" ");
        item.setECPisos(" ");
        item.setECImpermeabilizacion(" ");
        item.setECHidrosanitario(" ");
        item.setECHerrejes(" ");
        item.setECLimpiezaGeneral(" ");
        item.setPFVoltajeSalida(" ");
        item.setPFCorrienteDisplay(" ");
        item.setPFTensioDispaly(" ");
        item.setPFTemSalas(" ");
        item.setPFTensionl1l2(" ");
        item.setPFTensionl2l3(" ");
        item.setPFTensionl1l3(" ");
        item.setPFCorrientesL1(" ");
        item.setPFCorrientesL2(" ");
        item.setPFCorrientesL3(" ");
        item.setPFVoltajeNL1(" ");
        item.setPFVoltajeNL2(" ");
        item.setPFVoltajeNL3(" ");
        item.setPFVoltajeFaseNeutro(" ");
        item.setPFRectificadoresTemp(" ");
        item.setPFFechaControl(" ");
        item.setPFLimpieza(" ");
        item.setSCSistema(" ");
        item.setSCDetectores(" ");
        item.setSCExtintores(" ");
        item.setSCBotesAreneros(" ");
        item.setSCFechaCaducidad(" ");
        item.setSALectoras(" ");
        item.setSASupervisoresPuertas(" ");
        item.setSACCTV(" ");
        item.setSABaterias(" ");
        item.setSATablerosControl(" ");
        item.setSUAlarmas(" ");
        item.setSUTemperatura(" ");
        item.setSUCapacitores(" ");
        item.setSUVoltajeEntradaL1(" ");
        item.setSUVoltajeEntradaL2(" ");
        item.setSUVoltajeEntradaL3(" ");
        item.setSUVoltajeByPassL1(" ");
        item.setSUVoltajeByPassL2(" ");
        item.setSUVoltajeByPassL3(" ");
        item.setSUVoltajeSalidaL1(" ");
        item.setSUVoltajeSalidaL2(" ");
        item.setSUVoltajeSalidaL3(" ");
        item.setSUCorrienteSalidaL1(" ");
        item.setSUCorrienteSalidaL2(" ");
        item.setSUCorrienteSalidaL3(" ");
        item.setSUVoltajeTotalBaterias(" ");
        item.setSUVerificacionVentiladiores(" ");
        item.setSUReapreteConexiones(" ");
        item.setSUCorrienteCargaBaterias(" ");
        item.setSUCorrienteDescargaBaterias(" ");
        item.setSUTorqueBaterias(" ");
        item.setSUVoltajeEntreTierra(" ");
        item.setSULimpieza(" ");
        item.setPEFugasAceite(" ");
        item.setPEAmperajeL1(" ");
        item.setPEAmperajeL2(" ");
        item.setPEAmperajeL3(" ");
        item.setPEVoltajeL1(" ");
        item.setPEVoltajeL2(" ");
        item.setPEVoltajeL3(" ");
        item.setPEHorasOperacion(" ");
        item.setPEBaterias(" ");
        item.setPENivelDisel(" ");
        item.setPENivelAnticongelante(" ");
        item.setPEManguerasGeneral(" ");
        item.setPERuidosExtraños(" ");
        item.setPETableroTransparencia(" ");
        item.setPEPrecalentador(" ");
        item.setPEFiltros(" ");
        item.setPETemperatura(" ");
        item.setPEBandas(" ");
        item.setPEBateriasLiquido(" ");
        item.setPELubricacion(" ");
        item.setPEArranqueManualSinCarga(" ");
        item.setPELimpiezaGeneral(" ");
        item.setOPTEM(" ");
        item.setOPL1L2(" ");
        item.setOPL2L3(" ");
        item.setOPL3L1(" ");
        item.setOPP1W(" ");
        item.setOPP1VAR(" ");
        item.setOPP1VA(" ");
        item.setOPL1N(" ");
        item.setOPL2N(" ");
        item.setOPL3N(" ");
        item.setOPPF1(" ");
        item.setOPPF2(" ");
        item.setOPPF3(" ");
        item.setOPHZ(" ");
        item.setOPREVRPM(" ");
        item.setSSESobreCalentamientoPorFase(" ");
        item.setSSESobreCalentamientoPorFaseL1(" ");
        item.setSSEBarrasPuestaTierra(" ");
        item.setSSEBarrasPuestaTierraL2(" ");
        item.setSSETransformador(" ");
        item.setSSETransformadorNT(" ");
        item.setSSEFusibles(" ");
        item.setSSEFusiblesA1(" ");
        item.setSSETemperatura(" ");
        item.setSSETemperaturaA2(" ");
        item.setSSECuchillas(" ");
        item.setSSECuchillasA3(" ");
        item.setSSEInterruptores(" ");
        item.setSSEInterruptoresNT(" ");
        item.setTTLimpiezaGeneral(" ");
        item.setTTAnclasRetenidos(" ");
        item.setTTLucesObstruccion(" ");
        item.setTTTornilleriaHerraje(" ");
        item.setTTPuestaTierra(" ");
        item.setTTSistemaApartaRayos(" ");
        item.setHFugasGeneral(" ");
        item.setHHidroneumaticos(" ");
        item.setHBaños(" ");
        item.setHCisternasTanques(" ");
        item.setHBombas(" ");
        item.setHEmpaques(" ");
        item.setHAccesorios(" ");
        item.setOTROS(" ");
        item.setAccionesTomadas(" ");
        item.setMaterialesActividades(" ");
        item.setAntesFoto1(" ");
        item.setAntesFoto2(" ");
        item.setAntesFoto3(" ");
        item.setAntesFoto4(" ");
        item.setAntesFoto5(" ");
        item.setAntesFoto6(" ");
        item.setDespuesFoto1(" ");
        item.setDespuesFoto2(" ");
        item.setDespuesFoto3(" ");
        item.setDespuesFoto4(" ");
        item.setDespuesFoto5(" ");
        item.setDespuesFoto6(" ");
        item.setFirmaCliente(" ");
        item.setFirmaVertiv(" ");
        return item;
    }

    public GeneralServicios generarServiciosparaEnvioServer(Servicios GS){
        UsuarioD usu = obtenerUsuario();
        GeneralServicios item = new GeneralServicios();
        item.setIdUsuario(usu.getID_USER());
        //item.setIdFormatoGeneralServicios(GS.idfo());
        item.setFolioPreTrabajo(GS.getFolioPreTrabajo());
        item.setFechaInicio(GS.getFECHAINICIO());
        item.setFechaTermino(GS.getFECHAFIN());
        item.setIdCliente(GS.getCliente());
        item.setSRProyecto(GS.getSR());
        item.setTASK(GS.getTASK());
        item.setContactoCliente(GS.getContacto());
        item.setTipoServicio(GS.getTIPOSERVICIO());
        item.setTelefono(GS.getTelefono());
        item.setFreecuencia(GS.getFRECUENCIA());
        item.setEMail(GS.getMail());
        item.setNoTag(GS.getNTAG());
        item.setModeloEquipo(GS.getMODELO());
        item.setNoSerie(GS.getNSERIE());
        item.setDireccionSitio(GS.getDirección());
        item.setActividad(GS.getACTIVIDAD());
        item.setDiagnostico(GS.getDIAGNOSTICO());
        item.setAccionCorrectiva(GS.getACCIONCORRECTIVA());
        item.setComentariosRecomendaciones(GS.getCOMENTARIOS());
        item.setCantidad1(GS.getCANTIDAD1());
        item.setCantidad2(GS.getCANTIDAD2());
        item.setCantidad3(GS.getCANTIDAD3());
        item.setCantidad4(GS.getCANTIDAD4());
        item.setCantidad5(GS.getCANTIDAD5());
        item.setCantidad6(GS.getCANTIDAD6());
        item.setCantidad7(GS.getCANTIDAD7());
        item.setCantidad8(GS.getCANTIDAD8());
        item.setCantidad9(GS.getCANTIDAD9());
        item.setNoParte1(GS.getNPARTE1());
        item.setNoParte2(GS.getNPARTE2());
        item.setNoParte3(GS.getNPARTE3());
        item.setNoParte4(GS.getNPARTE4());
        item.setNoParte5(GS.getNPARTE5());
        item.setNoParte6(GS.getNPARTE6());
        item.setNoParte7(GS.getNPARTE7());
        item.setNoParte8(GS.getNPARTE8());
        item.setNoParte9(GS.getNPARTE9());
        item.setDescripcion1(GS.getESPECIFICACION1());
        item.setDescripcion2(GS.getESPECIFICACION2());
        item.setDescripcion3(GS.getESPECIFICACION3());
        item.setDescripcion4(GS.getESPECIFICACION4());
        item.setDescripcion5(GS.getESPECIFICACION5());
        item.setDescripcion6(GS.getESPECIFICACION6());
        item.setDescripcion7(GS.getESPECIFICACION7());
        item.setDescripcion8(GS.getESPECIFICACION8());
        item.setDescripcion9(GS.getESPECIFICACION9());
        item.setEquipo1(GS.getEQUIPO1());
        item.setEquipo2(GS.getEQUIPO2());
        item.setEquipo3(GS.getEQUIPO3());
        item.setEquipo4(GS.getEQUIPO4());
        item.setEquipo5(GS.getEQUIPO5());
        //item.setEquipo6(GS.get());
        //item.setEquipo7(GS.getEQUIPO7());
        //item.setEquipo8(GS.getEQUIPO8());
        //item.setEquipo9(GS.getEQUIPO9());
        item.setNoId1(GS.getNoID1());
        item.setNoId2(GS.getNoID2());
        item.setNoId3(GS.getNoID3());
        item.setNoId4(GS.getNoID4());
        item.setNoId5(GS.getNoID5());
        //item.setNoId6(GS.getACCIONCORRECTIVA());
        //item.setNoId7(GS.getACCIONCORRECTIVA());
        //item.setNoId8(GS.getACCIONCORRECTIVA());
        //item.setNoId9(GS.getACCIONCORRECTIVA());
        item.setFechaVencimiento1(GS.getFECHA1());
        item.setFechaVencimiento2(GS.getFECHA2());
        item.setFechaVencimiento3(GS.getFECHA3());
        item.setFechaVencimiento4(GS.getFECHA4());
        item.setFechaVencimiento5(GS.getFECHA5());
        //item.setFechaVencimiento6(GS.getACCIONCORRECTIVA());
        //item.setFechaVencimiento7(GS.getACCIONCORRECTIVA());
        //item.setFechaVencimiento8(GS.getACCIONCORRECTIVA());
        //item.setFechaVencimiento9(GS.getACCIONCORRECTIVA());
        item.setAntes1(GS.getFOTOANTES1());
        item.setAntes2(GS.getFOTOANTES2());
        item.setAntes3(GS.getFOTOANTES3());
        item.setAntes4(GS.getFOTOANTES4());
        item.setAntes5(GS.getFOTOANTES5());
        item.setAntes6(GS.getFOTOANTES6());
        item.setDespues1(GS.getFOTODESPUES1());
        item.setDespues2(GS.getFOTODESPUES2());
        item.setDespues3(GS.getFOTODESPUES3());
        item.setDespues4(GS.getFOTODESPUES4());
        item.setDespues5(GS.getFOTODESPUES5());
        item.setDespues6(GS.getFOTODESPUES6());
        item.setFirmaCliente(GS.getIMAGENFIRMA1());
        item.setFirmaVertiv(GS.getIMAGENFIRMA2());
        item.setFirmaClienteFinal(GS.getIMAGENFIRMA3());

        item.setNombreFirmaCliente(GS.getFIRMA1());
        item.setNombreFirmaVertiv(GS.getFIRMA2());
        item.setNomnbreFirmaClienteFinal(GS.getFIRMA13());
        item.setAD_NOMBRE1(GS.getAD_NOMBRE1());
        item.setAD_CORREO1(GS.getAD_CORREO1());
        item.setAD_NOMBRE2(GS.getAD_NOMBRE2());
        item.setAD_CORREO2(GS.getAD_CORREO2());
        item.setAD_NOMBRE3(GS.getAD_NOMBRE3());
        item.setAD_CORREO3(GS.getAD_CORREO3());
        item.setAD_NOMBRE4(GS.getAD_NOMBRE4());
        item.setAD_CORREO4(GS.getAD_CORREO4());
        item.setAD_NOMBRE5(GS.getAD_NOMBRE5());
        item.setAD_CORREO5(GS.getAD_CORREO5());
        item.setCLIENTEFINAL_EMPRESA(GS.getCLIENTEFINAL_EMPRESA());
        item.setCLIENTEFINAL_TELEFONO(GS.getcLIENTEFINAL_TELEFONO());
        item.setCLIENTEFINAL_CORREO(GS.getCLIENTEFINAL_CORREO());

        return item;
    }

    public static String generarUID(){
        return UUID.randomUUID().toString();
    }


}



//region

/*
    public boolean actualizarPzasMontoPedido(int id, String key,String pzas,String monto,String subtotal,String monto_iva, String ahorro_total){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.PIEZAS, pzas);
        valores.put(FilasBD.ColumnasPedido.MONTO, monto);
        valores.put(FilasBD.ColumnasPedido.SUBTOTAL, subtotal);
        valores.put(FilasBD.ColumnasPedido.MONTO_IVA, monto_iva);
        valores.put(FilasBD.ColumnasPedido.AHORRO_TOTAL, ahorro_total);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(id),key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarDetallePedido(Producto_Pedido productoPed){
        int id=productoPed.getId();

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasProductosPedido.CANTIDAD, productoPed.getCantidad());
        valores.put(FilasBD.ColumnasProductosPedido.TOTAL, productoPed.getTotal());
        valores.put(FilasBD.ColumnasProductosPedido.DESCUENTO, productoPed.getPorcDescuento());
        valores.put(FilasBD.ColumnasProductosPedido.IVA, productoPed.getIVA());
        valores.put(FilasBD.ColumnasProductosPedido.MONTO_SIN_IVA, productoPed.getMontoSinIva());
        valores.put(FilasBD.ColumnasProductosPedido.CAMPAÑA, productoPed.getCampaña());
        valores.put(FilasBD.ColumnasProductosPedido.PIEZAS_REGALO, productoPed.getPiezasRegalo());

        String whereClause = String.format("%s=? and %s=? and %s=?", FilasBD.ColumnasProductosPedido.ID_PEDIDO,FilasBD.ColumnasProductosPedido.ID_PRODUCTO,FilasBD.ColumnasProductosPedido.KEY_PEDIDO);
        String[] whereArgs = {productoPed.getPedidoID().toString(),productoPed.getProductoID(),productoPed.getKey_pedido()};

        int resultado = db.update(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarStatus(int id, String key,String status){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.STARUS, status);


        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(id),key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarContraseña(String contraseña){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasUsuario.CONTRASEÑA, contraseña);
        int resultado = db.update(BaseDatosTransfer.Tablas.USUARIO, valores, null, null);

        return resultado > 0;
    }

    public boolean guardarLocalizacionEnvio(int id, String key,LatLng coordenada){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.LATITUD, coordenada.latitude);
        valores.put(FilasBD.ColumnasPedido.LONGITUD, coordenada.longitude);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(id),key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarStatusPost(String idServer,String status){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.STARUS, status);

        String whereClause = String.format("%s=?",FilasBD.ColumnasPedido.ID_SERVIDOR);
        String[] whereArgs = {idServer};

        Log.d("Resultado","Datos:  idServidor=" + idServer+" Status="+status +"-- "+whereClause);

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
        //Key-398f74ce-6fa3-414a-8b1d-ec4362010c4e
    }

    public boolean actualizarIDserver(int id, String key,String idServer){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.ID_SERVIDOR, idServer);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(id),key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean cerrarSesion(String userID, String Status){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasSesion.SESION_ACTIVA, Status);

        String whereClause = String.format("%s=?", FilasBD.ColumnasSesion.ID_User);
        String[] whereArgs = {userID};

        if(Status.equals("Activo")) {
            //Actualizar fecha de inici de cesion.
            Date fecha = new Date();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

            ContentValues valores2 = new ContentValues();
            valores2.put(FilasBD.ColumnasUsuario.SINK, dateFormat.format(fecha).toString());
            db2.update(BaseDatosTransfer.Tablas.USUARIO, valores2, null, null);
        }

        int resultado = db.update(BaseDatosTransfer.Tablas.SESION, valores, null, null);
        return resultado > 0;
    }

    public boolean updateDivisiones(String userID, String Divisiones){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        SQLiteDatabase db2 = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.DatosUser.IDLABORATORIODIVISION, Divisiones);
        String whereClause = String.format("%s=?", FilasBD.DatosUser.ID_USER);
        String[] whereArgs = {userID};

        int resultado = db.update(BaseDatosTransfer.Tablas.USUARIO, valores, whereClause, whereArgs);
        return resultado > 0;
    }

    public boolean inicioSesionSinDatosM(String correo, String passw){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasSesion.SESION_ACTIVA, "Activo");

        String whereClause = String.format("%s=?", FilasBD.ColumnasSesion.ID_User);
        String[] whereArgs = {"correo"};

        int resultado = db.update(BaseDatosTransfer.Tablas.SESION, valores, null, null);

        return resultado > 0;
    }

    public boolean actualizarFechasSinc(String parametro){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date fhora= new Date();
        String v_hora= formato.format(fhora);

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("fecha",v_hora);
        //valores.put("ultima",v_hora);

        String whereClause = String.format("%s=? or %s=?", "descripcion", "descripcion");
        String[] whereArgs = {parametro, "ultima"};

        int resultado = db.update("actualizaciones", valores, whereClause, whereArgs);
        return resultado > 0;
    }

    public boolean ActualizaDatosPide(String id, String key,String Nombre, String Telefono, String Correo, String Firma){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.NOMBRE_AUTORIZO, Nombre);
        valores.put(FilasBD.ColumnasPedido.TELEFONO_AUTORIZO, Telefono);
        valores.put(FilasBD.ColumnasPedido.CORRREO_AUTORIZO, Correo);
        valores.put(FilasBD.ColumnasPedido.FIRMA_AUTORIZO, Firma);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {id,key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarPzasMontoPedidoRecalcu(int id, String key,String pzas,String monto,String subtotal,String monto_iva, String ahorro_total){
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.PIEZAS, pzas);
        valores.put(FilasBD.ColumnasPedido.MONTO, monto);
        valores.put(FilasBD.ColumnasPedido.SUBTOTAL, subtotal);
        valores.put(FilasBD.ColumnasPedido.MONTO_IVA, monto_iva);
        valores.put(FilasBD.ColumnasPedido.AHORRO_TOTAL, ahorro_total);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(id),key};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO+"_recalcular", valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean actualizarClientes(Cliente cliente) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();

        valores.put(FilasBD.ColumnasCLiente.NOMBREFARMACIA, cliente.getNonbre());
        valores.put(FilasBD.ColumnasCLiente.NUMEROCTA, cliente.getNumCuenta());
        valores.put(FilasBD.ColumnasCLiente.IDCADENA, cliente.getIDCADENA());
        valores.put(FilasBD.ColumnasCLiente.IDALMACEN, cliente.getIDALMACEN());
        valores.put(FilasBD.ColumnasCLiente.PSICOTROPICOS, cliente.getPSICOTROPICOS());
        valores.put(FilasBD.ColumnasCLiente.LIMITE_PSICOTROPICOS, cliente.getLIMITE_PSICOTROPICOS());
        valores.put(FilasBD.ColumnasCLiente.VENTAMAXDIA, cliente.getVENTAMAXDIA());
        valores.put(FilasBD.ColumnasCLiente.LIMITEDECREDITO, cliente.getLimiteCredito());
        valores.put(FilasBD.ColumnasCLiente.IDLISTAPRECIOS, cliente.getIDLISTAPRECIOS());
        valores.put(FilasBD.ColumnasCLiente.IDTERMINOPAGO, cliente.getIDTERMINOPAGO());
        valores.put(FilasBD.ColumnasCLiente.IDCONDICIONPAGO, cliente.getIDCONDICIONPAGO());
        valores.put(FilasBD.ColumnasCLiente.IDZONA, cliente.getID_Zona());
        valores.put(FilasBD.ColumnasCLiente.ESTATUS, cliente.getESTATUS());
        valores.put(FilasBD.ColumnasCLiente.RFC, cliente.getRFC());
        valores.put(FilasBD.ColumnasCLiente.IDTIPOREPARTO, cliente.getIDTIPOREPARTO());
        valores.put(FilasBD.ColumnasCLiente.BrickIMS, cliente.getBrikIMS());
        valores.put(FilasBD.ColumnasCLiente.BrickKnobloch, cliente.getBrickKnobloch());
        valores.put(FilasBD.ColumnasCLiente.DIRECCION, cliente.getDireccion());
        valores.put(FilasBD.ColumnasCLiente.CP, cliente.getCP());
        valores.put(FilasBD.ColumnasCLiente.LATITUD, cliente.getLatitud());
        valores.put(FilasBD.ColumnasCLiente.LONGITUD, cliente.getLongitud());
        valores.put(FilasBD.ColumnasCLiente.CATEGORIA, cliente.getCategoria());

        String whereClause = String.format("%s=?", FilasBD.ColumnasCLiente.IDFARMACIA);
        String[] whereArgs = {cliente.getIdFarmacia()};

        int resultado = db.update(BaseDatosTransfer.Tablas.FARMACIAS, valores, whereClause, whereArgs);

        return resultado > 0;
    }

    public boolean SustituirDatosPedido(int idPedido, String Key_pedido){
        boolean ok=false;
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        listadeProdctopsen_Pedido= obtenerProductosPedido_idRecalculado(idPedido,Key_pedido);

        DetallePedido CabeceroPedido=ontenerDetallePedidoRecalcu(idPedido);

        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.MONTO, CabeceroPedido.MONTO);
        valores.put(FilasBD.ColumnasPedido.SUBTOTAL, CabeceroPedido.SUBTOTALPEDIDO);
        valores.put(FilasBD.ColumnasPedido.MONTO_IVA, CabeceroPedido.IVAMONTO);
        valores.put(FilasBD.ColumnasPedido.AHORRO_TOTAL, CabeceroPedido.AHORRO);

        String whereClause = String.format("%s=? and %s=?", FilasBD.ColumnasPedido.ID_PEDIDO,FilasBD.ColumnasPedido.KEY_PEDIDO);
        String[] whereArgs = {Integer.toString(idPedido),Key_pedido};

        int resultado = db.update(BaseDatosTransfer.Tablas.PEDIDO, valores, whereClause, whereArgs);

        try {
            for (int x = 0; x < listadeProdctopsen_Pedido.size(); x++) {
                ContentValues valoresArticulos = new ContentValues();
                valoresArticulos.put(FilasBD.ColumnasProductosPedido.TOTAL, listadeProdctopsen_Pedido.get(x).getTotal());
                valoresArticulos.put(FilasBD.ColumnasProductosPedido.DESCUENTO, listadeProdctopsen_Pedido.get(x).getPorcDescuento());
                //valoresArticulos.put(FilasBD.ColumnasProductosPedido.AHORRO, listadeProdctopsen_Pedido.get(x).);
                valoresArticulos.put(FilasBD.ColumnasProductosPedido.IVA, listadeProdctopsen_Pedido.get(x).getIVA());

                String whereClause2 = String.format("%s=? and %s=?", FilasBD.ColumnasProductosPedido.ID_PEDIDO, FilasBD.ColumnasProductosPedido.KEY_PEDIDO);
                String[] whereArgs2 = {Integer.toString(idPedido), Key_pedido};

                db.update(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, valoresArticulos, whereClause2, whereArgs2);
            }
            ok=true;
        }catch (Exception e){

        }
        return ok;
    }

    public void ActualizarParametrosUsuario(String diviciones, String ambiente, String url) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasUsuario.IDLABORATORIODIVISION, diviciones);
        valores.put(FilasBD.ColumnasUsuario.AMBIENTE, ambiente);
        valores.put(FilasBD.ColumnasUsuario.URL,url);

        int resultado = db.update(BaseDatosTransfer.Tablas.USUARIO, valores, null, null);

    }

*/

     /*
    public void guardarIMEI(String IMEI){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.DatosTelefono.IMEI, IMEI);
        db.insertOrThrow(BaseDatosTransfer.Tablas.DATOS_TELEFONO, null, valores);
    }

    public String insertarUsuario(UsuarioSesion usuario) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        // Generar Pk
        String idUser = FilasBD.DatosUser.generarIDUsuario();

        ContentValues usuarioN = new ContentValues();
        usuarioN.put(FilasBD.DatosUser.ID, idUser);
        usuarioN.put(FilasBD.DatosUser.ID_USER, usuario.idUser);
        usuarioN.put(FilasBD.DatosUser.NOMBRE, usuario.nombre);
        usuarioN.put(FilasBD.DatosUser.PATERNO, usuario.paterno);
        usuarioN.put(FilasBD.DatosUser.MATERNO, usuario.materno);
        usuarioN.put(FilasBD.DatosUser.TELEFONO, usuario.telefono);
        usuarioN.put(FilasBD.DatosUser.LABRATORIO, usuario.laboratorio);
        usuarioN.put(FilasBD.DatosUser.IDLABORATORIODIVISION, usuario.id_laboratorio_division);
        usuarioN.put(FilasBD.DatosUser.CORREO, usuario.correo);
        usuarioN.put(FilasBD.DatosUser.CANIVALISMO, usuario.canibalismo);
        usuarioN.put(FilasBD.DatosUser.FARMACIAS_ASIGNADAS_BE, usuario.farmaciasAsignadasEnBackEnd);
        usuarioN.put(FilasBD.DatosUser.SINK, dateFormat.format(fecha));
        usuarioN.put(FilasBD.DatosUser.VALIDACIONES, usuario.validaciones);
        usuarioN.put(FilasBD.DatosUser.CONTRASEÑA, usuario.contrasena);
        usuarioN.put(FilasBD.DatosUser.AMBIENTE, usuario.ambiente);
        usuarioN.put(FilasBD.DatosUser.URL, usuario.url);

        db.insertOrThrow(BaseDatosTransfer.Tablas.USUARIO, null, usuarioN);
        return usuario.idUser;
    }

    public String RegistrarInicioSesion(Sesion sesion) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        // Generar Pk
        String idIniciodeSesion = FilasBD.SesionAct.generarIDSesion();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.SesionAct.ID, idIniciodeSesion);
        valores.put(FilasBD.SesionAct.ID_User, sesion.idUser);
        valores.put(FilasBD.SesionAct.SESION_ACTIVA, sesion.sesion);

        // Insertar cabecera
        db.insertOrThrow(BaseDatosTransfer.Tablas.SESION, null, valores);

        return idIniciodeSesion;
    }

    public void DatosPide_old(String idPedido, String KeyPedido) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("idPedido", idPedido);
        valores.put("keyPedido", KeyPedido);
        valores.put("nombre", "S/D");
        valores.put("telefono", "S/D");
        valores.put("correo", "S/D");
        valores.put("firma", "S/D");
        db.insertOrThrow("datosAutorizaPedido", null, valores);

        //  return idPedido;
    }

    public String NewPedido(Pedidos pedidoNuevo) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        // Generar Pk
        String KeyPedido = FilasBD.key_Pedido.generarKeyPedido();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasPedido.ID_USER, pedidoNuevo.getID_USER());
        valores.put(FilasBD.ColumnasPedido.FARMACIA, pedidoNuevo.getNombreCliente());
        valores.put(FilasBD.ColumnasPedido.NUMERO_CUENTA, pedidoNuevo.getCuenta());
        valores.put(FilasBD.ColumnasPedido.FECHA, pedidoNuevo.getFecha());
        valores.put(FilasBD.ColumnasPedido.PIEZAS,Integer.parseInt(pedidoNuevo.getPiezas()));
        valores.put(FilasBD.ColumnasPedido.MONTO, Double.parseDouble(pedidoNuevo.getMonto()));
        valores.put(FilasBD.ColumnasPedido.STARUS, pedidoNuevo.getStatus());
        valores.put(FilasBD.ColumnasPedido.KEY_PEDIDO, KeyPedido);
        valores.put(FilasBD.ColumnasPedido.ID_ALMACEN, pedidoNuevo.getIdalmacen());
        valores.put(FilasBD.ColumnasPedido.lISTA_PRECIOS, pedidoNuevo.getIdListaPrecios());
        valores.put(FilasBD.ColumnasPedido.SUBTOTAL, "0.0");
        valores.put(FilasBD.ColumnasPedido.MONTO_IVA, "0.0");
        valores.put(FilasBD.ColumnasPedido.AHORRO_TOTAL, "0.0");
        valores.put(FilasBD.ColumnasPedido.LATITUD, "0");
        valores.put(FilasBD.ColumnasPedido.LONGITUD, "0");
        valores.put(FilasBD.ColumnasPedido.FECHA_HORA_ENVIO, "");
        valores.put(FilasBD.ColumnasPedido.NOMBRE_AUTORIZO, "S/D");
        valores.put(FilasBD.ColumnasPedido.TELEFONO_AUTORIZO, "S/D");
        valores.put(FilasBD.ColumnasPedido.CORRREO_AUTORIZO, "S/D");
        valores.put(FilasBD.ColumnasPedido.FIRMA_AUTORIZO, "S/D");
        // Insertar cabecera
        db.insertOrThrow(BaseDatosTransfer.Tablas.PEDIDO, null, valores);

        return KeyPedido;
    }



    public void incertarProductos(Producto producto) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(FilasBD.CoumnasProductos.IDARTICULO, producto.getIDARTICULO());
        valores.put(FilasBD.CoumnasProductos.DESCRIPCION, producto.getDESCRIPCION());
        valores.put(FilasBD.CoumnasProductos.PRESENTACION, producto.getPRESENTACION());
        valores.put(FilasBD.CoumnasProductos.CANT_PRESENTACION, producto.getCANT_PRESENTACION());
        valores.put(FilasBD.CoumnasProductos.CODIGODEBARRAS, producto.getCODIGO_BARRAS());
        valores.put(FilasBD.CoumnasProductos.PRECIO_PUBLICO, producto.getPRECIO_PUBLICO());
        valores.put(FilasBD.CoumnasProductos.IVA, producto.getIVA());
        valores.put(FilasBD.CoumnasProductos.IDLABORATORIO, producto.getIDLABORATORIO());
        valores.put(FilasBD.CoumnasProductos.IDFDIVLAB, producto.getIDFDIVLAB());
        valores.put(FilasBD.CoumnasProductos.IDGRUPOESPECIAL, producto.getIDGRUPOESPECIAL());
        valores.put(FilasBD.CoumnasProductos.TIPODEDESCUENTO, producto.getTIPODEDESCUENTO());
        valores.put(FilasBD.CoumnasProductos.VENCIMIENTO, producto.getVENCIMIENTO());
        valores.put(FilasBD.CoumnasProductos.FAMILIA, producto.getFAMILIA());
        valores.put(FilasBD.CoumnasProductos.GPOTERAPEUTICO, producto.getGPOTERAPEUTICO());
        valores.put(FilasBD.CoumnasProductos.FEEFORSERVICE, producto.getFEEFORSERVICE());
        valores.put(FilasBD.CoumnasProductos.EXISTENCIA_POR_ALMACEN, producto.getEXISTENCIA_POR_ALMACEN());

        db.insertOrThrow(BaseDatosTransfer.Tablas.PRODUCTOS, null, valores);
        db.close();
    }

    public void incertarProductoEnPedido(Producto_Pedido producto) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasProductosPedido.ID_PEDIDO, producto.getPedidoID());
        valores.put(FilasBD.ColumnasProductosPedido.ID_PRODUCTO, producto.getProductoID());
        valores.put(FilasBD.ColumnasProductosPedido.NOMBRE, producto.getNombre());
        valores.put(FilasBD.ColumnasProductosPedido.CODIGO, producto.getCodigo());
        valores.put(FilasBD.ColumnasProductosPedido.CANTIDAD, producto.getCantidad());
        valores.put(FilasBD.ColumnasProductosPedido.DESCUENTO, producto.getPorcDescuento());
        valores.put(FilasBD.ColumnasProductosPedido.PRECIO_UNIDAD, producto.getPercioUnidad());
        valores.put(FilasBD.ColumnasProductosPedido.PIEZAS_REGALO, producto.getPiezasRegalo());
        valores.put(FilasBD.ColumnasProductosPedido.CAMPAÑA, producto.getCampaña());
        valores.put(FilasBD.ColumnasProductosPedido.TOTAL, producto.getTotal());
        valores.put(FilasBD.ColumnasProductosPedido.KEY_PEDIDO, producto.getKey_pedido());
        valores.put(FilasBD.ColumnasProductosPedido.IVA, producto.getIVA());
        valores.put(FilasBD.ColumnasProductosPedido.MONTO_SIN_IVA, producto.getMontoSinIva());
        valores.put(FilasBD.ColumnasProductosPedido.IVA_porcent, producto.getIVAporcent());

        db.insert(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, null, valores);
    }

    public void guardarDatosCliente(Cliente cliente){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s= %s", BaseDatosTransfer.Tablas.FARMACIAS,FilasBD.ColumnasCLiente.IDFARMACIA,cliente.getIdFarmacia());
        Cursor cl= db.rawQuery(sql, null);
        if(cl.moveToFirst()){
            actualizarClientes(cliente);
        }else{
            incertarClientes(cliente);
        }
    }

    public Cliente datosdeCliente(String idFarmacia){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        Cliente dats = null;
        String sql = String.format("SELECT * FROM %s WHERE %s= %s", BaseDatosTransfer.Tablas.FARMACIAS,FilasBD.ColumnasCLiente.IDFARMACIA,idFarmacia);
        Cursor cl= db.rawQuery(sql, null);
        if(cl.moveToFirst()) {
            dats = new Cliente(cl.getString(1), cl.getString(2), cl.getString(3), cl.getString(4),
                    cl.getString(5), cl.getString(6), cl.getString(7), cl.getString(8), cl.getString(9), cl.getString(10),
                    cl.getString(11), cl.getString(12), cl.getString(13), cl.getString(14), cl.getString(15), cl.getString(16),
                    cl.getString(17), cl.getString(18), cl.getString(19), cl.getString(20), cl.getString(21), cl.getString(22),cl.getString(23));
        }else{
            dats = new Cliente("S/D",
                    "",
                    "",
                    "",
                    "",
                    "0",
                    "0",
                    "10",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "",
                    "");
        }
        Log.d("Cliente","Cliente");
        DatabaseUtils.dumpCursor(cl);
        return dats;
    }

    public void incertarClientes(Cliente cliente) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasCLiente.IDFARMACIA, cliente.getIdFarmacia());
        valores.put(FilasBD.ColumnasCLiente.NOMBREFARMACIA, cliente.getNonbre());
        valores.put(FilasBD.ColumnasCLiente.NUMEROCTA, cliente.getNumCuenta());
        valores.put(FilasBD.ColumnasCLiente.IDCADENA, cliente.getIDCADENA());
        valores.put(FilasBD.ColumnasCLiente.IDALMACEN, cliente.getIDALMACEN());
        valores.put(FilasBD.ColumnasCLiente.PSICOTROPICOS, cliente.getPSICOTROPICOS());
        valores.put(FilasBD.ColumnasCLiente.LIMITE_PSICOTROPICOS, cliente.getLIMITE_PSICOTROPICOS());
        valores.put(FilasBD.ColumnasCLiente.VENTAMAXDIA, cliente.getVENTAMAXDIA());
        valores.put(FilasBD.ColumnasCLiente.LIMITEDECREDITO, cliente.getLimiteCredito());
        valores.put(FilasBD.ColumnasCLiente.IDLISTAPRECIOS, cliente.getIDLISTAPRECIOS());
        valores.put(FilasBD.ColumnasCLiente.IDTERMINOPAGO, cliente.getIDTERMINOPAGO());
        valores.put(FilasBD.ColumnasCLiente.IDCONDICIONPAGO, cliente.getIDCONDICIONPAGO());
        valores.put(FilasBD.ColumnasCLiente.IDZONA, cliente.getID_Zona());
        valores.put(FilasBD.ColumnasCLiente.ESTATUS, cliente.getESTATUS());
        valores.put(FilasBD.ColumnasCLiente.RFC, cliente.getRFC());
        valores.put(FilasBD.ColumnasCLiente.IDTIPOREPARTO, cliente.getIDTIPOREPARTO());
        valores.put(FilasBD.ColumnasCLiente.BrickIMS, cliente.getBrikIMS());
        valores.put(FilasBD.ColumnasCLiente.BrickKnobloch, cliente.getBrickKnobloch());
        valores.put(FilasBD.ColumnasCLiente.DIRECCION, cliente.getDireccion());
        valores.put(FilasBD.ColumnasCLiente.CP, cliente.getCP());
        valores.put(FilasBD.ColumnasCLiente.LATITUD, cliente.getLatitud());
        valores.put(FilasBD.ColumnasCLiente.LONGITUD, cliente.getLongitud());
        valores.put(FilasBD.ColumnasCLiente.CATEGORIA, cliente.getCategoria());

        db.insertOrThrow(BaseDatosTransfer.Tablas.FARMACIAS, null, valores);
    }

    public void crearParametrosUsuarios(ParametrosUsuario parametros) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasParametrosConfiguracion.ID_VALOR_SISTEMA, parametros.getID_VALOR_SISTEMA());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.DESCRIPCION, parametros.getDESCRIPCION());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.TIPO_DATO, parametros.getTIPO_DATO());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.VALOR, parametros.getVALOR());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.ACTIVO, parametros.getACTIVO());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.USUARIO_CREACION, parametros.getUSUARIO_CREACION());
        valores.put(FilasBD.ColumnasParametrosConfiguracion.FECHA_ULTIMA_ACTUALIZACION, parametros.getFECHA_ULTIMA_ACTUALIZACION());

        db.insertOrThrow(BaseDatosTransfer.Tablas.PARAMETROS_CONFIGURACION, null, valores);
    }

    public void NewCampañaCab(CampañaCab campañaCab) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasCampañas.IDCAMPAÑA, campañaCab.getIDCAMPAÑA());
        valores.put(FilasBD.ColumnasCampañas.IDLABORATORIO, campañaCab.getIDLABORATORIO());
        valores.put(FilasBD.ColumnasCampañas.NOMBRECAMPA, campañaCab.getNOMBRECAMPA());
        valores.put(FilasBD.ColumnasCampañas.TIPO, campañaCab.getTIPO());
        valores.put(FilasBD.ColumnasCampañas.STATUS, campañaCab.getSTATUS());
        valores.put(FilasBD.ColumnasCampañas.OFERTAPRECIOFINAL, campañaCab.getOFERTAPRECIOFINAL());
        valores.put(FilasBD.ColumnasCampañas.BASECALCULO, campañaCab.getBASECALCULO());
        valores.put(FilasBD.ColumnasCampañas.NIVELCALCULO, campañaCab.getNIVELCALCULO());
        valores.put(FilasBD.ColumnasCampañas.MENSAJE, campañaCab.getMENSAJE());
        valores.put(FilasBD.ColumnasCampañas.ABIERTA, campañaCab.getABIERTA());
        valores.put(FilasBD.ColumnasCampañas.FECHAINICIO, campañaCab.getFECHAINICIO());
        valores.put(FilasBD.ColumnasCampañas.FECHAFIN, campañaCab.getFECHAFIN());
        valores.put(FilasBD.ColumnasCampañas.Aprobacion, campañaCab.getAPROBACION());
        valores.put(FilasBD.ColumnasCampañas.ParametroAprob, campañaCab.getPARAMETROAPROBACION());
        valores.put(FilasBD.ColumnasCampañas.RangoAprob, campañaCab.getRANGOAPROBACION());

        db.insertOrThrow(BaseDatosTransfer.Tablas.CAMPAÑAS, null, valores);
    }

    public void NewCampañaDet(CampañaDet campañaDet) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasCampañaDetalle.IDCAMPAÑA, campañaDet.getIDCAMPAÑA());
        valores.put(FilasBD.ColumnasCampañaDetalle.IDARTICULO, campañaDet.getIDARTICULO());
        valores.put(FilasBD.ColumnasCampañaDetalle.MINIMO, campañaDet.getMINIMO());
        valores.put(FilasBD.ColumnasCampañaDetalle.MAXIMO, campañaDet.getMAXIMO());
        valores.put(FilasBD.ColumnasCampañaDetalle.LISTADEVALORES, campañaDet.getLISTADEVALORES());
        valores.put(FilasBD.ColumnasCampañaDetalle.LIMITEINFERIOR, campañaDet.getLIMITEINFERIOR());
        valores.put(FilasBD.ColumnasCampañaDetalle.LIMITESUPERIOR, campañaDet.getLIMITESUPERIOR());
        valores.put(FilasBD.ColumnasCampañaDetalle.VALOR, campañaDet.getVALOR());
        valores.put(FilasBD.ColumnasCampañaDetalle.CONCARGO, campañaDet.getCONCARGO());
        valores.put(FilasBD.ColumnasCampañaDetalle.SINCARGO, campañaDet.getSINCARGO());
        valores.put(FilasBD.ColumnasCampañaDetalle.NOMBREARTICULO, campañaDet.getNOMBREARTICULO());

        db.insertOrThrow(BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE, null, valores);
    }

    public void parametrosInternos(){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("idparametro", 1);
        valores.put("detalle", "Orden de lista de pedidos");
        valores.put("valor", "idPedido&&asc");
        db.insertOrThrow("parametrosInternos", null, valores);
    }

    public void ordenListaP(String valor){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("idparametro", 1);
        valores.put("detalle", "Orden de lista de pedidos");
        valores.put("valor", valor);
        db.insertOrThrow("parametrosInternos", null, valores);
    }

    public void copiarPedido(Integer idPedido){
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        Date fhora= new Date();
        String v_hora= formato.format(fhora);
        String key="";
        int idPedidoC=0;
        fila= ontenerPedido(idPedido);
        if(fila.moveToFirst()) {
            Pedidos copia = new Pedidos(0, "", fila.getString(3),
                    v_hora,
                    fila.getString(6),//piezas
                    fila.getString(7),//monto
                    "PENDIENTE",
                    fila.getString(2),
                    fila.getString(4),
                    fila.getString(10),
                    fila.getString(11));
            key = NewPedido(copia);

        }
        fila2=ontenerPedidoKey(key);

        if(fila2.moveToFirst()){
            idPedidoC= fila2.getInt(0);
            Cursor Productos = obtenerProductosPedido_id_cursor(idPedido,fila.getString(9));
            if (Productos.moveToFirst()) {
                do {
                    Producto_Pedido productoCopia =  new Producto_Pedido(
                            Productos.getInt(0), //id int
                            idPedidoC, //pedidoID int
                            Productos.getString(3), //productoID String
                            "", //userID String
                            Productos.getString(4), //nombre string
                            Productos.getString(5), //codigoB int
                            Productos.getInt(6), //cantidad int
                            Productos.getInt(7), //descuento int
                            Productos.getDouble(8), //precioUnidad double
                            Productos.getInt(9), //regalo int
                            Productos.getString(10), //Campaña
                            Productos.getDouble(11), //total dpouble
                            key,"",Productos.getString(13),
                            Productos.getDouble(14),
                            Productos.getString(15)); //keyPedido String

                    incertarProductoEnPedido(productoCopia);
                }
                while (Productos.moveToNext());
            }
        }
        //DatosPide(Integer.toString(idPedidoC), key);
    }

    public void fechasSinc(String desc,String Fecha){
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("descripcion", desc);
        valores.put("fecha", Fecha);
        db.insertOrThrow("actualizaciones", null, valores);
    }

    public void incertarProductoEnPedidoRecalcular(Producto_Pedido producto) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasProductosPedido.ID_PEDIDO, producto.getPedidoID());
        valores.put(FilasBD.ColumnasProductosPedido.ID_PRODUCTO, producto.getProductoID());
        valores.put(FilasBD.ColumnasProductosPedido.NOMBRE, producto.getNombre());
        valores.put(FilasBD.ColumnasProductosPedido.CODIGO, producto.getCodigo());
        valores.put(FilasBD.ColumnasProductosPedido.CANTIDAD, producto.getCantidad());
        valores.put(FilasBD.ColumnasProductosPedido.DESCUENTO, producto.getPorcDescuento());
        valores.put(FilasBD.ColumnasProductosPedido.PRECIO_UNIDAD, producto.getPercioUnidad());
        valores.put(FilasBD.ColumnasProductosPedido.PIEZAS_REGALO, producto.getPiezasRegalo());
        valores.put(FilasBD.ColumnasProductosPedido.CAMPAÑA, producto.getCampaña());
        valores.put(FilasBD.ColumnasProductosPedido.TOTAL, producto.getTotal());
        valores.put(FilasBD.ColumnasProductosPedido.KEY_PEDIDO, producto.getKey_pedido());
        valores.put(FilasBD.ColumnasProductosPedido.IVA, producto.getIVA());
        valores.put(FilasBD.ColumnasProductosPedido.MONTO_SIN_IVA, producto.getMontoSinIva());
        valores.put(FilasBD.ColumnasProductosPedido.IVA_porcent, producto.getIVAporcent());

        Double totalSinDesc=producto.getPercioUnidad()*producto.getCantidad();
        Double ahorro =0.0;
        try {
            ahorro =(totalSinDesc + Double.parseDouble(producto.getIVA())) - (producto.getTotal());
        }catch (Exception e){}
        valores.put(FilasBD.ColumnasProductosPedido.AHORRO, ahorro);

        db.insertOrThrow(BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular", null, valores);
    }

    public void incertarPreciosCLiente(ArrayList<PreciosCliente> listaPrecios) {
        borrarListaPrecios(listaPrecios.get(0).getIDFARMACIA());

        SQLiteDatabase db = baseDatos.getWritableDatabase();
        for(int c=0;c<listaPrecios.size();c++) {
            ContentValues valores = new ContentValues();
            valores.put(FilasBD.ClolumnasPreciosCliente.IDFARMACIA, listaPrecios.get(c).getIDFARMACIA());
            valores.put(FilasBD.ClolumnasPreciosCliente.IDARTICULO, listaPrecios.get(c).getIDARTICULO());
            valores.put(FilasBD.ClolumnasPreciosCliente.PRECIO, listaPrecios.get(c).getPRECIO());
            valores.put(FilasBD.ClolumnasPreciosCliente.IVA, listaPrecios.get(c).getIVA());
            valores.put(FilasBD.ClolumnasPreciosCliente.FECHASINC, listaPrecios.get(c).getFECHASINC());

            db.insertOrThrow(BaseDatosTransfer.Tablas.PRECIOS_CLIENTE, null, valores);
        }
    }

    public void actualizarExistencias(String [] DatosArticulo){
        //INCERTAR eXISTENCIAS
        Date fecha = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnasExistencias.IDALMACEN, DatosArticulo[0]);
        valores.put(FilasBD.ColumnasExistencias.IDARTICULO, DatosArticulo[1]);
        valores.put(FilasBD.ColumnasExistencias.EXISTENCIA, DatosArticulo[2]);
        valores.put(FilasBD.ColumnasExistencias.FECHA_SINCRONIZACION, dateFormat.format(fecha));

        //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());

        db.insertOrThrow(BaseDatosTransfer.Tablas.EXISTENCIAS, null, valores);
    }

    public void actualizarSeguimientoPedido(String datos, String idP, String KeyP){
        //INCERTAR eXISTENCIAS
        String [] Seguimiento= datos.split(",");
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.ID_PEDIDO_SERVER, Seguimiento[0]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.KEY_PEDIDO, KeyP);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.ID_PORTAL, Seguimiento[1]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.ID_RP, Seguimiento[2]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.FECHA_PORTAL, Seguimiento[3]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.FECHA_RP, Seguimiento[4]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.IVA, Seguimiento[5]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.SUBTOTAL_FACTURA, Seguimiento[6]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.TOTAL_FACTURA, Seguimiento[7]);
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.PIEZAS_SURTIDAS, "");
        valores.put(FilasBD.ColumnassSeguimientoEncabezado.NUM_FACTURA, "");

        db.insertOrThrow(BaseDatosTransfer.Tablas.SEGUIMIENTOENC, null, valores);
    }
*/
/*
    public Cursor easySelect(String BaseDatos){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s",BaseDatos);
        return db.rawQuery(sql, null);
    }
    public Cursor easySelectCustom(String qrySQL){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        //String sql = String.format("SELECT * FROM %s",BaseDatos);
        return db.rawQuery(qrySQL, null);
    }

    public Cursor obtenerInnicioSecion() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.SESION);
        return db.rawQuery(sql, null);
    }

    public Cursor SelectOrdenListaP() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM parametrosInternos");
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerDatosUsuario() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.USUARIO);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerPedidos() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PEDIDO);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerProductosPedido() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerProductosPedidoRecalcular() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular");
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerProductosPedidoRTecalculo() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular");
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerCampanasDet() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerCampanas() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.CAMPAÑAS);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerCampanaDatos(String IdCampaña) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s=%s", BaseDatosTransfer.Tablas.CAMPAÑAS, FilasBD.ColumnasCampañas.IDCAMPAÑA,IdCampaña);
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerClientes() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.FARMACIAS);
        return db.rawQuery(sql, null);
    }

    public ArrayList<Producto_Pedido> obtenerProductosPedido_id(int idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and key_pedido='%s' ", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                listadeProdctopsen_Pedido.add(new Producto_Pedido(
                        fila.getInt(0), //id int
                        fila.getInt(1), //pedidoID int
                        fila.getString(3), //productoID String
                        fila.getString(2), //userID String
                        fila.getString(4), //nombre string
                        fila.getString(5), //codigoB int
                        fila.getInt(6), //cantidad int
                        fila.getDouble(7), //descuento int
                        fila.getDouble(8), //precioUnidad double
                        fila.getInt(9), //regalo int
                        fila.getString(10), //Campaña string
                        fila.getDouble(11), //total dpouble
                        fila.getString(12), //keyPedido String
                        "",
                        fila.getString(13), // iva porcent
                        fila.getDouble(14), //monto sin iva
                        fila.getString(15))); // monto iva
            } while (fila.moveToNext());
        } else {
            //listadeProdctopsen_Pedido.add(new Producto_Pedido(1,1,"houyiu","","Sin productos",1,1,1,1.1,1,1.2,"kk"));
        }

        return listadeProdctopsen_Pedido;
    }

    public ArrayList<Producto_Pedido> obtenerProductosPedido_idRecalculado(int idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and key_pedido='%s' ", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular", idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                listadeProdctopsen_Pedido.add(new Producto_Pedido(
                        fila.getInt(0), //id int
                        fila.getInt(1), //pedidoID int
                        fila.getString(3), //productoID String
                        fila.getString(2), //userID String
                        fila.getString(4), //nombre string
                        fila.getString(5), //codigoB int
                        fila.getInt(6), //cantidad int
                        fila.getDouble(7), //descuento int
                        fila.getDouble(8), //precioUnidad double
                        fila.getInt(9), //regalo int
                        fila.getString(10), //Campaña string
                        fila.getDouble(11), //total dpouble
                        fila.getString(12),"",
                        fila.getString(13),
                        fila.getDouble(14),
                        fila.getString(15))); //keyPedido String
            } while (fila.moveToNext());
        } else {
            //listadeProdctopsen_Pedido.add(new Producto_Pedido(1,1,"houyiu","","Sin productos",1,1,1,1.1,1,1.2,"kk"));
        }

        return listadeProdctopsen_Pedido;
    }

    public Cursor obtenerPedidoCabecero_Recalculado(int idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and key_pedido='%s' ", BaseDatosTransfer.Tablas.PEDIDO+"_recalcular", idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);

        return fila;
    }

    public Cursor obtenerProductosPedido_id_cursor(int idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and key_pedido='%s' ", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            do {
                listadeProdctopsen_Pedido.add(new Producto_Pedido(
                        fila.getInt(0), //id int
                        fila.getInt(1), //pedidoID int
                        fila.getString(3), //productoID String
                        "", //userID String
                        fila.getString(4), //nombre string
                        fila.getString(5), //codigoB int
                        fila.getInt(6), //cantidad int
                        fila.getInt(7), //descuento int
                        fila.getDouble(8), //precioUnidad double
                        fila.getInt(9), //regalo int
                        fila.getString(10), //Campaña
                        fila.getDouble(11), //total dpouble
                        fila.getString(12),"",
                        fila.getString(13),
                        fila.getDouble(14),
                        fila.getString(15))); //keyPedido String
            } while (fila.moveToNext());
        } else {
            //listadeProdctopsen_Pedido.add(new Producto_Pedido(1,1,"houyiu","","Sin productos",1,1,1,1.1,1,1.2,"kk"));
        }

        return fila;
    }

    public Cursor obtenerCabeceraPedido_cursor(int idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and key_pedido='%s' ", BaseDatosTransfer.Tablas.PEDIDO, idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);
       return fila;
    }

    public Cursor obtenerProductosid(String id) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s= %s", BaseDatosTransfer.Tablas.PRODUCTOS,FilasBD.CoumnasProductos.IDARTICULO,Integer.parseInt(id));
        return db.rawQuery(sql, null);
    }

    public Cursor obtenerProductos() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PRODUCTOS);
        return db.rawQuery(sql, null);
    }

    //Eliminar, no es necesario
    public Cursor obtenerPedidosRecalcular() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PEDIDO+"_recalcular");
        return db.rawQuery(sql, null);
    }

    public ArrayList<Pedidos> obtenerListaPedidos() {
        Listapedidos = new ArrayList<Pedidos>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String [] ordenlista = new String[2];
        Cursor orden=parametrosOrden();
        if(orden.moveToFirst()){
            ordenlista=orden.getString(2).split("&&");
        }
        String sql = String.format("SELECT * FROM %s order by %s %s", BaseDatosTransfer.Tablas.PEDIDO,ordenlista[0],ordenlista[1]);
        //String sql = "SELECT * FROM pedidos ORDER BY date(fecha) DESC";
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            // Listapedidos.add(new Pedidos(1,"","","","","","","PENDIENTE",""));
            do {
                Listapedidos.add(new Pedidos(
                        fila.getInt(0), //id server
                        fila.getString(1), //numPedido
                        fila.getString(3), //NombreCliente
                        fila.getString(5), //Fecha
                        fila.getString(6), //piezas
                        fila.getString(7), //Monto
                        fila.getString(8), //Status
                        fila.getString(2),//idUsuar
                        fila.getString(4),//idAlmacen
                        fila.getString(10),
                        fila.getString(11)));
            } while (fila.moveToNext());
        }
        return Listapedidos;
    }

    private Cursor consultarOrden() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PRODUCTOS);
        return db.rawQuery(sql, null);
    }

    public DetallePedido ontenerDetallePedido(int id) {

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s", BaseDatosTransfer.Tablas.PEDIDO, id);
        String monto="0", pzas ="0",subtotal="0",ivamonto="0",ahorro="0";
        Double ahorr=0.0;
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            String sql2 = String.format("SELECT SUM(%s) as 'piezas', SUM(%s) as 'total' , SUM(%s) as 'subtot' , " +
                            "SUM(%s) as 'ivamont' FROM %s WHERE idPedido=%s and %s='%s'",
                    FilasBD.ColumnasProductosPedido.CANTIDAD, FilasBD.ColumnasProductosPedido.TOTAL, FilasBD.ColumnasProductosPedido.MONTO_SIN_IVA, FilasBD.ColumnasProductosPedido.IVA,
                    BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, id,
                    FilasBD.ColumnasProductosPedido.KEY_PEDIDO, fila.getString(9));
            fila2 = db.rawQuery(sql2, null);

            if (fila2.moveToFirst()&& fila2.getCount()>0) {
                if(fila2.getInt(0)>0){
                    pzas = fila2.getString(0);}
                if(fila2.getInt(1)>0){
                    monto = fila2.getString(1);}
                if(fila2.getInt(2)>0){
                    subtotal = fila2.getString(2);}
                if(fila2.getInt(3)>0){
                    ivamonto = fila2.getString(3);}
            } else {
                pzas = "0";
                monto = "0";
                subtotal = "0";
                ivamonto = "0";
            }

            String sql3 = String.format("SELECT %s,%s, %s FROM %s WHERE idPedido=%s and %s='%s'",
                    FilasBD.ColumnasProductosPedido.CANTIDAD, FilasBD.ColumnasProductosPedido.PRECIO_UNIDAD, FilasBD.ColumnasProductosPedido.IVA_porcent,
                    BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, id,
                    FilasBD.ColumnasProductosPedido.KEY_PEDIDO, fila.getString(9));
            Cursor fila3 = db.rawQuery(sql3, null);
            if(fila3.moveToFirst()){
                //0=cantidad, 1=precioUnid, 2=iva
                do {
                    Double montoSinDesc=fila3.getDouble(1)*fila3.getInt(0);//  listaObjetos.get(position).getPercioUnidad() * listaObjetos.get(position).getCantidad();
                    montoSinDesc=montoSinDesc+((montoSinDesc/100)*fila3.getDouble(2));

                    ahorr+=montoSinDesc;
                }while (fila3.moveToNext());

                ahorro=Double.toString (ahorr-Double.parseDouble(monto));
            }else{
                ahorro="0";
            }


            //if(pzas.equals("null")){pzas = "0";}
            //if(monto.isEmpty()){monto = "0";}
            detalle = new DetallePedido(Integer.toString(
                    fila.getInt(0)),
                    fila.getString(3),
                    fila.getString(4),
                    fila.getString(5),
                    "",
                    pzas,
                    monto,
                    fila.getString(8),
                    fila.getString(9),
                    fila.getString(1),
                    fila.getString(10),
                    fila.getString(11),
                    subtotal,
                    ivamonto,
                    ahorro
            );

        } else {
            //detalle=new DetallePedido(
            //  "","","","","","","","","");

        }
        return detalle;

    }

    public Cursor ontenerPedido(Integer id) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s", BaseDatosTransfer.Tablas.PEDIDO, id);
        return db.rawQuery(sql, null);
    }

    public Cursor ontenerPedidoKey(String key) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE key_pedido='%s'", BaseDatosTransfer.Tablas.PEDIDO, key);
        return db.rawQuery(sql, null);
    }

    public String ontenerIDnewPedido(String Key) {
        String ID = "";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE key_pedido='%s'", BaseDatosTransfer.Tablas.PEDIDO, Key);
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            ID = Integer.toString(fila.getInt(0));
        } else {
            ID = "Error";
        }
        return ID;
    }

    public ArrayList<Producto> obtenerListaProductos(String like,String campaña) {
        Listaproductos = new ArrayList<Producto>();

        try {
            SQLiteDatabase db = baseDatos.getReadableDatabase();
            Cursor articulos = obtenerCampanasDet();
            articulos.moveToFirst();
            String idArticulos = "";
            int contador = 0;
            String sql = "";
            if (campaña.equals("articulos_campaña")) {
                do {
                    if (contador == articulos.getCount() - 1) {
                        idArticulos = idArticulos + articulos.getString(1);
                    } else {
                        idArticulos = idArticulos + articulos.getString(1) + ",";
                    }
                    contador++;
                } while (articulos.moveToNext());

                sql = "SELECT * FROM " + BaseDatosTransfer.Tablas.PRODUCTOS + " where " +
                        FilasBD.CoumnasProductos.IDARTICULO + " in(" + idArticulos + ") order by " + FilasBD.CoumnasProductos.DESCRIPCION + " asc";
            } else {
                sql = "SELECT * FROM " + BaseDatosTransfer.Tablas.PRODUCTOS + " where " +
                        FilasBD.CoumnasProductos.DESCRIPCION + " like '%" + like + "%' or " + FilasBD.CoumnasProductos.CODIGODEBARRAS + " like '%" + like + "%' order by " + FilasBD.CoumnasProductos.DESCRIPCION + " asc";
            }


            fila = db.rawQuery(sql, null);
            if (fila.moveToFirst()) {
                //Listaproductos.add(new Producto(1,"","",
                //      "","","","","PENDIENTE","",
                //    "","","","","","",""));
                do {
                    Listaproductos.add(new Producto(
                            fila.getInt(1), //IDARTICULO
                            fila.getString(2), //DESCRIPCION
                            fila.getString(3), //PRESENTACION
                            fila.getString(4), //CANT_PRESENTACION
                            fila.getString(5), //CODIGO_BARRAS
                            fila.getString(6), //PRECIO_PUBLICO
                            fila.getString(7), //IVA
                            fila.getString(8), //DLABORATORIO
                            fila.getString(9), //IDFDIVLAB
                            fila.getString(10), //IDGRUPOESPECIAL
                            fila.getString(11), //TIPODESCUENTO
                            fila.getString(12), //VENCIMIENTO
                            fila.getString(13), //FAMILIA
                            fila.getString(14), //GPOTERAPEUTICO
                            fila.getString(15), //FEEFORSERVICE
                            fila.getString(16))); //EXISTENCIA_POR_ALMACEN
                } while (fila.moveToNext());
            }
        }catch (Exception e){

        }
        return Listaproductos;
    }

    public ArrayList<Cliente> obtenerListaClientes(String like, String valor) {
        listaClientes = new ArrayList<Cliente>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        //String sql = "SELECT * FROM "+BaseDatosTransfer.Tablas.FARMACIAS+" where " +
        //        FilasBD.CoumnasProductos.DESCRIPCION+ " like '%"+like+"%'";

        String sql = "SELECT * FROM " + BaseDatosTransfer.Tablas.FARMACIAS;
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            //Listaproductos.add(new Producto(1,"","",
            //      "","","","","PENDIENTE","",
            //    "","","","","","",""));
            do {
                listaClientes.add(new Cliente(
                        fila.getString(1), //IDARTICULO
                        fila.getString(2), //DESCRIPCION
                        fila.getString(3), //PRESENTACION
                        fila.getString(4), //CANT_PRESENTACION
                        fila.getString(5), //CODIGO_BARRAS
                        fila.getString(6), //PRECIO_PUBLICO
                        fila.getString(7), //IVA
                        fila.getString(8), //DLABORATORIO
                        fila.getString(9), //IDFDIVLAB
                        fila.getString(10), //IDGRUPOESPECIAL
                        fila.getString(11), //TIPODESCUENTO
                        fila.getString(12), //VENCIMIENTO
                        fila.getString(13), //FAMILIA
                        fila.getString(14), //GPOTERAPEUTICO
                        fila.getString(15), //FEEFORSERVICE
                        fila.getString(16),
                        fila.getString(17),
                        fila.getString(18),
                        fila.getString(19),
                        fila.getString(20),
                        fila.getString(21),
                        fila.getString(22),
                        fila.getString(23))); //EXISTENCIA_POR_ALMACEN
            } while (fila.moveToNext());
        }
        return listaClientes;
    }

    public Cursor parametros() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PARAMETROS_CONFIGURACION);
        return db.rawQuery(sql, null);
    }

    public ArrayList<String> consulta_indicadores(String f1, String f2) {
        //f1 = "2019/02/01 01:00:00";
        //f2 = "2019/02/28 00:00:00";
        //f1=f1+" 01:00:00";
        //f2=f2+" 00:00:00";
        //select count(NombreMedico)Numero,NombreMedico  from Medicos GROUP BY NombreMedico; convert(datetime, '23/10/2016', 103)
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql =("SELECT status, piezas, monto, substr(fecha, 1, 10) as 'fecha' FROM pedido");

        Cursor consulta1= db.rawQuery(sql, null);
        Log.d("Primer_consulta","Cantidades");
        ArrayList<String> listaBetween= new ArrayList<>();

        if(consulta1.moveToFirst()) {
            do{
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date fhora = null;
                Date fechaCampini = null;
                Date fechaCampfin = null;
                String fechaPedido=consulta1.getString(3);
                if(fechaPedido.contains("/")) {
                    String []sa= consulta1.getString(3).split("/");
                    fechaPedido=sa[2]+"-"+sa[1]+"-"+sa[0];
                }
                try {
                    fechaCampini = formato.parse(f1);
                    fechaCampfin = formato.parse(f2);
                    fhora= formato.parse(fechaPedido);
                } catch (ParseException e) {
                    e.printStackTrace();
                    try {
                        fechaCampini = formato.parse("2018-02-12");
                        fechaCampfin = formato.parse("2018-02-12");
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                    }
                }
                if ((fhora.after(fechaCampini) || fhora.equals(fechaCampini) )  && (fhora.before(fechaCampfin))|| fhora.equals(fechaCampfin)) {
                    JSONArray pedNew = new JSONArray();
                    String stauts, piezas,monto;
                    stauts=consulta1.getString(0);
                    piezas=consulta1.getString(1);
                    monto=consulta1.getString(2);
                    pedNew.put(stauts);
                    pedNew.put(piezas);
                    pedNew.put(monto);

                    listaBetween.add(pedNew.toString());
                }
            }while (consulta1.moveToNext());

        }

        DatabaseUtils.dumpCursor(consulta1);
        return listaBetween;
    }

    public Cursor consulta_indicadoresPrueba(String f1, String f2) {
        String filas="";
        f1 = f1.replace(" ", "");
        f2 = f2.replace(" ", "");
        f1=f1+" 01:00:00";
        f2=f2+" 00:00:00";
        //select count(NombreMedico)Numero,NombreMedico  from Medicos GROUP BY NombreMedico; convert(datetime, '23/10/2016', 103)
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        // String sql = String.format("SELECT * FROM %s WHERE %s BETWEEN '%s' AND '%s'; ", BaseDatosTransfer.Tablas.PEDIDO,
        //  String sql = String.format("SELECT * FROM %s WHERE %s BETWEEN '%s' AND '%s'", BaseDatosTransfer.Tablas.PEDIDO,FilasBD.ColumnasPedido.FECHA,f1,f2);
        String sql = String.format("SELECT %s,COUNT(%s) 'CANTIDAD', SUM(%s) 'PIEZAS', SUM(%s)'MONTO', fecha FROM %s where %s BETWEEN '%s' and '%s' GROUP BY %s",
                FilasBD.ColumnasPedido.STARUS, FilasBD.ColumnasPedido.STARUS, FilasBD.ColumnasPedido.PIEZAS, FilasBD.ColumnasPedido.MONTO, BaseDatosTransfer.Tablas.PEDIDO,
                FilasBD.ColumnasPedido.FECHA, f1, f2, FilasBD.ColumnasPedido.STARUS);

        Cursor dat=  db.rawQuery(sql, null);
        if(dat.moveToFirst()){
            filas= Integer.toString(dat.getCount());
        }else{filas="0";}

        Log.d("Segunda_consulta","Cantidades");
        DatabaseUtils.dumpCursor(dat);
        return dat;
    }

    public String obtenerLigaConexion(String id) {
        String liga;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        //String sql ="SELECT * FROM parametros_configuracion WHERE idvalorsistema='"+id+"';";
        String sql = String.format("SELECT * FROM %s WHERE %s='%s';",
                BaseDatosTransfer.Tablas.PARAMETROS_CONFIGURACION,
                FilasBD.ColumnasParametrosConfiguracion.ID_VALOR_SISTEMA, id);
        fila = db.rawQuery(sql, null);
        //if(fila.getCount()!=0){
        if (fila.moveToFirst()) {
            liga = fila.getString(4);
        } else {
            liga = " Error " + sql + ".- " + fila.getCount();
        }
        //db.close();
        return liga;
        //baseDatos.close();
    }

    public void comprobarPedido(int idPedido, String nombreProd) {
        //Producto_Pedido prod = new Producto_Pedido(0, 0, "", "", "", 0, 0, 0, 0, 0, 0, "");
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s' and %s=s% and %s=%s;",
                BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO,
                FilasBD.ColumnasProductosPedido.ID_PEDIDO, idPedido);
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {
            //liga=fila.getString(4);
        } else {
            //liga=" Error " + sql +".- " +fila.getCount();
        }
        //return prod;
    }

    public Cursor campañaProducto(String idProd) {
        //select count(NombreMedico)Numero,NombreMedico  from Medicos GROUP BY NombreMedico; convert(datetime, '23/10/2016', 103)
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE IDARTICULO=%s ", BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE, idProd);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor campañaCombo(int idCap, String idCombo) {
        //select count(NombreMedico)Numero,NombreMedico  from Medicos GROUP BY NombreMedico; convert(datetime, '23/10/2016', 103)
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE IDCAMPAÑA=%s and MINIMO='%s' ", BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE, idCap, idCombo);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public ArrayList<CampañaSelect> campaña(String idProd) {
        listaCampañas = new ArrayList<CampañaSelect>();
        String tipoAnt = "", DetalleAnt = "";
        int idCampAnt = 0;

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM  %s a inner join  %s b on(a.%s=b.%s) WHERE a.IDARTICULO=%s",
                BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE, BaseDatosTransfer.Tablas.CAMPAÑAS, FilasBD.ColumnasCampañaDetalle.IDCAMPAÑA, FilasBD.ColumnasCampañas.IDCAMPAÑA, idProd);
        CampañaDetetalles= db.rawQuery(sql, null);

        int index = 0;
        String IDCampañaAnt=" ", IDArticuloAnt=" ";
        int i=0;
        if (CampañaDetetalles.moveToFirst()) {
            String imgOferta = "http://likesreales.com.mx/wp-content/uploads/2014/08/OFERTA-ESPECIAL-12-300x270.png";
            String imgPorcentaje = "http://www.neuromanagementchile.cl/wp-content/uploads/2017/08/porcentaje.png";
            String imgRegalo = "https://www.akademus.es/frontend/assets/img/landing/regalo_abierto.png";
            String detalle_anterior="";
            String PiezasPaquete=" ";
            String PiezasRegalo=" *";
            String detalle_final="";
            String imagen="";

            //Llenar lista con campañas...
            if (CampañaDetetalles.getCount() > 1) {
                do{
                    //region MAS DE UNA CAMPAÑA
                    if(CampañaDetetalles.getString(14).equals("COMBO")){
                        //region COMBO
                        CampañaCombo=campañaCombo(CampañaDetetalles.getInt(0),CampañaDetetalles.getString(2));
                        if(CampañaCombo.moveToFirst()) {
                            if(CampañaCombo.getCount()==1){
                                detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " compra " + CampañaCombo.getString(8) + "pzs y te llevas "+CampañaCombo.getString(9) + " pzs de regalo.\n";
                            }
                            else {
                                do {
                                    if (CampañaCombo.getInt(8) > CampañaCombo.getInt(9)) {
                                        // detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " " + CampañaCombo.getString(8) + " pzs.\n";
                                        PiezasPaquete=PiezasPaquete+ CampañaCombo.getString(8) + " pzs de "+CampañaCombo.getString(10)+ ".\n" ;

                                    } else {
                                        if (CampañaCombo.getInt(8) < CampañaCombo.getInt(9)) {
                                            // detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " " + CampañaCombo.getString(9) + " pzs de regalo.\n";
                                            PiezasRegalo=PiezasRegalo+ CampañaCombo.getString(9) + " pzs de "+CampañaCombo.getString(10) + ". " ;
                                        }
                                    }

                                } while (CampañaCombo.moveToNext());
                            }

                            index++;
                        }
                        imagen=imgRegalo;
                        detalle_anterior=PiezasPaquete+PiezasRegalo;
                        //endregion
                    }
                    else {
                        if (CampañaDetetalles.getString(14).equals("RANGOS")) {
                            if(CampañaDetetalles.getString(17).equals("MONTO")) {
                                detalle_anterior = detalle_anterior + "*De $" + CampañaDetetalles.getString(5) + " a $" + CampañaDetetalles.getString(6) + ", Oferta de " + CampañaDetetalles.getString(7) + "%\n";
                            }
                            else{detalle_anterior = detalle_anterior + "*De " + CampañaDetetalles.getString(5) + "pzs a " + CampañaDetetalles.getString(6) + "pzs, Oferta de " + CampañaDetetalles.getString(7) + "%\n";}
                            index++;
                        }
                        if (CampañaDetetalles.getString(14).equals("LIBRE")) {
                            detalle_anterior=detalle_anterior+"Oferta libre \n";
                            index++;
                        }
                        if (CampañaDetetalles.getString(14).equals("ACOTADA")) {
                            detalle_anterior=detalle_anterior+"Entre "+CampañaDetetalles.getString(2) + " y " +CampañaDetetalles.getString(3) +" % de descuento.\n";
                            index++;
                        }
                        if (CampañaDetetalles.getString(14).equals("LISTA")) {
                            if(CampañaDetetalles.getString(16).equals("PRECIOFINAL")) {
                                detalle_anterior += "Precio final de $" + CampañaDetetalles.getString(4) + "\n";
                            }
                            else if(CampañaDetetalles.getString(16).equals("OFERTA")){
                                detalle_anterior += "Oferta de " + CampañaDetetalles.getString(4) + "%\n";
                            }

                            index++;

                        }
                        imagen=imgOferta;
                    }

                    if(CampañaDetetalles.getString(0).equals(IDCampañaAnt) && CampañaDetetalles.getString(1).equals(IDArticuloAnt)) {
                        listaCampañas.add(new CampañaSelect(
                                CampañaDetetalles.getString(0), CampañaDetetalles.getString(1),CampañaDetetalles.getString(2),
                                CampañaDetetalles.getString(3),CampañaDetetalles.getString(4),CampañaDetetalles.getString(5),
                                CampañaDetetalles.getString(6),CampañaDetetalles.getString(7),CampañaDetetalles.getString(8),
                                CampañaDetetalles.getString(9),CampañaDetetalles.getString(10),CampañaDetetalles.getString(12),
                                CampañaDetetalles.getString(13),CampañaDetetalles.getString(14),CampañaDetetalles.getString(15),
                                CampañaDetetalles.getString(16),CampañaDetetalles.getString(17),CampañaDetetalles.getString(18),
                                CampañaDetetalles.getString(19),CampañaDetetalles.getString(20),CampañaDetetalles.getString(21),
                                CampañaDetetalles.getString(22),detalle_anterior, imagen));

                        IDCampañaAnt=CampañaDetetalles.getString(0);
                        IDArticuloAnt=CampañaDetetalles.getString(1);
                        listaCampañas.remove(0);
                        if(index==CampañaDetetalles.getCount()){
                            detalle_anterior="Oferta de " + CampañaDetetalles.getString(4)+"%\n";
                        }
                        // detalle_anterior="";

                    }
                    else{
                        listaCampañas.add(new CampañaSelect(
                                CampañaDetetalles.getString(0), CampañaDetetalles.getString(1),CampañaDetetalles.getString(2),
                                CampañaDetetalles.getString(3),CampañaDetetalles.getString(4),CampañaDetetalles.getString(5),
                                CampañaDetetalles.getString(6),CampañaDetetalles.getString(7),CampañaDetetalles.getString(8),
                                CampañaDetetalles.getString(9),CampañaDetetalles.getString(10),CampañaDetetalles.getString(12),
                                CampañaDetetalles.getString(13),CampañaDetetalles.getString(14),CampañaDetetalles.getString(15),
                                CampañaDetetalles.getString(16),CampañaDetetalles.getString(17),CampañaDetetalles.getString(18),
                                CampañaDetetalles.getString(19),CampañaDetetalles.getString(20),CampañaDetetalles.getString(21),
                                CampañaDetetalles.getString(22),detalle_anterior, imagen));
                        IDCampañaAnt=CampañaDetetalles.getString(0);
                        IDArticuloAnt=CampañaDetetalles.getString(1);
                        if(index==CampañaDetetalles.getCount()){
                            detalle_anterior="";
                        }
                        PiezasPaquete=" *";
                        PiezasRegalo=" *";
                    }
                    i++;
                    //endregion
                }while (CampañaDetetalles.moveToNext());
            }
            else{
                //region UNA SOLA CAMPAÑA EN EL PRODUCTO
                if(CampañaDetetalles.getString(14).equals("COMBO")){
                    //region COMBO
                    CampañaCombo=campañaCombo(CampañaDetetalles.getInt(0),CampañaDetetalles.getString(2));
                    if(CampañaCombo.moveToFirst()) {
                        if(CampañaCombo.getCount()==1){
                            // detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " compra " + CampañaCombo.getString(8) + "pzs y te llevas "+CampañaCombo.getString(9) + " pzs de regalo.\n";


                            PiezasPaquete=PiezasPaquete+"En la compra de " +CampañaCombo.getString(8)+" pzs de "+
                                    CampañaCombo.getString(10)+", se agregaran "+ CampañaCombo.getString(9) + " pzs de regalo.";

                            PiezasRegalo=" ";
                        }
                        else {
                            do {
                                if (CampañaCombo.getInt(8) > CampañaCombo.getInt(9)) {
                                    // detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " " + CampañaCombo.getString(8) + " pzs.\n";
                                    PiezasPaquete=PiezasPaquete+CampañaCombo.getString(10)+ " " + CampañaCombo.getString(8) + " pzs.\n";

                                } else {
                                    if (CampañaCombo.getInt(8) < CampañaCombo.getInt(9)) {
                                        //   detalle_anterior = detalle_anterior + " *" + CampañaCombo.getString(10) + " " + CampañaCombo.getString(9) + " pzs de regalo.\n";
                                        PiezasRegalo=PiezasRegalo+CampañaCombo.getString(10) + " " + CampañaCombo.getString(9) + " pzs de regalo.\n";

                                    }
                                }

                            } while (CampañaCombo.moveToNext());
                        }
                    }
                    imagen=imgRegalo;
                    detalle_anterior=PiezasPaquete+PiezasRegalo;
                    //endregion
                }

                if (CampañaDetetalles.getString(14).equals("LIBRE")) {
                    detalle_anterior=detalle_anterior+"Oferta libre";
                }

                if (CampañaDetetalles.getString(14).equals("RANGOS")) {
                    if(CampañaDetetalles.getString(17).equals("MONTO")) {
                        if(CampañaDetetalles.getString(16).equals("PRECIOFINAL")){
                            detalle_anterior = detalle_anterior + "*De $" + CampañaDetetalles.getString(5) + " a $" +
                                    CampañaDetetalles.getString(6) + ", Precio final de " + CampañaDetetalles.getString(7) + "\n";
                        }else{
                            detalle_anterior = detalle_anterior + "*De $" + CampañaDetetalles.getString(5) + " a $" +
                                    CampañaDetetalles.getString(6) + ", Oferta de " + CampañaDetetalles.getString(7) + "%\n";
                        }
                    }
                    else {
                        if (CampañaDetetalles.getString(16).equals("PRECIOFINAL")) {
                            detalle_anterior = detalle_anterior + "*De " + CampañaDetetalles.getString(5) + "pzs a " +
                                    CampañaDetetalles.getString(6) + "pzs, Precio final de " + CampañaDetetalles.getString(7) + "\n";
                        } else {
                            detalle_anterior = detalle_anterior + "*De " + CampañaDetetalles.getString(5) + "pzs a " +
                                    CampañaDetetalles.getString(6) + "pzs, Oferta de " + CampañaDetetalles.getString(7) + "%\n";
                        }
                    }
                }

                if (CampañaDetetalles.getString(14).equals("LISTA")) {
                    if(CampañaDetetalles.getString(16).equals("PRECIOFINAL")){
                        detalle_anterior=detalle_anterior+"Precio final de $" + CampañaDetetalles.getString(4)+"\n";
                    }else if(CampañaDetetalles.getString(16).equals("OFERTA")){
                        detalle_anterior=detalle_anterior+"Oferta de " + CampañaDetetalles.getString(4)+"%\n";
                    }
                }

                if (CampañaDetetalles.getString(14).equals("ACOTADA")) {
                    if(CampañaDetetalles.getString(16).equals("PRECIOFINAL")){
                        detalle_anterior=detalle_anterior+"Entre $"+CampañaDetetalles.getString(2) + " y $" +
                                CampañaDetetalles.getString(3) +" como precio final.\n";
                    }else if(CampañaDetetalles.getString(16).equals("OFERTA")){
                        detalle_anterior=detalle_anterior+"Entre "+CampañaDetetalles.getString(2) + " y " +
                                CampañaDetetalles.getString(3) +" % de descuento. Pzas minimas "+CampañaDetetalles.getString(5)+"\n ";
                    }
                }

                listaCampañas.add(new CampañaSelect(
                        CampañaDetetalles.getString(0), CampañaDetetalles.getString(1),CampañaDetetalles.getString(2),
                        CampañaDetetalles.getString(3),CampañaDetetalles.getString(4),CampañaDetetalles.getString(5),
                        CampañaDetetalles.getString(6),CampañaDetetalles.getString(7),CampañaDetetalles.getString(8),
                        CampañaDetetalles.getString(9),CampañaDetetalles.getString(10),CampañaDetetalles.getString(12),
                        CampañaDetetalles.getString(13),CampañaDetetalles.getString(14),CampañaDetetalles.getString(15),
                        CampañaDetetalles.getString(16),CampañaDetetalles.getString(17),CampañaDetetalles.getString(18),
                        CampañaDetetalles.getString(19),CampañaDetetalles.getString(20),CampañaDetetalles.getString(21),
                        CampañaDetetalles.getString(22),detalle_anterior, imgOferta));

                detalle_anterior="";
                //endregion
            }
        }
        return listaCampañas;
    }

    public Cursor campañaIndiPrueb(String idProd) {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM  %s a inner join  %s b on(a.%s=b.%s) WHERE a.IDARTICULO=%s",
                BaseDatosTransfer.Tablas.CAMPAÑAS_DETALLE, BaseDatosTransfer.Tablas.CAMPAÑAS, FilasBD.ColumnasCampañaDetalle.IDCAMPAÑA, FilasBD.ColumnasCampañas.IDCAMPAÑA, idProd);
        return db.rawQuery(sql, null);
    }

    public Cursor pedicoCombo(int idPedido, String idProducto) {
        //select count(NombreMedico)Numero,NombreMedico  from Medicos GROUP BY NombreMedico; convert(datetime, '23/10/2016', 103)
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s and idProducto='%s' ", BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO, idPedido, idProducto);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor parametrosOrden() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM parametrosInternos");
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor fechasDeSincronizacion(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM actualizaciones");
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor fechasDeSincronizzacionAlmacenes(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT DISTINCT %s, %s FROM %s",FilasBD.ColumnasExistencias.IDALMACEN,FilasBD.ColumnasExistencias.FECHA_SINCRONIZACION,BaseDatosTransfer.Tablas.EXISTENCIAS);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor misAlmacenes(){
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT DISTINCT %s, %s FROM %s",FilasBD.ColumnasExistencias.IDALMACEN,FilasBD.ColumnasExistencias.FECHA_SINCRONIZACION,BaseDatosTransfer.Tablas.EXISTENCIAS);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor obtenerDatosPide(String idPedido, String Key_pedido) {
        listadeProdctopsen_Pedido = new ArrayList<Producto_Pedido>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido='%s' and keyPedido='%s' ", "datosAutorizaPedido", idPedido, Key_pedido);
        fila = db.rawQuery(sql, null);
        return fila;
    }

    public Cursor obtenerPedidosRecalculo() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.PEDIDO+"_recalcular");
        return db.rawQuery(sql, null);
    }

    public DetallePedido ontenerDetallePedidoRecalcu(int id) {

        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE idPedido=%s", BaseDatosTransfer.Tablas.PEDIDO+"_recalcular", id);
        String monto="0", pzas ="0",subtotal="0",ivamonto="0",ahorro="0";
        fila = db.rawQuery(sql, null);
        if (fila.moveToFirst()) {

            String sql2 = String.format("SELECT SUM(%s) as 'piezas', SUM(%s) as 'total' , SUM(%s) as 'subtot' , SUM(%s) as 'ivamont', SUM(%s) as 'ahorrotot' FROM %s WHERE idPedido=%s and %s='%s'",
                    FilasBD.ColumnasProductosPedido.CANTIDAD,
                    FilasBD.ColumnasProductosPedido.TOTAL,
                    FilasBD.ColumnasProductosPedido.MONTO_SIN_IVA,
                    FilasBD.ColumnasProductosPedido.IVA,
                    FilasBD.ColumnasProductosPedido.AHORRO,
                    BaseDatosTransfer.Tablas.PRODUCTOS_DE_PEDIDO+"_recalcular", id,
                    FilasBD.ColumnasProductosPedido.KEY_PEDIDO, fila.getString(9));
            fila2 = db.rawQuery(sql2, null);

            if (fila2.moveToFirst()&& fila2.getCount()>0) {
                if(fila2.getInt(0)>0){
                    pzas = fila2.getString(0);}
                if(fila2.getInt(1)>0){
                    monto = fila2.getString(1);}
                if(fila2.getInt(2)>0){
                    subtotal = fila2.getString(2);}
                if(fila2.getInt(3)>0){
                    ivamonto = fila2.getString(3);}
                if(fila2.getInt(4)>0){
                    ahorro = fila2.getString(4);}
            } else {
                pzas = "0";
                monto = "0";
                subtotal = "0";
                ivamonto = "0";
                ahorro="0";
            }
            //if(pzas.equals("null")){pzas = "0";}
            //if(monto.isEmpty()){monto = "0";}
            detalle = new DetallePedido(Integer.toString(
                    fila.getInt(0)),
                    fila.getString(3),
                    fila.getString(4),
                    fila.getString(5),
                    "",
                    pzas,
                    monto,
                    fila.getString(8),
                    fila.getString(9),
                    fila.getString(1),
                    fila.getString(10),
                    fila.getString(11),
                    subtotal,
                    ivamonto,
                    ahorro
            );

        } else {
            //detalle=new DetallePedido(
            //  "","","","","","","","","");

        }
        return detalle;

    }

    public ArrayList<String> PreciosCliente(String idCliente){
        ArrayList<String> preciosCliente = new ArrayList<String>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s WHERE %s='%s' ", BaseDatosTransfer.Tablas.PRECIOS_CLIENTE , FilasBD.ClolumnasPreciosCliente.IDFARMACIA, idCliente);
        fila = db.rawQuery(sql, null);
        if(fila.moveToFirst()){
            do{
                preciosCliente.add(fila.getString(0)+","+fila.getString(1)+","+fila.getString(2)+","+fila.getString(3)+","+fila.getString(4)+",");
            }while (fila.moveToNext());
        }
        return preciosCliente;
    }

    public Cursor obtenerExistencias() {
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s", BaseDatosTransfer.Tablas.EXISTENCIAS);
        return db.rawQuery(sql, null);
    }

    public String obtenerExistenciaProducto(String idArticulo, String IdALmacen) {
        String Exist="0";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s' and %s= '%s' ",
                BaseDatosTransfer.Tablas.EXISTENCIAS,FilasBD.ColumnasExistencias.IDARTICULO,idArticulo,FilasBD.ColumnasExistencias.IDALMACEN,IdALmacen);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            Exist=d.getString(2);
        }else{
            Exist="Sin dato";
        }
        return Exist;
    }

    public String obtenerExistenciasAlmacen(String idAlmacen) {
        String Exist="0";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s' ", BaseDatosTransfer.Tablas.EXISTENCIAS,FilasBD.ColumnasExistencias.IDALMACEN,idAlmacen);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            Exist=d.getString(3);
        }else{
            Exist="Sin dato";
        }
        return Exist;
    }

    public String obtenerImei(){
        String IMEI="0";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ", BaseDatosTransfer.Tablas.DATOS_TELEFONO);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            IMEI=d.getString(0);
        }else{
            IMEI="Sin dato";
        }
        return IMEI;
    }

    public Cursor obtenerImeiCursor(){
        String IMEI="0";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s ", BaseDatosTransfer.Tablas.DATOS_TELEFONO);
        Cursor d= db.rawQuery(sql, null);
        return d;
    }

    public SeguimientoEncabezado seguimientoPedido(String idpedido, String KeyPedido){
        boolean ok=false;
        SeguimientoEncabezado EncSeg= null;
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s' and %s='%s' ", BaseDatosTransfer.Tablas.SEGUIMIENTOENC,FilasBD.ColumnassSeguimientoEncabezado.ID_PEDIDO_SERVER,idpedido,
                FilasBD.ColumnassSeguimientoEncabezado.KEY_PEDIDO, KeyPedido);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            ok=true;
            EncSeg= new SeguimientoEncabezado(
                    d.getString(0), //id pedido servidor
                    d.getString(1),//key pedido
                    d.getString(2),//id portal
                    d.getString(3),//id rp
                    d.getString(4),// fecha portal
                    d.getString(5),// fecha rp
                    d.getString(6),// iva
                    d.getString(7),// subtotal factura
                    d.getString(8),// total factura
                    d.getString(9),// piezas surtidas
                    d.getString(10));// nimero de factura
        }else{
            EncSeg= new SeguimientoEncabezado(
                    "S/D", //id pedido servidor
                    "",//key pedido
                    "",//id portal
                    "",//id rp
                    "",// fecha portal
                    "",// fecha rp
                    "",// iva
                    "",// subtotal factura
                    "",// total factura
                    "",// piezas surtidas
                    "");// nimero de factura
        }
        return EncSeg;
    }

    public ArrayList<SeguimientoDetalle> seguimientoPedidoDetalle(String idpedido, String KeyPedido){
        ArrayList<SeguimientoDetalle> Seguimiento=new ArrayList<SeguimientoDetalle>();
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='%s' and %s='%s' ", BaseDatosTransfer.Tablas.SEGUIMIENTODET,FilasBD.ColumnassSeguimientoEncabezado.ID_PEDIDO_SERVER,idpedido,
                FilasBD.ColumnassSeguimientoEncabezado.KEY_PEDIDO, KeyPedido);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            do{
                SeguimientoDetalle segD= new SeguimientoDetalle(
                        d.getString(0),  //idPedido
                        d.getString(1),//key pedido
                        d.getString(2), //id articulo
                        d.getString(3) //numero de factura
                        ,d.getString(4) //total de piezas pedidas
                        ,d.getString(5) //Estatus
                        ,d.getString(6) //Total de articulos Facturados
                        ,d.getString(7)//oferta
                        ,d.getString(8) //iva
                        ,d.getString(9) //ieps
                        ,d.getString(10) //piezas que se surtieron
                        ,d.getString(11) //subtotal de articulo
                        ,d.getString(12)); //total factirado
                Seguimiento.add(segD);
            }while (d.moveToNext());
        }
        return Seguimiento;
    }

    public String numeroDePendientes(){
        String pendientes="0";
        SQLiteDatabase db = baseDatos.getReadableDatabase();
        String sql = String.format("SELECT * FROM %s where %s='PENDIENTE'", BaseDatosTransfer.Tablas.PEDIDO, FilasBD.ColumnasPedido.STARUS);
        Cursor d= db.rawQuery(sql, null);
        if(d.moveToFirst()){
            pendientes=Integer.toString(d.getCount());
        }else{
            pendientes="0";
        }
        return pendientes;
    }

*/
// endregion
