import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.util.List;


class Person {
  private String person;

  public Person(String p) {
    person = p;
  }
  public String hentNavn() {
    return person;
  }
}

class Hund {
  private String hund;

  public Hund(String h) {
    hund = h;
  }
  public String hentNavn() {
    return hund;
  }
}

class TestPersonHund {
  public static void main(String[] args) throws Exception {

    File fil = new File("navn.txt");
    Scanner les = new Scanner(fil);

    List<Hund> hunder = new ArrayList<Hund>();
    List<Person> personer = new ArrayList<Person>();

    while(les.hasNextLine()) {
      String klasse = les.next();
      if (klasse.equals("P")) {
        String s = les.next();
        Person p = new Person(s);
        personer.add(p);
      } else {
        String s = les.next();
        Hund h = new Hund(s);
        hunder.add(h);
      }
    }
  }
}
