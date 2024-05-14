import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuscarTextoGUI extends JFrame {

    private final JTextField archivoField;
    private final JCheckBox incluirDelimitadoresCheckBox;
    private final JCheckBox esIdentificadorDelimitadorCheckBox;
    private final JTextField textoABuscarTextField;
    private final JTextArea resultadoTextArea;
    private BuscarTextoLog buscador;

    public BuscarTextoGUI() {
        setTitle("Buscador de Texto");
        setSize(1000, 1000);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con una imagen de fondo
        JPanel panelPrincipal = new MyJPanel();
        panelPrincipal.setLayout(new GridBagLayout());

        // Establecer el tamaño del panel principal
        panelPrincipal.setPreferredSize(new Dimension(500,600));

        // Panel para el archivo
        JPanel panelArchivo = new JPanel();
        panelArchivo.setOpaque(false);
        panelArchivo.setLayout(new GridBagLayout());

        // Label y campo de archivo
        JLabel labelArchivo = new JLabel("Ruta del archivo:           ");
        Font font = new Font("Arial", Font.BOLD, 18);
        labelArchivo.setForeground(Color.WHITE.brighter());
        labelArchivo.setFont(font);

        archivoField = new JTextField();
        archivoField.setBackground(Color.LIGHT_GRAY);
        archivoField.setBorder(null);
        archivoField.setPreferredSize(new Dimension(250, 30));
        Font rutaArchivo = new Font("Arial", Font.PLAIN, 16);
        archivoField.setForeground(Color.black);
        archivoField.setFont(rutaArchivo);

        //boton para seleccionar el archivo entre los directorios
        JButton seleccionarButton = new JButton("Seleccionar Archivo") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Configurar el aspecto del botón cuando no está presionado
                if (!getModel().isPressed()) {
                    g2.setColor(Color.white);
                    g2.fillRect(0, 0, 0, 0); // Rellenar el fondo del botón, si lo ponemos a 0 -- el fondo es trasparente
                    g2.setColor(Color.LIGHT_GRAY); // Color del borde
                    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Dibujar el borde
                } else {
                    // Configurar el aspecto del botón cuando está presionado
                    g2.setColor(Color.WHITE); // Color del texto al estar presionado
                    g2.fillRect(0, 0, getWidth(), getHeight()); // Rellenar el fondo del botón
                    g2.setColor(Color.DARK_GRAY); // Color del borde al estar presionado
                    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Dibujar el borde
                }

                // Ajustar la fuente y pintar el texto centrado
                Font font = new Font("Arial", Font.BOLD, 14);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };

        // Configuración adicional del botón
        seleccionarButton.setFocusable(true);
        seleccionarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        seleccionarButton.setPreferredSize(new Dimension(200, 40));
        seleccionarButton.setOpaque(false); // Hacer el fondo del botón transparente

        // Añadir componentes al panelArchivo con GridBagConstraints
        GridBagConstraints gbcArchivoLabel = new GridBagConstraints();
        gbcArchivoLabel.gridx = 0;
        gbcArchivoLabel.gridy = 0;
        gbcArchivoLabel.anchor = GridBagConstraints.WEST;
        gbcArchivoLabel.insets = new Insets(20, 0, 10, 10);
        panelArchivo.add(labelArchivo, gbcArchivoLabel);

        GridBagConstraints gbcArchivoField = new GridBagConstraints();
        gbcArchivoField.gridx = 1;
        gbcArchivoField.gridy = 0;
        gbcArchivoField.weightx = 1.0;
        gbcArchivoField.fill = GridBagConstraints.HORIZONTAL;
        gbcArchivoField.insets = new Insets(10, 20, 10, 20);
        panelArchivo.add(archivoField, gbcArchivoField);

        GridBagConstraints gbcSeleccionarButton = new GridBagConstraints();
        gbcSeleccionarButton.gridx = 0;
        gbcSeleccionarButton.gridy = 1;
        gbcSeleccionarButton.gridwidth = 2;
        gbcSeleccionarButton.insets = new Insets(10, 20, 0, 20);
        panelArchivo.add(seleccionarButton, gbcSeleccionarButton);

        // Panel para el texto
        JPanel panelTexto = new JPanel();
        panelTexto.setLayout(new GridBagLayout());
        panelTexto.setOpaque(false);

        // Label y campo de texto
        JLabel labelTexto = new JLabel("Texto a buscar:");
        labelTexto.setForeground(Color.WHITE.brighter());
        labelTexto.setFont(font);

        textoABuscarTextField = new JTextField();
        textoABuscarTextField.setBackground(Color.LIGHT_GRAY);
        textoABuscarTextField.setBorder(null);
        textoABuscarTextField.setPreferredSize(new Dimension(250, 30));
        Font textoBusc = new Font("Arial", Font.PLAIN, 16);
        textoABuscarTextField.setForeground(Color.black);
        textoABuscarTextField.setFont(textoBusc);

        // Añadir label y campo de texto al panelTexto con GridBagConstraints
        GridBagConstraints gbcLabelTexto = new GridBagConstraints();
        gbcLabelTexto.gridx = 0;
        gbcLabelTexto.gridy = 0;
        gbcLabelTexto.anchor = GridBagConstraints.WEST;
        gbcLabelTexto.insets = new Insets(10, 20, 10, 10); // Ajuste de los márgenes izquierdo y derecho
        panelTexto.add(labelTexto, gbcLabelTexto);

        GridBagConstraints gbcTextoABuscar = new GridBagConstraints();
        gbcTextoABuscar.gridx = 1;
        gbcTextoABuscar.gridy = 0;
        gbcTextoABuscar.weightx = 1.0;
        gbcTextoABuscar.fill = GridBagConstraints.HORIZONTAL;
        gbcTextoABuscar.insets = new Insets(10, 0, 10, 20); // Ajuste de los márgenes izquierdo y derecho
        panelTexto.add(textoABuscarTextField, gbcTextoABuscar);

        // Checkboxes
        incluirDelimitadoresCheckBox = new JCheckBox("Incluir delimitadores");
        incluirDelimitadoresCheckBox.setOpaque(false);
        Font font3 = new Font("Arial", Font.BOLD, 18);
        incluirDelimitadoresCheckBox.setForeground(Color.WHITE.brighter());
        incluirDelimitadoresCheckBox.setFont(font3);

        esIdentificadorDelimitadorCheckBox = new JCheckBox("Es ID de delimitador");
        esIdentificadorDelimitadorCheckBox.setOpaque(false);
        Font font4 = new Font("Arial", Font.BOLD, 18);
        esIdentificadorDelimitadorCheckBox.setForeground(Color.WHITE.brighter());
        esIdentificadorDelimitadorCheckBox.setFont(font4);


        // Checkboxes en el mismo grid
        GridBagConstraints gbcCheckbox1 = new GridBagConstraints();
        gbcCheckbox1.gridx = 0;
        gbcCheckbox1.gridy = 1;
        gbcCheckbox1.anchor = GridBagConstraints.WEST;
        gbcCheckbox1.insets = new Insets(10, 40, 100, 10); // Ajuste de los márgenes izquierdo y derecho
        panelTexto.add(incluirDelimitadoresCheckBox, gbcCheckbox1);

        GridBagConstraints gbcCheckbox2 = new GridBagConstraints();
        gbcCheckbox2.gridx = 1;
        gbcCheckbox2.gridy = 1;
        gbcCheckbox2.anchor = GridBagConstraints.WEST;
        gbcCheckbox2.insets = new Insets(10, 20, 100, 10); // Ajuste de los márgenes izquierdo y derecho
        panelTexto.add(esIdentificadorDelimitadorCheckBox, gbcCheckbox2);


        resultadoTextArea = new JTextArea();
        resultadoTextArea.setBackground(Color.LIGHT_GRAY);
        resultadoTextArea.setForeground(Color.BLACK); // Establecer el color del texto a negro
        resultadoTextArea.setOpaque(true);
        resultadoTextArea.setEditable(false);
        resultadoTextArea.setPreferredSize(new Dimension(400, 400)); // Ajustar el tamaño del área de resultados
// Crear un borde con un espacio interno
        Border border = BorderFactory.createEmptyBorder(100, 10, 100, 10); // Espacio de 10 píxeles en todos los lados
        resultadoTextArea.setBorder(border);  // Establecer el borde en el JTextArea
        Font resultado = new Font("Arial", Font.PLAIN, 16);
        resultadoTextArea.setFont(resultado);

// Crear un JLabel para la leyenda
        JLabel leyendaLabel = new JLabel("Resultado de la búsqueda");
        leyendaLabel.setForeground(Color.BLACK); // Color del texto
        leyendaLabel.setFont(new Font("Arial", Font.ITALIC, 16)); // Fuente y tamaño del texto

// Crear un panel para contener el JTextArea y la leyenda
        JPanel panelResultado = new JPanel(new BorderLayout());
        panelResultado.setBackground(Color.LIGHT_GRAY); // Color de fondo
        panelResultado.add(leyendaLabel, BorderLayout.NORTH); // Agregar la leyenda encima del JTextArea
        panelResultado.add(resultadoTextArea, BorderLayout.CENTER); // Agregar el JTextArea al centro del panelResultado

// JScrollPane scrollPane para el panelResultado
        JScrollPane scrollPane = new JScrollPane(panelResultado); // Usar el panelResultado en lugar del resultadoTextArea
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

// GridBagConstraints para el JScrollPane scrollPane
        GridBagConstraints gbcScrollPane = new GridBagConstraints();
        gbcScrollPane.gridx = 0;
        gbcScrollPane.gridy = 2;
        gbcScrollPane.gridwidth = 2;
        gbcScrollPane.weighty = 1.0;
        gbcScrollPane.fill = GridBagConstraints.BOTH;
        gbcScrollPane.insets = new Insets(5, 20, 20, 20); // Ajuste de los márgenes izquierdo y derecho y margen inferior
        panelTexto.add(scrollPane, gbcScrollPane); // Agregar el scrollPane al panelTexto


        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.setLayout(new GridBagLayout());
        panelBoton.setOpaque(false);

        //boton de cargar texto
        JButton cargarButton = new JButton("Buscar y extraer texto") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                // Configurar el aspecto del botón cuando no está presionado
                if (!getModel().isPressed()) {
                    g2.setColor(Color.white);
                    g2.fillRect(0, 0, 0, 0); // Rellenar el fondo del botón, si lo ponemos a 0 -- el fondo es trasparente
                    g2.setColor(Color.LIGHT_GRAY); // Color del borde
                    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Dibujar el borde
                } else {
                    // Configurar el aspecto del botón cuando está presionado
                    g2.setColor(Color.WHITE); // Color del texto al estar presionado
                    g2.fillRect(0, 0, getWidth(), getHeight()); // Rellenar el fondo del botón
                    g2.setColor(Color.DARK_GRAY); // Color del borde al estar presionado
                    g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1); // Dibujar el borde
                }

                // Ajustar la fuente y pintar el texto centrado
                Font font = new Font("Arial", Font.BOLD, 14);
                g2.setFont(font);
                FontMetrics metrics = g2.getFontMetrics();
                int x = (getWidth() - metrics.stringWidth(getText())) / 2;
                int y = ((getHeight() - metrics.getHeight()) / 2) + metrics.getAscent();
                g2.drawString(getText(), x, y);

                g2.dispose();
            }
        };

        // Configuración adicional del botón extraer
        cargarButton.setFocusable(true);
        cargarButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cargarButton.setPreferredSize(new Dimension(200, 40));
        cargarButton.setOpaque(false); // Hacer el fondo del botón transparente

        // Añadir botón al panelBoton con GridBagConstraints
        GridBagConstraints gbcCargarButton = new GridBagConstraints();
        gbcCargarButton.gridx = 0;
        gbcCargarButton.gridy = 0;
        gbcCargarButton.weightx = 1.0; // Ajustar el peso para que ocupe todo el ancho disponible
        gbcCargarButton.fill = GridBagConstraints.HORIZONTAL; // Para que el botón se expanda horizontalmente
        gbcCargarButton.anchor = GridBagConstraints.CENTER; // Para centrar el botón en el panel
        gbcCargarButton.insets = new Insets(10, 20, 20, 20); // Ajuste de los márgenes izquierdo, derecho e inferior
        panelBoton.add(cargarButton, gbcCargarButton);


        // Añadir los paneles al panelPrincipal con GridBagConstraints
        GridBagConstraints gbcArchivo = new GridBagConstraints();
        gbcArchivo.gridx = 0;
        gbcArchivo.gridy = 0;
        gbcArchivo.anchor = GridBagConstraints.NORTHWEST;
        gbcArchivo.weighty = 1.0;
        gbcArchivo.insets = new Insets(80, 20, 10, 20); // Ajuste de los márgenes superior, izquierdo, derecho e inferior
        panelPrincipal.add(panelArchivo, gbcArchivo);


        GridBagConstraints gbcTexto = new GridBagConstraints();
        gbcTexto.gridx = 0;
        gbcTexto.gridy = 1;
        gbcTexto.anchor = GridBagConstraints.NORTHWEST;
        gbcTexto.weighty = 1.0;
        panelPrincipal.add(panelTexto, gbcTexto);

        GridBagConstraints gbcBoton = new GridBagConstraints();
        gbcBoton.gridx = 0;
        gbcBoton.gridy = 2;
        gbcBoton.anchor = GridBagConstraints.NORTH;
        gbcBoton.weighty = 0.0;

        panelPrincipal.add(panelBoton, gbcBoton);

        add(panelPrincipal);


        seleccionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seleccionarArchivo();
            }
        });

        cargarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuscarConoSin();
            }
        });
    }

//método que selecciona archivo
    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int selection = fileChooser.showOpenDialog(this);

        if (selection == JFileChooser.APPROVE_OPTION) {
            archivoField.setText(fileChooser.getSelectedFile().getAbsolutePath());
        }
    }

    private void BuscarConoSin() {
        String archivo = archivoField.getText();
        String textoABuscar = textoABuscarTextField.getText();
        boolean incluirDelimitadores = incluirDelimitadoresCheckBox.isSelected();
        boolean esIdentificadorDelimitador = esIdentificadorDelimitadorCheckBox.isSelected();

        if (archivo.isEmpty() || textoABuscar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los campos requeridos.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (buscador == null) {
            buscador = new BuscarTextoLog();
        }

        // Llamar al método de búsqueda y obtener el resultado
        String resultado = buscador.BuscarTextoLogical(archivo, textoABuscar, incluirDelimitadores, esIdentificadorDelimitador);

        // Establecer el resultado en el JTextArea
        resultadoTextArea.setText(resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuscarTextoGUI().setVisible(true);
            }
        });
    }

    private static class MyJPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon imageIcon = new ImageIcon("resources/fondoLibros.jpg");
            Image image = imageIcon.getImage();
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
        }
    }
}






