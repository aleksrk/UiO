import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main (String[] args) {
        int n = Integer.valueOf(args[0]);
        int k = Integer.valueOf(args[1]);

        double timing = 0;

        int[] random_list = new int[n];
        Random r = new Random(7361);
        for (int i = 0; i < random_list.length; i++) {
            random_list[i] = r.nextInt(n);
        }
        int[] sorted_correct = random_list.clone();
        Arrays.sort(sorted_correct);

        //Sequential
        System.out.println("Starting 7 runs on Sequential sorting algorithm..");
        for (int runs = 0; runs < 7; runs++) {
            int[] sorted = random_list.clone();

            long time_start = System.nanoTime();
            sorted = Sequential.find_k_largest(sorted, k);
            long time_stop = System.nanoTime();
            timing += time_stop - time_start;
            test_correctness(sorted, sorted_correct, k);
        }
        System.out.println("Finished sequential run, average time elapsed: " + String.format("%.2f", timing / 7000000) + " ms." + "\n");

        //Sequential Arrays.sort
        timing = 0;
        System.out.println("Starting 7 runs on Arrays.sort sorting algorithm..");
        for (int runs = 0; runs < 7; runs++) {
            int [] sorted_sort = random_list.clone();

            long time_start = System.nanoTime();
            Arrays.sort(sorted_sort);
            long time_stop = System.nanoTime();
            timing += time_stop - time_start;
        }
        System.out.println("Finished Arrays.sort run, average time elapsed: " + String.format("%.2f", timing / 7000000) + " ms." + "\n");

        //Parallel
        timing = 0;
        Para p = new Para();
        System.out.println("Starting 7 runs on Parallell sorting algorithm..");
        for (int runs = 0; runs < 7; runs++) {
            int [] sorted = random_list.clone();

            long time_start = System.nanoTime();
            sorted = p.find_k_largest(sorted, k);
            long time_stop = System.nanoTime();
            timing += time_stop - time_start;
            test_correctness(sorted, sorted_correct, k);
        }
        System.out.println("Finished Parallel run, average time elapsed: " + String.format("%.2f", timing / 7000000) + " ms." + "\n");

    }

    public static void test_correctness(int[] a, int[] answer, int k) {
        for (int i = 1; i < k; i++) {
            if (answer[answer.length - i] == a[i-1]) {
                continue;
            } else {
                System.out.println("Value " + answer[answer.length - i] + " is not equal " + a[i-1]);
            }
        }
    }
}
