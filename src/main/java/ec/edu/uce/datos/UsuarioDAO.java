package ec.edu.uce.datos;

import ec.edu.uce.dominio.Usuario;

public interface UsuarioDAO {
    void crear(Usuario usuario);
    void editar(Usuario usuario);
    void eliminar(String codigo);
    Usuario buscarPorCodigo(String codigo);
    Usuario[] consultarUsuarios();
}