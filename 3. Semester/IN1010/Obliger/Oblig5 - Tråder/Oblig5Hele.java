import java.util.*;
import java.io.*;
import java.util.concurrent.CountDownLatch;



public class Oblig5Hele {

    public static void main(String[] args) throws InterruptedException {

        Monitor2 hattSykdom = new Monitor2();
        Monitor2 ikkeHattSykdom = new Monitor2();

        File fil = new File(args[0] + "/metadata.csv");

        ArrayList<Thread> listeovertrad = new ArrayList<Thread>();

        try {

            Scanner sc = new Scanner(fil);
            while (sc.hasNextLine()) {
                //HashMap<String,Subsekvens> nyttMap = mittRegister.lesFraFil(new File(args[0] + "/" + sc.nextLine()));
                //monitor.leggTil(nyttMap);
                String[] temp = sc.nextLine().split(",");
                if (temp[1].equals("True")) {
                    LeseTrad lese = new LeseTrad(new File(args[0] + "/" + temp[0]), hattSykdom);
                    Thread trad = new Thread(lese);
                    listeovertrad.add(trad);
                    trad.start();
                } else if (temp[1].equals("False")) {
                    LeseTrad lese = new LeseTrad(new File(args[0] + "/" + temp[0]), ikkeHattSykdom);
                    Thread trad = new Thread(lese);
                    listeovertrad.add(trad);
                    trad.start();
                }


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

        ArrayList<Thread> listeoverflettetrad = new ArrayList<Thread>();
        CountDownLatch doneSignalSyke = new CountDownLatch(hattSykdom.antallMaps() - 1);
        CountDownLatch doneSignalIkkeSyke = new CountDownLatch(ikkeHattSykdom.antallMaps() - 1);

        for(int i = 0; i < 8; i++) {

            FletteTrad flettSyke = new FletteTrad(hattSykdom, doneSignalSyke);
            Thread fletteTradSyke = new Thread(flettSyke);
            listeoverflettetrad.add(fletteTradSyke);
            fletteTradSyke.start();
        }

        for (int i = 0; i < 8; i++) {
            FletteTrad flettFriske = new FletteTrad(ikkeHattSykdom, doneSignalIkkeSyke);
            Thread fletteTradFriske = new Thread(flettFriske);
            listeoverflettetrad.add(fletteTradFriske);
            fletteTradFriske.start();
        }


        try {
            for (Thread t : listeoverflettetrad) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.out.println("Main forstyrret mens den ventet");
            return;
        }

        doneSignalSyke.await();
        doneSignalIkkeSyke.await();

        HashMap<String,Subsekvens> subHattSykdom = hattSykdom.taUt(0);
        HashMap<String,Subsekvens> subIkkeHattSykdom = ikkeHattSykdom.taUt(0);

        String flestforekomster = "";
        int forekomster = 0;

        for (Map.Entry<String,Subsekvens> entry : subHattSykdom.entrySet()) {
            String tempForekomst = entry.getKey();
            if(subIkkeHattSykdom.containsKey(tempForekomst)) {
                int tempForekomster = entry.getValue().hentForekomster();
                Subsekvens tempSub = subIkkeHattSykdom.get(tempForekomst);
                int differanse = tempForekomster - tempSub.hentForekomster();
                if (differanse > forekomster) {
                    forekomster = differanse;
                    flestforekomster = tempForekomst;
                }

            } else {
                if (entry.getValue().hentForekomster() > forekomster) {
                    forekomster = entry.getValue().hentForekomster();
                    flestforekomster = entry.getKey();
                }
            }

        }
        System.out.println(flestforekomster + forekomster);

        flestforekomster = "";
        forekomster = 6;
        for (Map.Entry<String,Subsekvens> entry : subHattSykdom.entrySet()) {
            String tempForekomst = entry.getKey();
            if(subIkkeHattSykdom.containsKey(tempForekomst)) {
                int tempForekomster = entry.getValue().hentForekomster();
                Subsekvens tempSub = subIkkeHattSykdom.get(tempForekomst);
                int differanse = tempForekomster - tempSub.hentForekomster();
                if (differanse > forekomster) {
                    System.out.println(tempForekomst + differanse);
                }

            } else {
                if (entry.getValue().hentForekomster() > forekomster) {
                    System.out.println(tempForekomst + entry.getValue().hentForekomster());
                }
            }

        }


    }
}
