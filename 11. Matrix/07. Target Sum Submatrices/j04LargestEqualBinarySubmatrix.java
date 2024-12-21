/**
 * Problem Statement:
 * 
 *     Given a binary matrix (with 0s and 1s), your task is to find the area of the largest submatrix
 *     that has an equal number of 0s and 1s. The solution should identify the largest rectangular 
 *     submatrix where the number of 0s is equal to the number of 1s in the submatrix.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100) representing the number of rows in the matrix.
 *     - An integer `m` (1 <= m <= 100) representing the number of columns in the matrix.
 *     - A binary matrix `mat` of size `n x m` where each element is either 0 or 1.
 * 
 * Output:
 *     - An integer representing the area of the largest submatrix that has an equal number of 0s and 1s.
 * 
 * Example:
 *     Input:
 *     4 4
 *     1 0 1 0
 *     0 1 0 1
 *     1 0 1 0
 *     0 1 0 1
 * 
 *     Output:
 *     16
 * 
 *     Explanation:
 *     The largest submatrix with an equal number of 0s and 1s is the entire matrix.
 *     It contains 8 1s and 8 0s.
 */

import java.util.HashMap;
import java.util.Scanner;

public class j04LargestEqualBinarySubmatrix {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of rows
        int m = in.nextInt(); // Number of columns
        int[][] matrix = new int[n][m];

        // Reading the binary matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = in.nextInt(); // Matrix elements (0 or 1)
            }
        }

        // Call the function and print the result
        System.out.println("Largest Equal Binary Submatrix Area: " + maximumArea(matrix, n, m));
        in.close();
    }

    /**
     * Approach 1: Convert 0s to -1s and find the largest zero-sum submatrix using column prefix sum
     * 
     * Intuition:
     * - The idea is to convert the binary matrix where 0 becomes -1. This way, the problem becomes
     *   finding subarrays with a sum of zero, which implies an equal number of 1s and 0s in the submatrix.
     * - Then, for every pair of rows, we calculate the sum of elements in the columns between these rows.
     *   This reduces the problem to a 1D array, where the goal is to find the longest subarray with a sum of zero.
     * 
     * Time Complexity:
     * - O(n^2 * m), where `n` is the number of rows and `m` is the number of columns.
     *   For every pair of rows, we compute the column sums and then find the subarrays with sum zero in O(m).
     * 
     * Space Complexity:
     * - O(m), where `m` is the number of columns. This is for the `colPrefixSum` array and the HashMap used in the helper function.
     * 
     * @param mat The input binary matrix.
     * @param n The number of rows in the matrix.
     * @param m The number of columns in the matrix.
     * @return The area of the largest submatrix with an equal number of 0s and 1s.
     */
    public static int maximumArea(int[][] mat, int n, int m) {
        // Step 1: Convert the matrix so that 0 becomes -1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    mat[i][j] = -1;
                }
            }
        }

        // Step 2: Now we need to find the largest zero-sum submatrix
        int maxArea = 0;

        // Iterate over all possible pairs of rows
        for (int startRow = 0; startRow < n; startRow++) {
            int[] colPrefixSum = new int[m];

            for (int endRow = startRow; endRow < n; endRow++) {
                // Add the values of the current row to colPrefixSum
                for (int col = 0; col < m; col++) {
                    colPrefixSum[col] += mat[endRow][col];
                }

                // Find the largest zero-sum subarray in colPrefixSum
                int area = largestBinaryArray(colPrefixSum, endRow - startRow + 1);

                // Update the maximum area
                maxArea = Math.max(maxArea, area);
            }
        }

        return maxArea;
    }

    /**
     * Helper Function: largestBinaryArray
     * 
     * Intuition:
     * - This function uses a HashMap to track the sum of elements in a row between any pair of rows.
     *   If the sum of any subarray (column range) is zero, it indicates that the number of 1s and 0s are
     *   equal in that submatrix. The goal is to find the maximum length of such a subarray.
     * 
     * Time Complexity:
     * - O(m), where `m` is the number of columns. This is because we iterate over the array once.
     * 
     * Space Complexity:
     * - O(m), for the HashMap that stores the cumulative sums.
     * 
     * @param arr The array representing column sums for a pair of rows.
     * @param rowCount The number of rows in the current submatrix.
     * @return The area of the largest zero-sum subarray.
     */
    private static int largestBinaryArray(int[] arr, int rowCount) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLength = 0;
        map.put(0, -1); // To handle the case where the zero sum starts from index 0

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            if (map.containsKey(sum)) {
                // Calculate the length of the subarray
                int startIndex = map.get(sum);
                int subarrayLength = i - startIndex;

                // Calculate the area of the submatrix
                int area = subarrayLength * rowCount;
                maxLength = Math.max(maxLength, area);
            } else {
                map.put(sum, i);
            }
        }

        return maxLength;
    }
}
