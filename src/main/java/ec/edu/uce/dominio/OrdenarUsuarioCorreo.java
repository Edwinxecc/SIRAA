package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarUsuarioCorreo implements Comparator<Usuario> {
    @Override
    public int compare(Usuario o1, Usuario o2) {
        if (o1.getCorreo().compareTo(o2.getCorreo()) < 0) {
            return -1;
        } else if (o1.getCorreo().compareTo(o2.getCorreo()) > 0) {
            return 1;
        }
        return 0;
    }
} 