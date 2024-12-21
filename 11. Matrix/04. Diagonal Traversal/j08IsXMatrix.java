/**
 * Problem Statement:
 * 
 *     An "X Matrix" is a square matrix in which all the elements outside of the main diagonals (top-left to bottom-right and top-right to bottom-left)
 *     must be zero, and all the elements on the diagonals must be non-zero.
 * 
 *     Given a square matrix, determine if it is an "X Matrix".
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100) representing the size of the square matrix.
 *     - A matrix `grid` of size `n x n` where each element satisfies (0 <= grid[i][j] <= 1000).
 * 
 * Output:
 *     - Return `true` if the matrix is an X Matrix, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 0 1
 *     0 1 0
 *     1 0 1
 *     
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The elements on the diagonals are non-zero: [1, 1, 1] (main diagonal), and [1, 1, 1] (anti-diagonal).
 *     All elements outside the diagonals are zero.
 *     Therefore, the matrix is an X Matrix.
 */

import java.util.Scanner;

public class j08IsXMatrix {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows (square matrix)
        int col = in.nextInt(); // Input: number of columns (square matrix)
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: matrix elements
            }
        }

        // Call the solution method and print the result
        System.out.println(checkXMatrix(matrix));

        in.close();
    }

    /**
     * Approach 1: Check Diagonals and Non-Diagonals for Validity
     * 
     * Intuition:
     * - The matrix is an "X Matrix" if:
     *   1. The elements on the main diagonal (where i == j) and anti-diagonal (where i + j == n - 1) must be non-zero.
     *   2. All other elements (not on the diagonals) must be zero.
     * - We can iterate through each element of the matrix and check if these conditions hold.
     * 
     * Time Complexity:
     * - O(n^2) where `n` is the number of rows (or columns) in the matrix. We need to check each element of the matrix.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used except for a few variables.
     * 
     * @param grid The input matrix to be checked.
     * @return True if the matrix is an X Matrix, otherwise false.
     */
    public static boolean checkXMatrix(int[][] grid) {
        int n = grid.length; // The size of the square matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Check if the current element is on a diagonal
                if (i == j || i + j == n - 1) {
                    if (grid[i][j] == 0)
                        return false; // Diagonal element should not be zero
                } else {
                    if (grid[i][j] != 0)
                        return false; // Non-diagonal element should be zero
                }
            }
        }
        return true; // If all conditions are met, return true
    }
}
