// Usuario.java
package ec.edu.uce.dominio;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String correo;
    private String password;
    private List<Reserva> reservas;
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@uce\\.edu\\.ec$");

    public Usuario() {
        this.reservas = new ArrayList<>();
    }

    public Usuario(int id, String nombre, String apellido, String correo) {
        this();
        setUsuarioId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo");
        }
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no debe contener números");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (apellido.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El apellido no debe contener números");
        }
        this.apellido = apellido.trim();
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        if (!EMAIL_PATTERN.matcher(correo.trim()).matches()) {
            throw new IllegalArgumentException("El correo debe ser un correo institucional válido (@uce.edu.ec)");
        }
        this.correo = correo.trim().toLowerCase();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.trim().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        this.password = password;
    }

    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    public int generarId() {
        Random random = new Random();
        int newId;
        do {
            newId = random.nextInt(900) + 100; // Genera números entre 100 y 999
        } while (newId <= 0);
        return newId;
    }

    // Métodos CRUD de Reserva
    public void crearReserva(int idReserva, Auditorio auditorio, LocalDateTime inicio, LocalDateTime fin) {
        if (inicio == null || fin == null) {
            throw new IllegalArgumentException("Las fechas de inicio y fin no pueden ser nulas");
        }
        if (inicio.isAfter(fin)) {
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        if (inicio.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("No se pueden crear reservas en fechas pasadas");
        }

        Reserva nueva = new Reserva(idReserva, this, auditorio, inicio, fin);
        reservas.add(nueva);
        auditorio.agregarReserva(nueva);
    }

    public Reserva buscarReserva(int idReserva) {
        return reservas.stream()
                .filter(r -> r.getId() == idReserva)
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarReserva(int idReserva) {
        Reserva reserva = buscarReserva(idReserva);
        if (reserva != null) {
            reservas.remove(reserva);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Usuario{id=%d, nombre='%s', apellido='%s', correo='%s'}",
                usuarioId, nombre, apellido, correo);
    }
}
