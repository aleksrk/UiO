public class Varebil extends Bil {

    private double max_vekt;

    public Varebil(String regnr, String merke, String eier, int max_vekt){
        super(regnr, merke, eier);
        this.max_vekt = max_vekt;
        avgift = max_vekt*4;
    }

    public void skrivUt() {
        System.out.println("Regnr: + " + regnr + " Merke: " + merke + " Eier: " + eier + " Årsavgift: " + avgift);
        System.out.println("Maksimal lastevekt: " + max_vekt);
    }

}
