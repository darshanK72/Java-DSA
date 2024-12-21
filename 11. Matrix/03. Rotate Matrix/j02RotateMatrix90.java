/**
 * Problem Statement:
 * 
 *     Given a square matrix, the task is to rotate the matrix 90 degrees clockwise in place.
 *     - The rotation must be done without using any extra space, i.e., it should be done in-place.
 *     - The matrix is square, meaning the number of rows equals the number of columns.
 * 
 * Input:
 *     - An integer `n` representing the size of the matrix.
 *     - A matrix of size `n x n`.
 * 
 * Output:
 *     - The matrix after rotating it by 90 degrees clockwise.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     7 4 1
 *     8 5 2
 *     9 6 3
 * 
 *     Explanation:
 *     After rotating the matrix by 90 degrees clockwise, the rows of the original matrix become columns in the rotated matrix.
 */

import java.util.Scanner;

public class j02RotateMatrix90 {

    public static void main(String args[]) {
        // Reading input for matrix dimensions
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the matrix (n x n)
        int[][] matrix = new int[n][n];

        // Input: elements of the matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        // Rotate the matrix 90 degrees clockwise
        rotateMatrix90(matrix);

        // Output the rotated matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    /**
     * Approach 1: Rotate the matrix by first transposing and then reversing each row
     * 
     * Intuition:
     * - The 90-degree clockwise rotation of a matrix can be done by first transposing the matrix and then reversing each row.
     * - Transposition means swapping rows with columns (i.e., `matrix[i][j]` becomes `matrix[j][i]`).
     * - After transposing, each row needs to be reversed to complete the 90-degree rotation.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the matrix. We perform two passes over the matrix: 
     *   one for transposing and another for reversing each row.
     * 
     * Space Complexity:
     * - O(1), as we are performing the rotation in-place, modifying the matrix directly.
     * 
     * @param matrix The input matrix to rotate.
     */
    public static void rotateMatrix90(int[][] matrix) {
        // Step 1: Transpose the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i; j < matrix.length; j++) {
                // Swap elements to transpose the matrix
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row to complete the 90-degree rotation
        int s = 0;
        int e = matrix.length - 1;
        while (s < e) {
            for (int i = 0; i < matrix.length; i++) {
                // Swap elements to reverse each row
                int temp = matrix[i][e];
                matrix[i][e] = matrix[i][s];
                matrix[i][s] = temp;
            }
            s++;
            e--;
        }
    }
}
