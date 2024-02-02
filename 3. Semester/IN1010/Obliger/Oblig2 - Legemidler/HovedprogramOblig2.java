public class Hovedprogram {

    public static void main(String[] args) {

        Lege gunnar = new Lege("Gunnar");
        Spesialist arne = new Spesialist("Arne", "4728");

        Vanlig paracet = new Vanlig("Paracet", 20, 20.6);
        Narkotisk ritalin = new Narkotisk("Ritalin", 179, 20.0, 9);
        Vanedannende naproxen = new Vanedannende("Naproxen", 229, 40.0, 6);

        BlaResept resept1 = new BlaResept(paracet, gunnar, 240996, 4);
        HvitResept resept2 = new HvitResept(paracet, gunnar, 240996, 1);
        PResept resept3 = new PResept(paracet, gunnar, 240996, 4);
        MilResept resept4 = new MilResept(ritalin, arne, 240996);

        System.out.println("Informasjon om legen Gunnar: " + gunnar.toString());
        System.out.println("Informasjon om legen Arne: " + arne.toString());

        System.out.println("Informasjon om legemiddelet Paracet: " + paracet.toString());
        System.out.println("Informasjon om legemiddelet Ritalin: " + ritalin.toString());
        System.out.println("Informasjon om legemiddelet Naproxen: " + naproxen.toString());
        System.out.println("Informasjon om resept1: " + resept1.toString());
        System.out.println("Informasjon om resept2: " + resept2.toString());
        System.out.println("Informasjon om resept3: " + resept3.toString());
        System.out.println("Informasjon om resept4: " + resept4.toString());



    }

}
