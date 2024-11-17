public class FactoryEntitateJoc {
    public static EntitateJoc creazaEntitate(char tip, int x, int y) {
        switch (tip) {
            case '#':
                return new Perete(x,y); // Provide default positions
            case 'C':
                return new Cutie(x,y);
            case 'J':
                return new Jucator(x,y);
            case ' ':
                return null;
            case 'G':
                return new Obiectiv(x,y);
            default:
                return null;
        }
    }
}