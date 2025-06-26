package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Clase de prueba para verificar la implementación de la interfaz IAdministrarCRUD
 * en todas las clases del dominio.
 */
public class TestInterfazIAdministrarCRUD {
    
    public static void main(String[] args) {
        System.out.println("=== PRUEBA DE IMPLEMENTACIÓN DE IAdministrarCRUD ===\n");
        
        // Prueba con Universidad (Singleton)
        System.out.println("1. PRUEBA CON UNIVERSIDAD:");
        Universidad universidad = Universidad.getInstancia();
        testearClase(universidad, "Universidad");
        
        // Prueba con Usuario
        System.out.println("\n2. PRUEBA CON USUARIO:");
        Usuario usuario = new Usuario("Juan", "Pérez", "juan@email.com");
        testearClase(usuario, "Usuario");
        
        // Prueba con Facultad
        System.out.println("\n3. PRUEBA CON FACULTAD:");
        Facultad facultad = new Facultad("Ingeniería");
        testearClase(facultad, "Facultad");
        
        // Prueba con Auditorio
        System.out.println("\n4. PRUEBA CON AUDITORIO:");
        Auditorio auditorio = new Auditorio("Auditorio Principal", 100);
        testearClase(auditorio, "Auditorio");
        
        // Prueba con Equipo
        System.out.println("\n5. PRUEBA CON EQUIPO:");
        Equipo equipo = new Equipo("Proyector", "Visual", true);
        testearClase(equipo, "Equipo");
        
        // Prueba con Reserva
        System.out.println("\n6. PRUEBA CON RESERVA:");
        Reserva reserva = new Reserva(1, new Date(), new Date());
        testearClase(reserva, "Reserva");
        
        // Prueba con ReservaPrioritaria
        System.out.println("\n7. PRUEBA CON RESERVA PRIORITARIA:");
        ReservaPrioritaria reservaPrioritaria = new ReservaPrioritaria(2, new Date(), new Date(), 
                                                                       Estado.PRIORIDAD_ALTA, "Evento importante");
        testearClase(reservaPrioritaria, "ReservaPrioritaria");
        
        System.out.println("\n=== TODAS LAS PRUEBAS COMPLETADAS ===");
    }
    
    /**
     * Método genérico para probar los métodos CRUD de una clase.
     * @param objeto Objeto que implementa IAdministrarCRUD
     * @param nombreClase Nombre de la clase para mostrar en los mensajes
     */
    private static void testearClase(Object objeto, String nombreClase) {
        if (!(objeto instanceof IAdministrarCRUD)) {
            System.out.println("[!] " + nombreClase + " NO implementa IAdministrarCRUD");
            return;
        }
        
        IAdministrarCRUD crud = (IAdministrarCRUD) objeto;
        
        System.out.println("✓ " + nombreClase + " implementa IAdministrarCRUD");
        
        // Probar método nuevo()
        String resultadoNuevo = crud.nuevo(objeto);
        System.out.println("  - nuevo(): " + resultadoNuevo);
        
        // Probar método editar()
        String resultadoEditar = crud.editar(objeto);
        System.out.println("  - editar(): " + resultadoEditar);
        
        // Probar método borrar()
        String resultadoBorrar = crud.borrar(objeto);
        System.out.println("  - borrar(): " + resultadoBorrar);
        
        // Probar método buscarPorId()
        Object resultadoBuscar = crud.buscarPorId(1);
        System.out.println("  - buscarPorId(1): " + (resultadoBuscar != null ? "Encontrado" : "No encontrado"));
        
        // Probar método listar()
        String resultadoListar = crud.listar();
        System.out.println("  - listar(): " + resultadoListar);
    }
} 