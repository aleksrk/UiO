public class Prioritetskoe<T extends Comparable<T>> extends Lenkeliste<T> {

    public void leggTil(T x) {

        if (start == null) {
            super.leggTil(x);
            return;
        }

        Node nyNode = new Node(x);

        if (start.neste == null) {
            if (nyNode.data.compareTo(start.data) >= 0) {
                start.neste = nyNode;
            } else {
                nyNode.neste = start;
                start = nyNode;
            }
            stoerrelse++;
            return;
        }

        Node forrige = null;
        Node peker = start;

        while(peker != null) {

            if (peker.data.compareTo(nyNode.data) >= 0) {
                if (forrige == null) {
                    start = nyNode;
                } else {
                    forrige.neste = nyNode;
                }
                nyNode.neste = peker;
                stoerrelse++;
                return;
            }

            if (peker.neste == null) {
                peker.neste = nyNode;
                stoerrelse++;
                return;

            }
            forrige = peker;
            peker = peker.neste;

        }

    }
}
