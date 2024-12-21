/**
 * Problem Statement:
 * 
 *     Given two matrices `matrix1` and `matrix2`, the task is to determine if the two matrices are identical. 
 *     Two matrices are said to be identical if they have the same dimensions (number of rows and columns) and 
 *     all corresponding elements are the same.
 * 
 * Input:
 *     - An integer `row1` representing the number of rows in the first matrix.
 *     - An integer `col1` representing the number of columns in the first matrix.
 *     - A 2D matrix `matrix1` of size `row1 x col1`.
 *     - An integer `row2` representing the number of rows in the second matrix.
 *     - An integer `col2` representing the number of columns in the second matrix.
 *     - A 2D matrix `matrix2` of size `row2 x col2`.
 * 
 * Output:
 *     - A boolean value: `true` if the matrices are identical, `false` otherwise.
 * 
 * Example:
 *     Input:
 *     2 2
 *     1 2
 *     3 4
 *     2 2
 *     1 2
 *     3 4
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The two matrices have the same dimensions and all corresponding elements are identical.
 */

import java.util.Scanner;

public class j01IdenticalMatrix {

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

        // Output the result of matrix comparison
        System.out.println(isIdentical(matrix1, matrix2));
        in.close();
    }

    /**
     * Approach: Compare the matrices element by element
     * 
     * Intuition:
     * - To check if two matrices are identical, we first check if they have the same dimensions 
     *   (number of rows and columns). If the dimensions are not the same, we return false immediately.
     * - If the dimensions match, we then compare the elements of the matrices at each corresponding 
     *   position. If any element does not match, we return false.
     * - If we go through the entire matrix without finding any mismatches, we return true indicating 
     *   the matrices are identical.
     * 
     * Time Complexity:
     * - O(n * m), where n is the number of rows and m is the number of columns in the matrices.
     *   We are comparing each element of both matrices.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space for the result.
     * 
     * @param g1 The first matrix.
     * @param g2 The second matrix.
     * @return true if the matrices are identical, false otherwise.
     */
    public static boolean isIdentical(int[][] g1, int[][] g2) {
        // Check if the matrices have the same dimensions
        if (g1.length != g2.length || g1[0].length != g2[0].length)
            return false;

        // Compare each element of the matrices
        for (int i = 0; i < g1.length; i++) {
            for (int j = 0; j < g1[i].length; j++) {
                if (g1[i][j] != g2[i][j])
                    return false;
            }
        }

        return true; // Matrices are identical
    }
}
