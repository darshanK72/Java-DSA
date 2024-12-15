/**
 * Problem Statement:
 * 
 *     Given an integer `n`, find the highest power of two less than or equal to `n`. 
 *     The result should be the largest power of two that is less than or equal to the given number.
 * 
 * Input:
 *     - An integer `n` (1 <= n <= 10^9), representing the number to find the highest power of two for.
 * 
 * Output:
 *     - The highest power of two that is less than or equal to `n`.
 * 
 * Example:
 *     Input:
 *     20
 *     Output:
 *     16
 * 
 *     Explanation:
 *     The highest power of two less than or equal to 20 is 16 (2^4).
 * 
 *     Input:
 *     100
 *     Output:
 *     64
 * 
 *     Explanation:
 *     The highest power of two less than or equal to 100 is 64 (2^6).
 * 
 */

import java.util.Scanner;

public class j06HighestPowerOfTwo {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to find the highest power of two

        // Output: Checking using three different approaches
        System.out.println(highestPowerOfTwo1(n)); // Using bitwise AND approach
        System.out.println(highestPowerOfTwo2(n)); // Using bit shifting approach
        System.out.println(highestPowerOfTwo3(n)); // Using logarithmic approach

        in.close();
    }

    /**
     * Approach 1: Bitwise AND Approach
     * 
     * Intuition:
     * - This approach works by iterating from `n` down to 1 and checking if the current number 
     *   is a power of two. The first number found to be a power of two is the highest power 
     *   of two less than or equal to `n`.
     * - We use the property that a number `x` is a power of two if and only if `x & (x - 1) == 0`.
     * 
     * Time Complexity:
     * - O(n), as we iterate down from `n` to 1.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used.
     * 
     * @param n The input number.
     * @return The highest power of two less than or equal to `n`.
     */
    static int highestPowerOfTwo1(int n) {
        int res = 0;
        for (int i = n; i >= 1; i--) {
            // If i is a power of 2
            if ((i & (i - 1)) == 0) {
                res = i;
                break;
            }
        }
        return res;
    }

    /**
     * Approach 2: Bit Shifting Approach
     * 
     * Intuition:
     * - This approach works by starting with the smallest power of two (`1`), and iteratively 
     *   left-shifting until the value exceeds `n`. The last value before it exceeds `n` is the 
     *   highest power of two less than or equal to `n`.
     * 
     * Time Complexity:
     * - O(log n), since we left-shift the number, doubling it each time.
     * 
     * Space Complexity:
     * - O(1), as only a few variables are used.
     * 
     * @param n The input number.
     * @return The highest power of two less than or equal to `n`.
     */
    static int highestPowerOfTwo2(int n) {
        // Invalid input
        if (n < 1)
            return 0;

        int res = 1;

        // Try all powers starting from 2^1
        for (int i = 0; i < 8 * Integer.BYTES; i++) {
            int curr = 1 << i;

            // If current power is more than n, break
            if (curr > n)
                break;

            res = curr;
        }
        return res;
    }

    /**
     * Approach 3: Logarithmic Approach
     * 
     * Intuition:
     * - This approach calculates the highest power of two less than or equal to `n` by finding the integer part of the logarithm of `n` base 2, and then computing `2^p` where `p` is the result of the logarithm.
     * - This method utilizes the property that `2^p` is the highest power of two that is less than or equal to `n`.
     * 
     * Time Complexity:
     * - O(1), since logarithmic operations and exponentiation are constant-time.
     * 
     * Space Complexity:
     * - O(1), no additional space is used.
     * 
     * @param n The input number.
     * @return The highest power of two less than or equal to `n`.
     */
    public static int highestPowerOfTwo3(int n) {
        int p = (int) (Math.log(n) / Math.log(2)); // Logarithm to find the exponent
        return (int) Math.pow(2, p); // Return 2^p
    }
}
