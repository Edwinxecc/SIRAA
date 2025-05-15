package ec.edu.uce.dominio;

import static org.junit.jupiter.api.Assertions.*;

class ParametroYPoliticasTest {

    @org.junit.jupiter.api.Test
    void getId() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(1, "Política X", "Activo");
        assert parametro.getId() == 1 : "ID incorrecto";
    }

    @org.junit.jupiter.api.Test
    void setId() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(0, "Política Y", "Inactivo");
        parametro.setId(5);
        assert parametro.getId() == 5 : "ID no actualizado";
    }

    @org.junit.jupiter.api.Test
    void getPolitica() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(2, "Política Z", "Activo");
        assert parametro.getPolitica().equals("Política Z") : "Política incorrecta";
    }

    @org.junit.jupiter.api.Test
    void setPolitica() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(3, "", "Inactivo");
        parametro.setPolitica("Nueva Política");
        assert parametro.getPolitica().equals("Nueva Política") : "Política no actualizada";
    }

    @org.junit.jupiter.api.Test
    void getValor() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(4, "Política A", "Permitido");
        assert parametro.getValor().equals("Permitido") : "Valor incorrecto";
    }

    @org.junit.jupiter.api.Test
    void setValor() {
        ParametroYPoliticas parametro = new ParametroYPoliticas(5, "Política B", "");
        parametro.setValor("Denegado");
        assert parametro.getValor().equals("Denegado") : "Valor no actualizado";
    }

}