package ec.edu.uce.Util;

import ec.edu.uce.dominio.*;
import java.util.Arrays;
import java.util.Date;

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
        
        // Crear algunos usuarios de ejemplo
        Usuario[] usuarios = {
            new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec"),
            new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec"),
            new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec"),
            new Usuario("María", "García", "maria.garcia@uce.edu.ec")
        };

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
        
        // Crear algunos auditorios de ejemplo
        Auditorio[] auditorios = {
            new Auditorio("Sala de Conferencias", 75),
            new Auditorio("Auditorio Principal", 100),
            new Auditorio("Aula Magna", 200),
            new Auditorio("Auditorio Secundario", 50)
        };

        System.out.println("Auditorios originales:");
        for (Auditorio auditorio : auditorios) {
            System.out.println(auditorio);
        }

        // Ordenar por nombre (Comparable)
        System.out.println("\n--- Ordenados por nombre (Comparable) ---");
        Arrays.sort(auditorios);
        for (Auditorio auditorio : auditorios) {
            System.out.println(auditorio);
        }

        // Ordenar por capacidad (Comparator)
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
        
        // Crear algunas facultades de ejemplo
        Facultad[] facultades = {
            new Facultad("Facultad de Medicina", 5),
            new Facultad("Facultad de Ingeniería", 5),
            new Facultad("Facultad de Derecho", 5),
            new Facultad("Facultad de Ciencias Sociales", 5)
        };

        System.out.println("Facultades originales:");
        for (Facultad facultad : facultades) {
            System.out.println(facultad);
        }

        // Ordenar por nombre (Comparable)
        System.out.println("\n--- Ordenadas por nombre (Comparable) ---");
        Arrays.sort(facultades);
        for (Facultad facultad : facultades) {
            System.out.println(facultad);
        }

        // Ordenar por ID (Comparator)
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
        
        // Crear algunos equipos de ejemplo
        Equipo[] equipos = {
            new Equipo("Laptop", "Computación", true),
            new Equipo("Proyector", "Visual", true),
            new Equipo("Micrófono", "Audio", false),
            new Equipo("Cámara", "Video", true)
        };

        System.out.println("Equipos originales:");
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }

        // Ordenar por nombre (Comparable)
        System.out.println("\n--- Ordenados por nombre (Comparable) ---");
        Arrays.sort(equipos);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }

        // Ordenar por disponibilidad (Comparator)
        System.out.println("\n--- Ordenados por disponibilidad (Comparator) ---");
        Arrays.sort(equipos, Equipo.COMPARADOR_POR_DISPONIBILIDAD);
        for (Equipo equipo : equipos) {
            System.out.println(equipo);
        }

        // Ordenar por categoría (Comparator)
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