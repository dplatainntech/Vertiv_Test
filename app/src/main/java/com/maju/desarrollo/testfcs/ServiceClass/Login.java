package com.maju.desarrollo.testfcs.ServiceClass;

public class Login {
    String Usuario;
    String Contraseña;
    String Exitoso;
    String Error;
    String IdUsuario;
    String Pais;
    String PaisDescripcion;

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String contraseña) {
        Contraseña = contraseña;
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

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getPaisDescripcion() {
        return PaisDescripcion;
    }

    public void setPaisDescripcion(String paisDescripcion) {
        PaisDescripcion = paisDescripcion;
    }
}
