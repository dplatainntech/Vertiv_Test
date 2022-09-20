package com.maju.desarrollo.testfcs.SQLite.Model;

public class RefaccionesLinea {
    String IdLine;
    String IdHeader;
    String NoParte;
    String Descripcion;
    String Cantidad;
    String UnidadMedida;
    String Origen;
    String Foto1;
    String Foto2;
    String Foto3;
    String Exitoso;
    String Error;


    public String getIDLINE() {
        return IdLine;
    }

    public void setIDLINE(String IDLINE) {
        this.IdLine = IDLINE;
    }

    public String getIDHEADER() {
        return IdHeader;
    }

    public void setIDHEADER(String IDHEADER) {this.IdHeader = IDHEADER;}

    public String getNOPARTE() {
        return NoParte;
    }

    public void setNOPARTE(String NOPARTE) {
        this.NoParte = NOPARTE;
    }

    public String getDESCRIPCION() {
        return Descripcion;
    }

    public void setDESCRIPCION(String DESCRIPCION) {this.Descripcion = DESCRIPCION;}

    public String getCANTIDAD() {
        return Cantidad;
    }

    public void setCANTIDAD(String CANTIDAD) {this.Cantidad = CANTIDAD;}

    public String getORIGEN() {
        return Origen;
    }

    public void setORIGEN(String ORIGEN) {this.Origen = ORIGEN;}

    public String getUNIDADMEDIDA() {
        return UnidadMedida;
    }

    public void setUNIDADMEDIDA(String UNIDADMEDIDA) {
        this.UnidadMedida = UNIDADMEDIDA;
    }

    public String getFOTO1() {
        return Foto1;
    }

    public void setFOTO1(String FOTO1) {
        this.Foto1 = FOTO1;
    }

    public String getFOTO2() {
        return Foto2;
    }

    public void setFOTO2(String FOTO2) {this.Foto2 = FOTO2;}

    public String getFOTO3() {
        return Foto3;
    }

    public void setFOTO3(String FOTO3) {this.Foto3 = FOTO3;}

    public String getExitoso() {
        return Exitoso;
    }

    public void setExitoso(String exitoso) {this.Exitoso = exitoso;}

    public String getError() {
        return Error;
    }

    public void setError(String error) {this.Error = error;}

}
