class Bil3 {

    private String bilNummer;

    public Bil3 (String s) {
      bilNummer = s;
    }

    public String hentNummer() {
      return bilNummer;
    }

    public void hentInfo() {
      System.out.println(bilNummer);
    }
}
