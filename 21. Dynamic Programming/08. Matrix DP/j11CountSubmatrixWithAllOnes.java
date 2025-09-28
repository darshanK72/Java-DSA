/**
 * LeetCode 1277. Count Square Submatrices with All Ones
 * 
 * Problem Statement:
 *     Given a m * n matrix of ones and zeros, return how many square 
 *     submatrices have all ones.
 * 
 * Input:
 *     - matrix (int[][]): Binary matrix with 0's and 1's (1 <= m,n <= 300)
 * 
 * Output:
 *     - int: Total count of square submatrices containing only 1's
 * 
 * Example:
 *     Input: matrix = [[0,1,1,1],[1,1,1,1],[0,1,1,1]]
 *     Output: 15
 * 
 *     Explanation:
 *     There are 10 squares of side 1, 4 squares of side 2, and 1 square 
 *     of side 3. Total count = 10 + 4 + 1 = 15 squares.
 */

import java.util.Arrays;

public class j11CountSubmatrixWithAllOnes {

    /**
     * Approach 1: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - For each cell, we need to find the largest square that can be formed 
     *   with this cell as the bottom-right corner
     * - A square of size 's' at position (i,j) contributes 's' square 
     *   submatrices to the total count (squares of sizes 1x1, 2x2, ..., sxs)
     * - We can use recursion with memoization to avoid recomputing the same 
     *   subproblems
     * - The size of square at (i,j) depends on the minimum of squares formed 
     *   by its three neighbors: left, top, and top-left
     * 
     * Explanation:
     * - Step 1: Initialize DP table with -1 to mark unvisited states
     * - Step 2: Call recursive function for bottom-right corner to fill DP table
     * - Step 3: Sum all values in DP table to get total square count
     * - Step 4: Each DP[i][j] represents the largest square size with (i,j) as 
     *   bottom-right corner, which equals the count of squares ending at (i,j)
     * 
     * Time Complexity: O(m*n) where m and n are matrix dimensions
     * Space Complexity: O(m*n) for DP table and recursion stack
     * 
     * @param matrix    Binary matrix with 0's and 1's
     * @return         Total count of square submatrices with all 1's
     */
    public static int countSquares(int[][] matrix) {
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
        
        // Sum all DP values to get total square count
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dp[i][j];
            }
        }
        return ans;
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
     * - The returned value represents count of squares ending at current position
     * 
     * Explanation:
     * - Step 1: Check base cases (out of bounds, already computed)
     * - Step 2: Recursively compute square sizes from three neighbors
     * - Step 3: If current cell is 0, square size is 0 (no squares possible)
     * - Step 4: If current cell is 1, take minimum of neighbors plus 1
     * - Step 5: Store result in DP table and return
     * 
     * Time Complexity: O(1) per call, O(m*n) total with memoization
     * Space Complexity: O(m*n) for recursion stack in worst case
     * 
     * @param matrix    Binary matrix with 0's and 1's
     * @param dp       DP table to store computed results
     * @param row      Current row index
     * @param col      Current column index
     * @return         Count of squares ending at current position
     */
    public static int maximalSquareMemo(int[][] matrix, int[][] dp, int row, int col) {
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

        // If current cell is 0, no squares can be formed
        if (matrix[row][col] == 0) {
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
     * - Each DP[i][j] represents the count of squares ending at position (i,j)
     * - Sum all DP values to get total count of square submatrices
     * 
     * Explanation:
     * - Step 1: Initialize first row and column as base cases
     * - Step 2: Fill DP table row by row, column by column
     * - Step 3: For each cell, if it's 1, compute square size using 
     *   neighboring DP values
     * - Step 4: Sum all values in DP table to get total square count
     * - Step 5: Return total count of square submatrices
     * 
     * Time Complexity: O(m*n) where m and n are matrix dimensions
     * Space Complexity: O(m*n) for DP table
     * 
     * @param matrix    Binary matrix with 0's and 1's
     * @return         Total count of square submatrices with all 1's
     */
    public static int countSquaresTabulation(int[][] matrix) {
        // Get matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] dp = new int[m][n];

        // Initialize first row with corresponding matrix values
        for (int row = 0; row < m; row++) {
            dp[row][0] = matrix[row][0];
        }
        
        // Initialize first column with corresponding matrix values
        for (int col = 0; col < n; col++) {
            dp[0][col] = matrix[0][col];
        }

        // Fill DP table using previously computed values
        for (int row = 1; row < m; row++) {
            for (int col = 1; col < n; col++) {
                if (matrix[row][col] == 0) {
                    // No squares can be formed if current cell is 0
                    dp[row][col] = 0;
                } else {
                    // Square size is minimum of three neighbors plus current cell
                    dp[row][col] = Math.min(dp[row][col - 1], Math.min(dp[row - 1][col - 1], dp[row - 1][col])) + 1;
                }
            }
        }
        
        // Sum all DP values to get total square count
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                ans += dp[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        int[][] matrix1 = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println("Input: 3x4 matrix, Expected: 15, Output: " + countSquares(matrix1));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix1));
        
        int[][] matrix2 = {{1,0,1},{1,1,0},{1,1,0}};
        System.out.println("Input: 3x3 matrix, Expected: 7, Output: " + countSquares(matrix2));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] matrix3 = {{0}};
        System.out.println("Input: Single 0, Expected: 0, Output: " + countSquares(matrix3));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix3));
        
        int[][] matrix4 = {{1}};
        System.out.println("Input: Single 1, Expected: 1, Output: " + countSquares(matrix4));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] matrix5 = {{1,1,1,1}};
        System.out.println("Input: Single row all 1's, Expected: 4, Output: " + countSquares(matrix5));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix5));
        
        int[][] matrix6 = {{1},{1},{1},{1}};
        System.out.println("Input: Single column all 1's, Expected: 4, Output: " + countSquares(matrix6));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] matrix7 = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println("Input: 3x3 all 1's, Expected: 14, Output: " + countSquares(matrix7));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix7));
        
        int[][] matrix8 = {{0,0,0},{0,0,0},{0,0,0}};
        System.out.println("Input: 3x3 all 0's, Expected: 0, Output: " + countSquares(matrix8));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix8));
        
        int[][] matrix9 = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
        System.out.println("Input: 4x4 all 1's, Expected: 30, Output: " + countSquares(matrix9));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix9));
        
        int[][] matrix10 = {{1,1,0,0},{1,1,0,0},{0,0,1,1},{0,0,1,1}};
        System.out.println("Input: Four separate 2x2 squares, Expected: 8, Output: " + countSquares(matrix10));
        System.out.println("Tabulation: " + countSquaresTabulation(matrix10));
    }
}
