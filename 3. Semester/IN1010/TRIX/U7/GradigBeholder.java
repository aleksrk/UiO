class GradigBeholder<T extends Comparable<T>>{

    T mittElement;

    public T settInn(T element) {

        if(mittElement == null) {
            this.mittElement = element;
            return null;
        } else if (element.compareTo(mittElement) >= 0) {
            T midlertidig = mittElement;
            mittElement = element;
            return midlertidig;
        } else {
            return null;
        }
    }

    public T returnere() {
        return mittElement;
    }
}
