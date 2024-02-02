public abstract class Lenkeliste<T> implements Liste<T> {
    class Node {
        Node neste = null;
        T data;
        Node (T x) {
            data = x;
        }
    }
    private Node start = null;
}
