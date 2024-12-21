/**
 * Problem Statement:
 * 
 *     A valid Sudoku board is a 9x9 grid filled with digits from 1 to 9. The rules of Sudoku are as follows:
 *     - Each row must contain every number from 1 to 9 exactly once.
 *     - Each column must contain every number from 1 to 9 exactly once.
 *     - Each of the nine 3x3 subgrids must also contain every number from 1 to 9 exactly once.
 *     
 *     Given a partially filled 9x9 Sudoku board, determine if it is valid according to the above rules.
 * 
 * Input:
 *     - A 9x9 grid representing the Sudoku board. The grid contains integers between 0 and 9, where 0 represents an empty cell.
 * 
 * Output:
 *     - Return `true` if the Sudoku board is valid according to the given rules, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     5 3 0 0 7 0 0 0 0
 *     6 0 0 1 9 5 0 0 0
 *     0 9 8 0 0 0 0 6 0
 *     8 0 0 0 6 0 0 0 3
 *     4 0 0 8 0 3 0 0 1
 *     7 0 0 0 2 0 0 0 6
 *     0 6 0 0 0 0 2 8 0
 *     0 0 0 4 1 9 0 0 5
 *     0 0 0 0 8 0 0 7 9
 * 
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - The board follows all the Sudoku rules: Each row, column, and 3x3 subgrid contains the numbers 1 to 9 exactly once.
 */

import java.util.Scanner;

public class j09ValidSudoku {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int[][] board = new int[9][9];

        // Input the Sudoku board values
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                board[i][j] = in.nextInt();
            }
        }

        // Call the function to check if the Sudoku board is valid
        System.out.println(isValidSudoku(board));

        in.close();
    }

    /**
     * Approach: Brute Force Validation
     * 
     * Intuition:
     * - We need to check the validity of the Sudoku board by validating each row, column, and 3x3 subgrid.
     * - For each row and column, we create a map (array of size 9) to track the frequency of numbers from 1 to 9.
     *   - If any number appears more than once, the board is invalid for that row or column.
     * - Similarly, for each 3x3 subgrid, we validate the frequency of numbers in that subgrid.
     * - If any row, column, or subgrid fails the validation, we return `false`. Otherwise, we return `true`.
     * 
     * Time Complexity:
     * - O(N^2), where N is 9 (the size of the board). We are iterating over each cell in the board multiple times: 
     *   once for the rows, once for the columns, and once for the subgrids.
     * 
     * Space Complexity:
     * - O(N), where N is 9. We use a map (array) of size 9 for each row, column, and subgrid to check the frequency 
     *   of each number.
     * 
     * @param board The 9x9 Sudoku board represented as a 2D array.
     * @return `true` if the board is valid, otherwise `false`.
     */
    public static boolean isValidSudoku(int[][] board) {
        // Check rows and columns
        for (int i = 0; i < 9; i++) {
            int[] map = new int[9];

            // Validate row
            for (int j = 0; j < 9; j++) {
                int n = board[i][j];
                if (n >= 1 && n <= 9) {
                    map[n - 1]++;
                }
            }
            if (!isValid(map))
                return false;

            // Reset map for column validation
            map = new int[9];
            for (int j = 0; j < 9; j++) {
                int n = board[j][i];
                if (n >= 1 && n <= 9) {
                    map[n - 1]++;
                }
            }
            if (!isValid(map))
                return false;
        }

        // Check 3x3 subgrids
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                int[] map = new int[9];

                // Validate 3x3 subgrid
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        int n = board[r + i][c + j];
                        if (n >= 1 && n <= 9) {
                            map[n - 1]++;
                        }
                    }
                }
                if (!isValid(map))
                    return false;
            }
        }

        // If all checks passed, return true
        return true;
    }

    /**
     * Helper Method: Check if the map is valid (no duplicates in row, column, or subgrid)
     * 
     * Intuition:
     * - The map array represents the frequency of numbers from 1 to 9.
     * - If any number appears more than once in the map, the Sudoku is invalid for that row, column, or subgrid.
     * - We check if any element in the map is greater than 1; if so, the Sudoku board is invalid for that part.
     * 
     * Time Complexity:
     * - O(N), where N is 9. We are iterating over the map array of size 9.
     * 
     * Space Complexity:
     * - O(1), since the map array is of fixed size (9).
     * 
     * @param map The frequency map representing numbers from 1 to 9.
     * @return `true` if no number appears more than once, otherwise `false`.
     */
    public static boolean isValid(int[] map) {
        for (int i = 0; i < 9; i++) {
            if (map[i] > 1)
                return false;
        }
        return true;
    }
}
