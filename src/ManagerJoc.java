public class ManagerJoc {
    private static ManagerJoc instantaCurenta;
    private Nivel nivelCurent;

    private ManagerJoc(){}

    public static ManagerJoc getInstantaCurenta(){
        if (instantaCurenta == null) {
            instantaCurenta=new ManagerJoc();
        }
        return instantaCurenta;
    }

    public Nivel getNivelCurent(){
        return this.nivelCurent;
    }


    public void incarcaNivel(Nivel nivel){
        this.nivelCurent=nivel;
    }
}
