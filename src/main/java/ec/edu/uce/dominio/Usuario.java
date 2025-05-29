package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa un usuario del sistema SIRAA.
 * Esta clase maneja la información personal y las reservas de un usuario.
 */
public class Usuario {
    private String nombre;
    private String apellido;
    private String correo;
    private int numReservas = 0;
    private Reserva[] reservas;

    public Usuario(String nombre, String apellido, String correo){
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.reservas = new Reserva[0];
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

    public Reserva[] getReservas() {
        return reservas;
    }

    // ========================
    // CRUD de Reservas
    // ========================

    public void crearReserva() {
        if (numReservas == reservas.length) {
            Reserva[] aux = reservas;
            reservas = new Reserva[numReservas + 1];
            System.arraycopy(aux, 0, reservas, 0, numReservas);
        }
        reservas[numReservas] = new Reserva();
        numReservas++;
    }

    public void crearReservaPrioritaria(int nivelPrioridad) {
        if (numReservas == reservas.length) {
            Reserva[] aux = reservas;
            reservas = new Reserva[numReservas + 1];
            System.arraycopy(aux, 0, reservas, 0, numReservas);
        }
        reservas[numReservas] = new ReservaPrioritaria();
        ((ReservaPrioritaria)reservas[numReservas]).setNivelPrioridad(nivelPrioridad);
        numReservas++;
    }

    public void listarReservas() {
        if (numReservas == 0) {
            return;
        }
        int index = 0;
        for (Reserva reserva : reservas) {
            String tipo = (reserva instanceof ReservaPrioritaria) ? "PRIORITARIA" : "NORMAL";
            index++;
        }
    }

    public void verReservas() {
        if (numReservas == 0) {
            return;
        }
    }

    public void actualizarReserva(int indice, int nuevoId, Date nuevaInicio, Date nuevaFin) {
        if (indice >= 0 && indice < numReservas) {
            reservas[indice].setIdReserva(nuevoId);
            reservas[indice].setFechaInicio(nuevaInicio);
            reservas[indice].setFechaFin(nuevaFin);
        }
    }

    public void eliminarReserva(int indice) {
        if (indice >= 0 && indice < numReservas) {
            for (int i = indice; i < numReservas - 1; i++) {
                reservas[i] = reservas[i + 1];
            }
            reservas[numReservas - 1] = null;
            numReservas--;

            Reserva[] aux = new Reserva[numReservas];
            System.arraycopy(reservas, 0, aux, 0, numReservas);
            reservas = aux;
        }
    }

    @Override
    public String toString() {
        return "Usuario: " + nombre + " " + apellido + ", Correo: " + correo + ", Reservas: " + numReservas;
    }
}