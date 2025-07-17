package ec.edu.uce.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import ec.edu.uce.dominio.PersistenciaUtil;
import ec.edu.uce.dominio.Universidad;

public class TabbedPaneSample {
    public static void main(String[] args) {
        // Inicia la aplicación mostrando la ventana de login
        JFrameReserva.main(args);
    }

    public static void mainWithUsuario(ec.edu.uce.dominio.Usuario usuarioParam, ec.edu.uce.dominio.Facultad facultadParam) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            final ec.edu.uce.dominio.Usuario[] usuario = {usuarioParam};
            final ec.edu.uce.dominio.Facultad[] facultad = {facultadParam};
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {}

            JFrame frame = new JFrame("SIRAA - Usuario: " + usuario[0].getNombre() + " " + usuario[0].getApellido());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 400);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 14));

            // 1. Modelos de tabla y tablas
            String[] usuarioCols = {"ID", "Nombre", "Apellido", "Correo"};
            DefaultTableModel usuarioTableModel = new DefaultTableModel(usuarioCols, 0);
            JTable usuarioTable = new JTable(usuarioTableModel);
            String[] reservaCols = {"ID", "Código", "Estado", "Fecha Inicio", "Fecha Fin"};
            DefaultTableModel reservaTableModel = new DefaultTableModel(reservaCols, 0);
            JTable reservaTable = new JTable(reservaTableModel);
            String[] facultadCols = {"ID", "Código", "Nombre", "#Auditorios", "#Usuarios"};
            DefaultTableModel facultadTableModel = new DefaultTableModel(facultadCols, 0);
            JTable facultadTable = new JTable(facultadTableModel);
            String[] equipoCols = {"ID", "Nombre", "Categoría", "Disponible"};
            DefaultTableModel equipoTableModel = new DefaultTableModel(equipoCols, 0);
            JTable equipoTable = new JTable(equipoTableModel);
            // 2. Runnables de refresco
            Runnable refreshUsuarios = () -> {
                usuarioTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Usuario u : facultad[0].getUsuarios()) {
                    usuarioTableModel.addRow(new Object[]{u.getIdUsuario(), u.getNombre(), u.getApellido(), u.getCorreo()});
                }
            };
            Runnable refreshReservas = () -> {
                reservaTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Reserva r : usuario[0].getReservas()) {
                    reservaTableModel.addRow(new Object[]{r.getIdReserva(), r.getCodigoReserva(), r.getEstado().getDescripcion(), r.getFechaInicio(), r.getFechaFin()});
                }
            };
            Runnable refreshFacultades = () -> {
                facultadTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Facultad f : ec.edu.uce.dominio.Universidad.getInstancia().getFacultades()) {
                    facultadTableModel.addRow(new Object[]{f.getIdFacultad(), f.getCodigoFacultad(), f.getNombre(), f.getNumAuditorios(), f.getNumUsuarios()});
                }
            };
            Runnable refreshEquipos = () -> {
                equipoTableModel.setRowCount(0);
                if (!usuario[0].getReservas().isEmpty()) {
                    ec.edu.uce.dominio.Reserva r = usuario[0].getReservas().get(0);
                    for (ec.edu.uce.dominio.Equipo eq : r.getEquipos()) {
                        equipoTableModel.addRow(new Object[]{eq.getIdEquipo(), eq.getNombre(), eq.getCategoria(), eq.getDisponibilidad() ? "Sí" : "No"});
                    }
                }
            };
            // 3. Barra de botones y resto de la interfaz
            // ====== Barra superior con botones Guardar y Cargar ======
            JPanel topBar = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton guardarBtn = new JButton("Guardar");
            JButton cargarBtn = new JButton("Cargar");
            topBar.add(guardarBtn);
            topBar.add(cargarBtn);
            frame.add(topBar, BorderLayout.NORTH);

            // 1. DECLARA LOS RUNNABLES PRIMERO
            // 2. Luego la barra de botones y sus listeners
            guardarBtn.addActionListener(e -> {
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
                JOptionPane.showMessageDialog(frame, "Datos guardados correctamente.");
            });
            cargarBtn.addActionListener(e -> {
                Object cargada = PersistenciaUtil.cargarObjeto("universidad.txt");
                if (cargada != null && cargada instanceof Universidad) {
                    Universidad nuevaUni = (Universidad) cargada;
                    ec.edu.uce.dominio.Facultad nuevaFacultad = null;
                    for (ec.edu.uce.dominio.Facultad f : nuevaUni.getFacultades()) {
                        if (f.getIdFacultad() == facultad[0].getIdFacultad()) {
                            nuevaFacultad = f; break;
                        }
                    }
                    if (nuevaFacultad != null) {
                        facultad[0] = nuevaFacultad;
                        ec.edu.uce.dominio.Usuario nuevoUsuario = null;
                        for (ec.edu.uce.dominio.Usuario u : facultad[0].getUsuarios()) {
                            if (u.getIdUsuario() == usuario[0].getIdUsuario()) {
                                nuevoUsuario = u; break;
                            }
                        }
                        if (nuevoUsuario != null) {
                            usuario[0] = nuevoUsuario;
                        }
                    }
                    refreshUsuarios.run();
                    refreshReservas.run();
                    refreshFacultades.run();
                    refreshEquipos.run();
                    JOptionPane.showMessageDialog(frame, "Datos cargados y actualizados correctamente.");
                } else {
                    JOptionPane.showMessageDialog(frame, "No se pudo cargar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            // ===================== PANEL USUARIO =====================
            JPanel usuarioPanel = new JPanel(new BorderLayout());
            // Tabla de usuarios
            usuarioPanel.add(new JScrollPane(usuarioTable), BorderLayout.CENTER);
            // Panel de acciones usuario
            JPanel usuarioActions = new JPanel();
            JTextField nombreField = new JTextField(10);
            JTextField apellidoField = new JTextField(10);
            JTextField correoField = new JTextField(15);
            JButton crearUsuarioBtn = new JButton("Crear");
            JButton actualizarUsuarioBtn = new JButton("Actualizar");
            JButton eliminarUsuarioBtn = new JButton("Eliminar");
            usuarioActions.add(new JLabel("Nombre:")); usuarioActions.add(nombreField);
            usuarioActions.add(new JLabel("Apellido:")); usuarioActions.add(apellidoField);
            usuarioActions.add(new JLabel("Correo:")); usuarioActions.add(correoField);
            usuarioActions.add(crearUsuarioBtn);
            usuarioActions.add(actualizarUsuarioBtn);
            usuarioActions.add(eliminarUsuarioBtn);
            usuarioPanel.add(usuarioActions, BorderLayout.SOUTH);
            // Refrescar tabla usuarios
            refreshUsuarios.run();
            // Crear usuario
            crearUsuarioBtn.addActionListener(e -> {
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();
                String correo = correoField.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Completa todos los campos."); return;
                }
                // Validar duplicado
                boolean existe = false;
                for (ec.edu.uce.dominio.Usuario u : facultad[0].getUsuarios()) {
                    if (u.getNombre().equalsIgnoreCase(nombre) && u.getApellido().equalsIgnoreCase(apellido)) {
                        existe = true; break;
                    }
                }
                if (existe) {
                    JOptionPane.showMessageDialog(frame, "Ya existe un usuario con ese nombre y apellido."); return;
                }
                facultad[0].crearUsuario(new ec.edu.uce.dominio.Usuario(nombre, apellido, correo));
                refreshUsuarios.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            // Actualizar usuario
            actualizarUsuarioBtn.addActionListener(e -> {
                int row = usuarioTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un usuario."); return; }
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();
                String correo = correoField.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Completa todos los campos."); return;
                }
                int id = (int) usuarioTableModel.getValueAt(row, 0);
                for (ec.edu.uce.dominio.Usuario u : facultad[0].getUsuarios()) {
                    if (u.getIdUsuario() == id) {
                        u.setNombre(nombre);
                        u.setApellido(apellido);
                        u.setCorreo(correo);
                        break;
                    }
                }
                refreshUsuarios.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            // Eliminar usuario
            eliminarUsuarioBtn.addActionListener(e -> {
                int row = usuarioTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un usuario."); return; }
                int id = (int) usuarioTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Usuario toRemove = null;
                for (ec.edu.uce.dominio.Usuario u : facultad[0].getUsuarios()) {
                    if (u.getIdUsuario() == id) { toRemove = u; break; }
                }
                if (toRemove == null) {
                    JOptionPane.showMessageDialog(frame, "Usuario no encontrado."); return;
                }
                facultad[0].eliminarUsuario(toRemove);
                refreshUsuarios.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            usuarioTable.getSelectionModel().addListSelectionListener(e -> {
                int row = usuarioTable.getSelectedRow();
                if (row != -1) {
                    nombreField.setText((String) usuarioTableModel.getValueAt(row, 1));
                    apellidoField.setText((String) usuarioTableModel.getValueAt(row, 2));
                    correoField.setText((String) usuarioTableModel.getValueAt(row, 3));
                }
            });
            tabbedPane.addTab("Gestionar Usuario", usuarioPanel);

            // ===================== PANEL RESERVA =====================
            JPanel reservaPanel = new JPanel(new BorderLayout());
            // Tabla de reservas
            reservaPanel.add(new JScrollPane(reservaTable), BorderLayout.CENTER);
            JPanel reservaActions = new JPanel();
            JTextField fechaInicioField = new JTextField(16); // yyyy-MM-dd HH:mm
            JTextField fechaFinField = new JTextField(16);
            JButton crearReservaBtn = new JButton("Crear");
            JButton eliminarReservaBtn = new JButton("Eliminar");
            reservaActions.add(new JLabel("Fecha Inicio (yyyy-MM-dd HH:mm):")); reservaActions.add(fechaInicioField);
            reservaActions.add(new JLabel("Fecha Fin (yyyy-MM-dd HH:mm):")); reservaActions.add(fechaFinField);
            reservaActions.add(crearReservaBtn);
            reservaActions.add(eliminarReservaBtn);
            reservaPanel.add(reservaActions, BorderLayout.SOUTH);
            refreshReservas.run();
            crearReservaBtn.addActionListener(e -> {
                String inicioStr = fechaInicioField.getText().trim();
                String finStr = fechaFinField.getText().trim();
                if (inicioStr.isEmpty() || finStr.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Completa ambos campos de fecha y hora."); return;
                }
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
                    java.util.Date inicio = sdf.parse(inicioStr);
                    java.util.Date fin = sdf.parse(finStr);
                    if (!fin.after(inicio)) {
                        JOptionPane.showMessageDialog(frame, "La fecha/hora de fin debe ser posterior a la de inicio."); return;
                    }
                    java.util.Calendar calInicio = java.util.Calendar.getInstance();
                    java.util.Calendar calFin = java.util.Calendar.getInstance();
                    calInicio.setTime(inicio);
                    calFin.setTime(fin);
                    // Validar año, mes, día
                    if (calFin.get(java.util.Calendar.YEAR) < calInicio.get(java.util.Calendar.YEAR) ||
                        (calFin.get(java.util.Calendar.YEAR) == calInicio.get(java.util.Calendar.YEAR) && calFin.get(java.util.Calendar.MONTH) < calInicio.get(java.util.Calendar.MONTH)) ||
                        (calFin.get(java.util.Calendar.YEAR) == calInicio.get(java.util.Calendar.YEAR) && calFin.get(java.util.Calendar.MONTH) == calInicio.get(java.util.Calendar.MONTH) && calFin.get(java.util.Calendar.DAY_OF_MONTH) < calInicio.get(java.util.Calendar.DAY_OF_MONTH))) {
                        JOptionPane.showMessageDialog(frame, "La fecha de fin no puede ser anterior a la de inicio."); return;
                    }
                    // Validar rango de horas
                    long diffMs = fin.getTime() - inicio.getTime();
                    long diffHrs = diffMs / (1000 * 60 * 60);
                    if (diffHrs < 1) {
                        JOptionPane.showMessageDialog(frame, "La reserva debe durar al menos 1 hora."); return;
                    }
                    if (diffHrs > 3) {
                        JOptionPane.showMessageDialog(frame, "La reserva no puede durar más de 3 horas."); return;
                    }
                    usuario[0].crearReserva(new ec.edu.uce.dominio.Reserva(inicio, fin));
                    refreshReservas.run();
                    PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Formato de fecha y hora incorrecto. Usa yyyy-MM-dd HH:mm");
                }
            });
            eliminarReservaBtn.addActionListener(e -> {
                int row = reservaTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona una reserva."); return; }
                int id = (int) reservaTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Reserva toRemove = null;
                for (ec.edu.uce.dominio.Reserva r : usuario[0].getReservas()) {
                    if (r.getIdReserva() == id) { toRemove = r; break; }
                }
                if (toRemove != null) usuario[0].eliminarReserva(toRemove);
                refreshReservas.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            tabbedPane.addTab("Gestionar Reserva", reservaPanel);

            // ===================== PANEL FACULTAD =====================
            JPanel facultadPanel = new JPanel(new BorderLayout());
            // Tabla de facultades
            facultadPanel.add(new JScrollPane(facultadTable), BorderLayout.CENTER);
            JPanel facultadActions = new JPanel();
            JTextField nombreFacultadField = new JTextField(10);
            JButton crearFacultadBtn = new JButton("Crear");
            JButton eliminarFacultadBtn = new JButton("Eliminar");
            facultadActions.add(new JLabel("Nombre:")); facultadActions.add(nombreFacultadField);
            facultadActions.add(crearFacultadBtn);
            facultadActions.add(eliminarFacultadBtn);
            facultadPanel.add(facultadActions, BorderLayout.SOUTH);
            refreshFacultades.run();
            crearFacultadBtn.addActionListener(e -> {
                String nombre = nombreFacultadField.getText().trim();
                if (nombre.isEmpty()) { JOptionPane.showMessageDialog(frame, "Ingrese nombre de facultad."); return; }
                // Validar duplicado
                boolean existe = false;
                for (ec.edu.uce.dominio.Facultad f : ec.edu.uce.dominio.Universidad.getInstancia().getFacultades()) {
                    if (f.getNombre().equalsIgnoreCase(nombre)) { existe = true; break; }
                }
                if (existe) {
                    JOptionPane.showMessageDialog(frame, "Ya existe una facultad con ese nombre."); return;
                }
                ec.edu.uce.dominio.Universidad.getInstancia().crearFacultad(new ec.edu.uce.dominio.Facultad(nombre, 5));
                refreshFacultades.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            eliminarFacultadBtn.addActionListener(e -> {
                int row = facultadTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona una facultad."); return; }
                int id = (int) facultadTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Facultad toRemove = null;
                for (ec.edu.uce.dominio.Facultad f : ec.edu.uce.dominio.Universidad.getInstancia().getFacultades()) {
                    if (f.getIdFacultad() == id) { toRemove = f; break; }
                }
                if (toRemove == null) {
                    JOptionPane.showMessageDialog(frame, "Facultad no encontrada."); return;
                }
                ec.edu.uce.dominio.Universidad.getInstancia().eliminarFacultad(toRemove);
                refreshFacultades.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            tabbedPane.addTab("Gestionar Facultades", facultadPanel);

            // ===================== PANEL EQUIPO =====================
            JPanel equipoPanel = new JPanel(new BorderLayout());
            // Tabla de equipos
            equipoPanel.add(new JScrollPane(equipoTable), BorderLayout.CENTER);
            JPanel equipoActions = new JPanel();
            JTextField nombreEquipoField = new JTextField(10);
            JTextField categoriaEquipoField = new JTextField(10);
            JCheckBox disponibleCheck = new JCheckBox("Disponible");
            JButton crearEquipoBtn = new JButton("Crear");
            JButton eliminarEquipoBtn = new JButton("Eliminar");
            equipoActions.add(new JLabel("Nombre:")); equipoActions.add(nombreEquipoField);
            equipoActions.add(new JLabel("Categoría:")); equipoActions.add(categoriaEquipoField);
            equipoActions.add(disponibleCheck);
            equipoActions.add(crearEquipoBtn);
            equipoActions.add(eliminarEquipoBtn);
            equipoPanel.add(equipoActions, BorderLayout.SOUTH);
            refreshEquipos.run();
            crearEquipoBtn.addActionListener(e -> {
                if (usuario[0].getReservas().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Primero cree una reserva."); return;
                }
                String nombre = nombreEquipoField.getText().trim();
                String categoria = categoriaEquipoField.getText().trim();
                boolean disponible = disponibleCheck.isSelected();
                if (nombre.isEmpty() || categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Ingrese nombre y categoría."); return;
                }
                ec.edu.uce.dominio.Reserva r = usuario[0].getReservas().get(0);
                // Validar duplicado
                boolean existe = false;
                for (ec.edu.uce.dominio.Equipo eq : r.getEquipos()) {
                    if (eq.getNombre().equalsIgnoreCase(nombre) && eq.getCategoria().equalsIgnoreCase(categoria)) {
                        existe = true; break;
                    }
                }
                if (existe) {
                    JOptionPane.showMessageDialog(frame, "Ya existe un equipo con ese nombre y categoría en la reserva."); return;
                }
                try {
                    r.crearEquipo(new ec.edu.uce.dominio.Equipo(nombre, categoria, disponible));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error al crear equipo: " + ex.getMessage());
                }
                refreshEquipos.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            eliminarEquipoBtn.addActionListener(e -> {
                if (usuario[0].getReservas().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Primero cree una reserva."); return;
                }
                int row = equipoTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un equipo."); return; }
                int id = (int) equipoTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Reserva r = usuario[0].getReservas().get(0);
                ec.edu.uce.dominio.Equipo toRemove = null;
                for (ec.edu.uce.dominio.Equipo eq : r.getEquipos()) {
                    if (eq.getIdEquipo() == id) { toRemove = eq; break; }
                }
                if (toRemove == null) {
                    JOptionPane.showMessageDialog(frame, "Equipo no encontrado."); return;
                }
                r.eliminarEquipo(toRemove);
                refreshEquipos.run();
                PersistenciaUtil.guardarObjeto(Universidad.getInstancia(), "universidad.txt");
            });
            tabbedPane.addTab("Gestionar Equipos", equipoPanel);

            tabbedPane.setPreferredSize(new Dimension(850, 300));
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // Panel de contenido de ejemplo (rectángulos verticales)
    private static JPanel getDemoContent(int numRects) {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(200, 180, 180));
                int w = getWidth();
                int h = getHeight();
                int rectW = w / (numRects * 2);
                int rectH = (int)(h * 0.6);
                int totalWidth = numRects * rectW + (numRects - 1) * rectW / 2;
                int startX = (w - totalWidth) / 2;
                int y = (h - rectH) / 2;
                for (int i = 0; i < numRects; i++) {
                    int x = startX + i * (rectW + rectW / 2);
                    g.fillRect(x, y, rectW, rectH);
                }
            }
        };
        panel.setOpaque(true);
        panel.setBackground(null);
        return panel;
    }
} 