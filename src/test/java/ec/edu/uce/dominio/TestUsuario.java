package ec.edu.uce.dominio;

public class TestUsuario {
    public static void main(String[] args) {
        Usuario user = new Usuario();
        Reserva re = new Reserva();
        user.crearReserva(re);
        System.out.println(user.listarReservas());
    }
}
