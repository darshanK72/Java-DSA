/**
 * Problem Statement:
 * 
 *     Given a matrix with dimensions `m x n` where `m` represents the number of rows and 
 *     `n` represents the number of columns, print the matrix in a "snake-like" traversal.
 *     The traversal starts from the first element in the first row and proceeds in a 
 *     "zigzag" pattern where every odd-numbered row is traversed from right to left, 
 *     and every even-numbered row is traversed from left to right.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 100), representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 100), representing the number of columns in the matrix.
 *     - An `m x n` matrix, where each element satisfies (1 <= matrix[i][j] <= 10^5).
 * 
 * Output:
 *     - Print the elements of the matrix in a snake-like traversal.
 * 
 * Example:
 *     Input:
 *     3 4
 *     1 2 3 4
 *     5 6 7 8
 *     9 10 11 12
 *     Output:
 *     1 2 3 4
 *     8 7 6 5
 *     9 10 11 12
 * 
 *     Explanation:
 *     The matrix is:
 *     1 2 3 4
 *     5 6 7 8
 *     9 10 11 12
 *     The snake-like traversal is as follows:
 *     First row: 1 2 3 4 (left to right)
 *     Second row: 8 7 6 5 (right to left)
 *     Third row: 9 10 11 12 (left to right)
 */

import java.util.Scanner;

public class j03SnakeTraversal {
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

        // Perform snake-like traversal of the matrix
        snakeTraverse(matrix);
        in.close();
    }

    /**
     * Approach: Snake-Like Traversal
     * 
     * Intuition:
     * - We need to traverse the matrix in a zigzag or "snake-like" pattern.
     * - For each row, we decide the direction of traversal:
     *   - If the row index is even (0, 2, 4,...), we print the row from left to right.
     *   - If the row index is odd (1, 3, 5,...), we print the row from right to left.
     * - This creates the "snake-like" effect, where the direction alternates as we move down the rows.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns.
     * - We are iterating through each element of the matrix exactly once.
     * 
     * Space Complexity:
     * - O(1) extra space, as we are only using a few extra variables for looping and printing.
     * 
     * @param matrix The matrix to be traversed in snake-like order.
     */
    public static void snakeTraverse(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row % 2 == 0) {
                    // Even row: Traverse from left to right
                    System.out.print(matrix[row][col] + " ");
                } else {
                    // Odd row: Traverse from right to left
                    System.out.print(matrix[row][(matrix[row].length - 1) - col] + " ");
                }
            }
            System.out.println(); // New line after printing each row
        }
    }
}
