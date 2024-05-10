import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GestionVideojuegosGUI extends JFrame {
    private Coleccion coleccion;
    private JTextArea textArea;

    public GestionVideojuegosGUI() {
        super("Gestión de Colección de Videojuegos");
        coleccion = new Coleccion();

        // Configuración del JFrame
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Crear componentes
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar Videojuego");
        JButton btnListar = new JButton("Actualizar Lista Videojuegos");
        JButton btnEditar = new JButton("Editar Videojuego");
        JButton btnEliminar = new JButton("Eliminar Videojuego");
        textArea = new JTextArea(20, 40);
        textArea.setMargin(new Insets(10, 30, 10, 10)); // Establecer márgenes
        textArea.setFocusable(false);
        textArea.setFont(new Font("Monospaced", Font.BOLD, 17));
        textArea.setBorder(null);

        JScrollPane scrollPane = new JScrollPane(textArea);
        panelBotones.setBackground(Color.black);
        Dimension tamanoBoton = new Dimension(150, 50);
        btnAgregar.setPreferredSize(tamanoBoton);
        btnListar.setPreferredSize(tamanoBoton);
        btnEditar.setPreferredSize(tamanoBoton);
        btnEliminar.setPreferredSize(tamanoBoton);

        // Agregar componentes al panel de botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnListar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // Agregar componentes al JFrame
        add(panelBotones, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Acción del botón Agregar
        btnAgregar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                agregarVideojuego();
            }
        });

        // Acción del botón Listar
        btnListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarVideojuegos();
            }
        });

        // Acción del botón Editar
        btnEditar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarVideojuego();
            }
        });

        // Acción del botón Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarVideojuego();
            }
        });
    }


    private void agregarVideojuego() {
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese el nombre del videojuego:");
            if (nombre == null) {
                // Si el usuario cancela la entrada, simplemente no hacemos nada
                return;
            }

            if (nombre.trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre del videojuego es obligatorio.");
            }

            String annoStr = JOptionPane.showInputDialog("Ingrese el año de lanzamiento (deje en blanco si no lo sabe):");
            int anno = 0;
            if (!annoStr.isEmpty()) {
                if (!annoStr.matches("\\d+")) {
                    throw new NumberFormatException("El año debe ser un número entero.");
                }
                anno = Integer.parseInt(annoStr);
                if (anno < 1970 || anno > 2100) {
                    throw new IllegalArgumentException("El año debe estar comprendido entre 1970 y 2100.");
                }
            }


            Plataforma plataforma = (Plataforma) JOptionPane.showInputDialog(null, "Seleccione la plataforma:",
                    "Plataforma", JOptionPane.QUESTION_MESSAGE, null, Plataforma.values(), Plataforma.values()[0]);
            if (plataforma == null) {
                throw new IllegalArgumentException("Debe seleccionar una plataforma.");
            }

            TipoJuego tipoJuego = (TipoJuego) JOptionPane.showInputDialog(null, "Seleccione el tipo de juego:",
                    "Tipo de Juego", JOptionPane.QUESTION_MESSAGE, null, TipoJuego.values(), TipoJuego.values()[0]);
            if (tipoJuego == null) {
                throw new IllegalArgumentException("Debe seleccionar un tipo de juego.");
            }

            String valoracionStr = JOptionPane.showInputDialog("Ingrese la valoración del juego (deje en blanco si no lo sabe):");
            int valoracion = 0;
            if (!valoracionStr.isEmpty()) {
                if (!valoracionStr.matches("\\d+")) {
                    throw new NumberFormatException("La valoración debe ser un número entero.");
                }
                valoracion = Integer.parseInt(valoracionStr);
            }

            Videojuego juego = new Videojuego(nombre, anno, plataforma, tipoJuego, valoracion);
            coleccion.insertaVideojuego(juego);
            JOptionPane.showMessageDialog(this, "Videojuego agregado correctamente.");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    // Método para listar los videojuegos
    private void listarVideojuegos() {
        if (coleccion.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay videojuegos en el registro.", "Registro Vacío", JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format("%-25s %-8s %-15s %-15s %-6s%n", "Videojuego", "Año", "Plataforma", "Tipo de juego", "Val."));
            sb.append("-------------------------------------------------------------------------------------------\n");
            for (Videojuego v : coleccion.getLista()) {
                sb.append(String.format("%-25s %-8d %-15s %-15s %-6d%n", v.getNombre(), v.getAnno(), v.getPlataforma(), v.getTipoJuego(), v.getValoracion()));
            }

            textArea.setText(sb.toString());
        }
    }


    // Método para editar un videojuego
    private void editarVideojuego() {
        if (coleccion.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay videojuegos en el registro para editar.", "Registro Vacío", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] nombres = coleccion.getNombresVideojuegos();
            String juegoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un videojuego:",
                    "Editar Videojuego", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);

            if (juegoSeleccionado != null) {
                // Obtener el juego seleccionado
                Videojuego juego = coleccion.getVideojuegoPorNombre(juegoSeleccionado);
                // Mostrar cuadro de diálogo para editar los detalles del juego
                editarDetallesJuego(juego);
            }
        }
    }


    // Método para editar los detalles de un juego
    private void editarDetallesJuego(Videojuego juego) {

        // Mostrar un cuadro de diálogo con los detalles del juego y permitir la edición
        JTextField txtNombre = new JTextField(juego.getNombre());
        JTextField txtAnno = new JTextField(String.valueOf(juego.getAnno()));
        JComboBox<Plataforma> cmbPlataforma = new JComboBox<>(Plataforma.values());
        cmbPlataforma.setSelectedItem(juego.getPlataforma());
        JComboBox<TipoJuego> cmbTipoJuego = new JComboBox<>(TipoJuego.values());
        cmbTipoJuego.setSelectedItem(juego.getTipoJuego());
        JTextField txtValoracion = new JTextField(String.valueOf(juego.getValoracion()));

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Año:"));
        panel.add(txtAnno);
        panel.add(new JLabel("Plataforma:"));
        panel.add(cmbPlataforma);
        panel.add(new JLabel("Tipo de juego:"));
        panel.add(cmbTipoJuego);
        panel.add(new JLabel("Valoración:"));
        panel.add(txtValoracion);

        int resultado = JOptionPane.showConfirmDialog(null, panel, "Editar Detalles",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (resultado == JOptionPane.OK_OPTION) {
            // Guardar los detalles editados en el juego
            try {
                String nombre = txtNombre.getText();
                int anno = Integer.parseInt(txtAnno.getText());
                Plataforma plataforma = (Plataforma) cmbPlataforma.getSelectedItem();
                TipoJuego tipoJuego = (TipoJuego) cmbTipoJuego.getSelectedItem();
                int valoracion = Integer.parseInt(txtValoracion.getText());

                juego.setNombre(nombre);
                juego.setAnno(anno);
                juego.setPlataforma(plataforma);
                juego.setTipoJuego(tipoJuego);
                juego.setValoracion(valoracion);

                // Actualizar el listado de videojuegos
                listarVideojuegos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error: Los valores de Año y Valoración deben ser números enteros.",
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Método para eliminar un videojuego
    private void eliminarVideojuego() {
        if (coleccion.getLista().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay videojuegos en el registro para eliminar.", "Registro Vacío", JOptionPane.INFORMATION_MESSAGE);
        } else {
            String[] nombres = coleccion.getNombresVideojuegos();
            String juegoSeleccionado = (String) JOptionPane.showInputDialog(null, "Seleccione un videojuego:",
                    "Eliminar Videojuego", JOptionPane.QUESTION_MESSAGE, null, nombres, nombres[0]);

            if (juegoSeleccionado != null) {
                int confirmacion = JOptionPane.showConfirmDialog(null,
                        "¿Estás seguro de que deseas eliminar el videojuego seleccionado?", "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION);
                if (confirmacion == JOptionPane.YES_OPTION) {
                    // Obtener el índice del juego seleccionado
                    int indice = coleccion.getIndiceVideojuegoPorNombre(juegoSeleccionado);
                    // Eliminar el videojuego de la colección
                    coleccion.eliminaVideojuego(indice);
                    // Limpiar el TextArea
                    textArea.setText("");
                    JOptionPane.showMessageDialog(null, "Videojuego eliminado correctamente.");
                    // Actualizar el listado de videojuegos
                    listarVideojuegos();

                }
            }
        }
    }


    public static void main (String[]args){
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    GestionVideojuegosGUI gui = new GestionVideojuegosGUI();
                    gui.setVisible(true);
                }
            });
        }
    }

