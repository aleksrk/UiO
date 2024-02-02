
class GCDKalkulator {

    public static void main(String[] args) {
        System.out.println(gcd(12,2));
        System.out.println(gcd(12,5));
        System.out.println(gcdIterativ(12,2));
        System.out.println(gcdIterativ(12,5));
    }

    static int gcd(int a, int b) {
        int temp;
        int c;
        if (!(a >= b)) {
            temp = a;
            a = b;
            b = temp;
        }

        c = a % b;

        if (c == 0) {
            return b;
        } else {
            a = b;
            b = c;
            gcd(a, b);
        }
        return b;

    }

    static int gcdIterativ(int a, int b) {
        int temp;
        int c;
        if (!(a >= b)) {
            temp = a;
            a = b;
            b = temp;
        }

        c = a % b;

        while (c != 0) {
            a = b;
            b = c;
            c = a % b;
        }

        return b;
    }

}
