import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class GraficaGUI extends JFrame {
    private Nivel nivel;
    private Jucator jucator;

    public GraficaGUI() {
        AdapterNivel adapter = new AdapterNivel();
        try {
            String layout = adapter.citesteNivel("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\src\\nivel.txt");
            BuilderNivel builder = new BuilderNivel();
            nivel = BuilderNivel.buildNivel(layout);
            ManagerJoc.getInstantaCurenta().incarcaNivel(nivel);
            jucator = nivel.getJucator(); // Get the Jucator from the Nivel
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading level: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        setTitle("Joc Roboban");
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton butonSus = new JButton("Sus");
        JButton butonJos = new JButton("Jos");
        JButton butonStanga = new JButton("Stanga");
        JButton butonDreapta = new JButton("Dreapta");

        butonSus.addActionListener(e -> movePlayer(0, -1));
        butonJos.addActionListener(e -> movePlayer(0 , 1));
        butonStanga.addActionListener(e -> movePlayer(-1, 0));
        butonDreapta.addActionListener(e -> movePlayer(1, 0));

        JPanel panel = new JPanel();
        panel.add(butonSus);
        panel.add(butonJos);
        panel.add(butonStanga);
        panel.add(butonDreapta);
        add(panel, BorderLayout.SOUTH);

        // Custom JPanel for rendering the game
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderGame(g);
            }
        };
        add(gamePanel, BorderLayout.CENTER);
    }

    private void movePlayer(int deltaX, int deltaY) {
        new CommandMiscare(jucator, deltaX, deltaY, nivel).execute(); // Pass nivel to CommandMiscare
        jucator = nivel.getJucator(); // Update player reference
        repaint(); // Repaint the game panel to reflect changes
    }

    private void renderGame(Graphics g) {
        // Draw the level and player
        for (int i = 0; i < nivel.getWidth(); i++) {
            for (int j = 0; j < nivel.getHeight(); j++) {
                EntitateJoc element = nivel.getElement(i, j);
                if (element != null) {
                    element.grafica(g); // Call the graphics method of each entity
                }
                else{
                    new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\ground.png").paintIcon(null, g, i * 64, j * 64);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraficaGUI joc = new GraficaGUI();
            joc.setVisible(true);
        });
    }
}