//CLASE ENCARGADA DE CONECTAR CON LA BASE DE DATOS
package com.svalero.dao;

import java.sql.*;

public class Conexion {

    private Connection conexion;
    private final String DRIVER = "oracle.jdbc.driver.OracleDriver"; //Crear variable driver en la que va a integrar sql developer
    private final String URL_CONNECTION = "jdbc:oracle:thin:@//localhost:1521/xe"; //Agregar la conexion URL para que pueda conectarse a la bbdd
    private final String USER = "GESTIONPARQUES"; //Añadir el usuario de la BBDD con la que queremos trabajar
    private final String PASSWORD = "1234"; //Añadir la contraseña

    public Conexion() {

    }

    public Connection getConexion() {
        return conexion;
    }

    public void conectar() {
        try {
            Class.forName(DRIVER);
            conexion = DriverManager.getConnection(URL_CONNECTION, USER, PASSWORD);

        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void desconectar() {
        try {
            conexion.close();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }
}
