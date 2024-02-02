
class HvitRute extends Rute {

    public int besoekt;

    public HvitRute(int radnr, int kolnr, Labyrint lab) {
        super(radnr, kolnr, lab);
    }

    @Override
    public void finn(Rute fra) {
        if (this == fra) {
            return;
        }
        if (fra == null) {
            nord.finn(this);
            syd.finn(this);
            oest.finn(this);
            vest.finn(this);
        } else if (this.erBesoekt() == 0){
            this.settBesoekt();
            if (fra.nord == null) {} else {this.nord.finn(this);}
            if (fra.syd == null) {} else {this.syd.finn(this);}
            if (fra.vest == null) {} else {this.vest.finn(this);}
            if (fra.oest == null) {} else {this.oest.finn(this);}
        } else {
            return;
        }
    }

    @Override
    public String toString() {
        return ".";
    }

    @Override
    public void settBesoekt() {
        this.besoekt = 1;
    }

    public int erBesoekt() {
        return besoekt;
    }

}
