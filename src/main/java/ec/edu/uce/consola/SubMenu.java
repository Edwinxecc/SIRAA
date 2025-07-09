/**
 * Clase coordinadora que maneja la navegación entre los diferentes menús del sistema.
 */
package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Reserva;

public class SubMenu {
    private final MenuUsuario menuUsuario;
    private final MenuReserva menuReserva;
    private final MenuFacultad menuFacultad;
    private final MenuEquipo menuEquipo;
    private final MenuCredenciales menuCredenciales;

    public SubMenu(Facultad facultad, Usuario usuarioActual) {
        this.menuUsuario = new MenuUsuario(facultad, usuarioActual);
        this.menuReserva = new MenuReserva(facultad, usuarioActual);
        this.menuFacultad = new MenuFacultad(facultad);
        // Inicializar con null, se actualizará cuando se cree una reserva
        this.menuEquipo = new MenuEquipo(null);
        this.menuCredenciales = new MenuCredenciales();
    }

    public void menuGestionarUsuario() {
        menuUsuario.mostrarMenu();
    }

    public void menuGestionarReserva() {
        menuReserva.mostrarMenu();
    }

    public void menuGestionarFacultades() {
        menuFacultad.mostrarMenu();
    }

    public void menuAdministrarEquipos() {
        // Verificar si el usuario tiene reservas
        Reserva[] reservas = menuReserva.getUsuarioActual().getReservas();
        if (reservas.length == 0) {
            System.out.printf("%-25s%n", "❌ Debe crear una reserva antes de administrar equipos");
            System.out.printf("%-25s%n", "💡 Vaya a 'Gestionar Reservas' y cree una nueva reserva");
            return;
        }

        // Mostrar reservas disponibles para seleccionar
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "RESERVAS DISPONIBLES");
        System.out.printf("%s%n", "=".repeat(50));
        for (int i = 0; i < reservas.length; i++) {
            System.out.printf("%-5s%s%n", "[" + i + "]", reservas[i]);
        }

        System.out.printf("%n%-50s", "Seleccione el índice de la reserva para administrar equipos: ");
        java.util.Scanner entrada = new java.util.Scanner(System.in);
        int indice = 0;
        try {
            indice = Integer.parseInt(entrada.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.printf("%-25s%n", "❌ Índice inválido");
            return;
        }

        if (indice < 0 || indice >= reservas.length) {
            System.out.printf("%-25s%n", "❌ Índice inválido");
            return;
        }

        // Crear nuevo menú de equipos con la reserva seleccionada
        MenuEquipo menuEquipoActual = new MenuEquipo(reservas[indice]);
        menuEquipoActual.mostrarMenu();
    }

    public void menuRecuperarCredenciales() {
        menuCredenciales.mostrarMenu();
    }
}