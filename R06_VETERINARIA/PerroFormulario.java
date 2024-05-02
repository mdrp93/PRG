import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.*;

public class PerroFormulario extends JFrame {

    private JTextField nombreField;
    private JComboBox<RazaPerro> razaComboBox;
    private JTextField fechaNacimientoField;
    private JTextField pesoField;
    private JTextField microchipField;
    private JTextArea comentariosArea;
    private ClinicaVeterinariaGUI parentFrame;

    public PerroFormulario(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame; // Inicializar el campo de regresar a inicio

        setTitle("Formulario de Perro");
        setSize(700, 500);
        setResizable(false); // Evitar que se pueda cambiar el tamaño de la ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Evitar que se cierre al pulsar la X

        // Con esto podemos centrar la ventana en relación con la ventana principal, abajo
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
        //gbc.anchor = GridBagConstraints.EAST;   //alinear los componentes del GridBagLayout en el lado izquierdo del contenedor.
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

        // Raza
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel razaLabel = new JLabel("Raza ");
        razaLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(razaLabel, gbc);
        gbc.gridx++;
        razaComboBox = new JComboBox<>(RazaPerro.values());
        razaComboBox.setFont(new Font("Helvetica", Font.PLAIN, 20));
        razaComboBox.setBackground(Color.WHITE);

        panel.add(razaComboBox, gbc);

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

        // Microchip
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel microchipLabel = new JLabel("Nº de Microchip ");
        microchipLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(microchipLabel, gbc);
        gbc.gridx++;
        microchipField = new JTextField(20); // Ancho de 20 caracteres
        microchipField.setMargin(new Insets(0, 5, 0, 5));
        microchipField.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(microchipField, gbc);

        // Comentarios
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel comentariosLabel = new JLabel("Comentarios");
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
        JButton agregarButton = new JButton("Agregar Perro");
        agregarButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        agregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarPerro();
            }
        });
        panel.add(agregarButton, gbc);

        add(panel);
    }


    private void volverAtras() {
        parentFrame.setVisible(true); // Mostrar la ventana principal nuevamente
        dispose(); // Cerrar la ventana del formulario de perro
    }


    private void agregarPerro() {
        // Obtener los valores del formulario
        String nombre = nombreField.getText().trim();
        RazaPerro raza = (RazaPerro) razaComboBox.getSelectedItem();
        String microchip = microchipField.getText();
        String comentarios = comentariosArea.getText().trim(); // Asegurarse de eliminar espacios en blanco al inicio y al final

        // Validar que el nombre no esté vacío
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre del perro no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que la fecha de nacimiento no esté vacía
        String fechaNacimientoText = fechaNacimientoField.getText().trim();
        if (fechaNacimientoText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "La fecha de nacimiento del perro no puede estar en blanco.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Intentar convertir la fecha de nacimiento a LocalDate
        LocalDate fechaNacimiento;
        try {
            fechaNacimiento = LocalDate.parse(fechaNacimientoText);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha de nacimiento incorrecto. Debe ser 'yyyy-MM-dd'.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar el peso
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

        // Crear el objeto Perro con los datos ingresados
        Perro perro = new Perro(nombre, raza, fechaNacimiento, peso, microchip, comentarios);

        // Insertar el perro en la clínica veterinaria
        ClinicaVeterinaria.getInstance().insertaAnimal(perro);

        // Limpiar el formulario después de agregar el perro
        limpiarFormulario();
        dispose(); // Cerrar la ventana del formulario
        parentFrame.setVisible(true); // Mostrar la ventana principal nuevamente
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
            ClinicaVeterinariaGUI gui = new ClinicaVeterinariaGUI();  //asi podemos volver al inicio desde el formulario
            gui.setVisible(true);

            // Aquí creamos una instancia del formulario de perro y lo hacemos visible
            PerroFormulario formulario = new PerroFormulario(gui);
            formulario.setVisible(true);
        });
    }

}



