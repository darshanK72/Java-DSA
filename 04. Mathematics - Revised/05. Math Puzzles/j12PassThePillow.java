/**
 * Problem Statement:
 * 
 *     There are `n` players standing in a circle, and a pillow is passed between them every second. 
 *     The pillow is passed in a clockwise direction until it reaches the last player, then it is 
 *     passed in the opposite direction (counter-clockwise). The process continues with the pillow 
 *     being passed back and forth. Given the number of players `n` and the time `time` in seconds, 
 *     the task is to determine which player holds the pillow after `time` seconds.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000), representing the number of players.
 *     - An integer `time` (0 <= time <= 10^9), representing the time passed.
 * 
 * Output:
 *     - Return the index of the player holding the pillow after `time` seconds. Players are indexed starting from 1.
 * 
 * Example:
 *     Input:
 *     4 5
 *     Output:
 *     2
 * 
 *     Explanation:
 *     There are 4 players, and after 5 seconds, the pillow reaches player 2.
 *     pass 1 : 1 -> 2 -> 3 -> 4 - (3 sec)
 *     pass 2 : 1 <- (2 at 5 sec) <- 3 <- 4 - (6 sec)
 *    
 *     Input:
 *     3 7
 *     Output:
 *     1
 * 
 *     Explanation:
 *     There are 3 players, and after 7 seconds, the pillow reaches player 1.
 */

import java.util.Scanner;

public class j12PassThePillow {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of players
        int time = in.nextInt(); // Input: time passed
        System.out.println(passThePillow(n, time)); // Output the result
        in.close();
    }

    /**
     * Approach: Using Modulo Arithmetic to Determine Final Position
     * 
     * Intuition:
     * - Every full cycle (n-1 steps) returns the pillow to its starting point. So the total time `time` can be divided into full cycles 
     *   and remaining time.
     * - The direction alternates between clockwise and counter-clockwise:
     *     * After the first complete cycle (clockwise), it moves in reverse direction.
     *     * The next cycle will again be clockwise, and so on.
     * 
     * Algorithm:
     * 1. First, determine how many full cycles (`time / (n-1)`) have passed.
     * 2. The direction alternates after each cycle. If the number of cycles is odd, the direction is counter-clockwise; otherwise, it's clockwise.
     * 3. Calculate the remaining time after completing full cycles, and adjust the pillow’s final position accordingly.
     * 
     * Time Complexity:
     * - O(1), as the solution only requires a few constant-time operations.
     * 
     * Space Complexity:
     * - O(1), as the space usage is constant.
     * 
     * @param n The number of players.
     * @param time The time elapsed.
     * @return The index of the player holding the pillow.
     */
    public static int passThePillow(int n, int time) {
        // Calculate if the last pass was in reverse direction (counter-clockwise)
        boolean lastPass = (time / (n - 1)) % 2 == 1;

        // If the last pass was in reverse direction, calculate the player holding the
        // pillow
        if (lastPass) {
            return n - time % (n - 1); // Reverse direction: Player n to Player 1
        } else {
            // Otherwise, calculate the player holding the pillow in the forward direction
            return time % (n - 1) + 1; // Clockwise direction: Player 1 to Player n
        }
    }
}
