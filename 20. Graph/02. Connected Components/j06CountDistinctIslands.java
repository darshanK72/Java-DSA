/**
 * GFG: Count Distinct Islands
 *
 * Problem Statement:
 *     Given a boolean 2D matrix grid of size n x m. An island is a group of
 *     connected 1s (land) connected 4-directionally (horizontal or vertical).
 *     Two islands are considered to be distinct if and only if one island is
 *     not equal to another (not the same shape after translation).
 *     Return the number of distinct islands.
 *
 * Input:
 *     - grid: 2D int array of size n x m (1 <= n, m <= 50),
 *       grid[i][j] is 0 (water) or 1 (land)
 *
 * Output:
 *     - Integer representing the number of distinct islands
 *
 * Example:
 *     Input: grid = [
 *         [1,1,0,0,0],
 *         [1,1,0,0,0],
 *         [0,0,0,1,1],
 *         [0,0,0,1,1]
 *     ]
 *     Output: 1
 *     Explanation: Both islands have the same shape.
 *
 *     Input: grid = [
 *         [1,1,0,1,1],
 *         [1,0,0,0,0],
 *         [0,0,0,0,1],
 *         [1,1,0,1,1]
 *     ]
 *     Output: 3
 */

import java.util.*;

public class j06CountDistinctIslands {

    /**
     * Approach: DFS + Shape Encoding
     *
     * Intuition:
     * - Each island's shape can be encoded by the relative path taken from the
     *   starting cell.
     * - Use DFS to traverse each island and record the path as a string.
     * - Store unique path strings in a set to count distinct shapes.
     *
     * Explanation:
     * - Step 1: For each unvisited land cell, start DFS and encode the path.
     * - Step 2: Add the path string to a set if not already present.
     * - Step 3: The size of the set is the number of distinct islands.
     *
     * Time Complexity: O(n * m) where n = rows, m = columns
     * Space Complexity: O(n * m) for visited grid and set
     *
     * @param grid   2D int array representing the map
     * @return       Number of distinct islands
     */
    public static int countDistinctIslands(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        HashSet<String> set = new HashSet<>();
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder path = new StringBuilder();
                    dfs(grid, i, j, path, 'O'); // 'O' for origin
                    set.add(path.toString());
                }
            }
        }
        return set.size();
    }

    /**
     * Helper Method: DFS to encode island shape
     *
     * Intuition:
     * - Record the sequence of moves (directions) as a string to uniquely
     *   identify the island's shape.
     *
     * Explanation:
     * - Use a character for each direction: D (down), U (up), R (right), L (left)
     * - Add a separator (e.g., 'B' for backtrack) after each recursive call
     *   to distinguish different shapes
     *
     * Time Complexity: O(size of island)
     * Space Complexity: O(size of island) for recursion stack
     *
     * @param grid   2D int array
     * @param i      Current row
     * @param j      Current column
     * @param path   StringBuilder to record path
     * @param dir    Direction character
     */
    private static void dfs(int[][] grid, int i, int j, StringBuilder path, char dir) {
        int n = grid.length, m = grid[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0)
            return;
        grid[i][j] = 0;
        path.append(dir);
        dfs(grid, i + 1, j, path, 'D');
        dfs(grid, i - 1, j, path, 'U');
        dfs(grid, i, j + 1, path, 'R');
        dfs(grid, i, j - 1, path, 'L');
        path.append('B'); // Backtrack marker
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] grid1 = {
            {1,1,0,0,0},
            {1,1,0,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        };
        System.out.println("Input: grid1, Expected: 1, Output: " + countDistinctIslands(copyGrid(grid1)));

        int[][] grid2 = {
            {1,1,0,1,1},
            {1,0,0,0,0},
            {0,0,0,0,1},
            {1,1,0,1,1}
        };
        System.out.println("Input: grid2, Expected: 3, Output: " + countDistinctIslands(copyGrid(grid2)));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] empty = {};
        System.out.println("Input: empty grid, Expected: 0, Output: " + countDistinctIslands(empty));
        int[][] singleWater = {{0}};
        System.out.println("Input: [[0]], Expected: 0, Output: " + countDistinctIslands(copyGrid(singleWater)));
        int[][] singleLand = {{1}};
        System.out.println("Input: [[1]], Expected: 1, Output: " + countDistinctIslands(copyGrid(singleLand)));

        // Test Case 3: All land
        System.out.println("\nAll Land:");
        int[][] allLand = {
            {1,1},
            {1,1}
        };
        System.out.println("Input: allLand, Expected: 1, Output: " + countDistinctIslands(copyGrid(allLand)));

        // Test Case 4: All water
        System.out.println("\nAll Water:");
        int[][] allWater = {
            {0,0},
            {0,0}
        };
        System.out.println("Input: allWater, Expected: 0, Output: " + countDistinctIslands(copyGrid(allWater)));
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
