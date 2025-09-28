/**
 * LeetCode 576. Out of Boundary Paths
 * 
 * Problem Statement:
 *     There is an m x n grid with a ball. The ball is initially at the position 
 *     [startRow, startColumn]. You are allowed to move the ball to one of the 
 *     four adjacent cells in the grid (up, down, left, or right). Given the 
 *     integer maxMove, return the number of paths to move the ball out of the 
 *     grid boundary.
 * 
 * Input:
 *     - m (int): Number of rows in grid (1 <= m <= 50)
 *     - n (int): Number of columns in grid (1 <= n <= 50)
 *     - maxMove (int): Maximum number of moves allowed (0 <= maxMove <= 50)
 *     - startRow (int): Starting row position (0 <= startRow < m)
 *     - startColumn (int): Starting column position (0 <= startColumn < n)
 * 
 * Output:
 *     - int: Number of paths to move ball out of boundary (mod 10^9 + 7)
 * 
 * Example:
 *     Input: m = 2, n = 2, maxMove = 2, startRow = 0, startColumn = 0
 *     Output: 6
 * 
 *     Explanation:
 *     From position (0,0), we can move:
 *     1. Right -> Out (1 path)
 *     2. Down -> Out (1 path) 
 *     3. Right -> Down -> Out (1 path)
 *     4. Down -> Right -> Out (1 path)
 *     5. Right -> Right -> Out (1 path)
 *     6. Down -> Down -> Out (1 path)
 *     Total: 6 paths
 */

import java.util.Arrays;

public class j12OutOfBoundryPaths {

    // Direction arrays for 4 possible moves: right, up, left, down
    static int[] rowDir = new int[] { 0, -1, 0, 1 };
    static int[] colDir = new int[] { 1, 0, -1, 0 };
    static int MOD = 1000000007;

