package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarEquipoCategoria implements Comparator<Equipo> {
    @Override
    public int compare(Equipo o1, Equipo o2) {
        if (o1.getCategoria().compareTo(o2.getCategoria()) < 0) {
            return -1;
        } else if (o1.getCategoria().compareTo(o2.getCategoria()) > 0) {
            return 1;
        }
        return 0;
    }
} 