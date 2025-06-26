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
        nombre = validacion.ValidacionTexto(nombre, "nombre");

        System.out.printf("%-30s", "Apellido del usuario: ");
        String apellido = entrada.nextLine();
        apellido = validacion.ValidacionTexto(apellido, "apellido");

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
        System.out.printf("%n%s%n", "─".repeat(40));
        System.out.printf("%-25s%n", "🔍 [2] Consultar Usuario");
        Usuario[] usuarios = facultad.getUsuarios();
        if (usuarios.length == 0) {
            System.out.printf("%-25s%n", "❌ No hay ningún usuario creado");
            return;
        }
        
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "LISTA DE USUARIOS");
        System.out.printf("%s%n", "=".repeat(50));
        for (int i = 0; i < usuarios.length; i++) {
            Usuario usuario = usuarios[i];
            System.out.printf("%-5s%s%n", "[" + i + "]", usuario);
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
                System.out.printf("%n%s%n", "─".repeat(40));
                System.out.printf("%-25s%n", "✅ Usuario encontrado:");
                System.out.printf("%s%n", usuarioEncontrado);
            } else {
                System.out.printf("%-25s%d%n", "❌ No se encontró un usuario con el ID: ", id);
            }
        }
    }

    private void editarUsuario() {
        System.out.println("\n[3] Editar Usuario");
        Usuario[] usuarios = facultad.getUsuarios();
        if (usuarios.length == 0) {
            System.out.println("[!] No hay ningún usuario creado.");
            return;
        }
        
        System.out.println("=== LISTA DE USUARIOS ===");
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println("[" + i + "] " + usuarios[i]);
        }

        System.out.print("Ingrese el índice del usuario a editar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= usuarios.length) {
            System.out.println("[!] Índice inválido.");
            return;
        }

        Usuario usuarioAEditar = usuarios[indice];
        System.out.println("\nEditando usuario: " + usuarioAEditar);

        System.out.print("Nuevo nombre (actual: " + usuarioAEditar.getNombre() + "): ");
        String nuevoNombre = entrada.nextLine();
        if (!nuevoNombre.trim().isEmpty()) {
            nuevoNombre = validacion.ValidacionTexto(nuevoNombre, "nombre");
        } else {
            nuevoNombre = usuarioAEditar.getNombre();
        }

        System.out.print("Nuevo apellido (actual: " + usuarioAEditar.getApellido() + "): ");
        String nuevoApellido = entrada.nextLine();
        if (!nuevoApellido.trim().isEmpty()) {
            nuevoApellido = validacion.ValidacionTexto(nuevoApellido, "apellido");
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

        // Crear usuario actualizado
        Usuario usuarioActualizado = new Usuario(nuevoNombre, nuevoApellido, nuevoCorreo);
        usuarioActualizado.setEstado(usuarioAEditar.getEstado());
        
        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = usuarioAEditar.editar(usuarioActualizado);
        System.out.println(resultado);
        
        // Actualizar en la facultad
        facultad.actualizarUsuario(indice, usuarioActualizado);
        System.out.println("[✓] Usuario actualizado correctamente.");
    }

    private void eliminarUsuario() {
        System.out.println("\n[4] Eliminar Usuario");
        Usuario[] usuarios = facultad.getUsuarios();
        if (usuarios.length == 0) {
            System.out.println("[!] No hay ningún usuario creado.");
            return;
        }
        
        System.out.println("=== LISTA DE USUARIOS ===");
        for (int i = 0; i < usuarios.length; i++) {
            System.out.println("[" + i + "] " + usuarios[i]);
        }

        System.out.print("Ingrese el índice del usuario a eliminar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= usuarios.length) {
            System.out.println("[!] Índice inválido.");
            return;
        }

        Usuario usuarioAEliminar = usuarios[indice];
        System.out.println("¿Está seguro de eliminar el usuario: " + usuarioAEliminar + "? (s/n): ");
        String confirmacion = entrada.nextLine().toLowerCase();
        
        if (confirmacion.equals("s") || confirmacion.equals("si") || confirmacion.equals("sí")) {
            // Usar el método de la interfaz IAdministrarCRUD
            String resultado = usuarioAEliminar.borrar(usuarioAEliminar);
            System.out.println(resultado);
            
            // Eliminar de la facultad
            facultad.eliminarUsuario(indice);
            System.out.println("[✓] Usuario eliminado correctamente.");
        } else {
            System.out.println("[!] Operación cancelada.");
        }
    }
}