public class Vanlig extends Legemiddel {

    private static int id_teller;

    public Vanlig(String navn, int pris, double virkestoff) {
        super(navn, pris, virkestoff);
        id_teller++;
        id = id_teller;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
