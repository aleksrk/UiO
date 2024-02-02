import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

class Telefonbok {

    public static void main(String[] args){

      Person Lisa = new Person("Lisa", "443", "Snipetjernveien 11");
      Person Joakim = new Person("Joakim", "441", "Problemveien 3");
      Person Dag = new Person("Dag", "431", "Gategate 4");

      /*
      Person[] liste = new Person[10];
      liste[0] = Lisa;
      liste[1] = Joakim;
      liste[2] = Dag;

      for (int i=0; i<10; i++){
        if (liste[i] != null) {
            liste[i].skrivInfo();
        }
      */

      /*
      List<Person> liste = new ArrayList<Person>();

      liste.add(Lisa);
      liste.add(Joakim);
      liste.add(Dag);

      for (Person person : liste) {
        person.skrivInfo();
      }*/

      HashMap<String, Person> map = new HashMap<>();
      map.put(Lisa.hentNavn(), Lisa);
      map.put(Joakim.hentNavn(), Joakim);
      map.put(Dag.hentNavn(), Dag);

      for(Person entry : map.values()) {
            entry.skrivInfo();
      }

    }
}
