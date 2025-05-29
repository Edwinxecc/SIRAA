package ec.edu.uce.consola;

import ec.edu.uce.dominio.Facultad;

public class MenuFacultad extends MenuBase {
    private final Facultad facultad;

    public MenuFacultad(Facultad facultad) {
        super();
        this.facultad = facultad;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ GESTIONAR FACULTADES ===");
            System.out.println("[1] Crear Facultad");
            System.out.println("[2] Mostrar Facultades");
            System.out.println("[3] Editar Facultad");
            System.out.println("[4] Eliminar Facultad");
            System.out.println("[0] Salir");
            System.out.print("Selecciona una opción: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearFacultad();
                case 2 -> mostrarFacultades();
                case 3 -> editarFacultad();
                case 4 -> eliminarFacultad();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("[!] Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 0);
    }

    private void crearFacultad() {
        System.out.println("\n[1] Crear Facultad");
        
        System.out.print("Nombre de la facultad: ");
        String nombre = entrada.nextLine();
        nombre = validacion.ValidacionTexto(nombre, "nombre");

        System.out.print("Cantidad de auditorios: ");
        int cantidad = leerEnteroPositivo();

        facultad.crearAuditorio();
        System.out.println("[✓] Facultad creada correctamente.");
    }

    private void mostrarFacultades() {
        System.out.println("\n[2] Mostrar Facultades");
        System.out.println(facultad.toString());
    }

    private void editarFacultad() {
        System.out.println("\n[3] Editar Facultad");
        
        System.out.print("Nuevo nombre: ");
        String nuevoNombre = entrada.nextLine();
        nuevoNombre = validacion.ValidacionTexto(nuevoNombre, "nombre");

        System.out.print("Nueva cantidad de auditorios: ");
        int nuevaCantidad = leerEnteroPositivo();

        facultad.setNombre(nuevoNombre);
        facultad.setNumAuditorios(nuevaCantidad);
        System.out.println("[✓] Facultad editada correctamente.");
    }

    private void eliminarFacultad() {
        System.out.println("\n[4] Eliminar Facultad");
        System.out.println("[!] Esta operación no está disponible en esta versión del sistema.");
    }
} 