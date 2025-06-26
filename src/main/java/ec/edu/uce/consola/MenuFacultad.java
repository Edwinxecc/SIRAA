package ec.edu.uce.consola;

import ec.edu.uce.dominio.Facultad;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuFacultad extends MenuBase {
    private final Facultad facultad;

    public MenuFacultad(Facultad facultad) {
        super();
        this.facultad = facultad;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ GESTIONAR FACULTADES");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Crear Facultad");
            System.out.printf("%-5s%-30s%n", "[2]", "Mostrar Facultades");
            System.out.printf("%-5s%-30s%n", "[3]", "Editar Facultad");
            System.out.printf("%-5s%-30s%n", "[4]", "Eliminar Facultad");
            System.out.printf("%-5s%-30s%n", "[5]", "Buscar Facultad por ID");
            System.out.printf("%-5s%-30s%n", "[0]", "Salir");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-25s", "Selecciona una opción: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearFacultad();
                case 2 -> mostrarFacultades();
                case 3 -> editarFacultad();
                case 4 -> eliminarFacultad();
                case 5 -> buscarFacultadPorId();
                case 0 -> System.out.printf("%-25s%n", "🔄 Volviendo al menú principal...");
                default -> System.out.printf("%-25s%n", "❌ Opción no válida. Intenta nuevamente");
            }
        } while (opcion != 0);
    }

    private void crearFacultad() {
        System.out.println("\n[1] Crear Facultad");

        System.out.print("Nombre de la facultad: ");
        String nombre = entrada.nextLine();
        nombre = validacion.match(nombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);

        System.out.print("Cantidad inicial de auditorios: ");
        int cantidad = leerEnteroPositivo();

        // Crear nueva facultad
        Facultad nuevaFacultad = new Facultad(nombre, cantidad);
        
        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevaFacultad.nuevo(nuevaFacultad);
        System.out.println(resultado);
        
        if (resultado.contains("creada correctamente")) {
            System.out.println("\n[✓] Información de la facultad creada:");
            System.out.println("ID: " + nuevaFacultad.getIdFacultad());
            System.out.println("Código: " + nuevaFacultad.getCodigoFacultad());
            System.out.println("Nombre: " + nuevaFacultad.getNombre());
            System.out.println("Auditorios: " + nuevaFacultad.getNumAuditorios());
            System.out.println("Usuarios: " + nuevaFacultad.getNumUsuarios());
        }
    }

    private void mostrarFacultades() {
        System.out.println("\n[2] Mostrar Facultades");
        System.out.println("=== INFORMACIÓN DE LA FACULTAD ACTUAL ===");
        System.out.println(facultad);
        System.out.println("\n=== AUDITORIOS ===");
        List<ec.edu.uce.dominio.Auditorio> auditorios = new ArrayList<>(Arrays.asList(facultad.getAuditorios()));
        if (auditorios.isEmpty()) {
            System.out.println("[!] No hay auditorios registrados.");
        } else {
            for (ec.edu.uce.dominio.Auditorio a : auditorios) {
                System.out.println("Código: " + a.getCodigoAuditorio() + " | " + a);
            }
        }
        System.out.println("\n=== USUARIOS ===");
        List<ec.edu.uce.dominio.Usuario> usuarios = new ArrayList<>(Arrays.asList(facultad.getUsuarios()));
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay usuarios registrados.");
        } else {
            for (ec.edu.uce.dominio.Usuario u : usuarios) {
                System.out.println("Código: " + u.getCodigoUsuario() + " | " + u);
            }
        }
    }

    private void editarFacultad() {
        System.out.println("\n[3] Editar Facultad");
        System.out.println("Facultad actual: " + facultad);

        System.out.print("Nuevo nombre (actual: " + facultad.getNombre() + "): ");
        String nuevoNombre = entrada.nextLine();
        if (!nuevoNombre.trim().isEmpty()) {
            nuevoNombre = validacion.match(nuevoNombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);
        } else {
            nuevoNombre = facultad.getNombre();
        }

        // Crear facultad actualizada
        Facultad facultadActualizada = new Facultad(nuevoNombre, facultad.getNumAuditorios());
        facultadActualizada.setNumAuditorios(facultad.getNumAuditorios());
        
        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = facultad.editar(facultadActualizada);
        System.out.println(resultado);
        
        if (resultado.contains("editada correctamente")) {
            System.out.println("[✓] Facultad actualizada correctamente.");
        }
    }

    private void eliminarFacultad() {
        System.out.println("\n[4] Eliminar Facultad");
        System.out.println("Facultad actual: " + facultad);
        System.out.print("¿Está seguro de eliminar esta facultad? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            // Usar el método de la interfaz IAdministrarCRUD
            String resultado = facultad.borrar(facultad);
            System.out.println(resultado);
        } else {
            System.out.println("[!] Operación cancelada.");
        }
    }

    private void buscarFacultadPorId() {
        System.out.println("\n[5] Buscar Facultad por ID");
        System.out.print("Ingrese el ID de la facultad: ");
        int id = leerEnteroPositivo();
        
        Object resultado = facultad.buscarPorId(id);
        if (resultado != null && resultado instanceof Facultad) {
            Facultad facultadEncontrada = (Facultad) resultado;
            System.out.println("\n[✓] Facultad encontrada:");
            System.out.println(facultadEncontrada);
        } else {
            System.out.println("[!] No se encontró una facultad con el ID: " + id);
        }
    }
}