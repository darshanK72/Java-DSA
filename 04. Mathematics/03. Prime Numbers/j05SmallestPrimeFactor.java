/**
 * Problem Statement:
 * 
 *     Given a number `n`, find the smallest prime factor (SPF) for each number from 1 to `n`.
 *     For multiple queries, return the smallest prime factor (SPF) for the queried numbers.
 * 
 * Input:
 *     - An integer `q` (1 <= q <= 10^4), representing the number of queries.
 *     - A list of `q` integers where each integer represents a number for which the smallest prime factor is to be found. (1 <= nums[i] <= 1000)
 * 
 * Output:
 *     - For each query, output the smallest prime factor of the queried number.
 * 
 * Example:
 *     Input:
 *     3
 *     10 15 21
 *     Output:
 *     2
 *     3
 *     3
 * 
 *     Explanation:
 *     The smallest prime factor for 10 is 2, for 15 is 3, and for 21 is 3.
 */

import java.util.Scanner;
import java.util.Arrays;

public class j05SmallestPrimeFactor {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);

        // Read the number of queries
        int q = in.nextInt();
        int[] quries = new int[q];
        for (int i = 0; i < q; i++) {
            quries[i] = in.nextInt(); // Read each query number
        }

        // Generate the smallest prime factor (SPF) sieve up to 1000
        int[] seive = getSeive(1000);
        System.out.println(Arrays.toString(seive)); // Print the SPF array for reference

        // Output the smallest prime factor for each query
        for (int i = 0; i < q; i++) {
            System.out.println(seive[quries[i]]); // Print the result for each query
        }

        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach: Using Sieve to Find Smallest Prime Factor (SPF)
     * 
     * Intuition:
     * - We use a modified version of the Sieve of Eratosthenes to precompute the smallest prime factor (SPF) for each number from 1 to `n`.
     * - For each number, if it's still marked as itself (i.e., it's a prime), we mark all its multiples with the prime number as the smallest prime factor.
     * - This allows efficient computation of the smallest prime factor for any number in the range.
     * 
     * Time Complexity:
     * - O(n log log n) for building the sieve, where `n` is 1000.
     * - O(1) for each query lookup in the sieve array.
     * 
     * Space Complexity:
     * - O(n) for the sieve array, where `n` is 1000.
     * 
     * @param n The upper limit up to which smallest prime factors are precomputed.
     * @return A sieve array where each index `i` contains the smallest prime factor of `i`.
     */
    public static int[] getSeive(int n) {
        int[] seive = new int[n + 1]; // Initialize the sieve array
        for (int i = 2; i <= n; i++) {
            seive[i] = i; // Initially, each number is assumed to be its own smallest prime factor
        }
        for (int i = 2; i * i <= n; i++) {
            if (seive[i] == i) { // If `i` is prime
                for (int j = i * i; j <= n; j += i) { // Mark all multiples of `i`
                    if (seive[j] == j) { // If `j` hasn't been marked yet
                        seive[j] = i; // Assign `i` as the smallest prime factor of `j`
                    }
                }
            }
        }
        return seive; // Return the sieve array containing the smallest prime factors
    }
}
