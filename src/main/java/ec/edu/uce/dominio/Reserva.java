package ec.edu.uce.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Reserva {

    private int idReserva;
    private Date fechaReserva;

    // Lista de reservas (simulando almacenamiento en memoria)
    private static List<Reserva> listaReservas = new ArrayList<>();

    // Constructor con ID
    public Reserva(int id) {
        this.idReserva = id;
        this.fechaReserva = new Date(); // fecha actual
    }

    // Constructor vacío
    public Reserva() {
        this.fechaReserva = new Date(); // fecha actual por defecto
    }

    // Getter y Setter para idReserva
    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    // Getter y Setter para fechaReserva
    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    /**
     * Genera un ID personalizado usando parte del nombre y apellido del usuario
     * junto con un número aleatorio.
     *
     * @param usuario Usuario del que se obtienen datos
     * @return ID generado
     */
    public String generarId(Usuario usuario) {
        String nombre = usuario.getNombre();
        String apellido = usuario.getApellido();

        // Validar que tengan al menos 4 caracteres
        String parteNombre = nombre.length() >= 4 ? nombre.substring(0, 4) : nombre;
        String parteApellido = apellido.length() >= 4 ? apellido.substring(0, 4) : apellido;

        Random random = new Random();
        int numRandom = random.nextInt(1000); // de 0 a 999

        return parteNombre + parteApellido + String.format("%03d", numRandom);
    }

    /**
     * Método alternativo para crear una reserva asignando un ID
     */
    public void crearReserva(int id) {
        this.idReserva = id;
        this.fechaReserva = new Date();
    }

    // ==================== MÉTODOS CRUD ====================

    // CREATE
    public static void agregarReserva(Reserva reserva) {
        listaReservas.add(reserva);
    }

    // READ
    public static List<Reserva> obtenerTodasLasReservas() {
        return new ArrayList<>(listaReservas); // devolver copia para evitar modificación externa
    }

    public static Reserva buscarPorId(int id) {
        for (Reserva r : listaReservas) {
            if (r.getIdReserva() == id) {
                return r;
            }
        }
        return null;
    }

    // UPDATE
    public static boolean actualizarReserva(int id, int nuevoId) {
        Reserva r = buscarPorId(id);
        if (r != null) {
            r.setIdReserva(nuevoId);
            r.setFechaReserva(new Date());
            return true;
        }
        return false;
    }

    // DELETE
    public static boolean eliminarReserva(int id) {
        Reserva r = buscarPorId(id);
        if (r != null) {
            return listaReservas.remove(r);
        }
        return false;
    }

}
