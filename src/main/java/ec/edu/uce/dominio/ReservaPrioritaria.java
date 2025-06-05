package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa una reserva prioritaria en el sistema SIRAA.
 * Esta clase extiende la funcionalidad básica de Reserva añadiendo un nivel de prioridad.
 */
public class ReservaPrioritaria extends Reserva {
    // Constantes para la clase
    private static final NivelPrioridad NIVEL_PRIORIDAD_DEFAULT = NivelPrioridad.BAJA;
    private static final int MAX_EQUIPOS_PRIORITARIOS = 5;
    
    private NivelPrioridad nivelPrioridad;
    private String motivoPrioridad;
    private boolean requiereAprobacion;

    /**
     * Constructor completo para ReservaPrioritaria.
     * @param idReserva ID de la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param nivelPrioridad Nivel de prioridad de la reserva
     * @param motivoPrioridad Motivo de la prioridad
     */
    public ReservaPrioritaria(int idReserva, Date fechaInicio, Date fechaFin, 
                            NivelPrioridad nivelPrioridad, String motivoPrioridad) {
        super(idReserva, fechaInicio, fechaFin);
        this.nivelPrioridad = nivelPrioridad != null ? nivelPrioridad : NIVEL_PRIORIDAD_DEFAULT;
        this.motivoPrioridad = motivoPrioridad;
        this.requiereAprobacion = nivelPrioridad == NivelPrioridad.URGENTE;
        this.setEstado(Estado.PENDIENTE);
    }

    /**
     * Constructor por defecto.
     * Inicializa la reserva con valores por defecto y prioridad baja.
     */
    public ReservaPrioritaria() {
        super();
        this.nivelPrioridad = NIVEL_PRIORIDAD_DEFAULT;
        this.motivoPrioridad = "Sin motivo especificado";
        this.requiereAprobacion = false;
    }

    /**
     * Obtiene el nivel de prioridad de la reserva.
     * @return nivel de prioridad actual
     */
    public NivelPrioridad getNivelPrioridad() {
        return nivelPrioridad;
    }

    /**
     * Establece el nivel de prioridad de la reserva.
     * @param nivelPrioridad nuevo nivel de prioridad
     */
    public void setNivelPrioridad(NivelPrioridad nivelPrioridad) {
        if (nivelPrioridad != null) {
            this.nivelPrioridad = nivelPrioridad;
            this.requiereAprobacion = nivelPrioridad == NivelPrioridad.URGENTE;
        }
    }

    /**
     * Obtiene el motivo de la prioridad.
     * @return motivo de la prioridad
     */
    public String getMotivoPrioridad() {
        return motivoPrioridad;
    }

    /**
     * Establece el motivo de la prioridad.
     * @param motivoPrioridad nuevo motivo
     */
    public void setMotivoPrioridad(String motivoPrioridad) {
        if (motivoPrioridad != null && !motivoPrioridad.trim().isEmpty()) {
            this.motivoPrioridad = motivoPrioridad;
        }
    }

    /**
     * Verifica si la reserva requiere aprobación.
     * @return true si requiere aprobación, false en caso contrario
     */
    public boolean requiereAprobacion() {
        return requiereAprobacion;
    }

    @Override
    public void crearEquipo(String nombre, String categoria, boolean disponible) {
        if (getEquipos().length >= MAX_EQUIPOS_PRIORITARIOS) {
            throw new IllegalStateException("No se pueden agregar más equipos a una reserva prioritaria");
        }
        super.crearEquipo(nombre, categoria, disponible);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ReservaPrioritaria)) return false;
        
        ReservaPrioritaria otraReserva = (ReservaPrioritaria) obj;
        return this.nivelPrioridad == otraReserva.nivelPrioridad &&
               this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (!super.validarDuplicado(obj)) return false;
        if (!(obj instanceof ReservaPrioritaria)) return false;
        
        ReservaPrioritaria otraReserva = (ReservaPrioritaria) obj;
        return this.nivelPrioridad == otraReserva.nivelPrioridad &&
               this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nNivel de Prioridad: " + nivelPrioridad +
               "\nMotivo: " + motivoPrioridad +
               "\nRequiere Aprobación: " + (requiereAprobacion ? "Sí" : "No");
    }
} 