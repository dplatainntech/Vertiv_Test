package com.maju.desarrollo.testfcs.Class;

public class Orden {
    String IDORDEN;
    String CLIENTE;
    String DIRECCION;
    String FECHA;
    String HORA;
    String ESTATUS;

    public Orden(String IDORDEN, String CLIENTE, String DIRECCION, String FECHA, String HORA, String ESTATUS) {
        this.IDORDEN = IDORDEN;
        this.CLIENTE = CLIENTE;
        this.DIRECCION = DIRECCION;
        this.FECHA = FECHA;
        this.HORA = HORA;
        this.ESTATUS = ESTATUS;
    }

    public String getIDORDEN() {
        return IDORDEN;
    }

    public void setIDORDEN(String IDORDEN) {
        this.IDORDEN = IDORDEN;
    }

    public String getCLIENTE() {
        return CLIENTE;
    }

    public void setCLIENTE(String CLIENTE) {
        this.CLIENTE = CLIENTE;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    public String getHORA() {
        return HORA;
    }

    public void setHORA(String HORA) {
        this.HORA = HORA;
    }

    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }
}
