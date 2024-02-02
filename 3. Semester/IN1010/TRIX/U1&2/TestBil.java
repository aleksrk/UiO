class Bil {
      private String eier;
      private String merke;
      public Bil (String e, String m) {
        eier = e;
        merke = m;
      }
      public void skrivUt(){
        System.out.println(eier);
        System.out.println(merke);
      }
}

class TestBil {

      public static void main(String [] args){
        Bil b1 = new Bil("Markus", "Toyota");
        Bil b2 = new Bil("Tita", "Porsche");
      }
}
