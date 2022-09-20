package com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones;

public class CatBestel {
    String Exitoso;
    String Error;
    String IdBestel;
    String IdRegion;
    String IdEstado;
    String Clasificacion;
    String NombreSitio;
    String Direccion;
    String Cliente;

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String cliente) {
        Cliente = cliente;
    }

    public String getIdBestel() {
        return IdBestel;
    }

    public void setIdBestel(String idBestel) {
        IdBestel = idBestel;
    }

    public String getIdRegion() {
        return IdRegion;
    }

    public void setIdRegion(String idRegion) {
        IdRegion = idRegion;
    }

    public String getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(String idEstado) {
        IdEstado = idEstado;
    }

    public String getClasificacion() {
        return Clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        Clasificacion = clasificacion;
    }

    public String getNombreSitio() {
        return NombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        NombreSitio = nombreSitio;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }
}
