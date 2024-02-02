public class Spesialist extends Lege implements Godkjenningsfritak {

    private String kontrollID;

    public Spesialist(String navn, String kontrollID) {
        super(navn);
        this.kontrollID = kontrollID;
    }

    public String hentKontrollID() {
        return kontrollID;
    }

    @Override
    public String toString() {
        return ("Navn: " + navn + " KontrollID " + kontrollID);
    }

}
