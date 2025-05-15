package ec.edu.uce.dominio;

import java.util.Date;
import java.util.Random;

public class Reserva {
    private String idReserva;
    private Date fechaReserva;

    public Reserva(String id){
        this.idReserva = id;
        this.fechaReserva = new Date();
    }

    public Date getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(Date fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String generarId(Usuario usuario){
        String datosUsuario = usuario.getNombre().substring(3) + usuario.getApellido().substring(3);
        Random num = new Random();
        int numRandom = num.nextInt(999);
        return  datosUsuario + numRandom;
    }
}
