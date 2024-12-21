/**
 * Problem Statement:
 * 
 *     A matrix is called Toeplitz if every diagonal from top-left to bottom-right has the same element.
 *     Given a matrix, check if it is a Toeplitz matrix.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 1000) representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 1000) representing the number of columns in the matrix.
 *     - A matrix `mat` of size `row x col` where each element satisfies (1 <= mat[i][j] <= 1000).
 * 
 * Output:
 *     - Return `true` if the matrix is a Toeplitz matrix, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 1 2
 *     5 4 1
 *     
 *     Output:
 *     true
 * 
 *     Explanation:
 *     The diagonals of the matrix are:
 *     Diagonal 1: [1, 1, 1] - All elements are the same.
 *     Diagonal 2: [2, 2] - All elements are the same.
 *     Diagonal 3: [3] - Single element.
 *     Diagonal 4: [4] - Single element.
 *     Diagonal 5: [5] - Single element.
 *     Since all diagonals have the same element, the matrix is a Toeplitz matrix.
 */

import java.util.Scanner;

public class j06ToeplitzMatrix {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: matrix elements
            }
        }

        // Call the solution method and print the result
        System.out.println(isToeplitzMatrix(matrix));

        in.close();
    }

    /**
     * Approach 1: Check Diagonals for Consistency
     * 
     * Intuition:
     * - A matrix is Toeplitz if every diagonal from top-left to bottom-right has identical elements.
     * - To check this, we can iterate over each diagonal, starting from each element of the first row and 
     *   the first column, and ensure all elements in that diagonal are the same.
     * - If any element in a diagonal differs from the first element of the diagonal, return false. Otherwise, 
     *   return true at the end.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. We visit each element 
     *   in the matrix once, and each diagonal is checked in constant time.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used except for a few variables.
     * 
     * @param mat The input matrix to be checked.
     * @return True if the matrix is a Toeplitz matrix, otherwise false.
     */
    public static boolean isToeplitzMatrix(int[][] mat) {
        int m = mat.length; // Number of rows
        int n = mat[0].length; // Number of columns
        for (int d = 1 - n; d <= m - 1; d++) {
            int row = d <= 0 ? 0 : d;
            int col = d <= 0 ? Math.abs(d) : 0;
            int x = mat[row][col]; // First element of the diagonal
            while (row < m && col < n) {
                if (mat[row][col] != x)
                    return false; // If any element differs, it's not a Toeplitz matrix
                row++;
                col++;
            }
        }
        return true; // If no discrepancies were found, it is a Toeplitz matrix
    }
}
