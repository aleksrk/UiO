import java.util.Scanner;
import java.io.FileNotFoundException;

public class Hovedprogram {

    public static void main(String[] args) {
        Legesystem legesystem  = new Legesystem();
        Scanner inn = new Scanner(System.in);
        int aktiv = 5;;

        try {
            legesystem.lesFraFil("legedata.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        }

        while (aktiv != 0) {

            System.out.println("Hovedmeny: ");
            System.out.println("1. Skriv ut oversikt over pasienter, leger, legemidler og resepter");
            System.out.println("2. Opprette legemiddel, lege, pasient eller resept");
            System.out.println("3. Bruke en resept");
            System.out.println("4. Skrive ut statistikk");
            System.out.println("Skriv et tall 1-4, eller skriv 0 for å avslutte programmet.");

            try {
                aktiv = Integer.parseInt(inn.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Vennligst skriv ett tall, 0-5.");
            }
            if (aktiv == 1) {
                legesystem.skrivOversikt();
            } else if (aktiv == 2) {
                int aktiv2 = 5;

                while (aktiv2 != 0) {

                    System.out.println("Menyen for å legge til nye objekter:");
                    System.out.println("1. Legg til legemiddel");
                    System.out.println("2. Legg til lege");
                    System.out.println("3. Legg til pasient");
                    System.out.println("4. Legg til resept");
                    System.out.println("Skriv ett tall 1-4, eller skriv 0 for å returnere til hovedmenyen");

                    try {
                        aktiv2 = Integer.parseInt(inn.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Vennligst skriv ett tall, 0-5.");
                    }
                    if (aktiv2 == 1) {

                        System.out.println("Skriv hvilket type legemiddel du ønsker å legge til ");
                        String type = inn.nextLine();
                        System.out.println("Skriv navn på legemiddelet");
                        String navn = inn.nextLine();
                        System.out.println("Skriv prisen til legemiddelet");
                        int pris = Integer.parseInt(inn.nextLine());
                        System.out.println("Skriv styrken på virkestoffet til legmiddelet");
                        double virkestoff = Double.parseDouble(inn.nextLine());
                        int styrke;

                        if (type.equals("vanlig")) {
                            styrke = 0;
                        } else {
                            System.out.println("Skriv styrken til legemiddelet");
                            styrke = Integer.parseInt(inn.nextLine());
                        }

                        legesystem.leggTilLegemiddel(navn, type, pris, virkestoff, styrke);
                        System.out.println(navn + "er lagt til.");

                    } else if (aktiv2 == 2) {

                        System.out.println("Skriv legens navn");
                        String navn = inn.nextLine();
                        System.out.println("Skriv legens kontrollid, 0 dersom den ikke har noen");
                        String kontrollid = inn.nextLine();

                        legesystem.leggTilLege(navn, kontrollid);

                        System.out.println(navn + "er lagt til.");

                    } else if (aktiv2 == 3) {

                        System.out.println("Skriv inn pasientens navn");
                        String navn = inn.nextLine();
                        System.out.println("Skriv inn pasientens fødselsnummer");
                        String foedselsnummer = inn.nextLine();

                        legesystem.leggTilPasient(navn, foedselsnummer);
                        System.out.println(navn + " med fødselsnummer: " + foedselsnummer + " er lagt til.");
                        aktiv2 = 1;

                    } else if (aktiv2 == 4) {

                        boolean legeFinnes = false;
                        boolean legemiddelFinnes = false;
                        boolean pasientFinnes = false;

                        legesystem.skrivLeger();
                        System.out.println("Skriv den utskrivende legen for resepten");
                        String lege = inn.nextLine();

                        while (!legeFinnes) {
                            for (Lege l : legesystem.leger) {
                                if (l.hentNavn().equals(lege)) {
                                    legeFinnes = true;
                                    break;
                                }
                            }
                            if (!legeFinnes) {
                                System.out.println("Denne legen finnes ikke!");
                                System.out.println("Skriv inn lege navnet på nytt");

                                lege = inn.nextLine();
                            }
                        }

                        for (Legemiddel l : legesystem.legemidler) {
                            System.out.println(l.hentId() + ". " + l.hentNavn());
                        }

                        System.out.println("Skriv tallet til legemiddelet du ønsker å legge til resepten");
                        int id = Integer.parseInt(inn.nextLine());

                        while (!legemiddelFinnes) {
                            for (Legemiddel l : legesystem.legemidler) {
                                if (l.hentId() == id) {
                                    legemiddelFinnes = true;
                                    break;
                                }
                            }
                            if (!legemiddelFinnes) {
                                System.out.println("Dette legemiddelet finnes ikke, skriv ett tall fra listen over");
                                id = Integer.parseInt(inn.nextLine());
                            }
                        }

                        for (Pasient p : legesystem.pasienter) {
                            System.out.println(p.hentID() + ". " + p.hentNavn());
                        }

                        System.out.println("Skriv id til pasienten du ønsker å legge resepten til");
                        int pasientid = Integer.parseInt(inn.nextLine());

                        while (!pasientFinnes) {
                            for (Pasient p : legesystem.pasienter) {
                                if (p.hentID() == pasientid) {
                                    pasientFinnes = true;
                                    break;
                                }
                            }
                            if (!pasientFinnes) {
                                System.out.println("Denne pasienten finnes ikke, skriv en id fra listen over");
                                pasientid = Integer.parseInt(inn.nextLine());
                            }
                        }

                        System.out.println("På dette apoteket skriver vi kun ut hvite resepter, vennligst skriv inn hvor mange reit resepten skal ha");
                        int reit = Integer.parseInt(inn.nextLine());

                        legesystem.leggTilResept(id, lege, pasientid, "hvit", reit);


                    } else if (aktiv2 < 1 || aktiv2 > 4) {
                        System.out.println("Dette tallet er ikke mellom 1 og 4, tar deg tilbake til hovedmenyen.");
                        aktiv2 = 0;
                    } else {
                        aktiv = 5;
                        aktiv2 = 0;
                    }
                }
            } else if (aktiv == 3) {

                boolean pasientFinnesR = false;
                Pasient pasient = null;

                for (Pasient p : legesystem.pasienter) {
                    System.out.println(p.hentID() + ". " + p.hentNavn());
                }

                System.out.println("Skriv id til pasienten du ønsker å bruke en resept fra");
                int pasientidR = Integer.parseInt(inn.nextLine());

                while (!pasientFinnesR) {
                    for (Pasient p : legesystem.pasienter) {
                        if (p.hentID() == pasientidR) {
                            pasientFinnesR = true;
                            pasient = p;
                            break;
                        }
                    }
                    if (!pasientFinnesR) {
                        System.out.println("Denne pasienten finnes ikke, skriv en id fra listen over");
                        pasientidR = Integer.parseInt(inn.nextLine());
                    }
                }

                boolean reseptFinnesR = false;
                pasient.hentResepter();

                System.out.println("Skriv inn id på resepten du ønsker å bruke");
                int reseptidR = Integer.parseInt(inn.nextLine());

                while (!reseptFinnesR) {
                    for (Resept r : pasient.resepter) {
                        if (r.hentId() == reseptidR) {
                            reseptFinnesR = true;
                            break;
                        }
                    }
                    if (!reseptFinnesR) {
                    System.out.println("Denne resepten finnes ikke, skriv en id fra listen over");
                    reseptidR = Integer.parseInt(inn.nextLine());
                    }

                }

                pasient.brukResept(reseptidR);



            } else if (aktiv == 4) {
                legesystem.skrivStatistikk();
            } else if (aktiv == 0) {
                aktiv = 0;
            }
        }
    }
}
