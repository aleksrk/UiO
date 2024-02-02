public class TestResepter {


    public static void main(String[] args) {

        Vanlig paracet = new Vanlig("Paracet", 20, 20.6);
        Narkotisk ritalin = new Narkotisk("Ritalin", 179, 20.0, 9);
        Vanedannende naproxen = new Vanedannende("Naproxen", 229, 40.0, 6);
        Lege lege = new Lege("Arne");

        BlaResept blaa = new BlaResept(paracet, lege, 240996, 4);
        HvitResept hvit = new HvitResept(paracet, lege, 240996, 1);
        PResept presept = new PResept(paracet, lege, 240996, 4);
        MilResept milresept = new MilResept(paracet, lege, 240996);

        if (hvit.hentId() == 2) {
            System.out.println("Test av ID: Riktig");
        } else {
            System.out.println("Test av ID: Feilet");
        }

        if (hvit.hentLegemiddel() == paracet){
            System.out.println("Test av Legemiddel: Riktig");
        } else {
            System.out.println("Test av Legemiddel: Feilet");
        }

        if (hvit.hentLege() == lege) {
            System.out.println("Test av Lege: Riktig");
        } else {
            System.out.println("Test av Lege: Feilet");
        }

        if (hvit.hentPasientId() == 240996) {
            System.out.println("Test av PasientId: Riktig");
        } else {
            System.out.println("Test av PasientId: Feilet");
        }

        if (hvit.hentReit() == 1) {
            System.out.println("Test av Reit: Riktig");
        } else {
            System.out.println("Test av Reit: Feilet");
        }

        if (hvit.bruk()) {
            System.out.println("Test av bruk: Riktig");
        } else {
            System.out.println("Test av bruk: Riktig");
        }

        hvit.bruk();
        if (hvit.bruk()) {
            System.out.println("Test av bruk etter tom for resept: Feilet");
        } else {
            System.out.println("Test av bruk etter tom for resept: Riktig");
        }

        if (hvit.farge().equals("Hvit")) {
            System.out.println("Test av farge: Riktig");
        } else {
            System.out.println("Test av farge: Feilet");
        }

        if (hvit.prisAaBetale() == paracet.hentPris()) {
            System.out.println("Test av pris å betale: Riktig");
        } else {
            System.out.println("Test av pris å betale: Feilet");
        }

        if (presept.prisAaBetale() == 0) {
            System.out.println("Test av pris å betale på presept: Riktig");
        } else {
            System.out.println("Test av pris å betale på presept: Feilet");
        }

        if (milresept.prisAaBetale() == 0) {
            System.out.println("Test av pris å betale på milresept: Riktig");
        } else {
            System.out.println("Test av pris å betale på milresept: Feilet");
        }
        if (blaa.prisAaBetale() == 5) {
            System.out.println("Test av pris å betale på blåresept: Riktig");
        } else {
            System.out.println("Test av pris å betale på blåresept: Feilet");
        }

    }

}
