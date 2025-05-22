/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
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
    //private List<Equipo> equiposAsignados;
    private Equipo [] equipos;


    /**
     * Constructor con parámetros.
     * @param id ID de la reserva
     * @param usuario Usuario que realiza la reserva
     * @param auditorio Auditorio reservado
     * @param fechaInicio Fecha y hora de inicio
     * @param fechaFin Fecha y hora de fin
     */
    public Reserva(int id, Usuario usuario, Auditorio auditorio, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.usuario = usuario;
        this.auditorio = auditorio;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equiposAsignados = new ArrayList<>();
    }

    /**
     * Obtiene el ID de la reserva.
     * @return ID de la reserva
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el usuario que realizó la reserva.
     * @return Usuario de la reserva
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Obtiene el auditorio reservado.
     * @return Auditorio de la reserva
     */
    public Auditorio getAuditorio() {
        return auditorio;
    }

    /**
     * Obtiene la fecha y hora de inicio.
     * @return Fecha y hora de inicio
     */
    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha y hora de inicio.
     * @param fechaInicio Nueva fecha y hora de inicio
     */
    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha y hora de fin.
     * @return Fecha y hora de fin
     */
    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha y hora de fin.
     * @param fechaFin Nueva fecha y hora de fin
     */
    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene la lista de equipos asignados a la reserva.
     * @return Lista de equipos asignados
     */
    public List<Equipo> getEquiposAsignados() {
        return equiposAsignados;
    }

    /**
     * Asigna un equipo a la reserva.
     * @param equipo Equipo a asignar
     */
    public void asignarEquipo(Equipo equipo) {
        if (equipo.isDisponible()) {
            equipo.setDisponible(false);
            equiposAsignados.add(equipo);
            System.out.println("[✓] Equipo asignado a la reserva.");
        } else {
            System.out.println("[!] El equipo no está disponible.");
        }
    }

    /**
     * Libera un equipo asignado a la reserva.
     * @param equipo Equipo a liberar
     */
    public void liberarEquipo(Equipo equipo) {
        if (equiposAsignados.contains(equipo)) {
            equipo.setDisponible(true);
            equiposAsignados.remove(equipo);
            System.out.println("[✓] Equipo liberado de la reserva.");
        }
    }


    /**
     * Devuelve una representación en texto de la reserva.
     * @return String con los datos de la reserva
     */
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
