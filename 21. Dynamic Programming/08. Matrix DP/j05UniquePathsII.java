/**
 * LeetCode 63. Unique Paths II
 * 
 * Problem Statement:
 *     You are given an m x n integer array grid. There is a robot initially
 *     located at the top-left corner (i.e., grid[0][0]). The robot tries to
 *     move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot
 *     can only move either down or right at any point in time.
 * 
 *     An obstacle and space are marked as 1 or 0 respectively in grid. A path
 *     that the robot takes cannot include any square that is an obstacle.
 * 
 *     Return the number of possible unique paths that the robot can take to
 *     reach the bottom-right corner.
 * 
 * Input:
 *     - obstacleGrid: m x n grid where 0 represents empty space, 1 represents obstacle
 *     - m (1 <= m <= 100): number of rows in the grid
 *     - n (1 <= n <= 100): number of columns in the grid
 * 
 * Output:
 *     - int: number of unique paths from top-left to bottom-right corner
 * 
 * Example:
 *     Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 *     Output: 2
 * 
 *     Explanation:
 *     There is one obstacle in the middle of the 3x3 grid.
 *     There are two ways to reach the bottom-right corner:
 *     1. Right -> Right -> Down -> Down
 *     2. Down -> Down -> Right -> Right
 */

import java.util.*;

public class j05UniquePathsII {

    /**
     * Approach 1: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - Similar to Unique Paths but with obstacle constraints
     * - If current cell is obstacle (1), no paths exist from here
     * - Early termination if start or destination is obstacle
     * - Use memoization to cache results and avoid recalculating subproblems
     * - Check obstacle before exploring right and down movements
     * 
     * Explanation:
     * - Step 1: Validate that start and destination are not obstacles
     * - Step 2: Initialize memoization array with -1 to mark unvisited cells
     * - Step 3: For each cell, check if it's an obstacle (return 0)
     * - Step 4: If at destination and not obstacle, return 1 (base case)
     * - Step 5: Calculate paths by moving right and down if cells are accessible
     * - Step 6: Store result in cache and return sum of valid paths
     * 
     * Time Complexity: O(m*n) - each cell visited once due to memoization
     * Space Complexity: O(m*n) - memoization array + recursion stack O(m+n)
     * 
     * @param obstacleGrid   m x n grid where 0=empty space, 1=obstacle
     * @return               Number of unique paths from (0,0) to (m-1,n-1)
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // Get grid dimensions
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // Early termination if start or destination is obstacle
        if (obstacleGrid[m - 1][n - 1] == 1 || obstacleGrid[0][0] == 1)
            return 0;
        // Initialize memoization array with -1 to mark unvisited cells
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // Start recursive memoization from top-left corner
        return countUniquePathsWithObstaclesMemo(obstacleGrid, m, n, 0, 0, dp);
    }

    /**
     * Helper Method: Recursive memoization function with obstacle handling
     * 
     * Intuition:
     * - Recursively explore all possible paths from current position
     * - Skip cells that contain obstacles (marked as 1)
     * - Use memoization to cache results and avoid redundant calculations
     * - Combine paths from accessible right and down movements only
     * 
     * Explanation:
     * - Check bounds: if out of grid, return 0 (invalid path)
     * - Check obstacle: if current cell is obstacle, return 0 (blocked path)
     * - Base case: if at destination and not obstacle, return 1 (valid path)
     * - Memoization check: if already calculated, return cached result
     * - Recursive calls: explore right and down movements only
     * - Store and return sum of paths from both accessible directions
     * 
     * Time Complexity: O(m*n) - each cell visited once
     * Space Complexity: O(m*n) - memoization array + O(m+n) recursion stack
     * 
     * @param obstacleGrid   Grid with obstacles marked as 1
     * @param m             Total rows in grid
     * @param n             Total columns in grid
     * @param row           Current row position
     * @param col           Current column position
     * @param dp            Memoization array to cache results
     * @return              Number of unique paths from current position to destination
     */
    private static int countUniquePathsWithObstaclesMemo(int[][] obstacleGrid, int m, int n, int row, int col, int[][] dp) {
        // Check if current position is out of grid bounds
        if (row >= m || col >= n)
            return 0;
        // Check if current cell contains an obstacle
        if (obstacleGrid[row][col] == 1)
            return 0;
        // Base case: reached destination, found one valid path
        if (row == m - 1 && col == n - 1)
            return 1;
        // Return cached result if already calculated
        if (dp[row][col] != -1)
            return dp[row][col];

        // Recursively calculate paths by moving right and down
        int right = countUniquePathsWithObstaclesMemo(obstacleGrid, m, n, row, col + 1, dp);
        int down = countUniquePathsWithObstaclesMemo(obstacleGrid, m, n, row + 1, col, dp);

        // Cache and return total paths from current position
        return dp[row][col] = right + down;
    }

