public class Prioritetskoe<T> implements T{

    protected Node start = null;
    protected int prioritet;
    protected int antall = 0;

    public void settInn(T inn, int prio) {

        Node nyNode = new Node(inn, prio);
        antall++;

        if(start == null) {
            start = nyNode;
        } else if(nyNode.prio <= start.prio) {
            ny.neste = start; start = nyNode;
        } else {
            Node peker = start;
            while(peker.neste != null && peker.neste.prio<nyNode.prio) {
                peker = neste;
            }
            nyNode.neste = peker.neste;
            peker.neste = nyNode;
        }

        Node forrige = null;
        Node peker = start;

        while (peker != null) {
            if (peker.prioritet)
        }

    }
    public T taUt() {
        if (start == null) return null;
        T ut = start.data;
        start = start.neste; antall --;
        return ut;
    }
    public int antall() {
        return antall;
    }

    protected class Node {
        Node neste = null;
        T data;
        int prio;
        Node(T x, int prio) {
            data = x, this.prio = prio;
        }
    }
}
