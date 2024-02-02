class Kvadrat extends Figur {
    private double lengde;

    public Kvadrat(double lengde) {
        this.lengde = lengde;
    }

    public double areal() {
        return lengde*lengde;
    }
}
