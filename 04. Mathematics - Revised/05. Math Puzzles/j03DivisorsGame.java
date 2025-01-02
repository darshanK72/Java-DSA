/**
 * Problem Statement:
 * 
 *     In the Divisors Game, two players take turns to choose a divisor `x` of the current number `n` such that `1 < x < n`. 
 *     The chosen divisor `x` is then subtracted from `n`, and the game continues with the new value of `n`. 
 *     The player who cannot make a move (because `n` becomes 1) loses the game.
 *     Given an integer `n`, determine if the first player can win the game assuming both players play optimally.
 *     The first player can always win if `n` is even, and lose if `n` is odd.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number at the start of the game.
 * 
 * Output:
 *     - A boolean value: `true` if the first player can win, `false` otherwise.
 * 
 * Example:
 *     Input:
 *     4
 *     Output:
 *     true
 * 
 *     Explanation:
 *     - In this case, the first player can win by subtracting 2 from 4, leaving 2 for the second player.
 *     - The second player is forced to subtract 1, and the first player will win.
 */

import java.util.Scanner;

public class j03DivisorsGame {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the starting number n
        System.out.println(divisorGame(n)); // Output: true if the first player can win, false otherwise
        in.close();
    }

    /**
     * Approach: The winner is determined by whether the number n is even or odd.
     * 
     * Intuition:
     * - The game is straightforward: if the number `n` is even, the first player can always make a move
     *   that leaves the second player with an odd number, which will eventually force the second player to lose.
     * - If `n` is odd, the first player has no way to avoid leaving an even number for the second player,
     *   and thus the second player will always win.
     * 
     * Time Complexity:
     * - O(1), as the solution only involves checking if `n` is even or odd.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from the input and output.
     * 
     * @param n The starting number in the game.
     * @return true if the first player can win, false otherwise.
     */
    public static boolean divisorGame(int n) {
        // The first player wins if n is even, loses if n is odd.
        return n % 2 == 0;
    }
}
