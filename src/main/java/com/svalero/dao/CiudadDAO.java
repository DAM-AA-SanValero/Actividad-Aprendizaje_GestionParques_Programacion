package com.svalero.dao;

import com.svalero.domain.Ciudad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CiudadDAO {
    private Conexion conexion;

    public CiudadDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Busca el id de la ciudad introduciendo su nombre
     *
     * @param nombre_ciudad
     * @return Devuelve el id de la ciudad introducida
     * @throws SQLException
     */
    public String confirmarCiudad(String nombre_ciudad) throws SQLException {
        String consulta = "SELECT CIUDAD_ID FROM CIUDADES WHERE NOMBRE_CIUDAD = ?";

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, nombre_ciudad);
        ResultSet resultado = sentencia.executeQuery();

        resultado.next();
        String id_ciudad = resultado.getString(1);

        return id_ciudad;
    }

    /**
     * Lista las ciudades que contengan parques cuya suma total de su extension sea mayor a la introducida
     *
     * @param extension
     * @return Devuelve un listado de ciudades
     * @throws SQLException
     */
    public ArrayList<Ciudad> listarCiudades(Double extension) throws SQLException {
        String consulta = "SELECT C.NOMBRE_CIUDAD FROM CIUDADES C JOIN PARQUES P ON (C.CIUDAD_ID = P.CIUDAD_ID) " +
                "GROUP BY C.NOMBRE_CIUDAD HAVING SUM(P.EXTENSION_HA) > ?";

        ArrayList<Ciudad> listadoCiudades = new ArrayList<>();

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setDouble(1, extension);
        ResultSet listado = sentencia.executeQuery();

        while (listado.next()) {
            Ciudad ciudad = new Ciudad();
            ciudad.setNombre_ciudad(listado.getString(1));
            listadoCiudades.add(ciudad);
        }

        return listadoCiudades;
    }
}
