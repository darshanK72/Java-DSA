/**
 * Problem Statement:
 * 
 *     Given an integer `n`, determine whether `n` has exactly three divisors.
 *     A number `n` has exactly three divisors if there are only three integers 
 *     `i` such that `1 <= i <= n` and `n % i == 0`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9).
 * 
 * Output:
 *     - Return `true` if `n` has exactly three divisors, otherwise `false`.
 * 
 * Example:
 *     Input:
 *     4
 *     Output:
 *     true
 *     Explanation:
 *     - Factors of 4 are [1, 2, 4]. Since there are exactly three factors, the output is `true`.
 */

import java.util.Scanner;

public class j17ThreeDivisors {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer n

        // Call your solution
        System.out.printf("Your Solution: Does %d have exactly three factors? %s\n", n, hasOnly3FactorsBrute(n));

        // Call the optimized solution
        System.out.printf("Optimized Solution: Does %d have exactly three factors? %s\n", n, hasOnly3Factors(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach 1: Brute Force Approach to Count Factors
     * 
     * Intuition:
     * - Count all divisors of `n` by iterating from `1` to `n`.
     * - If the divisor count exceeds 3 during the process, return `false`.
     * 
     * Time Complexity:
     * - O(n), as we iterate from 1 to n to count divisors.
     * 
     * Space Complexity:
     * - O(1), as no extra space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` has exactly three factors, otherwise `false`.
     */
    public static boolean hasOnly3FactorsBrute(int n) {
        int c = 0; // Count of divisors
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) { // Check if i is a divisor of n
                c++;
            }
            if (c > 3) { // Early exit if divisor count exceeds 3
                return false;
            }
        }
        return c == 3; // Return true if exactly 3 divisors
    }

    /**
     * Approach 2 : Efficient Check for Exactly Three Divisors
     * 
     * Intuition:
     * - A number `n` has exactly three factors if and only if:
     *   1. `n` is a perfect square (e.g., `n = p^2` for some integer `p`).
     *   2. The square root of `n` (i.e., `p`) is a prime number.
     * - This is because the factors of a perfect square `p^2` are `[1, p, p^2]` when `p` is prime.
     * 
     * Algorithm:
     * 1. Check if `n` is a perfect square.
     * 2. If not, return `false`.
     * 3. If it is a perfect square, check if the square root is a prime number.
     * 
     * Time Complexity:
     * - O(√n) for the prime check.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used.
     * 
     * @param n The input number.
     * @return `true` if `n` has exactly three factors, otherwise `false`.
     */
    public static boolean hasOnly3Factors(int n) {
        // Check if n is a perfect square
        int sqrt = (int) Math.sqrt(n);
        if (sqrt * sqrt != n) {
            return false; // Not a perfect square
        }

        // Check if the square root is prime
        return isPrime(sqrt);
    }

    /**
     * Helper Method: Check if a Number is Prime
     * 
     * Intuition:
     * - A number is prime if it has no divisors other than `1` and itself.
     * - To check primality, iterate up to the square root of the number.
     * 
     * Time Complexity:
     * - O(√n), where `n` is the input number.
     * 
     * Space Complexity:
     * - O(1), as only integer variables are used.
     * 
     * @param num The number to check.
     * @return `true` if the number is prime, otherwise `false`.
     */
    public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // Numbers <= 1 are not prime
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false; // Divisor found
            }
        }
        return true; // No divisors found, number is prime
    }
}
