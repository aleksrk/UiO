import java.util.ArrayList;

class Ordbeholder {

    private ArrayList<String> ordbeholder = new ArrayList<>();

    public boolean settInn(String ord) {
        if (ordbeholder.contains(ord))
            return false;

        ordbeholder.add(ord);
        return true;
    }

    public int antallOrd() {
        return ordbeholder.size();
    }

    public String pop() {
        if (antallOrd() == 0)
            return null;

        return ordbeholder.remove(antallOrd() -1);
    }

}

public class OrdbeholderTest {

    public static void main(String[] args) {

        Ordbeholder testy = new Ordbeholder();
        String testOrd = "Kunt";

        if (testy.pop() == null) {
            System.out.println("Test 1 fungerer");
        } else {
            System.out.println("Test 1 fungerer IKKE");
        }

        if (testy.settInn(testOrd)) {
            System.out.println("Test 2 fungerer");
        } else {
            System.out.println("Test 2 fungerer IKKE");
        }

        if (!testy.settInn(testOrd)) {
            System.out.println("Test 3 fungerer");
        } else {
            System.out.println("Test 3 fungerer IKKE");
        }
        testy.settInn("ord to");
        testy.settInn("ord tre");
        if (testy.antallOrd() == 3) {
            System.out.println("Test 4 fungerer");
        } else {
            System.out.println("Test 4 fungerer IKKE");
        }

        if (testy.pop().equals("ord tre")) {
            System.out.println("Test 5 fungerer");
        } else {
            System.out.println("Test 5 fungerer IKKE");
        }

        if (testy.pop().equals("ord tre")) {
            System.out.println("Test 5 fungerer");
        } else {
            System.out.println("Test 5 fungerer IKKE");
        }

    }
}
