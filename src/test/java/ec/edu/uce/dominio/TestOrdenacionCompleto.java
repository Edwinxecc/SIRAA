package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Test completo para evidenciar rápidamente la implementación de ordenamientos.
 * Este test demuestra todos los métodos de ordenación implementados en las clases del dominio.
 */
public class TestOrdenacionCompleto {
    
    public static void main(String[] args) {
        System.out.println("=== TEST COMPLETO DE ORDENACIÓN SIRAA ===\n");
        
        // Test 1: Ordenación de Usuarios
        testOrdenacionUsuarios();
        
        // Test 2: Ordenación de Auditorios
        testOrdenacionAuditorios();
        
        // Test 3: Ordenación de Facultades
        testOrdenacionFacultades();
        
        // Test 4: Ordenación de Universidad
        testOrdenacionUniversidad();
        
        // Test 5: Ordenación de Reservas
        testOrdenacionReservas();
        
        // Test 6: Ordenación de Equipos
        testOrdenacionEquipos();
        
        System.out.println("\n=== TODOS LOS TESTS DE ORDENACIÓN COMPLETADOS ===");
    }
    
    private static void testOrdenacionUsuarios() {
        System.out.println("🔹 TEST ORDENACIÓN DE USUARIOS");
        System.out.println("=" .repeat(50));
        
        // Simular colección real con Map
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
        Collection<Usuario> usuariosCol = usuariosMap.values();
        Usuario[] usuarios = usuariosCol.toArray(new Usuario[0]);
        
        // Agregar reservas para probar ordenación por número de reservas
        usuarios[0].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[0].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[1].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
        usuarios[2].crearReserva(new Reserva(new Date(), new Date()));
        
        System.out.println("📋 Usuarios originales:");
        mostrarUsuarios(usuarios);
        
        // Ejemplo de uso para usuarios:
        Arrays.sort(usuarios, new ec.edu.uce.dominio.OrdenarUsuarioApellido());
        System.out.println("\n📊 Usuarios ordenados por apellido:");
        mostrarUsuarios(usuarios);
        
        // Test Comparator por correo
        Usuario[] usuariosPorCorreo = usuarios.clone();
        Arrays.sort(usuariosPorCorreo, new ec.edu.uce.dominio.OrdenarUsuarioCorreo());
        System.out.println("\n📊 Usuarios ordenados por correo:");
        mostrarUsuarios(usuariosPorCorreo);
        
        // Test Comparator por número de reservas
        Usuario[] usuariosPorReservas = usuarios.clone();
        Arrays.sort(usuariosPorReservas, new ec.edu.uce.dominio.OrdenarUsuarioNumReservas());
        System.out.println("\n📊 Usuarios ordenados por número de reservas (descendente):");
        mostrarUsuarios(usuariosPorReservas);
        
        // Test ordenación de reservas de un usuario
        System.out.println("\n📋 Reservas de " + usuarios[0].getNombre() + " antes de ordenar:");
        mostrarReservas(usuarios[0].getReservas());
        
        usuarios[0].ordenarReservasPorFecha();
        System.out.println("\n📊 Reservas ordenadas por fecha:");
        mostrarReservas(usuarios[0].getReservas());
        
        System.out.println();
    }
    
