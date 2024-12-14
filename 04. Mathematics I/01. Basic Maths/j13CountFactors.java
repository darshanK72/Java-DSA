/**
 * Problem Statement:
 * 
 *     Given an integer `n`, count the total number of factors (divisors) of `n`.
 * 
 * Input:
 *     - A single integer `n`.
 * 
 * Output:
 *     - An integer representing the total number of factors of `n`.
 * 
 * Example:
 *     Input:
 *     12
 *     Output:
 *     Number of factors of 12 is 6
 *     Explanation: Factors of 12 are [1, 2, 3, 4, 6, 12].
 * 
 * Constraints:
 *     - `n` is a positive integer.
 */

import java.util.Scanner;

public class j13CountFactors {

    public static void main(String args[]) {
        // Reading the input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer n

        // Calculate and display the number of factors
        System.out.printf("Number of factors of %d is %d\n", n, countFactors(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Efficient Counting of Factors
     * 
     * Intuition:
     * - Factors of a number `n` come in pairs. For every factor `i` found, there is a corresponding factor `n / i`.
     * - We only need to check numbers up to `sqrt(n)` because factors greater than `sqrt(n)` will already have been paired with smaller factors.
     * - Special care is taken for perfect squares, where one of the factors (sqrt(n)) is repeated only once.
     * 
     * Algorithm:
     * - Iterate from 1 to sqrt(n).
     * - For each `i`, check if it divides `n` without remainder (`n % i == 0`).
     * - If `i` is a factor, increment the count of factors:
     *   - If `i` and `n / i` are the same (i.e., `i == n / i` for perfect squares), increment the count by 1.
     *   - Otherwise, increment the count by 2 (one for `i` and one for `n / i`).
     * - Return the total count of factors.
     * 
     * Time Complexity: O(sqrt(n)), because we only loop up to sqrt(n).
     * Space Complexity: O(1), as we only use integer variables for counting.
     * 
     * @param n The integer for which the number of factors is to be counted.
     * @return The total number of factors of n.
     */
    public static int countFactors(int n) {
        int count = 0; // Initialize factor count

        // Loop from 1 to sqrt(n)
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) { // Check if `i` is a factor
                if (n / i != i) {
                    // If `i` and `n / i` are different, count both
                    count += 2;
                } else {
                    // Otherwise, count only once (perfect square case)
                    count++;
                }
            }
        }

        // Return the total count of factors
        return count;
    }
}
