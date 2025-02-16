/*-
 * Problem Statement:
 * 
 *     Write a program to solve a Sudoku puzzle by filling the empty cells.
 *     A Sudoku solution must satisfy all of the following rules:
 *     1. Each of the digits 1-9 must occur exactly once in each row.
 *     2. Each of the digits 1-9 must occur exactly once in each column.
 *     3. Each of the digits 1-9 must occur exactly once in each of the nine 3x3 sub-boxes of the grid.
 *     The '.' character indicates empty cells.
 * 
 * Input:
 *     - A 9x9 2D character array `board` representing the Sudoku puzzle, where empty cells are denoted by '.'.
 * 
 * Output:
 *     - The function modifies the input `board` in-place to represent the solved Sudoku puzzle.
 * 
 * Example:
 *     Input:
 *     [['5','3','.','.','7','.','.','.','.'],
 *      ['6','.','.','1','9','5','.','.','.'],
 *      ['.','9','8','.','.','.','.','6','.'],
 *      ['8','.','.','.','6','.','.','.','3'],
 *      ['4','.','.','8','.','3','.','.','1'],
 *      ['7','.','.','.','2','.','.','.','6'],
 *      ['.','6','.','.','.','.','2','8','.'],
 *      ['.','.','.','4','1','9','.','.','5'],
 *      ['.','.','.','.','8','.','.','7','9']]
 * 
 *     Output:
 *     [['5','3','4','6','7','8','9','1','2'],
 *      ['6','7','2','1','9','5','3','4','8'],
 *      ['1','9','8','3','4','2','5','6','7'],
 *      ['8','5','9','7','6','1','4','2','3'],
 *      ['4','2','6','8','5','3','7','9','1'],
 *      ['7','1','3','9','2','4','8','5','6'],
 *      ['9','6','1','5','3','7','2','8','4'],
 *      ['2','8','7','4','1','9','6','3','5'],
 *      ['3','4','5','2','8','6','1','7','9']]
 * 
 *     Explanation:
 *     The input board is a partially filled Sudoku puzzle. The output board is the same puzzle where all empty cells have been filled to satisfy the Sudoku rules.
 */

import java.util.Arrays;

public class j01SudokuSolver {

    public static void main(String args[]) {
        char[][] ans = new char[9][9];

        char[][] board = {
                { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' },
                { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' },
                { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' },
                { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' },
                { '.', '.', '.', '.', '8', '.', '.', '7', '9' }
        };

        fillSudoku1(board, ans, 0, 0);

        System.out.println(Arrays.deepToString(ans));

        fillSudoku2(board, 0, 0);

        System.out.println(Arrays.deepToString(board));
    }

    /*-
     * Approach 1: Backtracking with Separate Result Storage
     * 
     * Intuition:
     * - This approach attempts to solve the Sudoku puzzle by trying to place numbers from '1' to '9' in empty cells.
     * - It recursively moves to the next cell after placing a valid number.
     * - If a solution is found (i.e., all cells are filled), the board is copied into the `ans` array.
     * - If a placement leads to an invalid board, the algorithm backtracks and tries the next number.
     * 
     * Explanation:
     * - If we reach the end of the board (row == 9), we copy the solution into `ans` and return.
     * - If the current cell is already filled, move to the next cell.
     * - Try placing numbers '1' to '9', ensuring they follow Sudoku rules using `canPlaceNumber`.
     * - If a number is valid, place it, move to the next cell, and then backtrack if needed.
     * - Reset the cell if no valid number is found.
     * 
     * Time Complexity:
     * - O(9^(n*n)): Each empty cell has up to 9 possible choices in the worst case.
     * 
     * Space Complexity:
     * - O(n*n): Due to recursion and the additional `ans` storage.
     * 
     * @param board The input Sudoku board.
     * @param ans   The result board where the solution is stored.
     * @param row   Current row index.
     * @param col   Current column index.
     */
    public static void fillSudoku1(char[][] board, char[][] ans, int row, int col) {
        if (row == 9) {
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    ans[i][j] = board[i][j];
                }
            }
            return;
        }

        if (board[row][col] != '.') {
            fillSudoku1(board, ans, col < 8 ? row : row + 1, col < 8 ? col + 1 : 0);
            return;
        }

        for (char c = '1'; c <= '9'; c++) {
            if (canPlaceNumber(board, row, col, c)) {
                board[row][col] = c;
                fillSudoku1(board, ans, col < 8 ? row : row + 1, col < 8 ? col + 1 : 0);
                board[row][col] = '.';
            }
        }
    }

    /*-
     * Approach 2: Backtracking with Early Termination
     * 
     * Intuition:
     * - This approach is similar to the first, but it stops as soon as a valid solution is found.
     * - Instead of storing a separate result, it modifies the `board` directly.
     * - Once a solution is found, it returns `true` to prevent unnecessary computations.
     * 
     * Explanation:
     * - If we reach the last cell (row == 9), return `true` (Sudoku is solved).
     * - If the cell is already filled, move to the next one.
     * - Try placing numbers '1' to '9', ensuring they follow Sudoku rules.
     * - If a valid number is found, recursively solve the next cell.
     * - If a recursive call returns `true`, stop further processing.
     * - Reset the cell if no valid number is found.
     * 
     * Time Complexity:
     * - O(9^(n*n)) in the worst case.
     * 
     * Space Complexity:
     * - O(n*n) due to recursion.
     * 
     * @param board The input Sudoku board.
     * @param row   Current row index.
     * @param col   Current column index.
     * @return `true` if a valid Sudoku solution is found, otherwise `false`.
     */
    public static boolean fillSudoku2(char[][] board, int row, int col) {
        if (row == 9) {
            return true;
        }

        if (board[row][col] != '.') {
            return fillSudoku2(board, col < 8 ? row : row + 1, col < 8 ? col + 1 : 0);
        }

        for (char c = '1'; c <= '9'; c++) {
            if (canPlaceNumber(board, row, col, c)) {
                board[row][col] = c;
                boolean ans = fillSudoku2(board, col < 8 ? row : row + 1, col < 8 ? col + 1 : 0);
                if (ans == true)
                    return true;
                board[row][col] = '.';
            }
        }
        return false;
    }

    /*-
     * Helper Method: Check if a Number Can Be Placed
     * 
     * Intuition:
     * - This method checks if a given number can be placed at `board[row][col]` while following Sudoku rules.
     * - The number must not already exist in the same row, column, or 3x3 sub-grid.
     * 
     * Explanation:
     * - Iterate through the row and column to check if the number exists.
     * - Identify the top-left corner of the 3x3 sub-grid and check if the number exists within it.
     * - If the number is found in any of these, return `false`; otherwise, return `true`.
     * 
     * Time Complexity:
     * - O(9): Checking row, column, and sub-grid takes constant time.
     * 
     * Space Complexity:
     * - O(1): Uses no extra space.
     * 
     * @param board The input Sudoku board.
     * @param row   Current row index.
     * @param col   Current column index.
     * @param ch    The number to be placed.
     * @return `true` if the number can be placed, otherwise `false`.
     */
    public static boolean canPlaceNumber(char[][] board, int row, int col, char ch) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == ch || board[i][col] == ch)
                return false;
        }

        int r = (row / 3) * 3;
        int c = (col / 3) * 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + r][j + c] == ch)
                    return false;
            }
        }

        return true;
    }
}
