package ec.edu.uce.dominio;

import java.util.ArrayList;
import java.util.List;

public class GestionarEquipos {

    private int id;
    private String nombre;
    private String tipo;
    private boolean disponible;

    // Lista estática simulando base de datos en memoria
    private static List<GestionarEquipos> listaEquipos = new ArrayList<>();

    // Constructor
    public GestionarEquipos(int id, String nombre, String tipo, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.disponible = disponible;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // ====================== CRUD ======================

    // CREATE
    public static void crearEquipo(GestionarEquipos equipo) {
        listaEquipos.add(equipo);
        System.out.println("[✓] Equipo creado: " + equipo.getNombre());
    }

    // READ (individual)
    public static GestionarEquipos consultarEquipo(int id) {
        for (GestionarEquipos e : listaEquipos) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }

    // READ (todos)
    public static List<GestionarEquipos> listarEquipos() {
        return new ArrayList<>(listaEquipos); // para evitar modificar la lista original
    }

    // UPDATE
    public static boolean actualizarEquipo(int id, String nuevoNombre, String nuevoTipo, boolean nuevaDisponibilidad) {
        GestionarEquipos equipo = consultarEquipo(id);
        if (equipo != null) {
            equipo.setNombre(nuevoNombre);
            equipo.setTipo(nuevoTipo);
            equipo.setDisponible(nuevaDisponibilidad);
            System.out.println("[✓] Equipo actualizado.");
            return true;
        }
        return false;
    }

    // DELETE
    public static boolean eliminarEquipo(int id) {
        GestionarEquipos equipo = consultarEquipo(id);
        if (equipo != null) {
            listaEquipos.remove(equipo);
            System.out.println("[✓] Equipo eliminado: " + equipo.getNombre());
            return true;
        }
        return false;
    }

    // Mostrar info resumida
    public void mostrarInfo() {
        System.out.println("ID: " + id + " | Nombre: " + nombre + " | Tipo: " + tipo + " | Disponible: " + disponible);
    }
}
