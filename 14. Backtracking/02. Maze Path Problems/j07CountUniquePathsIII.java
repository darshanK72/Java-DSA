/**
 * Problem Statement:
 * 
 *     You are given a 2D grid of size m x n, where:
 *     - 1 represents the starting point (exactly one start).
 *     - 2 represents the ending point (exactly one end).
 *     - 0 represents empty cells.
 *     - -1 represents blocked cells.
 * 
 *     Your task is to find all unique paths from the starting point to the ending point, 
 *     visiting every empty cell (cells with 0) exactly once. You can only move up, down, left, or right.
 * 
 * Input:
 *     - A 2D grid `grid` of integers representing the grid.
 *     - The grid will have exactly one start and one end, with several cells being empty (0) or blocked (-1).
 * 
 * Output:
 *     - The number of unique paths that meet the requirement of visiting all empty cells exactly once.
 * 
 * Example:
 *     Input:
 *     [[1, 0, 0, 0],
 *      [0, -1, 0, 0],
 *      [0, 0, 0, 2]]
 *     Output:
 *     2
 * 
 *     Explanation:
 *     The two unique paths are:
 *     1. (0,0) -> (1,0) -> (2,0) -> (2,1) -> (2,2) -> (1,2) -> (1,3) -> (0,3)
 *     2. (0,0) -> (1,0) -> (1,1) -> (1,2) -> (2,2) -> (2,1) -> (2,0) -> (0,3)
 */

public class j07CountUniquePathsIII {

    public static void main(String[] args) {
        // Test case 1
        int[][] grid1 = {
                { 1, 0, 0, 0 },
                { 0, -1, 0, 0 },
                { 0, 0, 0, 2 }
        };
        System.out.println("Test Case 1: " + uniquePathsIII(grid1)); // Expected output: 2

        // Test case 2
        int[][] grid2 = {
                { 1, 0, 0, 0 },
                { 0, 0, 0, 0 },
                { 0, 0, 0, 2 }
        };
        System.out.println("Test Case 2: " + uniquePathsIII(grid2)); // Expected output: 4

        // Test case 3
        int[][] grid3 = {
                { 1, 0, 0 },
                { 0, -1, 0 },
                { 0, 0, 2 }
        };
        System.out.println("Test Case 3: " + uniquePathsIII(grid3)); // Expected output: 1
    }

    /**
     * Approach: Depth-First Search with Backtracking
     * 
     * Intuition:
     * - We are given a 2D grid where:
     *   - `1` marks the starting point.
     *   - `2` marks the destination.
     *   - `0` marks the empty cells, and `-1` marks the blocked cells.
     * 
     * - Our goal is to start at the `1` (starting point) and find all unique paths to `2` (ending point) 
     *   while ensuring that every empty cell (0) is visited exactly once.
     * - The task can be solved using Depth-First Search (DFS) combined with backtracking. The idea is to 
     *   explore all four directions (down, up, right, left) from the current cell, while keeping track of 
     *   the visited cells.
     * - A recursive DFS function will explore each valid path from the start, updating the count of free 
     *   cells (0 cells) visited during traversal.
     * - Once we reach the destination cell (`2`), we check if all empty cells have been visited. If so,
     *   we count that path as valid.
     * - As we explore paths, we use a `visited` array to ensure we do not visit the same cell twice on 
     *   any given path. This prevents cycles and ensures that the DFS is performing correct backtracking.
     * - The recursive calls will consider all possible paths by exploring each direction (up, down, left, 
     *   right), and backtrack once a path is fully explored or invalidated.
     * 
     * Time Complexity:
     * - O(4^N), where N is the number of cells in the grid, since at each step, we can move in four 
     *   directions and explore the entire grid recursively.
     * 
     * Space Complexity:
     * - O(m * n), where `m` is the number of rows and `n` is the number of columns in the grid. This 
     *   space is used by the `visited` array which keeps track of the cells we have visited during the DFS.
     * 
     * @param grid The input 2D grid representing the environment.
     * @return The total number of valid unique paths from start to destination.
     */

    public static int uniquePathsIII(int[][] grid) {
        int startRow = -1;
        int startCol = -1;
        int endRow = -1;
        int endCol = -1;
        int freeCells = 1; // The start cell is already counted as a free cell

        // Iterate through the grid to locate the start and end points and count free
        // cells
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) {
                    freeCells++; // Count empty cells
                } else if (grid[i][j] == 1) {
                    startRow = i;
                    startCol = j; // Found the start point
                } else if (grid[i][j] == 2) {
                    endRow = i;
                    endCol = j; // Found the end point
                }
            }
        }

        // Start counting paths from the start point
        return countPaths(grid, startRow, startCol, endRow, endCol, freeCells,
                new boolean[grid.length][grid[0].length]);
    }

    /**
     * countPaths: A helper function that uses DFS with backtracking to explore all paths.
     * 
     * Intuition:
     * - We start from the `startRow` and `startCol`, and explore the grid recursively.
     * - For each cell, we check the four possible directions (down, right, up, left).
     * - At each step, we check if the cell is within bounds, not blocked (-1), and not visited before.
     * - Once we reach the destination (`dr`, `dc`), we check if all empty cells have been visited.
     * - If all empty cells are visited, the path is valid.
     * - We backtrack by unmarking the current cell as visited once all four directions are explored.
     * 
     * Time Complexity:
     * - O(4^N), where N is the number of cells in the grid (due to recursive DFS exploring four directions at each step).
     * 
     * Space Complexity:
     * - O(m * n), due to the `visited` array of size `m * n`, where `m` is the number of rows and `n` is the number of columns.
     * 
     * @param grid The grid representing the environment.
     * @param sr The current row position.
     * @param sc The current column position.
     * @param dr The destination row.
     * @param dc The destination column.
     * @param freeCells The total number of empty cells (0).
     * @param visited The boolean array to track visited cells during DFS.
     * @return The count of valid paths from the current position to the destination.
     */
    public static int countPaths(int[][] grid, int sr, int sc, int dr, int dc, int freeCells, boolean[][] visited) {
        // Base case: out of bounds, blocked cell, or already visited
        if (sr < 0 || sc < 0 || sr >= grid.length || sc >= grid[0].length || grid[sr][sc] == -1 || visited[sr][sc])
            return 0;

        // If we've reached the destination and visited all free cells, return 1 (valid
        // path)
        if (sr == dr && sc == dc) {
            if (freeCells == 0)
                return 1; // Valid path, all empty cells visited
            return 0; // Invalid path, not all empty cells visited
        }

        // Mark the current cell as visited
        visited[sr][sc] = true;

        // Recursively check all four directions: down, right, up, left
        int count = 0;
        count += countPaths(grid, sr + 1, sc, dr, dc, freeCells - 1, visited); // Down
        count += countPaths(grid, sr, sc + 1, dr, dc, freeCells - 1, visited); // Right
        count += countPaths(grid, sr - 1, sc, dr, dc, freeCells - 1, visited); // Up
        count += countPaths(grid, sr, sc - 1, dr, dc, freeCells - 1, visited); // Left

        // Backtrack: unmark the current cell as visited
        visited[sr][sc] = false;

        return count;
    }
}
