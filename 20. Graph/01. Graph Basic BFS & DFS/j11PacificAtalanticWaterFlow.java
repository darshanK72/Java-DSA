/**
 * LeetCode 417. Pacific Atlantic Water Flow
 * 
 * Problem Statement:
 *     There is an m x n rectangular island that borders both the Pacific Ocean and
 *     Atlantic Ocean. The Pacific Ocean touches the island's left and top edges,
 *     and the Atlantic Ocean touches the island's right and bottom edges.
 *     The island is represented by an m x n matrix of integers heights where
 *     heights[r][c] represents the height above sea level at coordinate (r, c).
 *     The water can only flow in four directions (up, down, left, or right) from
 *     a cell to another one with height equal or lower.
 *     Return a list of grid coordinates where water can flow to both the Pacific
 *     and Atlantic oceans.
 * 
 * Input:
 *     - heights (1 <= m, n <= 200): 2D integer array representing the island
 *     - heights[r][c] (0 <= heights[r][c] <= 10^5)
 * 
 * Output:
 *     - List of lists, where each list contains two integers [r, c] representing
 *       a cell that can flow to both oceans
 * 
 * Example:
 *     Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
 *     Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
 * 
 *     Explanation:
 *     - Water can flow from these coordinates to both the Pacific and Atlantic.
 *     - For example, cell (3,0) can flow up to the Pacific and down/right to the Atlantic.
 */

import java.util.*;

public class j11PacificAtalanticWaterFlow {

    /**
     * Approach: Reverse DFS from Ocean Borders
     * 
     * Intuition:
     * - Instead of simulating water flow from every cell, we reverse the process:
     *   start DFS from the ocean borders and mark all cells reachable from each ocean.
     * - The intersection of cells reachable from both oceans gives the answer.
     * 
     * Explanation:
     * - For each cell on the Pacific border (top row, left column), run DFS to mark
     *   all cells that can reach the Pacific.
     * - For each cell on the Atlantic border (bottom row, right column), run DFS to
     *   mark all cells that can reach the Atlantic.
     * - For each cell, if it is marked as reachable from both oceans, add to result.
     * 
     * Time Complexity: O(m * n) -- Each cell is visited at most twice (once per ocean)
     * Space Complexity: O(m * n) -- For the visited arrays and recursion stack
     * 
     * @param heights 2D array of heights
     * @return List of coordinates that can flow to both oceans
     */
    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] pacific = new boolean[m][n]; // Reachable from Pacific
        boolean[][] atlantic = new boolean[m][n]; // Reachable from Atlantic

        // Run DFS from all Pacific border cells
        for (int i = 0; i < m; i++) {
            dfs(heights, i, 0, Integer.MIN_VALUE, pacific); // Left edge
            dfs(heights, i, n - 1, Integer.MIN_VALUE, atlantic); // Right edge
        }
        for (int j = 0; j < n; j++) {
            dfs(heights, 0, j, Integer.MIN_VALUE, pacific); // Top edge
            dfs(heights, m - 1, j, Integer.MIN_VALUE, atlantic); // Bottom edge
        }

        List<List<Integer>> out = new ArrayList<>();
        // Collect cells reachable from both oceans
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    out.add(Arrays.asList(i, j));
                }
            }
        }
        return out;
    }

    /**
     * Helper Method: DFS to mark reachable cells
     * 
     * Intuition:
     * - From the current cell, recursively visit neighbors with height >= current cell
     *   (since water can only flow from high to low or equal).
     * - Mark visited cells in the ocean matrix.
     * 
     * Explanation:
     * - If out of bounds, return.
     * - If current cell is lower than previous, or already visited, return.
     * - Mark cell as visited, then DFS in all four directions.
     * 
     * Time Complexity: O(m * n) per ocean
     * Space Complexity: O(m * n) for visited
     * 
     * @param heights 2D array of heights
     * @param i Row index
     * @param j Column index
     * @param prev Height of previous cell
     * @param ocean Visited matrix for current ocean
     */
    private static void dfs(int[][] heights, int i, int j, int prev, boolean[][] ocean) {
        // Check for out-of-bounds
        if (i < 0 || i >= heights.length || j < 0 || j >= heights[0].length)
            return;
        // If current cell is lower than previous or already visited, stop
        if (heights[i][j] < prev || ocean[i][j])
            return;
        // Mark cell as visited for this ocean
        ocean[i][j] = true;
        // Explore all four directions
        dfs(heights, i, j + 1, heights[i][j], ocean); // Right
        dfs(heights, i - 1, j, heights[i][j], ocean); // Up
        dfs(heights, i, j - 1, heights[i][j], ocean); // Left
        dfs(heights, i + 1, j, heights[i][j], ocean); // Down
    }

    public static void main(String[] args) {
        // Basic Test Case
        System.out.println("\nBasic Test Case:");
        int[][] heights1 = {
            {1,2,2,3,5},
            {3,2,3,4,4},
            {2,4,5,3,1},
            {6,7,1,4,5},
            {5,1,1,2,4}
        };
        System.out.println("Input: [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]");
        System.out.println("Expected: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]");
        System.out.println("Output:   " + pacificAtlantic(heights1));

        // Edge Case: Single cell
        System.out.println("\nEdge Case: Single cell");
        int[][] heights2 = {{42}};
        System.out.println("Input: [[42]]");
        System.out.println("Expected: [[0,0]]");
        System.out.println("Output:   " + pacificAtlantic(heights2));

        // Boundary Case: All same height
        System.out.println("\nBoundary Case: All same height");
        int[][] heights3 = {
            {1,1,1},
            {1,1,1},
            {1,1,1}
        };
        System.out.println("Input: [[1,1,1],[1,1,1],[1,1,1]]");
        System.out.println("Expected: All cells");
        System.out.println("Output:   " + pacificAtlantic(heights3));

        // Special Case: Increasing rows
        System.out.println("\nSpecial Case: Increasing rows");
        int[][] heights4 = {
            {1,2,3},
            {2,3,4},
            {3,4,5}
        };
        System.out.println("Input: [[1,2,3],[2,3,4],[3,4,5]]");
        System.out.println("Output:   " + pacificAtlantic(heights4));
    }
}
