package ec.edu.uce.dominio;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ec.edu.uce.Util.Validaciones;

/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
public class Reserva implements IAdministrarCRUD, Comparable<Reserva>, java.io.Serializable { // Ya no es abstracta

    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "RES";
    private static int contadorReservas = 0;
    private static final int CAPACIDAD_INICIAL_EQUIPOS_DEFECTO = 0;

    private int idReserva;
    private String codigoReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private List<Equipo> equipos;
    private int numEquipos = 0;
    private Estado estado;
    private Usuario usuario; // Referencia al usuario que hizo la reserva
    private Auditorio auditorio; // Referencia al auditorio reservado
    private Map<Reserva, List<Equipo>> relacionReservaEquipo = new HashMap<>();

    private transient Scanner entrada;
    private transient Validaciones validacion;

    // Constructores
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new ArrayList<>();
        this.estado = Estado.PENDIENTE;
        this.idReserva = generarIdReserva();
        this.codigoReserva = generarCodigoReserva();
        //inicializar();
    }

    public Reserva(){
        this(0, new Date(1990 - 1900, 0, 1), new Date(1990 - 1900, 0, 1));
    }

    // Constructor con objeto Reserva
    public Reserva(Reserva reserva) throws ReservaException {
        this(reserva.getFechaInicio(), reserva.getFechaFin());
        this.estado = reserva.getEstado();
        this.usuario = reserva.getUsuario();
        this.auditorio = reserva.getAuditorio();
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
        this.equipos = new ArrayList<>();
        this.estado = Estado.PENDIENTE;
        this.idReserva = generarIdReserva();
        this.codigoReserva = generarCodigoReserva();
    }

    // Constructor completo con usuario y auditorio
    public Reserva(Date fechaInicio, Date fechaFin, Usuario usuario, Auditorio auditorio) {
        this(fechaInicio, fechaFin);
        this.usuario = usuario;
        this.auditorio = auditorio;
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

    public List<Equipo> getEquipos() {
        return new ArrayList<>(equipos);
    }

    public Map<Reserva, List<Equipo>> getRelacionReservaEquipo() {
        return relacionReservaEquipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Auditorio getAuditorio() {
        return auditorio;
    }

    public void setAuditorio(Auditorio auditorio) {
        this.auditorio = auditorio;
    }

    // ========================
    // CRUD de Equipos
    // ========================

    public void crearEquipo(String nombre, String categoria, boolean disponible) throws ReservaException {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new ReservaException("El nombre del equipo no puede estar vacío");
            }
            if (categoria == null || categoria.trim().isEmpty()) {
                throw new ReservaException("La categoría del equipo no puede estar vacía");
            }
            crearEquipo(new Equipo(nombre, categoria, disponible));
        } catch (Exception e) {
            throw new ReservaException("Error al crear equipo", e);
        }
    }

    public void crearEquipo(Equipo equipo) throws ReservaException {
        try {
            if (equipo == null) {
                throw new ReservaException("El equipo no puede ser nulo");
            }
            if (validarDuplicadoEquipo(equipo)) {
                throw new ReservaException("El equipo ya existe");
            }
            equipos.add(equipo);
            numEquipos = equipos.size();
            relacionReservaEquipo.put(this, equipos);
        } catch (Exception e) {
            throw new ReservaException("Error al agregar equipo", e);
        }
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
        for (Equipo equipo : equipos) {
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
        for (int i = 0; i < equipos.size(); i++) {
            if (equipos.get(i) != null && equipos.get(i).getCodigoEquipo().equals(codigo)) {
                equipos.set(i, nuevoEquipo);
                relacionReservaEquipo.put(this, equipos);
                return "Equipo actualizado.";
            }
        }
        return "Código de equipo inválido.";
    }

    public String eliminarEquipo(String codigo) {
        boolean eliminado = equipos.removeIf(equipo -> equipo != null && equipo.getCodigoEquipo().equals(codigo));
        if (eliminado) {
            numEquipos = equipos.size();
            relacionReservaEquipo.put(this, equipos);
            return "Equipo eliminado.";
        }
        return "Código de equipo inválido.";
    }

    public String eliminarEquipo(Equipo equipo) {
        if (equipo == null) return "Equipo nulo.";
        boolean eliminado = equipos.remove(equipo);
        if (eliminado) {
            numEquipos = equipos.size();
            relacionReservaEquipo.put(this, equipos);
            return "Equipo eliminado.";
        }
        return "Equipo no encontrado.";
    }

    /**
     * Valida si un equipo ya existe (duplicado).
     * @param equipo El equipo a validar.
     * @return true si el equipo ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoEquipo(Equipo equipo) {
        if (equipo == null) return false;

        for (Equipo e : equipos) {
            if (e != null && e.getCodigoEquipo().equals(equipo.getCodigoEquipo())) {
                return true;
            }
        }
        return false;
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
                        "│ Equipos: %-3d │ Usuario: %-30s │%n" +
                        "│ Auditorio: %-30s │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                codigoReserva, idReserva, estado.getDescripcion(),
                fechaInicio.toString(),
                fechaFin.toString(),
                numEquipos,
                usuario != null ? usuario.getNombre() + " " + usuario.getApellido() : "Sin asignar",
                auditorio != null ? auditorio.getNombre() : "Sin asignar");
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

    /**
     * Criterio natural de comparación: por fecha de inicio (ascendente)
     */
    @Override
    public int compareTo(Reserva o) {
        if (this.fechaInicio.before(o.fechaInicio)) {
            return -1;
        } else if (this.fechaInicio.after(o.fechaInicio)) {
            return 1;
        }
        // Si las fechas son iguales, comparar por ID
        if (this.idReserva < o.idReserva) {
            return -1;
        } else if (this.idReserva > o.idReserva) {
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
        equipos.sort(new OrdenarEquipoNombre());
    }

    /**
     * Ordena los equipos de la reserva por categoría
     */
    public void ordenarEquiposPorCategoria() {
        equipos.sort(new OrdenarEquipoCategoria());
    }

    /**
     * Ordena los equipos de la reserva por disponibilidad
     */
    public void ordenarEquiposPorDisponibilidad() {
        equipos.sort(new OrdenarEquipoDisponibilidad());
    }

    /**
     * Ordena los equipos de la reserva por ID (ascendente)
     */
    public void ordenarEquiposPorId() {
        equipos.sort(new OrdenarEquipoId());
    }

    public void inicializar() {
        // Aquí puedes agregar datos de ejemplo si lo deseas, por ejemplo:
        // crearEquipo(new Equipo());
    }
}