    /**
     * Approach 2: Tabulation (Bottom-Up DP)
     * 
     * Intuition:
     * - Fill DP table from destination backwards to starting position
     * - Skip cells that contain obstacles during calculation
     * - Only add paths from accessible neighboring cells
     * - Use bottom-up approach to avoid recursion overhead
     * - Build solution iteratively from known base cases
     * 
     * Explanation:
     * - Step 1: Early termination if start or destination is obstacle
     * - Step 2: Initialize DP table with zeros
     * - Step 3: Set destination cell to 1 if not obstacle (base case)
     * - Step 4: Fill table bottom-up and right-to-left
     * - Step 5: Only add paths from non-obstacle neighboring cells
     * - Step 6: Return value at starting position (0,0)
     * 
     * Time Complexity: O(m*n) - visit each cell exactly once
     * Space Complexity: O(m*n) - DP table storage
     * 
     * @param obstacleGrid   m x n grid where 0=empty space, 1=obstacle
     * @return               Number of unique paths from (0,0) to (m-1,n-1)
     */
    public static int uniquePathsWithObstaclesTabulation(int[][] obstacleGrid) {
        // Get grid dimensions
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // Early termination if start or destination is obstacle
        if(obstacleGrid[m-1][n-1] == 1 || obstacleGrid[0][0] == 1) return 0;
        // Initialize DP table with zeros
        int[][] dp = new int[m][n];
        // Fill table from destination backwards to starting position
        for(int row = m - 1; row >= 0; row--){
            for(int col = n - 1; col >= 0; col--){
                // Base case: destination cell has exactly one path if not obstacle
                if(row == m - 1 && col == n - 1){
                    dp[row][col] = 1;
                }else{
                    // Add paths from cell below if within bounds and not obstacle
                    if(row + 1 < m && obstacleGrid[row + 1][col] != 1) dp[row][col] += dp[row + 1][col];
                    // Add paths from cell to the right if within bounds and not obstacle
                    if(col + 1 < n && obstacleGrid[row][col + 1] != 1) dp[row][col] += dp[row][col + 1];
                }
            }
        }
        // Return number of paths from starting position
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        int[][] grid1 = {{0,0,0},{0,1,0},{0,0,0}};
        System.out.println("Input: [[0,0,0],[0,1,0],[0,0,0]], Expected: 2, Output: " + uniquePathsWithObstacles(grid1));
        
        int[][] grid2 = {{0,1},{0,0}};
        System.out.println("Input: [[0,1],[0,0]], Expected: 1, Output: " + uniquePathsWithObstacles(grid2));
        
        int[][] grid3 = {{0,0},{1,0}};
        System.out.println("Input: [[0,0],[1,0]], Expected: 1, Output: " + uniquePathsWithObstacles(grid3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] grid4 = {{1}};
        System.out.println("Input: [[1]], Expected: 0, Output: " + uniquePathsWithObstacles(grid4));
        
        int[][] grid5 = {{0}};
        System.out.println("Input: [[0]], Expected: 1, Output: " + uniquePathsWithObstacles(grid5));
        
        int[][] grid6 = {{0,0},{0,1}};
        System.out.println("Input: [[0,0],[0,1]], Expected: 0, Output: " + uniquePathsWithObstacles(grid6));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        int[][] grid7 = {{0,0,0,0},{0,1,0,0},{0,0,0,0},{0,0,0,0}};
        System.out.println("Input: 4x4 grid with one obstacle, Output: " + uniquePathsWithObstacles(grid7));
        
        int[][] grid8 = {{0,1,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0},{0,0,0,0,0}};
        System.out.println("Input: 5x5 grid with obstacle in first row, Output: " + uniquePathsWithObstacles(grid8));

        // Test Case 4: Tabulation approach comparison
        System.out.println("\nTabulation Approach:");
        System.out.println("Input: [[0,0,0],[0,1,0],[0,0,0]], Expected: 2, Output: " + uniquePathsWithObstaclesTabulation(grid1));
        System.out.println("Input: [[0,1],[0,0]], Expected: 1, Output: " + uniquePathsWithObstaclesTabulation(grid2));
        System.out.println("Input: [[0,0],[1,0]], Expected: 1, Output: " + uniquePathsWithObstaclesTabulation(grid3));
    }
}
