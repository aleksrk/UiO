class Hund implements Comparable<Hund> {

    String navn;
    Kull mittKull;
    Tidspunkt minFodselstid;
    Hund neste = null;

    Hund(Kull k, String navn, Tidspunkt fodt) {
        this.navn = navn;
        mittKull = k;
        minFodselstid = fodt;
    }

    @Override
    public int compareTo(Hund h) {
        return minFodselstid.compareTo(h.minFodselstid);
    }
    public Hund mor() {
        return mittKull.mor;
    }
    public Hund far () {
        return mittKull.far;
    }
    public boolean erHelsosken(Hund h) {
        return this.mor()!=null && this.far()!=null && this.mor()==h.mor() && this.far()==h.far();
    }
    public boolean erHalvsosken(Hund h) {
        return (this.mor()!= null && this.mor()==h.mor() || this.far()!=null && this.far()==h.far()) && erHelsosken(h);
    }
    public Hund finnEldsteKjenteOpphav() {
        if (this.mor()==null) {
            return this.far()==null ? this : far().finnEldsteKjenteOpphav();
        }

        if (this.far()==null) {
            return mor().finnEldsteKjenteOpphav();
        }

        Hund morsEldsteOpphav = mor().finnEldsteKjenteOpphav();
        Hund farsEldsteOpphav = far().finnEldsteKjenteOpphav();
        if (morsEldsteOpphav.compareTo(farsEldsteOpphav) < 0) {
            return morsEldsteOpphav;
        } else {
            return farsEldsteOpphav;
        }
    }
}

abstract class Kull implements Iterable<Hund> {
    Hund mor, far;

    Kull (Hund mor, Hund far) {
        this.mor = mor;
        this.far = far;
    }
    public abstract void settInn(Hund h);
    public abstract Iterator<Hund> iterator();
}

class KullListe extends Kull {

    Hund foerste = null;
    Hund neste = null;

    KullListe(Hund mor, Hund far) {
        super(mor, far);
    }

    public void settInn(Hund h) {
        if (foreste == null) {
            foerste = h;
            return;
        }

        if (h.compareTo(foerste) < 0) {
            h.neste = foerste;
            foerste = h;
            return;
        }
        Hund p = foerste;
        while (true) {
            if (p.neste == null) {
                p.neste = h;
                break;
            } else if (h.compareTo(p.neste) > 0) {
                h.neste = p.neste.neste;
                p.neste = h;
                break;
            } else {
                p = p.neste;
            }
        }
    }

    public Iterator<Hund> iterator() {
        return new HundeIterator();
    }
    class HundeIterator implements Iterator<Hund> {
        private Hund denne = foerste;

        public boolean hasNext() {
            return denne != null;
        }
        public Hund next() {
            Hund svar = denne;
            denne = denne.neste;
            return svar;
        }
    }
}

class KullArray extends Kull {

}
