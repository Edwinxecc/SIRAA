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

    public void crearFacultad(Facultad facultad) {
        if (numFacultades == facultades.length) {
            Facultad[] aux = facultades;
            facultades = new Facultad[numFacultades + 1];
            System.arraycopy(aux, 0, facultades, 0, numFacultades);
        }
        facultades[numFacultades] = facultad;
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
        if (indice < 0 || indice >= facultades.length) {
            return;
        }
        Facultad[] aux = new Facultad[facultades.length - 1];
        // Copiar elementos antes del índice
        if (indice > 0) {
            System.arraycopy(facultades, 0, aux, 0, indice);
        }
        // Copiar elementos después del índice
        if (indice < facultades.length - 1) {
            System.arraycopy(facultades, indice + 1, aux, indice, facultades.length - indice - 1);
        }
        facultades = aux; // Actualizar el array original
    }

    public String listarNombresFacultades() {
        String texto = "";
        for (Facultad f: facultades){
            texto += f + "\r\n";
        }
        return texto;
    }

    @Override
    public String toString() {
        return "[Nombre: UCE, Facultades: " + numFacultades + "]";
    }
}