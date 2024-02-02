import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

class Hovedprogram {

    public static void main(String[] args) {
        try {
            File fil = new File("dataklynge4.txt");
            Scanner les = new Scanner(fil);

            Dataklynge saga = new Dataklynge();

            int antNoder;
            int antPro;
            int MinnePerNode;

            while (les.hasNextLine()) {
                String[] str = les.nextLine().split(" ");
                if (str.length != 3) {
                    System.out.println("you done fucked up");
                    return;
                }
                antNoder = Integer.parseInt(str[0]);
                antPro = Integer.parseInt(str[1]);
                MinnePerNode = Integer.parseInt(str[2]);

                for (int i = 0; i<antNoder; i++) {
                    Node node = new Node(antPro, MinnePerNode);
                    saga.addNode(node);
                }

            }

            System.out.println("Noder med minst 128 GB: " + saga.noderMedNokMinne(128));
            System.out.println("Noder med minst 512 GB: " + saga.noderMedNokMinne(512));
            System.out.println("Noder med minst 1024 GB: " + saga.noderMedNokMinne(1024));
            System.out.println("");
            System.out.println("Antall prosessorer: " + saga.antProsessorer());
            System.out.println("Antall rack:" + saga.antallRacks());
        } catch (FileNotFoundException e) {
            System.out.println("HAHA");
        }
    }
}
