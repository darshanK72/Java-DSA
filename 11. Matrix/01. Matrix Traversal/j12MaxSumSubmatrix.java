/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of integers, the task is to find the maximum sum of a submatrix 
 *     of size BxB. The matrix is of size N x N (1 ≤ N ≤ 1000, 1 ≤ B ≤ N). The sum of a 
 *     submatrix is calculated by summing the values of the elements in the submatrix. 
 *     Your goal is to find the submatrix of size BxB whose sum is the maximum possible 
 *     among all BxB submatrices in the matrix.
 * 
 * Input:
 *     - An integer `n` representing the size of the matrix (n x n).
 *     - An integer `B` representing the size of the submatrix.
 *     - A 2D matrix of integers `A` of size n x n where each element satisfies (1 ≤ A[i][j] ≤ 10^5).
 * 
 * Output:
 *     - An integer representing the maximum sum of any BxB submatrix.
 * 
 * Example:
 *     Input:
 *     4
 *     2
 *     1 2 3 4
 *     5 6 7 8
 *     9 10 11 12
 *     13 14 15 16
 * 
 *     Output:
 *     54
 * 
 *     Explanation:
 *     The submatrix of size 2x2 with the maximum sum is:
 *     11 12
 *     15 16
 *     The sum of this submatrix is 11 + 12 + 15 + 16 = 54.
 */

import java.util.ArrayList;
import java.util.Scanner;

public class j12MaxSumSubmatrix {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the size of the matrix
        int B = in.nextInt(); // Input: the size of the submatrix
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();

        // Reading the matrix elements
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                lst.add(in.nextInt()); // Input: elements of the matrix
            }
            list.add(lst);
        }

        // Call the function to find the maximum sum of BxB submatrix
        System.out.println(maxSumSubmatrix(list, B));
        in.close();
    }

    /**
     * Approach 1: Brute Force Method
     * 
     * Intuition:
     * - In this approach, we calculate the sum of each possible BxB submatrix 
     *   by iterating over all possible positions of the top-left corner of the submatrix.
     * - For each position, we calculate the sum of all BxB elements, which takes O(B^2) time.
     * - As we have to check all possible top-left corners of the BxB submatrices, the time 
     *   complexity is O((N-B+1) * (N-B+1) * B^2) where N is the size of the matrix.
     * 
     * Time Complexity:
     * - O(N^4) (for checking each BxB submatrix and summing its elements).
     * 
     * Space Complexity:
     * - O(1) (no extra space used except for variables).
     * 
     * @param A The input matrix.
     * @param B The size of the submatrix.
     * @return The maximum sum of a BxB submatrix.
     */
    public static int maxSumSubmatrix(ArrayList<ArrayList<Integer>> A, int B) {
        int N = A.size();
        int maxSum = Integer.MIN_VALUE;

        // Iterate over all possible top-left corners of the BxB submatrices
        for (int i = 0; i <= N - B; i++) {
            for (int j = 0; j <= N - B; j++) {
                // Calculate the sum of the BxB submatrix starting at (i, j)
                int total = 0;
                for (int x = 0; x < B; x++) {
                    for (int y = 0; y < B; y++) {
                        total += A.get(i + x).get(j + y);
                    }
                }
                // Update the maximum sum
                maxSum = Math.max(maxSum, total);
            }
        }
        return maxSum;
    }

    /**
     * Approach 2: Optimized Approach using Prefix Sum Matrix
     * 
     * Intuition:
     * - Instead of calculating the sum of each BxB submatrix from scratch, we can use a prefix sum matrix 
     *   to compute the sum of any submatrix in constant time.
     * - The prefix sum matrix is a 2D matrix where each cell at (i, j) contains the sum of all elements 
     *   from the top-left corner (0, 0) to (i, j).
     * - Using this prefix sum matrix, we can quickly calculate the sum of any BxB submatrix.
     * - This reduces the time complexity significantly by avoiding recalculating sums repeatedly.
     * 
     * Time Complexity:
     * - O(N^2) for constructing the prefix sum matrix.
     * - O(N^2) for checking all possible BxB submatrices.
     * - Overall time complexity is O(N^2).
     * 
     * Space Complexity:
     * - O(N^2) for storing the prefix sum matrix.
     * 
     * @param A The input matrix.
     * @param B The size of the submatrix.
     * @return The maximum sum of a BxB submatrix.
     */
    public static int maxSumSubmatrixOptimized(ArrayList<ArrayList<Integer>> A, int B) {
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
