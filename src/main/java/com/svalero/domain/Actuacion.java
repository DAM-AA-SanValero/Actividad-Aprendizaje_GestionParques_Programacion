package com.svalero.domain;

public class Actuacion {

    private String actuacion_id;
    private String parque_id;
    private String informacion;
    private int duracion;

    public Actuacion() {

    }

    public String getActuacion_id() {
        return actuacion_id;
    }

    public void setActuacion_id(String actuacion_id) {
        this.actuacion_id = actuacion_id;
    }

    public String getParque_id() {
        return parque_id;
    }

    public void setParque_id(String parque_id) {
        this.parque_id = parque_id;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
