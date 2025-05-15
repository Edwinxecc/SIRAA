package ec.edu.uce.dominio;


public class Auditorio {
    private int id;
    private String nombre;
    private int capacidad;
    private String ubicacion;

    public Auditorio(int id, String nombre, int capacidad, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.ubicacion = ubicacion;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }


    public String crearAuditorio() {
        return "Auditorio creado: " + nombre;
        //System.out.println("Auditorio creado: " + nombre);
    }

    public String consultarAuditorio() {
        //System.out.println("Auditorio: " + nombre + " - Capacidad: " + capacidad + " - Ubicación: " + ubicacion);
        return "Auditorio: " + nombre + " - Capacidad: " + capacidad + " - Ubicación: " + ubicacion;
    }

    public void actualizarAuditorio(String nuevoNombre, int nuevaCapacidad, String nuevaUbicacion) {
        this.nombre = nuevoNombre;
        this.capacidad = nuevaCapacidad;
        this.ubicacion = nuevaUbicacion;
        System.out.println("Auditorio actualizado.");
    }

    public String eliminarAuditorio() {
        //System.out.println("Auditorio eliminado: " + nombre);
        return "Auditorio eliminado: " + nombre;
    }
}
