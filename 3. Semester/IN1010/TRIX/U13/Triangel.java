
class Triangel {

    public static void main(String[] args) {
        triangel(3,5);
    }

    static void triangel(int m, int n) {
        String utskrift = "";
        for (int i = 0; i < m; i++) {
            utskrift+=("*");
        }
        System.out.println(utskrift);

        if (m == n) {
            System.out.println(utskrift);
            return;
        }
        triangel(m+1, n);
        System.out.println(utskrift);
    }
}
