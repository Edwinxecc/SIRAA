package ec.edu.uce.datos;

import ec.edu.uce.dominio.Facultad;

public interface FacultadDAO {
    void crear(Facultad facultad);
    void editar(Facultad facultad);
    void eliminar(String codigo);
    Facultad buscarPorCodigo(String codigo);
    Facultad[] consultarFacultades();
}
