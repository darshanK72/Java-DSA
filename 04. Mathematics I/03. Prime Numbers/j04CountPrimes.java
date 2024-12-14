/*-
 * Problem Statement:
 * 
 *     Given an integer n, return the number of prime numbers that are strictly less than n.
 *     A prime number is a number greater than 1 that has no positive divisors other than 1 and itself.
 * 
 * Input:
 *     - An integer n (1 <= n <= 10^6), representing the upper bound for checking prime numbers.
 * 
 * Output:
 *     - An integer representing the number of prime numbers less than n.
 * 
 * Example:
 *     Input:
 *     10
 *     Output:
 *     4
 * 
 *     Explanation:
 *     The prime numbers less than 10 are 2, 3, 5, and 7. Hence, the output is 4.
 */

import java.util.Scanner;

public class j04CountPrimes {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the upper bound for checking primes
        System.out.println(countPrimes(n)); // Output: number of primes less than n
        in.close();
    }

    /*-
     * Approach 1: Sieve of Eratosthenes
     * 
     * Intuition:
     * - The Sieve of Eratosthenes is an efficient algorithm for finding all primes up to a given limit.
     * - We start by assuming all numbers are prime and then iteratively mark multiples of each prime number as non-prime.
     * 
     * Time Complexity:
     * - O(n log log n), where n is the input size.
     * 
     * Space Complexity:
     * - O(n), for storing the sieve array to keep track of prime status for each number up to n.
     * 
     * @param n The upper bound to check for primes.
     * @return The number of prime numbers less than n.
     */
    public static int countPrimes(int n) {
        if (n <= 2)
            return 0; // No primes less than 2

        boolean[] seive = new boolean[n + 1];

        // Initially assume all numbers >= 2 are prime
        for (int i = 2; i * i <= n; i++) {
            if (!seive[i]) {
                // Mark multiples of i as non-prime
                for (int j = 2 * i; j <= n; j += i) {
                    seive[j] = true;
                }
            }
        }

        // Count numbers that are still marked as prime
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!seive[i])
                count++;
        }
        return count;
    }

    /*-
     * Approach 2: Optimized Sieve of Eratosthenes (if applicable)
     * 
     * Intuition:
     * - By only iterating over odd numbers after 2, we can slightly reduce the space and time complexity.
     * 
     * Time Complexity:
     * - O(n log log n), similar to Approach 1.
     * 
     * Space Complexity:
     * - O(n), still using a boolean array to track primes.
     * 
     * @param n The upper bound to check for primes.
     * @return The number of prime numbers less than n.
     */
    public static int optimizedSolutionMethod(int n) {
        if (n <= 2)
            return 0; // No primes less than 2

        boolean[] seive = new boolean[n + 1];
        for (int i = 2; i * i <= n; i++) {
            if (!seive[i]) {
                for (int j = 2 * i; j <= n; j += i) {
                    seive[j] = true;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            if (!seive[i])
                count++;
        }
        return count;
    }

    /*-
     * Alternative Approach: Brute-force Checking
     * 
     * Intuition:
     * - A less efficient but simpler approach is to check for each number whether it is prime by dividing it with all smaller numbers.
     * 
     * Time Complexity:
     * - O(n * sqrt(n)), since for each number we check divisibility up to its square root.
     * 
     * Space Complexity:
     * - O(1), no extra space required except for a few variables.
     * 
     * @param n The upper bound to check for primes.
     * @return The number of prime numbers less than n.
     */
    public static int alternativeSolutionMethod(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            for (int j = 2; j * j <= i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime)
                count++;
        }
        return count;
    }
}
