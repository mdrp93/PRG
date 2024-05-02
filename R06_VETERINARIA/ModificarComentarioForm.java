import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ModificarComentarioForm extends JFrame {
    private ClinicaVeterinariaGUI parentFrame;
    private JTextField nombreAnimalField;
    private JTextArea nuevoComentarioArea;

    public ModificarComentarioForm(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Modificar Comentario de un Animal");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame); // Centrar la ventana en relación con la ventana principal

        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(2, 2));

        // Nombre del animal
        formPanel.add(new JLabel("Nombre del Animal:"));
        nombreAnimalField = new JTextField();
        formPanel.add(nombreAnimalField);

        // Nuevo comentario
        formPanel.add(new JLabel("Nuevo Comentario:"));
        nuevoComentarioArea = new JTextArea();
        formPanel.add(new JScrollPane(nuevoComentarioArea));

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Botón para modificar comentario
        JButton modificarButton = new JButton("Modificar Comentario");
        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                modificarComentario();
            }
        });
        mainPanel.add(modificarButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void modificarComentario() {
        // Obtener los valores del formulario
        String nombreAnimal = nombreAnimalField.getText();
        String nuevoComentario = nuevoComentarioArea.getText();

        // Lógica para modificar el comentario del animal
        // Aquí debes llamar a un método en ClinicaVeterinariaGUI o en otra clase que maneje la lógica de negocio.

        // Limpiar el formulario después de modificar el comentario
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        nombreAnimalField.setText("");
        nuevoComentarioArea.setText("");
    }
}
