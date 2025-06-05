package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa una reserva prioritaria en el sistema SIRAA.
 * Esta clase extiende la funcionalidad básica de Reserva añadiendo un nivel de prioridad.
 */
public class ReservaPrioritaria extends Reserva {
    // Constantes para la clase
    private static final Estado ESTADO_DEFAULT = Estado.PRIORIDAD_BAJA;
    private static final int MAX_EQUIPOS_PRIORITARIOS = 5;
    
    private String motivoPrioridad;

    /**
     * Constructor completo para ReservaPrioritaria.
     * @param idReserva ID de la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param estado Estado y nivel de prioridad de la reserva
     * @param motivoPrioridad Motivo de la prioridad
     */
    public ReservaPrioritaria(int idReserva, Date fechaInicio, Date fechaFin, 
                            Estado estado, String motivoPrioridad) {
        super(idReserva, fechaInicio, fechaFin);
        this.setEstado(estado != null && estado.esPrioritario() ? estado : ESTADO_DEFAULT);
        this.motivoPrioridad = motivoPrioridad;
    }

    /**
     * Constructor por defecto.
     * Inicializa la reserva con valores por defecto y prioridad baja.
     */
    public ReservaPrioritaria() {
        super();
        this.setEstado(ESTADO_DEFAULT);
        this.motivoPrioridad = "Sin motivo especificado";
    }

    /**
     * Obtiene el nivel de prioridad de la reserva.
     * @return nivel de prioridad actual
     */
    public int getNivelPrioridad() {
        return getEstado().getNivelPrioridad();
    }

    /**
     * Establece el nivel de prioridad de la reserva.
     * @param estado nuevo estado y nivel de prioridad
     */
    public void setNivelPrioridad(Estado estado) {
        if (estado != null && estado.esPrioritario()) {
            this.setEstado(estado);
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
        return getEstado().requiereAprobacion();
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
        return this.getEstado() == otraReserva.getEstado() &&
               this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        if (!super.validarDuplicado(obj)) return false;
        if (!(obj instanceof ReservaPrioritaria)) return false;
        
        ReservaPrioritaria otraReserva = (ReservaPrioritaria) obj;
        return this.getEstado() == otraReserva.getEstado() &&
               this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nNivel de Prioridad: " + getEstado().getDescripcion() +
               "\nMotivo: " + motivoPrioridad +
               "\nRequiere Aprobación: " + (requiereAprobacion() ? "Sí" : "No");
    }
} 