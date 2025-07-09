package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarFacultadNumAuditorios implements Comparator<Facultad> {
    @Override
    public int compare(Facultad o1, Facultad o2) {
        if (o2.getNumAuditorios() < o1.getNumAuditorios()) {
            return -1;
        } else if (o2.getNumAuditorios() > o1.getNumAuditorios()) {
            return 1;
        }
        return 0;
    }
} 