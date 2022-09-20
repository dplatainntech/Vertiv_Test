package com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones;

public class CatEstado {
    String Exitoso;
    String Error;
    String IdEstado;
    String IdRegion;
    String Nombre;
    String Pais;


    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getExitoso() {
        return Exitoso;
    }

    public void setExitoso(String exitoso) {
        Exitoso = exitoso;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getIdEstado() {
        return IdEstado;
    }

    public void setIdEstado(String idEstado) {
        IdEstado = idEstado;
    }

    public String getIdRegion() {
        return IdRegion;
    }

    public void setIdRegion(String idRegion) {
        IdRegion = idRegion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }
}
