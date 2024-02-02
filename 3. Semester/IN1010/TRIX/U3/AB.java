class A {

    public void skrivUt() {
        System.out.println("Jeg tilhører klasse A");
    }
}

class B extends A {
    /*public void skrivUt() {
        System.out.println("Jeg tilhører klasse B");
    }*/
}

class AB {

    public static void main(String[] args) {
        B objektB = new B();
        B pekerB = new B();
        A pekerA = new A();

        pekerB = objektB;
        pekerA = objektB;

        pekerB.skrivUt();
        pekerA.skrivUt();
    }
}
