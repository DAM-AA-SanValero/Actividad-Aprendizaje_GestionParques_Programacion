package com.svalero.domain;

public class Ejecucion {

    private String actuacion_id;
    private String cuadrilla_id;
    private String fecha_actuacion;

    public Ejecucion() {

    }

    public String getActuacion_id() {
        return actuacion_id;
    }

    public void setActuacion_id(String actuacion_id) {
        this.actuacion_id = actuacion_id;
    }

    public String getCuadrilla_id() {
        return cuadrilla_id;
    }

    public void setCuadrilla_id(String cuadrilla_id) {
        this.cuadrilla_id = cuadrilla_id;
    }

    public String getFecha_actuacion() {
        return fecha_actuacion;
    }

    public void setFecha_actuacion(String fecha_actuacion) {
        this.fecha_actuacion = fecha_actuacion;
    }
}
