// Auditorio.java
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

    public Auditorio(int id, String nombre, int capacidad) {
        setId(id);
        setNombre(nombre);
        setCapacidad(capacidad);
        this.reservas = new ArrayList<>();
    }

    // Getters y Setters con validaciones
    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID debe ser un número positivo.");
        }
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        val.ValidacionTexto(nombre, "Nombre del Auditorio");
        this.nombre = nombre;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        this.capacidad = capacidad;
    }

    // CRUD de Reservas

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public Reserva buscarReserva(int idReserva) {
        for (Reserva r : reservas) {
            if (r.getId() == idReserva) {
                return r;
            }
        }
        return null;
    }

    public boolean editarReserva(int idReserva, String nuevoUsuario, String nuevaFechaInicio, String nuevaFechaFin) {
        Reserva r = buscarReserva(idReserva);
        if (r != null) {
            // Puedes implementar lógica adicional aquí si deseas
            return true;
        }
        return false;
    }

    public boolean eliminarReserva(Reserva reserva) {
        return reservas.remove(reserva);
    }

    public List<Reserva> getReservas() {
        return reservas;
    }

    @Override
    public String toString() {
        return "Auditorio ID: " + id +
                ", Nombre: " + nombre +
                ", Capacidad: " + capacidad +
                ", Total Reservas: " + reservas.size();
    }
}