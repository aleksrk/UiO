class DobbeltLenketListe<T> {

    protected Node start = null;
    int antall = 0;

    protected class Node {
        Node neste = null;
        Node forrige = null;
        T data;
        Node(T x) {
            data = x;
        }

        void settNeste(Node neste) {
            this.neste = neste;
            neste.settForrige(this);
        }

        void settForrige(Node forrige) {
            this.forrige = forrige;
        }

        Node hentNeste() {
            return neste;
        }

        Node hentForrige() {
            return forrige;
        }
        T hentData() {
            return data;
        }
    }

    public void settInn(T element) {
        Node nyNode = new Node(element);
        if (antall == 0) {
            start = nyNode;
        } else {
            Node peker = start;
            while (node.hentNeste() != null) {
                node = node.hentNeste();
            }
            node.settNeste(nyNode);
            nyNode.settForrige(node);
        }
        antall++;
    }

    public void skrivUt() {
        Node node = start;
        while (node != null) {
            System.out.println(node.hentData());
            node = node.hentNeste();
        }
    }
}
