import java.util.Scanner;
import java.io.File;

class FraFil {

  public static void main(String[] args) throws Exception {
      File fil = new File("tekst.txt");
      Scanner les = new Scanner(fil);

      while (les.hasNextLine()) {
        System.out.println(les.nextLine());
      }
      les.close();
  }
}
