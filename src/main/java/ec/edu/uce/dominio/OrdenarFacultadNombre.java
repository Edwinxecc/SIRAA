package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarFacultadNombre implements Comparator<Facultad> {
    @Override
    public int compare(Facultad o1, Facultad o2) {
        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
            return -1;
        } else if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
            return 1;
        }
        return 0;
    }
} 