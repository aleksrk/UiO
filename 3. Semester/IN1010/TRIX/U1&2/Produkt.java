import java.util.Scanner;

class Produkt {
    public static void main (String[] args) {

      int a,b,c;
      Scanner minInn = new Scanner(System.in);

      System.out.print("Oppgi verdien til a:");
      a = minInn.nextInt();

      System.out.print("Oppgi verdien til b:");
      b = minInn.nextInt();
      c = a*b;
      if (a>b) {
        System.out.println("Differansen mellom a og b er positiv");
      } else if (a==b) {
        System.out.println("Differansen mellom a og b er positiv");
      } else {
        System.out.println("Differansen mellom a og b er negativ");
      }
      System.out.println("Produktet av a og b er: " + c);

  }
}
