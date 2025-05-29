package ec.edu.uce;

import ec.edu.uce.consola.Menu;
import ec.edu.uce.dominio.Facultad;

/**
 * Clase principal que inicia el sistema SIRAA.
 * Sistema Integrado de Reserva de Auditorios y Administración.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema SIRAA...");
        System.out.println("Sistema Integrado de Reserva de Auditorios y Administración");
        System.out.println("Universidad Central del Ecuador");
        System.out.println("=================================================");

        // Inicializar la facultad principal
        Facultad facultadPrincipal = new Facultad("Facultad de Ingeniería", 3);
        
        // Crear y mostrar el menú principal
        Menu menuPrincipal = new Menu(facultadPrincipal);
        menuPrincipal.menuDeInicio();
    }
}