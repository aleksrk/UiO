import java.util.Scanner;

class SkattIRuritania {

  public static void main (String[] args) {

    Scanner in = new Scanner(System.in);
    System.out.println("Tast din inntekt: ");
    float inntekt = Float.parseFloat(in.nextLine());
    float skatt;

    if (inntekt < 10000) {
      skatt = inntekt*0.1f;
    } else {
      float over10 = inntekt - 10000f;
      skatt = 1000f + (over10 * 0.3f);
    }

    System.out.println("Din beregnede skatte er: " + skatt);
  }
}
