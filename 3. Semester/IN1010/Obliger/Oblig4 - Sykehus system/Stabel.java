public class Stabel<T> extends Lenkeliste<T> {

    @Override
    public void leggTil(T x) {

        if(stoerrelse == 0) {
            super.leggTil(x);
        } else {
            Node nyNode = new Node(x);
            nyNode.neste = start;
            start = nyNode;
            stoerrelse++;
        }
    }
}
