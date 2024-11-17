import javax.swing.*;
import java.awt.*;

public class Perete extends EntitateJoc {
    private int x;
    private int y;
    private ImageIcon sprite;

    public Perete(int x, int y) {
        this.x = x;
        this.y = y;
        this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\perete.png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void grafica(Graphics g) {
        sprite.paintIcon(null, g, x * 64, y * 64); // Draw the player sprite
    }
}