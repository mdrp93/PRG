import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ReptilFormulario extends JFrame {

    private JTextField nombreField;
    private JComboBox<EspecieReptil> especieComboBox;
    private JTextField fechaNacimientoField;
    private JTextField pesoField;
    private JCheckBox venenosoCheckBox;
    private JTextArea comentariosArea;
    private ClinicaVeterinariaGUI parentFrame;

    public ReptilFormulario(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Formulario de Reptil");
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
        especieComboBox = new JComboBox<>(EspecieReptil.values());
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

        // ¿Es venenoso?
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel venenosoLabel = new JLabel("¿Es venenoso? ");
        venenosoLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(venenosoLabel, gbc);
        gbc.gridx++;
        venenosoCheckBox = new JCheckBox();
        venenosoCheckBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
        venenosoCheckBox.setBackground(Color.WHITE);
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(venenosoCheckBox, gbc);


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
        JButton agregarButton = new JButton("Agregar Reptil");
        agregarButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarReptil();
            }
        });
        panel.add(agregarButton, gbc);

        add(panel);
    }

    private void volverAtras() {
        parentFrame.setVisible(true);
        dispose();
    }

    private void agregarReptil() {
        String nombre = nombreField.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del reptil no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        EspecieReptil especie = (EspecieReptil) especieComboBox.getSelectedItem();

        String fechaNacimientoText = fechaNacimientoField.getText().trim();
        if (fechaNacimientoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento del reptil no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
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

        boolean venenoso = venenosoCheckBox.isSelected();
        String comentarios = comentariosArea.getText();

        Reptil reptil = new Reptil(nombre, especie, fechaNacimiento, peso, venenoso, comentarios);
        ClinicaVeterinaria.getInstance().insertaAnimal(reptil);

        limpiarFormulario();
        dispose();
        parentFrame.setVisible(true);
    }

    private void limpiarFormulario() {
        nombreField.setText("");
        especieComboBox.setSelectedIndex(0);
        fechaNacimientoField.setText("");
        pesoField.setText("");
        venenosoCheckBox.setSelected(false);
        comentariosArea.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClinicaVeterinariaGUI gui = new ClinicaVeterinariaGUI();
            gui.setVisible(true);

            ReptilFormulario formulario = new ReptilFormulario(gui);
            formulario.setVisible(true);
        });
    }
}

