public class Buss extends Kjoretoy {

    protected int antallSitteplasser;

    public Buss(String regnr, String merke, String eier, int antallSitteplasser) {

        super(regnr, merke, eier);
        this.antallSitteplasser = antallSitteplasser;
    }

    public void skrivUt() {
        System.out.println("Regnr: + " + regnr + " Merke: " + merke + " Eier: " + eier + " Antallsitteplasser: " + antallSitteplasser);
    }
}
