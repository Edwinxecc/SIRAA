package ec.edu.uce.dominio;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class ReservaTest {

    @org.junit.jupiter.api.Test
    void getFechaReserva() {
        Reserva reserva = new Reserva(1);
        assert reserva.getFechaReserva() != null;
    }

    @org.junit.jupiter.api.Test
    void setFechaReserva() {
        Reserva reserva = new Reserva();
        Date fecha = new Date(1234567890000L);
        reserva.setFechaReserva(fecha);
        assert reserva.getFechaReserva().equals(fecha);
    }

    @org.junit.jupiter.api.Test
    void generarId() {
        Reserva reserva = new Reserva();
        Usuario usuario = new Usuario();
        usuario.setNombre("Carlos");
        usuario.setApellido("Perez");

        String id = reserva.generarId(usuario);
        String esperadoInicio = "losrez"; // Carlos → los, Perez → rez

        assert id.startsWith(esperadoInicio);

        String numero = id.substring(esperadoInicio.length());
        assert numero.matches("\\d{1,3}");
    }

    @org.junit.jupiter.api.Test
    void crearReserva() {
        Reserva reserva = new Reserva();
        reserva.crearReserva(123);

        try {
            java.lang.reflect.Field campo = Reserva.class.getDeclaredField("idReserva");
            campo.setAccessible(true);
            int id = (int) campo.get(reserva);
            assert id == 123;
        } catch (Exception e) {
            assert false; // La prueba falla si ocurre alguna excepción
        }
    }
}