/**
 * Representa la Universidad Central del Ecuador en el sistema SIRAA.
 * Esta clase maneja la información y operaciones relacionadas con las facultades de la universidad.
 */
package ec.edu.uce.dominio;


public class Universidad {
    private int numFacultades;
    private Facultad[] facultades;

    public Universidad(int numFacultades){
        this.numFacultades = numFacultades;
        this.facultades = new Facultad[0];
    }

    public Universidad(){
        this(0);
    }

    public int getNumFacultades() {
        return numFacultades;
    }

    public void setNumFacultades(int numFacultades) {
        if (numFacultades > 0){
            this.numFacultades = numFacultades;
        }
    }

    public void crearFacultad(){
        if (numFacultades == facultades.length){
            Facultad [] aux = facultades;
            facultades = new Facultad[numFacultades+1];
            System.arraycopy(aux, 0, facultades, 0, numFacultades);
        }
        facultades[numFacultades] = new Facultad();
        numFacultades++;
    }

    @Override
    public String toString() {
        return "[Nombre: UCE, " + "Facultades: " + getNumFacultades();
    }
}