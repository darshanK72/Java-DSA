import java.util.Arrays;
import java.util.Scanner;

public class j09MergeTwoSortedArraysInBoth {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        long[] arr1 = new long[m];
        long[] arr2 = new long[n];
        for (int i = 0; i < m; i++) {
            arr1[i] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            arr2[i] = in.nextInt();
        }
        mergeArrays(arr1, arr2, m, n);
        System.out.println(Arrays.toString(arr1));
        in.close();
    }

    public static void mergeArrays(long arr1[], long arr2[], int n, int m) {
        long[] out = new long[n + m];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < n && j < m) {
            if (arr1[i] < arr2[j]) {
                out[k] = arr1[i];
                i++;
            } else {
                out[k] = arr2[j];
                j++;
            }
            k++;
        }

        while (i < n) {
            out[k] = arr1[i];
            k++;
            i++;
        }

        while (j < m) {
            out[k] = arr2[j];
            k++;
            j++;
        }

        k = 0;
        j = 0;
        while (j < n) {
            arr1[j] = out[k];
            k++;
            j++;
        }

        j = 0;
        while (j < m) {
            arr2[j] = out[k];
            k++;
            j++;
        }
    }

    public static void mergeArraysEfficient(long arr1[], long arr2[], int n, int m) {
        int i = n - 1;
        int j = 0;
        while (i >= 0 && j < m) {
            if (arr1[i] > arr2[j]) {
                long temp = arr1[i];
                arr1[i] = arr2[j];
                arr2[j] = temp;
                i--;
                j++;
            } else {
                break;
            }
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
    }
}
