package ec.edu.uce.dominio;

import java.util.Date;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
public class Reserva implements IAdministrarCRUD, Comparable<Reserva> { // Ya no es abstracta

    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "RES";
    private static int contadorReservas = 0;
    private static final int CAPACIDAD_INICIAL_EQUIPOS_DEFECTO = 0;

    private int idReserva;
    private String codigoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Map<String, Equipo> equipos;
    private int numEquipos = 0;
    private Estado estado;

    // Constructores
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new HashMap<>();
        this.estado = Estado.PENDIENTE;
        this.idReserva = generarIdReserva();
        this.codigoReserva = generarCodigoReserva();
        inicializar();
    }

    public Reserva(){
        this(0, new Date(1990 - 1900, 0, 1), new Date(1990 - 1900, 0, 1));
    }

    // Constructor con objeto Reserva
    public Reserva(Reserva reserva) {
        this(reserva.getFechaInicio(), reserva.getFechaFin());
        this.estado = reserva.getEstado();
        for (Equipo equipo : reserva.getEquipos()) {
            if (equipo != null) {
                crearEquipo(equipo);
            }
        }
    }

    // Constructor con fechas
    public Reserva(Date fechaInicio, Date fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new HashMap<>();
        this.estado = Estado.PENDIENTE;
        this.idReserva = generarIdReserva();
        this.codigoReserva = generarCodigoReserva();
    }

    // Método para generar IDs automáticos
    private int generarIdReserva() {
        contadorReservas++;
        return contadorReservas;
    }

    // Método para generar códigos automáticos
    private String generarCodigoReserva() {
        return PREFIJO_CODIGO + String.format("%04d", contadorReservas);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Reserva)) return false;

        Reserva otraReserva = (Reserva) obj;
        return this.idReserva == otraReserva.idReserva &&
                this.codigoReserva.equals(otraReserva.codigoReserva) &&
                this.fechaInicio.equals(otraReserva.fechaInicio) &&
                this.fechaFin.equals(otraReserva.fechaFin);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Reserva)) return false;

        Reserva otraReserva = (Reserva) obj;
        return this.idReserva == otraReserva.idReserva ||
                this.codigoReserva.equals(otraReserva.codigoReserva) ||
                (this.fechaInicio.equals(otraReserva.fechaInicio) &&
                        this.fechaFin.equals(otraReserva.fechaFin));
    }

    // Getters y setters
    public int getIdReserva() {
        return idReserva;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
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
        return equipos.values().toArray(new Equipo[0]);
    }

    // ========================
    // CRUD de Equipos
    // ========================

    public void crearEquipo(String nombre, String categoria, boolean disponible) {
        crearEquipo(new Equipo(nombre, categoria, disponible));
    }

    public void crearEquipo(Equipo equipo) {
        if (equipo == null) return;
        if (equipos.containsKey(equipo.getCodigoEquipo())) {
            System.out.println("[!] Equipo duplicado. No se puede agregar.");
            return;
        }
        equipos.put(equipo.getCodigoEquipo(), equipo);
    }

    public String listarEquipos() {
        return listarEquipos(false);
    }

    public String listarEquipos(boolean soloDisponibles) {
        if (equipos.isEmpty()) {
            return "No hay equipos asignados a esta reserva.";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Equipo equipo : equipos.values()) {
            if (equipo != null && (!soloDisponibles || equipo.getDisponibilidad())) {
                sb.append(String.format("[%d] Nombre: %s | Categoría: %s | Disponible: %s%n",
                        i++,
                        equipo.getNombre(),
                        equipo.getCategoria(),
                        equipo.getDisponibilidad() ? "Sí" : "No"
                ));
            }
        }
        return sb.toString();
    }

    public String actualizarEquipo(String codigo, Equipo nuevoEquipo) {
        if (equipos.containsKey(codigo) && nuevoEquipo != null) {
            equipos.put(codigo, nuevoEquipo);
            return "Equipo actualizado.";
        }
        return "Código de equipo inválido.";
    }

    public String eliminarEquipo(String codigo) {
        if (equipos.remove(codigo) != null) {
            return "Equipo eliminado.";
        }
        return "Código de equipo inválido.";
    }

    public String eliminarEquipo(Equipo equipo) {
        if (equipo == null) return "Equipo nulo.";
        return eliminarEquipo(equipo.getCodigoEquipo());
    }

    public boolean requiereAprobacion() {
        return this.estado == Estado.PRIORIDAD_URGENTE;
    }

    public String tipoReserva() {
        return "Reserva Regular";
    }

    @Override
    public String toString() {
        return String.format("┌─ RESERVA ───────────────────────────────────────────────────────────┐%n" +
                        "│ Código: %-15s │ ID: %-8d │ Estado: %-20s │%n" +
                        "│ Fecha Inicio: %-30s │%n" +
                        "│ Fecha Fin: %-32s │%n" +
                        "│ Equipos Asignados: %-3d │ Tipo: %-20s │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                codigoReserva, idReserva, estado.getDescripcion(),
                fechaInicio.toString(),
                fechaFin.toString(),
                numEquipos, tipoReserva());
    }

    // ========================
    // Métodos de la interfaz IAdministrarCRUD
    // ========================

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Reserva)) {
            return "[!] El objeto no es una Reserva válida.";
        }
        Reserva reservaNueva = (Reserva) obj;
        if (validarDuplicado(reservaNueva)) {
            return "[!] La reserva ya existe.";
        }
        // Aquí se simula la creación de la reserva
        return "[✓] Reserva creada correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Reserva)) {
            return "[!] El objeto no es una Reserva válida.";
        }
        Reserva reservaEditada = (Reserva) obj;
        // Aquí se simula la edición de la reserva
        this.fechaInicio = reservaEditada.getFechaInicio();
        this.fechaFin = reservaEditada.getFechaFin();
        this.estado = reservaEditada.getEstado();
        return "[✓] Reserva editada correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Reserva)) {
            return "[!] El objeto no es una Reserva válida.";
        }
        Reserva reservaABorrar = (Reserva) obj;
        if (this.equals(reservaABorrar)) {
            // Aquí se simula la eliminación de la reserva
            return "[✓] Reserva eliminada correctamente.";
        }
        return "[!] Reserva no encontrada.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == this.idReserva) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    // ========================
    // Métodos de la interfaz Comparable
    // ========================

    /**
     * Criterio natural de comparación: por idReserva y luego por fechaInicio
     */
    @Override
    public int compareTo(Reserva o) {
        if (this.idReserva < o.idReserva) {
            return -1;
        } else if (this.idReserva > o.idReserva) {
            return 1;
        }
        // Si los id son iguales, comparar por fechaInicio
        if (this.fechaInicio.compareTo(o.fechaInicio) < 0) {
            return -1;
        } else if (this.fechaInicio.compareTo(o.fechaInicio) > 0) {
            return 1;
        }
        return 0;
    }

    // ========================
    // Métodos de Ordenación para Equipos
    // ========================

    /**
     * Ordena los equipos de la reserva por nombre
     */
    public void ordenarEquiposPorNombre() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, new OrdenarEquipoNombre());
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por categoría
     */
    public void ordenarEquiposPorCategoria() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, new OrdenarEquipoCategoria());
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por disponibilidad (disponibles primero)
     */
    public void ordenarEquiposPorDisponibilidad() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, new OrdenarEquipoDisponibilidad());
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por ID (ascendente)
     */
    public void ordenarEquiposPorId() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, new OrdenarEquipoId());
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }
    
    // Método para inicializar equipos de ejemplo
    public void inicializar() {
        if (equipos.isEmpty()) {
            crearEquipo(new Equipo("Proyector", "Audiovisual", true));
            crearEquipo(new Equipo("Micrófono", "Audio", true));
            crearEquipo(new Equipo("Laptop", "Computo", false));
        }
    }
}
