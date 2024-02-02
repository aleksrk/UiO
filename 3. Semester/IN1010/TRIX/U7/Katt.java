class Katt extends Comparable<Katt>{

    private String navn;
    private int alder;

    public Katt(String navn, int alder) {
        this.navn = navn;
        this.alder = alder;
    }

    @Overrides
    public int compareTo(Katt k) {
        if (this.alder < k.alder) {
            return -1;
        } else if (this.alder > k.alder) {
            return 1;
        } else {
            return 0;
        }
    }

    @Overrides
    public String toString(){
        System.out.println("Navn: " + navn + " Alder " + alder);
    }
}
