/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
package ec.edu.uce.dominio;

import java.util.Date;

public class Reserva {
    private int idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Equipo[] equipos;
    private int numEquipos = 0;

    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new Equipo[numEquipos];
    }

    public Reserva(){
        this(0, new Date(1990, 1, 1), new Date(1990, 1, 1));
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        if (idReserva > 0 && this.idReserva != idReserva){
            this.idReserva = idReserva;
        }
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        if (fechaInicio != this.fechaInicio){
            this.fechaInicio = fechaInicio;
        }
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        if (fechaFin != this.fechaFin) {
            this.fechaFin = fechaFin;
        }
    }

    public void crearEquipo(){
        if (numEquipos == equipos.length){
            Equipo [] aux = equipos;
            equipos = new Equipo[numEquipos+1];
            System.arraycopy(aux, 0, equipos, 0, numEquipos);
        }
        equipos[numEquipos] = new Equipo();
        numEquipos++;
    }

}
