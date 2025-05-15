package ec.edu.uce.Util;

import java.util.Scanner;

public class Validaciones {
    public Validaciones(){

    }

    public String ValidacionTexto(String textoInicial, String campo) {
        Scanner entrada = new Scanner(System.in);
        String texto = textoInicial;

        while (texto.matches(".*\\d.*")) {
            System.out.println("El " + campo + " no debe contener números. Inténtalo de nuevo:");
            texto = entrada.next();
        }

        return texto;
    }

}
