package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarReservaEstado implements Comparator<Reserva> {
    @Override
    public int compare(Reserva o1, Reserva o2) {
        if (o1.getEstado().getDescripcion().compareTo(o2.getEstado().getDescripcion()) < 0) {
            return -1;
        } else if (o1.getEstado().getDescripcion().compareTo(o2.getEstado().getDescripcion()) > 0) {
            return 1;
        }
        return 0;
    }
} 