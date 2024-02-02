import java.util.HashMap;
import java.util.Scanner;

class Telefonbok2 {
    public static void main (String[] args){

        HashMap<String, String> map = new HashMap<>();

        map.put("Arne", "22334455");
        map.put("Lisa", "95959595");
        map.put("Jonas", "97959795");
        map.put("Peder", "12345678");

        Scanner in = new Scanner(System.in);

        int status = 0;
        while (status != 1) {
          System.out.println("tast 0 for søk, eller 1 for å avslutte");
          status = Integer.parseInt(in.nextLine());
          if (status == 0) {
            System.out.println("Tast inn navn du behøver nummer til: ");

            String navn = in.nextLine();
            if (map.containsKey(navn)) {
              String tlf = map.get(navn);
              System.out.println("Navn: " + navn + " Tlf: " + tlf);
            } else {
              System.out.println(navn + " finnes ikke i telefonboken!");
            }
          }
        }
        System.out.println(map);
    }
}
