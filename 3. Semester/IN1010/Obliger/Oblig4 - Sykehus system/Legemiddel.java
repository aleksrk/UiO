public abstract class Legemiddel {

    protected String navn;
    protected int id;
    protected int pris;
    protected double virkestoff;
    private static int id_teller = 1;

    public Legemiddel(String navn, int pris, double virkestoff) {
        this.navn = navn;
        this.pris = pris;
        this.virkestoff = virkestoff;
        id = id_teller;
        id_teller++;
    }

    public int hentId() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public int hentPris() {
        return pris;
    }

    public double hentVirkestoff() {
        return virkestoff;
    }

    public void settNyPris(int pris) {
        this.pris = pris;
    }

    @Override
    public String toString() {
        return ("Legemiddel:" + navn + " ID: " + id + " Pris: " + pris + " Virkestoff: " + virkestoff);
    }

}
