package consola;

public class Usuario {
    private String idUsario;
    private String nombre;
    private String apellido;
    private String correo;
    // tipoUsuario se define 0 administrador, 1 docente y 2 estudiante
    private int tipoUsuario;

    public Usuario(String id, String nombreArg, String apellidoArg, String correoArg){
        // code example xd
        this.idUsario = id;
        this.nombre = nombreArg;
        this.apellido = apellidoArg;
        this.correo = correoArg;
    }

}
