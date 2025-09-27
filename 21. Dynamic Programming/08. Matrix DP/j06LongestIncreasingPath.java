/**
 * LeetCode 329. Longest Increasing Path in a Matrix
 * 
 * Problem Statement:
 *     Given an m x n integers matrix, return the length of the longest increasing
 *     path. From each cell, you can either move in four directions: left, right,
 *     up, or down. You may not move diagonally or move outside the boundary
 *     (i.e., wrap-around is not allowed).
 * 
 * Input:
 *     - matrix: m x n 2D array of integers (1 <= m, n <= 200)
 *     - -2^31 <= matrix[i][j] <= 2^31 - 1
 * 
 * Output:
 *     - int: length of the longest increasing path
 * 
 * Example:
 *     Input: matrix = [[9,9,4],[6,6,8],[2,1,1]]
 *     Output: 4
 * 
 *     Explanation:
 *     The longest increasing path is [1, 2, 6, 9].
 *     Visual representation:
 *     9 9 4
 *     6 6 8
 *     2 1 1
 *     Path: 1 → 2 → 6 → 9 (length 4)
 */

import java.util.*;

public class j06LongestIncreasingPath {
    // Direction arrays for 4-directional movement (right, down, left, up)
    static int[] rowDir = new int[] { 0, 1, 0, -1 };
    static int[] colDir = new int[] { 1, 0, -1, 0 };

