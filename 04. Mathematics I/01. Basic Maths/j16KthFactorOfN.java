/**
 * Problem Statement:
 * 
 *     You are given two positive integers `n` and `k`. A factor of an integer `n` is defined as an integer `i` 
 *     such that `n % i == 0`. Find the `k`th factor of `n` in ascending order. If there are fewer than `k` factors, 
 *     return -1.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 1000).
 *     - An integer `k` (1 <= k <= 1000).
 * 
 * Output:
 *     - The `k`th factor of `n` in ascending order, or `-1` if `k` is larger than the number of factors of `n`.
 * 
 * Example:
 *     Input:
 *     12 3
 *     Output:
 *     3
 *     Explanation:
 *     Factors of 12 are [1, 2, 3, 4, 6, 12]. The 3rd factor is 3.
 * 
 * Constraints:
 *     - 1 <= n <= 1000
 *     - 1 <= k <= 1000
 * 
 * Notes:
 *     - This is related to LeetCode Problem 1492 - The kth Factor of n.
 */

import java.util.Scanner;

public class j16KthFactorOfN {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer n
        int k = in.nextInt(); // Input: the kth factor to find

        // Compute the kth factor
        System.out.printf("The %dth factor of %d is %d\n", k, n, kthFactor(n, k));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Brute Force to Find the Kth Factor
     * 
     * Intuition:
     * - Factors of a number `n` are integers that divide `n` without a remainder. 
     *   These factors can be found by iterating through numbers from `1` to `n`.
     * - The `k`th factor in ascending order can be determined by counting the factors during this iteration.
     * - If `k` factors are found, return the last one. Otherwise, return -1.
     * 
     * Algorithm:
     * - Iterate through integers `i` from `1` to `n`.
     * - For each integer, check if it divides `n` without a remainder (`n % i == 0`).
     * - If `i` is a factor, decrement `k`.
     * - When `k` reaches `0`, return the current factor.
     * - If the loop completes without finding `k` factors, return `-1`.
     * 
     * Time Complexity:
     * - O(n), as we iterate through all numbers up to `n`.
     * 
     * Space Complexity:
     * - O(1), as we only use a few integer variables.
     * 
     * @param n The input number.
     * @param k The kth factor to find.
     * @return The kth factor of `n` or `-1` if it does not exist.
     */
    public static int kthFactor(int n, int k) {
        int factor = -1; // Initialize factor as -1 (default for no kth factor)

        // Iterate through potential divisors from 1 to n
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) { // Check if i is a factor
                k--; // Decrement k when a factor is found
            }

            // If k becomes 0, the current i is the kth factor
            if (k == 0) {
                factor = i;
                break;
            }
        }

        return factor; // Return the factor or -1 if k factors are not found
    }
}
