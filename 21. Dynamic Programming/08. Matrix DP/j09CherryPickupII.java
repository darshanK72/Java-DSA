/**
 * LeetCode 1463. Cherry Pickup II
 * 
 * Problem Statement:
 *     You are given a rows x cols matrix grid representing a field of cherries.
 *     You have two robots that can collect cherries for you:
 *     - Robot #1 is located at the top-left corner (0, 0)
 *     - Robot #2 is located at the top-right corner (0, cols - 1)
 *     Both robots move down row by row, and both can move left or right in 
 *     each row. When both robots visit the same cell, only one robot can 
 *     collect the cherries. Return the maximum number of cherries collection 
 *     by both robots.
 * 
 * Input:
 *     - grid (int[][]): matrix where grid[i][j] represents cherries at (i,j)
 *     - 1 <= rows, cols <= 70
 *     - 0 <= grid[i][j] <= 100
 * 
 * Output:
 *     - int: maximum number of cherries that can be collected
 * 
 * Example:
 *     Input: grid = [[3,1,1],[2,5,1],[1,5,5],[2,1,1]]
 *     Output: 24
 * 
 *     Explanation:
 *     Robot 1: (0,0) -> (1,1) -> (2,2) -> (3,2) = 3 + 5 + 5 + 1 = 14
 *     Robot 2: (0,2) -> (1,1) -> (2,2) -> (3,1) = 1 + 5 + 5 + 1 = 12
 *     Total: 14 + 12 = 26, but they meet at (1,1) and (2,2), so subtract 
 *     duplicates: 26 - 2 = 24
 */

import java.util.Arrays;

public class j09CherryPickupII {

    /**
     * Approach: 3D Dynamic Programming with Memoization
     * 
     * Intuition:
     * - Both robots move down simultaneously from row 0 to row m-1
     * - At each row, we need to track positions of both robots (col1, col2)
     * - State can be represented as (row, col1, col2) since both are on same row
     * - Each robot can move left, stay, or right (3 choices each = 9 combinations)
     * - When both robots are at same cell, count cherries only once
     * 
     * Explanation:
     * - Step 1: Use 3D DP where dp[row][col1][col2] = max cherries from this state
     * - Step 2: Try all 9 combinations of movements for both robots
     * - Step 3: Handle collision case by counting cherries only once
     * - Step 4: Base case: when we reach the last row
     * - Step 5: Memoize results to avoid recomputation
     * 
     * Time Complexity: O(m * n^2) where m = rows, n = cols
     * Space Complexity: O(m * n^2) for DP table and recursion stack
     * 
     * @param grid    Matrix representing cherry field
     * @return        Maximum cherries that can be collected
     */
    public static int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // 3D DP table: dp[row][col1][col2] = max cherries from this state
        int[][][] dp = new int[m][n][n];
        
        // Initialize DP table with -1 (unvisited states)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        
        // Start from top row with robots at (0,0) and (0,n-1)
        int result = cherryPickupMemo(grid, dp, 0, 0, n - 1);
        
        // Return 0 if no valid path exists
        return Math.max(0, result);
    }

    /**
     * Helper Method: Memoized Recursive Function
     * 
     * Intuition:
     * - Recursively explore all possible paths for both robots
     * - Try all 9 combinations of movements (3 for each robot)
     * - Use memoization to avoid recomputing same states
     * - Handle collision by counting cherries only once
     * 
     * Explanation:
     * - Step 1: Check if current state is valid and not out of bounds
     * - Step 2: Base case: if at last row, return cherries collected
     * - Step 3: Check memoization table for already computed result
     * - Step 4: Calculate cherries for current positions (handle collision)
     * - Step 5: Try all 9 combinations of next movements
     * - Step 6: Return maximum result and memoize it
     * 
     * Time Complexity: O(1) per state (with memoization)
     * Space Complexity: O(1) per recursive call
     * 
     * @param grid    Cherry field matrix
     * @param dp      Memoization table
     * @param row     Current row (both robots are on same row)
     * @param col1    Column position of robot 1
     * @param col2    Column position of robot 2
     * @return        Maximum cherries from current state to end
     */
    private static int cherryPickupMemo(int[][] grid, int[][][] dp, int row, int col1, int col2) {
        int m = grid.length;
        int n = grid[0].length;
        
        // Check bounds for both robots
        if (row >= m || col1 < 0 || col1 >= n || col2 < 0 || col2 >= n) {
            return Integer.MIN_VALUE;
        }
        
        // Base case: reached the last row
        if (row == m - 1) {
            if (col1 == col2) {
                // Both robots at same cell, count cherries only once
                return grid[row][col1];
            } else {
                // Different cells, count both
                return grid[row][col1] + grid[row][col2];
            }
        }
        
        // Check if already computed
        if (dp[row][col1][col2] != -1) {
            return dp[row][col1][col2];
        }
        
        // Calculate cherries for current positions
        int currentCherries = grid[row][col1] + grid[row][col2];
        if (col1 == col2) {
            // Both robots at same cell, don't double count
            currentCherries -= grid[row][col1];
        }
        
        int maxCherries = Integer.MIN_VALUE;
        
        // Try all 9 combinations of movements for both robots
        // Each robot can move: -1 (left), 0 (stay), 1 (right)
        for (int d1 = -1; d1 <= 1; d1++) {
            for (int d2 = -1; d2 <= 1; d2++) {
                // Recursively explore next state
                int nextCherries = cherryPickupMemo(grid, dp, row + 1, col1 + d1, col2 + d2);
                
                // Update maximum if valid path found
                if (nextCherries != Integer.MIN_VALUE) {
                    maxCherries = Math.max(maxCherries, currentCherries + nextCherries);
                }
            }
        }
        
        // Memoize and return result
        return dp[row][col1][col2] = maxCherries;
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        int[][] grid1 = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        System.out.println("Input: [[3,1,1],[2,5,1],[1,5,5],[2,1,1]], Expected: 24, Output: " + cherryPickup(grid1));
        
        int[][] grid2 = {{1,0,0,0,0,0,1},{2,0,0,0,0,3,0},{2,0,9,0,0,0,0},{0,3,0,5,4,0,0},{1,0,2,3,0,0,6}};
        System.out.println("Input: 5x7 grid, Expected: 28, Output: " + cherryPickup(grid2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] grid3 = {{1,1},{1,1}};
        System.out.println("Input: 2x2 all 1s, Expected: 4, Output: " + cherryPickup(grid3));
        
        int[][] grid4 = {{0}};
        System.out.println("Input: [[0]], Expected: 0, Output: " + cherryPickup(grid4));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] grid5 = {{100}};
        System.out.println("Input: [[100]], Expected: 100, Output: " + cherryPickup(grid5));
        
        int[][] grid6 = new int[70][70];
        for (int i = 0; i < 70; i++) {
            Arrays.fill(grid6[i], 100);
        }
        System.out.println("Input: 70x70 all 100s, Expected: 7000, Output: " + cherryPickup(grid6));

        // Test Case 4: Special cases
        System.out.println("\nSpecial Cases:");
        int[][] grid7 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Input: [[1,2,3],[4,5,6],[7,8,9]], Expected: 21, Output: " + cherryPickup(grid7));
        
        int[][] grid8 = {{0,8,7,10,9,10,0,9,6},{8,7,10,8,7,4,9,6,10},{8,1,1,5,1,5,5,1,2},{9,4,10,8,8,1,9,5,0},{4,3,6,10,9,8,4,6,4},{10,9,6,1,8,7,4,10,0}};
        System.out.println("Input: Complex 6x9 grid, Expected: 96, Output: " + cherryPickup(grid8));
    }
}
