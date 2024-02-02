public class Personbil extends Bil{

    private int antallSitteplasser;

    public Personbil(String regnr, String merke, String eier, int antallSitteplasser) {
        super(regnr, merke, eier);
        this.antallSitteplasser = antallSitteplasser;
        avgift = 3000.0;
    }

    public void skrivUt() {
        System.out.println("Regnr: + " + regnr + " Merke: " + merke + " Eier: " + eier + " Årsavgift: " + avgift);
        System.out.println("Antall sitteplasser: " + antallSitteplasser);
    }
}
