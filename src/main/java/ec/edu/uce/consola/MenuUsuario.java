package ec.edu.uce.consola;

import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;

public class MenuUsuario extends MenuBase {
    private final Facultad facultad;
    private final Usuario usuarioActual;

    public MenuUsuario(Facultad facultad, Usuario usuarioActual) {
        super();
        this.facultad = facultad;
        this.usuarioActual = usuarioActual;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("\n=== MENÚ GESTIONAR USUARIO ===");
            System.out.println("[1] Crear Usuario");
            System.out.println("[2] Consultar Usuario");
            System.out.println("[3] Editar Usuario");
            System.out.println("[4] Eliminar Usuario");
            System.out.println("[0] Volver al menú principal");
            System.out.print(">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearUsuario();
                case 2 -> consultarUsuario();
                case 3 -> editarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("[!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void crearUsuario() {
        System.out.println("\n[1] Crear Usuario");
        
        System.out.print("Ingresa el nombre del usuario: ");
        String nombre = entrada.nextLine();
        nombre = validacion.ValidacionTexto(nombre, "nombre");

        System.out.print("Ingresa el apellido del usuario: ");
        String apellido = entrada.nextLine();
        apellido = validacion.ValidacionTexto(apellido, "apellido");

        System.out.print("Ingresa el correo del usuario: ");
        String correo = entrada.nextLine();
        while (!correo.endsWith("@uce.edu.ec")) {
            System.out.println("[!] El correo debe ser institucional (@uce.edu.ec)");
            System.out.print("Ingresa el correo del usuario: ");
            correo = entrada.nextLine();
        }

        facultad.crearUsuario(nombre, apellido, correo);
        System.out.println("\n[✓] Usuario creado con éxito");
    }

    private void consultarUsuario() {
        System.out.println("\n[2] Consultar Usuario");
        System.out.println(facultad.listarUsuarios());
    }

    private void editarUsuario() {
        System.out.println("\n[3] Editar Usuario");
        System.out.println(facultad.listarUsuarios());
        
        System.out.print("Ingrese el índice del usuario a editar: ");
        int indice = leerEnteroPositivo() - 1;

        System.out.print("Nuevo nombre: ");
        String nuevoNombre = entrada.nextLine();
        nuevoNombre = validacion.ValidacionTexto(nuevoNombre, "nombre");

        System.out.print("Nuevo apellido: ");
        String nuevoApellido = entrada.nextLine();
        nuevoApellido = validacion.ValidacionTexto(nuevoApellido, "apellido");

        System.out.print("Nuevo correo: ");
        String nuevoCorreo = entrada.nextLine();
        while (!nuevoCorreo.endsWith("@uce.edu.ec")) {
            System.out.println("[!] El correo debe ser institucional (@uce.edu.ec)");
            System.out.print("Ingresa el nuevo correo: ");
            nuevoCorreo = entrada.nextLine();
        }

        facultad.actualizarUsuario(indice, nuevoNombre, nuevoApellido, nuevoCorreo);
        System.out.println("Usuario actualizado.");
    }

    private void eliminarUsuario() {
        System.out.println("\n[4] Eliminar Usuario");
        System.out.println(facultad.listarUsuarios());
        
        System.out.print("Ingrese el índice del usuario a eliminar: ");
        int indice = leerEnteroPositivo() - 1;
        
        facultad.eliminarUsuario(indice);
        System.out.println("Usuario eliminado.");
    }
} 