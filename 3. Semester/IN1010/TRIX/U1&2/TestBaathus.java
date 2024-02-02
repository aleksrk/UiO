class Baat {

    private static int antProd = 0;
    private int prodnr;
    private String merke;

    public Baat(String mrk) {
        prodnr = antProd;
        antProd++;
        merke = mrk;
    }

    public String hentInfo() {
        return "Produksjonsnummer: " + prodnr + ", merke: " + merke;
    }
}

class Baathus {

    private Baat[] baathus;

    public Baathus (int antallPlasser) { // Konstruktør definer antall plasser
        baathus = new Baat[antallPlasser];
    }

    public void settInn (Baat enBaat) { // Legg til en baat i lista
        boolean sattInn = false;
        int teller = 0;

        while (teller < baathus.length && sattInn == false){
            if (baathus[teller] == null) {
              baathus[teller] = enBaat;
              sattInn = true;
            }
            teller++;

        }
        if (sattInn != true){
            System.out.println("Det er ikke plass til flere baater!");
        }
    }

    public void skrivBaater() { // Skriver ut informasjon om baatene
      for(int i = 0; i < baathus.length; i++){
        if (baathus[i] != null) {
          System.out.println(baathus[i].hentInfo());
        }
      }
    }
}

class TestBaathus{

  public static void main (String [] args){
    Baathus baathus = new Baathus(3);

    Baat b1 = new Baat("Ferrari");
    Baat b2 = new Baat("Lamborghini");
    Baat b3 = new Baat("Mersha mi");
    Baat b4 = new Baat("hihi");

    baathus.settInn(b1);
    baathus.settInn(b2);
    baathus.settInn(b3);
    baathus.settInn(b4);

    baathus.skrivBaater();
  }
}
