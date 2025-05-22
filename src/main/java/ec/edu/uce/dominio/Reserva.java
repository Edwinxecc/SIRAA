// Reserva.java
package ec.edu.uce.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private int id;
    private Usuario usuario;
    private Auditorio auditorio;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private List<Equipo> equiposAsignados;

    // Lista simulando base de datos
    private static List<Reserva> listaReservas = new ArrayList<>();

    public Reserva(int id, Usuario usuario, Auditorio auditorio, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.usuario = usuario;
        this.auditorio = auditorio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equiposAsignados = new ArrayList<>();
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Auditorio getAuditorio() {
        return auditorio;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public List<Equipo> getEquiposAsignados() {
        return equiposAsignados;
    }

    public void asignarEquipo(Equipo equipo) {
        if (equipo.isDisponible()) {
            equipo.setDisponible(false);
            equiposAsignados.add(equipo);
            System.out.println("[✓] Equipo asignado a la reserva.");
        } else {
            System.out.println("[!] El equipo no está disponible.");
        }
    }

    public void liberarEquipo(Equipo equipo) {
        if (equiposAsignados.contains(equipo)) {
            equipo.setDisponible(true);
            equiposAsignados.remove(equipo);
            System.out.println("[✓] Equipo liberado de la reserva.");
        }
    }

    // CRUD
    public static void crearReserva(Reserva reserva) {
        listaReservas.add(reserva);
        System.out.println("[✓] Reserva creada.");
    }

    public static Reserva consultarReserva(int id) {
        for (Reserva r : listaReservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    public static List<Reserva> listarReservas() {
        return new ArrayList<>(listaReservas);
    }

    public static boolean actualizarReserva(int id, LocalDateTime nuevaInicio, LocalDateTime nuevaFin) {
        Reserva reserva = consultarReserva(id);
        if (reserva != null) {
            reserva.setFechaInicio(nuevaInicio);
            reserva.setFechaFin(nuevaFin);
            System.out.println("[✓] Reserva actualizada.");
            return true;
        }
        return false;
    }

    public static boolean eliminarReserva(int id) {
        Reserva reserva = consultarReserva(id);
        if (reserva != null) {
            for (Equipo eq : reserva.getEquiposAsignados()) {
                eq.setDisponible(true);
            }
            listaReservas.remove(reserva);
            System.out.println("[✓] Reserva eliminada.");
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Reserva ID: " + id +
                ", Usuario: " + usuario.getNombre() +
                ", Auditorio: " + auditorio.getNombre() +
                ", Inicio: " + fechaInicio +
                ", Fin: " + fechaFin +
                ", Equipos: " + equiposAsignados.size();
    }
}
