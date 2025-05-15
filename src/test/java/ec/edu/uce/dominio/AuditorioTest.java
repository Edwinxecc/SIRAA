package ec.edu.uce.dominio;

import static org.junit.jupiter.api.Assertions.*;

class AuditorioTest {
    Auditorio auditorio;
    @org.junit.jupiter.api.Test
    void getId() {
        auditorio = new Auditorio(78, "Auditorio Antonio Salgado", 156, "FICA");
        assert auditorio.getId()==78: "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void setId() {
        auditorio = new Auditorio(78, "Auditorio Antonio Salgado", 156, "FICA");
        auditorio.setId(96);
        assert auditorio.getId()==96: "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        auditorio = new Auditorio(78, "Auditorio Antonio Salgado", 156, "FICA");
        assert auditorio.getNombre() == "Auditorio Antonio Salgado": "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        auditorio = new Auditorio(78, "Auditorio Antonio Salgado", 156, "FICA");
        assert auditorio.getNombre().equals("Auditorio Antonio Salgado") : "No coinciden";
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        auditorio = new Auditorio(78, "Nombre Inicial", 156, "FICA");
        auditorio.setNombre("Nuevo Nombre");
        assert auditorio.getNombre().equals("Nuevo Nombre") : "No se actualizó el nombre correctamente";
    }

    @org.junit.jupiter.api.Test
    void getCapacidad() {
        auditorio = new Auditorio(78, "Auditorio", 200, "FICA");
        assert auditorio.getCapacidad() == 200 : "La capacidad no coincide";
    }

    @org.junit.jupiter.api.Test
    void setCapacidad() {
        auditorio = new Auditorio(78, "Auditorio", 100, "FICA");
        auditorio.setCapacidad(250);
        assert auditorio.getCapacidad() == 250 : "No se actualizó la capacidad correctamente";
    }

    @org.junit.jupiter.api.Test
    void getUbicacion() {
        auditorio = new Auditorio(78, "Auditorio", 150, "FICA");
        assert auditorio.getUbicacion().equals("FICA") : "La ubicación no coincide";
    }

    @org.junit.jupiter.api.Test
    void setUbicacion() {
        auditorio = new Auditorio(78, "Auditorio", 150, "FICA");
        auditorio.setUbicacion("Nuevo Edificio");
        assert auditorio.getUbicacion().equals("Nuevo Edificio") : "No se actualizó la ubicación correctamente";
    }

    @org.junit.jupiter.api.Test
    void testCrearAuditorio() {
        Auditorio auditorio = new Auditorio(1, "Auditorio Central", 300, "Edificio Norte");
        assert auditorio.crearAuditorio().equals("Auditorio creado: Auditorio Central") : "Mensaje incorrecto";
    }

    @org.junit.jupiter.api.Test
    void testConsultarAuditorio() {
        Auditorio auditorio = new Auditorio(2, "Auditorio A", 150, "Edificio Este");
        assert auditorio.consultarAuditorio().equals("Auditorio: Auditorio A - Capacidad: 150 - Ubicación: Edificio Este") : "Datos incorrectos";
    }

    @org.junit.jupiter.api.Test
    void testActualizarAuditorio() {
        Auditorio auditorio = new Auditorio(3, "Auditorio B", 200, "Edificio Sur");
        auditorio.actualizarAuditorio("Auditorio Renovado", 400, "Edificio Norte");
        assert auditorio.getNombre().equals("Auditorio Renovado");
        assert auditorio.getCapacidad() == 400;
        assert auditorio.getUbicacion().equals("Edificio Norte");
    }

    @org.junit.jupiter.api.Test
    void testEliminarAuditorio() {
        Auditorio auditorio = new Auditorio(4, "Auditorio X", 120, "Edificio Oeste");
        assert auditorio.eliminarAuditorio().equals("Auditorio eliminado: Auditorio X") : "Eliminación incorrecta";
    }
}