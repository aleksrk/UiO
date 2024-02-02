import java.util.Scanner;
import java.io.File;

class Gruppeoppmoete {

    private String[] navn = new String[14];
    private boolean[] oppmoete = new boolean[14];
    private int totalStudenter = 0;

    public void lesFil() throws Exception {

      File fil = new File("gruppeliste.txt");
      Scanner les = new Scanner(fil);

      while (les.hasNextLine() && totalStudenter<navn.length) {
        navn[totalStudenter] = les.nextLine();
        totalStudenter++;
      }
    }

    public void regOppmoete(String student) {
      for (int i = 0; i<totalStudenter; i++) {
        if (student.equals(navn[i])) {
          oppmoete[i] = true;
        }
      }
    }

    public void skrivUt() {
      for (int i = 0; i<14; i++) {
        System.out.println(navn[i] + ": " + oppmoete[i]);
      }
    }

}

class TestGruppeoppmoete {
  public static void main(String[] args) throws Exception {
    Gruppeoppmoete moete = new Gruppeoppmoete();

    moete.lesFil();
    moete.regOppmoete("Per");
    moete.regOppmoete("Geir");
    moete.regOppmoete("Emilie");
    moete.skrivUt();
  }
}
