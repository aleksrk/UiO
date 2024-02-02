public class Sequential {
    public static int[] insertSort (int [] a, int stop) {
        int i, t;
        for (int k = 0; k < stop; k++) {
            t = a[k + 1];
            i = k;
            while (i >= 0 && a[i] < t) {
                a[i+1] = a[i];
                i--;
            }
            a[i+1] = t;
        }
        return a;
    }

    public static int[] find_k_largest(int[] a, int stop) {
        int i, t;
        a = insertSort(a, stop);
        for (int k = stop; k < a.length; k++) {
            if (a[stop-1] < a[k]) {
                a[stop-1] = a[k];
                for (int j = stop - 1; j > 0; j--) {
                    if (a[j] > a[j-1]) {
                        int temp = a[j-1];
                        a[j-1] = a[j];
                        a[j] = temp;
                    } else {
                        break;
                    }
                }
            }
        }

        return a;
    }
}
