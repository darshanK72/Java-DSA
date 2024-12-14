/*-
 * Problem Statement:
 * 
 *     Given an integer `n`, find the `n`th prime number. The prime numbers are the numbers that are greater than 1 and are divisible only by 1 and themselves.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6), representing the position of the prime number to find.
 * 
 * Output:
 *     - The `n`th prime number.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     11
 * 
 *     Explanation:
 *     The first five prime numbers are [2, 3, 5, 7, 11], so the 5th prime number is 11.
 */

import java.util.Scanner;

public class j06NthPrimeNumber {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the n-th prime number to find
        System.out.println(findNthPrime(n)); // Print the result
        in.close(); // Close the scanner to release resources
    }

    /*-
     * Approach: Using Sieve of Eratosthenes to Find the nth Prime Number
     * 
     * Intuition:
     * - We generate a sieve up to a large number (1,000,000) using the Sieve of Eratosthenes algorithm to mark prime numbers.
     * - We count the number of primes as we go through the sieve and return the `n`th prime number once it is found.
     * 
     * Time Complexity:
     * - O(n log log n) for generating the sieve up to 1,000,000.
     * - O(n) for counting primes up to the `n`th prime.
     * 
     * Space Complexity:
     * - O(n) for the sieve array, where n is 1,000,000.
     * 
     * @param n The position of the prime number to find.
     * @return The `n`th prime number.
     */
    public static int findNthPrime(int n) {
        if (n < 1)
            return -1; // If n is less than 1, return -1 as it's an invalid request
        boolean[] seive = new boolean[1000000]; // Sieve array to mark non-prime numbers
        for (int i = 2; i * i < 1000000; i++) {
            if (!seive[i]) { // If `i` is prime
                for (int j = i * i; j < 1000000; j += i) { // Mark all multiples of `i` as non-prime
                    seive[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i <= 1000000; i++) {
            if (!seive[i])
                count++; // If `i` is prime, increment the count
            if (count == n && !seive[i])
                return i; // Return the `n`th prime when found
        }
        return -1; // Return -1 if no `n`th prime is found within the limit
    }
}
