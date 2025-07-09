package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarAuditorioNombre implements Comparator<Auditorio> {
    @Override
    public int compare(Auditorio o1, Auditorio o2) {
        if (o1.getNombre().compareTo(o2.getNombre()) < 0) {
            return -1;
        } else if (o1.getNombre().compareTo(o2.getNombre()) > 0) {
            return 1;
        }
        return 0;
    }
}