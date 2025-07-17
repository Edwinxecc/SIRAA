/**
 * Clase principal del menú del sistema SIRAA.
 * Maneja la creación de usuarios y el menú principal del sistema.
 */
package ec.edu.uce.consola;

import java.util.Scanner;
import java.util.List;
import java.util.Date;

import ec.edu.uce.dominio.*;
import ec.edu.uce.Util.RelacionesUtil;

public class Menu {
    /**
     * Usuario actual
     */
    private Usuario usuarioActual;
    /**
     * Facultad actual
     */
    private Facultad facultadActual;
    /**
     * Auditorio actual
     */
    private Auditorio auditorioActual;

    public Menu(Facultad facultadActual) {
        this.facultadActual = facultadActual;
    }

    /**
     * Muestra el flujo principal del sistema.
     * 1. Crear usuario
     * 2. Seleccionar facultad
     * 3. Seleccionar auditorio
     * 4. Crear reserva
     */
    public void menuDeInicio() {
        System.out.printf("%n%s%n", "=".repeat(60));
        System.out.printf("%-20s%s%n", "", "Bienvenidos al Sistema SIRAA");
        System.out.printf("%s%n%n", "=".repeat(60));
        
        // Paso 1: Crear usuario
        crearUsuarioInicial();
        
        // Paso 2: Seleccionar facultad
        seleccionarFacultad();
        
        // Paso 3: Seleccionar auditorio
        seleccionarAuditorio();
        
        // Paso 4: Crear reserva inicial
        crearReservaInicial();
        
        // Mostrar información del sistema
        mostrarInformacionSistema();
        
        // Ir al menú principal
        menuElegirOpcion();
    }

    /**
     * Crea el usuario inicial del sistema.
     */
    private void crearUsuarioInicial() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-25s%n", "PASO 1: CREAR USUARIO");
        System.out.printf("%s%n", "─".repeat(50));
        
