/**
 * Problem Statement:
 * 
 *     Given a 2D matrix of integers, and a set of queries, the task is to find the sum of elements in a sub-matrix 
 *     for each query. A sub-matrix is defined by its top-left and bottom-right corners, and the sum of the elements 
 *     inside this sub-matrix should be computed efficiently.
 * 
 *     To do so, we first calculate a **prefix sum matrix** where each element represents the sum of all elements 
 *     from the top-left corner (0,0) to the current element. This allows us to compute the sum of any sub-matrix in 
 *     constant time using the inclusion-exclusion principle.
 * 
 * Input:
 *     - An integer `row` and `col` representing the number of rows and columns of the matrix.
 *     - A `row x col` matrix where each element satisfies (1 <= matrix[i][j] <= 10^4).
 *     - An integer `t`, the number of queries, followed by `t` sets of four integers `row1`, `col1`, `row2`, and `col2` 
 *       representing the top-left and bottom-right corners of the sub-matrix for which we need to compute the sum.
 * 
 * Output:
 *     - For each query, output the sum of the sub-matrix defined by the provided corners.
 * 
 * Example:
 *     Input:
 *     3 3
 *     1 2 3
 *     4 5 6
 *     7 8 9
 *     2
 *     0 0 1 1
 *     1 1 2 2
 * 
 *     Output:
 *     12
 *     28
 *     
 *     Explanation:
 *     - The first query asks for the sum of the sub-matrix from (0,0) to (1,1), which includes the elements:
 *       [[1, 2], [4, 5]] and their sum is 12.
 *     - The second query asks for the sum of the sub-matrix from (1,1) to (2,2), which includes the elements:
 *       [[5, 6], [8, 9]] and their sum is 28.
 */

import java.util.Scanner;

public class j11MatrixRangeSumQuery {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Number of rows in the matrix
        int col = in.nextInt(); // Number of columns in the matrix
        int[][] matrix = new int[row][col]; // Initialize the matrix
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input matrix elements
            }
        }

        // Step 1: Generate prefix sum matrix
        int[][] prefixSumMatrix = getPrefixSum(matrix);

        // Number of queries
        int t = in.nextInt();

        // Process each query
        while (t > 0) {
            int row1 = in.nextInt(); // Top-left row of the sub-matrix
            int col1 = in.nextInt(); // Top-left column of the sub-matrix
            int row2 = in.nextInt(); // Bottom-right row of the sub-matrix
            int col2 = in.nextInt(); // Bottom-right column of the sub-matrix
            // Output the sum of elements in the sub-matrix
            System.out.println(getGridSum(prefixSumMatrix, row1, col1, row2, col2));
            t--;
        }
        in.close();
    }

    /**
     * Approach 1: Using Prefix Sum Matrix
     * 
     * Intuition:
     * - The key idea is to precompute the prefix sum of the matrix in a new matrix `prefixSumMatrix`. 
     *   Each element in this matrix represents the sum of elements from the top-left corner (0,0) to the current element.
     *   Using this precomputed information, the sum of any sub-matrix can be computed in constant time.
     * - This approach leverages the inclusion-exclusion principle to calculate the sum of the sub-matrix in constant time 
     *   after the prefix sum matrix is constructed.
     * 
     * Time Complexity:
     * - Building the prefix sum matrix takes O(m * n), where `m` and `n` are the dimensions of the original matrix.
     * - Each query is answered in O(1) time using the precomputed prefix sum matrix.
     * 
     * Space Complexity:
     * - O(m * n), where `m` and `n` are the dimensions of the original matrix, for storing the prefix sum matrix.
     * 
     * @param matrix The input 2D matrix of integers.
     * @return The prefix sum matrix.
     */
    public static int[][] getPrefixSum(int[][] matrix) {
        int m = matrix.length; // Number of rows
        int n = matrix[0].length; // Number of columns
        int[][] mat = new int[m + 1][n + 1]; // Initialize the prefix sum matrix

        // Build the prefix sum matrix using dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                mat[i][j] = mat[i][j - 1] + mat[i - 1][j] - mat[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }
        return mat;
    }

    /**
     * Approach 2: Query the Sum of Sub-matrix
     * 
     * Intuition:
     * - Once we have the prefix sum matrix, we can calculate the sum of any sub-matrix in constant time using the 
     *   following formula:
     *     sum = prefixSumMatrix[row2 + 1][col2 + 1] - prefixSumMatrix[row2 + 1][col1] 
     *           - prefixSumMatrix[row1][col2 + 1] + prefixSumMatrix[row1][col1];
     * - The idea is that we can subtract the unnecessary areas from the total sum to isolate the sum of the desired sub-matrix.
     * 
     * Time Complexity:
     * - O(1) per query, as it uses the precomputed prefix sum matrix to directly compute the result.
     * 
     * Space Complexity:
     * - O(1) for each query, as no additional space is used.
     * 
     * @param prefixSumMatrix The precomputed prefix sum matrix.
     * @param row1 The top-left row of the sub-matrix.
     * @param col1 The top-left column of the sub-matrix.
     * @param row2 The bottom-right row of the sub-matrix.
     * @param col2 The bottom-right column of the sub-matrix.
     * @return The sum of the sub-matrix.
     */
    public static int getGridSum(int[][] prefixSumMatrix, int row1, int col1, int row2, int col2) {
        // Using the inclusion-exclusion principle to calculate the sub-matrix sum
        return prefixSumMatrix[row2 + 1][col2 + 1]
                - prefixSumMatrix[row2 + 1][col1]
                - prefixSumMatrix[row1][col2 + 1]
                + prefixSumMatrix[row1][col1];
    }
}
