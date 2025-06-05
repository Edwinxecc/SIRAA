package ec.edu.uce.consola;

import ec.edu.uce.dominio.Equipo;
import ec.edu.uce.dominio.Reserva;

public class MenuEquipo extends MenuBase {
    private final Reserva reservaActual;

    public MenuEquipo(Reserva reservaActual) {
        super();
        this.reservaActual = reservaActual;
    }

    public Reserva getReservaActual() {
        return reservaActual;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n--- MENÚ ADMINISTRAR EQUIPOS ---");
            System.out.println("[1] Agregar equipo");
            System.out.println("[2] Consultar equipo");
            System.out.println("[3] Eliminar equipo");
            System.out.println("[0] Volver al menú anterior");
            System.out.print(">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> agregarEquipo();
                case 2 -> consultarEquipos();
                case 3 -> eliminarEquipo();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("[!] Opción inválida.");
            }
        } while (opcion != 0);
    }

    private void agregarEquipo() {
        System.out.println("\n[1] Agregar Equipo");

        System.out.print("Ingrese nombre del equipo: ");
        String nombre = entrada.nextLine();
        nombre = validacion.ValidacionTexto(nombre, "nombre");

        System.out.print("Ingrese tipo de equipo: ");
        String categoria = entrada.nextLine();
        categoria = validacion.ValidacionTexto(categoria, "categoría");

        System.out.print("¿Está disponible? (true/false): ");
        boolean disponible = leerBooleano();

        reservaActual.crearEquipo(nombre, categoria, disponible);
        System.out.println("[✓] Equipo agregado exitosamente.");
    }

    private void consultarEquipos() {
        System.out.println("\n[2] Consultar Equipos");
        System.out.println(reservaActual.listarEquipos());
    }

    private void eliminarEquipo() {
        System.out.println("\n[3] Eliminar Equipo");
        System.out.println(reservaActual.listarEquipos());
        
        System.out.print("\nSeleccione el índice del equipo a eliminar: ");
        int indice = leerEnteroPositivo() - 1;
        
        String resultado = reservaActual.eliminarEquipo(indice);
        System.out.println(resultado);
    }
} 