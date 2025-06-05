package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Reserva;
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
            System.out.println("\n--- MENÚ GESTIÓN DE RESERVAS ---");
            System.out.println("[1] Crear Reserva Normal");
            System.out.println("[2] Crear Reserva Prioritaria");
            System.out.println("[3] Ver Reservas");
            System.out.println("[4] Editar Reserva");
            System.out.println("[5] Eliminar Reserva");
            System.out.println("[0] Volver al Menú Principal");
            System.out.print(">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> crearReservaPrioritaria();
                case 3 -> mostrarReservas();
                case 4 -> editarReserva();
                case 5 -> eliminarReserva();
                case 0 -> {
                    System.out.println("Regresando al menú principal...");
                    return;
                }
                default -> System.out.println("[!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void crearReserva() {
        System.out.println("\n[1] Crear Reserva");
        
        // Mostrar auditorios disponibles
        System.out.println("\nAuditorios disponibles:");
        facultad.listarAuditorios();
        
        System.out.print("\nSeleccione el índice del auditorio: ");
        int indiceAuditorio = leerEnteroPositivo() - 1;

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

        usuarioActual.crearReserva();
        int indiceReserva = usuarioActual.getReservas().length - 1;
        Reserva nuevaReserva = usuarioActual.getReservas()[indiceReserva];
        nuevaReserva.setFechaInicio(inicio);
        nuevaReserva.setFechaFin(fin);

        System.out.println("[✓] Reserva creada exitosamente.");
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

        // Crear la reserva prioritaria
        usuarioActual.crearReservaPrioritaria(estado, motivo);
        System.out.println("[✓] Reserva prioritaria creada exitosamente.");
    }

    private void mostrarReservas() {
        System.out.println("\n[3] Ver Reservas");
        System.out.println(usuarioActual.listarReservas());
    }

    private void editarReserva() {
        System.out.println("\n[4] Editar Reserva");
        System.out.println(usuarioActual.listarReservas());
        
        System.out.print("\nSeleccione el índice de la reserva a editar: ");
        int indice = leerEnteroPositivo() - 1;

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

        usuarioActual.actualizarReserva(indice, usuarioActual.getReservas()[indice].getIdReserva(), inicio, fin);
        System.out.println("Reserva actualizada.");
    }

    private void eliminarReserva() {
        System.out.println("\n[5] Eliminar Reserva");
        System.out.println(usuarioActual.listarReservas());
        
        System.out.print("\nSeleccione el índice de la reserva a eliminar: ");
        int indice = leerEnteroPositivo() - 1;
        
        usuarioActual.eliminarReserva(indice);
        System.out.println("Reserva eliminada.");
    }
} 