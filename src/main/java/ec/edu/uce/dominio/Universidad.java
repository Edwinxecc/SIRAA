package ec.edu.uce.dominio;

import ec.edu.uce.Util.Validaciones;

public class Universidad {
    private String nombre;
    private int numFacultades;
    private Facultad[] facultades;

    public Universidad() {
        this.nombre = "Sin nombre";
        this.numFacultades = 0;
        this.facultades = new Facultad[2];
    }

    public Universidad(String nombre, int capacidadInicial) {
        this.nombre = nombre;
        this.numFacultades = 0;
        this.facultades = new Facultad[capacidadInicial];
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        Validaciones val = new Validaciones();
        val.ValidacionTexto(nombre, "nombre");
        this.nombre = nombre;
    }

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

    public Facultad buscarFacultad(String nombre) {
        for (int i = 0; i < numFacultades; i++) {
            if (facultades[i].getNombre().equalsIgnoreCase(nombre)) {
                return facultades[i];
            }
        }
        return null;
    }

    public boolean editarFacultad(String nombreOriginal, String nuevoNombre, int nuevosAuditorios) {
        Facultad f = buscarFacultad(nombreOriginal);
        if (f != null) {
            f.setNombre(nuevoNombre);
            f.setNumeroAuditorios(nuevosAuditorios);
            return true;
        }
        return false;
    }

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

    public String consultarFacultad() {
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numFacultades; i++) {
            texto.append(facultades[i]).append("\r\n");
        }
        return texto.toString();
    }

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