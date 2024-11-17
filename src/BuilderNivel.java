public class BuilderNivel {
    public static Nivel buildNivel(String nivelLayout) {
        String[] randuri = nivelLayout.split("\n");
        Nivel nivel = new Nivel(randuri[0].length(), randuri.length);
        for (int i = 0; i < randuri.length; i++) {
            for (int j = 0; j < randuri[i].length(); j++) {
                EntitateJoc element = FactoryEntitateJoc.creazaEntitate(randuri[i].charAt(j), j, i);
                if (element instanceof Jucator) {
                    nivel.setPozitieJucator(j, i); // Set player position
                }
                nivel.setElement(j, i, element); // Set the element in the grid
            }
        }
        return nivel;
    }
}