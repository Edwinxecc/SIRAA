package ec.edu.uce.dominio;

import java.util.Date; // Necesario para las fechas de la reserva

public class TestUsuario {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Usuario ---");

        // 1. Instanciación de Usuario
        // Se crea un nuevo usuario con valores por defecto.
        Usuario user = new Usuario();
        System.out.println("Usuario creado: " + user);
        System.out.println("-----------------------------------");

        // 2. Prueba de CRUD de Reservas
        System.out.println("--- Prueba de CRUD de Reservas para el Usuario ---");

        // Crear una reserva
        // Dado que 'Reserva' es abstracta, necesitamos instanciar 'ReservaPrioritaria'.
        // También se añaden fechas y un estado para la reserva.
        System.out.println("Creando algunas reservas...");
        Reserva re1 = new ReservaPrioritaria(1, new Date(), new Date(new Date().getTime() + 3600 * 1000 * 24), Estado.PENDIENTE, "Reunión importante");
        user.crearReserva(re1);

        Reserva re2 = new ReservaPrioritaria(2, new Date(new Date().getTime() + 3600 * 1000 * 24 * 7), new Date(new Date().getTime() + 3600 * 1000 * 24 * 8), Estado.PRIORIDAD_MEDIA, "Evento departamental");
        user.crearReserva(re2);

        // Crear una reserva con el método simplificado del usuario
        user.crearReservaPrioritaria(Estado.PRIORIDAD_ALTA, "Conferencia especial");


        System.out.println("Reservas del usuario después de la creación:");
        System.out.println(user.listarReservas());
        System.out.println("Número de reservas del usuario: " + user.getReservas().length);
        System.out.println("-----------------------------------");

        // Listar solo reservas activas (no canceladas)
        System.out.println("Listando solo reservas activas:");
        // Cambiamos el estado de una reserva a CANCELADA para probar el filtro
        if (user.getReservas().length > 0) {
            user.getReservas()[0].setEstado(Estado.CANCELADA);
            System.out.println("Estado de la primera reserva cambiado a CANCELADA.");
        }
        System.out.println(user.listarReservas(true)); // Listar solo activas
        System.out.println("-----------------------------------");


        // Actualizar una reserva
        System.out.println("Actualizando la primera reserva (índice 0) con nuevas fechas y ID...");
        // Aseguramos que haya al menos una reserva para actualizar
        if (user.getReservas().length > 0) {
            user.actualizarReserva(0, 99, new Date(new Date().getTime() + 3600 * 1000 * 24 * 10), new Date(new Date().getTime() + 3600 * 1000 * 24 * 11));
            System.out.println("Reservas después de la actualización de la primera reserva:");
            System.out.println(user.listarReservas());
        } else {
            System.out.println("No hay reservas para actualizar.");
        }
        System.out.println("-----------------------------------");

        // Eliminar una reserva
        System.out.println("Eliminando la primera reserva (índice 0)...");
        // Aseguramos que haya al menos una reserva para eliminar
        if (user.getReservas().length > 0) {
            user.eliminarReserva(0);
            System.out.println("Reservas después de la eliminación:");
            System.out.println(user.listarReservas());
            System.out.println("Número de reservas del usuario: " + user.getReservas().length);
        } else {
            System.out.println("No hay reservas para eliminar.");
        }
        System.out.println("-----------------------------------");


        System.out.println("--- Fin del Test de Usuario ---");
    }
}