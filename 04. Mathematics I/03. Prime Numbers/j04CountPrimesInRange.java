/*-
 * Problem Statement:
 * 
 *     Given a range of integers for multiple queries, find the number of prime numbers within each specified range.
 * 
 * Input:
 *     - An integer `q` (1 <= q <= 10^4), representing the number of queries.
 *     - `q` pairs of integers where each pair (s, e) represents the start (`s`) and end (`e`) of a range. (1 <= s <= e <= 10^6)
 * 
 * Output:
 *     - An array of integers where each integer represents the number of primes in the range [s, e] for each query.
 * 
 * Example:
 *     Input:
 *     2
 *     1 10
 *     10 20
 *     Output:
 *     [4, 4]
 * 
 *     Explanation:
 *     For the first query, primes between 1 and 10 are [2, 3, 5, 7], so the output is 4.
 *     For the second query, primes between 10 and 20 are [11, 13, 17, 19], so the output is 4.
 */

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class j04CountPrimesInRange {
    public static void main(String args[]) {
        // Reading input
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

    /*-
     * Approach : Using Sieve of Eratosthenes and Prefix Sum Array
     * 
     * Intuition:
     * - First, generate a sieve for prime numbers up to 1,000,000 using the Sieve of Eratosthenes algorithm.
     * - Create a prefix sum array where each entry stores the count of primes up to that index.
     * - For each query, compute the difference between the prefix sum at the end and start of the range to find the prime count.
     * 
     * Time Complexity:
     * - O(n log log n) for the Sieve of Eratosthenes, where n is 10^6.
     * - O(q) for processing each query, where q is the number of queries.
     * 
     * Space Complexity:
     * - O(n) for the sieve and prefix sum array, where n is 10^6.
     * 
     * @param quries The list of queries where each query contains a range [s, e].
     * @return An array of integers representing the number of primes in each range for the queries.
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

    /*-
     * Seive Helper : Sieve of Eratosthenes
     * 
     * Intuition:
     * - Use the Sieve of Eratosthenes algorithm to identify all prime numbers up to n (where n = 10^6).
     * - Mark all multiples of a number as non-prime, starting from 2.
     * 
     * Time Complexity:
     * - O(n log log n), where n is 10^6.
     * 
     * Space Complexity:
     * - O(n) for the sieve array.
     * 
     * @param n The upper limit up to which primes are to be found.
     * @return A sieve array where each index indicates whether the number is prime (1) or not (0).
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
