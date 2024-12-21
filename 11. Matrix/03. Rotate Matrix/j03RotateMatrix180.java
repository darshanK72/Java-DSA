/**
 * Problem Statement:
 * 
 *     Given a square matrix, the task is to rotate the matrix by 180 degrees in place.
 *     - The matrix must be rotated by 180 degrees without using extra space.
 *     - The matrix is square, meaning the number of rows equals the number of columns.
 * 
 * Input:
 *     - An integer `n` representing the size of the matrix.
 *     - A matrix of size `n x n`.
 * 
 * Output:
 *     - The matrix after rotating it by 180 degrees.
 * 
 * Example:
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     9 8 7
 *     6 5 4
 *     3 2 1
 * 
 *     Explanation:
 *     After rotating the matrix by 180 degrees, the elements are flipped both horizontally and vertically.
 */

import java.util.Scanner;

public class j03RotateMatrix180 {

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

        // Rotate the matrix 180 degrees
        rotateMatrix180(matrix);

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
     * Approach 1: Rotate the matrix 180 degrees by flipping horizontally and then vertically
     * 
     * Intuition:
     * - A 180-degree rotation is equivalent to flipping the matrix both horizontally and vertically.
     * - First, flip the matrix horizontally by reversing each row.
     * - Then, flip the matrix vertically by reversing each column.
     * - This in-place approach works because, after flipping both horizontally and vertically, the matrix ends up rotated by 180 degrees.
     * 
     * Time Complexity:
     * - O(n^2), where `n` is the size of the matrix. We perform two passes over the matrix: 
     *   one for horizontal flipping and another for vertical flipping.
     * 
     * Space Complexity:
     * - O(1), as we are modifying the matrix in place without using any additional space.
     * 
     * @param matrix The input matrix to rotate.
     */
    public static void rotateMatrix180(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Flip the matrix horizontally (reverse each row)
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

        // Step 2: Flip the matrix vertically (reverse each column)
        for (int i = 0; i < n; i++) {
            int s = 0;
            int e = n - 1;
            while (s < e) {
                int temp = matrix[s][i];
                matrix[s][i] = matrix[e][i];
                matrix[e][i] = temp;
                s++;
                e--;
            }
        }
    }
}
