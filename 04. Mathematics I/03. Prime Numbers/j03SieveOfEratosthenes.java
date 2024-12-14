/** 
 * Problem Statement:
 * 
 *     Given an integer `n`, find all the prime numbers less than or equal to `n`.
 *     You can solve this problem using the **Sieve of Eratosthenes**, a classic algorithm to efficiently find all primes up to `n`.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^6).
 * 
 * Output:
 *     - Print all the prime numbers from 2 to `n`.
 * 
 * Example:
 *     Input:
 *     30
 *     Output:
 *     2 3 5 7 11 13 17 19 23 29
 * 
 * Explanation:
 *     - The prime numbers up to 30 are 2, 3, 5, 7, 11, 13, 17, 19, 23, and 29.
 */

import java.util.Scanner;

public class j03SieveOfEratosthenes {

    // Complexity: O(N * log(log N))
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // Read the upper bound for prime numbers
        int n = in.nextInt();

        // Array to store prime flags, default is false (indicating prime)
        boolean[] primes = new boolean[n + 1];

        // Print prime numbers using naive sieve method
        printSieveNive(n, primes);

        // Print prime numbers using efficient sieve method
        printSieveEfficient(n, primes);

        in.close();
    }

    /** 
     * Approach: Naive Sieve of Eratosthenes
     * 
     * Time Complexity: O(N * log(log N)) where N is the input value.
     * This complexity arises from marking multiples of each prime number.
     * 
     * Intuition:
     * - Start from 2 and mark all multiples of each prime as not prime.
     * - For each prime number `i`, mark all numbers `i*2, i*3, ..., i*k` as non-prime.
     * 
     * @param n The upper bound for which we need to find primes.
     * @param primes An array that marks whether a number is prime or not.
     */
    public static void printSieveNive(int n, boolean[] primes) {
        // Mark non-prime numbers starting from 2
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) { // i is prime
                for (int j = 2 * i; j <= n; j += i) { // Mark multiples of i as non-prime
                    primes[j] = true;
                }
            }
        }

        // Print the prime numbers from the sieve
        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println(); // For formatting
    }

    /** 
     * Approach: Efficient Sieve of Eratosthenes
     * 
     * Time Complexity: O(N * log(log N)) where N is the input value.
     * This complexity arises from marking multiples of each prime number, 
     * but in the optimized version, we start marking multiples from `i * i` instead of `2 * i`.
     * 
     * Intuition:
     * - Start from 2, but for each prime number `i`, mark multiples starting from `i * i`.
     * - This is more efficient because any smaller multiple of `i` will have already been marked by smaller primes.
     * 
     * @param n The upper bound for which we need to find primes.
     * @param primes An array that marks whether a number is prime or not.
     */
    public static void printSieveEfficient(int n, boolean[] primes) {
        // Mark non-prime numbers starting from 2
        for (int i = 2; i * i <= n; i++) {
            if (!primes[i]) { // i is prime
                for (int j = i * i; j <= n; j += i) { // Mark multiples of i as non-prime, starting from i*i
                    primes[j] = true;
                }
            }
        }

        // Print the prime numbers from the sieve
        for (int i = 2; i <= n; i++) {
            if (!primes[i]) {
                System.out.print(i + " ");
            }
        }
        System.out.println(); // For formatting
    }
}
