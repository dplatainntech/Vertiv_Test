package com.maju.desarrollo.testfcs.ServiceClass;

public class PreTrabajo {
    String IdPretrabajo;
    String NombreCliente;
    String GEN_SR;
    String GEN_TASK;
    String GEN_PROYECTO;
    String GEN_SITIO;
    String GEN_REFERENCIA;
    String GEN_USUARIO_FINAL;
    boolean EPP_ELECTICO_GUANTES;
    String INSPEC_PERMISO_ARCHIVO;
    String FIRMA_1_CARGO;
    String FIRMA_2_CARGO;
    String FIRMA_3_CARGO;
    String FIRMA_4_CARGO;
    String FIRMA_5_CARGO;
    String FIRMA_6_CARGO;
    String IdUsuario;
    String IdCliente;
    String Exitoso;
    String Error;
    String FolioPreTrabajo;
    String NombreSitio;
    String Fecha;
    String LiderGrupoCuadrilla;
    String DireccionSitio;
    String DescripcionActividad;
    String EquiposHerramientasMateriales;
    boolean HojasSeguridad;
    boolean CintaPrecaucion;
    boolean ConosPedestales;
    boolean  Mixta;
    boolean Malla;
    String OtrosArea;
    boolean  ProyeccionParticulas;
    boolean Atrapamiento;
    boolean GolpesCortes;
    boolean Quemaduras;
    boolean CaidaMateriales;
    boolean CaidaMismoNivel;
    boolean CaidaDistintoNivel;
    boolean OrdenLimpieza;
    boolean OtroPersonalTrabajando;
    boolean ChoqueElectrico;
    boolean ArcoElectrico;
    boolean FuegoExplosion;
    boolean ExposicionRuido;
    boolean ExposicionVibraciones;
    boolean FatigaVisual;
    boolean ExposicionAltasBjasTemperaturas;
    boolean DeficienciaOxigeno;
    boolean ExposiconGases;
    boolean ExposicionPolvo;
    boolean SobreEsfuerzo;
    boolean ManipulacionProductosQuimicos;
    boolean Ruido;
    String OtrosRiesgos;
    boolean UsarDispositivosElevacion;
    boolean SustituirQuimicosToxicos;
    boolean AislarRuidoGenerado;
    boolean  ColocarGuardasProtectoras;
    boolean InstalarPlataformas;
    boolean  InstalarSistemaPuntosAnclaje;
    boolean InstalarIluminacion;
    boolean InstalarDisyuntor;
    boolean InstalarSistemaPuestaTierra;
    boolean MantenerOrden;
    boolean SeñalizarAreaTrabajo;
    boolean BloquearEtiquetarFuentesEnergia;
    boolean InstalarMurosContenerDerrames;
    boolean PermisoTrabajo;
    boolean ProcedTrabajo;
    boolean SupervisionPermanente;
    boolean UsarHerramientaAislada;
    boolean EquipoProteccionPersonal;
    String OtrosMedidasPrevension;
    boolean Casco;
    boolean GafasProtectoras;
    boolean ProtectoresAuditores;
    boolean  ZapatosSeguridad;
    boolean GuantesTrabajo;
    boolean Barbiquejo;
    boolean GafasSeguridad;
    boolean CascoElectrico;
    boolean ZapatosDielectricos;
    boolean SobreguanteCuero;
    boolean CaretaArcoElectrico;
    boolean BalaClava;
    boolean TrajeArcoElectrico;
    boolean ProtectoresAuditivos;
    boolean MangasDielectricas;
    boolean ProteccionContraCaidas;
    boolean ProteccionRespiratoria;
    boolean ProteccionSoldadora;
    boolean ProteccionContraQuimicos;
    String Adiconales;
    boolean InspeccionEPP;
    String EspecifiqueDano;
    boolean EntradaEspaciosConfinados;
    boolean TrabajosCaliente;
    boolean TrabajosEquiposEnergizados;
    boolean NA;
    boolean TrabajosAltura;
    boolean CondicionesInseguras;
    String CondicionInsegura;
    String MedidasCorrectivas;
    String Nombre1;
    String ArchivoFirma1;
    String Nombre2;
    String ArchivoFirma2;
    String Nombre3;
    String ArchivoFirma3;
    String Nombre4;
    String ArchivoFirma4;
    String Nombre5;
    String ArchivoFirma5;
    String Nombre6;
    String ArchivoFirma6;
    String NumeroEmergencia;
    String NumeroSupervisor;
    String HospitalCercano;
    String TipoParticipante1;
    String TipoParticipante2;
    String TipoParticipante3;
    String TipoParticipante4;
    String TipoParticipante5;
    String TipoParticipante6;
    String Pais;

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getIdPretrabajo() {
        return IdPretrabajo;
    }

    public void setIdPretrabajo(String idPretrabajo) {
        IdPretrabajo = idPretrabajo;
    }

    public String getTipoParticipante1() {
        return TipoParticipante1;
    }

