/**
 * Clase principal del menú del sistema SIRAA.
 * Maneja la autenticación de usuarios y el menú principal del sistema.
 */
package ec.edu.uce.consola;

import java.util.Scanner;
import ec.edu.uce.dominio.*;

public class Menu {
    /**
     * Rol de administrador
     */
    private static final String ADMIN_ROLE = "ADMIN";
    /**
     * Rol de usuario regular
     */
    private static final String USER_ROLE = "USER";
    /**
     * Rol actual del usuario autenticado
     */
    private String currentRole;
    /**
     * Usuario actual
     */
    private Usuario usuarioActual;
    /**
     * Facultad actual
     */
    private Facultad facultadActual;

    public Menu(Facultad facultadActual) {
        this.facultadActual = facultadActual;
    }

    /**
     * Muestra el menú de inicio y maneja la autenticación.
     * Permite hasta 3 intentos de inicio de sesión.
     */
    public void menuDeInicio() {
        String usr, password;
        System.out.printf("%n%s%n", "=".repeat(60));
        System.out.printf("%-20s%s%n", "", "Bienvenidos al Sistema SIRAA");
        System.out.printf("%s%n%n", "=".repeat(60));
        Scanner entrada = new Scanner(System.in);

        int intentos = 0;
        final int MAX_INTENTOS = 3;

        while (intentos < MAX_INTENTOS) {
            System.out.printf("%s%n", "─".repeat(40));
            System.out.printf("%-25s%n", "Ingresa tus credenciales");
            System.out.printf("%-25s", "Correo [@uce.edu.ec]: ");
            usr = entrada.nextLine().trim();
            System.out.printf("%-25s", "Contraseña: ");
            password = entrada.nextLine().trim();

            if (!validarCorreo(usr)) {
                System.out.printf("%s%n", "─".repeat(40));
                System.out.printf("%-25s%n", "❌ Error: El correo debe ser institucional (@uce.edu.ec)");
                intentos++;
                continue;
            }

            if (verificarCredenciales(usr, password)) {
                determinarRol(usr);
                // Crear usuario actual
                usuarioActual = new Usuario("Usuario", "Test", usr);
                System.out.printf("%s%n", "─".repeat(40));
                System.out.printf("%-25s%n", "✅ Acceso concedido");
                menuElegirOpcion();
                return;
            } else {
                System.out.printf("%s%n", "─".repeat(40));
                System.out.printf("%-25s%n", "❌ Credenciales incorrectas");
                System.out.printf("%-25s%d%n", "Intentos restantes: ", MAX_INTENTOS - ++intentos);
            }
        }

        if (intentos >= MAX_INTENTOS) {
            System.out.printf("%s%n", "─".repeat(40));
            System.out.printf("%-25s%n", "❌ Número máximo de intentos alcanzado");
            System.out.printf("%-25s%n", "Por favor, intente más tarde");
            System.exit(0);
        }
    }

    /**
     * Verifica las credenciales del usuario.
     *
     * @param correo   Correo del usuario
     * @param password Contraseña del usuario
     * @return true si las credenciales son válidas, false en caso contrario
     */
    private boolean verificarCredenciales(String correo, String password) {
        return (correo.equals("admin@uce.edu.ec") && password.equals("admin123")) ||
                (correo.equals("usuario@uce.edu.ec") && password.equals("user123"));
    }

    /**
     * Determina el rol del usuario basado en su correo.
     *
     * @param correo Correo del usuario
     */
    private void determinarRol(String correo) {
        currentRole = correo.startsWith("admin") ? ADMIN_ROLE : USER_ROLE;
    }

    /**
     * Valida que el correo sea institucional.
     *
     * @param correo Correo a validar
     * @return true si el correo es válido, false en caso contrario
     */
    public boolean validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return correo.endsWith("@uce.edu.ec");
    }

    /**
     * Muestra el menú principal y maneja las opciones según el rol del usuario.
     */
    public void menuElegirOpcion() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        SubMenu subMenuObj = new SubMenu(facultadActual, usuarioActual);

        while (true) {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ PRINCIPAL");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Gestionar Usuario");
            System.out.printf("%-5s%-30s%n", "[2]", "Gestionar Reservas");

            if (ADMIN_ROLE.equals(currentRole)) {
                System.out.printf("%-5s%-30s%n", "[3]", "Gestionar Facultades (A)");
                System.out.printf("%-5s%-30s%n", "[4]", "Gestionar Equipos (A)");
                System.out.printf("%-5s%-30s%n", "[5]", "Recuperar Credenciales (A)");
            }

            System.out.printf("%-5s%-30s%n", "[0]", "Salir");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            try {
                opcion = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.printf("%-25s%n", "❌ Por favor, ingresa un número válido");
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
                        System.out.printf("%-25s%n", "❌ Acceso denegado. Se requieren privilegios de administrador");
                    }
                    break;
                case 4:
                    if (ADMIN_ROLE.equals(currentRole)) {
                        subMenuObj.menuAdministrarEquipos();
                    } else {
                        System.out.printf("%-25s%n", "❌ Acceso denegado. Se requieren privilegios de administrador");
                    }
                    break;
                case 5:
                    if (ADMIN_ROLE.equals(currentRole)) {
                        subMenuObj.menuRecuperarCredenciales();
                    } else {
                        System.out.printf("%-25s%n", "❌ Acceso denegado. Se requieren privilegios de administrador");
                    }
                    break;
                case 0:
                    System.out.printf("%-25s%n", "👋 Gracias por usar el sistema. ¡Hasta pronto!");
                    return;
                default:
                    System.out.printf("%-25s%n", "❌ Opción no válida. Por favor, intente nuevamente");
            }
        }
    }
}