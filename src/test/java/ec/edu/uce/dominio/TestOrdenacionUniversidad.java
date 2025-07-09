package ec.edu.uce.dominio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Date;

/**
 * Test unitario para la implementación de ordenación en la clase Universidad.
 */
public class TestOrdenacionUniversidad {
    
    private Universidad universidad;
    
    @BeforeEach
    void setUp() {
        // Obtener la instancia única de Universidad
        universidad = Universidad.getInstancia();
        
        // Limpiar facultades existentes para el test
        while (universidad.getNumFacultades() > 0) {
            Facultad[] facultades = universidad.getFacultades();
            universidad.eliminarFacultad(facultades[0].getCodigoFacultad());
        }
        
        // Agregar algunas facultades para probar ordenación
        universidad.crearFacultad(new Facultad("Facultad de Ingeniería"));
        universidad.crearFacultad(new Facultad("Facultad de Ciencias"));
        universidad.crearFacultad(new Facultad("Facultad de Medicina"));
    }
    
    @Test
    void testComparablePorId() {
        // Test para verificar que Comparable funciona (aunque solo hay una instancia)
        Universidad otraUniversidad = Universidad.getInstancia();
        assertEquals(0, universidad.compareTo(otraUniversidad),
                "Comparable debe retornar 0 para la misma instancia");
    }
    
    @Test
    void testComparatorPorNumFacultades() {
        // Test para verificar ordenación por número de facultades (descendente)
        // Como solo hay una instancia, probamos el comparador directamente
        Universidad u1 = Universidad.getInstancia();
        Universidad u2 = Universidad.getInstancia();
        
        // Deberían ser iguales ya que es la misma instancia
        assertEquals(0, new OrdenarUniversidadNumFacultades().compare(u1, u2),
                "El comparador debe retornar 0 para universidades con igual número de facultades");
    }
    
    @Test
    void testOrdenarFacultadesPorNombre() {
        // Test para verificar ordenación de facultades por nombre
        // Crear facultades con nombres diferentes
        universidad.crearFacultad(new Facultad("Facultad de Zootecnia"));
        universidad.crearFacultad(new Facultad("Facultad de Arquitectura"));
        universidad.crearFacultad(new Facultad("Facultad de Administración"));
        
        // Ordenar facultades por nombre
        universidad.ordenarFacultadesPorNombre();
        
        // Verificar que las facultades están ordenadas alfabéticamente por nombre
        Facultad[] facultades = universidad.getFacultades();
        for (int i = 0; i < facultades.length - 1; i++) {
            assertTrue(facultades[i].getNombre().compareTo(facultades[i + 1].getNombre()) <= 0,
                    "Las facultades deben estar ordenadas alfabéticamente por nombre");
        }
    }
    
    @Test
    void testOrdenarFacultadesPorId() {
        // Test para verificar ordenación de facultades por ID
        // Crear facultades (los IDs se generan automáticamente)
        universidad.crearFacultad(new Facultad("Facultad de Economía"));
        universidad.crearFacultad(new Facultad("Facultad de Psicología"));
        universidad.crearFacultad(new Facultad("Facultad de Educación"));
        
        // Ordenar facultades por ID
        universidad.ordenarFacultadesPorId();
        
        // Verificar que las facultades están ordenadas por ID
        Facultad[] facultades = universidad.getFacultades();
        for (int i = 0; i < facultades.length - 1; i++) {
            assertTrue(facultades[i].getIdFacultad() <= facultades[i + 1].getIdFacultad(),
                    "Las facultades deben estar ordenadas por ID ascendente");
        }
    }
    
