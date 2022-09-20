package com.maju.desarrollo.testfcs.SQLite.Model;

public class PreOrden {
    String FOLIO;
    String GEN_SR;
    String GEN_TASK;
    String GEN_CLIENTE;
    String GEN_SITIO;
    String GEN_DIRECCION;
    String GEN_REFERENCIA;
    String GEN_FECHA;
    String GEN_USUARIO_FINAL;
    String GEN_LIDER_GRUPO;
    String ACTV_DESCRIPCION_ACTIVIDADES;
    String HERRAM_DESCRIPCION_EHM;
    String HERRAM_HOJAS_SEGURIDAD;
    String HERRAM_DELIMITACION_AT;
    String RIESGO_PARTICULAS;
    String RIESGO_ATRAPAMIENTO;
    String RIESGO_GOLPES;
    String RIESGO_QUEMADURAS;
    String RIESGO_CAIDA_MATE;
    String RIESGO_CAIDA_MISMO_NIVEL;
    String RIESGO_CAIDA_DIST_NIVEL;
    String RIESGO_LIMPIEZA_DEFI;
    String RIESGO_OTRO_PERSONAL;
    String RIESGO_CHOQUE_ELECTRICO;
    String RIESGO_ARCO_ELECT;
    String RIESGO_FUEGO;
    String RIESGO_EXPO_RUIDO;
    String RIESGO_EXP_VIBRA;
    String RIESGO_FATIGA_VISUAL;
    String RIESGO_TEMPERATURAS;
    String RIESGO_DEFI_OXIGENO;
    String RIESGO_GASES;
    String RIESGO_POLVO;
    String RIESGO_SOBRE_ESFUERZO;
    String RIESGO_QUIMICOS;
    String RIESGO_RUIDO;
    String RIESGO_OTRO;
    String PREVEN_DISPO_MECANICA;
    String PREVEN_SUST_QUIMICOS;
    String PREVEN_AISLAR_RUIDO;
    String PREVEN_PROTECTORES_MAQUINAS;
    String PREVEN_PLATA_ANDAMIOS;
    String PREVEN_SIS_PNTS_ANCLAJE;
    String PREVEN_ILUMI_ART;
    String PREVEN_DISYUNTOR;
    String PREVEN_SIST_PUESTA_TIERRA;
    String PREVEN_ORDEN_LIMPIEZA;
    String PREVEN_AREA_TRABAJO;
    String PREVEN_BE_FUENTES_ENERGIA;
    String PREVEN_MUROS_DERRAME;
    String PREVEN_PERMISOS;
    String PREVEN_INSTRUCTIVOS;
    String PREVEN_SUPERVISION;
    String PREVEN_HERRAMI_AISLADAS;
    String PREVEN_EPP;
    String PREVEN_OTRO;
    String EPP_BASICO_CASCO;
    String EPP_BASICO_GAFAS;
    String EPP_BASICO_TAPONES;
    String EPP_BASICO_ZAPATOS;
    String EPP_BASICO_GUANTES;
    String EPP_BASICO_BARBIQUEJO;
    String EPP_ELECTICO_GAFAS;
    String EPP_ELECTICO_CASCO;
    String EPP_ELECTICO_ZAPATOS;
    String EPP_ELECTICO_GUANTES;
    String EPP_ELECTICO_SOBREGUANTE;
    String EPP_ELECTICO_CARETA;
    String EPP_ELECTICO_BALACLAVA;
    String EPP_ELECTICO_TRAJE;
    String EPP_ELECTICO_PROTECTORES_AUDI;
    String EPP_ELECTICO_MANGAS;
    String EPP_OTROS_PROTECCION_CAIDAS;
    String EPP_OTROS_PROTECCION_RESPITA;
    String EPP_OTROS_PROTECCION_SOLDAD;
    String EPP_OTROS_PROTECCION_QUIMICOS;
    String EPP_OTROS_ADICIONALES;
    String EMERG_EMERGENCIAS;
    String EMERG_SUPERVISOR_VERTIV;
    String EMERG_HOSPITAL;
    String INSPEC_CONDIC_OPTIMAS;
    String INSPEC_CONDIC_OPTIMAS_NO;
    String INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE;
    String INSPEC_ACTIVIDADES;
    String INSPEC_PERMISO_ARCHIVO;
    String INSPEC_TRABAJOS_ALTURA;
    String INSPEC_CONDIC_INSEGURAS;
    String INSPEC_CAUSAS_ACCIDENTES;
    String INSPEC_MEDIDAS_CORRECTIVAS;
    String FIRMA_1_NOMBRE;
    String FIRMA_1_CARGO;
    String FIRMA_1_IMAGEN;
    String FIRMA_2_NOMBRE;
    String FIRMA_2_CARGO;
    String FIRMA_2_IMAGEN;
    String FIRMA_3_NOMBRE;
    String FIRMA_3_CARGO;
    String FIRMA_3_IMAGEN;
    String FIRMA_4_NOMBRE;
    String FIRMA_4_CARGO;
    String FIRMA_4_IMAGEN;
    String FIRMA_5_NOMBRE;
    String FIRMA_5_CARGO;
    String FIRMA_5_IMAGEN;
    String FIRMA_6_NOMBRE;
    String FIRMA_6_CARGO;
    String FIRMA_6_IMAGEN;
    String GEN_PROYECTO;

