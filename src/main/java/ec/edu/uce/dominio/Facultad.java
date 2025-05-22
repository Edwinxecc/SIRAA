package ec.edu.uce.dominio;

import ec.edu.uce.Util.Validaciones;
import java.util.ArrayList;
import java.util.List;

public class Facultad {
    private String nombre;
    private int numeroAuditorios;
    private Validaciones val = new Validaciones();
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Auditorio> auditorios = new ArrayList<>();

    public Facultad() {
        this.nombre = "Ingenieria y Ciencias Aplicadas";
        this.numeroAuditorios = 1;
    }

    public Facultad(String nombre, int numAuditorios) {
        setNombre(nombre);
        setNumeroAuditorios(numAuditorios);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        val.ValidacionTexto(nombre, "Nombre");
        this.nombre = nombre;
    }

    public int getNumeroAuditorios() {
        return numeroAuditorios;
    }

    public void setNumeroAuditorios(int numeroAuditorios) {
        if (numeroAuditorios > 0) {
            this.numeroAuditorios = numeroAuditorios;
        } else {
            throw new IllegalArgumentException("El número de auditorios debe ser mayor a 0.");
        }
    }

    public void crearUsuario(String nombre, String apellido, String correo) {
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuario.generarId());
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setCorreo(correo);
        usuarios.add(usuario);
    }

    public Usuario buscarUsuario(int id) {
        for (Usuario usuario : usuarios) {
            if (usuario.getUsuarioId() == id) {
                return usuario;
            }
        }
        return null;
    }

    public boolean editarUsuario(int id, String nuevoNombre, String nuevoApellido, String nuevoCorreo) {
        Usuario usuario = buscarUsuario(id);
        if (usuario != null) {
            usuario.setNombre(nuevoNombre);
            usuario.setApellido(nuevoApellido);
            usuario.setCorreo(nuevoCorreo);
            return true;
        }
        return false;
    }

    public boolean eliminarUsuario(int id) {
        Usuario usuario = buscarUsuario(id);
        if (usuario != null) {
            usuarios.remove(usuario);
            return true;
        }
        return false;
    }

    public void crearAuditorio(int id, String nombre, int capacidad) {
        Auditorio auditorio = new Auditorio(id, nombre, capacidad);
        auditorios.add(auditorio);
        numeroAuditorios = auditorios.size();
    }

    public Auditorio buscarAuditorio(int id) {
        for (Auditorio auditorio : auditorios) {
            if (auditorio.getId() == id) {
                return auditorio;
            }
        }
        return null;
    }

    public boolean editarAuditorio(int id, String nuevoNombre, int nuevaCapacidad) {
        Auditorio auditorio = buscarAuditorio(id);
        if (auditorio != null) {
            auditorio.setNombre(nuevoNombre);
            auditorio.setCapacidad(nuevaCapacidad);
            return true;
        }
        return false;
    }

    public boolean eliminarAuditorio(int id) {
        Auditorio auditorio = buscarAuditorio(id);
        if (auditorio != null) {
            auditorios.remove(auditorio);
            numeroAuditorios = auditorios.size();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Facultad: " + nombre + " (Auditorios: " + numeroAuditorios + ")";
    }
}
