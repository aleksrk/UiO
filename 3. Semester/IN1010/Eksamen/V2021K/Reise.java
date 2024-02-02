public class Reise {

    protected String navn;
    protected int pris;
    protected int plasser;


    public Reise(String navn, int pris, int plasser) {
        this.navn = navn;
        this.pris = pris;
        this.plasser = plasser;
    }

    public int getPris() {
        return Math.round(pris*1.25);
    }

}