    public void setTipoParticipante1(String tipoParticipante1) {
        TipoParticipante1 = tipoParticipante1;
    }

    public String getTipoParticipante2() {
        return TipoParticipante2;
    }

    public void setTipoParticipante2(String tipoParticipante2) {
        TipoParticipante2 = tipoParticipante2;
    }

    public String getTipoParticipante3() {
        return TipoParticipante3;
    }

    public void setTipoParticipante3(String tipoParticipante3) {
        TipoParticipante3 = tipoParticipante3;
    }

    public String getTipoParticipante4() {
        return TipoParticipante4;
    }

    public void setTipoParticipante4(String tipoParticipante4) {
        TipoParticipante4 = tipoParticipante4;
    }

    public String getTipoParticipante5() {
        return TipoParticipante5;
    }

    public void setTipoParticipante5(String tipoParticipante5) {
        TipoParticipante5 = tipoParticipante5;
    }

    public String getTipoParticipante6() {
        return TipoParticipante6;
    }

    public void setTipoParticipante6(String tipoParticipante6) {
        TipoParticipante6 = tipoParticipante6;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        NombreCliente = nombreCliente;
    }

    /*
        public PreTrabajo(String idUsuario, String idCliente, String exitoso, String error, String folioPreTrabajo, String nombreSitio, String fecha, String liderGrupoCuadrilla, String direccionSitio, String descripcionActividad, String equiposHerramientasMateriales, boolean hojasSeguridad, boolean cintaPrecaucion, boolean conosPedestales, boolean mixta, boolean malla, String otrosArea, boolean proyeccionParticulas, boolean atrapamiento, boolean golpesCortes, boolean quemaduras, boolean caidaMateriales, boolean caidaMismoNivel, boolean caidaDistintoNivel, boolean ordenLimpieza, boolean otroPersonalTrabajando, boolean choqueElectrico, boolean arcoElectrico, boolean fuegoExplosion, boolean exposicionRuido, boolean exposicionVibraciones, boolean fatigaVisual, boolean exposicionAltasBjasTemperaturas, boolean deficienciaOxigeno, boolean exposiconGases, boolean exposicionPolvo, boolean sobreEsfuerzo, boolean manipulacionProductosQuimicos, boolean ruido, String otrosRiesgos, boolean usarDispositivosElevacion, boolean sustituirQuimicosToxicos, boolean aislarRuidoGenerado, boolean colocarGuardasProtectoras, boolean instalarPlataformas, boolean instalarSistemaPuntosAnclaje, boolean instalarIluminacion, boolean instalarDisyuntor, boolean instalarSistemaPuestaTierra, boolean mantenerOrden, boolean señalizarAreaTrabajo, boolean bloquearEtiquetarFuentesEnergia, boolean instalarMurosContenerDerrames, boolean permisoTrabajo, boolean procedTrabajo, boolean supervisionPermanente, boolean usarHerramientaAislada, boolean equipoProteccionPersonal, String otrosMedidasPrevension, boolean casco, boolean gafasProtectoras, boolean protectoresAuditores, boolean zapatosSeguridad, boolean guantesTrabajo, boolean barbiquejo, boolean gafasSeguridad, boolean cascoElectrico, boolean zapatosDielectricos, boolean sobreguanteCuero, boolean caretaArcoElectrico, boolean balaClava, boolean trajeArcoElectrico, boolean protectoresAuditivos, boolean mangasDielectricas, boolean proteccionContraCaidas, boolean proteccionRespiratoria, boolean proteccionSoldadora, boolean proteccionContraQuimicos, String adiconales, boolean inspeccionEPP, String especifiqueDano, boolean entradaEspaciosConfinados, boolean trabajosCaliente, boolean trabajosEquiposEnergizados, boolean NA, boolean trabajosAltura, boolean condicionesInseguras, String condicionInsegura, String medidasCorrectivas, String nombre1, String archivoFirma1, String nombre2, String archivoFirma2, String nombre3, String archivoFirma3, String nombre4, String archivoFirma4, String nombre5, String ArchivoFirma5, String nombre6, String archivoFirma6, String numeroEmergencia, String numeroSupervisor, String hospitalCercano) {
            IdUsuario = idUsuario;
            IdCliente = idCliente;
            Exitoso = exitoso;
            Error = error;
            FolioPreTrabajo = folioPreTrabajo;
            NombreSitio = nombreSitio;
            Fecha = fecha;
            LiderGrupoCuadrilla = liderGrupoCuadrilla;
            DireccionSitio = direccionSitio;
            DescripcionActividad = descripcionActividad;
            EquiposHerramientasMateriales = equiposHerramientasMateriales;
            HojasSeguridad = hojasSeguridad;
            CintaPrecaucion = cintaPrecaucion;
            ConosPedestales = conosPedestales;
            Mixta = mixta;
            Malla = malla;
            OtrosArea = otrosArea;
            ProyeccionParticulas = proyeccionParticulas;
            Atrapamiento = atrapamiento;
            GolpesCortes = golpesCortes;
            Quemaduras = quemaduras;
            CaidaMateriales = caidaMateriales;
            CaidaMismoNivel = caidaMismoNivel;
            CaidaDistintoNivel = caidaDistintoNivel;
            OrdenLimpieza = ordenLimpieza;
            OtroPersonalTrabajando = otroPersonalTrabajando;
            ChoqueElectrico = choqueElectrico;
            ArcoElectrico = arcoElectrico;
            FuegoExplosion = fuegoExplosion;
            ExposicionRuido = exposicionRuido;
            ExposicionVibraciones = exposicionVibraciones;
            FatigaVisual = fatigaVisual;
            ExposicionAltasBjasTemperaturas = exposicionAltasBjasTemperaturas;
            DeficienciaOxigeno = deficienciaOxigeno;
            ExposiconGases = exposiconGases;
            ExposicionPolvo = exposicionPolvo;
            SobreEsfuerzo = sobreEsfuerzo;
            ManipulacionProductosQuimicos = manipulacionProductosQuimicos;
            Ruido = ruido;
            OtrosRiesgos = otrosRiesgos;
            UsarDispositivosElevacion = usarDispositivosElevacion;
            SustituirQuimicosToxicos = sustituirQuimicosToxicos;
            AislarRuidoGenerado = aislarRuidoGenerado;
            ColocarGuardasProtectoras = colocarGuardasProtectoras;
            InstalarPlataformas = instalarPlataformas;
            InstalarSistemaPuntosAnclaje = instalarSistemaPuntosAnclaje;
            InstalarIluminacion = instalarIluminacion;
            InstalarDisyuntor = instalarDisyuntor;
            InstalarSistemaPuestaTierra = instalarSistemaPuestaTierra;
            MantenerOrden = mantenerOrden;
            SeñalizarAreaTrabajo = señalizarAreaTrabajo;
            BloquearEtiquetarFuentesEnergia = bloquearEtiquetarFuentesEnergia;
            InstalarMurosContenerDerrames = instalarMurosContenerDerrames;
            PermisoTrabajo = permisoTrabajo;
            ProcedTrabajo = procedTrabajo;
            SupervisionPermanente = supervisionPermanente;
            UsarHerramientaAislada = usarHerramientaAislada;
            EquipoProteccionPersonal = equipoProteccionPersonal;
            OtrosMedidasPrevension = otrosMedidasPrevension;
            Casco = casco;
            GafasProtectoras = gafasProtectoras;
            ProtectoresAuditores = protectoresAuditores;
            ZapatosSeguridad = zapatosSeguridad;
            GuantesTrabajo = guantesTrabajo;
            Barbiquejo = barbiquejo;
            GafasSeguridad = gafasSeguridad;
            CascoElectrico = cascoElectrico;
            ZapatosDielectricos = zapatosDielectricos;
            SobreguanteCuero = sobreguanteCuero;
            CaretaArcoElectrico = caretaArcoElectrico;
            BalaClava = balaClava;
            TrajeArcoElectrico = trajeArcoElectrico;
            ProtectoresAuditivos = protectoresAuditivos;
            MangasDielectricas = mangasDielectricas;
            ProteccionContraCaidas = proteccionContraCaidas;
            ProteccionRespiratoria = proteccionRespiratoria;
            ProteccionSoldadora = proteccionSoldadora;
            ProteccionContraQuimicos = proteccionContraQuimicos;
            Adiconales = adiconales;
            InspeccionEPP = inspeccionEPP;
            EspecifiqueDano = especifiqueDano;
            EntradaEspaciosConfinados = entradaEspaciosConfinados;
            TrabajosCaliente = trabajosCaliente;
            TrabajosEquiposEnergizados = trabajosEquiposEnergizados;
            this.NA = NA;
            TrabajosAltura = trabajosAltura;
            CondicionesInseguras = condicionesInseguras;
            CondicionInsegura = condicionInsegura;
            MedidasCorrectivas = medidasCorrectivas;
            Nombre1 = nombre1;
            ArchivoFirma1 = archivoFirma1;
            Nombre2 = nombre2;
            ArchivoFirma2 = archivoFirma2;
            Nombre3 = nombre3;
            ArchivoFirma3 = archivoFirma3;
            Nombre4 = nombre4;
            ArchivoFirma4 = archivoFirma4;
            Nombre5 = nombre5;
            this.ArchivoFirma5 = ArchivoFirma5;
            Nombre6 = nombre6;
            ArchivoFirma6 = archivoFirma6;
            NumeroEmergencia = numeroEmergencia;
            NumeroSupervisor = numeroSupervisor;
            HospitalCercano = hospitalCercano;
        }
    */
public String getGEN_SR() {
    return GEN_SR;
}