        Scanner entrada = new Scanner(System.in);
        
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = entrada.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.print("[!] El nombre no puede estar vacío. Ingrese el nombre: ");
            nombre = entrada.nextLine().trim();
        }
        
        System.out.print("Ingrese el apellido del usuario: ");
        String apellido = entrada.nextLine().trim();
        while (apellido.isEmpty()) {
            System.out.print("[!] El apellido no puede estar vacío. Ingrese el apellido: ");
            apellido = entrada.nextLine().trim();
        }
        
        System.out.print("Ingrese el correo institucional (@uce.edu.ec): ");
        String correo = entrada.nextLine().trim();
        while (!validarCorreo(correo)) {
            System.out.print("[!] El correo debe ser institucional. Ingrese el correo (@uce.edu.ec): ");
            correo = entrada.nextLine().trim();
        }
        
        usuarioActual = new Usuario(nombre, apellido, correo);
        System.out.println("✅ Usuario creado exitosamente: " + usuarioActual.getNombre() + " " + usuarioActual.getApellido());
    }
    
    /**
     * Selecciona una facultad existente.
     */
    private void seleccionarFacultad() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-25s%n", "PASO 2: SELECCIONAR FACULTAD");
        System.out.printf("%s%n", "─".repeat(50));
        
        Universidad universidad = Universidad.getInstancia();
        List<Facultad> facultades = universidad.getFacultades();
        
        if (facultades.isEmpty()) {
            System.out.println("[!] No hay facultades disponibles. Se creará una facultad por defecto.");
            Facultad nuevaFacultad = new Facultad("Facultad de Ingeniería");
            universidad.crearFacultad(nuevaFacultad);
            facultades = universidad.getFacultades();
        }
        
        System.out.println("Facultades disponibles:");
        for (int i = 0; i < facultades.size(); i++) {
            System.out.println("[" + i + "] " + facultades.get(i).getNombre());
        }
        
        Scanner entrada = new Scanner(System.in);
        int indice;
        do {
            System.out.print("Seleccione el índice de la facultad: ");
            try {
                indice = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                indice = -1;
            }
        } while (indice < 0 || indice >= facultades.size());
        
        facultadActual = facultades.get(indice);
        System.out.println("✅ Facultad seleccionada: " + facultadActual.getNombre());
    }
    
    /**
     * Selecciona un auditorio dentro de la facultad.
     */
    private void seleccionarAuditorio() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-25s%n", "PASO 3: SELECCIONAR AUDITORIO");
        System.out.printf("%s%n", "─".repeat(50));
        
        List<Auditorio> auditorios = facultadActual.getAuditorios();
        
        if (auditorios.isEmpty()) {
            System.out.println("[!] No hay auditorios en esta facultad. Se crearán auditorios por defecto.");
            Auditorio auditorio1 = new Auditorio("Auditorio Principal", 100);
            Auditorio auditorio2 = new Auditorio("Auditorio Secundario", 50);
            facultadActual.crearAuditorio(auditorio1);
            facultadActual.crearAuditorio(auditorio2);
            auditorios = facultadActual.getAuditorios();
        }
        
        System.out.println("Auditorios disponibles en " + facultadActual.getNombre() + ":");
        for (int i = 0; i < auditorios.size(); i++) {
            Auditorio auditorio = auditorios.get(i);
            System.out.println("[" + i + "] " + auditorio.getNombre() + " (Capacidad: " + auditorio.getCapacidad() + " personas)");
        }
        
        Scanner entrada = new Scanner(System.in);
        int indice;
        do {
            System.out.print("Seleccione el índice del auditorio: ");
            try {
                indice = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                indice = -1;
            }
        } while (indice < 0 || indice >= auditorios.size());
        
        auditorioActual = auditorios.get(indice);
        System.out.println("✅ Auditorio seleccionado: " + auditorioActual.getNombre());
    }
    
    /**
     * Crea una reserva inicial para el auditorio seleccionado.
     */
    private void crearReservaInicial() {
        System.out.printf("%n%s%n", "─".repeat(50));
        System.out.printf("%-25s%n", "PASO 4: CREAR RESERVA INICIAL");
        System.out.printf("%s%n", "─".repeat(50));
        
        try {
            // Crear una reserva por defecto
            Reserva reserva = new Reserva(new Date(), new Date());
            reserva.setUsuario(usuarioActual);
            reserva.setAuditorio(auditorioActual);
            
            usuarioActual.crearReserva(reserva);
            auditorioActual.crearReserva(reserva);
            
            System.out.println("✅ Reserva creada exitosamente para el auditorio: " + auditorioActual.getNombre());
            System.out.println("📅 Fecha de reserva: " + reserva.getFechaInicio());
            
        } catch (Exception e) {
            System.out.println("[!] Error al crear la reserva: " + e.getMessage());
        }
    }
    
    /**
     * Valida que el correo sea institucional.
     *
     * @param correo Correo a validar
     * @return true si el correo es válido, false en caso contrario
     */
    public boolean validarCorreo(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return correo.endsWith("@uce.edu.ec");
    }

    /**
     * Muestra el menú principal del sistema.
     */
    public void menuElegirOpcion() {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        SubMenu subMenuObj = new SubMenu(facultadActual, usuarioActual);

        while (true) {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ PRINCIPAL");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Gestionar Usuario");
            System.out.printf("%-5s%-30s%n", "[2]", "Gestionar Reservas");
            System.out.printf("%-5s%-30s%n", "[3]", "Gestionar Facultades");
            System.out.printf("%-5s%-30s%n", "[4]", "Gestionar Equipos");
            System.out.printf("%-5s%-30s%n", "[5]", "Recuperar Credenciales");
            System.out.printf("%-5s%-30s%n", "[0]", "Salir");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            try {
                opcion = Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.printf("%-25s%n", "❌ Por favor, ingresa un número válido");
                continue;
            }

            switch (opcion) {
                case 1:
                    subMenuObj.menuGestionarUsuario();
                    break;
                case 2:
                    subMenuObj.menuGestionarReserva();
                    break;
                case 3:
                    subMenuObj.menuGestionarFacultades();
                    break;
                case 4:
                    subMenuObj.menuAdministrarEquipos();
                    break;
                case 5:
                    subMenuObj.menuRecuperarCredenciales();
                    break;
                case 0:
                    System.out.printf("%-25s%n", "👋 Gracias por usar el sistema. ¡Hasta pronto!");
                    return;
                default:
                    System.out.printf("%-25s%n", "❌ Opción no válida. Por favor, intente nuevamente");
            }
        }
    }

    /**
     * Muestra la información del sistema después de la configuración inicial.
     */
    private void mostrarInformacionSistema() {
        System.out.printf("%n%s%n", "=".repeat(60));
        System.out.printf("%-20s%s%n", "", "INFORMACIÓN DEL SISTEMA");
        System.out.printf("%s%n", "=".repeat(60));
        System.out.println("Sistema Integrado de Reserva de Auditorios y Administración");
        System.out.println("Universidad Central del Ecuador");
        System.out.println();
        
        // Mostrar resumen de relaciones del sistema
        Universidad universidad = Universidad.getInstancia();
        RelacionesUtil.mostrarResumenRelaciones(universidad);
        
        System.out.printf("%s%n", "─".repeat(60));
        System.out.println();
    }
    
    /**
     * Muestra todas las reservas del usuario actual.
     */
    private void verReservasUsuario() {
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "RESERVAS DEL USUARIO");
        System.out.printf("%s%n", "=".repeat(50));
        
        List<Reserva> reservas = usuarioActual.getReservas();
        
        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para mostrar.");
            return;
        }
        
        System.out.println("Usuario: " + usuarioActual.getNombre() + " " + usuarioActual.getApellido());
        System.out.println("Correo: " + usuarioActual.getCorreo());
        System.out.println("Total de reservas: " + reservas.size());
        System.out.println();
        
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println("=== RESERVA " + (i + 1) + " ===");
            System.out.println(reservas.get(i));
            System.out.println();
        }
    }
    
    /**
     * Muestra la información de la facultad asociada al usuario.
     */
    private void verInformacionFacultad() {
        System.out.printf("%n%s%n", "=".repeat(50));
        System.out.printf("%-20s%s%n", "", "INFORMACIÓN DE LA FACULTAD");
        System.out.printf("%s%n", "=".repeat(50));
        
        System.out.println("Facultad asociada al usuario:");
        System.out.println(facultadActual);
        System.out.println();
        
        System.out.println("Auditorios disponibles:");
        List<Auditorio> auditorios = facultadActual.getAuditorios();
        if (auditorios.isEmpty()) {
            System.out.println("[!] No hay auditorios en esta facultad.");
        } else {
            for (int i = 0; i < auditorios.size(); i++) {
                System.out.println("[" + i + "] " + auditorios.get(i));
            }
        }
    }
}