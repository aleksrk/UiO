
class Person {
    private String navn;
    private int alder;
    private Person mor = null;
    private Person far = null;

    public Person(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }
    public Person(String navn, int alder, Person mor, Person far) {
        this.navn = navn;
        this.alder = alder;
        this.mor = mor;
        this.far = far;
    }

    public int hentAlder() {
        return this.alder;
    }

    public String hentNavn() {
        return this.navn;
    }
    @Override
    public String toString() {
        System.out.println(navn + "(" + alder + ")");
    }
}
