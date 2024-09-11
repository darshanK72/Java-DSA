import java.util.Scanner;

public class j10SumOfEvenAfterQueries {
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
        System.out.println(sumEvenAfterQueries(arr, queries));
        in.close();
    }

    public static int[] sumEvenAfterQueries(int[] nums, int[][] queries) {
        int[] output = new int[queries.length];
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 == 0)
                sum += nums[i];
        }

        for (int i = 0; i < queries.length; i++) {
            if (nums[queries[i][1]] % 2 == 0) {
                sum -= nums[queries[i][1]];
            }
            nums[queries[i][1]] += queries[i][0];
            if (nums[queries[i][1]] % 2 == 0) {
                sum += nums[queries[i][1]];
            }
            output[i] = sum;
        }
        return output;
    }
}
