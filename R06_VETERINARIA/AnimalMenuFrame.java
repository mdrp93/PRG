import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AnimalMenuFrame extends JFrame {
    private ClinicaVeterinariaGUI parentFrame;

    public AnimalMenuFrame(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Menú de Animales");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame); // Centrar la ventana en relación con la ventana principal
        setVisible(true);

        initComponents();
    }

    private void initComponents() {
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel para la imagen de fondo detrás de los botones
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("inicio.jpg"); // Ajusta la imagen de fondo
                Image image = imagenFondo.getImage();
                Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                g.drawImage(scaledIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Panel para los botones con GridLayout (4 filas y 1 columna)
        JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 filas, 1 columna, espacio horizontal y vertical de 10px

        // Crear botones para cada tipo de animal
        String[] animalOptions = {"Perro", "Gato", "Pájaro", "Reptil"};
        for (String option : animalOptions) {
            JButton button = new JButton(option);
            button.setFont(new Font("Helvetica", Font.PLAIN, 20));
            button.setBackground(Color.PINK); // Cambiar el color del botón a rosa
            button.addActionListener(e -> handleAnimalOption(option));

            buttonPanel.add(button); // Agregar botón al panel de botones
        }

        // Agregar el panel de botones al panel de fondo
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel de fondo al panel principal
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);

        // Agregar el panel principal al frame
        add(mainPanel);
    }

    private void handleAnimalOption(String option) {
        switch (option) {
            case "Perro":
                openPerroFormulario();
                break;
            case "Gato":
                openGatoFormulario();
                break;
            case "Pájaro":
                openPajaroFormulario();
                break;
            case "Reptil":
                openReptilFormulario();
                break;
        }
    }

    private void openPerroFormulario() {
        PerroFormulario perroFrame = new PerroFormulario(parentFrame);
        perroFrame.setVisible(true);
        dispose();
    }

    private void openGatoFormulario() {
        GatoFormulario gatoFrame = new GatoFormulario(parentFrame);
        gatoFrame.setVisible(true);
        dispose();
    }

    private void openPajaroFormulario() {
        PajaroFormulario pajaroFrame = new PajaroFormulario(parentFrame);
        pajaroFrame.setVisible(true);
        dispose();
    }

    private void openReptilFormulario() {
        ReptilFormulario reptilFrame = new ReptilFormulario(parentFrame);
        reptilFrame.setVisible(true);
        dispose();
    }
}
