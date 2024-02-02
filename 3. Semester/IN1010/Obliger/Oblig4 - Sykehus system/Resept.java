public abstract class Resept {

    protected int id;
    protected Legemiddel legemiddel;
    protected Lege utskrivendeLege;
    protected Pasient pasient;
    protected int reit;
    private static int idteller = 1;

    public Resept(Legemiddel legemiddel, Lege utskrivendeLege, Pasient pasient, int reit) {
        this.legemiddel = legemiddel;
        this.utskrivendeLege = utskrivendeLege;
        this.pasient = pasient;
        this.reit = reit;
        id = idteller;
        idteller++;
    }

    public int hentId() {
        return id;
    }

    public Legemiddel hentLegemiddel() {
        return legemiddel;
    }

    public Lege hentLege() {
        return utskrivendeLege;
    }

    public Pasient hentPasient() {
        return pasient;
    }

    public int hentReit() {
        return reit;
    }

    public boolean bruk() {
        if (reit > 0) {
            reit--;
            return true;
        }
        return false;
    }

    abstract public String farge();

    abstract public int prisAaBetale();

    @Override
    public String toString(){
        return ("ID: " + id + " Legemiddel " + legemiddel.hentNavn() +
        " Lege: " + utskrivendeLege.hentNavn() + " Pasient: " + pasient +
        " Reit: " + reit);
    }

}
