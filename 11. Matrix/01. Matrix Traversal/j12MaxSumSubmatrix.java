import java.util.ArrayList;
import java.util.Scanner;

public class j12MaxSumSubmatrix {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                lst.add(in.nextInt());
            }
            list.add(lst);
        }
        System.out.println(maxSumSubmatrix(list, n));
        in.close();
    }

    public static int maxSumSubmatrix(ArrayList<ArrayList<Integer>> A, int B) {
        int N = A.size();
        // Step 1: Construct the prefix sum matrix
        int[][] prefix = new int[N][N];

        // Fill the prefix sum array
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int current = A.get(i).get(j);
                prefix[i][j] = current;

                if (i > 0)
                    prefix[i][j] += prefix[i - 1][j]; // Add sum from the row above
                if (j > 0)
                    prefix[i][j] += prefix[i][j - 1]; // Add sum from the column on the left
                if (i > 0 && j > 0)
                    prefix[i][j] -= prefix[i - 1][j - 1]; // Avoid double-counting the top-left corner
            }
        }

        // Step 2: Find the maximum sum of a BxB submatrix
        int maxSum = Integer.MIN_VALUE;

        // Iterate over all possible top-left corners of the BxB submatrices
        for (int i = B - 1; i < N; i++) {
            for (int j = B - 1; j < N; j++) {
                // Calculate the sum of the BxB submatrix ending at (i, j)
                int total = prefix[i][j];

                // Exclude the sum of rows above the submatrix
                if (i - B >= 0)
                    total -= prefix[i - B][j];

                // Exclude the sum of columns to the left of the submatrix
                if (j - B >= 0)
                    total -= prefix[i][j - B];

                // Add back the sum of the top-left excluded part (it was subtracted twice)
                if (i - B >= 0 && j - B >= 0)
                    total += prefix[i - B][j - B];

                // Update the maximum sum
                maxSum = Math.max(maxSum, total);
            }
        }

        return maxSum;
    }
}
