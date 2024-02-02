import java.util.Collections;
import java.util.Arrays;

public class Main {
    public static void main (String[] args) {
        int n = Integer.valueOf(args[0]);
        int seed = Integer.valueOf(args[1]);


        double[] seq_timing = new double[7];
        double[] seq_timing_a = new double[7];
        double[] seq_timing_b = new double[7];
        double[] para_timing = new double[7];
        double[] para_timing_a = new double[7];
        double[] para_timing_b = new double[7];

        double[][] a = Oblig2Precode.generateMatrixA(seed, n);
        double[][] b = Oblig2Precode.generateMatrixB(seed, n);
        double[][] a_transposed = Sequential.MATRIX_TRANSPOSE(a, n);
        double[][] b_transposed = Sequential.MATRIX_TRANSPOSE(b, n);

        double[][] correct = new double[n][n];
        double[][] c = new double[n][n];

        System.out.println("Running program with matrix of size " + n + "x" + n + " with seed " + seed + ".\n");
        //Sequential standard runs
        System.out.println("Starting 7 runs on sequential multiplication algorithm..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            correct = Sequential.MATRIX_MULTIPLY(a, b, n);
            long time_stop = System.nanoTime();
            seq_timing[runs] = (time_stop - time_start);
        }

        Arrays.sort(seq_timing);
        System.out.println("Finished sequential run, time elapsed: " + String.format("%.4f", seq_timing[3] / 1000000) + "ms.\n");


        //Sequential a tranposed runs
        System.out.println("Starting 7 runs on sequential multiplication algorithm with a transposed..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            c = Sequential.MATRIX_MULTIPLY_TRANSPOSE_A(a_transposed, b, n);
            long time_stop = System.nanoTime();
            seq_timing_a[runs] = (time_stop - time_start);
        }

        test_correct(c, correct, n);
        Arrays.sort(seq_timing_a);
        System.out.println("Finished sequential run with a transposed, time elapsed: " + String.format("%.4f", seq_timing_a[3] / 1000000) + "ms.\n");

        //Sequential b tranposed runs
        System.out.println("Starting 7 runs on sequential multiplication algorithm with b transposed..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            c = Sequential.MATRIX_MULTIPLY_TRANSPOSE_B(a, b_transposed, n);
            long time_stop = System.nanoTime();
            seq_timing_b[runs] = (time_stop - time_start);
        }

        test_correct(c, correct, n);
        Arrays.sort(seq_timing_b);
        System.out.println("Finished sequential run with b transposed, time elapsed: " + String.format("%.4f", seq_timing_b[3] / 1000000) + "ms.\n");


        Para p = new Para();
        //Parallel standard runs
        System.out.println("Starting 7 runs on parallel multiplication algorithm..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            c = p.MATRIX_MULTIPLY(a, b, n, "MATRIX_MULTIPLY");
            long time_stop = System.nanoTime();
            para_timing[runs] = (time_stop - time_start);
        }

        test_correct(c, correct, n);
        Arrays.sort(para_timing);
        System.out.println("Finished parallel run, time elapsed: " + String.format("%.4f", para_timing[3] / 1000000) + "ms.\n");

        //Parallel a transposed
        System.out.println("Starting 7 runs on parallel multiplication algorithm with a transposed..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            c = p.MATRIX_MULTIPLY(a_transposed, b, n, "A_TRANSPOSED");
            long time_stop = System.nanoTime();
            para_timing_a[runs] = (time_stop - time_start);
        }

        test_correct(c, correct, n);
        Arrays.sort(para_timing_a);
        System.out.println("Finished parallel run with a transposed, time elapsed: " + String.format("%.4f", para_timing_a[3] / 1000000) + "ms.\n");

        //Parallel b transposed
        System.out.println("Starting 7 runs on parallel multiplication algorithm with b transposed..");

        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            c = p.MATRIX_MULTIPLY(a, b_transposed, n, "B_TRANSPOSED");
            long time_stop = System.nanoTime();
            para_timing_b[runs] = (time_stop - time_start);
        }

        test_correct(c, correct, n);
        Arrays.sort(para_timing_b);
        System.out.println("Finished parallel run with b transposed, time elapsed: " + String.format("%.4f", para_timing_b[3] / 1000000) + "ms.\n");

    }
    public static void test_correct(double[][] a, double[][] answer, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double diff = Math.abs(answer[i][j] - a[i][j]);
                if (diff < 1E-7) {
                    //do nothing
                } else {
                    System.out.println(answer[i][j] + " does not equal " + a[i][j]);
                }
            }
        }
    }
}
