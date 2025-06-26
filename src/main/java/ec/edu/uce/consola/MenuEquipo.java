package ec.edu.uce.consola;

import ec.edu.uce.dominio.Equipo;
import ec.edu.uce.dominio.Reserva;
import java.util.ArrayList;
import java.util.List;

public class MenuEquipo extends MenuBase {
    private final Reserva reservaActual;

    public MenuEquipo(Reserva reservaActual) {
        super();
        this.reservaActual = reservaActual;
    }

    public Reserva getReservaActual() {
        return reservaActual;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ ADMINISTRAR EQUIPOS");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Agregar equipo");
            System.out.printf("%-5s%-30s%n", "[2]", "Consultar equipos");
            System.out.printf("%-5s%-30s%n", "[3]", "Editar equipo");
            System.out.printf("%-5s%-30s%n", "[4]", "Eliminar equipo");
            System.out.printf("%-5s%-30s%n", "[5]", "Buscar equipo por ID");
            System.out.printf("%-5s%-30s%n", "[0]", "Volver al menú anterior");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> agregarEquipo();
                case 2 -> consultarEquipos();
                case 3 -> editarEquipo();
                case 4 -> eliminarEquipo();
                case 5 -> buscarEquipoPorId();
                case 0 -> System.out.printf("%-25s%n", "🔄 Volviendo al menú principal...");
                default -> System.out.printf("%-25s%n", "❌ Opción inválida");
            }
        } while (opcion != 0);
    }

    private void agregarEquipo() {
        System.out.println("\n[1] Agregar Equipo");

        System.out.print("Ingrese nombre del equipo: ");
        String nombre = entrada.nextLine();
        nombre = validacion.match(nombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);

        System.out.print("Ingrese tipo de equipo: ");
        String categoria = entrada.nextLine();
        categoria = validacion.match(categoria, "categoría", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "La categoría solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);

        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = leerBooleano();

        // Crear nuevo equipo
        Equipo nuevoEquipo = new Equipo(nombre, categoria, disponible);
        
        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevoEquipo.nuevo(nuevoEquipo);
        System.out.println(resultado);
        
        if (resultado.contains("creado correctamente")) {
            reservaActual.crearEquipo(nuevoEquipo);
            System.out.println("\n[✓] Información del equipo creado:");
            System.out.println("ID: " + nuevoEquipo.getIdEquipo());
            System.out.println("Código: " + nuevoEquipo.getCodigoEquipo());
            System.out.println("Nombre: " + nuevoEquipo.getNombre());
            System.out.println("Categoría: " + nuevoEquipo.getCategoria());
            System.out.println("Disponible: " + (nuevoEquipo.getDisponibilidad() ? "Sí" : "No"));
            System.out.println("Estado: " + nuevoEquipo.getEstado().getDescripcion());
        }
    }

    private void consultarEquipos() {
        System.out.println("\n[2] Consultar Equipos");
        String listado = reservaActual.listarEquipos();
        if (listado.contains("No hay equipos")) {
            System.out.println("[!] No hay equipos para mostrar.");
        } else {
            System.out.println("=== EQUIPOS DE LA RESERVA ===");
            System.out.println(listado);
        }
    }

    private void editarEquipo() {
        System.out.println("\n[3] Editar Equipo");
        List<Equipo> equipos = new ArrayList<>(List.of(reservaActual.getEquipos()));
        if (equipos.isEmpty()) {
            System.out.println("[!] No hay equipos para editar.");
            return;
        }
        System.out.println("=== LISTA DE EQUIPOS ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println("[" + i + "] " + equipos.get(i) + " (Código: " + equipos.get(i).getCodigoEquipo() + ")");
        }
        System.out.print("\nSeleccione el índice del equipo a editar: ");
        int indice = leerEnteroPositivo();
        if (indice < 0 || indice >= equipos.size()) {
            System.out.println("[!] Índice inválido.");
            return;
        }
        Equipo equipoAEditar = equipos.get(indice);
        System.out.println("\nEditando equipo: " + equipoAEditar);
        System.out.print("Nuevo nombre (actual: " + equipoAEditar.getNombre() + "): ");
        String nuevoNombre = entrada.nextLine();
        if (!nuevoNombre.trim().isEmpty()) {
            nuevoNombre = validacion.match(nuevoNombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);
        } else {
            nuevoNombre = equipoAEditar.getNombre();
        }
        System.out.print("Nueva categoría (actual: " + equipoAEditar.getCategoria() + "): ");
        String nuevaCategoria = entrada.nextLine();
        if (!nuevaCategoria.trim().isEmpty()) {
            nuevaCategoria = validacion.match(nuevaCategoria, "categoría", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "La categoría solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);
        } else {
            nuevaCategoria = equipoAEditar.getCategoria();
        }
        System.out.print("¿Está disponible? (actual: " + (equipoAEditar.getDisponibilidad() ? "Sí" : "No") + ") (true/false): ");
        String disponibilidadStr = entrada.nextLine();
        boolean nuevaDisponibilidad = equipoAEditar.getDisponibilidad();
        if (!disponibilidadStr.trim().isEmpty()) {
            nuevaDisponibilidad = disponibilidadStr.toLowerCase().equals("true");
        }
        Equipo equipoActualizado = new Equipo(nuevoNombre, nuevaCategoria, nuevaDisponibilidad);
        equipoActualizado.setEstado(equipoAEditar.getEstado());
        String resultado = equipoAEditar.editar(equipoActualizado);
        System.out.println(resultado);
        if (resultado.contains("editado correctamente")) {
            reservaActual.actualizarEquipo(equipoAEditar.getCodigoEquipo(), equipoActualizado);
            System.out.println("[✓] Equipo actualizado correctamente.");
        }
    }

    private void eliminarEquipo() {
        System.out.println("\n[4] Eliminar Equipo");
        List<Equipo> equipos = new ArrayList<>(List.of(reservaActual.getEquipos()));
        if (equipos.isEmpty()) {
            System.out.println("[!] No hay equipos para eliminar.");
            return;
        }
        System.out.println("=== LISTA DE EQUIPOS ===");
        for (int i = 0; i < equipos.size(); i++) {
            System.out.println("[" + i + "] " + equipos.get(i) + " (Código: " + equipos.get(i).getCodigoEquipo() + ")");
        }
        System.out.print("\nSeleccione el índice del equipo a eliminar: ");
        int indice = leerEnteroPositivo();
        if (indice < 0 || indice >= equipos.size()) {
            System.out.println("[!] Índice inválido.");
            return;
        }
        Equipo equipoAEliminar = equipos.get(indice);
        System.out.println("¿Está seguro de eliminar el equipo: " + equipoAEliminar + "? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            String resultado = equipoAEliminar.borrar(equipoAEliminar);
            System.out.println(resultado);
            if (resultado.contains("eliminado correctamente")) {
                String resultadoEliminacion = reservaActual.eliminarEquipo(equipoAEliminar.getCodigoEquipo());
                System.out.println(resultadoEliminacion);
            }
        } else {
            System.out.println("[!] Operación cancelada.");
        }
    }

    private void buscarEquipoPorId() {
        System.out.println("\n[5] Buscar Equipo por ID");
        System.out.print("Ingrese el ID del equipo: ");
        int id = leerEnteroPositivo();
        
        Equipo[] equipos = reservaActual.getEquipos();
        Equipo equipoEncontrado = null;
        
        for (Equipo equipo : equipos) {
            if (equipo.getIdEquipo() == id) {
                equipoEncontrado = equipo;
                break;
            }
        }
        
        if (equipoEncontrado != null) {
            System.out.println("\n[✓] Equipo encontrado:");
            System.out.println(equipoEncontrado);
        } else {
            System.out.println("[!] No se encontró un equipo con el ID: " + id);
        }
    }
} 