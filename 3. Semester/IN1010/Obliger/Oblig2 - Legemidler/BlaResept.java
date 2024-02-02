public class BlaResept extends Resept {

    public BlaResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);
    }

    public String farge() {
        return "Blå";
    }

    public int prisAaBetale() {
        return ( (int) (legemiddel.hentPris()*.25) );
    }

    @Override
    public String toString() {
        return (super.toString() + " Reseptfarge: " + farge());
    }
}
