/**
 * Problem Statement:
 * 
 *     Given a matrix with dimensions `m x n` where `m` represents the number of rows and 
 *     `n` represents the number of columns, print the elements of the matrix column-wise.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 100), representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 100), representing the number of columns in the matrix.
 *     - An `m x n` matrix, where each element satisfies (1 <= matrix[i][j] <= 10^5).
 * 
 * Output:
 *     - Print the elements of the matrix column-wise. Each column is printed in a separate line.
 * 
 * Example:
 *     Input:
 *     2 3
 *     1 2 3
 *     4 5 6
 *     Output:
 *     1 4
 *     2 5
 *     3 6
 * 
 *     Explanation:
 *     The matrix is:
 *     1 2 3
 *     4 5 6
 *     The elements are printed column by column: 
 *     First column: 1, 4 
 *     Second column: 2, 5
 *     Third column: 3, 6
 */

import java.util.Scanner;

public class j02PrintMatrixColWise {
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

        // Print matrix column-wise
        printMatrixColWise(matrix, row, col);
        in.close();
    }

    /**
     * Approach: Iterative Column-Wise Print
     * 
     * Intuition:
     * - We can iterate over each column and for each column, print all its elements row by row.
     * - This ensures that elements are printed column by column.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     * - We are iterating through each element of the matrix once.
     * 
     * Space Complexity:
     * - O(1) extra space, as we are only using a few extra variables for looping.
     * 
     * @param mat The matrix to be printed.
     * @param m The number of rows in the matrix.
     * @param n The number of columns in the matrix.
     */
    public static void printMatrixColWise(int[][] mat, int m, int n) {
        for (int col = 0; col < n; col++) {
            for (int row = 0; row < m; row++) {
                System.out.print(mat[row][col] + " ");
            }
            System.out.println();
        }
    }
}
