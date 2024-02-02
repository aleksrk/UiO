class Kontroll {
    Modell modell;
    GUI gui;
    private Thread traad;
    private Retning retning = Retning.VEST;

    Kontroll() {
        gui = new GUI(this);
        modell = new Modell(gui);
        gui.byggGUI();
        traad = new Thread(new Kjoerer());
        traad.start();
    }

    void avsluttSpillet() {
        System.exit(1);
    }
    void gaaVenstre(){
        modell.flytt(0,-1);
    }
    void gaaHoyre(){
        modell.flytt(0,1);
    }
    void gaaOpp(){
        modell.flytt(-1,0);
    }
    void gaaNed(){
        modell.flytt(1,0);
    }
    void settRetning(Retning r) {
        this.retning = r;
    }
    void flytt() {

        switch(retning) {
            case VEST: gaaVenstre();
                    break;
            case OST: gaaHoyre();
                    break;
            case NORD: gaaOpp();
                    break;
            case SOR: gaaNed();
                    break;
        }

    }
    String hentVerdi(int a, int b) {
        return modell.modellHentVerdi(a,b);
    }

    class Kjoerer implements Runnable {

        @Override
        public void run(){
            while(true){
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    return;
                }
                flytt();
            }
        }
    }
}
