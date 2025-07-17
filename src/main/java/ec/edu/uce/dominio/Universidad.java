// Universidad.java
package ec.edu.uce.dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa una universidad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades.
 * Implementa el patrón Singleton para asegurar una única instancia de Universidad.
 */
public class Universidad implements IAdministrarCRUD, Comparable<Universidad> {
    // 1. Atributo estático privado que almacena la única instancia
    private static Universidad instancia;

    private List<Facultad> facultades;
    private Map<Universidad, List<Facultad>> relacionUniversidadFacultad = new HashMap<>();

    // 2. Constructor privado para evitar que se cree con new desde fuera
    private Universidad() {
        this.facultades = new ArrayList<>();
        //inicializar();
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
        // No se usa, el número de facultades lo gestiona la lista
    }

    /**
     * Valida si una facultad ya existe por su nombre.
     * @param facultad La facultad a validar.
     * @return true si la facultad ya existe, false en caso contrario.
     */
    public boolean validarDuplicado(Facultad facultad) {
        if (facultad == null) return false;
        return buscarFacultadPorCodigo(facultad.getCodigoFacultad()) != null;
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
        facultades.add(facultad);
        relacionUniversidadFacultad.put(this, facultades);
    }

    public List<Facultad> getFacultades() {
        return new ArrayList<>(facultades);
    }

    public Map<Universidad, List<Facultad>> getRelacionUniversidadFacultad() {
        return relacionUniversidadFacultad;
    }

    /**
     * Obtiene una facultad por su código.
     * @param codigo Código de la facultad.
     * @return La facultad con el código especificado o null si no existe.
     */
    public Facultad buscarFacultadPorCodigo(String codigo) {
        for (Facultad facultad : facultades) {
            if (facultad != null && facultad.getCodigoFacultad().equals(codigo)) {
                return facultad;
            }
        }
        return null;
    }

    /**
     * Actualiza una facultad con un nuevo objeto Facultad.
     * @param facultad Nuevo objeto Facultad.
     */
    public void actualizarFacultad(Facultad facultad) {
        if (facultad != null) {
            for (int i = 0; i < facultades.size(); i++) {
                if (facultades.get(i) != null && facultades.get(i).getCodigoFacultad().equals(facultad.getCodigoFacultad())) {
                    facultades.set(i, facultad);
                    relacionUniversidadFacultad.put(this, facultades);
                    return;
                }
            }
        }
    }

    /**
     * Elimina una facultad por su código.
     * @param codigo Código de la facultad a eliminar.
     */
    public void eliminarFacultad(String codigo) {
        facultades.removeIf(facultad -> facultad != null && facultad.getCodigoFacultad().equals(codigo));
        relacionUniversidadFacultad.put(this, facultades);
    }

    /**
     * Elimina una facultad por su objeto.
     * @param facultad Objeto Facultad a eliminar.
     */
    public void eliminarFacultad(Facultad facultad) {
        if (facultad == null) return;
        facultades.remove(facultad);
        relacionUniversidadFacultad.put(this, facultades);
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
        if (id == null || id < 0 || id >= facultades.size()) {

            return null;
        }
        for(Facultad fac: facultades){
            if(fac.getIdFacultad()==id){
                return facultades.get(id);
            }
        }
        return facultades.get(id);
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
        facultades.sort(new OrdenarFacultadNombre());
    }

    /**
     * Ordena las facultades de la universidad por número de auditorios (descendente)
     */
    public void ordenarFacultadesPorNumAuditorios() {
        facultades.sort(new OrdenarFacultadNumAuditorios());
    }

    /**
     * Ordena las facultades de la universidad por número de usuarios (descendente)
     */
    public void ordenarFacultadesPorNumUsuarios() {
        facultades.sort(new OrdenarFacultadNumUsuarios());
    }

    /**
     * Ordena las facultades de la universidad por ID (ascendente)
     */
    public void ordenarFacultadesPorId() {
        facultades.sort(new OrdenarFacultadId());
    }
}