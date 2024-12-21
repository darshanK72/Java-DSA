/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of integers, the task is to find the largest submatrix where the sum of all its elements is zero.
 *     A submatrix is defined as a contiguous block of elements in the matrix. You need to identify the submatrix 
 *     with the maximum area where the sum of its elements equals zero.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 100), representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 100), representing the number of columns in the matrix.
 *     - A 2D matrix `a` of size `row x col` where each element `a[i][j]` (-10^4 <= a[i][j] <= 10^4).
 * 
 * Output:
 *     - A 2D matrix representing the largest zero-sum submatrix, or an empty list if no such submatrix exists.
 * 
 * Example:
 *     Input:
 *     4 4
 *     1 -1 0 0
 *     0 1 -1 0
 *     -1 0 1 1
 *     0 -1 0 1
 * 
 *     Output:
 *     [[1, -1, 0, 0],
 *      [0, 1, -1, 0],
 *      [-1, 0, 1, 1]]
 * 
 *     Explanation:
 *     The largest submatrix with zero sum is a 3x4 matrix starting from (0, 0) to (2, 3).
 *     The sum of all elements in this submatrix is zero.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class j02LargestZeroSumSubmatrix {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }
        System.out.println(sumZeroMatrix(matrix)); // Output: largest zero-sum submatrix
        in.close();
    }

    /**
     * Approach: Use the technique of summing subarrays between rows and then applying a modified Kadane's algorithm
     *          to find the largest zero-sum submatrix.
     * 
     * Intuition:
     * - For each pair of rows, we compute the prefix sums for the columns. This effectively converts the 2D matrix 
     *   problem to a 1D array problem for each row pair.
     * - Then, we treat each column sum as an array and try to find the largest subarray (using Kadane's approach) 
     *   that sums to zero. This subarray defines the largest zero-sum submatrix for that pair of rows.
     * - We repeat this process for every pair of rows and track the submatrix with the maximum area.
     * 
     * Time Complexity:
     * - O(m^2 * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We iterate over all row pairs, and for each pair, we process the columns in O(n).
     * 
     * Space Complexity:
     * - O(n), due to the space required for the sumPrefix array and the hash map used in Kadane's algorithm.
     * 
     * @param a The input matrix.
     * @return The largest zero-sum submatrix, or an empty list if no such submatrix exists.
     */
    public static ArrayList<ArrayList<Integer>> sumZeroMatrix(int[][] a) {
        int m = a.length;
        if (m == 0)
            return new ArrayList<>(); // Edge case: empty matrix
        int n = a[0].length;

        // To store the coordinates and size of the largest zero sum submatrix
        int maxArea = 0;
        int[] bestCoordinates = { -1, -1, -1, -1 }; // Top-left (r1, c1) and bottom-right (r2, c2)

        // Iterate over all pairs of rows
        for (int i = 0; i < m; i++) {
            int[] sumPrefix = new int[n]; // Array to store the sum of elements between row i and row j
            for (int j = i; j < m; j++) {
                // Add elements from row j to sumPrefix (effectively updating the column sums)
                for (int k = 0; k < n; k++) {
                    sumPrefix[k] += a[j][k];
                }

                // Find the largest zero sum subarray for the current sumPrefix array
                int[] subarrayResult = largestZeroSumSubarray(sumPrefix);

                // Check if we found a larger submatrix
                if (subarrayResult[0] != -1) { // If a zero-sum subarray was found
                    int area = (j - i + 1) * (subarrayResult[1] - subarrayResult[0] + 1);
                    if (area > maxArea) {
                        maxArea = area;
                        bestCoordinates[0] = i; // Top-left row
                        bestCoordinates[1] = subarrayResult[0]; // Top-left column
                        bestCoordinates[2] = j; // Bottom-right row
                        bestCoordinates[3] = subarrayResult[1]; // Bottom-right column
                    }
                }
            }
        }

        // If no zero-sum submatrix is found, return an empty ArrayList
        if (bestCoordinates[0] == -1)
            return new ArrayList<>();

        // Extract and return the largest zero-sum submatrix
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = bestCoordinates[0]; i <= bestCoordinates[2]; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = bestCoordinates[1]; j <= bestCoordinates[3]; j++) {
                row.add(a[i][j]);
            }
            result.add(row);
        }

        return result;
    }

    /**
     * Helper function to find the largest zero sum subarray in a given 1D array using a hashmap.
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
     * @return The start and end indices of the largest zero-sum subarray.
     */
    private static int[] largestZeroSumSubarray(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        int maxLen = 0;
        int start = -1, end = -1;
        map.put(0, -1);

        // Iterate through the array to find the largest zero-sum subarray
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i]; // Update the running sum

            if (map.containsKey(sum)) {
                int prevIndex = map.get(sum);
                if (i - prevIndex > maxLen) {
                    maxLen = i - prevIndex;
                    start = prevIndex + 1;
                    end = i;
                }
            } else {
                map.put(sum, i); // Store the first occurrence of the sum
            }
        }
        return new int[] { start, end }; // Return the start and end indices of the largest zero-sum subarray
    }
}
