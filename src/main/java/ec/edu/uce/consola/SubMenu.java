package ec.edu.uce.consola;

import ec.edu.uce.Util.Validaciones;
import ec.edu.uce.dominio.Reserva;
import ec.edu.uce.dominio.Usuario;

import java.util.Scanner;

public class SubMenu {
    public void MenuGestionarUsuario(){
        Usuario usuarioObj2;
        usuarioObj2 = new Usuario();
        Usuario usuarioObj = new Usuario();
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("\n[1] Crear Usuario");
        System.out.println("[2] Consultar Usuario");
        System.out.println("[3] Editar Usuario");
        System.out.println("[4] Eliminar Usuario");
        System.out.println(">:  ");
        opcion = entrada.nextInt();
        switch (opcion){
            case 1:
                String nombre, apellido, correo;
                System.out.println("\n[1] Crear Usuario");
                usuarioObj2.setUsuarioId(usuarioObj.generarId());

                Validaciones validacion = new Validaciones();

                System.out.println("Ingresa el nombre del usuario nuevo: ");
                nombre = entrada.next();
                nombre = validacion.ValidacionTexto(nombre, "nombre");
                usuarioObj2.setNombre(nombre);

                System.out.println("Ingresa el apellido del usuario nuevo: ");
                apellido = entrada.next();
                apellido = validacion.ValidacionTexto(apellido, "apellido");
                usuarioObj2.setApellido(apellido);
                System.out.println("Ingresa el correo del usuario nuevo: ");
                correo = entrada.next();
                usuarioObj2.setCorreo(correo);
                //usuarioObj2 = new Usuario(usuarioObj.generarId(), nombre, apellido, correo);
                System.out.println("\n\n");
                System.out.println("-------------------------------------");
                System.out.println("| ID: " + usuarioObj2.getUsuarioId());
                System.out.println("-------------------------------------");
                System.out.println("| Correo: " + usuarioObj2.getCorreo());
                System.out.println("| Nombre: " + usuarioObj2.getNombre());
                System.out.println("| Apellido: " + usuarioObj2.getApellido());
                System.out.println("-------------------------------------");
                System.out.println("[*] Usuario Creado con Exito");
                MenuGestionarUsuario();
                break;
            case 2:
                System.out.println("[2] Consultar Usuario");
                System.out.println(usuarioObj2.toString());
                MenuGestionarUsuario();
            case 3:
                System.out.println("[3] Editar Usuario");
                String nombreEdit, apellidoEdit, correoEdit;

                System.out.println("Ingresa el nombre del usuario nuevo: ");
                nombreEdit = entrada.next();
                usuarioObj2.setNombre(nombreEdit);
                System.out.println("Ingresa el apellido del usuario nuevo: ");
                apellidoEdit = entrada.next();
                usuarioObj2.setApellido(apellidoEdit);
                System.out.println("Ingresa el correo del usuario nuevo: ");
                correoEdit = entrada.next();
                usuarioObj2.setCorreo(correoEdit);

                System.out.println("\n\n");
                System.out.println("-------------------------------------");
                System.out.println("| ID: " + usuarioObj2.getUsuarioId());
                System.out.println("-------------------------------------");
                System.out.println("| Correo: " + usuarioObj2.getCorreo());
                System.out.println("| Nombre: " + usuarioObj2.getNombre());
                System.out.println("| Apellido: " + usuarioObj2.getApellido());
                System.out.println("-------------------------------------");
                System.out.println("[*] Usuario Editado con Exito");
                MenuGestionarUsuario();
                break;
            case 4:
                System.out.println("[4] Eliminar Usuario");
                System.out.println("[*] Disponible en una nueva version");
                MenuGestionarUsuario();
                break;
            default:
                System.out.println("Ingrese una opcion valida");
        }
    }

    public void MenuGestionarReserva(){
        Reserva reserva = new Reserva();
        Scanner entrada = new Scanner(System.in);
        int opcion;
        System.out.println("\n[1] Crear Reserva");
        System.out.println("[2] Editar Reserva");
        System.out.println(">:  ");
        opcion = entrada.nextInt();
        switch (opcion){
            case 1:
                reserva.crearReserva(12);
                System.out.println("Reserva Creada");
                break;
            case 2:
                int id;
                System.out.println("Editar Reserva");
                System.out.println("\nIngresa el id: " );
                id = entrada.nextInt();
                reserva = new Reserva(id);
                break;
            default:

        }
    }

    public void menuConfigurarParametros() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        System.out.println("\n[1] Establecer duración máxima de reserva");
        System.out.println("[2] Definir horario de atención");
        System.out.println("[3] Política de cancelación");
        System.out.println("[0] Volver al menú anterior");
        System.out.print(">: ");
        opcion = entrada.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Duración máxima establecida.");
                break;
            case 2:
                System.out.println("Horario de atención configurado.");
                break;
            case 3:
                System.out.println("Política de cancelación actualizada.");
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida.");
                menuConfigurarParametros();
                break;
        }
    }

    public void menuAdministrarEquipos() {
        Scanner entrada = new Scanner(System.in);
        int opcion;

        System.out.println("\n[1] Agregar equipo");
        System.out.println("[2] Consultar equipo");
        System.out.println("[3] Eliminar equipo");
        System.out.println("[0] Volver al menú anterior");
        System.out.print(">: ");
        opcion = entrada.nextInt();

        switch (opcion) {
            case 1:
                System.out.println("Equipo agregado correctamente.");
                break;
            case 2:
                System.out.println("Mostrando lista de equipos...");
                break;
            case 3:
                System.out.println("Equipo eliminado.");
                break;
            case 0:
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("Opción inválida.");
                menuAdministrarEquipos();
                break;
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
