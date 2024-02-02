import java.util.List;
import java.util.ArrayList;

class Rack {


  private Node[] rack = new Node[12];
  private int antallNoder=0;

    public void addNode(Node node) {
        if (antallNoder < 12) {
            rack[antallNoder] = node;
            antallNoder++;
        } else {
            System.out.println("ikke nok plass");
        }
    }


  public int rackSize() {
    return antallNoder;
  }

  public int antProsessorer() {
    int prosessorer = 0;
    for (int i=0; i<antallNoder; i++) {
      prosessorer += rack[i].getProsessorer();
    }
    return prosessorer;
  }

  public int noderStoreNok(int paakrevdMinne) {
      int noderMinne = 0;

      for (int i=0; i<antallNoder; i++) {
          if (rack[i].getMinnestorrelse() >= paakrevdMinne) {
              noderMinne += 1;
          }
      }
      return noderMinne;
  }
}
