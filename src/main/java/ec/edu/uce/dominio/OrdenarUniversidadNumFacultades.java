package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarUniversidadNumFacultades implements Comparator<Universidad> {
    @Override
    public int compare(Universidad o1, Universidad o2) {
        if (o2.getNumFacultades() < o1.getNumFacultades()) {
            return -1;
        } else if (o2.getNumFacultades() > o1.getNumFacultades()) {
            return 1;
        }
        return 0;
    }
} 