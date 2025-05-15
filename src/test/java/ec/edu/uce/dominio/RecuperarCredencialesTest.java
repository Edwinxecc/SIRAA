package ec.edu.uce.dominio;

import static org.junit.jupiter.api.Assertions.*;

class RecuperarCredencialesTest {

    @org.junit.jupiter.api.Test
    void validarCodigo() {
        RecuperarCredenciales rc = new RecuperarCredenciales("usuario@ejemplo.com");
        assert rc.validarCodigo("123456") : "Código correcto no validado";
        assert rc.isCodigoValidado() : "El código debería estar validado";
    }

    @org.junit.jupiter.api.Test
    void getCorreo() {
        RecuperarCredenciales rc = new RecuperarCredenciales("test@correo.com");
        assert rc.getCorreo().equals("test@correo.com") : "Correo incorrecto";
    }

    @org.junit.jupiter.api.Test
    void setCorreo() {
        RecuperarCredenciales rc = new RecuperarCredenciales("viejo@correo.com");
        rc.setCorreo("nuevo@correo.com");
        assert rc.getCorreo().equals("nuevo@correo.com") : "Correo no actualizado";
    }

    @org.junit.jupiter.api.Test
    void isCodigoValidado() {
        RecuperarCredenciales rc = new RecuperarCredenciales("test@correo.com");
        assert !rc.isCodigoValidado() : "El código no debería estar validado por defecto";
    }

    @org.junit.jupiter.api.Test
    void crearSolicitud() {
        RecuperarCredenciales rc = new RecuperarCredenciales("user@correo.com");
        // No se puede testear lo que imprime, pero podemos afirmar que no lanza excepción
        rc.crearSolicitud();
        assert true; // Placeholder
    }

    @org.junit.jupiter.api.Test
    void leerEstado() {
        RecuperarCredenciales rc = new RecuperarCredenciales("user@correo.com");
        rc.leerEstado();
        assert true; // Placeholder
    }

    @org.junit.jupiter.api.Test
    void actualizarCorreo() {
        RecuperarCredenciales rc = new RecuperarCredenciales("user@correo.com");
        rc.actualizarCorreo("nuevo@correo.com");
        assert rc.getCorreo().equals("nuevo@correo.com") : "Correo no actualizado correctamente";
    }

    @org.junit.jupiter.api.Test
    void eliminarSolicitud() {
        RecuperarCredenciales rc = new RecuperarCredenciales("user@correo.com");
        rc.eliminarSolicitud();
        assert true; // Placeholder
    }
}