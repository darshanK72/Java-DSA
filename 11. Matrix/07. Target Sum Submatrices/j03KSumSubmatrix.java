/**
 * Problem Statement:
 * 
 *     Given a matrix of integers, you need to find the number of submatrices
 *     whose sum is equal to a target value. A submatrix is defined by selecting
 *     two rows and two columns. For every pair of rows, compute the sum of all 
 *     the elements between these rows, and find subarrays whose sum equals the
 *     target.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 100) representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 100) representing the number of columns in the matrix.
 *     - A matrix of integers of size `row x col` where each element satisfies (-10^4 <= matrix[i][j] <= 10^4).
 *     - An integer `k` representing the target sum.
 * 
 * Output:
 *     - An integer representing the number of submatrices whose sum equals `k`.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 1 1
 *     1 1 1
 *     1 1 1
 *     2
 * 
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The submatrices with sum 2 are:
 *     1. [ [1, 1], [1, 1] ]
 *     2. [ [1, 1], [1, 1] ]
 *     3. [ [1, 1], [1, 1] ]
 *     4. [ [1, 1], [1, 1] ]
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j03KSumSubmatrix {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col]; // The matrix itself
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: matrix elements
            }
        }
        int k = in.nextInt(); // Input: target sum
        System.out.println(numSubmatrixSumTarget(matrix, k)); // Output the result
        in.close();
    }

    /**
     * Approach 1: Brute Force using Prefix Sum and HashMap for Efficient Calculation
     * 
     * Intuition:
     * - The idea is to consider every possible pair of rows. For each pair of rows,
     *   compute the sum of elements for each column between the two rows.
     *   This reduces the problem to finding subarrays in a 1D array whose sum
     *   equals the target sum `k`, which can be efficiently solved using a HashMap.
     * 
     * Time Complexity:
     * - O(m^2 * n), where m is the number of rows and n is the number of columns.
     *   For each pair of rows, we compute the column sums and then find the subarrays
     *   with sum equal to `k` using the HashMap.
     * 
     * Space Complexity:
     * - O(n), where n is the number of columns. This is for the `preSumRow` array
     *   and the HashMap used to track the running sums.
     * 
     * @param matrix The input matrix.
     * @param target The target sum.
     * @return The count of submatrices whose sum equals the target.
     */
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length; // Number of rows
        int n = matrix[0].length; // Number of columns
        int[] preSumRow = new int[n]; // Array to store column sums
        int ans = 0;

        // Iterate over all possible row pairs
        for (int i = 0; i < m; i++) {
            Arrays.fill(preSumRow, 0); // Reset the column sums for each new row pair
            for (int j = i; j < m; j++) {
                // Update the column sums by adding the values of row j
                for (int k = 0; k < n; k++) {
                    preSumRow[k] += matrix[j][k];
                }
                // Count the number of subarrays in preSumRow that sum to target
                ans += subarraySumK(preSumRow, target);
            }
        }
        return ans;
    }

    /**
     * Helper Function: subarraySumK
     * 
     * Intuition:
     * - This function uses a HashMap to count how many subarrays of the array
     *   `arr` have a sum equal to `k`. The key insight is to maintain a running
     *   sum and use the HashMap to track how many times each sum has been seen.
     *   If the running sum minus `k` has been seen before, it means that there
     *   is a subarray whose sum is `k`.
     * 
     * Time Complexity:
     * - O(n), where n is the length of the array. This is because we iterate over
     *   the array once and perform constant time operations with the HashMap.
     * 
     * Space Complexity:
     * - O(n), where n is the length of the array. This is for the HashMap that stores
     *   the running sums.
     * 
     * @param arr The array of column sums.
     * @param k The target sum.
     * @return The count of subarrays whose sum equals the target.
     */
    public static int subarraySumK(int[] arr, int k) {
        HashMap<Integer, Integer> map = new HashMap<>(); // HashMap to store sum frequencies
        int sum = 0; // Running sum
        int count = 0; // Count of subarrays whose sum equals k
        map.put(sum, 1); // Base case: there's one way to have a sum of 0 (no elements)

        // Iterate over the array to find subarrays with sum equal to k
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // Update the running sum
            // If sum - k is in the map, it means a subarray exists that sums to k
            if (map.containsKey(sum - k)) {
                count += map.get(sum - k); // Increment the count by the frequency of (sum - k)
            }
            // Update the map with the current sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
