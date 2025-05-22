package ec.edu.uce.consola;

import ec.edu.uce.Util.Validaciones;
import ec.edu.uce.dominio.Reserva;
import ec.edu.uce.dominio.Usuario;
import ec.edu.uce.dominio.Facultad;
import ec.edu.uce.dominio.Equipo;
import java.util.List;
import java.util.Scanner;

public class SubMenu {

    public void MenuGestionarUsuario() {
        Usuario usuarioObj = new Usuario();
        Validaciones validacion = new Validaciones();
        Scanner entrada = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("\n--- MENÚ GESTIONAR USUARIO ---");
            System.out.println("[1] Crear Usuario");
            System.out.println("[2] Consultar Usuario");
            System.out.println("[3] Editar Usuario");
            System.out.println("[4] Eliminar Usuario");
            System.out.println("[0] Salir");
            System.out.print(">: ");

            while (!entrada.hasNextInt()) {
                System.out.println("[!] Entrada inválida. Ingrese un número.");
                entrada.next();
                System.out.print(">: ");
            }
            opcion = entrada.nextInt();
            entrada.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("\n[1] Crear Usuario");
                    usuarioObj.setUsuarioId(usuarioObj.generarId());

                    System.out.print("Ingresa el nombre del usuario: ");
                    String nombre = entrada.nextLine();
                    nombre = validacion.ValidacionTexto(nombre, "nombre");
                    usuarioObj.setNombre(nombre);

                    System.out.print("Ingresa el apellido del usuario: ");
                    String apellido = entrada.nextLine();
                    apellido = validacion.ValidacionTexto(apellido, "apellido");
                    usuarioObj.setApellido(apellido);

                    System.out.print("Ingresa el correo del usuario: ");
                    String correo = entrada.nextLine();
                    usuarioObj.setCorreo(correo);

                    System.out.println("\n[*] Usuario creado con éxito:");
                    imprimirUsuario(usuarioObj);
                    break;

                case 2:
                    System.out.println("\n[2] Consultar Usuario");
                    System.out.print("Ingresa el ID del usuario para consultar: ");
                    int idConsulta = leerEnteroPositivo(entrada);

                    if (usuarioObj.getNombre() == null) {
                        System.out.println("[!] No hay usuario registrado.");
                    } else if (usuarioObj.getUsuarioId() == idConsulta) {
                        imprimirUsuario(usuarioObj);
                    } else {
                        System.out.println("[!] Usuario no encontrado con ese ID.");
                    }
                    break;

                case 3:
                    System.out.println("\n[3] Editar Usuario");
                    System.out.print("Ingresa el ID del usuario a editar: ");
                    int idEditar = leerEnteroPositivo(entrada);

                    if (usuarioObj.getNombre() == null) {
                        System.out.println("[!] No hay usuario registrado.");
                    } else if (usuarioObj.getUsuarioId() == idEditar) {
                        System.out.print("Nuevo nombre: ");
                        String nuevoNombre = entrada.nextLine();
                        nuevoNombre = validacion.ValidacionTexto(nuevoNombre, "nombre");
                        usuarioObj.setNombre(nuevoNombre);

                        System.out.print("Nuevo apellido: ");
                        String nuevoApellido = entrada.nextLine();
                        nuevoApellido = validacion.ValidacionTexto(nuevoApellido, "apellido");
                        usuarioObj.setApellido(nuevoApellido);

                        System.out.print("Nuevo correo: ");
                        String nuevoCorreo = entrada.nextLine();
                        usuarioObj.setCorreo(nuevoCorreo);

                        System.out.println("[*] Usuario editado con éxito:");
                        imprimirUsuario(usuarioObj);
                    } else {
                        System.out.println("[!] Usuario no encontrado con ese ID.");
                    }
                    break;

                case 4:
                    System.out.println("\n[4] Eliminar Usuario");
                    System.out.print("Ingresa el ID del usuario a eliminar: ");
                    int idEliminar = leerEnteroPositivo(entrada);

                    if (usuarioObj.getNombre() == null) {
                        System.out.println("[!] No hay usuario registrado.");
                    } else if (usuarioObj.getUsuarioId() == idEliminar) {
                        usuarioObj = new Usuario(); // Reiniciar objeto para eliminar
                        System.out.println("[*] Usuario eliminado correctamente.");
                    } else {
                        System.out.println("[!] Usuario no encontrado con ese ID.");
                    }
                    break;

                case 0:
                    System.out.println("[!] Saliendo....");
                    break;

                default:
                    System.out.println("[!] Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void imprimirUsuario(Usuario u) {
        System.out.println("-------------------------------------");
        System.out.println("| ID: " + u.getUsuarioId());
        System.out.println("| Nombre: " + u.getNombre());
        System.out.println("| Apellido: " + u.getApellido());
        System.out.println("| Correo: " + u.getCorreo());
        System.out.println("-------------------------------------");
    }

    public void MenuGestionarReserva() {
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
                case 1 -> {
                    System.out.print("Ingrese un ID para la reserva: ");
                    int id = leerEnteroPositivo(entrada);
                    Reserva nueva = new Reserva(id);
                    Reserva.agregarReserva(nueva);
                    System.out.println("[✓] Reserva creada con ID: " + id);
                }
                case 2 -> {
                    System.out.println("\n--- LISTA DE RESERVAS ---");
                    if (Reserva.obtenerTodasLasReservas().isEmpty()) {
                        System.out.println("[!] No hay reservas registradas.");
                    } else {
                        for (Reserva r : Reserva.obtenerTodasLasReservas()) {
                            System.out.println("ID: " + r.getIdReserva() + " | Fecha: " + r.getFechaReserva());
                        }
                    }
                }
                case 3 -> {
                    System.out.print("Ingrese el ID de la reserva a editar: ");
                    int id = leerEnteroPositivo(entrada);
                    Reserva encontrada = Reserva.buscarPorId(id);
                    if (encontrada != null) {
                        System.out.print("Ingrese el nuevo ID para la reserva: ");
                        int nuevoId = leerEnteroPositivo(entrada);
                        Reserva.actualizarReserva(id, nuevoId);
                        System.out.println("[✓] Reserva actualizada.");
                    } else {
                        System.out.println("[!] Reserva no encontrada.");
                    }
                }
                case 4 -> {
                    System.out.print("Ingrese el ID de la reserva a eliminar: ");
                    int id = leerEnteroPositivo(entrada);
                    boolean eliminada = Reserva.eliminarReserva(id);
                    if (eliminada) {
                        System.out.println("[✓] Reserva eliminada.");
                    } else {
                        System.out.println("[!] No se encontró la reserva con ese ID.");
                    }
                }
                case 0 -> System.out.println("Regresando al menú principal...");
                default -> System.out.println("[!] Opción no válida.");
            }

        } while (opcion != 0);
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

