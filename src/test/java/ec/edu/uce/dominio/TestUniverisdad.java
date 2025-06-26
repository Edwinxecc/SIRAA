package ec.edu.uce.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class TestUniverisdad { // El nombre de la clase corregido a TestUniversidad (antes TestUniverisdad)
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Universidad (Singleton) ---");

        // 1. Obtención de la instancia de Universidad (Patrón Singleton)
        // Se obtiene la única instancia de la Universidad utilizando el método estático getInstancia.
        // Se le da una capacidad inicial de 5 para las facultades.
        Universidad uni = Universidad.getInstancia();
        System.out.println("Instancia de Universidad obtenida: " + uni);
        System.out.println("-----------------------------------");

        // 2. Creación y gestión de Facultades
        System.out.println("--- Prueba de CRUD de Facultades ---");

        // Crear facultades
        System.out.println("Creando facultades...");
        Facultad fac1 = new Facultad("Civil", 167);
        uni.crearFacultad(fac1); // Crea una facultad con nombre y capacidad
        uni.crearFacultad(new Facultad("Ingeniería", 100)); // Crea otra facultad
        uni.crearFacultad(); // Crea una facultad con constructor por defecto ("Sin nombre", 5)
        System.out.println("Facultades después de la creación:");
        System.out.println(uni.listarNombresFacultades());
        System.out.println("Número actual de facultades: " + uni.getNumFacultades());
        System.out.println("-----------------------------------");

        // Intentar crear una facultad duplicada (por nombre)
        System.out.println("Intentando crear una facultad duplicada (Civil)...");
        uni.crearFacultad(new Facultad("Civil", 200)); // Debería indicar que ya existe
        System.out.println("Facultades después de intentar duplicar:");
        System.out.println(uni.listarNombresFacultades());
        System.out.println("-----------------------------------");

        // Eliminar una facultad
        System.out.println("Eliminando la facultad en el índice 0 (Civil)...");
        uni.eliminarFacultad(0); // Elimina la primera facultad
        System.out.println("Facultades después de la eliminación:");
        System.out.println(uni.listarNombresFacultades());
        System.out.println("Número actual de facultades: " + uni.getNumFacultades());
        System.out.println("-----------------------------------");

        // Actualizar una facultad (ejemplo, actualizamos "Ingeniería" que ahora está en el índice 0)
        System.out.println("Actualizando el nombre de la facultad en el índice 0 (Ingeniería)...");
        uni.actualizarFacultad(0, "Facultad de Sistemas");
        System.out.println("Facultades después de la actualización:");
        System.out.println(uni.listarNombresFacultades());
        System.out.println("-----------------------------------");

        System.out.println("--- Fin del Test de Universidad ---");
    }
}