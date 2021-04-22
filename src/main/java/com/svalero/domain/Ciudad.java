package com.svalero.domain;

public class Ciudad {

    private String ciudad_id;
    private String nombre_ciudad;
    private String ccaa;

    public Ciudad() {

    }

    public Ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

    public String getCiudad_id() {
        return ciudad_id;
    }

    public void setCiudad_id(String ciudad_id) {
        this.ciudad_id = ciudad_id;
    }

    public String getNombre_ciudad() {
        return nombre_ciudad;
    }

    public void setNombre_ciudad(String nombre_ciudad) {
        this.nombre_ciudad = nombre_ciudad;
    }

    public String getCcaa() {
        return ccaa;
    }

    public void setCcaa(String ccaa) {
        this.ccaa = ccaa;
    }

    @Override
    public String toString() {
        return nombre_ciudad;
    }
}
