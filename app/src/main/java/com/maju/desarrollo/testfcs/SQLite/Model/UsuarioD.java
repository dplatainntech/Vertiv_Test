package com.maju.desarrollo.testfcs.SQLite.Model;

public class UsuarioD {
    String ID_USER;
    String NOMBRE;
    String PATERNO;
    String MATERNO;
    String TELEFONO;
    String CORREO;
    String CONTRASEÑA;
    String PAIS;
    String PaisDescripcion;


    public UsuarioD(String ID_USER, String NOMBRE, String PATERNO, String MATERNO, String TELEFONO, String CORREO, String CONTRASEÑA, String PAIS,String PaisDescripcion) {
        this.ID_USER = ID_USER;
        this.NOMBRE = NOMBRE;
        this.PATERNO = PATERNO;
        this.MATERNO = MATERNO;
        this.TELEFONO = TELEFONO;
        this.CORREO = CORREO;
        this.CONTRASEÑA = CONTRASEÑA;
        this.PAIS = PAIS;
        this.PaisDescripcion = PaisDescripcion;
    }

    public String getID_USER() {
        return ID_USER;
    }

    public void setID_USER(String ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public String getPATERNO() {
        return PATERNO;
    }

    public void setPATERNO(String PATERNO) {
        this.PATERNO = PATERNO;
    }

    public String getMATERNO() {
        return MATERNO;
    }

    public void setMATERNO(String MATERNO) {
        this.MATERNO = MATERNO;
    }

    public String getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(String TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public String getCORREO() {
        return CORREO;
    }

    public void setCORREO(String CORREO) {
        this.CORREO = CORREO;
    }

    public String getCONTRASEÑA() {
        return CONTRASEÑA;
    }

    public void setCONTRASEÑA(String CONTRASEÑA) {
        this.CONTRASEÑA = CONTRASEÑA;
    }

    public String getPAIS() {
        return PAIS;
    }

    public void setPAIS(String PAIS) {
        this.PAIS = PAIS;
    }

    public String getPaisDescripcion() {
        return PaisDescripcion;
    }

    public void setPaisDescripcion(String paisDescripcion) {
        PaisDescripcion = paisDescripcion;
    }
}
