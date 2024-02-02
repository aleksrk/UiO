
public class Sequential {

    public static double[][] MATRIX_MULTIPLY(double[][] a, double[][] b, int n) {

        double[][] result = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] MATRIX_TRANSPOSE(double[][] a, int n) {
        double[][] result = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                result[i][j] = a[j][i];
            }
        }

        return result;
    }

    public static double[][] MATRIX_MULTIPLY_TRANSPOSE_A(double[][]a, double[][]b, int n) {
        double [][] result = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    result[i][j] += a[k][i] * b[k][j];
                }
            }
        }

        return result;
    }

    public static double[][] MATRIX_MULTIPLY_TRANSPOSE_B(double[][]a, double[][]b, int n) {
        double [][] result = new double[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    result[i][j] += a[i][k] * b[j][k];
                }
            }
        }

        return result;
    }
}
