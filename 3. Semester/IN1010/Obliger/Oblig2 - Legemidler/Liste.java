public interface Liste<T> {
    int stoerrelse();
    void leggTil();
    T hent();
    T fjern();
}
