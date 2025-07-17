package ec.edu.uce.dominio;


public class TestMapRelaciones {
    public static void main(String[] args) {
        Universidad u = Universidad.getInstancia();
        u.crearFacultad(new Facultad("Ingenieria", 12));
        u.crearFacultad(new Facultad("Medicina", 8));

        Facultad fac = new Facultad();
        fac.crearAuditorio(0, new Auditorio("Aula Magna", 180));
        fac.crearAuditorio(1, new Auditorio("Sala de parto xd", 180));
        Usuario user = new Usuario("Edwin", "Caiza", "edcaizac1@uce.edu.ec");
        fac.crearUsuario(user);
        System.out.println(fac.listarUsuarios());
        System.out.println(fac.getUsuarios().get(0).getNombre());

        user.crearReserva(0,new Reserva());

        System.out.println(user.listarReservas());
        System.out.println(u.listarNombresFacultades());


    }
}
