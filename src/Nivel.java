public class Nivel {
    private EntitateJoc[][] grid;
    private int width;
    private int height;
    private Jucator jucator; // Reference to the player

    public Nivel(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new EntitateJoc[width][height];
    }

    public void setElement(int x, int y, EntitateJoc element) {
        grid[x][y] = element;
        if (element instanceof Jucator) {
            this.jucator = (Jucator) element; // Set the player reference when adding
        }
    }

    public EntitateJoc getElement(int x, int y) {
        return grid[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Jucator getJucator() {
        return jucator; // Return the player reference
    }

    // Method to set the player's position
    public void setPozitieJucator(int x, int y) {
        if (jucator != null) {
            grid[jucator.getX()][jucator.getY()] = null; // Clear the old position
            jucator.setPosition(x, y); // Update the player's position
            grid[x][y] = jucator; // Set the player in the new position
        }
    }
}