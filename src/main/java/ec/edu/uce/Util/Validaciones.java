package ec.edu.uce.Util;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Clase de utilidades para validaciones del sistema SIRAA.
 * Implementa el patrón Singleton para asegurar una única instancia.
 */
public class Validaciones {
    public Validaciones(){

    }

    /**
     * Valida que un texto no contenga números.
     * @param textoInicial Texto a validar
     * @param campo Nombre del campo para mensajes de error
     * @return Texto validado sin números
     */
    public String ValidacionTexto(String textoInicial, String campo) {
        assert textoInicial != null : "El texto inicial no puede ser nulo";
        assert campo != null : "El campo no puede ser nulo";
        
        Scanner entrada = new Scanner(System.in);
        String texto = textoInicial;

        while (texto.matches(".*\\d.*")) {
            System.out.println("El " + campo + " no debe contener números. Inténtalo de nuevo:");
            texto = entrada.next();
        }

        return texto;
    }

    /**
     * Valida que un correo sea institucional (@uce.edu.ec).
     * @param correo Correo a validar
     * @return true si el correo es válido, false en caso contrario
     */
    public boolean validarCorreoInstitucional(String correo) {
        assert correo != null : "El correo no puede ser nulo";
        
        if (correo.trim().isEmpty()) {
            return false;
        }
        return correo.endsWith("@uce.edu.ec");
    }

    /**
     * Valida que un número esté en un rango específico.
     * @param numero Número a validar
     * @param min Valor mínimo (inclusive)
     * @param max Valor máximo (inclusive)
     * @return true si el número está en el rango, false en caso contrario
     */
    public boolean validarRango(int numero, int min, int max) {
        return numero >= min && numero <= max;
    }

    /**
     * Valida que una cadena no esté vacía o sea null.
     * @param texto Texto a validar
     * @return true si el texto es válido, false en caso contrario
     */
    public boolean validarTextoNoVacio(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    /**
     * Valida si el texto cumple con el patrón regex proporcionado.
     * @param texto Texto a validar
     * @param regex Expresión regular
     * @return true si el texto cumple con el patrón, false en caso contrario
     */
    public boolean pattern(String texto, String regex) {
        if (texto == null) return false;
        return Pattern.matches(regex, texto);
    }

    /**
     * Solicita al usuario una entrada que cumpla con el patrón regex proporcionado.
     * @param textoInicial Texto inicial a validar
     * @param campo Nombre del campo para mensajes de error
     * @param regex Expresión regular que debe cumplir la entrada
     * @param mensajeError Mensaje de error a mostrar si no cumple
     * @param entrada Scanner para leer la entrada del usuario
     * @return Texto validado que cumple con el patrón
     */
    public String match(String textoInicial, String campo, String regex, String mensajeError, Scanner entrada) {
        String texto = textoInicial;
        while (!Pattern.matches(regex, texto)) {
            System.out.println(mensajeError != null ? mensajeError : ("El " + campo + " no cumple con el formato requerido. Inténtalo de nuevo:"));
            texto = entrada.nextLine();
        }
        return texto;
    }
}