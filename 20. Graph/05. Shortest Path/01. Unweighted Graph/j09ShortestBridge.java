/**
 * LeetCode 934. Shortest Bridge
 *
 * Problem Statement:
 *     You are given an n x n binary matrix grid where exactly two islands exist.
 *     An island is a group of 1's (land) connected 4-directionally. You may flip
 *     0's (water) to 1's (land) to connect the two islands. Return the minimum
 *     number of 0's you must flip to connect the two islands (i.e., the length
 *     of the shortest bridge).
 *
 * Input:
 *     - grid (int[][]): n x n binary matrix (0 = water, 1 = land)
 *
 * Output:
 *     - int: Minimum number of 0's to flip to connect the two islands
 *
 * Example:
 *     Input: grid = [[0,1],[1,0]]
 *     Output: 1
 *
 *     Explanation:
 *     Flip one 0 to connect the two islands.
 *
 *     Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 *     Output: 2
 *
 *     Explanation:
 *     Flip two 0's to connect the two islands.
 */

import java.util.*;

public class j09ShortestBridge {
    /**
     * Approach: DFS to mark first island, then BFS to expand to second island
     *
     * Intuition:
     * - Use DFS to find and mark all cells of the first island, adding them to a queue.
     * - Use BFS from all first island cells to find the shortest path to the second island.
     * - Each BFS level represents flipping one 0 (water) to 1 (land).
     *
     * Explanation:
     * - Step 1: Find the first land cell and use DFS to mark the entire first island.
     * - Step 2: Add all first island cells to a queue for BFS.
     * - Step 3: Use BFS to expand outward, flipping water cells, until the second island is reached.
     * - Step 4: Return the number of flips (BFS levels) needed to reach the second island.
     *
     * Time Complexity: O(n^2) (each cell visited at most twice)
     * Space Complexity: O(n^2) for visited array and queue
     *
     * @param grid n x n binary matrix
     * @return Minimum number of 0's to flip to connect the two islands
     */
    public static int shortestBridge(int[][] grid) {
        int n = grid.length;
        int[] rowDir = new int[] { 1, 0, -1, 0 }; // Row deltas for 4 directions
        int[] colDir = new int[] { 0, 1, 0, -1 }; // Col deltas for 4 directions
        boolean[][] visited = new boolean[n][n]; // Track visited cells
        Queue<int[]> queue = new LinkedList<>(); // Queue for BFS, stores island cells
        boolean found = false; // Flag to indicate if first island is found
        // Step 1: Find and mark the first island using DFS
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, visited, queue, rowDir, colDir, i, j); // Mark first island
                    found = true;
                    break;
                }
            }
            if (found)
                break; // Stop after marking the first island
        }
        int steps = 0; // Number of flips (BFS levels)
        // Step 2: BFS from all first island cells to reach the second island
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of cells to process at current BFS level
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll(); // Get current cell coordinates
                int row = cell[0];
                int col = cell[1];
                // Try all 4 directions
                for (int d = 0; d < 4; d++) {
                    int r = row + rowDir[d];
                    int c = col + colDir[d];
                    // Check if next cell is within bounds and not visited
                    if (r >= 0 && r < n && c >= 0 && c < n && !visited[r][c]) {
                        if (grid[r][c] == 1) {
                            // If next cell is land, we've reached the second island
                            return steps;
                        }
                        visited[r][c] = true; // Mark water cell as visited
                        queue.add(new int[] { r, c }); // Add water cell to queue for next BFS level
                    }
                }
            }
            steps++; // Increment flip count after processing current level
        }
        return steps; // Should never reach here if input is valid
    }

    /**
     * Helper Method: DFS to mark all cells of the first island
     *
     * Intuition:
     * - Recursively visit all connected land cells and add them to the queue.
     *
     * Explanation:
     * - Mark current cell as visited and add to queue.
     * - Recursively visit all 4 neighbors if they are land and not visited.
     *
     * Time Complexity: O(n^2) in total for all DFS calls
     * Space Complexity: O(n^2) for recursion stack and visited array
     *
     * @param grid    n x n binary matrix
     * @param visited Visited array
     * @param queue   Queue to collect first island cells
     * @param rowDir  Row deltas
     * @param colDir  Col deltas
     * @param i       Current row
     * @param j       Current col
     */
    public static void dfs(int[][] grid, boolean[][] visited, Queue<int[]> queue, int[] rowDir, int[] colDir, int i,
            int j) {
        if (visited[i][j])
            return; // Skip if already visited
        visited[i][j] = true; // Mark current cell as visited
        queue.add(new int[] { i, j }); // Add current cell to queue
        for (int d = 0; d < 4; d++) {
            int row = i + rowDir[d]; // Next row index
            int col = j + colDir[d]; // Next col index
            // Check if next cell is within bounds, is land, and not visited
            if (row >= 0 && row < grid.length && col >= 0 && col < grid.length && grid[row][col] == 1
                    && !visited[row][col]) {
                dfs(grid, visited, queue, rowDir, colDir, row, col); // Recursively visit neighbor
            }
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[][] grid1 = {{0,1},{1,0}};
        System.out.println("Basic Test Case:");
        // Should return 1 as the minimum number of flips
        System.out.println("Expected: 1, Output: " + shortestBridge(grid1));

        // Test Case 2: Larger grid
        int[][] grid2 = {{0,1,0},{0,0,0},{0,0,1}};
        System.out.println("\nLarger Grid:");
        // Should return 2 as the minimum number of flips
        System.out.println("Expected: 2, Output: " + shortestBridge(grid2));

        // Test Case 3: Islands already adjacent
        int[][] grid3 = {{1,1,1,1,1},{1,0,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};
        System.out.println("\nIslands Already Adjacent:");
        // Should return 1 as only one flip is needed
        System.out.println("Expected: 1, Output: " + shortestBridge(grid3));
    }
}
