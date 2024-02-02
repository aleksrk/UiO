import java.util.Arrays;
import java.util.List;

class Main {
    public static void main (String[] args) {
        int seed = Integer.valueOf(args[0]);
        double[] seq_timing = new double[7];
        double[] para_timing = new double[7];

        int i = 1000;
        
        while (i <= 100000000) {
            System.out.println("Running Algorithms for n = " + i);
            ConvexHull c = new ConvexHull(i, seed);
            Sequential seq = new Sequential(c);

            for (int runs = 0; runs < 7; runs ++) {
                long time_start = System.nanoTime();
                IntList CoHullSeq = seq.findConvexHull();
                seq_timing[runs] = (System.nanoTime() - time_start);
            }
            Arrays.sort(seq_timing);
            System.out.println("\nFinished sequential run, time elapsed: "
                + String.format("%.4f", seq_timing[3] / 1000000) + "ms.");

            Parallel par = new Parallel();
            for (int runs = 0; runs < 7; runs ++) {
                long time_start = System.nanoTime();
                IntList CoHullPar = par.findConvexHull(c, 8);
                para_timing[runs] = (System.nanoTime() - time_start);
            }
            Arrays.sort(para_timing);
            System.out.println("Finished Parallel run, time elapsed: "
                + String.format("%.4f", para_timing[3] / 1000000) + "ms.\n");

            i = i * 10;
        }


        ConvexHull out = new ConvexHull(50, seed);
        Parallel p = new Parallel();
        IntList CoHullPar = p.findConvexHull(out, 8);
        Oblig4Precode oblig4par = new Oblig4Precode(out, CoHullPar);
        oblig4par.writeHullPoints();


    }
}
