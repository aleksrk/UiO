class BoolskeVerdier {
        public static void main(String [] args){
          boolean sann;
          boolean usann;
          sann = true;
          usann = false;
          System.out.println(sann);
          if (sann != usann) {
            System.out.println("Forste test slo til!");
          } else {
            System.out.println("you dumb");
          }
          if (sann == usann) {
            System.out.println("you dumb");
          } else {
            System.out.println("Andre test slo ikke til!");
          }
        }
}
