package ec.edu.uce.dominio;

import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class TestFacultad {
    public static void main(String[] args) {
        System.out.println("--- Test de la clase Facultad ---");

        // 1. Instanciación de Facultad
        // Se crea una nueva instancia de Facultad.
        Facultad f = new Facultad();
        System.out.println("Facultad creada: " + f);
        System.out.println("-----------------------------------");

        // 2. CRUD de Usuarios
        System.out.println("--- Prueba de CRUD de Usuarios ---");

        // Crear usuarios
        System.out.println("Creando usuarios...");
        f.crearUsuario("luis", "Caiza", "a@uce.edu.ec");
        f.crearUsuario("Lewandowski", "Angulo", "lew@uce.edu.ec");
        f.crearUsuario("Maria", "Gonzales", "maria@example.com"); // Añadido para tener más datos
        System.out.println("Usuarios después de la creación:");
        System.out.println(f.listarUsuarios());
        System.out.println("-----------------------------------");

        // Eliminar un usuario
        System.out.println("Eliminando el usuario en el índice 0 (luis Caiza)...");
        Usuario[] usuarios = f.getUsuarios();
        if (usuarios.length > 0) {
            f.eliminarUsuario(usuarios[0].getCodigoUsuario());
        }
        System.out.println("Usuarios después de la eliminación:");
        System.out.println(f.listarUsuarios());
        System.out.println("-----------------------------------");

        // Actualizar un usuario (ejemplo, actualizamos "Lewandowski")
        System.out.println("Actualizando el usuario en el índice 0 (Lewandowski)..."); // Ahora Lewandowski está en el índice 0
        if (usuarios.length > 0) {
            Usuario usuarioActualizado = new Usuario("Robert", "Lewandowski", "robert.l@example.com");
            f.actualizarUsuario(usuarios[0].getCodigoUsuario(), usuarioActualizado);
        }
        System.out.println("Usuarios después de la actualización:");
        System.out.println(f.listarUsuarios());
        System.out.println("-----------------------------------");

        // 3. CRUD de Auditorios
        System.out.println("--- Prueba de CRUD de Auditorios ---");

        // Crear auditorios
        System.out.println("Creando auditorios...");
        Auditorio a = new Auditorio("Hola", 176);
        Auditorio b = new Auditorio("FICA", 125);
        Auditorio c = new Auditorio("Central", 300); // Añadido para tener más datos

        f.crearAuditorio(); // Crea un auditorio con constructor por defecto ("Sin nombre", 0)
        f.crearAuditorio(a);
        f.crearAuditorio(b);
        f.crearAuditorio(c);
        System.out.println("Auditorios después de la creación:");
        System.out.println(f.listarAuditorios());
        System.out.println("-----------------------------------");

        // Eliminar un auditorio
        System.out.println("Eliminando el auditorio en el índice 0 (Sin nombre)...");
        Auditorio[] auditorios = f.getAuditorios();
        if (auditorios.length > 0) {
            f.eliminarAuditorio(auditorios[0].getCodigoAuditorio());
        }
        System.out.println("Auditorios después de la eliminación:");
        System.out.println(f.listarAuditorios());
        System.out.println("-----------------------------------");

        // Actualizar un auditorio (ejemplo, actualizamos "Hola")
        System.out.println("Actualizando el auditorio en el índice 0 (Hola)..."); // Ahora "Hola" está en el índice 0
        if (auditorios.length > 0) {
            Auditorio auditorioActualizado = new Auditorio("Auditorio Principal", 200);
            f.actualizarAuditorio(auditorios[0].getCodigoAuditorio(), auditorioActualizado);
        }
        System.out.println("Auditorios después de la actualización:");
        System.out.println(f.listarAuditorios());
        System.out.println("-----------------------------------");

        System.out.println("--- Fin del Test de Facultad ---");
    }
}