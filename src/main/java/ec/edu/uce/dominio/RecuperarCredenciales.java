package ec.edu.uce.dominio;


public class RecuperarCredenciales {
    private String correo;
    private String codigoVerificacion;
    private boolean codigoValidado;

    public RecuperarCredenciales(String correo) {
        this.correo = correo;
        this.codigoVerificacion = generarCodigo();
        this.codigoValidado = false;
    }

    private String generarCodigo() {
        return "123456"; // Simulación
    }

    public boolean validarCodigo(String codigoIngresado) {
        if (codigoVerificacion.equals(codigoIngresado)) {
            codigoValidado = true;
        }
        return codigoValidado;
    }

    // Getters y Setters
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public boolean isCodigoValidado() { return codigoValidado; }

    // Métodos representativos (sin arrays)
    public void crearSolicitud() {
        System.out.println("Solicitud de recuperación creada para " + correo);
    }

    public void leerEstado() {
        System.out.println("Estado de recuperación para " + correo + ": " + (codigoValidado ? "Validado" : "Pendiente"));
    }

    public void actualizarCorreo(String nuevoCorreo) {
        this.correo = nuevoCorreo;
        System.out.println("Correo actualizado a: " + nuevoCorreo);
    }

    public void eliminarSolicitud() {
        System.out.println("Solicitud de recuperación eliminada para " + correo);
    }
}

