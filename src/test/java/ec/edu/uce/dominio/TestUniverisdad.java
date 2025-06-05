package ec.edu.uce.dominio;

public class TestUniverisdad {
    public static void main(String[] args) {
        Universidad uni = new Universidad();
        Facultad fac = new Facultad("Civil", 167);
        uni.crearFacultad(fac);
        uni.crearFacultad();
        System.out.println(uni.listarNombresFacultades());
        uni.eliminarFacultad(0);
        System.out.println(uni.listarNombresFacultades());

    }
}
