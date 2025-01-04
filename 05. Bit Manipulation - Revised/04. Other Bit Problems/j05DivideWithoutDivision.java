/**
 * Problem Statement:
 * 
 *     Implement integer division without using multiplication, division, or modulus operators. Given two integers `dividend` and `divisor`, return the quotient after dividing the `dividend` by the `divisor`.
 * 
 *     Note:
 *     - If the result exceeds the range of a 32-bit signed integer, return `Integer.MAX_VALUE` or `Integer.MIN_VALUE` as appropriate.
 * 
 * Input:
 *     - Two integers:
 *         - `dividend`: The integer to be divided.
 *         - `divisor`: The integer to divide by.
 * 
 * Output:
 *     - An integer representing the quotient.
 * 
 * Example:
 *     Input:
 *         10 3
 *     Output:
 *         3
 * 
 *     Explanation:
 *         10 divided by 3 equals 3 with a remainder of 1.
 * 
 *     Input:
 *         7 -3
 *     Output:
 *         -2
 * 
 *     Explanation:
 *         7 divided by -3 equals -2 with a remainder of 1.
 */

import java.util.Scanner;
import java.lang.Math;

public class j05DivideWithoutDivision {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the dividend
        int d = in.nextInt(); // Input: the divisor

        // Call the first approach
        System.out.printf("Approach 1 Result: %d\n", divide1(n, d));

        // Call the optimized approach
        System.out.printf("Approach 2 Result: %d\n", divide2(n, d));

        in.close();
    }

    /**
     * Approach 1: Bit Manipulation for Division
     * 
     * Intuition:
     * - Use bitwise shifting to determine the largest multiplier of the divisor that fits within the dividend.
     * - Repeatedly subtract this value from the dividend while accumulating the quotient.
     * 
     * Time Complexity:
     * - O(log^2(n)), where `n` is the absolute value of the dividend. For each subtraction, we perform a logarithmic search using bit-shifting.
     * 
     * Space Complexity:
     * - O(1), since no extra data structures are used.
     * 
     * @param dividend The integer to be divided.
     * @param divisor The integer to divide by.
     * @return The quotient after division.
     */
    public static int divide1(int dividend, int divisor) {
        // Handle edge cases for overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (divisor == 1)
            return dividend;
        if (divisor == -1)
            return -dividend;

        // Determine the sign of the result
        boolean sign = (dividend < 0) ^ (divisor < 0);

        // Work with positive values for simplicity
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long sum = 0;
        // Subtract multiples of divisor using bit-shifting
        while (n >= d) {
            int i = 0;
            while (n >= (d << (i + 1))) {
                i++;
            }
            sum += (1 << i);
            n = n - d * (1 << i);
        }

        // Return the result with appropriate sign
        if (sign) {
            return (sum > Integer.MAX_VALUE) ? Integer.MIN_VALUE : (int) -sum;
        } else {
            return (sum > Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int) sum;
        }
    }

    /**
     * Approach 2: Optimized Division with Bit Manipulation
     * 
     * Intuition:
     * - Similar to the first approach but optimized to handle edge cases and simplify operations.
     * - Use long type to safely perform operations and determine the largest multiple of the divisor quickly.
     * 
     * Time Complexity:
     * - O(log^2(n)), where `n` is the absolute value of the dividend.
     * 
     * Space Complexity:
     * - O(1), since no extra data structures are used.
     * 
     * @param dividend The integer to be divided.
     * @param divisor The integer to divide by.
     * @return The quotient after division.
     */
    public static int divide2(int dividend, int divisor) {
        // Handle edge cases for overflow
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == 1)
            return Integer.MIN_VALUE;

        // Determine the sign of the result
        int sign = (dividend < 0) != (divisor < 0) ? -1 : 1;

        // Work with positive values for simplicity
        long n = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);

        long ans = 0;
        // Subtract multiples of divisor using bit-shifting
        while (n >= d) {
            int i = 0;
            while (n >= (d << (i + 1))) {
                i++;
            }
            ans += (1L << i);
            n -= (d << i);
        }

        // Return the result with appropriate sign
        return sign * (int) ans;
    }
}
