public class Stringhelper {
    public static int inneholder(String s, String t) {
        int pos = 0;
        for (int i = 0; i < s.length(); i++) {
            boolean eq = true;
            for (int j = 0; j < t.length(); j++) {
                if (s.charAt(i+j) != t.charAt(j)) {
                    eq = false; break;
                }
            }
            if (eq) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(inneholder("STEIN", "EI"));
    }
}