    public void menuGestionarFacultades() {
        Scanner scanner = new Scanner(System.in);
        int opcion;
        Facultad op = new Facultad();

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

                    Facultad nueva = new Facultad(nombre, cantidad);
                    if (op.crearFacultad(nueva)) {
                        System.out.println("Facultad creada correctamente.");
                    } else {
                        System.out.println("Error: nombre inválido.");
                    }
                    break;

                case 2:
                    op.buscarFacultades();
                    break;

                case 3:
                    System.out.print("Nombre actual de la facultad: ");
                    String nombreActual = scanner.nextLine();
                    System.out.print("Nuevo nombre: ");
                    String nuevoNombre = scanner.nextLine();
                    System.out.print("Nueva cantidad de auditorios: ");
                    int nuevosAuditorios = leerEnteroPositivo(scanner);

                    if (op.editarFacultad(nombreActual, nuevoNombre, nuevosAuditorios)) {
                        System.out.println("Facultad editada correctamente.");
                    } else {
                        System.out.println("Facultad no encontrada.");
                    }
                    break;

                case 4:
                    System.out.print("Nombre de la facultad a eliminar: ");
                    String nombreEliminar = scanner.nextLine();

                    if (op.eliminarFacultad(nombreEliminar)) {
                        System.out.println("Facultad eliminada correctamente.");
                    } else {
                        System.out.println("Facultad no encontrada.");
                    }
                    break;

                case 0:
                    System.out.println("Saliendo del menú...");
                    break;

                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
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

    private boolean leerBooleano(Scanner entrada) {
        while (true) {
            String valor = entrada.nextLine().trim().toLowerCase();
            if (valor.equals("true") || valor.equals("sí") || valor.equals("si")) return true;
            if (valor.equals("false") || valor.equals("no")) return false;
            System.out.print("[!] Ingrese 'true' o 'false' (o 'sí' / 'no'): ");
        }
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
}