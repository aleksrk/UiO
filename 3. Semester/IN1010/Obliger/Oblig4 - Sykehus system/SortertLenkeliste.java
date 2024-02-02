public class SortertLenkeliste<T extends Comparable<T>> extends IndeksertListe<T> {

    @Override
    public void leggTil(T x) {
        for (int i = 0; i < stoerrelse; i++) {
            if (super.hent(i).compareTo(x) > 0) {
                super.leggTil(i, x);
                return;
            }
        }
        super.leggTil(x);
    }
}
