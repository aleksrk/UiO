import java.util.ArrayList;

class Modell {
        GUI gui;
        int[][] brett = new int[12][12];
        int slangeLengde;
        boolean spilletErSlutt = false;
        ArrayList<Koordinater> Slangen = new ArrayList<Koordinater>();

        Modell(GUI g) {
            this.gui = g;
            boolean skattPlassert = false;
            int a, b;
            brett[6][6] = 2;
            Slangen.add(new Koordinater(6, 6));
            for (int i = 0; i < 12; i++) {
                skattPlassert = false;
                while(!skattPlassert) {
                    a = trekk(0,11);
                    b = trekk(0,11);
                    if (brett[a][b] == 0) {
                        brett[a][b] = 1;
                        skattPlassert = true;
                    }
                }

            }
        }

        boolean lovligFlytt(int r, int k){
            return false;
        }

        private int trekk(int a, int b) {
            return (int)(Math.random()*(b-a+1) + a);
        }

        String modellHentVerdi(int a, int b) {
            if (brett[a][b] == 0) {
                return "";
            } else if (brett[a][b] == 1) {
                return "   # ";
            } else {
                return "   S ";
            }
        }
        public Koordinater hodePos() {
            return Slangen.get(0);
        }
        void flytt(int a, int b) {
            Koordinater k = Slangen.get(Slangen.size() - 1);
            int rad = k.getRad();
            int kol = k.getKol();
            if (rad + a < 0 || rad + a > 12 || kol + b < 0 || kol + b > 12) {
                System.out.println("utenfor boksen");
                System.exit(0);
            }
            Koordinater slangensEnde = Slangen.get(0);
            if (brett[rad + a][kol + b] == 0) {
                Slangen.add(new Koordinater(rad + a, kol + b));
                Slangen.remove(0);
                brett[slangensEnde.getRad()][slangensEnde.getKol()] = 0;
                gui.markerRute(slangensEnde.getRad(), slangensEnde.getKol(), "");
                brett[rad + a][kol + b] = 2;
                gui.markerRute(rad + a, kol + b, "  S ");
            } else if (brett[rad + a][kol + b] == 1) {
                Slangen.add(new Koordinater(rad + a, kol + b));
                brett[rad+a][kol+b] = 2;
                gui.markerRute(rad + a, kol + b, "  S ");
            } else if (brett[rad + a][kol + b] == 2) {
                System.out.println("du kræsjet");
                System.exit(0);
            }
        }
}

class Koordinater {
    int akoord;
    int bkoord;
    Koordinater(int a, int b) {
        this.akoord = a;
        this.bkoord = b;
    }

    int getRad() {
        return akoord;
    }

    int getKol() {
        return bkoord;
    }

}
