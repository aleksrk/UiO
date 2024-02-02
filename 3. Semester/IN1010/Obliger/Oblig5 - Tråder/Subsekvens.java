public class Subsekvens {

    public final String subs;
    private int forekomster;

    public Subsekvens(String subs, int forekomster) {

        this.subs = subs;
        this.forekomster = forekomster;

    }

    public int hentForekomster() {
        return forekomster;
    }

    public void settForekomster(int forekomster) {
        this.forekomster = forekomster;
    }

    @Override
    public String toString() {
        return ("(" + subs + "," + forekomster + ")");
    }

}
