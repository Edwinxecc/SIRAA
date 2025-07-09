package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarEquipoId implements Comparator<Equipo> {
    @Override
    public int compare(Equipo o1, Equipo o2) {
        if (o1.getIdEquipo() < o2.getIdEquipo()) {
            return -1;
        } else if (o1.getIdEquipo() > o2.getIdEquipo()) {
            return 1;
        }
        return 0;
    }
} 