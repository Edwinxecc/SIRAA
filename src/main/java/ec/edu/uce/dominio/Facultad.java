// Facultad.java
package ec.edu.uce.dominio;

import java.util.Arrays;
import java.util.Comparator;

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
    private Auditorio[] auditorios; // Arreglo para almacenar auditorios
    private int numUsuarios;   // Lleva la cuenta de usuarios "reales"
    private Usuario[] usuarios;    // Arreglo para almacenar usuarios

    // Constructor completo
    public Facultad(String nombre, int capacidadInicialAuditorios){
        this.nombre = nombre;
        // Inicializamos los arreglos con una capacidad inicial (puede ser 0 si quieres que crezcan dinámicamente desde el primer elemento)
        this.auditorios = new Auditorio[capacidadInicialAuditorios > 0 ? capacidadInicialAuditorios : 5]; // Asignamos una capacidad inicial por defecto de 5 si no se especifica
        this.usuarios = new Usuario[5]; // Asignamos una capacidad inicial por defecto de 5
        this.numAuditorios = 0; // Al inicio no hay auditorios
        this.numUsuarios = 0;   // Al inicio no hay usuarios
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
        return numAuditorios; // Devuelve la cantidad de auditorios que realmente existen
    }

    public int getNumUsuarios() {
        return numUsuarios; // Devuelve la cantidad de usuarios que realmente existen
    }

    public Usuario[] getUsuarios() {
        // Devolvemos una copia del arreglo con solo los elementos reales para evitar NullPointerExceptions
        Usuario[] usuariosActivos = new Usuario[numUsuarios];
        System.arraycopy(usuarios, 0, usuariosActivos, 0, numUsuarios);
        return usuariosActivos;
    }

    public Auditorio[] getAuditorios() {
        // Devolvemos una copia del arreglo con solo los elementos reales
        Auditorio[] auditoriosActivos = new Auditorio[numAuditorios];
        System.arraycopy(auditorios, 0, auditoriosActivos, 0, numAuditorios);
        return auditoriosActivos;
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

        // Validar duplicado por correo
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].validarDuplicado(usuario)) {
                System.out.println("[!] Usuario duplicado. No se puede agregar.");
                return;
            }
        }

        // Si el arreglo está lleno, lo redimensionamos para que quepa el nuevo usuario
        if (numUsuarios == usuarios.length) {
            Usuario[] aux = usuarios; // Guardamos el arreglo actual
            usuarios = new Usuario[numUsuarios + 1]; // Creamos un nuevo arreglo con un espacio más
            System.arraycopy(aux, 0, usuarios, 0, numUsuarios); // Copiamos los usuarios existentes
        }
        usuarios[numUsuarios] = usuario; // Agregamos el nuevo usuario en la siguiente posición disponible
        numUsuarios++; // Incrementamos el contador de usuarios reales
    }

    // Listar Usuarios
    public String listarUsuarios() {
        if (numUsuarios == 0) {
            return "[!] No hay usuarios creados.";
        }
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numUsuarios; i++) {
            texto.append("[").append(i).append("] ").append(usuarios[i]).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Usuario - Sobrecarga
    public void actualizarUsuario(int indice, String nuevoNombre, String nuevoApellido, String nuevoCorreo) {
        if (indice >= 0 && indice < numUsuarios) { // Aseguramos que el índice sea válido dentro de los usuarios reales
            usuarios[indice].setNombre(nuevoNombre);
            usuarios[indice].setApellido(nuevoApellido);
            usuarios[indice].setCorreo(nuevoCorreo);
        }
    }

    public void actualizarUsuario(int indice, Usuario usuario) {
        if (indice >= 0 && indice < numUsuarios && usuario != null) { // Aseguramos que el índice sea válido
            usuarios[indice] = usuario;
        }
    }

    // Eliminar Usuario - Sobrecarga
    public void eliminarUsuario(int indice) {
        // Validar que el índice esté dentro del rango de usuarios reales
        if (indice < 0 || indice >= numUsuarios) {
            return;
        }

        // Movemos los elementos para llenar el espacio del eliminado
        for (int i = indice; i < numUsuarios - 1; i++) {
            usuarios[i] = usuarios[i + 1];
        }
        usuarios[numUsuarios - 1] = null; // Opcional: limpiar la última posición
        numUsuarios--; // Decrementamos el contador de usuarios reales
    }

    public void eliminarUsuario(Usuario usuario) {
        if (usuario == null) return;
        for (int i = 0; i < numUsuarios; i++) {
            if (usuarios[i] != null && usuarios[i].equals(usuario)) { // Añadimos null check
                eliminarUsuario(i);
                break;
            }
        }
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

        if (numAuditorios == auditorios.length) {
            Auditorio[] aux = auditorios;
            auditorios = new Auditorio[numAuditorios + 1];
            System.arraycopy(aux, 0, auditorios, 0, numAuditorios);
        }
        auditorios[numAuditorios] = auditorio;
        numAuditorios++;
    }

    // Listar Auditorios
    public String listarAuditorios() {
        if (numAuditorios == 0) {
            return "[!] No hay auditorios creados.";
        }
        StringBuilder texto = new StringBuilder();
        for (int i = 0; i < numAuditorios; i++) {
            texto.append("[").append(i).append("] ").append(auditorios[i]).append("\r\n");
        }
        return texto.toString();
    }

    // Actualizar Auditorio - Sobrecarga
    public void actualizarAuditorio(int indice, String nuevoNombre, int nuevaCapacidad) {
        if (indice >= 0 && indice < numAuditorios) {
            auditorios[indice].setNombre(nuevoNombre);
            auditorios[indice].setCapacidad(nuevaCapacidad);
        }
    }

    public void actualizarAuditorio(int indice, Auditorio auditorio) {
        if (indice >= 0 && indice < numAuditorios && auditorio != null) {
            auditorios[indice] = auditorio;
        }
    }

    // Eliminar Auditorio - Sobrecarga
    public void eliminarAuditorio(int indice) {
        if (indice < 0 || indice >= numAuditorios) {
            return;
        }

        // Movemos los elementos para llenar el espacio del eliminado
        for (int i = indice; i < numAuditorios - 1; i++) {
            auditorios[i] = auditorios[i + 1];
        }
        auditorios[numAuditorios - 1] = null; // Opcional: limpiar la última posición
        numAuditorios--; // Decrementamos el contador de auditorios reales
    }

    public void eliminarAuditorio(Auditorio auditorio) {
        if (auditorio == null) return;
        for (int i = 0; i < numAuditorios; i++) {
            if (auditorios[i] != null && auditorios[i].equals(auditorio)) { // Añadimos null check
                eliminarAuditorio(i);
                break;
            }
        }
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
        System.arraycopy(auditoriosActivos, 0, auditorios, 0, numAuditorios);
    }

    /**
     * Ordena los auditorios de la facultad por capacidad (ascendente)
     */
    public void ordenarAuditoriosPorCapacidad() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, Auditorio.COMPARADOR_POR_CAPACIDAD);
        // Actualizar el arreglo interno
        System.arraycopy(auditoriosActivos, 0, auditorios, 0, numAuditorios);
    }

    /**
     * Ordena los auditorios de la facultad por número de reservas (descendente)
     */
    public void ordenarAuditoriosPorNumReservas() {
        Auditorio[] auditoriosActivos = getAuditorios();
        Arrays.sort(auditoriosActivos, Auditorio.COMPARADOR_POR_NUM_RESERVAS);
        // Actualizar el arreglo interno
        System.arraycopy(auditoriosActivos, 0, auditorios, 0, numAuditorios);
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
        System.arraycopy(usuariosActivos, 0, usuarios, 0, numUsuarios);
    }

    /**
     * Ordena los usuarios de la facultad por apellido
     */
    public void ordenarUsuariosPorApellido() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_APELLIDO);
        // Actualizar el arreglo interno
        System.arraycopy(usuariosActivos, 0, usuarios, 0, numUsuarios);
    }

    /**
     * Ordena los usuarios de la facultad por correo
     */
    public void ordenarUsuariosPorCorreo() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_CORREO);
        // Actualizar el arreglo interno
        System.arraycopy(usuariosActivos, 0, usuarios, 0, numUsuarios);
    }

    /**
     * Ordena los usuarios de la facultad por número de reservas (descendente)
     */
    public void ordenarUsuariosPorNumReservas() {
        Usuario[] usuariosActivos = getUsuarios();
        Arrays.sort(usuariosActivos, Usuario.COMPARADOR_POR_NUM_RESERVAS);
        // Actualizar el arreglo interno
        System.arraycopy(usuariosActivos, 0, usuarios, 0, numUsuarios);
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
