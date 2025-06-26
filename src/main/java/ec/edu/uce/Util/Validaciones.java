package ec.edu.uce.Util;

import java.util.Scanner;

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
        if (correo == null || correo.trim().isEmpty()) {
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
}
