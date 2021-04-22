
//CLASE ENCARGADA DE GESTIONAR EL MENÚ PARA PREGUNTAR AL USUARIO QUE OPCIÓN ELEGIR Y QUE MÉTODO EJECUTAR
package com.svalero;

import com.svalero.dao.CiudadDAO;
import com.svalero.dao.Conexion;
import com.svalero.dao.ParqueDAO;
import com.svalero.domain.Ciudad;
import com.svalero.domain.Parque;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestionParque {
    private final Scanner sc;
    private boolean salirPrograma;
    private final ParqueDAO parqueDAO;
    private final CiudadDAO ciudadDAO;

    public GestionParque() {
        Conexion conexion = new Conexion();
        conexion.conectar();
        parqueDAO = new ParqueDAO(conexion);
        ciudadDAO = new CiudadDAO(conexion);

        sc = new Scanner(System.in);
        salirPrograma = false;
    }

    public void ejecutar() {
        do {
            System.out.println("------------------------");
            System.out.println("Elige una opción:");
            System.out.println("1. Listar los parques por nombre de ciudad");
            System.out.println("2. Listar los parques por nombre de CCAA");
            System.out.println("3. Registrar parque por nombre de ciudad");
            System.out.println("4. Actualizar información de parque ");
            System.out.println("5. Buscar parque");
            System.out.println("6. Borrar todos los parques de una ciudad por nombre");
            System.out.println("7. Devolver el número de parques de una determinada ciudad que tengan \n" +
                    "una extensión individual mayor que la que desees");
            System.out.println("8. Listar el nombre de todas las ciudades que contengan parques cuya suma \n" +
                    "total de su extensión sea mayor a la introducida");
            System.out.println("0. Salir del programa");
            System.out.println("------------------------");

            String opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    listarParques_PorCiudad();
                    break;
                case "2":
                    listarParques_PorCCAA();
                    break;
                case "3":
                    registrarParque();
                    break;
                case "4":
                    actualizarParque();
                    break;
                case "5":
                    buscarParque();
                    break;
                case "6":
                    borrarParque();
                    break;
                case "7":
                    extension_mayor();
                    break;
                case "8":
                    listarCiudades();

                    break;
                case "0":
                    System.out.println("Saliendo del programa...");
                    salirPrograma = true;
                    break;
                default:
                    System.out.println("Error desconocido");
            }

        } while (!salirPrograma);
    }

    public void listarParques_PorCiudad() {
        System.out.println("Selecciona la ciudad donde quieras listar los parques");
        String ciudad = sc.nextLine();
        System.out.println("\nParques registrados en " + ciudad + ":");
        try {
            ArrayList<Parque> listadoParques = parqueDAO.listarParques_PorCiudad(ciudad);
            for (Parque parque : listadoParques) {
                System.out.println(parque);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void listarParques_PorCCAA() {
        System.out.println("Selecciona la comunidad autónoma donde quieras listar los parques");
        String ccaa = sc.nextLine();

        try {
            ArrayList<Parque> listadoParques = parqueDAO.listarParques_PorCCAA(ccaa);
            for (Parque parque : listadoParques) {
                System.out.println(parque);

            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void extension_mayor() {
        int numParques;
        System.out.println("Seleccione una ciudad");
        String ciudad = sc.nextLine();
        System.out.println("Seleccione una extension (en hectareas)");
        Double extension = sc.nextDouble();
        sc.nextLine();

        try {
            numParques = parqueDAO.extension_mayor(ciudad, extension);
            System.out.println("Hay " + numParques + " parque/s en " + ciudad + " con una extension mayor a " + extension + " hectareas");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void listarCiudades() {
        System.out.println("Introduce una extension (en hectareas)");
        Double extension = sc.nextDouble();
        sc.nextLine();

        try {
            ArrayList<Ciudad> listadoCiudades = ciudadDAO.listarCiudades(extension);
            for (Ciudad ciudad : listadoCiudades) {
                System.out.println(ciudad);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("ERROR");
        }
    }

    private void buscarParque() {
        System.out.println("Introduce el parque que quieres buscar");
        String nombre_parque = sc.nextLine();

        try {
            ArrayList<Parque> listadoParques = parqueDAO.buscarParque(nombre_parque);

            System.out.println("Se ha encontrado el siguiente/s parque/s: ");
            for (Parque parque : listadoParques) {
                System.out.println(parque);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.out.println("Error");
        }
    }

    private void borrarParque() {
        System.out.println("Selecciona una ciudad donde quieras borrar el parque");
        String ciudad = sc.nextLine();

        try {
            parqueDAO.borrarParque(ciudad);
            System.out.println("Parques borrados exitosamente");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public void actualizarParque() {
        System.out.println("Selecciona el parque que quieras actualizar");
        String nombre_parque = sc.nextLine();

        try {
            ArrayList<String> datosParque = parqueDAO.mostrarDatosParque(nombre_parque);

            System.out.println("-------------------------------------");
            for (String dato : datosParque) {
                System.out.println(dato);
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        Parque actualizacionParque = new Parque();
        Ciudad actualizacionCiudad = new Ciudad();

        System.out.println("\nIntroduce un nuevo parque: ");
        String nuevo_parque = sc.nextLine();
        actualizacionParque.setNombre_parque(nuevo_parque);

        System.out.println("Introduce una nueva ciudad: ");
        String nueva_ciudad = sc.nextLine();
        actualizacionCiudad.setNombre_ciudad(nueva_ciudad);

        System.out.println("Introduce una nueva extension (en hectareas): ");
        Double nueva_extension = sc.nextDouble();
        actualizacionParque.setExtension_ha(nueva_extension);
        sc.nextLine();

        try {
            String parque_id = parqueDAO.getParqueId(nombre_parque);
            actualizacionParque.setParque_id(parque_id);

            parqueDAO.actualizarParque(actualizacionParque, actualizacionCiudad);
            System.out.println("\nParque modificado correctamente\n");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }

    public void registrarParque() {

        System.out.println("Selecciona la ciudad donde quieras registrar el parque");
        String nombre_ciudad = sc.nextLine();

        try {
            Ciudad ciudad = new Ciudad();
            String ciudad_id = ciudadDAO.confirmarCiudad(nombre_ciudad);
            ciudad.setNombre_ciudad(nombre_ciudad);
            try {
                System.out.println("Introduce el identificador del parque");
                String parque_id = sc.nextLine();
                System.out.println("Introduce el nombre del parque");
                String nombre_parque = sc.nextLine();
                System.out.println("Introduce la extension del parque");
                double extension_ha = sc.nextDouble();
                sc.nextLine();

                Parque registro = new Parque();
                registro.setParque_id(parque_id);
                registro.setCiudad_id(ciudad_id);
                registro.setNombre_parque(nombre_parque);
                registro.setExtension_ha(extension_ha);

                parqueDAO.registrarParque(registro);
                System.out.println("Parque registrado correctamente\n");
            } catch (SQLException sqle) {

            }
        } catch (SQLException sqle) {
            System.out.println("La ciudad introducida no existe");

        }
    }
}