    /**
     * Approach 1: Brute Force DFS (No Memoization)
     * 
     * Intuition:
     * - Try every cell as starting point for longest increasing path
     * - From each cell, explore all 4 directions recursively
     * - Only move to cells with greater values (increasing path constraint)
     * - Track maximum path length found across all starting positions
     * - No memoization leads to redundant calculations (exponential time)
     * 
     * Explanation:
     * - Step 1: Iterate through every cell as potential starting point
     * - Step 2: For each starting cell, perform DFS to find longest path
     * - Step 3: In DFS, explore all 4 directions (up, down, left, right)
     * - Step 4: Only proceed if neighboring cell has greater value
     * - Step 5: Recursively calculate path length and return maximum
     * - Step 6: Update global maximum path length across all starting points
     * 
     * Time Complexity: O(2^(m*n)) - exponential due to repeated subproblems
     * Space Complexity: O(m*n) - recursion stack depth
     * 
     * @param matrix   m x n 2D array of integers
     * @return         Length of longest increasing path
     */
    public static int longestIncreasingPath(int[][] matrix) {
        // Initialize maximum path length to 0
        int ans = 0;
        // Get matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;
        // Try every cell as starting point for longest increasing path
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate longest path starting from current cell
                int val = dfs(matrix, i, j);
                // Update global maximum path length
                ans = Math.max(ans, val);
            }
        }
        // Return longest increasing path length found
        return ans;
    }

    /**
     * Helper Method: DFS without memoization (brute force)
     * 
     * Intuition:
     * - Recursively explore all 4 directions from current cell
     * - Only move to cells with strictly greater values
     * - Return maximum path length found from current position
     * - Base case: no valid moves available (path length = 1)
     * 
     * Explanation:
     * - Initialize path length to 1 (current cell counts as 1)
     * - Check all 4 directions using direction arrays
     * - Validate bounds and increasing value constraint
     * - Recursively explore valid neighboring cells
     * - Return maximum path length from current position
     * 
     * Time Complexity: O(4^(m*n)) - exponential exploration
     * Space Complexity: O(m*n) - recursion stack
     * 
     * @param matrix   Input 2D matrix
     * @param row      Current row position
     * @param col      Current column position
     * @return         Length of longest increasing path from current cell
     */
    private static int dfs(int[][] matrix, int row, int col) {
        // Initialize path length to 1 (current cell)
        int max = 1;
        // Explore all 4 directions using direction arrays
        for (int d = 0; d < 4; d++) {
            // Calculate new position using direction offsets
            int newRow = row + rowDir[d];
            int newCol = col + colDir[d];
            // Check bounds and increasing value constraint
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length
                    && matrix[newRow][newCol] > matrix[row][col]) {
                // Recursively explore and update maximum path length
                max = Math.max(max, 1 + dfs(matrix, newRow, newCol));
            }
        }
        // Return longest path length from current position
        return max;
    }

    /**
     * Approach 2: DFS with Memoization (Optimized)
     * 
     * Intuition:
     * - Use memoization to cache results and avoid redundant calculations
     * - Each cell's longest path is calculated only once and stored
     * - Subsequent calls return cached result instead of recalculating
     * - Dramatically reduces time complexity from exponential to polynomial
     * - Still explore all possible starting points for global maximum
     * 
     * Explanation:
     * - Step 1: Initialize memoization array with -1 (unvisited marker)
     * - Step 2: Try every cell as starting point for longest path
     * - Step 3: Use memoized DFS to calculate path length efficiently
     * - Step 4: Update global maximum across all starting positions
     * - Step 5: Return longest increasing path length found
     * 
     * Time Complexity: O(m*n) - each cell visited once due to memoization
     * Space Complexity: O(m*n) - memoization array + recursion stack O(m*n)
     * 
     * @param matrix   m x n 2D array of integers
     * @return         Length of longest increasing path
     */
    public int longestIncreasingPathMemo(int[][] matrix) {
        // Initialize maximum path length to 0
        int ans = 0;
        // Get matrix dimensions
        int m = matrix.length;
        int n = matrix[0].length;
        // Initialize memoization array with -1 to mark unvisited cells
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // Try every cell as starting point for longest increasing path
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Calculate longest path starting from current cell with memoization
                int val = dfsMemo(matrix, dp, i, j);
                // Update global maximum path length
                ans = Math.max(ans, val);
            }
        }
        // Return longest increasing path length found
        return ans;
    }

    /**
     * Helper Method: DFS with memoization (optimized recursive function)
     * 
     * Intuition:
     * - Cache results in DP array to avoid recalculating same subproblems
     * - Return cached result if cell has been visited before
     * - Only explore unvisited cells and store results for future use
     * - Dramatically improves performance by eliminating redundant work
     * 
     * Explanation:
     * - Memoization check: return cached result if already calculated
     * - Initialize path length to 1 (current cell counts as 1)
     * - Explore all 4 directions using direction arrays
     * - Validate bounds and increasing value constraint
     * - Recursively explore valid neighboring cells with memoization
     * - Cache and return maximum path length from current position
     * 
     * Time Complexity: O(m*n) - each cell visited once
     * Space Complexity: O(m*n) - memoization array + O(m*n) recursion stack
     * 
     * @param matrix   Input 2D matrix
     * @param dp       Memoization array to cache results
     * @param row      Current row position
     * @param col      Current column position
     * @return         Length of longest increasing path from current cell
     */
    public static int dfsMemo(int[][] matrix, int[][] dp, int row, int col) {
        // Return cached result if already calculated
        if (dp[row][col] != -1)
            return dp[row][col];
        // Initialize path length to 1 (current cell)
        int max = 1;
        // Explore all 4 directions using direction arrays
        for (int d = 0; d < 4; d++) {
            // Calculate new position using direction offsets
            int newRow = row + rowDir[d];
            int newCol = col + colDir[d];
            // Check bounds and increasing value constraint
            if (newRow >= 0 && newRow < matrix.length && newCol >= 0 && newCol < matrix[0].length
                    && matrix[newRow][newCol] > matrix[row][col]) {
                // Recursively explore and update maximum path length with memoization
                max = Math.max(max, 1 + dfsMemo(matrix, dp, newRow, newCol));
            }
        }
        // Cache and return longest path length from current position
        return dp[row][col] = max;
    }

    public static void main(String[] args) {
        j06LongestIncreasingPath solution = new j06LongestIncreasingPath();
        
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        int[][] matrix1 = {{9,9,4},{6,6,8},{2,1,1}};
        System.out.println("Input: [[9,9,4],[6,6,8],[2,1,1]], Expected: 4, Output: " + longestIncreasingPath(matrix1));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix1));
        
        int[][] matrix2 = {{3,4,5},{3,2,6},{2,2,1}};
        System.out.println("Input: [[3,4,5],[3,2,6],[2,2,1]], Expected: 4, Output: " + longestIncreasingPath(matrix2));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix2));
        
        int[][] matrix3 = {{1}};
        System.out.println("Input: [[1]], Expected: 1, Output: " + longestIncreasingPath(matrix3));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] matrix4 = {{7,8,9},{6,5,4},{3,2,1}};
        System.out.println("Input: [[7,8,9],[6,5,4],[3,2,1]], Expected: 9, Output: " + longestIncreasingPath(matrix4));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix4));
        
        int[][] matrix5 = {{1,2,3,4,5}};
        System.out.println("Input: [[1,2,3,4,5]], Expected: 5, Output: " + longestIncreasingPath(matrix5));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix5));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] matrix6 = {{1,1,1},{1,1,1},{1,1,1}};
        System.out.println("Input: [[1,1,1],[1,1,1],[1,1,1]], Expected: 1, Output: " + longestIncreasingPath(matrix6));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix6));
        
        int[][] matrix7 = {{1,2},{4,3}};
        System.out.println("Input: [[1,2],[4,3]], Expected: 4, Output: " + longestIncreasingPath(matrix7));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix7));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] matrix8 = {{0,1,2,3,4,5,6,7,8,9}};
        System.out.println("Input: Single row with increasing sequence, Expected: 10, Output: " + longestIncreasingPath(matrix8));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix8));
        
        int[][] matrix9 = {{9},{8},{7},{6},{5},{4},{3},{2},{1},{0}};
        System.out.println("Input: Single column with decreasing sequence, Expected: 1, Output: " + longestIncreasingPath(matrix9));
        System.out.println("Memoized Output: " + solution.longestIncreasingPathMemo(matrix9));
    }
}
