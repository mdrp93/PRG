import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.dnd.DnDConstants;
import java.io.*;
import java.util.List;

public class LeerArchivoBinarioGUI extends JFrame {

    private JButton btnSeleccionarArchivo, btnLeer, btnVolver;
    private JPanel dropPanel;
    private JLabel dragDropLabel;
    private File archivoArrastrado;

    public LeerArchivoBinarioGUI() {
        setTitle("Leer archivo binario");
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


        panel.add(dropPanel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        btnSeleccionarArchivo = new JButton("Seleccionar archivo binario");
        btnSeleccionarArchivo.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnSeleccionarArchivo.setFocusPainted(false);
        btnLeer = new JButton("Leer");
        btnLeer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnVolver = new JButton("Volver");
        btnVolver.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        btnSeleccionarArchivo.addActionListener(e -> seleccionarArchivo());
        btnLeer.addActionListener(e -> leerArchivo());
        btnVolver.addActionListener(e -> {
            dispose(); // Cerrar la ventana actual
            new MainGUI(); // Abrir el menú principal
        });
        

        buttonPanel.add(btnSeleccionarArchivo);
        buttonPanel.add(btnLeer);
        buttonPanel.add(btnVolver);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        // Habilitar el panel para arrastrar y soltar archivos
        dropPanel.setTransferHandler(new FileDropHandler());

        // Hacer que el panel de arrastre acepte archivos arrastrados
        DropTarget dropTarget = new DropTarget(dropPanel, DnDConstants.ACTION_COPY, new DropTargetListener() {
            @Override
            public void dragEnter(DropTargetDragEvent dtde) {
                dtde.acceptDrag(DnDConstants.ACTION_COPY);
                dragDropLabel.setText("Suelte el archivo aquí");
            }

            @Override
            public void dragOver(DropTargetDragEvent dtde) {}

            @Override
            public void dropActionChanged(DropTargetDragEvent dtde) {}

            @Override
            public void dragExit(DropTargetEvent dte) {
                dragDropLabel = new JLabel("Seleccione o Arrastre el archivo");
            }

            @Override
            public void drop(DropTargetDropEvent dtde) {
                Transferable transferable = dtde.getTransferable();
                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    dtde.acceptDrop(DnDConstants.ACTION_COPY);
                    try {
                        List<File> fileList = (List<File>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        for (File file : fileList) {
                            archivoArrastrado = file;
                            // Realizar acciones necesarias con los archivos soltados
                            dragDropLabel.setText("Archivo soltado: " + file.getAbsolutePath());
                        }
                        dtde.dropComplete(true);
                    } catch (UnsupportedFlavorException | IOException e) {
                        e.printStackTrace();
                        dtde.dropComplete(false);
                    }
                } else {
                    dtde.rejectDrop();
                }
                dragDropLabel = new JLabel("Seleccione o Arrastre el archivo");
            }
        });

        dropPanel.setDropTarget(dropTarget);

        add(panel);
        setVisible(true);
    }

    private void seleccionarArchivo() {
        JFileChooser fileChooser = new JFileChooser();
        int seleccion = fileChooser.showOpenDialog(this);
        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File archivoBinario = fileChooser.getSelectedFile();
            leerArchivoBinario(archivoBinario);
        }
    }

    private void leerArchivo() {
        if (archivoArrastrado != null) {
            leerArchivoBinario(archivoArrastrado);
        } else {
            JOptionPane.showMessageDialog(this, "Primero arrastre un archivo o selecciónelo utilizando el botón 'Seleccionar archivo'.");
        }
    }

    private void leerArchivoBinario(File archivoBinario) {
        StringBuilder datos = new StringBuilder(); // Utilizamos un StringBuilder para almacenar los datos del archivo

        try (DataInputStream in = new DataInputStream(new FileInputStream(archivoBinario))) {
            while (in.available() > 0) {
                String nombre = in.readUTF();
                String dni = in.readUTF();
                int genero = in.readInt();

                datos.append(nombre).append("-").append(dni).append("-").append(genero).append("\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error al leer el archivo binario: " + e.getMessage());
        }

        // Llamar al método para abrir el editor de texto con los datos
        abrirEditorTexto(datos.toString());
    }

    private void abrirEditorTexto(String texto) {
        try {
            // Crear un archivo temporal para guardar el texto
            File tempFile = File.createTempFile("temp", ".txt");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write(texto);
            }

            // Abrir el archivo temporal con el editor de texto predeterminado
            Desktop.getDesktop().open(tempFile);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "No se pudo abrir el editor de texto: " + e.getMessage());
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
                    archivoArrastrado = file;
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
        SwingUtilities.invokeLater(LeerArchivoBinarioGUI::new);
    }
}

