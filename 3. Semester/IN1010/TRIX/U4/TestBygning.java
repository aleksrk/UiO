class Bygning {
    
    public void Bygning(double bruttoAreal) {
        System.out.println("Jeg er en bygning.");
    }
}

class Skyskraper extends Bygning {

    public void Skyskraper(double bruttoAreal, int antallEtasjer) {
        super();
        System.out.println("Jeg er en skyskraper.");
    }
}

class TestBygning {

    public static void main(String[] args) {
        Skyskraper skraper = new Skyskraper();

        /* Oppg a)
        Vi oppretter ett nytt objekt av typen Skyskraper.
        En subklasse vil alltid starte sin konstruktør med å kalle superklassens konstuktør først.
        Vi printer derfor først "Jeg er en bygning." også "Jeg er en skyskraper."
        */

        /* Oppg b)
        Her er det ingen forskjell fra tidligere.
        */
    }
}
