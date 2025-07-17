package ec.edu.uce.dominio;

/**
 * Excepción específica para errores relacionados con reservas.
 */
public class ReservaException extends SIRAAException {
    
    /**
     * Constructor por defecto.
     */
    public ReservaException() {
        super("Error en la reserva");
    }
    
    /**
     * Constructor con mensaje personalizado.
     * @param mensaje Mensaje de error
     */
    public ReservaException(String mensaje) {
        super("Error en la reserva: " + mensaje);
    }
    
    /**
     * Constructor con mensaje y causa.
     * @param mensaje Mensaje de error
     * @param causa Causa del error
     */
    public ReservaException(String mensaje, Throwable causa) {
        super("Error en la reserva: " + mensaje, causa);
    }
} 