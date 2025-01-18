/**
 * Problem Statement:
 * 
 *      You are given an integer array `rolls` representing the results of a series of dice rolls, 
 *      and two integers `mean` and `n`. The `mean` represents the average value of all dice rolls 
 *      (including the missing ones). You need to find the `n` missing dice rolls such that the 
 *      overall mean matches the given `mean`.
 * 
 *      Each die roll is a number between 1 and 6 (inclusive). If no such missing rolls exist, 
 *      return an empty array.
 * 
 * Input:
 *     - An integer array `rolls` (1 <= rolls.length <= 10^4), where each element satisfies 
 *       (1 <= rolls[i] <= 6).
 *     - An integer `mean` (1 <= mean <= 6), representing the target average.
 *     - An integer `n` (1 <= n <= 10^5), representing the number of missing dice rolls.
 * 
 * Output:
 *     - An integer array of size `n` containing the missing dice rolls, or an empty array 
 *       if it's impossible to construct such a result.
 * 
 * Example:
 *     Input:
 *         rolls = [3, 2, 4, 3]
 *         mean = 4
 *         n = 2
 *     Output:
 *         [6, 6]
 * 
 *     Explanation:
 *         The total number of dice rolls, including missing ones, is 6 (4 existing rolls + 2 missing rolls). 
 *         The total sum needed for an average of 4 is 6 * 4 = 24.
 *         The sum of the existing rolls is 3 + 2 + 4 + 3 = 12.
 *         The sum of the missing rolls must be 24 - 12 = 12. Since each roll must be between 1 and 6, 
 *         the only valid configuration is [6, 6].
 */

import java.util.Arrays;

public class j21FindMissingObservations {

    public static void main(String[] args) {
        int[] rolls = { 3, 2, 4, 3 };
        int mean = 4;
        int n = 2;

        // Example test case
        System.out.println(Arrays.toString(missingRolls(rolls, mean, n)));
    }

    /**
     * Approach: Greedy Allocation
     * 
     * Intuition:
     * - The task is to distribute the required sum across `n` missing rolls while satisfying 
     *   the constraints of dice values (1 to 6).
     * - First, calculate the total sum needed for the missing rolls.
     * - Distribute the average value (sum / n) across all `n` rolls and use the remainder 
     *   to adjust the values incrementally, ensuring the total sum is achieved.
     * - If the required sum is out of the valid range (n <= sum <= 6 * n), return an empty array.
     * 
     * Time Complexity:
     * - O(n): We iterate through the missing rolls array once to distribute the values.
     * 
     * Space Complexity:
     * - O(n): An additional array of size `n` is used to store the missing rolls.
     * 
     * @param rolls The array of existing dice rolls.
     * @param mean The target average of all rolls.
     * @param n The number of missing dice rolls.
     * @return An array of size `n` representing the missing rolls, or an empty array if impossible.
     */
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        // Calculate the total sum needed for all rolls
        int totalSum = (rolls.length + n) * mean;

        // Subtract the sum of the given rolls from the total sum
        for (int roll : rolls) {
            totalSum -= roll;
        }

        // If the required sum is outside the valid range, return an empty array
        if (totalSum < n || totalSum > 6 * n) {
            return new int[] {};
        }

        // Calculate the base value and remainder for distribution
        int baseValue = totalSum / n;
        int remainder = totalSum % n;

        // Allocate the missing rolls
        int[] missingRolls = new int[n];
        for (int i = 0; i < n; i++) {
            missingRolls[i] = baseValue;
            if (remainder > 0) {
                missingRolls[i]++;
                remainder--;
            }
        }

        return missingRolls;
    }
}
