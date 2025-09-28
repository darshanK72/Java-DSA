/**
 * LeetCode 221. Maximal Square
 * 
 * Problem Statement:
 *     Given an m x n binary matrix filled with 0's and 1's, find the largest 
 *     square containing only 1's and return its area.
 * 
 * Input:
 *     - matrix (char[][]): Binary matrix with '0's and '1's (1 <= m,n <= 300)
 * 
 * Output:
 *     - int: Area of the largest square containing only 1's
 * 
 * Example:
 *     Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],
 *                     ["1","1","1","1","1"],["1","0","0","1","0"]]
 *     Output: 4
 * 
 *     Explanation:
 *     The largest square is:
 *     1 1
 *     1 1
 *     Located at bottom-right of the matrix with side length 2, area = 4
 */

import java.util.Arrays;

public class j10MaximalSquare {

    /**
     * Approach 1: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - For each cell, we need to find the largest square that can be formed 
     *   with this cell as the bottom-right corner
     * - A square of size 's' at position (i,j) means all cells in the 
     *   submatrix from (i-s+1,j-s+1) to (i,j) are '1'
     * - We can use recursion with memoization to avoid recomputing the same 
     *   subproblems
     * - The size of square at (i,j) depends on the minimum of squares formed 
     *   by its three neighbors: left, top, and top-left
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: Call recursive function for bottom-right corner to fill DP table
     * - Step 3: Iterate through DP table to find maximum square size
     * - Step 4: Return area (size * size) of largest square found
     * - The recursive function checks base cases and computes square size based 
     *   on neighboring cells
     * 
     * Time Complexity: O(m*n) where m and n are matrix dimensions
     * Space Complexity: O(m*n) for DP table and recursion stack
     * 
     * @param matrix    Binary matrix with '0's and '1's
     * @return         Area of largest square containing only 1's
     */
    public static int maximalSquare(char[][] matrix) {
        // Validate input matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Initialize DP table with unvisited marker (-1)
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }

        // Fill DP table using memoization starting from bottom-right
        maximalSquareMemo(matrix, dp, m - 1, n - 1);

