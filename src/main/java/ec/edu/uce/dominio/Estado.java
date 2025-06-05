package ec.edu.uce.dominio;

/**
 * Enumerado que representa los estados y niveles de prioridad en el sistema SIRAA.
 */
public enum Estado {
    // Estados normales
    PENDIENTE("Pendiente", 0),
    CONFIRMADA("Confirmada", 0),
    CANCELADA("Cancelada", 0),
    EN_PROCESO("En Proceso", 0),
    FINALIZADA("Finalizada", 0),
    
    // Estados prioritarios
    PRIORIDAD_BAJA("Prioridad Baja", 1),
    PRIORIDAD_MEDIA("Prioridad Media", 2),
    PRIORIDAD_ALTA("Prioridad Alta", 3),
    PRIORIDAD_URGENTE("Prioridad Urgente", 4);

    private final String descripcion;
    private final int nivelPrioridad;

    Estado(String descripcion, int nivelPrioridad) {
        this.descripcion = descripcion;
        this.nivelPrioridad = nivelPrioridad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }

    public boolean esPrioritario() {
        return nivelPrioridad > 0;
    }

    public boolean requiereAprobacion() {
        return this == PRIORIDAD_URGENTE;
    }

    @Override
    public String toString() {
        return descripcion;
    }
} 