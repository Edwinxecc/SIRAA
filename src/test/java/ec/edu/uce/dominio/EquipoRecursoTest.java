package ec.edu.uce.dominio;

import static org.junit.jupiter.api.Assertions.*;

class EquipoRecursoTest {

    GestionarEquipos equipoRecurso;

    @org.junit.jupiter.api.Test
    void getId() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        assert equipoRecurso.getId() == 16: "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void setId() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        equipoRecurso.setId(18);
        assert equipoRecurso.getId() == 18: "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void getNombre() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        assert equipoRecurso.getNombre().equalsIgnoreCase("EPSON"): "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void setNombre() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        equipoRecurso.setNombre("LG");
        assert equipoRecurso.getNombre().equalsIgnoreCase("LG"): "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void getTipo() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        assert equipoRecurso.getTipo().equalsIgnoreCase("Proyector"): "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void setTipo() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        equipoRecurso.setTipo("Monitor");
        assert equipoRecurso.getTipo().equalsIgnoreCase("Monitor"):"No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void getDisponible() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        assert equipoRecurso.getDisponible()==true: "No coinciden los valores";
    }

    @org.junit.jupiter.api.Test
    void setDisponible() {
        equipoRecurso = new GestionarEquipos(16, "EPSON", "Proyector", true);
        equipoRecurso.setDisponible();
        assert equipoRecurso.getDisponible()!= true: "Error al cambiar la disponibilidad";

    }

}