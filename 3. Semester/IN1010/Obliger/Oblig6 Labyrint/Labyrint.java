import java.io.*;
import java.util.*;


class Labyrint {

    protected Rute[][] labyrintData;
    protected File fil;
    private int rader;
    private int kolonner;

    public Labyrint(File fil) {

        this.fil = fil;
        lesFraFil();

    }

    @Override
    public String toString() {
        String retur = "";
        for (Rute[] r : labyrintData) {
            for (Rute k : r) {
                retur += k.toString();
            }
            retur += "\n";
        }

        return retur;
    }

    public void lesFraFil() {

        try {

            Scanner sc = new Scanner(fil);
            String lest;
            int raden = 0;

            while (sc.hasNextLine()) {
                lest = sc.nextLine();

                if (lest.charAt(0) == '#' || lest.charAt(0) == '.') {
                    for (int i = 0; i < kolonner; i++) {
                        if (lest.charAt(i) == '.') {
                            if (i == kolonner - 1 || raden == rader - 1 || i == 0 || raden == 0) {
                                labyrintData[raden][i] = new Aapning(raden, i, this);
                            } else {
                                labyrintData[raden][i] = new HvitRute(raden, i, this);
                            }
                        } else {
                            labyrintData[raden][i] = new SortRute(raden, i, this);
                        }
                    }
                    raden++;
                } else {
                    String[] lese = lest.split(" ");
                    rader = Integer.parseInt(lese[0]);
                    kolonner = Integer.parseInt(lese[1]);
                    labyrintData = new Rute[rader][kolonner];
                }
            }

            finnNaboer();

        } catch (FileNotFoundException e) {
            System.out.println("Filen finnes ikke!");
            return;
        }


    }

    public void finnNaboer() {

        Rute nord, vest, syd, oest;

        for (int i = 0; i < rader; i++) {
            for (int j = 0; j < kolonner; j++) {
                if (i - 1 < 0) {
                    nord = null;
                } else {
                    nord = labyrintData[i-1][j];
                }

                if (j - 1 < 0) {
                    vest = null;
                } else {
                    vest = labyrintData[i][j-1];
                }

                if (i + 1 >= rader) {
                    syd = null;
                } else {
                    syd = labyrintData[i+1][j];
                }

                if (j + 1 >= kolonner) {
                    oest = null;
                } else {
                    oest = labyrintData[i][j+1];
                }
                labyrintData[i][j].settNaboer(nord, vest, syd, oest);
            }
        }

    }

    public void finnUtveiFra(int rad, int kol) {
        labyrintData[rad][kol].finn(null);
    }

}
