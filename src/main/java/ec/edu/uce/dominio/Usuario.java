/**
 * Representa un usuario del sistema SIRAA.
 * Esta clase maneja la información personal y las reservas de un usuario.
 */
package ec.edu.uce.dominio;


public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private int numReservas = 0;
    private Reserva [] reservas;

    public Usuario(String nombre, String apellido, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.reservas = new Reserva[numReservas];


    }

    public Usuario(){
        this("Sin nombre", "Sin apellido", "NOuser@example.com");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre.length() > 5){
            this.nombre = nombre;
        }
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido.length() > 3){
            this.apellido = apellido;
        }
    }

    public String getCorreo() {
        return correo;
    }


    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void crearReservas(){
        if (numReservas == reservas.length){
            Reserva [] aux = reservas;
            reservas = new Reserva [numReservas+1];
            System.arraycopy(aux, 0, reservas, 0, numReservas);
        }
        reservas[numReservas] = new Reserva();
        numReservas++;
    }
}