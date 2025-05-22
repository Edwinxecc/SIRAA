/**
 * Representa la Universidad Central del Ecuador en el sistema SIRAA.
 * Esta clase maneja la información y operaciones relacionadas con las facultades de la universidad.
 */
package ec.edu.uce.dominio;

import ec.edu.uce.Util.Validaciones;

public class Universidad {
    private String nombre;
    private int numFacultades;
    private Facultad[] facultades;

    /**
     * Constructor por defecto.
     * Inicializa la universidad con valores predeterminados.
     */
    public Universidad() {
        this.nombre = "Sin nombre";
        this.numFacultades = 0;
        this.facultades = new Facultad[2];
    }

    /**
     * Constructor con parámetros.
     * @param nombre Nombre de la universidad
     * @param capacidadInicial Capacidad inicial del arreglo de facultades
     */
    public Universidad(String nombre, int capacidadInicial) {
        this.nombre = nombre;
        this.numFacultades = 0;
        this.facultades = new Facultad[capacidadInicial];
    }

    /**
     * Obtiene el nombre de la universidad.
     * @return Nombre de la universidad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la universidad.
     * @param nombre Nombre a establecer
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public void setNombre(String nombre) {
        Validaciones val = new Validaciones();
        val.ValidacionTexto(nombre, "nombre");
        this.nombre = nombre;
    }

    /**
     * Crea una nueva facultad en la universidad.
     * Si el arreglo de facultades está lleno, se duplica su capacidad.
     * @param facultad Facultad a crear
     */
    public void crearFacultad(Facultad facultad) {

        if (numFacultades == facultades.length) {
            Facultad[] facaux = new Facultad[facultades.length * 2];
            for (int i = 0; i < facultades.length; i++) {
                facaux[i] = facultades[i];
            }
            facultades = facaux;
        }
        facultades[numFacultades++] = facultad;
    }

    /**
     * Busca una facultad por su nombre.
     * @param nombre Nombre de la facultad a buscar
     * @return Facultad encontrada o null si no existe
     */
    public Facultad buscarFacultad(String nombre) {
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i].getNombre().equalsIgnoreCase(nombre)) {
                return facultades[i];
            }
        }
        return null;
    }

    /**
     * Edita una facultad existente.
     * @param nombreOriginal Nombre actual de la facultad
     * @param nuevoNombre Nuevo nombre
     * @param nuevosAuditorios Nuevo número de auditorios
     * @return true si se editó la facultad, false si no se encontró
     */
    public boolean editarFacultad(String nombreOriginal, String nuevoNombre, int nuevosAuditorios) {
        Facultad f = buscarFacultad(nombreOriginal);
        if (f != null) {
            f.setNombre(nuevoNombre);
            f.setNumeroAuditorios(nuevosAuditorios);
            return true;
        }
        return false;
    }

    /**
     * Elimina una facultad por su nombre.
     * @param nombre Nombre de la facultad a eliminar
     * @return true si se eliminó la facultad, false si no se encontró
     */
    public boolean eliminarFacultad(String nombre) {
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i].getNombre().equalsIgnoreCase(nombre)) {
                for (int j = i; j < numFacultades - 1; j++) {
                    facultades[j] = facultades[j + 1];
                }
                facultades[--numFacultades] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene una lista de todas las facultades.
     * @return Lista de nombres de facultades
     */
    public String consultarFacultad() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numFacultades; i++) {
            texto.append(facultades[i]).append("\r\n");
        }
        return texto.toString();
    }

    /**
     * Devuelve una representación en texto de la universidad.
     * @return String con los datos de la universidad y sus facultades
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Universidad: ").append(nombre).append("\n");
        sb.append("Número de Facultades: ").append(numFacultades).append("\n");
        sb.append("Facultades:\n");
        for (int i = 0; i < numFacultades; i++) {
            sb.append("- ").append(facultades[i]).append("\n");
        }
        return sb.toString();
    }
}