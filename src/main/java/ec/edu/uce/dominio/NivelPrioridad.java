package ec.edu.uce.dominio;

/**
 * Enumerado que representa los niveles de prioridad para las reservas prioritarias.
 */
public enum NivelPrioridad {
    BAJA(1, "Baja"),
    MEDIA(2, "Media"),
    ALTA(3, "Alta"),
    URGENTE(4, "Urgente");

    private final int valor;
    private final String descripcion;

    NivelPrioridad(int valor, String descripcion) {
        this.valor = valor;
        this.descripcion = descripcion;
    }

    public int getValor() {
        return valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
} 