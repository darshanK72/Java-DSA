/**
 * LeetCode 909. Snakes and Ladders
 *
 * Problem Statement:
 *     You are given an n x n integer matrix 'board' where the cells are numbered from 1 to n*n in a Boustrophedon style (left to right, then right to left for the next row, and so on from bottom to top). Each cell contains -1 (no snake/ladder) or a destination cell number (snake/ladder). Starting from cell 1, return the minimum number of moves to reach cell n*n. If not possible, return -1.
 *
 * Input:
 *     - board (int[][]): n x n matrix, board[i][j] is -1 or destination cell (1 <= n <= 20)
 *
 * Output:
 *     - int: Minimum number of moves to reach cell n*n, or -1 if impossible
 *
 * Example:
 *     Input: board = [
 *         [-1,-1,-1,-1,-1,-1],
 *         [-1,-1,-1,-1,-1,-1],
 *         [-1,-1,-1,-1,-1,-1],
 *         [-1,35,-1,-1,13,-1],
 *         [-1,-1,-1,-1,-1,-1],
 *         [-1,15,-1,-1,-1,-1]
 *     ]
 *     Output: 4
 *
 *     Explanation:
 *     - Start at 1. Roll 2 → 3, 3 → 15 (ladder), 15 → 13 (snake), 13 → 35 (ladder), 35 → 36.
 *     - Minimum moves: 4
 */

import java.util.*;

public class j05SnakeAndLadder {
    /**
     * Approach: Breadth-First Search (BFS)
     *
     * Intuition:
     * - Each cell is a node; dice rolls are edges to next 1-6 cells.
     * - Use BFS to find the shortest path from cell 1 to n*n.
     * - Use a visited array to avoid revisiting cells.
     * - For each move, if a cell has a snake/ladder, jump to its destination.
     *
     * Explanation:
     * - Step 1: Start BFS from cell 1, mark as visited.
     * - Step 2: For each move, try all dice rolls (1-6).
     * - Step 3: If destination cell is unvisited, add to queue.
     * - Step 4: If we reach n*n, return the number of moves.
     * - Step 5: If queue is empty and n*n not reached, return -1.
     *
     * Time Complexity: O(n^2) (each cell visited at most once)
     * Space Complexity: O(n^2) for visited array and queue
     *
     * @param board n x n board with snakes and ladders
     * @return Minimum moves to reach n*n, or -1 if impossible
     */
    public static int snakesAndLadders(int[][] board) {
        int n = board.length;
        boolean[][] visited = new boolean[n][n]; // Track visited cells
        Queue<Integer> queue = new LinkedList<>(); // Queue for BFS, stores cell numbers
        visited[n - 1][0] = true; // Start at cell 1 (bottom-left), mark as visited
        queue.add(1); // Enqueue starting cell number
        int steps = 0; // Number of moves taken
        while (!queue.isEmpty()) {
            int size = queue.size(); // Number of cells to process at current BFS level
            for (int i = 0; i < size; i++) {
                int cell = queue.poll(); // Get current cell number
                if (cell == n * n)
                    return steps; // If reached last cell, return number of moves
                // Try all possible dice rolls (1 to 6)
                for (int d = 1; d <= 6; d++) {
                    int newCell = cell + d; // Next cell number after dice roll
                    if (newCell > n * n)
                        break; // Skip if cell number exceeds board
                    int[] rowCol = getRowCol(newCell, n); // Convert cell number to (row, col)
                    int row = rowCol[0];
                    int col = rowCol[1];
                    if (visited[row][col])
                        continue; // Skip if already visited
                    visited[row][col] = true; // Mark cell as visited
                    if (board[row][col] != -1) {
                        // If cell has a snake or ladder, jump to its destination
                        queue.add(board[row][col]);
                    } else {
                        // Otherwise, move to the new cell
                        queue.add(newCell);
                    }
                }
            }
            steps++; // Increment move count after processing current level
        }
        return -1; // If end is not reachable, return -1
    }

    /**
     * Helper Method: Convert cell number to (row, col) on board
     *
     * Intuition:
     * - Board is numbered in Boustrophedon style from bottom-left.
     * - Need to map cell number to correct (row, col) indices.
     *
     * Explanation:
     * - Calculate row from bottom, then determine if row is reversed.
     *
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @param cell Cell number (1-indexed)
     * @param n    Board size
     * @return     int[]{row, col} indices
     */
    public static int[] getRowCol(int cell, int n) {
        int rowFromTop = (cell - 1) / n; // Row index from the top
        int rowFromBottom = (n - 1) - rowFromTop; // Row index from the bottom
        int col = (cell - 1) % n; // Column index (left to right)
        if (n % 2 == rowFromBottom % 2) {
            col = (n - 1) - col; // Reverse column if row is reversed
        }
        return new int[] { rowFromBottom, col }; // Return (row, col) indices
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        int[][] board1 = {
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,35,-1,-1,13,-1},
            {-1,-1,-1,-1,-1,-1},
            {-1,15,-1,-1,-1,-1}
        };
        System.out.println("Basic Test Case:");
        // Should return 4 as the minimum number of moves
        System.out.println("Expected: 4, Output: " + snakesAndLadders(board1));

        // Test Case 2: Edge case (no snakes/ladders)
        int[][] board2 = {
            {-1,-1,-1},
            {-1,-1,-1},
            {-1,-1,-1}
        };
        System.out.println("\nEdge Case (no snakes/ladders):");
        // Should return 2 as the minimum number of moves
        System.out.println("Expected: 2, Output: " + snakesAndLadders(board2));

        // Test Case 3: Impossible case
        int[][] board3 = {
            {-1,-1,-1},
            {-1,-1,-1},
            {-1,-1,2}
        };
        System.out.println("\nImpossible Case:");
        // Should return -1 since the end is not reachable
        System.out.println("Expected: -1, Output: " + snakesAndLadders(board3));

        // Test Case 4: Special case (ladder to end)
        int[][] board4 = {
            {-1,-1,-1},
            {-1,-1,9},
            {-1,-1,-1}
        };
        System.out.println("\nSpecial Case (ladder to end):");
        // Should return 1 as the ladder leads directly to the end
        System.out.println("Expected: 1, Output: " + snakesAndLadders(board4));
    }
}
