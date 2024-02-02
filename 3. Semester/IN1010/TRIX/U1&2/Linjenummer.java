import java.util.Scanner;
import java.io.File;

class Linjenummer {
  public static void main(String[] args) throws Exception {
    File fil = new File("mary.txt");
    Scanner les = new Scanner(fil);
    int teller = 1;
    while (les.hasNextLine()) {
      System.out.println("# " + teller + ": " + les.nextLine());
      teller++;
    }
  }
}
