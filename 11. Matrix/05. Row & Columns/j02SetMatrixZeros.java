/**
 * Problem Statement:
 * 
 *     Given an `m x n` matrix, if an element in the matrix is `0`, set its entire row and column to `0`. 
 *     Do this in place without using extra space.
 * 
 * Input:
 *     - An integer `m` (1 <= m <= 200) representing the number of rows in the matrix.
 *     - An integer `n` (1 <= n <= 200) representing the number of columns in the matrix.
 *     - A matrix `matrix` of size `m x n` where each element satisfies (-1000 <= matrix[i][j] <= 1000).
 * 
 * Output:
 *     - Modify the matrix in place by setting rows and columns to zero where applicable.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 1 1
 *     1 0 1
 *     1 1 1
 *     
 *     Output:
 *     [1, 0, 1]
 *     [0, 0, 0]
 *     [1, 0, 1]
 * 
 *     Explanation:
 *     The element in the second row and second column is zero. Therefore, the second row and second column are set to zero.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j02SetMatrixZeros {
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

        // Call the solution methods and print the results
        setMatrixZeros(matrix);
        for (int[] arr : matrix) {
            System.out.println(Arrays.toString(arr));
        }

        in.close();
    }

    /**
     * Approach 1: Using Extra Space (Mark the rows and columns using markers)
     * 
     * Intuition:
     * - A straightforward approach is to mark the rows and columns that contain zeros using an auxiliary matrix or data structure.
     * - We can iterate through the matrix and mark the respective rows and columns where the zeros are found.
     * - After marking, we iterate again and set the entire row and column to zero.
     * 
     * Time Complexity:
     * - O(m * n * (m + n)), where `m` is the number of rows and `n` is the number of columns. 
     *   This is because for each zero, we are traversing the entire row and column.
     * 
     * Space Complexity:
     * - O(m * n), where we use an auxiliary matrix to store the marked rows and columns.
     * 
     * @param matrix The input matrix.
     */
    public static void setMatrixZeros(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    for (int k = 0; k < matrix.length; k++) {
                        if (matrix[k][j] != 0) {
                            matrix[k][j] = -1;
                        }
                    }
                    for (int k = 0; k < matrix[0].length; k++) {
                        if (matrix[i][k] != 0) {
                            matrix[i][k] = -1;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == -1)
                    matrix[i][j] = 0;
            }
        }
    }

    /**
     * Approach 2: Optimized Solution Using Row and Column Flags
     * 
     * Intuition:
     * - In this approach, we use two boolean arrays, `rowFlag` and `colFlag`, to keep track of which rows and columns need to be set to zero.
     * - We iterate through the matrix to find zeros and set the corresponding flags.
     * - After that, we iterate through the matrix again and set rows and columns to zero based on the flags.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We iterate through the matrix twice (once to set flags and once to update the matrix).
     * 
     * Space Complexity:
     * - O(m + n), because we use two boolean arrays of size `m` and `n` to store the flags for rows and columns.
     * 
     * @param matrix The input matrix.
     */
    public static void setMatrixZeroesEfficient(int[][] matrix) {
        boolean[] rowFlag = new boolean[matrix.length];
        boolean[] colFlag = new boolean[matrix[0].length];

        // Identify which rows and columns should be set to zero
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    rowFlag[i] = true;
                    colFlag[j] = true;
                }
            }
        }

        // Set the rows and columns to zero based on the flags
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (rowFlag[i] || colFlag[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /**
     * Approach 3: In-place Solution Using First Row and Column as Markers
     * 
     * Intuition:
     * - We can reduce the space complexity by using the first row and first column of the matrix itself as markers for rows and columns that need to be zeroed.
     * - If we encounter a zero at position (i, j), we set `matrix[i][0]` and `matrix[0][j]` to zero.
     * - After scanning the matrix, we update the rest of the matrix based on the markers.
     * 
     * Time Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns. 
     *   We need to iterate through the entire matrix twice (once for marking and once for setting zeros).
     * 
     * Space Complexity:
     * - O(1), since we are using the matrix itself to store the row and column markers, and no additional space is used.
     * 
     * @param matrix The input matrix.
     */
    public static void setMatrixZerosInplace(int[][] matrix) {
        boolean rowZero = false;
        boolean colZero = false;

        // Check if the first row or first column should be zeroed
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0)
                        rowZero = true;
                    if (j == 0)
                        colZero = true;
                }
            }
        }

        // Zero out the cells based on the first row and column
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Handle the first row and first column separately
        if (rowZero) {
            for (int i = 0; i < matrix[0].length; i++) {
                matrix[0][i] = 0;
            }
        }

        if (colZero) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}
