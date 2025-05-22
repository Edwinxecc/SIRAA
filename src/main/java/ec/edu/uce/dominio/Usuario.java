// Usuario.java
package ec.edu.uce.dominio;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String correo;
    private List<Reserva> reservas;

    public Usuario() {
        this.reservas = new ArrayList<>();
    }

    public Usuario(int id, String nombre, String apellido, String correo) {
        this.usuarioId = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.reservas = new ArrayList<>();
    }

    public Usuario(Usuario[] usuarios) {
        usuarios = new Usuario[usuarios.length];
        this.reservas = new ArrayList<>();
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int generarId() {
        Random num = new Random();
        return num.nextInt(999);
    }

    // Métodos CRUD de Reserva

    public void crearReserva(int idReserva, Auditorio auditorio, LocalDateTime inicio, LocalDateTime fin) {
        Reserva nueva = new Reserva(idReserva, this, auditorio, inicio, fin);
        reservas.add(nueva);
        auditorio.agregarReserva(nueva);
    }

    public Reserva buscarReserva(int idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        return null;
    }

    public boolean editarReserva(int idReserva, LocalDateTime nuevaInicio, LocalDateTime nuevaFin) {
        Reserva r = buscarReserva(idReserva);
        if (r != null) {
            r.setFechaInicio(nuevaInicio);
            r.setFechaFin(nuevaFin);
            return true;
        }
        return false;
    }

    public boolean eliminarReserva(int idReserva) {
        Reserva r = buscarReserva(idReserva);
        if (r != null) {
            reservas.remove(r);
            r.getAuditorio().eliminarReserva(r);
            return true;
        }
        return false;
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "ID: " + this.usuarioId + "\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellido: " + this.apellido + "\n" +
                "Correo: " + this.correo + "\n" +
                "Reservas: " + reservas.size();
    }
}
