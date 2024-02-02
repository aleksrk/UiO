public abstract class Bil extends Kjoretoy {

    protected double avgift;

    public Bil(String regnr, String merke, String eier) {
        super(regnr, merke, eier);
    }

    public void skrivUt() {
        System.out.println("Regnr: + " + regnr + " Merke: " + merke + " Eier: " + eier + " Årsavgift: " + avgift);
    }

    public double hentAvgift() {
        return avgift;
    }

}
