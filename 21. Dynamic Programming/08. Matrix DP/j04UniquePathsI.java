/**
 * LeetCode 62. Unique Paths
 * 
 * Problem Statement:
 *     There is a robot on an m x n grid. The robot is initially located at the
 *     top-left corner (i.e., grid[0][0]). The robot tries to move to the
 *     bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move
 *     either down or right at any point in time.
 * 
 *     Given the two integers m and n, return the number of possible unique paths
 *     that the robot can take to reach the bottom-right corner.
 * 
 * Input:
 *     - m (1 <= m <= 100): number of rows in the grid
 *     - n (1 <= n <= 100): number of columns in the grid
 * 
 * Output:
 *     - int: number of unique paths from top-left to bottom-right corner
 * 
 * Example:
 *     Input: m = 3, n = 7
 *     Output: 28
 * 
 *     Explanation:
 *     From (0,0) to (2,6), robot can move:
 *     - Right 6 times, Down 2 times: RRRRRRDD
 *     - Or any permutation of 6 R's and 2 D's
 *     - Total ways = C(8,2) = 28
 */

import java.util.*;

public class j04UniquePathsI {

    /**
     * Approach 1: Memoization (Top-Down DP)
     * 
     * Intuition:
     * - At each cell (i,j), robot can reach from top cell (i-1,j) or left cell (i,j-1)
     * - Base case: destination cell has 1 path
     * - Use memoization to avoid recalculating same subproblems
     * - Cache results in 2D array to store number of paths from each cell
     * 
     * Explanation:
     * - Step 1: Initialize memoization array with -1 to mark unvisited cells
     * - Step 2: For each cell, check if we've already calculated paths from here
     * - Step 3: If at destination, return 1 (base case)
     * - Step 4: If out of bounds, return 0 (invalid path)
     * - Step 5: Calculate paths by moving right and down recursively
     * - Step 6: Store result in cache and return sum of right and down paths
     * 
     * Time Complexity: O(m*n) - each cell visited once due to memoization
     * Space Complexity: O(m*n) - memoization array + recursion stack O(m+n)
     * 
     * @param m    Number of rows in the grid (1 <= m <= 100)
     * @param n    Number of columns in the grid (1 <= n <= 100)
     * @return     Number of unique paths from (0,0) to (m-1,n-1)
     */
    public static int uniquePaths(int m, int n) {
        // Initialize memoization array with -1 to mark unvisited cells
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }
        // Start recursive memoization from top-left corner
        return countUniquePathsMemo(m, n, 0, 0, dp);
    }

    /**
     * Helper Method: Recursive memoization function
     * 
     * Intuition:
     * - Recursively explore all possible paths from current position
     * - Use memoization to cache results and avoid redundant calculations
     * - Combine paths from right and down movements
     * 
     * Explanation:
     * - Check bounds: if out of grid, return 0 (invalid path)
     * - Base case: if at destination, return 1 (one valid path)
     * - Memoization check: if already calculated, return cached result
     * - Recursive calls: explore right and down movements
     * - Store and return sum of paths from both directions
     * 
     * Time Complexity: O(m*n) - each cell visited once
     * Space Complexity: O(m*n) - memoization array + O(m+n) recursion stack
     * 
     * @param m     Total rows in grid
     * @param n     Total columns in grid
     * @param row   Current row position
     * @param col   Current column position
     * @param dp    Memoization array to cache results
     * @return      Number of unique paths from current position to destination
     */
    private static int countUniquePathsMemo(int m, int n, int row, int col, int[][] dp) {
        // Check if current position is out of grid bounds
        if (row >= m || col >= n)
            return 0;
        // Base case: reached destination, found one valid path
        if (row == m - 1 && col == n - 1)
            return 1;
        // Return cached result if already calculated
        if (dp[row][col] != -1)
            return dp[row][col];

        // Recursively calculate paths by moving right and down
        int right = countUniquePathsMemo(m, n, row, col + 1, dp);
        int down = countUniquePathsMemo(m, n, row + 1, col, dp);

        // Cache and return total paths from current position
        return dp[row][col] = right + down;
    }

    /**
     * Approach 2: Tabulation (Bottom-Up DP)
     * 
     * Intuition:
     * - Fill DP table from destination backwards to starting position
     * - Each cell represents number of paths from that cell to destination
     * - Use bottom-up approach to avoid recursion overhead
     * - Build solution iteratively from known base cases
     * 
     * Explanation:
     * - Step 1: Initialize DP table with zeros
     * - Step 2: Set destination cell to 1 (base case)
     * - Step 3: Fill table bottom-up and right-to-left
     * - Step 4: Each cell = paths from cell below + paths from cell right
     * - Step 5: Return value at starting position (0,0)
     * 
     * Time Complexity: O(m*n) - visit each cell exactly once
     * Space Complexity: O(m*n) - DP table storage
     * 
     * @param m    Number of rows in the grid (1 <= m <= 100)
     * @param n    Number of columns in the grid (1 <= n <= 100)
     * @return     Number of unique paths from (0,0) to (m-1,n-1)
     */
    public static int uniquePathsTabulation(int m, int n) {
        // Initialize DP table with zeros
        int[][] dp = new int[m][n];
        // Fill table from destination backwards to starting position
        for(int row = m - 1; row >= 0; row--){
            for(int col = n - 1; col >= 0; col--){
                // Base case: destination cell has exactly one path
                if(row == m - 1 && col == n - 1){
                    dp[row][col] = 1;
                }else{
                    // Add paths from cell below if within bounds
                    if(row + 1 < m) dp[row][col] += dp[row + 1][col];
                    // Add paths from cell to the right if within bounds
                    if(col + 1 < n) dp[row][col] += dp[row][col + 1];
                }
            }
        }
        // Return number of paths from starting position
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Test Case 1: Basic cases
        System.out.println("\nBasic Test Cases:");
        System.out.println("Input: m=3, n=7, Expected: 28, Output: " + uniquePaths(3, 7));
        System.out.println("Input: m=3, n=2, Expected: 3, Output: " + uniquePaths(3, 2));
        System.out.println("Input: m=7, n=3, Expected: 28, Output: " + uniquePaths(7, 3));
        System.out.println("Input: m=3, n=3, Expected: 6, Output: " + uniquePaths(3, 3));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        System.out.println("Input: m=1, n=1, Expected: 1, Output: " + uniquePaths(1, 1));
        System.out.println("Input: m=1, n=100, Expected: 1, Output: " + uniquePaths(1, 100));
        System.out.println("Input: m=100, n=1, Expected: 1, Output: " + uniquePaths(100, 1));

        // Test Case 3: Boundary cases
        System.out.println("\nBoundary Cases:");
        System.out.println("Input: m=100, n=100, Output: " + uniquePaths(100, 100));
        System.out.println("Input: m=2, n=2, Expected: 2, Output: " + uniquePaths(2, 2));
        System.out.println("Input: m=10, n=10, Output: " + uniquePaths(10, 10));

        // Test Case 4: Tabulation approach comparison
        System.out.println("\nTabulation Approach:");
        System.out.println("Input: m=3, n=7, Expected: 28, Output: " + uniquePathsTabulation(3, 7));
        System.out.println("Input: m=3, n=2, Expected: 3, Output: " + uniquePathsTabulation(3, 2));
        System.out.println("Input: m=7, n=3, Expected: 28, Output: " + uniquePathsTabulation(7, 3));
    }
}
