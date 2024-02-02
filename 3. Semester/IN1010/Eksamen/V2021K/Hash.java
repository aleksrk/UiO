public interface Hash {
    public void settInn(String nokkel, Reise reise);
    public Reise hent(String nokkel);
    public Reise fjern(String nokkel);
    public int antall();
}
