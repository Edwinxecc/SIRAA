/**
 * Representa un equipo disponible para reservas en el sistema SIRAA.
 * Esta clase maneja la información y el estado de los equipos que pueden ser asignados a las reservas.
 */
package ec.edu.uce.dominio;



public class Equipo {
    private String nombre;
    private String categoria;
    private boolean disponibilidad;

    public Equipo(String nombre, String categoria, boolean disponibilidad){
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
    }

    public Equipo(){
        this("Sin nombre", "Sin categoria", false);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 0){
            this.nombre = nombre;
        }
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        if (this.categoria != categoria){
            this.categoria = categoria;
        }
    }

    public boolean getDisponibilidad(){
        return this.disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }



}
