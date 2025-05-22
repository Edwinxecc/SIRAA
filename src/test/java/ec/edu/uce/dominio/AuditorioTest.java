package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AuditorioTest {
    private Auditorio auditorio;

    @BeforeEach
    void setUp() {
        auditorio = new Auditorio(1, "Auditorio Test", 100);
    }

    @Test
    void testConstructor() {
        assertEquals(1, auditorio.getId());
        assertEquals("Auditorio Test", auditorio.getNombre());
        assertEquals(100, auditorio.getCapacidad());
        assertNotNull(auditorio.getReservas());
        assertTrue(auditorio.getReservas().isEmpty());
    }

    @Test
    void testSetId() {
        auditorio.setId(2);
        assertEquals(2, auditorio.getId());
    }

    @Test
    void testSetIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> auditorio.setId(0));
        assertThrows(IllegalArgumentException.class, () -> auditorio.setId(-1));
    }

    @Test
    void testSetNombre() {
        auditorio.setNombre("Nuevo Auditorio");
        assertEquals("Nuevo Auditorio", auditorio.getNombre());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123"})
    void testSetNombreInvalido(String nombre) {
        assertThrows(IllegalArgumentException.class, () -> auditorio.setNombre(nombre));
    }

    @Test
    void testSetCapacidad() {
        auditorio.setCapacidad(200);
        assertEquals(200, auditorio.getCapacidad());
    }

    @Test
    void testSetCapacidadInvalida() {
        assertThrows(IllegalArgumentException.class, () -> auditorio.setCapacidad(0));
        assertThrows(IllegalArgumentException.class, () -> auditorio.setCapacidad(-1));
    }

    @Test
    void testAgregarReserva() {
        Usuario usuario = new Usuario(1, "Test", "User", "test.user@uce.edu.ec");
        Reserva reserva = new Reserva(1, usuario, auditorio, 
            java.time.LocalDateTime.now().plusDays(1),
            java.time.LocalDateTime.now().plusDays(1).plusHours(2));
        
        auditorio.agregarReserva(reserva);
        
        assertEquals(1, auditorio.getReservas().size());
        assertTrue(auditorio.getReservas().contains(reserva));
    }

    @Test
    void testBuscarReserva() {
        Usuario usuario = new Usuario(1, "Test", "User", "test.user@uce.edu.ec");
        Reserva reserva = new Reserva(1, usuario, auditorio,
            java.time.LocalDateTime.now().plusDays(1),
            java.time.LocalDateTime.now().plusDays(1).plusHours(2));
        
        auditorio.agregarReserva(reserva);
        
        assertNotNull(auditorio.buscarReserva(1));
        assertNull(auditorio.buscarReserva(2));
    }

    @Test
    void testEliminarReserva() {
        Usuario usuario = new Usuario(1, "Test", "User", "test.user@uce.edu.ec");
        Reserva reserva = new Reserva(1, usuario, auditorio,
            java.time.LocalDateTime.now().plusDays(1),
            java.time.LocalDateTime.now().plusDays(1).plusHours(2));
        
        auditorio.agregarReserva(reserva);
        assertTrue(auditorio.eliminarReserva(reserva));
        assertFalse(auditorio.getReservas().contains(reserva));
    }

    @Test
    void testToString() {
        String expected = "Auditorio ID: 1, Nombre: Auditorio Test, Capacidad: 100, Total Reservas: 0";
        assertEquals(expected, auditorio.toString());
    }
} 