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
        java.util.Calendar cal = java.util.Calendar.getInstance();
        cal.set(2024, java.util.Calendar.JANUARY, 15, 9, 0, 0);
        Date fecha1 = cal.getTime(); // 15 de enero de 2024, 9:00 AM
        cal.set(2024, java.util.Calendar.JANUARY, 15, 11, 0, 0);
        Date fecha2 = cal.getTime(); // 15 de enero de 2024, 11:00 AM
        Reserva reserva1 = new Reserva(1, fecha1, fecha2);
        reserva1.setEstado(Estado.CONFIRMADA);
        auditorio.crearReserva(reserva1);
        
        // Reserva 2
        cal.set(2024, java.util.Calendar.JANUARY, 16, 14, 0, 0);
        Date fecha3 = cal.getTime(); // 16 de enero de 2024, 2:00 PM
        cal.set(2024, java.util.Calendar.JANUARY, 16, 16, 0, 0);
        Date fecha4 = cal.getTime(); // 16 de enero de 2024, 4:00 PM
        Reserva reserva2 = new Reserva(2, fecha3, fecha4);
        reserva2.setEstado(Estado.PENDIENTE);
        auditorio.crearReserva(reserva2);
        
        // Reserva 3
        cal.set(2024, java.util.Calendar.JANUARY, 17, 10, 0, 0);
        Date fecha5 = cal.getTime(); // 17 de enero de 2024, 10:00 AM
        cal.set(2024, java.util.Calendar.JANUARY, 17, 12, 0, 0);
        Date fecha6 = cal.getTime(); // 17 de enero de 2024, 12:00 PM
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
        Reserva[] reservas = auditorio.getReservas();
        if (reservas.length > 1) {
            auditorio.actualizarEstadoReserva(reservas[1].getCodigoReserva(), Estado.EN_PROCESO);
        }
        System.out.println("Estado actualizado. Nueva lista:");
        System.out.println(auditorio.listarReservas());
        
        // 9. Verificar disponibilidad
        System.out.println("\n9. Verificando disponibilidad...");
        Date fechaVerificar1, fechaVerificar2, fechaVerificar3, fechaVerificar4;
        cal.set(2024, java.util.Calendar.JANUARY, 15, 8, 0, 0);
        fechaVerificar1 = cal.getTime(); // 8:00 AM
        cal.set(2024, java.util.Calendar.JANUARY, 15, 10, 0, 0);
        fechaVerificar2 = cal.getTime(); // 10:00 AM
        boolean disponible = auditorio.estaDisponible(fechaVerificar1, fechaVerificar2);
        System.out.println("¿Disponible de 8:00 AM a 10:00 AM el 15 de enero? " + (disponible ? "Sí" : "No"));
        
        cal.set(2024, java.util.Calendar.JANUARY, 18, 9, 0, 0);
        fechaVerificar3 = cal.getTime(); // 18 de enero, 9:00 AM
        cal.set(2024, java.util.Calendar.JANUARY, 18, 11, 0, 0);
        fechaVerificar4 = cal.getTime(); // 18 de enero, 11:00 AM
        boolean disponible2 = auditorio.estaDisponible(fechaVerificar3, fechaVerificar4);
        System.out.println("¿Disponible de 9:00 AM a 11:00 AM el 18 de enero? " + (disponible2 ? "Sí" : "No"));
        
        // 10. Intentar crear reserva duplicada
        System.out.println("\n10. Intentando crear reserva duplicada...");
        auditorio.crearReserva(reserva1); // Debería mostrar mensaje de duplicado
        
        // 11. Eliminar una reserva
        System.out.println("\n11. Eliminando reserva...");
        if (reservas.length > 0) {
            auditorio.eliminarReserva(reservas[0].getCodigoReserva());
            System.out.println("Reserva eliminada. Nueva lista:");
            System.out.println(auditorio.listarReservas());
        } else {
            System.out.println("No hay reservas para eliminar.");
        }
        
        // 12. Mostrar información final del auditorio
        System.out.println("\n12. Información final del auditorio:");
        System.out.println(auditorio);
        System.out.println("Total de reservas: " + auditorio.getNumReservas());
        
        System.out.println("\n=== FIN DE LA DEMOSTRACIÓN ===");
    }
} 