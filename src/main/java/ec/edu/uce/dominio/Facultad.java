/**
 * Representa una facultad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades, incluyendo sus auditorios y usuarios.
 */
package ec.edu.uce.dominio;

public class Facultad {
    private String nombre;
    private int numAuditorios;
    private Auditorio[] auditorios;
    private int numUsuarios = 0;
    private Usuario[] usuarios;


    public Facultad(String nombre, int numAuditorios){
        this.nombre = nombre;
        this.numAuditorios = numAuditorios;
        this.usuarios = new Usuario[numUsuarios];
    }

    public Facultad(){
        this("Sin nombre", 0);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 5){
            this.nombre = nombre;
        }
    }

    public int getNumAuditorios() {
        return numAuditorios;
    }

    public void setNumAuditorios(int numAuditorios) {
        if (numAuditorios>0){
            this.numAuditorios = numAuditorios;
        }
    }

    public void crearUsuario(){
        if (numUsuarios == usuarios.length){
            Usuario [] aux = usuarios;
            usuarios = new Usuario[numUsuarios+1];
            System.arraycopy(aux, 0, usuarios, 0, numUsuarios);
        }
        usuarios[numUsuarios] = new Usuario();
        numUsuarios++;
    }

    public void crearAuditorio(){
        if(numAuditorios == auditorios.length){
            Auditorio [] aux = auditorios;
            auditorios = new Auditorio[numAuditorios+1];
            System.arraycopy(aux, 0, auditorios, 0, numAuditorios);
        }
        auditorios[numAuditorios] = new Auditorio();
        numAuditorios++;
    }
}
