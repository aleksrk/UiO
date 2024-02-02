class Node {
  private int prosessorer;
  private int minnestorrelse;

  public Node(int p, int m) {
    prosessorer = p;
    minnestorrelse = m;
  }

  public int getProsessorer(){
    return prosessorer;
  }

  public int getMinnestorrelse() {
    return minnestorrelse;
  }
}
