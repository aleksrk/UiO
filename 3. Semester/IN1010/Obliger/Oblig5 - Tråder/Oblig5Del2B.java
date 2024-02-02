import java.util.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;

public class Oblig5Del2B {

    public static void main(String []args) throws InterruptedException {

        Monitor2 monitor = new Monitor2();

        File fil = new File(args[0] + "/metadata.csv");

        ArrayList<Thread> listeovertrad = new ArrayList<Thread>();

        try {

            Scanner sc = new Scanner(fil);
            while (sc.hasNextLine()) {
                //HashMap<String,Subsekvens> nyttMap = mittRegister.lesFraFil(new File(args[0] + "/" + sc.nextLine()));
                //monitor.leggTil(nyttMap);
                LeseTrad lese = new LeseTrad(new File(args[0] + "/" + sc.nextLine()), monitor);
                Thread trad = new Thread(lese);
                listeovertrad.add(trad);
                trad.start();
            }

        } catch (FileNotFoundException e) {
            System.out.println("oops");
        }

        try {
            for (Thread t : listeovertrad) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main forstyrret mens den ventet");
            return;
        }

        /*while(monitor.antallMaps() > 1) {
            HashMap<String,Subsekvens> mapEn = monitor.taUt(1);
            HashMap<String,Subsekvens> mapTo = monitor.taUt(0);
            HashMap<String,Subsekvens> nyttMap = monitor.hashFletting(mapEn, mapTo);
            monitor.leggTil(nyttMap);
        }*/
        System.out.println(monitor.antallMaps());
        ArrayList<Thread> listeoverflettetrad = new ArrayList<Thread>();
        CountDownLatch doneSignal = new CountDownLatch(monitor.antallMaps()-1);

        for(int i = 0; i < 8; i++) {

            FletteTrad flett = new FletteTrad(monitor, doneSignal);
            Thread fletteTrad = new Thread(flett);
            listeoverflettetrad.add(fletteTrad);
            fletteTrad.start();

        }

        try {
            for (Thread t : listeoverflettetrad) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main forstyrret mens den ventet");
            return;
        }


        doneSignal.await();


        HashMap<String,Subsekvens> map = monitor.taUt(0);

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
