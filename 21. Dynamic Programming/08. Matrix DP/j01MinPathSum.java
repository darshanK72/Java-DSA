/**
 * LeetCode 64. Minimum Path Sum
 * 
 * Problem Statement:
 *     Given a m x n grid filled with non-negative numbers, find a path from
 *     top-left to bottom-right, which minimizes the sum of all numbers along
 *     its path. You can only move either down or right at any point in time.
 * 
 * Input:
 *     - grid (1 <= m, n <= 200): a 2D int array with non-negative values
 * 
 * Output:
 *     - The minimum possible path sum from (0,0) to (m-1,n-1)
 * 
 * Example:
 *     Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *     Output: 7
 * 
 *     Explanation:
 *     The path 1 -> 3 -> 1 -> 1 -> 1 has a total sum of 7 and is minimal.
 */
import java.util.Arrays;

public class j01MinPathSum {
    /**
     * Approach 1: Top-Down DP (Memoization) from start (0,0)
     * 
     * Intuition:
     * - This is an optimal substructure problem on a grid. To minimize the
     *   cost from (0,0) to (m-1,n-1), at any cell (r,c) we must choose the
     *   cheaper of the two allowed next moves: down to (r+1,c) or right to
     *   (r,c+1). The minimal cost from (r,c) is therefore:
     *       grid[r][c] + min(cost(r+1,c), cost(r,c+1)).
     * - Overlapping subproblems occur because many paths recompute costs for
     *   the same suffix cells. Memoization ensures each (r,c) is solved once.
     * - We treat moves that go out of bounds as having an infinite cost so
     *   they are never chosen by the min.
     * 
     * Explanation:
     * - Build a recursive function f(r,c) returning minimal path sum from
     *   (r,c) to destination.
     *   Step 1: Base conditions
     *     - If (r,c) is outside the grid, return a large sentinel (acts like
     *       +∞) so this path is never selected.
     *     - If (r,c) is the destination, return grid[r][c].
     *   Step 2: Memoization check
     *     - If dp[r][c] is already computed, reuse it.
     *   Step 3: Recurse
     *     - Compute down = f(r+1,c) and right = f(r,c+1).
     *   Step 4: Combine
     *     - dp[r][c] = grid[r][c] + min(down, right).
     *   Step 5: Return dp[r][c].
     * - Initialize dp with -1 to denote unknown states and call f(0,0).
     * 
     * Time Complexity: O(m*n) — each state computed once
     * Space Complexity: O(m*n) for memo table + O(m+n) recursion stack
     * 
     * @param grid 2D grid of non-negative ints (m x n)
     * @return minimal path sum from (0,0) to (m-1,n-1)
     */
    public static int minPathSum(int[][] grid) {
        // Handle edge cases (null or empty grid)
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];            // memo table for minimal costs
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);            // mark all states as unknown
        }
        // Start recursion at top-left cell
        return minPathSumMemo(grid, dp, 0, 0);
    }

    /**
     * Helper Method: minPathSumMemo
     * 
     * Intuition:
     * - Recursively compute minimal cost from (row,col) to destination by
     *   picking the cheaper of moving down or right.
     * 
     * Explanation:
     * - Base cases guard out-of-bounds (return large sentinel) and destination
     *   cell (return its value). Use memo table `dp` to avoid recomputation.
     * 
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n) + recursion depth O(m+n)
     * 
     * @param grid grid values
     * @param dp memo table initialized with -1
     * @param row current row index
     * @param col current column index
     * @return minimal path sum from (row,col) to bottom-right
     */
    public static int minPathSumMemo(int[][] grid, int[][] dp, int row, int col) {
        // Step A: Guard out-of-bounds by returning a large sentinel
        if (row >= grid.length || col >= grid[0].length)
            return Integer.MAX_VALUE / 2;
        // Step B: If at destination, return its value (no further moves)
        if (row == grid.length - 1 && col == grid[0].length - 1) {
            return grid[row][col];
        }
        // Step C: If already computed, reuse memoized answer
        if (dp[row][col] != -1)
            return dp[row][col];

        // Step D: Recurse to two choices — down and right
        int down = minPathSumMemo(grid, dp, row + 1, col);   // move down
        int right = minPathSumMemo(grid, dp, row, col + 1);  // move right

        // Step E: Combine current cell cost with minimal of the two choices
        return dp[row][col] = grid[row][col] + Math.min(down, right);
    }

    /**
     * Approach 2: Top-Down DP (Memoization) from end (m-1,n-1)
     * 
     * Intuition:
     * - Consider the reverse traversal: to compute the cost of reaching
     *   (r,c) from (0,0), we can arrive either from the left (r,c-1) or from
     *   above (r-1,c). The minimal cost to reach (r,c) equals:
     *       grid[r][c] + min(cost(r,c-1), cost(r-1,c)).
     * - This is the mirror image of Approach 1. We recurse backward starting
     *   at the destination and move left/up until we hit the origin.
     * 
     * Explanation:
     * - Define g(r,c) as the minimal cost to reach (r,c) from origin.
     *   Step 1: Base conditions
     *     - If (r,c) is outside the grid, return a large sentinel (+∞).
     *     - If (r,c) is the origin, return grid[r][c].
     *   Step 2: Memoization check for dp[r][c].
     *   Step 3: Recurse to left and up predecessors.
     *   Step 4: Combine with current cell value and memoize.
     * - Initialize dp with -1 and call g(m-1,n-1).
     * 
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n) + recursion depth O(m+n)
     * 
     * @param grid 2D grid of non-negative ints (m x n)
     * @return minimal path sum from (0,0) to (m-1,n-1)
     */
    public static  int minPathSumBackword(int[][] grid) {
        // Handle edge cases (null or empty grid)
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m][n];            // memo table for reach-costs
        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);            // mark all states as unknown
        }
        // Recurse backward from destination using the correct helper
        return minPathSumMemoBackword(grid, dp, m - 1, n - 1);
    }

    /**
     * Helper Method: minPathSumMemoBackword
     * 
     * Intuition:
     * - Reverse the traversal choice: from (row,col) move left or up and take
     *   the cheaper path towards the origin.
     * 
     * Explanation:
     * - Guard out-of-bounds with large sentinel; origin returns its value.
     *   Memoize results in `dp`.
     * 
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n) + recursion depth O(m+n)
     * 
     * @param grid grid values
     * @param dp memo table initialized with -1
     * @param row current row index
     * @param col current column index
     * @return minimal path sum from origin to (row,col)
     */
    public static int minPathSumMemoBackword(int[][] grid, int[][] dp, int row, int col) {
        // Step A: Guard out-of-bounds (treat as +∞ so path isn't chosen)
        if (row < 0 || col < 0)
            return Integer.MAX_VALUE / 2;
        // Step B: Reaching origin: cost equals its own value
        if (row == 0 && col == 0)
            return grid[row][col];
        // Step C: Reuse memoized answer if available
        if (dp[row][col] != -1)
            return dp[row][col];

        // Step D: Recurse to predecessors — left and up
        int left = minPathSumMemoBackword(grid, dp, row, col - 1); // move left
        int up = minPathSumMemoBackword(grid, dp, row - 1, col);   // move up

        // Step E: Current cell value plus better predecessor cost
        return dp[row][col] = Math.min(left, up) + grid[row][col];
    }

    /**
     * Approach 3: Bottom-Up DP (Tabulation)
     * 
     * Intuition:
     * - The same recurrence can be computed iteratively. If we know minimal
     *   costs for cells to the right and below, we can compute the current
     *   cell in O(1). Filling from bottom-right to top-left ensures all
     *   dependencies are already available.
     * 
     * Explanation:
     * - Create dp with one extra row and column filled with +∞ sentinels so
     *   that for boundary cells we can uniformly read dp[r+1][c] and
     *   dp[r][c+1] without separate checks.
     *   Step 1: Initialize the sentinel borders.
     *   Step 2: Set destination dp[m-1][n-1] = grid[m-1][n-1].
     *   Step 3: Iterate rows from m-1..0 and cols from n-1..0, combining the
     *           current grid value with min(dp[r+1][c], dp[r][c+1]).
     *   Step 4: Answer is dp[0][0].
     * 
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     * 
     * @param grid 2D grid of non-negative ints (m x n)
     * @return minimal path sum from (0,0) to (m-1,n-1)
     */
    public static int minPathSumTabulation(int[][] grid) {
        // Handle edge cases (null or empty grid)
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        int m = grid.length;
        int n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];   // extra border for uniform logic
        for(int row = 0; row <= m; row++){
            dp[row][n] = Integer.MAX_VALUE;    // sentinel on the right border
        }
        for(int col = 0; col <= n; col++){
            dp[m][col] = Integer.MAX_VALUE;    // sentinel on the bottom border
        }
        
        for(int row = m - 1; row >= 0; row--){
            for(int col = n - 1; col >= 0; col--){
                if(row == m - 1 && col == n - 1){
                    dp[row][col] = grid[row][col]; // destination cell cost
                }else{
                    int down = dp[row + 1][col];    // cost if moving down next
                    int right = dp[row][col + 1];   // cost if moving right next
                    // Current cell = its value + better of the two choices
                    dp[row][col] = grid[row][col] + Math.min(down,right);
                }
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        // Basic / Happy path cases
        System.out.println("\nBasic Test Cases:");
        int[][] g1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Grid: [[1,3,1],[1,5,1],[4,2,1]]  Expected: 7  -> "
                + minPathSum(g1));
        int[][] g2 = {{1,2},{1,1}};
        System.out.println("Grid: [[1,2],[1,1]]              Expected: 3  -> "
                + minPathSumBackword(g2));

        // Edge cases (null, empty)
        System.out.println("\nEdge Cases:");
        System.out.println("Grid: null                        Expected: 0  -> "
                + minPathSum(null));
        System.out.println("Grid: [[]] (invalid)              Expected: 0  -> "
                + minPathSum(new int[][]{{}}));

        // Boundary cases (single row/column)
        System.out.println("\nBoundary Cases:");
        int[][] g3 = {{5,1,2,3}};
        System.out.println("Grid: [[5,1,2,3]]                 Expected: 11 -> "
                + minPathSumTabulation(g3));
        int[][] g4 = {{5},{1},{2},{3}};
        System.out.println("Grid: [[5],[1],[2],[3]]           Expected: 11 -> "
                + minPathSum(g4));

        // Special / Larger case
        System.out.println("\nSpecial/Large Cases:");
        int[][] g5 = {
            {1, 3, 1, 2},
            {2, 1, 8, 1},
            {4, 2, 1, 3},
            {1, 2, 1, 1}
        };
        System.out.println("Grid: 4x4 mixed                    -> "
                + minPathSumTabulation(g5));
    }
}
