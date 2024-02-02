import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;



public class Monitor1 {

    private static SubsekvensRegister register = new SubsekvensRegister();

    private Lock laas = new ReentrantLock();

    public void leggTil(HashMap<String,Subsekvens> map) {
        laas.lock();
        try {
            register.leggTil(map);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUt(int index) {
        return register.taUt(index);
    }

    public int antallMaps() {
        return register.antallMaps();
    }

    public static HashMap<String,Subsekvens> lesFraFil(File fil) {
        return register.lesFraFil(fil);
    }

    public static HashMap<String,Subsekvens> hashFletting(HashMap<String,Subsekvens> mapEn, HashMap<String,Subsekvens> mapTo) {
        return register.hashFletting(mapEn, mapTo);
    }

}
