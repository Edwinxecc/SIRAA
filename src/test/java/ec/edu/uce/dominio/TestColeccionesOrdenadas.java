package ec.edu.uce.dominio;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Test para demostrar el uso de colecciones ordenadas: TreeMap y TreeSet.
 * Este test evidencia el comportamiento de estas colecciones con datos del sistema SIRAA.
 */
public class TestColeccionesOrdenadas {
    
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE COLECCIONES ORDENADAS SIRAA ===\n");
        
        // Test 1: TreeMap básico (siguiendo el ejemplo proporcionado)
        testTreeMapBasico();
        
        // Test 2: TreeSet básico (siguiendo el ejemplo proporcionado)
        testTreeSetBasico();
        
        // Test 3: TreeMap con datos del sistema SIRAA
        testTreeMapSIRAA();
        
        // Test 4: TreeSet con datos del sistema SIRAA
        testTreeSetSIRAA();
        
        // Test 5: TreeMap con objetos del dominio
        testTreeMapObjetos();
        
        // Test 6: TreeSet con objetos del dominio
        testTreeSetObjetos();
        
        System.out.println("\n=== TODOS LOS TESTS DE COLECCIONES COMPLETADOS ===");
    }
    
    private static void testTreeMapBasico() {
        System.out.println("🔹 TEST TREEMAP BÁSICO (Ejemplo Original)");
        System.out.println("=" .repeat(60));
        
        Map<String, String> lista = new TreeMap<>();
        lista.put("Est-001", "Edwin");
        lista.put("Est-001", "Edwin"); // clave duplicada, sobrescribe el valor
        lista.put("Est-002", "Jayden");

        Set<String> claves = lista.keySet();
        for (String item : claves) {
            System.out.println("Clave: " + item + ", Valor: " + lista.get(item));
        }
        
        System.out.println("📊 Observaciones:");
        System.out.println("   • TreeMap no permite claves duplicadas");
        System.out.println("   • Las claves se mantienen ordenadas alfabéticamente");
        System.out.println("   • Si se inserta la misma clave, el valor se sobrescribe");
        System.out.println();
    }
    
    private static void testTreeSetBasico() {
        System.out.println("🔹 TEST TREESET BÁSICO (Ejemplo Original)");
        System.out.println("=" .repeat(60));
        
        Set<String> set = new TreeSet<>();
        set.add("uno");
        set.add("dos");
        set.add("dos"); // no permite duplicados
        set.add("abc");

        for (String item : set) {
            System.out.println("Item: " + item);
        }
        
        System.out.println("📊 Observaciones:");
        System.out.println("   • TreeSet no permite elementos duplicados");
        System.out.println("   • Los elementos se mantienen ordenados alfabéticamente");
        System.out.println("   • El segundo 'dos' no se agregó porque ya existía");
        System.out.println();
    }
    
    private static void testTreeMapSIRAA() {
        System.out.println("🔹 TEST TREEMAP CON DATOS SIRAA");
        System.out.println("=" .repeat(60));
        
        // TreeMap para mapear códigos de auditorio con sus nombres
        Map<String, String> auditorios = new TreeMap<>();
        auditorios.put("AUD0001", "Auditorio Principal");
        auditorios.put("AUD0003", "Auditorio de Ciencias");
        auditorios.put("AUD0002", "Auditorio de Ingeniería");
        auditorios.put("AUD0001", "Auditorio Principal Modificado"); // sobrescribe el anterior
        
        System.out.println("📋 Auditorios por código:");
        Set<String> codigos = auditorios.keySet();
        for (String codigo : codigos) {
            System.out.println("Código: " + codigo + ", Nombre: " + auditorios.get(codigo));
        }
        
        // TreeMap para mapear códigos de facultad con número de auditorios
        Map<String, Integer> facultades = new TreeMap<>();
        facultades.put("FAC0001", 3);
        facultades.put("FAC0002", 1);
        facultades.put("FAC0003", 2);
        facultades.put("FAC0001", 5); // sobrescribe el valor anterior
        
        System.out.println("\n📋 Facultades por código:");
        Set<String> codigosFac = facultades.keySet();
        for (String codigo : codigosFac) {
            System.out.println("Código: " + codigo + ", Auditorios: " + facultades.get(codigo));
        }
        
        System.out.println();
    }
    
    private static void testTreeSetSIRAA() {
        System.out.println("🔹 TEST TREESET CON DATOS SIRAA");
        System.out.println("=" .repeat(60));
        
        // TreeSet para códigos de reserva únicos
        Set<String> codigosReserva = new TreeSet<>();
        codigosReserva.add("RES0001");
        codigosReserva.add("RES0003");
        codigosReserva.add("RES0002");
        codigosReserva.add("RES0001"); // duplicado, no se agrega
        codigosReserva.add("RES0004");
        
        System.out.println("📋 Códigos de reserva únicos (ordenados):");
        for (String codigo : codigosReserva) {
            System.out.println("Código: " + codigo);
        }
        
        // TreeSet para estados de reserva únicos
        Set<String> estadosReserva = new TreeSet<>();
        estadosReserva.add("CONFIRMADA");
        estadosReserva.add("PENDIENTE");
        estadosReserva.add("CANCELADA");
        estadosReserva.add("PENDIENTE"); // duplicado, no se agrega
        estadosReserva.add("FINALIZADA");
        
        System.out.println("\n📋 Estados de reserva únicos (ordenados):");
        for (String estado : estadosReserva) {
            System.out.println("Estado: " + estado);
        }
        
        System.out.println();
    }
    
    private static void testTreeMapObjetos() {
        System.out.println("🔹 TEST TREEMAP CON OBJETOS DEL DOMINIO");
        System.out.println("=" .repeat(60));
        
        // Crear objetos del dominio
        Usuario usuario1 = new Usuario("Juan", "Pérez", "juan.perez@uce.edu.ec");
        Usuario usuario2 = new Usuario("María", "García", "maria.garcia@uce.edu.ec");
        Usuario usuario3 = new Usuario("Carlos", "López", "carlos.lopez@uce.edu.ec");
        
        Auditorio auditorio1 = new Auditorio("Auditorio A", 150);
        Auditorio auditorio2 = new Auditorio("Auditorio B", 200);
        Auditorio auditorio3 = new Auditorio("Auditorio C", 100);
        
        // TreeMap que mapea códigos de usuario con objetos Usuario
        Map<String, Usuario> usuariosMap = new TreeMap<>();
        usuariosMap.put(usuario1.getCodigoUsuario(), usuario1);
        usuariosMap.put(usuario2.getCodigoUsuario(), usuario2);
        usuariosMap.put(usuario3.getCodigoUsuario(), usuario3);
        usuariosMap.put(usuario1.getCodigoUsuario(), usuario1); // duplicado, sobrescribe
        
        System.out.println("📋 Usuarios por código:");
        Set<String> codigosUsuarios = usuariosMap.keySet();
        for (String codigo : codigosUsuarios) {
            Usuario usuario = usuariosMap.get(codigo);
            System.out.printf("Código: %s | %s %s | %s%n", 
                codigo, usuario.getNombre(), usuario.getApellido(), usuario.getCorreo());
        }
        
        // TreeMap que mapea códigos de auditorio con objetos Auditorio
        Map<String, Auditorio> auditoriosMap = new TreeMap<>();
        auditoriosMap.put(auditorio1.getCodigoAuditorio(), auditorio1);
        auditoriosMap.put(auditorio2.getCodigoAuditorio(), auditorio2);
        auditoriosMap.put(auditorio3.getCodigoAuditorio(), auditorio3);
        
        System.out.println("\n📋 Auditorios por código:");
        Set<String> codigosAuditorios = auditoriosMap.keySet();
        for (String codigo : codigosAuditorios) {
            Auditorio auditorio = auditoriosMap.get(codigo);
            System.out.printf("Código: %s | %s | Capacidad: %d%n", 
                codigo, auditorio.getNombre(), auditorio.getCapacidad());
        }
        
        System.out.println();
    }
    
    private static void testTreeSetObjetos() {
        System.out.println("🔹 TEST TREESET CON OBJETOS DEL DOMINIO");
        System.out.println("=" .repeat(60));
        
        // Crear objetos del dominio
        Usuario usuario1 = new Usuario("Ana", "Martínez", "ana.martinez@uce.edu.ec");
        Usuario usuario2 = new Usuario("Luis", "Rodríguez", "luis.rodriguez@uce.edu.ec");
        Usuario usuario3 = new Usuario("Patricia", "Hernández", "patricia.hernandez@uce.edu.ec");
        
        // TreeSet de códigos de usuario (String)
        Set<String> codigosUsuarios = new TreeSet<>();
        codigosUsuarios.add(usuario1.getCodigoUsuario());
        codigosUsuarios.add(usuario2.getCodigoUsuario());
        codigosUsuarios.add(usuario3.getCodigoUsuario());
        codigosUsuarios.add(usuario1.getCodigoUsuario()); // duplicado, no se agrega
        
        System.out.println("📋 Códigos de usuario únicos (ordenados):");
        for (String codigo : codigosUsuarios) {
            System.out.println("Código: " + codigo);
        }
        
        // TreeSet de nombres de usuarios (String)
        Set<String> nombresUsuarios = new TreeSet<>();
        nombresUsuarios.add(usuario1.getNombre());
        nombresUsuarios.add(usuario2.getNombre());
        nombresUsuarios.add(usuario3.getNombre());
        nombresUsuarios.add(usuario1.getNombre()); // duplicado, no se agrega
        
        System.out.println("\n📋 Nombres de usuario únicos (ordenados):");
        for (String nombre : nombresUsuarios) {
            System.out.println("Nombre: " + nombre);
        }
        
        // TreeSet de correos de usuarios (String)
        Set<String> correosUsuarios = new TreeSet<>();
        correosUsuarios.add(usuario1.getCorreo());
        correosUsuarios.add(usuario2.getCorreo());
        correosUsuarios.add(usuario3.getCorreo());
        correosUsuarios.add(usuario1.getCorreo()); // duplicado, no se agrega
        
        System.out.println("\n📋 Correos de usuario únicos (ordenados):");
        for (String correo : correosUsuarios) {
            System.out.println("Correo: " + correo);
        }
        
        System.out.println();
    }
} 