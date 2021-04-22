
//CLASE CORRESPONDIENTE A UNA DE LAS TABLAS REGISTRADAS EN LA BBDD
package com.svalero.domain;

public class Parque {

    private String parque_id;
    private String ciudad_id;
    private String nombre_parque;
    private double extension_ha;


    public Parque() {

    }

    public Parque(String parque_id, String nombre_parque, double extension_ha) {
        this.parque_id = parque_id;
        this.nombre_parque = nombre_parque;
        this.extension_ha = extension_ha;
    }

    public String getParque_id() {
        return parque_id;
    }

    public void setParque_id(String parque_id) {
        this.parque_id = parque_id;
    }

    public String getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(String ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public String getNombre_parque() {
        return nombre_parque;
    }

    public void setNombre_parque(String nombre_parque) {
        this.nombre_parque = nombre_parque;
    }

    public double getExtension_ha() {
        return extension_ha;
    }

    public void setExtension_ha(double extension_ha) {
        this.extension_ha = extension_ha;
    }

    @Override
    public String toString() {
        return nombre_parque;
    }
}
