/*-
 * Problem Statement:
 *
 *     A peak element in a 2D grid is an element that is strictly greater than its adjacent elements. 
 *     A 2D grid element is considered adjacent if it is directly above, below, left, or right.
 * 
 *     You need to find a peak element in the given 2D grid and return its position.
 * 
 * Input:
 *     - Two integers m and n (1 <= m, n <= 1000), the number of rows and columns of the grid.
 *     - A 2D matrix with m rows and n columns.
 * 
 * Output:
 *     - Return an array of two integers, representing the row and column index of the peak element.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 4 3
 *     2 5 6
 *     3 6 9
 *     Output:
 *     [1, 2]
 * 
 *     Explanation:
 *     The peak element is 6 at position (1, 2).
 */

import java.util.Scanner;

public class j07FindPeakElementII {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Number of rows
        int n = in.nextInt(); // Number of columns
        int[][] arr = new int[m][n];

        // Reading the matrix
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        // Output the result of both approaches
        System.out.println(java.util.Arrays.toString(findPeakGrid(arr)));
        System.out.println(java.util.Arrays.toString(findPeakGridEfficient(arr)));
        in.close();
    }

    /*-
     * Brute force approach to find the peak element.
     * We iterate through all elements and check if they are greater than their neighbors.
     *
     * @param mat The input 2D grid.
     * @return The indices of the peak element.
     */
    public static int[] findPeakGrid(int[][] mat) {
        // Iterate through each element in the matrix
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                int point = mat[i][j];
                int top = i == 0 ? -1 : mat[i - 1][j]; // Top neighbor
                int bottom = i == mat.length - 1 ? -1 : mat[i + 1][j]; // Bottom neighbor
                int left = j == 0 ? -1 : mat[i][j - 1]; // Left neighbor
                int right = j == mat[0].length - 1 ? -1 : mat[i][j + 1]; // Right neighbor

                // Check if the current element is greater than its neighbors
                if (point > top && point > bottom && point > left && point > right) {
                    return new int[] { i, j }; // Return the peak element's position
                }
            }
        }
        return new int[] { -1, -1 }; // If no peak is found (not expected for valid input)
    }

    /*-
     * Optimized approach using a binary search strategy on the columns.
     * We choose a middle column, find the maximum element in that column,
     * and then check its neighbors to decide which direction to move.
     *
     * @param mat The input 2D grid.
     * @return The indices of the peak element.
     */
    public static int[] findPeakGridEfficient(int[][] mat) {
        int s = 0; // Start column
        int e = mat[0].length - 1; // End column

        while (s <= e) {
            int mid = s + (e - s) / 2; // Middle column
            int maxIndex = getMaxIndex(mat, mid); // Find the row index of the max element in the middle column

            // Check the neighbors of the peak element
            int left = mid == 0 ? -1 : mat[maxIndex][mid - 1]; // Left neighbor
            int right = mid == mat[0].length - 1 ? -1 : mat[maxIndex][mid + 1]; // Right neighbor

            // If the current element is greater than both neighbors, it's the peak
            if (mat[maxIndex][mid] > left && mat[maxIndex][mid] > right) {
                return new int[] { maxIndex, mid }; // Return the peak element's position
            }
            // If the left neighbor is greater, search in the left half
            else if (mat[maxIndex][mid] < left) {
                e = mid - 1;
            }
            // If the right neighbor is greater, search in the right half
            else {
                s = mid + 1;
            }
        }
        return new int[] { -1, -1 }; // If no peak is found (not expected for valid input)
    }

    /*-
     * Helper function to find the index of the maximum element in a given column.
     *
     * @param mat The input 2D grid.
     * @param col The column to search.
     * @return The row index of the maximum element in the column.
     */
    public static int getMaxIndex(int[][] mat, int col) {
        int maxValue = Integer.MIN_VALUE;
        int maxIndex = -1;

        // Traverse the column to find the maximum element
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][col] > maxValue) {
                maxValue = mat[i][col];
                maxIndex = i; // Store the index of the max element
            }
        }
        return maxIndex;
    }
}
