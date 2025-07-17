package ec.edu.uce.dominio;

/**
 * Excepción personalizada para el sistema SIRAA.
 * Se usa para manejar errores específicos del sistema.
 */
public class SIRAAException extends Exception {
    
    /**
     * Constructor por defecto.
     */
    public SIRAAException() {
        super("Error en el sistema SIRAA");
    }
    
    /**
     * Constructor con mensaje personalizado.
     * @param mensaje Mensaje de error
     */
    public SIRAAException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Constructor con mensaje y causa.
     * @param mensaje Mensaje de error
     * @param causa Causa del error
     */
    public SIRAAException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
} 