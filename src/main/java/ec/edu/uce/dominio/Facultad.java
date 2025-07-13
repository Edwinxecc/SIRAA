// Facultad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.HashMap;
import ec.edu.uce.datos.UsuarioDAO;
import ec.edu.uce.datos.UsuarioDAOMemoriaImp;
import ec.edu.uce.datos.AuditorioDAO;
import ec.edu.uce.datos.AuditorioDAOMemoriaImp;

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
    private int numUsuarios;   // Lleva la cuenta de usuarios "reales"
    private UsuarioDAO usuarioDAO;
    private AuditorioDAO auditorioDAO;

    // Constructor completo
    public Facultad(String nombre, int capacidadInicialAuditorios){
        this.nombre = nombre;
        this.usuarioDAO = new UsuarioDAOMemoriaImp();
        this.auditorioDAO = new AuditorioDAOMemoriaImp();
        this.numAuditorios = 0;
        this.numUsuarios = 0;
        this.idFacultad = generarIdFacultad();
        this.codigoFacultad = generarCodigoFacultad();
        inicializar();
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
        return auditorioDAO.consultarAuditorios().length;
    }

    public int getNumUsuarios() {
        return usuarioDAO.consultarUsuarios().length;
    }

    public Usuario[] getUsuarios() {
        return usuarioDAO.consultarUsuarios();
    }

    public Auditorio[] getAuditorios() {
        return auditorioDAO.consultarAuditorios();
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
        if (usuarioDAO.buscarPorCodigo(usuario.getCodigoUsuario()) != null) {
            System.out.println("[!] Usuario duplicado. No se puede agregar.");
            return;
        }
        usuarioDAO.crear(usuario);
    }

    // Listar Usuarios
    public String listarUsuarios() {
        Usuario[] usuarios = usuarioDAO.consultarUsuarios();
        if (usuarios.length == 0) {
            return "[!] No hay usuarios creados.";
        }
        StringBuilder texto = new StringBuilder();
        int i = 0;
        for (Usuario u : usuarios) {
            texto.append("[").append(i++).append("] ").append(u).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Usuario - Sobrecarga
    public void actualizarUsuario(String codigo, Usuario usuario) {
        if (usuarioDAO.buscarPorCodigo(codigo) != null && usuario != null) {
            usuarioDAO.editar(usuario);
        }
    }

    public void actualizarUsuario(int indice, Usuario usuario) {
        Usuario[] usuarios = usuarioDAO.consultarUsuarios();
        if (indice >= 0 && indice < usuarios.length && usuario != null) {
            usuarioDAO.editar(usuario);
        }
    }

    // Eliminar Usuario - Sobrecarga
    public void eliminarUsuario(String codigo) {
        usuarioDAO.eliminar(codigo);
    }

    public void eliminarUsuario(Usuario usuario) {
        if (usuario == null) return;
        usuarioDAO.eliminar(usuario.getCodigoUsuario());
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
        if (auditorioDAO.buscarPorCodigo(auditorio.getCodigoAuditorio()) != null) {
            System.out.println("[!] Auditorio duplicado. No se puede agregar.");
            return;
        }
        auditorioDAO.crear(auditorio);
    }

    // Listar Auditorios
    public String listarAuditorios() {
        if (auditorioDAO.consultarAuditorios().length == 0) {
            return "[!] No hay auditorios creados.";
        }
        StringBuilder texto = new StringBuilder();
        int i = 0;
        for (Auditorio a : auditorioDAO.consultarAuditorios()) {
            texto.append("[").append(i++).append("] ").append(a).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Auditorio - Sobrecarga
    public void actualizarAuditorio(String codigo, Auditorio auditorio) {
        if (auditorioDAO.buscarPorCodigo(codigo) != null && auditorio != null) {
            auditorioDAO.editar(auditorio);
        }
    }

    public void actualizarAuditorio(int indice, Auditorio auditorio) {
        if (indice >= 0 && indice < auditorioDAO.consultarAuditorios().length && auditorio != null) {
            auditorioDAO.editar(auditorioDAO.consultarAuditorios()[indice]);
        }
    }

    // Eliminar Auditorio - Sobrecarga
    public void eliminarAuditorio(String codigo) {
        auditorioDAO.eliminar(codigo);
    }

    public void eliminarAuditorio(Auditorio auditorio) {
        if (auditorio == null) return;
        auditorioDAO.eliminar(auditorio.getCodigoAuditorio());
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

    /**
     * Criterio natural de comparación: por idFacultad (ascendente)
     */
    @Override
    public int compareTo(Facultad o) {
        if (this.idFacultad < o.idFacultad) {
            return -1;
        } else if (this.idFacultad > o.idFacultad) {
            return 1;
        }
        // Si los id son iguales, comparar por nombre
        if (this.nombre.compareTo(o.nombre) < 0) {
            return -1;
        } else if (this.nombre.compareTo(o.nombre) > 0) {
            return 1;
        }
        return 0;
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
        // No es necesario actualizar la colección interna, ya que el DAO gestiona el almacenamiento
    }

    /**
     * Ordena los auditorios de la facultad por capacidad (ascendente)
     */
    public void ordenarAuditoriosPorCapacidad() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, new OrdenarAuditorioCapacidad());
    }

    /**
     * Ordena los auditorios de la facultad por número de reservas (descendente)
     */
    public void ordenarAuditoriosPorNumReservas() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, new OrdenarAuditorioNumReservas());
    }

    // ========================
    // Métodos de Ordenación para Usuarios
    // ========================

    /**
     * Ordena los usuarios de la facultad por nombre
     */
    public void ordenarUsuariosPorNombre() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, new OrdenarUsuarioNombre());
    }

    /**
     * Ordena los usuarios de la facultad por apellido
     */
    public void ordenarUsuariosPorApellido() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, new OrdenarUsuarioApellido());
    }

    /**
     * Ordena los usuarios de la facultad por correo
     */
    public void ordenarUsuariosPorCorreo() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, new OrdenarUsuarioCorreo());
        // Actualizar el arreglo interno si es necesario
    }

    /**
     * Ordena los usuarios de la facultad por número de reservas (descendente)
     */
    public void ordenarUsuariosPorNumReservas() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, new OrdenarUsuarioNumReservas());
        // Actualizar el arreglo interno si es necesario
    }

    // ========================
    // Comparadores Estáticos
    // ========================

    /**
     * Comparador para ordenar facultades por número de auditorios (descendente)
     */

    /**
     * Comparador para ordenar facultades por número de usuarios (descendente)
     */

    /**
     * Comparador para ordenar facultades por ID (ascendente)
     */
}
