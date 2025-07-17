/**
 * Clase coordinadora que maneja la navegación entre los diferentes menús del sistema.
 */
package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Reserva;

import java.util.List;

public class SubMenu {
    private final MenuUsuario menuUsuario;
    private final MenuReserva menuReserva;
    private final MenuFacultad menuFacultad;

    public SubMenu(Facultad facultad, Usuario usuarioActual) {
        this.menuUsuario = new MenuUsuario(facultad, usuarioActual);
        this.menuReserva = new MenuReserva(facultad, usuarioActual);
        this.menuFacultad = new MenuFacultad(usuarioActual);
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
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "GESTIONAR EQUIPOS");
        System.out.printf("%s%n", "=".repeat(50));
        System.out.println("Esta funcionalidad está disponible para todos los usuarios.");
        System.out.println("Aquí puedes gestionar los equipos de tus reservas.");
        
        // Verificar si el usuario tiene reservas
        List<Reserva> reservas = menuReserva.getUsuarioActual().getReservas();
        if (reservas.isEmpty()) {
            System.out.println("[!] Debe crear una reserva antes de administrar equipos");
            System.out.println("💡 Vaya a 'Gestionar Reservas' y cree una nueva reserva");
            return;
        }

        // Mostrar reservas disponibles para seleccionar
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "RESERVAS DISPONIBLES");
        System.out.printf("%s%n", "=".repeat(50));
        for (int i = 0; i < reservas.size(); i++) {
            System.out.printf("%-5s%s%n", "[" + i + "]", reservas.get(i));
        }
    }

    public void menuRecuperarCredenciales() {
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "RECUPERAR CREDENCIALES");
        System.out.printf("%s%n", "=".repeat(50));
        System.out.println("Esta funcionalidad está disponible para todos los usuarios.");
        System.out.println("Aquí puedes recuperar tus credenciales de acceso.");
        System.out.println("\nPara recuperar credenciales:");
        System.out.println("1. Contacta al administrador del sistema");
        System.out.println("2. Proporciona tu correo institucional");
        System.out.println("3. Verifica tu identidad");
    }
}