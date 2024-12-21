/**
 * Problem Statement:
 * 
 *     You are given a 3x3 Tic-Tac-Toe board, represented as a list of strings, where each string contains three characters. The characters are either 'X', 'O', or a space ' ' representing an empty cell. 
 *     Your task is to determine if the board is in a valid state, considering the following conditions:
 *     - Player 'X' starts the game, so there must be either the same number or one more 'X' than 'O'.
 *     - If 'X' wins, there must be exactly one more 'X' than 'O'.
 *     - If 'O' wins, there must be an equal number of 'X' and 'O'.
 *     - The game cannot have both 'X' and 'O' winning simultaneously.
 *     - If the game is still ongoing or ends in a draw, the board state is valid.
 * 
 * Input:
 *     - A list of 3 strings, each containing 3 characters representing the Tic-Tac-Toe board.
 * 
 * Output:
 *     - Return `true` if the board is in a valid state, otherwise return `false`.
 * 
 * Example:
 *     Input:
 *     ["XOX", "O O", "XOX"]
 *     
 *     Output:
 *     false
 *     
 *     Explanation:
 *     - Player 'X' has one more 'X' than 'O', but there are multiple 'X' and 'O' winning conditions met, making the board invalid.
 */

import java.util.Scanner;

public class j05ValidTicTacToe {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        String[] board = new String[3];
        for (int i = 0; i < 3; i++) {
            board[i] = in.nextLine(); // Reading each row of the Tic-Tac-Toe board
        }

        // Check if the Tic-Tac-Toe board is valid
        System.out.println(validTicTacToe(board));

        in.close();
    }

    /**
     * Approach: Validate the Tic-Tac-Toe Board State
     * 
     * Intuition:
     * - The first step is to count the number of 'X' and 'O' characters on the board to check if they follow the expected rules (e.g., 'X' cannot have fewer than 'O').
     * - Then, we check if either 'X' or 'O' has won the game. If both players win simultaneously, the board is invalid.
     * - If 'X' wins, there should be exactly one more 'X' than 'O', and if 'O' wins, the number of 'X' and 'O' should be equal.
     * 
     * Time Complexity:
     * - O(1): The size of the board is fixed at 3x3, so the operations to check the board are constant.
     * 
     * Space Complexity:
     * - O(1): We are using a constant amount of space for the board and calculations.
     * 
     * @param board The Tic-Tac-Toe board.
     * @return true if the board is in a valid state, false otherwise.
     */
    public static boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;

        // Count the occurrences of 'X' and 'O' on the board
        for (String s : board) {
            for (int i = 0; i < 3; i++) {
                if (s.charAt(i) == 'X')
                    countX++;
                else if (s.charAt(i) == 'O')
                    countO++;
            }
        }

        // Check if the counts of 'X' and 'O' are valid
        if (countX != countO && countO != countX - 1)
            return false;

        // Check if 'X' wins and the number of 'X' and 'O' is valid
        if (isWinner(board, 'X') && countX != countO + 1)
            return false;

        // Check if 'O' wins and the number of 'X' and 'O' is valid
        if (isWinner(board, 'O') && countX != countO)
            return false;

        return true;
    }

    /**
     * Helper function: Check if a player has won the game
     * 
     * Intuition:
     * - To check if a player (either 'X' or 'O') has won, we need to check all possible winning conditions.
     * - This includes checking all rows, columns, and both diagonals for three matching symbols.
     * 
     * Time Complexity:
     * - O(1): The checks for rows, columns, and diagonals are constant operations (since the board is fixed at 3x3).
     * 
     * Space Complexity:
     * - O(1): No additional space is used beyond the board.
     * 
     * @param board The Tic-Tac-Toe board.
     * @param c The player symbol ('X' or 'O').
     * @return true if the player has won, false otherwise.
     */
    public static boolean isWinner(String[] board, char c) {
        // Check rows for a win
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c)
                return true;
        }

        // Check columns for a win
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c)
                return true;
        }

        // Check diagonal (top-left to bottom-right)
        if (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c)
            return true;

        // Check anti-diagonal (top-right to bottom-left)
        if (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c)
            return true;

        return false;
    }
}
