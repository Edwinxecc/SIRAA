// Facultad.java
package ec.edu.uce.dominio;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import ec.edu.uce.Util.Validaciones;

/**
 * Representa una facultad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades, incluyendo sus auditorios y usuarios.
 */
public class Facultad implements IAdministrarCRUD, Comparable<Facultad>, java.io.Serializable {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "FAC";
    private static int contadorFacultades = 0;

    private int idFacultad;
    private String codigoFacultad;
    private String nombre;
    private int numAuditorios; // Lleva la cuenta de auditorios "reales"
    private int numUsuarios;   // Lleva la cuenta de usuarios "reales"
    private List<Usuario> usuarios;
    private List<Auditorio> auditorios;
    private Map<Facultad, List<Auditorio>> relacionFacultadAuditorio = new HashMap<>();
    private Map<Facultad, List<Usuario>> relacionFacultadUsuario = new HashMap<>();

    private transient Scanner entrada;
    private transient Validaciones validacion;
    private transient Universidad uni;

    // Constructor completo
    public Facultad(String nombre, int capacidadInicialAuditorios){
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.auditorios = new ArrayList<>();
        this.numAuditorios = 0;
        this.numUsuarios = 0;
        this.idFacultad = generarIdFacultad();
        this.codigoFacultad = generarCodigoFacultad();
        //inicializar();
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

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Auditorio> getAuditorios() {
        return new ArrayList<>(auditorios);
    }

    public Map<Facultad, List<Auditorio>> getRelacionFacultadAuditorio() {
        return relacionFacultadAuditorio;
    }

    public Map<Facultad, List<Usuario>> getRelacionFacultadUsuario() {
        return relacionFacultadUsuario;
    }

    // ========================
    // CRUD de Usuarios
    // ========================

    // Crear Usuario - Sobrecarga
    public void crearUsuario(String nombre, String apellido, String correo) {
        crearUsuario(new Usuario(nombre, apellido, correo));
    }

    public void crearUsuario(Usuario usuario) {
        assert usuario != null : "El usuario no puede ser nulo";
        
        if (validarDuplicadoUsuario(usuario)) {
            System.out.println("[!] Usuario duplicado. No se puede agregar.");
            return;
        }
        usuarios.add(usuario);
        numUsuarios = usuarios.size();
        relacionFacultadUsuario.put(this, usuarios);
    }

    // Listar Usuarios
    public String listarUsuarios() {
        if (usuarios.isEmpty()) {
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
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i) != null && usuarios.get(i).getCodigoUsuario().equals(codigo)) {
                usuarios.set(i, usuario);
                return;
            }
        }
    }

    public void actualizarUsuario(int indice, Usuario usuario) {
        if (indice >= 0 && indice < usuarios.size() && usuario != null) {
            usuarios.set(indice, usuario);
            relacionFacultadUsuario.put(this, usuarios);
        }
    }

    // Eliminar Usuario - Sobrecarga
    public void eliminarUsuario(String codigo) {
        usuarios.removeIf(usuario -> usuario != null && usuario.getCodigoUsuario().equals(codigo));
        numUsuarios = usuarios.size();
        relacionFacultadUsuario.put(this, usuarios);
    }

    public void eliminarUsuario(Usuario usuario) {
        if (usuario == null) return;
        usuarios.remove(usuario);
        numUsuarios = usuarios.size();
        relacionFacultadUsuario.put(this, usuarios);
    }

    /**
     * Valida si un usuario ya existe (duplicado).
     * @param usuario El usuario a validar.
     * @return true si el usuario ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoUsuario(Usuario usuario) {
        if (usuario == null) return false;

        for (Usuario u : usuarios) {
            if (u != null && u.getCodigoUsuario().equals(usuario.getCodigoUsuario())) {
                return true;
            }
        }
        return false;
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
        if (validarDuplicadoAuditorio(auditorio)) {
            System.out.println("[!] Auditorio duplicado. No se puede agregar.");
            return;
        }
        // Asignar la facultad al auditorio
        auditorio.setFacultad(this);
        auditorios.add(auditorio);
        numAuditorios = auditorios.size();
        relacionFacultadAuditorio.put(this, auditorios);
    }

    // Listar Auditorios
    public String listarAuditorios() {
        if (auditorios.isEmpty()) {
            return "[!] No hay auditorios creados.";
        }
        StringBuilder texto = new StringBuilder();
        int i = 0;
        for (Auditorio a : auditorios) {
            texto.append("[").append(i++).append("] ").append(a).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Auditorio - Sobrecarga
    public void actualizarAuditorio(String codigo, Auditorio auditorio) {
        for (int i = 0; i < auditorios.size(); i++) {
            if (auditorios.get(i) != null && auditorios.get(i).getCodigoAuditorio().equals(codigo)) {
                auditorios.set(i, auditorio);
                auditorio.setFacultad(this);
                return;
            }
        }
    }

    public void actualizarAuditorio(int indice, Auditorio auditorio) {
        if (indice >= 0 && indice < auditorios.size() && auditorio != null) {
            auditorios.set(indice, auditorio);
            auditorio.setFacultad(this);
            relacionFacultadAuditorio.put(this, auditorios);
        }
    }

    // Eliminar Auditorio - Sobrecarga
    public void eliminarAuditorio(String codigo) {
        auditorios.removeIf(auditorio -> auditorio != null && auditorio.getCodigoAuditorio().equals(codigo));
        numAuditorios = auditorios.size();
        relacionFacultadAuditorio.put(this, auditorios);
    }

    public void eliminarAuditorio(Auditorio auditorio) {
        if (auditorio == null) return;
        auditorios.remove(auditorio);
        numAuditorios = auditorios.size();
        relacionFacultadAuditorio.put(this, auditorios);
    }

    /**
     * Valida si un auditorio ya existe (duplicado).
     * @param auditorio El auditorio a validar.
     * @return true si el auditorio ya existe, false en caso contrario.
     */
    public boolean validarDuplicadoAuditorio(Auditorio auditorio) {
        if (auditorio == null) return false;

        for (Auditorio a : auditorios) {
            if (a != null && a.getCodigoAuditorio().equals(auditorio.getCodigoAuditorio())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un auditorio por su ID.
     * @param idAuditorio ID del auditorio a buscar.
     * @return El auditorio encontrado o null si no existe.
     */
    public Auditorio buscarAuditorioPorId(int idAuditorio) {
        for (Auditorio auditorio : auditorios) {
            if (auditorio != null && auditorio.getIdAuditorio() == idAuditorio) {
                return auditorio;
            }
        }
        return null;
    }

    /**
     * Verifica si un auditorio pertenece a esta facultad.
     * @param auditorio El auditorio a verificar.
     * @return true si el auditorio pertenece a esta facultad, false en caso contrario.
     */
    public boolean contieneAuditorio(Auditorio auditorio) {
        if (auditorio == null) return false;
        return auditorios.contains(auditorio);
    }

    // ========================
    // Método inicializar
    // ========================

    public void inicializar() {
        // Crear 5 auditorios de ejemplo
        crearAuditorio(new Auditorio("Auditorio Principal", 100, this));
        crearAuditorio(new Auditorio("Auditorio Secundario", 50, this));
        crearAuditorio(new Auditorio("Sala de Conferencias", 75, this));
        crearAuditorio(new Auditorio("Aula Magna", 200, this));
        crearAuditorio(new Auditorio("Sala de Proyecciones", 30, this));

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
        return "| Nombre: "+ nombre + "| Codigo: " + codigoFacultad + "|";
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
        auditorios.sort(new OrdenarAuditorioNombre());
    }

    /**
     * Ordena los auditorios de la facultad por capacidad (ascendente)
     */
    public void ordenarAuditoriosPorCapacidad() {
        auditorios.sort(new OrdenarAuditorioCapacidad());
    }

    /**
     * Ordena los auditorios de la facultad por número de reservas (descendente)
     */
    public void ordenarAuditoriosPorNumReservas() {
        auditorios.sort(new OrdenarAuditorioNumReservas());
    }

    // ========================
    // Métodos de Ordenación para Usuarios
    // ========================

    /**
     * Ordena los usuarios de la facultad por nombre
     */
    public void ordenarUsuariosPorNombre() {
        usuarios.sort(new OrdenarUsuarioNombre());
    }

    /**
     * Ordena los usuarios de la facultad por apellido
     */
    public void ordenarUsuariosPorApellido() {
        usuarios.sort(new OrdenarUsuarioApellido());
    }

    /**
     * Ordena los usuarios de la facultad por correo
     */
    public void ordenarUsuariosPorCorreo() {
        usuarios.sort(new OrdenarUsuarioCorreo());
    }

    /**
     * Ordena los usuarios de la facultad por número de reservas (descendente)
     */
    public void ordenarUsuariosPorNumReservas() {
        usuarios.sort(new OrdenarUsuarioNumReservas());
    }

}
