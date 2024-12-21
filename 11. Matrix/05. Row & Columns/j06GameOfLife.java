/**
 * Problem Statement:
 *
 *     The "Game of Life" is a cellular automaton devised by the British mathematician John Horton Conway in 1970. 
 *     It consists of a grid of cells, where each cell can either be alive or dead. The cells evolve in discrete steps 
 *     according to the following rules:
 *
 *     1. Any live cell with fewer than two live neighbors dies (underpopulation).
 *     2. Any live cell with two or three live neighbors survives (population equilibrium).
 *     3. Any live cell with more than three live neighbors dies (overpopulation).
 *     4. Any dead cell with exactly three live neighbors becomes alive (reproduction).
 *
 *     Given a board representing the game of life, you need to implement an algorithm to compute the next state of 
 *     the board. The board consists of an `m x n` grid of cells where each cell is either 0 (dead) or 1 (alive). 
 *     Implement the solution in-place without using extra space, modifying the board in the process.
 *
 * Input:
 *     - An integer `row` (1 <= row <= 25) representing the number of rows in the board.
 *     - An integer `col` (1 <= col <= 25) representing the number of columns in the board.
 *     - A 2D array `matrix` of size `row x col`, where each element is either 0 (dead) or 1 (alive).
 * 
 * Output:
 *     - The modified board after applying the Game of Life rules.
 * 
 * Example:
 *     Input:
 *     4 4
 *     0 1 0 0
 *     1 1 1 0
 *     1 1 0 0
 *     0 0 0 0
 *
 *     Output:
 *     0 0 0 0
 *     1 1 1 0
 *     1 1 1 0
 *     0 1 0 0
 *
 *     Explanation:
 *     The board after applying the rules of the Game of Life for one step.
 */

import java.util.Arrays;
import java.util.Scanner;