    public PreOrden(String FOLIO, String GEN_SR, String GEN_TASK, String GEN_CLIENTE, String GEN_SITIO, String GEN_DIRECCION, String GEN_REFERENCIA, String GEN_FECHA, String GEN_USUARIO_FINAL, String GEN_LIDER_GRUPO, String ACTV_DESCRIPCION_ACTIVIDADES, String HERRAM_DESCRIPCION_EHM, String HERRAM_HOJAS_SEGURIDAD, String HERRAM_DELIMITACION_AT, String RIESGO_PARTICULAS, String RIESGO_ATRAPAMIENTO, String RIESGO_GOLPES, String RIESGO_QUEMADURAS, String RIESGO_CAIDA_MATE, String RIESGO_CAIDA_MISMO_NIVEL, String RIESGO_CAIDA_DIST_NIVEL, String RIESGO_LIMPIEZA_DEFI, String RIESGO_OTRO_PERSONAL, String RIESGO_CHOQUE_ELECTRICO, String RIESGO_ARCO_ELECT, String RIESGO_FUEGO, String RIESGO_EXPO_RUIDO, String RIESGO_EXP_VIBRA, String RIESGO_FATIGA_VISUAL, String RIESGO_TEMPERATURAS, String RIESGO_DEFI_OXIGENO, String RIESGO_GASES, String RIESGO_POLVO, String RIESGO_SOBRE_ESFUERZO, String RIESGO_QUIMICOS, String RIESGO_RUIDO, String RIESGO_OTRO, String PREVEN_DISPO_MECANICA, String PREVEN_SUST_QUIMICOS, String PREVEN_AISLAR_RUIDO, String PREVEN_PROTECTORES_MAQUINAS, String PREVEN_PLATA_ANDAMIOS, String PREVEN_SIS_PNTS_ANCLAJE, String PREVEN_ILUMI_ART, String PREVEN_DISYUNTOR, String PREVEN_SIST_PUESTA_TIERRA, String PREVEN_ORDEN_LIMPIEZA, String PREVEN_AREA_TRABAJO, String PREVEN_BE_FUENTES_ENERGIA, String PREVEN_MUROS_DERRAME, String PREVEN_PERMISOS, String PREVEN_INSTRUCTIVOS, String PREVEN_SUPERVISION, String PREVEN_HERRAMI_AISLADAS, String PREVEN_EPP, String PREVEN_OTRO, String EPP_BASICO_CASCO, String EPP_BASICO_GAFAS, String EPP_BASICO_TAPONES, String EPP_BASICO_ZAPATOS, String EPP_BASICO_GUANTES, String EPP_BASICO_BARBIQUEJO, String EPP_ELECTICO_GAFAS, String EPP_ELECTICO_CASCO, String EPP_ELECTICO_ZAPATOS, String EPP_ELECTICO_GUANTES, String EPP_ELECTICO_SOBREGUANTE, String EPP_ELECTICO_CARETA, String EPP_ELECTICO_BALACLAVA, String EPP_ELECTICO_TRAJE, String EPP_ELECTICO_PROTECTORES_AUDI, String EPP_ELECTICO_MANGAS, String EPP_OTROS_PROTECCION_CAIDAS, String EPP_OTROS_PROTECCION_RESPITA, String EPP_OTROS_PROTECCION_SOLDAD, String EPP_OTROS_PROTECCION_QUIMICOS, String EPP_OTROS_ADICIONALES, String EMERG_EMERGENCIAS, String EMERG_SUPERVISOR_VERTIV, String EMERG_HOSPITAL, String INSPEC_CONDIC_OPTIMAS, String INSPEC_CONDIC_OPTIMAS_NO, String INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE, String INSPEC_ACTIVIDADES, String INSPEC_PERMISO_ARCHIVO, String INSPEC_TRABAJOS_ALTURA, String INSPEC_CONDIC_INSEGURAS, String INSPEC_CAUSAS_ACCIDENTES, String INSPEC_MEDIDAS_CORRECTIVAS, String FIRMA_1_NOMBRE, String FIRMA_1_CARGO, String FIRMA_1_IMAGEN, String FIRMA_2_NOMBRE, String FIRMA_2_CARGO, String FIRMA_2_IMAGEN, String FIRMA_3_NOMBRE, String FIRMA_3_CARGO, String FIRMA_3_IMAGEN, String FIRMA_4_NOMBRE, String FIRMA_4_CARGO, String FIRMA_4_IMAGEN, String FIRMA_5_NOMBRE, String FIRMA_5_CARGO, String FIRMA_5_IMAGEN, String FIRMA_6_NOMBRE, String FIRMA_6_CARGO, String FIRMA_6_IMAGEN, String GEN_PROYECTO) {
        this.FOLIO = FOLIO;
        this.GEN_SR = GEN_SR;
        this.GEN_TASK = GEN_TASK;
        this.GEN_CLIENTE = GEN_CLIENTE;
        this.GEN_SITIO = GEN_SITIO;
        this.GEN_DIRECCION = GEN_DIRECCION;
        this.GEN_REFERENCIA = GEN_REFERENCIA;
        this.GEN_FECHA = GEN_FECHA;
        this.GEN_USUARIO_FINAL = GEN_USUARIO_FINAL;
        this.GEN_LIDER_GRUPO = GEN_LIDER_GRUPO;
        this.ACTV_DESCRIPCION_ACTIVIDADES = ACTV_DESCRIPCION_ACTIVIDADES;
        this.HERRAM_DESCRIPCION_EHM = HERRAM_DESCRIPCION_EHM;
        this.HERRAM_HOJAS_SEGURIDAD = HERRAM_HOJAS_SEGURIDAD;
        this.HERRAM_DELIMITACION_AT = HERRAM_DELIMITACION_AT;
        this.RIESGO_PARTICULAS = RIESGO_PARTICULAS;
        this.RIESGO_ATRAPAMIENTO = RIESGO_ATRAPAMIENTO;
        this.RIESGO_GOLPES = RIESGO_GOLPES;
        this.RIESGO_QUEMADURAS = RIESGO_QUEMADURAS;
        this.RIESGO_CAIDA_MATE = RIESGO_CAIDA_MATE;
        this.RIESGO_CAIDA_MISMO_NIVEL = RIESGO_CAIDA_MISMO_NIVEL;
        this.RIESGO_CAIDA_DIST_NIVEL = RIESGO_CAIDA_DIST_NIVEL;
        this.RIESGO_LIMPIEZA_DEFI = RIESGO_LIMPIEZA_DEFI;
        this.RIESGO_OTRO_PERSONAL = RIESGO_OTRO_PERSONAL;
        this.RIESGO_CHOQUE_ELECTRICO = RIESGO_CHOQUE_ELECTRICO;
        this.RIESGO_ARCO_ELECT = RIESGO_ARCO_ELECT;
        this.RIESGO_FUEGO = RIESGO_FUEGO;
        this.RIESGO_EXPO_RUIDO = RIESGO_EXPO_RUIDO;
        this.RIESGO_EXP_VIBRA = RIESGO_EXP_VIBRA;
        this.RIESGO_FATIGA_VISUAL = RIESGO_FATIGA_VISUAL;
        this.RIESGO_TEMPERATURAS = RIESGO_TEMPERATURAS;
        this.RIESGO_DEFI_OXIGENO = RIESGO_DEFI_OXIGENO;
        this.RIESGO_GASES = RIESGO_GASES;
        this.RIESGO_POLVO = RIESGO_POLVO;
        this.RIESGO_SOBRE_ESFUERZO = RIESGO_SOBRE_ESFUERZO;
        this.RIESGO_QUIMICOS = RIESGO_QUIMICOS;
        this.RIESGO_RUIDO = RIESGO_RUIDO;
        this.RIESGO_OTRO = RIESGO_OTRO;
        this.PREVEN_DISPO_MECANICA = PREVEN_DISPO_MECANICA;
        this.PREVEN_SUST_QUIMICOS = PREVEN_SUST_QUIMICOS;
        this.PREVEN_AISLAR_RUIDO = PREVEN_AISLAR_RUIDO;
        this.PREVEN_PROTECTORES_MAQUINAS = PREVEN_PROTECTORES_MAQUINAS;
        this.PREVEN_PLATA_ANDAMIOS = PREVEN_PLATA_ANDAMIOS;
        this.PREVEN_SIS_PNTS_ANCLAJE = PREVEN_SIS_PNTS_ANCLAJE;
        this.PREVEN_ILUMI_ART = PREVEN_ILUMI_ART;
        this.PREVEN_DISYUNTOR = PREVEN_DISYUNTOR;
        this.PREVEN_SIST_PUESTA_TIERRA = PREVEN_SIST_PUESTA_TIERRA;
        this.PREVEN_ORDEN_LIMPIEZA = PREVEN_ORDEN_LIMPIEZA;
        this.PREVEN_AREA_TRABAJO = PREVEN_AREA_TRABAJO;
        this.PREVEN_BE_FUENTES_ENERGIA = PREVEN_BE_FUENTES_ENERGIA;
        this.PREVEN_MUROS_DERRAME = PREVEN_MUROS_DERRAME;
        this.PREVEN_PERMISOS = PREVEN_PERMISOS;
        this.PREVEN_INSTRUCTIVOS = PREVEN_INSTRUCTIVOS;
        this.PREVEN_SUPERVISION = PREVEN_SUPERVISION;
        this.PREVEN_HERRAMI_AISLADAS = PREVEN_HERRAMI_AISLADAS;
        this.PREVEN_EPP = PREVEN_EPP;
        this.PREVEN_OTRO = PREVEN_OTRO;
        this.EPP_BASICO_CASCO = EPP_BASICO_CASCO;
        this.EPP_BASICO_GAFAS = EPP_BASICO_GAFAS;
        this.EPP_BASICO_TAPONES = EPP_BASICO_TAPONES;
        this.EPP_BASICO_ZAPATOS = EPP_BASICO_ZAPATOS;
        this.EPP_BASICO_GUANTES = EPP_BASICO_GUANTES;
        this.EPP_BASICO_BARBIQUEJO = EPP_BASICO_BARBIQUEJO;
        this.EPP_ELECTICO_GAFAS = EPP_ELECTICO_GAFAS;
        this.EPP_ELECTICO_CASCO = EPP_ELECTICO_CASCO;
        this.EPP_ELECTICO_ZAPATOS = EPP_ELECTICO_ZAPATOS;
        this.EPP_ELECTICO_GUANTES = EPP_ELECTICO_GUANTES;
        this.EPP_ELECTICO_SOBREGUANTE = EPP_ELECTICO_SOBREGUANTE;
        this.EPP_ELECTICO_CARETA = EPP_ELECTICO_CARETA;
        this.EPP_ELECTICO_BALACLAVA = EPP_ELECTICO_BALACLAVA;
        this.EPP_ELECTICO_TRAJE = EPP_ELECTICO_TRAJE;
        this.EPP_ELECTICO_PROTECTORES_AUDI = EPP_ELECTICO_PROTECTORES_AUDI;
        this.EPP_ELECTICO_MANGAS = EPP_ELECTICO_MANGAS;
        this.EPP_OTROS_PROTECCION_CAIDAS = EPP_OTROS_PROTECCION_CAIDAS;
        this.EPP_OTROS_PROTECCION_RESPITA = EPP_OTROS_PROTECCION_RESPITA;
        this.EPP_OTROS_PROTECCION_SOLDAD = EPP_OTROS_PROTECCION_SOLDAD;
        this.EPP_OTROS_PROTECCION_QUIMICOS = EPP_OTROS_PROTECCION_QUIMICOS;
        this.EPP_OTROS_ADICIONALES = EPP_OTROS_ADICIONALES;
        this.EMERG_EMERGENCIAS = EMERG_EMERGENCIAS;
        this.EMERG_SUPERVISOR_VERTIV = EMERG_SUPERVISOR_VERTIV;
        this.EMERG_HOSPITAL = EMERG_HOSPITAL;
        this.INSPEC_CONDIC_OPTIMAS = INSPEC_CONDIC_OPTIMAS;
        this.INSPEC_CONDIC_OPTIMAS_NO = INSPEC_CONDIC_OPTIMAS_NO;
        this.INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE = INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE;
        this.INSPEC_ACTIVIDADES = INSPEC_ACTIVIDADES;
        this.INSPEC_PERMISO_ARCHIVO = INSPEC_PERMISO_ARCHIVO;
        this.INSPEC_TRABAJOS_ALTURA = INSPEC_TRABAJOS_ALTURA;
        this.INSPEC_CONDIC_INSEGURAS = INSPEC_CONDIC_INSEGURAS;
        this.INSPEC_CAUSAS_ACCIDENTES = INSPEC_CAUSAS_ACCIDENTES;
        this.INSPEC_MEDIDAS_CORRECTIVAS = INSPEC_MEDIDAS_CORRECTIVAS;
        this.FIRMA_1_NOMBRE = FIRMA_1_NOMBRE;
        this.FIRMA_1_CARGO = FIRMA_1_CARGO;
        this.FIRMA_1_IMAGEN = FIRMA_1_IMAGEN;
        this.FIRMA_2_NOMBRE = FIRMA_2_NOMBRE;
        this.FIRMA_2_CARGO = FIRMA_2_CARGO;
        this.FIRMA_2_IMAGEN = FIRMA_2_IMAGEN;
        this.FIRMA_3_NOMBRE = FIRMA_3_NOMBRE;
        this.FIRMA_3_CARGO = FIRMA_3_CARGO;
        this.FIRMA_3_IMAGEN = FIRMA_3_IMAGEN;
        this.FIRMA_4_NOMBRE = FIRMA_4_NOMBRE;
        this.FIRMA_4_CARGO = FIRMA_4_CARGO;
        this.FIRMA_4_IMAGEN = FIRMA_4_IMAGEN;
        this.FIRMA_5_NOMBRE = FIRMA_5_NOMBRE;
        this.FIRMA_5_CARGO = FIRMA_5_CARGO;
        this.FIRMA_5_IMAGEN = FIRMA_5_IMAGEN;
        this.FIRMA_6_NOMBRE = FIRMA_6_NOMBRE;
        this.FIRMA_6_CARGO = FIRMA_6_CARGO;
        this.FIRMA_6_IMAGEN = FIRMA_6_IMAGEN;
        this.GEN_PROYECTO = GEN_PROYECTO;
    }

