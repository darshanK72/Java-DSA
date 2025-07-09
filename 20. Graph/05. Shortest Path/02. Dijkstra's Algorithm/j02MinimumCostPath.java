/**
 * GFG: Minimum Cost Path (Grid)
 *
 * Problem Statement:
 *     Given a square grid of size n x n, where each cell has a cost associated with it.
 *     Find a path from the top-left cell (0,0) to the bottom-right cell (n-1,n-1) such
 *     that the sum of the costs along the path is minimized. You can move in 4 directions
 *     (up, down, left, right) from a cell.
 *
 * Input:
 *     - grid: int[][], n x n grid (1 <= n <= 100, 0 <= grid[i][j] <= 1000)
 *
 * Output:
 *     - int: Minimum cost to reach (n-1, n-1) from (0, 0)
 *
 * Example:
 *     Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 *     Output: 7
 *
 *     Explanation:
 *         Path: 1 → 3 → 1 → 1 → 1 (down, right, right, down)
 *         Total cost = 1+3+1+1+1 = 7
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class j02MinimumCostPath {
    /**
     * Approach: Dijkstra's Algorithm on Grid
     *
     * Intuition:
     * - Treat each cell as a node in a graph, with edge weights as cell costs.
     * - Use Dijkstra's algorithm to always expand the cell with the minimum cost so far.
     * - The minimum cost to reach (n-1, n-1) is the answer.
     *
     * Explanation:
     * - Step 1: Initialize a distance array with infinity for all cells.
     * - Step 2: Use a min-heap (priority queue) to process cells by minimum cost.
     * - Step 3: For each cell, try moving in 4 directions. If a cheaper path is found,
     *           update the distance and add the cell to the queue.
     * - Step 4: When (n-1, n-1) is reached, its distance is the answer.
     *
     * Time Complexity: O(n^2 * log n), since each cell is processed at most once and heap operations are log n^2.
     * Space Complexity: O(n^2), for distance array and heap.
     *
     * @param grid   n x n grid of costs (1 <= n <= 100, 0 <= grid[i][j] <= 1000)
     * @return       Minimum cost to reach (n-1, n-1) from (0, 0)
     */
    public static int minimumCostPath(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0; // Edge case: empty grid
        int n = grid.length;
        int[] rowDir = new int[] { 1, 0, -1, 0 }; // Down, Right, Up, Left
        int[] colDir = new int[] { 0, 1, 0, -1 };
        int[][] dist = new int[n][n];
        for (int[] arr : dist)
            Arrays.fill(arr, Integer.MAX_VALUE); // Initialize all distances to infinity
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[] { 0, 0, grid[0][0] }); // Start from (0,0) with its cost
        dist[0][0] = grid[0][0];

        while (!pq.isEmpty()) {
            int[] cell = pq.poll(); // cell[0]=row, cell[1]=col, cell[2]=cost so far
            // If we've reached the destination, return the cost
            if (cell[0] == n - 1 && cell[1] == n - 1)
                return cell[2];
            for (int d = 0; d < 4; d++) {
                int row = cell[0] + rowDir[d];
                int col = cell[1] + colDir[d];
                // Check bounds and if a cheaper path is found
                if (row >= 0 && row < n && col >= 0 && col < n && dist[row][col] > (cell[2] + grid[row][col])) {
                    dist[row][col] = cell[2] + grid[row][col]; // Update cost
                    pq.add(new int[] { row, col, dist[row][col] }); // Add to queue
                }
            }
        }
        // If unreachable (shouldn't happen in a connected grid)
        return dist[n - 1][n - 1];
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] g1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println("Input: [[1,3,1],[1,5,1],[4,2,1]], Expected: 7, Output: " + minimumCostPath(g1));
        int[][] g2 = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println("Input: [[1,2,3],[4,5,6],[7,8,9]], Expected: 21, Output: " + minimumCostPath(g2));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] g3 = {};
        System.out.println("Input: [], Expected: 0, Output: " + minimumCostPath(g3));
        int[][] g4 = null;
        System.out.println("Input: null, Expected: 0, Output: " + minimumCostPath(g4));
        int[][] g5 = {{5}};
        System.out.println("Input: [[5]], Expected: 5, Output: " + minimumCostPath(g5));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] g6 = new int[100][100];
        for (int i = 0; i < 100; i++) Arrays.fill(g6[i], 1);
        System.out.println("Input: 100x100 grid of 1s, Expected: 199, Output: " + minimumCostPath(g6));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] g7 = {{1,1000,1000},{1,1,1000},{1000,1,1}};
        System.out.println("Input: [[1,1000,1000],[1,1,1000],[1000,1,1]], Expected: 5, Output: " + minimumCostPath(g7));
    }
}