    public void setGEN_SR(String GEN_SR) {
        this.GEN_SR = GEN_SR;
    }

    public String getGEN_TASK() {
        return GEN_TASK;
    }

    public void setGEN_TASK(String GEN_TASK) {
        this.GEN_TASK = GEN_TASK;
    }

    public String getGEN_PROYECTO() {
        return GEN_PROYECTO;
    }

    public void setGEN_PROYECTO(String GEN_PROYECTO) {
        this.GEN_PROYECTO = GEN_PROYECTO;
    }

    public String getGEN_SITIO() {
        return GEN_SITIO;
    }

    public void setGEN_SITIO(String GEN_SITIO) {
        this.GEN_SITIO = GEN_SITIO;
    }

    public String getGEN_REFERENCIA() {
        return GEN_REFERENCIA;
    }

    public void setGEN_REFERENCIA(String GEN_REFERENCIA) {
        this.GEN_REFERENCIA = GEN_REFERENCIA;
    }

    public String getGEN_USUARIO_FINAL() {
        return GEN_USUARIO_FINAL;
    }

    public void setGEN_USUARIO_FINAL(String GEN_USUARIO_FINAL) {
        this.GEN_USUARIO_FINAL = GEN_USUARIO_FINAL;
    }

    public boolean isEPP_ELECTICO_GUANTES() {
        return EPP_ELECTICO_GUANTES;
    }

