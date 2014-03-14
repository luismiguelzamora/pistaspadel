package com.reservas.controller;

/**
 *
 * @author luis miguel zamora aviles
 */
public enum Rutas {

    RUTA_VISTAS("/WEB-INF/vistas"),
    RUTA_PLANTILLAS("/WEB-INF/plantillas"),
    RUTA_PLANTILLA_ERROR("/WEB-INF/plantillas/error.jsp"),
    RUTA_PLANTILLA_PRIVADA("/WEB-INF/plantillas/privada.jsp"),
    RUTA_PLANTILLA_PUBLICA("/WEB-INF/plantillas/publica.jsp");

    private final java.lang.String ruta;

    private Rutas(java.lang.String ruta) {
        this.ruta = ruta;
    }

    public String getRuta() {
        return ruta;
    }

}
