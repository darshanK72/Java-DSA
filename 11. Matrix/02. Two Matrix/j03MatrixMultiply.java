/**
 * Problem Statement:
 * 
 *     Given two matrices `matrix1` and `matrix2`, the task is to multiply them and return the result. 
 *     Matrix multiplication is only possible if the number of columns of the first matrix is equal to 
 *     the number of rows of the second matrix. The result of multiplying two matrices is a matrix where 
 *     each element is the sum of the products of corresponding elements from the rows of the first matrix 
 *     and columns of the second matrix.
 * 
 * Input:
 *     - An integer `row1` representing the number of rows in the first matrix.
 *     - An integer `col1` representing the number of columns in the first matrix.
 *     - A matrix `matrix1` of size `row1 x col1`.
 *     - An integer `row2` representing the number of rows in the second matrix.
 *     - An integer `col2` representing the number of columns in the second matrix.
 *     - A matrix `matrix2` of size `row2 x col2`.
 * 
 * Output:
 *     - The resulting matrix after multiplication if the multiplication is possible, otherwise print "Cannot multiply".
 * 
 * Example:
 *     Input:
 *     2 3
 *     1 2 3
 *     4 5 6
 *     3 2
 *     7 8
 *     9 10
 *     11 12
 * 
 *     Output:
 *     58 64
 *     139 154
 * 
 *     Explanation:
 *     The result matrix is obtained by multiplying `matrix1` with `matrix2`. The number of columns in `matrix1`
 *     is equal to the number of rows in `matrix2`, so multiplication is possible.
 */

import java.util.Scanner;

public class j03MatrixMultiply {

    public static void main(String args[]) {
        // Reading input for the first matrix
        Scanner in = new Scanner(System.in);
        int row1 = in.nextInt(); // Input: number of rows in the first matrix
        int col1 = in.nextInt(); // Input: number of columns in the first matrix
        int[][] matrix1 = new int[row1][col1];

        // Input: elements of the first matrix
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                matrix1[i][j] = in.nextInt();
            }
        }

        // Reading input for the second matrix
        int row2 = in.nextInt(); // Input: number of rows in the second matrix
        int col2 = in.nextInt(); // Input: number of columns in the second matrix
        int[][] matrix2 = new int[row2][col2];

        // Input: elements of the second matrix
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                matrix2[i][j] = in.nextInt();
            }
        }

        int[][] out = null;

        // Check if matrix multiplication is possible
        if (col1 != row2) {
            System.out.println("Cannot multiply");
        } else {
            // Perform matrix multiplication
            out = multiply(matrix1, matrix2);

            // Output the resulting matrix
            for (int i = 0; i < row1; i++) {
                for (int j = 0; j < col2; j++) {
                    System.out.print(out[i][j] + " ");
                }
                System.out.println();
            }
        }

        in.close();
    }

    /**
     * Approach: Perform Matrix Multiplication
     * 
     * Intuition:
     * - Matrix multiplication is performed by calculating the dot product of each row of the first matrix 
     *   (`matrix1`) with each column of the second matrix (`matrix2`). 
     * - If `matrix1` has dimensions `A x B` and `matrix2` has dimensions `B x C`, the resulting matrix will 
     *   have dimensions `A x C`.
     * - To calculate each element in the resulting matrix, we sum the product of the corresponding elements 
     *   from the row of the first matrix and the column of the second matrix.
     * 
     * Time Complexity:
     * - O(n^3), where n is the number of rows and columns (in the worst case). 
     *   This is because we perform three nested loops: one for rows of `matrix1`, one for columns of `matrix2`, 
     *   and one for the elements in the row of `matrix1` and column of `matrix2`.
     * 
     * Space Complexity:
     * - O(A * C), where `A` is the number of rows in the first matrix and `C` is the number of columns 
     *   in the second matrix. This is the space required to store the resulting matrix.
     * 
     * @param A The first matrix.
     * @param B The second matrix.
     * @return The resulting matrix after multiplication.
     */
    public static int[][] multiply(int A[][], int B[][]) {
        // Initialize result matrix C
        int[][] C = new int[A.length][B[0].length];

        // Perform matrix multiplication
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B[0].length; j++) {
                for (int k = 0; k < A[0].length; k++) {
                    C[i][j] += (A[i][k] * B[k][j]);
                }
            }
        }
        return C; // Return the resulting matrix
    }
}
