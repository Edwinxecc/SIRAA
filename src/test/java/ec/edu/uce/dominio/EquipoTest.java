package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EquipoTest {
    private Equipo equipo;

    @BeforeEach
    void setUp() {
        equipo = new Equipo(1, "Proyector", "Video", true);
    }

    @Test
    void testConstructor() {
        assertEquals(1, equipo.getId());
        assertEquals("Proyector", equipo.getNombre());
        assertEquals("Video", equipo.getTipo());
        assertTrue(equipo.isDisponible());
    }

    @Test
    void testSetId() {
        equipo.setId(2);
        assertEquals(2, equipo.getId());
    }

    @Test
    void testSetNombre() {
        equipo.setNombre("Micrófono");
        assertEquals("Micrófono", equipo.getNombre());
    }

    @Test
    void testSetTipo() {
        equipo.setTipo("Audio");
        assertEquals("Audio", equipo.getTipo());
    }

    @Test
    void testSetDisponible() {
        equipo.setDisponible(false);
        assertFalse(equipo.isDisponible());
    }

    @Test
    void testCrearEquipo() {
        Equipo nuevoEquipo = new Equipo(2, "Micrófono", "Audio", true);
        Equipo.crearEquipo(nuevoEquipo);
        
        Equipo encontrado = Equipo.consultarEquipo(2);
        assertNotNull(encontrado);
        assertEquals("Micrófono", encontrado.getNombre());
    }

    @Test
    void testConsultarEquipo() {
        Equipo.crearEquipo(equipo);
        
        assertNotNull(Equipo.consultarEquipo(1));
        assertNull(Equipo.consultarEquipo(999));
    }

    @Test
    void testListarEquipos() {
        Equipo.crearEquipo(equipo);
        Equipo nuevoEquipo = new Equipo(2, "Micrófono", "Audio", true);
        Equipo.crearEquipo(nuevoEquipo);
        
        assertFalse(Equipo.listarEquipos().isEmpty());
        assertEquals(2, Equipo.listarEquipos().size());
    }

    @Test
    void testActualizarEquipo() {
        Equipo.crearEquipo(equipo);
        
        assertTrue(Equipo.actualizarEquipo(1, "Proyector HD", "Video", false));
        assertFalse(Equipo.actualizarEquipo(999, "No existe", "N/A", true));
        
        Equipo actualizado = Equipo.consultarEquipo(1);
        assertNotNull(actualizado);
        assertEquals("Proyector HD", actualizado.getNombre());
        assertEquals("Video", actualizado.getTipo());
        assertFalse(actualizado.isDisponible());
    }

    @Test
    void testEliminarEquipo() {
        Equipo.crearEquipo(equipo);
        
        assertTrue(Equipo.eliminarEquipo(1));
        assertFalse(Equipo.eliminarEquipo(999));
        assertNull(Equipo.consultarEquipo(1));
    }
} 