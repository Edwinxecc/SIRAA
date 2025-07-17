package ec.edu.uce.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TabbedPaneSample {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Usar el Look and Feel por defecto (clásico)
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {
                // Ignorar si falla
            }

            JFrame frame = new JFrame("Tabbed Pane Sample");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(700, 300);
            frame.getContentPane().setBackground(null);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 14));

            // Panel de contenido para 'Gestionar Usuario'
            JPanel usuarioPanel = new JPanel(new GridLayout(1, 3, 30, 0));
            usuarioPanel.setBackground(null);

            // Primera columna: nombre, apellido, correo, botón actualizar
            JPanel col1 = new JPanel(new GridBagLayout());
            col1.setOpaque(true);
            col1.setBackground(null);
            JPanel col1Inner = new JPanel();
            col1Inner.setOpaque(false);
            col1Inner.setLayout(new BoxLayout(col1Inner, BoxLayout.Y_AXIS));
            col1Inner.setMaximumSize(new Dimension(180, 220));
            col1Inner.setPreferredSize(new Dimension(180, 220));
            JTextField nombreField = new JTextField(12);
            JTextField apellidoField = new JTextField(12);
            JTextField correoField = new JTextField(12);
            JButton actualizarBtn = new JButton("Actualizar");
            col1Inner.add(Box.createVerticalStrut(10));
            JLabel nombreLabel = new JLabel("Nombre:");
            nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            col1Inner.add(nombreLabel);
            col1Inner.add(Box.createVerticalStrut(4));
            nombreField.setMaximumSize(new Dimension(160, 25));
            col1Inner.add(nombreField);
            col1Inner.add(Box.createVerticalStrut(12));
            JLabel apellidoLabel = new JLabel("Apellido:");
            apellidoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            col1Inner.add(apellidoLabel);
            col1Inner.add(Box.createVerticalStrut(4));
            apellidoField.setMaximumSize(new Dimension(160, 25));
            col1Inner.add(apellidoField);
            col1Inner.add(Box.createVerticalStrut(12));
            JLabel correoLabel = new JLabel("Correo:");
            correoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            col1Inner.add(correoLabel);
            col1Inner.add(Box.createVerticalStrut(4));
            correoField.setMaximumSize(new Dimension(160, 25));
            col1Inner.add(correoField);
            col1Inner.add(Box.createVerticalStrut(18));
            actualizarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            col1Inner.add(actualizarBtn);
            col1Inner.add(Box.createVerticalGlue());
            col1.add(col1Inner, new GridBagConstraints());

            // Segunda columna: nombre, botón buscar, tabla
            JPanel col2 = new JPanel(new GridBagLayout());
            col2.setOpaque(true);
            col2.setBackground(null);
            JPanel col2Inner = new JPanel();
            col2Inner.setOpaque(false);
            col2Inner.setLayout(new BoxLayout(col2Inner, BoxLayout.Y_AXIS));
            col2Inner.setMaximumSize(new Dimension(180, 180));
            col2Inner.setPreferredSize(new Dimension(180, 180));
            JTextField buscarNombreField = new JTextField(12);
            JButton buscarBtn = new JButton("Buscar");
            col2Inner.add(Box.createVerticalStrut(10));
            JLabel buscarLabel = new JLabel("Nombre:");
            buscarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            col2Inner.add(buscarLabel);
            col2Inner.add(Box.createVerticalStrut(4));
            buscarNombreField.setMaximumSize(new Dimension(160, 25));
            col2Inner.add(buscarNombreField);
            col2Inner.add(Box.createVerticalStrut(10));
            buscarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            col2Inner.add(buscarBtn);
            col2Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para buscar
            String[] cols2 = {"ID", "Nombre", "Apellido"};
            Object[][] data2 = {{"1", "Juan", "Perez"}, {"2", "Ana", "Lopez"}};
            JTable table2 = new JTable(data2, cols2);
            JScrollPane tableScroll2 = new JScrollPane(table2);
            tableScroll2.setPreferredSize(new Dimension(160, 60));
            col2Inner.add(tableScroll2);
            col2Inner.add(Box.createVerticalGlue());
            col2.add(col2Inner, new GridBagConstraints());

            // Tercera columna: nombre, botón eliminar, tabla
            JPanel col3 = new JPanel(new GridBagLayout());
            col3.setOpaque(true);
            col3.setBackground(null);
            JPanel col3Inner = new JPanel();
            col3Inner.setOpaque(false);
            col3Inner.setLayout(new BoxLayout(col3Inner, BoxLayout.Y_AXIS));
            col3Inner.setMaximumSize(new Dimension(180, 180));
            col3Inner.setPreferredSize(new Dimension(180, 180));
            JTextField eliminarNombreField = new JTextField(12);
            JButton eliminarBtn = new JButton("Eliminar");
            col3Inner.add(Box.createVerticalStrut(10));
            JLabel eliminarLabel = new JLabel("Nombre:");
            eliminarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            col3Inner.add(eliminarLabel);
            col3Inner.add(Box.createVerticalStrut(4));
            eliminarNombreField.setMaximumSize(new Dimension(160, 25));
            col3Inner.add(eliminarNombreField);
            col3Inner.add(Box.createVerticalStrut(10));
            eliminarBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            col3Inner.add(eliminarBtn);
            col3Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para eliminar
            String[] cols3 = {"ID", "Nombre", "Apellido"};
            Object[][] data3 = {{"3", "Luis", "Mora"}, {"4", "Sofia", "Diaz"}};
            JTable table3 = new JTable(data3, cols3);
            JScrollPane tableScroll3 = new JScrollPane(table3);
            tableScroll3.setPreferredSize(new Dimension(160, 60));
            col3Inner.add(tableScroll3);
            col3Inner.add(Box.createVerticalGlue());
            col3.add(col3Inner, new GridBagConstraints());

            usuarioPanel.add(col1);
            usuarioPanel.add(col2);
            usuarioPanel.add(col3);

            tabbedPane.addTab("Gestionar Usuario", new JScrollPane(usuarioPanel));
            // Panel de contenido para 'Gestionar Reserva'
            JPanel reservaPanel = new JPanel(new GridLayout(1, 4, 30, 0));
            reservaPanel.setBackground(null);

            // Columna 1: Crear Reserva
            JPanel colR1 = new JPanel(new GridBagLayout());
            colR1.setOpaque(true);
            colR1.setBackground(null);
            JPanel colR1Inner = new JPanel();
            colR1Inner.setOpaque(false);
            colR1Inner.setLayout(new BoxLayout(colR1Inner, BoxLayout.Y_AXIS));
            colR1Inner.setMaximumSize(new Dimension(180, 180));
            colR1Inner.setPreferredSize(new Dimension(180, 180));
            JLabel fechaInicioLabel1 = new JLabel("Fecha Inicio:");
            JTextField fechaInicioField1 = new JTextField(12);
            JLabel fechaFinLabel1 = new JLabel("Fecha Fin:");
            JTextField fechaFinField1 = new JTextField(12);
            JButton crearReservaBtn = new JButton("Crear Reserva");
            fechaInicioLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            fechaFinLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            crearReservaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR1Inner.add(Box.createVerticalStrut(10));
            colR1Inner.add(fechaInicioLabel1);
            colR1Inner.add(Box.createVerticalStrut(4));
            fechaInicioField1.setMaximumSize(new Dimension(160, 25));
            colR1Inner.add(fechaInicioField1);
            colR1Inner.add(Box.createVerticalStrut(10));
            colR1Inner.add(fechaFinLabel1);
            colR1Inner.add(Box.createVerticalStrut(4));
            fechaFinField1.setMaximumSize(new Dimension(160, 25));
            colR1Inner.add(fechaFinField1);
            colR1Inner.add(Box.createVerticalStrut(18));
            colR1Inner.add(crearReservaBtn);
            colR1Inner.add(Box.createVerticalGlue());
            colR1.add(colR1Inner, new GridBagConstraints());

            // Columna 2: Actualizar Reserva
            JPanel colR2 = new JPanel(new GridBagLayout());
            colR2.setOpaque(true);
            colR2.setBackground(null);
            JPanel colR2Inner = new JPanel();
            colR2Inner.setOpaque(false);
            colR2Inner.setLayout(new BoxLayout(colR2Inner, BoxLayout.Y_AXIS));
            colR2Inner.setMaximumSize(new Dimension(180, 180));
            colR2Inner.setPreferredSize(new Dimension(180, 180));
            JLabel fechaInicioLabel2 = new JLabel("Fecha Inicio:");
            JTextField fechaInicioField2 = new JTextField(12);
            JLabel fechaFinLabel2 = new JLabel("Fecha Fin:");
            JTextField fechaFinField2 = new JTextField(12);
            JButton actualizarReservaBtn = new JButton("Actualizar");
            fechaInicioLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            fechaFinLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            actualizarReservaBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR2Inner.add(Box.createVerticalStrut(10));
            colR2Inner.add(fechaInicioLabel2);
            colR2Inner.add(Box.createVerticalStrut(4));
            fechaInicioField2.setMaximumSize(new Dimension(160, 25));
            colR2Inner.add(fechaInicioField2);
            colR2Inner.add(Box.createVerticalStrut(10));
            colR2Inner.add(fechaFinLabel2);
            colR2Inner.add(Box.createVerticalStrut(4));
            fechaFinField2.setMaximumSize(new Dimension(160, 25));
            colR2Inner.add(fechaFinField2);
            colR2Inner.add(Box.createVerticalStrut(18));
            colR2Inner.add(actualizarReservaBtn);
            colR2Inner.add(Box.createVerticalGlue());
            colR2.add(colR2Inner, new GridBagConstraints());

            // Columna 3: Buscar por ID y tabla
            JPanel colR3 = new JPanel(new GridBagLayout());
            colR3.setOpaque(true);
            colR3.setBackground(null);
            JPanel colR3Inner = new JPanel();
            colR3Inner.setOpaque(false);
            colR3Inner.setLayout(new BoxLayout(colR3Inner, BoxLayout.Y_AXIS));
            colR3Inner.setMaximumSize(new Dimension(180, 180));
            colR3Inner.setPreferredSize(new Dimension(180, 180));
            JLabel buscarIdLabel = new JLabel("ID:");
            JTextField buscarIdField = new JTextField(12);
            JButton buscarIdBtn = new JButton("Buscar");
            buscarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buscarIdBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR3Inner.add(Box.createVerticalStrut(10));
            colR3Inner.add(buscarIdLabel);
            colR3Inner.add(Box.createVerticalStrut(4));
            buscarIdField.setMaximumSize(new Dimension(160, 25));
            colR3Inner.add(buscarIdField);
            colR3Inner.add(Box.createVerticalStrut(10));
            buscarIdBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR3Inner.add(buscarIdBtn);
            colR3Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para buscar
            String[] cols3R = {"ID", "Nombre", "Apellido"};
            Object[][] data3R = {{"3", "Luis", "Mora"}, {"4", "Sofia", "Diaz"}};
            JTable table3R = new JTable(data3R, cols3R);
            JScrollPane tableScroll3R = new JScrollPane(table3R);
            tableScroll3R.setPreferredSize(new Dimension(160, 60));
            colR3Inner.add(tableScroll3R);
            colR3Inner.add(Box.createVerticalGlue());
            colR3.add(colR3Inner, new GridBagConstraints());

            // Columna 4: Eliminar por ID y tabla
            JPanel colR4 = new JPanel(new GridBagLayout());
            colR4.setOpaque(true);
            colR4.setBackground(null);
            JPanel colR4Inner = new JPanel();
            colR4Inner.setOpaque(false);
            colR4Inner.setLayout(new BoxLayout(colR4Inner, BoxLayout.Y_AXIS));
            colR4Inner.setMaximumSize(new Dimension(180, 180));
            colR4Inner.setPreferredSize(new Dimension(180, 180));
            JLabel eliminarIdLabel = new JLabel("ID:");
            JTextField eliminarIdField = new JTextField(12);
            JButton eliminarIdBtn = new JButton("Eliminar");
            eliminarIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            eliminarIdBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR4Inner.add(Box.createVerticalStrut(10));
            colR4Inner.add(eliminarIdLabel);
            colR4Inner.add(Box.createVerticalStrut(4));
            eliminarIdField.setMaximumSize(new Dimension(160, 25));
            colR4Inner.add(eliminarIdField);
            colR4Inner.add(Box.createVerticalStrut(10));
            eliminarIdBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colR4Inner.add(eliminarIdBtn);
            colR4Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para eliminar
            String[] cols4 = {"ID", "Nombre", "Apellido"};
            Object[][] data4 = {{"3", "Luis", "Mora"}, {"4", "Sofia", "Diaz"}};
            JTable table4 = new JTable(data4, cols4);
            JScrollPane tableScroll4 = new JScrollPane(table4);
            tableScroll4.setPreferredSize(new Dimension(160, 60));
            colR4Inner.add(tableScroll4);
            colR4Inner.add(Box.createVerticalGlue());
            colR4.add(colR4Inner, new GridBagConstraints());

            reservaPanel.add(colR1);
            reservaPanel.add(colR2);
            reservaPanel.add(colR3);
            reservaPanel.add(colR4);

            tabbedPane.addTab("Gestionar Reserva", new JScrollPane(reservaPanel));
            // Panel de contenido para 'Gestionar Facultades'
            JPanel facultadPanel = new JPanel(new GridLayout(1, 4, 30, 0));
            facultadPanel.setBackground(null);

            // Columna 1: Crear Facultad
            JPanel colF1 = new JPanel(new GridBagLayout());
            colF1.setOpaque(true);
            colF1.setBackground(null);
            JPanel colF1Inner = new JPanel();
            colF1Inner.setOpaque(false);
            colF1Inner.setLayout(new BoxLayout(colF1Inner, BoxLayout.Y_AXIS));
            colF1Inner.setMaximumSize(new Dimension(180, 180));
            colF1Inner.setPreferredSize(new Dimension(180, 180));
            JLabel nombreFacultadLabel1 = new JLabel("Nombre:");
            JTextField nombreFacultadField1 = new JTextField(12);
            JLabel codigoFacultadLabel1 = new JLabel("Código:");
            JTextField codigoFacultadField1 = new JTextField(12);
            JButton crearFacultadBtn = new JButton("Crear");
            nombreFacultadLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            codigoFacultadLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            crearFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF1Inner.add(Box.createVerticalStrut(10));
            colF1Inner.add(nombreFacultadLabel1);
            colF1Inner.add(Box.createVerticalStrut(4));
            nombreFacultadField1.setMaximumSize(new Dimension(160, 25));
            colF1Inner.add(nombreFacultadField1);
            colF1Inner.add(Box.createVerticalStrut(10));
            colF1Inner.add(codigoFacultadLabel1);
            colF1Inner.add(Box.createVerticalStrut(4));
            codigoFacultadField1.setMaximumSize(new Dimension(160, 25));
            colF1Inner.add(codigoFacultadField1);
            colF1Inner.add(Box.createVerticalStrut(18));
            colF1Inner.add(crearFacultadBtn);
            colF1Inner.add(Box.createVerticalGlue());
            colF1.add(colF1Inner, new GridBagConstraints());

            // Columna 2: Actualizar Facultad
            JPanel colF2 = new JPanel(new GridBagLayout());
            colF2.setOpaque(true);
            colF2.setBackground(null);
            JPanel colF2Inner = new JPanel();
            colF2Inner.setOpaque(false);
            colF2Inner.setLayout(new BoxLayout(colF2Inner, BoxLayout.Y_AXIS));
            colF2Inner.setMaximumSize(new Dimension(180, 180));
            colF2Inner.setPreferredSize(new Dimension(180, 180));
            JLabel nombreFacultadLabel2 = new JLabel("Nombre:");
            JTextField nombreFacultadField2 = new JTextField(12);
            JLabel codigoFacultadLabel2 = new JLabel("Código:");
            JTextField codigoFacultadField2 = new JTextField(12);
            JButton actualizarFacultadBtn = new JButton("Actualizar");
            nombreFacultadLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            codigoFacultadLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            actualizarFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF2Inner.add(Box.createVerticalStrut(10));
            colF2Inner.add(nombreFacultadLabel2);
            colF2Inner.add(Box.createVerticalStrut(4));
            nombreFacultadField2.setMaximumSize(new Dimension(160, 25));
            colF2Inner.add(nombreFacultadField2);
            colF2Inner.add(Box.createVerticalStrut(10));
            colF2Inner.add(codigoFacultadLabel2);
            colF2Inner.add(Box.createVerticalStrut(4));
            codigoFacultadField2.setMaximumSize(new Dimension(160, 25));
            colF2Inner.add(codigoFacultadField2);
            colF2Inner.add(Box.createVerticalStrut(18));
            colF2Inner.add(actualizarFacultadBtn);
            colF2Inner.add(Box.createVerticalGlue());
            colF2.add(colF2Inner, new GridBagConstraints());

            // Columna 3: Buscar Facultad por ID y tabla
            JPanel colF3 = new JPanel(new GridBagLayout());
            colF3.setOpaque(true);
            colF3.setBackground(null);
            JPanel colF3Inner = new JPanel();
            colF3Inner.setOpaque(false);
            colF3Inner.setLayout(new BoxLayout(colF3Inner, BoxLayout.Y_AXIS));
            colF3Inner.setMaximumSize(new Dimension(180, 180));
            colF3Inner.setPreferredSize(new Dimension(180, 180));
            JLabel buscarFacultadIdLabel = new JLabel("ID:");
            JTextField buscarFacultadIdField = new JTextField(12);
            JButton buscarFacultadBtn = new JButton("Buscar");
            buscarFacultadIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buscarFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF3Inner.add(Box.createVerticalStrut(10));
            colF3Inner.add(buscarFacultadIdLabel);
            colF3Inner.add(Box.createVerticalStrut(4));
            buscarFacultadIdField.setMaximumSize(new Dimension(160, 25));
            colF3Inner.add(buscarFacultadIdField);
            colF3Inner.add(Box.createVerticalStrut(10));
            buscarFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF3Inner.add(buscarFacultadBtn);
            colF3Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para buscar facultad
            String[] colsF3 = {"ID", "Nombre", "Código"};
            Object[][] dataF3 = {{"1", "Facultad A", "FA01"}, {"2", "Facultad B", "FB02"}};
            JTable tableF3 = new JTable(dataF3, colsF3);
            JScrollPane tableScrollF3 = new JScrollPane(tableF3);
            tableScrollF3.setPreferredSize(new Dimension(160, 60));
            colF3Inner.add(tableScrollF3);
            colF3Inner.add(Box.createVerticalGlue());
            colF3.add(colF3Inner, new GridBagConstraints());

            // Columna 4: Eliminar Facultad por ID y tabla
            JPanel colF4 = new JPanel(new GridBagLayout());
            colF4.setOpaque(true);
            colF4.setBackground(null);
            JPanel colF4Inner = new JPanel();
            colF4Inner.setOpaque(false);
            colF4Inner.setLayout(new BoxLayout(colF4Inner, BoxLayout.Y_AXIS));
            colF4Inner.setMaximumSize(new Dimension(180, 180));
            colF4Inner.setPreferredSize(new Dimension(180, 180));
            JLabel eliminarFacultadIdLabel = new JLabel("ID:");
            JTextField eliminarFacultadIdField = new JTextField(12);
            JButton eliminarFacultadBtn = new JButton("Eliminar");
            eliminarFacultadIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            eliminarFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF4Inner.add(Box.createVerticalStrut(10));
            colF4Inner.add(eliminarFacultadIdLabel);
            colF4Inner.add(Box.createVerticalStrut(4));
            eliminarFacultadIdField.setMaximumSize(new Dimension(160, 25));
            colF4Inner.add(eliminarFacultadIdField);
            colF4Inner.add(Box.createVerticalStrut(10));
            eliminarFacultadBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colF4Inner.add(eliminarFacultadBtn);
            colF4Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para eliminar facultad
            String[] colsF4 = {"ID", "Nombre", "Código"};
            Object[][] dataF4 = {{"3", "Facultad C", "FC03"}, {"4", "Facultad D", "FD04"}};
            JTable tableF4 = new JTable(dataF4, colsF4);
            JScrollPane tableScrollF4 = new JScrollPane(tableF4);
            tableScrollF4.setPreferredSize(new Dimension(160, 60));
            colF4Inner.add(tableScrollF4);
            colF4Inner.add(Box.createVerticalGlue());
            colF4.add(colF4Inner, new GridBagConstraints());

            facultadPanel.add(colF1);
            facultadPanel.add(colF2);
            facultadPanel.add(colF3);
            facultadPanel.add(colF4);

            tabbedPane.addTab("Gestionar Facultades", new JScrollPane(facultadPanel));
            // Panel de contenido para 'Gestionar Equipos'
            JPanel equipoPanel = new JPanel(new GridLayout(1, 4, 30, 0));
            equipoPanel.setBackground(null);

            // Columna 1: Crear Equipo
            JPanel colE1 = new JPanel(new GridBagLayout());
            colE1.setOpaque(true);
            colE1.setBackground(null);
            JPanel colE1Inner = new JPanel();
            colE1Inner.setOpaque(false);
            colE1Inner.setLayout(new BoxLayout(colE1Inner, BoxLayout.Y_AXIS));
            colE1Inner.setMaximumSize(new Dimension(180, 180));
            colE1Inner.setPreferredSize(new Dimension(180, 180));
            JLabel nombreEquipoLabel1 = new JLabel("Nombre:");
            JTextField nombreEquipoField1 = new JTextField(12);
            JLabel categoriaEquipoLabel1 = new JLabel("Categoría:");
            JTextField categoriaEquipoField1 = new JTextField(12);
            JButton crearEquipoBtn = new JButton("Crear");
            nombreEquipoLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoriaEquipoLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
            crearEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE1Inner.add(Box.createVerticalStrut(10));
            colE1Inner.add(nombreEquipoLabel1);
            colE1Inner.add(Box.createVerticalStrut(4));
            nombreEquipoField1.setMaximumSize(new Dimension(160, 25));
            colE1Inner.add(nombreEquipoField1);
            colE1Inner.add(Box.createVerticalStrut(10));
            colE1Inner.add(categoriaEquipoLabel1);
            colE1Inner.add(Box.createVerticalStrut(4));
            categoriaEquipoField1.setMaximumSize(new Dimension(160, 25));
            colE1Inner.add(categoriaEquipoField1);
            colE1Inner.add(Box.createVerticalStrut(18));
            colE1Inner.add(crearEquipoBtn);
            colE1Inner.add(Box.createVerticalGlue());
            colE1.add(colE1Inner, new GridBagConstraints());

            // Columna 2: Actualizar Equipo
            JPanel colE2 = new JPanel(new GridBagLayout());
            colE2.setOpaque(true);
            colE2.setBackground(null);
            JPanel colE2Inner = new JPanel();
            colE2Inner.setOpaque(false);
            colE2Inner.setLayout(new BoxLayout(colE2Inner, BoxLayout.Y_AXIS));
            colE2Inner.setMaximumSize(new Dimension(180, 180));
            colE2Inner.setPreferredSize(new Dimension(180, 180));
            JLabel nombreEquipoLabel2 = new JLabel("Nombre:");
            JTextField nombreEquipoField2 = new JTextField(12);
            JLabel categoriaEquipoLabel2 = new JLabel("Categoría:");
            JTextField categoriaEquipoField2 = new JTextField(12);
            JButton actualizarEquipoBtn = new JButton("Actualizar");
            nombreEquipoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            categoriaEquipoLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
            actualizarEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE2Inner.add(Box.createVerticalStrut(10));
            colE2Inner.add(nombreEquipoLabel2);
            colE2Inner.add(Box.createVerticalStrut(4));
            nombreEquipoField2.setMaximumSize(new Dimension(160, 25));
            colE2Inner.add(nombreEquipoField2);
            colE2Inner.add(Box.createVerticalStrut(10));
            colE2Inner.add(categoriaEquipoLabel2);
            colE2Inner.add(Box.createVerticalStrut(4));
            categoriaEquipoField2.setMaximumSize(new Dimension(160, 25));
            colE2Inner.add(categoriaEquipoField2);
            colE2Inner.add(Box.createVerticalStrut(18));
            colE2Inner.add(actualizarEquipoBtn);
            colE2Inner.add(Box.createVerticalGlue());
            colE2.add(colE2Inner, new GridBagConstraints());

            // Columna 3: Buscar Equipo por ID y tabla
            JPanel colE3 = new JPanel(new GridBagLayout());
            colE3.setOpaque(true);
            colE3.setBackground(null);
            JPanel colE3Inner = new JPanel();
            colE3Inner.setOpaque(false);
            colE3Inner.setLayout(new BoxLayout(colE3Inner, BoxLayout.Y_AXIS));
            colE3Inner.setMaximumSize(new Dimension(180, 180));
            colE3Inner.setPreferredSize(new Dimension(180, 180));
            JLabel buscarEquipoIdLabel = new JLabel("ID:");
            JTextField buscarEquipoIdField = new JTextField(12);
            JButton buscarEquipoBtn = new JButton("Buscar");
            buscarEquipoIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            buscarEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE3Inner.add(Box.createVerticalStrut(10));
            colE3Inner.add(buscarEquipoIdLabel);
            colE3Inner.add(Box.createVerticalStrut(4));
            buscarEquipoIdField.setMaximumSize(new Dimension(160, 25));
            colE3Inner.add(buscarEquipoIdField);
            colE3Inner.add(Box.createVerticalStrut(10));
            buscarEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE3Inner.add(buscarEquipoBtn);
            colE3Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para buscar equipo
            String[] colsE3 = {"ID", "Nombre", "Categoría"};
            Object[][] dataE3 = {{"1", "Proyector", "Audiovisual"}, {"2", "Micrófono", "Sonido"}};
            JTable tableE3 = new JTable(dataE3, colsE3);
            JScrollPane tableScrollE3 = new JScrollPane(tableE3);
            tableScrollE3.setPreferredSize(new Dimension(160, 60));
            colE3Inner.add(tableScrollE3);
            colE3Inner.add(Box.createVerticalGlue());
            colE3.add(colE3Inner, new GridBagConstraints());

            // Columna 4: Eliminar Equipo por ID y tabla
            JPanel colE4 = new JPanel(new GridBagLayout());
            colE4.setOpaque(true);
            colE4.setBackground(null);
            JPanel colE4Inner = new JPanel();
            colE4Inner.setOpaque(false);
            colE4Inner.setLayout(new BoxLayout(colE4Inner, BoxLayout.Y_AXIS));
            colE4Inner.setMaximumSize(new Dimension(180, 180));
            colE4Inner.setPreferredSize(new Dimension(180, 180));
            JLabel eliminarEquipoIdLabel = new JLabel("ID:");
            JTextField eliminarEquipoIdField = new JTextField(12);
            JButton eliminarEquipoBtn = new JButton("Eliminar");
            eliminarEquipoIdLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            eliminarEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE4Inner.add(Box.createVerticalStrut(10));
            colE4Inner.add(eliminarEquipoIdLabel);
            colE4Inner.add(Box.createVerticalStrut(4));
            eliminarEquipoIdField.setMaximumSize(new Dimension(160, 25));
            colE4Inner.add(eliminarEquipoIdField);
            colE4Inner.add(Box.createVerticalStrut(10));
            eliminarEquipoBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            colE4Inner.add(eliminarEquipoBtn);
            colE4Inner.add(Box.createVerticalStrut(10));
            // Tabla de ejemplo para eliminar equipo
            String[] colsE4 = {"ID", "Nombre", "Categoría"};
            Object[][] dataE4 = {{"3", "Laptop", "Computo"}, {"4", "Parlante", "Sonido"}};
            JTable tableE4 = new JTable(dataE4, colsE4);
            JScrollPane tableScrollE4 = new JScrollPane(tableE4);
            tableScrollE4.setPreferredSize(new Dimension(160, 60));
            colE4Inner.add(tableScrollE4);
            colE4Inner.add(Box.createVerticalGlue());
            colE4.add(colE4Inner, new GridBagConstraints());

            equipoPanel.add(colE1);
            equipoPanel.add(colE2);
            equipoPanel.add(colE3);
            equipoPanel.add(colE4);

            tabbedPane.addTab("Gestionar Equipos", new JScrollPane(equipoPanel));

            tabbedPane.setPreferredSize(new Dimension(650, 180));
            frame.add(tabbedPane, BorderLayout.CENTER);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    public static void mainWithUsuario(ec.edu.uce.dominio.Usuario usuario, ec.edu.uce.dominio.Facultad facultad) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception e) {}

            JFrame frame = new JFrame("SIRAA - Usuario: " + usuario.getNombre() + " " + usuario.getApellido());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 400);
            frame.setLayout(new BorderLayout());

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.setFont(new Font("SansSerif", Font.PLAIN, 14));

            // ===================== PANEL USUARIO =====================
            JPanel usuarioPanel = new JPanel(new BorderLayout());
            // Tabla de usuarios
            String[] usuarioCols = {"ID", "Nombre", "Apellido", "Correo"};
            DefaultTableModel usuarioTableModel = new DefaultTableModel(usuarioCols, 0);
            JTable usuarioTable = new JTable(usuarioTableModel);
            JScrollPane usuarioScroll = new JScrollPane(usuarioTable);
            usuarioPanel.add(usuarioScroll, BorderLayout.CENTER);
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
            Runnable refreshUsuarios = () -> {
                usuarioTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Usuario u : facultad.getUsuarios()) {
                    usuarioTableModel.addRow(new Object[]{u.getIdUsuario(), u.getNombre(), u.getApellido(), u.getCorreo()});
                }
            };
            refreshUsuarios.run();
            // Crear usuario
            crearUsuarioBtn.addActionListener(e -> {
                String nombre = nombreField.getText().trim();
                String apellido = apellidoField.getText().trim();
                String correo = correoField.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Completa todos los campos."); return;
                }
                facultad.crearUsuario(new ec.edu.uce.dominio.Usuario(nombre, apellido, correo));
                refreshUsuarios.run();
            });
            // Actualizar usuario
            actualizarUsuarioBtn.addActionListener(e -> {
                int row = usuarioTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un usuario."); return; }
                int id = (int) usuarioTableModel.getValueAt(row, 0);
                for (ec.edu.uce.dominio.Usuario u : facultad.getUsuarios()) {
                    if (u.getIdUsuario() == id) {
                        u.setNombre(nombreField.getText().trim());
                        u.setApellido(apellidoField.getText().trim());
                        u.setCorreo(correoField.getText().trim());
                        break;
                    }
                }
                refreshUsuarios.run();
            });
            // Eliminar usuario
            eliminarUsuarioBtn.addActionListener(e -> {
                int row = usuarioTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un usuario."); return; }
                int id = (int) usuarioTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Usuario toRemove = null;
                for (ec.edu.uce.dominio.Usuario u : facultad.getUsuarios()) {
                    if (u.getIdUsuario() == id) { toRemove = u; break; }
                }
                if (toRemove != null) facultad.eliminarUsuario(toRemove);
                refreshUsuarios.run();
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
            String[] reservaCols = {"ID", "Código", "Estado", "Fecha Inicio", "Fecha Fin"};
            DefaultTableModel reservaTableModel = new DefaultTableModel(reservaCols, 0);
            JTable reservaTable = new JTable(reservaTableModel);
            JScrollPane reservaScroll = new JScrollPane(reservaTable);
            reservaPanel.add(reservaScroll, BorderLayout.CENTER);
            JPanel reservaActions = new JPanel();
            JTextField fechaInicioField = new JTextField(12);
            JTextField fechaFinField = new JTextField(12);
            JButton crearReservaBtn = new JButton("Crear");
            JButton eliminarReservaBtn = new JButton("Eliminar");
            reservaActions.add(new JLabel("Fecha Inicio (yyyy-MM-dd):")); reservaActions.add(fechaInicioField);
            reservaActions.add(new JLabel("Fecha Fin (yyyy-MM-dd):")); reservaActions.add(fechaFinField);
            reservaActions.add(crearReservaBtn);
            reservaActions.add(eliminarReservaBtn);
            reservaPanel.add(reservaActions, BorderLayout.SOUTH);
            Runnable refreshReservas = () -> {
                reservaTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Reserva r : usuario.getReservas()) {
                    reservaTableModel.addRow(new Object[]{r.getIdReserva(), r.getCodigoReserva(), r.getEstado().getDescripcion(), r.getFechaInicio(), r.getFechaFin()});
                }
            };
            refreshReservas.run();
            crearReservaBtn.addActionListener(e -> {
                try {
                    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                    java.util.Date inicio = sdf.parse(fechaInicioField.getText().trim());
                    java.util.Date fin = sdf.parse(fechaFinField.getText().trim());
                    usuario.crearReserva(new ec.edu.uce.dominio.Reserva(inicio, fin));
                    refreshReservas.run();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Formato de fecha incorrecto.");
                }
            });
            eliminarReservaBtn.addActionListener(e -> {
                int row = reservaTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona una reserva."); return; }
                int id = (int) reservaTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Reserva toRemove = null;
                for (ec.edu.uce.dominio.Reserva r : usuario.getReservas()) {
                    if (r.getIdReserva() == id) { toRemove = r; break; }
                }
                if (toRemove != null) usuario.eliminarReserva(toRemove);
                refreshReservas.run();
            });
            tabbedPane.addTab("Gestionar Reserva", reservaPanel);

            // ===================== PANEL FACULTAD =====================
            JPanel facultadPanel = new JPanel(new BorderLayout());
            String[] facultadCols = {"ID", "Código", "Nombre", "#Auditorios", "#Usuarios"};
            DefaultTableModel facultadTableModel = new DefaultTableModel(facultadCols, 0);
            JTable facultadTable = new JTable(facultadTableModel);
            JScrollPane facultadScroll = new JScrollPane(facultadTable);
            facultadPanel.add(facultadScroll, BorderLayout.CENTER);
            JPanel facultadActions = new JPanel();
            JTextField nombreFacultadField = new JTextField(10);
            JButton crearFacultadBtn = new JButton("Crear");
            JButton eliminarFacultadBtn = new JButton("Eliminar");
            facultadActions.add(new JLabel("Nombre:")); facultadActions.add(nombreFacultadField);
            facultadActions.add(crearFacultadBtn);
            facultadActions.add(eliminarFacultadBtn);
            facultadPanel.add(facultadActions, BorderLayout.SOUTH);
            Runnable refreshFacultades = () -> {
                facultadTableModel.setRowCount(0);
                for (ec.edu.uce.dominio.Facultad f : ec.edu.uce.dominio.Universidad.getInstancia().getFacultades()) {
                    facultadTableModel.addRow(new Object[]{f.getIdFacultad(), f.getCodigoFacultad(), f.getNombre(), f.getNumAuditorios(), f.getNumUsuarios()});
                }
            };
            refreshFacultades.run();
            crearFacultadBtn.addActionListener(e -> {
                String nombre = nombreFacultadField.getText().trim();
                if (nombre.isEmpty()) { JOptionPane.showMessageDialog(frame, "Ingrese nombre de facultad."); return; }
                ec.edu.uce.dominio.Universidad.getInstancia().crearFacultad(new ec.edu.uce.dominio.Facultad(nombre, 5));
                refreshFacultades.run();
            });
            eliminarFacultadBtn.addActionListener(e -> {
                int row = facultadTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona una facultad."); return; }
                int id = (int) facultadTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Facultad toRemove = null;
                for (ec.edu.uce.dominio.Facultad f : ec.edu.uce.dominio.Universidad.getInstancia().getFacultades()) {
                    if (f.getIdFacultad() == id) { toRemove = f; break; }
                }
                if (toRemove != null) ec.edu.uce.dominio.Universidad.getInstancia().eliminarFacultad(toRemove);
                refreshFacultades.run();
            });
            tabbedPane.addTab("Gestionar Facultades", facultadPanel);

            // ===================== PANEL EQUIPO =====================
            JPanel equipoPanel = new JPanel(new BorderLayout());
            String[] equipoCols = {"ID", "Nombre", "Categoría", "Disponible"};
            DefaultTableModel equipoTableModel = new DefaultTableModel(equipoCols, 0);
            JTable equipoTable = new JTable(equipoTableModel);
            JScrollPane equipoScroll = new JScrollPane(equipoTable);
            equipoPanel.add(equipoScroll, BorderLayout.CENTER);
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
            // Para la demo, usar la primera reserva del usuario (si existe)
            Runnable refreshEquipos = () -> {
                equipoTableModel.setRowCount(0);
                if (!usuario.getReservas().isEmpty()) {
                    ec.edu.uce.dominio.Reserva r = usuario.getReservas().get(0);
                    for (ec.edu.uce.dominio.Equipo eq : r.getEquipos()) {
                        equipoTableModel.addRow(new Object[]{eq.getIdEquipo(), eq.getNombre(), eq.getCategoria(), eq.getDisponibilidad() ? "Sí" : "No"});
                    }
                }
            };
            refreshEquipos.run();
            crearEquipoBtn.addActionListener(e -> {
                if (usuario.getReservas().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Primero cree una reserva."); return;
                }
                String nombre = nombreEquipoField.getText().trim();
                String categoria = categoriaEquipoField.getText().trim();
                boolean disponible = disponibleCheck.isSelected();
                if (nombre.isEmpty() || categoria.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Ingrese nombre y categoría."); return;
                }
                ec.edu.uce.dominio.Reserva r = usuario.getReservas().get(0);
                try {
                    r.crearEquipo(new ec.edu.uce.dominio.Equipo(nombre, categoria, disponible));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(frame, "Error al crear equipo: " + ex.getMessage());
                }
                refreshEquipos.run();
            });
            eliminarEquipoBtn.addActionListener(e -> {
                if (usuario.getReservas().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Primero cree una reserva."); return;
                }
                int row = equipoTable.getSelectedRow();
                if (row == -1) { JOptionPane.showMessageDialog(frame, "Selecciona un equipo."); return; }
                int id = (int) equipoTableModel.getValueAt(row, 0);
                ec.edu.uce.dominio.Reserva r = usuario.getReservas().get(0);
                ec.edu.uce.dominio.Equipo toRemove = null;
                for (ec.edu.uce.dominio.Equipo eq : r.getEquipos()) {
                    if (eq.getIdEquipo() == id) { toRemove = eq; break; }
                }
                if (toRemove != null) r.eliminarEquipo(toRemove);
                refreshEquipos.run();
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