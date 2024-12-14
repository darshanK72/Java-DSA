/*-
 * Problem Statement:
 * 
 *     Alice and Bob are playing a game. Each turn, starting with Alice, the player must pick up coins 
 *     such that the total value of the coins picked is 115. A coin of value 75 and a coin of value 10 
 *     are available. The game ends when a player cannot pick up coins with a total value of 115.
 *     Return the name of the player who wins the game if both players play optimally.
 * 
 * Input:
 *     - Two integers `x` and `y` representing the number of coins with values 75 and 10 respectively.
 *     - The integers `x` and `y` satisfy (1 <= x, y <= 100).
 * 
 * Output:
 *     - Return the name of the winner, either "Alice" or "Bob".
 * 
 * Example:
 *     Input:
 *     2 7
 *     Output:
 *     Alice
 * 
 *     Explanation:
 *     Alice picks 1 coin of 75 and 4 coins of 10. Then Bob cannot make a valid move, so Alice wins.
 * 
 *     Input:
 *     4 11
 *     Output:
 *     Bob
 * 
 *     Explanation:
 *     Alice picks 1 coin of 75 and 4 coins of 10. Bob picks 1 coin of 75 and 4 coins of 10. Now Alice 
 *     cannot make a valid move, so Bob wins.
 */

import java.util.Scanner;

public class j19WinningPlayerInCoinGame {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int coin75 = in.nextInt(); // Input: number of 75-value coins
        int coin10 = in.nextInt(); // Input: number of 10-value coins
        System.out.println(winningPlayer(coin75, coin10)); // Call the solution method
        in.close();
    }

    /*-
     * Approach 1: Greedy Simulation Approach
     * 
     * Intuition:
     * - Alice and Bob alternately take 1 coin of value 75 and 4 coins of value 10 in each turn.
     * - The game ends when either player cannot take this combination. We simulate the game by alternating turns 
     *   between Alice and Bob, checking if they have enough coins to pick the required set.
     * 
     * Time Complexity:
     * - O(min(x, y)) since in each turn, we either decrease the number of 75-value or 10-value coins by 1 or 4 respectively.
     * 
     * Space Complexity:
     * - O(1) because we are only using a few integer variables for tracking the state.
     * 
     * @param x The number of 75-value coins.
     * @param y The number of 10-value coins.
     * @return The name of the winner ("Alice" or "Bob").
     */
    public static String winningPlayer(int x, int y) {
        // Alice starts first, so simulate alternating turns between Alice and Bob
        boolean isAliceTurn = true;

        while (x >= 1 && y >= 4) {
            // Each player takes 1 coin of 75 and 4 coins of 10 in their turn
            if (isAliceTurn) {
                x -= 1;
                y -= 4;
            } else {
                x -= 1;
                y -= 4;
            }

            // Alternate turns
            isAliceTurn = !isAliceTurn;
        }

        // If it's Alice's turn and she cannot take a move, Bob wins, and vice versa
        return isAliceTurn ? "Bob" : "Alice";
    }

    /*-
     * Approach 1: Optimized Solution for Determining the Losing Player
     * 
     * Intuition:
     * - Alice and Bob alternate turns, where each player must take 1 coin of 75 and 4 coins of 10 (totaling 115 value).
     * - To determine the winner, we calculate how many full rounds can be played with the available coins.
     *   A round consists of 1 coin of 75 and 4 coins of 10.
     *   The number of full rounds is the minimum of the number of 75-value coins divided by 2 and the number of 10-value coins divided by 8.
     * - After calculating the rounds, the remaining coins are updated and the winner is decided based on who cannot make a valid move.
     * 
     * Time Complexity:
     * - O(1), since we only perform simple arithmetic and comparisons without iterating over the coins.
     * 
     * Space Complexity:
     * - O(1), as we are only using a constant amount of space.
     * 
     * @param x The number of 75-value coins.
     * @param y The number of 10-value coins.
     * @return The name of the player who loses ("Alice" or "Bob").
     */
    public static String losingPlayer(int x, int y) {
        // Calculate how many full rounds can be played
        int rounds = Math.min(x / 2, y / 8); // Each round consumes 2 coins of 75 and 8 coins of 10

        // Update the remaining coins after the rounds
        x -= rounds * 2; // Update the remaining 75-value coins
        y -= rounds * 8; // Update the remaining 10-value coins

        // If Alice can still make a valid move (i.e., x >= 2 for 75-value coins and y >= 8 for 10-value coins), Alice wins
        // Otherwise, Bob wins because Alice cannot make a valid move
        if (x > 0 && y >= 4) {
            return "Alice"; // Alice can still play
        }
        return "Bob"; // Bob wins since Alice cannot play
    }
}
