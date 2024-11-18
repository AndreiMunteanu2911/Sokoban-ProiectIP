import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Obiectiv extends EntitateJoc {
    private int x;
    private int y;
    private boolean isCutiePeObiectiv;
    private ImageIcon sprite;

    public Obiectiv(int x, int y) {
        this.x = x;
        this.y = y;
        this.isCutiePeObiectiv = false; // Initially, no crate is on the target
        this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv.png"); // Load target sprite
    }

    @Override
    public void grafica(Graphics g) {
        sprite.paintIcon(null, g, x * 64, y * 64); // Draw the player sprite
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isCutiePeObiectiv() {
        return isCutiePeObiectiv;
    }

    public void setCutiePeObiectiv(boolean isCrateOnTarget) {
        this.isCutiePeObiectiv = isCrateOnTarget;
        if (this.isCutiePeObiectiv){
            this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+cutie.png");
        }else{
            this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv.png");
        }

    }

}