    public String getGEN_PROYECTO() {
        return GEN_PROYECTO;
    }

    public void setGEN_PROYECTO(String GEN_PROYECTO) {
        this.GEN_PROYECTO = GEN_PROYECTO;
    }

    public String getFOLIO() {
        return FOLIO;
    }

    public void setFOLIO(String FOLIO) {
        this.FOLIO = FOLIO;
    }

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

    public String getGEN_CLIENTE() {
        return GEN_CLIENTE;
    }

    public void setGEN_CLIENTE(String GEN_CLIENTE) {
        this.GEN_CLIENTE = GEN_CLIENTE;
    }

    public String getGEN_SITIO() {
        return GEN_SITIO;
    }

    public void setGEN_SITIO(String GEN_SITIO) {
        this.GEN_SITIO = GEN_SITIO;
    }

    public String getGEN_DIRECCION() {
        return GEN_DIRECCION;
    }

    public void setGEN_DIRECCION(String GEN_DIRECCION) {
        this.GEN_DIRECCION = GEN_DIRECCION;
    }

    public String getGEN_REFERENCIA() {
        return GEN_REFERENCIA;
    }

    public void setGEN_REFERENCIA(String GEN_REFERENCIA) {
        this.GEN_REFERENCIA = GEN_REFERENCIA;
    }

    public String getGEN_FECHA() {
        return GEN_FECHA;
    }

    public void setGEN_FECHA(String GEN_FECHA) {
        this.GEN_FECHA = GEN_FECHA;
    }

    public String getGEN_USUARIO_FINAL() {
        return GEN_USUARIO_FINAL;
    }

    public void setGEN_USUARIO_FINAL(String GEN_USUARIO_FINAL) {
        this.GEN_USUARIO_FINAL = GEN_USUARIO_FINAL;
    }

    public String getGEN_LIDER_GRUPO() {
        return GEN_LIDER_GRUPO;
    }

    public void setGEN_LIDER_GRUPO(String GEN_LIDER_GRUPO) {
        this.GEN_LIDER_GRUPO = GEN_LIDER_GRUPO;
    }

    public String getACTV_DESCRIPCION_ACTIVIDADES() {
        return ACTV_DESCRIPCION_ACTIVIDADES;
    }

    public void setACTV_DESCRIPCION_ACTIVIDADES(String ACTV_DESCRIPCION_ACTIVIDADES) {
        this.ACTV_DESCRIPCION_ACTIVIDADES = ACTV_DESCRIPCION_ACTIVIDADES;
    }

    public String getHERRAM_DESCRIPCION_EHM() {
        return HERRAM_DESCRIPCION_EHM;
    }

    public void setHERRAM_DESCRIPCION_EHM(String HERRAM_DESCRIPCION_EHM) {
        this.HERRAM_DESCRIPCION_EHM = HERRAM_DESCRIPCION_EHM;
    }

    public String getHERRAM_HOJAS_SEGURIDAD() {
        return HERRAM_HOJAS_SEGURIDAD;
    }

    public void setHERRAM_HOJAS_SEGURIDAD(String HERRAM_HOJAS_SEGURIDAD) {
        this.HERRAM_HOJAS_SEGURIDAD = HERRAM_HOJAS_SEGURIDAD;
    }

    public String getHERRAM_DELIMITACION_AT() {
        return HERRAM_DELIMITACION_AT;
    }

    public void setHERRAM_DELIMITACION_AT(String HERRAM_DELIMITACION_AT) {
        this.HERRAM_DELIMITACION_AT = HERRAM_DELIMITACION_AT;
    }

    public String getRIESGO_PARTICULAS() {
        return RIESGO_PARTICULAS;
    }

    public void setRIESGO_PARTICULAS(String RIESGO_PARTICULAS) {
        this.RIESGO_PARTICULAS = RIESGO_PARTICULAS;
    }

    public String getRIESGO_ATRAPAMIENTO() {
        return RIESGO_ATRAPAMIENTO;
    }

    public void setRIESGO_ATRAPAMIENTO(String RIESGO_ATRAPAMIENTO) {
        this.RIESGO_ATRAPAMIENTO = RIESGO_ATRAPAMIENTO;
    }

    public String getRIESGO_GOLPES() {
        return RIESGO_GOLPES;
    }

    public void setRIESGO_GOLPES(String RIESGO_GOLPES) {
        this.RIESGO_GOLPES = RIESGO_GOLPES;
    }

    public String getRIESGO_QUEMADURAS() {
        return RIESGO_QUEMADURAS;
    }

    public void setRIESGO_QUEMADURAS(String RIESGO_QUEMADURAS) {
        this.RIESGO_QUEMADURAS = RIESGO_QUEMADURAS;
    }

    public String getRIESGO_CAIDA_MATE() {
        return RIESGO_CAIDA_MATE;
    }

    public void setRIESGO_CAIDA_MATE(String RIESGO_CAIDA_MATE) {
        this.RIESGO_CAIDA_MATE = RIESGO_CAIDA_MATE;
    }

    public String getRIESGO_CAIDA_MISMO_NIVEL() {
        return RIESGO_CAIDA_MISMO_NIVEL;
    }

    public void setRIESGO_CAIDA_MISMO_NIVEL(String RIESGO_CAIDA_MISMO_NIVEL) {
        this.RIESGO_CAIDA_MISMO_NIVEL = RIESGO_CAIDA_MISMO_NIVEL;
    }

    public String getRIESGO_CAIDA_DIST_NIVEL() {
        return RIESGO_CAIDA_DIST_NIVEL;
    }

    public void setRIESGO_CAIDA_DIST_NIVEL(String RIESGO_CAIDA_DIST_NIVEL) {
        this.RIESGO_CAIDA_DIST_NIVEL = RIESGO_CAIDA_DIST_NIVEL;
    }

    public String getRIESGO_LIMPIEZA_DEFI() {
        return RIESGO_LIMPIEZA_DEFI;
    }

    public void setRIESGO_LIMPIEZA_DEFI(String RIESGO_LIMPIEZA_DEFI) {
        this.RIESGO_LIMPIEZA_DEFI = RIESGO_LIMPIEZA_DEFI;
    }

    public String getRIESGO_OTRO_PERSONAL() {
        return RIESGO_OTRO_PERSONAL;
    }

    public void setRIESGO_OTRO_PERSONAL(String RIESGO_OTRO_PERSONAL) {
        this.RIESGO_OTRO_PERSONAL = RIESGO_OTRO_PERSONAL;
    }

    public String getRIESGO_CHOQUE_ELECTRICO() {
        return RIESGO_CHOQUE_ELECTRICO;
    }