    /**
     * Approach 1: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - For each position (row, col) with remaining moves, we need to count 
     *   paths that lead out of boundary
     * - We can move in 4 directions, and each move reduces remaining moves by 1
     * - Base case: if we're out of boundary, we have 1 valid path
     * - Base case: if no moves left and still inside boundary, we have 0 paths
     * - Use memoization to avoid recomputing same subproblems
     * - The result is sum of paths from all 4 possible next positions
     * 
     * Explanation:
     * - Step 1: Initialize 3D DP table with -1 to mark unvisited states
     * - Step 2: Call recursive function with starting position and max moves
     * - Step 3: Recursive function checks base cases and explores 4 directions
     * - Step 4: Sum paths from all valid moves and store result in DP table
     * - Step 5: Return total paths modulo 10^9 + 7
     * 
     * Time Complexity: O(m*n*maxMove) where m,n are grid dimensions
     * Space Complexity: O(m*n*maxMove) for DP table and recursion stack
     * 
     * @param m            Number of rows in grid
     * @param n            Number of columns in grid
     * @param maxMove      Maximum number of moves allowed
     * @param startRow     Starting row position
     * @param startColumn  Starting column position
     * @return            Number of paths to move ball out of boundary
     */
    public static int findPaths(int m, int n, int maxMove, int startRow, int startColumn) {
        // Initialize 3D DP table with unvisited marker (-1)
        int[][][] dp = new int[m + 1][n + 1][maxMove + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        // Call recursive function to compute paths using memoization
        return findPathsMemo(m, n, maxMove, startRow, startColumn, dp);
    }

    /**
     * Helper Method: Recursive memoization function
     * 
     * Intuition:
     * - This function computes the number of paths from current position 
     *   (row, col) with remaining moves to get out of boundary
     * - Uses 4 recursive calls for each possible direction (right, up, left, down)
     * - Base cases handle boundary conditions and move exhaustion
     * - Memoization prevents recomputation of same subproblems
     * - Result is sum of paths from all valid directions modulo MOD
     * 
     * Explanation:
     * - Step 1: Check if out of boundary (base case: 1 path)
     * - Step 2: Check if no moves left (base case: 0 paths)
     * - Step 3: Check if already computed (return cached result)
     * - Step 4: Try all 4 directions recursively
     * - Step 5: Sum paths from all directions and store in DP table
     * - Step 6: Return result modulo MOD
     * 
     * Time Complexity: O(1) per call, O(m*n*maxMove) total with memoization
     * Space Complexity: O(m*n*maxMove) for recursion stack in worst case
     * 
     * @param m        Number of rows in grid
     * @param n        Number of columns in grid
     * @param maxMove  Remaining moves allowed
     * @param row      Current row position
     * @param col      Current column position
     * @param dp       3D DP table to store computed results
     * @return        Number of paths from current position to boundary
     */
    public static int findPathsMemo(int m, int n, int maxMove, int row, int col, int[][][] dp) {
        // Base case: out of boundary, found 1 valid path
        if (row >= m || col >= n || row < 0 || col < 0)
            return 1;
            
        // Base case: no moves left, no valid paths
        if (maxMove <= 0)
            return 0;
            
        // Return already computed value to avoid recomputation
        if (dp[row][col][maxMove] != -1)
            return dp[row][col][maxMove];
            
        // Initialize total paths from current position
        int totalPaths = 0;
        
        // Try all 4 possible directions and sum their paths
        for (int i = 0; i < 4; i++) {
            totalPaths = (totalPaths + findPathsMemo(m, n, maxMove - 1, row + rowDir[i], col + colDir[i], dp)) % MOD;
        }

        // Store result in DP table and return
        return dp[row][col][maxMove] = totalPaths;
    }

    /**
     * Approach 2: Tabulation (Bottom-Up DP)
     * 
     * Intuition:
     * - Build solution iteratively from smaller subproblems to larger ones
     * - Start with 0 moves (all positions have 0 paths)
     * - For each move count k, compute paths for all positions
     * - For each position, check all 4 directions and count boundary exits
     * - If neighbor is out of boundary, add 1 path; otherwise add neighbor's paths
     * - This avoids recursion overhead and builds solution bottom-up
     * 
     * Explanation:
     * - Step 1: Initialize 3D DP table with all zeros
     * - Step 2: For each move count from 1 to maxMove
     * - Step 3: For each position in grid
     * - Step 4: Check all 4 directions from current position
     * - Step 5: If direction leads out of boundary, add 1 path
     * - Step 6: If direction stays in boundary, add neighbor's paths from k-1 moves
     * - Step 7: Return paths from starting position with maxMove
     * 
     * Time Complexity: O(m*n*maxMove*4) = O(m*n*maxMove)
     * Space Complexity: O(m*n*maxMove) for DP table
     * 
     * @param m            Number of rows in grid
     * @param n            Number of columns in grid
     * @param maxMove      Maximum number of moves allowed
     * @param startRow     Starting row position
     * @param startColumn  Starting column position
     * @return            Number of paths to move ball out of boundary
     */
    public static int findPathsMemo(int m, int n, int maxMove, int startRow, int startColumn) {
        // Initialize 3D DP table for tabulation approach
        int[][][] dp = new int[m][n][maxMove + 1];
        
        // Fill DP table for each move count from 1 to maxMove
        for (int k = 1; k <= maxMove; k++) {
            for (int row = 0; row < m; row++) {
                for (int col = 0; col < n; col++) {
                    // Check all 4 possible directions from current position
                    for (int d = 0; d < 4; d++) {
                        // Calculate next position after moving in direction d
                        int nextRow = row + rowDir[d];
                        int nextCol = col + colDir[d];
                        
                        if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n) {
                            // Out of boundary: add 1 path
                            dp[row][col][k] = (dp[row][col][k] + 1) % MOD;
                        } else {
                            // Inside boundary: add paths from neighbor with k-1 moves
                            dp[row][col][k] = (dp[row][col][k] + dp[nextRow][nextCol][k - 1]) % MOD;
                        }
                    }
                }
            }
        }
        
        // Return paths from starting position with maxMove
        return dp[startRow][startColumn][maxMove];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: m=2, n=2, maxMove=2, startRow=0, startColumn=0, Expected: 6, Output: " + findPaths(2, 2, 2, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(2, 2, 2, 0, 0));
        
        System.out.println("Input: m=1, n=3, maxMove=3, startRow=0, startColumn=1, Expected: 12, Output: " + findPaths(1, 3, 3, 0, 1));
        System.out.println("Tabulation: " + findPathsMemo(1, 3, 3, 0, 1));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: m=1, n=1, maxMove=0, startRow=0, startColumn=0, Expected: 0, Output: " + findPaths(1, 1, 0, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(1, 1, 0, 0, 0));
        
        System.out.println("Input: m=1, n=1, maxMove=1, startRow=0, startColumn=0, Expected: 4, Output: " + findPaths(1, 1, 1, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(1, 1, 1, 0, 0));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: m=1, n=2, maxMove=1, startRow=0, startColumn=0, Expected: 2, Output: " + findPaths(1, 2, 1, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(1, 2, 1, 0, 0));
        
        System.out.println("Input: m=2, n=1, maxMove=1, startRow=0, startColumn=0, Expected: 2, Output: " + findPaths(2, 1, 1, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(2, 1, 1, 0, 0));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        System.out.println("Input: m=3, n=3, maxMove=3, startRow=1, startColumn=1, Expected: 12, Output: " + findPaths(3, 3, 3, 1, 1));
        System.out.println("Tabulation: " + findPathsMemo(3, 3, 3, 1, 1));
        
        System.out.println("Input: m=2, n=2, maxMove=1, startRow=0, startColumn=0, Expected: 2, Output: " + findPaths(2, 2, 1, 0, 0));
        System.out.println("Tabulation: " + findPathsMemo(2, 2, 1, 0, 0));
        
        System.out.println("Input: m=1, n=5, maxMove=2, startRow=0, startColumn=2, Expected: 4, Output: " + findPaths(1, 5, 2, 0, 2));
        System.out.println("Tabulation: " + findPathsMemo(1, 5, 2, 0, 2));
    }
}
