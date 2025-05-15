package ec.edu.uce.consola;

import java.util.Scanner;

public class MenuPrincipal {
    public void MenuDeIncio(){
        String usr, password;
        System.out.println("Bienvenidos al Sistema SIRAA");
        System.out.println("Ingresa tus credenciales");
        Scanner entrada = new Scanner(System.in);
        System.out.println("Ingresa tu correo [@uce.edu.ec]: ");
        usr = entrada.nextLine();
    }
}