    public void setRIESGO_CHOQUE_ELECTRICO(String RIESGO_CHOQUE_ELECTRICO) {
        this.RIESGO_CHOQUE_ELECTRICO = RIESGO_CHOQUE_ELECTRICO;
    }

    public String getRIESGO_ARCO_ELECT() {
        return RIESGO_ARCO_ELECT;
    }

    public void setRIESGO_ARCO_ELECT(String RIESGO_ARCO_ELECT) {
        this.RIESGO_ARCO_ELECT = RIESGO_ARCO_ELECT;
    }

    public String getRIESGO_FUEGO() {
        return RIESGO_FUEGO;
    }

    public void setRIESGO_FUEGO(String RIESGO_FUEGO) {
        this.RIESGO_FUEGO = RIESGO_FUEGO;
    }

    public String getRIESGO_EXPO_RUIDO() {
        return RIESGO_EXPO_RUIDO;
    }

    public void setRIESGO_EXPO_RUIDO(String RIESGO_EXPO_RUIDO) {
        this.RIESGO_EXPO_RUIDO = RIESGO_EXPO_RUIDO;
    }

    public String getRIESGO_EXP_VIBRA() {
        return RIESGO_EXP_VIBRA;
    }

    public void setRIESGO_EXP_VIBRA(String RIESGO_EXP_VIBRA) {
        this.RIESGO_EXP_VIBRA = RIESGO_EXP_VIBRA;
    }

    public String getRIESGO_FATIGA_VISUAL() {
        return RIESGO_FATIGA_VISUAL;
    }

    public void setRIESGO_FATIGA_VISUAL(String RIESGO_FATIGA_VISUAL) {
        this.RIESGO_FATIGA_VISUAL = RIESGO_FATIGA_VISUAL;
    }

    public String getRIESGO_TEMPERATURAS() {
        return RIESGO_TEMPERATURAS;
    }

    public void setRIESGO_TEMPERATURAS(String RIESGO_TEMPERATURAS) {
        this.RIESGO_TEMPERATURAS = RIESGO_TEMPERATURAS;
    }

    public String getRIESGO_DEFI_OXIGENO() {
        return RIESGO_DEFI_OXIGENO;
    }

    public void setRIESGO_DEFI_OXIGENO(String RIESGO_DEFI_OXIGENO) {
        this.RIESGO_DEFI_OXIGENO = RIESGO_DEFI_OXIGENO;
    }

    public String getRIESGO_GASES() {
        return RIESGO_GASES;
    }

    public void setRIESGO_GASES(String RIESGO_GASES) {
        this.RIESGO_GASES = RIESGO_GASES;
    }

    public String getRIESGO_POLVO() {
        return RIESGO_POLVO;
    }

    public void setRIESGO_POLVO(String RIESGO_POLVO) {
        this.RIESGO_POLVO = RIESGO_POLVO;
    }

    public String getRIESGO_SOBRE_ESFUERZO() {
        return RIESGO_SOBRE_ESFUERZO;
    }

    public void setRIESGO_SOBRE_ESFUERZO(String RIESGO_SOBRE_ESFUERZO) {
        this.RIESGO_SOBRE_ESFUERZO = RIESGO_SOBRE_ESFUERZO;
    }

    public String getRIESGO_QUIMICOS() {
        return RIESGO_QUIMICOS;
    }

    public void setRIESGO_QUIMICOS(String RIESGO_QUIMICOS) {
        this.RIESGO_QUIMICOS = RIESGO_QUIMICOS;
    }

    public String getRIESGO_RUIDO() {
        return RIESGO_RUIDO;
    }

    public void setRIESGO_RUIDO(String RIESGO_RUIDO) {
        this.RIESGO_RUIDO = RIESGO_RUIDO;
    }

    public String getRIESGO_OTRO() {
        return RIESGO_OTRO;
    }

    public void setRIESGO_OTRO(String RIESGO_OTRO) {
        this.RIESGO_OTRO = RIESGO_OTRO;
    }

    public String getPREVEN_DISPO_MECANICA() {
        return PREVEN_DISPO_MECANICA;
    }

    public void setPREVEN_DISPO_MECANICA(String PREVEN_DISPO_MECANICA) {
        this.PREVEN_DISPO_MECANICA = PREVEN_DISPO_MECANICA;
    }

    public String getPREVEN_SUST_QUIMICOS() {
        return PREVEN_SUST_QUIMICOS;
    }

    public void setPREVEN_SUST_QUIMICOS(String PREVEN_SUST_QUIMICOS) {
        this.PREVEN_SUST_QUIMICOS = PREVEN_SUST_QUIMICOS;
    }

    public String getPREVEN_AISLAR_RUIDO() {
        return PREVEN_AISLAR_RUIDO;
    }

    public void setPREVEN_AISLAR_RUIDO(String PREVEN_AISLAR_RUIDO) {
        this.PREVEN_AISLAR_RUIDO = PREVEN_AISLAR_RUIDO;
    }

    public String getPREVEN_PROTECTORES_MAQUINAS() {
        return PREVEN_PROTECTORES_MAQUINAS;
    }

    public void setPREVEN_PROTECTORES_MAQUINAS(String PREVEN_PROTECTORES_MAQUINAS) {
        this.PREVEN_PROTECTORES_MAQUINAS = PREVEN_PROTECTORES_MAQUINAS;
    }

    public String getPREVEN_PLATA_ANDAMIOS() {
        return PREVEN_PLATA_ANDAMIOS;
    }

    public void setPREVEN_PLATA_ANDAMIOS(String PREVEN_PLATA_ANDAMIOS) {
        this.PREVEN_PLATA_ANDAMIOS = PREVEN_PLATA_ANDAMIOS;
    }

    public String getPREVEN_SIS_PNTS_ANCLAJE() {
        return PREVEN_SIS_PNTS_ANCLAJE;
    }

    public void setPREVEN_SIS_PNTS_ANCLAJE(String PREVEN_SIS_PNTS_ANCLAJE) {
        this.PREVEN_SIS_PNTS_ANCLAJE = PREVEN_SIS_PNTS_ANCLAJE;
    }

    public String getPREVEN_ILUMI_ART() {
        return PREVEN_ILUMI_ART;
    }

    public void setPREVEN_ILUMI_ART(String PREVEN_ILUMI_ART) {
        this.PREVEN_ILUMI_ART = PREVEN_ILUMI_ART;
    }

    public String getPREVEN_DISYUNTOR() {
        return PREVEN_DISYUNTOR;
    }

    public void setPREVEN_DISYUNTOR(String PREVEN_DISYUNTOR) {
        this.PREVEN_DISYUNTOR = PREVEN_DISYUNTOR;
    }

