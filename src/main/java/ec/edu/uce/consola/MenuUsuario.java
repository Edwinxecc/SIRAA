package ec.edu.uce.consola;

import java.util.ArrayList;
import java.util.List;

import ec.edu.uce.dominio.*;

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
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ GESTIONAR USUARIO");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Crear Usuario");
            System.out.printf("%-5s%-30s%n", "[2]", "Consultar Usuario");
            System.out.printf("%-5s%-30s%n", "[3]", "Editar Usuario");
            System.out.printf("%-5s%-30s%n", "[4]", "Eliminar Usuario");
            System.out.printf("%-5s%-30s%n", "[0]", "Volver al menú principal");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearUsuario();
                case 2 -> consultarUsuario();
                case 3 -> editarUsuario();
                case 4 -> eliminarUsuario();
                case 0 -> System.out.printf("%-25s%n", "🔄 Volviendo al menú principal...");
                default -> System.out.printf("%-25s%n", "❌ Opción no válida");
            }
        } while (opcion != 0);
    }

    private void crearUsuario() {
        System.out.printf("%n%s%n", "─".repeat(40));
        System.out.printf("%-25s%n", "📝 [1] Crear Usuario");

        System.out.printf("%-30s", "Nombre del usuario: ");
        String nombre = entrada.nextLine();
        nombre = validacion.match(nombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);

        System.out.printf("%-30s", "Apellido del usuario: ");
        String apellido = entrada.nextLine();
        apellido = validacion.match(apellido, "apellido", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El apellido solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);

        System.out.printf("%-30s", "Correo del usuario: ");
        String correo = entrada.nextLine();
        while (!correo.endsWith("@uce.edu.ec")) {
            System.out.printf("%-25s%n", "❌ El correo debe ser institucional (@uce.edu.ec)");
            System.out.printf("%-30s", "Correo del usuario: ");
            correo = entrada.nextLine();
        }

        // Crear usuario
        Usuario nuevoUsuario = new Usuario(nombre, apellido, correo);
        facultad.crearUsuario(nuevoUsuario);

        // Mostrar información del usuario creado
        System.out.printf("%n%s%n", "─".repeat(40));
        System.out.printf("%-25s%n", "✅ Usuario creado con éxito:");
        System.out.printf("%-15s%-20s%n", "ID:", nuevoUsuario.getIdUsuario());
        System.out.printf("%-15s%-20s%n", "Código:", nuevoUsuario.getCodigoUsuario());
        System.out.printf("%-15s%-20s%n", "Nombre:", nuevoUsuario.getNombre() + " " + nuevoUsuario.getApellido());
        System.out.printf("%-15s%-20s%n", "Correo:", nuevoUsuario.getCorreo());
        System.out.printf("%-15s%-20s%n", "Estado:", nuevoUsuario.getEstado().getDescripcion());
    }

    private void consultarUsuario() {
        System.out.printf("%n%s%n", "=".repeat(40));
        System.out.printf("%-25s%n", "🔍 [2] Consultar Usuario");
        facultad.inicializar();
        List<Usuario> usuarios = new ArrayList<>(facultad.getUsuarios());
        if (usuarios.isEmpty()) {
            System.out.printf("%-25s%n", "❌ No hay ningún usuario creado");
            return;
        }
        System.out.println("¿Por qué criterio desea ordenar los usuarios?");
        System.out.println("[1] ID (orden natural)");
        System.out.println("[2] Nombre");
        System.out.println("[3] Apellido");
        System.out.println("[4] Correo");
        System.out.println("[5] Número de reservas");
        System.out.print(">: ");
        int criterio = leerEnteroPositivo();
        switch (criterio) {
           // case 1 -> usuarios.sort(new OrdenarUsuarioId());
            case 2 -> usuarios.sort(new OrdenarUsuarioNombre());
            case 3 -> usuarios.sort(new OrdenarUsuarioApellido());
            case 4 -> usuarios.sort(new OrdenarUsuarioCorreo());
            case 5 -> usuarios.sort(new OrdenarUsuarioNumReservas());
            default -> java.util.Collections.sort(usuarios);
        }
        System.out.println("=== LISTA DE USUARIOS ORDENADA ===");
        for (Usuario usuario : usuarios) {
            System.out.printf("Código: %s | %s%n", usuario.getCodigoUsuario(), usuario);
        }
        // Opción para buscar por ID
        System.out.printf("%n%-50s", "¿Desea buscar un usuario por ID? (s/n): ");
        String respuesta = entrada.nextLine().toLowerCase();
        if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
            System.out.printf("%-30s", "ID del usuario: ");
            int id = leerEnteroPositivo();
            Usuario usuarioEncontrado = null;
            for (Usuario usuario : usuarios) {
                if (usuario.getIdUsuario() == id) {
                    usuarioEncontrado = usuario;
                    break;
                }
            }
            if (usuarioEncontrado != null) {
                System.out.printf("%n%s%n", "=".repeat(40));
                System.out.printf("%-25s%n", "✅ Usuario encontrado:");
                System.out.printf("%s%n", usuarioEncontrado);
            } else {
                System.out.printf("%-25s%d%n", "❌ No se encontró un usuario con el ID: ", id);
            }
        }
    }

    private void editarUsuario() {
        System.out.println("\n[3] Editar Usuario");
        List<Usuario> usuarios = new ArrayList<>(facultad.getUsuarios());
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay ningún usuario creado.");
            return;
        }
        System.out.println("=== LISTA DE USUARIOS ===");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] Código: " + usuarios.get(i).getCodigoUsuario() + " | " + usuarios.get(i));
        }
        System.out.print("Ingrese el índice del usuario a editar: ");
        int indice = leerEnteroPositivo();
        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("[!] Índice inválido.");
            return;
        }
        Usuario usuarioAEditar = usuarios.get(indice);
        System.out.println("\nEditando usuario: " + usuarioAEditar);
        System.out.print("Nuevo nombre (actual: " + usuarioAEditar.getNombre() + "): ");
        String nuevoNombre = entrada.nextLine();
        if (!nuevoNombre.trim().isEmpty()) {
            nuevoNombre = validacion.match(nuevoNombre, "nombre", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El nombre solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);
        } else {
            nuevoNombre = usuarioAEditar.getNombre();
        }
        System.out.print("Nuevo apellido (actual: " + usuarioAEditar.getApellido() + "): ");
        String nuevoApellido = entrada.nextLine();
        if (!nuevoApellido.trim().isEmpty()) {
            nuevoApellido = validacion.match(nuevoApellido, "apellido", "^[A-Za-zÁÉÍÓÚáéíóúÑñ ]{2,}$", "El apellido solo debe contener letras y espacios (mínimo 2 caracteres). Inténtalo de nuevo:", entrada);
        } else {
            nuevoApellido = usuarioAEditar.getApellido();
        }
        System.out.print("Nuevo correo (actual: " + usuarioAEditar.getCorreo() + "): ");
        String nuevoCorreo = entrada.nextLine();
        if (!nuevoCorreo.trim().isEmpty()) {
            while (!nuevoCorreo.endsWith("@uce.edu.ec")) {
                System.out.println("[!] El correo debe ser institucional (@uce.edu.ec)");
                System.out.print("Ingresa el nuevo correo: ");
                nuevoCorreo = entrada.nextLine();
            }
        } else {
            nuevoCorreo = usuarioAEditar.getCorreo();
        }
        Usuario usuarioActualizado = new Usuario(nuevoNombre, nuevoApellido, nuevoCorreo);
        usuarioActualizado.setEstado(usuarioAEditar.getEstado());
        String resultado = usuarioAEditar.editar(usuarioActualizado);
        System.out.println(resultado);
        facultad.actualizarUsuario(usuarioAEditar.getCodigoUsuario(), usuarioActualizado);
        System.out.println("[✓] Usuario actualizado correctamente.");
        System.out.println("Información actualizada: " + usuarioActualizado);
    }

    private void eliminarUsuario() {
        System.out.println("\n[4] Eliminar Usuario");
        List<Usuario> usuarios = new ArrayList<>(facultad.getUsuarios());
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay ningún usuario creado.");
            return;
        }
        System.out.println("=== LISTA DE USUARIOS ===");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.println("[" + i + "] Código: " + usuarios.get(i).getCodigoUsuario() + " | " + usuarios.get(i));
        }
        System.out.print("Ingrese el índice del usuario a eliminar: ");
        int indice = leerEnteroPositivo();
        if (indice < 0 || indice >= usuarios.size()) {
            System.out.println("[!] Índice inválido.");
            return;
        }
        Usuario usuarioAEliminar = usuarios.get(indice);
        System.out.println("¿Está seguro de eliminar el usuario: " + usuarioAEliminar + "? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            String resultado = usuarioAEliminar.borrar(usuarioAEliminar);
            System.out.println(resultado);
            facultad.eliminarUsuario(usuarioAEliminar.getCodigoUsuario());
            System.out.println("[✓] Usuario eliminado correctamente.");
        } else {
            System.out.println("[!] Operación cancelada.");
        }
    }
}