import java.util.Arrays;
import java.util.Scanner;

public class j15XorQueriesOfSubarray {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        int q = in.nextInt();
        int[][] queries = new int[q][2];
        for (int i = 0; i < q; i++) {
            queries[i][0] = in.nextInt();
            queries[i][1] = in.nextInt();
        }

        System.out.println(Arrays.toString(xorQueries(arr, queries)));
        System.out.println(Arrays.toString(xorQueriesEfficient(arr, queries)));
        in.close();
    }

    public static int[] xorQueries(int[] arr, int[][] queries) {
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            for (int j = queries[i][0]; j <= queries[i][1]; j++) {
                ans[i] ^= arr[j];
            }
        }
        return ans;
    }

    public static int[] xorQueriesEfficient(int[] arr, int[][] queries) {
        int[] xorPrefix = new int[arr.length];
        int[] ans = new int[queries.length];
        int xor = 0;
        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];
            xorPrefix[i] = xor;
        }

        for (int i = 0; i < queries.length; i++) {
            if (queries[i][0] == 0) {
                ans[i] = xorPrefix[queries[i][1]];
            } else {
                ans[i] = xorPrefix[queries[i][1]] ^ xorPrefix[queries[i][0] - 1];
            }
        }
        return ans;
    }
}
