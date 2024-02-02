import java.util.Scanner;

class Handletur {

  public static void main(String[] args) {

    int broed = 20;
    int melk = 15;
    int ost = 40;
    int yoghurt = 12;
    int totalPris = 0;
    Scanner in = new Scanner(System.in);

    System.out.print("Hvor mange brød vil du ha?");
    totalPris += Integer.parseInt(in.nextLine()) * broed;

    System.out.print("Hvor mange melk vil du ha?");
    totalPris += Integer.parseInt(in.nextLine()) * melk;

    System.out.print("Hvor mange ost vil du ha?");
    totalPris += Integer.parseInt(in.nextLine()) * ost;

    System.out.print("Hvor mange yoghurt vil du ha?");
    totalPris += Integer.parseInt(in.nextLine()) * yoghurt;

    System.out.println("Du skal betale: " + totalPris + " kr");

  }
}
