import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TresEnRayaGUI {
    private int[] tablero;
    private final int VACIO = 0;
    private final int JUGADOR1 = 1;
    private final int JUGADOR2 = 2;
    private JButton[] buttons;
    private JFrame frame;
    private JPanel startPanel;
    private JPanel gamePanel;
    private int turnoActual;

    public TresEnRayaGUI() {
        tablero = new int[9];
        buttons = new JButton[9];
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Tres en Raya");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());

        JPanel welcomePanel = new JPanel(new GridBagLayout());
        welcomePanel.setPreferredSize(new Dimension(500, 500));// Establecer el tamaño preferido del panel
        welcomePanel.setBackground(Color.black);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.insets = new Insets(10, 5, 15, 5);

        //Label del mensaje de bienvenido
        JLabel welcomeLabel = new JLabel("<html><center>Bienvenido al<br> Tres en Raya<br><br></center></html>", SwingConstants.CENTER);
        Font font1 = new Font("Monospaced", Font.BOLD, 30);
        int red = 255;
        int green = 0;
        int blue = 0;
        // Crea un color con los componentes RGB
        Color textColor = new Color(0, 225, 255, 100);
        welcomeLabel.setForeground(textColor);

        welcomeLabel.setFont(font1);


        welcomePanel.add(welcomeLabel,gbc);



        //boton inicio de jugadorvsjugador
        JButton jugadorVsJugadorButton = new JButton("Jugador vs Jugador");
        jugadorVsJugadorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jugadorVsJugadorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jugadorVsJugadorButton.setPreferredSize(new Dimension(400, 50));
        Font font2 = new Font("Monospaced", Font.BOLD, 16);
        jugadorVsJugadorButton.setFont(font2);
        jugadorVsJugadorButton.setForeground(Color.BLACK);
        jugadorVsJugadorButton.setFocusPainted(false);
        jugadorVsJugadorButton.setBorderPainted(false);
        jugadorVsJugadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoContraJugador();
            }
        });

        welcomePanel.add(jugadorVsJugadorButton, gbc);


        //boton inicio de jugadorvsOrdenador
        JButton jugadorVsOrdenadorButton = new JButton("Jugador vs Ordenador");
        jugadorVsOrdenadorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        jugadorVsOrdenadorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        jugadorVsOrdenadorButton.setPreferredSize(new Dimension(400, 50));
        jugadorVsOrdenadorButton.setFont(font2);
        jugadorVsOrdenadorButton.setForeground(Color.BLACK);
        jugadorVsOrdenadorButton.setFocusPainted(false);
        jugadorVsOrdenadorButton.setBorderPainted(false);
        jugadorVsOrdenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoContraOrdenador();
            }
        });
        welcomePanel.add(jugadorVsOrdenadorButton, gbc);

        //boton inicio de ordenadorvsordenador
        JButton ordenadorVsOrdenadorButton = new JButton("Ordenador vs Ordenador");
        ordenadorVsOrdenadorButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        ordenadorVsOrdenadorButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ordenadorVsOrdenadorButton.setPreferredSize(new Dimension(400, 50));
        ordenadorVsOrdenadorButton.setFont(font2);
        ordenadorVsOrdenadorButton.setForeground(Color.BLACK);
        ordenadorVsOrdenadorButton.setFocusPainted(false);
        ordenadorVsOrdenadorButton.setBorderPainted(false);
        ordenadorVsOrdenadorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarJuegoOrdenadorVsOrdenador();
            }
        });
        welcomePanel.add(ordenadorVsOrdenadorButton, gbc);


        startPanel.add(welcomePanel, BorderLayout.CENTER);

        frame.add(startPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    private void iniciarJuegoContraJugador() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        frame.add(gamePanel, BorderLayout.CENTER);
        jugadorVSjugador(); //ERROR jugarContraOtroJugador()
        frame.revalidate();
        frame.repaint();
    }

    private void iniciarJuegoContraOrdenador() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        frame.add(gamePanel, BorderLayout.CENTER);
        inicializarBotones();
        frame.revalidate();
        frame.repaint();
        JOptionPane.showMessageDialog(frame, "Jugador es X y Ordenador es O");
    }

    private void iniciarJuegoOrdenadorVsOrdenador() {
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        frame.add(gamePanel, BorderLayout.CENTER);
        inicializarBotones();
        frame.revalidate();
        frame.repaint();
        JOptionPane.showMessageDialog(frame, "Ordenador 1 es X y Ordenador 2 es O");
        ordenadorVsOrdenador();
    }



    private void jugadorVSjugador() {
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 100));
            button.setFont(new Font("Arial", Font.BOLD, 40));
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tablero[index] == VACIO) {
                        String marca = (turnoActual == JUGADOR1) ? "X" : "O"; // Determinar la marca (X o O) según el jugador actual
                        button.setText(marca);
                        tablero[index] = turnoActual;
                        verificarGanador(JUGADOR1);
                        if (!verificarGanador(JUGADOR1)) {
                            turnoActual = (turnoActual == JUGADOR1) ? JUGADOR2 : JUGADOR1;
                            button.setText(marca);
                            // Verificar si quedan movimientos antes de que el Ordenador haga su movimiento
                            if (quedanMovimientos()) {
                                verificarGanador(JUGADOR2);
                            } else {
                                // Si no quedan movimientos, mostrar un mensaje de empate
                                JOptionPane.showMessageDialog(frame, "¡Empate!");
                            }
                        }
                    }
                }
            });
            buttons[i] = button;
            gamePanel.add(button);

        }
    }


    private void inicializarBotones() {
        for (int i = 0; i < 9; i++) {
            JButton button = new JButton();
            button.setPreferredSize(new Dimension(100, 100));
            button.setFont(new Font("Arial", Font.BOLD, 40));
            final int index = i;
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (tablero[index] == VACIO) {
                        button.setText("X");
                        tablero[index] = JUGADOR1;
                        if (!verificarGanador(JUGADOR1)) {
                            // Verificar si quedan movimientos antes de que el Ordenador haga su movimiento
                            if (quedanMovimientos()) {
                                moverOrdenador(JUGADOR2);
                                verificarGanador(JUGADOR2);
                            } else {
                                // Si no quedan movimientos, mostrar un mensaje de empate
                                JOptionPane.showMessageDialog(frame, "¡Empate!");
                            }
                        }
                    }
                }
            });
            buttons[i] = button;
            gamePanel.add(button);
        }
    }


    private void moverOrdenador(int jugador) {
        Random rand = new Random();
        int pos;
        do {
            pos = rand.nextInt(9);
        } while (tablero[pos] != VACIO);

        String marca = (jugador == JUGADOR1) ? "X" : "O"; // Determinar la marca (X o O) según el jugador
        tablero[pos] = jugador;

        if (jugador == JUGADOR1) {
            buttons[pos].setText("X");
        } else {
            buttons[pos].setText("O");
        }
    }


    private void ordenadorVsOrdenador() {
        reiniciarJuego();

        try {
            Thread.sleep(500); // Pausa un segundo para que la interfaz gráfica tenga tiempo de actualizarse
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        Timer timer = new Timer(500, new ActionListener() {
            int turno = JUGADOR1; // Iniciar con el turno del Jugador 1 (Ordenador 1)

            @Override
            public void actionPerformed(ActionEvent e) {
                if (quedanMovimientos()) {
                    moverOrdenador(turno); // Movimiento del jugador actual (Ordenador)
                    verificarGanador(turno); // Verificar si el jugador actual ha ganado después de su movimiento
                    turno = (turno == JUGADOR1) ? JUGADOR2 : JUGADOR1; // Cambiar al siguiente jugador
                } else {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador si no quedan movimientos
                    JOptionPane.showMessageDialog(frame, "¡Empate!"); // Mostrar mensaje de empate si no quedan movimientos
                }
            }
        });

        timer.start(); // Iniciar el temporizador
    }



    private boolean verificarGanador(int jugador) {
        for (int i = 0; i < 3; i++) {
            if (tablero[i] == jugador && tablero[i + 3] == jugador && tablero[i + 6] == jugador) {
                JOptionPane.showMessageDialog(frame, "¡Jugador " + jugador + " gana!");
                reiniciarJuego();
                return true;
            }
            if (tablero[i * 3] == jugador && tablero[i * 3 + 1] == jugador && tablero[i * 3 + 2] == jugador) {
                JOptionPane.showMessageDialog(frame, "¡Jugador " + jugador + " gana!");
                reiniciarJuego();
                return true;
            }
        }
        if (tablero[0] == jugador && tablero[4] == jugador && tablero[8] == jugador) {
            JOptionPane.showMessageDialog(frame, "¡Jugador " + jugador + " gana!");
            reiniciarJuego();
            return true;
        }
        if (tablero[2] == jugador && tablero[4] == jugador && tablero[6] == jugador) {
            JOptionPane.showMessageDialog(frame, "¡Jugador " + jugador + " gana!");
            reiniciarJuego();
            return true;
        }
        boolean empate = true;
        for (int i = 0; i < 9; i++) {
            if (tablero[i] == VACIO) {
                empate = false;
                break;
            }
        }
        if (empate) {
            JOptionPane.showMessageDialog(frame, "¡Empate!");
            reiniciarJuego();
            return true;
        }
        return false;
    }

    private void reiniciarJuego() {
        for (int i = 0; i < tablero.length; i++) {
            tablero[i] = VACIO;
        }
        // Limpia los textos de los botones en el panel de juego
        for (JButton button : buttons) {
            button.setText("");
        }
    }



    private boolean quedanMovimientos() {
        for (int i = 0; i < tablero.length; i++) {
            if (tablero[i] == VACIO) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TresEnRayaGUI();
            }
        });
    }
}
