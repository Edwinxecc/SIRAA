/**
 * Representa una facultad en el sistema SIRAA.
 * Esta clase maneja la información de las facultades, incluyendo sus auditorios y usuarios.
 */
package ec.edu.uce.dominio;
// sin listas
import ec.edu.uce.Util.Validaciones;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Facultad {
    private String nombre;
    private int numeroAuditorios;
    //private final Validaciones val; validart internamente en cada metodo
    //private final List<Usuario> usuarios;
    private Usuario[] usuarios;
    //private final List<Auditorio> auditorios;
    private Auditorio[] auditorios;
    /** Contador para generar IDs únicos de auditorios */
    private static int contadorAuditorios = 1;
    //private static List<Reserva> listaReservas = new ArrayList<>(); //
    private Reserva [] reservas;
    /** Lista estática que simula una base de datos en memoria */
    //private static final List<Facultad> facultades = new ArrayList<>();

    /**
     * Constructor por defecto.
     * Inicializa la facultad con valores predeterminados.
     */
    public Facultad() {
        this.nombre = "Ingenieria y Ciencias Aplicadas";
        this.numeroAuditorios = 1;
        this.val = new Validaciones();
        this.usuarios = new ArrayList<>();
        this.auditorios = new ArrayList<>();
        inicializarAuditorios();
    }

    /**
     * Constructor con parámetros.
     * @param nombre Nombre de la facultad
     * @param numAuditorios Número de auditorios
     */
    public Facultad(String nombre, int numAuditorios) {
        this.val = new Validaciones();
        this.usuarios = new ArrayList<>();
        this.auditorios = new ArrayList<>();
        setNombre(nombre);
        setNumeroAuditorios(numAuditorios);
        inicializarAuditorios();
    }

    /**
     * Inicializa los auditorios de la facultad.
     * Crea la cantidad especificada de auditorios con capacidad predeterminada.
     */
    private void inicializarAuditorios() {
        for (int i = 0; i < numeroAuditorios; i++) {
            auditorios.add(new Auditorio(contadorAuditorios++, "Auditorio " + (i + 1), 100));
        }
    }

    /**
     * Obtiene el nombre de la facultad.
     * @return Nombre de la facultad
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la facultad.
     * @param nombre Nombre a establecer
     * @throws IllegalArgumentException si el nombre es inválido
     */
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la facultad no puede estar vacío");
        }
        String nombreValidado = val.ValidacionTexto(nombre.trim(), "Nombre");
        if (nombreValidado == null) {
            throw new IllegalArgumentException("El nombre de la facultad no es válido");
        }
        this.nombre = nombreValidado;
    }

    /**
     * Obtiene el número de auditorios.
     * @return Número de auditorios
     */
    public int getNumeroAuditorios() {
        return numeroAuditorios;
    }

    /**
     * Establece el número de auditorios.
     * @param numeroAuditorios Número de auditorios a establecer
     * @throws IllegalArgumentException si el número es menor o igual a 0
     */
    public void setNumeroAuditorios(int numeroAuditorios) {
        if (numeroAuditorios <= 0) {
            throw new IllegalArgumentException("El número de auditorios debe ser mayor a 0");
        }
        this.numeroAuditorios = numeroAuditorios;
    }

    /**
     * Obtiene la lista de usuarios de la facultad.
     * @return Copia de la lista de usuarios
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Obtiene la lista de auditorios de la facultad.
     * @return Copia de la lista de auditorios
     */
    public List<Auditorio> getAuditorios() {
        return new ArrayList<>(auditorios);
    }

    /**
     * Crea un nuevo usuario en la facultad.
     * @param nombre Nombre del usuario
     * @param apellido Apellido del usuario
     * @param correo Correo institucional del usuario
     * @throws IllegalArgumentException si los datos son inválidos o el correo ya existe
     */
    public void crearUsuario(String nombre, String apellido, String correo) {
        if (nombre == null || apellido == null || correo == null) {
            throw new IllegalArgumentException("Los datos del usuario no pueden ser nulos");
        }

        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuario.generarId());
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        
        if (buscarUsuarioPorCorreo(correo) != null) {
            throw new IllegalArgumentException("Ya existe un usuario con ese correo");
        }
        
        usuarios.add(usuario);
    }

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscarUsuario(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsuarioId() == id)
                .findFirst()
                .orElse(null);
    }

    /**
     * Busca un usuario por su correo.
     * @param correo Correo del usuario a buscar
     * @return Usuario encontrado o null si no existe
     */
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarios.stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id ID del usuario a eliminar
     * @return true si se eliminó el usuario, false si no se encontró
     */
    public boolean eliminarUsuario(int id) {
        return usuarios.removeIf(usuario -> usuario.getUsuarioId() == id);
    }

    /**
     * Busca un auditorio por su nombre.
     * @param nombre Nombre del auditorio a buscar
     * @return Auditorio encontrado o null si no existe
     */
    public Auditorio buscarAuditorio(String nombre) {
        return auditorios.stream()
                .filter(auditorio -> auditorio.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    /**
     * Crea una nueva facultad en el sistema.
     * @param facultad Facultad a crear
     * @return true si se creó la facultad, false si ya existe una con el mismo nombre
     */
    public static boolean crearFacultad(Facultad facultad) {
        if (facultad == null) {
            return false;
        }
        if (buscarFacultadPorNombre(facultad.getNombre()) != null) {
            return false;
        }
        return facultades.add(facultad);
    }

    /**
     * Obtiene la lista de todas las facultades.
     * @return Copia de la lista de facultades
     */
    public static List<Facultad> listarFacultades() {
        return new ArrayList<>(facultades);
    }

    /**
     * Busca una facultad por su nombre.
     * @param nombre Nombre de la facultad a buscar
     * @return Facultad encontrada o null si no existe
     */
    public static Facultad buscarFacultadPorNombre(String nombre) {
        if (nombre == null) return null;
        return facultades.stream()
                .filter(f -> f.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    /**
     * Edita una facultad existente.
     * @param nombreOriginal Nombre actual de la facultad
     * @param nuevoNombre Nuevo nombre
     * @param nuevosAuditorios Nuevo número de auditorios
     * @return true si se editó la facultad, false si no se encontró o el nuevo nombre ya existe
     */
    public static boolean editarFacultad(String nombreOriginal, String nuevoNombre, int nuevosAuditorios) {
        Facultad facultad = buscarFacultadPorNombre(nombreOriginal);
        if (facultad == null) return false;

        if (!nombreOriginal.equalsIgnoreCase(nuevoNombre) && 
            buscarFacultadPorNombre(nuevoNombre) != null) {
            return false;
        }

        facultad.setNombre(nuevoNombre);
        facultad.setNumeroAuditorios(nuevosAuditorios);
        return true;
    }

    /**
     * Elimina una facultad por su nombre.
     * @param nombre Nombre de la facultad a eliminar
     * @return true si se eliminó la facultad, false si no se encontró
     */
    public static boolean eliminarFacultad(String nombre) {
        return facultades.removeIf(f -> f.getNombre().equalsIgnoreCase(nombre));
    }

    /**
     * Muestra todas las facultades registradas.
     */
    public void buscarFacultades() {
        List<Facultad> todas = listarFacultades();
        if (todas.isEmpty()) {
            System.out.println("No hay facultades registradas.");
        } else {
            System.out.println("\n=== FACULTADES REGISTRADAS ===");
            for (Facultad f : todas) {
                System.out.println(f);
            }
        }
    }

    /**
     * Compara esta facultad con otro objeto.
     * @param o Objeto a comparar
     * @return true si son iguales, false en caso contrario
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facultad facultad = (Facultad) o;
        return Objects.equals(nombre, facultad.nombre);
    }

    /**
     * Genera un código hash para la facultad.
     * @return Código hash basado en el nombre
     */
    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }
    /**
     * Crea una nueva reserva y la agrega a la lista.
     * @param reserva Reserva a crear
     */
    public static void crearReserva(Reserva reserva) {
        listaReservas.add(reserva);
        System.out.println("[✓] Reserva creada.");
    }

    /**
     * Busca una reserva por su ID.
     * @param id ID de la reserva a buscar
     * @return Reserva encontrada o null si no existe
     */
    public static Reserva consultarReserva(int id) {
        for (Reserva r : listaReservas) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }

    /**
     * Obtiene una copia de la lista de todas las reservas.
     * @return Lista de reservas
     */
    public static List<Reserva> listarReservas() {
        return new ArrayList<>(listaReservas);
    }

    /**
     * Actualiza las fechas de una reserva existente.
     * @param id ID de la reserva a actualizar
     * @param nuevaInicio Nueva fecha de inicio
     * @param nuevaFin Nueva fecha de fin
     * @return true si se actualizó la reserva, false si no se encontró
     */
    public static boolean actualizarReserva(int id, LocalDateTime nuevaInicio, LocalDateTime nuevaFin) {
        Reserva reserva = consultarReserva(id);
        if (reserva != null) {
            reserva.setFechaInicio(nuevaInicio);
            reserva.setFechaFin(nuevaFin);
            System.out.println("[✓] Reserva actualizada.");
            return true;
        }
        return false;
    }

    /**
     * Elimina una reserva y libera sus equipos asignados.
     * @param id ID de la reserva a eliminar
     * @return true si se eliminó la reserva, false si no se encontró
     */
    public static boolean eliminarReserva(int id) {
        Reserva reserva = consultarReserva(id);
        if (reserva != null) {
            for (Equipo eq : reserva.getEquiposAsignados()) {
                eq.setDisponible(true);
            }
            listaReservas.remove(reserva);
            System.out.println("[✓] Reserva eliminada.");
            return true;
        }
        return false;
    }


    /**
     * Devuelve una representación en texto de la facultad.
     * @return String con los datos de la facultad
     */
    @Override
    public String toString() {
        return String.format("Facultad{nombre='%s', numeroAuditorios=%d, usuarios=%d}",
                nombre, numeroAuditorios, usuarios.size());
    }
}
