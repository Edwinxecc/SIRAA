/**
 * Representa un equipo disponible para reservas en el sistema SIRAA.
 * Esta clase maneja la información y el estado de los equipos que pueden ser asignados a las reservas.
 */
package ec.edu.uce.dominio;

import java.util.ArrayList;
import java.util.List;

public class Equipo {

    private int id;
    private String nombre;
    private String tipo;
    private boolean disponible;

    /** Lista estática que simula una base de datos en memoria */
    private static List<Equipo> listaEquipos = new ArrayList<>();

    /**
     * Constructor con parámetros.
     * @param id ID del equipo
     * @param nombre Nombre del equipo
     * @param tipo Tipo de equipo
     * @param disponible Estado de disponibilidad inicial
     */
    public Equipo(int id, String nombre, String tipo, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponible = disponible;
    }

    /**
     * Obtiene el ID del equipo.
     * @return ID del equipo
     */
    public int getId() { return id; }

    /**
     * Establece el ID del equipo.
     * @param id ID a establecer
     */
    public void setId(int id) { this.id = id; }

    /**
     * Obtiene el nombre del equipo.
     * @return Nombre del equipo
     */
    public String getNombre() { return nombre; }

    /**
     * Establece el nombre del equipo.
     * @param nombre Nombre a establecer
     */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /**
     * Obtiene el tipo del equipo.
     * @return Tipo del equipo
     */
    public String getTipo() { return tipo; }

    /**
     * Establece el tipo del equipo.
     * @param tipo Tipo a establecer
     */
    public void setTipo(String tipo) { this.tipo = tipo; }

    /**
     * Verifica si el equipo está disponible.
     * @return true si el equipo está disponible, false en caso contrario
     */
    public boolean isDisponible() {
        return disponible;
    }

    /**
     * Establece la disponibilidad del equipo.
     * @param disponible Estado de disponibilidad a establecer
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Crea un nuevo equipo y lo agrega a la lista.
     * @param equipo Equipo a crear
     */
    public static void crearEquipo(Equipo equipo) {
        listaEquipos.add(equipo);
        System.out.println("[✓] Equipo creado: " + equipo.getNombre());
    }

    /**
     * Busca un equipo por su ID.
     * @param id ID del equipo a buscar
     * @return Equipo encontrado o null si no existe
     */
    public static Equipo consultarEquipo(int id) {
        for (Equipo e : listaEquipos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    /**
     * Obtiene una copia de la lista de todos los equipos.
     * @return Lista de equipos
     */
    public static List<Equipo> listarEquipos() {
        return new ArrayList<>(listaEquipos);
    }

    /**
     * Actualiza la información de un equipo existente.
     * @param id ID del equipo a actualizar
     * @param nuevoNombre Nuevo nombre
     * @param nuevoTipo Nuevo tipo
     * @param nuevaDisponibilidad Nueva disponibilidad
     * @return true si se actualizó el equipo, false si no se encontró
     */
    public static boolean actualizarEquipo(int id, String nuevoNombre, String nuevoTipo, boolean nuevaDisponibilidad) {
        Equipo equipo = consultarEquipo(id);
        if (equipo != null) {
            equipo.setNombre(nuevoNombre);
            equipo.setTipo(nuevoTipo);
            equipo.setDisponible(nuevaDisponibilidad);
            System.out.println("[✓] Equipo actualizado.");
            return true;
        }
        return false;
    }

    /**
     * Elimina un equipo de la lista.
     * @param id ID del equipo a eliminar
     * @return true si se eliminó el equipo, false si no se encontró
     */
    public static boolean eliminarEquipo(int id) {
        Equipo equipo = consultarEquipo(id);
        if (equipo != null) {
            listaEquipos.remove(equipo);
            System.out.println("[✓] Equipo eliminado: " + equipo.getNombre());
            return true;
        }
        return false;
    }

    /**
     * Muestra la información resumida del equipo.
     */
    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Tipo: " + tipo + " | Disponible: " + disponible);
    }
}
