package ec.edu.uce.dominio;

/**
 * Representa un equipo disponible para reservas en el sistema SIRAA.
 * Esta clase maneja la información y el estado de los equipos que pueden ser asignados a las reservas.
 */
public class Equipo {
    private String nombre;
    private String categoria;
    private boolean disponibilidad;

    // Constructor completo
    public Equipo(String nombre, String categoria, boolean disponibilidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
    }

    // Constructor por defecto
    public Equipo() {
        this("Sin nombre", "Sin categoria", false);
    }

    // Getters y Setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && !nombre.isEmpty()) {
            this.nombre = nombre;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (categoria != null && !categoria.equals(this.categoria)) {
            this.categoria = categoria;
        }
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    // Método auxiliar opcional para imprimir detalles
    public void mostrarInfo() {
        System.out.println("Equipo: " + nombre + " | Categoría: " + categoria + " | Disponible: " + (disponibilidad ? "Sí" : "No"));
    }
}