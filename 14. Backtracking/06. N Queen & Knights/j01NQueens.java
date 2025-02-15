/*-
 * Problem Statement:
 * 
 *      The N-Queens problem is to place N queens on an N × N chessboard such that no two queens
 *      attack each other. A queen can move horizontally, vertically, and diagonally. The task is
 *      to generate all possible valid board configurations where N queens can be placed following
 *      these constraints.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 9), representing the size of the chessboard (N × N).
 * 
 * Output:
 *     - All valid placements of N queens on the board.
 *     - The total number of possible valid arrangements.
 * 
 * Example:
 *     Input:
 *     4
 * 
 *     Output:
 *     [ 
 *       [".Q..",  // Representation of valid queen placements
 *        "...Q",
 *        "Q...",
 *        "..Q."],
 * 
 *       ["..Q.", 
 *        "Q...",
 *        "...Q",
 *        ".Q.."]
 *     ]
 *     2
 * 
 *     Explanation:
 *     - The first board configuration shows one valid way to place the queens.
 *     - The second board configuration shows another valid way.
 *     - The total number of valid arrangements is `2`.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class j01NQueens {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // Print all possible arrangements of N-Queens
        printNQueenArrangements(new boolean[n][n], "", 0);

        // Print the count of all possible valid arrangements
        System.out.println(countNQueensArrangements(new boolean[n][n], n, 0));

        // Store and print all valid board configurations
        List<List<String>> set = new ArrayList<>();
        placeNQueens(new boolean[n][n], n, 0, set);
        System.out.println(set);
        in.close();
    }

    /*-
     * Approach: Backtracking
     * 
     * Intuition:
     * - The problem is solved using backtracking, a technique that explores all 
     *   possibilities and backtracks when an invalid state is encountered.
     * - We place a queen in a row and then recursively try to place queens in the next row 
     *   while ensuring that no two queens attack each other.
     * - If we successfully place all N queens, we have found a valid arrangement.
     * 
     * Explanation:
     * - We start from row `0` and try placing a queen in each column.
     * - If placing the queen at `row, col` is safe (i.e., no other queen is attacking it), 
     *   we place the queen and move to the next row.
     * - If all queens are placed successfully, we print/store the arrangement.
     * - We then backtrack by removing the queen and trying the next possibility.
     * - The function uses recursion to explore all possible placements.
     * 
     * Time Complexity:
     * - O(N!) in the worst case, as we are placing `N` queens recursively.
     * 
     * Space Complexity:
     * - O(N²) for storing the board.
     */

    public static void printNQueenArrangements(boolean[][] chess, String ans, int row) {
        if (row == chess.length) {
            System.out.println(ans);
            return;
        }
        for (int col = 0; col < chess.length; col++) {
            if (canPlaceQueen(chess, row, col)) {
                chess[row][col] = true;
                printNQueenArrangements(chess, ans + row + "-" + col + ",", row + 1);
                chess[row][col] = false;
            }
        }
    }

    /*-
     * Approach 2: Counting Valid Arrangements
     * 
     * Intuition:
     * - Instead of printing the arrangements, this function counts all possible valid 
     *   ways of placing the N queens.
     * - Uses the same recursive backtracking approach.
     * 
     * Explanation:
     * - If all queens are placed (`row == n`), return `1` as a valid arrangement is found.
     * - Try placing a queen in every column and recursively count the valid arrangements.
     * - Use backtracking by removing the queen after checking each placement.
     * 
     * Time Complexity:
     * - O(N!) similar to the first approach.
     * 
     * Space Complexity:
     * - O(N²) for the board representation.
     */

    public static int countNQueensArrangements(boolean[][] chess, int n, int row) {
        if (row == n) {
            return 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (canPlaceQueen(chess, row, i)) {
                chess[row][i] = true;
                count += countNQueensArrangements(chess, n, row + 1);
                chess[row][i] = false;
            }
        }
        return count;
    }

    /*-
     * Approach 3: Storing and Returning Valid Arrangements
     * 
     * Intuition:
     * - Instead of printing the arrangements, this approach stores them in a list.
     * - Each valid arrangement is stored as an `N x N` list of strings, where:
     *   - `"Q"` represents a queen.
     *   - `"."` represents an empty space.
     * - We generate all valid solutions and store them in a list.
     * 
     * Explanation:
     * - Use recursion and backtracking to explore all valid placements.
     * - Convert the board state into a list of strings and add it to the final result.
     * - This method is useful when the output needs to be returned instead of printed.
     * 
     * Time Complexity:
     * - O(N!) due to the recursive nature of the solution.
     * 
     * Space Complexity:
     * - O(N²) for storing the board configurations.
     */

    public static void placeNQueens(boolean[][] chess, int n, int row, List<List<String>> set) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String s = "";
                for (int j = 0; j < n; j++) {
                    if (chess[i][j]) {
                        s += "Q";
                    } else {
                        s += ".";
                    }
                }
                list.add(s);
            }
            set.add(list);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (canPlaceQueen(chess, row, i)) {
                chess[row][i] = true;
                placeNQueens(chess, n, row + 1, set);
                chess[row][i] = false;
            }
        }
    }

    /*-
     * Function: canPlaceQueen
     * 
     * Intuition:
     * - A queen can be placed only if no other queen is attacking it.
     * - We need to check the column, upper-left diagonal, and upper-right diagonal.
     * 
     * Explanation:
     * - Check above in the same column.
     * - Check upper-left diagonal.
     * - Check upper-right diagonal.
     * - If no queen is found in these positions, return `true`, else return `false`.
     * 
     * Time Complexity:
     * - O(N) for checking a column and diagonals.
     * 
     * Space Complexity:
     * - O(1) as no extra space is used.
     */

    public static boolean canPlaceQueen(boolean[][] chess, int row, int col) {
        for (int i = row - 1; i >= 0; i--) {
            if (chess[i][col])
                return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chess[i][j])
                return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < chess[0].length; i--, j++) {
            if (chess[i][j])
                return false;
        }
        return true;
    }
}
