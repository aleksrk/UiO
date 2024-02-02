class Mult {
        static int tall1;
        public static void main (String [ ] param) {

            int tall2;
            tall1= Integer.parseInt(param[0]);
            tall2= Integer.parseInt(param[1]);
            System.out.println ("Svar: " + tall1 * tall2);
        }
}


class SumHeltall {
    public static void main(String[] args) {
        int a;
        int b;

        a = 4;
        b = 5;

        System.out.println("Summen er: " + (a + b));
    }
}

class Utskrift {
    public static void main(String [] args) {
        System.out.println("Beethoven skrev Skjebnesymfonien");
        System.out.println("og åtte andre symfonier");
    }
}
