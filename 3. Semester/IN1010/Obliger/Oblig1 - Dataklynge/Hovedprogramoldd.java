class Hovedprogram2 {

    public static void main(String[] args) {
        Dataklynge saga = new Dataklynge();

        for (int i = 0; i<450; i++) {

            Node node = new Node(4, 512);
            saga.addNode(node);

        }

        for (int i = 0; i<16; i++) {

            Node node2 = new Node(8,1024);
            saga.addNode(node2);

        }
        System.out.println("Noder med minst 128 GB: " + saga.noderMedNokMinne(128));
        System.out.println("Noder med minst 512 GB: " + saga.noderMedNokMinne(512));
        System.out.println("Noder med minst 1024 GB: " + saga.noderMedNokMinne(1024));
        System.out.println("");
        System.out.println("Antall prosessorer: " + saga.antProsessorer());
        System.out.println("Antall rack:" + saga.antallRacks());

    }
}
