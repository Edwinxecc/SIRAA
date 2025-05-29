package ec.edu.uce.dominio;

/**
 * Representa una facultad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades, incluyendo sus auditorios y usuarios.
 */
public class Facultad {
    private String nombre;
    private int numAuditorios = 0;
    private Auditorio[] auditorios;
    private int numUsuarios = 0;
    private Usuario[] usuarios;

    // Constructor completo
    public Facultad(String nombre, int capacidadInicialAuditorios){
        this.nombre = nombre;
        this.auditorios = new Auditorio[capacidadInicialAuditorios > 0 ? capacidadInicialAuditorios : 0];
        this.usuarios = new Usuario[0];
    }

    // Constructor por defecto
    public Facultad(){
        this("Sin nombre", 0);
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null && nombre.length() > 5){
            this.nombre = nombre;
        }
    }

    public int getNumAuditorios() {
        return numAuditorios;
    }

    public void setNumAuditorios(int numAuditorios) {
        if (numAuditorios >= 0){
            this.numAuditorios = numAuditorios;
        }
    }

    public int getNumUsuarios() {
        return numUsuarios;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public Auditorio[] getAuditorios() {
        return auditorios;
    }

    // ========================
    // CRUD de Usuarios
    // ========================

    public void crearUsuario(String nombre, String apellido, String correo) {
        if (numUsuarios == usuarios.length) {
            Usuario[] aux = usuarios;
            usuarios = new Usuario[numUsuarios + 1];
            System.arraycopy(aux, 0, usuarios, 0, numUsuarios);
        }
        usuarios[numUsuarios] = new Usuario(nombre, apellido, correo);
        numUsuarios++;
    }

    public void listarUsuarios() {
        if (numUsuarios == 0) {
            return;
        }
        for (int i = 0; i < numUsuarios; i++) {
            Usuario u = usuarios[i];
        }
    }

    public void actualizarUsuario(int indice, String nuevoNombre, String nuevoApellido, String nuevoCorreo) {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nuevoNombre);
            usuarios[indice].setApellido(nuevoApellido);
            usuarios[indice].setCorreo(nuevoCorreo);
        }
    }

    public void eliminarUsuario(int indice) {
        if (indice >= 0 && indice < numUsuarios) {
            for (int i = indice; i < numUsuarios - 1; i++) {
                usuarios[i] = usuarios[i + 1];
            }
            usuarios[numUsuarios - 1] = null;
            numUsuarios--;

            Usuario[] aux = new Usuario[numUsuarios];
            System.arraycopy(usuarios, 0, aux, 0, numUsuarios);
            usuarios = aux;
        }
    }

    // ========================
    // CRUD de Auditorios
    // ========================

    public void crearAuditorio() {
        if (numAuditorios == auditorios.length) {
            Auditorio[] aux = auditorios;
            auditorios = new Auditorio[numAuditorios + 1];
            System.arraycopy(aux, 0, auditorios, 0, numAuditorios);
        }
        auditorios[numAuditorios] = new Auditorio();
        numAuditorios++;
    }

    public void listarAuditorios() {
        if (numAuditorios == 0) {
            return;
        }
        for (int i = 0; i < numAuditorios; i++) {
            Auditorio a = auditorios[i];
        }
    }

    public void actualizarAuditorio(int indice, String nuevoNombre, int nuevaCapacidad) {
        if (indice >= 0 && indice < numAuditorios) {
            auditorios[indice].setNombre(nuevoNombre);
            auditorios[indice].setCapacidad(nuevaCapacidad);
        }
    }

    public void eliminarAuditorio(int indice) {
        if (indice >= 0 && indice < numAuditorios) {
            for (int i = indice; i < numAuditorios - 1; i++) {
                auditorios[i] = auditorios[i + 1];
            }
            auditorios[numAuditorios - 1] = null;
            numAuditorios--;

            Auditorio[] aux = new Auditorio[numAuditorios];
            System.arraycopy(auditorios, 0, aux, 0, numAuditorios);
            auditorios = aux;
        }
    }

    // ========================
    // toString
    // ========================

    @Override
    public String toString() {
        return "Facultad: " + nombre + ", Usuarios: " + numUsuarios + ", Auditorios: " + numAuditorios;
    }
}