package ec.edu.uce.dominio;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorTest {

    @org.junit.jupiter.api.Test
    void getIdAdministrador() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        assert administrador.getIdAdministrador()==13: "No coincide";
    }

    @org.junit.jupiter.api.Test
    void setIdAdministrador() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        administrador.setIdAdministrador(19);
        assert administrador.getIdAdministrador()==19: "Np coinciden";

    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        assert administrador.getNombre() == "Marco": "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        administrador.setNombre("Oscar");
        assert administrador.getNombre() == "Oscar": "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void getApellido() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        assert administrador.getApellido() == "Borro": "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void setApellido() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        administrador.setApellido("Perez");
        assert administrador.getApellido() == "Perez":"No coinciden";
    }

    @org.junit.jupiter.api.Test
    void getCorreo() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        assert administrador.getCorreo() == "msrcb@uce.edu.ec": "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void setCorreo() {
        Administrador administrador;
        administrador = new Administrador(13, "Marco", "Borro", "msrcb@uce.edu.ec");
        administrador.setCorreo("correonuevo@uce.edu.ec");
        assert administrador.getCorreo() == "correonuevo@uce.edu.ec": "No coinciden";
    }

}