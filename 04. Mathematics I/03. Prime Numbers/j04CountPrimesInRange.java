
/**
 * * Problem Statement:
 * Given multiple queries where each query specifies a range [s, e],
 * the task is to find the number of prime numbers within each range.
 * A prime number is a natural number greater than 1 that has no positive divisors other than 1 and itself.
 *
 * * Input:
 * - The first line contains an integer q, the number of queries.
 * - The next q lines each contain two integers, s and e, defining the range.
 *
 * * Output:
 * - For each query, output the count of prime numbers in the specified range.
 *
 * * Constraints:
 * - 1 <= s <= e <= 1,000,000
 * - 1 <= q <= 10^4
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class j04CountPrimesInRange {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the number of queries
        int q = in.nextInt();
        ArrayList<Integer[]> quries = new ArrayList<>();

        // Read each query range and store it in the queries list
        for (int i = 1; i <= q; i++) {
            int s = in.nextInt(); // Start of the range
            int e = in.nextInt(); // End of the range
            quries.add(new Integer[] { s, e });
        }

        // Calculate the number of primes for each query range and print the result
        System.out.println(Arrays.toString(countPrimesInRange(quries)));

        in.close(); // Close the scanner to release resources
    }

    /**
     * Method to calculate the count of prime numbers in given ranges.
     *
     * Time Complexity: O(n + q), where n is the maximum number in the range
     * (1,000,000) and q is the number of queries.
     * Space Complexity: O(n), for the sieve array.
     *
     * @param quries List of integer arrays where each array contains [s, e].
     * @return An array of integers where each value represents the count of primes
     *         in the corresponding range.
     */
    public static int[] countPrimesInRange(ArrayList<Integer[]> quries) {

        // Generate a sieve for prime numbers up to 1,000,000
        int[] seive = getSeive(1000000);

        // Create a prefix sum array of the sieve to count primes efficiently
        int count = 0;
        for (int i = 1; i <= 1000000; i++) {
            count += seive[i]; // Accumulate count of primes
            seive[i] = count; // Store the cumulative count
        }

        // Initialize the output array to store the prime count for each query
        int[] out = new int[quries.size()];

        // Calculate the prime count for each query range using the prefix sum array
        for (int i = 0; i < quries.size(); i++) {
            out[i] = seive[quries.get(i)[1]] - seive[quries.get(i)[0]];
        }

        return out; // Return the results
    }

    /**
     * Method to generate a sieve of Eratosthenes up to a given number n.
     *
     * Time Complexity: O(n log log n), where n is the maximum number in the range.
     * Space Complexity: O(n), for the sieve array.
     *
     * @param n The upper limit for generating prime numbers.
     * @return An array where each index i contains 1 if i is a prime number,
     *         otherwise 0.
     */
    public static int[] getSeive(int n) {
        int[] seive = new int[n + 1]; // Initialize the sieve array

        // Mark 0 and 1 as non-prime
        seive[0] = seive[1] = 0;

        // Assume all numbers from 2 to n are prime initially
        for (int i = 2; i <= n; i++) {
            seive[i] = 1;
        }

        // Use the sieve of Eratosthenes algorithm to mark non-prime numbers
        for (int i = 2; i * i <= n; i++) {
            if (seive[i] == 1) { // If the number is still marked as prime
                for (int j = i * i; j <= n; j += i) { // Mark all multiples of i as non-prime
                    seive[j] = 0;
                }
            }
        }

        return seive; // Return the sieve array
    }
}
