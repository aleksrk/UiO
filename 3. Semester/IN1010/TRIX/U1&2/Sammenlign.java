class Sammenlign {
        public static void main(String [] param){
            int a;
            int b;

            a = Integer.parseInt(param[0]);
            b = Integer.parseInt(param[1]);

            if (a>b){
              System.out.println("A er storre enn b");
            } else {
              System.out.println("A er ikke storre enn b");
          }
        }
}
