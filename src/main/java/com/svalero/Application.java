
//CLASE PRINCIPAL DONDE SE VA A EJECUTAR EL PROGRAMA, CREANDO INSTANCIAS DE DETERMINADAS CLASES
package com.svalero;

public class Application {
    public static void main(String[] args) {
        GestionParque gestion = new GestionParque();
        gestion.ejecutar();
    }
}
