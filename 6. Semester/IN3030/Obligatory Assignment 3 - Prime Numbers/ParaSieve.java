import java.util.concurrent.*;
import java.util.ArrayList;

public class ParaSieve {
    int n, k, root, root_2, numOfPrimes, byteCells, lastPrime;
    int[] primes;
    byte[] oddNumbers;
    int[][] primesNested;
    int count;

    CyclicBarrier mainThread, workerThreads;

    public ParaSieve(int n, int k) {
        this.n = n;
        this.k = k;
        root = (int) Math.sqrt(n);
        root_2 = (int) Math.sqrt(root);
        byteCells = (n / 16) + 1;
        oddNumbers = new byte[byteCells];
    }

    private void traverse(int prime) {
        for (int i = prime*prime; i <= root; i += prime * 2)
            mark(i);
    }

    private int nextPrime(int prev) {
        for (int i = prev; i <= root; i += 2)
            if (isPrime(i))
            return i;

        return 0;
    }

    private void mark(int num) {
        if (num % 2 == 0) {
            return;
        }
        int bitIndex = (num / 2) % 8;
        int byteIndex = num / 16;
        oddNumbers[byteIndex] |= (1 << bitIndex);
    }

    private boolean isPrime(int num) {
        if((num % 2) == 0) {
              return false;
          }
        int bitIndex = (num / 2) % 8;
        int byteIndex = num / 16;

        return (oddNumbers[byteIndex] & (1 << bitIndex)) == 0;
    }

    public int[] sieve() {
        this.primesNested = new int[k][];
        count = 1;
        int num = 3;
        while (num != 0 && num <= root_2) {
            traverse(num);
            num = nextPrime(num + 2);
        }
        lastPrime = num;
        int start = root / 16;
        int increment = (byteCells - start) / k;
        int rest = (byteCells - start) % k;

        mainThread = new CyclicBarrier(k + 1);
        workerThreads = new CyclicBarrier(k);

        for (int i = 0; i < rest; i++) {
            new Thread(new Worker(i, start, start + increment + 1)).start();
            start += increment + 1;
        }

        for (int i = rest; i < k; i++) {
            new Thread(new Worker(i, start ,start + increment)).start();
            start += increment;
        }

        try {
            mainThread.await();
        } catch (Exception e) {
        }

        primes[0] = 2;
        return primes;


    }

    public class Worker implements Runnable {
            int id, start, stop;
            int[] localPrimes;

            public Worker(int id, int start, int stop) {
                this.id = id;
                this.start = (start * 16) + 1;

                if (id == k - 1) {
                    this.stop = ((n & 1) == 0 ) ? n + 1 : n + 2;
                } else {
                    this.stop = (stop * 16) + 1;
                }
            }

            public void traverse(int prime, int first, int last) {
                for (int i = first * prime; i < this.stop; i += prime * 2) {
                    mark(i);
                }
            }
            @Override
            public void run() {
                    int num = 3;
                    int end = this.stop;
                    int startFactor, endFactor;

                    while (num != 0 && num <= root && num*num <= end - 2) {
                        startFactor = (this.start + num - 1) / num;
                        startFactor = (num < startFactor) ? startFactor : num;
                        endFactor = (end - 2) / num;
                        if ((startFactor & 1) == 0) startFactor++;
                        if ((endFactor & 1) == 0) endFactor--;
                        traverse(num, startFactor, endFactor);
                        num = nextPrime(num + 2);
                    }


                int count = 0;

                for (int i = (id == 0) ? 1 : start; i < end; i++) {
                    if(isPrime(i)) {
                        count++;
                    }
                }

                localPrimes = new int[count];

                int j = 0;
                for (int i = (id == 0) ? 1 : start; i < end && j < count; i++) {
                    if(isPrime(i))
                        localPrimes[j++] = i;
                }

                primesNested[id] = localPrimes;

                try {
                    workerThreads.await();
                } catch (Exception e) {}

                if (id == 0) {
                    count = 0;
                    for (int[] list : primesNested) {
                        count += list.length;
                    }

                    primes = new int[count];
                }

                try {
                    workerThreads.await();
                } catch (Exception e) {}

                int startIndex = 0;

                if (id != 0) {
                    for (int i = 0; i < id; i++) {
                        startIndex += primesNested[i].length;
                    }
                }

                j = startIndex;

                for (int i = 0; i < localPrimes.length; i++) {
                    primes[j++] = localPrimes[i];
                }

                try {
                    mainThread.await();
                } catch (Exception e) {}
            }
    }
}
