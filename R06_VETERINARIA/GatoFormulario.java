import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class GatoFormulario extends JFrame {

    private JTextField nombreField;
    private JComboBox<RazaGato> razaComboBox;
    private JTextField fechaNacimientoField;
    private JTextField pesoField;
    private JTextField microchipField;
    private JTextArea comentariosArea;
    private ClinicaVeterinariaGUI parentFrame;

    public GatoFormulario(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Formulario de Gato");
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
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(nombreLabel, gbc);
        gbc.gridx++;
        nombreField = new JTextField(20);
        nombreField.setMargin(new Insets(0, 5, 0, 5));
        nombreField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(nombreField, gbc);

        // Raza
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel razaLabel = new JLabel("Raza ");
        razaLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(razaLabel, gbc);
        gbc.gridx++;
        razaComboBox = new JComboBox<>(RazaGato.values());
        razaComboBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
        razaComboBox.setBackground(Color.WHITE);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(razaComboBox, gbc);

        // Fecha de Nacimiento
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento (yyyy-MM-dd) ");
        fechaNacimientoLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(fechaNacimientoLabel, gbc);
        gbc.gridx++;
        fechaNacimientoField = new JTextField(20);
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
        pesoField = new JTextField(20);
        pesoField.setMargin(new Insets(0, 5, 0, 5));
        pesoField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(pesoField, gbc);

        // Microchip
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel microchipLabel = new JLabel("Nº de Microchip ");
        microchipLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(microchipLabel, gbc);
        gbc.gridx++;
        microchipField = new JTextField(20);
        microchipField.setMargin(new Insets(0, 5, 0, 5));
        microchipField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(microchipField, gbc);

        // Comentarios
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel comentariosLabel = new JLabel("Comentarios:");
        comentariosLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(comentariosLabel, gbc);
        gbc.gridx++;
        comentariosArea = new JTextArea(5, 20);
        comentariosArea.setEditable(true);
        comentariosArea.setLineWrap(true);
        comentariosArea.setMargin(new Insets(0, 5, 0, 5));
        comentariosArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(new JScrollPane(comentariosArea), gbc);

        // Botón para agregar
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton agregarButton = new JButton("Agregar Gato");
        agregarButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarGato();
            }
        });
        panel.add(agregarButton, gbc);

        add(panel);
    }

    private void volverAtras() {
        parentFrame.setVisible(true);
        dispose();
    }

    private void agregarGato() {
        String nombre = nombreField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del gato no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RazaGato raza = (RazaGato) razaComboBox.getSelectedItem();

        String fechaNacimientoText = fechaNacimientoField.getText().trim();
        if (fechaNacimientoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento del gato no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
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


        String microchip = microchipField.getText();
        String comentarios = comentariosArea.getText();

        Gato gato = new Gato(nombre, raza, fechaNacimiento, peso, microchip, comentarios);

        ClinicaVeterinaria.getInstance().insertaAnimal(gato);

        limpiarFormulario();
        dispose();
        parentFrame.setVisible(true);
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        razaComboBox.setSelectedIndex(0);
        fechaNacimientoField.setText("");
        pesoField.setText("");
        microchipField.setText("");
        comentariosArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClinicaVeterinariaGUI gui = new ClinicaVeterinariaGUI();
            gui.setVisible(true);

            GatoFormulario fichaGato = new GatoFormulario(gui);
            fichaGato.setVisible(true);
        });
    }
}


