package ec.edu.uce.dominio;

public class TestEquipo {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Equipo ---");

        // 1. Instanciación con constructor por defecto
        Equipo e1 = new Equipo();
        System.out.println("Equipo por defecto: " + e1);

        // 2. Instanciación con constructor completo
        Equipo e2 = new Equipo("Laptop", "Computación", true);
        System.out.println("Equipo con datos: " + e2);

        // 3. Prueba de setters con valores válidos e inválidos
        e2.setNombre("Laptop Gamer");
        e2.setCategoria("Computación Avanzada");
        e2.setDisponibilidad(false);
        System.out.println("Equipo modificado: " + e2);

        e2.setNombre(null); // No debe cambiar
        e2.setCategoria(null); // No debe cambiar
        System.out.println("Equipo después de intentar setear valores inválidos: " + e2);

        // 4. Prueba de método actualizar con objeto
        Equipo e3 = new Equipo("Tablet", "Computación", true);
        e2.actualizar(e3);
        System.out.println("Equipo después de actualizar con otro equipo: " + e2);

        System.out.println("--- Fin del Test de Equipo ---");
    }
}
