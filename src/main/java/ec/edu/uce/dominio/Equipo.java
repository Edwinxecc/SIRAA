package ec.edu.uce.dominio;

/**
 * Representa un equipo disponible para reservas en el sistema SIRAA.
 * Esta clase maneja la información y el estado de los equipos que pueden ser asignados a las reservas.
 */
public class Equipo {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "EQ";
    private static int contadorEquipos = 0;
    
    private String codigoEquipo;
    private String nombre;
    private String categoria;
    private boolean disponibilidad;
    private Estado estado;

    // Constructor completo
    public Equipo(String nombre, String categoria, boolean disponibilidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
        this.estado = disponibilidad ? Estado.CONFIRMADA : Estado.CANCELADA;
        this.codigoEquipo = generarCodigoEquipo();
    }

    // Constructor por defecto
    public Equipo() {
        this("Sin nombre", "Sin categoria", false);
    }

    // Método para generar códigos automáticos
    private String generarCodigoEquipo() {
        contadorEquipos++;
        return PREFIJO_CODIGO + String.format("%04d", contadorEquipos);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipo)) return false;
        
        Equipo otroEquipo = (Equipo) obj;
        return this.codigoEquipo.equals(otroEquipo.codigoEquipo) &&
               this.nombre.equals(otroEquipo.nombre) &&
               this.categoria.equals(otroEquipo.categoria);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Equipo)) return false;
        
        Equipo otroEquipo = (Equipo) obj;
        return this.codigoEquipo.equals(otroEquipo.codigoEquipo) ||
               (this.nombre.equals(otroEquipo.nombre) && 
                this.categoria.equals(otroEquipo.categoria));
    }

    // Getters y Setters
    public String getCodigoEquipo() {
        return codigoEquipo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

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
        System.out.println("Equipo [" + codigoEquipo + "]: " + nombre + 
                         " | Categoría: " + categoria + 
                         " | Disponible: " + (disponibilidad ? "Sí" : "No") +
                         " | Estado: " + estado);
    }
}