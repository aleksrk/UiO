public class PResept extends HvitResept {

    public PResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        super(legemiddel, utskrivendeLege, pasient, reit);

        if (oppdatertPris < 108) {
            oppdatertPris = 0;
        } else {
        oppdatertPris -= 108;
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
