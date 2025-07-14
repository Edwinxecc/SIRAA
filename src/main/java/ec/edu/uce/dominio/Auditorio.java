package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

/**
 * Representa un auditorio en el sistema SIRAA.
 * Esta clase maneja la información y las reservas de un auditorio.
 * Implementa la relación uno a muchos (1:0..n) con Reserva usando arreglos.
 */
public class Auditorio implements IAdministrarCRUD, Comparable<Auditorio> {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "AUD";
    private static int contadorAuditorios = 0;

    private int idAuditorio;
    private String codigoAuditorio;
    private String nombre;
    private int capacidad;
    private int numReservas = 0;
    private Map<String, Reserva> reservas;

    public Auditorio(String nombre, int capacidad){
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.reservas = new HashMap<>();
        this.numReservas = 0;
        this.idAuditorio = generarIdAuditorio();
        this.codigoAuditorio = generarCodigoAuditorio();
        inicializar();
    }

    public Auditorio(){
        this("Sin nombre", 0);
    }

    // Método para generar IDs automáticos
    private int generarIdAuditorio() {
        contadorAuditorios++;
        return contadorAuditorios;
    }

    // Método para generar códigos automáticos
    private String generarCodigoAuditorio() {
        return PREFIJO_CODIGO + String.format("%04d", contadorAuditorios);
    }

    // Getters y setters
    public int getIdAuditorio() {
        return idAuditorio;
    }

