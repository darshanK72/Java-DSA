/**
 * Problem Statement:
 * 
 *      Given a row-wise sorted matrix of size `m x m`, find the median of the matrix.
 * 
 * Input:
 *     - An integer `m` representing the size of the square matrix.
 *     - A 2D array `mat` of size `m x m`, where each row is sorted in non-decreasing order.
 * 
 * Output:
 *     - An integer representing the median of the matrix.
 * 
 * Example:
 *     Input:
 *         3
 *         1 3 5
 *         2 6 9
 *         3 6 9
 * 
 *     Output:
 *         5
 * 
 *     Explanation:
 *     - The flattened matrix is [1, 2, 3, 3, 5, 6, 6, 9, 9].
 *     - The median is the middle element, which is 5.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j03MedianInRowWiseSortedMatrix {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Input: Read the size of the square matrix
        int m = in.nextInt();

        // Input: Initialize the matrix
        int mat[][] = new int[m][m];

        // Input: Populate the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                mat[i][j] = in.nextInt();
            }
        }

        // Output the median using brute force approach
        System.out.println(medianBruitForce(mat));

        // Output the median using binary search approach
        System.out.println(medianBinarySearch(mat));

        in.close(); // Close the scanner
    }

   /**
     * Approach 1: Brute Force
     * 
     * Intuition:
     * - The matrix is row-wise sorted. If we flatten all rows into a single array
     *   and sort it, we can directly access the middle element to find the median.
     * - This approach leverages sorting after flattening the matrix.
     * 
     * Steps:
     * 1. Flatten the matrix into a single array.
     * 2. Sort the array.
     * 3. Return the middle element as the median.
     * 
     * Time Complexity:
     * - O(m^2 * log(m^2)): Flattening takes O(m^2), sorting takes O(m^2 * log(m^2)).
     * 
     * Space Complexity:
     * - O(m^2): Additional space is used to store the flattened array.
     * 
     * @param mat The input matrix.
     * @return The median of the matrix.
     */
    public static int medianBruitForce(int mat[][]) {
        // Flatten the matrix into a single array
        int m = mat.length * mat[0].length;
        int[] flat = new int[m];
        int k = 0;

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                flat[k++] = mat[i][j];
            }
        }

        // Sort the flattened array
        Arrays.sort(flat);

        // Return the middle element as the median
        return flat[m / 2];
    }

   /**
     * Approach 2: Binary Search
     * 
     * Intuition:
     * - Since each row is sorted, the median will have half the elements smaller
     *   than itself. We can use binary search to find the smallest number `x` for
     *   which there are at least `m*m/2 + 1` elements less than or equal to `x`.
     * 
     * Steps:
     * 1. Find the minimum and maximum elements in the matrix (start range for binary search).
     * 2. Perform binary search to find the value `x` that satisfies the median condition.
     * 3. Use a helper method `countLessThanK` to count elements ≤ `x` in the matrix.
     * 
     * Time Complexity:
     * - O(m * log(max-min)): Binary search runs for log(max-min) iterations, and each
     *   iteration involves counting elements across all rows (O(m)).
     * 
     * Space Complexity:
     * - O(1): No additional space is required.
     * 
     * @param mat The input matrix.
     * @return The median of the matrix.
     */
    public static int medianBinarySearch(int mat[][]) {
        // Initialize search range
        int low = 2001; // Upper bound of element range in the matrix
        int high = -1; // Lower bound of element range in the matrix
        int n = mat.length; // Size of the matrix
        int required = (n * n) / 2; // Half of the total elements in the matrix

        // Find the global min and max in the matrix
        for (int i = 0; i < mat.length; i++) {
            low = Math.min(low, mat[i][0]); // Minimum element in the first column
            high = Math.max(high, mat[i][n - 1]); // Maximum element in the last column
        }

        // Binary search to find the median
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Count elements ≤ mid
            if (countLessThanK(mat, mid) <= required) {
                low = mid + 1; // Median is greater, search in the right half
            } else {
                high = mid - 1; // Median is smaller, search in the left half
            }
        }

        return low;
    }

    /**
     * Helper Method: Count elements ≤ k in the matrix
     * 
     * @param mat The input matrix.
     * @param k   The threshold value.
     * @return The count of elements ≤ k.
     */
    public static int countLessThanK(int[][] mat, int k) {
        int count = 0;

        // Count elements row by row
        for (int i = 0; i < mat.length; i++) {
            count += upperBound(mat[i], k);
        }

        return count;
    }

   /**
     * Helper Method: Find the first index where `arr[index] > k` in a sorted array
     * 
     * Intuition:
     * - This uses binary search to find the number of elements ≤ k in a sorted array.
     * 
     * Time Complexity:
     * - O(log(n)): Binary search on each row.
     * 
     * @param arr The input array (sorted row of the matrix).
     * @param k The threshold value.
     * @return The count of elements ≤ k in the array.
     */
    public static int upperBound(int[] arr, int k) {
        int s = 0;
        int e = arr.length - 1;

        // Binary search for the upper bound
        while (s <= e) {
            int mid = s + (e - s) / 2;

            if (arr[mid] > k) {
                e = mid - 1; // Search in the left half
            } else {
                s = mid + 1; // Search in the right half
            }
        }

        return s;
    }
}
