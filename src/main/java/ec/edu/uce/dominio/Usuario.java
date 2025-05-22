/**
 * Representa un usuario del sistema SIRAA.
 * Esta clase maneja la información personal y las reservas de un usuario.
 */
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
    /** Patrón para validar correos institucionales UCE */
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@uce\\.edu\\.ec$");

    /**
     * Constructor por defecto.
     * Inicializa la lista de reservas vacía.
     */
    public Usuario() {
        this.reservas = new ArrayList<>();
    }

    /**
     * Constructor con parámetros.
     * @param id ID del usuario
     * @param nombre Nombre del usuario
     * @param apellido Apellido del usuario
     * @param correo Correo institucional del usuario
     */
    public Usuario(int id, String nombre, String apellido, String correo) {
        this();
        setUsuarioId(id);
        setNombre(nombre);
        setApellido(apellido);
        setCorreo(correo);
    }

    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario
     */
    public int getUsuarioId() {
        return usuarioId;
    }

    /**
     * Establece el ID del usuario.
     * @param usuarioId ID a establecer
     * @throws IllegalArgumentException si el ID es menor o igual a 0
     */
    public void setUsuarioId(int usuarioId) {
        if (usuarioId <= 0) {
            throw new IllegalArgumentException("El ID del usuario debe ser un número positivo");
        }
        this.usuarioId = usuarioId;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre a establecer
     * @throws IllegalArgumentException si el nombre es nulo, vacío o contiene números
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (nombre.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El nombre no debe contener números");
        }
        this.nombre = nombre.trim();
    }

    /**
     * Obtiene el apellido del usuario.
     * @return Apellido del usuario
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Establece el apellido del usuario.
     * @param apellido Apellido a establecer
     * @throws IllegalArgumentException si el apellido es nulo, vacío o contiene números
     */
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede estar vacío");
        }
        if (apellido.matches(".*\\d.*")) {
            throw new IllegalArgumentException("El apellido no debe contener números");
        }
        this.apellido = apellido.trim();
    }

    /**
     * Obtiene el correo del usuario.
     * @return Correo institucional del usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Establece el correo del usuario.
     * @param correo Correo institucional a establecer
     * @throws IllegalArgumentException si el correo es inválido o no es institucional
     */
    public void setCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalArgumentException("El correo no puede estar vacío");
        }
        if (!EMAIL_PATTERN.matcher(correo.trim()).matches()) {
            throw new IllegalArgumentException("El correo debe ser un correo institucional válido (@uce.edu.ec)");
        }
        this.correo = correo.trim().toLowerCase();
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password Contraseña a establecer
     * @throws IllegalArgumentException si la contraseña es nula o tiene menos de 8 caracteres
     */
    public void setPassword(String password) {
        if (password == null || password.trim().length() < 8) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
        this.password = password;
    }

    /**
     * Obtiene la lista de reservas del usuario.
     * @return Copia de la lista de reservas
     */
    public List<Reserva> getReservas() {
        return new ArrayList<>(reservas);
    }

    /**
     * Genera un ID aleatorio para el usuario.
     * @return ID generado entre 100 y 999
     */
    public int generarId() {
        Random random = new Random();
        int newId;
        do {
            newId = random.nextInt(900) + 100; // Genera números entre 100 y 999
        } while (newId <= 0);
        return newId;
    }

    /**
     * Crea una nueva reserva para el usuario.
     * @param idReserva ID de la reserva
     * @param auditorio Auditorio a reservar
     * @param inicio Fecha y hora de inicio
     * @param fin Fecha y hora de fin
     * @throws IllegalArgumentException si las fechas son inválidas
     */
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

    /**
     * Busca una reserva por su ID.
     * @param idReserva ID de la reserva a buscar
     * @return Reserva encontrada o null si no existe
     */
    public Reserva buscarReserva(int idReserva) {
        return reservas.stream()
                .filter(r -> r.getId() == idReserva)
                .findFirst()
                .orElse(null);
    }

    /**
     * Elimina una reserva del usuario.
     * @param idReserva ID de la reserva a eliminar
     * @return true si se eliminó la reserva, false si no se encontró
     */
    public boolean eliminarReserva(int idReserva) {
        Reserva reserva = buscarReserva(idReserva);
        if (reserva != null) {
            reservas.remove(reserva);
            return true;
        }
        return false;
    }

    /**
     * Devuelve una representación en texto del usuario.
     * @return String con los datos del usuario
     */
    @Override
    public String toString() {
        return String.format("Usuario{id=%d, nombre='%s', apellido='%s', correo='%s'}",
                usuarioId, nombre, apellido, correo);
    }
}
