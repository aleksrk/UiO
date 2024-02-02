abstract class Lenkeliste<T> implements Liste<T>{

    protected int stoerrelse = 0;
    protected Node start = null;

    protected class Node {
        Node neste = null;
        T data;
        Node(T x) {
            data = x;
        }
    }

    public int stoerrelse() {
        return stoerrelse;
    }

    public void leggTil(T x) {

        Node nyNode = new Node(x);

        if (stoerrelse == 0) {
            start = nyNode;
        } else {
            Node peker = start;
            while(peker.neste != null) {
                peker = peker.neste;
            }
            peker.neste = nyNode;
        }
        stoerrelse++;

    }

    public T hent() {
        return start.data;
    }

    public T fjern() throws UgyldigListeindeks {
        if (stoerrelse == 0) {
            throw new UgyldigListeindeks(0);
        }
        T data = start.data;
        start = start.neste;

        stoerrelse--;
        return data;
    }

    @Override
    public String toString() {
        String utskrift = "";
        Node peker = start;

        if (peker == null) {
            return utskrift;
        }

        while(peker.neste != null) {
            utskrift += peker.data;
            utskrift += " ";
            peker = peker.neste;
        }
        utskrift += peker.data;
        return utskrift;
    }
}
