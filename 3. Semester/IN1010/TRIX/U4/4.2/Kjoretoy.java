public abstract class Kjoretoy {

    protected String regnr;
    protected String merke;
    protected String eier;

    public Kjoretoy(String regnr, String merke, String eier) {
        this.regnr = regnr;
        this.merke = merke;
        this.eier = eier;
    }

    public void skrivUt() {

        System.out.println("Regnr: + " + regnr + " Merke: " + merke + " Eier: " + eier);

    }

    public String hentNavn() {
        return eier;
    }

    public String hentRegnr() {
        return regnr;
    }

}
