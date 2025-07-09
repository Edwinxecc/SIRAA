package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarUsuarioApellido implements Comparator<Usuario> {
    @Override
    public int compare(Usuario o1, Usuario o2) {
        if (o1.getApellido().compareTo(o2.getApellido()) < 0) {
            return -1;
        } else if (o1.getApellido().compareTo(o2.getApellido()) > 0) {
            return 1;
        }
        return 0;
    }
} 