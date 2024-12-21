/**
 * Problem Statement:
 * 
 *     Given a 2D matrix, the task is to convert it into a 1D array. The elements of the 2D matrix should be 
 *     stored in the 1D array row by row, from left to right. The order of the elements in the 1D array should 
 *     be such that the first row of the matrix comes first in the 1D array, followed by the second row, and so on.
 * 
 * Input:
 *     - Two integers `row` and `col` (1 <= row, col <= 1000), representing the number of rows and columns of the matrix.
 *     - A 2D matrix of size `row x col` where each element satisfies (1 <= mat[i][j] <= 1000).
 * 
 * Output:
 *     - A 1D array that contains all the elements of the matrix in row-major order.
 * 
 * Example:
 *     Input:
 *     2 3
 *     1 2 3
 *     4 5 6
 *     
 *     Output:
 *     1 2 3 4 5 6
 * 
 *     Explanation:
 *     - The 2D matrix is flattened into a 1D array, with the elements of the matrix stored row by row.
 */

import java.util.Scanner;

public class j08Convert2DTo1DArray {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col]; // Initialize the 2D matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }

        int[] arr = convertTo1D(matrix); // Convert 2D matrix to 1D array

        // Output the 1D array
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " "); // Print the elements of the 1D array
        }
        in.close();
    }

    /**
     * Approach: Convert 2D Array to 1D Array
     * 
     * Intuition:
     * - The matrix is a 2D array, and we want to store its elements in a 1D array.
     * - The elements of the matrix are stored row by row, left to right.
     * - To convert the 2D array into a 1D array, we can use a linear index `i`, and map it to the 2D array indices 
     *   using row and column calculations.
     *   - `row = i / col` gives the corresponding row index, and `col = i % col` gives the column index.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns in the matrix. We need to visit 
     *   each element of the matrix to store it in the 1D array.
     * 
     * Space Complexity:
     * - O(m * n), where `m * n` is the total number of elements in the 2D array. This is the space required for the 
     *   resulting 1D array.
     * 
     * @param mat The input 2D array (matrix).
     * @return The converted 1D array with all elements of the matrix.
     */
    public static int[] convertTo1D(int[][] mat) {
        int l = mat.length * mat[0].length; // Total number of elements in the matrix
        int[] out = new int[l]; // Initialize the 1D array
        for (int i = 0; i < l; i++) { // Iterate over all elements in the matrix
            int row = i / mat[0].length; // Calculate the corresponding row index
            int col = i % mat[0].length; // Calculate the corresponding column index
            out[i] = mat[row][col]; // Map the 2D element to the 1D array
        }
        return out; // Return the 1D array
    }
}
