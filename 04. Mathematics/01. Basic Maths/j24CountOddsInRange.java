/**
 * Problem Statement:
 * 
 *     Given two integers `low` and `high`, the task is to count how many odd numbers exist
 *     between the inclusive range `[low, high]`.
 * 
 * Input:
 *     - Two integers `low` and `high` (1 <= low <= high <= 10^9).
 * 
 * Output:
 *     - A single integer representing the count of odd numbers between `low` and `high`.
 * 
 * Example:
 *     Input:
 *     3 9
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The odd numbers in the range [3, 9] are 3, 5, 7, and 9, so the count is 4.
 * 
 */

import java.util.Scanner;

public class j24CountOddsInRange {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int low = in.nextInt(); // Input: the lower bound of the range
        int high = in.nextInt(); // Input: the upper bound of the range
        System.out.println(countOdds(low, high)); // Print the result
        in.close(); // Close the scanner to avoid memory leaks
    }

    /**
     * Approach 1: Direct Calculation Using Mathematical Formula
     * 
     * Intuition:
     * - The number of odd numbers in a range [low, high] can be computed efficiently using the formula:
     *   - The number of odd numbers up to `n` is given by `(n + 1) / 2`.
     *   - So, the number of odd numbers between `low` and `high` is:
     *     count = (high + 1) / 2 - low / 2
     * 
     * Time Complexity:
     * - O(1), as we are performing a constant number of operations.
     * 
     * Space Complexity:
     * - O(1), as we are only using a few variables for computation.
     * 
     * @param low The lower bound of the range.
     * @param high The upper bound of the range.
     * @return The count of odd numbers in the range [low, high].
     */
    public static int countOdds(int low, int high) {
        return (high + 1) / 2 - low / 2;
    }
}
