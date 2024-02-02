// En subsekvens skal vaere en del av et hashmap
class SubSekvens {
  private String subSekvens;
  // Det finnes alltid én av en substring hvis det opprettes en subsekvens
  private int antall = 1;

  // Konstruktoer for subsekvens.
  public SubSekvens(String subSekvens) {
    this.subSekvens = subSekvens;
  }
  // hent og sett metoder for subsekvens
  public String hentSubSekvens() {return subSekvens;}
  public void settAntall(int antall) {this.antall = antall;}
  public int hentAntall() {return antall;}
  public void leggTil(int ant) {antall+=ant;}
}
