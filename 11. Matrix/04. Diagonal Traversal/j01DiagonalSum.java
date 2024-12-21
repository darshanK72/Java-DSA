/**
 * Problem Statement:
 * 
 *     Given a square matrix `mat` of size `n x n`, return the sum of the diagonal elements.
 *     The matrix has two diagonals: the primary diagonal (from the top left to the bottom right),
 *     and the secondary diagonal (from the top right to the bottom left).
 *     You need to calculate the sum of both diagonals. If both diagonals overlap at the center (in the case of an odd-sized matrix),
 *     count that element only once.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000) representing the size of the matrix.
 *     - A matrix `mat` of size `n x n` where each element satisfies (1 <= mat[i][j] <= 10^5).
 * 
 * Output:
 *     - An integer representing the sum of the diagonals.
 * 
 * Example:
 * 
 *     Input:
 *     3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     25
 * 
 *     Explanation:
 *     The matrix is:
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     The sum of the primary diagonal: 1 + 5 + 9 = 15
 *     The sum of the secondary diagonal: 3 + 5 + 7 = 15
 *     The center element (5) is counted only once, so the final sum is 15 + 15 - 5 = 25.
 */

import java.util.Scanner;

public class j01DiagonalSum {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: size of the matrix
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }

        // Call the solution method
        System.out.println(diagonalSum(matrix));

        in.close();
    }

    /**
     * Approach: Traverse the Matrix for Diagonal Elements
     * 
     * Intuition:
     * - The primary diagonal consists of elements where row index equals column index (mat[i][i]).
     * - The secondary diagonal consists of elements where the column index is the complement of the row index (mat[i][n-i-1]).
     * - The center element of the matrix (when `n` is odd) will be included in both diagonals, so we subtract it once to avoid double-counting.
     * 
     * Time Complexity:
     * - O(n), where `n` is the size of one dimension of the matrix. We traverse each row once to calculate the sum.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of extra space besides the input matrix.
     * 
     * @param mat The input matrix.
     * @return The sum of the diagonals.
     */
    public static int diagonalSum(int[][] mat) {
        int sum = 0;
        int i = 0;
        while (i < mat.length) {
            sum += mat[i][i]; // Add primary diagonal element
            if (i != mat.length - i - 1) {
                sum += mat[i][mat.length - i - 1]; // Add secondary diagonal element, if not the center
            }
            i++;
        }
        return sum;
    }
}
