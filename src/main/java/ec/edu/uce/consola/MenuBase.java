package ec.edu.uce.consola;

import ec.edu.uce.Util.Validaciones;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public abstract class MenuBase {
    protected final Validaciones validacion;
    protected static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    protected final Scanner entrada;

    public MenuBase() {
        this.validacion = new Validaciones();
        this.entrada = new Scanner(System.in);
    }

    protected int leerEnteroPositivo() {
        int numero;
        while (true) {
            while (!entrada.hasNextInt()) {
                System.out.print("[!] Entrada inválida. Ingrese un número: ");
                entrada.next();
            }
            numero = entrada.nextInt();
            entrada.nextLine();

            if (numero < 0) {
                System.out.print("[!] El número no puede ser negativo. Intente de nuevo: ");
            } else {
                break;
            }
        }
        return numero;
    }

    protected boolean leerBooleano() {
        while (true) {
            String valor = entrada.nextLine().trim().toLowerCase();
            if (valor.equals("true") || valor.equals("sí") || valor.equals("si")) return true;
            if (valor.equals("false") || valor.equals("no")) return false;
            System.out.print("[!] Ingrese 'true' o 'false' (o 'sí' / 'no'): ");
        }
    }

    protected LocalDateTime leerFecha() {
        try {
            String fechaStr = entrada.nextLine().trim();
            return LocalDateTime.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("[!] Formato de fecha inválido. Use dd/MM/yyyy HH:mm");
            return null;
        }
    }
} 