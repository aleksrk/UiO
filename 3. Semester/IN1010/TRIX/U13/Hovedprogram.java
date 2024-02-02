
class Hovedprogram {


    public static void main (String[] args) {
        rekursivUtskrift(Integer.parseInt(args[0]));
    }

    public static void rekursivUtskrift(int i) {
        if (i <= 10) {
            System.out.println(i);
            rekursivUtskrift(i+1);
        } else return;
    }
}
