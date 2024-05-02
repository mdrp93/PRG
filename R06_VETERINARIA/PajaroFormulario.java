import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class PajaroFormulario extends JFrame {

    private JTextField nombreField;
    private JComboBox<EspeciePajaro> especieComboBox;
    private JTextField fechaNacimientoField;
    private JTextField pesoField;
    private JCheckBox cantorCheckBox;
    private JTextArea comentariosArea;
    private ClinicaVeterinariaGUI parentFrame;

    public PajaroFormulario(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Formulario de Pájaro");
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        int parentX = parentFrame.getX();
        int parentY = parentFrame.getY();
        int parentWidth = parentFrame.getWidth();
        int parentHeight = parentFrame.getHeight();
        setLocation(parentX + (parentWidth - getWidth()) / 2, parentY + parentHeight - getHeight());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                volverAtras();
            }
        });

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.PINK);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Nombre
        JLabel nombreLabel = new JLabel("Nombre ");
        nombreLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(nombreLabel, gbc);
        gbc.gridx++;
        nombreField = new JTextField(20); // Ancho de 20 caracteres
        nombreField.setMargin(new Insets(0, 5, 0, 5));
        nombreField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(nombreField, gbc);

        // Especie
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel especieLabel = new JLabel("Especie ");
        especieLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(especieLabel, gbc);
        gbc.gridx++;
        especieComboBox = new JComboBox<>(EspeciePajaro.values());
        especieComboBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
        especieComboBox.setBackground(Color.WHITE);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(especieComboBox, gbc);

        // Fecha de Nacimiento
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento (yyyy-MM-dd) ");
        fechaNacimientoLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(fechaNacimientoLabel, gbc);
        gbc.gridx++;
        fechaNacimientoField = new JTextField(20); // Ancho de 20 caracteres
        fechaNacimientoField.setMargin(new Insets(0, 5, 0, 5));
        fechaNacimientoField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(fechaNacimientoField, gbc);

        // Peso
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel pesoLabel = new JLabel("Peso ");
        pesoLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(pesoLabel, gbc);
        gbc.gridx++;
        pesoField = new JTextField(20); // Ancho de 20 caracteres
        pesoField.setMargin(new Insets(0, 5, 0, 5));
        pesoField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(pesoField, gbc);

        // ¿Es cantor?
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cantorLabel = new JLabel("¿Es cantor? ");
        cantorLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(cantorLabel, gbc);
        gbc.gridx++;
        cantorCheckBox = new JCheckBox();
        cantorCheckBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(cantorCheckBox, gbc);

        // Comentarios
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel comentariosLabel = new JLabel("Comentarios:");
        comentariosLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(comentariosLabel, gbc);
        gbc.gridx++;
        comentariosArea = new JTextArea(5, 20); // 5 filas y 20 caracteres de ancho
        comentariosArea.setEditable(true); // El usuario puede editar el JTextArea comentariosArea
        comentariosArea.setLineWrap(true); // El texto se envolverá automáticamente cuando alcance el borde derecho del JTextArea
        comentariosArea.setMargin(new Insets(0, 5, 0, 5));
        comentariosArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(new JScrollPane(comentariosArea), gbc);

        // Botón para agregar
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton agregarButton = new JButton("Agregar Pájaro");
        agregarButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPajaro();
            }
        });
        panel.add(agregarButton, gbc);

        add(panel);
    }

    private void volverAtras() {
        parentFrame.setVisible(true);
        dispose();
    }

    private void agregarPajaro() {
        String nombre = nombreField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del pájaro no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EspeciePajaro especie = (EspeciePajaro) especieComboBox.getSelectedItem();

        String fechaNacimientoText = fechaNacimientoField.getText().trim();
        if (fechaNacimientoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento del pájaro no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaNacimientoText);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha de nacimiento incorrecto. Debe ser 'yyyy-MM-dd'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double peso;
        try {
            peso = Double.parseDouble(pesoField.getText());
            if (peso <= 0) {
                JOptionPane.showMessageDialog(this, "El peso debe ser un número positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Formato de peso incorrecto. Debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean cantor = cantorCheckBox.isSelected();
        String comentarios = comentariosArea.getText();

        Pajaro pajaro = new Pajaro(nombre, especie, fechaNacimiento, peso, cantor, comentarios);
        ClinicaVeterinaria.getInstance().insertaAnimal(pajaro);

        limpiarFormulario();
        dispose();
        parentFrame.setVisible(true);
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        especieComboBox.setSelectedIndex(0);
        fechaNacimientoField.setText("");
        pesoField.setText("");
        cantorCheckBox.setSelected(false);
        comentariosArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClinicaVeterinariaGUI gui = new ClinicaVeterinariaGUI();
            gui.setVisible(true);

            PajaroFormulario formulario = new PajaroFormulario(gui);
            formulario.setVisible(true);
        });
    }
}
