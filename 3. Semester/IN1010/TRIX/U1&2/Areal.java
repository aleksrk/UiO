/*
bredde = 10
hoyde = 20
print("Bredde:", bredde)
print("Høyde:", hoyde)

areal = bredde * hoyde
print("Areal:", areal)
*/

class Areal {
      private static int bredde;
      private static int hoyde;
      public static void main(String [] args) {

          bredde = 10;
          hoyde = 20;

          System.out.println("Bredde:" + bredde);
          System.out.println("Lengde:" + hoyde);

          int areal = bredde*hoyde;

          System.out.println("Areal:" + areal);
      }
}
