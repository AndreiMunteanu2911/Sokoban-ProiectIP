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
    public void setEstePeObiectiv(boolean val, int deltaX, int deltaY){
        this.estePeObiectiv=val;
        if (!this.estePeObiectiv){
            if (deltaX == 1 && deltaY == 0) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator3.png");
            }
            if (deltaX == -1 && deltaY == 0) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator4.png");
            }
            if (deltaX == 0 && deltaY == 1) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
            }
            if (deltaX == 0 && deltaY == -1) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator2.png");
            }
        }
        else{
            if (deltaX == 1 && deltaY == 0) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator3.png");
            }
            if (deltaX == -1 && deltaY == 0) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator4.png");
            }
            if (deltaX == 0 && deltaY == 1) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator1.png");
            }
            if (deltaX == 0 && deltaY == -1) {
                this.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator2.png");
            }
        }
    }

    public void setPozitie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void grafica(Graphics g) {
        sprite.paintIcon(null, g, x * 64, y * 64); // Draw the player sprite
    }
}