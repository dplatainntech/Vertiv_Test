package com.maju.desarrollo.testfcs.SQLite.Model;

public class InfoGenerica {
    String Id_Formato;
    String FolioPT;
    String Formato;
    String Fecha;
    String SR;
    String TASK;
    String Item;
    String Serie;
    String cliente;

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getId_Formato() {
        return Id_Formato;
    }

    public void setId_Formato(String id_Formato) {
        Id_Formato = id_Formato;
    }

    public String getFolioPT() {
        return FolioPT;
    }

    public void setFolioPT(String folioPT) {
        FolioPT = folioPT;
    }

    public String getFormato() {
        return Formato;
    }

    public void setFormato(String formato) {
        Formato = formato;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getSR() {
        return SR;
    }

    public void setSR(String SR) {
        this.SR = SR;
    }

    public String getTASK() {
        return TASK;
    }

    public void setTASK(String TASK) {
        this.TASK = TASK;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public String getSerie() {
        return Serie;
    }

    public void setSerie(String serie) {
        Serie = serie;
    }
}
