public class HashListe implements Hash, Iterable<Reise> {

    Node[] alleN;

    class Node {
        String nokkel;
        Reise reise;
        Node neste = null;

        Node (String nokkel, Reise reise) {
            this.nokkel = nokkel;
            this.reise = reise;
        }
    }

    public HashListe(int lengde) {
        alleN = new Node[lengde];
    }
    public void settInn(String nokkel, Reise reise){

    }
    public Reise hent(String nokkel){}
    public Reise fjern(String nokkel){}
    public int antall(){}
}
