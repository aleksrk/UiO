import java.util.ArrayList;

class TestFigur {
    public static void main(String[] args) {
        ArrayList<Figur> liste = new ArrayList<Figur>();
        liste.add(new Kvadrat(4));
        liste.add(new Trekant(3,2));
        liste.add(new Sirkel(3));
        liste.add(new Rektangel(3,7));
        double areal = 0;
        for (Figur figur : liste) {
            areal += figur.areal();
        }
        System.out.println(areal);
    }
}