public class j06GameOfLife {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int row = in.nextInt(); // Input: number of rows in the board
        int col = in.nextInt(); // Input: number of columns in the board
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = in.nextInt(); // Input: elements of the board
            }
        }
        gameOfLife(matrix); // Call the function to modify the board
        System.out.println(Arrays.toString(matrix)); // Print the modified board
        in.close();
    }

    /**
     * Approach 1: Basic Solution with a Temporary Matrix
     *
     * Intuition:
     * - We create a temporary matrix to store the current state of the board and then apply the Game of Life rules to update 
     *   the original matrix.
     * - This approach works by iterating through each cell of the board and checking its neighbors to determine if the cell 
     *   should live or die based on the rules of the game.
     *
     * Time Complexity:
     * - O(row * col), where `row` is the number of rows and `col` is the number of columns in the board. This is because 
     *   we iterate through each cell once.
     *
     * Space Complexity:
     * - O(row * col), as we are using a temporary matrix to store the current state of the board.
     *
     * @param board The input board of cells.
     */
    public static void gameOfLife(int[][] board) {
        int[][] mat = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                mat[i][j] = board[i][j]; // Copy the current state of the board into the temporary matrix
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (liveOrDie(mat, i, j)) {
                    board[i][j] = 1; // Cell lives
                } else {
                    board[i][j] = 0; // Cell dies
                }
            }
        }
    }

    /**
     * Helper Method: liveOrDie
     *
     * Intuition:
     * - This method checks the number of live neighbors of a given cell and determines whether the cell will live or die 
     *   based on the rules of the Game of Life.
     * 
     * Time Complexity:
     * - O(1), as it checks a fixed number of neighbors (8 neighbors maximum).
     *
     * Space Complexity:
     * - O(1), since no additional data structures are used.
     *
     * @param board The current state of the board.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return true if the cell should stay alive or become alive, false otherwise.
     */
    public static boolean liveOrDie(int[][] board, int row, int col) {
        int lifesCount = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && board[row - 1][col - 1] == 1)
            lifesCount++;
        if (row - 1 >= 0 && board[row - 1][col] == 1)
            lifesCount++;
        if (row - 1 >= 0 && col + 1 < board[0].length && board[row - 1][col + 1] == 1)
            lifesCount++;
        if (col + 1 < board[0].length && board[row][col + 1] == 1)
            lifesCount++;
        if (row + 1 < board.length && col + 1 < board[0].length && board[row + 1][col + 1] == 1)
            lifesCount++;
        if (row + 1 < board.length && board[row + 1][col] == 1)
            lifesCount++;
        if (row + 1 < board.length && col - 1 >= 0 && board[row + 1][col - 1] == 1)
            lifesCount++;
        if (col - 1 >= 0 && board[row][col - 1] == 1)
            lifesCount++;

        if (board[row][col] == 1) {
            if (lifesCount < 2 || lifesCount > 3)
                return false; // Cell dies due to underpopulation or overpopulation
        } else {
            if (lifesCount != 3)
                return false; // Dead cell becomes alive only if exactly 3 neighbors are alive
        }
        return true;
    }

    /**
     * Approach 2: Optimized Solution Using In-Place Modification
     *
     * Intuition:
     * - Instead of using a temporary matrix, we can optimize the space usage by modifying the board in place. 
     * - We mark cells that will become alive as 2, and cells that will become dead as -1. This way, we can distinguish 
     *   between the old state and the new state in the same board.
     * - After all cells are updated, we convert the transitional states (2 and -1) to their final values (1 and 0).
     *
     * Time Complexity:
     * - O(row * col), where `row` is the number of rows and `col` is the number of columns in the board. We make two 
     *   passes over the board: one for marking changes and one for final updates.
     *
     * Space Complexity:
     * - O(1), since the board is modified in place without using extra space.
     *
     * @param board The input board of cells.
     */
    public static void gameOfLifeInplace(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (liveOrDie(board, i, j)) {
                    if (board[i][j] == 0) {
                        board[i][j] = 2; // Mark dead cell to become live
                    }
                } else {
                    if (board[i][j] == 1) {
                        board[i][j] = -1; // Mark live cell to become dead
                    }
                }
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0; // Update transitional state to dead
                } else if (board[i][j] == 2) {
                    board[i][j] = 1; // Update transitional state to live
                }
            }
        }
    }

    /**
     * Helper Method: liveOrDieInplace
     *
     * Intuition:
     * - Similar to the `liveOrDie` method, but this version works for the in-place solution where cells are marked 
     *   temporarily with -1 and 2 to indicate transitions.
     *
     * Time Complexity:
     * - O(1), as it checks a fixed number of neighbors (8 neighbors maximum).
     *
     * Space Complexity:
     * - O(1), as no additional space is used for this helper method.
     *
     * @param board The current state of the board.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return true if the cell should stay alive or become alive, false otherwise.
     */
    public static boolean liveOrDieInplace(int[][] board, int row, int col) {
        int lifesCount = 0;
        if (row - 1 >= 0 && col - 1 >= 0 && (board[row - 1][col - 1] == 1 || board[row - 1][col - 1] == -1))
            lifesCount++;
        if (row - 1 >= 0 && (board[row - 1][col] == 1 || board[row - 1][col] == -1))
            lifesCount++;
        if (row - 1 >= 0 && col + 1 < board[0].length
                && (board[row - 1][col + 1] == 1 || board[row - 1][col + 1] == -1))
            lifesCount++;
        if (col + 1 < board[0].length && (board[row][col + 1] == 1 || board[row][col + 1] == -1))
            lifesCount++;
        if (row + 1 < board.length && col + 1 < board[0].length
                && (board[row + 1][col + 1] == 1 || board[row + 1][col + 1] == -1))
            lifesCount++;
        if (row + 1 < board.length && (board[row + 1][col] == 1 || board[row + 1][col] == -1))
            lifesCount++;
        if (row + 1 < board.length && col - 1 >= 0 && (board[row + 1][col - 1] == 1 || board[row + 1][col - 1] == -1))
            lifesCount++;
        if (col - 1 >= 0 && (board[row][col - 1] == 1 || board[row][col - 1] == -1))
            lifesCount++;

        if (board[row][col] == 1) {
            return !(lifesCount < 2 || lifesCount > 3); // Cell stays alive if 2 or 3 neighbors are live
        } else {
            return lifesCount == 3; // Dead cell becomes alive if exactly 3 neighbors are live
        }
    }

    /**
     * Approach 3: Optimized Solution with Neighbor Counting Function
     *
     * Intuition:
     * - This approach improves upon the in-place solution by using a helper function to count live neighbors, 
     *   centralizing the logic for neighbor counting.
     * - This reduces code duplication and improves clarity.
     *
     * Time Complexity:
     * - O(row * col), where `row` is the number of rows and `col` is the number of columns in the board.
     *
     * Space Complexity:
     * - O(1), as we do not use any additional space for counting the neighbors.
     *
     * @param board The input board of cells.
     */
    public static void gameOfLife2(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int lives = countLiveNeighbors(board, i, j);

                // If the cell is currently live (1)
                if (board[i][j] == 1) {
                    // It dies if it has fewer than 2 or more than 3 live neighbors
                    if (lives < 2 || lives > 3) {
                        board[i][j] = -1; // -1 signifies the cell was live, but now dead
                    }
                }
                // If the cell is currently dead (0)
                else {
                    // It becomes live if it has exactly 3 live neighbors
                    if (lives == 3) {
                        board[i][j] = 2; // 2 signifies the cell was dead, but now live
                    }
                }
            }
        }

        // Final pass to update the board to the new state
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 0; // dead cell
                } else if (board[i][j] == 2) {
                    board[i][j] = 1; // live cell
                }
            }
        }
    }

    /**
     * Helper Method: countLiveNeighbors
     *
     * Intuition:
     * - This helper function counts the number of live neighbors for a given cell by checking all 8 surrounding cells. 
     *   This reduces redundancy and improves the clarity of the solution.
     *
     * Time Complexity:
     * - O(1), as it checks a fixed number of neighbors (8 neighbors maximum).
     *
     * Space Complexity:
     * - O(1), since no additional data structures are used.
     *
     * @param board The current state of the board.
     * @param row The row index of the cell.
     * @param col The column index of the cell.
     * @return The number of live neighbors for the given cell.
     */
    private static int countLiveNeighbors(int[][] board, int row, int col) {
        int lives = 0;
        int[] directions = { -1, 0, 1 };

        for (int i : directions) {
            for (int j : directions) {
                if (i == 0 && j == 0)
                    continue; // Skip the cell itself

                int r = row + i;
                int c = col + j;

                if (r >= 0 && r < board.length && c >= 0 && c < board[0].length) {
                    // Check the current or previous state of the cell
                    if (board[r][c] == 1 || board[r][c] == -1) {
                        lives++;
                    }
                }
            }
        }
        return lives;
    }
}
