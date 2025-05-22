package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class FacultadTest {
    private Facultad facultad;

    @BeforeEach
    void setUp() {
        facultad = new Facultad("Ingeniería", 2);
    }

    @Test
    void testConstructorVacio() {
        Facultad f = new Facultad();
        assertEquals("Ingenieria y Ciencias Aplicadas", f.getNombre());
        assertEquals(1, f.getNumeroAuditorios());
        assertNotNull(f.getUsuarios());
        assertNotNull(f.getAuditorios());
        assertTrue(f.getUsuarios().isEmpty());
        assertEquals(1, f.getAuditorios().size());
    }

    @Test
    void testConstructorParametrizado() {
        assertEquals("Ingeniería", facultad.getNombre());
        assertEquals(2, facultad.getNumeroAuditorios());
        assertNotNull(facultad.getUsuarios());
        assertNotNull(facultad.getAuditorios());
        assertTrue(facultad.getUsuarios().isEmpty());
        assertEquals(2, facultad.getAuditorios().size());
    }

    @Test
    void testSetNombre() {
        facultad.setNombre("Nueva Facultad");
        assertEquals("Nueva Facultad", facultad.getNombre());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123"})
    void testSetNombreInvalido(String nombre) {
        assertThrows(IllegalArgumentException.class, () -> facultad.setNombre(nombre));
    }

    @Test
    void testSetNumeroAuditorios() {
        facultad.setNumeroAuditorios(3);
        assertEquals(3, facultad.getNumeroAuditorios());
    }

    @Test
    void testSetNumeroAuditoriosInvalido() {
        assertThrows(IllegalArgumentException.class, () -> facultad.setNumeroAuditorios(0));
        assertThrows(IllegalArgumentException.class, () -> facultad.setNumeroAuditorios(-1));
    }

    @Test
    void testCrearUsuario() {
        facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        assertEquals(1, facultad.getUsuarios().size());
        
        Usuario usuario = facultad.buscarUsuarioPorCorreo("juan.perez@uce.edu.ec");
        assertNotNull(usuario);
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Pérez", usuario.getApellido());
    }

    @Test
    void testCrearUsuarioCorreoDuplicado() {
        facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        assertThrows(IllegalArgumentException.class, () -> 
            facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec"));
    }

    @Test
    void testBuscarUsuario() {
        facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        Usuario usuario = facultad.getUsuarios().get(0);
        
        assertNotNull(facultad.buscarUsuario(usuario.getUsuarioId()));
        assertNull(facultad.buscarUsuario(999));
    }

    @Test
    void testBuscarUsuarioPorCorreo() {
        facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        
        assertNotNull(facultad.buscarUsuarioPorCorreo("juan.perez@uce.edu.ec"));
        assertNull(facultad.buscarUsuarioPorCorreo("noexiste@uce.edu.ec"));
    }

    @Test
    void testEliminarUsuario() {
        facultad.crearUsuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        Usuario usuario = facultad.getUsuarios().get(0);
        
        assertTrue(facultad.eliminarUsuario(usuario.getUsuarioId()));
        assertFalse(facultad.eliminarUsuario(999));
        assertTrue(facultad.getUsuarios().isEmpty());
    }

    @Test
    void testBuscarAuditorio() {
        Auditorio auditorio = facultad.getAuditorios().get(0);
        assertNotNull(facultad.buscarAuditorio(auditorio.getNombre()));
        assertNull(facultad.buscarAuditorio("No Existe"));
    }

    @Test
    void testCrearFacultad() {
        Facultad nuevaFacultad = new Facultad("Medicina", 1);
        assertTrue(Facultad.crearFacultad(nuevaFacultad));
        assertFalse(Facultad.crearFacultad(nuevaFacultad)); // No debe permitir duplicados
    }

    @Test
    void testListarFacultades() {
        Facultad nuevaFacultad = new Facultad("Medicina", 1);
        Facultad.crearFacultad(nuevaFacultad);
        
        assertFalse(Facultad.listarFacultades().isEmpty());
        assertTrue(Facultad.listarFacultades().contains(nuevaFacultad));
    }

    @Test
    void testBuscarFacultadPorNombre() {
        Facultad nuevaFacultad = new Facultad("Medicina", 1);
        Facultad.crearFacultad(nuevaFacultad);
        
        assertNotNull(Facultad.buscarFacultadPorNombre("Medicina"));
        assertNull(Facultad.buscarFacultadPorNombre("No Existe"));
    }

    @Test
    void testEditarFacultad() {
        Facultad nuevaFacultad = new Facultad("Medicina", 1);
        Facultad.crearFacultad(nuevaFacultad);
        
        assertTrue(Facultad.editarFacultad("Medicina", "Medicina General", 2));
        assertFalse(Facultad.editarFacultad("No Existe", "Nueva", 1));
    }

    @Test
    void testEliminarFacultad() {
        Facultad nuevaFacultad = new Facultad("Medicina", 1);
        Facultad.crearFacultad(nuevaFacultad);
        
        assertTrue(Facultad.eliminarFacultad("Medicina"));
        assertFalse(Facultad.eliminarFacultad("No Existe"));
    }

    @Test
    void testToString() {
        String expected = "Facultad{nombre='Ingeniería', numeroAuditorios=2, usuarios=0}";
        assertEquals(expected, facultad.toString());
    }
} 