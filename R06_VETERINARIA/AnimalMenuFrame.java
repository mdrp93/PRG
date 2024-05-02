import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class AnimalMenuFrame extends JFrame {
    private ClinicaVeterinariaGUI parentFrame;

    public AnimalMenuFrame(ClinicaVeterinariaGUI parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("Menú de Animales");
        setSize(700, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(parentFrame); // Centrar la ventana en relación con la ventana principal
        setVisible(true);

        initComponents();
    }

    private void initComponents() {
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel para la imagen de fondo detrás de los botones y el texto
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

        // Panel para el texto
        JPanel textPanel = new JPanel(new FlowLayout());
        JLabel textLabel = new JLabel("¿Qué tipo de animal quiere registrar?");
        textLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        textPanel.setOpaque(false);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setVerticalAlignment(SwingConstants.CENTER);
        textPanel.add(textLabel, BorderLayout.NORTH);

        // Panel contenedor para los botones con margen a los lados
        JPanel buttonMarginPanel = new JPanel(new BorderLayout());
        buttonMarginPanel.setOpaque(false);
        buttonMarginPanel.setBorder(BorderFactory.createEmptyBorder(70, 90, 60, 90)); // Margen de 50px a los lados

        // Panel para los botones con GridLayout (4 filas y 1 columna)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 4, 15, 50)); // 0 filas, 1 columna, espacio vertical de 10px
        buttonPanel.setOpaque(false);

        // Crear botones para cada tipo de animal
        String[] animalOptions = {"Perro", "Gato", "Pájaro", "Reptil"};
        for (String option : animalOptions) {
            JButton button = new JButton(option);
            button.setFont(new Font("Helvetica", Font.PLAIN , 20));
            button.setBackground(Color.PINK);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Eliminar el borde
            button.addActionListener(e -> handleAnimalOption(option));
            button.setPreferredSize(new Dimension(20, 20)); // Ajustar el tamaño de los botones


            // Agregar listener de ratón para cambiar el color de fondo al pasar el ratón por encima
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(Color.CYAN); // Cambiar el color de fondo al pasar el ratón por encima
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(Color.PINK);; // Restaurar el color de fondo al salir el ratón
                }
            });

            buttonPanel.add(button);
        }

        // Agregar el panel de botones al panel contenedor
        buttonMarginPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel de texto al panel de fondo
        backgroundPanel.add(textPanel, BorderLayout.NORTH);

        // Agregar el panel contenedor de botones al panel de fondo
        backgroundPanel.add(buttonMarginPanel, BorderLayout.CENTER);

        // Agregar el panel de fondo al panel principal
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);

        // Establecer el enfoque en el panel principal
        mainPanel.setFocusable(true);
        mainPanel.requestFocusInWindow();

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

