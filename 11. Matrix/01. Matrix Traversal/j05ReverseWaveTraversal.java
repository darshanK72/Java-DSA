/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of size `m x n`, the task is to perform a reverse wave traversal of the matrix. 
 *     The traversal starts from the last column and moves leftward. In each column, the elements are printed in 
 *     a "wave-like" fashion. For an even-indexed column, the elements are printed from top to bottom. For an 
 *     odd-indexed column, the elements are printed from bottom to top.
 * 
 * Input:
 *     - Two integers `m` and `n` (1 <= m, n <= 100), representing the number of rows and columns of the matrix.
 *     - A 2D matrix `matrix` of size `m x n` where each element satisfies (1 <= matrix[i][j] <= 100).
 * 
 * Output:
 *     - Print the elements of the matrix in reverse wave traversal order.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     
 *     Output:
 *     7 4 1 
 *     2 5 8 
 *     9 6 3
 * 
 *     Explanation:
 *     - First column (3rd column in zero-index): print from bottom to top → 7 4 1
 *     - Second column: print from top to bottom → 2 5 8
 *     - Third column: print from bottom to top → 9 6 3
 */

import java.util.Scanner;

public class j05ReverseWaveTraversal {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col]; // Initialize the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }

        // Call the reverse wave traversal function
        reverseWaveTraversal(matrix);
        in.close();
    }

    /**
     * Approach: Reverse Wave Traversal
     * 
     * Intuition:
     * - We need to traverse the matrix starting from the last column and move towards the first column.
     * - For each column, if the column index is even, we traverse the elements from top to bottom.
     * - If the column index is odd, we traverse the elements from bottom to top.
     * - We handle the traversal based on the column parity (even or odd) and ensure we print the values in the required order.
     * 
     * Time Complexity:
     * - O(m * n), where m is the number of rows and n is the number of columns. We are visiting each element exactly once.
     * 
     * Space Complexity:
     * - O(1), no extra space is used beyond the input matrix.
     * 
     * @param matrix The 2D matrix representing the input.
     */
    public static void reverseWaveTraversal(int[][] matrix) {
        for (int col = matrix[0].length - 1; col >= 0; col--) { // Traverse columns from right to left
            if (col % 2 == 0) { // If column is even, traverse top to bottom
                for (int row = 0; row < matrix.length; row++) {
                    System.out.print(matrix[row][col] + " ");
                }
            } else { // If column is odd, traverse bottom to top
                for (int row = matrix.length - 1; row >= 0; row--) {
                    System.out.print(matrix[row][col] + " ");
                }
            }
            System.out.println(); // New line after each column
        }
    }
}
