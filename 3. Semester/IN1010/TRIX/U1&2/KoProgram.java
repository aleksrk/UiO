import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class KoSystem {

    private List<KoLapp> koListe = new ArrayList<KoLapp>();
    private KoLapp lapp;
    private int antKunder;

    //Lager et nytt objekt av KoLapp, printer ut informasjon om den og legger den til i listen over koLapper.
    public void trekkKoLapp(){
      KoLapp lapp = new KoLapp(antKunder+1);
      koListe.add(lapp);
      System.out.println("Du er nummer: " + lapp.hentNummer() + " i køen.");
      antKunder++;
    }

    //Henter ut, printer ut informasjon og fjerner den forste kolappen i lista. Gir feilmelding dersom ingen kunder staar i ko.
    public void betjenKunde(){
      if (antKunder>0) {
      KoLapp kunde = koListe.get(0);
      System.out.println("Vi betjener nå kunde med kølapp nummer " + kunde.hentNummer());
      koListe.remove(0);
      antKunder--;
      } else {
      System.out.println("Det er ingen kunder i kø for øyeblikket.");
      }
    }

    //Returnerer antall kunder som er i ko.
    public int antKunder(){
      return antKunder;
    }

    //Printer alle kunder i ko
    public void printKunderIKo(){
      for (KoLapp lapp : koListe) {
        System.out.println(lapp.hentNummer());
      }
    }
}

class KoLapp {

    private int koNummer;

    //Konstruktor
    public KoLapp(int nummer){
      koNummer = nummer;
    }

    //Returnerer et tildelt nummer paa kolappen.
    public int hentNummer(){
      return koNummer;
    }
}

class KoProgram {

  public static void main(String[] args) {
    int status = 0;
    Scanner in = new Scanner(System.in);

    KoSystem system  = new KoSystem();

    while (status != 4) {

      System.out.println("1 - Trekk ny kolapp.");
      System.out.println("2 - Betjen kunde.");
      System.out.println("3 - Print antall kunder i kø.");
      System.out.println("4 - Avslutt.");
      status = Integer.parseInt(in.nextLine());
      if (status == 1) {
        system.trekkKoLapp();
      } else if (status == 2) {
        system.betjenKunde();
      } else if (status == 3) {
        system.printKunderIKo();
      } else if (status == 4) {
      } else {
        System.out.println("Det er ikke ett gyldig menyvalg");
        status = 0;
      }
    }

  }
}
