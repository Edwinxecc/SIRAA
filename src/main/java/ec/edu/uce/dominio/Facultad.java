package ec.edu.uce.dominio;

import ec.edu.uce.Util.Validaciones;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Facultad {
    private String nombre;
    private int numeroAuditorios;
    private final Validaciones val;
    private final List<Usuario> usuarios;
    private final List<Auditorio> auditorios;
    private static int contadorAuditorios = 1;
    private static final List<Facultad> facultades = new ArrayList<>();

    public Facultad() {
        this.nombre = "Ingenieria y Ciencias Aplicadas";
        this.numeroAuditorios = 1;
        this.val = new Validaciones();
        this.usuarios = new ArrayList<>();
        this.auditorios = new ArrayList<>();
        inicializarAuditorios();
    }

    public Facultad(String nombre, int numAuditorios) {
        this.val = new Validaciones();
        this.usuarios = new ArrayList<>();
        this.auditorios = new ArrayList<>();
        setNombre(nombre);
        setNumeroAuditorios(numAuditorios);
        inicializarAuditorios();
    }

    private void inicializarAuditorios() {
        for (int i = 0; i < numeroAuditorios; i++) {
            auditorios.add(new Auditorio(contadorAuditorios++, "Auditorio " + (i + 1), 100));
        }
    }

    public String getNombre() {
        return nombre;
    }

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

    public int getNumeroAuditorios() {
        return numeroAuditorios;
    }

    public void setNumeroAuditorios(int numeroAuditorios) {
        if (numeroAuditorios <= 0) {
            throw new IllegalArgumentException("El número de auditorios debe ser mayor a 0");
        }
        this.numeroAuditorios = numeroAuditorios;
    }

    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    public List<Auditorio> getAuditorios() {
        return new ArrayList<>(auditorios);
    }

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

    public Usuario buscarUsuario(int id) {
        return usuarios.stream()
                .filter(usuario -> usuario.getUsuarioId() == id)
                .findFirst()
                .orElse(null);
    }

    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarios.stream()
                .filter(usuario -> usuario.getCorreo().equalsIgnoreCase(correo))
                .findFirst()
                .orElse(null);
    }

    public boolean eliminarUsuario(int id) {
        return usuarios.removeIf(usuario -> usuario.getUsuarioId() == id);
    }

    public Auditorio buscarAuditorio(String nombre) {
        return auditorios.stream()
                .filter(auditorio -> auditorio.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    // Métodos CRUD para gestionar facultades
    public static boolean crearFacultad(Facultad facultad) {
        if (facultad == null) {
            return false;
        }
        // Verificar si ya existe una facultad con el mismo nombre
        if (buscarFacultadPorNombre(facultad.getNombre()) != null) {
            return false;
        }
        return facultades.add(facultad);
    }

    public static List<Facultad> listarFacultades() {
        return new ArrayList<>(facultades);
    }

    public static Facultad buscarFacultadPorNombre(String nombre) {
        if (nombre == null) return null;
        return facultades.stream()
                .filter(f -> f.getNombre().equalsIgnoreCase(nombre))
                .findFirst()
                .orElse(null);
    }

    public static boolean editarFacultad(String nombreOriginal, String nuevoNombre, int nuevosAuditorios) {
        Facultad facultad = buscarFacultadPorNombre(nombreOriginal);
        if (facultad == null) return false;

        // Verificar que el nuevo nombre no exista (si es diferente al actual)
        if (!nombreOriginal.equalsIgnoreCase(nuevoNombre) && 
            buscarFacultadPorNombre(nuevoNombre) != null) {
            return false;
        }

        facultad.setNombre(nuevoNombre);
        facultad.setNumeroAuditorios(nuevosAuditorios);
        return true;
    }

    public static boolean eliminarFacultad(String nombre) {
        return facultades.removeIf(f -> f.getNombre().equalsIgnoreCase(nombre));
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facultad facultad = (Facultad) o;
        return Objects.equals(nombre, facultad.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre);
    }

    @Override
    public String toString() {
        return String.format("Facultad{nombre='%s', numeroAuditorios=%d, usuarios=%d}",
                nombre, numeroAuditorios, usuarios.size());
    }
}
