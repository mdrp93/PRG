import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JButton btnConvertir, btnLeer;

    public MainGUI() {
        setTitle("Conversor de archivos");
        setSize(750, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Crear un panel con BoxLayout y orientación vertical
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        // Crear un JLabel con el título
        JLabel titleLabel = new JLabel("html");
        titleLabel.setText("<html><div style='text-align: center; width: 200px;'>Bienvenido al programa de tratamiento de archivos binarios</div></html>");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 14));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Centrar el título horizontalmente
        titleLabel.setMaximumSize(new Dimension(300, Integer.MAX_VALUE)); // Establecer un ancho máximo para el JLabel (ancho máximo de 300 píxeles)
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Centrar el texto horizontalmente
        titleLabel.setVerticalAlignment(SwingConstants.CENTER); // Centrar el texto verticalmente
        titleLabel.setHorizontalTextPosition(SwingConstants.CENTER); // Centrar el texto horizontalmente
        titleLabel.setVerticalTextPosition(SwingConstants.CENTER); // Centrar el texto verticalmente
        titleLabel.setOpaque(true); // Permitir que el JLabel tenga un fondo
        titleLabel.setBackground(Color.BLACK); // Establecer el color de fondo del JLabel
        titleLabel.setForeground(Color.WHITE); // Establecer el color del texto del JLabel
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10)); // Establecer un borde alrededor del JLabel
        panel.add(titleLabel);

        // Crear un panel contenedor para los botones
        JPanel buttonContainer = new JPanel();
        buttonContainer.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Alinear los botones horizontalmente con un espacio de 10 píxeles entre ellos
        buttonContainer.setBackground(Color.BLACK); // Establecer el color de fondo del contenedor
        
        btnConvertir = new JButton("Convertir archivo a .bin");
        btnConvertir.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        btnLeer = new JButton("Leer archivo binario");
        btnLeer.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        btnConvertir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ConvertirArchivoGUI();
            }
        });

        btnLeer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LeerArchivoBinarioGUI();
            }
        });

        buttonContainer.add(btnConvertir); // Agregar el botón de convertir al contenedor
        buttonContainer.add(btnLeer); // Agregar el botón de leer al contenedor

        panel.add(buttonContainer); // Agregar el contenedor de botones al panel principal

        // Agregar el panel al centro de la ventana
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainGUI();
            }
        });
    }
}

