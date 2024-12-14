/*-
 * Problem Statement:
 * 
 *     Given an integer `n`, find the largest prime factor of `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6).
 * 
 * Output:
 *     - The largest prime factor of `n`.
 * 
 * Example:
 *     Input:
 *     28
 *     Output:
 *     7
 * 
 *     Explanation:
 *     The prime factors of 28 are [2, 2, 7]. The largest prime factor is 7.
 */

import java.util.Scanner;

public class j09LargestPrimeFactor {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number n for which we need to find the largest prime factor
        System.out.println(largestPrimeFactor(n)); // Print the result
        in.close(); // Close the scanner to release resources
    }

    /*-
     * Approach: Trial Division to Find Largest Prime Factor
     * 
     * Intuition:
     * - We perform trial division starting from 2 to sqrt(n), checking which numbers divide `n`.
     * - For each divisor, we check if it divides `n` multiple times (as it may have higher powers).
     * - We track the largest divisor that divides `n` and is prime.
     * 
     * Time Complexity:
     * - O(sqrt(n)) because we only iterate up to sqrt(n) to check factors.
     * 
     * Space Complexity:
     * - O(1) since we only need a few variables for calculations.
     * 
     * @param n The number for which we need to find the largest prime factor.
     * @return The largest prime factor of `n`.
     */
    public static int largestPrimeFactor(int n) {
        int max = -1; // Initialize the largest prime factor as -1
        // Trial division to find prime factors
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) { // If `i` is a divisor of `n`
                max = Math.max(max, i); // Update max if `i` is greater
                n /= i; // Divide `n` by `i` until it's no longer divisible
            }
        }
        if (n > 1) { // If `n` is prime and greater than 1, it's the largest prime factor
            max = Math.max(max, n);
        }
        return max; // Return the largest prime factor
    }
}
