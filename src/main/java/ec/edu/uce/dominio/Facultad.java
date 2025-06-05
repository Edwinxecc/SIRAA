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

    public String listarUsuarios() {
        String texto = "";
        for (Usuario u: usuarios){
            texto += u + "\r\n";
        }
        return texto;
    }

    public void actualizarUsuario(int indice, String nuevoNombre, String nuevoApellido, String nuevoCorreo) {
        if (indice >= 0 && indice < numUsuarios) {
            usuarios[indice].setNombre(nuevoNombre);
            usuarios[indice].setApellido(nuevoApellido);
            usuarios[indice].setCorreo(nuevoCorreo);
        }
    }

    public void eliminarUsuario(int indice) {
        if (indice < 0 || indice >= usuarios.length) {
            return;
        }
        Usuario[] aux = new Usuario[usuarios.length-1];
        // Copiar elementos antes del índice
        if (indice > 0) {
            System.arraycopy(usuarios, 0, aux, 0, indice);
        }
        // Copiar elementos después del índice
        if (indice < usuarios.length - 1) {
            System.arraycopy(usuarios, indice + 1, aux, indice, usuarios.length - indice - 1);
        }
        usuarios = aux; // Actualizar el array original
    }

    // ========================
    // CRUD de Auditorios
    // ========================

    public void crearAuditorio() {
        //por defecto
        if (numAuditorios == auditorios.length) {
            Auditorio[] aux = auditorios;
            auditorios = new Auditorio[numAuditorios + 1];
            System.arraycopy(aux, 0, auditorios, 0, numAuditorios);
        }
        auditorios[numAuditorios] = new Auditorio();
        numAuditorios++;
    }

    public void crearAuditorio(Auditorio auditorio){
        if (numAuditorios == auditorios.length) {
            Auditorio[] aux = auditorios;
            auditorios = new Auditorio[numAuditorios + 1];
            System.arraycopy(aux, 0, auditorios, 0, numAuditorios);
        }
        auditorios[numAuditorios] = auditorio;
        numAuditorios++;
    }

    public String listarAuditorios() {
        String texto = "";
        for (Auditorio a: auditorios){
            texto += a + "\r\n";
        }
        return texto;
    }

    public void actualizarAuditorio(int indice, String nuevoNombre, int nuevaCapacidad) {
        if (indice >= 0 && indice < numAuditorios) {
            auditorios[indice].setNombre(nuevoNombre);
            auditorios[indice].setCapacidad(nuevaCapacidad);
        }
    }

    public void eliminarAuditorio(int indice) {
        if (indice < 0 || indice >= auditorios.length) {
            return;
        }
        Auditorio[] aux = new Auditorio[auditorios.length-1];
        // Copiar elementos antes del índice
        if (indice > 0) {
            System.arraycopy(auditorios, 0, aux, 0, indice);
        }
        // Copiar elementos después del índice
        if (indice < auditorios.length - 1) {
            System.arraycopy(auditorios, indice + 1, aux, indice, auditorios.length - indice - 1);
        }
        auditorios = aux; // Actualizar el array original
    }

    // ========================
    // toString
    // ========================

    @Override
    public String toString() {
        return "Facultad: " + nombre + ", Usuarios: " + numUsuarios + ", Auditorios: " + numAuditorios;
    }
}