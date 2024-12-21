/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of integers, find the total number of submatrices with a sum equal to zero. 
 *     A submatrix is a contiguous block of elements from the original matrix, and a sum of a submatrix is 
 *     the sum of all its elements. The task is to count how many such submatrices exist where the sum equals zero.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 100), representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 100), representing the number of columns in the matrix.
 *     - A 2D matrix `A` of size `row x col` where each element `A[i][j]` (-10^4 <= A[i][j] <= 10^4).
 * 
 * Output:
 *     - An integer representing the total number of submatrices with a sum equal to zero.
 * 
 * Example:
 *     Input:
 *     3 3
 *     0 1 1
 *     1 1 1
 *     1 1 0
 * 
 *     Output:
 *     4
 * 
 *     Explanation:
 *     There are 4 submatrices with a sum of zero: 
 *     1. Submatrix starting at (0,0) and ending at (0,0).
 *     2. Submatrix starting at (0,2) and ending at (0,2).
 *     3. Submatrix starting at (2,0) and ending at (2,0).
 *     4. Submatrix starting at (2,2) and ending at (2,2).
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class j01CountZeroSumSubmatrix {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            ArrayList<Integer> lst = new ArrayList<>();
            for (int j = 0; j < col; j++) {
                lst.add(in.nextInt()); // Input: elements of the matrix
            }
            list.add(lst);
        }
        System.out.println(countZeroSumSubmatrix(list)); // Output: count of submatrices with sum 0
        in.close();
    }

    /**
     * Approach: Use prefix sum and hash map to count submatrices with zero sum.
     * 
     * Intuition:
     * - The idea is to reduce the problem of counting submatrices to counting subarrays with zero sum.
     * - We iterate over every pair of rows in the matrix. For each pair of rows, we collapse the matrix 
     *   into a 1D array where each element represents the sum of the columns between the two rows.
     * - Then we use a hash map to count subarrays with a zero sum in this 1D array. This gives us the number 
     *   of submatrices for that specific row pair.
     * - We do this for all pairs of rows in the matrix.
     * 
     * Time Complexity:
     * - O(m * m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   This is because we iterate over all pairs of rows and for each pair, we process the columns in O(n).
     * 
     * Space Complexity:
     * - O(n), where `n` is the number of columns. This space is used for the hash map and the prefix sum array.
     * 
     * @param A The input matrix.
     * @return The count of submatrices with zero sum.
     */
    public static int countZeroSumSubmatrix(ArrayList<ArrayList<Integer>> A) {
        int m = A.size();
        if (m == 0)
            return 0; // If the matrix is empty, no submatrices
        int n = A.get(0).size();
        int ans = 0;
        int[] sumPrefix = new int[n]; // Array to store the prefix sum of each column

        // Iterate over all pairs of rows
        for (int i = 0; i < m; i++) {
            Arrays.fill(sumPrefix, 0); // Reset the prefix sum array for each new starting row
            for (int j = i; j < m; j++) {
                // For each row pair (i, j), calculate the sum of columns
                for (int k = 0; k < n; k++) {
                    sumPrefix[k] += A.get(j).get(k); // Add the current row to the prefix sum
                }
                // Count the number of subarrays with sum zero for the current prefix sum
                ans += subarraySumK(sumPrefix);
            }
        }
        return ans;
    }

    /**
     * Helper function to count subarrays with sum equal to zero using a hash map.
     * 
     * Intuition:
     * - We maintain a running sum while iterating through the array. 
     * - If the running sum has been encountered before, it means there is a subarray with sum zero between
     *   the previous occurrence and the current index.
     * - The hash map is used to store the count of each sum encountered so far.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of columns. This is because we iterate through the prefix sum array once.
     * 
     * Space Complexity:
     * - O(n), due to the space required for the hash map.
     * 
     * @param arr The 1D array representing the prefix sum of columns.
     * @return The number of subarrays with sum zero.
     */
    public static int subarraySumK(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int count = 0;
        map.put(0, 1); // Base case: zero sum is encountered once
        // Iterate through the array to count subarrays with sum zero
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // Update the running sum
            if (map.containsKey(sum)) {
                count += map.get(sum); // If the sum is already in the map, it means there are subarrays with sum zero
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1); // Update the count of the current sum in the map
        }
        return count;
    }
}