    public String getCodigoAuditorio() {
        return codigoAuditorio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.trim().isEmpty()) {
            this.nombre = nombre;
        }
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad >= 0) {
            this.capacidad = capacidad;
        }
    }

    public int getNumReservas() {
        return reservas.size();
    }

    public Reserva[] getReservas() {
        return reservas.values().toArray(new Reserva[0]);
    }

    // ========================
    // CRUD de Reservas
    // ========================

    /**
     * Crea una nueva reserva con valores por defecto.
     */
    public void crearReserva() {
        crearReserva(new Reserva());
    }

    /**
     * Crea una nueva reserva con parámetros específicos.
     * @param idReserva ID de la reserva
     * @param fechaInicio Fecha de inicio
     * @param fechaFin Fecha de fin
     */
    public void crearReserva(int idReserva, Date fechaInicio, Date fechaFin) {
        crearReserva(new Reserva(idReserva, fechaInicio, fechaFin));
    }

    /**
     * Crea una nueva reserva.
     * @param reserva Objeto Reserva a crear.
     */
    public void crearReserva(Reserva reserva) {
        if (reserva == null) return;
        if (reservas.containsKey(reserva.getCodigoReserva())) {
            System.err.println("La reserva ya existe y no se puede agregar duplicada.");
            return;
        }
        reservas.put(reserva.getCodigoReserva(), reserva);
    }

    /**
     * Lista todas las reservas del auditorio.
     * @return Una cadena con la lista de reservas.
     */
    public String listarReservas() {
        if (reservas.isEmpty()) {
            return "[!] No hay reservas registradas para este auditorio.";
        }
        StringBuilder texto = new StringBuilder();
        texto.append("=== RESERVAS DEL AUDITORIO: ").append(nombre.toUpperCase()).append(" ===\n");
        int indice = 0;
        for (Reserva reserva : reservas.values()) {
            if (reserva != null) {
                texto.append("[").append(indice++).append("] ").append(reserva).append("\n");
            }
        }
        return texto.toString();
    }

    /**
     * Lista las reservas por estado.
     * @param estado Estado de las reservas a listar.
     * @return Una cadena con la lista de reservas del estado especificado.
     */
    public String listarReservasPorEstado(Estado estado) {
        if (reservas.isEmpty()) {
            return "[!] No hay reservas registradas para este auditorio.";
        }
        if (estado == null) {
            return "[!] Estado no válido.";
        }

        StringBuilder texto = new StringBuilder();
        texto.append("=== RESERVAS ").append(estado.getDescripcion().toUpperCase()).append(" ===\n");
        int contador = 0;

        for (Reserva reserva : reservas.values()) {
            if (reserva != null && reserva.getEstado() == estado) {
                texto.append("[").append(contador++).append("] ").append(reserva).append("\n");
            }
        }

        if (contador == 0) {
            return "[!] No hay reservas con estado: " + estado.getDescripcion();
        }

        return texto.toString();
    }

    /**
     * Obtiene una reserva por su índice.
     * @param indice Índice de la reserva.
     * @return La reserva en el índice especificado o null si el índice es inválido.
     */
    public Reserva getReserva(int indice) {
        if (indice >= 0 && indice < numReservas) {
            return reservas.values().toArray(new Reserva[0])[indice];
        }
        return null;
    }

    /**
     * Busca una reserva por su código.
     * @param codigoReserva Código de la reserva a buscar.
     * @return La reserva encontrada o null si no existe.
     */
    public Reserva buscarReservaPorCodigo(String codigoReserva) {
        if (codigoReserva == null || codigoReserva.trim().isEmpty()) {
            return null;
        }

        return reservas.get(codigoReserva);
    }

    /**
     * Actualiza una reserva por su código.
     * @param codigo Código de la reserva a actualizar.
     * @param nuevaReserva Nuevo objeto Reserva.
     */
    public void actualizarReserva(String codigo, Reserva nuevaReserva) {
        if (reservas.containsKey(codigo) && nuevaReserva != null) {
            reservas.put(codigo, nuevaReserva);
        }
    }

    /**
     * Actualiza el estado de una reserva.
     * @param codigo Código de la reserva a actualizar.
     * @param nuevoEstado Nuevo estado de la reserva.
     */
    public void actualizarEstadoReserva(String codigo, Estado nuevoEstado) {
        if (reservas.containsKey(codigo) && nuevoEstado != null) {
            reservas.get(codigo).setEstado(nuevoEstado);
        }
    }

    /**
     * Elimina una reserva por su código.
     * @param codigo Código de la reserva a eliminar.
     */
    public void eliminarReserva(String codigo) {
        reservas.remove(codigo);
    }

    /**
     * Elimina una reserva por su objeto.
     * @param reserva Objeto Reserva a eliminar.
     */
    public void eliminarReserva(Reserva reserva) {
        if (reserva == null) return;
        reservas.remove(reserva.getCodigoReserva());
    }

    /**
     * Valida si una reserva ya existe (duplicado).
     * @param reserva La reserva a validar.
     * @return true si la reserva ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoReserva(Reserva reserva) {
        if (reserva == null) return false;

        return reservas.containsKey(reserva.getCodigoReserva());
    }

    /**
     * Verifica si el auditorio está disponible en un rango de fechas.
     * @param fechaInicio Fecha de inicio a verificar.
     * @param fechaFin Fecha de fin a verificar.
     * @return true si está disponible, false en caso contrario.
     */
    public boolean estaDisponible(java.util.Date fechaInicio, java.util.Date fechaFin) {
        if (fechaInicio == null || fechaFin == null) {
            return false;
        }

        for (Reserva reserva : reservas.values()) {
            if (reserva != null &&
                    (reserva.getEstado() == Estado.CONFIRMADA ||
                            reserva.getEstado() == Estado.EN_PROCESO)) {

                // Verificar si hay solapamiento de fechas
                if (!(fechaFin.before(reserva.getFechaInicio()) ||
                        fechaInicio.after(reserva.getFechaFin()))) {
                    return false; // Hay conflicto de fechas
                }
            }
        }
        return true; // No hay conflictos
    }

    // ========================
    // Métodos de la interfaz IAdministrarCRUD
    // ========================

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Auditorio)) {
            return "[!] El objeto no es un Auditorio válido.";
        }
        Auditorio auditorioNuevo = (Auditorio) obj;
        if (validarDuplicadoAuditorio(auditorioNuevo)) {
            return "[!] El auditorio ya existe.";
        }
        // Aquí se simula la creación del auditorio
        return "[✓] Auditorio creado correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Auditorio)) {
            return "[!] El objeto no es un Auditorio válido.";
        }
        Auditorio auditorioEditado = (Auditorio) obj;
        // Aquí se simula la edición del auditorio
        this.nombre = auditorioEditado.getNombre();
        this.capacidad = auditorioEditado.getCapacidad();
        return "[✓] Auditorio editado correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Auditorio)) {
            return "[!] El objeto no es un Auditorio válido.";
        }
        Auditorio auditorioABorrar = (Auditorio) obj;
        if (this.equals(auditorioABorrar)) {
            // Aquí se simula la eliminación del auditorio
            return "[✓] Auditorio eliminado correctamente.";
        }
        return "[!] Auditorio no encontrado.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == this.idAuditorio) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    /**
     * Valida si un auditorio ya existe (duplicado).
     * @param auditorio El auditorio a validar.
     * @return true si el auditorio ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoAuditorio(Auditorio auditorio) {
        if (auditorio == null) return false;
        return this.idAuditorio == auditorio.idAuditorio ||
                this.codigoAuditorio.equals(auditorio.codigoAuditorio) ||
                this.nombre.equals(auditorio.nombre);
    }

    // toString
    @Override
    public String toString() {
        return String.format("┌─ AUDITORIO ────────────────────────────────────────────────────────┐%n" +
                        "│ Código: %-15s │ ID: %-8d │ Capacidad: %-8d personas │%n" +
                        "│ Nombre: %-50s │%n" +
                        "│ Reservas Activas: %-3d │ Estado: %-20s │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                codigoAuditorio, idAuditorio, capacidad,
                nombre,
                numReservas, "Disponible");
    }

    // equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Auditorio)) return false;

        Auditorio otro = (Auditorio) obj;
        return this.idAuditorio == otro.idAuditorio &&
                this.codigoAuditorio.equals(otro.codigoAuditorio) &&
                this.nombre.equals(otro.nombre) &&
                this.capacidad == otro.capacidad;
    }

    // Comparable
    /**
     * Criterio natural de comparación: por ID (ascendente) y luego por nombre (ascendente)
     */
    @Override
    public int compareTo(Auditorio o) {
        if (this.idAuditorio < o.idAuditorio) {
            return -1;
        } else if (this.idAuditorio > o.idAuditorio) {
            return 1;
        }
        // Si los id son iguales, comparar por nombre
        if (this.nombre.compareTo(o.nombre) < 0) {
            return -1;
        } else if (this.nombre.compareTo(o.nombre) > 0) {
            return 1;
        }
        return 0;
    }

    // ========================
    // Métodos de Ordenación para Reservas
    // ========================

    /**
     * Ordena las reservas del auditorio por fecha de inicio (ascendente)
     */
    public void ordenarReservasPorFecha() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, new OrdenarReservaFechaInicio());
        reservas.clear();
        for (Reserva reserva : reservasActivas) {
            reservas.put(reserva.getCodigoReserva(), reserva);
        }
    }

    /**
     * Ordena las reservas del auditorio por estado
     */
    public void ordenarReservasPorEstado() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, new OrdenarReservaEstado());
        reservas.clear();
        for (Reserva reserva : reservasActivas) {
            reservas.put(reserva.getCodigoReserva(), reserva);
        }
    }

    /**
     * Ordena las reservas del auditorio por ID (ascendente)
     */
    public void ordenarReservasPorId() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, (r1, r2) -> Integer.compare(r1.getIdReserva(), r2.getIdReserva()));
        reservas.clear();
        for (Reserva reserva : reservasActivas) {
            reservas.put(reserva.getCodigoReserva(), reserva);
        }
    }

    private void inicializar() {
        // Aquí puedes agregar datos de ejemplo si lo deseas, por ejemplo:
        // crearReserva(new Reserva());
    }
}
