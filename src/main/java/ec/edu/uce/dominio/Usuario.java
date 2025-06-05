package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa un usuario del sistema SIRAA.
 * Esta clase maneja la información personal y las reservas de un usuario.
 */
public class Usuario {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "USR";
    private static int contadorUsuarios = 0;
    
    private String codigoUsuario;
    private String nombre;
    private String apellido;
    private String correo;
    private int numReservas = 0;
    private Reserva[] reservas;
    private Estado estado;

    // Constructores
    public Usuario(String nombre, String apellido, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.reservas = new Reserva[0];
        this.estado = Estado.CONFIRMADA;
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

    // Método para generar códigos automáticos
    private String generarCodigoUsuario() {
        contadorUsuarios++;
        return PREFIJO_CODIGO + String.format("%04d", contadorUsuarios);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Usuario)) return false;
        
        Usuario otroUsuario = (Usuario) obj;
        return this.codigoUsuario.equals(otroUsuario.codigoUsuario) &&
               this.correo.equals(otroUsuario.correo);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Usuario)) return false;
        
        Usuario otroUsuario = (Usuario) obj;
        return this.codigoUsuario.equals(otroUsuario.codigoUsuario) ||
               this.correo.equals(otroUsuario.correo);
    }

    // Getters y Setters
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
        return reservas;
    }

    // ========================
    // CRUD de Reservas
    // ========================

    // Crear Reserva - Sobrecarga de métodos
    public void crearReserva() {
        crearReserva(new Reserva());
    }

    public void crearReserva(Reserva reserva){
        if (reserva == null) return;
        
        if (numReservas == reservas.length) {
            Reserva[] aux = reservas;
            reservas = new Reserva[numReservas + 1];
            System.arraycopy(aux, 0, reservas, 0, numReservas);
        }
        reservas[numReservas] = reserva;
        numReservas++;
    }

    public void crearReservaPrioritaria(Estado estado, String motivo) {
        crearReserva(new ReservaPrioritaria(0, new Date(), new Date(), estado, motivo));
    }

    // Listar Reservas - Sobrecarga de métodos
    public String listarReservas() {
        return listarReservas(false);
    }

    public String listarReservas(boolean soloActivas) {
        StringBuilder texto = new StringBuilder();
        for (Reserva r: reservas) {
            if (!soloActivas || r.getEstado() != Estado.CANCELADA) {
                texto.append(r).append("\r\n");
            }
        }
        return texto.toString();
    }

    // Actualizar Reserva - Sobrecarga de métodos
    public void actualizarReserva(int indice, int nuevoId, Date nuevaInicio, Date nuevaFin) {
        if (indice >= 0 && indice < numReservas) {
            reservas[indice].setIdReserva(nuevoId);
            reservas[indice].setFechaInicio(nuevaInicio);
            reservas[indice].setFechaFin(nuevaFin);
        }
    }

    public void actualizarReserva(int indice, Reserva nuevaReserva) {
        if (indice >= 0 && indice < numReservas && nuevaReserva != null) {
            reservas[indice] = nuevaReserva;
        }
    }

    // Eliminar Reserva - Sobrecarga de métodos
    public void eliminarReserva(int indice) {
        if (indice < 0 || indice >= reservas.length) {
            return;
        }
        Reserva[] aux = new Reserva[reservas.length - 1];
        // Copiar elementos antes del índice
        if (indice > 0) {
            System.arraycopy(reservas, 0, aux, 0, indice);
        }
        // Copiar elementos después del índice
        if (indice < reservas.length - 1) {
            System.arraycopy(reservas, indice + 1, aux, indice, reservas.length - indice - 1);
        }
        reservas = aux; // Actualizar el array original
        numReservas--;
    }

    public void eliminarReserva(Reserva reserva) {
        if (reserva == null) return;
        
        for (int i = 0; i < numReservas; i++) {
            if (reservas[i].equals(reserva)) {
                eliminarReserva(i);
                break;
            }
        }
    }

    @Override
    public String toString() {
        return "[Usuario " + codigoUsuario + ": " + nombre + " " + apellido + 
               ", Correo: " + correo + 
               ", Estado: " + estado + "]";
    }
}