        // Find maximum square size from DP table
        int maxSize = 0;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // Only consider cells with '1' as potential square corners
                if (matrix[row][col] == '1') {
                    maxSize = Math.max(maxSize, dp[row][col]);
                }
            }
        }

        // Return area of largest square (size squared)
        return maxSize * maxSize;
    }

    /**
     * Helper Method: Recursive memoization function
     * 
     * Intuition:
     * - This function computes the largest square size that can be formed with 
     *   the current cell as bottom-right corner
     * - Uses three recursive calls to get square sizes from left, top-left, 
     *   and top neighbors
     * - The current cell's square size is minimum of these three plus 1
     * - Base cases handle boundary conditions and already computed values
     * 
     * Explanation:
     * - Step 1: Check base cases (out of bounds, already computed)
     * - Step 2: Recursively compute square sizes from three neighbors
     * - Step 3: If current cell is '0', square size is 0
     * - Step 4: If current cell is '1', take minimum of neighbors plus 1
     * - Step 5: Store result in DP table and return
     * 
     * Time Complexity: O(1) per call, O(m*n) total with memoization
     * Space Complexity: O(m*n) for recursion stack in worst case
     * 
     * @param matrix    Binary matrix with '0's and '1's
     * @param dp       DP table to store computed results
     * @param row      Current row index
     * @param col      Current column index
     * @return         Size of largest square with current cell as corner
     */
    public static int maximalSquareMemo(char[][] matrix, int[][] dp, int row, int col) {
        // Base case: out of bounds
        if (row < 0 || col < 0)
            return 0;
            
        // Return already computed value to avoid recomputation
        if (dp[row][col] != -1)
            return dp[row][col];

        // Recursively compute square sizes from three neighboring positions
        int left = maximalSquareMemo(matrix, dp, row, col - 1);        // Left neighbor
        int upLeft = maximalSquareMemo(matrix, dp, row - 1, col - 1);  // Top-left neighbor
        int up = maximalSquareMemo(matrix, dp, row - 1, col);          // Top neighbor

        // If current cell is '0', no square can be formed
        if (matrix[row][col] == '0') {
            return dp[row][col] = 0;
        } else {
            // Square size is minimum of three neighbors plus current cell
            return dp[row][col] = Math.min(left, Math.min(upLeft, up)) + 1;
        }
    }

    /**
     * Approach 2: Tabulation (Bottom-Up DP)
     * 
     * Intuition:
     * - Build solution iteratively from smaller subproblems to larger ones
     * - Start with first row and column as base cases (single cells)
     * - For each subsequent cell, compute square size based on previously 
     *   computed neighboring cells
     * - This avoids recursion overhead and uses less space than memoization
     * 
     * Explanation:
     * - Step 1: Initialize first row and column as base cases
     * - Step 2: Fill DP table row by row, column by column
     * - Step 3: For each cell, if it's '1', compute square size using 
     *   neighboring DP values
     * - Step 4: Find maximum value in DP table
     * - Step 5: Return area of largest square
     * 
     * Time Complexity: O(m*n) where m and n are matrix dimensions
     * Space Complexity: O(m*n) for DP table
     * 
     * @param matrix    Binary matrix with '0's and '1's
     * @return         Area of largest square containing only 1's
     */
    public static int maximalSquareTabulation(char[][] matrix) {
        // Get matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Initialize first row with corresponding matrix values
        for (int row = 0; row < m; row++) {
            dp[row][0] = matrix[row][0] - '0';
        }
        
        // Initialize first column with corresponding matrix values
        for (int col = 0; col < n; col++) {
            dp[0][col] = matrix[0][col] - '0';
        }

        // Fill DP table using previously computed values
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == '0') {
                    // No square can be formed if current cell is '0'
                    dp[row][col] = 0;
                } else {
                    // Square size is minimum of three neighbors plus current cell
                    dp[row][col] = Math.min(dp[row][col - 1], Math.min(dp[row - 1][col - 1], dp[row - 1][col])) + 1;
                }
            }
        }
        
        // Find maximum square size in DP table
        int maxAns = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                maxAns = Math.max(maxAns, dp[i][j]);
            }
        }
        
        // Return area of largest square (size squared)
        return maxAns * maxAns;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        char[][] matrix1 = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println("Input: 4x5 matrix with largest 2x2 square, Expected: 4, Output: " + maximalSquare(matrix1));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix1));
        
        char[][] matrix2 = {{'0','1'},{'1','0'}};
        System.out.println("Input: 2x2 matrix, Expected: 1, Output: " + maximalSquare(matrix2));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        char[][] matrix3 = {{'0'}};
        System.out.println("Input: Single '0', Expected: 0, Output: " + maximalSquare(matrix3));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix3));
        
        char[][] matrix4 = {{'1'}};
        System.out.println("Input: Single '1', Expected: 1, Output: " + maximalSquare(matrix4));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        char[][] matrix5 = {{'1','1','1','1'}};
        System.out.println("Input: Single row all '1's, Expected: 1, Output: " + maximalSquare(matrix5));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix5));
        
        char[][] matrix6 = {{'1'},{'1'},{'1'},{'1'}};
        System.out.println("Input: Single column all '1's, Expected: 1, Output: " + maximalSquare(matrix6));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        char[][] matrix7 = {{'1','1','1'},{'1','1','1'},{'1','1','1'}};
        System.out.println("Input: 3x3 all '1's, Expected: 9, Output: " + maximalSquare(matrix7));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix7));
        
        char[][] matrix8 = {{'0','0','0'},{'0','0','0'},{'0','0','0'}};
        System.out.println("Input: 3x3 all '0's, Expected: 0, Output: " + maximalSquare(matrix8));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix8));
        
        char[][] matrix9 = {{'1','1','0','0','0'},{'1','1','0','0','0'},{'0','0','1','1','1'},{'0','0','1','1','1'}};
        System.out.println("Input: Two separate 2x2 squares, Expected: 4, Output: " + maximalSquare(matrix9));
        System.out.println("Tabulation: " + maximalSquareTabulation(matrix9));
    }
}
