package ec.edu.uce.dominio;

import java.util.ArrayList;

public class GestionarFacultades {
    private String nombre;
    private int numeroAuditorios;
    private ArrayList<GestionarFacultades> listaFacultades = new ArrayList<>();

    public GestionarFacultades(){
        this.nombre = "Ingenieria y Ciencias Aplicadas";
        this.numeroAuditorios = 1;
    }

    public GestionarFacultades(String nombre, int numAuditorios){
        this.nombre = nombre;
        this.numeroAuditorios = numAuditorios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroAuditorios() {
        return numeroAuditorios;
    }

    public void setNumeroAuditorios(int numeroAuditorios) {
        this.numeroAuditorios = numeroAuditorios;
    }

    public boolean crearFacultad(GestionarFacultades fac) {
        String nombre = fac.getNombre();
        int cantidad = fac.getNumeroAuditorios();

        if (nombre == null || nombre.isEmpty()) {
            return false;
        }
        listaFacultades.add(fac);
        return true;
    }
    public void buscarFacultades() {
        for (GestionarFacultades f : listaFacultades) {
            System.out.println("Nombre: " + f.getNombre() + ", Auditorios: " + f.getNumeroAuditorios());
        }
    }

    public boolean editarFacultad(String nombreOriginal, String nuevoNombre, int nuevosAuditorios) {
        for (GestionarFacultades f : listaFacultades) {
            if (f.getNombre().equalsIgnoreCase(nombreOriginal)) {
                f.setNombre(nuevoNombre);
                f.setNumeroAuditorios(nuevosAuditorios);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarFacultad(String nombre) {
        for (GestionarFacultades f : listaFacultades) {
            if (f.getNombre().equalsIgnoreCase(nombre)) {
                listaFacultades.remove(f);
                return true;
            }
        }
        return false;
    }

}
