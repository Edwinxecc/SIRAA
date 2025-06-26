// Universidad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * Representa una universidad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades.
 * Implementa el patrón Singleton para asegurar una única instancia de Universidad.
 */
public class Universidad implements IAdministrarCRUD, Comparable<Universidad> {
    // 1. Atributo estático privado que almacena la única instancia
    private static Universidad instancia;

    private Map<String, Facultad> facultades;

    // 2. Constructor privado para evitar que se cree con new desde fuera
    private Universidad() {
        this.facultades = new HashMap<>();
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
        return facultades.size();
    }

    public void setNumFacultades(int numFacultades) {
        if (numFacultades >= 0){
            // This method is no longer used as the number of facultades is managed by the map
        }
    }

    /**
     * Valida si una facultad ya existe por su nombre.
     * @param facultad La facultad a validar.
     * @return true si la facultad ya existe, false en caso contrario.
     */
    public boolean validarDuplicado(Facultad facultad) {
        if (facultad == null) return false;
        return facultades.containsKey(facultad.getCodigoFacultad());
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
        facultades.put(facultad.getCodigoFacultad(), facultad);
    }

    public Facultad[] getFacultades() {
        return facultades.values().toArray(new Facultad[0]);
    }

    /**
     * Obtiene una facultad por su índice.
     * @param codigo Código de la facultad.
     * @return La facultad en el índice especificado o null si el índice es inválido.
     */
    public Facultad getFacultad(String codigo) {
        return facultades.get(codigo);
    }

    /**
     * Actualiza el nombre de una facultad.
     * @param codigo Código de la facultad a actualizar.
     * @param nuevoNombre Nuevo nombre de la facultad.
     */
    public void actualizarFacultad(String codigo, Facultad facultad) {
        if (facultades.containsKey(codigo) && facultad != null) {
            facultades.put(codigo, facultad);
        }
    }

    /**
     * Actualiza una facultad con un nuevo objeto Facultad.
     * @param facultad Nuevo objeto Facultad.
     */
    public void actualizarFacultad(Facultad facultad) {
        if (facultad != null) {
            facultades.put(facultad.getCodigoFacultad(), facultad);
        }
    }

    /**
     * Elimina una facultad por su índice.
     * @param codigo Código de la facultad a eliminar.
     */
    public void eliminarFacultad(String codigo) {
        facultades.remove(codigo);
    }

    /**
     * Elimina una facultad por su objeto.
     * @param facultad Objeto Facultad a eliminar.
     */
    public void eliminarFacultad(Facultad facultad) {
        if (facultad == null) return;
        facultades.remove(facultad.getCodigoFacultad());
    }

    /**
     * Lista los nombres de las facultades.
     * @return Una cadena con la lista de facultades.
     */
    public String listarNombresFacultades() {
        if (facultades.isEmpty()) {
            return "[!] No hay facultades registradas.";
        }
        StringBuilder texto = new StringBuilder();
        for (Facultad f : facultades.values()) {
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
        for (Facultad f : facultades.values()) {
            if (f.equals(facultadEditada)) {
                actualizarFacultad(f.getCodigoFacultad(), facultadEditada);
                return "[✓] Facultad editada correctamente.";
            }
        }
        return "[!] Facultad no encontrada.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadABorrar = (Facultad) obj;
        for (Facultad f : facultades.values()) {
            if (f.equals(facultadABorrar)) {
                eliminarFacultad(f.getCodigoFacultad());
                return "[✓] Facultad eliminada correctamente.";
            }
        }
        return "[!] Facultad no encontrada.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0 || id >= getNumFacultades()) {
            return null;
        }
        return getFacultad(id.toString());
    }

    @Override
    public String listar() {
        return listarNombresFacultades();
    }

    @Override
    public int compareTo(Universidad otraUniversidad) {
        return this.toString().compareTo(otraUniversidad.toString());
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
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_NUM_AUDITORIOS);
    }

    /**
     * Ordena las facultades de la universidad por número de usuarios (descendente)
     */
    public void ordenarFacultadesPorNumUsuarios() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_NUM_USUARIOS);
    }

    /**
     * Ordena las facultades de la universidad por ID (ascendente)
     */
    public void ordenarFacultadesPorId() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_ID);
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar universidades por número de facultades (descendente)
     */
    public static final Comparator<Universidad> COMPARADOR_POR_NUM_FACULTADES = new Comparator<Universidad>() {
        @Override
        public int compare(Universidad u1, Universidad u2) {
            return Integer.compare(u2.getNumFacultades(), u1.getNumFacultades()); // Descendente
        }
    };
}
