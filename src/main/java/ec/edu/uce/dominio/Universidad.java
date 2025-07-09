// Universidad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;
import ec.edu.uce.datos.FacultadDAO;
import ec.edu.uce.datos.FacultadDAOMemoriaImp;

/**
 * Representa una universidad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades.
 * Implementa el patrón Singleton para asegurar una única instancia de Universidad.
 */
public class Universidad implements IAdministrarCRUD, Comparable<Universidad> {
    // 1. Atributo estático privado que almacena la única instancia
    private static Universidad instancia;

    private FacultadDAO facultadDAO;

    // 2. Constructor privado para evitar que se cree con new desde fuera
    private Universidad() {
        this.facultadDAO = new FacultadDAOMemoriaImp();
        inicializar();
    }

    // 3. Método público estático que devuelve la instancia única
    public static Universidad getInstancia() {
        if (instancia == null) {
            instancia = new Universidad(); // se crea solo una vez
        }
        return instancia;
    }

    // Getters y setters

    public int getNumFacultades() {
        return facultadDAO.consultarFacultades().length;
    }

    public void setNumFacultades(int numFacultades) {
        // No se usa, el número de facultades lo gestiona el DAO
    }

    /**
     * Valida si una facultad ya existe por su nombre.
     * @param facultad La facultad a validar.
     * @return true si la facultad ya existe, false en caso contrario.
     */
    public boolean validarDuplicado(Facultad facultad) {
        if (facultad == null) return false;
        return facultadDAO.buscarPorCodigo(facultad.getCodigoFacultad()) != null;
    }

    // Métodos CRUD de Facultad

    /**
     * Crea una nueva facultad con valores por defecto.
     */
    public void crearFacultad() {
        crearFacultad(new Facultad());
    }

    /**
     * Crea una nueva facultad con un nombre específico.
     * @param nombreFacultad Nombre de la facultad.
     */
    public void crearFacultad(String nombreFacultad) {
        crearFacultad(new Facultad(nombreFacultad));
    }

    /**
     * Crea una nueva facultad.
     * @param facultad Objeto Facultad a crear.
     */
    public void crearFacultad(Facultad facultad) {
        if (facultad == null) return;
        if (validarDuplicado(facultad)) {
            System.out.println("La facultad ya existe y no se puede agregar duplicada.");
            return;
        }
        facultadDAO.crear(facultad);
    }

    public Facultad[] getFacultades() {
        return facultadDAO.consultarFacultades();
    }

    /**
     * Obtiene una facultad por su código.
     * @param codigo Código de la facultad.
     * @return La facultad con el código especificado o null si no existe.
     */
    public Facultad getFacultad(String codigo) {
        return facultadDAO.buscarPorCodigo(codigo);
    }

    /**
     * Actualiza una facultad con un nuevo objeto Facultad.
     * @param facultad Nuevo objeto Facultad.
     */
    public void actualizarFacultad(Facultad facultad) {
        if (facultad != null) {
            facultadDAO.editar(facultad);
        }
    }

    /**
     * Elimina una facultad por su código.
     * @param codigo Código de la facultad a eliminar.
     */
    public void eliminarFacultad(String codigo) {
        facultadDAO.eliminar(codigo);
    }

    /**
     * Elimina una facultad por su objeto.
     * @param facultad Objeto Facultad a eliminar.
     */
    public void eliminarFacultad(Facultad facultad) {
        if (facultad == null) return;
        facultadDAO.eliminar(facultad.getCodigoFacultad());
    }

    /**
     * Lista los nombres de las facultades.
     * @return Una cadena con la lista de facultades.
     */
    public String listarNombresFacultades() {
        Facultad[] facultades = facultadDAO.consultarFacultades();
        if (facultades.length == 0) {
            return "[!] No hay facultades registradas.";
        }
        StringBuilder texto = new StringBuilder();
        for (Facultad f : facultades) {
            texto.append(f).append("\r\n");
        }
        return texto.toString();
    }

    // ========================
    // Método inicializar
    // ========================

    public void inicializar() {
        // Crear 5 facultades de ejemplo
        crearFacultad(new Facultad("Facultad de Ingeniería", 5));
        crearFacultad(new Facultad("Facultad de Ciencias Sociales", 5));
        crearFacultad(new Facultad("Facultad de Medicina", 5));
        crearFacultad(new Facultad("Facultad de Derecho", 5));
        crearFacultad(new Facultad("Facultad de Arquitectura", 5));
    }

    @Override
    public String toString() {
        return String.format("┌─ UNIVERSIDAD ──────────────────────────────────────────────────────┐%n" +
                        "│ Nombre: %-50s │%n" +
                        "│ Facultades Registradas: %-3d │%n" +
                        "└─────────────────────────────────────────────────────────────────────┘",
                "Universidad Central del Ecuador (UCE)",
                getNumFacultades());
    }

    // Métodos de la interfaz IAdministrarCRUD

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadNueva = (Facultad) obj;
        if (validarDuplicado(facultadNueva)) {
            return "[!] La facultad ya existe.";
        }
        crearFacultad(facultadNueva);
        return "[✓] Facultad creada correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadEditada = (Facultad) obj;
        actualizarFacultad(facultadEditada);
        return "[✓] Facultad editada correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadABorrar = (Facultad) obj;
        eliminarFacultad(facultadABorrar);
        return "[✓] Facultad eliminada correctamente.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        Facultad[] facultades = facultadDAO.consultarFacultades();
        if (id == null || id < 0 || id >= facultades.length) {
            return null;
        }
        return facultades[id];
    }

    @Override
    public String listar() {
        return listarNombresFacultades();
    }

    /**
     * Criterio natural de comparación: por nombre de universidad (ascendente)
     */
    @Override
    public int compareTo(Universidad o) {
        // Si existiera un idUniversidad, comparar primero por id
        // Como no hay, comparar solo por nombre
        if ("Universidad Central del Ecuador (UCE)".compareTo("Universidad Central del Ecuador (UCE)") < 0) {
            return -1;
        } else if ("Universidad Central del Ecuador (UCE)".compareTo("Universidad Central del Ecuador (UCE)") > 0) {
            return 1;
        }
        return 0;
    }

    // ========================
    // Métodos de Ordenación para Facultades
    // ========================

    /**
     * Ordena las facultades de la universidad por nombre
     */
    public void ordenarFacultadesPorNombre() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas);
    }

    /**
     * Ordena las facultades de la universidad por número de auditorios (descendente)
     */
    public void ordenarFacultadesPorNumAuditorios() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, new OrdenarFacultadNumAuditorios());
    }

    /**
     * Ordena las facultades de la universidad por número de usuarios (descendente)
     */
    public void ordenarFacultadesPorNumUsuarios() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, new OrdenarFacultadNumUsuarios());
    }

    /**
     * Ordena las facultades de la universidad por ID (ascendente)
     */
    public void ordenarFacultadesPorId() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, new OrdenarFacultadId());
    }
}