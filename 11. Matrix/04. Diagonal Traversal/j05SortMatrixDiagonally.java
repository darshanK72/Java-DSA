/**
 * Problem Statement:
 * 
 *     Given a matrix of integers, sort each of the diagonals of the matrix in ascending order and return the result.
 *     A diagonal is a list of elements that are at the same distance from the top-left corner, i.e., the elements where 
 *     the row index and column index differ by the same amount.
 * 
 * Input:
 *     - An integer `row` (1 <= row <= 100) representing the number of rows in the matrix.
 *     - An integer `col` (1 <= col <= 100) representing the number of columns in the matrix.
 *     - A matrix `mat` of size `row x col` where each element satisfies (1 <= mat[i][j] <= 1000).
 * 
 * Output:
 *     - Return the matrix after sorting each diagonal in ascending order.
 * 
 * Example:
 *     Input:
 *     3 3
 *     3 3 1
 *     2 2 4
 *     6 5 7
 *     
 *     Output:
 *     1 2 3
 *     2 3 4
 *     5 6 7
 * 
 *     Explanation:
 *     Diagonal 1 (starting at (0, 0)): [3, 2, 7] => sorted to [2, 3, 7]
 *     Diagonal 2 (starting at (0, 1)): [3, 4] => sorted to [3, 4]
 *     Diagonal 3 (starting at (0, 2)): [1] => no sorting needed
 *     Diagonal 4 (starting at (1, 0)): [2, 5] => sorted to [2, 5]
 *     Diagonal 5 (starting at (1, 1)): [2, 7] => sorted to [2, 7]
 *     Diagonal 6 (starting at (2, 0)): [6] => no sorting needed
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class j05SortMatrixDiagonally {
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
        int[][] result = diagonalSort(matrix);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

        in.close();
    }

    /**
     * Approach 1: Sorting Diagonals Using ArrayList
     * 
     * Intuition:
     * - For each diagonal, collect the elements in an ArrayList, sort the list, and then reassign the sorted elements
     *   back to their respective positions in the matrix.
     * - This approach works by processing each diagonal one by one, starting from the top-left corner.
     * 
     * Time Complexity:
     * - O(m * n log(min(m, n))) where `m` and `n` are the number of rows and columns in the matrix. Sorting each 
     *   diagonal takes O(k log k) time where `k` is the length of the diagonal, and the number of diagonals is O(m + n).
     * 
     * Space Complexity:
     * - O(min(m, n)) for storing the elements of a diagonal in the ArrayList.
     * 
     * @param mat The input matrix to be sorted diagonally.
     * @return The matrix after sorting each diagonal.
     */
    public static int[][] diagonalSort(int[][] mat) {
        int m = mat.length; // Number of rows
        int n = mat[0].length; // Number of columns
        for (int d = 1 - n; d <= m - 1; d++) {
            ArrayList<Integer> out = new ArrayList<Integer>(); // To hold diagonal elements
            int row = d <= 0 ? 0 : d;
            int col = d <= 0 ? Math.abs(d) : 0;

            // Collect diagonal elements
            while (row < m && col < n) {
                out.add(mat[row][col]);
                row++;
                col++;
            }

            // Sort the diagonal
            Collections.sort(out);

            // Reassign sorted values to the diagonal positions
            int i = 0;
            row = d <= 0 ? 0 : d;
            col = d <= 0 ? Math.abs(d) : 0;
            while (row < m && col < n) {
                mat[row][col] = out.get(i++);
                row++;
                col++;
            }
        }
        return mat; // Return the sorted matrix
    }
}
