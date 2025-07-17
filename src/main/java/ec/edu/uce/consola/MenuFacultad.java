package ec.edu.uce.consola;

import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Auditorio;
import ec.edu.uce.datos.FacultadDAO;
import ec.edu.uce.datos.FacultadDAOMemoriaImp;

import java.util.Scanner;

public class MenuFacultad {
    private final FacultadDAO facultadDAO;
    private final Usuario usuarioActual;
    private final Scanner scanner;

    public MenuFacultad(Usuario usuarioActual) {
        this.facultadDAO = new FacultadDAOMemoriaImp();
        this.usuarioActual = usuarioActual;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = obtenerOpcion();
            procesarOpcion(opcion);
        } while (opcion != 0);
    }

    private void mostrarOpciones() {
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "GESTIONAR FACULTADES");
        System.out.printf("%s%n", "=".repeat(50));
        System.out.printf("%-5s%-30s%n", "[1]", "Crear Facultad");
        System.out.printf("%-5s%-30s%n", "[2]", "Listar Facultades");
        System.out.printf("%-5s%-30s%n", "[3]", "Buscar Facultad");
        System.out.printf("%-5s%-30s%n", "[4]", "Editar Facultad");
        System.out.printf("%-5s%-30s%n", "[5]", "Eliminar Facultad");
        System.out.printf("%-5s%-30s%n", "[0]", "Volver al Menú Principal");
        System.out.printf("%s%n", "─".repeat(50));
        System.out.printf("%-5s", ">: ");
    }

    private int obtenerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                crearFacultad();
                break;
            case 2:
                listarFacultades();
                break;
            case 3:
                buscarFacultad();
                break;
            case 4:
                editarFacultad();
                break;
            case 5:
                eliminarFacultad();
                break;
            case 0:
                System.out.println("👋 Volviendo al menú principal...");
                break;
            default:
                System.out.println("❌ Opción no válida. Por favor, intente nuevamente");
        }
    }

    private void crearFacultad() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-20s%s%n", "", "CREAR FACULTAD");
        System.out.printf("%s%n", "─".repeat(50));

        System.out.print("Ingrese el nombre de la facultad: ");
        String nombre = scanner.nextLine().trim();

        if (nombre.isEmpty()) {
            System.out.println("[!] El nombre de la facultad no puede estar vacío");
            return;
        }

        // Verificar si ya existe una facultad con ese nombre
        Facultad[] facultades = facultadDAO.consultarFacultades();
        for (Facultad f : facultades) {
            if (f != null && f.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println("[!] Ya existe una facultad con ese nombre");
                return;
            }
        }

        Facultad nuevaFacultad = new Facultad(nombre);
        facultadDAO.crear(nuevaFacultad);
        System.out.println("✅ Facultad creada exitosamente: " + nuevaFacultad.getNombre());
    }

    private void listarFacultades() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-20s%s%n", "", "LISTAR FACULTADES");
        System.out.printf("%s%n", "─".repeat(50));

        Facultad[] facultades = facultadDAO.consultarFacultades();
        
        if (facultades.length == 0) {
            System.out.println("[!] No hay facultades registradas en el sistema");
            return;
        }

        System.out.println("Total de facultades: " + facultades.length);
        System.out.println();
        
        for (int i = 0; i < facultades.length; i++) {
            if (facultades[i] != null) {
                System.out.println("=== FACULTAD " + (i + 1) + " ===");
                System.out.println(facultades[i]);
                System.out.println();
            }
        }
    }

    private void buscarFacultad() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-20s%s%n", "", "BUSCAR FACULTAD");
        System.out.printf("%s%n", "─".repeat(50));

        System.out.print("Ingrese el código de la facultad a buscar: ");
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println("[!] El código de la facultad no puede estar vacío");
            return;
        }

        Facultad facultad = facultadDAO.buscarPorCodigo(codigo);
        
        if (facultad != null) {
            System.out.println("✅ Facultad encontrada:");
            System.out.println(facultad);
        } else {
            System.out.println("[!] No se encontró una facultad con el código: " + codigo);
        }
    }

    private void editarFacultad() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-20s%s%n", "", "EDITAR FACULTAD");
        System.out.printf("%s%n", "─".repeat(50));

        System.out.print("Ingrese el código de la facultad a editar: ");
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println("[!] El código de la facultad no puede estar vacío");
            return;
        }

        Facultad facultadExistente = facultadDAO.buscarPorCodigo(codigo);

        if (facultadExistente == null) {
            System.out.println("[!] No se encontró una facultad con el código: " + codigo);
            return;
        }

        System.out.println("Facultad actual:");
        System.out.println(facultadExistente);
        System.out.println();

        System.out.print("Ingrese el nuevo nombre de la facultad: ");
        String nuevoNombre = scanner.nextLine().trim();

        if (nuevoNombre.isEmpty()) {
            System.out.println("[!] El nombre de la facultad no puede estar vacío");
            return;
        }

        // Verificar si ya existe otra facultad con ese nombre
        Facultad[] facultades = facultadDAO.consultarFacultades();
        for (Facultad f : facultades) {
            if (f != null && !f.getCodigoFacultad().equals(codigo) && f.getNombre().equalsIgnoreCase(nuevoNombre)) {
                System.out.println("[!] Ya existe otra facultad con ese nombre");
                return;
            }
        }

        Facultad facultadEditada = new Facultad(nuevoNombre);
        
        // Copiar auditorios de la facultad existente
        for (Auditorio auditorio : facultadExistente.getAuditorios()) {
            facultadEditada.crearAuditorio(auditorio);
        }

        facultadDAO.editar(facultadEditada);
        System.out.println("✅ Facultad editada exitosamente");
    }

    private void eliminarFacultad() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-20s%s%n", "", "ELIMINAR FACULTAD");
        System.out.printf("%s%n", "─".repeat(50));

        System.out.print("Ingrese el código de la facultad a eliminar: ");
        String codigo = scanner.nextLine().trim();
        
        if (codigo.isEmpty()) {
            System.out.println("[!] El código de la facultad no puede estar vacío");
            return;
        }

        Facultad facultad = facultadDAO.buscarPorCodigo(codigo);
        
        if (facultad == null) {
            System.out.println("[!] No se encontró una facultad con el código: " + codigo);
            return;
        }

        System.out.println("Facultad a eliminar:");
        System.out.println(facultad);
        System.out.println();

        System.out.print("¿Está seguro de que desea eliminar esta facultad? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("y") || confirmacion.equals("yes")) {
            facultadDAO.eliminar(codigo);
            System.out.println("✅ Facultad eliminada exitosamente");
        } else {
            System.out.println("❌ Operación cancelada");
        }
    }
} 