    public String getPREVEN_SIST_PUESTA_TIERRA() {
        return PREVEN_SIST_PUESTA_TIERRA;
    }

    public void setPREVEN_SIST_PUESTA_TIERRA(String PREVEN_SIST_PUESTA_TIERRA) {
        this.PREVEN_SIST_PUESTA_TIERRA = PREVEN_SIST_PUESTA_TIERRA;
    }

    public String getPREVEN_ORDEN_LIMPIEZA() {
        return PREVEN_ORDEN_LIMPIEZA;
    }

    public void setPREVEN_ORDEN_LIMPIEZA(String PREVEN_ORDEN_LIMPIEZA) {
        this.PREVEN_ORDEN_LIMPIEZA = PREVEN_ORDEN_LIMPIEZA;
    }

    public String getPREVEN_AREA_TRABAJO() {
        return PREVEN_AREA_TRABAJO;
    }

    public void setPREVEN_AREA_TRABAJO(String PREVEN_AREA_TRABAJO) {
        this.PREVEN_AREA_TRABAJO = PREVEN_AREA_TRABAJO;
    }

    public String getPREVEN_BE_FUENTES_ENERGIA() {
        return PREVEN_BE_FUENTES_ENERGIA;
    }

    public void setPREVEN_BE_FUENTES_ENERGIA(String PREVEN_BE_FUENTES_ENERGIA) {
        this.PREVEN_BE_FUENTES_ENERGIA = PREVEN_BE_FUENTES_ENERGIA;
    }

    public String getPREVEN_MUROS_DERRAME() {
        return PREVEN_MUROS_DERRAME;
    }

    public void setPREVEN_MUROS_DERRAME(String PREVEN_MUROS_DERRAME) {
        this.PREVEN_MUROS_DERRAME = PREVEN_MUROS_DERRAME;
    }

    public String getPREVEN_PERMISOS() {
        return PREVEN_PERMISOS;
    }

    public void setPREVEN_PERMISOS(String PREVEN_PERMISOS) {
        this.PREVEN_PERMISOS = PREVEN_PERMISOS;
    }

    public String getPREVEN_INSTRUCTIVOS() {
        return PREVEN_INSTRUCTIVOS;
    }

    public void setPREVEN_INSTRUCTIVOS(String PREVEN_INSTRUCTIVOS) {
        this.PREVEN_INSTRUCTIVOS = PREVEN_INSTRUCTIVOS;
    }

    public String getPREVEN_SUPERVISION() {
        return PREVEN_SUPERVISION;
    }

    public void setPREVEN_SUPERVISION(String PREVEN_SUPERVISION) {
        this.PREVEN_SUPERVISION = PREVEN_SUPERVISION;
    }

    public String getPREVEN_HERRAMI_AISLADAS() {
        return PREVEN_HERRAMI_AISLADAS;
    }

    public void setPREVEN_HERRAMI_AISLADAS(String PREVEN_HERRAMI_AISLADAS) {
        this.PREVEN_HERRAMI_AISLADAS = PREVEN_HERRAMI_AISLADAS;
    }

    public String getPREVEN_EPP() {
        return PREVEN_EPP;
    }

    public void setPREVEN_EPP(String PREVEN_EPP) {
        this.PREVEN_EPP = PREVEN_EPP;
    }

    public String getPREVEN_OTRO() {
        return PREVEN_OTRO;
    }

    public void setPREVEN_OTRO(String PREVEN_OTRO) {
        this.PREVEN_OTRO = PREVEN_OTRO;
    }

    public String getEPP_BASICO_CASCO() {
        return EPP_BASICO_CASCO;
    }

    public void setEPP_BASICO_CASCO(String EPP_BASICO_CASCO) {
        this.EPP_BASICO_CASCO = EPP_BASICO_CASCO;
    }

    public String getEPP_BASICO_GAFAS() {
        return EPP_BASICO_GAFAS;
    }

    public void setEPP_BASICO_GAFAS(String EPP_BASICO_GAFAS) {
        this.EPP_BASICO_GAFAS = EPP_BASICO_GAFAS;
    }

    public String getEPP_BASICO_TAPONES() {
        return EPP_BASICO_TAPONES;
    }

    public void setEPP_BASICO_TAPONES(String EPP_BASICO_TAPONES) {
        this.EPP_BASICO_TAPONES = EPP_BASICO_TAPONES;
    }

    public String getEPP_BASICO_ZAPATOS() {
        return EPP_BASICO_ZAPATOS;
    }

    public void setEPP_BASICO_ZAPATOS(String EPP_BASICO_ZAPATOS) {
        this.EPP_BASICO_ZAPATOS = EPP_BASICO_ZAPATOS;
    }

    public String getEPP_BASICO_GUANTES() {
        return EPP_BASICO_GUANTES;
    }

    public void setEPP_BASICO_GUANTES(String EPP_BASICO_GUANTES) {
        this.EPP_BASICO_GUANTES = EPP_BASICO_GUANTES;
    }

    public String getEPP_BASICO_BARBIQUEJO() {
        return EPP_BASICO_BARBIQUEJO;
    }

    public void setEPP_BASICO_BARBIQUEJO(String EPP_BASICO_BARBIQUEJO) {
        this.EPP_BASICO_BARBIQUEJO = EPP_BASICO_BARBIQUEJO;
    }

    public String getEPP_ELECTICO_GAFAS() {
        return EPP_ELECTICO_GAFAS;
    }

    public void setEPP_ELECTICO_GAFAS(String EPP_ELECTICO_GAFAS) {
        this.EPP_ELECTICO_GAFAS = EPP_ELECTICO_GAFAS;
    }

    public String getEPP_ELECTICO_CASCO() {
        return EPP_ELECTICO_CASCO;
    }

    public void setEPP_ELECTICO_CASCO(String EPP_ELECTICO_CASCO) {
        this.EPP_ELECTICO_CASCO = EPP_ELECTICO_CASCO;
    }

    public String getEPP_ELECTICO_ZAPATOS() {
        return EPP_ELECTICO_ZAPATOS;
    }

    public void setEPP_ELECTICO_ZAPATOS(String EPP_ELECTICO_ZAPATOS) {
        this.EPP_ELECTICO_ZAPATOS = EPP_ELECTICO_ZAPATOS;
    }

    public String getEPP_ELECTICO_GUANTES() {
        return EPP_ELECTICO_GUANTES;
    }

    public void setEPP_ELECTICO_GUANTES(String EPP_ELECTICO_GUANTES) {
        this.EPP_ELECTICO_GUANTES = EPP_ELECTICO_GUANTES;
    }

