/**
 * LeetCode 1020. Number of Enclaves
 *
 * Problem Statement:
 *     You are given an m x n binary matrix grid, where 0 represents a sea cell
 *     and 1 represents a land cell. A move consists of walking from one land
 *     cell to another adjacent (4-directionally) land cell or walking off the
 *     boundary of the grid. Return the number of land cells in grid for which
 *     we cannot walk off the boundary of the grid in any number of moves.
 *
 * Input:
 *     - grid: 2D int array of size m x n (1 <= m, n <= 500),
 *       grid[i][j] is 0 (sea) or 1 (land)
 *
 * Output:
 *     - Integer representing the number of enclave land cells
 *
 * Example:
 *     Input: grid = [
 *         [0,0,0,0],
 *         [1,0,1,0],
 *         [0,1,1,0],
 *         [0,0,0,0]
 *     ]
 *     Output: 3
 *     Explanation: There are 3 enclave land cells (cannot reach boundary).
 *
 *     Input: grid = [
 *         [0,1,1,0],
 *         [0,0,1,0],
 *         [0,0,1,0],
 *         [0,0,0,0]
 *     ]
 *     Output: 0
 */

public class j05NumberOfEnclaves {

    /**
     * Approach: Flood Fill (DFS from Boundary)
     *
     * Intuition:
     * - Any land cell connected to the boundary is not an enclave.
     * - Flood fill (DFS) from all boundary land cells to mark them as sea (0).
     * - Count remaining land cells (1s) as enclaves.
     *
     * Explanation:
     * - Step 1: For each boundary cell, if it's land, start DFS to mark all
     *   connected land as sea.
     * - Step 2: After marking, count all remaining land cells in the grid.
     *
     * Time Complexity: O(m * n) where m = rows, n = columns
     * Space Complexity: O(m * n) for recursion stack in worst case
     *
     * @param grid   2D int array representing the map
     * @return       Number of enclave land cells
     */
    public static int numEnclaves(int[][] grid) {
        // Handle edge case: null or empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        // Flood fill from boundary rows
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        // Flood fill from boundary columns
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        int enclaves = 0;
        // Count remaining land cells
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    enclaves++;
            }
        }
        return enclaves;
    }

    /**
     * Helper Method: DFS to mark all boundary-connected land as sea
     *
     * Intuition:
     * - Explore all connected land cells from the boundary
     * - Mark them as sea to prevent counting as enclave
     *
     * Explanation:
     * - Base case: Out of bounds or sea cell
     * - Mark current cell as sea (0)
     * - Recursively visit all 4 neighbors (up, down, left, right)
     *
     * Time Complexity: O(size of connected region)
     * Space Complexity: O(size of connected region) for recursion stack
     *
     * @param grid   2D int array
     * @param i      Row index
     * @param j      Column index
     */
    private static void dfs(int[][] grid, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0)
            return;
        grid[i][j] = 0;
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] grid1 = {
            {0,0,0,0},
            {1,0,1,0},
            {0,1,1,0},
            {0,0,0,0}
        };
        System.out.println("Input: grid1, Expected: 3, Output: " + numEnclaves(copyGrid(grid1)));

        int[][] grid2 = {
            {0,1,1,0},
            {0,0,1,0},
            {0,0,1,0},
            {0,0,0,0}
        };
        System.out.println("Input: grid2, Expected: 0, Output: " + numEnclaves(copyGrid(grid2)));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] empty = {};
        System.out.println("Input: empty grid, Expected: 0, Output: " + numEnclaves(empty));
        int[][] singleSea = {{0}};
        System.out.println("Input: [[0]], Expected: 0, Output: " + numEnclaves(copyGrid(singleSea)));
        int[][] singleLand = {{1}};
        System.out.println("Input: [[1]], Expected: 0, Output: " + numEnclaves(copyGrid(singleLand)));

        // Test Case 3: All land
        System.out.println("\nAll Land:");
        int[][] allLand = {
            {1,1},
            {1,1}
        };
        System.out.println("Input: allLand, Expected: 0, Output: " + numEnclaves(copyGrid(allLand)));

        // Test Case 4: All sea
        System.out.println("\nAll Sea:");
        int[][] allSea = {
            {0,0},
            {0,0}
        };
        System.out.println("Input: allSea, Expected: 0, Output: " + numEnclaves(copyGrid(allSea)));
    }

    // Utility to deep copy a grid for testing
    private static int[][] copyGrid(int[][] grid) {
        if (grid == null || grid.length == 0) return grid;
        int[][] copy = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++)
            System.arraycopy(grid[i], 0, copy[i], 0, grid[0].length);
        return copy;
    }
}
