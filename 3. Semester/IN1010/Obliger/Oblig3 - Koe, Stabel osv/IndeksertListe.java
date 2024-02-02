public class IndeksertListe<T> extends Lenkeliste<T> {

    public void leggTil (int pos, T x) throws UgyldigListeindeks {

        if(pos < 0 || pos > stoerrelse){
            throw new UgyldigListeindeks(pos);
        }

        Node nyNode = new Node(x);
        Node peker = start;

        if (pos == 0) {
            nyNode.neste = peker;
            start = nyNode;
            stoerrelse++;
        } else {
            for(int i=0; i<pos-1; i++) {
                peker = peker.neste;
            }
            nyNode.neste = peker.neste;
            peker.neste = nyNode;
            stoerrelse++;
        }
    }

    public void sett (int pos, T x) throws UgyldigListeindeks {

        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0){
            throw new UgyldigListeindeks(pos);
        }

        Node nyNode = new Node(x);
        Node peker = start;

        if (pos == 0) {
            nyNode.neste = start.neste;
            start = nyNode;
        } else {
            for(int i=0; i<pos-1; i++) {
                peker = peker.neste;
            }
            nyNode.neste = peker.neste.neste;
            peker.neste = nyNode;
        }
    }

    public T hent (int pos) throws UgyldigListeindeks {

        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0){
            throw new UgyldigListeindeks(pos);
        }

        Node peker = start;

        for(int i=0; i<pos; i++) {
            peker = peker.neste;
        }
        return peker.data;
    }

    public T fjern (int pos) throws UgyldigListeindeks {
        T data;
        if(pos < 0 || pos >= stoerrelse || stoerrelse == 0){
            throw new UgyldigListeindeks(pos);
        }

        Node peker = start;

        if (pos == 0) {
            data = super.fjern();
        } else {
            for (int i=0; i<pos-1; i++) {
                peker = peker.neste;
            }
            data = peker.neste.data;
            peker.neste = peker.neste.neste;
            stoerrelse--;
            return data;
        }
        return data;
    }
}
