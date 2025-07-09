package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarUsuarioNombre implements Comparator<Usuario> {
    @Override
    public int compare(Usuario u1, Usuario u2) {
        int comparacionNombre = u1.getNombre().compareTo(u2.getNombre());
        if (comparacionNombre != 0) {
            return comparacionNombre;
        }
        return u1.getApellido().compareTo(u2.getApellido());
    }
} 