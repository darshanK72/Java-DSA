/**
 * LeetCode 200. Number of Islands
 *
 * Problem Statement:
 *     Given an m x n 2D binary grid grid which represents a map of '1's (land)
 *     and '0's (water), return the number of islands. An island is surrounded
 *     by water and is formed by connecting adjacent lands horizontally or
 *     vertically. You may assume all four edges of the grid are surrounded by water.
 *
 * Input:
 *     - grid: 2D char array of size m x n (1 <= m, n <= 300),
 *       grid[i][j] is '1' (land) or '0' (water)
 *
 * Output:
 *     - Integer representing the number of islands
 *
 * Example:
 *     Input: grid = [
 *         ['1','1','1','1','0'],
 *         ['1','1','0','1','0'],
 *         ['1','1','0','0','0'],
 *         ['0','0','0','0','0']
 *     ]
 *     Output: 1
 *
 *     Input: grid = [
 *         ['1','1','0','0','0'],
 *         ['1','1','0','0','0'],
 *         ['0','0','1','0','0'],
 *         ['0','0','0','1','1']
 *     ]
 *     Output: 3
 */

public class j04NumberOfIslands {

    /**
     * Approach: Depth-First Search (DFS)
     *
     * Intuition:
     * - Each island is a connected component of '1's in the grid.
     * - Use DFS to mark all cells in an island as visited.
     * - Count the number of times DFS is initiated from an unvisited '1'.
     *
     * Explanation:
     * - Step 1: Iterate through each cell in the grid.
     * - Step 2: If the cell is '1' and not visited, start DFS to mark the island.
     * - Step 3: Increment island count for each DFS initiation.
     *
     * Time Complexity: O(m * n) where m = rows, n = columns
     * Space Complexity: O(m * n) for visited array and recursion stack
     *
     * @param grid   2D char array representing the map
     * @return       Number of islands
     */
    public static int numIslands(char[][] grid) {
        // Handle edge case: null or empty grid
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int island = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    island++;
                    dfs(grid, visited, i, j);
                }
            }
        }
        return island;
    }

    /**
     * Helper Method: DFS to mark all cells in an island
     *
     * Intuition:
     * - Explore all connected '1's from the starting cell
     * - Mark each visited cell to avoid revisiting
     *
     * Explanation:
     * - Base case: Out of bounds, already visited, or water ('0')
     * - Mark current cell as visited
     * - Recursively visit all 4 neighbors (up, down, left, right)
     *
     * Time Complexity: O(size of island)
     * Space Complexity: O(size of island) for recursion stack
     *
     * @param grid     2D char array
     * @param visited  Visited array
     * @param i        Row index
     * @param j        Column index
     */
    private static void dfs(char[][] grid, boolean[][] visited, int i, int j) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || visited[i][j] || grid[i][j] == '0')
            return;
        visited[i][j] = true;
        dfs(grid, visited, i + 1, j);
        dfs(grid, visited, i - 1, j);
        dfs(grid, visited, i, j + 1);
        dfs(grid, visited, i, j - 1);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        char[][] grid1 = {
            {'1','1','1','1','0'},
            {'1','1','0','1','0'},
            {'1','1','0','0','0'},
            {'0','0','0','0','0'}
        };
        System.out.println("Input: grid1, Expected: 1, Output: " + numIslands(grid1));

        char[][] grid2 = {
            {'1','1','0','0','0'},
            {'1','1','0','0','0'},
            {'0','0','1','0','0'},
            {'0','0','0','1','1'}
        };
        System.out.println("Input: grid2, Expected: 3, Output: " + numIslands(grid2));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        char[][] empty = {};
        System.out.println("Input: empty grid, Expected: 0, Output: " + numIslands(empty));
        char[][] singleWater = {{'0'}};
        System.out.println("Input: [['0']], Expected: 0, Output: " + numIslands(singleWater));
        char[][] singleLand = {{'1'}};
        System.out.println("Input: [['1']], Expected: 1, Output: " + numIslands(singleLand));

        // Test Case 3: All land
        System.out.println("\nAll Land:");
        char[][] allLand = {
            {'1','1'},
            {'1','1'}
        };
        System.out.println("Input: allLand, Expected: 1, Output: " + numIslands(allLand));

        // Test Case 4: All water
        System.out.println("\nAll Water:");
        char[][] allWater = {
            {'0','0'},
            {'0','0'}
        };
        System.out.println("Input: allWater, Expected: 0, Output: " + numIslands(allWater));
    }
}
