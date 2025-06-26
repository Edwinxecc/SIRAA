// Facultad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;

/**
 * Representa una facultad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades, incluyendo sus auditorios y usuarios.
 */
public class Facultad implements IAdministrarCRUD, Comparable<Facultad> {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "FAC";
    private static int contadorFacultades = 0;
    
    private int idFacultad;
    private String codigoFacultad;
    private String nombre;
    private int numAuditorios; // Lleva la cuenta de auditorios "reales"
    private Map<String, Auditorio> auditorios;
    private int numUsuarios;   // Lleva la cuenta de usuarios "reales"
    private Map<String, Usuario> usuarios;

    // Constructor completo
    public Facultad(String nombre, int capacidadInicialAuditorios){
        this.nombre = nombre;
        this.auditorios = new HashMap<>();
        this.usuarios = new HashMap<>();
        this.numAuditorios = 0;
        this.numUsuarios = 0;
        this.idFacultad = generarIdFacultad();
        this.codigoFacultad = generarCodigoFacultad();
    }

    // Constructor que recibe solo el nombre
    public Facultad(String nombre) {
        this(nombre, 5); // Llama al constructor completo con una capacidad por defecto
    }

    // Constructor por defecto
    public Facultad(){
        this("Sin nombre", 5); // Llama al constructor completo con una capacidad por defecto
    }

    // Método para generar IDs automáticos
    private int generarIdFacultad() {
        contadorFacultades++;
        return contadorFacultades;
    }

    // Método para generar códigos automáticos
    private String generarCodigoFacultad() {
        return PREFIJO_CODIGO + String.format("%04d", contadorFacultades);
    }

    // Getters y setters
    public int getIdFacultad() {
        return idFacultad;
    }

