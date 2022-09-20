package com.maju.desarrollo.testfcs.SQLite.Model;

import android.provider.BaseColumns;

import com.maju.desarrollo.testfcs.SQLite.FilasDB;

public class RefaccionesHeader {
    int Id;
    String IdHeader;
    String IdSolicitante;
    String Cliente;
    String Sitio;
    String SR;
    String Task;
    String Tipo;
    String Otro;
    String Servicio;
    String Contrato;
    String Recolecta;
    String TelCel;
    String DirecEntrega;
    String Comentario;
    String Folio;
    String Estatus;
    String Dias;
    String Pais;
    String Exitoso;
    String Error;

    public int getID() {
        return Id;
    }

    public void setID(int Id) {
        this.Id = Id;
    }

    public String getIDHEADER() {
        return IdHeader;
    }

    public void setIDHEADER(String IDHEADER) {
        this.IdHeader = IDHEADER;
    }

    public String getIDSOLICITANTE() {
        return IdSolicitante;
    }

    public void setIDSOLICITANTE(String IDSOLICITANTE) {this.IdSolicitante = IDSOLICITANTE;}

    public String getCLIENTE() {
        return Cliente;
    }

    public void setCLIENTE(String CLIENTE) {this.Cliente = CLIENTE;}

    public String getSITIO() {
        return Sitio;
    }

    public void setSITIO(String SITIO) {
        this.Sitio = SITIO;
    }

    public String getTIPO() {
        return Tipo;
    }

    public void setTIPO(String TIPO) {
        this.Tipo = TIPO;
    }

    public String getSR() {
        return SR;
    }

    public void setSR(String SR) {
        this.SR = SR;
    }

    public String getTASK() {
        return Task;
    }

    public void setTASK(String TASK) {
        this.Task = TASK;
    }

    public String getSERVICIO() {
        return Servicio;
    }

    public void setSERVICIO(String SERVICIO) {
        this.Servicio = SERVICIO;
    }

    public String getCONTRATO() {
        return Contrato;
    }

    public void setCONTRATO(String CONTRATO) {
        this.Contrato = CONTRATO;
    }

    public String getRECOLECTA() {
        return Recolecta;
    }

    public void setRECOLECTA(String RECOLECTA) {
        this.Recolecta = RECOLECTA;
    }


    public String getTELCEL() {
        return TelCel;
    }

    public void setTELCEL(String TELCEL) {
        this.TelCel = TELCEL;
    }

    public String getDIRECENTREGA() { return DirecEntrega; }

    public void setDIRECENTREGA(String DIRECENTREGA) {this.DirecEntrega = DIRECENTREGA;}

    public String getCOMENTARIO() {
        return Comentario;
    }

    public void setCOMENTARIO(String COMENTARIO) {
        this.Comentario = COMENTARIO;
    }

    public String getOTRO() {
        return Otro;
    }

    public void setOTRO(String OTRO) {
        this.Otro = OTRO;
    }

    public String getFOLIO() {
        return Folio;
    }

    public void setFOLIO(String FOLIO) {
        this.Folio = FOLIO;
    }

    public String getESTATUS() {
        return Estatus;
    }

    public void setESTATUS(String ESTATUS) {
        this.Estatus = ESTATUS;
    }

    public String getDIAS() {
        return Dias;
    }

    public void setDIAS(String DIAS) {
        this.Dias = DIAS;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String Pais) {
        this.Pais = Pais;
    }

    public String getExitoso() {
        return Exitoso;
    }

    public void setExitoso(String exitoso) {
        this.Exitoso = exitoso;
    }

    public String getError() {
        return Error;
    }

    public void setError(String Error) {
        this.Error = Error;
    }


}
