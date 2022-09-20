package com.maju.desarrollo.testfcs.ServiceClass.Sincronizaciones;

public class CatCliente {
    String 	Exitoso;
    String 	Error;
    String 	IdClientes;
    String 	NombreCliente;
    String 	NombreSitio;
    String 	Direccion;
    String Pais;


    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getIdClientes() {
        return IdClientes;
    }

    public void setIdClientes(String idClientes) {
        IdClientes = idClientes;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
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
