/**
 * LeetCode 695. Max Area of Island
 *
 * Problem Statement:
 *     Given a non-empty 2D binary matrix grid of 0's (water) and 1's (land),
 *     return the area of the largest island in the grid. An island is a group
 *     of 1's connected 4-directionally (horizontal or vertical). You may assume
 *     all four edges of the grid are surrounded by water.
 *
 * Input:
 *     - grid: 2D int array of size m x n (1 <= m, n <= 50),
 *       grid[i][j] is 0 (water) or 1 (land)
 *
 * Output:
 *     - Integer representing the area of the largest island
 *
 * Example:
 *     Input: grid = [
 *         [0,0,1,0,0,0,0,1,0,0,0,0,0],
 *         [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *         [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *         [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *         [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *         [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *         [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *         [0,0,0,0,0,0,0,1,1,0,0,0,0]
 *     ]
 *     Output: 6
 *
 *     Input: grid = [
 *         [0,0,0,0,0,0,0,0]
 *     ]
 *     Output: 0
 */

public class j07MaxAreaOfIsland {

    /**
     * Approach: DFS to Calculate Area
     *
     * Intuition:
     * - Each island is a connected component of 1's in the grid.
     * - Use DFS to traverse each island and count its area.
     * - Track the maximum area found during traversal.
     *
     * Explanation:
     * - Step 1: Iterate through each cell in the grid.
     * - Step 2: If the cell is 1, start DFS to calculate the area of the island.
     * - Step 3: Update the maximum area if the current island is larger.
     *
     * Time Complexity: O(m * n) where m = rows, n = columns
     * Space Complexity: O(m * n) for recursion stack in worst case
     *
     * @param grid   2D int array representing the map
     * @return       Area of the largest island
     */
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int maxArea = 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int[] area = new int[1];
                    getArea(grid, i, j, area);
                    maxArea = Math.max(maxArea, area[0]);
                }
            }
        }
        return maxArea;
    }

    /**
     * Helper Method: DFS to calculate area of an island
     *
     * Intuition:
     * - Explore all connected 1's from the starting cell and count them.
     *
     * Explanation:
     * - Base case: Out of bounds or water cell
     * - Mark current cell as visited (set to 0)
     * - Increment area count
     * - Recursively visit all 4 neighbors (up, down, left, right)
     *
     * Time Complexity: O(size of island)
     * Space Complexity: O(size of island) for recursion stack
     *
     * @param grid   2D int array
     * @param i      Row index
     * @param j      Column index
     * @param area   Array of size 1 to accumulate area
     */
    private static void getArea(int[][] grid, int i, int j, int[] area) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0)
            return;
        grid[i][j] = 0;
        area[0]++;
        getArea(grid, i + 1, j, area);
        getArea(grid, i - 1, j, area);
        getArea(grid, i, j + 1, area);
        getArea(grid, i, j - 1, area);
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        System.out.println("Basic Test Cases:");
        int[][] grid1 = {
            {0,0,1,0,0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,1,1,0,1,0,0,0,0,0,0,0,0},
            {0,1,0,0,1,1,0,0,1,0,1,0,0},
            {0,1,0,0,1,1,0,0,1,1,1,0,0},
            {0,0,0,0,0,0,0,0,0,0,1,0,0},
            {0,0,0,0,0,0,0,1,1,1,0,0,0},
            {0,0,0,0,0,0,0,1,1,0,0,0,0}
        };
        System.out.println("Input: grid1, Expected: 6, Output: " + maxAreaOfIsland(copyGrid(grid1)));

        int[][] grid2 = {
            {0,0,0,0,0,0,0,0}
        };
        System.out.println("Input: grid2, Expected: 0, Output: " + maxAreaOfIsland(copyGrid(grid2)));

        // Test Case 2: Edge cases
        System.out.println("\nEdge Cases:");
        int[][] empty = {};
        System.out.println("Input: empty grid, Expected: 0, Output: " + maxAreaOfIsland(empty));
        int[][] singleWater = {{0}};
        System.out.println("Input: [[0]], Expected: 0, Output: " + maxAreaOfIsland(copyGrid(singleWater)));
        int[][] singleLand = {{1}};
        System.out.println("Input: [[1]], Expected: 1, Output: " + maxAreaOfIsland(copyGrid(singleLand)));

        // Test Case 3: All land
        System.out.println("\nAll Land:");
        int[][] allLand = {
            {1,1},
            {1,1}
        };
        System.out.println("Input: allLand, Expected: 4, Output: " + maxAreaOfIsland(copyGrid(allLand)));

        // Test Case 4: All water
        System.out.println("\nAll Water:");
        int[][] allWater = {
            {0,0},
            {0,0}
        };
        System.out.println("Input: allWater, Expected: 0, Output: " + maxAreaOfIsland(copyGrid(allWater)));
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
