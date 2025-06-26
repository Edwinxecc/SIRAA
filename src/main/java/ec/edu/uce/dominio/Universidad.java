// Universidad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Representa una universidad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades.
 * Implementa el patrón Singleton para asegurar una única instancia de Universidad.
 */
public class Universidad implements IAdministrarCRUD, Comparable<Universidad> {
    // 1. Atributo estático privado que almacena la única instancia
    private static Universidad instancia;

    private int numFacultades = 0;
    private Facultad[] facultades;

    // 2. Constructor privado para evitar que se cree con new desde fuera
    private Universidad() {
        this.numFacultades = 0;
        this.facultades = new Facultad[10]; // capacidad inicial de 10
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
        return numFacultades;
    }

    public void setNumFacultades(int numFacultades) {
        if (numFacultades >= 0){
            this.numFacultades = numFacultades;
        }
    }

    /**
     * Valida si una facultad ya existe por su nombre.
     * @param facultad La facultad a validar.
     * @return true si la facultad ya existe, false en caso contrario.
     */
    public boolean validarDuplicado(Facultad facultad) {
        if (facultad == null) return false;
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i] != null && facultades[i].equals(facultad)) {
                return true;
            }
        }
        return false;
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
        if (numFacultades == facultades.length) {
            Facultad[] aux = facultades;
            facultades = new Facultad[numFacultades + 1];
            System.arraycopy(aux, 0, facultades, 0, numFacultades);
        }
        facultades[numFacultades] = facultad;
        numFacultades++;
    }

    public Facultad[] getFacultades() {
        // Devuelve una copia con solo los elementos reales para evitar NullPointerExceptions
        Facultad[] facultadesActivas = new Facultad[numFacultades];
        System.arraycopy(facultades, 0, facultadesActivas, 0, numFacultades);
        return facultadesActivas;
    }

    /**
     * Obtiene una facultad por su índice.
     * @param indice Índice de la facultad.
     * @return La facultad en el índice especificado o null si el índice es inválido.
     */
    public Facultad getFacultad(int indice) {
        if (indice >= 0 && indice < numFacultades) {
            return facultades[indice];
        }
        return null;
    }

    /**
     * Actualiza el nombre de una facultad.
     * @param indice Índice de la facultad a actualizar.
     * @param nuevoNombre Nuevo nombre de la facultad.
     */
    public void actualizarFacultad(int indice, String nuevoNombre) {
        if (indice >= 0 && indice < numFacultades) {
            facultades[indice].setNombre(nuevoNombre);
        }
    }

    /**
     * Actualiza una facultad con un nuevo objeto Facultad.
     * @param indice Índice de la facultad a actualizar.
     * @param facultad Nuevo objeto Facultad.
     */
    public void actualizarFacultad(int indice, Facultad facultad) {
        if (indice >= 0 && indice < numFacultades && facultad != null) {
            facultades[indice] = facultad;
        }
    }

    /**
     * Elimina una facultad por su índice.
     * @param indice Índice de la facultad a eliminar.
     */
    public void eliminarFacultad(int indice) {
        if (indice < 0 || indice >= numFacultades) {
            return;
        }
        for (int i = indice; i < numFacultades - 1; i++) {
            facultades[i] = facultades[i + 1];
        }
        facultades[numFacultades - 1] = null;
        numFacultades--;
    }

    /**
     * Elimina una facultad por su objeto.
     * @param facultad Objeto Facultad a eliminar.
     */
    public void eliminarFacultad(Facultad facultad) {
        if (facultad == null) return;
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i] != null && facultades[i].equals(facultad)) {
                eliminarFacultad(i);
                break;
            }
        }
    }

    /**
     * Lista los nombres de las facultades.
     * @return Una cadena con la lista de facultades.
     */
    public String listarNombresFacultades() {
        if (numFacultades == 0) {
            return "[!] No hay facultades registradas.";
        }
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i] != null) {
                texto.append(facultades[i]).append("\r\n");
            }
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
                           numFacultades);
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
        for (int i = 0; i < getNumFacultades(); i++) {
            Facultad f = getFacultad(i);
            if (f != null && f.equals(facultadEditada)) {
                actualizarFacultad(i, facultadEditada);
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
        for (int i = 0; i < getNumFacultades(); i++) {
            Facultad f = getFacultad(i);
            if (f != null && f.equals(facultadABorrar)) {
                eliminarFacultad(i);
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
        return getFacultad(id);
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
        // Actualizar el arreglo interno
        System.arraycopy(facultadesActivas, 0, facultades, 0, numFacultades);
    }

    /**
     * Ordena las facultades de la universidad por número de auditorios (descendente)
     */
    public void ordenarFacultadesPorNumAuditorios() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_NUM_AUDITORIOS);
        // Actualizar el arreglo interno
        System.arraycopy(facultadesActivas, 0, facultades, 0, numFacultades);
    }

    /**
     * Ordena las facultades de la universidad por número de usuarios (descendente)
     */
    public void ordenarFacultadesPorNumUsuarios() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_NUM_USUARIOS);
        // Actualizar el arreglo interno
        System.arraycopy(facultadesActivas, 0, facultades, 0, numFacultades);
    }

    /**
     * Ordena las facultades de la universidad por ID (ascendente)
     */
    public void ordenarFacultadesPorId() {
        Facultad[] facultadesActivas = getFacultades();
        Arrays.sort(facultadesActivas, Facultad.COMPARADOR_POR_ID);
        // Actualizar el arreglo interno
        System.arraycopy(facultadesActivas, 0, facultades, 0, numFacultades);
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
