package ec.edu.uce.dominio;

import java.util.Date;

public class TestReservaPrioritaria {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase ReservaPrioritaria ---");

        // 1. Instanciación con constructor por defecto
        ReservaPrioritaria rp1 = new ReservaPrioritaria();
        System.out.println("ReservaPrioritaria por defecto: " + rp1);

        // 2. Instanciación con constructor completo
        Date inicio = new Date();
        Date fin = new Date(inicio.getTime() + 3600 * 1000);
        ReservaPrioritaria rp2 = new ReservaPrioritaria(1, inicio, fin, Estado.PRIORIDAD_ALTA, "Evento importante");
        System.out.println("ReservaPrioritaria con datos: " + rp2);

        // 3. Prueba de setters y getters
        rp2.setMotivoPrioridad("Cambio de motivo");
        rp2.setNivelPrioridad(Estado.PRIORIDAD_URGENTE);
        System.out.println("ReservaPrioritaria modificada: " + rp2);

        // 4. Prueba de creación de equipos (máximo 5)
        try {
            rp2.crearEquipo("Proyector", "Visual", true);
            rp2.crearEquipo("Micrófono", "Audio", true);
            rp2.crearEquipo("Laptop", "Computación", true);
            rp2.crearEquipo("Cámara", "Video", false);
            rp2.crearEquipo("Pantalla", "Visual", true);
            // Intentar agregar uno más debe lanzar excepción
            rp2.crearEquipo("Altavoces", "Audio", true);
        } catch (IllegalStateException e) {
            System.out.println("Excepción capturada al agregar más de 5 equipos: " + e.getMessage());
        }

        System.out.println("Equipos en ReservaPrioritaria:");
        System.out.println(rp2.listarEquipos());

        System.out.println("--- Fin del Test de ReservaPrioritaria ---");
    }
}
