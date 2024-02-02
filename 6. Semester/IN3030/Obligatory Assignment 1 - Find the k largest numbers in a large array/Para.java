import java.util.concurrent.*;
import java.util.Arrays;

public class Para {
    int processors =  Runtime.getRuntime().availableProcessors();
    int[][] commonData;


    public int[] find_k_largest(int[] a, int k) {

        commonData = new int[processors][k];
        int start = 0;
        int increment = a.length / processors;
        int stop = increment;


        Thread[] threads = new Thread[processors];
        for (int i = 0; i < processors; i++) {
            threads[i] = new Thread(new Worker(i, start, stop, a, k));
            threads[i].start();
            start = stop;
            stop += increment;
        }

        for (int i = 0; i < processors; i++) {
            try {
                threads[i].join();
            } catch (Exception e) {
                break;
            }
        }

        for (int i = 1; i < processors; i++) {
            for (int j = 0; j < k; j++) {
                if (commonData[i][j] > commonData[0][k-1]) {
                    commonData[0][k-1] = commonData[i][j];
                    for (int l = k - 1; l > 0; l--) {
                        if (commonData[0][l] > commonData[0][l-1]) {
                            int temp = commonData[0][l-1];
                            commonData[0][l-1] = commonData[0][l];
                            commonData[0][l] = temp;
                        } else {
                            break;
                        }
                    }
                } else {
                    break;
                }
            }
        }

        return commonData[0];
    }

    public class Worker implements Runnable {

        private int id;
        private int start;
        private int stop;
        private int[] a;
        private int k;

        public Worker (int id, int start, int stop, int[] a, int k) {
            this.id = id;
            this.start = start;
            this.stop = stop;
            this.a = a;
            this.k = k;
        }

        public void run() {
            int[] results = new int[this.k];
            int t = 0;
            for (int i = this.start; i < this.start + k; i++) {
                results[t] = this.a[i];
                t++;
            }
            results = Sequential.insertSort(results, this.k);

            for (int j = this.start + this.k; j < this.stop; j++) {
                if (results[this.k-1] < a[this.k]) {
                    results[this.k-1] = a[this.k];
                    for (int i = k - 1; i > 0; i--) {
                        if (results[i] > results[i-1]) {
                            int temp = results[i-1];
                            results[i-1] = results[i];
                            results[i] = temp;
                        } else {
                            break;
                        }
                    }
                }
            }
            commonData[this.id] = results;
        }
     }



}
