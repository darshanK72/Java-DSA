/**
 * Problem Statement:
 * 
 *     Given an integer `n`, calculate the product of all its factors (divisors), modulo 10^9 + 7.
 * 
 * Input:
 *     - A single integer `n`.
 * 
 * Output:
 *     - A single integer representing the product of all factors of `n` modulo 10^9 + 7.
 * 
 * Example:
 *     Input:
 *     12
 *     Output:
 *     Product of factors of 12 is 1728
 *     Explanation: Factors of 12 are [1, 2, 3, 4, 6, 12]. Their product is 1 * 2 * 3 * 4 * 6 * 12 = 1728.
 * 
 * Constraints:
 *     - `n` is a positive integer.
 * 
 * Notes:
 *     - The result is computed modulo 10^9 + 7 to avoid overflow for large numbers.
 */

import java.util.Scanner;

public class j15ProductOfFactors {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer n

        // Calculate and display the product of factors
        System.out.printf("Product of factors of %d is %d\n", n, factorProduct(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Efficient Calculation of Product of Factors
     * 
     * Intuition:
     * - Factors of a number `n` come in pairs. For every factor `i` found, there is a corresponding factor `n / i`.
     * - To find the product of factors efficiently, iterate only up to `sqrt(n)`, multiplying each factor and its pair.
     * - Special care is taken for perfect squares, where one of the factors (sqrt(n)) is repeated only once.
     * - Since the product of factors can grow extremely large, we use modulo 10^9 + 7 to handle large values.
     * - If the number `n` has `k` factors, the product of factors is `n^(k/2)`.
     * 
     * Algorithm:
     * - Iterate from 1 to sqrt(n).
     * - For each `i`, check if it divides `n` without a remainder (`n % i == 0`).
     * - If `i` is a factor, multiply both `i` and `n / i` into the product. If `i` equals `n / i` (perfect square), multiply it only once.
     * - Return the product modulo 10^9 + 7.
     * 
     * Time Complexity:
     * - O(sqrt(n)), as we only iterate up to sqrt(n) and perform constant-time operations in each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables.
     * 
     * @param N The input number.
     * @return The product of all factors of `N` modulo 10^9 + 7.
     */
    public static int factorProduct(int N) {
        final int MOD = 1000000007; // Modulo value to avoid overflow
        long product = 1; // Variable to store the product of factors

        // Loop through potential divisors from 1 to sqrt(N)
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) { // Check if i is a factor
                if (N / i != i) {
                    // If i and N / i are distinct, multiply both into the product
                    product = (product * i) % MOD;
                    product = (product * (N / i)) % MOD;
                } else {
                    // If i is the square root of N, multiply it only once
                    product = (product * i) % MOD;
                }
            }
        }

        return (int) product; // Return the result modulo 10^9 + 7
    }
}
