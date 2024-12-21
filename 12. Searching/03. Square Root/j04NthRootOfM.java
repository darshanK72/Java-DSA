/*-
 * Problem Statement:
 * 
 *     Given two integers `n` and `m`, find the `n`th root of `m`. The `n`th root of `m` is a number `x` such that 
 *     `x^n = m`. You need to find the integer `x` that satisfies the condition, or return -1 if no such integer exists.
 * 
 * Input:
 *     - Two integers `n` and `m` (1 <= n <= 10^6, 1 <= m <= 10^9) where `n` is the degree of the root and `m` is the 
 *       number for which the root is to be calculated.
 * 
 * Output:
 *     - An integer `x` such that `x^n = m`, or `-1` if no integer `x` satisfies the condition.
 * 
 * Example:
 *     Input:
 *     3 27
 *     Output:
 *     3
 * 
 *     Explanation:
 *     The cube root of 27 is 3, as `3^3 = 27`.
 */

import java.util.Scanner;

public class j04NthRootOfM {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the degree of the root
        int m = in.nextInt(); // Input: the number for which nth root is to be found
        System.out.print(nthRoot(n, m)); // Output: the nth root of m
        in.close();
    }

    /*-
     * Approach: Binary Search to Find nth Root
     * 
     * Intuition:
     * - The `n`th root of a number `m` is the number `x` such that `x^n = m`. To find `x`, we can use binary search on 
     *   the range from `1` to `m`. The key idea is that as we increase `x`, `x^n` increases as well.
     * - We perform binary search to find the integer `x` such that `x^n == m`. If `x^n < m`, we increase `x`, and if 
     *   `x^n > m`, we decrease `x`. If we find `x` such that `x^n = m`, we return `x`. If no such integer exists, we 
     *   return -1.
     * 
     * Time Complexity:
     * - The binary search runs in `O(log m)` time because we are halving the search space in each step.
     * - The `power` function runs in `O(n)` time for each iteration to compute `x^n`.
     * 
     * Space Complexity:
     * - O(1), since we are only using a constant amount of extra space.
     * 
     * @param n The degree of the root.
     * @param m The number for which the nth root is to be found.
     * @return The integer `x` such that `x^n == m`, or -1 if no such integer exists.
     */
    public static int nthRoot(int n, int m) {
        int s = 1, e = m;
        while (s <= e) {
            int mid = s + (e - s) / 2; // Find the middle value of the current range
            long midPow = power(mid, n, m); // Compute mid^n
            if (midPow == m) {
                return mid; // If mid^n equals m, return mid
            } else if (midPow < m) {
                s = mid + 1; // If mid^n is less than m, move to the right side of the search space
            } else {
                e = mid - 1; // If mid^n is greater than m, move to the left side of the search space
            }
        }
        return -1; // If no such integer exists, return -1
    }

    /*-
     * Helper function to compute base^exp. 
     * We use this to compute mid^n efficiently.
     * 
     * Time Complexity:
     * - O(n), because we are iterating `n` times to compute base^exp.
     * 
     * Space Complexity:
     * - O(1), as we only use a constant amount of space.
     * 
     * @param base The base number.
     * @param exp The exponent.
     * @param max The maximum allowed value to avoid overflow.
     * @return The value of base raised to the power exp.
     */
    private static long power(int base, int exp, int max) {
        long result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
            if (result > max)
                break; // If result exceeds max, stop calculating
        }
        return result;
    }
}
