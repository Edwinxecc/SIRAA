package ec.edu.uce.dominio;

public class TestAuditorio {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Auditorio ---");

        // 1. Instanciación con constructor por defecto
        Auditorio a1 = new Auditorio();
        System.out.println("Auditorio por defecto: " + a1);

        // 2. Instanciación con constructor completo
        Auditorio a2 = new Auditorio("Auditorio Principal", 150);
        System.out.println("Auditorio con datos: " + a2);

        // 3. Prueba de setters con valores válidos e inválidos
        a2.setNombre("Auditorio Modificado");
        a2.setCapacidad(200);
        System.out.println("Auditorio modificado: " + a2);

        a2.setNombre(null); // No debe cambiar
        a2.setCapacidad(-10); // No debe cambiar
        System.out.println("Auditorio después de intentar setear valores inválidos: " + a2);

        System.out.println("--- Fin del Test de Auditorio ---");
    }
}
