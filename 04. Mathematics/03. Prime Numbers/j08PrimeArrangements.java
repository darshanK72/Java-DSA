/**
 * Problem Statement:
 * 
 *     Given an integer `n`, find the number of prime arrangements for `n` numbers. The prime arrangements are the ways in which the primes can be arranged in the first `n` positions while keeping non-primes in the rest.
 *     The result should be computed modulo 1000000007.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 100).
 * 
 * Output:
 *     - The number of prime arrangements for the integer `n`, modulo 1000000007.
 * 
 * Example:
 *     Input:
 *     5
 *     Output:
 *     12
 * 
 *     Explanation:
 *     The prime numbers up to 5 are [2, 3, 5]. There are 3 prime numbers and 2 non-prime numbers. The number of prime arrangements is 3! * 2! = 12.
 */

import java.util.Scanner;

public class j08PrimeArrangements {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number n for which we need to find prime arrangements
        System.out.println(numPrimeArrangements(n)); // Print the result
        in.close(); // Close the scanner to release resources
    }

    /**
     * Approach: Counting Primes and Using Factorial to Calculate Arrangements
     * 
     * Intuition:
     * - First, we calculate how many numbers from 1 to `n` are prime using the Sieve of Eratosthenes.
     * - Then, we calculate the number of ways to arrange the primes and non-primes using factorials.
     * - The result is the product of the factorial of the number of primes and the factorial of the number of non-primes, modulo 1000000007.
     * 
     * Time Complexity:
     * - O(n log log n) for generating the sieve up to `n`.
     * - O(n) for counting primes and calculating factorials.
     * 
     * Space Complexity:
     * - O(n) for storing the sieve array.
     * 
     * @param n The number for which the prime arrangements need to be calculated.
     * @return The number of prime arrangements modulo 1000000007.
     */
    public static int numPrimeArrangements(int n) {
        boolean[] seive = new boolean[n + 1]; // Sieve array to mark non-prime numbers
        seive[0] = true; // 0 is not prime
        seive[1] = true; // 1 is not prime
        for (int i = 2; i * i <= n; i++) {
            if (!seive[i]) { // If `i` is prime
                for (int j = i * i; j <= n; j += i) { // Mark multiples of `i` as non-prime
                    seive[j] = true;
                }
            }
        }

        // Count the number of primes
        int count = 0;
        for (int i = 0; i <= n; i++) {
            if (!seive[i]) { // If `i` is prime
                count++;
            }
        }

        // Calculate the result as the product of factorial of primes and non-primes
        return (int) (fact(count) * fact(n - count) % 1000000007);
    }

    /**
     * Helper function to calculate the factorial modulo 1000000007.
     * 
     * Intuition:
     * - Calculate the factorial of a number `n` using a loop and taking modulo 1000000007 at each step to avoid overflow.
     * 
     * Time Complexity:
     * - O(n) for calculating the factorial.
     * 
     * Space Complexity:
     * - O(1) for using a few variables for calculations.
     * 
     * @param n The number to calculate the factorial of.
     * @return The factorial of `n` modulo 1000000007.
     */
    public static long fact(int n) {
        long f = 1; // Initialize the factorial to 1
        for (int i = 2; i <= n; i++) {
            f = (f * i) % 1000000007; // Calculate the factorial modulo 1000000007
        }
        return f; // Return the factorial
    }
}
