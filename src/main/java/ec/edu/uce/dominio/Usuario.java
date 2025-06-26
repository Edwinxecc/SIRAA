// Usuario.java
package ec.edu.uce.dominio;

import java.util.Date;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * Representa un usuario del sistema SIRAA.
 * Esta clase maneja la información personal y las reservas de un usuario.
 */
public class Usuario implements IAdministrarCRUD, Comparable<Usuario> {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "USR";
    private static int contadorUsuarios = 0;

    private int idUsuario;
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private Map<String, Reserva> reservas;
    private Estado estado;

    // Constructores
    public Usuario(String nombre, String apellido, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.reservas = new HashMap<>();
        this.estado = Estado.CONFIRMADA;
        this.idUsuario = generarIdUsuario();
        this.codigoUsuario = generarCodigoUsuario();
    }

    public Usuario(){
        this("Sin nombre", "Sin apellido", "NOuser@example.com");
    }

    // Constructor con objeto Usuario
    public Usuario(Usuario usuario) {
        this(usuario.getNombre(), usuario.getApellido(), usuario.getCorreo());
        this.estado = usuario.getEstado();
    }

    // Método para generar IDs automáticos
    private int generarIdUsuario() {
        contadorUsuarios++;
        return contadorUsuarios;
    }

    // Método para generar códigos automáticos
    private String generarCodigoUsuario() {
        return PREFIJO_CODIGO + String.format("%04d", contadorUsuarios);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario)) return false;

        Usuario otroUsuario = (Usuario) obj;
        return this.idUsuario == otroUsuario.idUsuario &&
                this.codigoUsuario.equals(otroUsuario.codigoUsuario) &&
                this.correo.equals(otroUsuario.correo);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Usuario)) return false;

        Usuario otroUsuario = (Usuario) obj;
        return this.idUsuario == otroUsuario.idUsuario ||
                this.codigoUsuario.equals(otroUsuario.codigoUsuario) ||
                this.correo.equals(otroUsuario.correo);
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null){
            this.nombre = nombre;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido.length() > 3){
            this.apellido = apellido;
        }
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Reserva[] getReservas() {
        return reservas.values().toArray(new Reserva[0]);
    }

    public int getNumReservas() {
        return reservas.size();
    }

    // ========================
    // CRUD de Reservas
    // ========================

    // Crear Reserva - Sobrecarga de métodos
    public void crearReserva() {
        // Se crea una ReservaPrioritaria por defecto ya que Reserva es abstracta.
        crearReserva(new ReservaPrioritaria());
    }

    public void crearReserva(Reserva reserva){
        if (reserva == null) return;
        if (reservas.containsKey(reserva.getCodigoReserva())) {
            System.out.println("[!] Reserva duplicada. No se puede agregar.");
            return;
        }
        reservas.put(reserva.getCodigoReserva(), reserva);
    }

    public void crearReservaPrioritaria(Estado estado, String motivo) {
        crearReserva(new ReservaPrioritaria(0, new Date(), new Date(), estado, motivo));
    }

    // Listar Reservas - Sobrecarga de métodos
    public String listarReservas() {
        return listarReservas(false);
    }

    public String listarReservas(boolean soloActivas) {
        if (reservas.isEmpty()) {
            return "No hay reservas asignadas a este usuario.";
        }
        StringBuilder texto = new StringBuilder();
        for (Reserva r : reservas.values()) {
            if (r != null && (!soloActivas || r.getEstado() != Estado.CANCELADA)) {
                texto.append(r).append("\r\n");
            }
        }
        return texto.toString();
    }

    // Actualizar Reserva - Sobrecarga de métodos
    public void actualizarReserva(String codigo, Reserva nuevaReserva) {
        if (reservas.containsKey(codigo) && nuevaReserva != null) {
            reservas.put(codigo, nuevaReserva);
        }
    }

    // Eliminar Reserva - Sobrecarga de métodos
    public void eliminarReserva(String codigo) {
        reservas.remove(codigo);
    }

    public void eliminarReserva(Reserva reserva) {
        if (reserva == null) return;
        reservas.remove(reserva.getCodigoReserva());
    }

    // Métodos de la interfaz IAdministrarCRUD

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Usuario)) {
            return "[!] El objeto no es un Usuario válido.";
        }
        Usuario usuarioNuevo = (Usuario) obj;

        // Validar duplicado por correo
        for (Reserva r : reservas.values()) {
            if (r != null && r.validarDuplicado(usuarioNuevo)) {
                return "[!] El usuario ya existe.";
            }
        }

        crearReserva(new ReservaPrioritaria());
        return "[✓] Usuario creado correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Usuario)) {
            return "[!] El objeto no es un Usuario válido.";
        }
        Usuario usuarioEditado = (Usuario) obj;
        // Aquí se simula la edición del usuario
        this.nombre = usuarioEditado.getNombre();
        this.apellido = usuarioEditado.getApellido();
        this.correo = usuarioEditado.getCorreo();
        this.estado = usuarioEditado.getEstado();
        return "[✓] Usuario editado correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Usuario)) {
            return "[!] El objeto no es un Usuario válido.";
        }
        Usuario usuarioABorrar = (Usuario) obj;
        for (Reserva r : reservas.values()) {
            if (r != null && r.equals(usuarioABorrar)) {
                eliminarReserva(r.getCodigoReserva());
                return "[✓] Usuario eliminado correctamente.";
            }
        }
        return "[!] Usuario no encontrado.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == this.idUsuario) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    // ========================
    // Método inicializar
    // ========================

    public void inicializar() {
        // Crear 5 reservas de ejemplo
        crearReserva(new ReservaPrioritaria(1, new Date(), new Date(), Estado.PRIORIDAD_BAJA, "Reunión de trabajo"));
        crearReserva(new ReservaPrioritaria(2, new Date(), new Date(), Estado.PRIORIDAD_MEDIA, "Presentación"));
        crearReserva(new ReservaPrioritaria(3, new Date(), new Date(), Estado.PRIORIDAD_ALTA, "Conferencia"));
        crearReserva(new ReservaPrioritaria(4, new Date(), new Date(), Estado.PRIORIDAD_URGENTE, "Evento importante"));
        crearReserva(new ReservaPrioritaria(5, new Date(), new Date(), Estado.PRIORIDAD_BAJA, "Capacitación"));
    }

    @Override
    public String toString() {
        return String.format("┌─ USUARIO ──────────────────────────────────────────────────────────┐%n" +
                           "│ Código: %-15s │ ID: %-8d │ Estado: %-20s │%n" +
                           "│ Nombre: %-20s │ Apellido: %-20s │%n" +
                           "│ Correo: %-50s │%n" +
                           "│ Reservas Activas: %-3d │%n" +
                           "└─────────────────────────────────────────────────────────────────────┘",
                           codigoUsuario, idUsuario, estado.getDescripcion(),
                           nombre, apellido,
                           correo, reservas.size());
    }

    @Override
    public int compareTo(Usuario otroUsuario) {
        return Integer.compare(this.idUsuario, otroUsuario.idUsuario);
    }

    // ========================
    // Métodos de Ordenación para Reservas
    // ========================

    /**
     * Ordena las reservas del usuario por fecha de inicio (ascendente)
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
        reservas.clear();
        for (Reserva r : reservasActivas) {
            reservas.put(r.getCodigoReserva(), r);
        }
    }

    /**
     * Ordena las reservas del usuario por estado
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
        reservas.clear();
        for (Reserva r : reservasActivas) {
            reservas.put(r.getCodigoReserva(), r);
        }
    }

    /**
     * Ordena las reservas del usuario por ID (ascendente)
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
        reservas.clear();
        for (Reserva r : reservasActivas) {
            reservas.put(r.getCodigoReserva(), r);
        }
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar usuarios por nombre
     */
    public static final Comparator<Usuario> COMPARADOR_POR_NOMBRE = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            int comparacionNombre = u1.getNombre().compareTo(u2.getNombre());
            if (comparacionNombre != 0) {
                return comparacionNombre;
            }
            return u1.getApellido().compareTo(u2.getApellido());
        }
    };

    /**
     * Comparador para ordenar usuarios por apellido
     */
    public static final Comparator<Usuario> COMPARADOR_POR_APELLIDO = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            int comparacionApellido = u1.getApellido().compareTo(u2.getApellido());
            if (comparacionApellido != 0) {
                return comparacionApellido;
            }
            return u1.getNombre().compareTo(u2.getNombre());
        }
    };

    /**
     * Comparador para ordenar usuarios por correo
     */
    public static final Comparator<Usuario> COMPARADOR_POR_CORREO = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return u1.getCorreo().compareTo(u2.getCorreo());
        }
    };

    /**
     * Comparador para ordenar usuarios por número de reservas (descendente)
     */
    public static final Comparator<Usuario> COMPARADOR_POR_NUM_RESERVAS = new Comparator<Usuario>() {
        @Override
        public int compare(Usuario u1, Usuario u2) {
            return Integer.compare(u2.getNumReservas(), u1.getNumReservas()); // Descendente
        }
    };
}
