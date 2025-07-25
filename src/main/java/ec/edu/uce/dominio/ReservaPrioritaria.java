package ec.edu.uce.dominio;

import java.util.Date;
import java.util.Scanner;
import ec.edu.uce.Util.Validaciones;

/**
 * Representa una reserva prioritaria en el sistema SIRAA.
 * Esta clase extiende la funcionalidad básica de Reserva añadiendo un nivel de prioridad.
 */
public class ReservaPrioritaria extends Reserva implements java.io.Serializable {
    // Constantes para la clase
    private static final Estado ESTADO_DEFAULT = Estado.PRIORIDAD_BAJA;
    private static final int MAX_EQUIPOS_PRIORITARIOS = 5;

    private String motivoPrioridad;

    private transient Scanner entrada;
    private transient Validaciones validacion;

    /**
     * Constructor completo para ReservaPrioritaria.
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param estado Estado y nivel de prioridad de la reserva
     * @param motivoPrioridad Motivo de la prioridad
     */
    public ReservaPrioritaria(Date fechaInicio, Date fechaFin, Estado estado, String motivoPrioridad) {
        super(fechaInicio, fechaFin);
        this.setEstado(estado != null && estado.esPrioritario() ? estado : ESTADO_DEFAULT);
        this.motivoPrioridad = motivoPrioridad;
    }

    /**
     * Constructor con ID específico (para compatibilidad).
     * @param idReserva ID de la reserva
     * @param fechaInicio Fecha de inicio de la reserva
     * @param fechaFin Fecha de fin de la reserva
     * @param estado Estado y nivel de prioridad de la reserva
     * @param motivoPrioridad Motivo de la prioridad
     */
    public ReservaPrioritaria(int idReserva, Date fechaInicio, Date fechaFin, Estado estado, String motivoPrioridad) {
        super(fechaInicio, fechaFin);
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
    @Override
    public boolean requiereAprobacion() {
        return getEstado().requiereAprobacion();
    }

    @Override
    public void crearEquipo(String nombre, String categoria, boolean disponible) throws ReservaException {
        try {
            // Se asegura de que no se agreguen más equipos de los permitidos para reservas prioritarias.
            if (getEquipos().size() >= MAX_EQUIPOS_PRIORITARIOS) {
                throw new ReservaException("No se pueden agregar más de " + MAX_EQUIPOS_PRIORITARIOS + " equipos a una reserva prioritaria");
            }
            super.crearEquipo(nombre, categoria, disponible);
        } catch (ReservaException e) {
            throw e; // Re-lanzar la excepción personalizada
        } catch (Exception e) {
            throw new ReservaException("Error al crear equipo en reserva prioritaria", e);
        }
    }

    // ========================
    // Método inicializar
    // ========================

    public void inicializar() {
        // Crear 5 equipos de ejemplo
        try {
            crearEquipo("Proyector", "Visual", true);
            crearEquipo("Micrófono", "Audio", true);
            crearEquipo("Laptop", "Computación", true);
            crearEquipo("Cámara", "Video", false);
            crearEquipo("Pantalla", "Visual", true);
        } catch (ReservaException e) {
            System.out.println("[!] Error al inicializar equipos: " + e.getMessage());
        }
    }

    @Override
    public boolean equals(Object obj) {
        // Llama al equals de la clase padre y luego compara los atributos específicos de ReservaPrioritaria.
        if (!super.equals(obj)) return false;
        if (!(obj instanceof ReservaPrioritaria)) return false;

        ReservaPrioritaria otraReserva = (ReservaPrioritaria) obj;
        return this.getEstado() == otraReserva.getEstado() &&
                this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public boolean validarDuplicado(Object obj) {
        // Llama al validarDuplicado de la clase padre y luego compara los atributos específicos.
        if (!super.validarDuplicado(obj)) return false;
        if (!(obj instanceof ReservaPrioritaria)) return false;

        ReservaPrioritaria otraReserva = (ReservaPrioritaria) obj;
        return this.getEstado() == otraReserva.getEstado() &&
                this.motivoPrioridad.equals(otraReserva.motivoPrioridad);
    }

    @Override
    public String tipoReserva() {
        return "Reserva Prioritaria";
    }

    // ========================
    // Métodos sobrescritos de la interfaz IAdministrarCRUD
    // ========================

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof ReservaPrioritaria)) {
            return "[!] El objeto no es una ReservaPrioritaria válida.";
        }
        ReservaPrioritaria reservaNueva = (ReservaPrioritaria) obj;
        if (validarDuplicado(reservaNueva)) {
            return "[!] La reserva prioritaria ya existe.";
        }
        // Aquí se simula la creación de la reserva prioritaria
        return "[✓] Reserva prioritaria creada correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof ReservaPrioritaria)) {
            return "[!] El objeto no es una ReservaPrioritaria válida.";
        }
        ReservaPrioritaria reservaEditada = (ReservaPrioritaria) obj;
        // Aquí se simula la edición de la reserva prioritaria
        super.setFechaInicio(reservaEditada.getFechaInicio());
        super.setFechaFin(reservaEditada.getFechaFin());
        super.setEstado(reservaEditada.getEstado());
        this.motivoPrioridad = reservaEditada.getMotivoPrioridad();
        return "[✓] Reserva prioritaria editada correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof ReservaPrioritaria)) {
            return "[!] El objeto no es una ReservaPrioritaria válida.";
        }
        ReservaPrioritaria reservaABorrar = (ReservaPrioritaria) obj;
        if (this.equals(reservaABorrar)) {
            // Aquí se simula la eliminación de la reserva prioritaria
            return "[✓] Reserva prioritaria eliminada correctamente.";
        }
        return "[!] Reserva prioritaria no encontrada.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == super.getIdReserva()) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("┌─ RESERVA PRIORITARIA ──────────────────────────────────────────────┐%n" +
                        "│ Código: %-15s │ ID: %-8d │ Estado: %-20s │%n" +
                        "│ Fecha Inicio: %-30s │%n" +
                        "│ Fecha Fin: %-32s │%n" +
                        "│ Equipos Asignados: %-3d │ Tipo: %-20s │%n" +
                        "│ Motivo Prioridad: %-50s │%n" +
                        "│ Nivel Prioridad: %-3d │ Requiere Aprobación: %-8s │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                getCodigoReserva(), getIdReserva(), getEstado().getDescripcion(),
                getFechaInicio().toString(),
                getFechaFin().toString(),
                getEquipos().size(), tipoReserva(),
                motivoPrioridad,
                getNivelPrioridad(), requiereAprobacion() ? "Sí" : "No");
    }
}