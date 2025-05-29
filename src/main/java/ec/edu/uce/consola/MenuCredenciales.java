package ec.edu.uce.consola;

public class MenuCredenciales extends MenuBase {
    
    public MenuCredenciales() {
        super();
    }

    public void mostrarMenu() {
        System.out.println("\n[1] Recuperar contraseña de administrador");
        System.out.print("Ingresa tu correo institucional: ");
        String correo = entrada.nextLine();

        if (correo.endsWith("@uce.edu.ec")) {
            System.out.println("Enlace de recuperación enviado a " + correo);
        } else {
            System.out.println("Correo no válido. Intenta de nuevo.");
            mostrarMenu();
        }
    }
} 