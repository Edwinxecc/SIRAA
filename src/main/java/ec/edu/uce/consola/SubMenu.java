package ec.edu.uce.consola;

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
                System.out.println("Ingresa el nombre del usuario nuevo: ");
                nombre = entrada.next();
                usuarioObj2.setNombre(nombre);
                System.out.println("Ingresa el apellido del usuario nuevo: ");
                apellido = entrada.next();
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

    }
}
