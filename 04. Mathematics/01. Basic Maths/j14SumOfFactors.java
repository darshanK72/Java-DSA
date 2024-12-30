/**
 * Problem Statement:
 * 
 *     Given an integer `n`, calculate the sum of all its factors (divisors).
 * 
 * Input:
 *     - A single integer `n`.
 * 
 * Output:
 *     - A single integer representing the sum of all factors of `n`.
 * 
 * Example:
 *     Input:
 *     12
 *     Output:
 *     Sum of factors of 12 is 28
 *     Explanation: Factors of 12 are [1, 2, 3, 4, 6, 12]. Their sum is 28.
 * 
 * Constraints:
 *     - `n` is a positive integer.
 */

import java.util.Scanner;

public class j14SumOfFactors {

    public static void main(String args[]) {
        // Reading the input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer n

        // Calculate and display the sum of factors
        System.out.printf("Sum of factors of %d is %d\n", n, factorSum(n));

        // Close the scanner
        in.close();
    }

    /**
     * Approach: Efficient Calculation of Sum of Factors
     * 
     * Intuition:
     * - Factors of a number `n` come in pairs. For every factor `i` found, there is a corresponding factor `n / i`.
     * - To find the sum of factors efficiently, iterate only up to `sqrt(n)`, adding each factor and its corresponding pair.
     * - Special care is taken for perfect squares, where one of the factors (sqrt(n)) is repeated only once.
     * 
     * Algorithm:
     * - Iterate from 1 to sqrt(n).
     * - For each `i`, check if it divides `n` without a remainder (`n % i == 0`).
     * - If `i` is a factor, add both `i` and `n / i` to the sum. If `i` is equal to `n / i` (perfect square), add it only once.
     * 
     * Time Complexity:
     * - O(sqrt(n)), as we only iterate up to sqrt(n) and perform constant-time operations in each iteration.
     * 
     * Space Complexity:
     * - O(1), as we only use a few variables.
     * 
     * @param N The input number.
     * @return The sum of all factors of `N`.
     */
    public static long factorSum(int N) {
        long sum = 0; // Variable to store the sum of factors

        // Loop through potential divisors from 1 to sqrt(N)
        for (int i = 1; i * i <= N; i++) {
            if (N % i == 0) { // Check if i is a factor
                if (N / i != i) {
                    // If i and N / i are distinct, add both to the sum
                    sum += i;
                    sum += N / i;
                } else {
                    // If i is the square root of N, add it only once
                    sum += i;
                }
            }
        }

        return sum; // Return the total sum of factors
    }
}
