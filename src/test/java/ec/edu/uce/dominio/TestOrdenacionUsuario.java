package ec.edu.uce.dominio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Test unitario para la implementación de ordenación en la clase Usuario.
 */
public class TestOrdenacionUsuario {
    
    private Usuario[] usuarios;
    
    @BeforeEach
    void setUp() {
        // Crear usuarios de prueba con diferentes características
        Map<String, Usuario> usuariosMap = new HashMap<>();
        Usuario u1 = new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec");
        Usuario u2 = new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec");
        Usuario u3 = new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        Usuario u4 = new Usuario("María", "García", "maria.garcia@uce.edu.ec");
        Usuario u5 = new Usuario("Luis", "Rodríguez", "luis.rodriguez@uce.edu.ec");
        usuariosMap.put(u1.getCodigoUsuario(), u1);
        usuariosMap.put(u2.getCodigoUsuario(), u2);
        usuariosMap.put(u3.getCodigoUsuario(), u3);
        usuariosMap.put(u4.getCodigoUsuario(), u4);
        usuariosMap.put(u5.getCodigoUsuario(), u5);
        usuarios = usuariosMap.values().toArray(new Usuario[0]);
        
        // Agregar algunas reservas a los usuarios para probar ordenación por número de reservas
        usuarios[0].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[0].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[1].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
    }
    
    @Test
    void testComparablePorId() {
        // Test para verificar que Comparable ordena por ID
        Usuario[] usuariosOrdenados = usuarios.clone();
        Arrays.sort(usuariosOrdenados);
        
        // Verificar que están ordenados por ID ascendente
        for (int i = 0; i < usuariosOrdenados.length - 1; i++) {
            assertTrue(usuariosOrdenados[i].getIdUsuario() <= usuariosOrdenados[i + 1].getIdUsuario(),
                    "Los usuarios deben estar ordenados por ID ascendente");
        }
    }
    
    @Test
    void testComparatorPorNombre() {
        // Test para verificar ordenación por nombre
        Usuario[] usuariosOrdenados = usuarios.clone();
        Arrays.sort(usuariosOrdenados, new OrdenarUsuarioNombre());
        
        // Verificar que están ordenados alfabéticamente por nombre
        String[] nombresEsperados = {"Ana", "Carlos", "Juan", "Luis", "María"};
        for (int i = 0; i < usuariosOrdenados.length; i++) {
            assertEquals(nombresEsperados[i], usuariosOrdenados[i].getNombre(),
                    "Los usuarios deben estar ordenados alfabéticamente por nombre");
        }
    }
    
    @Test
    void testComparatorPorApellido() {
        // Test para verificar ordenación por apellido
        Usuario[] usuariosOrdenados = usuarios.clone();
        Arrays.sort(usuariosOrdenados, new ec.edu.uce.dominio.OrdenarUsuarioApellido());
        
        // Verificar que están ordenados alfabéticamente por apellido
        String[] apellidosEsperados = {"García", "López", "Martínez", "Pérez", "Rodríguez"};
        for (int i = 0; i < usuariosOrdenados.length; i++) {
            assertEquals(apellidosEsperados[i], usuariosOrdenados[i].getApellido(),
                    "Los usuarios deben estar ordenados alfabéticamente por apellido");
        }
    }
    
    @Test
    void testComparatorPorCorreo() {
        // Test para verificar ordenación por correo
        Usuario[] usuariosOrdenados = usuarios.clone();
        Arrays.sort(usuariosOrdenados, new ec.edu.uce.dominio.OrdenarUsuarioCorreo());
        
        // Verificar que están ordenados alfabéticamente por correo
        String[] correosEsperados = {
            "ana.martinez@uce.edu.ec",
            "carlos.lopez@uce.edu.ec",
            "juan.perez@uce.edu.ec",
            "luis.rodriguez@uce.edu.ec",
            "maria.garcia@uce.edu.ec"
        };
        for (int i = 0; i < usuariosOrdenados.length; i++) {
            assertEquals(correosEsperados[i], usuariosOrdenados[i].getCorreo(),
                    "Los usuarios deben estar ordenados alfabéticamente por correo");
        }
    }
    
    @Test
    void testComparatorPorNumReservas() {
        // Test para verificar ordenación por número de reservas (descendente)
        Usuario[] usuariosOrdenados = usuarios.clone();
        Arrays.sort(usuariosOrdenados, new ec.edu.uce.dominio.OrdenarUsuarioNumReservas());
        
        // Verificar que están ordenados por número de reservas descendente
        int[] numReservasEsperadas = {3, 2, 1, 0, 0}; // Juan(3), Carlos(2), Ana(1), María(0), Luis(0)
        for (int i = 0; i < usuariosOrdenados.length; i++) {
            assertEquals(numReservasEsperadas[i], usuariosOrdenados[i].getNumReservas(),
                    "Los usuarios deben estar ordenados por número de reservas descendente");
        }
    }
    
