import java.util.ArrayList;
public class sortertArrayList<T extends Comparable<T>> {
    ArrayList liste = new ArrayList<T>();

    public void settInn(T data) {
        for (int i =0; i < list.size(); i++) {
            if (list.get(i).compareTo(data) > 0)
            liste.add(i, data);
            return;
        }
        liste.add(data);
    }

    public T hentUtFoerste() {
        return list.remove(0);
    }
}