    public void setEPP_ELECTICO_GUANTES(boolean EPP_ELECTICO_GUANTES) {
        this.EPP_ELECTICO_GUANTES = EPP_ELECTICO_GUANTES;
    }

    public String getINSPEC_PERMISO_ARCHIVO() {
        return INSPEC_PERMISO_ARCHIVO;
    }

    public void setINSPEC_PERMISO_ARCHIVO(String INSPEC_PERMISO_ARCHIVO) {
        this.INSPEC_PERMISO_ARCHIVO = INSPEC_PERMISO_ARCHIVO;
    }

    public String getFIRMA_1_CARGO() {
        return FIRMA_1_CARGO;
    }

    public void setFIRMA_1_CARGO(String FIRMA_1_CARGO) {
        this.FIRMA_1_CARGO = FIRMA_1_CARGO;
    }

    public String getFIRMA_2_CARGO() {
        return FIRMA_2_CARGO;
    }

    public void setFIRMA_2_CARGO(String FIRMA_2_CARGO) {
        this.FIRMA_2_CARGO = FIRMA_2_CARGO;
    }

    public String getFIRMA_3_CARGO() {
        return FIRMA_3_CARGO;
    }

    public void setFIRMA_3_CARGO(String FIRMA_3_CARGO) {
        this.FIRMA_3_CARGO = FIRMA_3_CARGO;
    }

    public String getFIRMA_4_CARGO() {
        return FIRMA_4_CARGO;
    }

    public void setFIRMA_4_CARGO(String FIRMA_4_CARGO) {
        this.FIRMA_4_CARGO = FIRMA_4_CARGO;
    }

    public String getFIRMA_5_CARGO() {
        return FIRMA_5_CARGO;
    }

    public void setFIRMA_5_CARGO(String FIRMA_5_CARGO) {
        this.FIRMA_5_CARGO = FIRMA_5_CARGO;
    }

    public String getFIRMA_6_CARGO() {
        return FIRMA_6_CARGO;
    }

    public void setFIRMA_6_CARGO(String FIRMA_6_CARGO) {
        this.FIRMA_6_CARGO = FIRMA_6_CARGO;
    }

