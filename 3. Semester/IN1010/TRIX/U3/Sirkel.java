class Sirkel extends Figur {
    private double radius;

    public Sirkel(double radius) {
        this.radius = radius;
    }

    public double areal() {
        return radius*radius*Math.PI;
    }

}
