import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;

public class SubsekvensRegister {

    public List<HashMap<String,Subsekvens>> hashBeholder = new ArrayList<HashMap<String,Subsekvens>>();
    private Lock laas = new ReentrantLock();

    public void leggTil(HashMap<String,Subsekvens> map) {
        hashBeholder.add(map);

    }

    public HashMap<String,Subsekvens> taUt(int index) {

        /*try {
            while(hashBeholder.size() == 0) {
                laas.lock();
            }
        } finally {
            laas.unlock();
        }*/
        return hashBeholder.remove(index);


    }

    public int antallMaps() {
        return hashBeholder.size();
    }

    public static HashMap<String,Subsekvens> lesFraFil(File fil) {

        HashMap<String,Subsekvens> nyttMap = new HashMap<String,Subsekvens>();


        try {

            Scanner sc = new Scanner(fil);

            while (sc.hasNextLine()) {

                String linje = sc.nextLine();

                if (linje.length() < 3) {
                    System.out.println("Det er noe alvorlig galt med filen din");
                    System.exit(0);
                }

                Set<String> subsekvenser = new HashSet<String>();

                for (int i = 0; i < linje.length() - 2; i++) {
                    subsekvenser.add(""+linje.charAt(i)+linje.charAt(i+1)+linje.charAt(i+2));
                }

                for (String e : subsekvenser) {
                    Subsekvens nySekvens = new Subsekvens(e, 1);
                    nyttMap.put(e, nySekvens);
                }


            }

        } catch (FileNotFoundException e) {
            System.out.println("File finnes ikke!");
        }

        return nyttMap;
    }

    public static HashMap<String,Subsekvens> hashFletting(HashMap<String,Subsekvens> mapEn, HashMap<String,Subsekvens> mapTo) {

        HashMap<String,Subsekvens> nyttMap = mapEn;

        for (String key : mapTo.keySet()) {

            if (!nyttMap.containsKey(key)) {

                nyttMap.put(key, mapTo.get(key));

            } else {

                Subsekvens nySub = nyttMap.get(key);
                Subsekvens gammelSub = mapTo.get(key);
                int nySubAntall = nySub.hentForekomster();
                int gammelSubAntall = gammelSub.hentForekomster();
                nySub.settForekomster(nySubAntall + gammelSubAntall);
                nyttMap.put(key, nySub);

            }
        }

        return nyttMap;




    }


}
