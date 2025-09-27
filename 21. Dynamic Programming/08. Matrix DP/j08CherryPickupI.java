/**
 * LeetCode 741. Cherry Pickup
 * 
 * Problem Statement:
 *     You are given an n x n grid representing a field of cherries, each cell is
 *     one of three possible integers: 0 means the cell is empty, 1 means the cell
 *     contains a cherry that can be picked up, and -1 means the cell contains a
 *     thorn that blocks your path. Return the maximum number of cherries you can
 *     collect by following the rules below:
 * 
 *     1. Starting at the position (0, 0) and reaching (n - 1, n - 1) by moving
 *        right or down through valid path cells.
 *     2. When you pass through a cell containing a cherry, you pick it up, and
 *        the cell becomes an empty cell (0).
 *     3. If there is no valid path between (0, 0) and (n - 1, n - 1), then no
 *        cherries can be collected.
 * 
 *     The key insight is that we can model this as two robots moving simultaneously
 *     from (0,0) to (n-1,n-1), where both robots pick cherries optimally.
 * 
 * Input:
 *     - grid: n x n 2D array where 0=empty, 1=cherry, -1=thorn
 *     - 1 <= n <= 50
 * 
 * Output:
 *     - int: maximum number of cherries that can be collected
 * 
 * Example:
 *     Input: grid = [[0,1,-1],[1,0,-1],[1,1,1]]
 *     Output: 5
 * 
 *     Explanation:
 *     Robot 1 path: (0,0) → (1,0) → (2,0) → (2,1) → (2,2)
 *     Robot 2 path: (0,0) → (0,1) → (1,1) → (2,1) → (2,2)
 *     Cherries collected: 0+1+1+1+1+1 = 5 (both robots reach same cell once)
 */

import java.util.*;

public class j08CherryPickupI {

