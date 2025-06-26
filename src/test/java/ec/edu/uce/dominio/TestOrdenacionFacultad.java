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
 * Test unitario para la implementación de ordenación en la clase Facultad.
 */
public class TestOrdenacionFacultad {
    
    private Facultad[] facultades;
    
    @BeforeEach
    void setUp() {
        // Crear facultades de prueba con diferentes características
        Map<String, Facultad> facultadesMap = new HashMap<>();
        Facultad f1 = new Facultad("Facultad de Ingeniería");
        Facultad f2 = new Facultad("Facultad de Ciencias");
        Facultad f3 = new Facultad("Facultad de Medicina");
        Facultad f4 = new Facultad("Facultad de Derecho");
        Facultad f5 = new Facultad("Facultad de Economía");
        facultadesMap.put(f1.getCodigoFacultad(), f1);
        facultadesMap.put(f2.getCodigoFacultad(), f2);
        facultadesMap.put(f3.getCodigoFacultad(), f3);
        facultadesMap.put(f4.getCodigoFacultad(), f4);
        facultadesMap.put(f5.getCodigoFacultad(), f5);
        facultades = facultadesMap.values().toArray(new Facultad[0]);
        
        // Agregar auditorios y usuarios para probar ordenación
        facultades[0].crearAuditorio(new Auditorio("Auditorio A", 150));
        facultades[0].crearAuditorio(new Auditorio("Auditorio B", 200));
        facultades[0].crearUsuario(new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec"));
        facultades[0].crearUsuario(new Usuario("María", "García", "maria.garcia@uce.edu.ec"));
        facultades[1].crearAuditorio(new Auditorio("Auditorio C", 100));
        facultades[1].crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"));
        facultades[2].crearAuditorio(new Auditorio("Auditorio D", 300));
        facultades[2].crearAuditorio(new Auditorio("Auditorio E", 120));
        facultades[2].crearAuditorio(new Auditorio("Auditorio F", 180));
        facultades[2].crearUsuario(new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec"));
        facultades[2].crearUsuario(new Usuario("Luis", "Rodríguez", "luis.rodriguez@uce.edu.ec"));
        facultades[2].crearUsuario(new Usuario("Patricia", "Hernández", "patricia.hernandez@uce.edu.ec"));
    }
    
    @Test
    void testComparablePorId() {
        // Test para verificar que Comparable ordena por ID
        Facultad[] facultadesOrdenadas = facultades.clone();
        Arrays.sort(facultadesOrdenadas);
        
        // Verificar que están ordenadas por ID ascendente
        for (int i = 0; i < facultadesOrdenadas.length - 1; i++) {
            assertTrue(facultadesOrdenadas[i].getIdFacultad() <= facultadesOrdenadas[i + 1].getIdFacultad(),
                    "Las facultades deben estar ordenadas por ID ascendente");
        }
    }
    
    @Test
    void testComparatorPorNumAuditorios() {
        // Test para verificar ordenación por número de auditorios (descendente)
        Facultad[] facultadesOrdenadas = facultades.clone();
        Arrays.sort(facultadesOrdenadas, Facultad.COMPARADOR_POR_NUM_AUDITORIOS);
        
        // Verificar que están ordenadas por número de auditorios descendente
        int[] numAuditoriosEsperados = {3, 2, 1, 0, 0}; // Medicina(3), Ingeniería(2), Ciencias(1), Derecho(0), Economía(0)
        for (int i = 0; i < facultadesOrdenadas.length; i++) {
            assertEquals(numAuditoriosEsperados[i], facultadesOrdenadas[i].getNumAuditorios(),
                    "Las facultades deben estar ordenadas por número de auditorios descendente");
        }
    }
    
    @Test
    void testComparatorPorNumUsuarios() {
        // Test para verificar ordenación por número de usuarios (descendente)
        Facultad[] facultadesOrdenadas = facultades.clone();
        Arrays.sort(facultadesOrdenadas, Facultad.COMPARADOR_POR_NUM_USUARIOS);
        
        // Verificar que están ordenadas por número de usuarios descendente
        int[] numUsuariosEsperados = {3, 2, 1, 0, 0}; // Medicina(3), Ingeniería(2), Ciencias(1), Derecho(0), Economía(0)
        for (int i = 0; i < facultadesOrdenadas.length; i++) {
            assertEquals(numUsuariosEsperados[i], facultadesOrdenadas[i].getNumUsuarios(),
                    "Las facultades deben estar ordenadas por número de usuarios descendente");
        }
    }
    
    @Test
    void testComparatorPorId() {
        // Test para verificar ordenación por ID usando Comparator
        Facultad[] facultadesOrdenadas = facultades.clone();
        Arrays.sort(facultadesOrdenadas, Facultad.COMPARADOR_POR_ID);
        
        // Verificar que están ordenadas por ID ascendente
        for (int i = 0; i < facultadesOrdenadas.length - 1; i++) {
            assertTrue(facultadesOrdenadas[i].getIdFacultad() <= facultadesOrdenadas[i + 1].getIdFacultad(),
                    "Las facultades deben estar ordenadas por ID ascendente");
        }
    }
    
    @Test
    void testOrdenarAuditoriosPorNombre() {
        // Test para verificar ordenación de auditorios por nombre
        Facultad facultad = facultades[0];
        
        // Crear auditorios con nombres diferentes
        facultad.crearAuditorio(new Auditorio("Auditorio Z", 100));
        facultad.crearAuditorio(new Auditorio("Auditorio X", 150));
        facultad.crearAuditorio(new Auditorio("Auditorio Y", 200));
        
        // Ordenar auditorios por nombre
        facultad.ordenarAuditoriosPorNombre();
        
        // Verificar que los auditorios están ordenados alfabéticamente por nombre
        Auditorio[] auditorios = facultad.getAuditorios();
        for (int i = 0; i < auditorios.length - 1; i++) {
            assertTrue(auditorios[i].getNombre().compareTo(auditorios[i + 1].getNombre()) <= 0,
                    "Los auditorios deben estar ordenados alfabéticamente por nombre");
        }
    }
    
    @Test
    void testOrdenarAuditoriosPorCapacidad() {
        // Test para verificar ordenación de auditorios por capacidad
        Facultad facultad = facultades[0];
        
        // Crear auditorios con capacidades diferentes
        facultad.crearAuditorio(new Auditorio("Auditorio Grande", 500));
        facultad.crearAuditorio(new Auditorio("Auditorio Pequeño", 50));
        facultad.crearAuditorio(new Auditorio("Auditorio Mediano", 250));
        
        // Ordenar auditorios por capacidad
        facultad.ordenarAuditoriosPorCapacidad();
        
        // Verificar que los auditorios están ordenados por capacidad ascendente
        Auditorio[] auditorios = facultad.getAuditorios();
        for (int i = 0; i < auditorios.length - 1; i++) {
            assertTrue(auditorios[i].getCapacidad() <= auditorios[i + 1].getCapacidad(),
                    "Los auditorios deben estar ordenados por capacidad ascendente");
        }
    }
    
    @Test
    void testOrdenarAuditoriosPorNumReservas() {
        // Test para verificar ordenación de auditorios por número de reservas
        Facultad facultad = facultades[0];
        
        // Crear auditorios y agregar reservas
        Auditorio auditorio1 = new Auditorio("Auditorio 1", 100);
        auditorio1.crearReserva(new Reserva(new Date(), new Date()));
        auditorio1.crearReserva(new Reserva(new Date(), new Date()));
        
        Auditorio auditorio2 = new Auditorio("Auditorio 2", 150);
        auditorio2.crearReserva(new Reserva(new Date(), new Date()));
        
        Auditorio auditorio3 = new Auditorio("Auditorio 3", 200);
        auditorio3.crearReserva(new Reserva(new Date(), new Date()));
        auditorio3.crearReserva(new Reserva(new Date(), new Date()));
        auditorio3.crearReserva(new Reserva(new Date(), new Date()));
        
        facultad.crearAuditorio(auditorio1);
        facultad.crearAuditorio(auditorio2);
        facultad.crearAuditorio(auditorio3);
        
        // Ordenar auditorios por número de reservas
        facultad.ordenarAuditoriosPorNumReservas();
        
        // Verificar que los auditorios están ordenados por número de reservas descendente
        Auditorio[] auditorios = facultad.getAuditorios();
        for (int i = 0; i < auditorios.length - 1; i++) {
            assertTrue(auditorios[i].getNumReservas() >= auditorios[i + 1].getNumReservas(),
                    "Los auditorios deben estar ordenados por número de reservas descendente");
        }
    }
    
    @Test
    void testOrdenarUsuariosPorNombre() {
        // Test para verificar ordenación de usuarios por nombre
        Facultad facultad = facultades[0];
        
        // Crear usuarios con nombres diferentes
        facultad.crearUsuario(new Usuario("Zara", "Pérez", "zara.perez@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("Ana", "García", "ana.garcia@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"));
        
        // Ordenar usuarios por nombre
        facultad.ordenarUsuariosPorNombre();
        
        // Verificar que los usuarios están ordenados alfabéticamente por nombre
        Usuario[] usuarios = facultad.getUsuarios();
        for (int i = 0; i < usuarios.length - 1; i++) {
            assertTrue(usuarios[i].getNombre().compareTo(usuarios[i + 1].getNombre()) <= 0,
                    "Los usuarios deben estar ordenados alfabéticamente por nombre");
        }
    }
    
    @Test
    void testOrdenarUsuariosPorApellido() {
        // Test para verificar ordenación de usuarios por apellido
        Facultad facultad = facultades[0];
        
        // Crear usuarios con apellidos diferentes
        facultad.crearUsuario(new Usuario("Juan", "Zapata", "juan.zapata@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("María", "García", "maria.garcia@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"));
        
        // Ordenar usuarios por apellido
        facultad.ordenarUsuariosPorApellido();
        
        // Verificar que los usuarios están ordenados alfabéticamente por apellido
        Usuario[] usuarios = facultad.getUsuarios();
        for (int i = 0; i < usuarios.length - 1; i++) {
            assertTrue(usuarios[i].getApellido().compareTo(usuarios[i + 1].getApellido()) <= 0,
                    "Los usuarios deben estar ordenados alfabéticamente por apellido");
        }
    }
    
    @Test
    void testOrdenarUsuariosPorCorreo() {
        // Test para verificar ordenación de usuarios por correo
        Facultad facultad = facultades[0];
        
        // Crear usuarios con correos diferentes
        facultad.crearUsuario(new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("María", "García", "maria.garcia@uce.edu.ec"));
        facultad.crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"));
        
        // Ordenar usuarios por correo
        facultad.ordenarUsuariosPorCorreo();
        
        // Verificar que los usuarios están ordenados alfabéticamente por correo
        Usuario[] usuarios = facultad.getUsuarios();
        for (int i = 0; i < usuarios.length - 1; i++) {
            assertTrue(usuarios[i].getCorreo().compareTo(usuarios[i + 1].getCorreo()) <= 0,
                    "Los usuarios deben estar ordenados alfabéticamente por correo");
        }
    }
    
    @Test
    void testOrdenarUsuariosPorNumReservas() {
        // Test para verificar ordenación de usuarios por número de reservas
        Facultad facultad = facultades[0];
        
        // Crear usuarios y agregar reservas
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        usuario1.crearReserva(new Reserva(new Date(), new Date()));
        usuario1.crearReserva(new Reserva(new Date(), new Date()));
        
        Usuario usuario2 = new Usuario("María", "García", "maria.garcia@uce.edu.ec");
        usuario2.crearReserva(new Reserva(new Date(), new Date()));
        
        Usuario usuario3 = new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec");
        usuario3.crearReserva(new Reserva(new Date(), new Date()));
        usuario3.crearReserva(new Reserva(new Date(), new Date()));
        usuario3.crearReserva(new Reserva(new Date(), new Date()));
        
        facultad.crearUsuario(usuario1);
        facultad.crearUsuario(usuario2);
        facultad.crearUsuario(usuario3);
        
        // Ordenar usuarios por número de reservas
        facultad.ordenarUsuariosPorNumReservas();
        
        // Verificar que los usuarios están ordenados por número de reservas descendente
        Usuario[] usuarios = facultad.getUsuarios();
        for (int i = 0; i < usuarios.length - 1; i++) {
            assertTrue(usuarios[i].getNumReservas() >= usuarios[i + 1].getNumReservas(),
                    "Los usuarios deben estar ordenados por número de reservas descendente");
        }
    }
    
    @Test
    void testComparableConsistencia() {
        // Test para verificar consistencia de Comparable
        Facultad facultad1 = facultades[0];
        Facultad facultad2 = facultades[1];
        
        // Verificar que compareTo es consistente
        int resultado1 = facultad1.compareTo(facultad2);
        int resultado2 = facultad2.compareTo(facultad1);
        
        assertTrue((resultado1 > 0 && resultado2 < 0) || (resultado1 < 0 && resultado2 > 0) || (resultado1 == 0 && resultado2 == 0),
                "compareTo debe ser consistente");
    }
    
    @Test
    void testComparatorTransitividad() {
        // Test para verificar transitividad de Comparator
        Facultad facultad1 = facultades[0];
        Facultad facultad2 = facultades[1];
        Facultad facultad3 = facultades[2];
        
        // Verificar transitividad del comparador por número de auditorios
        int resultado1 = Facultad.COMPARADOR_POR_NUM_AUDITORIOS.compare(facultad1, facultad2);
        int resultado2 = Facultad.COMPARADOR_POR_NUM_AUDITORIOS.compare(facultad2, facultad3);
        int resultado3 = Facultad.COMPARADOR_POR_NUM_AUDITORIOS.compare(facultad1, facultad3);
        
        if (resultado1 > 0 && resultado2 > 0) {
            assertTrue(resultado3 > 0, "El comparador debe ser transitivo");
        } else if (resultado1 < 0 && resultado2 < 0) {
            assertTrue(resultado3 < 0, "El comparador debe ser transitivo");
        }
    }
} 