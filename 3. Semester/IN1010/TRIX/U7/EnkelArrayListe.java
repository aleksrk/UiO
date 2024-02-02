class EnkelArrayListe implements Iterator<T> {

    private String[] liste;
    private int index = 0;

    public EnkelArrayListe(int lengde) {
        liste = new String[lengde];
    }

    public void settInn(String neste) {
        liste[index] = neste;
        index++;
    }

    @Override
    public T next() {
        return liste[index];
    }

    public boolean hasNext() {
        
    }
}
