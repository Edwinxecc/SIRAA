package ec.edu.uce.dominio;

import java.util.Comparator;

public class OrdenarReservaNumEquipos implements Comparator<Reserva> {
    @Override
    public int compare(Reserva o1, Reserva o2) {
        if (o2.getEquipos().size() < o1.getEquipos().size()) {
            return -1;
        } else if (o2.getEquipos().size() > o1.getEquipos().size()) {
            return 1;
        }
        return 0;
    }
} 