package ec.edu.uce.dominio;

public class EquipoRecurso {
    private int id;
    private String nombre;
    private String tipo;
    private boolean disponible;

    public EquipoRecurso(int id, String nombre, String tipo, boolean disponible) {
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

    public boolean getDisponible() {
        return disponible;
    }
    public boolean setDisponible() {
        if (disponible){
            return false;
        }else return true;
    }

    public void crearEquipo() {
        System.out.println("Equipo creado: " + nombre);
    }

    public void consultarEquipo() {
        System.out.println("Equipo: " + nombre + " - Tipo: " + tipo + " - Disponible: " + disponible);
    }

    public void actualizarEquipo(String nuevoNombre, String nuevoTipo, boolean disponibilidad) {
        this.nombre = nuevoNombre;
        this.tipo = nuevoTipo;
        this.disponible = disponibilidad;
        System.out.println("Equipo actualizado.");
    }

    public void eliminarEquipo() {
        System.out.println("Equipo eliminado: " + nombre);
    }
}
