package ec.edu.uce.dominio;

public class Universidad {
    private int numFacultades = 0;
    private Facultad[] facultades;

    public Universidad(int capacidadInicial){
        this.numFacultades = 0;
        this.facultades = new Facultad[capacidadInicial > 0 ? capacidadInicial : 0];
    }

    public Universidad(){
        this(0);
    }

    public int getNumFacultades() {
        return numFacultades;
    }

    public void setNumFacultades(int numFacultades) {
        if (numFacultades >= 0){
            this.numFacultades = numFacultades;
        }
    }

    public void crearFacultad() {
        if (numFacultades == facultades.length) {
            Facultad[] aux = facultades;
            facultades = new Facultad[numFacultades + 1];
            System.arraycopy(aux, 0, facultades, 0, numFacultades);
        }
        facultades[numFacultades] = new Facultad();
        numFacultades++;
    }

    public Facultad[] getFacultades() {
        return facultades;
    }

    public Facultad getFacultad(int indice) {
        if (indice >= 0 && indice < numFacultades) {
            return facultades[indice];
        }
        return null;
    }

    public void actualizarFacultad(int indice, String nuevoNombre, int capacidadInicialAuditorios) {
        if (indice >= 0 && indice < numFacultades) {
            facultades[indice].setNombre(nuevoNombre);
            facultades[indice].setNumAuditorios(capacidadInicialAuditorios);
        }
    }

    public void eliminarFacultad(int indice) {
        if (indice >= 0 && indice < numFacultades) {
            for (int i = indice; i < numFacultades - 1; i++) {
                facultades[i] = facultades[i + 1];
            }
            facultades[numFacultades - 1] = null;
            numFacultades--;

            Facultad[] aux = new Facultad[numFacultades];
            System.arraycopy(facultades, 0, aux, 0, numFacultades);
            facultades = aux;
        }
    }

    public String listarNombresFacultades() {
        StringBuilder sb = new StringBuilder();
        for (Facultad f : facultades) {
            if (f != null) {
                sb.append(f.getNombre()).append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "[Nombre: UCE, Facultades: " + numFacultades + "]";
    }
}