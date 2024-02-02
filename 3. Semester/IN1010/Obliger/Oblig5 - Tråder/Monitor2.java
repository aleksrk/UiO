import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.io.*;



public class Monitor2 {

    SubsekvensRegister register = new SubsekvensRegister();


    private Lock laas = new ReentrantLock();
    Condition lengdeIkkeTo = laas.newCondition();

    public void leggTil(HashMap<String,Subsekvens> map) {
        laas.lock();
        try {
            register.leggTil(map);
        } finally {
            laas.unlock();
        }
    }

    public HashMap<String,Subsekvens> taUt(int index) {
        HashMap<String,Subsekvens> returmap;
        laas.lock();
        try {
            returmap = register.taUt(index);
        } finally {
            laas.unlock();
        }
        return returmap;
    }

    public int antallMaps() {
        laas.lock();
        int antall;
        try{
             antall = register.antallMaps();
        }
        finally{
            laas.unlock();
        }
        return antall;
    }

    public HashMap<String,Subsekvens> lesFraFil(File fil) {
        return register.lesFraFil(fil);
    }

    public ArrayList<HashMap<String,Subsekvens>> taUtToMaps() throws InterruptedException {

        ArrayList<HashMap<String,Subsekvens>> toMaps = new ArrayList<HashMap<String,Subsekvens>>();
        laas.lock();

        try {
            /*while(this.antallMaps() < 2) {
                lengdeIkkeTo.await();
            }*/
            toMaps.add(this.taUt(1));
            toMaps.add(this.taUt(0));
        } finally {
            laas.unlock();
        }

        return toMaps;


    }

    public HashMap<String,Subsekvens> hashFletting(HashMap<String,Subsekvens> mapEn, HashMap<String,Subsekvens> mapTo) {
        HashMap<String,Subsekvens> returmap;
        laas.lock();
        try {
            returmap = register.hashFletting(mapEn, mapTo);
        } finally {
            laas.unlock();
        }
        return returmap;
    }

}
