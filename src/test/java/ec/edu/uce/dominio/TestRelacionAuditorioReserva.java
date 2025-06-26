package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Clase de test para demostrar la relación uno a muchos (1:0..n) 
 * entre Auditorio y Reserva usando arreglos y métodos CRUD.
 */
public class TestRelacionAuditorioReserva {
    
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN RELACIÓN AUDITORIO-RESERVA (1:0..n) ===\n");
        
        // 1. Crear un auditorio
        System.out.println("1. Creando auditorio...");
        Auditorio auditorio = new Auditorio("Auditorio Principal", 100);
        System.out.println("Auditorio creado: " + auditorio);
        
        // 2. Crear algunas reservas
        System.out.println("\n2. Creando reservas...");
        
        // Reserva 1
        Date fecha1 = new Date(2024, 0, 15, 9, 0); // 15 de enero de 2024, 9:00 AM
        Date fecha2 = new Date(2024, 0, 15, 11, 0); // 15 de enero de 2024, 11:00 AM
        Reserva reserva1 = new Reserva(1, fecha1, fecha2);
        reserva1.setEstado(Estado.CONFIRMADA);
        auditorio.crearReserva(reserva1);
        
        // Reserva 2
        Date fecha3 = new Date(2024, 0, 16, 14, 0); // 16 de enero de 2024, 2:00 PM
        Date fecha4 = new Date(2024, 0, 16, 16, 0); // 16 de enero de 2024, 4:00 PM
        Reserva reserva2 = new Reserva(2, fecha3, fecha4);
        reserva2.setEstado(Estado.PENDIENTE);
        auditorio.crearReserva(reserva2);
        
        // Reserva 3
        Date fecha5 = new Date(2024, 0, 17, 10, 0); // 17 de enero de 2024, 10:00 AM
        Date fecha6 = new Date(2024, 0, 17, 12, 0); // 17 de enero de 2024, 12:00 PM
        auditorio.crearReserva(3, fecha5, fecha6);
        
        System.out.println("Reservas creadas exitosamente");
        
        // 3. Listar todas las reservas
        System.out.println("\n3. Listando todas las reservas:");
        System.out.println(auditorio.listarReservas());
        
        // 4. Listar reservas por estado
        System.out.println("4. Listando reservas confirmadas:");
        System.out.println(auditorio.listarReservasPorEstado(Estado.CONFIRMADA));
        
        System.out.println("5. Listando reservas pendientes:");
        System.out.println(auditorio.listarReservasPorEstado(Estado.PENDIENTE));
        
        // 6. Buscar reserva por código
        System.out.println("6. Buscando reserva por código...");
        String codigoBuscar = reserva1.getCodigoReserva();
        Reserva reservaEncontrada = auditorio.buscarReservaPorCodigo(codigoBuscar);
        if (reservaEncontrada != null) {
            System.out.println("Reserva encontrada: " + reservaEncontrada);
        } else {
            System.out.println("Reserva no encontrada");
        }
        
        // 7. Obtener reserva por índice
        System.out.println("\n7. Obteniendo reserva por índice...");
        Reserva reservaPorIndice = auditorio.getReserva(1);
        if (reservaPorIndice != null) {
            System.out.println("Reserva en índice 1: " + reservaPorIndice);
        }
        
        // 8. Actualizar estado de una reserva
        System.out.println("\n8. Actualizando estado de reserva...");
        auditorio.actualizarEstadoReserva(1, Estado.EN_PROCESO);
        System.out.println("Estado actualizado. Nueva lista:");
        System.out.println(auditorio.listarReservas());
        
        // 9. Verificar disponibilidad
        System.out.println("\n9. Verificando disponibilidad...");
        Date fechaVerificar1 = new Date(2024, 0, 15, 8, 0); // 8:00 AM
        Date fechaVerificar2 = new Date(2024, 0, 15, 10, 0); // 10:00 AM
        boolean disponible = auditorio.estaDisponible(fechaVerificar1, fechaVerificar2);
        System.out.println("¿Disponible de 8:00 AM a 10:00 AM el 15 de enero? " + (disponible ? "Sí" : "No"));
        
        Date fechaVerificar3 = new Date(2024, 0, 18, 9, 0); // 18 de enero, 9:00 AM
        Date fechaVerificar4 = new Date(2024, 0, 18, 11, 0); // 18 de enero, 11:00 AM
        boolean disponible2 = auditorio.estaDisponible(fechaVerificar3, fechaVerificar4);
        System.out.println("¿Disponible de 9:00 AM a 11:00 AM el 18 de enero? " + (disponible2 ? "Sí" : "No"));
        
        // 10. Intentar crear reserva duplicada
        System.out.println("\n10. Intentando crear reserva duplicada...");
        auditorio.crearReserva(reserva1); // Debería mostrar mensaje de duplicado
        
        // 11. Eliminar una reserva
        System.out.println("\n11. Eliminando reserva...");
        auditorio.eliminarReserva(0);
        System.out.println("Reserva eliminada. Nueva lista:");
        System.out.println(auditorio.listarReservas());
        
        // 12. Mostrar información final del auditorio
        System.out.println("\n12. Información final del auditorio:");
        System.out.println(auditorio);
        System.out.println("Total de reservas: " + auditorio.getNumReservas());
        
        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
} 