/*-
 * Problem Statement:
 * 
 * The Knight's Tour problem involves moving a knight on an `N × M` chessboard 
 * such that the knight visits every square **exactly once**.
 * 
 * The knight moves in an "L" shape:
 * - 2 squares in one direction and 1 square perpendicular to it.
 * - 8 possible moves in total.
 * 
 * The goal is to find a valid sequence of moves where the knight covers the entire board.
 * 
 * Input:
 *     - Two integers `n` and `m`, representing the dimensions of the chessboard.
 * 
 * Output:
 *     - A valid knight's tour sequence, printed as a `n × m` matrix.
 *     - If no valid sequence exists, return an empty board.
 * 
 * Example:
 *     Input:
 *     5 5
 * 
 *     Output:
 *     A 5x5 matrix showing the order in which the knight visited each square.
 */

import java.util.Scanner;
import java.util.ArrayList;

public class j02KnightTours {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] chess = new int[n][m];

        // Print a single valid Knight's Tour
        knightTour(chess, 0, 0, 1);

        // Store and print the first valid Knight's Tour found
        System.out.println(knightTour(n, m));

        in.close();
    }

    /*-
     * Approach: Backtracking
     * 
     * Intuition:
     * - The problem is solved using **backtracking**, where we explore all possible moves
     *   for the knight recursively.
     * - If the knight successfully covers the entire board, we print the solution.
     * - If an invalid move is encountered, we backtrack and try another path.
     * 
     * Explanation:
     * - Start from the top-left corner `(0,0)`.
     * - Try all **8 possible moves** of the knight.
     * - If the move is valid, place the knight and move to the next position.
     * - If the board is fully covered, print the solution.
     * - Backtrack when a move leads to a dead-end.
     * 
     * Time Complexity:
     * - **O(8^(N*M))** in the worst case (brute-force search).
     * 
     * Space Complexity:
     * - **O(N*M)** for storing the board state.
     */

    public static void knightTour(int[][] chess, int row, int col, int move) {
        if (row < 0 || col < 0 || row >= chess.length || col >= chess[0].length || chess[row][col] > 0) {
            return; // Invalid move
        }

        if (move == chess.length * chess[0].length) {
            chess[row][col] = move;
            printBoard(chess);
            chess[row][col] = 0; // Backtrack
            return;
        }

        chess[row][col] = move; // Place knight

        // Possible moves of the knight
        knightTour(chess, row - 2, col + 1, move + 1);
        knightTour(chess, row - 1, col + 2, move + 1);
        knightTour(chess, row + 1, col + 2, move + 1);
        knightTour(chess, row + 2, col + 1, move + 1);
        knightTour(chess, row + 2, col - 1, move + 1);
        knightTour(chess, row + 1, col - 2, move + 1);
        knightTour(chess, row - 1, col - 2, move + 1);
        knightTour(chess, row - 2, col - 1, move + 1);

        chess[row][col] = 0; // Backtracking step
    }

    /*-
     * Approach: Finding and Storing a Valid Knight's Tour
     * 
     * Intuition:
     * - Instead of printing, this function **stores** a valid tour in a 2D list.
     * - Uses **backtracking** to find a valid path and stops when a solution is found.
     * 
     * Explanation:
     * - The function initializes a board with `-1` to represent unvisited positions.
     * - It attempts to move the knight to different positions.
     * - If the entire board is covered, return the board as the solution.
     * 
     * Time Complexity:
     * - **O(N*M)** in the best case when a solution is found early.
     * - **O(8^(N*M))** in the worst case.
     * 
     * Space Complexity:
     * - **O(N*M)** for storing the board.
     */

    public static ArrayList<ArrayList<Integer>> knightTour(int n, int m) {
        ArrayList<ArrayList<Integer>> set = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            set.add(new ArrayList<>());
            for (int j = 0; j < m; j++) {
                set.get(i).add(-1);
            }
        }
        int[] dx = { -1, -1, +1, +1, +2, +2, -2, -2 };
        int[] dy = { +2, -2, -2, +2, -1, +1, -1, +1 };

        generateKnightTour(set, n, m, dx, dy, 0, 0, 0);
        return set;
    }

    /*-
     * Function: generateKnightTour
     * 
     * Intuition:
     * - This function **attempts to find a single valid knight's tour** using recursion.
     * - If a valid path is found, it stops searching further.
     * 
     * Explanation:
     * - If all `N*M` positions are filled, return `true` (valid tour found).
     * - Try moving the knight in all 8 possible directions.
     * - If a valid move is found, mark the position and continue.
     * - If a dead-end is reached, backtrack and try another move.
     * 
     * Time Complexity:
     * - **O(8^(N*M))** worst case.
     * 
     * Space Complexity:
     * - **O(N*M)** for storing the board state.
     */

    public static boolean generateKnightTour(ArrayList<ArrayList<Integer>> set, int n, int m, int[] dx, int[] dy, int r,
            int c, int count) {
        if (count == n * m)
            return true;
        if (r < 0 || c < 0 || r >= n || c >= m)
            return false;
        if (set.get(r).get(c) != -1)
            return false;

        for (int i = 0; i < 8; i++) {
            set.get(r).set(c, count);
            if (generateKnightTour(set, n, m, dx, dy, r + dx[i], c + dy[i], count + 1))
                return true;
            set.get(r).set(c, -1);
        }

        return false;
    }

    /*-
     * Function: printBoard
     * 
     * Intuition:
     * - This function prints the chessboard where each square represents the order in
     *   which the knight visited that square.
     * 
     * Explanation:
     * - Iterate through the 2D array and print the numbers in a formatted manner.
     * - Each number represents the move number in which the knight visited that position.
     * 
     * Time Complexity:
     * - **O(N*M)** as it prints all board positions.
     * 
     * Space Complexity:
     * - **O(1)** as no extra space is used.
     */

    public static void printBoard(int[][] chess) {
        for (int[] row : chess) {
            for (int cell : row) {
                System.out.printf("%2d ", cell);
            }
            System.out.println();
        }
        System.out.println();
    }
}
