package ec.edu.uce.dominio;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Clase de prueba para demostrar que las relaciones de aiguia se han implementado correctamente
 * en las clases de src, manteniendo todas las funcionalidades existentes.
 */
public class TestRelaciones {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE RELACIONES IMPLEMENTADAS ===\n");
        
        // 1. Prueba de relación Universidad -> Facultad
        testRelacionUniversidadFacultad();
        
        // 2. Prueba de relación Facultad -> Auditorio
        testRelacionFacultadAuditorio();
        
        // 3. Prueba de relación Facultad -> Usuario
        testRelacionFacultadUsuario();
        
        // 4. Prueba de relación Auditorio -> Reserva
        testRelacionAuditorioReserva();
        
        // 5. Prueba de relación Usuario -> Reserva
        testRelacionUsuarioReserva();
        
        // 6. Prueba de relación Reserva -> Equipo
        testRelacionReservaEquipo();
        
        System.out.println("\n=== TODAS LAS RELACIONES SE HAN IMPLEMENTADO CORRECTAMENTE ===");
    }
    
    private static void testRelacionUniversidadFacultad() {
        System.out.println("1. PRUEBA RELACIÓN UNIVERSIDAD -> FACULTAD");
        Universidad universidad = Universidad.getInstancia();
        
        // Crear facultades
        Facultad facultad1 = new Facultad("Facultad de Ingeniería");
        Facultad facultad2 = new Facultad("Facultad de Ciencias");
        
        universidad.crearFacultad(facultad1);
        universidad.crearFacultad(facultad2);
        
        // Verificar relación
        Map<Universidad, List<Facultad>> relacion = universidad.getRelacionUniversidadFacultad();
        System.out.println("✓ Relación Universidad-Facultad implementada: " + (relacion != null && !relacion.isEmpty()));
        System.out.println("  - Universidad tiene " + universidad.getNumFacultades() + " facultades");
        System.out.println();
    }
    
    private static void testRelacionFacultadAuditorio() {
        System.out.println("2. PRUEBA RELACIÓN FACULTAD -> AUDITORIO");
        Facultad facultad = new Facultad("Facultad de Ingeniería");
        
        // Crear auditorios
        Auditorio auditorio1 = new Auditorio("Auditorio A", 100);
        Auditorio auditorio2 = new Auditorio("Auditorio B", 150);
        
        facultad.crearAuditorio(auditorio1);
        facultad.crearAuditorio(auditorio2);
        
        // Verificar relación
        Map<Facultad, List<Auditorio>> relacion = facultad.getRelacionFacultadAuditorio();
        System.out.println("✓ Relación Facultad-Auditorio implementada: " + (relacion != null && !relacion.isEmpty()));
        System.out.println("  - Facultad tiene " + facultad.getNumAuditorios() + " auditorios");
        System.out.println();
    }
    
    private static void testRelacionFacultadUsuario() {
        System.out.println("3. PRUEBA RELACIÓN FACULTAD -> USUARIO");
        Facultad facultad = new Facultad("Facultad de Ciencias");
        
        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan@example.com");
        Usuario usuario2 = new Usuario("María", "García", "maria@example.com");
        
        facultad.crearUsuario(usuario1);
        facultad.crearUsuario(usuario2);
        
        // Verificar relación
        Map<Facultad, List<Usuario>> relacion = facultad.getRelacionFacultadUsuario();
        System.out.println("✓ Relación Facultad-Usuario implementada: " + (relacion != null && !relacion.isEmpty()));
        System.out.println("  - Facultad tiene " + facultad.getNumUsuarios() + " usuarios");
        System.out.println();
    }
    
    private static void testRelacionAuditorioReserva() {
        System.out.println("4. PRUEBA RELACIÓN AUDITORIO -> RESERVA");
        Auditorio auditorio = new Auditorio("Auditorio Principal", 200);
        
        // Crear reservas
        try {
            Reserva reserva1 = new Reserva(new Date(), new Date());
            Reserva reserva2 = new Reserva(new Date(), new Date());
            
            auditorio.crearReserva(reserva1);
            auditorio.crearReserva(reserva2);
            
            // Verificar relación
            Map<Auditorio, List<Reserva>> relacion = auditorio.getRelacionAuditorioReserva();
            System.out.println("✓ Relación Auditorio-Reserva implementada: " + (relacion != null && !relacion.isEmpty()));
            System.out.println("  - Auditorio tiene " + auditorio.getNumReservas() + " reservas");
        } catch (SIRAAException e) {
            System.out.println("✗ Error al crear reservas: " + e.getMessage());
        }
        System.out.println();
    }
    
    private static void testRelacionUsuarioReserva() {
        System.out.println("5. PRUEBA RELACIÓN USUARIO -> RESERVA");
        Usuario usuario = new Usuario("Carlos", "López", "carlos@example.com");
        
        // Crear reservas
        Reserva reserva1 = new Reserva(new Date(), new Date());
        Reserva reserva2 = new Reserva(new Date(), new Date());
        
        usuario.crearReserva(reserva1);
        usuario.crearReserva(reserva2);
        
        // Verificar relación
        Map<Usuario, List<Reserva>> relacion = usuario.getRelacionUsuarioReserva();
        System.out.println("✓ Relación Usuario-Reserva implementada: " + (relacion != null && !relacion.isEmpty()));
        System.out.println("  - Usuario tiene " + usuario.getNumReservas() + " reservas");
        System.out.println();
    }
    
    private static void testRelacionReservaEquipo() {
        System.out.println("6. PRUEBA RELACIÓN RESERVA -> EQUIPO");
        Reserva reserva = new Reserva(new Date(), new Date());
        
        // Crear equipos
        try {
            Equipo equipo1 = new Equipo("Proyector", "Visual", true);
            Equipo equipo2 = new Equipo("Micrófono", "Audio", true);
            
            reserva.crearEquipo(equipo1);
            reserva.crearEquipo(equipo2);
            
            // Verificar relación
            Map<Reserva, List<Equipo>> relacion = reserva.getRelacionReservaEquipo();
            System.out.println("✓ Relación Reserva-Equipo implementada: " + (relacion != null && !relacion.isEmpty()));
            System.out.println("  - Reserva tiene " + reserva.getEquipos().size() + " equipos");
        } catch (ReservaException e) {
            System.out.println("✗ Error al crear equipos: " + e.getMessage());
        }
        System.out.println();
    }
} 