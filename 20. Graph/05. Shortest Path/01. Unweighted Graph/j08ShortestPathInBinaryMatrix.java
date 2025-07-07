/**
 * LeetCode 1091. Shortest Path in Binary Matrix
 *
 * Problem Statement:
 *     Given an n x n binary matrix grid, return the length of the shortest
 *     clear path from the top-left cell (0,0) to the bottom-right cell (n-1,n-1).
 *     A clear path is a path where every visited cell is 0, and you can move
 *     8 directions (horizontally, vertically, or diagonally). If no such path
 *     exists, return -1.
 *
 * Input:
 *     - grid (int[][]): n x n binary matrix (0 = open, 1 = blocked)
 *
 * Output:
 *     - int: Length of shortest clear path, or -1 if impossible
 *
 * Example:
 *     Input: grid = [[0,1],[1,0]]
 *     Output: 2
 *
 *     Explanation:
 *     Path: (0,0) -> (1,1)
 *
 *     Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
 *     Output: 4
 *
 *     Explanation:
 *     Path: (0,0) -> (0,1) -> (0,2) -> (1,2) -> (2,2)
 */

import java.util.*;

public class j08ShortestPathInBinaryMatrix {
    /**
     * Helper class to store cell position and distance for BFS
     */
    static class Cell {
        int row;
        int col;
        int dist;
        Cell(int row, int col, int dist) {
            this.row = row;
            this.col = col;
            this.dist = dist;
        }
    }

    /**
     * Approach: Breadth-First Search (BFS)
     *
     * Intuition:
     * - Each cell is a node; edges exist to all 8 neighbors if open (0).
     * - Use BFS to find the shortest path from (0,0) to (n-1,n-1).
     * - Use a visited array to avoid revisiting cells.
     *
     * Explanation:
     * - Step 1: If start or end is blocked, return -1.
     * - Step 2: Start BFS from (0,0), mark as visited.
     * - Step 3: For each cell, try all 8 directions.
     * - Step 4: If neighbor is open and unvisited, add to queue.
     * - Step 5: If (n-1,n-1) is reached, return distance.
     * - Step 6: If queue is empty and end not reached, return -1.
     *
     * Time Complexity: O(n^2) (each cell visited at most once)
     * Space Complexity: O(n^2) for visited array and queue
     *
     * @param grid n x n binary matrix
     * @return Length of shortest clear path, or -1 if impossible
     */
    public static int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1)
            return -1; // If start or end is blocked, no path exists
        int[] rowDir = new int[] { 0, 1, 0, -1, 1, 1, -1, -1 }; // Row deltas for 8 directions
        int[] colDir = new int[] { 1, 0, -1, 0, 1, -1, 1, -1 }; // Col deltas for 8 directions
        boolean[][] visited = new boolean[n][n]; // Track visited cells
        Queue<Cell> queue = new LinkedList<>(); // Queue for BFS, stores cell and distance
        queue.add(new Cell(0, 0, 1)); // Start BFS from (0,0) with distance 1
        visited[0][0] = true; // Mark start cell as visited
        while (!queue.isEmpty()) {
            Cell cell = queue.poll(); // Get current cell and distance
            if (cell.row == n - 1 && cell.col == n - 1) {
                return cell.dist; // If reached end cell, return distance
            }
            // Try all 8 possible directions
            for (int d = 0; d < 8; d++) {
                int row = cell.row + rowDir[d]; // Next row index
                int col = cell.col + colDir[d]; // Next col index
                // Check if next cell is within bounds, unvisited, and open
                if (row >= 0 && row < n && col >= 0 && col < n && !visited[row][col] && grid[row][col] == 0) {
                    visited[row][col] = true; // Mark next cell as visited
                    queue.add(new Cell(row, col, cell.dist + 1)); // Enqueue next cell with incremented distance
                }
            }
        }
        return -1; // If end is not reachable, return -1
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[][] grid1 = {{0,1},{1,0}};
        System.out.println("Basic Test Case:");
        // Should return 2 as the shortest path length
        System.out.println("Expected: 2, Output: " + shortestPathBinaryMatrix(grid1));

        // Test Case 2: No path
        int[][] grid2 = {{0,1,1},{1,1,0},{1,1,0}};
        System.out.println("\nNo Path:");
        // Should return -1 since no path exists
        System.out.println("Expected: -1, Output: " + shortestPathBinaryMatrix(grid2));

        // Test Case 3: Larger grid
        int[][] grid3 = {{0,0,0},{1,1,0},{1,1,0}};
        System.out.println("\nLarger Grid:");
        // Should return 4 as the shortest path length
        System.out.println("Expected: 4, Output: " + shortestPathBinaryMatrix(grid3));

        // Test Case 4: Start or end blocked
        int[][] grid4 = {{1,0},{0,0}};
        System.out.println("\nStart Blocked:");
        // Should return -1 since start is blocked
        System.out.println("Expected: -1, Output: " + shortestPathBinaryMatrix(grid4));
        int[][] grid5 = {{0,0},{0,1}};
        System.out.println("End Blocked:");
        // Should return -1 since end is blocked
        System.out.println("Expected: -1, Output: " + shortestPathBinaryMatrix(grid5));
    }
}
