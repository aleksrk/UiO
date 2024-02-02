import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Oblig6 {

    public static void main(String[] args) throws FileNotFoundException {

        File fil = new File(args[0]);
        Labyrint lab = new Labyrint(fil);
        Scanner sc = new Scanner(System.in);

        System.out.println("Slik ser labyrinten ut: \n");
        System.out.println(lab);

        int kjoerer = 1;
        String[] s;
        System.out.println("Skriv inn koordinater <rad> <kolonne> (-1 for å avslutte)");

        while (kjoerer != 0) {
            s = sc.nextLine().split(" ");
            if (s.length > 1) {
                System.out.println("Åpninger: ");
                lab.finnUtveiFra(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
                System.out.println("Skriv inn nye koordinater (-1 for å avslutte)");
            } else {
                System.out.println("Programmet avsluttes.");
                kjoerer = 0;
            }


        }

        //lab.finnUtveiFra(1,1);

    }
}
