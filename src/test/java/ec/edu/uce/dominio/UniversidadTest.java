package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UniversidadTest {
    private Universidad universidad;

    @BeforeEach
    void setUp() {
        universidad = new Universidad("Universidad Central", 2);
    }

    @Test
    void testConstructorVacio() {
        Universidad u = new Universidad();
        assertEquals("Sin nombre", u.getNombre());
        assertTrue(u.consultarFacultad().isEmpty());
    }

    @Test
    void testConstructorParametrizado() {
        assertEquals("Universidad Central", universidad.getNombre());
        assertTrue(universidad.consultarFacultad().isEmpty());
    }

    @Test
    void testSetNombre() {
        universidad.setNombre("Nueva Universidad");
        assertEquals("Nueva Universidad", universidad.getNombre());
    }

    @Test
    void testCrearFacultad() {
        Facultad facultad = new Facultad("Ingeniería", 2);
        universidad.crearFacultad(facultad);
        
        assertNotNull(universidad.buscarFacultad("Ingeniería"));
        assertEquals(facultad, universidad.buscarFacultad("Ingeniería"));
    }

    @Test
    void testCrearMultiplesFacultades() {
        // Prueba la expansión dinámica del array
        for (int i = 0; i < 5; i++) {
            Facultad facultad = new Facultad("Facultad " + i, 1);
            universidad.crearFacultad(facultad);
        }
        
        for (int i = 0; i < 5; i++) {
            assertNotNull(universidad.buscarFacultad("Facultad " + i));
        }
    }

    @Test
    void testBuscarFacultad() {
        Facultad facultad = new Facultad("Ingeniería", 2);
        universidad.crearFacultad(facultad);
        
        assertNotNull(universidad.buscarFacultad("Ingeniería"));
        assertNull(universidad.buscarFacultad("No Existe"));
    }

    @Test
    void testEditarFacultad() {
        Facultad facultad = new Facultad("Ingeniería", 2);
        universidad.crearFacultad(facultad);
        
        assertTrue(universidad.editarFacultad("Ingeniería", "Ingeniería Sistemas", 3));
        assertFalse(universidad.editarFacultad("No Existe", "Nueva", 1));
        
        Facultad editada = universidad.buscarFacultad("Ingeniería Sistemas");
        assertNotNull(editada);
        assertEquals("Ingeniería Sistemas", editada.getNombre());
        assertEquals(3, editada.getNumeroAuditorios());
    }

    @Test
    void testEliminarFacultad() {
        Facultad facultad1 = new Facultad("Ingeniería", 2);
        Facultad facultad2 = new Facultad("Medicina", 1);
        universidad.crearFacultad(facultad1);
        universidad.crearFacultad(facultad2);
        
        assertTrue(universidad.eliminarFacultad("Ingeniería"));
        assertFalse(universidad.eliminarFacultad("No Existe"));
        assertNull(universidad.buscarFacultad("Ingeniería"));
        assertNotNull(universidad.buscarFacultad("Medicina"));
    }

    @Test
    void testConsultarFacultad() {
        assertTrue(universidad.consultarFacultad().isEmpty());
        
        Facultad facultad = new Facultad("Ingeniería", 2);
        universidad.crearFacultad(facultad);
        
        assertFalse(universidad.consultarFacultad().isEmpty());
        assertTrue(universidad.consultarFacultad().contains(facultad.toString()));
    }

    @Test
    void testToString() {
        Facultad facultad = new Facultad("Ingeniería", 2);
        universidad.crearFacultad(facultad);
        
        String resultado = universidad.toString();
        assertTrue(resultado.contains("Universidad: Universidad Central"));
        assertTrue(resultado.contains("Número de Facultades: 1"));
        assertTrue(resultado.contains("Facultades:"));
        assertTrue(resultado.contains("- " + facultad.toString()));
    }
}