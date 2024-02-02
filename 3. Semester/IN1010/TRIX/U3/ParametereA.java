public class ParametereA {
    public static void main(String[] args) {
        String a = "en tekst!";

        metodeEn(a); //fails here

        System.out.println(a);

    }

    public static void metodeEn(String a) {
        a = a + 12; //mismatching variable types
    }
}
