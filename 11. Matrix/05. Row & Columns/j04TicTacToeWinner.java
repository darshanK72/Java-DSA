/**
 * Problem Statement:
 * 
 *     You are given a sequence of moves in a Tic-Tac-Toe game between two players, A and B. The players alternate making moves on a 3x3 grid. 
 *     Player A always plays with the symbol 'X' and Player B plays with the symbol 'O'. 
 *     Your task is to determine the winner of the game based on the sequence of moves. If the game ends in a draw, return "Draw". 
 *     If the game is still ongoing, return "Pending". 
 *     
 * Input:
 *     - An integer `m` (1 <= m <= 9), representing the number of moves made.
 *     - A 2D array `moves` of size `m x 2` where each element is a pair `(r, c)` representing the row and column index of the move.
 * 
 * Output:
 *     - Return "A" if Player A wins, "B" if Player B wins, "Draw" if the game is a draw, and "Pending" if the game is still ongoing.
 * 
 * Example:
 *     Input:
 *     5
 *     0 0
 *     1 1
 *     0 1
 *     1 0
 *     0 2
 *     
 *     Output:
 *     A
 *     
 *     Explanation:
 *     - Player A places 'X' at (0, 0).
 *     - Player B places 'O' at (1, 1).
 *     - Player A places 'X' at (0, 1).
 *     - Player B places 'O' at (1, 0).
 *     - Player A places 'X' at (0, 2) and wins the game by completing a row.
 */

import java.util.Scanner;

public class j04TicTacToeWinner {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(); // Input: number of moves
        int[][] moves = new int[m][2];

        // Reading the moves for the game
        for (int i = 0; i < m; i++) {
            moves[i][0] = in.nextInt(); // Row index of the move
            moves[i][1] = in.nextInt(); // Column index of the move
        }

        // Call the function to determine the winner
        System.out.println(tictactoeWinner(moves));

        in.close();
    }

    /**
     * Approach: Simulate the Tic-Tac-Toe game and check for a winner
     * 
     * Intuition:
     * - We simulate the Tic-Tac-Toe game by iterating through the moves.
     * - For each move, we place either 'X' or 'O' on the board.
     * - After each move, we check if the player who made the move has won by checking the row, column, and diagonals.
     * - If a player wins, we return "A" for Player A or "B" for Player B.
     * - If all moves are made and there's no winner, we return "Draw".
     * - If the game is not over and there are still moves to be made, we return "Pending".
     * 
     * Time Complexity:
     * - O(1): The board size is fixed (3x3), and we are checking at most 9 moves. The operations to check the winner take constant time.
     * 
     * Space Complexity:
     * - O(1): The board size is fixed, so the space used is constant.
     * 
     * @param moves The list of moves made in the game.
     * @return The result of the game: "A", "B", "Draw", or "Pending".
     */
    public static String tictactoeWinner(int[][] moves) {
        char[][] board = new char[3][3]; // 3x3 Tic-Tac-Toe board
        char c = 'X'; // Player A starts with 'X'

        // Process each move
        for (int[] move : moves) {
            board[move[0]][move[1]] = c; // Place the current player's symbol
            if (isWinner(board, move[0], move[1], c)) {
                if (c == 'X')
                    return "A"; // Player A wins
                else
                    return "B"; // Player B wins
            }
            // Switch player
            if (c == 'X')
                c = 'O';
            else
                c = 'X';
        }

        // If all 9 moves have been played and no winner
        if (moves.length == 9)
            return "Draw";

        // If there are still moves to be made
        return "Pending";
    }

    /**
     * Helper function: Check if a player has won
     * 
     * Intuition:
     * - After each move, we check if the current player has completed a row, column, or diagonal.
     * - If there are three identical symbols in a row, column, or diagonal, that player has won.
     * 
     * Time Complexity:
     * - O(1): Checking rows, columns, and diagonals for a winner involves a constant number of operations (3 for each).
     * 
     * Space Complexity:
     * - O(1): No additional space is used beyond the board.
     * 
     * @param board The Tic-Tac-Toe board.
     * @param row The row index of the last move.
     * @param col The column index of the last move.
     * @param chr The symbol of the current player ('X' or 'O').
     * @return true if the current player has won, false otherwise.
     */
    public static boolean isWinner(char[][] board, int row, int col, char chr) {
        int count = 0;

        // Check row
        for (int c = 0; c < 3; c++) {
            if (board[row][c] == chr)
                count++;
        }
        if (count == 3)
            return true;

        // Check column
        count = 0;
        for (int r = 0; r < 3; r++) {
            if (board[r][col] == chr)
                count++;
        }
        if (count == 3)
            return true;

        // Check diagonal (top-left to bottom-right)
        count = 0;
        for (int d = 0; d < 3; d++) {
            if (board[d][d] == chr)
                count++;
        }
        if (count == 3)
            return true;

        // Check anti-diagonal (top-right to bottom-left)
        count = 0;
        for (int d = 0; d < 3; d++) {
            if (board[d][3 - d - 1] == chr)
                count++;
        }
        if (count == 3)
            return true;

        return false;
    }
}
