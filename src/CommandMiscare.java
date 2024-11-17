// Import the Crate class if it's in a different package

public class CommandMiscare {
    private Jucator jucator;
    private int deltaX;
    private int deltaY;
    private Nivel nivel; // Reference to the Nivel instance

    public CommandMiscare(Jucator jucator, int deltaX, int deltaY, Nivel nivel) {
        this.jucator = jucator;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.nivel = nivel; // Assign the nivel instance
    }

    public void execute() {
        int newX = jucator.getX() + deltaX;
        int newY = jucator.getY() + deltaY;
        if (deltaX==1&&deltaY==0){
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator3.png");
        }
        if (deltaX==-1&&deltaY==0){
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator4.png");
        }
        if (deltaX==0&&deltaY==1){
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
        }
        if (deltaX==0&&deltaY==-1){
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator2.png");
        }


        // Check for boundaries and obstacles
        if (isMoveValid(newX, newY)) {
            // Move the player to the new position
            movePlayer(newX, newY);
        } else if (isCrate(newX, newY)) {
            // Check if the player can push the crate
            if (canPushCrate(newX, newY)) {
                // Move the crate
                pushCrate(newX, newY);
                // Move the player
                //movePlayer(newX, newY);
            }
            else if (nivel.getElement(newX,newY) instanceof Obiectiv){
                if (!((Obiectiv) nivel.getElement(newX, newY)).isCrateOnTarget()){
                    System.out.println("sneedtest");
                    movePlayer(newX, newY);
                    jucator.setEstePeObiectiv(true);

                }
            }
        }
    }

    private boolean isMoveValid(int x, int y) {
        // Check if the move is valid (e.g., within bounds and not colliding with walls)
        EntitateJoc target = nivel.getElement(x, y);
        return (x >= 0 && x < nivel.getWidth() && y >= 0 && y < nivel.getHeight() && !(target instanceof Perete)&&!isCrate(x, y));
    }

    private boolean isCrate(int x, int y) {
        // Check if the target position contains a crate
        EntitateJoc target = nivel.getElement(x, y);
        return target instanceof Cutie; // Check if the target is an instance of Crate
    }

    private boolean isObiectiv(int x, int y) {
        // Check if the target position contains a crate
        EntitateJoc target = nivel.getElement(x, y);
        return target instanceof Obiectiv; // Check if the target is an instance of Crate
    }

    private boolean canPushCrate(int crateX, int crateY) {
        // Calculate the position in front of the crate in the direction of the push
        int pushX = crateX + deltaX;
        int pushY = crateY + deltaY;

        // Check if the push position is within bounds and empty
        EntitateJoc target = nivel.getElement(pushX, pushY);
        return (pushX >= 0 && pushX < nivel.getWidth() && pushY >= 0 && pushY < nivel.getHeight() && ((target ==null)||(target instanceof Obiectiv&& !((Obiectiv) target).isCrateOnTarget())));
    }

    private void pushCrate(int crateX, int crateY) {
        // Calculate the position in front of the crate
        int pushX = crateX + deltaX;
        int pushY = crateY + deltaY;

        // Get the crate entity
        EntitateJoc crate = nivel.getElement(crateX, crateY);

        // Move the crate to the new position
        if (nivel.getElement(pushX,pushY) instanceof Obiectiv){
            if (((Obiectiv) nivel.getElement(pushX,pushY)).isCrateOnTarget()==false){
                ((Obiectiv) nivel.getElement(pushX,pushY)).setCrateOnTarget(true);
                nivel.setElement(crateX,crateY, null);
            }
        }else{
            nivel.setElement(pushX,pushY,null);
            nivel.setElement(pushX, pushY, crate); // Move the crate to the new position
            nivel.setElement(crateX, crateY, null); // Clear the old position of the crate

            // Update the crate's internal position
            if (crate instanceof Cutie) {
                ((Cutie) crate).setPosition(pushX, pushY); // Update the crate's position
            }
        }

    }

    private void movePlayer(int x, int y) {
        nivel.setPozitieJucator(x, y); // Use the existing method to move the player
    }
}