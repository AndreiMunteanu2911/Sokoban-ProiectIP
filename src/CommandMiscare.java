
public class CommandMiscare {
    private Jucator jucator;
    private int deltaX;
    private int deltaY;
    private Nivel nivel;

    public CommandMiscare(Jucator jucator, int deltaX, int deltaY, Nivel nivel) {
        this.jucator = jucator;
        this.deltaX = deltaX;
        this.deltaY = deltaY;
        this.nivel = nivel;
    }

    public void execute() {
        int newX = jucator.getX() + deltaX;
        int newY = jucator.getY() + deltaY;
        if (deltaX == 1 && deltaY == 0) {
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator3.png");
        }
        if (deltaX == -1 && deltaY == 0) {
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator4.png");
        }
        if (deltaX == 0 && deltaY == 1) {
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
        }
        if (deltaX == 0 && deltaY == -1) {
            jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator2.png");
        }


        if (isMiscareValida(newX, newY)) {
            if (nivel.getElement(newX, newY) instanceof Obiectiv) {
                if (!((Obiectiv) nivel.getElement(newX, newY)).isCutiePeObiectiv()) {
                    mutaJucator(newX, newY);
                    if (jucator.getEstePeObiectiv()){
                        nivel.setElement(newX-deltaX,newY-deltaY,new Obiectiv(newX-deltaX,newY-deltaY));
                       }
                    jucator.setEstePeObiectiv(true,deltaX,deltaY);
                } else {
                    if (nivel.getElement(newX + deltaX, newY + deltaY) == null) {
                        nivel.setElement(newX + deltaX, newY + deltaY, new Cutie(newX + deltaX, newY + deltaY));
                        ((Obiectiv) nivel.getElement(newX, newY)).setCutiePeObiectiv(false);
                    }
                    if (nivel.getElement(newX + deltaX, newY + deltaY) instanceof Obiectiv && !((Obiectiv) nivel.getElement(newX + deltaX, newY + deltaY)).isCutiePeObiectiv()) {
                        nivel.setElement(newX + deltaX, newY + deltaY, new Obiectiv(newX + deltaX, newY + deltaY));
                        ((Obiectiv) nivel.getElement(newX, newY)).setCutiePeObiectiv(false);
                        ((Obiectiv) nivel.getElement(newX+deltaX, newY+deltaY)).setCutiePeObiectiv(true);
                    }
                }

            } else {


                mutaJucator(newX, newY);
                if (jucator.getEstePeObiectiv()){
                    nivel.setElement(newX-deltaX,newY-deltaY,new Obiectiv(newX-deltaX,newY-deltaY));
                    jucator.setEstePeObiectiv(false, deltaX,deltaY);
                }
            }
        } else{
            if (!jucator.getEstePeObiectiv()){
                if (deltaX == 1 && deltaY == 0) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator3.png");
                }
                if (deltaX == -1 && deltaY == 0) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator4.png");
                }
                if (deltaX == 0 && deltaY == 1) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator1.png");
                }
                if (deltaX == 0 && deltaY == -1) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\jucator2.png");
                }
            }
            else{
                if (deltaX == 1 && deltaY == 0) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator3.png");
                }
                if (deltaX == -1 && deltaY == 0) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator4.png");
                }
                if (deltaX == 0 && deltaY == 1) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator1.png");
                }
                if (deltaX == 0 && deltaY == -1) {
                    jucator.setSprite("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\grafica\\obiectiv+jucator2.png");
                }
            }
                if (isCutie(newX, newY)) {
                    if (verificaPushCutie(newX, newY)) {
                        pushCutie(newX, newY);
                        AdapterAudio.playAudio("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\audio\\sounds\\jump.wav",false);
                    }
                }
        }
    }

    private boolean isMiscareValida(int x, int y) {
        EntitateJoc target = nivel.getElement(x, y);
        return (x >= 0 && x < nivel.getColoane() && y >= 0 && y < nivel.getRanduri() && !(target instanceof Perete) && !isCutie(x, y));
    }

    private boolean isCutie(int x, int y) {
        EntitateJoc target = nivel.getElement(x, y);
        return target instanceof Cutie;
    }

    private boolean isObiectiv(int x, int y) {
        EntitateJoc target = nivel.getElement(x, y);
        return target instanceof Obiectiv;
    }

    private boolean verificaPushCutie(int crateX, int crateY) {
        int pushX = crateX + deltaX;
        int pushY = crateY + deltaY;
        EntitateJoc target = nivel.getElement(pushX, pushY);
        return (pushX >= 0 && pushX < nivel.getColoane() && pushY >= 0 && pushY < nivel.getRanduri() && ((target == null) || (target instanceof Obiectiv && !((Obiectiv) target).isCutiePeObiectiv())));
    }

    private void pushCutie(int crateX, int crateY) {
        int pushX = crateX + deltaX;
        int pushY = crateY + deltaY;
        EntitateJoc crate = nivel.getElement(crateX, crateY);
        if (nivel.getElement(pushX, pushY) instanceof Obiectiv) {
            if (!((Obiectiv) nivel.getElement(pushX, pushY)).isCutiePeObiectiv()) {
                ((Obiectiv) nivel.getElement(pushX, pushY)).setCutiePeObiectiv(true);
                nivel.setElement(crateX, crateY, null);
            }
        } else {
            nivel.setElement(pushX, pushY, null);
            nivel.setElement(pushX, pushY, crate);
            nivel.setElement(crateX, crateY, null);
            if (crate instanceof Cutie) {
                ((Cutie) crate).setPozitie(pushX, pushY);
            }
        }
    }

    private void mutaJucator(int x, int y) {
        nivel.setPozitieJucator(x, y);
        AdapterAudio.playAudio("D:\\ANDREI\\cursuri\\ANUL III UGAL\\IP\\Joc Sokoban\\audio\\sounds\\tap.wav",false);
    }
}