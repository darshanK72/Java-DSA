/**
 * Problem Statement:
 * 
 *     Given a binary array `flips` where `flips[i]` represents that the bulb at position `flips[i]` (1-indexed) is turned on 
 *     at the `i-th` step, determine how many times all the bulbs in the prefix of the array are turned on and aligned (in the on state).
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^4), representing the number of flips.
 *     - An array `flips` of size `n`, where each element (1 <= flips[i] <= n) represents the 1-based position of the bulb flipped on.
 * 
 * Output:
 *     - An integer representing the count of moments when all the bulbs in the prefix are aligned and turned on.
 * 
 * Example:
 *     Input:
 *     5
 *     [3, 2, 4, 1, 5]
 *     Output:
 *     2
 * 
 *     Explanation:
 *     - At step 1: The bulb at position 3 is turned on. Prefix is [OFF, OFF, ON, OFF, OFF].
 *     - At step 2: The bulb at position 2 is turned on. Prefix is [OFF, ON, ON, OFF, OFF].
 *     - At step 3: The bulb at position 4 is turned on. Prefix is [OFF, ON, ON, ON, OFF].
 *     - At step 4: The bulb at position 1 is turned on. Prefix is [ON, ON, ON, ON, OFF]. (All prefix bulbs are aligned!)
 *     - At step 5: The bulb at position 5 is turned on. Prefix is [ON, ON, ON, ON, ON]. (All prefix bulbs are aligned!)
 *     - Moments when the prefix is aligned: Steps 4 and 5, so the output is 2.
 */

import java.util.Scanner;

public class j11CountPrefixAligned {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Number of flips
        int[] flips = new int[n];
        for (int i = 0; i < n; i++) {
            flips[i] = in.nextInt(); // Bulb flipped on at step i
        }

        // Call solution method and output result
        System.out.println(numTimesAllBlue(flips));
        in.close();
    }

    /**
     * Approach: Tracking Maximum Flip Index
     * 
     * Intuition:
     * - For the prefix to be fully aligned, the maximum bulb index turned on so far (`maxTill`) must match the number of flips made.
     * - This ensures that all bulbs up to the current step are on and aligned, without any gaps.
     * 
     * Explanation:
     * - Iterate through the flips array.
     * - Maintain a variable `maxTill` to track the highest index flipped on so far.
     * - For each step `i`, check if `maxTill == i` (1-indexed), which indicates that all bulbs in the prefix are aligned.
     * - Increment the answer `ans` whenever this condition is met.
     * 
     * Time Complexity:
     * - O(n), where `n` is the number of flips, as we iterate through the flips array once.
     * 
     * Space Complexity:
     * - O(1), as we only use a few extra variables.
     * 
     * @param flips The array representing the order of bulb flips.
     * @return The number of moments when the prefix is aligned.
     */
    public static int numTimesAllBlue(int[] flips) {
        int maxTill = 0; // Tracks the maximum bulb index flipped so far
        int ans = 0; // Count of moments when all bulbs in the prefix are aligned

        for (int i = 1; i <= flips.length; i++) {
            maxTill = Math.max(maxTill, flips[i - 1]); // Update maximum index
            if (maxTill == i) {
                ans++; // If the prefix is aligned, increment the counter
            }
        }
        return ans;
    }
}
