import java.io.*;
import java.util.*;
import java.util.concurrent.CountDownLatch;

public class FletteTrad implements Runnable {

    public Monitor2 monitor;
    public CountDownLatch doneSignal;

    public FletteTrad(Monitor2 monitor, CountDownLatch doneSignal) {
        this.monitor = monitor;
        this.doneSignal = doneSignal;
    }

    public void run() {
        while(monitor.antallMaps()>1) {
            try {
                ArrayList<HashMap<String,Subsekvens>> toMaps = monitor.taUtToMaps();
                HashMap<String,Subsekvens> nyttMap = monitor.hashFletting(toMaps.get(0), toMaps.get(1));
                monitor.leggTil(nyttMap);
                doneSignal.countDown();
            } catch (InterruptedException e) {
                System.out.println("noe gikk galt");
                return;
            }
        }

    }
}
