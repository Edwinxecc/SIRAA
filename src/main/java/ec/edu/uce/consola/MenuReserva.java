package ec.edu.uce.consola;

import ec.edu.uce.dominio.*;
import ec.edu.uce.Util.RelacionesUtil;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class MenuReserva extends MenuBase {
    private final Facultad facultad;
    private final Usuario usuarioActual;

    public MenuReserva(Facultad facultad, Usuario usuarioActual) {
        super();
        this.facultad = facultad;
        this.usuarioActual = usuarioActual;
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.printf("%n%s%n", "=".repeat(50));
            System.out.printf("%-20s%s%n", "", "MENÚ GESTIÓN DE RESERVAS");
            System.out.printf("%s%n", "=".repeat(50));
            System.out.printf("%-5s%-30s%n", "[1]", "Crear Reserva");
            System.out.printf("%-5s%-30s%n", "[2]", "Ver Reservas");
            System.out.printf("%-5s%-30s%n", "[3]", "Editar Reserva");
            System.out.printf("%-5s%-30s%n", "[4]", "Eliminar Reserva");
            System.out.printf("%-5s%-30s%n", "[5]", "Buscar Reserva por ID");
            System.out.printf("%-5s%-30s%n", "[6]", "Mostrar Relaciones");
            System.out.printf("%-5s%-30s%n", "[0]", "Volver al Menú Principal");
            System.out.printf("%s%n", "─".repeat(50));
            System.out.printf("%-5s", ">: ");

            opcion = leerEnteroPositivo();

            switch (opcion) {
                case 1 -> crearReserva();
                case 2 -> mostrarReservas();
                case 3 -> editarReserva();
                case 4 -> eliminarReserva();
                case 5 -> buscarReservaPorId();
                case 6 -> mostrarRelaciones();
                case 0 -> {
                    System.out.printf("%-25s%n", "🔄 Regresando al menú principal...");
                    return;
                }
                default -> System.out.printf("%-25s%n", "❌ Opción no válida");
            }
        } while (opcion != 0);
    }

    private void crearReserva() {
        System.out.println("\n[1] Crear Reserva");
        
        System.out.println("\nSeleccione el tipo de reserva:");
        System.out.println("[1] Reserva Normal");
        System.out.println("[2] Reserva Prioritaria");
        System.out.print(">: ");
        
        int tipoReserva = leerEnteroPositivo();
        
        switch (tipoReserva) {
            case 1 -> crearReservaNormal();
            case 2 -> crearReservaPrioritaria();
            default -> System.out.println("[!] Opción no válida. Seleccione 1 o 2.");
        }
    }

    private void crearReservaNormal() {
        System.out.println("\n[1.1] Crear Reserva Normal");

        // Paso 1: Seleccionar facultad
        System.out.println("\n=== SELECCIÓN DE FACULTAD ===");
        Universidad universidad = Universidad.getInstancia();
        List<Facultad> facultades = universidad.getFacultades();
        
        if (facultades.isEmpty()) {
            System.out.println("[!] No hay facultades disponibles.");
            return;
        }

        System.out.println("Facultades disponibles:");
        for (int i = 0; i < facultades.size(); i++) {
            System.out.println("[" + i + "] " + facultades.get(i).getNombre());
        }

        System.out.print("\nSeleccione el índice de la facultad: ");
        int indiceFacultad = leerEnteroPositivo();
        
        if (indiceFacultad < 0 || indiceFacultad >= facultades.size()) {
            System.out.println("[!] Índice de facultad inválido.");
            return;
        }

        Facultad facultadSeleccionada = facultades.get(indiceFacultad);
        System.out.println("Facultad seleccionada: " + facultadSeleccionada.getNombre());

        // Paso 2: Mostrar auditorios de la facultad seleccionada
        System.out.println("\n=== AUDITORIOS DE LA FACULTAD ===");
        List<Auditorio> auditorios = facultadSeleccionada.getAuditorios();
        
        if (auditorios.isEmpty()) {
            System.out.println("[!] No hay auditorios disponibles en esta facultad.");
            return;
        }

        System.out.println("Auditorios disponibles:");
        for (int i = 0; i < auditorios.size(); i++) {
            Auditorio auditorio = auditorios.get(i);
            System.out.println("[" + i + "] ID: " + auditorio.getIdAuditorio() + 
                             " | " + auditorio.getNombre() + 
                             " | Capacidad: " + auditorio.getCapacidad() + " personas");
        }

        // Paso 3: Seleccionar auditorio por ID
        System.out.print("\nIngrese el ID del auditorio que desea reservar: ");
        int idAuditorio = leerEnteroPositivo();
        
        Auditorio auditorioSeleccionado = facultadSeleccionada.buscarAuditorioPorId(idAuditorio);
        
        if (auditorioSeleccionado == null) {
            System.out.println("[!] No se encontró un auditorio con ese ID en la facultad seleccionada.");
            return;
        }

        // Paso 4: Validar que el auditorio pertenece a la facultad seleccionada
        if (!facultadSeleccionada.contieneAuditorio(auditorioSeleccionado)) {
            System.out.println("[!] El auditorio seleccionado no pertenece a la facultad elegida.");
            return;
        }

        System.out.println("Auditorio seleccionado: " + auditorioSeleccionado.getNombre());

        // Paso 5: Verificar disponibilidad del auditorio
        System.out.println("\n=== VERIFICACIÓN DE DISPONIBILIDAD ===");
        System.out.println("Ingrese fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaInicio = leerFecha();
        if (fechaInicio == null) return;

        System.out.println("Ingrese fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaFin = leerFecha();
        if (fechaFin == null) return;

        if (fechaFin.isBefore(fechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(fechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(fechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Verificar disponibilidad
        if (!auditorioSeleccionado.estaDisponible(inicio, fin)) {
            System.out.println("[!] El auditorio no está disponible en el horario seleccionado.");
            return;
        }

        // Paso 6: Crear la reserva con las referencias correctas
        Reserva nuevaReserva = new Reserva(inicio, fin, usuarioActual, auditorioSeleccionado);

        // Paso 7: Agregar la reserva al usuario y al auditorio
        try {
            usuarioActual.crearReserva(nuevaReserva);
            auditorioSeleccionado.crearReserva(nuevaReserva);
        } catch (SIRAAException e) {
            System.out.println("[!] Error al crear la reserva: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
            return;
        } finally {
            // Limpiar recursos si es necesario
            System.out.println("[i] Proceso de creación de reserva completado");
        }

        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevaReserva.nuevo(nuevaReserva);
        System.out.println(resultado);

        if (resultado.contains("creada correctamente")) {
            System.out.println("\n[✓] Información de la reserva creada:");
            System.out.println("ID: " + nuevaReserva.getIdReserva());
            System.out.println("Código: " + nuevaReserva.getCodigoReserva());
            System.out.println("Estado: " + nuevaReserva.getEstado().getDescripcion());
            System.out.println("Fecha Inicio: " + nuevaReserva.getFechaInicio());
            System.out.println("Fecha Fin: " + nuevaReserva.getFechaFin());
            System.out.println("Tipo: " + nuevaReserva.tipoReserva());
            System.out.println("Usuario: " + nuevaReserva.getUsuario().getNombre() + " " + nuevaReserva.getUsuario().getApellido());
            System.out.println("Auditorio: " + nuevaReserva.getAuditorio().getNombre());
            System.out.println("Facultad: " + facultadSeleccionada.getNombre());
        }
    }

    private void crearReservaPrioritaria() {
        System.out.println("\n[1.2] Crear Reserva Prioritaria");

        // Seleccionar nivel de prioridad
        System.out.println("\nSeleccione el nivel de prioridad:");
        System.out.println("[1] Prioridad Baja");
        System.out.println("[2] Prioridad Media");
        System.out.println("[3] Prioridad Alta");
        System.out.println("[4] Prioridad Urgente");
        System.out.print(">: ");

        int opcion = leerEnteroPositivo();
        Estado estado;

        switch (opcion) {
            case 1 -> estado = Estado.PRIORIDAD_BAJA;
            case 2 -> estado = Estado.PRIORIDAD_MEDIA;
            case 3 -> estado = Estado.PRIORIDAD_ALTA;
            case 4 -> estado = Estado.PRIORIDAD_URGENTE;
            default -> {
                System.out.println("[!] Opción no válida. Se usará Prioridad Baja por defecto.");
                estado = Estado.PRIORIDAD_BAJA;
            }
        }

        // Solicitar motivo de la prioridad
        System.out.print("\nIngrese el motivo de la prioridad: ");
        String motivo = entrada.nextLine();
        while (motivo.trim().isEmpty()) {
            System.out.print("[!] El motivo no puede estar vacío. Ingrese el motivo: ");
            motivo = entrada.nextLine();
        }

        // Paso 1: Seleccionar facultad
        System.out.println("\n=== SELECCIÓN DE FACULTAD ===");
        Universidad universidad = Universidad.getInstancia();
        List<Facultad> facultades = universidad.getFacultades();
        
        if (facultades.isEmpty()) {
            System.out.println("[!] No hay facultades disponibles.");
            return;
        }

        System.out.println("Facultades disponibles:");
        for (int i = 0; i < facultades.size(); i++) {
            System.out.println("[" + i + "] " + facultades.get(i).getNombre());
        }

        System.out.print("\nSeleccione el índice de la facultad: ");
        int indiceFacultad = leerEnteroPositivo();
        
        if (indiceFacultad < 0 || indiceFacultad >= facultades.size()) {
            System.out.println("[!] Índice de facultad inválido.");
            return;
        }

        Facultad facultadSeleccionada = facultades.get(indiceFacultad);
        System.out.println("Facultad seleccionada: " + facultadSeleccionada.getNombre());

        // Paso 2: Mostrar auditorios de la facultad seleccionada
        System.out.println("\n=== AUDITORIOS DE LA FACULTAD ===");
        List<Auditorio> auditorios = facultadSeleccionada.getAuditorios();
        
        if (auditorios.isEmpty()) {
            System.out.println("[!] No hay auditorios disponibles en esta facultad.");
            return;
        }

        System.out.println("Auditorios disponibles:");
        for (int i = 0; i < auditorios.size(); i++) {
            Auditorio auditorio = auditorios.get(i);
            System.out.println("[" + i + "] ID: " + auditorio.getIdAuditorio() + 
                             " | " + auditorio.getNombre() + 
                             " | Capacidad: " + auditorio.getCapacidad() + " personas");
        }

        // Paso 3: Seleccionar auditorio por ID
        System.out.print("\nIngrese el ID del auditorio que desea reservar: ");
        int idAuditorio = leerEnteroPositivo();
        
        Auditorio auditorioSeleccionado = facultadSeleccionada.buscarAuditorioPorId(idAuditorio);
        
        if (auditorioSeleccionado == null) {
            System.out.println("[!] No se encontró un auditorio con ese ID en la facultad seleccionada.");
            return;
        }

        // Paso 4: Validar que el auditorio pertenece a la facultad seleccionada
        if (!facultadSeleccionada.contieneAuditorio(auditorioSeleccionado)) {
            System.out.println("[!] El auditorio seleccionado no pertenece a la facultad elegida.");
            return;
        }

        System.out.println("Auditorio seleccionado: " + auditorioSeleccionado.getNombre());

        // Paso 5: Verificar disponibilidad del auditorio
        System.out.println("\n=== VERIFICACIÓN DE DISPONIBILIDAD ===");
        System.out.println("Ingrese fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaInicio = leerFecha();
        if (fechaInicio == null) return;

        System.out.println("Ingrese fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime fechaFin = leerFecha();
        if (fechaFin == null) return;

        if (fechaFin.isBefore(fechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(fechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(fechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Verificar disponibilidad
        if (!auditorioSeleccionado.estaDisponible(inicio, fin)) {
            System.out.println("[!] El auditorio no está disponible en el horario seleccionado.");
            return;
        }

        // Paso 6: Crear la reserva prioritaria con las referencias correctas
        ReservaPrioritaria nuevaReserva = new ReservaPrioritaria(inicio, fin, estado, motivo);
        nuevaReserva.setUsuario(usuarioActual);
        nuevaReserva.setAuditorio(auditorioSeleccionado);

        // Paso 7: Agregar la reserva al usuario y al auditorio
        try {
            usuarioActual.crearReserva(nuevaReserva);
            auditorioSeleccionado.crearReserva(nuevaReserva);
        } catch (ReservaException e) {
            System.out.println("[!] Error al crear la reserva prioritaria: " + e.getMessage());
            return;
        } catch (SIRAAException e) {
            System.out.println("[!] Error al crear la reserva prioritaria: " + e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("[!] Error inesperado: " + e.getMessage());
            return;
        } finally {
            // Limpiar recursos si es necesario
            System.out.println("[i] Proceso de creación de reserva prioritaria completado");
        }

        // Usar el método de la interfaz IAdministrarCRUD
        String resultado = nuevaReserva.nuevo(nuevaReserva);
        System.out.println(resultado);

        if (resultado.contains("creada correctamente")) {
            System.out.println("\n[✓] Información de la reserva prioritaria creada:");
            System.out.println("ID: " + nuevaReserva.getIdReserva());
            System.out.println("Código: " + nuevaReserva.getCodigoReserva());
            System.out.println("Estado: " + nuevaReserva.getEstado().getDescripcion());
            System.out.println("Nivel de Prioridad: " + nuevaReserva.getNivelPrioridad());
            System.out.println("Motivo: " + nuevaReserva.getMotivoPrioridad());
            System.out.println("Requiere Aprobación: " + (nuevaReserva.requiereAprobacion() ? "Sí" : "No"));
            System.out.println("Tipo: " + nuevaReserva.tipoReserva());
            System.out.println("Usuario: " + nuevaReserva.getUsuario().getNombre() + " " + nuevaReserva.getUsuario().getApellido());
            System.out.println("Auditorio: " + nuevaReserva.getAuditorio().getNombre());
            System.out.println("Facultad: " + facultadSeleccionada.getNombre());
        }
    }

    private void mostrarReservas() {
        System.out.println("\n[2] Ver Reservas");
        List<Reserva> reservas = usuarioActual.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para mostrar.");
            return;
        }
        
        System.out.println("Usuario actual: " + usuarioActual.getNombre() + " " + usuarioActual.getApellido());
        System.out.println("Total de reservas: " + reservas.size());
        System.out.println();
        
        System.out.println("¿Por qué criterio desea ordenar las reservas?");
        System.out.println("[1] ID (orden natural)");
        System.out.println("[2] Fecha de inicio");
        System.out.println("[3] Fecha de fin");
        System.out.println("[4] Estado");
        System.out.println("[5] Número de equipos");
        System.out.print(">: ");
        int criterio = leerEnteroPositivo();
        switch (criterio) {
            case 2 -> reservas.sort(new OrdenarReservaFechaInicio());
            case 3 -> reservas.sort(new OrdenarReservaFechaFin());
            case 4 -> reservas.sort(new OrdenarReservaEstado());
            case 5 -> reservas.sort(new OrdenarReservaNumEquipos());
            default -> java.util.Collections.sort(reservas);
        }
        System.out.println("=== RESERVAS DEL USUARIO ORDENADAS ===");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }

    private void editarReserva() {
        System.out.println("\n[3] Editar Reserva");
        List<Reserva> reservas = usuarioActual.getReservas();

        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para editar.");
            return;
        }

        System.out.println("=== LISTA DE RESERVAS ===");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println("[" + i + "] " + reservas.get(i));
        }

        System.out.print("\nSeleccione el índice de la reserva a editar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= reservas.size()) {
            System.out.println("[!] Índice de reserva inválido.");
            return;
        }

        Reserva reservaAEditar = reservas.get(indice);
        System.out.println("\nEditando reserva: " + reservaAEditar);

        System.out.println("\nIngrese nueva fecha y hora de inicio (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaInicio = leerFecha();
        if (nuevaFechaInicio == null) return;

        System.out.println("Ingrese nueva fecha y hora de fin (formato: dd/MM/yyyy HH:mm)");
        LocalDateTime nuevaFechaFin = leerFecha();
        if (nuevaFechaFin == null) return;

        if (nuevaFechaFin.isBefore(nuevaFechaInicio)) {
            System.out.println("[!] La fecha de fin debe ser posterior a la fecha de inicio.");
            return;
        }

        // Convertir LocalDateTime a Date para la reserva
        Date inicio = java.util.Date.from(nuevaFechaInicio.atZone(java.time.ZoneId.systemDefault()).toInstant());
        Date fin = java.util.Date.from(nuevaFechaFin.atZone(java.time.ZoneId.systemDefault()).toInstant());

        // Crear reserva actualizada
        Reserva reservaActualizada = new Reserva(inicio, fin);
        reservaActualizada.setEstado(reservaAEditar.getEstado());
        reservaActualizada.setUsuario(reservaAEditar.getUsuario());
        reservaActualizada.setAuditorio(reservaAEditar.getAuditorio());

        // Actualizar la reserva en el usuario
        usuarioActual.actualizarReserva(reservaAEditar.getCodigoReserva(), reservaActualizada);

        // Actualizar la reserva en el auditorio si existe
        if (reservaAEditar.getAuditorio() != null) {
            reservaAEditar.getAuditorio().actualizarReserva(reservaAEditar.getCodigoReserva(), reservaActualizada);
        }

        System.out.println("[✓] Reserva actualizada correctamente.");
    }

    private void eliminarReserva() {
        System.out.println("\n[4] Eliminar Reserva");
        List<Reserva> reservas = usuarioActual.getReservas();

        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para eliminar.");
            return;
        }

        System.out.println("=== LISTA DE RESERVAS ===");
        for (int i = 0; i < reservas.size(); i++) {
            System.out.println("[" + i + "] " + reservas.get(i));
        }

        System.out.print("\nSeleccione el índice de la reserva a eliminar: ");
        int indice = leerEnteroPositivo();

        if (indice < 0 || indice >= reservas.size()) {
            System.out.println("[!] Índice de reserva inválido.");
            return;
        }

        Reserva reservaAEliminar = reservas.get(indice);
        System.out.println("\nEliminando reserva: " + reservaAEliminar);

        // Eliminar la reserva del usuario
        usuarioActual.eliminarReserva(reservaAEliminar);

        // Eliminar la reserva del auditorio si existe
        if (reservaAEliminar.getAuditorio() != null) {
            reservaAEliminar.getAuditorio().eliminarReserva(reservaAEliminar);
        }

        System.out.println("[✓] Reserva eliminada correctamente.");
    }

    private void buscarReservaPorId() {
        System.out.println("\n[5] Buscar Reserva por ID");
        System.out.print("Ingrese el ID de la reserva a buscar: ");
        int idReserva = leerEnteroPositivo();

        List<Reserva> reservas = usuarioActual.getReservas();
        Reserva reservaEncontrada = null;

        for (Reserva reserva : reservas) {
            if (reserva != null && reserva.getIdReserva() == idReserva) {
                reservaEncontrada = reserva;
                break;
            }
        }

        if (reservaEncontrada != null) {
            System.out.println("\n[✓] Reserva encontrada:");
            System.out.println(reservaEncontrada);
        } else {
            System.out.println("[!] No se encontró una reserva con el ID: " + idReserva);
        }
    }

    private void mostrarRelaciones() {
        System.out.println("\n[6] Mostrar Relaciones");
        
        // Mostrar relaciones del usuario actual
        RelacionesUtil.mostrarRelacionesUsuario(usuarioActual);
        
        // Mostrar relaciones de las reservas del usuario
        List<Reserva> reservas = usuarioActual.getReservas();
        if (reservas.isEmpty()) {
            System.out.println("[!] No hay reservas para mostrar relaciones.");
            return;
        }
        
        System.out.println("\n=== DETALLES DE RELACIONES DE RESERVAS ===");
        for (Reserva reserva : reservas) {
            RelacionesUtil.mostrarRelacionesReserva(reserva);
            System.out.println("---");
        }
    }

    // Método para obtener el usuario actual
    public Usuario getUsuarioActual() {
        return usuarioActual;
    }
}