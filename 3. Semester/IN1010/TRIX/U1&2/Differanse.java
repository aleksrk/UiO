import java.util.Scanner;

class Differanse {
    public static void main (String[] args) {
      int a,b,c;
      Scanner minInn = new Scanner(System.in);

      System.out.print("Oppgi verdien til a:");
      a = minInn.nextInt();

      System.out.print("Oppgi verdien til b:");
      b = minInn.nextInt();
      c = a-b;
      System.out.println("Differansen mellom a og b er: " + c);
    }
}
