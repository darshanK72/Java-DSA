/*-
 * Problem Statement:
 * 
 *     The Nim Game is a two-player game where players take turns removing stones from piles. On each turn, 
 *     a player must remove 1, 2, or 3 stones from a pile. The player who cannot take a turn (because the pile is empty) loses.
 *     Given a pile of `n` stones, determine if the current player can win the game assuming both players play optimally.
 *     The current player can win if and only if `n % 4 != 0`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number of stones in the pile.
 * 
 * Output:
 *     - A boolean value: `true` if the current player can win the game, `false` otherwise.
 * 
 * Example:
 *     Input:
 *     4
 *     Output:
 *     false
 * 
 *     Explanation:
 *     - In this case, the current player has no way to avoid losing because `4 % 4 == 0`.
 *     - Regardless of how many stones are removed, the opponent can always make a move to leave `n % 4 == 0`.
 *     - Thus, the current player loses, so the output is `false`.
 */

import java.util.Scanner;

public class j01NimGame {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of stones in the pile
        System.out.println(canWinNim(n)); // Output: true if the current player can win, false otherwise
        in.close();
    }

    /*-
     * Approach: Check if the current player can win based on the number of stones.
     * 
     * Intuition:
     * - The key observation is that the outcome of the game is determined by `n % 4`. 
     * - If `n % 4 == 0`, then the current player is in a losing position because no matter how many stones are removed,
     *   the opponent can always play optimally and force the game into a state where `n % 4 == 0` again.
     * - If `n % 4 != 0`, the current player can make a move that forces the opponent into a losing position.
     * 
     * Time Complexity:
     * - O(1), as the solution only involves checking the result of a modulus operation.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from the input and output.
     * 
     * @param n The number of stones in the pile.
     * @return true if the current player can win, false otherwise.
     */
    public static boolean canWinNim(int n) {
        // Current player wins if n % 4 != 0, otherwise they lose
        return !(n % 4 == 0);
    }
}
