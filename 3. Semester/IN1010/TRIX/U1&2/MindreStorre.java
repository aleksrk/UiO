import java.util.Scanner;

class MindreStorre {
    public static void main (String[] args) {
      int a;
      Scanner minInn = new Scanner(System.in);
      System.out.print("Tast inn ett tall: ");
      a = minInn.nextInt();
      if (a>10 && a<20) {
        System.out.println("Tallet er mellom 10 og 20");
      } else if (a>20) {
        System.out.println("Tallet er større enn 20");
      } else {
        System.out.println("Tallet er under 10");
      }
    }
}
