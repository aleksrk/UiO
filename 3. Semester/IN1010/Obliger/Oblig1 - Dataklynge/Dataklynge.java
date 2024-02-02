import java.util.List;
import java.util.ArrayList;

class Dataklynge {

  private List<Rack> racks = new ArrayList<Rack>();

  public void addNode(Node node) {

        Rack r;

        if (racks.isEmpty()) {
          r = new Rack();
          racks.add(r);
        } else {
          r = racks.get(racks.size()-1);
        }

        if (r.rackSize() < 12) {
          Rack newRack = new Rack();
          racks.add(newRack);
          newRack.addNode(node);
        } else {
          r.addNode(node);

        }
    }

  public int antProsessorer() {

      int prosessorer = 0;

      for (Rack rack : racks) {
          prosessorer += rack.antProsessorer();
      }

      return prosessorer;
  }

  public int noderMedNokMinne (int paakrevdMinne) {
      int noderMinne = 0;

      for (Rack rack : racks) {
        noderMinne += rack.noderStoreNok(paakrevdMinne);
      }
      return noderMinne;
  }

  public int antallRacks () {
      return racks.size();
  }
}
