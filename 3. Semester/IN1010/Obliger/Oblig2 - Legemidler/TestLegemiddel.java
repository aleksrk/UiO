public class TestLegemiddel {

    public static void main(String[] args) {


        Vanlig paracet = new Vanlig("Paracet", 19, 20.6);
        Narkotisk ritalin = new Narkotisk("Ritalin", 179, 20.0, 9);
        Vanedannende naproxen = new Vanedannende("Naproxen", 229, 40.0, 6);


        //test paracet
        System.out.println("Kjører test av legemiddelet paracet: ");
        kjorTest(paracet, 1, "Paracet", 19, 20.6, 26);

        //test ritalin
        System.out.println("Kjører test av legemiddelet ritalin: ");
        kjorTest(ritalin, 1, "Ritalin", 179, 20.0, 279);
        testNarkotiskStyrke(ritalin, 9);

        //test naproxen
        System.out.println("Kjører test av legemiddelet naproxen: ");
        kjorTest(naproxen, 1, "Naproxen", 229, 40.0, 169);
        testVanedannendeStyrke(naproxen, 6);

    }

    public static void testId(Legemiddel legemiddel, int forventetId) {
        if (legemiddel.hentId() == forventetId) {
            System.out.println("Test ID: Riktig");
        } else {
            System.out.println("Test ID: Feilet");
        }
    }

    public static void testNavn(Legemiddel legemiddel, String forventetNavn) {
        if (legemiddel.hentNavn().equals(forventetNavn)) {
            System.out.println("Test Navn: Riktig");
        } else {
            System.out.println("Test Navn: Feilet");
        }
    }

    public static void testPris(Legemiddel legemiddel, int forventetPris) {
        if (legemiddel.hentPris() == forventetPris) {
            System.out.println("Test Pris: Riktig");
        } else {
            System.out.println("Test Pris: Feilet");
        }
    }

    public static void testVirkestoff(Legemiddel legemiddel, double forventetVirkestoff) {
        if (legemiddel.hentVirkestoff() == forventetVirkestoff) {
            System.out.println("Test Virkestoff: Riktig");
        } else {
            System.out.println("Test Virkestoff: Feilet");
        }
    }

    public static void testNyPris(Legemiddel legemiddel, int nypris) {
        legemiddel.settNyPris(nypris);
        if (legemiddel.hentPris() == nypris) {
            System.out.println("Test Nypris: Riktig");
        } else {
            System.out.println("Test Nypris: Feilet");
        }
    }

    public static void testNarkotiskStyrke(Narkotisk legemiddel, int forventetStyrke) {
        if (legemiddel.hentNarkotiskStyrke() == forventetStyrke) {
            System.out.println("Test Narkotisk Styrke: Riktig");
        } else {
            System.out.println("Test Narkotisk Styrke: Feilet");
        }
    }

    public static void testVanedannendeStyrke(Vanedannende legemiddel, int forventetStyrke) {
        if (legemiddel.hentVanedannendeStyrke() == forventetStyrke) {
            System.out.println("Test Vanedannende Styrke: Riktig");
        } else {
            System.out.println("Test Vanedannende Styrke: Feilet");
        }
    }
    public static void kjorTest(Legemiddel legemiddel, int id, String navn, int pris, double virkestoff, int nypris) {
        testId(legemiddel, id);
        testNavn(legemiddel, navn);
        testPris(legemiddel, pris);
        testVirkestoff(legemiddel, virkestoff);
        testNyPris(legemiddel, nypris);
    }
}