    private static void testOrdenacionAuditorios() {
        System.out.println("🔹 TEST ORDENACIÓN DE AUDITORIOS");
        System.out.println("=" .repeat(50));
        
        // Crear auditorios de prueba
        Auditorio[] auditorios = {
            new Auditorio("Auditorio A", 150),
            new Auditorio("Auditorio B", 200),
            new Auditorio("Auditorio C", 100),
            new Auditorio("Auditorio D", 300),
            new Auditorio("Auditorio E", 120)
        };
        
        // Agregar reservas para probar ordenación por número de reservas
        auditorios[0].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[0].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[1].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[2].crearReserva(new Reserva(new Date(), new Date()));
        auditorios[3].crearReserva(new Reserva(new Date(), new Date()));
        
        System.out.println("📋 Auditorios originales:");
        mostrarAuditorios(auditorios);
        
        // Ejemplo de uso para auditorios:
        Arrays.sort(auditorios, new ec.edu.uce.dominio.OrdenarAuditorioNombre());
        System.out.println("\n📊 Auditorios ordenados por nombre:");
        mostrarAuditorios(auditorios);
        
        // Test Comparator por capacidad
        Auditorio[] auditoriosPorCapacidad = auditorios.clone();
        Arrays.sort(auditoriosPorCapacidad, new OrdenarAuditorioCapacidad());
        System.out.println("\n📊 Auditorios ordenados por capacidad (ascendente):");
        mostrarAuditorios(auditoriosPorCapacidad);
        
        // Test Comparator por número de reservas
        Auditorio[] auditoriosPorReservas = auditorios.clone();
        Arrays.sort(auditoriosPorReservas, new OrdenarAuditorioNumReservas());
        System.out.println("\n📊 Auditorios ordenados por número de reservas (descendente):");
        mostrarAuditorios(auditoriosPorReservas);
        
        // Test ordenación de reservas de un auditorio
        System.out.println("\n📋 Reservas del " + auditorios[0].getNombre() + " antes de ordenar:");
        mostrarReservas(auditorios[0].getReservas());
        
        auditorios[0].ordenarReservasPorFecha();
        System.out.println("\n📊 Reservas ordenadas por fecha:");
        mostrarReservas(auditorios[0].getReservas());
        
        System.out.println();
    }
    
    private static void testOrdenacionFacultades() {
        System.out.println("🔹 TEST ORDENACIÓN DE FACULTADES");
        System.out.println("=" .repeat(50));
        
        // Crear facultades de prueba
        Facultad[] facultades = {
            new Facultad("Facultad de Ingeniería"),
            new Facultad("Facultad de Ciencias"),
            new Facultad("Facultad de Medicina"),
            new Facultad("Facultad de Derecho"),
            new Facultad("Facultad de Economía")
        };
        
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
        
        System.out.println("📋 Facultades originales:");
        mostrarFacultades(facultades);
        
        // Ejemplo de uso para facultades:
        Arrays.sort(facultades, new ec.edu.uce.dominio.OrdenarFacultadNombre());
        System.out.println("\n📊 Facultades ordenadas por nombre:");
        mostrarFacultades(facultades);
        
        // Test Comparator por número de auditorios
        Facultad[] facultadesPorAuditorios = facultades.clone();
        Arrays.sort(facultadesPorAuditorios, new OrdenarFacultadNumAuditorios());
        System.out.println("\n📊 Facultades ordenadas por número de auditorios (descendente):");
        mostrarFacultades(facultadesPorAuditorios);
        
        // Test Comparator por número de usuarios
        Facultad[] facultadesPorUsuarios = facultades.clone();
        Arrays.sort(facultadesPorUsuarios, new OrdenarFacultadNumUsuarios());
        System.out.println("\n📊 Facultades ordenadas por número de usuarios (descendente):");
        mostrarFacultades(facultadesPorUsuarios);
        
        // Test ordenación de auditorios de una facultad
        System.out.println("\n📋 Auditorios de " + facultades[0].getNombre() + " antes de ordenar:");
        mostrarAuditorios(facultades[0].getAuditorios());
        
        facultades[0].ordenarAuditoriosPorNombre();
        System.out.println("\n📊 Auditorios ordenados por nombre:");
        mostrarAuditorios(facultades[0].getAuditorios());
        
        // Test ordenación de usuarios de una facultad
        System.out.println("\n📋 Usuarios de " + facultades[0].getNombre() + " antes de ordenar:");
        mostrarUsuarios(facultades[0].getUsuarios());
        
        facultades[0].ordenarUsuariosPorNombre();
        System.out.println("\n📊 Usuarios ordenados por nombre:");
        mostrarUsuarios(facultades[0].getUsuarios());
        
        System.out.println();
    }
    
