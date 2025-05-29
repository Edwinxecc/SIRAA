/**
 * Representa un auditorio en el sistema SIRAA.
 * Esta clase maneja la información y las reservas de un auditorio.
 */
package ec.edu.uce.dominio;


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

}