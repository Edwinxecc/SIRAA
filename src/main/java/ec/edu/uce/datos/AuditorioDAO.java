package ec.edu.uce.datos;

import ec.edu.uce.dominio.Auditorio;

public interface AuditorioDAO {
    void crear(Auditorio auditorio);
    void editar(Auditorio auditorio);
    void eliminar(String codigo);
    Auditorio buscarPorCodigo(String codigo);
    Auditorio[] consultarAuditorios();
}
