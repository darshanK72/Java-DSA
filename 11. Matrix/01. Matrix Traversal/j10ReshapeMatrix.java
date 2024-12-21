/**
 * Problem Statement:
 * 
 *     Given a matrix of size `m x n` and two integers `r` and `c`, the task is to reshape the matrix into a new matrix 
 *     with `r` rows and `c` columns. The reshaped matrix should be filled with all the elements of the original matrix 
 *     in the same row-traversing order as they were.
 *     
 *     If the reshape operation is possible and legal, output the new reshaped matrix; otherwise, output the original matrix.
 * 
 * Input:
 *     - Two integers `m` and `n` (1 <= m, n <= 100) representing the number of rows and columns of the original matrix.
 *     - A matrix of size `m x n` where each element satisfies (1 <= mat[i][j] <= 1000).
 *     - Two integers `r` and `c` (1 <= r, c <= 300), representing the number of rows and columns of the new reshaped matrix.
 * 
 * Output:
 *     - If the reshape operation is possible, return the reshaped matrix; otherwise, return the original matrix.
 * 
 * Example:
 *     Input:
 *     2 2
 *     1 2
 *     3 4
 *     1 4
 *     
 *     Output:
 *     [[1, 2, 3, 4]]
 *     
 *     Explanation:
 *     - The input matrix is a 2x2 matrix, and the task is to reshape it into a 1x4 matrix.
 *     - The result is a matrix with 1 row and 4 columns containing all elements in the same order.
 *     
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     2 4
 *     
 *     Output:
 *     [[1, 2, 3, 4], [5, 6, 7, 8]]
 * 
 *     Explanation:
 *     - The input matrix is a 3x3 matrix, and the task is to reshape it into a 2x4 matrix.
 *     - The reshape operation is possible as the total number of elements is 9, which matches the total size of the new matrix (2 * 4).
 *     - The reshaped matrix is outputted with elements in row-major order.
 */

import java.util.Scanner;

public class j10ReshapeMatrix {

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

        int r = in.nextInt(); // Input: number of rows in the reshaped matrix
        int c = in.nextInt(); // Input: number of columns in the reshaped matrix

        int[][] grid = reshapeMatrix(matrix, r, c); // Perform the reshape operation

        // Output the reshaped matrix
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println(); // Move to next line after each row
        }
        in.close();
    }

    /**
     * Approach: Reshape Matrix
     * 
     * Intuition:
     * - The reshaping operation can be done by traversing the original matrix and filling a new matrix of the desired shape.
     * - We need to ensure that the total number of elements in the reshaped matrix equals the total number of elements 
     *   in the original matrix. If this is not the case, we return the original matrix.
     * - If the reshape is valid, we iterate over the original matrix in row-major order and place the elements into the 
     *   new matrix, adjusting the row and column indices.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns in the original matrix.
     *   We traverse the original matrix once to fill the new matrix.
     * 
     * Space Complexity:
     * - O(r * c), where `r` is the number of rows and `c` is the number of columns in the new matrix.
     *   We allocate space for the new reshaped matrix.
     * 
     * @param mat The input 2D matrix.
     * @param r The number of rows in the reshaped matrix.
     * @param c The number of columns in the reshaped matrix.
     * @return The reshaped matrix, or the original matrix if reshaping is not possible.
     */
    public static int[][] reshapeMatrix(int[][] mat, int r, int c) {
        // If the number of elements does not match, return the original matrix
        if (mat.length * mat[0].length != r * c)
            return mat;

        int[][] out = new int[r][c]; // Initialize the reshaped matrix
        int row = 0, col = 0;

        // Traverse the original matrix and fill the reshaped matrix
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                out[row][col] = mat[i][j];
                col++; // Move to the next column
                if (col == c) { // If we've filled a row, move to the next row
                    col = 0;
                    row++;
                }
            }
        }
        return out; // Return the reshaped matrix
    }
}
