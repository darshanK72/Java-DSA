/**
 * Problem Statement:
 * 
 *     Given a matrix of size `m x n`, return all elements of the matrix in spiral order.
 * 
 * Input:
 *     - Two integers `row` and `col`, where `1 <= row, col <= 10^4`, representing the number of rows and columns of the matrix.
 *     - An `m x n` matrix where each element `matrix[i][j]` satisfies `1 <= matrix[i][j] <= 10^5`.
 * 
 * Output:
 *     - A list of integers representing the elements of the matrix in spiral order.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 * 
 *     Output:
 *     [1, 2, 3, 6, 9, 8, 7, 4, 5]
 * 
 *     Explanation:
 *     The elements are traversed in the following spiral order:
 *     1 -> 2 -> 3 -> 6 -> 9 -> 8 -> 7 -> 4 -> 5
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01SpiralMatrixI {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows
        int col = in.nextInt(); // Input: number of columns
        int[][] matrix = new int[row][col];

        // Input: elements of the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt();
            }
        }

        // Call the method for spiral order
        System.out.println(spiralOrder1(matrix));

        in.close();
    }

    /**
     * Approach 1: Iterative Spiral Traversal
     * 
     * Intuition:
     * - We initialize four pointers (rowStart, rowEnd, colStart, colEnd) to define the boundaries of the matrix.
     * - Start from the outermost boundary and move inwards in a spiral fashion:
     *   - Traverse the top row (left to right).
     *   - Traverse the rightmost column (top to bottom).
     *   - Traverse the bottom row (right to left).
     *   - Traverse the leftmost column (bottom to top).
     * - After each traversal, we update the respective boundaries and continue the process until all elements are traversed.
     * 
     * Time Complexity:
     * - O(m * n), where m is the number of rows and n is the number of columns. We traverse all elements of the matrix once.
     * 
     * Space Complexity:
     * - O(m * n), because we store all the elements of the matrix in a list to return them.
     * 
     * @param matrix The input matrix of size m x n.
     * @return A list of integers representing the elements of the matrix in spiral order.
     */
    public static List<Integer> spiralOrder1(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rowStart = 0;
        int rowEnd = m - 1;
        int colStart = 0;
        int colEnd = n - 1;

        ArrayList<Integer> out = new ArrayList<Integer>();

        // Start traversing the matrix in spiral order
        while (true) {
            // Traverse from left to right
            for (int i = colStart; i <= colEnd; i++) {
                out.add(matrix[rowStart][i]);
            }
            rowStart++;
            if (rowStart > rowEnd)
                break;

            // Traverse from top to bottom
            for (int j = rowStart; j <= rowEnd; j++) {
                out.add(matrix[j][colEnd]);
            }
            colEnd--;
            if (colStart > colEnd)
                break;

            // Traverse from right to left
            for (int i = colEnd; i >= colStart; i--) {
                out.add(matrix[rowEnd][i]);
            }
            rowEnd--;
            if (rowStart > rowEnd)
                break;

            // Traverse from bottom to top
            for (int j = rowEnd; j >= rowStart; j--) {
                out.add(matrix[j][colStart]);
            }
            colStart++;
            if (colStart > colEnd)
                break;
        }

        return out;
    }

    /**
     * Approach 2: Optimized Spiral Traversal
     * 
     * Intuition:
     * - This approach optimizes the conditions for checking whether the current row or column has been completely traversed.
     * - Instead of using `break` to exit the loop, we explicitly check if the rowStart is less than or equal to rowEnd, and if colStart is less than or equal to colEnd before continuing with the traversals.
     * - This reduces the number of checks in the main loop, and the structure remains similar to the first approach.
     * 
     * Time Complexity:
     * - O(m * n), where m is the number of rows and n is the number of columns. We traverse all elements of the matrix once.
     * 
     * Space Complexity:
     * - O(m * n), for storing the elements of the matrix in a list to return them.
     * 
     * @param matrix The input matrix of size m x n.
     * @return A list of integers representing the elements of the matrix in spiral order.
     */
    public List<Integer> spiralOrder2(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int rowStart = 0;
        int rowEnd = m - 1;
        int colStart = 0;
        int colEnd = n - 1;

        ArrayList<Integer> out = new ArrayList<Integer>();

        // Start traversing the matrix in spiral order
        while (rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse from left to right
            for (int i = colStart; i <= colEnd; i++) {
                out.add(matrix[rowStart][i]);
            }
            rowStart++;

            // Traverse from top to bottom
            for (int j = rowStart; j <= rowEnd; j++) {
                out.add(matrix[j][colEnd]);
            }
            colEnd--;

            if (rowStart <= rowEnd) {
                // Traverse from right to left
                for (int i = colEnd; i >= colStart; i--) {
                    out.add(matrix[rowEnd][i]);
                }
                rowEnd--;
            }

            if (colStart <= colEnd) {
                // Traverse from bottom to top
                for (int j = rowEnd; j >= rowStart; j--) {
                    out.add(matrix[j][colStart]);
                }
                colStart++;
            }
        }

        return out;
    }
}
