/**
 * Problem Statement:
 * 
 *     Given an integer `n`, the task is to calculate the sum of the cubes of the first `n` natural numbers.
 *     That is, compute the sum: `1^3 + 2^3 + 3^3 + ... + n^3`.
 * 
 * Input:
 *     - A single integer `n` (1 <= n <= 10^6), representing the number of terms in the series.
 * 
 * Output:
 *     - A single integer representing the sum of cubes of the first `n` numbers.
 * 
 * Example:
 *     Input:
 *     3
 *     Output:
 *     36
 * 
 *     Explanation:
 *     The sum of cubes for `1^3 + 2^3 + 3^3` is `1 + 8 + 27 = 36`.
 * 
 */

import java.util.Scanner;

public class j23SumOfCubeSeries {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number of terms
        System.out.println(sumOfCubeSeries(n)); // Print the sum of cubes
        System.out.println(sumOfCubeSeriesEfficient(n)); // Print the sum of cubes
        in.close(); // Close the scanner to avoid memory leaks
    }

    /**
     * Approach 1: Brute Force Approach to Sum of Cubes
     * 
     * Intuition:
     * - The sum of cubes for the first `n` natural numbers can be computed directly by iterating through
     *   the numbers and calculating their cubes, then adding them to a running sum.
     * 
     * Time Complexity:
     * - O(n), as we iterate from `1` to `n` and compute the cube for each number.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used for computation.
     * 
     * @param n The input number up to which we calculate the sum of cubes.
     * @return The sum of cubes of the first `n` numbers.
     */
    public static long sumOfCubeSeries(long n) {
        long sum = 0; // Initialize sum
        for (long i = 1; i <= n; i++) {
            sum += (i * i * i); // Add the cube of i to the sum
        }
        return sum; // Return the total sum
    }

    /**
     * Approach 2: Efficient Approach using Formula
     * 
     * Intuition:
     * - The sum of cubes of the first `n` natural numbers can be calculated directly using the formula:
     *   Sum of cubes = (n * (n + 1) / 2) ^ 2
     * 
     * Time Complexity:
     * - O(1), as this approach uses a closed-form formula and does not require iteration.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used for computation.
     * 
     * @param n The input number up to which we calculate the sum of cubes.
     * @return The sum of cubes of the first `n` numbers.
     */
    public static long sumOfCubeSeriesEfficient(long n) {
        // Formula for sum of cubes: (n * (n + 1) / 2) ^ 2
        long sumOfFirstN = (n * (n + 1)) / 2;
        return sumOfFirstN * sumOfFirstN; // Return the square of the sum of first `n` numbers
    }
}
