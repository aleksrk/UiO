import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main (String[] args) {
        int n = Integer.valueOf(args[0]);
        int k = Integer.valueOf(args[1]);
        int[] primes = new int[0];
        long[][] results = new long[0][0];

        double[] seq_timing_sieve = new double[7];
        double[] seq_timing_factor = new double[7];
        double[] para_timing_sieve = new double[7];
        double[] para_timing_factor = new double[7];

        System.out.println("Running Sieve with sequential algorithm..");
        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            SieveOfEratosthenes sieve = new SieveOfEratosthenes(n);
            primes = sieve.getPrimes();
            long time_stop = System.nanoTime();
            seq_timing_sieve[runs] = (time_stop - time_start);
            //System.out.println("\nPrimes found: ");
            for(int prime : primes) {
                //System.out.print(prime + ", ");
            }
        }
        Arrays.sort(seq_timing_sieve);
        System.out.println("\nFinished sequential run, time elapsed: " + String.format("%.4f", seq_timing_sieve[3] / 1000000) + "ms.\n");

        System.out.println("Running Sieve with parallel algorithm..");
        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            ParaSieve parasieve = new ParaSieve(n, k);
            primes = parasieve.sieve();
            long time_stop = System.nanoTime();
            para_timing_sieve[runs] = (time_stop - time_start);
            //System.out.println("\nPrimes found: ");
            for(int prime : primes) {
                //System.out.print(prime + ", ");
            }
        }
        Arrays.sort(para_timing_sieve);
        System.out.println("\nFinished parallel run, time elapsed: " + String.format("%.4f", para_timing_sieve[3] / 1000000) + "ms.\n");

        System.out.println("Running Factorize with sequential algorithm..");
        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            Factorizer factor = new Factorizer();
            results = factor.factorize(primes, n);
            long time_stop = System.nanoTime();
            seq_timing_factor[runs] = (time_stop - time_start);
        }
        Arrays.sort(seq_timing_factor);
        System.out.println("\nFinished Factorization run, time elapsed: " + String.format("%.4f", seq_timing_factor[3] / 1000000) + "ms.\n");
        writeFactorization(results, n, "Sequential");

        System.out.println("Running Factorize with parallel algorithm..");
        for (int runs = 0; runs < 7; runs ++) {
            long time_start = System.nanoTime();
            ParaFactorizer parafactor = new ParaFactorizer(primes, n, k);
            results = parafactor.factorize();
            long time_stop = System.nanoTime();
            para_timing_factor[runs] = (time_stop - time_start);
        }
        Arrays.sort(para_timing_factor);
        System.out.println("\nFinished Factorization run, time elapsed: " + String.format("%.4f", para_timing_factor[3] / 1000000) + "ms.\n");
        writeFactorization(results, n, "Parallel");

    }

    private static void writeFactorization(long[][] factorization, int n, String method) {
        Oblig3Precode ob3 = new Oblig3Precode(n);
        long nn = (long)n*n;

        for (long[] factors : factorization) {
            for (long factor : factors) {
                ob3.addFactor(nn, factor);
            }
            nn--;
        }

        ob3.writeFactors(method);
    }
}