    private static void testOrdenacionUniversidad() {
        System.out.println("🔹 TEST ORDENACIÓN DE UNIVERSIDAD");
        System.out.println("=" .repeat(50));
        
        // Obtener la instancia única de Universidad
        Universidad universidad = Universidad.getInstancia();
        
        // Limpiar facultades existentes
        while (universidad.getNumFacultades() > 0) {
            Facultad[] facultades = universidad.getFacultades();
            universidad.eliminarFacultad(facultades[0].getCodigoFacultad());
        }
        
        // Crear facultades de prueba
        universidad.crearFacultad(new Facultad("Facultad de Ingeniería"));
        universidad.crearFacultad(new Facultad("Facultad de Ciencias"));
        universidad.crearFacultad(new Facultad("Facultad de Medicina"));
        universidad.crearFacultad(new Facultad("Facultad de Derecho"));
        universidad.crearFacultad(new Facultad("Facultad de Economía"));
        
        System.out.println("📋 Facultades de la Universidad antes de ordenar:");
        mostrarFacultades(universidad.getFacultades());
        
        // Test ordenación de facultades por nombre
        universidad.ordenarFacultadesPorNombre();
        System.out.println("\n📊 Facultades ordenadas por nombre:");
        mostrarFacultades(universidad.getFacultades());
        
        // Test ordenación de facultades por ID
        universidad.ordenarFacultadesPorId();
        System.out.println("\n📊 Facultades ordenadas por ID:");
        mostrarFacultades(universidad.getFacultades());
        
        System.out.println();
    }
    
    private static void testOrdenacionReservas() {
        System.out.println("🔹 TEST ORDENACIÓN DE RESERVAS");
        System.out.println("=" .repeat(50));
        
        // Crear reservas de prueba con diferentes fechas
        Date fecha1 = new Date(2024, 0, 15, 9, 0); // 15 enero 2024, 9:00
        Date fecha2 = new Date(2024, 0, 16, 14, 0); // 16 enero 2024, 14:00
        Date fecha3 = new Date(2024, 0, 14, 10, 0); // 14 enero 2024, 10:00
        Date fecha4 = new Date(2024, 0, 17, 16, 0); // 17 enero 2024, 16:00
        
        Reserva[] reservas = {
            new Reserva(fecha1, fecha2),
            new Reserva(fecha2, fecha3),
            new Reserva(fecha3, fecha1),
            new Reserva(fecha4, fecha2)
        };
        
        // Establecer diferentes estados
        reservas[0].setEstado(Estado.CONFIRMADA);
        reservas[1].setEstado(Estado.PENDIENTE);
        reservas[2].setEstado(Estado.CANCELADA);
        reservas[3].setEstado(Estado.CONFIRMADA);
        
        System.out.println("📋 Reservas originales:");
        mostrarReservas(reservas);
        
        // Ejemplo de uso para reservas:
        Arrays.sort(reservas, new ec.edu.uce.dominio.OrdenarReservaFechaInicio());
        System.out.println("\n📊 Reservas ordenadas por fecha de inicio:");
        mostrarReservas(reservas);
        
        // Test Comparator por fecha de fin
        Reserva[] reservasPorFechaFin = reservas.clone();
        Arrays.sort(reservasPorFechaFin, new OrdenarReservaFechaFin());
        System.out.println("\n📊 Reservas ordenadas por fecha de fin:");
        mostrarReservas(reservasPorFechaFin);
        
        // Test Comparator por estado
        Reserva[] reservasPorEstado = reservas.clone();
        Arrays.sort(reservasPorEstado, new OrdenarReservaEstado());
        System.out.println("\n📊 Reservas ordenadas por estado:");
        mostrarReservas(reservasPorEstado);
        
        System.out.println();
    }
    
