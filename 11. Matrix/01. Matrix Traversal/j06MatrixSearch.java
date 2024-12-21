/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of size `m x n`, and an integer `x`, the task is to search for the element `x` 
 *     in the matrix. The matrix contains integers, and each row is sorted in ascending order. The goal 
 *     is to find the index of `x` in the matrix (if it exists). If the element is found, return the 
 *     indices of the element as a 2D array [row, column]. If the element is not found, return [-1, -1].
 * 
 * Input:
 *     - Two integers `m` and `n` (1 <= m, n <= 100), representing the number of rows and columns of the matrix.
 *     - A 2D matrix `matrix` of size `m x n` where each element satisfies (1 <= matrix[i][j] <= 1000).
 *     - An integer `x` (1 <= x <= 1000) representing the element to search for.
 * 
 * Output:
 *     - Print the row and column indices of the element `x` in the matrix. If the element is not present, 
 *       print `-1 -1`.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     5
 *     
 *     Output:
 *     1 1
 * 
 *     Explanation:
 *     - The element `5` is located at row 1, column 1 in the matrix.
 */

import java.util.Scanner;

public class j06MatrixSearch {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the matrix
        int col = in.nextInt(); // Input: number of columns in the matrix
        int[][] matrix = new int[row][col]; // Initialize the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the matrix
            }
        }
        int x = in.nextInt(); // Input: element to search for
        int[] index = matrixSearch(matrix, x); // Call the matrix search function
        System.out.println(index[0] + " " + index[1]); // Output the result
        in.close();
    }

    /**
     * Approach: Brute Force Search
     * 
     * Intuition:
     * - The matrix is a 2D array, and we can search for the element by iterating over every element in the matrix.
     * - For each element, check if it matches the target `x`. If a match is found, return the current row and column indices.
     * - If no match is found after traversing the entire matrix, return [-1, -1].
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. We may need to check every element in the matrix.
     * 
     * Space Complexity:
     * - O(1), since we are only storing a few variables and returning the result directly.
     * 
     * @param mat The 2D matrix.
     * @param x The target element to search for.
     * @return The indices of the element in the matrix, or [-1, -1] if not found.
     */
    public static int[] matrixSearch(int[][] mat, int x) {
        for (int i = 0; i < mat.length; i++) { // Traverse rows
            for (int j = 0; j < mat[0].length; j++) { // Traverse columns
                if (mat[i][j] == x)
                    return new int[] { i, j }; // Element found, return indices
            }
        }
        return new int[] { -1, -1 }; // Element not found
    }
}
