public class Pasient {

    private int id;
    private String navn;
    private String foedselsnummer;
    private static int idteller = 1;
    public IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();


    public Pasient(String navn, String foedselsnummer) {

        this.navn = navn;
        this.foedselsnummer = foedselsnummer;
        id = idteller;
        idteller++;

    }

    public void brukResept(int reseptid) {
        Resept resept = null;
        for (Resept r : resepter) {
            if(r.hentId() == reseptid) {
                resept = r;
            }
        }
        resept.bruk();
    }

    public void leggTilResept(Resept nyResept) {
        resepter.leggTil(nyResept);
    }

    public int hentID() {
        return id;
    }

    public String hentNavn() {
        return navn;
    }

    public void hentResepter() {
        for (Resept r : resepter) {
            System.out.println(r.toString());
        }
    }

    @Override
    public String toString() {
        return ("Navn: " + navn + " Fødselsnummer: " + foedselsnummer);
    }
}