    private static void testOrdenacionEquipos() {
        System.out.println("🔹 TEST ORDENACIÓN DE EQUIPOS");
        System.out.println("=" .repeat(50));
        
        // Crear equipos de prueba
        Equipo[] equipos = {
            new Equipo("Proyector", "Visual", true),
            new Equipo("Micrófono", "Audio", true),
            new Equipo("Parlante", "Audio", false),
            new Equipo("Computadora", "Computación", true),
            new Equipo("Pizarra", "Visual", false)
        };
        
        System.out.println("📋 Equipos originales:");
        mostrarEquipos(equipos);
        
        // Ejemplo de uso para equipos:
        Arrays.sort(equipos, new ec.edu.uce.dominio.OrdenarEquipoNombre());
        System.out.println("\n📊 Equipos ordenados por nombre (Comparable):");
        mostrarEquipos(equipos);
        
        // Test Comparator por categoría
        Equipo[] equiposPorCategoria = equipos.clone();
        Arrays.sort(equiposPorCategoria, new OrdenarEquipoCategoria());
        System.out.println("\n📊 Equipos ordenados por categoría:");
        mostrarEquipos(equiposPorCategoria);
        
        // Test Comparator por disponibilidad
        Equipo[] equiposPorDisponibilidad = equipos.clone();
        Arrays.sort(equiposPorDisponibilidad, new OrdenarEquipoDisponibilidad());
        System.out.println("\n📊 Equipos ordenados por disponibilidad (disponibles primero):");
        mostrarEquipos(equiposPorDisponibilidad);
        
        // Test Comparator por estado
        Equipo[] equiposPorEstado = equipos.clone();
        Arrays.sort(equiposPorEstado, new OrdenarEquipoEstado());
        System.out.println("\n📊 Equipos ordenados por estado:");
        mostrarEquipos(equiposPorEstado);
        
        // Test Comparator por ID
        Equipo[] equiposPorId = equipos.clone();
        Arrays.sort(equiposPorId, new OrdenarEquipoId());
        System.out.println("\n📊 Equipos ordenados por ID:");
        mostrarEquipos(equiposPorId);
        
        System.out.println();
    }
    
    // Métodos auxiliares para mostrar los arrays
    private static void mostrarUsuarios(Usuario[] usuarios) {
        for (int i = 0; i < usuarios.length; i++) {
            System.out.printf("[%d] ID: %d | %s %s | %s | Reservas: %d%n", 
                i, usuarios[i].getIdUsuario(), usuarios[i].getNombre(), 
                usuarios[i].getApellido(), usuarios[i].getCorreo(), 
                usuarios[i].getNumReservas());
        }
    }
    
    private static void mostrarAuditorios(Auditorio[] auditorios) {
        for (int i = 0; i < auditorios.length; i++) {
            System.out.printf("[%d] ID: %d | %s | Capacidad: %d | Reservas: %d%n", 
                i, auditorios[i].getIdAuditorio(), auditorios[i].getNombre(), 
                auditorios[i].getCapacidad(), auditorios[i].getNumReservas());
        }
    }
    
    private static void mostrarFacultades(Facultad[] facultades) {
        for (int i = 0; i < facultades.length; i++) {
            System.out.printf("[%d] ID: %d | %s | Auditorios: %d | Usuarios: %d%n", 
                i, facultades[i].getIdFacultad(), facultades[i].getNombre(), 
                facultades[i].getNumAuditorios(), facultades[i].getNumUsuarios());
        }
    }
    
    private static void mostrarReservas(Reserva[] reservas) {
        for (int i = 0; i < reservas.length; i++) {
            System.out.printf("[%d] ID: %d | Inicio: %s | Fin: %s | Estado: %s%n", 
                i, reservas[i].getIdReserva(), 
                reservas[i].getFechaInicio().toString().substring(0, 16), 
                reservas[i].getFechaFin().toString().substring(0, 16), 
                reservas[i].getEstado().getDescripcion());
        }
    }
    
    private static void mostrarEquipos(Equipo[] equipos) {
        for (int i = 0; i < equipos.length; i++) {
            System.out.printf("[%d] ID: %d | %s | Categoría: %s | Disponible: %s | Estado: %s%n", 
                i, equipos[i].getIdEquipo(), equipos[i].getNombre(), 
                equipos[i].getCategoria(), equipos[i].getDisponibilidad() ? "Sí" : "No", equipos[i].getEstado().getDescripcion());
        }
    }
} 