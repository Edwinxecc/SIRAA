package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
public class Reserva {
    private int idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Equipo[] equipos;
    private int numEquipos = 0;

    // Constructor completo
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new Equipo[0];
    }

    // Constructor por defecto
    public Reserva(){
        this(0, new Date(1990 - 1900, 0, 1), new Date(1990 - 1900, 0, 1));
    }

    // Getters y setters
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
        if (fechaInicio != null){
            this.fechaInicio = fechaInicio;
        }
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        if (fechaFin != null) {
            this.fechaFin = fechaFin;
        }
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    // ========================
    // CRUD de Equipos
    // ========================

    public void crearEquipo(String nombre, String categoria, boolean disponible){
        if (numEquipos == equipos.length){
            Equipo[] aux = equipos;
            equipos = new Equipo[numEquipos + 1];
            System.arraycopy(aux, 0, equipos, 0, numEquipos);
        }
        equipos[numEquipos] = new Equipo(nombre, categoria, disponible);
        numEquipos++;
    }

    public String listarEquipos(){
        if (numEquipos == 0) {
            return "No hay equipos asignados a esta reserva.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numEquipos; i++) {
            sb.append(String.format("[%d] Nombre: %s | Categoría: %s | Disponible: %s%n",
                    i,
                    equipos[i].getNombre(),
                    equipos[i].getCategoria(),
                    equipos[i].getDisponibilidad() ? "Sí" : "No"
            ));
        }
        return sb.toString();
    }

    public String actualizarEquipo(int indice, String nuevoNombre, String nuevaCategoria, boolean nuevaDisponibilidad){
        if (indice >= 0 && indice < numEquipos){
            equipos[indice].setNombre(nuevoNombre);
            equipos[indice].setCategoria(nuevaCategoria);
            equipos[indice].setDisponibilidad(nuevaDisponibilidad);
            return "Equipo actualizado.";
        } else {
            return "Índice de equipo inválido.";
        }
    }

    public String eliminarEquipo(int indice){
        if (indice >= 0 && indice < numEquipos){
            for (int i = indice; i < numEquipos - 1; i++) {
                equipos[i] = equipos[i + 1];
            }
            equipos[numEquipos - 1] = null;
            numEquipos--;

            Equipo[] aux = new Equipo[numEquipos];
            System.arraycopy(equipos, 0, aux, 0, numEquipos);
            equipos = aux;

            return "Equipo eliminado.";
        } else {
            return "Índice de equipo inválido.";
        }
    }

    // ========================
    // toString
    // ========================
    @Override
    public String toString() {
        return "Reserva ID: " + idReserva +
                ", Fecha inicio: " + fechaInicio +
                ", Fecha fin: " + fechaFin +
                ", Número de equipos: " + numEquipos;
    }
}