package ec.edu.uce.consola;

public class MenuCredenciales extends MenuBase {

    public MenuCredenciales() {
        super();
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ RECUPERAR CREDENCIALES");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Recuperar contraseña");
            System.out.printf("%-5s%-30s%n", "[2]", "Recuperar usuario");
            System.out.printf("%-5s%-30s%n", "[3]", "Ver información de ayuda");
            System.out.printf("%-5s%-30s%n", "[0]", "Volver al menú principal");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> recuperarContrasena();
                case 2 -> recuperarUsuario();
                case 3 -> mostrarAyuda();
                case 0 -> System.out.printf("%-25s%n", "🔄 Volviendo al menú principal...");
                default -> System.out.printf("%-25s%n", "❌ Opción no válida");
            }
        } while (opcion != 0);
    }

    private void recuperarContrasena() {
        System.out.println("\n[1] Recuperar Contraseña");
        System.out.print("Ingresa tu correo institucional (@uce.edu.ec): ");
        String correo = entrada.nextLine();

        if (validarCorreoInstitucional(correo)) {
            System.out.println("[✓] Enlace de recuperación enviado a " + correo);
            System.out.println("[!] Revisa tu bandeja de entrada y sigue las instrucciones.");
        } else {
            System.out.println("[!] Correo no válido. Debe ser un correo institucional (@uce.edu.ec)");
        }
    }

    private void recuperarUsuario() {
        System.out.println("\n[2] Recuperar Usuario");
        System.out.print("Ingresa tu correo institucional (@uce.edu.ec): ");
        String correo = entrada.nextLine();

        if (validarCorreoInstitucional(correo)) {
            System.out.println("[✓] Información de usuario enviada a " + correo);
            System.out.println("[!] Revisa tu bandeja de entrada para obtener tu nombre de usuario.");
        } else {
            System.out.println("[!] Correo no válido. Debe ser un correo institucional (@uce.edu.ec)");
        }
    }

    private void mostrarAyuda() {
        System.out.println("\n[3] Información de Ayuda");
        System.out.println("=== AYUDA PARA RECUPERAR CREDENCIALES ===");
        System.out.println("• Solo se pueden recuperar credenciales de correos institucionales (@uce.edu.ec)");
        System.out.println("• El proceso de recuperación puede tomar hasta 24 horas");
        System.out.println("• Si no recibes el correo, verifica tu carpeta de spam");
        System.out.println("• Para asistencia adicional, contacta al administrador del sistema");
        System.out.println("• Credenciales de prueba disponibles:");
        System.out.println("  - Admin: admin@uce.edu.ec / admin123");
        System.out.println("  - Usuario: usuario@uce.edu.ec / user123");
    }

    private boolean validarCorreoInstitucional(String correo) {
        return correo != null && correo.trim().endsWith("@uce.edu.ec");
    }
} 