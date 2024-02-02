import java.util.Scanner;
import java.io.File;


class TemperaturLeser {
  public static void main(String[] args) throws Exception {

    File fil = new File("temperatur.txt");
    Scanner les = new Scanner(fil);

    String[] temp = new String[12];
    int teller = 0;

    while (les.hasNextLine()) {
      temp[teller] = les.nextLine();
      teller++;
    }

    for (int i = 0; i<temp.length; i++) {
      System.out.println(temp[i]);
    }

  }

}
