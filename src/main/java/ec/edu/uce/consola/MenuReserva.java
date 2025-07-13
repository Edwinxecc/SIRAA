package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Reserva;
import ec.edu.uce.dominio.ReservaPrioritaria;
import ec.edu.uce.dominio.Auditorio;
import ec.edu.uce.dominio.Estado;
import java.time.LocalDateTime;
import java.util.Date;

public class MenuReserva extends MenuBase {
    private final Facultad facultad;
    private final Usuario usuarioActual;

    public MenuReserva(Facultad facultad, Usuario usuarioActual) {
        super();
        this.facultad = facultad;
        this.usuarioActual = usuarioActual;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ GESTIÓN DE RESERVAS");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Crear Reserva Normal");
            System.out.printf("%-5s%-30s%n", "[2]", "Crear Reserva Prioritaria");
            System.out.printf("%-5s%-30s%n", "[3]", "Ver Reservas");
            System.out.printf("%-5s%-30s%n", "[4]", "Editar Reserva");
            System.out.printf("%-5s%-30s%n", "[5]", "Eliminar Reserva");
            System.out.printf("%-5s%-30s%n", "[6]", "Buscar Reserva por ID");
            System.out.printf("%-5s%-30s%n", "[0]", "Volver al Menú Principal");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> crearReservaPrioritaria();
                case 3 -> mostrarReservas();
                case 4 -> editarReserva();
                case 5 -> eliminarReserva();
                case 6 -> buscarReservaPorId();
                case 0 -> {
                    System.out.printf("%-25s%n", "🔄 Regresando al menú principal...");
                    return;
                }
                default -> System.out.printf("%-25s%n", "❌ Opción no válida");
            }
        } while (opcion != 0);
    }

    private void crearReserva() {
        System.out.println("\n[1] Crear Reserva");

        // Mostrar auditorios disponibles
        System.out.println("\nAuditorios disponibles:");
        facultad.listarAuditorios();

        System.out.print("\nSeleccione el índice del auditorio: ");
        int indiceAuditorio = leerEnteroPositivo();
        ec.edu.uce.dominio.Auditorio[] auditorios = facultad.getAuditorios();
        if (indiceAuditorio < 0 || indiceAuditorio >= auditorios.length) {
            System.out.println("[!] Índice de auditorio inválido.");
            return;
        }

        System.out.println("\nIngrese fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaInicio = leerFecha();
        if (fechaInicio == null) return;

        System.out.println("Ingrese fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaFin = leerFecha();
        if (fechaFin == null) return;

        if (fechaFin.isBefore(fechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(fechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(fechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Crear nueva reserva
        Reserva nuevaReserva = new Reserva(inicio, fin);

        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevaReserva.nuevo(nuevaReserva);
        System.out.println(resultado);

        if (resultado.contains("creada correctamente")) {
            usuarioActual.crearReserva(nuevaReserva);
            System.out.println("\n[✓] Información de la reserva creada:");
            System.out.println("ID: " + nuevaReserva.getIdReserva());
            System.out.println("Código: " + nuevaReserva.getCodigoReserva());
            System.out.println("Estado: " + nuevaReserva.getEstado().getDescripcion());
            System.out.println("Fecha Inicio: " + nuevaReserva.getFechaInicio());
            System.out.println("Fecha Fin: " + nuevaReserva.getFechaFin());
            System.out.println("Tipo: " + nuevaReserva.tipoReserva());
        }
    }

    private void crearReservaPrioritaria() {
        System.out.println("\n[2] Crear Reserva Prioritaria");

        // Seleccionar nivel de prioridad
        System.out.println("\nSeleccione el nivel de prioridad:");
        System.out.println("[1] Prioridad Baja");
        System.out.println("[2] Prioridad Media");
        System.out.println("[3] Prioridad Alta");
        System.out.println("[4] Prioridad Urgente");
        System.out.print(">: ");

        int opcion = leerEnteroPositivo();
        Estado estado;

        switch (opcion) {
            case 1 -> estado = Estado.PRIORIDAD_BAJA;
            case 2 -> estado = Estado.PRIORIDAD_MEDIA;
            case 3 -> estado = Estado.PRIORIDAD_ALTA;
            case 4 -> estado = Estado.PRIORIDAD_URGENTE;
            default -> {
                System.out.println("[!] Opción no válida. Se usará Prioridad Baja por defecto.");
                estado = Estado.PRIORIDAD_BAJA;
            }
        }

        // Solicitar motivo de la prioridad
        System.out.print("\nIngrese el motivo de la prioridad: ");
        String motivo = entrada.nextLine();
        while (motivo.trim().isEmpty()) {
            System.out.print("[!] El motivo no puede estar vacío. Ingrese el motivo: ");
            motivo = entrada.nextLine();
        }

        System.out.println("\nIngrese fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaInicio = leerFecha();
        if (fechaInicio == null) return;

        System.out.println("Ingrese fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaFin = leerFecha();
        if (fechaFin == null) return;

        if (fechaFin.isBefore(fechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(fechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(fechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Crear la reserva prioritaria
        ReservaPrioritaria nuevaReserva = new ReservaPrioritaria(inicio, fin, estado, motivo);

        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevaReserva.nuevo(nuevaReserva);
        System.out.println(resultado);

        if (resultado.contains("creada correctamente")) {
            usuarioActual.crearReserva(nuevaReserva);
            System.out.println("\n[✓] Información de la reserva prioritaria creada:");
            System.out.println("ID: " + nuevaReserva.getIdReserva());
            System.out.println("Código: " + nuevaReserva.getCodigoReserva());
            System.out.println("Estado: " + nuevaReserva.getEstado().getDescripcion());
            System.out.println("Nivel de Prioridad: " + nuevaReserva.getNivelPrioridad());
            System.out.println("Motivo: " + nuevaReserva.getMotivoPrioridad());
            System.out.println("Requiere Aprobación: " + (nuevaReserva.requiereAprobacion() ? "Sí" : "No"));
            System.out.println("Tipo: " + nuevaReserva.tipoReserva());
        }
    }

    private void mostrarReservas() {
        System.out.println("\n[3] Ver Reservas");
        usuarioActual.inicializar();
        java.util.List<ec.edu.uce.dominio.Reserva> reservas = new java.util.ArrayList<>(java.util.Arrays.asList(usuarioActual.getReservas()));
        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para mostrar.");
            return;
        }
        System.out.println("¿Por qué criterio desea ordenar las reservas?");
        System.out.println("[1] ID (orden natural)");
        System.out.println("[2] Fecha de inicio");
        System.out.println("[3] Fecha de fin");
        System.out.println("[4] Estado");
        System.out.println("[5] Número de equipos");
        System.out.print(">: ");
        int criterio = leerEnteroPositivo();
        switch (criterio) {
            case 2 -> reservas.sort(new ec.edu.uce.dominio.OrdenarReservaFechaInicio());
            case 3 -> reservas.sort(new ec.edu.uce.dominio.OrdenarReservaFechaFin());
            case 4 -> reservas.sort(new ec.edu.uce.dominio.OrdenarReservaEstado());
            case 5 -> reservas.sort(new ec.edu.uce.dominio.OrdenarReservaNumEquipos());
            default -> java.util.Collections.sort(reservas);
        }
        System.out.println("=== RESERVAS DEL USUARIO ORDENADAS ===");
        for (ec.edu.uce.dominio.Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private void editarReserva() {
        System.out.println("\n[4] Editar Reserva");
        Reserva[] reservas = usuarioActual.getReservas();

        if (reservas.length == 0) {
            System.out.println("[!] No hay reservas para editar.");
            return;
        }

        System.out.println("=== LISTA DE RESERVAS ===");
        for (int i = 0; i < reservas.length; i++) {
            System.out.println("[" + i + "] " + reservas[i]);
        }

        System.out.print("\nSeleccione el índice de la reserva a editar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= reservas.length) {
            System.out.println("[!] Índice de reserva inválido.");
            return;
        }

        Reserva reservaAEditar = reservas[indice];
        System.out.println("\nEditando reserva: " + reservaAEditar);

        System.out.println("\nIngrese nueva fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaInicio = leerFecha();
        if (nuevaFechaInicio == null) return;

        System.out.println("Ingrese nueva fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaFin = leerFecha();
        if (nuevaFechaFin == null) return;

        if (nuevaFechaFin.isBefore(nuevaFechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(nuevaFechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(nuevaFechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Crear reserva actualizada
        Reserva reservaActualizada = new Reserva(inicio, fin);
        reservaActualizada.setEstado(reservaAEditar.getEstado());

        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = reservaAEditar.editar(reservaActualizada);
        System.out.println(resultado);

        if (resultado.contains("editada correctamente")) {
            usuarioActual.actualizarReserva(reservaAEditar.getCodigoReserva(), reservaActualizada);
            System.out.println("[✓] Reserva actualizada correctamente.");
        }
    }

    private void eliminarReserva() {
        System.out.println("\n[5] Eliminar Reserva");
        Reserva[] reservas = usuarioActual.getReservas();

        if (reservas.length == 0) {
            System.out.println("[!] No hay reservas para eliminar.");
            return;
        }

        System.out.println("=== LISTA DE RESERVAS ===");
        for (int i = 0; i < reservas.length; i++) {
            System.out.println("[" + i + "] " + reservas[i]);
        }

        System.out.print("\nSeleccione el índice de la reserva a eliminar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= reservas.length) {
            System.out.println("[!] Índice inválido.");
            return;
        }

        Reserva reservaAEliminar = reservas[indice];
        System.out.println("¿Está seguro de eliminar la reserva: " + reservaAEliminar + "? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();

        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            // Usar el método de la interfaz IAdministrarCRUD
            String resultado = reservaAEliminar.borrar(reservaAEliminar);
            System.out.println(resultado);

            if (resultado.contains("eliminada correctamente")) {
                usuarioActual.eliminarReserva(reservaAEliminar.getCodigoReserva());
                System.out.println("[✓] Reserva eliminada correctamente.");
            }
        } else {
            System.out.println("[!] Operación cancelada.");
        }
    }

    private void buscarReservaPorId() {
        System.out.println("\n[6] Buscar Reserva por ID");
        System.out.print("Ingrese el ID de la reserva: ");
        int id = leerEnteroPositivo();

        Reserva[] reservas = usuarioActual.getReservas();
        Reserva reservaEncontrada = null;

        for (Reserva reserva : reservas) {
            if (reserva.getIdReserva() == id) {
                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null) {
            System.out.println("\n[✓] Reserva encontrada:");
            System.out.println(reservaEncontrada);
        } else {
            System.out.println("[!] No se encontró una reserva con el ID: " + id);
        }
    }

    // Método para obtener el usuario actual
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}