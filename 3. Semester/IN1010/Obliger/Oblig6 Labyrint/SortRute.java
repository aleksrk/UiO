
class SortRute extends Rute {

    public int besoekt = 0;

    public SortRute(int radnr, int kolnr, Labyrint lab) {
        super(radnr, kolnr, lab);
    }

    @Override
    public void finn(Rute fra) {
        if (fra == null) {
            System.out.println("Du kan ikke starte i en sort rute!");
            return;
        } else {
            this.settBesoekt();
        }
    }

    @Override
    public String toString() {
        return "#";
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
