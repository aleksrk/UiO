public class MilResept extends HvitResept {

    public MilResept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient) {
        super(legemiddel, utskrivendeLege, pasient, 3);
        oppdatertPris = 0;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
