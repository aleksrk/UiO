public class HvitResept extends Resept {

    protected int oppdatertPris;

    public HvitResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
        oppdatertPris = legemiddel.hentPris();
    }
    public String farge() {
        return "Hvit";
    }

    public int prisAaBetale() {
        return oppdatertPris;
    }

    @Override
    public String toString() {
        return (super.toString() + " Reseptfarge: " + farge());
    }
}
