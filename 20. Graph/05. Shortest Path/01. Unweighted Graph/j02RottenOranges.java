/**
 * LeetCode 994. Rotting Oranges
 * 
 * Problem Statement:
 *     You are given an m x n grid where each cell can have one of three values:
 *         0 - Empty cell
 *         1 - Fresh orange
 *         2 - Rotten orange
 *     Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *     Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 * 
 * Input:
 *     - grid (1 <= m, n <= 10^2): 2D integer array representing the box of oranges
 * 
 * Output:
 *     - Integer: Minimum number of minutes until all oranges are rotten, or -1 if impossible
 * 
 * Example:
 *     Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
 *     Output: 4
 *     
 *     Explanation:
 *     Minute 0: [[2,1,1],[1,1,0],[0,1,1]]
 *     Minute 1: [[2,2,1],[2,1,0],[0,1,1]]
 *     Minute 2: [[2,2,2],[2,2,0],[0,1,1]]
 *     Minute 3: [[2,2,2],[2,2,0],[0,2,1]]
 *     Minute 4: [[2,2,2],[2,2,0],[0,2,2]]
 *     All oranges are rotten after 4 minutes.
 * 
 *     Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
 *     Output: -1
 *     
 *     Explanation:
 *     The orange in the bottom left corner (grid[2][0]) is never reached.
 */

import java.util.LinkedList;
import java.util.Queue;

public class j02RottenOranges {

    /**
     * Helper Class: Cell
     * 
     * Intuition:
     * - Represents a cell in the grid with its coordinates and the time at which it becomes rotten.
     * 
     * Explanation:
     * - Used to store the state in the BFS queue.
     * - Tracks the position and time for each rotten orange.
     */
    static class Cell {
        int i;
        int j;
        int time;

        Cell(int i, int j, int t) {
            this.i = i;
            this.j = j;
            this.time = t;
        }
    }

    /**
     * Approach: Multi-source BFS
     * 
     * Intuition:
     * - Use BFS to simulate the rotting process minute by minute.
     * - All initially rotten oranges are sources; spread rot to adjacent fresh oranges.
     * 
     * Explanation:
     * - Enqueue all rotten oranges with time 0.
     * - For each cell in the queue, rot its 4-directional neighbors if they are fresh.
     * - Track the time taken for each orange to rot.
     * - If any fresh orange remains, return -1.
     * 
     * Time Complexity: O(m*n) where m and n are grid dimensions (each cell visited at most once)
     * Space Complexity: O(m*n) for visited array and queue
     * 
     * @param grid    2D array representing the box of oranges (1 <= m, n <= 10^2)
     * @return       Minimum minutes to rot all oranges, or -1 if impossible
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return -1; // Handle null/empty grid
        int m = grid.length;
        int n = grid[0].length;
        int[] rowDir = new int[] { 1, 0, -1, 0 }; // Directions: down, right, up, left
        int[] colDir = new int[] { 0, 1, 0, -1 };
        boolean[][] visited = new boolean[m][n]; // Track visited cells
        Queue<Cell> queue = new LinkedList<>(); // BFS queue
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 2) {
                    visited[i][j] = true; // Mark rotten oranges as visited
                    queue.add(new Cell(i, j, 0)); // Enqueue all rotten oranges
                }
            }
        }
        int time = 0;
        while (!queue.isEmpty()) {
            Cell cell = queue.poll(); // Dequeue cell
            time = Math.max(time, cell.time); // Track max time
            for (int d = 0; d < 4; d++) {
                int row = cell.i + rowDir[d];
                int col = cell.j + colDir[d];
                // Check bounds and if neighbor is fresh and unvisited
                if (row >= 0 && row < m && col >= 0 && col < n && !visited[row][col] && grid[row][col] == 1) {
                    visited[row][col] = true; // Mark as visited
                    grid[row][col] = 2; // Rot the orange
                    queue.add(new Cell(row, col, cell.time + 1)); // Enqueue with incremented time
                }
            }
        }
        // Check for any remaining fresh oranges
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1)
                    return -1;
            }
        }
        return time;
    }

    public static void main(String[] args) {
        // Basic Test Cases
        System.out.println("\nBasic Test Cases:");
        int[][] grid1 = { {2,1,1}, {1,1,0}, {0,1,1} };
        System.out.println("Input: [[2,1,1],[1,1,0],[0,1,1]], Expected: 4, Output: " + orangesRotting(cloneGrid(grid1)));
        int[][] grid2 = { {2,1,1}, {0,1,1}, {1,0,1} };
        System.out.println("Input: [[2,1,1],[0,1,1],[1,0,1]], Expected: -1, Output: " + orangesRotting(cloneGrid(grid2)));

        // Edge Cases
        System.out.println("\nEdge Cases:");
        int[][] grid3 = { {0} };
        System.out.println("Input: [[0]], Expected: 0, Output: " + orangesRotting(cloneGrid(grid3)));
        int[][] grid4 = { {1} };
        System.out.println("Input: [[1]], Expected: -1, Output: " + orangesRotting(cloneGrid(grid4)));
        int[][] grid5 = { {2} };
        System.out.println("Input: [[2]], Expected: 0, Output: " + orangesRotting(cloneGrid(grid5)));
        System.out.println("Input: null, Expected: -1, Output: " + orangesRotting(null));

        // Boundary Cases
        System.out.println("\nBoundary Cases:");
        int[][] grid6 = new int[100][100];
        for (int i = 0; i < 100; i++) for (int j = 0; j < 100; j++) grid6[i][j] = 2;
        System.out.println("Input: 100x100 all rotten, Expected: 0, Output: " + orangesRotting(cloneGrid(grid6)));
        int[][] grid7 = new int[100][100];
        for (int i = 0; i < 100; i++) for (int j = 0; j < 100; j++) grid7[i][j] = 1;
        System.out.println("Input: 100x100 all fresh, Expected: -1, Output: " + orangesRotting(cloneGrid(grid7)));

        // Special Cases
        System.out.println("\nSpecial Cases:");
        int[][] grid8 = { {2,2,2}, {2,2,2}, {2,2,2} };
        System.out.println("Input: all rotten, Expected: 0, Output: " + orangesRotting(cloneGrid(grid8)));
        int[][] grid9 = { {0,0,0}, {0,0,0}, {0,0,0} };
        System.out.println("Input: all empty, Expected: 0, Output: " + orangesRotting(cloneGrid(grid9)));
    }

    /**
     * Helper Method: cloneGrid
     * 
     * Intuition:
     * - Clones a 2D array to avoid modifying the original grid in test cases.
     * 
     * Explanation:
     * - Deep copies the input grid for independent test runs.
     * 
     * Time Complexity: O(m*n)
     * Space Complexity: O(m*n)
     * 
     * @param grid    2D array to clone
     * @return       Deep copy of the input grid
     */
    private static int[][] cloneGrid(int[][] grid) {
        if (grid == null) return null;
        int[][] copy = new int[grid.length][];
        for (int i = 0; i < grid.length; i++) {
            copy[i] = grid[i].clone();
        }
        return copy;
    }
}
