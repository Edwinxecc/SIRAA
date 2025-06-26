package ec.edu.uce.dominio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Test unitario para la implementación de ordenación en la clase Auditorio.
 */
public class TestOrdenacionAuditorio {
    
    private Auditorio[] auditorios;
    
    @BeforeEach
    void setUp() {
        // Crear auditorios de prueba con diferentes características
        auditorios = new Auditorio[]{
            new Auditorio("Auditorio A", 150),
            new Auditorio("Auditorio B", 200),
            new Auditorio("Auditorio C", 100),
            new Auditorio("Auditorio D", 300),
            new Auditorio("Auditorio E", 120)
        };
        
        // Agregar algunas reservas a los auditorios para probar ordenación por número de reservas
        auditorios[0].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[0].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[1].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[3].crearReserva(new Reserva(new Date(), new Date()));
    }
    
    @Test
    void testComparablePorId() {
        // Test para verificar que Comparable ordena por ID
        Auditorio[] auditoriosOrdenados = auditorios.clone();
        Arrays.sort(auditoriosOrdenados);
        
        // Verificar que están ordenados por ID ascendente
        for (int i = 0; i < auditoriosOrdenados.length - 1; i++) {
            assertTrue(auditoriosOrdenados[i].getIdAuditorio() <= auditoriosOrdenados[i + 1].getIdAuditorio(),
                    "Los auditorios deben estar ordenados por ID ascendente");
        }
    }
    
    @Test
    void testComparatorPorCapacidad() {
        // Test para verificar ordenación por capacidad (ascendente)
        Auditorio[] auditoriosOrdenados = auditorios.clone();
        Arrays.sort(auditoriosOrdenados, Auditorio.COMPARADOR_POR_CAPACIDAD);
        
        // Verificar que están ordenados por capacidad ascendente
        int[] capacidadesEsperadas = {100, 120, 150, 200, 300};
        for (int i = 0; i < auditoriosOrdenados.length; i++) {
            assertEquals(capacidadesEsperadas[i], auditoriosOrdenados[i].getCapacidad(),
                    "Los auditorios deben estar ordenados por capacidad ascendente");
        }
    }
    
    @Test
    void testComparatorPorNumReservas() {
        // Test para verificar ordenación por número de reservas (descendente)
        Auditorio[] auditoriosOrdenados = auditorios.clone();
        Arrays.sort(auditoriosOrdenados, Auditorio.COMPARADOR_POR_NUM_RESERVAS);
        
        // Verificar que están ordenados por número de reservas descendente
        int[] numReservasEsperadas = {3, 2, 1, 1, 0}; // C(3), A(2), B(1), D(1), E(0)
        for (int i = 0; i < auditoriosOrdenados.length; i++) {
            assertEquals(numReservasEsperadas[i], auditoriosOrdenados[i].getNumReservas(),
                    "Los auditorios deben estar ordenados por número de reservas descendente");
        }
    }
    
    @Test
    void testComparatorPorId() {
        // Test para verificar ordenación por ID usando Comparator
        Auditorio[] auditoriosOrdenados = auditorios.clone();
        Arrays.sort(auditoriosOrdenados, Auditorio.COMPARADOR_POR_ID);
        
        // Verificar que están ordenados por ID ascendente
        for (int i = 0; i < auditoriosOrdenados.length - 1; i++) {
            assertTrue(auditoriosOrdenados[i].getIdAuditorio() <= auditoriosOrdenados[i + 1].getIdAuditorio(),
                    "Los auditorios deben estar ordenados por ID ascendente");
        }
    }
    