    /**
     * Approach 1: 4D DP (Four Dimensional Dynamic Programming)
     * 
     * Intuition:
     * - Model the problem as two robots moving simultaneously from (0,0) to (n-1,n-1)
     * - Robot 1 at (row1,col1) and Robot 2 at (row2,col2) move together
     * - Both robots can only move right or down at each step
     * - If both robots are at same cell, count cherry only once
     * - Use 4D memoization to avoid recalculating same state combinations
     * 
     * Explanation:
     * - Step 1: Initialize 4D DP array for all possible robot position combinations
     * - Step 2: Both robots start at (0,0) and move simultaneously
     * - Step 3: At each step, robots can move in 4 combinations: (down,down), (right,down), (right,right), (down,right)
     * - Step 4: Check bounds and thorn constraints for both robot positions
     * - Step 5: Add cherries from both positions, subtracting duplicate if same cell
     * - Step 6: Return maximum cherries collected across all movement combinations
     * 
     * Time Complexity: O(n^4) - all possible combinations of two robot positions
     * Space Complexity: O(n^4) - 4D memoization array + recursion stack O(n)
     * 
     * @param grid   n x n grid where 0=empty, 1=cherry, -1=thorn
     * @return       Maximum number of cherries that can be collected
     */
    public static int cherryPickup4DP(int[][] grid) {
        // Get grid dimensions
        int n = grid.length;
        // Initialize 4D memoization array for robot position combinations
        int[][][][] dp = new int[n][n][n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    Arrays.fill(dp[i][j][k], -1);
                }
            }
        }
        // Start both robots from (0,0) and calculate maximum cherries
        int ans = cherryPickupMemo4DP(grid, dp, 0, 0, 0, 0);
        // Return 0 if no valid path exists (negative result)
        return (ans <= 0) ? 0 : ans;
    }

    /**
     * Helper Method: 4D DP recursive function with memoization
     * 
     * Intuition:
     * - Recursively explore all possible movement combinations for both robots
     * - Use memoization to cache results and avoid redundant calculations
     * - Handle collision case where both robots occupy same cell
     * - Return maximum cherries collected from current state to destination
     * 
     * Explanation:
     * - Base case: if either robot hits boundary or thorn, return invalid path
     * - Base case: if both robots reach destination, return cherry value
     * - Memoization check: return cached result if already calculated
     * - Calculate cherries from both positions, handling collision case
     * - Explore all 4 movement combinations: (down,down), (right,down), (right,right), (down,right)
     * - Return maximum cherries plus current cell values
     * 
     * Time Complexity: O(n^4) - each state calculated once
     * Space Complexity: O(n^4) - memoization array + O(n) recursion stack
     * 
     * @param grid   Input grid with cherries and thorns
     * @param dp     4D memoization array
     * @param row1   Robot 1 row position
     * @param col1   Robot 1 column position
     * @param row2   Robot 2 row position
     * @param col2   Robot 2 column position
     * @return       Maximum cherries from current state to destination
     */
    private static int cherryPickupMemo4DP(int[][] grid, int[][][][] dp, int row1, int col1, int row2, int col2) {
        // Check if Robot 1 is out of bounds or on thorn
        if (row1 >= grid.length || col1 >= grid.length || grid[row1][col1] == -1)
            return Integer.MIN_VALUE;
        // Check if Robot 2 is out of bounds or on thorn
        if (row2 >= grid.length || col2 >= grid.length || grid[row2][col2] == -1)
            return Integer.MIN_VALUE;
        // Base case: both robots reached destination
        if (row1 == grid.length - 1 && col1 == grid.length - 1)
            return grid[row1][col1];
        // Return cached result if already calculated
        if (dp[row1][col1][row2][col2] != -1)
            return dp[row1][col1][row2][col2];

        // Calculate cherries from both robot positions
        int ans = grid[row1][col1] + grid[row2][col2];
        // If both robots are at same cell, count cherry only once
        if (row1 == row2 && col1 == col2)
            ans -= grid[row1][col1];
        
        // Explore all 4 movement combinations for both robots
        int downDown = cherryPickupMemo4DP(grid, dp, row1 + 1, col1, row2 + 1, col2);
        int rightDown = cherryPickupMemo4DP(grid, dp, row1, col1 + 1, row2 + 1, col2);
        int rightRight = cherryPickupMemo4DP(grid, dp, row1, col1 + 1, row2, col2 + 1);
        int downRight = cherryPickupMemo4DP(grid, dp, row1 + 1, col1, row2, col2 + 1);

        // Cache and return maximum cherries from all movement combinations
        return dp[row1][col1][row2][col2] = ans
                + Math.max(Math.max(downDown, rightDown), Math.max(rightRight, downRight));
    }

    /**
     * Approach 2: 3D DP (Optimized Space Complexity)
     * 
     * Intuition:
     * - Optimize space by using constraint: both robots move simultaneously
     * - At any step k, Robot 1 at (r1,c1) and Robot 2 at (r2,c2)
     * - Key insight: r1 + c1 = r2 + c2 = k (both robots have taken same steps)
     * - This allows us to reduce 4D state to 3D: (r1, c1, r2) where c2 = r1 + c1 - r2
     * - Dramatically reduces space complexity from O(n^4) to O(n^3)
     * 
     * Explanation:
     * - Step 1: Use constraint r1 + c1 = r2 + c2 = k to eliminate one dimension
     * - Step 2: Calculate c2 = r1 + c1 - r2 from the constraint
     * - Step 3: Initialize 3D DP array instead of 4D for space optimization
     * - Step 4: Apply same logic as 4D approach but with reduced state space
     * - Step 5: Return maximum cherries collected with optimized memory usage
     * 
     * Time Complexity: O(n^3) - reduced state space due to constraint
     * Space Complexity: O(n^3) - 3D memoization array + recursion stack O(n)
     * 
     * @param grid   n x n grid where 0=empty, 1=cherry, -1=thorn
     * @return       Maximum number of cherries that can be collected
     */
    public static int cherryPickup3DP(int[][] grid) {
        // Get grid dimensions
        int n = grid.length;
        // Initialize 3D memoization array (optimized from 4D)
        int[][][] dp = new int[n][n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                Arrays.fill(dp[i][j],-1);
            }
        }
        // Start both robots from (0,0) with 3D optimization
        int ans = cherryPickupMemo3DP(grid,dp,0,0,0);
        // Return 0 if no valid path exists (negative result)
        return (ans <= 0) ? 0 : ans;
    }

    /**
     * Helper Method: 3D DP recursive function with space optimization
     * 
     * Intuition:
     * - Use constraint r1 + c1 = r2 + c2 to reduce state space from 4D to 3D
     * - Calculate c2 = r1 + c1 - r2 from the simultaneous movement constraint
     * - Apply same logic as 4D approach but with optimized memory usage
     * - Cache results in 3D array instead of 4D for space efficiency
     * 
     * Explanation:
     * - Calculate c2 from constraint: c2 = r1 + c1 - r2 at the beginning
     * - Apply same boundary and thorn checks as 4D approach
     * - Use 3D memoization array: dp[r1][c1][r2] where c2 is derived
     * - Explore same 4 movement combinations with reduced state representation
     * - Return maximum cherries with optimized space complexity
     * 
     * Time Complexity: O(n^3) - reduced state space
     * Space Complexity: O(n^3) - 3D memoization array + O(n) recursion stack
     * 
     * @param grid   Input grid with cherries and thorns
     * @param dp     3D memoization array (optimized from 4D)
     * @param row1   Robot 1 row position
     * @param col1   Robot 1 column position
     * @param row2   Robot 2 row position
     * @return       Maximum cherries from current state to destination
     */
    private static int cherryPickupMemo3DP(int[][] grid,int[][][] dp,int row1,int col1,int row2){
        // Calculate col2 from constraint: r1 + c1 = r2 + c2, so c2 = r1 + c1 - r2
        int col2 = row1 + col1 - row2;
        
        // Check if Robot 1 is out of bounds or on thorn
        if(row1 >= grid.length || col1 >= grid.length || grid[row1][col1] == -1) return Integer.MIN_VALUE;
        // Check if Robot 2 is out of bounds or on thorn
        if(row2 >= grid.length || col2 >= grid.length || grid[row2][col2] == -1) return Integer.MIN_VALUE;
        // Base case: both robots reached destination
        if(row1 == grid.length - 1 && col1 == grid.length - 1) return grid[row1][col1];
        // Return cached result if already calculated (using 3D indexing)
        if(dp[row1][col1][row2] != -1) return dp[row1][col1][row2];

        // Calculate cherries from both robot positions
        int ans = grid[row1][col1] + grid[row2][col2];
        // If both robots are at same cell, count cherry only once
        if(row1 == row2 && col1 == col2) ans -= grid[row1][col1];
        
        // Explore all 4 movement combinations for both robots
        int downDown = cherryPickupMemo3DP(grid,dp,row1 + 1,col1,row2 + 1);
        int rightDown = cherryPickupMemo3DP(grid,dp,row1,col1 + 1,row2 + 1);
        int rightRight = cherryPickupMemo3DP(grid,dp,row1,col1 + 1,row2);
        int downRight = cherryPickupMemo3DP(grid,dp,row1 + 1,col1,row2);

        // Cache and return maximum cherries from all movement combinations (3D indexing)
        return dp[row1][col1][row2] = ans + Math.max(Math.max(downDown,rightDown),Math.max(rightRight,downRight));
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        int[][] grid1 = {{0,1,-1},{1,0,-1},{1,1,1}};
        System.out.println("Input: [[0,1,-1],[1,0,-1],[1,1,1]], Expected: 5");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid1));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid1));
        
        int[][] grid2 = {{1,1,-1},{1,-1,1},{-1,1,1}};
        System.out.println("\nInput: [[1,1,-1],[1,-1,1],[-1,1,1]], Expected: 0");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid2));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] grid3 = {{1}};
        System.out.println("Input: [[1]], Expected: 1");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid3));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid3));
        
        int[][] grid4 = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
        System.out.println("\nInput: 4x4 grid of all cherries, Expected: 7");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid4));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] grid5 = {{0,0,0},{0,0,0},{0,0,0}};
        System.out.println("Input: 3x3 grid of all zeros, Expected: 0");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid5));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid5));
        
        int[][] grid6 = {{1,1,1,0,0},{0,0,1,0,0},{0,0,1,1,1},{0,0,0,0,1},{0,0,0,0,1}};
        System.out.println("\nInput: 5x5 grid with specific pattern");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid6));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] grid7 = {{1,-1,-1,-1,-1,-1,-1,-1,-1,-1},{1,0,0,0,0,0,0,0,0,0},{1,1,1,1,1,1,1,1,1,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,1}};
        System.out.println("Input: 10x10 grid with specific pattern");
        System.out.println("4D DP Output: " + cherryPickup4DP(grid7));
        System.out.println("3D DP Output: " + cherryPickup3DP(grid7));
        
        // Performance comparison
        System.out.println("\nPerformance Comparison:");
        long start = System.currentTimeMillis();
        cherryPickup4DP(grid7);
        long time4D = System.currentTimeMillis() - start;
        
        start = System.currentTimeMillis();
        cherryPickup3DP(grid7);
        long time3D = System.currentTimeMillis() - start;
        
        System.out.println("4D DP Time: " + time4D + "ms");
        System.out.println("3D DP Time: " + time3D + "ms");
    }
}
