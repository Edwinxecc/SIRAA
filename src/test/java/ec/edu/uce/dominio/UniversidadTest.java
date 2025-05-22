package ec.edu.uce.dominio;


class UniversidadTest {
    public static void main(String[] args) {
        Universidad uni = new Universidad("UCE", 21);
        Facultad fac = new Facultad("Ingenieria", 2);
        uni.crearFacultad(fac);
        uni.crearFacultad(fac = new Facultad("Arte", 1));
        System.out.println(uni);
        System.out.println(uni.consultarFacultad());
    }
}