    public String getEPP_ELECTICO_SOBREGUANTE() {
        return EPP_ELECTICO_SOBREGUANTE;
    }

    public void setEPP_ELECTICO_SOBREGUANTE(String EPP_ELECTICO_SOBREGUANTE) {
        this.EPP_ELECTICO_SOBREGUANTE = EPP_ELECTICO_SOBREGUANTE;
    }

    public String getEPP_ELECTICO_CARETA() {
        return EPP_ELECTICO_CARETA;
    }

    public void setEPP_ELECTICO_CARETA(String EPP_ELECTICO_CARETA) {
        this.EPP_ELECTICO_CARETA = EPP_ELECTICO_CARETA;
    }

    public String getEPP_ELECTICO_BALACLAVA() {
        return EPP_ELECTICO_BALACLAVA;
    }

    public void setEPP_ELECTICO_BALACLAVA(String EPP_ELECTICO_BALACLAVA) {
        this.EPP_ELECTICO_BALACLAVA = EPP_ELECTICO_BALACLAVA;
    }

    public String getEPP_ELECTICO_TRAJE() {
        return EPP_ELECTICO_TRAJE;
    }

    public void setEPP_ELECTICO_TRAJE(String EPP_ELECTICO_TRAJE) {
        this.EPP_ELECTICO_TRAJE = EPP_ELECTICO_TRAJE;
    }

    public String getEPP_ELECTICO_PROTECTORES_AUDI() {
        return EPP_ELECTICO_PROTECTORES_AUDI;
    }

    public void setEPP_ELECTICO_PROTECTORES_AUDI(String EPP_ELECTICO_PROTECTORES_AUDI) {
        this.EPP_ELECTICO_PROTECTORES_AUDI = EPP_ELECTICO_PROTECTORES_AUDI;
    }

    public String getEPP_ELECTICO_MANGAS() {
        return EPP_ELECTICO_MANGAS;
    }

    public void setEPP_ELECTICO_MANGAS(String EPP_ELECTICO_MANGAS) {
        this.EPP_ELECTICO_MANGAS = EPP_ELECTICO_MANGAS;
    }

    public String getEPP_OTROS_PROTECCION_CAIDAS() {
        return EPP_OTROS_PROTECCION_CAIDAS;
    }

    public void setEPP_OTROS_PROTECCION_CAIDAS(String EPP_OTROS_PROTECCION_CAIDAS) {
        this.EPP_OTROS_PROTECCION_CAIDAS = EPP_OTROS_PROTECCION_CAIDAS;
    }

    public String getEPP_OTROS_PROTECCION_RESPITA() {
        return EPP_OTROS_PROTECCION_RESPITA;
    }

    public void setEPP_OTROS_PROTECCION_RESPITA(String EPP_OTROS_PROTECCION_RESPITA) {
        this.EPP_OTROS_PROTECCION_RESPITA = EPP_OTROS_PROTECCION_RESPITA;
    }

    public String getEPP_OTROS_PROTECCION_SOLDAD() {
        return EPP_OTROS_PROTECCION_SOLDAD;
    }

    public void setEPP_OTROS_PROTECCION_SOLDAD(String EPP_OTROS_PROTECCION_SOLDAD) {
        this.EPP_OTROS_PROTECCION_SOLDAD = EPP_OTROS_PROTECCION_SOLDAD;
    }

    public String getEPP_OTROS_PROTECCION_QUIMICOS() {
        return EPP_OTROS_PROTECCION_QUIMICOS;
    }

    public void setEPP_OTROS_PROTECCION_QUIMICOS(String EPP_OTROS_PROTECCION_QUIMICOS) {
        this.EPP_OTROS_PROTECCION_QUIMICOS = EPP_OTROS_PROTECCION_QUIMICOS;
    }

    public String getEPP_OTROS_ADICIONALES() {
        return EPP_OTROS_ADICIONALES;
    }

    public void setEPP_OTROS_ADICIONALES(String EPP_OTROS_ADICIONALES) {
        this.EPP_OTROS_ADICIONALES = EPP_OTROS_ADICIONALES;
    }

    public String getEMERG_EMERGENCIAS() {
        return EMERG_EMERGENCIAS;
    }

    public void setEMERG_EMERGENCIAS(String EMERG_EMERGENCIAS) {
        this.EMERG_EMERGENCIAS = EMERG_EMERGENCIAS;
    }

    public String getEMERG_SUPERVISOR_VERTIV() {
        return EMERG_SUPERVISOR_VERTIV;
    }

    public void setEMERG_SUPERVISOR_VERTIV(String EMERG_SUPERVISOR_VERTIV) {
        this.EMERG_SUPERVISOR_VERTIV = EMERG_SUPERVISOR_VERTIV;
    }

    public String getEMERG_HOSPITAL() {
        return EMERG_HOSPITAL;
    }

    public void setEMERG_HOSPITAL(String EMERG_HOSPITAL) {
        this.EMERG_HOSPITAL = EMERG_HOSPITAL;
    }

    public String getINSPEC_CONDIC_OPTIMAS() {
        return INSPEC_CONDIC_OPTIMAS;
    }

    public void setINSPEC_CONDIC_OPTIMAS(String INSPEC_CONDIC_OPTIMAS) {
        this.INSPEC_CONDIC_OPTIMAS = INSPEC_CONDIC_OPTIMAS;
    }

    public String getINSPEC_CONDIC_OPTIMAS_NO() {
        return INSPEC_CONDIC_OPTIMAS_NO;
    }

    public void setINSPEC_CONDIC_OPTIMAS_NO(String INSPEC_CONDIC_OPTIMAS_NO) {
        this.INSPEC_CONDIC_OPTIMAS_NO = INSPEC_CONDIC_OPTIMAS_NO;
    }

    public String getINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE() {
        return INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE;
    }

    public void setINSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE(String INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE) {
        this.INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE = INSPEC_CONDIC_OPTIMAS_NO_ESPECIFIQUE;
    }

    public String getINSPEC_ACTIVIDADES() {
        return INSPEC_ACTIVIDADES;
    }

    public void setINSPEC_ACTIVIDADES(String INSPEC_ACTIVIDADES) {
        this.INSPEC_ACTIVIDADES = INSPEC_ACTIVIDADES;
    }

    public String getINSPEC_PERMISO_ARCHIVO() {
        return INSPEC_PERMISO_ARCHIVO;
    }

    public void setINSPEC_PERMISO_ARCHIVO(String INSPEC_PERMISO_ARCHIVO) {
        this.INSPEC_PERMISO_ARCHIVO = INSPEC_PERMISO_ARCHIVO;
    }

