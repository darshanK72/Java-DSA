/**
 * Problem Statement:
 * 
 *     Given a matrix `matrix` of size `row x col`, the task is to find the transpose of the matrix.
 *     - The transpose of a matrix is obtained by swapping the rows and columns. Specifically, 
 *       the element at position `matrix[i][j]` becomes `matrix[j][i]` in the transposed matrix.
 *     - If the matrix is square (i.e., number of rows equals number of columns), the transpose is done in-place.
 *     - If the matrix is rectangular, a new matrix is created for the transpose.
 * 
 * Input:
 *     - An integer `row` representing the number of rows in the matrix.
 *     - An integer `col` representing the number of columns in the matrix.
 *     - A matrix of size `row x col`.
 * 
 * Output:
 *     - The transposed matrix.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     1 4 7
 *     2 5 8
 *     3 6 9
 * 
 *     Explanation:
 *     The transposed matrix is obtained by swapping rows with columns, so:
 *     - First row becomes the first column, second row becomes the second column, and so on.
 */

import java.util.Scanner;

public class j01TransposeMatrix {

    public static void main(String args[]) {
        // Reading input for the matrix dimensions
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col];

        // Input: elements of the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        // If the matrix is square, transpose in-place, else create a new matrix for the
        // transpose
        if (row == col) {
            // In-place transpose for square matrix
            transposeMatrixRactangle(matrix);
            // Output the transposed matrix
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    System.out.print(matrix[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            // For rectangular matrix, return the transposed matrix
            int[][] mat = transposeMatrix(matrix);
            // Output the transposed matrix
            for (int i = 0; i < mat.length; i++) {
                for (int j = 0; j < mat[0].length; j++) {
                    System.out.print(mat[i][j] + " ");
                }
                System.out.println();
            }
        }

        in.close();
    }

    /**
     * Approach 1: Create a new matrix for the transpose of a rectangular matrix
     * 
     * Intuition:
     * - For a rectangular matrix, the transposed matrix will have dimensions where the number of rows is equal to the number of columns 
     *   of the original matrix, and vice versa. To create the transpose, we iterate through each element of the original matrix and place 
     *   it at the correct position in the new matrix (i.e., swap rows and columns).
     * 
     * Time Complexity:
     * - O(row * col), where `row` and `col` are the number of rows and columns in the original matrix. We visit each element once.
     * 
     * Space Complexity:
     * - O(col * row), as we create a new matrix for the transposed result.
     * 
     * @param matrix The input matrix.
     * @return The transposed matrix.
     */
    public static int[][] transposeMatrix(int[][] matrix) {
        int[][] out = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                out[i][j] = matrix[j][i];
            }
        }
        return out;
    }

    /**
     * Approach 2: In-place transpose for square matrices
     * 
     * Intuition:
     * - For a square matrix (where rows = columns), we can transpose the matrix in place. 
     *   This is done by swapping the elements at position `matrix[i][j]` with `matrix[j][i]`, 
     *   but we only need to perform the swap for elements above the diagonal to avoid reversing the process.
     * 
     * Time Complexity:
     * - O(row^2), since we are iterating through half of the elements in the matrix (above the diagonal) and swapping them.
     * 
     * Space Complexity:
     * - O(1), since we are performing the operation in-place and not using extra space.
     * 
     * @param matrix The square matrix to transpose in place.
     */
    public static void transposeMatrixRactangle(int[][] matrix) {
        // Iterate through the upper triangular part of the matrix and swap elements
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
