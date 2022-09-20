package com.maju.desarrollo.testfcs.ServiceClass;

public class Usuario {

    String usuario ;
    String mail ;
    String contra ;
    String nombre ;
    String ne ;
    String respuesta ;
    String TError ;

    public Usuario(String usuario, String mail, String contra, String nombre, String ne, String respuesta, String TError) {
        this.usuario = usuario;
        this.mail = mail;
        this.contra = contra;
        this.nombre = nombre;
        this.ne = ne;
        this.respuesta = respuesta;
        this.TError = TError;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getTError() {
        return TError;
    }

    public void setTError(String TError) {
        this.TError = TError;
    }
}
