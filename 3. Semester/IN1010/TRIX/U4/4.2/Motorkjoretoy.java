import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Motorkjoretoy {

    public static Scanner inn = new Scanner(System.in);
    public static ArrayList<Kjoretoy> data = new ArrayList<Kjoretoy>();

    public static void main(String [] args) {

        int index = 0;
        String biltype;
        String regnr;
        String merke;
        String eier;
        int antallPlasser;
        int max_vekt;
        double total = 0;

        //meny
        System.out.println("Hei og velkommen til Mujafas Car storage, du har nå følgende valg: ");
        while (index != 4) {
            System.out.println("Tast 1 for å registrere nytt kjøretøy");
            System.out.println("Tast 2 for å søke opp informasjon om ett kjøretøy");
            System.out.println("Tast 3 for å se årsavgift på dine kjøretøy");
            System.out.println("Tast 4 for å avslutte programmet");
            index = inn.nextInt();
            if (index == 1) {
                System.out.println("Skriv inn kjøretøyet du vil registrere(Buss, Varebil eller Personbil)");
                biltype = inn.next();
                if (biltype.equals("Buss")) {

                    System.out.println("Skriv inn regnr: ");
                    regnr = inn.next();
                    System.out.println("Skriv inn merke: ");
                    merke = inn.next();
                    System.out.println("Skriv inn eier: ");
                    eier = inn.next();
                    System.out.println("Skriv inn antall sitteplasser: ");
                    antallPlasser = inn.nextInt();
                    data.add(new Buss(regnr, merke, eier, antallPlasser));
                    System.out.println("Kjøretøyet er registrert!");

                } else if (biltype.equals("Varebil")) {

                    System.out.println("Skriv inn regnr: ");
                    regnr = inn.next();
                    System.out.println("Skriv inn merke: ");
                    merke = inn.next();
                    System.out.println("Skriv inn eier: ");
                    eier = inn.next();
                    System.out.println("Skriv inn antall sitteplasser: ");
                    antallPlasser = inn.nextInt();
                    data.add(new Varebil(regnr, merke, eier, antallPlasser));

                } else if (biltype.equals("Personbil")) {

                    System.out.println("Skriv inn regnr: ");
                    regnr = inn.next();
                    System.out.println("Skriv inn merke: ");
                    merke = inn.next();
                    System.out.println("Skriv inn eier: ");
                    eier = inn.next();
                    System.out.println("Skriv inn maksimal vekt: ");
                    max_vekt = inn.nextInt();
                    data.add(new Personbil(regnr, merke, eier, max_vekt));

                } else {
                    System.out.println("Vennligst skriv enten Buss, Varebil, eller Personbil");
                }
            } else if (index == 2) {
                System.out.println("Skriv inn regnr: ");
                regnr = inn.next();
                for (Kjoretoy kjoretoy : data) {
                    if (kjoretoy.hentRegnr().equals(regnr)) {
                        kjoretoy.skrivUt();
                        break;
                    }
                    System.out.println("Det kjøretøyet finnes ikke, tar deg tilbake til hovedmenyen");
                }
            } else if (index == 3) {
                System.out.println("Skriv inn navnet eller firmaet du vil finne avgiften til: ");
                eier = inn.next();
                for (Kjoretoy kjoretoy : data) {
                    if (kjoretoy.hentNavn().equals(eier) && kjoretoy instanceof Bil) {
                            total += ((Bil) kjoretoy).hentAvgift();
                    }
                }
                if (total == 0) {
                    System.out.println("Det er ingen eier ved det navnet, tar deg tilbake til hovedmenyen");
                    break;
                }
                System.out.println(total);
            } else if (index == 4){
            } else {
                System.out.println("Vennligst skriv ett tall mellom 1-4");
            }

        }




    }

}
