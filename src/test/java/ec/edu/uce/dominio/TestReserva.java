package ec.edu.uce.dominio;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class TestReserva {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Reserva ---");

        // 1. Instanciación con constructor por defecto
        Reserva r1 = new Reserva();
        System.out.println("Reserva por defecto: " + r1);

        // 2. Instanciación con constructor completo
        Date inicio = new Date();
        Date fin = new Date(inicio.getTime() + 3600 * 1000); // 1 hora después
        Reserva r2 = new Reserva(1, inicio, fin);
        System.out.println("Reserva con datos: " + r2);

        // 3. Prueba de creación y listado de equipos
        r2.crearEquipo("Proyector", "Visual", true);
        r2.crearEquipo(new Equipo("Micrófono", "Audio", false));
        System.out.println("Equipos en la reserva:");
        System.out.println(r2.listarEquipos());

        // 4. Actualizar equipo
        r2.actualizarEquipo(0, "Proyector HD", "Visual", true);
        System.out.println("Equipos después de actualización:");
        System.out.println(r2.listarEquipos());

        // 5. Eliminar equipo
        r2.eliminarEquipo(1);
        System.out.println("Equipos después de eliminación:");
        System.out.println(r2.listarEquipos());

        // 6. Prueba de setters y getters
        r2.setEstado(Estado.CONFIRMADA);
        r2.setIdReserva(10);
        System.out.println("Reserva después de cambios: " + r2);

        System.out.println("--- Fin del Test de Reserva ---");
    }
}
