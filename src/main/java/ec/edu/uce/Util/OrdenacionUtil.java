package ec.edu.uce.Util;

import ec.edu.uce.dominio.*;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

/**
 * Clase de utilidad para demostrar el uso de los métodos de ordenación
 * implementados en las clases del sistema SIRAA.
 */
public class OrdenacionUtil {

    /**
     * Demuestra la ordenación de usuarios
     */
    public static void demostrarOrdenacionUsuarios() {
        System.out.println("=== DEMOSTRACIÓN ORDENACIÓN DE USUARIOS ===");
        // Simular colección real con Map
        Map<String, Usuario> usuariosMap = new HashMap<>();
        Usuario u1 = new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec");
        Usuario u2 = new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec");
        Usuario u3 = new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        Usuario u4 = new Usuario("María", "García", "maria.garcia@uce.edu.ec");
        usuariosMap.put(u1.getCodigoUsuario(), u1);
        usuariosMap.put(u2.getCodigoUsuario(), u2);
        usuariosMap.put(u3.getCodigoUsuario(), u3);
        usuariosMap.put(u4.getCodigoUsuario(), u4);
        Collection<Usuario> usuariosCol = usuariosMap.values();
        Usuario[] usuarios = usuariosCol.toArray(new Usuario[0]);
        System.out.println("Usuarios originales:");
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        // Ordenar por ID (Comparable)
        System.out.println("\n--- Ordenados por ID (Comparable) ---");
        Arrays.sort(usuarios);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        // Ordenar por nombre (Comparator)
        System.out.println("\n--- Ordenados por nombre (Comparator) ---");
        Arrays.sort(usuarios, Usuario.COMPARADOR_POR_NOMBRE);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
        // Ordenar por apellido (Comparator)
        System.out.println("\n--- Ordenados por apellido (Comparator) ---");
        Arrays.sort(usuarios, Usuario.COMPARADOR_POR_APELLIDO);
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
        }
    }

    /**
     * Demuestra la ordenación de auditorios
     */
    public static void demostrarOrdenacionAuditorios() {
        System.out.println("\n=== DEMOSTRACIÓN ORDENACIÓN DE AUDITORIOS ===");
        Map<String, Auditorio> auditoriosMap = new HashMap<>();
        Auditorio a1 = new Auditorio("Sala de Conferencias", 75);
        Auditorio a2 = new Auditorio("Auditorio Principal", 100);
        Auditorio a3 = new Auditorio("Aula Magna", 200);
        Auditorio a4 = new Auditorio("Auditorio Secundario", 50);
        auditoriosMap.put(a1.getCodigoAuditorio(), a1);
        auditoriosMap.put(a2.getCodigoAuditorio(), a2);
        auditoriosMap.put(a3.getCodigoAuditorio(), a3);
        auditoriosMap.put(a4.getCodigoAuditorio(), a4);
        Auditorio[] auditorios = auditoriosMap.values().toArray(new Auditorio[0]);
        System.out.println("Auditorios originales:");
        for (Auditorio auditorio : auditorios) {
            System.out.println(auditorio);
        }
        System.out.println("\n--- Ordenados por nombre (Comparable) ---");
        Arrays.sort(auditorios);
        for (Auditorio auditorio : auditorios) {
            System.out.println(auditorio);
        }
        System.out.println("\n--- Ordenados por capacidad (Comparator) ---");
        Arrays.sort(auditorios, Auditorio.COMPARADOR_POR_CAPACIDAD);
        for (Auditorio auditorio : auditorios) {
            System.out.println(auditorio);
        }
    }

    /**
     * Demuestra la ordenación de facultades
     */
    public static void demostrarOrdenacionFacultades() {
        System.out.println("\n=== DEMOSTRACIÓN ORDENACIÓN DE FACULTADES ===");
        Map<String, Facultad> facultadesMap = new HashMap<>();
        Facultad f1 = new Facultad("Facultad de Medicina", 5);
        Facultad f2 = new Facultad("Facultad de Ingeniería", 5);
        Facultad f3 = new Facultad("Facultad de Derecho", 5);
        Facultad f4 = new Facultad("Facultad de Ciencias Sociales", 5);
        facultadesMap.put(f1.getCodigoFacultad(), f1);
        facultadesMap.put(f2.getCodigoFacultad(), f2);
        facultadesMap.put(f3.getCodigoFacultad(), f3);
        facultadesMap.put(f4.getCodigoFacultad(), f4);
        Facultad[] facultades = facultadesMap.values().toArray(new Facultad[0]);
        System.out.println("Facultades originales:");
        for (Facultad facultad : facultades) {
            System.out.println(facultad);
        }
        System.out.println("\n--- Ordenadas por nombre (Comparable) ---");
        Arrays.sort(facultades);
        for (Facultad facultad : facultades) {
            System.out.println(facultad);
        }
        System.out.println("\n--- Ordenadas por ID (Comparator) ---");
        Arrays.sort(facultades, Facultad.COMPARADOR_POR_ID);
        for (Facultad facultad : facultades) {
            System.out.println(facultad);
        }
    }

    /**
     * Demuestra la ordenación de reservas
     */
    public static void demostrarOrdenacionReservas() {
        System.out.println("\n=== DEMOSTRACIÓN ORDENACIÓN DE RESERVAS ===");
        
        // Crear algunas reservas de ejemplo
        Date fecha1 = new Date(2024, 0, 15, 9, 0);
        Date fecha2 = new Date(2024, 0, 15, 11, 0);
        Date fecha3 = new Date(2024, 0, 16, 14, 0);
        Date fecha4 = new Date(2024, 0, 16, 16, 0);
        
        Reserva[] reservas = {
            new Reserva(fecha3, fecha4),
            new Reserva(fecha1, fecha2),
            new ReservaPrioritaria(fecha2, fecha3, Estado.PRIORIDAD_ALTA, "Evento importante"),
            new ReservaPrioritaria(fecha1, fecha2, Estado.PRIORIDAD_BAJA, "Reunión regular")
        };

        System.out.println("Reservas originales:");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }

        // Ordenar por fecha de inicio (Comparator)
        System.out.println("\n--- Ordenadas por fecha de inicio (Comparator) ---");
        Arrays.sort(reservas, Reserva.COMPARADOR_POR_FECHA_INICIO);
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }

        // Ordenar por estado (Comparator)
        System.out.println("\n--- Ordenadas por estado (Comparator) ---");
        Arrays.sort(reservas, Reserva.COMPARADOR_POR_ESTADO);
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    /**
     * Demuestra la ordenación de equipos
     */
    public static void demostrarOrdenacionEquipos() {
        System.out.println("\n=== DEMOSTRACIÓN ORDENACIÓN DE EQUIPOS ===");
        Map<String, Equipo> equiposMap = new HashMap<>();
        Equipo e1 = new Equipo("Laptop", "Computación", true);
        Equipo e2 = new Equipo("Proyector", "Visual", true);
        Equipo e3 = new Equipo("Micrófono", "Audio", false);
        Equipo e4 = new Equipo("Cámara", "Video", true);
        equiposMap.put(e1.getCodigoEquipo(), e1);
        equiposMap.put(e2.getCodigoEquipo(), e2);
        equiposMap.put(e3.getCodigoEquipo(), e3);
        equiposMap.put(e4.getCodigoEquipo(), e4);
        Equipo[] equipos = equiposMap.values().toArray(new Equipo[0]);
        System.out.println("Equipos originales:");
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
        System.out.println("\n--- Ordenados por nombre (Comparable) ---");
        Arrays.sort(equipos);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
        System.out.println("\n--- Ordenados por disponibilidad (Comparator) ---");
        Arrays.sort(equipos, Equipo.COMPARADOR_POR_DISPONIBILIDAD);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
        System.out.println("\n--- Ordenados por categoría (Comparator) ---");
        Arrays.sort(equipos, Equipo.COMPARADOR_POR_CATEGORIA);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }
    }

    /**
     * Ejecuta todas las demostraciones de ordenación
     */
    public static void ejecutarTodasLasDemostraciones() {
        System.out.println("🚀 INICIANDO DEMOSTRACIONES DE ORDENACIÓN SIRAA");
        System.out.println("=".repeat(60));
        
        demostrarOrdenacionUsuarios();
        demostrarOrdenacionAuditorios();
        demostrarOrdenacionFacultades();
        demostrarOrdenacionReservas();
        demostrarOrdenacionEquipos();
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("✅ TODAS LAS DEMOSTRACIONES COMPLETADAS");
    }
} 