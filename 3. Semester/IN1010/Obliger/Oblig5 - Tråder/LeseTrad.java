import java.io.*;
import java.util.*;

public class LeseTrad implements Runnable {

    private File fil;
    public Monitor2 monitor;


    public LeseTrad(File fil, Monitor2 monitor) {
        this.fil = fil;
        this.monitor = monitor;
    }

    public void run() {

        HashMap<String,Subsekvens> nyttMap = monitor.lesFraFil(fil);
        monitor.leggTil(nyttMap);
    }
}
