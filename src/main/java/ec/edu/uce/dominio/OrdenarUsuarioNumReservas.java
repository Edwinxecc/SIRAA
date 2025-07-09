package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarUsuarioNumReservas implements Comparator<Usuario> {
    @Override
    public int compare(Usuario o1, Usuario o2) {
        if (o2.getNumReservas() < o1.getNumReservas()) {
            return -1;
        } else if (o2.getNumReservas() > o1.getNumReservas()) {
            return 1;
        }
        return 0;
    }
} 