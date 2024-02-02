import java.util.ArrayList;
import java.util.List;

class Brev {

  private String avsender;
  private String mottaker;
  private List<String> linjer = new ArrayList<String>();

  public Brev(String avs, String mot) {
      avsender = avs;
      mottaker = mot;
  }
  public void skrivLinje(String linje) {
    linjer.add(linje);
  }
  public void lesBrev() {
    System.out.println("Hei " + mottaker);
    System.out.println("");

    for (int i=0;i<linjer.size();i++) {
      System.out.println(linjer.get(i));
    }
    System.out.println("");
    System.out.print("Hilsen fra, " + avsender);
  }
}
