package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarAuditorioId implements Comparator<Auditorio> {
    @Override
    public int compare(Auditorio o1, Auditorio o2) {
        if (o1.getIdAuditorio() < o2.getIdAuditorio()) {
            return -1;
        } else if (o1.getIdAuditorio() > o2.getIdAuditorio()) {
            return 1;
        }
        return 0;
    }
}