    @Test
    void testOrdenarReservasPorFecha() {
        // Test para verificar ordenación de reservas por fecha
        Auditorio auditorio = auditorios[0];
        
        // Crear reservas con fechas diferentes
        Date fecha1 = new Date(2024, 0, 15, 9, 0); // 15 enero 2024, 9:00
        Date fecha2 = new Date(2024, 0, 16, 14, 0); // 16 enero 2024, 14:00
        Date fecha3 = new Date(2024, 0, 14, 10, 0); // 14 enero 2024, 10:00
        
        auditorio.crearReserva(new Reserva(fecha1, fecha2));
        auditorio.crearReserva(new Reserva(fecha2, fecha3));
        auditorio.crearReserva(new Reserva(fecha3, fecha1));
        
        // Ordenar reservas por fecha
        auditorio.ordenarReservasPorFecha();
        
        // Verificar que las reservas están ordenadas por fecha de inicio
        Reserva[] reservas = auditorio.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getFechaInicio().compareTo(reservas[i + 1].getFechaInicio()) <= 0,
                    "Las reservas deben estar ordenadas por fecha de inicio ascendente");
        }
    }
    
    @Test
    void testOrdenarReservasPorEstado() {
        // Test para verificar ordenación de reservas por estado
        Auditorio auditorio = auditorios[0];
        
        // Crear reservas con diferentes estados
        Reserva reserva1 = new Reserva(new Date(), new Date());
        reserva1.setEstado(Estado.CONFIRMADA);
        
        Reserva reserva2 = new Reserva(new Date(), new Date());
        reserva2.setEstado(Estado.PENDIENTE);
        
        Reserva reserva3 = new Reserva(new Date(), new Date());
        reserva3.setEstado(Estado.CANCELADA);
        
        auditorio.crearReserva(reserva1);
        auditorio.crearReserva(reserva2);
        auditorio.crearReserva(reserva3);
        
        // Ordenar reservas por estado
        auditorio.ordenarReservasPorEstado();
        
        // Verificar que las reservas están ordenadas por estado
        Reserva[] reservas = auditorio.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getEstado().getDescripcion().compareTo(
                    reservas[i + 1].getEstado().getDescripcion()) <= 0,
                    "Las reservas deben estar ordenadas por estado alfabéticamente");
        }
    }
    
    @Test
    void testOrdenarReservasPorId() {
        // Test para verificar ordenación de reservas por ID
        Auditorio auditorio = auditorios[0];
        
        // Crear reservas (los IDs se generan automáticamente)
        auditorio.crearReserva(new Reserva(new Date(), new Date()));
        auditorio.crearReserva(new Reserva(new Date(), new Date()));
        auditorio.crearReserva(new Reserva(new Date(), new Date()));
        
        // Ordenar reservas por ID
        auditorio.ordenarReservasPorId();
        
        // Verificar que las reservas están ordenadas por ID
        Reserva[] reservas = auditorio.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getIdReserva() <= reservas[i + 1].getIdReserva(),
                    "Las reservas deben estar ordenadas por ID ascendente");
        }
    }
    
    @Test
    void testComparableConsistencia() {
        // Test para verificar consistencia de Comparable
        Auditorio auditorio1 = auditorios[0];
        Auditorio auditorio2 = auditorios[1];
        
        // Verificar que compareTo es consistente
        int resultado1 = auditorio1.compareTo(auditorio2);
        int resultado2 = auditorio2.compareTo(auditorio1);
        
        assertTrue((resultado1 > 0 && resultado2 < 0) || (resultado1 < 0 && resultado2 > 0) || (resultado1 == 0 && resultado2 == 0),
                "compareTo debe ser consistente");
    }
    
    @Test
    void testComparatorTransitividad() {
        // Test para verificar transitividad de Comparator
        Auditorio auditorio1 = auditorios[0];
        Auditorio auditorio2 = auditorios[1];
        Auditorio auditorio3 = auditorios[2];
        
        // Verificar transitividad del comparador por capacidad
        int resultado1 = Auditorio.COMPARADOR_POR_CAPACIDAD.compare(auditorio1, auditorio2);
        int resultado2 = Auditorio.COMPARADOR_POR_CAPACIDAD.compare(auditorio2, auditorio3);
        int resultado3 = Auditorio.COMPARADOR_POR_CAPACIDAD.compare(auditorio1, auditorio3);
        
        if (resultado1 > 0 && resultado2 > 0) {
            assertTrue(resultado3 > 0, "El comparador debe ser transitivo");
        } else if (resultado1 < 0 && resultado2 < 0) {
            assertTrue(resultado3 < 0, "El comparador debe ser transitivo");
        }
    }
} 