    @Test
    void testOrdenarReservasPorFecha() {
        // Test para verificar ordenación de reservas por fecha
        Usuario usuario = usuarios[0];
        
        // Crear reservas con fechas diferentes
        Date fecha1 = new Date(2024, 0, 15, 9, 0); // 15 enero 2024, 9:00
        Date fecha2 = new Date(2024, 0, 16, 14, 0); // 16 enero 2024, 14:00
        Date fecha3 = new Date(2024, 0, 14, 10, 0); // 14 enero 2024, 10:00
        
        usuario.crearReserva(new Reserva(fecha1, fecha2));
        usuario.crearReserva(new Reserva(fecha2, fecha3));
        usuario.crearReserva(new Reserva(fecha3, fecha1));
        
        // Ordenar reservas por fecha
        usuario.ordenarReservasPorFecha();
        
        // Verificar que las reservas están ordenadas por fecha de inicio
        Reserva[] reservas = usuario.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getFechaInicio().compareTo(reservas[i + 1].getFechaInicio()) <= 0,
                    "Las reservas deben estar ordenadas por fecha de inicio ascendente");
        }
    }
    
    @Test
    void testOrdenarReservasPorEstado() {
        // Test para verificar ordenación de reservas por estado
        Usuario usuario = usuarios[0];
        
        // Crear reservas con diferentes estados
        Reserva reserva1 = new Reserva(new Date(), new Date());
        reserva1.setEstado(Estado.CONFIRMADA);
        
        Reserva reserva2 = new Reserva(new Date(), new Date());
        reserva2.setEstado(Estado.PENDIENTE);
        
        Reserva reserva3 = new Reserva(new Date(), new Date());
        reserva3.setEstado(Estado.CANCELADA);
        
        usuario.crearReserva(reserva1);
        usuario.crearReserva(reserva2);
        usuario.crearReserva(reserva3);
        
        // Ordenar reservas por estado
        usuario.ordenarReservasPorEstado();
        
        // Verificar que las reservas están ordenadas por estado
        Reserva[] reservas = usuario.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getEstado().getDescripcion().compareTo(
                    reservas[i + 1].getEstado().getDescripcion()) <= 0,
                    "Las reservas deben estar ordenadas por estado alfabéticamente");
        }
    }
    
    @Test
    void testOrdenarReservasPorId() {
        // Test para verificar ordenación de reservas por ID
        Usuario usuario = usuarios[0];
        
        // Crear reservas (los IDs se generan automáticamente)
        usuario.crearReserva(new Reserva(new Date(), new Date()));
        usuario.crearReserva(new Reserva(new Date(), new Date()));
        usuario.crearReserva(new Reserva(new Date(), new Date()));
        
        // Ordenar reservas por ID
        usuario.ordenarReservasPorId();
        
        // Verificar que las reservas están ordenadas por ID
        Reserva[] reservas = usuario.getReservas();
        for (int i = 0; i < reservas.length - 1; i++) {
            assertTrue(reservas[i].getIdReserva() <= reservas[i + 1].getIdReserva(),
                    "Las reservas deben estar ordenadas por ID ascendente");
        }
    }
    
    @Test
    void testComparableConsistencia() {
        // Test para verificar consistencia de Comparable
        Usuario usuario1 = usuarios[0];
        Usuario usuario2 = usuarios[1];
        
        // Verificar que compareTo es consistente
        int resultado1 = usuario1.compareTo(usuario2);
        int resultado2 = usuario2.compareTo(usuario1);
        
        assertTrue((resultado1 > 0 && resultado2 < 0) || (resultado1 < 0 && resultado2 > 0) || (resultado1 == 0 && resultado2 == 0),
                "compareTo debe ser consistente");
    }
    
    @Test
    void testComparatorTransitividad() {
        // Test para verificar transitividad de Comparator
        Usuario usuario1 = usuarios[0];
        Usuario usuario2 = usuarios[1];
        Usuario usuario3 = usuarios[2];
        
        // Verificar transitividad del comparador por nombre
        int resultado1 = new OrdenarUsuarioNombre().compare(usuario1, usuario2);
        int resultado2 = new OrdenarUsuarioNombre().compare(usuario2, usuario3);
        int resultado3 = new OrdenarUsuarioNombre().compare(usuario1, usuario3);
        
        if (resultado1 > 0 && resultado2 > 0) {
            assertTrue(resultado3 > 0, "El comparador debe ser transitivo");
        } else if (resultado1 < 0 && resultado2 < 0) {
            assertTrue(resultado3 < 0, "El comparador debe ser transitivo");
        }
    }
} 