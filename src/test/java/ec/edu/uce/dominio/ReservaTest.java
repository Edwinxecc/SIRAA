package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {
    private Reserva reserva;
    private Usuario usuario;
    private Auditorio auditorio;
    private LocalDateTime inicio;
    private LocalDateTime fin;

    @BeforeEach
    void setUp() {
        usuario = new Usuario(1, "Test", "User", "test.user@uce.edu.ec");
        auditorio = new Auditorio(1, "Auditorio Test", 100);
        inicio = LocalDateTime.now().plusDays(1);
        fin = inicio.plusHours(2);
        reserva = new Reserva(1, usuario, auditorio, inicio, fin);
    }

    @Test
    void testConstructor() {
        assertEquals(1, reserva.getId());
        assertEquals(usuario, reserva.getUsuario());
        assertEquals(auditorio, reserva.getAuditorio());
        assertEquals(inicio, reserva.getFechaInicio());
        assertEquals(fin, reserva.getFechaFin());
        assertNotNull(reserva.getEquiposAsignados());
        assertTrue(reserva.getEquiposAsignados().isEmpty());
    }

    @Test
    void testSetFechaInicio() {
        LocalDateTime nuevaFecha = inicio.plusDays(1);
        reserva.setFechaInicio(nuevaFecha);
        assertEquals(nuevaFecha, reserva.getFechaInicio());
    }

    @Test
    void testSetFechaFin() {
        LocalDateTime nuevaFecha = fin.plusHours(1);
        reserva.setFechaFin(nuevaFecha);
        assertEquals(nuevaFecha, reserva.getFechaFin());
    }

    @Test
    void testAsignarEquipo() {
        Equipo equipo = new Equipo(1, "Proyector", "Video", true);
        reserva.asignarEquipo(equipo);
        
        assertFalse(equipo.isDisponible());
        assertEquals(1, reserva.getEquiposAsignados().size());
        assertTrue(reserva.getEquiposAsignados().contains(equipo));
    }

    @Test
    void testAsignarEquipoNoDisponible() {
        Equipo equipo = new Equipo(1, "Proyector", "Video", false);
        reserva.asignarEquipo(equipo);
        
        assertFalse(equipo.isDisponible());
        assertTrue(reserva.getEquiposAsignados().isEmpty());
    }

    @Test
    void testLiberarEquipo() {
        Equipo equipo = new Equipo(1, "Proyector", "Video", true);
        reserva.asignarEquipo(equipo);
        reserva.liberarEquipo(equipo);
        
        assertTrue(equipo.isDisponible());
        assertTrue(reserva.getEquiposAsignados().isEmpty());
    }

    @Test
    void testLiberarEquipoNoAsignado() {
        Equipo equipo = new Equipo(1, "Proyector", "Video", false);
        reserva.liberarEquipo(equipo);
        
        assertFalse(equipo.isDisponible());
        assertTrue(reserva.getEquiposAsignados().isEmpty());
    }

    @Test
    void testCrearReserva() {
        Reserva.crearReserva(reserva);
        
        Reserva encontrada = Reserva.consultarReserva(1);
        assertNotNull(encontrada);
        assertEquals(reserva.getId(), encontrada.getId());
    }

    @Test
    void testConsultarReserva() {
        Reserva.crearReserva(reserva);
        
        assertNotNull(Reserva.consultarReserva(1));
        assertNull(Reserva.consultarReserva(999));
    }

    @Test
    void testListarReservas() {
        Reserva.crearReserva(reserva);
        
        assertFalse(Reserva.listarReservas().isEmpty());
        assertEquals(1, Reserva.listarReservas().size());
        assertTrue(Reserva.listarReservas().contains(reserva));
    }

    @Test
    void testActualizarReserva() {
        Reserva.crearReserva(reserva);
        LocalDateTime nuevoInicio = inicio.plusDays(1);
        LocalDateTime nuevoFin = nuevoInicio.plusHours(2);
        
        assertTrue(Reserva.actualizarReserva(1, nuevoInicio, nuevoFin));
        assertFalse(Reserva.actualizarReserva(999, nuevoInicio, nuevoFin));
        
        Reserva actualizada = Reserva.consultarReserva(1);
        assertNotNull(actualizada);
        assertEquals(nuevoInicio, actualizada.getFechaInicio());
        assertEquals(nuevoFin, actualizada.getFechaFin());
    }

    @Test
    void testEliminarReserva() {
        Equipo equipo = new Equipo(1, "Proyector", "Video", true);
        reserva.asignarEquipo(equipo);
        Reserva.crearReserva(reserva);
        
        assertTrue(Reserva.eliminarReserva(1));
        assertFalse(Reserva.eliminarReserva(999));
        assertTrue(equipo.isDisponible());
        assertNull(Reserva.consultarReserva(1));
    }

    @Test
    void testToString() {
        String expected = "Reserva ID: 1, Usuario: Test, Auditorio: Auditorio Test, " +
                         "Inicio: " + inicio + ", Fin: " + fin + ", Equipos: 0";
        assertEquals(expected, reserva.toString());
    }
} 