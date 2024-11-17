import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;

public class Cutie extends EntitateJoc {
    private int x;
    private int y;
    private ImageIcon sprite;

    public Cutie(int x, int y) {
        this.x = x;
        this.y = y;
        this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\cutie.png");
    }

    @Override
    public void grafica(Graphics g) {
        sprite.paintIcon(null, g, x * 64, y * 64); // Draw the player sprite
    }

    // Getters and setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }}