class Rektangel extends Figur {
    private double lengde;
    private double hoyde;

    public Rektangel(double lengde, double hoyde) {
        this.lengde = lengde;
        this.hoyde = hoyde;
    }

    public double areal() {
        return lengde*hoyde;
    }
}
