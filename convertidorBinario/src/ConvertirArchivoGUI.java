import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.dnd.*;
import java.awt.datatransfer.*;
import java.io.*;
import java.awt.Desktop;
import java.util.List;

public class ConvertirArchivoGUI extends JFrame {
    private JButton btnSeleccionarArchivo, btnConvertir, btnMostrarArchivo, btnVolver;
    private File archivoTextoSeleccionado;
    private File archivoBinarioGenerado;
    private JPanel dropPanel;
    private JLabel dragDropLabel;

    public ConvertirArchivoGUI() {
        setTitle("Convertir archivo de texto a binario");
        setSize(750, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
       setResizable(false);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);

        dropPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0));
                g2d.setColor(Color.LIGHT_GRAY);
                g2d.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
                g2d.dispose();
            }
        };
        dropPanel.setPreferredSize(new Dimension(150, 200)); // Ajustar el tamaño del panel de arrastre
        dropPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 10)); // Alinear el texto verticalmente
        dropPanel.setBackground(Color.BLACK);
        dragDropLabel = new JLabel("Seleccione o Arrastre el archivo");
        dragDropLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        dragDropLabel.setForeground(Color.LIGHT_GRAY);
        dropPanel.add(dragDropLabel);

        // Agregar el listener para el arrastre de archivos
        dropPanel.setTransferHandler(new FileDropHandler());

        panel.add(dropPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);

        // Botones
        btnSeleccionarArchivo = new JButton("Seleccionar archivo de texto");
        btnSeleccionarArchivo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnConvertir = new JButton("Convertir");
        btnConvertir.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnMostrarArchivo = new JButton("Mostrar archivo .bin en directorio");
        btnMostrarArchivo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnMostrarArchivo.setVisible(false); //hacerlo invisible
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        btnSeleccionarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int seleccion = fileChooser.showOpenDialog(ConvertirArchivoGUI.this);
                if (seleccion == JFileChooser.APPROVE_OPTION) {
                    archivoTextoSeleccionado = fileChooser.getSelectedFile();
                    String archivoBinario = JOptionPane.showInputDialog("Introduce el nombre del archivo binario de salida:");
                    convertirArchivo(archivoBinario);
                }
            }
        });

        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (archivoTextoSeleccionado != null) {
                    String archivoBinario = JOptionPane.showInputDialog("Introduce el nombre del archivo binario de salida:");
                    convertirArchivo(archivoBinario);
                } else {
                    JOptionPane.showMessageDialog(ConvertirArchivoGUI.this, "Primero debes seleccionar un archivo de texto.");
                }
            }
        });
        

        btnMostrarArchivo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (archivoBinarioGenerado != null && archivoBinarioGenerado.exists()) {
                    try {
                        String parentDirectory = archivoBinarioGenerado.getParent();
                        if (parentDirectory != null) {
                            Desktop.getDesktop().open(new File(parentDirectory));
                        } else {
                            JOptionPane.showMessageDialog(ConvertirArchivoGUI.this, "No se pudo determinar el directorio del archivo.");
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(ConvertirArchivoGUI.this, "No se pudo abrir el directorio del archivo: " + ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(ConvertirArchivoGUI.this, "El archivo binario no ha sido generado aún.");
                }
            }
        });
        

        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainGUI();
            }
        });

        // Agregar componentes al panel principal
        buttonPanel.add(btnSeleccionarArchivo);
        buttonPanel.add(btnConvertir);
        buttonPanel.add(btnMostrarArchivo);
        buttonPanel.add(btnVolver);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    private void convertirArchivo(String nombreArchivoBinario) {
        if (archivoTextoSeleccionado != null) {
            // Verificar si el nombre del archivo binario tiene la extensión .bin
            String archivoBinario;
            if (!nombreArchivoBinario.toLowerCase().endsWith(".bin")) {
                archivoBinario = nombreArchivoBinario + ".bin"; // Agregar la extensión si no está presente
            } else {
                archivoBinario = nombreArchivoBinario; // El nombre del archivo ya tiene la extensión .bin
            }
    
            File archivoGenerado = new File(archivoBinario);
    
            if (archivoGenerado.exists()) {
                // El archivo binario ya existe
                int opcion = JOptionPane.showConfirmDialog(this, "El archivo '" + archivoGenerado.getName() + "' ya existe. ¿Desea sobrescribirlo?");
                if (opcion != JOptionPane.YES_OPTION) {
                    return; // Salir del método si el usuario elige no sobrescribir el archivo
                }
            }
    
            // Crear un nuevo JFileChooser para que el usuario seleccione la ubicación del archivo
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Guardar archivo binario");
            
            // Establecer el directorio inicial como el directorio padre del archivo seleccionado
            fileChooser.setCurrentDirectory(archivoTextoSeleccionado.getParentFile());
            
            fileChooser.setSelectedFile(archivoGenerado); // Establecer el nombre predeterminado del archivo
            int seleccion = fileChooser.showSaveDialog(this);
    
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                // Obtener el archivo seleccionado por el usuario
                File archivoDestino = fileChooser.getSelectedFile();
    
                try (BufferedReader br = new BufferedReader(new FileReader(archivoTextoSeleccionado));
                     DataOutputStream out = new DataOutputStream(new FileOutputStream(archivoDestino))) {
    
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        String[] partes = linea.split("-");
                        if (partes.length != 3) {
                            JOptionPane.showMessageDialog(this, "Error: el formato de entrada no es válido. Cada línea debe tener tres elementos separados por guiones: nombre - DNI - género");
                            return;
                        }
                        out.writeUTF(partes[0].trim());
                        out.writeUTF(partes[1].trim());
                        out.writeInt(Integer.parseInt(partes[2].trim()));
                    }
    
                    archivoBinarioGenerado = archivoDestino;
                    JOptionPane.showMessageDialog(this, "Archivo binario generado correctamente.");
    
                    // Mostrar el botón después de la conversión exitosa
                    btnMostrarArchivo.setVisible(true);
    
                } catch (IOException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Error al procesar el archivo: " + ex.getMessage());
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Primero debes seleccionar un archivo de texto.");
        }
    }
    
    
    

    private class FileDropHandler extends TransferHandler {
        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.javaFileListFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) {
                return false;
            }

            Transferable transferable = support.getTransferable();
            try {
                List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                for (File file : fileList) {
                    archivoTextoSeleccionado = file;
                    // Realizar acciones necesarias con los archivos soltados
                    dragDropLabel.setText("Archivo soltado: " + file.getAbsolutePath());
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ConvertirArchivoGUI::new);
    }
}





    /*el programa leerá cualquier archivo de texto que se le pase como argumento de entrada, ya sea un archivo CSV o un archivo de texto plano con un formato diferente.*/
    //El programa asigna automaticamente la extension .bin al nombre del nuevo archivo, si el usuario no la proporciona"