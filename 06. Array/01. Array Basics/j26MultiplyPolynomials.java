import java.util.Arrays;
import java.util.Scanner;

public class j26MultiplyPolynomials {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int[] arr1 = new int[m];
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextInt();
        }
        int n = in.nextInt();
        int[] arr2 = new int[n];
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt();
        }
        System.out.println(Arrays.toString(polyMultiply(arr1, arr2, m, n)));
        in.close();
    }

    public static int[] polyMultiply(int arr1[], int arr2[], int m, int n) {
        int[] out = new int[m + n - 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                out[i + j] += (arr1[i] * arr2[j]);
            }
        }
        return out;
    }
}