    @Test
    void testOrdenarFacultadesPorNumAuditorios() {
        // Test para verificar ordenación de facultades por número de auditorios
        // Crear facultades y agregar auditorios
        Facultad facultad1 = new Facultad("Facultad 1");
        facultad1.crearAuditorio(new Auditorio("Auditorio A", 100));
        facultad1.crearAuditorio(new Auditorio("Auditorio B", 150));
        
        Facultad facultad2 = new Facultad("Facultad 2");
        facultad2.crearAuditorio(new Auditorio("Auditorio C", 200));
        
        Facultad facultad3 = new Facultad("Facultad 3");
        facultad3.crearAuditorio(new Auditorio("Auditorio D", 300));
        facultad3.crearAuditorio(new Auditorio("Auditorio E", 250));
        facultad3.crearAuditorio(new Auditorio("Auditorio F", 180));
        
        universidad.crearFacultad(facultad1);
        universidad.crearFacultad(facultad2);
        universidad.crearFacultad(facultad3);
        
        // Ordenar facultades por número de auditorios
        universidad.ordenarFacultadesPorNumAuditorios();
        
        // Verificar que las facultades están ordenadas por número de auditorios descendente
        Facultad[] facultades = universidad.getFacultades();
        for (int i = 0; i < facultades.length - 1; i++) {
            assertTrue(facultades[i].getNumAuditorios() >= facultades[i + 1].getNumAuditorios(),
                    "Las facultades deben estar ordenadas por número de auditorios descendente");
        }
    }
    
    @Test
    void testOrdenarFacultadesPorNumUsuarios() {
        // Test para verificar ordenación de facultades por número de usuarios
        // Crear facultades y agregar usuarios
        Facultad facultad1 = new Facultad("Facultad 1");
        facultad1.crearUsuario(new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec"));
        facultad1.crearUsuario(new Usuario("María", "García", "maria.garcia@uce.edu.ec"));
        
        Facultad facultad2 = new Facultad("Facultad 2");
        facultad2.crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"));
        
        Facultad facultad3 = new Facultad("Facultad 3");
        facultad3.crearUsuario(new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec"));
        facultad3.crearUsuario(new Usuario("Luis", "Rodríguez", "luis.rodriguez@uce.edu.ec"));
        facultad3.crearUsuario(new Usuario("Patricia", "Hernández", "patricia.hernandez@uce.edu.ec"));
        
        universidad.crearFacultad(facultad1);
        universidad.crearFacultad(facultad2);
        universidad.crearFacultad(facultad3);
        
        // Ordenar facultades por número de usuarios
        universidad.ordenarFacultadesPorNumUsuarios();
        
        // Verificar que las facultades están ordenadas por número de usuarios descendente
        Facultad[] facultades = universidad.getFacultades();
        for (int i = 0; i < facultades.length - 1; i++) {
            assertTrue(facultades[i].getNumUsuarios() >= facultades[i + 1].getNumUsuarios(),
                    "Las facultades deben estar ordenadas por número de usuarios descendente");
        }
    }
    
    @Test
    void testComparableConsistencia() {
        // Test para verificar consistencia de Comparable
        Universidad universidad1 = Universidad.getInstancia();
        Universidad universidad2 = Universidad.getInstancia();
        
        // Verificar que compareTo es consistente para la misma instancia
        int resultado1 = universidad1.compareTo(universidad2);
        int resultado2 = universidad2.compareTo(universidad1);
        
        assertEquals(0, resultado1, "compareTo debe retornar 0 para la misma instancia");
        assertEquals(0, resultado2, "compareTo debe retornar 0 para la misma instancia");
    }
    
    @Test
    void testComparatorTransitividad() {
        // Test para verificar transitividad de Comparator
        Universidad universidad1 = Universidad.getInstancia();
        Universidad universidad2 = Universidad.getInstancia();
        Universidad universidad3 = Universidad.getInstancia();
        
        // Verificar transitividad del comparador por número de facultades
        int resultado1 = new OrdenarUniversidadNumFacultades().compare(universidad1, universidad2);
        int resultado2 = new OrdenarUniversidadNumFacultades().compare(universidad2, universidad3);
        int resultado3 = new OrdenarUniversidadNumFacultades().compare(universidad1, universidad3);
        
        // Como son la misma instancia, todos deben ser 0
        assertEquals(0, resultado1, "El comparador debe retornar 0 para la misma instancia");
        assertEquals(0, resultado2, "El comparador debe retornar 0 para la misma instancia");
        assertEquals(0, resultado3, "El comparador debe retornar 0 para la misma instancia");
    }
} 