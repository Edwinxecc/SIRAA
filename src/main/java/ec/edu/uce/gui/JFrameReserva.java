package ec.edu.uce.gui;

import ec.edu.uce.dominio.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class JFrameReserva extends JFrame {
    private JPanel panel1;
    private JButton buttonCrear;
    private JLabel JLabelNombre;
    private JTextField JtextFieldNombre;
    private JLabel JLabelApellido;
    private JTextField JtextFieldApellido;
    private JButton ingresarButton;
    private JButton registrarseButton;

    private Facultad facultad; // Asumimos una sola facultad para demo
    private Universidad universidad;
    private static final String ARCHIVO_UNIVERSIDAD = "universidad.dat";

    public JFrameReserva() {
        setTitle("Login - SIRAA");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setContentPane(panel1);
        setVisible(true);

        // Al iniciar, intentar cargar universidad desde archivo
        Object cargada = PersistenciaUtil.cargarObjeto(ARCHIVO_UNIVERSIDAD);
        if (cargada != null && cargada instanceof Universidad) {
            universidad = (Universidad) cargada;
        } else {
            universidad = Universidad.getInstancia();
        }

        // Demo: usar la primera facultad existente o crear una
        if (universidad.getFacultades().isEmpty()) {
            facultad = new Facultad("Demo Facultad", 5);
            universidad.crearFacultad(facultad);
            PersistenciaUtil.guardarObjeto(universidad, ARCHIVO_UNIVERSIDAD);
        } else {
            facultad = universidad.getFacultades().get(0);
        }

        ingresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JtextFieldNombre.getText().trim();
                String apellido = JtextFieldApellido.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Ingrese nombre y apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Usuario usuario = buscarUsuario(nombre, apellido);
                if (usuario != null) {
                    PersistenciaUtil.guardarObjeto(universidad, ARCHIVO_UNIVERSIDAD);
                    abrirVentanaPrincipal(usuario);
                } else {
                    JOptionPane.showMessageDialog(panel1, "Usuario no encontrado. Regístrese primero.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registrarseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = JtextFieldNombre.getText().trim();
                String apellido = JtextFieldApellido.getText().trim();
                if (nombre.isEmpty() || apellido.isEmpty()) {
                    JOptionPane.showMessageDialog(panel1, "Ingrese nombre y apellido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Usuario usuario = buscarUsuario(nombre, apellido);
                if (usuario == null) {
                    usuario = new Usuario(nombre, apellido, nombre.toLowerCase() + "." + apellido.toLowerCase() + "@uce.edu.ec");
                    facultad.crearUsuario(usuario);
                    PersistenciaUtil.guardarObjeto(universidad, ARCHIVO_UNIVERSIDAD);
                    JOptionPane.showMessageDialog(panel1, "Usuario registrado exitosamente.");
                    abrirVentanaPrincipal(usuario);
                } else {
                    JOptionPane.showMessageDialog(panel1, "El usuario ya existe. Inicie sesión.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }

    private Usuario buscarUsuario(String nombre, String apellido) {
        for (Usuario u : facultad.getUsuarios()) {
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getApellido().equalsIgnoreCase(apellido)) {
                return u;
            }
        }
        return null;
    }

    private void abrirVentanaPrincipal(Usuario usuario) {
        // Abrir la ventana principal y cerrar el login
        PersistenciaUtil.guardarObjeto(universidad, ARCHIVO_UNIVERSIDAD);
        this.dispose();
        TabbedPaneSample.mainWithUsuario(usuario, facultad);
    }

    // Para inicializar desde main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JFrameReserva());
    }
}
