public class AllInclusive extends Reise {
    private int kvalitet;

    public AllInclusive(String navn, int pris, int plasser, int kvalitet) {
        super(navn, pris, plasser);
        this.kvalitet = kvalitet;
    }

    @Override
    public int getPris() {
        return pris;
    }

}
