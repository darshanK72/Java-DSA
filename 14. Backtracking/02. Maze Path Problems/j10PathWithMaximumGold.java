/**
 * Problem Statement:
 * 
 *     Given a 2D grid of integers, where each cell represents the amount of gold in that cell,
 *     you need to find the maximum amount of gold you can collect by starting from any cell and 
 *     moving in any of the four directions (up, down, left, right) while collecting gold. You can 
 *     only visit a cell with gold once, and after visiting a cell, you should stop moving through that cell.
 *     
 * Input:
 *     - A 2D integer array `grid` of size m x n where (1 <= m, n <= 100) and 0 <= grid[i][j] <= 1000.
 *     
 * Output:
 *     - An integer representing the maximum amount of gold that can be collected.
 * 
 * Example:
 *     Input:
 *     [[0, 6, 0],
 *      [5, 8, 7],
 *      [0, 9, 0]]
 *     Output:
 *     24
 *     
 *     Explanation:
 *     The maximum amount of gold that can be collected is 24 by following this path:
 *     (starting at grid[1][1]) -> 8 -> 7 -> 9
 *     Gold collected = 8 + 7 + 9 = 24.
 */

public class j10PathWithMaximumGold {

    /**
     * Approach 1: Backtracking with Memoization
     * 
     * Intuition:
     * - The core idea behind this approach is to explore all possible paths from each non-zero cell in the grid.
     * - We perform a Depth-First Search (DFS) starting from every cell that contains gold.
     * - At each step, we move to an adjacent cell (up, down, left, or right) and collect gold if the adjacent cell
     *   has a positive value. We also mark the cell as visited (set it to 0) to ensure we don’t revisit it.
     * - Once we reach a dead-end (no more valid moves), we backtrack by restoring the cell's value and continuing the search.
     * - We compute the total gold collected for each path and keep track of the maximum gold encountered.
     * 
     * Time Complexity:
     * - O(m * n), where m and n are the dimensions of the grid. We perform DFS starting from each cell that has gold.
     * - In the worst case, we explore all cells in the grid.
     * 
     * Space Complexity:
     * - O(m * n), for the space used to store the visited cells and recursive call stack during DFS.
     * 
     * @param grid The input grid of gold values.
     * @return The maximum gold collected through backtracking.
     */
    public int getMaximumGold1(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    // Call the backtracking function from every non-zero cell
                    max = Math.max(getMaxGoldBacktrack1(grid, i, j, max, 0), max);
                }
            }
        }
        return max;
    }

    public static int getMaxGoldBacktrack1(int[][] grid, int sr, int sc, int max, int curr) {
        // Base case: if out of bounds or no gold left in the current cell
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length || grid[sr][sc] == 0) {
            max = Math.max(curr, max); // Update max with the total collected gold so far
            return max;
        }

        int gold = grid[sr][sc]; // Store the current gold value
        grid[sr][sc] = 0; // Mark the current cell as visited (set to 0)

        // Explore all four directions and collect gold recursively
        max = Math.max(getMaxGoldBacktrack1(grid, sr + 1, sc, max, curr + gold), max); // Move down
        max = Math.max(getMaxGoldBacktrack1(grid, sr, sc + 1, max, curr + gold), max); // Move right
        max = Math.max(getMaxGoldBacktrack1(grid, sr - 1, sc, max, curr + gold), max); // Move up
        max = Math.max(getMaxGoldBacktrack1(grid, sr, sc - 1, max, curr + gold), max); // Move left

        grid[sr][sc] = gold; // Restore the cell's original value for further searches
        return max;
    }

    /**
     * Approach 2: Optimized Backtracking without extra memoization
     * 
     * Intuition:
     * - This approach is very similar to the first one, but instead of using a separate variable to keep track of
     *   the maximum gold found, we simplify the process by directly calculating the maximum gold that can be collected
     *   from each starting position.
     * - We start by checking all cells of the grid, and for each cell with gold, we recursively move in all four directions
     *   to find the total amount of gold that can be collected. We backtrack by marking the cell as visited and restoring
     *   its original value.
     * - This version reduces the need for an additional `max` argument in the recursive calls.
     * 
     * Time Complexity:
     * - O(m * n), where m and n are the dimensions of the grid. We perform DFS starting from each non-zero cell, which
     *   results in a linear number of calls relative to the number of cells.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used except for the recursion stack. The space for visited cells is implicit since
     *   we overwrite grid values as we traverse.
     * 
     * @param grid The input grid of gold values.
     * @return The maximum gold collected by direct backtracking.
     */
    public int getMaximumGold2(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    // Call the backtracking function from every non-zero cell
                    max = Math.max(max, getMaxGoldBacktrack2(grid, i, j));
                }
            }
        }
        return max;
    }

    private int getMaxGoldBacktrack2(int[][] grid, int row, int col) {
        // Base case: if out of bounds or no gold left in the current cell
        if (row < 0 || col < 0 || row >= grid.length || col >= grid[0].length || grid[row][col] == 0) {
            return 0;
        }

        int gold = grid[row][col]; // Store the current gold value
        grid[row][col] = 0; // Mark the current cell as visited

        // Explore all four directions (down, up, right, left) to collect gold
        // recursively
        int maxGold = 0;
        maxGold = Math.max(maxGold, getMaxGoldBacktrack2(grid, row + 1, col)); // Move down
        maxGold = Math.max(maxGold, getMaxGoldBacktrack2(grid, row - 1, col)); // Move up
        maxGold = Math.max(maxGold, getMaxGoldBacktrack2(grid, row, col + 1)); // Move right
        maxGold = Math.max(maxGold, getMaxGoldBacktrack2(grid, row, col - 1)); // Move left

        grid[row][col] = gold; // Restore the cell's original value
        return maxGold + gold; // Return the total gold collected
    }
}
