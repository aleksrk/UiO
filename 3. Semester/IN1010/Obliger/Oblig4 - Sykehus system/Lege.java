public class Lege implements Comparable<Lege> {

    protected String navn;
    public IndeksertListe<Resept> utskrevneResepter = new IndeksertListe<Resept>();

    public Lege(String navn) {
        this.navn = navn;
    }

    public String hentNavn() {
        return navn;
    }

    public String toString() {
        return ("Navn: " + navn);
    }

    public int compareTo (Lege annen) {
        return this.navn.compareTo(annen.navn);
    }

    public IndeksertListe skrivUtResepter() {
        return utskrevneResepter;
    }

    public Resept skrivResept(Legemiddel legemiddel, Pasient pasient, int reit) throws UlovligUtskrift {
        HvitResept hvit = new HvitResept(legemiddel, this, pasient, reit);
        utskrevneResepter.leggTil(hvit);
        pasient.leggTilResept(hvit);
        return hvit;
    }
}
