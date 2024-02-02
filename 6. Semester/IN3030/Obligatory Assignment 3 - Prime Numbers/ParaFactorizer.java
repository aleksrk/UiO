import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CyclicBarrier;

public class ParaFactorizer {
    long number;
    int[] primes;
    int k;
    ArrayList<ArrayList<ArrayList<Integer>>> factors;
    long[][] finalFactors;
    CyclicBarrier workerThreads, mainThread;

    public ParaFactorizer(int[] primes, int n, int k) {
        number = (long) n * n;
        this.primes = primes;
        this.k = k;
        factors = new ArrayList<>(100);
        finalFactors = new long[100][];
        for (int i = 0; i < 100; i++) {
            ArrayList<ArrayList<Integer>> list = new ArrayList<>(k);
            for (int j = 0; j < k; j++) {
                list.add(new ArrayList<>());
            }
            factors.add(list);
        }
        mainThread = new CyclicBarrier(k + 1);
        workerThreads = new CyclicBarrier(k);
    }

    public long[][] factorize() {
        for (int id = 0; id < k; id++) {
            new Thread(new Worker(id)).start();
        }
        try {
            mainThread.await();
        } catch (Exception e) {}
        return finalFactors;
    }

    private class Worker implements Runnable {
        int id;

        private Worker(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                factorizePartially(number - i);
            }
            try {
                workerThreads.await();
            } catch (Exception e) {}

            for (int start = id; start < factors.size(); start += k) {
                long[] factorSet;
                int size = 0;
                long product = 1;
                long n = number - start;
                ArrayList<ArrayList<Integer>> factorLists = factors.get(start);

                for (ArrayList<Integer> factorList : factorLists) {
                    size += factorList.size();
                    for (int factor : factorList) {
                        product *= factor;
                    }
                }

                if (product != n) {
                    factorSet = new long[size + 1];
                    factorSet[factorSet.length - 1] =  (n/product);
                } else {
                    factorSet = new long[size];
                }

                int i = 0;
                for (ArrayList<Integer> factorList : factorLists) {
                    for (int factor : factorList) {
                        factorSet[i++] = factor;
                    }
                }
                finalFactors[start] = factorSet;
                Arrays.sort(factorSet);
            }

            try {
                mainThread.await();
            } catch (Exception e) {}
        }

        private void factorizePartially(long n) {
            int start = id;
            int stop = (int) (number - n);
            ArrayList<Integer> factorList = factors.get(stop).get(id);

            while (start < primes.length && Math.pow(primes[start], 2) <= n) {
                if (n % primes[start] == 0) {;
                    factorList.add(primes[start]);
                    n /= primes[start];
                } else {
                    start += k;
                }
            }
        }
    }
}
