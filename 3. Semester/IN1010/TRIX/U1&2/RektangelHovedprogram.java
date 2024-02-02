class Rektangel {
      double bredde;
      double lengde;
  public Rektangel (double l, double b) {   // Konstruktør
      lengde = l;
      bredde = b;
  }

  public void oekLengde (int okning) {    // Oek lengden som angitt
      lengde += okning;
  }

  public void oekBredde (int okning) {    // Oek bredden som angitt
      bredde += okning;
  }

  public double areal () {     // Beregn mitt areal
      return lengde*bredde;
  }

  public double omkrets () {   // Beregn min omkrets
      return lengde+lengde+bredde+bredde;
  }
  public void reduserLengde(int reduser) { // Reduser lengden som angitt
      if (lengde - reduser < 1){
        System.out.println("Lengden kan ikke være mindre enn null!")
      } else {
        lengde = lengde - reduser;
      }
  }
  public void reduserBredde(int reduser) { // Reduser bredden som angitt
      if (bredde - reduser < 1){
        System.out.println("Bredden kan ikke være mindre enn null!")
      } else {
        bredde = bredde - reduser;
      }
  }
}

class RektangelHovedprogram{
    public static void main(String [] args){
      Rektangel rek1 = new Rektangel(2,4);
      Rektangel rek2 = new Rektangel(6,8);
      System.out.println("Areal: " + rek1.areal());
      System.out.println("Areal: " +rek2.areal());
      rek1.oekBredde(2);
      rek2.oekLengde(4);
      System.out.println("Omkrets: " + rek1.omkrets());
      System.out.println("Omkrets: " + rek2.omkrets());
  }
}
