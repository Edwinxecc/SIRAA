package ec.edu.uce.dominio;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String correo;

    public Usuario(int id, String nombre, String apellido, String correo){
        this.usuarioId=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }
}
