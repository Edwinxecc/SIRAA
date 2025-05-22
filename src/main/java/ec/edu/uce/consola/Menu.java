package ec.edu.uce.consola;

import java.util.Scanner;

public class Menu {
    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";
    private String currentRole;

    public void menuDeInicio() {
        String usr, password;
        System.out.println("\n=============================");
        System.out.println("Bienvenidos al Sistema SIRAA");
        System.out.println("=============================\n");
        Scanner entrada = new Scanner(System.in);
        
        int intentos = 0;
        final int MAX_INTENTOS = 3;
        
        while (intentos < MAX_INTENTOS) {
            System.out.println("Ingresa tus credenciales");
            System.out.print("Ingresa tu correo [@uce.edu.ec]: ");
            usr = entrada.nextLine().trim();
            System.out.print("Ingresa tu contraseña: ");
            password = entrada.nextLine().trim();

            if (!validarCorreo(usr)) {
                System.out.println("Error: El correo debe ser un correo institucional (@uce.edu.ec)");
                intentos++;
                continue;
            }

            // Simular verificación de credenciales (en producción esto debería validar contra una base de datos)
            if (verificarCredenciales(usr, password)) {
                determinarRol(usr);
                menuElegirOpcion();
                return;
            } else {
                System.out.println("Credenciales incorrectas. Intentos restantes: " + (MAX_INTENTOS - ++intentos) + "\n");
            }
        }
        
        if (intentos >= MAX_INTENTOS) {
            System.out.println("Número máximo de intentos alcanzado. Por favor, intente más tarde.");
            System.exit(0);
        }
    }

    private boolean verificarCredenciales(String correo, String password) {
        // TODO: Implementar verificación real contra base de datos
        // Por ahora, simulamos que admin@uce.edu.ec/admin123 es administrador
        // y usuario@uce.edu.ec/user123 es usuario regular
        return (correo.equals("admin@uce.edu.ec") && password.equals("admin123")) ||
               (correo.equals("usuario@uce.edu.ec") && password.equals("user123"));
    }

    private void determinarRol(String correo) {
        currentRole = correo.startsWith("admin") ? ADMIN_ROLE : USER_ROLE;
    }

    public boolean validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        int atIndex = correo.indexOf('@');
        if (atIndex != -1) {
            String dominio = correo.substring(atIndex);
            return dominio.equalsIgnoreCase("@uce.edu.ec");
        }
        return false;
    }

    public void menuElegirOpcion() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        SubMenu subMenuObj = new SubMenu();

        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("[1] Gestionar Usuario");
            System.out.println("[2] Gestionar Reservas");
            
            if (ADMIN_ROLE.equals(currentRole)) {
                System.out.println("[3] Gestionar Facultades (A)");
                System.out.println("[4] Gestionar Equipos (A)");
                System.out.println("[5] Recuperar Credenciales (A)");
            }
            
            System.out.println("[0] Salir");
            System.out.print(">: ");
            
            try {
                opcion = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    subMenuObj.menuGestionarUsuario();
                    break;
                case 2:
                    subMenuObj.menuGestionarReserva();
                    break;
                case 3:
                    if (ADMIN_ROLE.equals(currentRole)) {
                        subMenuObj.menuGestionarFacultades();
                    } else {
                        System.out.println("Acceso denegado. Se requieren privilegios de administrador.");
                    }
                    break;
                case 4:
                    if (ADMIN_ROLE.equals(currentRole)) {
                        subMenuObj.menuAdministrarEquipos();
                    } else {
                        System.out.println("Acceso denegado. Se requieren privilegios de administrador.");
                    }
                    break;
                case 5:
                    if (ADMIN_ROLE.equals(currentRole)) {
                        subMenuObj.menuRecuperarCredenciales();
                    } else {
                        System.out.println("Acceso denegado. Se requieren privilegios de administrador.");
                    }
                    break;
                case 0:
                    System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente nuevamente.");
            }
        }
    }
}