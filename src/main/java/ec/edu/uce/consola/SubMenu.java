package ec.edu.uce.consola;

import ec.edu.uce.Util.Validaciones;
import ec.edu.uce.dominio.Reserva;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Equipo;
import ec.edu.uce.dominio.Auditorio;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class SubMenu {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private final Validaciones validacion = new Validaciones();
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public void menuGestionarUsuario() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ GESTIONAR USUARIO ===");
            System.out.println("[1] Crear Usuario");
            System.out.println("[2] Consultar Usuario");
            System.out.println("[3] Editar Usuario");
            System.out.println("[4] Eliminar Usuario");
            System.out.println("[0] Volver al menú principal");
            System.out.print(">: ");

            while (!entrada.hasNextInt()) {
                System.out.println("[!] Entrada inválida. Ingrese un número.");
                entrada.next();
                System.out.print(">: ");
            }
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> crearUsuario(entrada);
                case 2 -> consultarUsuario(entrada);
                case 3 -> editarUsuario(entrada);
                case 4 -> eliminarUsuario(entrada);
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("[!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void crearUsuario(Scanner entrada) {
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

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsuarioId(nuevoUsuario.generarId());
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setApellido(apellido);
        nuevoUsuario.setCorreo(correo);
        
        usuarios.add(nuevoUsuario);
        System.out.println("\n[✓] Usuario creado con éxito:");
        imprimirUsuario(nuevoUsuario);
    }

    private void consultarUsuario(Scanner entrada) {
        System.out.println("\n[2] Consultar Usuario");
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay usuarios registrados.");
            return;
        }

        System.out.print("Ingresa el ID del usuario para consultar: ");
        int idConsulta = leerEnteroPositivo(entrada);

        Usuario usuarioEncontrado = buscarUsuarioPorId(idConsulta);
        if (usuarioEncontrado != null) {
            imprimirUsuario(usuarioEncontrado);
        } else {
            System.out.println("[!] Usuario no encontrado con ese ID.");
        }
    }

    private void editarUsuario(Scanner entrada) {
        System.out.println("\n[3] Editar Usuario");
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay usuarios registrados.");
            return;
        }

        System.out.print("Ingresa el ID del usuario a editar: ");
        int idEditar = leerEnteroPositivo(entrada);

        Usuario usuarioEditar = buscarUsuarioPorId(idEditar);
        if (usuarioEditar != null) {
            System.out.print("Nuevo nombre: ");
            String nuevoNombre = entrada.nextLine();
            nuevoNombre = validacion.ValidacionTexto(nuevoNombre, "nombre");
            usuarioEditar.setNombre(nuevoNombre);

            System.out.print("Nuevo apellido: ");
            String nuevoApellido = entrada.nextLine();
            nuevoApellido = validacion.ValidacionTexto(nuevoApellido, "apellido");
            usuarioEditar.setApellido(nuevoApellido);

            System.out.print("Nuevo correo: ");
            String nuevoCorreo = entrada.nextLine();
            while (!nuevoCorreo.endsWith("@uce.edu.ec")) {
                System.out.println("[!] El correo debe ser institucional (@uce.edu.ec)");
                System.out.print("Ingresa el nuevo correo: ");
                nuevoCorreo = entrada.nextLine();
            }
            usuarioEditar.setCorreo(nuevoCorreo);

            System.out.println("[✓] Usuario editado con éxito:");
            imprimirUsuario(usuarioEditar);
        } else {
            System.out.println("[!] Usuario no encontrado con ese ID.");
        }
    }

    private void eliminarUsuario(Scanner entrada) {
        System.out.println("\n[4] Eliminar Usuario");
        if (usuarios.isEmpty()) {
            System.out.println("[!] No hay usuarios registrados.");
            return;
        }

        System.out.print("Ingresa el ID del usuario a eliminar: ");
        int idEliminar = leerEnteroPositivo(entrada);

        Usuario usuarioEliminar = buscarUsuarioPorId(idEliminar);
        if (usuarioEliminar != null) {
            usuarios.remove(usuarioEliminar);
            System.out.println("[✓] Usuario eliminado correctamente.");
        } else {
            System.out.println("[!] Usuario no encontrado con ese ID.");
        }
    }

    private Usuario buscarUsuarioPorId(int id) {
        return usuarios.stream()
                .filter(u -> u.getUsuarioId() == id)
                .findFirst()
                .orElse(null);
    }

    private void imprimirUsuario(Usuario u) {
        System.out.println("-------------------------------------");
        System.out.println("| ID: " + u.getUsuarioId());
        System.out.println("| Nombre: " + u.getNombre());
        System.out.println("| Apellido: " + u.getApellido());
        System.out.println("| Correo: " + u.getCorreo());
        System.out.println("-------------------------------------");
    }

    public void menuGestionarReserva() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ GESTIÓN DE RESERVAS ---");
            System.out.println("[1] Crear Reserva");
            System.out.println("[2] Ver Reservas");
            System.out.println("[3] Editar Reserva");
            System.out.println("[4] Eliminar Reserva");
            System.out.println("[0] Volver al Menú Principal");
            System.out.print(">: ");

            while (!entrada.hasNextInt()) {
                System.out.println("[!] Entrada inválida. Ingrese un número.");
                entrada.next();
                System.out.print(">: ");
            }

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> crearReserva(entrada);
                case 2 -> mostrarReservas();
                case 3 -> editarReserva(entrada);
                case 4 -> eliminarReserva(entrada);
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("[!] Opción no válida.");
            }

        } while (opcion != 0);
    }

    private void crearReserva(Scanner entrada) {
        try {
            System.out.print("Ingrese ID de la reserva: ");
            int id = leerEnteroPositivo(entrada);

            // Solicitar y validar usuario
            System.out.print("Ingrese ID del usuario: ");
            int idUsuario = leerEnteroPositivo(entrada);
            Usuario usuario = buscarUsuarioPorId(idUsuario);
            if (usuario == null) {
                System.out.println("[!] Usuario no encontrado.");
                return;
            }

            // Solicitar y validar auditorio
            System.out.print("Ingrese ID del auditorio: ");
            int idAuditorio = leerEnteroPositivo(entrada);
            Auditorio auditorio = buscarAuditorioPorId(idAuditorio);
            if (auditorio == null) {
                System.out.println("[!] Auditorio no encontrado.");
                return;
            }

            // Solicitar fechas
            System.out.println("Ingrese fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
            LocalDateTime fechaInicio = leerFecha(entrada);
            if (fechaInicio == null) return;

            System.out.println("Ingrese fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
            LocalDateTime fechaFin = leerFecha(entrada);
            if (fechaFin == null) return;

            // Validar que la fecha de fin sea posterior a la de inicio
            if (fechaFin.isBefore(fechaInicio)) {
                System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
                return;
            }

            // Crear la reserva
            Reserva nueva = new Reserva(id, usuario, auditorio, fechaInicio, fechaFin);
            Reserva.crearReserva(nueva);
            System.out.println("[✓] Reserva creada exitosamente.");

        } catch (IllegalArgumentException e) {
            System.out.println("[!] Error al crear la reserva: " + e.getMessage());
        }
    }

    private void mostrarReservas() {
        List<Reserva> reservas = Reserva.listarReservas();
        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas registradas.");
        } else {
            System.out.println("\n=== LISTA DE RESERVAS ===");
            for (Reserva r : reservas) {
                System.out.println(r);
            }
        }
    }

    private void editarReserva(Scanner entrada) {
        System.out.print("Ingrese el ID de la reserva a editar: ");
        int id = leerEnteroPositivo(entrada);
        
        System.out.println("Ingrese nueva fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaInicio = leerFecha(entrada);
        if (nuevaFechaInicio == null) return;

        System.out.println("Ingrese nueva fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaFin = leerFecha(entrada);
        if (nuevaFechaFin == null) return;

        if (Reserva.actualizarReserva(id, nuevaFechaInicio, nuevaFechaFin)) {
            System.out.println("[✓] Reserva actualizada exitosamente.");
        } else {
            System.out.println("[!] No se encontró la reserva con ese ID.");
        }
    }

    private void eliminarReserva(Scanner entrada) {
        System.out.print("Ingrese el ID de la reserva a eliminar: ");
        int id = leerEnteroPositivo(entrada);
        if (Reserva.eliminarReserva(id)) {
            System.out.println("[✓] Reserva eliminada exitosamente.");
        } else {
            System.out.println("[!] No se encontró la reserva con ese ID.");
        }
    }

    private LocalDateTime leerFecha(Scanner entrada) {
        try {
            String fechaStr = entrada.nextLine().trim();
            return LocalDateTime.parse(fechaStr, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("[!] Formato de fecha inválido. Use dd/MM/yyyy HH:mm");
            return null;
        }
    }

    private Auditorio buscarAuditorioPorId(int id) {
        // TODO: Implementar búsqueda real de auditorios
        // Por ahora retornamos un auditorio de prueba
        return new Auditorio(id, "Auditorio " + id, 100);
    }

    public void menuGestionarFacultades() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n=== MENÚ GESTIONAR FACULTADES ===");
            System.out.println("[1] Crear Facultad");
            System.out.println("[2] Mostrar Facultades");
            System.out.println("[3] Editar Facultad");
            System.out.println("[4] Eliminar Facultad");
            System.out.println("[0] Salir");
            System.out.print("Selecciona una opción: ");

            while (!scanner.hasNextInt()) {
                System.out.println("[!] Entrada inválida. Ingrese un número.");
                scanner.next();
                System.out.print("Selecciona una opción: ");
            }
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre de la facultad: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Cantidad de auditorios: ");
                    int cantidad = leerEnteroPositivo(scanner);

                    try {
                        Facultad nueva = new Facultad(nombre, cantidad);
                        if (Facultad.crearFacultad(nueva)) {
                            System.out.println("[✓] Facultad creada correctamente.");
                        } else {
                            System.out.println("[!] Ya existe una facultad con ese nombre.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("[!] Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    List<Facultad> facultades = Facultad.listarFacultades();
                    if (facultades.isEmpty()) {
                        System.out.println("[!] No hay facultades registradas.");
                    } else {
                        System.out.println("\n=== FACULTADES REGISTRADAS ===");
                        for (Facultad f : facultades) {
                            System.out.println(f);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Nombre actual de la facultad: ");
                    String nombreActual = scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nueva cantidad de auditorios: ");
                    int nuevosAuditorios = leerEnteroPositivo(scanner);

                    try {
                        if (Facultad.editarFacultad(nombreActual, nuevoNombre, nuevosAuditorios)) {
                            System.out.println("[✓] Facultad editada correctamente.");
                        } else {
                            System.out.println("[!] Facultad no encontrada o el nuevo nombre ya existe.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("[!] Error: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.print("Nombre de la facultad a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    if (Facultad.eliminarFacultad(nombreEliminar)) {
                        System.out.println("[✓] Facultad eliminada correctamente.");
                    } else {
                        System.out.println("[!] Facultad no encontrada.");
                    }
                    break;

                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("[!] Opción no válida. Intenta nuevamente.");
            }
        } while (opcion != 0);
    }

    public void menuAdministrarEquipos() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ ADMINISTRAR EQUIPOS ---");
            System.out.println("[1] Agregar equipo");
            System.out.println("[2] Consultar equipo");
            System.out.println("[3] Eliminar equipo");
            System.out.println("[0] Volver al menú anterior");
            System.out.print(">: ");

            while (!entrada.hasNextInt()) {
                System.out.println("[!] Entrada inválida. Ingrese un número.");
                entrada.next();
                System.out.print(">: ");
            }

            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese ID del equipo: ");
                    int id = leerEnteroPositivo(entrada);

                    System.out.print("Ingrese nombre del equipo: ");
                    String nombre = entrada.nextLine();

                    System.out.print("Ingrese tipo de equipo: ");
                    String tipo = entrada.nextLine();

                    System.out.print("¿Está disponible? (true/false): ");
                    boolean disponible = leerBooleano(entrada);

                    Equipo equipo = new Equipo(id, nombre, tipo, disponible);
                    Equipo.crearEquipo(equipo);
                }
                case 2 -> {
                    System.out.println("\n--- LISTA DE EQUIPOS ---");
                    List<Equipo> equipos = Equipo.listarEquipos();
                    if (equipos.isEmpty()) {
                        System.out.println("[!] No hay equipos registrados.");
                    } else {
                        for (Equipo eq : equipos) {
                            eq.mostrarInfo();
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese el ID del equipo a eliminar: ");
                    int id = leerEnteroPositivo(entrada);
                    boolean eliminado = Equipo.eliminarEquipo(id);
                    if (!eliminado) {
                        System.out.println("[!] No se encontró el equipo con ID " + id);
                    }
                }
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("[!] Opción inválida.");
            }

        } while (opcion != 0);
    }

    public void menuRecuperarCredenciales() {
        Scanner entrada = new Scanner(System.in);
        String correo;

        System.out.println("\n[1] Recuperar contraseña de administrador");
        System.out.print("Ingresa tu correo institucional: ");
        correo = entrada.nextLine();

        if (correo.endsWith("@uce.edu.ec")) {
            System.out.println("Enlace de recuperación enviado a " + correo);
        } else {
            System.out.println("Correo no válido. Intenta de nuevo.");
            menuRecuperarCredenciales();
        }
    }

    private int leerEnteroPositivo(Scanner entrada) {
        int numero;
        while (true) {
            while (!entrada.hasNextInt()) {
                System.out.print("[!] Entrada inválida. Ingrese un número: ");
                entrada.next();
            }
            numero = entrada.nextInt();
            entrada.nextLine();

            if (numero <= 0) {
                System.out.print("[!] El número debe ser positivo. Intente de nuevo: ");
            } else {
                break;
            }
        }
        return numero;
    }

    private boolean leerBooleano(Scanner entrada) {
        while (true) {
            String valor = entrada.nextLine().trim().toLowerCase();
            if (valor.equals("true") || valor.equals("sí") || valor.equals("si")) return true;
            if (valor.equals("false") || valor.equals("no")) return false;
            System.out.print("[!] Ingrese 'true' o 'false' (o 'sí' / 'no'): ");
        }
    }
}