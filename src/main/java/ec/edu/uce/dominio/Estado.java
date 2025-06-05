package ec.edu.uce.dominio;

/**
 * Enumerado que representa los posibles estados de una reserva o equipo en el sistema SIRAA.
 */
public enum Estado {
    PENDIENTE("Pendiente"),
    CONFIRMADA("Confirmada"),
    CANCELADA("Cancelada"),
    EN_PROCESO("En Proceso"),
    FINALIZADA("Finalizada");

    private final String descripcion;

    Estado(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
} 