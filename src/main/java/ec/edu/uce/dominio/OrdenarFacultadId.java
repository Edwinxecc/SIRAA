package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarFacultadId implements Comparator<Facultad> {
    @Override
    public int compare(Facultad o1, Facultad o2) {
        if (o1.getIdFacultad() < o2.getIdFacultad()) {
            return -1;
        } else if (o1.getIdFacultad() > o2.getIdFacultad()) {
            return 1;
        }
        return 0;
    }
} 