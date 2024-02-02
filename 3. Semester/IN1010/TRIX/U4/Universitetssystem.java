public abstract class Person {

    protected int alder;
    protected String navn;

    public void Person(int alder, String navn) {
        this.alder = alder;
        this.navn = navn;
    }

    public void skrivUt() {
        System.out.println("Navn: " + navn);
        System.out.println("Alder: " + alder);
    }
}

public abstract class Studenter {

    protected String kurs;
    protected String sted;

    public void Studenter(String kurs, String sted, int alder, String navn) {
        super(alder, navn);
        this.kurs = kurs;
        this.sted = sted;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Kurs: " + kurs);
        System.out.println("Sted: " + sted);
    }
}

public class BachelorStudenter {
    boolean tilgang;

    public void BachelorStudenter(boolean tilgang, String kurs, String sted, int alder, String navn) {
        super(kurs, sted, alder, navn);
        this.tilgang = tilgang;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Har Tilgang: " + tilgang);
    }
}

public class MasterStudenter {
    boolean tilgang;

    public void MasterStudenter(boolean tilgang, String kurs, String sted, int alder, String navn) {
        super(kurs, sted, alder, navn);
        this.tilgang = tilgang;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Har Tilgang: " + tilgang);
    }
}

public abstract class Ansatte {
    protected double timelonn;

    public void Ansatte(double timelonn, String navn, int alder) {
        super(navn, alder);
        this.timelonn = timelonn;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Timelønn: " + timelonn);
    }
}

public class Forskere {
    String fagomrode;

    public void Forskere(String fagomrode, double timelonn, String navn, int alder) {
        super(timelonn, navn, alder);
        this.fagomrode = fagomrode;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Fagområde: " + fagomrode);
    }
}

public class Professorer {
    String fagomrode;
    String kurs;

    public void Professorer(String fagomrode, String kurs, double timelonn, String navn, int alder) {
        super(timelonn, navn, alder);
        this.fagomrode = fagomrode;
        this.kurs = kurs;
    }

    public void skrivUt() {
        super.skrivUt();
        System.out.println("Fagområde: " + fagomrode);
        System.out.println("Kurs: " + kurs);
    }

}

public class Universitetssystem {

}
