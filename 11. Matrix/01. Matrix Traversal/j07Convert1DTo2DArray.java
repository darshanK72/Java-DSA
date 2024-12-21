/**
 * Problem Statement:
 * 
 *     Given a 1D array of integers, the task is to convert it into a 2D array with `m` rows and `n` columns. 
 *     If the total number of elements in the 1D array does not match the expected number of elements in the 
 *     2D array (`m * n`), return an empty 2D array. Otherwise, fill the 2D array row by row with the elements 
 *     of the 1D array.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the size of the original 1D array.
 *     - A 1D array `original` of size `n` (1 <= original[i] <= 10^5).
 *     - Two integers `m` and `n` (1 <= m, n <= 1000), representing the number of rows and columns for the 2D array.
 * 
 * Output:
 *     - A 2D array of size `m x n` with the elements of the original 1D array.
 *     - If the total number of elements in the 1D array does not match `m * n`, return an empty 2D array.
 * 
 * Example:
 *     Input:
 *     6
 *     1 2 3 4 5 6
 *     2 3
 *     
 *     Output:
 *     1 2 3
 *     4 5 6
 * 
 *     Explanation:
 *     - The 1D array of size 6 is successfully converted into a 2D array with 2 rows and 3 columns.
 */

import java.util.Scanner;

public class j07Convert1DTo2DArray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the original 1D array
        int[] original = new int[n]; // Initialize the original 1D array
        for (int i = 0; i < n; i++) {
            original[i] = in.nextInt(); // Input: elements of the original 1D array
        }
        int row = in.nextInt(); // Input: number of rows for the 2D array
        int col = in.nextInt(); // Input: number of columns for the 2D array
        int[][] mat = convertTo2D(original, row, col); // Convert 1D array to 2D array

        // Output the 2D array
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        in.close();
    }

    /**
     * Approach: Convert 1D Array to 2D Array
     * 
     * Intuition:
     * - We are given a 1D array and need to fit its elements into a 2D array with specified rows `m` and columns `n`.
     * - The total number of elements required for the 2D array is `m * n`. If the length of the 1D array does not 
     *   match this total, we return an empty 2D array.
     * - Otherwise, we fill the 2D array row by row, transferring each element from the 1D array into the correct position in the 2D array.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of the original 1D array. We iterate over the original array once to fill the 2D array.
     * 
     * Space Complexity:
     * - O(m * n), where `m * n` is the total size of the 2D array. This is the space used for the resulting 2D array.
     * 
     * @param original The input 1D array.
     * @param m The number of rows in the 2D array.
     * @param n The number of columns in the 2D array.
     * @return The 2D array with the elements of the 1D array.
     */
    public static int[][] convertTo2D(int[] original, int m, int n) {
        // Check if the number of elements in the 1D array matches m * n
        if (m * n != original.length)
            return new int[0][0]; // Return empty array if sizes don't match

        int k = 0;
        int[][] out = new int[m][n]; // Initialize the 2D array with m rows and n columns
        for (int i = 0; i < m; i++) { // Iterate over rows
            for (int j = 0; j < n; j++) { // Iterate over columns
                out[i][j] = original[k++]; // Fill the 2D array with elements from the 1D array
            }
        }
        return out; // Return the filled 2D array
    }
}
