package ec.edu.uce.dominio;


import ec.edu.uce.dominio.Facultad;

import java.util.ArrayList;
import java.util.List;

/**
 * TestAuditorio
 */
public class TestAuditorio {

  public static void main(String[] args) {
    //Facultad fac = new Facultad();
    //Facultad f = new Facultad("Ingenieria");

    Facultad fac = new Facultad("Ingenieria");
    Auditorio au = new Auditorio();
    List<Auditorio> auditorios = new ArrayList<>(2);
    fac.crearAuditorio();
    fac.crearAuditorio(auditorios.add(new Auditorio("Oli", 2)));
    fac.crearAuditorio(auditorios.add(new Auditorio("Sambrano", 190));
    System.out.println(fac);
    System.out.println(fac.listarAuditorios());
    au.crearReserva();
  }

}
