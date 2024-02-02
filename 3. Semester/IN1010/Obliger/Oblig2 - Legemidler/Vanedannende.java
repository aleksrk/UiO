public class Vanedannende extends Legemiddel {

    private int styrke;
    private static int id_teller;

    public Vanedannende(String navn, int pris, double virkestoff, int styrke) {
        super(navn, pris, virkestoff);
        this.styrke = styrke;
        id_teller++;
        id = id_teller;
    }

    public int hentVanedannendeStyrke() {
        return styrke;
    }
    @Override
    public String toString() {
        return (super.toString() + " Styrke: " + styrke);
    }

}
