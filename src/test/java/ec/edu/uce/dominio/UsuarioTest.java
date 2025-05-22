package ec.edu.uce.dominio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {
    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
    }

    @Test
    void testConstructorVacio() {
        assertNotNull(usuario);
        assertNotNull(usuario.getReservas());
        assertTrue(usuario.getReservas().isEmpty());
    }

    @Test
    void testConstructorConParametros() {
        Usuario u = new Usuario(1, "Juan", "Pérez", "juan.perez@uce.edu.ec");
        assertEquals(1, u.getUsuarioId());
        assertEquals("Juan", u.getNombre());
        assertEquals("Pérez", u.getApellido());
        assertEquals("juan.perez@uce.edu.ec", u.getCorreo());
    }

    @Test
    void testSetUsuarioId() {
        usuario.setUsuarioId(1);
        assertEquals(1, usuario.getUsuarioId());
    }

    @Test
    void testSetUsuarioIdInvalido() {
        assertThrows(IllegalArgumentException.class, () -> usuario.setUsuarioId(0));
        assertThrows(IllegalArgumentException.class, () -> usuario.setUsuarioId(-1));
    }

    @Test
    void testSetNombre() {
        usuario.setNombre("Juan");
        assertEquals("Juan", usuario.getNombre());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Juan123", "Pedro1"})
    void testSetNombreInvalido(String nombre) {
        assertThrows(IllegalArgumentException.class, () -> usuario.setNombre(nombre));
    }

    @Test
    void testSetApellido() {
        usuario.setApellido("Pérez");
        assertEquals("Pérez", usuario.getApellido());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "Pérez123", "García1"})
    void testSetApellidoInvalido(String apellido) {
        assertThrows(IllegalArgumentException.class, () -> usuario.setApellido(apellido));
    }

    @Test
    void testSetCorreo() {
        usuario.setCorreo("juan.perez@uce.edu.ec");
        assertEquals("juan.perez@uce.edu.ec", usuario.getCorreo());
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "",
        " ",
        "juan.perez@gmail.com",
        "juan.perez@uce.edu.com",
        "@uce.edu.ec",
        "juan.perez@"
    })
    void testSetCorreoInvalido(String correo) {
        assertThrows(IllegalArgumentException.class, () -> usuario.setCorreo(correo));
    }

    @Test
    void testSetPassword() {
        usuario.setPassword("password123");
        assertEquals("password123", usuario.getPassword());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "123", "pass"})
    void testSetPasswordInvalido(String password) {
        assertThrows(IllegalArgumentException.class, () -> usuario.setPassword(password));
    }

    @Test
    void testGenerarId() {
        int id = usuario.generarId();
        assertTrue(id >= 100 && id <= 999);
    }

    @Test
    void testCrearReserva() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Test", 100);
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = inicio.plusHours(2);

        usuario.setUsuarioId(1);
        usuario.crearReserva(1, auditorio, inicio, fin);

        assertEquals(1, usuario.getReservas().size());
    }

    @Test
    void testCrearReservaFechasPasadas() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Test", 100);
        LocalDateTime inicio = LocalDateTime.now().minusDays(1);
        LocalDateTime fin = inicio.plusHours(2);

        usuario.setUsuarioId(1);
        assertThrows(IllegalArgumentException.class, 
            () -> usuario.crearReserva(1, auditorio, inicio, fin));
    }

    @Test
    void testCrearReservaFechasInvalidas() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Test", 100);
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = inicio.minusHours(2);

        usuario.setUsuarioId(1);
        assertThrows(IllegalArgumentException.class, 
            () -> usuario.crearReserva(1, auditorio, inicio, fin));
    }

    @Test
    void testBuscarReserva() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Test", 100);
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = inicio.plusHours(2);

        usuario.setUsuarioId(1);
        usuario.crearReserva(1, auditorio, inicio, fin);

        assertNotNull(usuario.buscarReserva(1));
        assertNull(usuario.buscarReserva(2));
    }

    @Test
    void testEliminarReserva() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Test", 100);
        LocalDateTime inicio = LocalDateTime.now().plusDays(1);
        LocalDateTime fin = inicio.plusHours(2);

        usuario.setUsuarioId(1);
        usuario.crearReserva(1, auditorio, inicio, fin);

        assertTrue(usuario.eliminarReserva(1));
        assertFalse(usuario.eliminarReserva(2));
        assertTrue(usuario.getReservas().isEmpty());
    }
} 