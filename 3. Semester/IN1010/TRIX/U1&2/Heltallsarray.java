  class Heltallsarray {
  public static void main (String[] args){

    int a = 0;
    int b = 1;
    int c = 2;
    int d = 3;
    int e = 4;

    System.out.println(a);
    System.out.println(b);
    System.out.println(c);
    System.out.println(d);
    System.out.println(e);

    int heltallsarray[] = new int[5];

    for (int i = 0; i<heltallsarray.length; i++) {
      heltallsarray[i] = i;
    }

    for (int i = 0; i<heltallsarray.length; i++) {
      System.out.println(heltallsarray[i]);
    }

  }


}
