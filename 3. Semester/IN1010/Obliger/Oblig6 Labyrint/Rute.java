
abstract class Rute {

    protected int radnr;
    protected int kolnr;
    protected Labyrint lab;
    protected Rute nord;
    protected Rute syd;
    protected Rute vest;
    protected Rute oest;

    public Rute(int radnr, int kolnr, Labyrint lab) {
        this.radnr = radnr;
        this.kolnr = kolnr;
        this.lab = lab;
    }

    public String tegn() {
        return "";
    }

    public void settNaboer(Rute nord, Rute vest, Rute syd, Rute oest) {
        this.nord = nord;
        this.vest = vest;
        this.syd = syd;
        this.oest = oest;
    }

    public void finn(Rute fra) {
    }

    public void settBesoekt() {}
    public int erBesoekt() {
        return 0;
    }

}