    public String getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        IdUsuario = idUsuario;
    }

    public String getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(String idCliente) {
        IdCliente = idCliente;
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

    public String getFolioPreTrabajo() {
        return FolioPreTrabajo;
    }

    public void setFolioPreTrabajo(String folioPreTrabajo) {
        FolioPreTrabajo = folioPreTrabajo;
    }

    public String getNombreSitio() {
        return NombreSitio;
    }

    public void setNombreSitio(String nombreSitio) {
        NombreSitio = nombreSitio;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public String getLiderGrupoCuadrilla() {
        return LiderGrupoCuadrilla;
    }

    public void setLiderGrupoCuadrilla(String liderGrupoCuadrilla) {
        LiderGrupoCuadrilla = liderGrupoCuadrilla;
    }

    public String getDireccionSitio() {
        return DireccionSitio;
    }

    public void setDireccionSitio(String direccionSitio) {
        DireccionSitio = direccionSitio;
    }

    public String getDescripcionActividad() {
        return DescripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        DescripcionActividad = descripcionActividad;
    }

    public String getEquiposHerramientasMateriales() {
        return EquiposHerramientasMateriales;
    }

    public void setEquiposHerramientasMateriales(String equiposHerramientasMateriales) {
        EquiposHerramientasMateriales = equiposHerramientasMateriales;
    }

    public boolean isHojasSeguridad() {
        return HojasSeguridad;
    }

    public void setHojasSeguridad(boolean hojasSeguridad) {
        HojasSeguridad = hojasSeguridad;
    }

    public boolean isCintaPrecaucion() {
        return CintaPrecaucion;
    }

    public void setCintaPrecaucion(boolean cintaPrecaucion) {
        CintaPrecaucion = cintaPrecaucion;
    }

    public boolean isConosPedestales() {
        return ConosPedestales;
    }

    public void setConosPedestales(boolean conosPedestales) {
        ConosPedestales = conosPedestales;
    }

    public boolean isMixta() {
        return Mixta;
    }

    public void setMixta(boolean mixta) {
        Mixta = mixta;
    }

    public boolean isMalla() {
        return Malla;
    }

    public void setMalla(boolean malla) {
        Malla = malla;
    }

    public String getOtrosArea() {
        return OtrosArea;
    }

    public void setOtrosArea(String otrosArea) {
        OtrosArea = otrosArea;
    }

    public boolean isProyeccionParticulas() {
        return ProyeccionParticulas;
    }

    public void setProyeccionParticulas(boolean proyeccionParticulas) {
        ProyeccionParticulas = proyeccionParticulas;
    }

    public boolean isAtrapamiento() {
        return Atrapamiento;
    }

    public void setAtrapamiento(boolean atrapamiento) {
        Atrapamiento = atrapamiento;
    }

    public boolean isGolpesCortes() {
        return GolpesCortes;
    }

    public void setGolpesCortes(boolean golpesCortes) {
        GolpesCortes = golpesCortes;
    }

    public boolean isQuemaduras() {
        return Quemaduras;
    }

    public void setQuemaduras(boolean quemaduras) {
        Quemaduras = quemaduras;
    }

    public boolean isCaidaMateriales() {
        return CaidaMateriales;
    }

    public void setCaidaMateriales(boolean caidaMateriales) {
        CaidaMateriales = caidaMateriales;
    }

    public boolean isCaidaMismoNivel() {
        return CaidaMismoNivel;
    }

    public void setCaidaMismoNivel(boolean caidaMismoNivel) {
        CaidaMismoNivel = caidaMismoNivel;
    }

    public boolean isCaidaDistintoNivel() {
        return CaidaDistintoNivel;
    }

    public void setCaidaDistintoNivel(boolean caidaDistintoNivel) {
        CaidaDistintoNivel = caidaDistintoNivel;
    }

    public boolean isOrdenLimpieza() {
        return OrdenLimpieza;
    }

    public void setOrdenLimpieza(boolean ordenLimpieza) {
        OrdenLimpieza = ordenLimpieza;
    }

    public boolean isOtroPersonalTrabajando() {
        return OtroPersonalTrabajando;
    }

    public void setOtroPersonalTrabajando(boolean otroPersonalTrabajando) {
        OtroPersonalTrabajando = otroPersonalTrabajando;
    }

    public boolean isChoqueElectrico() {
        return ChoqueElectrico;
    }

    public void setChoqueElectrico(boolean choqueElectrico) {
        ChoqueElectrico = choqueElectrico;
    }

    public boolean isArcoElectrico() {
        return ArcoElectrico;
    }

    public void setArcoElectrico(boolean arcoElectrico) {
        ArcoElectrico = arcoElectrico;
    }

    public boolean isFuegoExplosion() {
        return FuegoExplosion;
    }

    public void setFuegoExplosion(boolean fuegoExplosion) {
        FuegoExplosion = fuegoExplosion;
    }

    public boolean isExposicionRuido() {
        return ExposicionRuido;
    }

    public void setExposicionRuido(boolean exposicionRuido) {
        ExposicionRuido = exposicionRuido;
    }

    public boolean isExposicionVibraciones() {
        return ExposicionVibraciones;
    }

    public void setExposicionVibraciones(boolean exposicionVibraciones) {
        ExposicionVibraciones = exposicionVibraciones;
    }

    public boolean isFatigaVisual() {
        return FatigaVisual;
    }

    public void setFatigaVisual(boolean fatigaVisual) {
        FatigaVisual = fatigaVisual;
    }

    public boolean isExposicionAltasBjasTemperaturas() {
        return ExposicionAltasBjasTemperaturas;
    }

    public void setExposicionAltasBjasTemperaturas(boolean exposicionAltasBjasTemperaturas) {
        ExposicionAltasBjasTemperaturas = exposicionAltasBjasTemperaturas;
    }

    public boolean isDeficienciaOxigeno() {
        return DeficienciaOxigeno;
    }

    public void setDeficienciaOxigeno(boolean deficienciaOxigeno) {
        DeficienciaOxigeno = deficienciaOxigeno;
    }

    public boolean isExposiconGases() {
        return ExposiconGases;
    }

    public void setExposiconGases(boolean exposiconGases) {
        ExposiconGases = exposiconGases;
    }

    public boolean isExposicionPolvo() {
        return ExposicionPolvo;
    }

    public void setExposicionPolvo(boolean exposicionPolvo) {
        ExposicionPolvo = exposicionPolvo;
    }

    public boolean isSobreEsfuerzo() {
        return SobreEsfuerzo;
    }

    public void setSobreEsfuerzo(boolean sobreEsfuerzo) {
        SobreEsfuerzo = sobreEsfuerzo;
    }

    public boolean isManipulacionProductosQuimicos() {
        return ManipulacionProductosQuimicos;
    }

    public void setManipulacionProductosQuimicos(boolean manipulacionProductosQuimicos) {
        ManipulacionProductosQuimicos = manipulacionProductosQuimicos;
    }

    public boolean isRuido() {
        return Ruido;
    }

    public void setRuido(boolean ruido) {
        Ruido = ruido;
    }

    public String getOtrosRiesgos() {
        return OtrosRiesgos;
    }

    public void setOtrosRiesgos(String otrosRiesgos) {
        OtrosRiesgos = otrosRiesgos;
    }

    public boolean isUsarDispositivosElevacion() {
        return UsarDispositivosElevacion;
    }

    public void setUsarDispositivosElevacion(boolean usarDispositivosElevacion) {
        UsarDispositivosElevacion = usarDispositivosElevacion;
    }

    public boolean isSustituirQuimicosToxicos() {
        return SustituirQuimicosToxicos;
    }

    public void setSustituirQuimicosToxicos(boolean sustituirQuimicosToxicos) {
        SustituirQuimicosToxicos = sustituirQuimicosToxicos;
    }

    public boolean isAislarRuidoGenerado() {
        return AislarRuidoGenerado;
    }

    public void setAislarRuidoGenerado(boolean aislarRuidoGenerado) {
        AislarRuidoGenerado = aislarRuidoGenerado;
    }

    public boolean isColocarGuardasProtectoras() {
        return ColocarGuardasProtectoras;
    }

    public void setColocarGuardasProtectoras(boolean colocarGuardasProtectoras) {
        ColocarGuardasProtectoras = colocarGuardasProtectoras;
    }

    public boolean isInstalarPlataformas() {
        return InstalarPlataformas;
    }

    public void setInstalarPlataformas(boolean instalarPlataformas) {
        InstalarPlataformas = instalarPlataformas;
    }

    public boolean isInstalarSistemaPuntosAnclaje() {
        return InstalarSistemaPuntosAnclaje;
    }

    public void setInstalarSistemaPuntosAnclaje(boolean instalarSistemaPuntosAnclaje) {
        InstalarSistemaPuntosAnclaje = instalarSistemaPuntosAnclaje;
    }

    public boolean isInstalarIluminacion() {
        return InstalarIluminacion;
    }

    public void setInstalarIluminacion(boolean instalarIluminacion) {
        InstalarIluminacion = instalarIluminacion;
    }

    public boolean isInstalarDisyuntor() {
        return InstalarDisyuntor;
    }

    public void setInstalarDisyuntor(boolean instalarDisyuntor) {
        InstalarDisyuntor = instalarDisyuntor;
    }

    public boolean isInstalarSistemaPuestaTierra() {
        return InstalarSistemaPuestaTierra;
    }

    public void setInstalarSistemaPuestaTierra(boolean instalarSistemaPuestaTierra) {
        InstalarSistemaPuestaTierra = instalarSistemaPuestaTierra;
    }

    public boolean isMantenerOrden() {
        return MantenerOrden;
    }

    public void setMantenerOrden(boolean mantenerOrden) {
        MantenerOrden = mantenerOrden;
    }

    public boolean isSeñalizarAreaTrabajo() {
        return SeñalizarAreaTrabajo;
    }

    public void setSeñalizarAreaTrabajo(boolean señalizarAreaTrabajo) {
        SeñalizarAreaTrabajo = señalizarAreaTrabajo;
    }

    public boolean isBloquearEtiquetarFuentesEnergia() {
        return BloquearEtiquetarFuentesEnergia;
    }

    public void setBloquearEtiquetarFuentesEnergia(boolean bloquearEtiquetarFuentesEnergia) {
        BloquearEtiquetarFuentesEnergia = bloquearEtiquetarFuentesEnergia;
    }

    public boolean isInstalarMurosContenerDerrames() {
        return InstalarMurosContenerDerrames;
    }

    public void setInstalarMurosContenerDerrames(boolean instalarMurosContenerDerrames) {
        InstalarMurosContenerDerrames = instalarMurosContenerDerrames;
    }

    public boolean isPermisoTrabajo() {
        return PermisoTrabajo;
    }

    public void setPermisoTrabajo(boolean permisoTrabajo) {
        PermisoTrabajo = permisoTrabajo;
    }

    public boolean isProcedTrabajo() {
        return ProcedTrabajo;
    }

    public void setProcedTrabajo(boolean procedTrabajo) {
        ProcedTrabajo = procedTrabajo;
    }

    public boolean isSupervisionPermanente() {
        return SupervisionPermanente;
    }

    public void setSupervisionPermanente(boolean supervisionPermanente) {
        SupervisionPermanente = supervisionPermanente;
    }

    public boolean isUsarHerramientaAislada() {
        return UsarHerramientaAislada;
    }

    public void setUsarHerramientaAislada(boolean usarHerramientaAislada) {
        UsarHerramientaAislada = usarHerramientaAislada;
    }

    public boolean isEquipoProteccionPersonal() {
        return EquipoProteccionPersonal;
    }

    public void setEquipoProteccionPersonal(boolean equipoProteccionPersonal) {
        EquipoProteccionPersonal = equipoProteccionPersonal;
    }

    public String getOtrosMedidasPrevension() {
        return OtrosMedidasPrevension;
    }

    public void setOtrosMedidasPrevension(String otrosMedidasPrevension) {
        OtrosMedidasPrevension = otrosMedidasPrevension;
    }

    public boolean isCasco() {
        return Casco;
    }

    public void setCasco(boolean casco) {
        Casco = casco;
    }

    public boolean isGafasProtectoras() {
        return GafasProtectoras;
    }

    public void setGafasProtectoras(boolean gafasProtectoras) {
        GafasProtectoras = gafasProtectoras;
    }

    public boolean isProtectoresAuditores() {
        return ProtectoresAuditores;
    }

    public void setProtectoresAuditores(boolean protectoresAuditores) {
        ProtectoresAuditores = protectoresAuditores;
    }

    public boolean isZapatosSeguridad() {
        return ZapatosSeguridad;
    }

    public void setZapatosSeguridad(boolean zapatosSeguridad) {
        ZapatosSeguridad = zapatosSeguridad;
    }

    public boolean isGuantesTrabajo() {
        return GuantesTrabajo;
    }

    public void setGuantesTrabajo(boolean guantesTrabajo) {
        GuantesTrabajo = guantesTrabajo;
    }

    public boolean isBarbiquejo() {
        return Barbiquejo;
    }

    public void setBarbiquejo(boolean barbiquejo) {
        Barbiquejo = barbiquejo;
    }

    public boolean isGafasSeguridad() {
        return GafasSeguridad;
    }

    public void setGafasSeguridad(boolean gafasSeguridad) {
        GafasSeguridad = gafasSeguridad;
    }

    public boolean isCascoElectrico() {
        return CascoElectrico;
    }

    public void setCascoElectrico(boolean cascoElectrico) {
        CascoElectrico = cascoElectrico;
    }

    public boolean isZapatosDielectricos() {
        return ZapatosDielectricos;
    }

    public void setZapatosDielectricos(boolean zapatosDielectricos) {
        ZapatosDielectricos = zapatosDielectricos;
    }

    public boolean isSobreguanteCuero() {
        return SobreguanteCuero;
    }

    public void setSobreguanteCuero(boolean sobreguanteCuero) {
        SobreguanteCuero = sobreguanteCuero;
    }

    public boolean isCaretaArcoElectrico() {
        return CaretaArcoElectrico;
    }

    public void setCaretaArcoElectrico(boolean caretaArcoElectrico) {
        CaretaArcoElectrico = caretaArcoElectrico;
    }

    public boolean isBalaClava() {
        return BalaClava;
    }

    public void setBalaClava(boolean balaClava) {
        BalaClava = balaClava;
    }

    public boolean isTrajeArcoElectrico() {
        return TrajeArcoElectrico;
    }

    public void setTrajeArcoElectrico(boolean trajeArcoElectrico) {
        TrajeArcoElectrico = trajeArcoElectrico;
    }

    public boolean isProtectoresAuditivos() {
        return ProtectoresAuditivos;
    }

    public void setProtectoresAuditivos(boolean protectoresAuditivos) {
        ProtectoresAuditivos = protectoresAuditivos;
    }

    public boolean isMangasDielectricas() {
        return MangasDielectricas;
    }

    public void setMangasDielectricas(boolean mangasDielectricas) {
        MangasDielectricas = mangasDielectricas;
    }

    public boolean isProteccionContraCaidas() {
        return ProteccionContraCaidas;
    }

    public void setProteccionContraCaidas(boolean proteccionContraCaidas) {
        ProteccionContraCaidas = proteccionContraCaidas;
    }

    public boolean isProteccionRespiratoria() {
        return ProteccionRespiratoria;
    }

    public void setProteccionRespiratoria(boolean proteccionRespiratoria) {
        ProteccionRespiratoria = proteccionRespiratoria;
    }

    public boolean isProteccionSoldadora() {
        return ProteccionSoldadora;
    }

    public void setProteccionSoldadora(boolean proteccionSoldadora) {
        ProteccionSoldadora = proteccionSoldadora;
    }

    public boolean isProteccionContraQuimicos() {
        return ProteccionContraQuimicos;
    }

    public void setProteccionContraQuimicos(boolean proteccionContraQuimicos) {
        ProteccionContraQuimicos = proteccionContraQuimicos;
    }

    public String getAdiconales() {
        return Adiconales;
    }

    public void setAdiconales(String adiconales) {
        Adiconales = adiconales;
    }

    public boolean isInspeccionEPP() {
        return InspeccionEPP;
    }

    public void setInspeccionEPP(boolean inspeccionEPP) {
        InspeccionEPP = inspeccionEPP;
    }

    public String getEspecifiqueDano() {
        return EspecifiqueDano;
    }

    public void setEspecifiqueDano(String especifiqueDano) {
        EspecifiqueDano = especifiqueDano;
    }

    public boolean isEntradaEspaciosConfinados() {
        return EntradaEspaciosConfinados;
    }

    public void setEntradaEspaciosConfinados(boolean entradaEspaciosConfinados) {
        EntradaEspaciosConfinados = entradaEspaciosConfinados;
    }

    public boolean isTrabajosCaliente() {
        return TrabajosCaliente;
    }

    public void setTrabajosCaliente(boolean trabajosCaliente) {
        TrabajosCaliente = trabajosCaliente;
    }

    public boolean isTrabajosEquiposEnergizados() {
        return TrabajosEquiposEnergizados;
    }

    public void setTrabajosEquiposEnergizados(boolean trabajosEquiposEnergizados) {
        TrabajosEquiposEnergizados = trabajosEquiposEnergizados;
    }

    public boolean isNA() {
        return NA;
    }

    public void setNA(boolean NA) {
        this.NA = NA;
    }

    public boolean isTrabajosAltura() {
        return TrabajosAltura;
    }

    public void setTrabajosAltura(boolean trabajosAltura) {
        TrabajosAltura = trabajosAltura;
    }

    public boolean isCondicionesInseguras() {
        return CondicionesInseguras;
    }

    public void setCondicionesInseguras(boolean condicionesInseguras) {
        CondicionesInseguras = condicionesInseguras;
    }

    public String getCondicionInsegura() {
        return CondicionInsegura;
    }

    public void setCondicionInsegura(String condicionInsegura) {
        CondicionInsegura = condicionInsegura;
    }

    public String getMedidasCorrectivas() {
        return MedidasCorrectivas;
    }

    public void setMedidasCorrectivas(String medidasCorrectivas) {
        MedidasCorrectivas = medidasCorrectivas;
    }

    public String getNombre1() {
        return Nombre1;
    }

    public void setNombre1(String nombre1) {
        Nombre1 = nombre1;
    }

    public String getArchivoFirma1() {
        return ArchivoFirma1;
    }

    public void setArchivoFirma1(String archivoFirma1) {
        ArchivoFirma1 = archivoFirma1;
    }

    public String getNombre2() {
        return Nombre2;
    }

    public void setNombre2(String nombre2) {
        Nombre2 = nombre2;
    }

    public String getArchivoFirma2() {
        return ArchivoFirma2;
    }

    public void setArchivoFirma2(String archivoFirma2) {
        ArchivoFirma2 = archivoFirma2;
    }

    public String getNombre3() {
        return Nombre3;
    }

    public void setNombre3(String nombre3) {
        Nombre3 = nombre3;
    }

    public String getArchivoFirma3() {
        return ArchivoFirma3;
    }

    public void setArchivoFirma3(String archivoFirma3) {
        ArchivoFirma3 = archivoFirma3;
    }

    public String getNombre4() {
        return Nombre4;
    }

    public void setNombre4(String nombre4) {
        Nombre4 = nombre4;
    }

    public String getArchivoFirma4() {
        return ArchivoFirma4;
    }

    public void setArchivoFirma4(String archivoFirma4) {
        ArchivoFirma4 = archivoFirma4;
    }

    public String getNombre5() {
        return Nombre5;
    }

    public void setNombre5(String nombre5) {
        Nombre5 = nombre5;
    }

    public String getArchivoFirma5() {
        return ArchivoFirma5;
    }

    public void setArchivoFirma5(String ArchivoFirma5) {
        this.ArchivoFirma5 = ArchivoFirma5;
    }

    public String getNombre6() {
        return Nombre6;
    }

    public void setNombre6(String nombre6) {
        Nombre6 = nombre6;
    }

    public String getArchivoFirma6() {
        return ArchivoFirma6;
    }

    public void setArchivoFirma6(String archivoFirma6) {
        ArchivoFirma6 = archivoFirma6;
    }

    public String getNumeroEmergencia() {
        return NumeroEmergencia;
    }

    public void setNumeroEmergencia(String numeroEmergencia) {
        NumeroEmergencia = numeroEmergencia;
    }

    public String getNumeroSupervisor() {
        return NumeroSupervisor;
    }

    public void setNumeroSupervisor(String numeroSupervisor) {
        NumeroSupervisor = numeroSupervisor;
    }

    public String getHospitalCercano() {
        return HospitalCercano;
    }

    public void setHospitalCercano(String hospitalCercano) {
        HospitalCercano = hospitalCercano;
    }
}
