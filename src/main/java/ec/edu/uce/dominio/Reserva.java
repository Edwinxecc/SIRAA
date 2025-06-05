package ec.edu.uce.dominio;

import java.util.Date;

/**
 * Representa una reserva de auditorio en el sistema SIRAA.
 * Esta clase maneja la información de las reservas, incluyendo usuario, auditorio, fechas y equipos asignados.
 */
public class Reserva {
    // Variables static final para generación automática de códigos
    private static final String PREFIJO_CODIGO = "RES";
    private static int contadorReservas = 0;
    
    private String codigoReserva;
    private int idReserva;
    private Date fechaInicio;
    private Date fechaFin;
    private Equipo[] equipos;
    private int numEquipos = 0;
    private Estado estado;

    // Constructores
    public Reserva(int idReserva, Date fechaInicio, Date fechaFin){
        this.idReserva = idReserva;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.equipos = new Equipo[0];
        this.estado = Estado.PENDIENTE;
        this.codigoReserva = generarCodigoReserva();
    }

    public Reserva(){
        this(0, new Date(1990 - 1900, 0, 1), new Date(1990 - 1900, 0, 1));
    }

    // Constructor con objeto Reserva
    public Reserva(Reserva reserva) {
        this(reserva.getIdReserva(), reserva.getFechaInicio(), reserva.getFechaFin());
        this.estado = reserva.getEstado();
        // Copiar equipos
        for (Equipo equipo : reserva.getEquipos()) {
            if (equipo != null) {
                crearEquipo(equipo);
            }
        }
    }

    // Método para generar códigos automáticos
    private String generarCodigoReserva() {
        contadorReservas++;
        return PREFIJO_CODIGO + String.format("%04d", contadorReservas);
    }

    // Método equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Reserva)) return false;
        
        Reserva otraReserva = (Reserva) obj;
        return this.codigoReserva.equals(otraReserva.codigoReserva) &&
               this.idReserva == otraReserva.idReserva &&
               this.fechaInicio.equals(otraReserva.fechaInicio) &&
               this.fechaFin.equals(otraReserva.fechaFin);
    }

    // Método para validar duplicados
    public boolean validarDuplicado(Object obj) {
        if (!(obj instanceof Reserva)) return false;
        
        Reserva otraReserva = (Reserva) obj;
        return this.codigoReserva.equals(otraReserva.codigoReserva) ||
               (this.fechaInicio.equals(otraReserva.fechaInicio) && 
                this.fechaFin.equals(otraReserva.fechaFin));
    }

    // Getters y setters
    public String getCodigoReserva() {
        return codigoReserva;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        if (estado != null) {
            this.estado = estado;
        }
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        if (idReserva > 0 && this.idReserva != idReserva){
            this.idReserva = idReserva;
        }
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        if (fechaInicio != null){
            this.fechaInicio = fechaInicio;
        }
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        if (fechaFin != null) {
            this.fechaFin = fechaFin;
        }
    }

    public Equipo[] getEquipos() {
        return equipos;
    }

    // ========================
    // CRUD de Equipos
    // ========================

    // Crear Equipo - Sobrecarga de métodos
    public void crearEquipo(String nombre, String categoria, boolean disponible) {
        crearEquipo(new Equipo(nombre, categoria, disponible));
    }

    public void crearEquipo(Equipo equipo) {
        if (equipo == null) return;
        
        if (numEquipos == equipos.length) {
            Equipo[] aux = equipos;
            equipos = new Equipo[numEquipos + 1];
            System.arraycopy(aux, 0, equipos, 0, numEquipos);
        }
        equipos[numEquipos] = equipo;
        numEquipos++;
    }

    // Listar Equipos - Sobrecarga de métodos
    public String listarEquipos() {
        return listarEquipos(false);
    }

    public String listarEquipos(boolean soloDisponibles) {
        if (numEquipos == 0) {
            return "No hay equipos asignados a esta reserva.";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numEquipos; i++) {
            if (!soloDisponibles || equipos[i].getDisponibilidad()) {
                sb.append(String.format("[%d] Nombre: %s | Categoría: %s | Disponible: %s%n",
                        i,
                        equipos[i].getNombre(),
                        equipos[i].getCategoria(),
                        equipos[i].getDisponibilidad() ? "Sí" : "No"
                ));
            }
        }
        return sb.toString();
    }

    // Actualizar Equipo - Sobrecarga de métodos
    public String actualizarEquipo(int indice, String nuevoNombre, String nuevaCategoria, boolean nuevaDisponibilidad) {
        if (indice >= 0 && indice < numEquipos) {
            equipos[indice].setNombre(nuevoNombre);
            equipos[indice].setCategoria(nuevaCategoria);
            equipos[indice].setDisponibilidad(nuevaDisponibilidad);
            return "Equipo actualizado.";
        }
        return "Índice de equipo inválido.";
    }

    public String actualizarEquipo(int indice, Equipo nuevoEquipo) {
        if (indice >= 0 && indice < numEquipos && nuevoEquipo != null) {
            equipos[indice] = nuevoEquipo;
            return "Equipo actualizado.";
        }
        return "Índice de equipo inválido o equipo nulo.";
    }

    // Eliminar Equipo - Sobrecarga de métodos
    public String eliminarEquipo(int indice) {
        if (indice >= 0 && indice < numEquipos) {
            for (int i = indice; i < numEquipos - 1; i++) {
                equipos[i] = equipos[i + 1];
            }
            equipos[numEquipos - 1] = null;
            numEquipos--;

            Equipo[] aux = new Equipo[numEquipos];
            System.arraycopy(equipos, 0, aux, 0, numEquipos);
            equipos = aux;

            return "Equipo eliminado.";
        }
        return "Índice de equipo inválido.";
    }

    public String eliminarEquipo(Equipo equipo) {
        if (equipo == null) return "Equipo nulo.";
        
        for (int i = 0; i < numEquipos; i++) {
            if (equipos[i].equals(equipo)) {
                return eliminarEquipo(i);
            }
        }
        return "Equipo no encontrado.";
    }

    @Override
    public String toString() {
        return "Reserva [" + codigoReserva + "] ID: " + idReserva +
                ", Fecha inicio: " + fechaInicio +
                ", Fecha fin: " + fechaFin +
                ", Estado: " + estado +
                ", Número de equipos: " + numEquipos;
    }
}