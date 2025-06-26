package ec.edu.uce.dominio;

/**
 * Enumerado que representa los estados y niveles de prioridad en el sistema SIRAA.
 * Se incluyen estados para reservas, usuarios y equipos.
 */
public enum Estado {
    // Estados para Reservas
    PENDIENTE("Pendiente", 0, Categoria.RESERVA),
    CONFIRMADA("Confirmada", 0, Categoria.RESERVA),
    CANCELADA("Cancelada", 0, Categoria.RESERVA),
    EN_PROCESO("En Proceso", 0, Categoria.RESERVA),
    FINALIZADA("Finalizada", 0, Categoria.RESERVA),

    // Estados prioritarios para Reservas
    PRIORIDAD_BAJA("Prioridad Baja", 1, Categoria.RESERVA),
    PRIORIDAD_MEDIA("Prioridad Media", 2, Categoria.RESERVA),
    PRIORIDAD_ALTA("Prioridad Alta", 3, Categoria.RESERVA),
    PRIORIDAD_URGENTE("Prioridad Urgente", 4, Categoria.RESERVA),

    // Estados para Usuarios
    ACTIVO("Activo", 0, Categoria.USUARIO),
    INACTIVO("Inactivo", 0, Categoria.USUARIO),
    SUSPENDIDO("Suspendido", 0, Categoria.USUARIO),

    // Estados para Equipos
    DISPONIBLE("Disponible", 0, Categoria.EQUIPO),
    NO_DISPONIBLE("No Disponible", 0, Categoria.EQUIPO),
    EN_MANTENIMIENTO("En Mantenimiento", 0, Categoria.EQUIPO);

    private final String descripcion;
    private final int nivelPrioridad;
    private final Categoria categoria;

    Estado(String descripcion, int nivelPrioridad, Categoria categoria) {
        this.descripcion = descripcion;
        this.nivelPrioridad = nivelPrioridad;
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public boolean esPrioritario() {
        return nivelPrioridad > 0 && categoria == Categoria.RESERVA;
    }

    public boolean requiereAprobacion() {
        return this == PRIORIDAD_URGENTE;
    }

    public boolean esEstadoUsuario() {
        return categoria == Categoria.USUARIO;
    }

    public boolean esEstadoEquipo() {
        return categoria == Categoria.EQUIPO;
    }

    public boolean esEstadoReserva() {
        return categoria == Categoria.RESERVA;
    }

    @Override
    public String toString() {
        return descripcion;
    }

    /**
     * Categoría del estado para diferenciar su uso.
     */
    public enum Categoria {
        RESERVA,
        USUARIO,
        EQUIPO
    }
}