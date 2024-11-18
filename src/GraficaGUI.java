import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class GraficaGUI extends JFrame implements KeyListener {
    private Nivel nivel;
    private Jucator jucator;
    private int nivelCurent=1;
    private int mutariCurente=0;

    public GraficaGUI() {

        incarcaNivel(nivelCurent);

        setTitle("Joc Roboban");
        setSize(640, 640);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        AdapterAudio.playAudio("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\audio\\time_for_adventure.wav",true);

        JPanel panelJoc = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                graficaJoc(g);
            }
        };
        panelJoc.setFocusable(true);
        panelJoc.addKeyListener(this);
        add(panelJoc, BorderLayout.CENTER);

        panelJoc.requestFocusInWindow();
    }

    private void incarcaNivel(int nr) {
        AdapterNivel adapter = new AdapterNivel();
        try {
            if (nr != 21) {
            String layout = adapter.citesteNivel("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\src\\niveluri\\nivel"+nr+".txt");
            BuilderNivel builder = new BuilderNivel();
            nivel = BuilderNivel.buildNivel(layout);
            ManagerJoc.getInstantaCurenta().incarcaNivel(nivel);
            jucator = nivel.getJucator();}
            else {
                JOptionPane.showMessageDialog(this, "Ati terminat toate nivelurile. Felicitari");
                this.nivelCurent=1;
                incarcaNivel(1);
                this.mutariCurente=0;
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Ati terminat toate nivelurile" + e.getMessage(), "Felicitari", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void mutaJucator(int deltaX, int deltaY) {
        this.mutariCurente++;
        new CommandMiscare(jucator, deltaX, deltaY, nivel).execute();
        jucator = nivel.getJucator();
        repaint();
        if (nivel.verificaObiective()) {
            AdapterAudio.playAudio("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\audio\\sounds\\coin.wav",false);
            JOptionPane.showMessageDialog(this, "Felicitari! Ati terminat nivelul in "+this.mutariCurente+" mutari", "Bravo!", JOptionPane.INFORMATION_MESSAGE);
            this.nivelCurent++;
            this.mutariCurente=0;
            incarcaNivel(nivelCurent);
            repaint();
        }

    }

    private void graficaJoc(Graphics g) {
        for (int i = 0; i < nivel.getColoane()-1; i++) {
            for (int j = 0; j < nivel.getRanduri(); j++) {
                EntitateJoc element = nivel.getElement(i, j);
                if (element != null) {
                    element.grafica(g);
                } else {
                    new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\ground.png").paintIcon(null, g, i * 64, j * 64);
                }
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_UP:
                mutaJucator(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                mutaJucator(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                mutaJucator(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                mutaJucator(1, 0);
                break;
            case KeyEvent.VK_R:
                restartNivel();
                break;
        }
    }

    private void restartNivel() {
        incarcaNivel(nivelCurent);
        this.mutariCurente=0;
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GraficaGUI joc = new GraficaGUI();
            joc.setVisible(true);
        });
    }
}