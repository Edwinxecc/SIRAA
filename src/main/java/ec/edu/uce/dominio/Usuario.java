package ec.edu.uce.dominio;

import java.util.Random;

public class Usuario {
    private int usuarioId;
    private String nombre;
    private String apellido;
    private String correo;

    public Usuario(){

    }

    public Usuario(int id, String nombre, String apellido, String correo){
        this.usuarioId=id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
    }

    public Usuario (Usuario []usuarios){
        usuarios = new Usuario[usuarios.length];
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

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int generarId(){
        Random num = new Random();
        int numRandom = num.nextInt(999);
        return numRandom;
    }

    public void crearUsuario(Usuario nuevoUsuario) {
        nuevoUsuario = new Usuario();
        this.usuarioId = nuevoUsuario.generarId();
        this.nombre = nuevoUsuario.nombre;
        this.apellido = nuevoUsuario.apellido;
        this.correo = nuevoUsuario.correo;

    }



    public String toString() {
        return "ID:"+this.correo+"\n" +
                "Nombre: " + this.nombre + "\n" +
                "Apellid: " + this.apellido + "\n" +
                "correo: " + this.correo;
    }

}
