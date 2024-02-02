import java.util.*;
import java.io.*;

public class Oblig5Del1 {

    public static void main(String []args) {

        SubsekvensRegister mittRegister = new SubsekvensRegister();

        File fil = new File(args[0] + "/metadata.csv");

        try {

            Scanner sc = new Scanner(fil);
            while (sc.hasNextLine()) {
                HashMap<String,Subsekvens> nyttMap = mittRegister.lesFraFil(new File(args[0] + "/" + sc.nextLine()));
                mittRegister.leggTil(nyttMap);
            }

        } catch (FileNotFoundException e) {
            System.out.println("oops");
        }

        while(mittRegister.antallMaps() > 1) {
            HashMap<String,Subsekvens> mapEn = mittRegister.taUt(1);
            HashMap<String,Subsekvens> mapTo = mittRegister.taUt(0);
            HashMap<String,Subsekvens> nyttMap = mittRegister.hashFletting(mapEn, mapTo);
            mittRegister.leggTil(nyttMap);
        }

        HashMap<String,Subsekvens> map = mittRegister.taUt(0);

        String flestforekomster = "";
        int forekomster = 0;

        for (Map.Entry<String, Subsekvens> entry : map.entrySet()) {

            if (entry.getValue().hentForekomster() > forekomster) {
                forekomster = entry.getValue().hentForekomster();
                flestforekomster = entry.getKey();
            }
        }

        System.out.println(flestforekomster + forekomster);

    }
}
