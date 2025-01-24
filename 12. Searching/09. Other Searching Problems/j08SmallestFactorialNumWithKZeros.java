/**
 * Problem Statement:
 * 
 *     Find the smallest number `x` such that the factorial of `x` has at least `n` trailing zeros.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^5), representing the desired number of trailing zeros.
 * 
 * Output:
 *     - An integer `x` such that the factorial of `x` has at least `n` trailing zeros.
 *       If multiple such values exist, return the smallest one.
 * 
 * Example:
 *     Input:
 *     6
 * 
 *     Output:
 *     25
 * 
 *     Explanation:
 *     - Factorial of 25 is 25! = 25 × 24 × ... × 1, and it contains exactly 6 trailing zeros.
 *     - Factorials of numbers less than 25 have fewer than 6 trailing zeros.
 */

import java.util.Scanner;

public class j08SmallestFactorialNumWithKZeros {

    public static void main(String[] args) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the desired number of trailing zeros
        System.out.println("Smallest number with at least " + n + " trailing zeros in its factorial: " + findNum(n));
        in.close();
    }

    /**
     * Approach: Binary Search for Smallest Number with At Least `n` Trailing Zeros
     * 
     * Intuition:
     * - The number of trailing zeros in a factorial is determined by the number of times `5`
     *   is a factor in the numbers from `1` to `x`.
     * - We calculate the trailing zeros for a given number `x` using a helper function.
     * - Since trailing zeros increase monotonically with `x`, we can use binary search
     *   to find the smallest `x` such that the factorial of `x` has at least `n` trailing zeros.
     * 
     * Steps:
     * 1. Define the search space as `[0, n * 5]` (since every `5` contributes to a trailing zero).
     * 2. Perform binary search:
     *    - Compute the midpoint `mid` and calculate the number of trailing zeros for `mid`.
     *    - If the trailing zeros are greater than or equal to `n`, reduce the search space to `[s, mid - 1]`.
     *    - Otherwise, increase the search space to `[mid + 1, e]`.
     * 3. Return the smallest `x` that satisfies the condition.
     * 
     * Time Complexity:
     * - O(log(n * 5) * log(x)), where `x` is the midpoint of the search space, 
     *   and `log(x)` accounts for the division in calculating trailing zeros.
     * 
     * Space Complexity:
     * - O(1), as we use only a constant amount of additional space.
     * 
     * @param n The desired number of trailing zeros.
     * @return The smallest number whose factorial contains at least `n` trailing zeros.
     */
    public static int findNum(int n) {
        int s = 0; // Start of the search space
        int e = n * 5; // End of the search space (upper bound)

        while (s <= e) {
            int mid = s + (e - s) / 2; // Calculate the midpoint
            if (trailingZeros(mid) >= n) {
                e = mid - 1; // Narrow the search to the left half
            } else {
                s = mid + 1; // Narrow the search to the right half
            }
        }

        return s; // The smallest number whose factorial has at least `n` trailing zeros
    }

    /**
     * Helper Function: Calculate Trailing Zeros in Factorial
     * 
     * Intuition:
     * - Trailing zeros in a factorial are caused by factors of `10`, which are products of `2` and `5`.
     * - Since there are typically more factors of `2` than `5` in a factorial, the number of trailing zeros
     *   is determined by the number of factors of `5`.
     * - To count factors of `5` in numbers from `1` to `x`, repeatedly divide `x` by powers of `5`
     *   (i.e., `5`, `25`, `125`, ...) and sum the quotients.
     * 
     * Time Complexity:
     * - O(log(x)), where `x` is the input number, due to repeated division by `5`.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return The number of trailing zeros in `n!`.
     */
    public static int trailingZeros(int n) {
        int count = 0; // Initialize the count of trailing zeros
        for (int i = 5; (n / i) > 0; i *= 5) {
            count += (n / i); // Add the count of factors of `5`
        }
        return count;
    }
}
