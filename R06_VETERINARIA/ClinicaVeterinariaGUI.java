import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class ClinicaVeterinariaGUI  extends JFrame {
    private ClinicaVeterinaria clinica;
    private Scanner scanner;
    private MainMenuFrame mainMenuFrame;


    public ClinicaVeterinariaGUI() {
        setTitle("Clínica Veterinaria");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Inicializar la clínica y el scanner
        clinica = new ClinicaVeterinaria();
        scanner = new Scanner(System.in);

        initComponents();

    }

    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout());


        // Panel para la imagen de fondo detrás del texto
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("inicio.jpg");
                Image image = imagenFondo.getImage();
                Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                g.drawImage(scaledIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Texto de bienvenida
        JLabel welcomeLabel = new JLabel("¡Bienvenido a nuestra CLÍNICA VETERINARIA!");
        setResizable(false);
        welcomeLabel.setFont(new Font("Helvetica", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setVerticalAlignment(SwingConstants.CENTER); // Alinea verticalmente al centro
        backgroundPanel.add(welcomeLabel, BorderLayout.CENTER);
            // Agregar un listener de ratón al JLabel para cambiar el color del texto cuando se pasa el ratón por encima
            welcomeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    welcomeLabel.setForeground(Color.CYAN); // Cambiar el color del texto al pasar el ratón por encima
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    welcomeLabel.setForeground(Color.BLACK); // Restaurar el color original del texto al salir el ratón
                }
            });

        // Panel para centrar el botón
        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(25, 5, 20, 5); // Margen alrededor del botón

        // Botón para entrar al menú
        JButton menuButton = new JButton("Entrar al Menú");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMainMenu();
            }
        });
        menuButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuButton.setBackground(Color.CYAN); // Cambiar el color de fondo al pasar el ratón por encima
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuButton.setBackground(Color.PINK);; // Restaurar el color de fondo al salir el ratón
            }
        });
        menuButton.setFont(new Font("Helvetica", Font.PLAIN, 30));
        menuButton.setBackground(Color.PINK); // Cambiar el color del botón a rosa
        menuButton.setFocusable(false);
        menuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Eliminar el borde
        menuButton.setHorizontalAlignment(SwingConstants.CENTER); // Alinea horizontalmente al centro


        centerPanel.add(menuButton, gbc);

        // Agregar el panel del texto e imagen debajo del texto de bienvenida
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);

        // Agregar el panel central al centro del contenedor principal
        mainPanel.add(centerPanel, BorderLayout.SOUTH); // Coloca el panel con el botón en la parte inferior

        add(mainPanel);
    }



    private void openMainMenu() {
        // Ocultar la ventana principal
        this.setVisible(false);


        // Mostrar el menú principal
        mainMenuFrame = new MainMenuFrame(this);
        mainMenuFrame.setVisible(true);
    }

// MAIN
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ClinicaVeterinariaGUI gui = new ClinicaVeterinariaGUI();
            gui.setVisible(true);
            gui.setLocationRelativeTo(null);
        });
    }

//ventana del menu
    public class MainMenuFrame extends JFrame{
        private ClinicaVeterinariaGUI parentFrame;

        public MainMenuFrame(ClinicaVeterinariaGUI parentFrame) {
            this.parentFrame = parentFrame;

            setTitle("Menú Principal");
            setSize(700, 500);
            setResizable(false);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addWindowListener(new WindowAdapter() {     //con esto hacemos que el boton de Dispose_on_close vuelva atrás a la ventan en la que fijamos el dispose.
                    @Override
                    public void windowClosing(WindowEvent e) {
                        volverAtras();
                    }
                });
            setLocationRelativeTo(parentFrame); // Centrar la ventana en relación con la ventana principal
            setVisible(true);


            initComponents();
        }


    private void initComponents() {
        // Panel principal con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Panel para la imagen de fondo detrás del texto y los botones
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("inicio.jpg");
                Image image = imagenFondo.getImage();
                Image scaledImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
                g.drawImage(scaledIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Texto de menú
        JLabel textoMenu = new JLabel("Elige una opción del menú: ");
        textoMenu.setFont(new Font("Helvetica", Font.BOLD, 30));
        textoMenu.setForeground(Color.BLACK);
        textoMenu.setHorizontalAlignment(SwingConstants.CENTER);
        textoMenu.setVerticalAlignment(SwingConstants.CENTER); // Alinea verticalmente al centro
        backgroundPanel.add(textoMenu, BorderLayout.NORTH);

        // Panel para los botones con GridBagLayout
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false); // Hacer el panel de botones transparente

        // Crear y configurar GridBagConstraints para los botones
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); //margenes

        // Crear botones para las opciones del menú
        String[] opciones = {"Nuevo registro", "Modificar comentario", "Lista de animales", "Volver atrás", "Salir"};
        for (String opcion : opciones) {
            JButton button = new JButton(opcion);
            button.setFont(new Font("Helvetica", Font.PLAIN, 20));
            button.setBackground(Color.pink);
            button.setFocusable(false);
            button.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); // Eliminar el borde

            button.setBorder(BorderFactory.createEmptyBorder()); // Eliminar el borde
            button.addActionListener(e -> handleMenuOption(opcion));

            // Ajustar el tamaño
            button.setPreferredSize(new Dimension(250, 30));

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

            // Agregar el botón al panel con GridBagConstraints
            buttonPanel.add(button, gbc);

            // Incrementar el índice de la fila para el siguiente botón
            gbc.gridy++;
        }

        // Agregar el panel de botones al panel de fondo
        backgroundPanel.add(buttonPanel, BorderLayout.CENTER);

        // Agregar el panel de fondo al panel principal
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);

        // Agregar el panel principal al frame
        add(mainPanel);
    }




    private void volverAtras() {
        parentFrame.setVisible(true); // Mostrar la ventana principal nuevamente
        dispose(); // Cerrar la ventana del formulario de perro
    }

    private void handleMenuOption(String option) {
        switch (option) {
            case "Nuevo registro":
                AnimalMenuFrame menuFrame = new AnimalMenuFrame(parentFrame);
                menuFrame.setVisible(true);
                dispose();
                break;

            case "Modificar comentario":
                openModificarComentarioForm(parentFrame);
                dispose();
                break;

            case "Lista de animales":
                ListaAnimalesFrame listaAnimalesFrame = new ListaAnimalesFrame(ClinicaVeterinaria.getInstance(), parentFrame);
                listaAnimalesFrame.setVisible(true);
                dispose();
                break;


            case "Volver atrás":
                // Mostrar la ventana del menú principal
                parentFrame.setVisible(true);
                // Cerrar la ventana del menú actual
                dispose();
                break;

            case "Salir":
                exitProgram();
                break;
        }
    }


    private void openModificarComentarioForm(ClinicaVeterinariaGUI parentFrame) {
        ModificarComentarioForm formFrame = new ModificarComentarioForm(parentFrame);
        formFrame.setVisible(true);
    }

    private void exitProgram() {
            parentFrame.setVisible(false); // Ocultar la ventana principal
            System.exit(0); // Salir de la aplicación
        }

    }


}