    public String getINSPEC_TRABAJOS_ALTURA() {
        return INSPEC_TRABAJOS_ALTURA;
    }

    public void setINSPEC_TRABAJOS_ALTURA(String INSPEC_TRABAJOS_ALTURA) {
        this.INSPEC_TRABAJOS_ALTURA = INSPEC_TRABAJOS_ALTURA;
    }

    public String getINSPEC_CONDIC_INSEGURAS() {
        return INSPEC_CONDIC_INSEGURAS;
    }

    public void setINSPEC_CONDIC_INSEGURAS(String INSPEC_CONDIC_INSEGURAS) {
        this.INSPEC_CONDIC_INSEGURAS = INSPEC_CONDIC_INSEGURAS;
    }

    public String getINSPEC_CAUSAS_ACCIDENTES() {
        return INSPEC_CAUSAS_ACCIDENTES;
    }

    public void setINSPEC_CAUSAS_ACCIDENTES(String INSPEC_CAUSAS_ACCIDENTES) {
        this.INSPEC_CAUSAS_ACCIDENTES = INSPEC_CAUSAS_ACCIDENTES;
    }

    public String getINSPEC_MEDIDAS_CORRECTIVAS() {
        return INSPEC_MEDIDAS_CORRECTIVAS;
    }

    public void setINSPEC_MEDIDAS_CORRECTIVAS(String INSPEC_MEDIDAS_CORRECTIVAS) {
        this.INSPEC_MEDIDAS_CORRECTIVAS = INSPEC_MEDIDAS_CORRECTIVAS;
    }

    public String getFIRMA_1_NOMBRE() {
        return FIRMA_1_NOMBRE;
    }

    public void setFIRMA_1_NOMBRE(String FIRMA_1_NOMBRE) {
        this.FIRMA_1_NOMBRE = FIRMA_1_NOMBRE;
    }

    public String getFIRMA_1_CARGO() {
        return FIRMA_1_CARGO;
    }

    public void setFIRMA_1_CARGO(String FIRMA_1_CARGO) {
        this.FIRMA_1_CARGO = FIRMA_1_CARGO;
    }

    public String getFIRMA_1_IMAGEN() {
        return FIRMA_1_IMAGEN;
    }

    public void setFIRMA_1_IMAGEN(String FIRMA_1_IMAGEN) {
        this.FIRMA_1_IMAGEN = FIRMA_1_IMAGEN;
    }

    public String getFIRMA_2_NOMBRE() {
        return FIRMA_2_NOMBRE;
    }

    public void setFIRMA_2_NOMBRE(String FIRMA_2_NOMBRE) {
        this.FIRMA_2_NOMBRE = FIRMA_2_NOMBRE;
    }

    public String getFIRMA_2_CARGO() {
        return FIRMA_2_CARGO;
    }

    public void setFIRMA_2_CARGO(String FIRMA_2_CARGO) {
        this.FIRMA_2_CARGO = FIRMA_2_CARGO;
    }

    public String getFIRMA_2_IMAGEN() {
        return FIRMA_2_IMAGEN;
    }

    public void setFIRMA_2_IMAGEN(String FIRMA_2_IMAGEN) {
        this.FIRMA_2_IMAGEN = FIRMA_2_IMAGEN;
    }

    public String getFIRMA_3_NOMBRE() {
        return FIRMA_3_NOMBRE;
    }

    public void setFIRMA_3_NOMBRE(String FIRMA_3_NOMBRE) {
        this.FIRMA_3_NOMBRE = FIRMA_3_NOMBRE;
    }

    public String getFIRMA_3_CARGO() {
        return FIRMA_3_CARGO;
    }

    public void setFIRMA_3_CARGO(String FIRMA_3_CARGO) {
        this.FIRMA_3_CARGO = FIRMA_3_CARGO;
    }

    public String getFIRMA_3_IMAGEN() {
        return FIRMA_3_IMAGEN;
    }

    public void setFIRMA_3_IMAGEN(String FIRMA_3_IMAGEN) {
        this.FIRMA_3_IMAGEN = FIRMA_3_IMAGEN;
    }

    public String getFIRMA_4_NOMBRE() {
        return FIRMA_4_NOMBRE;
    }

    public void setFIRMA_4_NOMBRE(String FIRMA_4_NOMBRE) {
        this.FIRMA_4_NOMBRE = FIRMA_4_NOMBRE;
    }

    public String getFIRMA_4_CARGO() {
        return FIRMA_4_CARGO;
    }

    public void setFIRMA_4_CARGO(String FIRMA_4_CARGO) {
        this.FIRMA_4_CARGO = FIRMA_4_CARGO;
    }

    public String getFIRMA_4_IMAGEN() {
        return FIRMA_4_IMAGEN;
    }

    public void setFIRMA_4_IMAGEN(String FIRMA_4_IMAGEN) {
        this.FIRMA_4_IMAGEN = FIRMA_4_IMAGEN;
    }

    public String getFIRMA_5_NOMBRE() {
        return FIRMA_5_NOMBRE;
    }

    public void setFIRMA_5_NOMBRE(String FIRMA_5_NOMBRE) {
        this.FIRMA_5_NOMBRE = FIRMA_5_NOMBRE;
    }

    public String getFIRMA_5_CARGO() {
        return FIRMA_5_CARGO;
    }

    public void setFIRMA_5_CARGO(String FIRMA_5_CARGO) {
        this.FIRMA_5_CARGO = FIRMA_5_CARGO;
    }

    public String getFIRMA_5_IMAGEN() {
        return FIRMA_5_IMAGEN;
    }

    public void setFIRMA_5_IMAGEN(String FIRMA_5_IMAGEN) {
        this.FIRMA_5_IMAGEN = FIRMA_5_IMAGEN;
    }

    public String getFIRMA_6_NOMBRE() {
        return FIRMA_6_NOMBRE;
    }

    public void setFIRMA_6_NOMBRE(String FIRMA_6_NOMBRE) {
        this.FIRMA_6_NOMBRE = FIRMA_6_NOMBRE;
    }

    public String getFIRMA_6_CARGO() {
        return FIRMA_6_CARGO;
    }

    public void setFIRMA_6_CARGO(String FIRMA_6_CARGO) {
        this.FIRMA_6_CARGO = FIRMA_6_CARGO;
    }

    public String getFIRMA_6_IMAGEN() {
        return FIRMA_6_IMAGEN;
    }

    public void setFIRMA_6_IMAGEN(String FIRMA_6_IMAGEN) {
        this.FIRMA_6_IMAGEN = FIRMA_6_IMAGEN;
    }
}

//region campos
/*
* String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
    String ;
*
* */
//endregion