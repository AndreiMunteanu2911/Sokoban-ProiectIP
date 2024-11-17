import java.awt.*;

import javax.swing.ImageIcon;

public class Jucator extends EntitateJoc {
    private int x;
    private int y;
    private ImageIcon sprite;
    private boolean estePeObiectiv;

    public Jucator(int startX, int startY) {
        this.x = startX;
        this.y = startY;
        this.estePeObiectiv=false;
        this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
    }

    public void setSprite(String filepath){
        this.sprite = new ImageIcon(filepath);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean getEstePeObiectiv(){
        return estePeObiectiv;
    }
    public void setEstePeObiectiv(boolean val){
        this.estePeObiectiv=val;
        if (this.estePeObiectiv==false){
            this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
        }
        else{
            this.sprite = new ImageIcon("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1+obiectiv.png");
        }
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void grafica(Graphics g) {
        sprite.paintIcon(null, g, x * 64, y * 64); // Draw the player sprite
    }
}