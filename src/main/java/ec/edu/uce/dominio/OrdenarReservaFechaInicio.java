package ec.edu.uce.dominio;

import java.util.Comparator;
import java.util.Date;

public class OrdenarReservaFechaInicio implements Comparator<Reserva> {
    @Override
    public int compare(Reserva o1, Reserva o2) {
        if (o1.getFechaInicio().compareTo(o2.getFechaInicio()) < 0) {
            return -1;
        } else if (o1.getFechaInicio().compareTo(o2.getFechaInicio()) > 0) {
            return 1;
        }
        return 0;
    }
} 