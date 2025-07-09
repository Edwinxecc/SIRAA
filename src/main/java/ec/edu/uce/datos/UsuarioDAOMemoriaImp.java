package ec.edu.uce.datos;

import ec.edu.uce.dominio.Usuario;
import java.util.Arrays;

public class UsuarioDAOMemoriaImp implements UsuarioDAO {
    private static Usuario[] usuarios;
    private static int cantUsuarios;

    static {
        usuarios = new Usuario[10];
        cantUsuarios = 0;
    }

    @Override
    public void crear(Usuario usuario) {
        if (usuario == null || buscarPorCodigo(usuario.getCodigoUsuario()) != null) {
            return;
        }
        if (cantUsuarios == usuarios.length) {
            usuarios = Arrays.copyOf(usuarios, usuarios.length * 2);
        }
        usuarios[cantUsuarios] = usuario;
        cantUsuarios++;
    }

    @Override
    public void editar(Usuario usuario) {
        if (usuario == null) return;
        for (int i = 0; i < cantUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigoUsuario().equals(usuario.getCodigoUsuario())) {
                usuarios[i] = usuario;
                return;
            }
        }
    }

    @Override
    public void eliminar(String codigo) {
        int indiceAEliminar = -1;
        for (int i = 0; i < cantUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigoUsuario().equals(codigo)) {
                indiceAEliminar = i;
                break;
            }
        }
        if (indiceAEliminar != -1) {
            System.arraycopy(usuarios, indiceAEliminar + 1, usuarios, indiceAEliminar, cantUsuarios - 1 - indiceAEliminar);
            usuarios[--cantUsuarios] = null;
        }
    }

    @Override
    public Usuario buscarPorCodigo(String codigo) {
        for (int i = 0; i < cantUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].getCodigoUsuario().equals(codigo)) {
                return usuarios[i];
            }
        }
        return null;
    }

    @Override
    public Usuario[] consultarUsuarios() {
        return Arrays.copyOf(usuarios, cantUsuarios);
    }
}
