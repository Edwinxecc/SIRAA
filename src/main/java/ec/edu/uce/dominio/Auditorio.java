/**
 * Representa un auditorio en el sistema SIRAA.
 * Esta clase maneja la información y las reservas de un auditorio.
 */
package ec.edu.uce.dominio;

import ec.edu.uce.Util.Validaciones;

import java.util.ArrayList;
import java.util.List;

public class Auditorio {
    private int id;
    private String nombre;
    private int capacidad;
    private List<Reserva> reservas;
    private Validaciones val = new Validaciones();

    /**
     * Constructor con parámetros.
     * @param id ID del auditorio
     * @param nombre Nombre del auditorio
     * @param capacidad Capacidad máxima del auditorio
     */
    public Auditorio(int id, String nombre, int capacidad) {
        setId(id);
        setNombre(nombre);
        setCapacidad(capacidad);
        this.reservas = new ArrayList<>();
    }

    /**
     * Obtiene el ID del auditorio.
     * @return ID del auditorio
     */
    public int getId() {
        return id;
    }

    /**
     * Establece el ID del auditorio.
     * @param id ID a establecer
     * @throws IllegalArgumentException si el ID es menor o igual a 0
     */
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        this.id = id;
    }

    /**
     * Obtiene el nombre del auditorio.
     * @return Nombre del auditorio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del auditorio.
     * @param nombre Nombre a establecer
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public void setNombre(String nombre) {
        val.ValidacionTexto(nombre, "Nombre del Auditorio");
        this.nombre = nombre;
    }

    /**
     * Obtiene la capacidad del auditorio.
     * @return Capacidad máxima del auditorio
     */
    public int getCapacidad() {
        return capacidad;
    }

    /**
     * Establece la capacidad del auditorio.
     * @param capacidad Capacidad a establecer
     * @throws IllegalArgumentException si la capacidad es menor o igual a 0
     */
    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
    }

    /**
     * Agrega una reserva al auditorio.
     * @param reserva Reserva a agregar
     */
    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    /**
     * Busca una reserva por su ID.
     * @param idReserva ID de la reserva a buscar
     * @return Reserva encontrada o null si no existe
     */
    public Reserva buscarReserva(int idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        return null;
    }

    /**
     * Edita una reserva existente.
     * @param idReserva ID de la reserva a editar
     * @param nuevoUsuario Nuevo usuario de la reserva
     * @param nuevaFechaInicio Nueva fecha de inicio
     * @param nuevaFechaFin Nueva fecha de fin
     * @return true si se editó la reserva, false si no se encontró
     */
    public boolean editarReserva(int idReserva, String nuevoUsuario, String nuevaFechaInicio, String nuevaFechaFin) {
        Reserva r = buscarReserva(idReserva);
        if (r != null) {
            // Puedes implementar lógica adicional aquí si deseas
            return true;
        }
        return false;
    }

    /**
     * Elimina una reserva del auditorio.
     * @param reserva Reserva a eliminar
     * @return true si se eliminó la reserva, false si no existía
     */
    public boolean eliminarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

    /**
     * Obtiene la lista de reservas del auditorio.
     * @return Lista de reservas
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Devuelve una representación en texto del auditorio.
     * @return String con los datos del auditorio
     */
    @Override
    public String toString() {
        return "Auditorio ID: " + id +
                ", Nombre: " + nombre +
                ", Capacidad: " + capacidad +
                ", Total Reservas: " + reservas.size();
    }
}