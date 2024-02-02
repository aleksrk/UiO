import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Legesystem {

    SortertLenkeliste<Lege> leger = new SortertLenkeliste<Lege>();
    IndeksertListe<Resept> resepter = new IndeksertListe<Resept>();
    IndeksertListe<Legemiddel> legemidler = new IndeksertListe<Legemiddel>();
    IndeksertListe<Pasient> pasienter = new IndeksertListe<Pasient>();

    private static int antallVanedannendeResepter = 0;
    private static int antallNarkotiskeResepter = 0;

    public void leggTilPasient(String navn, String foedselsnummer) {
        pasienter.leggTil(new Pasient(navn, foedselsnummer));
    }

    public void leggTilLegemiddel(String navn, String type, int pris, double virkestoff, int styrke) {

        if (type.equals("narkotisk")) {

            legemidler.leggTil(new Narkotisk(navn, pris, virkestoff, styrke));

        } else if (type.equals("vanedannende")) {

            legemidler.leggTil(new Vanedannende(navn, pris, virkestoff, styrke));

        } else if (type.equals("vanlig")) {

            legemidler.leggTil(new Vanlig(navn, pris, virkestoff));
        } else {
            System.out.println("Kunne ikke legge til legemiddel, typen må enten være, narkotisk, vanedannende, eller vanlig.");
        }

    }

    public void leggTilLege(String lege, String kontrollid) {
        if (kontrollid.equals("0")) {
            leger.leggTil(new Lege(lege));
        } else {
            leger.leggTil(new Spesialist(lege, kontrollid));
        }
    }

    public void skrivOversikt() {

        skrivResepter();
        skrivLeger();
        skrivLegemidler();
        skrivPasienter();

    }

    public void skrivStatistikk() {

        System.out.println("Totalt antall utksrevne resepter på vanedannende legemidler: " + antallVanedannendeResepter);
        System.out.println("Totalt antall utksrevne resepter på narkotiske legemidler: " + antallNarkotiskeResepter);

        for (Lege l : leger) {
            int ant = 0;
            for (Resept r : l.utskrevneResepter) {
                if (r.legemiddel instanceof Narkotisk) {
                    ant++;
                }
            }
            if (ant != 0) {
                System.out.println(l.hentNavn() + " har skrevet ut " + ant + " antall resepter på narkotiske legemidler.");
            }
        }

        for (Pasient p : pasienter) {
            int ant2 = 0;
            for (Resept r : p.resepter) {
                if (r.legemiddel instanceof Narkotisk) {
                    ant2++;
                }
            }
            if (ant2 != 0) {
                System.out.println(p.hentNavn() + " har " + ant2 + " antall resepter på narkotiske legemidler.");
            }
        }
    }

    public void skrivResepter() {
        System.out.println("Resepter:");
        for (Resept resept : resepter) {
            System.out.println(resept.toString());
            System.out.println("respet loop funker");
        }
    }

    public void skrivLeger() {
        System.out.println("Leger");
        for (Lege lege : leger) {
            System.out.println(lege.toString());
        }
    }

    public void skrivLegemidler() {
        System.out.println("Legemidler");
        for (Legemiddel legemiddel : legemidler) {
            System.out.println(legemiddel.toString());
        }
    }

    public void skrivPasienter() {
        System.out.println("Pasienter");
        for (Pasient pasient : pasienter) {
            System.out.println(pasient.toString());
        }
    }

    public void leggTilResept(int legemiddelNummer, String lege, int pasientID, String type, int reit) {
        Resept resepten = null;
        Lege legen = null;
        Legemiddel legemiddelet = null;
        Pasient pasienten = null;

        for (Lege l : leger) {
            if (lege.equals(l.hentNavn())) {
                legen = l;
                break;
            }
        }

        for (Legemiddel legemiddel : legemidler) {
            if (legemiddelNummer == legemiddel.hentId()) {
                legemiddelet = legemiddel;
                break;
            }
        }

        for (Pasient pasient : pasienter) {
            if (pasientID == pasient.hentID()) {
                pasienten = pasient;
                break;
            }
        }
        try {
            resepter.leggTil(legen.skrivResept(legemiddelet, pasienten, reit));
            if (legemiddelet instanceof Vanedannende) {
                antallVanedannendeResepter++;
            } else if (legemiddelet instanceof Narkotisk) {
                antallNarkotiskeResepter++;
            }
        } catch (UlovligUtskrift e) {
            System.out.println("Dette gikk itte.");
        }

    }


    public void lesFraFil(String s) throws FileNotFoundException {
        File fil = new File(s);
        Scanner les;
        try {
            les = new Scanner(fil);
        } catch (FileNotFoundException e) {
            System.out.println("Denne filen eksisterer ikke.");
            throw e;
        }
        String kategori = "";

        while(les.hasNextLine()) {

            String lest = les.nextLine();

            if (lest.charAt(0) == '#') {
                kategori = lest.split(" ")[1];
                continue;
            }

            String[] entry = lest.split(",");

            if (kategori.equals("Pasienter")) {
                if (entry.length == 2) {
                    leggTilPasient(entry[0], entry[1]);
                } else {
                    System.out.println("Ugyldig format på input fra fil!");
                }

            } else if (kategori.equals("Legemidler")) {
                if (entry.length == 5 || entry.length == 4) {
                    if (entry[1].equals("narkotisk")) {

                        leggTilLegemiddel(entry[0], entry[1], Integer.parseInt(entry[2]), Double.parseDouble(entry[3]), Integer.parseInt(entry[4]));

                    } else if (entry[1].equals("vanedannende")) {

                        leggTilLegemiddel(entry[0], entry[1], Integer.parseInt(entry[2]), Double.parseDouble(entry[3]), Integer.parseInt(entry[4]));

                    } else {

                        leggTilLegemiddel(entry[0], entry[1], Integer.parseInt(entry[2]), Integer.parseInt(entry[3]), 0);
                    }
                } else {
                    System.out.println("Ugyldig format på input fra fil!");
                }


            } else if (kategori.equals("Leger")) {
                if (entry.length == 2) {
                        leggTilLege(entry[0], entry[1]);
                } else {
                    System.out.println("Ugyldig format på input fra fil!");
                }

            } else {
                if (entry.length == 5 || entry.length == 4) {

                    if (entry[3].equals("militaer")) {
                            leggTilResept(Integer.parseInt(entry[0]), entry[1], Integer.parseInt(entry[2]), entry[3], 3);
                    } else {
                            leggTilResept(Integer.parseInt(entry[0]), entry[1], Integer.parseInt(entry[2]), entry[3], Integer.parseInt(entry[4]));
                    }
                } else {
                    System.out.println("Ugyldig format på input fra fil!");
                }

            }
        }




    }
}
