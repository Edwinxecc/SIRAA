package ec.edu.uce.dominio;

import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;

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
    private Equipo[] equipos;
    private int numEquipos = 0;
    private Estado estado;

    // Constructores
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new Equipo[CAPACIDAD_INICIAL_EQUIPOS_DEFECTO];
        this.estado = Estado.PENDIENTE;
        this.idReserva = generarIdReserva();
        this.codigoReserva = generarCodigoReserva();
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
        this.equipos = new Equipo[CAPACIDAD_INICIAL_EQUIPOS_DEFECTO];
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
        Equipo[] equiposCopia = new Equipo[numEquipos];
        System.arraycopy(equipos, 0, equiposCopia, 0, numEquipos);
        return equiposCopia;
    }

    // ========================
    // CRUD de Equipos
    // ========================

    public void crearEquipo(String nombre, String categoria, boolean disponible) {
        crearEquipo(new Equipo(nombre, categoria, disponible));
    }

    public void crearEquipo(Equipo equipo) {
        if (equipo == null) return;

        if (numEquipos == equipos.length) {
            Equipo[] aux = equipos;
            equipos = new Equipo[numEquipos + 1];
            System.arraycopy(aux, 0, equipos, 0, numEquipos);
        }
        equipos[numEquipos] = equipo;
        numEquipos++;
    }

    public String listarEquipos() {
        return listarEquipos(false);
    }

    public String listarEquipos(boolean soloDisponibles) {
        if (numEquipos == 0) {
            return "No hay equipos asignados a esta reserva.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numEquipos; i++) {
            if (equipos[i] != null && (!soloDisponibles || equipos[i].getDisponibilidad())) {
                sb.append(String.format("[%d] Nombre: %s | Categoría: %s | Disponible: %s%n",
                        i,
                        equipos[i].getNombre(),
                        equipos[i].getCategoria(),
                        equipos[i].getDisponibilidad() ? "Sí" : "No"
                ));
            }
        }
        return sb.toString();
    }

    public String actualizarEquipo(int indice, String nuevoNombre, String nuevaCategoria, boolean nuevaDisponibilidad) {
        if (indice >= 0 && indice < numEquipos) {
            equipos[indice].setNombre(nuevoNombre);
            equipos[indice].setCategoria(nuevaCategoria);
            equipos[indice].setDisponibilidad(nuevaDisponibilidad);
            return "Equipo actualizado.";
        }
        return "Índice de equipo inválido.";
    }

    public String actualizarEquipo(int indice, Equipo nuevoEquipo) {
        if (indice >= 0 && indice < numEquipos && nuevoEquipo != null) {
            equipos[indice] = nuevoEquipo;
            return "Equipo actualizado.";
        }
        return "Índice de equipo inválido o equipo nulo.";
    }

    public String eliminarEquipo(int indice) {
        if (indice >= 0 && indice < numEquipos) {
            for (int i = indice; i < numEquipos - 1; i++) {
                equipos[i] = equipos[i + 1];
            }
            equipos[numEquipos - 1] = null;
            numEquipos--;

            Equipo[] aux = new Equipo[numEquipos];
            System.arraycopy(equipos, 0, aux, 0, numEquipos);
            equipos = aux;

            return "Equipo eliminado.";
        }
        return "Índice de equipo inválido.";
    }

    public String eliminarEquipo(Equipo equipo) {
        if (equipo == null) return "Equipo nulo.";
        
        for (int i = 0; i < numEquipos; i++) {
            if (equipos[i] != null && equipos[i].equals(equipo)) {
                return eliminarEquipo(i);
            }
        }
        return "Equipo no encontrado.";
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

    @Override
    public int compareTo(Reserva otraReserva) {
        return this.idReserva - otraReserva.idReserva;
    }

    // ========================
    // Métodos de Ordenación para Equipos
    // ========================

    /**
     * Ordena los equipos de la reserva por nombre
     */
    public void ordenarEquiposPorNombre() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, Equipo.COMPARADOR_POR_NOMBRE);
        // Actualizar el arreglo interno
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por categoría
     */
    public void ordenarEquiposPorCategoria() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, Equipo.COMPARADOR_POR_CATEGORIA);
        // Actualizar el arreglo interno
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por disponibilidad (disponibles primero)
     */
    public void ordenarEquiposPorDisponibilidad() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, Equipo.COMPARADOR_POR_DISPONIBILIDAD);
        // Actualizar el arreglo interno
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    /**
     * Ordena los equipos de la reserva por ID (ascendente)
     */
    public void ordenarEquiposPorId() {
        Equipo[] equiposActivos = getEquipos();
        Arrays.sort(equiposActivos, Equipo.COMPARADOR_POR_ID);
        // Actualizar el arreglo interno
        System.arraycopy(equiposActivos, 0, equipos, 0, numEquipos);
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar reservas por fecha de inicio (ascendente)
     */
    public static final Comparator<Reserva> COMPARADOR_POR_FECHA_INICIO = new Comparator<Reserva>() {
        @Override
        public int compare(Reserva r1, Reserva r2) {
            return r1.getFechaInicio().compareTo(r2.getFechaInicio());
        }
    };

    /**
     * Comparador para ordenar reservas por fecha de fin (ascendente)
     */
    public static final Comparator<Reserva> COMPARADOR_POR_FECHA_FIN = new Comparator<Reserva>() {
        @Override
        public int compare(Reserva r1, Reserva r2) {
            return r1.getFechaFin().compareTo(r2.getFechaFin());
        }
    };

    /**
     * Comparador para ordenar reservas por estado
     */
    public static final Comparator<Reserva> COMPARADOR_POR_ESTADO = new Comparator<Reserva>() {
        @Override
        public int compare(Reserva r1, Reserva r2) {
            return r1.getEstado().getDescripcion().compareTo(r2.getEstado().getDescripcion());
        }
    };

    /**
     * Comparador para ordenar reservas por número de equipos (descendente)
     */
    public static final Comparator<Reserva> COMPARADOR_POR_NUM_EQUIPOS = new Comparator<Reserva>() {
        @Override
        public int compare(Reserva r1, Reserva r2) {
            return Integer.compare(r2.getEquipos().length, r1.getEquipos().length); // Descendente
        }
    };
}
