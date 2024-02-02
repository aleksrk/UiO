import java.util.concurrent.*;
import java.util.Arrays;

public class Para {
    int processors = Runtime.getRuntime().availableProcessors();
    int[][] commonData;


    public double[][] MATRIX_MULTIPLY(double[][] a, double[][] b, int n, String method) {
        double result[][] = new double[n][n];

        int start = 0;
        int increment = n / processors;
        int left = n % processors;
        int stop = increment + left;

        Thread[] threads = new Thread[processors];
        for (int i = 0; i < processors; i++) {
            threads[i] = new Thread(new Worker(i, start, stop, a, b, result, method, n));
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

        return result;
    }

    public class Worker implements Runnable {
        private int id;
        private int start;
        private int stop;
        private double[][] a;
        private double[][] b;
        private double[][] result;
        private String method;
        private int n;

        public Worker(int id, int start, int stop, double[][] a, double[][] b, double[][] result, String method, int n) {
            this.id = id;
            this.start = start;
            this.stop = stop;
            this.a = a;
            this.b = b;
            this.result = result;
            this.method = method;
            this.n = n;
        }

        public void run() {
            if(this.method.equals("A_TRANSPOSED")) {
                for(int i = start; i < stop; i++) {
                    for(int j = 0; j < n; j++) {
                        for(int k = 0; k < n; k++) {
                            this.result[i][j] += this.a[k][i] * this.b[k][j];
                        }
                    }
                }
            } else if (this.method.equals("B_TRANSPOSED")) {
                for(int i = start; i < stop; i++) {
                    for(int j = 0; j < n; j++) {
                        for(int k = 0; k < n; k++) {
                            this.result[i][j] += this.a[i][k] * this.b[j][k];
                        }
                    }
                }
            } else {
                for(int i = start; i < stop; i++) {
                    for(int j = 0; j < n; j++) {
                        for(int k = 0; k < n; k++) {
                            this.result[i][j] += this.a[i][k] * this.b[k][j];
                        }
                    }
                }
            }
        }
    }
}
