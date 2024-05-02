import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ListaAnimalesFrame extends JFrame {

    private final ClinicaVeterinaria clinica;
    private final JComboBox<String> animalComboBox;
    private final JTextArea detallesTextArea;
    private final JFrame parentFrame;

    public ListaAnimalesFrame(ClinicaVeterinaria clinica, JFrame parentFrame) {
        this.clinica = clinica;
        this.parentFrame = parentFrame;

        setTitle("Lista de Animales");
        setSize(700, 500); // Ajuste del tamaño más grande
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Cambiado a DO_NOTHING_ON_CLOSE
        setLocationRelativeTo(null);

        // Configurar el fondo rosa
        getContentPane().setBackground(Color.PINK);

        // Panel principal con BorderLayout y relleno
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(Color.PINK);
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Agregar relleno de 20 píxeles alrededor

        // Panel superior con FlowLayout
        JPanel panelSuperior = new JPanel(new FlowLayout());
        panelSuperior.setBackground(Color.PINK);

        JLabel labelSeleccionar = new JLabel("Seleccionar consulta ");
        labelSeleccionar.setFont(new Font("Helvetica", Font.BOLD, 20));
        labelSeleccionar.setForeground(Color.BLACK);
        panelSuperior.add(labelSeleccionar);

        // Obtener la lista de nombres de animales para el JComboBox
        List<Animal> animales = clinica.getListaAnimales();
        String[] nombresAnimales = animales.stream().map(Animal::getNombre).toArray(String[]::new);

        // Agregar "Elija un registro" como la primera opción en el JComboBox
        String[] opcionesComboBox = new String[nombresAnimales.length + 1];
        opcionesComboBox[0] = "Elija un registro";
        System.arraycopy(nombresAnimales, 0, opcionesComboBox, 1, nombresAnimales.length);

        animalComboBox = new JComboBox<>(opcionesComboBox);
        // Establecer tamaño para el JComboBox
        animalComboBox.setPreferredSize(new Dimension(300, 30)); // Tamaño más grande
        animalComboBox.setBackground(Color.white);
        animalComboBox.setOpaque(true);
        animalComboBox.setEditable(false);

        animalComboBox.setFont(new Font("Helvetica", Font.LAYOUT_LEFT_TO_RIGHT, 16));

        panelSuperior.add(animalComboBox);

        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        // JTextArea no editable para mostrar los detalles del animal seleccionado
        detallesTextArea = new JTextArea();
        detallesTextArea.setBackground(Color.WHITE);
        detallesTextArea.setEditable(false);
        detallesTextArea.setFocusable(false);
        detallesTextArea.setFont(new Font("Helvetica", Font.PLAIN, 18));
        detallesTextArea.setOpaque(true); // Hacer el JTextArea transparente
        detallesTextArea.setMargin(new Insets(20, 20, 20, 20)); // Ajuste de los márgenes internos

        // Panel para envolver el JComboBox y el JTextArea con relleno
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBackground(Color.WHITE); // Color blanco para cubrir el fondo rosa
        panelCentro.setBorder(null); // quitar bordes

        panelCentro.add(new JScrollPane(detallesTextArea), BorderLayout.CENTER);

        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        // Agregar el panel principal al contenido del frame
        getContentPane().add(panelPrincipal);

        // Establecer "Elija un registro" como la opción seleccionada por defecto en el JComboBox
        animalComboBox.setSelectedIndex(0);

        // Agregar un listener al JComboBox para actualizar los detalles del animal seleccionado
        animalComboBox.addActionListener(e -> mostrarDetallesAnimalSeleccionado());

        // Modificar el comportamiento por defecto de cierre para volver a la ventana principal
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                parentFrame.setVisible(true);
                dispose();
            }
        });
    }

    private void mostrarDetallesAnimalSeleccionado() {
        String nombreAnimalSeleccionado = (String) animalComboBox.getSelectedItem();
        // Si se selecciona "Elija un registro", limpiar el JTextArea
        if (nombreAnimalSeleccionado.equals("Elija un registro")) {
            detallesTextArea.setText("");
        } else {
            Animal animalSeleccionado = clinica.buscaAnimal(nombreAnimalSeleccionado);

            if (animalSeleccionado != null) {
                // Mostrar los detalles del animal seleccionado en el JTextArea
                    StringBuilder detalles = new StringBuilder();
                    detalles.append("NOMBRE: \t").append(animalSeleccionado.getNombre()).append("\n\n");
                    detalles.append("FECHA DE NACIMIENTO: ").append(animalSeleccionado.getFechaNacimiento()).append("\n\n");
                    detalles.append("PESO:  \t").append(animalSeleccionado.getPeso()).append(" kg\n\n");


                // Verificar el tipo de animal y agregar los detalles específicos
                if (animalSeleccionado instanceof Perro) {
                    Perro perro = (Perro) animalSeleccionado;
                    detalles.append("RAZA: \t").append(perro.getRaza()).append("\n\n");
                    detalles.append("MICROCHIP: \t").append(perro.getMicrochip()).append("\n\n");
                } else if (animalSeleccionado instanceof Gato) {
                    Gato gato = (Gato) animalSeleccionado;
                    detalles.append("RAZA: \t").append(gato.getRaza()).append("\n\n");
                    detalles.append("MICROCHIP: \t").append(gato.getMicrochip()).append("\n\n");
                } else if (animalSeleccionado instanceof Pajaro) {
                    Pajaro pajaro = (Pajaro) animalSeleccionado;
                    detalles.append("ESPECIE: \t").append(pajaro.getEspecie()).append("\n\n");
                    detalles.append("CANTOR: \t").append(pajaro.getCantor() ? "Sí" : "No").append("\n\n");
                } else if (animalSeleccionado instanceof Reptil) {
                    Reptil reptil = (Reptil) animalSeleccionado;
                    detalles.append("ESPECIE: \t").append(reptil.getEspecie()).append("\n\n");
                    detalles.append("VENENOSO: \t").append(reptil.getVenenoso() ? "Sí" : "No").append("\n\n");
                }

                detalles.append("\nCOMENTARIOS: \n").append(animalSeleccionado.getComentarios()).append("\n");

                detallesTextArea.setText(detalles.toString());
            } else {
                detallesTextArea.setText("No se encontraron detalles para el animal seleccionado.");
            }
        }
    }
}

