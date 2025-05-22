package ec.edu.uce.consola;

import java.util.Scanner;

public class Menu {

    public void menuDeInicio() {
        String usr, password;
        System.out.println("\n=============================");
        System.out.println("Bienvenidos al Sistema SIRAA");
        System.out.println("=============================\n");
        Scanner entrada = new Scanner(System.in);
        while (true) {
            System.out.println("Ingresa tus credenciales");
            System.out.print("Ingresa tu correo [@uce.edu.ec]: ");
            usr = entrada.nextLine();
            System.out.print("Ingresa tu contraseña: ");
            password = entrada.nextLine();

            if (validarCorreo(usr) && password.equals("1234")) {
                menuElegirOpcion();
                break;
            } else {
                System.out.println("Credenciales incorrectas.\n");
            }
        }
    }

    public boolean validarCorreo(String correo) {
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
            System.out.println("\n[1] Gestionar Usuario");
            System.out.println("[2] Gestionar Reservar");
            System.out.println("[3] Gestionar Facultades (A)");
            System.out.println("[4] Gestionar Equipos (A)");
            System.out.println("[5] Recuperar Credenciales (A)");
            System.out.println("[0] Salir");
            System.out.print(">: ");
            if (!entrada.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                entrada.next();
                continue;
            }
            opcion = entrada.nextInt();
            entrada.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    subMenuObj.MenuGestionarUsuario();
                    break;
                case 2:
                    subMenuObj.MenuGestionarReserva();
                    break;
                case 3:
                    subMenuObj.menuGestionarFacultades();
                    break;
                case 4:
                    subMenuObj.menuAdministrarEquipos();
                    break;
                case 5:
                    subMenuObj.menuRecuperarCredenciales();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Ingrese una opción válida");
            }
        }
    }
}