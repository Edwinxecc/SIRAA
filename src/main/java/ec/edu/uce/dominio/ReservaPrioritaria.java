package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa una reserva prioritaria en el sistema SIRAA.
 * Esta clase extiende la funcionalidad básica de Reserva añadiendo un nivel de prioridad.
 */
public class ReservaPrioritaria extends Reserva {
    private int nivelPrioridad;

    /**
     * Constructor completo para ReservaPrioritaria.
     * @param idReserva ID de la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param nivelPrioridad Nivel de prioridad de la reserva (mayor número = mayor prioridad)
     */
    public ReservaPrioritaria(int idReserva, Date fechaInicio, Date fechaFin, int nivelPrioridad) {
        super(idReserva, fechaInicio, fechaFin);
        this.nivelPrioridad = nivelPrioridad;
    }

    /**
     * Constructor por defecto.
     * Inicializa la reserva con valores por defecto y prioridad 0.
     */
    public ReservaPrioritaria() {
        super();
        this.nivelPrioridad = 0;
    }

    /**
     * Obtiene el nivel de prioridad de la reserva.
     * @return nivel de prioridad actual
     */
    public int getNivelPrioridad() {
        return nivelPrioridad;
    }

    /**
     * Establece el nivel de prioridad de la reserva.
     * @param nivelPrioridad nuevo nivel de prioridad
     */
    public void setNivelPrioridad(int nivelPrioridad) {
        if (nivelPrioridad >= 0) {
            this.nivelPrioridad = nivelPrioridad;
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", Nivel de Prioridad: " + nivelPrioridad;
    }
} 