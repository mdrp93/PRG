import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModificarComentarioForm extends JFrame {
    private JTextField nombreAnimalField;
    private JTextArea nuevoComentarioArea;
    private ClinicaVeterinariaGUI parentFrame;

    public ModificarComentarioForm(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Modificar Comentario de Animal");
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
        gbc.insets = (new Insets(5, 5, 5, 5));

        // Nombre del animal
        JLabel nombreAnimalLabel = new JLabel("Nombre del Animal ");
        nombreAnimalLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(nombreAnimalLabel, gbc);
        gbc.gridx++;
        nombreAnimalField = new JTextField(20);
        nombreAnimalField.setMargin(new Insets(5, 5, 5, 5));
        nombreAnimalField.setFont(new Font("Helvetica", Font.PLAIN, 20));

        panel.add(nombreAnimalField, gbc);

        // Nuevo comentario
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel nuevoComentarioLabel = new JLabel("Nuevo Comentario ");
        nuevoComentarioLabel.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(nuevoComentarioLabel, gbc);
        gbc.gridx++;
        nuevoComentarioArea = new JTextArea(5, 20);
        nuevoComentarioArea.setLineWrap(true);
        nuevoComentarioArea.setMargin(new Insets(10, 5, 10, 5));
        nuevoComentarioArea.setFont(new Font("Helvetica", Font.PLAIN, 20));
        panel.add(new JScrollPane(nuevoComentarioArea), gbc);

        // Botón para modificar el comentario
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton modificarButton = new JButton("Modificar Comentario");
        modificarButton.setFont(new Font("Helvetica", Font.PLAIN, 20));
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarComentario();
            }
        });
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(modificarButton, gbc);

        add(panel);
    }

    private void volverAtras() {
        parentFrame.setVisible(true);
        dispose();
    }

    private void modificarComentario() {
        String nombreAnimal = nombreAnimalField.getText().trim();
        String nuevoComentario = nuevoComentarioArea.getText().trim();

        if (nombreAnimal.isEmpty() || nuevoComentario.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, completa todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        ClinicaVeterinaria.getInstance().modificaComentarioAnimal(nombreAnimal, nuevoComentario);
        JOptionPane.showMessageDialog(this, "Comentario modificado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        limpiarFormulario();
        volverAtras();
    }

    private void limpiarFormulario() {
        nombreAnimalField.setText("");
        nuevoComentarioArea.setText("");
    }
}


