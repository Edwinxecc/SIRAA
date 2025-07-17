package ec.edu.uce.dominio;

/**
 * Representa un equipo disponible para reservas en el sistema SIRAA.
 * Esta clase maneja la información y el estado de los equipos que pueden ser asignados a las reservas.
 */
public class Equipo implements IAdministrarCRUD, Comparable<Equipo> {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "EQ";
    private static int contadorEquipos = 0;

    private int idEquipo;
    private String codigoEquipo;
    private String nombre;
    private String categoria;
    private boolean disponibilidad;
    private Estado estado;

    // Constructores
    public Equipo(String nombre, String categoria, boolean disponibilidad) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.disponibilidad = disponibilidad;
        this.estado = disponibilidad ? Estado.CONFIRMADA : Estado.CANCELADA;
        this.idEquipo = generarIdEquipo();
        this.codigoEquipo = generarCodigoEquipo();
    }

    public Equipo() {
        this("Sin nombre", "Sin categoria", false);
    }

    // Constructor con objeto Equipo
    public Equipo(Equipo equipo) {
        this(equipo.getNombre(), equipo.getCategoria(), equipo.getDisponibilidad());
        this.estado = equipo.getEstado();
    }

    // Método para generar IDs automáticos
    private int generarIdEquipo() {
        contadorEquipos++;
        return contadorEquipos;
    }

    // Método para generar códigos automáticos
    private String generarCodigoEquipo() {
        return PREFIJO_CODIGO + String.format("%04d", contadorEquipos);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Equipo)) return false;

        Equipo otroEquipo = (Equipo) obj;
        return this.idEquipo == otroEquipo.idEquipo &&
                this.codigoEquipo.equals(otroEquipo.codigoEquipo) &&
                this.nombre.equals(otroEquipo.nombre) &&
                this.categoria.equals(otroEquipo.categoria);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Equipo)) return false;

        Equipo otroEquipo = (Equipo) obj;
        return this.idEquipo == otroEquipo.idEquipo ||
                this.codigoEquipo.equals(otroEquipo.codigoEquipo) ||
                (this.nombre.equals(otroEquipo.nombre) &&
                        this.categoria.equals(otroEquipo.categoria));
    }

    // Getters y Setters
    public int getIdEquipo() {
        return idEquipo;
    }

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
        this.estado = disponibilidad ? Estado.CONFIRMADA : Estado.CANCELADA;
    }

    // Método para actualizar todos los atributos
    public void actualizar(String nombre, String categoria, boolean disponibilidad) {
        setNombre(nombre);
        setCategoria(categoria);
        setDisponibilidad(disponibilidad);
    }

    // Método para actualizar con objeto
    public void actualizar(Equipo equipo) {
        if (equipo != null) {
            actualizar(equipo.getNombre(), equipo.getCategoria(), equipo.getDisponibilidad());
            setEstado(equipo.getEstado());
        }
    }

    // Método auxiliar para imprimir detalles
    public void mostrarInfo() {
        System.out.println("Equipo [" + codigoEquipo + "] ID: " + idEquipo + " | " + nombre +
                " | Categoría: " + categoria +
                " | Disponible: " + (disponibilidad ? "Sí" : "No") +
                " | Estado: " + estado);
    }

    @Override
    public String toString() {
        return String.format("┌─ EQUIPO ────────────────────────────────────────────────────────────┐%n" +
                        "│ Código: %-15s │ ID: %-8d │ Categoría: %-20s │%n" +
                        "│ Nombre: %-50s │%n" +
                        "│ Disponible: %-8s │ Estado: %-20s │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                codigoEquipo, idEquipo, categoria,
                nombre,
                disponibilidad ? "Sí" : "No", estado.getDescripcion());
    }

    // ========================
    // Métodos de la interfaz IAdministrarCRUD
    // ========================

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Equipo)) {
            return "[!] El objeto no es un Equipo válido.";
        }
        Equipo equipoNuevo = (Equipo) obj;
        if (validarDuplicado(equipoNuevo)) {
            return "[!] El equipo ya existe.";
        }
        // Aquí se simula la creación del equipo
        return "[✓] Equipo creado correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Equipo)) {
            return "[!] El objeto no es un Equipo válido.";
        }
        Equipo equipoEditado = (Equipo) obj;
        // Aquí se simula la edición del equipo
        this.nombre = equipoEditado.getNombre();
        this.categoria = equipoEditado.getCategoria();
        this.disponibilidad = equipoEditado.getDisponibilidad();
        this.estado = equipoEditado.getEstado();
        return "[✓] Equipo editado correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Equipo)) {
            return "[!] El objeto no es un Equipo válido.";
        }
        Equipo equipoABorrar = (Equipo) obj;
        if (this.equals(equipoABorrar)) {
            // Aquí se simula la eliminación del equipo
            return "[✓] Equipo eliminado correctamente.";
        }
        return "[!] Equipo no encontrado.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == this.idEquipo) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    // ========================
    // Métodos de la interfaz Comparable
    // ========================

    /**
     * Criterio natural de comparación: por ID y luego por nombre
     */
    @Override
    public int compareTo(Equipo o) {
        if (this.idEquipo < o.idEquipo) {
            return -1;
        } else if (this.idEquipo > o.idEquipo) {
            return 1;
        }
        // Si los id son iguales, comparar por nombre
        if (this.nombre.compareTo(o.nombre) < 0) {
            return -1;
        } else if (this.nombre.compareTo(o.nombre) > 0) {
            return 1;
        }
        return 0;
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar equipos por nombre
     */

    /**
     * Comparador para ordenar equipos por categoría
     */

    /**
     * Comparador para ordenar equipos por disponibilidad (disponibles primero)
     */

    /**
     * Comparador para ordenar equipos por ID (ascendente)
     */

    /**
     * Comparador para ordenar equipos por estado
     */
}