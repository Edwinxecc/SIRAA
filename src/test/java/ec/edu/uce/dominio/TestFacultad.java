package ec.edu.uce.dominio;

public class TestFacultad {
    public static void main(String[] args) {
        Facultad f = new Facultad();
        f.crearUsuario("luis", "Caiza", "a@uce.edu.ec");
        f.crearUsuario("Lewandowski", "Angulo", "lew@uce.edu.ec");
        System.out.println(f.listarUsuarios());
        System.out.println("Eliminar un usuario: ");
        f.eliminarUsuario(0);
        System.out.println(f.listarUsuarios());
        // Auditorio
        Auditorio a = new Auditorio("Hola", 176);
        Auditorio b = new Auditorio("FICA", 125);
        f.crearAuditorio();
        f.crearAuditorio(a);
        f.crearAuditorio(b);
        System.out.println(f.listarAuditorios());
        System.out.println("Eliminando: ");
        f.eliminarAuditorio(0);
        System.out.println(f.listarAuditorios());

    }
}