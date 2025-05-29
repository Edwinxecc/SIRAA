package ec.edu.uce.dominio;

/**
 * Representa un auditorio en el sistema SIRAA.
 * Esta clase maneja la información y las reservas de un auditorio.
 */
public class Auditorio {
    private String nombre;
    private int capacidad;

    public Auditorio(String nombre, int capacidad){
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public Auditorio(){
        this("Sin nombre", 0);
    }

    // Getters y setters
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

    // toString
    @Override
    public String toString() {
        return "Auditorio: " + nombre + ", Capacidad: " + capacidad;
    }
}