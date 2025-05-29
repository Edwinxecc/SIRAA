/**
 * Clase coordinadora que maneja la navegación entre los diferentes menús del sistema.
 */
package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;

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
        this.menuEquipo = new MenuEquipo(usuarioActual.getReservas().length > 0 ? usuarioActual.getReservas()[0] : null);
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
        if (menuEquipo.getReservaActual() == null) {
            System.out.println("[!] Debe crear una reserva antes de administrar equipos.");
            return;
        }
        menuEquipo.mostrarMenu();
    }

    public void menuRecuperarCredenciales() {
        menuCredenciales.mostrarMenu();
    }
}