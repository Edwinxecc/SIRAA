package ec.edu.uce.Util;


import ec.edu.uce.dominio.*;
import java.util.List;

/**
 * Clase de utilidad para mostrar las relaciones entre entidades del sistema SIRAA.
 * Proporciona métodos para visualizar las asociaciones entre usuarios, facultades, auditorios y reservas.
 */
public class RelacionesUtil {

    /**
     * Muestra todas las relaciones de la universidad.
     * @param universidad La universidad a analizar.
     */
    public static void mostrarRelacionesUniversidad(Universidad universidad) {
        System.out.println("\n=== RELACIONES DE LA UNIVERSIDAD ===");
        System.out.println(universidad);
        
        List<Facultad> facultades = universidad.getFacultades();
        System.out.println("\nFacultades registradas: " + facultades.size());
        
        for (Facultad facultad : facultades) {
            mostrarRelacionesFacultad(facultad);
        }
    }

    /**
     * Muestra las relaciones de una facultad específica.
     * @param facultad La facultad a analizar.
     */
    public static void mostrarRelacionesFacultad(Facultad facultad) {
        System.out.println("\n--- RELACIONES DE LA FACULTAD: " + facultad.getNombre() + " ---");
        System.out.println(facultad);
        
        List<Usuario> usuarios = facultad.getUsuarios();
        List<Auditorio> auditorios = facultad.getAuditorios();
        
        System.out.println("\nUsuarios registrados: " + usuarios.size());
        for (Usuario usuario : usuarios) {
            System.out.println("  - " + usuario.getNombre() + " " + usuario.getApellido() + 
                             " (Reservas: " + usuario.getNumReservas() + ")");
        }
        
        System.out.println("\nAuditorios registrados: " + auditorios.size());
        for (Auditorio auditorio : auditorios) {
            System.out.println("  - " + auditorio.getNombre() + 
                             " (Capacidad: " + auditorio.getCapacidad() + 
                             ", Reservas: " + auditorio.getNumReservas() + ")");
        }
    }

    /**
     * Muestra las relaciones de un usuario específico.
     * @param usuario El usuario a analizar.
     */
    public static void mostrarRelacionesUsuario(Usuario usuario) {
        System.out.println("\n=== RELACIONES DEL USUARIO ===");
        System.out.println(usuario);
        
        List<Reserva> reservas = usuario.getReservas();
        System.out.println("\nReservas del usuario: " + reservas.size());
        
        for (Reserva reserva : reservas) {
            System.out.println("  - Reserva " + reserva.getCodigoReserva() + 
                             " | Auditorio: " + (reserva.getAuditorio() != null ? reserva.getAuditorio().getNombre() : "Sin asignar") +
                             " | Estado: " + reserva.getEstado().getDescripcion());
        }
    }

    /**
     * Muestra las relaciones de un auditorio específico.
     * @param auditorio El auditorio a analizar.
     */
    public static void mostrarRelacionesAuditorio(Auditorio auditorio) {
        System.out.println("\n=== RELACIONES DEL AUDITORIO ===");
        System.out.println(auditorio);
        
        List<Reserva> reservas = auditorio.getReservas();
        System.out.println("\nReservas del auditorio: " + reservas.size());
        
        for (Reserva reserva : reservas) {
            System.out.println("  - Reserva " + reserva.getCodigoReserva() + 
                             " | Usuario: " + (reserva.getUsuario() != null ? 
                                               reserva.getUsuario().getNombre() + " " + reserva.getUsuario().getApellido() : "Sin asignar") +
                             " | Estado: " + reserva.getEstado().getDescripcion());
        }
    }

    /**
     * Muestra las relaciones de una reserva específica.
     * @param reserva La reserva a analizar.
     */
    public static void mostrarRelacionesReserva(Reserva reserva) {
        System.out.println("\n=== RELACIONES DE LA RESERVA ===");
        System.out.println(reserva);
        
        System.out.println("\nDetalles de la reserva:");
        System.out.println("  - Usuario: " + (reserva.getUsuario() != null ? 
                                             reserva.getUsuario().getNombre() + " " + reserva.getUsuario().getApellido() : "Sin asignar"));
        System.out.println("  - Auditorio: " + (reserva.getAuditorio() != null ? 
                                               reserva.getAuditorio().getNombre() : "Sin asignar"));
        System.out.println("  - Facultad: " + (reserva.getAuditorio() != null && reserva.getAuditorio().getFacultad() != null ? 
                                              reserva.getAuditorio().getFacultad().getNombre() : "Sin asignar"));
        
        List<Equipo> equipos = reserva.getEquipos();
        System.out.println("  - Equipos asignados: " + equipos.size());
        for (Equipo equipo : equipos) {
            System.out.println("    * " + equipo.getNombre() + " (" + equipo.getCategoria() + ")");
        }
    }

    /**
     * Muestra un resumen de todas las relaciones del sistema.
     * @param universidad La universidad a analizar.
     */
    public static void mostrarResumenRelaciones(Universidad universidad) {
        System.out.println("\n=== RESUMEN DE RELACIONES DEL SISTEMA ===");
        
        List<Facultad> facultades = universidad.getFacultades();
        int totalUsuarios = 0;
        int totalAuditorios = 0;
        int totalReservas = 0;
        
        for (Facultad facultad : facultades) {
            totalUsuarios += facultad.getNumUsuarios();
            totalAuditorios += facultad.getNumAuditorios();
            
            for (Auditorio auditorio : facultad.getAuditorios()) {
                totalReservas += auditorio.getNumReservas();
            }
        }
        
        System.out.println("Total de facultades: " + facultades.size());
        System.out.println("Total de usuarios: " + totalUsuarios);
        System.out.println("Total de auditorios: " + totalAuditorios);
        System.out.println("Total de reservas: " + totalReservas);
        
        System.out.println("\nDistribución por facultad:");
        for (Facultad facultad : facultades) {
            System.out.println("  - " + facultad.getNombre() + ": " + 
                             facultad.getNumUsuarios() + " usuarios, " + 
                             facultad.getNumAuditorios() + " auditorios");
        }
    }
} 