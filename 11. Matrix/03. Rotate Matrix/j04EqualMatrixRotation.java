/**
 * Problem Statement:
 * 
 *     You are given two matrices, mat and target, of the same size. You need to determine if 
 *     matrix target can be obtained by rotating matrix mat by 90, 180, or 270 degrees.
 *     Implement a function `findRotation` that returns true if mat can be rotated to become target, 
 *     otherwise return false.
 * 
 * Input:
 *     - Two integers `row1`, `col1` (1 <= row1, col1 <= 10^3) representing the dimensions of the first matrix `mat`.
 *     - A matrix `mat` of size `row1 x col1`, where each element satisfies (1 <= mat[i][j] <= 10^5).
 *     - Two integers `row2`, `col2` (1 <= row2, col2 <= 10^3) representing the dimensions of the second matrix `target`.
 *     - A matrix `target` of size `row2 x col2`, where each element satisfies (1 <= target[i][j] <= 10^5).
 * 
 * Output:
 *     - A boolean value: true if mat can be rotated to become target, false otherwise.
 * 
 * Example:
 * 
 *     Input:
 *     2 2
 *     1 2
 *     3 4
 *     2 2
 *     3 1
 *     4 2
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     Matrix mat can be rotated by 90 degrees to match the target matrix:
 *     Original matrix:    Rotated 90 degrees:
 *     1  2               3  4
 *     3  4               1  2
 * 
 *     After a 90-degree rotation, mat becomes the target matrix.
 */

import java.util.Scanner;

public class j04EqualMatrixRotation {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row1 = in.nextInt();
        int col1 = in.nextInt();
        int[][] matrix1 = new int[row1][col1];
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col1; j++) {
                matrix1[i][j] = in.nextInt(); // Input: elements of matrix1
            }
        }

        int row2 = in.nextInt();
        int col2 = in.nextInt();
        int[][] matrix2 = new int[row2][col2];
        for (int i = 0; i < row2; i++) {
            for (int j = 0; j < col2; j++) {
                matrix2[i][j] = in.nextInt(); // Input: elements of matrix2
            }
        }

        // Call the solution method
        System.out.println(findRotation(matrix1, matrix2));

        in.close();
    }

    /**
     * Approach: Check All 4 Rotations
     * 
     * Intuition:
     * - We can rotate the matrix in 4 ways: 0, 90, 180, and 270 degrees.
     * - We check for each rotation if it matches the target matrix. If any match is found, we return true.
     * - The rotation is achieved by performing a transpose and then reversing each row.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of one side of the matrix. We check each element in the matrix for each of the 4 rotations.
     * 
     * Space Complexity:
     * - O(1) as the matrix is modified in place without extra space (besides the input matrices).
     * 
     * @param mat The input matrix to check.
     * @param target The target matrix we are comparing to.
     * @return true if any rotation of mat matches target, false otherwise.
     */
    public static boolean findRotation(int[][] mat, int[][] target) {
        int t = 4; // We will check for 0, 90, 180, and 270-degree rotations
        while (t-- > 0) {
            if (isSame(mat, target)) {
                return true;
            }
            rotateMatrix90(mat); // Rotate the matrix by 90 degrees
        }
        return false;
    }

    /**
     * Helper Method: isSame
     * 
     * Intuition:
     * - This method checks if the two matrices mat and target are the same.
     * - If any mismatch is found between the elements at any position, we return false.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of one side of the matrix. We compare every element of the two matrices.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used, besides the input matrices.
     * 
     * @param mat The first matrix to compare.
     * @param target The second matrix to compare.
     * @return true if both matrices are the same, false otherwise.
     */
    private static boolean isSame(int[][] mat, int[][] target) {
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                if (mat[i][j] != target[i][j]) {
                    return false; // If any element doesn't match, return false
                }
            }
        }
        return true; // All elements matched, return true
    }

    /**
     * Helper Method: rotateMatrix90
     * 
     * Intuition:
     * - This method rotates the given matrix by 90 degrees clockwise.
     * - We first transpose the matrix, then reverse each row to get the desired rotation.
     * 
     * Time Complexity:
     * - O(n^2), where n is the length of one side of the matrix. We process each element for both the transpose and row reversal.
     * 
     * Space Complexity:
     * - O(1), as the matrix is rotated in place without using any extra space.
     * 
     * @param matrix The matrix to rotate by 90 degrees.
     */
    private static void rotateMatrix90(int[][] matrix) {
        int n = matrix.length;

        // Transpose the matrix (swap rows and columns)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Reverse each row to achieve 90-degree rotation
        for (int i = 0; i < n; i++) {
            int s = 0;
            int e = n - 1;
            while (s < e) {
                int temp = matrix[i][s];
                matrix[i][s] = matrix[i][e];
                matrix[i][e] = temp;
                s++;
                e--;
            }
        }
    }
}
