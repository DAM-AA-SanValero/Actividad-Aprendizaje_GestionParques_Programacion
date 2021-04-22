package com.svalero.domain;

public class Jardinero {

    private String jardinero_id;
    private String cuadrilla_id;
    private String jefe_id;
    private String nombre;
    private String apellido;

    public Jardinero() {

    }

    public String getJardinero_id() {
        return jardinero_id;
    }

    public void setJardinero_id(String jardinero_id) {
        this.jardinero_id = jardinero_id;
    }

    public String getCuadrilla_id() {
        return cuadrilla_id;
    }

    public void setCuadrilla_id(String cuadrilla_id) {

        this.cuadrilla_id = cuadrilla_id;
    }

    public String getJefe_id() {
        return jefe_id;
    }

    public void setJefe_id(String jefe_id) {
        this.jefe_id = jefe_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
