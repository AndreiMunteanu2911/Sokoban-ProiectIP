import java.util.ArrayList;
import java.util.List;


public class Nivel {
    private EntitateJoc[][] grid;
    private int coloane;
    private int randuri;
    private Jucator jucator;
    private List<Integer> listaObiectiveX;
    private List<Integer> listaObiectiveY;

    public Nivel(int coloane, int randuri) {
        this.coloane = coloane;
        this.randuri = randuri;
        grid = new EntitateJoc[coloane][randuri];
        this.listaObiectiveX=new ArrayList<Integer>();
        this.listaObiectiveY=new ArrayList<Integer>();
    }

    public void setElement(int x, int y, EntitateJoc element) {
        grid[x][y] = element;
        if (element instanceof Jucator) {
            this.jucator = (Jucator) element;
        }
    }

    public List<Integer> getListaObiectiveX(){
        return this.listaObiectiveX;
    }
    public List<Integer> getListaObiectiveY(){
        return this.listaObiectiveY;
    }

    public EntitateJoc getElement(int x, int y) {
        return grid[x][y];
    }

    public int getColoane() {
        return coloane;
    }

    public int getRanduri() {
        return randuri;
    }

    public Jucator getJucator() {
        return jucator;
    }

    // Method to set the player's position
    public void setPozitieJucator(int x, int y) {
        if (jucator != null) {
            grid[jucator.getX()][jucator.getY()] = null;
            jucator.setPozitie(x, y);
            grid[x][y] = jucator;
        }
    }
    public boolean verificaObiective() {
        for (int i = 0; i < listaObiectiveX.size(); i++) {
            int x = listaObiectiveX.get(i);
            int y = listaObiectiveY.get(i);
            EntitateJoc element = this.getElement(x, y);

            if (element instanceof Obiectiv) {
                if (!((Obiectiv) element).isCutiePeObiectiv()) {
                    return false;
                }
            } else {
                System.out.println("False2");
                return false;
            }
        }
        System.out.println("True");
        return true;
    }

}