import java.util.ArrayList;

public class Factorizer {

    public static long[][] factorize(int[] primes, int n) {
        long maxBase = (long) n * n;
        long[][] factors = new long[100][];

        for (int i = 0; i < 100; i++) {
            factorizeBase(primes, maxBase - i, factors, i);
        }
        return factors;
    }

    private static void factorizeBase(int[] primes, long base, long[][] factors, int round) {
        int primeIndex = 0;
        long factor = base;
        ArrayList<Long> primeFactors = new ArrayList<>();

        while (primeIndex < primes.length && Math.pow(primes[primeIndex], 2) <= base) {
            if (factor % primes[primeIndex] == 0) {
                factor = factor / primes[primeIndex];
                primeFactors.add((long) primes[primeIndex]);
            } else {
                primeIndex++;
            }
        }
        if (factor != 1) {
            primeFactors.add(factor);
        }

        long[] factorArray = new long[primeFactors.size()];
        int j = 0;
        for (long primeFactor : primeFactors) {
            factorArray[j++] = primeFactor;
        }

        factors[round] = factorArray;
    }
}
