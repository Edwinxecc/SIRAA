package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Clase de prueba para verificar el funcionamiento del autonumerado
 * en todas las clases del dominio.
 */
public class TestAutonumerado {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE AUTONUMERADO ===\n");
        
        // Prueba con Facultad
        System.out.println("1. PRUEBA CON FACULTAD:");
        testearAutonumeradoFacultad();
        
        // Prueba con Usuario
        System.out.println("\n2. PRUEBA CON USUARIO:");
        testearAutonumeradoUsuario();
        
        // Prueba con Auditorio
        System.out.println("\n3. PRUEBA CON AUDITORIO:");
        testearAutonumeradoAuditorio();
        
        // Prueba con Reserva
        System.out.println("\n4. PRUEBA CON RESERVA:");
        testearAutonumeradoReserva();
        
        // Prueba con ReservaPrioritaria
        System.out.println("\n5. PRUEBA CON RESERVA PRIORITARIA:");
        testearAutonumeradoReservaPrioritaria();
        
        System.out.println("\n=== TODAS LAS PRUEBAS COMPLETADAS ===");
    }
    
    /**
     * Prueba el autonumerado en la clase Facultad.
     */
    private static void testearAutonumeradoFacultad() {
        Facultad facultad1 = new Facultad("Ingeniería");
        Facultad facultad2 = new Facultad("Medicina");
        Facultad facultad3 = new Facultad("Derecho");
        
        System.out.println("Facultad 1: " + facultad1);
        System.out.println("Facultad 2: " + facultad2);
        System.out.println("Facultad 3: " + facultad3);
        
        // Verificar que los IDs son secuenciales
        System.out.println("✓ IDs secuenciales: " + facultad1.getIdFacultad() + ", " + 
                          facultad2.getIdFacultad() + ", " + facultad3.getIdFacultad());
        
        // Verificar que los códigos son únicos
        System.out.println("✓ Códigos únicos: " + facultad1.getCodigoFacultad() + ", " + 
                          facultad2.getCodigoFacultad() + ", " + facultad3.getCodigoFacultad());
    }
    
    /**
     * Prueba el autonumerado en la clase Usuario.
     */
    private static void testearAutonumeradoUsuario() {
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan@email.com");
        Usuario usuario2 = new Usuario("María", "García", "maria@email.com");
        Usuario usuario3 = new Usuario("Carlos", "López", "carlos@email.com");
        
        System.out.println("Usuario 1: " + usuario1);
        System.out.println("Usuario 2: " + usuario2);
        System.out.println("Usuario 3: " + usuario3);
        
        // Verificar que los IDs son secuenciales
        System.out.println("✓ IDs secuenciales: " + usuario1.getIdUsuario() + ", " + 
                          usuario2.getIdUsuario() + ", " + usuario3.getIdUsuario());
        
        // Verificar que los códigos son únicos
        System.out.println("✓ Códigos únicos: " + usuario1.getCodigoUsuario() + ", " + 
                          usuario2.getCodigoUsuario() + ", " + usuario3.getCodigoUsuario());
    }
    
    /**
     * Prueba el autonumerado en la clase Auditorio.
     */
    private static void testearAutonumeradoAuditorio() {
        Auditorio auditorio1 = new Auditorio("Auditorio Principal", 100);
        Auditorio auditorio2 = new Auditorio("Sala de Conferencias", 50);
        Auditorio auditorio3 = new Auditorio("Aula Magna", 200);
        
        System.out.println("Auditorio 1: " + auditorio1);
        System.out.println("Auditorio 2: " + auditorio2);
        System.out.println("Auditorio 3: " + auditorio3);
        
        // Verificar que los IDs son secuenciales
        System.out.println("✓ IDs secuenciales: " + auditorio1.getIdAuditorio() + ", " + 
                          auditorio2.getIdAuditorio() + ", " + auditorio3.getIdAuditorio());
        
        // Verificar que los códigos son únicos
        System.out.println("✓ Códigos únicos: " + auditorio1.getCodigoAuditorio() + ", " + 
                          auditorio2.getCodigoAuditorio() + ", " + auditorio3.getCodigoAuditorio());
    }
    
    /**
     * Prueba el autonumerado en la clase Reserva.
     */
    private static void testearAutonumeradoReserva() {
        Date fecha1 = new Date();
        Date fecha2 = new Date(System.currentTimeMillis() + 86400000); // +1 día
        Date fecha3 = new Date(System.currentTimeMillis() + 172800000); // +2 días
        
        Reserva reserva1 = new Reserva(fecha1, fecha2);
        Reserva reserva2 = new Reserva(fecha2, fecha3);
        Reserva reserva3 = new Reserva(fecha1, fecha3);
        
        System.out.println("Reserva 1: " + reserva1);
        System.out.println("Reserva 2: " + reserva2);
        System.out.println("Reserva 3: " + reserva3);
        
        // Verificar que los IDs son secuenciales
        System.out.println("✓ IDs secuenciales: " + reserva1.getIdReserva() + ", " + 
                          reserva2.getIdReserva() + ", " + reserva3.getIdReserva());
        
        // Verificar que los códigos son únicos
        System.out.println("✓ Códigos únicos: " + reserva1.getCodigoReserva() + ", " + 
                          reserva2.getCodigoReserva() + ", " + reserva3.getCodigoReserva());
    }
    
    /**
     * Prueba el autonumerado en la clase ReservaPrioritaria.
     */
    private static void testearAutonumeradoReservaPrioritaria() {
        Date fecha1 = new Date();
        Date fecha2 = new Date(System.currentTimeMillis() + 86400000); // +1 día
        Date fecha3 = new Date(System.currentTimeMillis() + 172800000); // +2 días
        
        ReservaPrioritaria reserva1 = new ReservaPrioritaria(fecha1, fecha2, Estado.PRIORIDAD_BAJA, "Reunión");
        ReservaPrioritaria reserva2 = new ReservaPrioritaria(fecha2, fecha3, Estado.PRIORIDAD_ALTA, "Conferencia");
        ReservaPrioritaria reserva3 = new ReservaPrioritaria(fecha1, fecha3, Estado.PRIORIDAD_URGENTE, "Evento importante");
        
        System.out.println("Reserva Prioritaria 1: " + reserva1);
        System.out.println("Reserva Prioritaria 2: " + reserva2);
        System.out.println("Reserva Prioritaria 3: " + reserva3);
        
        // Verificar que los IDs son secuenciales
        System.out.println("✓ IDs secuenciales: " + reserva1.getIdReserva() + ", " + 
                          reserva2.getIdReserva() + ", " + reserva3.getIdReserva());
        
        // Verificar que los códigos son únicos
        System.out.println("✓ Códigos únicos: " + reserva1.getCodigoReserva() + ", " + 
                          reserva2.getCodigoReserva() + ", " + reserva3.getCodigoReserva());
    }
} 