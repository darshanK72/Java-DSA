/**
 * LeetCode 463. Island Perimeter
 *
 * Problem Statement:
 *     You are given a map in the form of a 2D grid where 1 represents land and 0 represents water.
 *     Grid cells are connected horizontally/vertically (not diagonally). There is exactly one island (all 1s are connected).
 *     Return the perimeter of the island.
 *
 * Input:
 *     - grid (1 <= m, n <= 100): 2D grid of 0s and 1s
 *
 * Output:
 *     - Integer: The perimeter of the island
 *
 * Example:
 *     Input: grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 *     Output: 16
 *
 *     Explanation:
 *     The perimeter is the sum of all edges that are adjacent to water or the grid boundary.
 */

public class j14IcelandPerimeter {
    /**
     * Approach 1: DFS Traversal
     *
     * Intuition:
     * - Start DFS from any land cell. For each land cell, check all 4 directions.
     * - If a neighbor is water or out of bounds, increment perimeter.
     * - Mark visited cells to avoid revisiting.
     *
     * Explanation:
     * - Step 1: Find any land cell to start DFS.
     * - Step 2: For each cell, recursively visit all connected land cells.
     * - Step 3: For each direction, if neighbor is water or out of bounds, add to perimeter.
     *
     * Time Complexity: O(m*n) (each cell visited at most once)
     * Space Complexity: O(m*n) for visited array and recursion stack
     *
     * @param grid   2D grid of 0s and 1s
     * @return      Perimeter of the island
     */
    public static int islandPerimeterDFS(int[][] grid) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns

        int[] rowDir = new int[] { -1, 0, 1, 0 }; // Row direction for 4 neighbors
        int[] colDir = new int[] { 0, 1, 0, -1 }; // Column direction for 4 neighbors
        int row = -1; // Row index of any land cell
        int col = -1; // Column index of any land cell

        // Find any land cell to start DFS
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) { // Found a land cell
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        int[] perimeter = new int[1]; // Array to hold perimeter count (mutable in recursion)
        dfs(grid, new boolean[m][n], row, col, perimeter, rowDir, colDir); // Start DFS
        return perimeter[0]; // Return computed perimeter
    }

    /**
     * Helper Method: DFS to calculate perimeter
     *
     * Intuition:
     * - For each land cell, check all 4 directions. If neighbor is water or out of bounds, increment perimeter.
     *
     * Explanation:
     * - Recursively visit all connected land cells, marking them as visited.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     *
     * @param grid      2D grid
     * @param visited   Visited array
     * @param i         Current row
     * @param j         Current column
     * @param perimeter Array holding perimeter count
     * @param rowDir    Row direction array
     * @param colDir    Column direction array
     * @return          void
     */
    public static void dfs(int[][] grid, boolean[][] visited, int i, int j, int[] perimeter, int[] rowDir, int[] colDir) {
        visited[i][j] = true; // Mark current cell as visited
        for (int d = 0; d < 4; d++) { // Explore all 4 directions
            int row = i + rowDir[d]; // Compute neighbor row
            int col = j + colDir[d]; // Compute neighbor column
            if (row < grid.length && col < grid[0].length && row >= 0 && col >= 0) { // Check bounds
                if (grid[row][col] == 0) { // Neighbor is water
                    perimeter[0]++; // Increment perimeter for water edge
                } else if (!visited[row][col]) { // Neighbor is unvisited land
                    dfs(grid, visited, row, col, perimeter, rowDir, colDir); // Recurse on neighbor
                }
            } else {
                perimeter[0]++; // Out of bounds, so increment perimeter
            }
        }
    }

    /**
     * Approach 2: Efficient Iterative Counting
     *
     * Intuition:
     * - For each land cell, check all 4 directions. If neighbor is water or out of bounds, increment perimeter.
     * - No need for visited array since we count perimeter directly.
     *
     * Explanation:
     * - Iterate through all cells. For each land cell, check up, down, left, right.
     * - If neighbor is water or out of bounds, add to perimeter.
     *
     * Time Complexity: O(m*n)
     * Space Complexity: O(1)
     *
     * @param grid   2D grid of 0s and 1s
     * @return      Perimeter of the island
     */
    public static int islandPerimeterEfficient(int[][] grid) {
        int m = grid.length; // Number of rows
        int n = grid[0].length; // Number of columns
        int perimeter = 0; // Initialize perimeter

        for (int i = 0; i < m; i++) { // Iterate over all rows
            for (int j = 0; j < n; j++) { // Iterate over all columns
                if (grid[i][j] == 1) { // If current cell is land
                    // Check top edge
                    if(i == 0 || grid[i-1][j] == 0) perimeter++; // Top is water or out of bounds
                    // Check bottom edge
                    if(i == m - 1 || grid[i + 1][j] == 0) perimeter++; // Bottom is water or out of bounds
                    // Check left edge
                    if(j == 0 || grid[i][j-1] == 0) perimeter++; // Left is water or out of bounds
                    // Check right edge
                    if(j == n - 1 || grid[i][j+1] == 0) perimeter++; // Right is water or out of bounds
                }
            }
        }
        return perimeter; // Return computed perimeter
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] grid1 = {
            {0,1,0,0},
            {1,1,1,0},
            {0,1,0,0},
            {1,1,0,0}
        };
        System.out.println("Input: [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]], Expected: 16, Output (DFS): " + islandPerimeterDFS(grid1));
        System.out.println("Output (Efficient): " + islandPerimeterEfficient(grid1));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] grid2 = {{1}};
        System.out.println("Input: [[1]], Expected: 4, Output (DFS): " + islandPerimeterDFS(grid2));
        System.out.println("Output (Efficient): " + islandPerimeterEfficient(grid2));
        int[][] grid3 = {{0}};
        System.out.println("Input: [[0]], Expected: 0, Output (DFS): " + islandPerimeterDFS(grid3));
        System.out.println("Output (Efficient): " + islandPerimeterEfficient(grid3));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] grid4 = new int[1][10];
        for (int i = 0; i < 10; i++) grid4[0][i] = 1; // Fill row with land
        System.out.println("Input: 1x10 all land, Output (DFS): " + islandPerimeterDFS(grid4));
        System.out.println("Output (Efficient): " + islandPerimeterEfficient(grid4));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] grid5 = {
            {1,0,1,0},
            {0,1,0,1},
            {1,0,1,0},
            {0,1,0,1}
        };
        System.out.println("Input: checkerboard, Output (DFS): " + islandPerimeterDFS(grid5));
        System.out.println("Output (Efficient): " + islandPerimeterEfficient(grid5));
    }
}
