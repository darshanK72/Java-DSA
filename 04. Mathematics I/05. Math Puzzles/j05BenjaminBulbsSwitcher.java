/**
 * Problem Statement:
 * 
 *     There are `n` bulbs in a row, initially all turned off. You have to perform `n` rounds of operations.
 *     In the first round, you toggle every bulb (turn them on). In the second round, you toggle every second bulb (turn off bulbs that were on). 
 *     In the third round, you toggle every third bulb, and so on until the `n`th round where you toggle only the `n`th bulb.
 *     After performing all `n` rounds, how many bulbs remain turned on?
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^10), representing the number of bulbs.
 * 
 * Output:
 *     - The number of bulbs that remain turned on.
 * 
 * Example:
 *     Input:
 *     3
 *     Output:
 *     1
 * 
 *     Explanation:
 *     - After the first round, all bulbs are turned on.
 *     - After the second round, the second bulb is turned off.
 *     - After the third round, the third bulb is turned on.
 *     - Only the first bulb remains on at the end.
 */

import java.util.Scanner;

// Complexity: O(sqrt(N)), where N is the input number.
public class j05BenjaminBulbsSwitcher {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: number of bulbs
        System.out.println(bulbSwitch(n)); // Output: the number of bulbs that remain on
        in.close();
    }

    /**
     * Approach: The problem can be reduced to finding the number of perfect squares less than or equal to `n`.
     * 
     * Intuition:
     * - A bulb will be turned on or off depending on the number of times it is toggled.
     * - A bulb at position `i` will be toggled as many times as the number of divisors of `i`.
     * - If `i` has an odd number of divisors, it will end up being turned on; otherwise, it will be turned off.
     * - Only perfect square numbers have an odd number of divisors because they have one divisor that appears twice (the square root of the number).
     * - Therefore, the number of bulbs that remain on is equal to the number of perfect squares ≤ `n`.
     * 
     * Time Complexity:
     * - O(sqrt(N)), as the solution iterates from `1` to `sqrt(n)` to count the perfect squares.
     * 
     * Space Complexity:
     * - O(1), as only a constant amount of extra space is used.
     * 
     * @param n The number of bulbs.
     * @return The number of bulbs that remain on after all rounds.
     */
    public static int bulbSwitch(int n) {
        // Count the number of perfect squares less than or equal to `n`
        int c = 0;
        for (int i = 1; i * i <= n; i++) {
            c++; // Increment count for each perfect square
        }
        return c;
    }

    /**
     * Alternative Approach: Using the property of perfect squares directly without a loop.
     * 
     * Intuition:
     * - The number of perfect squares less than or equal to `n` is simply the integer part of the square root of `n`.
     * - Instead of looping, we can directly compute this value using the `sqrt` function and return it.
     * 
     * Time Complexity:
     * - O(1), since the computation of the square root is a constant time operation.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used aside from the input and output.
     * 
     * @param n The number of bulbs.
     * @return The number of bulbs that remain on after all rounds.
     */
    public static int optimizedBulbSwitch(int n) {
        // Return the integer part of the square root of n
        return (int) Math.sqrt(n);
    }
}
