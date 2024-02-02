
class Aapning extends HvitRute {

    public int besoekt = 0;

    public Aapning(int radnr, int kolnr, Labyrint lab) {
        super(radnr, kolnr, lab);

    }

    @Override
    public void finn(Rute fra) {
        if (fra == null) {
            this.settBesoekt();
        } else {
            this.settBesoekt();
            System.out.println("(" + this.radnr + "," + this.kolnr + ")");
        }
    }

    @Override
    public void settBesoekt() {
        this.besoekt = 1;
    }

    @Override
    public int erBesoekt() {
        return besoekt;
    }

}
