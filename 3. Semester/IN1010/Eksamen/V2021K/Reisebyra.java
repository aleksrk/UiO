import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class Reisebyra {


    public void les(String file) {

        try {
            File fil = new File(file);
        } catch(FileNotFoundException e) {
            System.out.println("filen finnes ikke");
        }

        Scanner sc = new Scanner(file);
        String[] lest;

        while (sc.hasNextLine()) {
            lest = sc.nextLine().split(",");
            if (lest.length == 3) {
                Reise reise = new Reise(lest[0], Integer.parseInt(lest[1]), Integer.parseInt(lest[2]));
            } else if (lest.length == 4) {
                BareFly fly = new BareFly(lest[0], Integer.parseInt(lest[1]), Integer.parseInt(lest[2]));
            } else {
                AllInclusive allinc = new AllInclusive(lest[0], Integer.parseInt(lest[1]), Integer.parseInt(lest[2]), Integer.parseInt(lest[3], Integer.parseInt(lest[4])));
            }
        }

    }
}
