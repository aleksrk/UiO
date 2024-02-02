class EspressoMaskin {
    private int vannmengde = 0;


    // Lag espresso dersom det er nok vann
    public void lagEspresso() {
      if (vannmengde >= 40) {
        System.out.println("Her er din Espresso");
        vannmengde -= 40;
        System.out.println("Det er ikke nok vann til å lage en Espresso!");
      }
    }

    // Lag lungo dersom det er nok vann
    public void lagLungo() {
      if (vannmengde >= 110) {
        System.out.println("Her er din Lungo");
        vannmengde -= 110;
      } else {
        System.out.println("Det er ikke nok vann til å lage en lungo!");
      }
    }

    // Fyll på et gitt antall milliliter vann, dersom det er plass
    public void fyllVann(int ml) {
      vannmengde += ml;
      if (vannmengde > 1000) {
        vannmengde = 1000;
        System.out.println("Det er ikke så mye plass i maskinen! " + vannmengde - 1000 + "ml har forduftet");
      }
    }

    // Les av målestrekene på vanntanken og tilgjengelig vann i ml
    public int hentVannmengde() {
      return vannmengde;
    }
}

class TestEspressoMaskin {
  public static void main(String[] args) {
    EspressoMaskin maskin = new EspressoMaskin();
    maskin.fyllVann(1100);
    maskin.lagLungo();
    maskin.lagEspresso();
    System.out.println(maskin.hentVannmengde());
  }
}