    public String getCodigoFacultad() {
        return codigoFacultad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre != null){
            this.nombre = nombre;
        }
    }

    public int getNumAuditorios() {
        return auditorios.size();
    }

    public int getNumUsuarios() {
        return usuarios.size();
    }

    public Usuario[] getUsuarios() {
        return usuarios.values().toArray(new Usuario[0]);
    }

    public Auditorio[] getAuditorios() {
        return auditorios.values().toArray(new Auditorio[0]);
    }

    // ========================
    // CRUD de Usuarios
    // ========================

    // Crear Usuario - Sobrecarga
    public void crearUsuario(String nombre, String apellido, String correo) {
        crearUsuario(new Usuario(nombre, apellido, correo));
    }

    public void crearUsuario(Usuario usuario) {
        if (usuario == null) return;
        if (usuarios.containsKey(usuario.getCodigoUsuario())) {
            System.out.println("[!] Usuario duplicado. No se puede agregar.");
            return;
        }
        usuarios.put(usuario.getCodigoUsuario(), usuario);
    }

    // Listar Usuarios
    public String listarUsuarios() {
        if (usuarios.isEmpty()) {
            return "[!] No hay usuarios creados.";
        }
        StringBuilder texto = new StringBuilder();
        int i = 0;
        for (Usuario u : usuarios.values()) {
            texto.append("[").append(i++).append("] ").append(u).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Usuario - Sobrecarga
    public void actualizarUsuario(String codigo, Usuario usuario) {
        if (usuarios.containsKey(codigo) && usuario != null) {
            usuarios.put(codigo, usuario);
        }
    }

    public void actualizarUsuario(int indice, Usuario usuario) {
        if (indice >= 0 && indice < numUsuarios && usuario != null) { // Aseguramos que el índice sea válido
            usuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    // Eliminar Usuario - Sobrecarga
    public void eliminarUsuario(String codigo) {
        usuarios.remove(codigo);
    }

    public void eliminarUsuario(Usuario usuario) {
        if (usuario == null) return;
        usuarios.remove(usuario.getCodigoUsuario());
    }

    // ========================
    // CRUD de Auditorios
    // ========================

    // Crear Auditorio - Sobrecarga
    public void crearAuditorio() {
        crearAuditorio(new Auditorio());
    }

    public void crearAuditorio(Auditorio auditorio){
        if (auditorio == null) return;
        if (auditorios.containsKey(auditorio.getCodigoAuditorio())) {
            System.out.println("[!] Auditorio duplicado. No se puede agregar.");
            return;
        }
        auditorios.put(auditorio.getCodigoAuditorio(), auditorio);
    }

    // Listar Auditorios
    public String listarAuditorios() {
        if (auditorios.isEmpty()) {
            return "[!] No hay auditorios creados.";
        }
        StringBuilder texto = new StringBuilder();
        int i = 0;
        for (Auditorio a : auditorios.values()) {
            texto.append("[").append(i++).append("] ").append(a).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Auditorio - Sobrecarga
    public void actualizarAuditorio(String codigo, Auditorio auditorio) {
        if (auditorios.containsKey(codigo) && auditorio != null) {
            auditorios.put(codigo, auditorio);
        }
    }

    public void actualizarAuditorio(int indice, Auditorio auditorio) {
        if (indice >= 0 && indice < numAuditorios && auditorio != null) {
            auditorios.put(auditorios.values().toArray(new Auditorio[0])[indice].getCodigoAuditorio(), auditorio);
        }
    }

    // Eliminar Auditorio - Sobrecarga
    public void eliminarAuditorio(String codigo) {
        auditorios.remove(codigo);
    }

    public void eliminarAuditorio(Auditorio auditorio) {
        if (auditorio == null) return;
        auditorios.remove(auditorio.getCodigoAuditorio());
    }

    // ========================
    // Método inicializar
    // ========================

    public void inicializar() {
        // Crear 5 auditorios de ejemplo
        crearAuditorio(new Auditorio("Auditorio Principal", 100));
        crearAuditorio(new Auditorio("Auditorio Secundario", 50));
        crearAuditorio(new Auditorio("Sala de Conferencias", 75));
        crearAuditorio(new Auditorio("Aula Magna", 200));
        crearAuditorio(new Auditorio("Sala de Proyecciones", 30));

        // Crear 5 usuarios de ejemplo
        crearUsuario(new Usuario("Juan", "Pérez", "juan.perez@email.com"));
        crearUsuario(new Usuario("María", "García", "maria.garcia@email.com"));
        crearUsuario(new Usuario("Carlos", "López", "carlos.lopez@email.com"));
        crearUsuario(new Usuario("Ana", "Martínez", "ana.martinez@email.com"));
        crearUsuario(new Usuario("Luis", "Rodríguez", "luis.rodriguez@email.com"));
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Facultad)) return false;

        Facultad otraFacultad = (Facultad) obj;
        return this.idFacultad == otraFacultad.idFacultad &&
                this.codigoFacultad.equals(otraFacultad.codigoFacultad) &&
                this.nombre.equals(otraFacultad.nombre);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Facultad facultad) {
        if (facultad == null) return false;
        return this.codigoFacultad.equals(facultad.codigoFacultad) ||
                this.nombre.equals(facultad.nombre);
    }

    // Métodos de la interfaz IAdministrarCRUD

    @Override
    public String nuevo(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadNueva = (Facultad) obj;
        if (validarDuplicado(facultadNueva)) {
            return "[!] La facultad ya existe.";
        }
        // Aquí se simula la creación de la facultad
        return "[✓] Facultad creada correctamente.";
    }

    @Override
    public String editar(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadEditada = (Facultad) obj;
        // Aquí se simula la edición de la facultad
        this.nombre = facultadEditada.getNombre();
        return "[✓] Facultad editada correctamente.";
    }

    @Override
    public String borrar(Object obj) {
        if (!(obj instanceof Facultad)) {
            return "[!] El objeto no es una Facultad válida.";
        }
        Facultad facultadABorrar = (Facultad) obj;
        if (this.equals(facultadABorrar)) {
            // Aquí se simula la eliminación de la facultad
            return "[✓] Facultad eliminada correctamente.";
        }
        return "[!] Facultad no encontrada.";
    }

    @Override
    public Object buscarPorId(Integer id) {
        if (id == null || id < 0) {
            return null;
        }
        if (id == this.idFacultad) {
            return this;
        }
        return null;
    }

    @Override
    public String listar() {
        return toString();
    }

    @Override
    public String toString() {
        return String.format("┌─ FACULTAD ──────────────────────────────────────────────────────────┐%n" +
                           "│ Código: %-15s │ ID: %-8d │%n" +
                           "│ Nombre: %-50s │%n" +
                           "│ Auditorios: %-3d │ Usuarios: %-3d │%n" +
                           "└─────────────────────────────────────────────────────────────────────┘",
                           codigoFacultad, idFacultad,
                           nombre,
                           numAuditorios, numUsuarios);
    }

    public void setNumAuditorios(int nuevaCantidad) {
        if (nuevaCantidad >= 0) {
            this.numAuditorios = nuevaCantidad;
        }
    }

    @Override
    public int compareTo(Facultad otraFacultad) {
        return this.nombre.compareTo(otraFacultad.nombre);
    }

    // ========================
    // Métodos de Ordenación para Auditorios
    // ========================

    /**
     * Ordena los auditorios de la facultad por nombre
     */
    public void ordenarAuditoriosPorNombre() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos);
        // Actualizar el arreglo interno
        auditorios.clear();
        for (Auditorio auditorio : auditoriosActivos) {
            auditorios.put(auditorio.getCodigoAuditorio(), auditorio);
        }
    }

    /**
     * Ordena los auditorios de la facultad por capacidad (ascendente)
     */
    public void ordenarAuditoriosPorCapacidad() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, Auditorio.COMPARADOR_POR_CAPACIDAD);
        // Actualizar el arreglo interno
        auditorios.clear();
        for (Auditorio auditorio : auditoriosActivos) {
            auditorios.put(auditorio.getCodigoAuditorio(), auditorio);
        }
    }

    /**
     * Ordena los auditorios de la facultad por número de reservas (descendente)
     */
    public void ordenarAuditoriosPorNumReservas() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, Auditorio.COMPARADOR_POR_NUM_RESERVAS);
        // Actualizar el arreglo interno
        auditorios.clear();
        for (Auditorio auditorio : auditoriosActivos) {
            auditorios.put(auditorio.getCodigoAuditorio(), auditorio);
        }
    }

    // ========================
    // Métodos de Ordenación para Usuarios
    // ========================

    /**
     * Ordena los usuarios de la facultad por nombre
     */
    public void ordenarUsuariosPorNombre() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_NOMBRE);
        // Actualizar el arreglo interno
        usuarios.clear();
        for (Usuario usuario : usuariosActivos) {
            usuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    /**
     * Ordena los usuarios de la facultad por apellido
     */
    public void ordenarUsuariosPorApellido() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_APELLIDO);
        // Actualizar el arreglo interno
        usuarios.clear();
        for (Usuario usuario : usuariosActivos) {
            usuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    /**
     * Ordena los usuarios de la facultad por correo
     */
    public void ordenarUsuariosPorCorreo() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_CORREO);
        // Actualizar el arreglo interno
        usuarios.clear();
        for (Usuario usuario : usuariosActivos) {
            usuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    /**
     * Ordena los usuarios de la facultad por número de reservas (descendente)
     */
    public void ordenarUsuariosPorNumReservas() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_NUM_RESERVAS);
        // Actualizar el arreglo interno
        usuarios.clear();
        for (Usuario usuario : usuariosActivos) {
            usuarios.put(usuario.getCodigoUsuario(), usuario);
        }
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar facultades por número de auditorios (descendente)
     */
    public static final Comparator<Facultad> COMPARADOR_POR_NUM_AUDITORIOS = new Comparator<Facultad>() {
        @Override
        public int compare(Facultad f1, Facultad f2) {
            return Integer.compare(f2.getNumAuditorios(), f1.getNumAuditorios()); // Descendente
        }
    };

    /**
     * Comparador para ordenar facultades por número de usuarios (descendente)
     */
    public static final Comparator<Facultad> COMPARADOR_POR_NUM_USUARIOS = new Comparator<Facultad>() {
        @Override
        public int compare(Facultad f1, Facultad f2) {
            return Integer.compare(f2.getNumUsuarios(), f1.getNumUsuarios()); // Descendente
        }
    };

    /**
     * Comparador para ordenar facultades por ID (ascendente)
     */
    public static final Comparator<Facultad> COMPARADOR_POR_ID = new Comparator<Facultad>() {
        @Override
        public int compare(Facultad f1, Facultad f2) {
            return Integer.compare(f1.getIdFacultad(), f2.getIdFacultad());
        }
    };
}
