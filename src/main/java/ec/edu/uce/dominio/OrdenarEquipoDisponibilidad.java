package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarEquipoDisponibilidad implements Comparator<Equipo> {
    @Override
    public int compare(Equipo o1, Equipo o2) {
        if (o1.getDisponibilidad() && !o2.getDisponibilidad()) {
            return -1;
        } else if (!o1.getDisponibilidad() && o2.getDisponibilidad()) {
            return 1;
        } else if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
            return -1;
        } else if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
            return 1;
        }
        return 0;
    }
} 