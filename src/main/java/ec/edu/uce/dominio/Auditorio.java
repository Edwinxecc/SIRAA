package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;

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
    private Reserva[] reservas; // Arreglo para almacenar las reservas del auditorio

    public Auditorio(String nombre, int capacidad){
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.reservas = new Reserva[10]; // Capacidad inicial de 10 reservas
        this.numReservas = 0;
        this.idAuditorio = generarIdAuditorio();
        this.codigoAuditorio = generarCodigoAuditorio();
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
        return numReservas;
    }

    public Reserva[] getReservas() {
        // Devuelve una copia con solo los elementos reales para evitar NullPointerExceptions
        Reserva[] reservasActivas = new Reserva[numReservas];
        System.arraycopy(reservas, 0, reservasActivas, 0, numReservas);
        return reservasActivas;
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
    public void crearReserva(int idReserva, java.util.Date fechaInicio, java.util.Date fechaFin) {
        crearReserva(new Reserva(idReserva, fechaInicio, fechaFin));
    }

    /**
     * Crea una nueva reserva.
     * @param reserva Objeto Reserva a crear.
     */
    public void crearReserva(Reserva reserva) {
        if (reserva == null) return;
        
        // Validar duplicado
        if (validarDuplicadoReserva(reserva)) {
            System.out.println("La reserva ya existe y no se puede agregar duplicada.");
            return;
        }
        
        if (numReservas == reservas.length) {
            // Redimensionar el arreglo
            Reserva[] aux = reservas;
            reservas = new Reserva[numReservas + 1];
            System.arraycopy(aux, 0, reservas, 0, numReservas);
        }
        reservas[numReservas] = reserva;
        numReservas++;
    }

    /**
     * Lista todas las reservas del auditorio.
     * @return Una cadena con la lista de reservas.
     */
    public String listarReservas() {
        if (numReservas == 0) {
            return "[!] No hay reservas registradas para este auditorio.";
        }
        StringBuilder texto = new StringBuilder();
        texto.append("=== RESERVAS DEL AUDITORIO: ").append(nombre.toUpperCase()).append(" ===\n");
        int indice = 0;
        for (Reserva reserva : getReservas()) {
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
        if (numReservas == 0) {
            return "[!] No hay reservas registradas para este auditorio.";
        }
        if (estado == null) {
            return "[!] Estado no válido.";
        }
        
        StringBuilder texto = new StringBuilder();
        texto.append("=== RESERVAS ").append(estado.getDescripcion().toUpperCase()).append(" ===\n");
        int contador = 0;
        
        for (int i = 0; i < numReservas; i++) {
            if (reservas[i] != null && reservas[i].getEstado() == estado) {
                texto.append("[").append(contador++).append("] ").append(reservas[i]).append("\n");
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
            return reservas[indice];
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
        
        for (int i = 0; i < numReservas; i++) {
            if (reservas[i] != null && 
                reservas[i].getCodigoReserva().equals(codigoReserva.trim())) {
                return reservas[i];
            }
        }
        return null;
    }

    /**
     * Actualiza una reserva por su índice.
     * @param indice Índice de la reserva a actualizar.
     * @param nuevaReserva Nuevo objeto Reserva.
     */
    public void actualizarReserva(int indice, Reserva nuevaReserva) {
        if (indice >= 0 && indice < numReservas && nuevaReserva != null) {
            reservas[indice] = nuevaReserva;
        }
    }

    /**
     * Actualiza el estado de una reserva.
     * @param indice Índice de la reserva a actualizar.
     * @param nuevoEstado Nuevo estado de la reserva.
     */
    public void actualizarEstadoReserva(int indice, Estado nuevoEstado) {
        if (indice >= 0 && indice < numReservas && nuevoEstado != null) {
            reservas[indice].setEstado(nuevoEstado);
        }
    }

    /**
     * Elimina una reserva por su índice.
     * @param indice Índice de la reserva a eliminar.
     */
    public void eliminarReserva(int indice) {
        if (indice < 0 || indice >= numReservas) {
            return;
        }
        
        for (int i = indice; i < numReservas - 1; i++) {
            reservas[i] = reservas[i + 1];
        }
        reservas[numReservas - 1] = null;
        numReservas--;
    }

    /**
     * Elimina una reserva por su objeto.
     * @param reserva Objeto Reserva a eliminar.
     */
    public void eliminarReserva(Reserva reserva) {
        if (reserva == null) return;
        
        for (Reserva reservaActual : getReservas()) {
            if (reservaActual != null && reservaActual.equals(reserva)) {
                // Encontrar el índice de la reserva para eliminarla
                for (int i = 0; i < numReservas; i++) {
                    if (reservas[i] != null && reservas[i].equals(reserva)) {
                        eliminarReserva(i);
                        break;
                    }
                }
                break;
            }
        }
    }

    /**
     * Valida si una reserva ya existe (duplicado).
     * @param reserva La reserva a validar.
     * @return true si la reserva ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoReserva(Reserva reserva) {
        if (reserva == null) return false;
        
        for (int i = 0; i < numReservas; i++) {
            if (reservas[i] != null && reservas[i].validarDuplicado(reserva)) {
                return true;
            }
        }
        return false;
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
        
        for (int i = 0; i < numReservas; i++) {
            if (reservas[i] != null && 
                (reservas[i].getEstado() == Estado.CONFIRMADA || 
                 reservas[i].getEstado() == Estado.EN_PROCESO)) {
                
                // Verificar si hay solapamiento de fechas
                if (!(fechaFin.before(reservas[i].getFechaInicio()) || 
                      fechaInicio.after(reservas[i].getFechaFin()))) {
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
    @Override
    public int compareTo(Auditorio otro) {
        return this.nombre.compareTo(otro.nombre);
    }

    // ========================
    // Métodos de Ordenación para Reservas
    // ========================

    /**
     * Ordena las reservas del auditorio por fecha de inicio (ascendente)
     */
    public void ordenarReservasPorFecha() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, new Comparator<Reserva>() {
            @Override
            public int compare(Reserva r1, Reserva r2) {
                return r1.getFechaInicio().compareTo(r2.getFechaInicio());
            }
        });
        // Actualizar el arreglo interno
        System.arraycopy(reservasActivas, 0, reservas, 0, numReservas);
    }

    /**
     * Ordena las reservas del auditorio por estado
     */
    public void ordenarReservasPorEstado() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, new Comparator<Reserva>() {
            @Override
            public int compare(Reserva r1, Reserva r2) {
                return r1.getEstado().getDescripcion().compareTo(r2.getEstado().getDescripcion());
            }
        });
        // Actualizar el arreglo interno
        System.arraycopy(reservasActivas, 0, reservas, 0, numReservas);
    }

    /**
     * Ordena las reservas del auditorio por ID (ascendente)
     */
    public void ordenarReservasPorId() {
        Reserva[] reservasActivas = getReservas();
        Arrays.sort(reservasActivas, new Comparator<Reserva>() {
            @Override
            public int compare(Reserva r1, Reserva r2) {
                return Integer.compare(r1.getIdReserva(), r2.getIdReserva());
            }
        });
        // Actualizar el arreglo interno
        System.arraycopy(reservasActivas, 0, reservas, 0, numReservas);
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar auditorios por capacidad (ascendente)
     */
    public static final Comparator<Auditorio> COMPARADOR_POR_CAPACIDAD = new Comparator<Auditorio>() {
        @Override
        public int compare(Auditorio a1, Auditorio a2) {
            return Integer.compare(a1.getCapacidad(), a2.getCapacidad());
        }
    };

    /**
     * Comparador para ordenar auditorios por número de reservas (descendente)
     */
    public static final Comparator<Auditorio> COMPARADOR_POR_NUM_RESERVAS = new Comparator<Auditorio>() {
        @Override
        public int compare(Auditorio a1, Auditorio a2) {
            return Integer.compare(a2.getNumReservas(), a1.getNumReservas()); // Descendente
        }
    };

    /**
     * Comparador para ordenar auditorios por ID (ascendente)
     */
    public static final Comparator<Auditorio> COMPARADOR_POR_ID = new Comparator<Auditorio>() {
        @Override
        public int compare(Auditorio a1, Auditorio a2) {
            return Integer.compare(a1.getIdAuditorio(), a2.getIdAuditorio());
        }
    };
}