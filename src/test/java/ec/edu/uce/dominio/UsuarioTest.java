package ec.edu.uce.dominio;

class UsuarioTest {

    @org.junit.jupiter.api.Test
    void getUsuarioId() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(10);
        assert usuario.getUsuarioId() == 10;
    }

    @org.junit.jupiter.api.Test
    void setUsuarioId() {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(25);
        assert usuario.getUsuarioId() == 25;
    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Juan");
        assert usuario.getNombre().equals("Juan");
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Lucía");
        assert usuario.getNombre().equals("Lucía");
    }

    @org.junit.jupiter.api.Test
    void getApellido() {
        Usuario usuario = new Usuario();
        usuario.setApellido("Ramirez");
        assert usuario.getApellido().equals("Ramirez");
    }

    @org.junit.jupiter.api.Test
    void setApellido() {
        Usuario usuario = new Usuario();
        usuario.setApellido("Gómez");
        assert usuario.getApellido().equals("Gómez");
    }

    @org.junit.jupiter.api.Test
    void getCorreo() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("correo@dominio.com");
        assert usuario.getCorreo().equals("correo@dominio.com");
    }

    @org.junit.jupiter.api.Test
    void setCorreo() {
        Usuario usuario = new Usuario();
        usuario.setCorreo("test@ejemplo.com");
        assert usuario.getCorreo().equals("test@ejemplo.com");
    }

    @org.junit.jupiter.api.Test
    void generarId() {
        Usuario usuario = new Usuario();
        int id = usuario.generarId();
        assert id >= 0 && id < 999;
    }

    @org.junit.jupiter.api.Test
    void crearUsuario() {
        Usuario usuario = new Usuario();
        Usuario nuevo = new Usuario();
        nuevo.setNombre("Ana");
        nuevo.setApellido("Lopez");
        nuevo.setCorreo("ana@correo.com");

        usuario.crearUsuario(nuevo);

        assert usuario.getNombre() == null;
        assert usuario.getApellido() == null;
        assert usuario.getCorreo() == null;
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        Usuario usuario = new Usuario();
        usuario.setNombre("Mario");
        usuario.setApellido("Torres");
        usuario.setCorreo("mario@dominio.com");

        String texto = usuario.toString();

        assert texto.contains("ID:mario@dominio.com");
        assert texto.contains("Nombre: Mario");
        assert texto.contains("Apellid: Torres");
    }
}