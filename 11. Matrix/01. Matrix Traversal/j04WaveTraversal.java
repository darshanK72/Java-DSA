/**
 * Problem Statement:
 * 
 *     Given a matrix with dimensions `m x n`, perform a "wave-like" traversal on it.
 *     The traversal starts from the top-left of the matrix and moves down in the first column.
 *     Once it reaches the bottom, it moves right to the next column and traverses upward.
 *     This pattern continues across all columns of the matrix.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 100), representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 100), representing the number of columns in the matrix.
 *     - An `m x n` matrix, where each element satisfies (1 <= matrix[i][j] <= 10^5).
 * 
 * Output:
 *     - Print the elements of the matrix in a wave-like traversal.
 * 
 * Example:
 *     Input:
 *     3 4
 *     1 2 3 4
 *     5 6 7 8
 *     9 10 11 12
 *     Output:
 *     1 5 9
 *     10 6 2
 *     3 7 11
 *     12 8 4
 * 
 *     Explanation:
 *     The matrix is:
 *     1  2  3  4
 *     5  6  7  8
 *     9 10 11 12
 *     The wave-like traversal is as follows:
 *     First column: 1 5 9 (top to bottom)
 *     Second column: 10 6 2 (bottom to top)
 *     Third column: 3 7 11 (top to bottom)
 *     Fourth column: 12 8 4 (bottom to top)
 */

import java.util.Scanner;

public class j04WaveTraversal {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: the number of rows in the matrix
        int col = in.nextInt(); // Input: the number of columns in the matrix
        int[][] matrix = new int[row][col];

        // Filling the matrix with values
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: each element of the matrix
            }
        }

        // Perform wave-like traversal of the matrix
        waveTraversal(matrix);
        in.close();
    }

    /**
     * Approach: Wave-Like Traversal of Matrix
     * 
     * Intuition:
     * - In a wave-like traversal, we need to iterate through each column.
     * - For each column:
     *   - If the column index is even (0, 2, 4,...), we traverse from top to bottom.
     *   - If the column index is odd (1, 3, 5,...), we traverse from bottom to top.
     * - This alternation creates a wave-like traversal across the matrix.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns.
     * - We are iterating through all the elements of the matrix exactly once.
     * 
     * Space Complexity:
     * - O(1), as we only use a few extra variables for looping and printing. 
     *   The space used is constant.
     * 
     * @param matrix The matrix to be traversed in wave-like order.
     */
    public static void waveTraversal(int[][] matrix) {
        for (int col = 0; col < matrix[0].length; col++) {
            if (col % 2 == 0) {
                // Even column: Traverse from top to bottom
                for (int row = 0; row < matrix.length; row++) {
                    System.out.print(matrix[row][col] + " ");
                }
            } else {
                // Odd column: Traverse from bottom to top
                for (int row = matrix.length - 1; row >= 0; row--) {
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println(); // New line after printing each column
        }
    }
}
