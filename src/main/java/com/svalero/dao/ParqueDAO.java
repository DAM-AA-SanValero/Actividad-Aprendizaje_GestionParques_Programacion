
//CLASE RELACIONADA CON LA TABLA CORRESPONDIENTE A LA BBDD, LA CUAL SE VA A COMUNICAR CON LA BASE DE DATOS SQL DEVELOPER
package com.svalero.dao;

import com.svalero.domain.Ciudad;
import com.svalero.domain.Parque;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParqueDAO {


    private Conexion conexion;

    public ParqueDAO(Conexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Lista los parques por ciudad
     *
     * @param ciudad
     * @return Devuelve un listado de parques
     * @throws SQLException
     */
    public ArrayList<Parque> listarParques_PorCiudad(String ciudad) throws SQLException {
        String consulta = "SELECT P.NOMBRE_PARQUE FROM PARQUES P JOIN CIUDADES C ON (C.CIUDAD_ID = P.CIUDAD_ID) " +
                "WHERE C.NOMBRE_CIUDAD = ?";

        ArrayList<Parque> listadoParques = new ArrayList<>();

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, ciudad);
        ResultSet listado = sentencia.executeQuery();

        while (listado.next()) {
            Parque parque = new Parque();
            parque.setNombre_parque(listado.getString(1));
            listadoParques.add(parque);

        }
        return listadoParques;
    }

    /**
     * Lista los parques por Comunidad Autónoma
     *
     * @param ccaa
     * @return Devuelve un listado de parques
     * @throws SQLException
     */
    public ArrayList<Parque> listarParques_PorCCAA(String ccaa) throws SQLException {
        String consulta = "SELECT P.NOMBRE_PARQUE FROM PARQUES P JOIN CIUDADES C ON (C.CIUDAD_ID = P.CIUDAD_ID) " +
                "WHERE C.CCAA = ?";

        ArrayList<Parque> listadoParques = new ArrayList<>();

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, ccaa);
        ResultSet listado = sentencia.executeQuery();

        while (listado.next()) {
            Parque parque = new Parque();
            parque.setNombre_parque(listado.getString(1));
            listadoParques.add(parque);
        }
        return listadoParques;
    }

    /**
     * Busca un parque introduciendo una cadena de texto
     *
     * @param nombre_parque
     * @return Devuelve un listado de parques que comiencen por la cadena introducida
     * @throws SQLException
     */
    public ArrayList<Parque> buscarParque(String nombre_parque) throws SQLException {
        String consulta = "SELECT NOMBRE_PARQUE FROM PARQUES WHERE UPPER(NOMBRE_PARQUE) LIKE UPPER('" + nombre_parque + "%')";

        ArrayList<Parque> listadoParques = new ArrayList<>();

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        ResultSet listado = sentencia.executeQuery();

        while (listado.next()) {
            Parque parque = new Parque();
            parque.setNombre_parque(listado.getString(1));
            listadoParques.add(parque);
        }

        return listadoParques;
    }

    /**
     * Muestra el número de parques con una extensión mayor a la introducida
     *
     * @param ciudad
     * @param extension
     * @return Devuelve el numero de parques que tengan una extension mayor a la extension introducida
     * @throws SQLException
     */
    public int extension_mayor(String ciudad, Double extension) throws SQLException {
        String consulta = "SELECT COUNT(*) FROM PARQUES P JOIN CIUDADES C ON (C.CIUDAD_ID = P.CIUDAD_ID) " +
                "WHERE C.NOMBRE_CIUDAD = ? AND P.EXTENSION_HA > ?";

        int numParques = 0;

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, ciudad);
        sentencia.setDouble(2, extension);
        ResultSet resultado = sentencia.executeQuery();

        resultado.next();
        numParques = resultado.getInt(1);

        return numParques;
    }

    /**
     * Borra todos los parques por nombre de ciudad
     *
     * @param ciudad
     * @throws SQLException
     */
    public void borrarParque(String ciudad) throws SQLException {
        String consulta = "DELETE FROM PARQUES WHERE CIUDAD_ID = (SELECT CIUDAD_ID FROM CIUDADES " +
                "WHERE NOMBRE_CIUDAD = ?)";

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, ciudad);
        sentencia.executeUpdate();
    }

    /**
     * Muestra los datos de un parque, como su extension y la ciudad a la que pertenece
     *
     * @param nombre_parque
     * @return Devuelve los datos del parque
     * @throws SQLException
     */
    public ArrayList<String> mostrarDatosParque(String nombre_parque) throws SQLException {
        String consulta = "SELECT P.NOMBRE_PARQUE, C.NOMBRE_CIUDAD, P.EXTENSION_HA FROM PARQUES P " +
                "JOIN CIUDADES C ON (C.CIUDAD_ID = P.CIUDAD_ID) WHERE P.NOMBRE_PARQUE = ?";

        ArrayList<String> datosParque = new ArrayList<>();

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, nombre_parque);
        ResultSet resultado = sentencia.executeQuery();

        while (resultado.next()) {
            String parque = resultado.getString(1);
            String ciudad = resultado.getString(2);
            double extension = resultado.getDouble(3);

            datosParque.add("Parque: " + parque);
            datosParque.add("Ciudad: " + ciudad);
            datosParque.add("Extension: " + extension + " hectáreas");
        }

        return datosParque;
    }

    /**
     * Obtención del id del parque a partir del nombre del parque
     *
     * @param parque_id
     * @return Devuelve el id del parque
     * @throws SQLException
     */
    public String getParqueId(String parque_id) throws SQLException {
        String consulta = "SELECT PARQUE_ID FROM PARQUES WHERE NOMBRE_PARQUE = ?";

        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);
        sentencia.setString(1, parque_id);
        ResultSet resultado = sentencia.executeQuery();

        resultado.next();
        String idParque = resultado.getString(1);

        return idParque;
    }

    /**
     * Modifica el nombre del parque, su extension y ciudad.
     *
     * @param actualizacionParque
     * @param actualizacionCiudad
     * @throws SQLException
     */
    public void actualizarParque(Parque actualizacionParque, Ciudad actualizacionCiudad) throws SQLException {
        String consulta = "UPDATE PARQUES SET NOMBRE_PARQUE = ?, EXTENSION_HA = ?, CIUDAD_ID = " +
                "(SELECT CIUDAD_ID FROM CIUDADES WHERE NOMBRE_CIUDAD = ?) WHERE PARQUE_ID = ?";


        PreparedStatement sentencia_parque = conexion.getConexion().prepareStatement(consulta);
        sentencia_parque.setString(1, actualizacionParque.getNombre_parque());
        sentencia_parque.setDouble(2, actualizacionParque.getExtension_ha());
        sentencia_parque.setString(3, actualizacionCiudad.getNombre_ciudad());
        sentencia_parque.setString(4, actualizacionParque.getParque_id());

        sentencia_parque.executeUpdate();
    }

    /**
     * Registro de un parque introduciendo su ID, el id de la ciudad, el nombre del parque y su extensión
     *
     * @param registro
     * @throws SQLException
     */
    public void registrarParque(Parque registro) throws SQLException {

        String consulta = "INSERT INTO PARQUES VALUES (?,?,?,?)";
        PreparedStatement sentencia = conexion.getConexion().prepareStatement(consulta);

        sentencia.setString(1, registro.getParque_id());
        sentencia.setString(2, registro.getCiudad_id());
        sentencia.setString(3, registro.getNombre_parque());
        sentencia.setDouble(4, registro.getExtension_ha());
        sentencia.executeUpdate();
    }
}
