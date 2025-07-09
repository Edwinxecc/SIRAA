package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarAuditorioNumReservas implements Comparator<Auditorio> {
    @Override
    public int compare(Auditorio o1, Auditorio o2) {
        if (o2.getNumReservas() < o1.getNumReservas()) {
            return -1;
        } else if (o2.getNumReservas() > o1.getNumReservas()) {
            return 1;
        }
        return 0;
    }
}