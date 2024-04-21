package TresEnRayaApp;
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

    private void initialize() { //iniciar panel de inicio 
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


/**
*métodos y lógica del jeugo 
**/

    //metodo para iniciar juego jugador contra jugador
    private void iniciarJuegoContraJugador() {

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        JPanel buttonDefaultPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Crear un panel para los botones
        buttonDefaultPanel.setBackground(Color.BLACK); 
       
        JButton backButton = new JButton("Volver a modos"); // Crear el botón de volver atrás 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Agregar aquí la lógica para volver atrás
                frame.getContentPane().removeAll();
                frame.add(startPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reiniciarJuego();
            }
        });

        Font font1 = new Font("Monospaced", Font.BOLD, 16);
        backButton.setFont(font1);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBorder(null);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Color textBotonModos = new Color(0, 225, 255, 100);
        backButton.setForeground(textBotonModos.brighter());
        
        buttonDefaultPanel.add(backButton); // Agregar el botón de volver atrás al panel de botones

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(buttonDefaultPanel, BorderLayout.NORTH);
        
        jugadorVSjugador(); 
        frame.revalidate();
        frame.repaint();
        JOptionPane.showMessageDialog(frame, "Jugador 1 es O y Jugador 2 es X");
    }

    //Método para iniciar juego jugador contra PC
    private void iniciarJuegoContraOrdenador() {

        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        JPanel buttonDefaultPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Crear un panel para los botones
        buttonDefaultPanel.setBackground(Color.BLACK); 
       
        JButton backButton = new JButton("Volver a modos"); // Crear el botón de volver atrás 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Agregar aquí la lógica para volver atrás
                frame.getContentPane().removeAll();
                frame.add(startPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                reiniciarJuego();
            }
        });
        //caracteristicas del boton: letra, tamaño, transparencia...
        Font font1 = new Font("Monospaced", Font.BOLD, 16); 
        backButton.setFont(font1);
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false);
        backButton.setBorder(null);
        backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); 
        Color textBotonModos = new Color(0, 225, 255, 100);
        backButton.setForeground(textBotonModos.brighter());
        
        buttonDefaultPanel.add(backButton); // Agregar el botón de volver atrás al panel de botones

        gamePanel = new JPanel();
        gamePanel.setLayout(new GridLayout(3, 3));
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(buttonDefaultPanel, BorderLayout.NORTH);

        mvjugadorContraOrdenador();
        frame.revalidate();
        frame.repaint();
        JOptionPane.showMessageDialog(frame, "Jugador es X y Ordenador es O");
    }

    //Método para iniciar juego PC contra PC
    private void iniciarJuegoOrdenadorVsOrdenador() {
        
        frame.getContentPane().removeAll();
        frame.setLayout(new BorderLayout());
        JPanel buttonDefaultPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Crear un panel para los botones
        buttonDefaultPanel.setBackground(Color.BLACK); 
       
        JButton backButton = new JButton("Volver a modos"); // Crear el botón de volver atrás 
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timer.stop();
                // Agregar aquí la lógica para volver atrás
                frame.getContentPane().removeAll();
                frame.add(startPanel, BorderLayout.CENTER);
                frame.revalidate();
                frame.repaint();
                
                reiniciarJuego();
                
            }
        });

            Font font1 = new Font("Monospaced", Font.BOLD, 16);
            backButton.setFont(font1);
            backButton.setOpaque(false);
            backButton.setContentAreaFilled(false);
            backButton.setBorderPainted(false);
            backButton.setBorder(null);
            backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Color textBotonModos = new Color(0, 225, 255, 100);
            backButton.setForeground(textBotonModos.brighter());
            
            buttonDefaultPanel.add(backButton); // Agregar el botón de volver atrás al panel de botones

            gamePanel = new JPanel();
            gamePanel.setLayout(new GridLayout(3, 3));
            frame.add(gamePanel, BorderLayout.CENTER);
            frame.add(buttonDefaultPanel, BorderLayout.NORTH);

            ordenadorVsOrdenador();
            frame.revalidate();
            frame.repaint();
            JOptionPane.showMessageDialog(frame, "Ordenador 1 es X y Ordenador 2 es O");
            
    }

    private boolean turnoX = true;
    //metodo logico para  jugador contra jugador
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
                        if (turnoX) {
                            button.setText("X");
                            tablero[index] = JUGADOR1;
                            turnoX = false; // Cambiar turno al siguiente jugador
                        } else {
                            button.setText("O");
                            tablero[index] = JUGADOR2;
                            turnoX = true; // Cambiar turno al siguiente jugador
                        }
                        
                        if (verificarGanador(JUGADOR1)) {
                            // Si el jugador 1 gana, mostrar un mensaje de victoria
                            
                            reiniciarJuego();
                        } else if (verificarGanador(JUGADOR2)) {
                            // Si el jugador 2 gana, mostrar un mensaje de victoria
                            
                            reiniciarJuego();
                        } else if (!quedanMovimientos()) {
                            // Si no quedan movimientos, mostrar un mensaje de empate
                            
                            reiniciarJuego();
                        }
                    }
                }
            });
            buttons[i] = button;
            gamePanel.add(button);
        }
    }
    
    
    
    //metodo logico para  jugador contra maquina
    private void mvjugadorContraOrdenador() {
       
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
                                reiniciarJuego();
                            }
                        }
                    }
                }
            });
            buttons[i] = button;
            gamePanel.add(button);
        }
    }

    
    private Timer timer; //ponemos la variable timer fuera para que desde el método iniciarJuegoOrdenadorVsOrdenador() pueda acceder y parar el temporizador si volvemos atrás

    //método para juego de máquina contra máquina: simulacion de juego 
    private void ordenadorVsOrdenador() {
       // Inicializar los botones para el juego
    for (int i = 0; i < 9; i++) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(100, 100));
        button.setFont(new Font("Arial", Font.BOLD, 40));
        gamePanel.add(button); // Agregar el botón al panel de juego
        buttons[i] = button; // Agregar el botón al arreglo de botones para poder acceder a él más tarde
    }

       timer = new Timer(1000, new ActionListener() {
            int turno = JUGADOR1; // Inicia con el turno del Jugador 1 (Ordenador 1)

            @Override
            public void actionPerformed(ActionEvent e) {
                if (quedanMovimientos()) {
                    moverOrdenador(turno); // Movimiento del jugador actual (Ordenador)
                    verificarGanador(turno); // Verificar si el jugador actual ha ganado después de su movimiento
                    turno = (turno == JUGADOR1) ? JUGADOR2 : JUGADOR1; // Cambiar al siguiente jugador
                } else {
                    ((Timer) e.getSource()).stop(); // Detener el temporizador si no quedan movimientos
                    return;
                }
            }
        });

        timer.start(); // Iniciar el temporizador
        
        reiniciarJuego();
    }


    private void moverOrdenador(int jugador) {
        Random rand = new Random();
        int pos;
        do {
            pos = rand.nextInt(9);
        } while (tablero[pos] != VACIO);

        tablero[pos] = jugador;

        if (jugador == JUGADOR1) {
            buttons[pos].setText("X");
        } else {
            buttons[pos].setText("O");
        }
        return;
    }

    //verificamos si hay un ganador y cual es
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

    //verificar si hay movimientos aun disponibles para seguir jugando
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
