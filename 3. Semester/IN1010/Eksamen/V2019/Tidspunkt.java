class Tidspunkt implements Comparable<Tidspunkt> {
    private int aar, mnd, dag, time, min, sek;
    Tidspunkt(int aar, int mnd, int dag, int time, int min, int sek) {
        this.aar = aar;
        this.mnd = mnd;
        this.dag = dag;
        this.time = time;
        this.min = min;
        this.sek = sek;
    }

    @Override
    public int compareTo(Tidspunkt t) {
        if (this.aar != t.aar) return this.aar - t.aar;
        if (this.mnd != t.mnd) return this.mnd - t.mnd;
        if (this.dag != t.dag) return this.dag - t.dag;
        if (this.time != t.time) return this.time - t.time;
        if (this.min != t.min) return this.min - t.min;
        return this.sek - t.sek;
    }

}
