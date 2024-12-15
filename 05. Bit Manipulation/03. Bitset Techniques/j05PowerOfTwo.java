/**
 * Problem Statement:
 * 
 *     Given an integer `n`, determine if it is a power of two. A number is considered a power of two 
 *     if there exists an integer `x` such that `n = 2^x` for some non-negative integer `x`.
 * 
 * Input:
 *     - An integer `n` (0 <= n <= 10^9), representing the number to check.
 * 
 * Output:
 *     - `true` if `n` is a power of two, `false` otherwise.
 * 
 * Example:
 *     Input:
 *     16
 *     Output:
 *     true
 * 
 *     Explanation:
 *     16 is a power of two since 16 = 2^4.
 * 
 *     Input:
 *     18
 *     Output:
 *     false
 * 
 *     Explanation:
 *     18 is not a power of two.
 * 
 */

import java.util.Scanner;

public class j05PowerOfTwo {
    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the number to check if it is a power of two

        // Output: Checking using two different approaches
        System.out.println(isPowerofTwo(n)); // Using loop-based approach
        System.out.println(isPowerOfTwoBit(n)); // Using bitwise approach

        in.close();
    }

    /**
     * Approach 1: Loop-based Check
     * 
     * Intuition:
     * - If `n` is a power of two, repeatedly divide it by 2 until it becomes 1. If, at any point, 
     *   `n` is not divisible by 2, it is not a power of two.
     * 
     * Time Complexity:
     * - O(log n), since we divide the number by 2 in each iteration.
     * 
     * Space Complexity:
     * - O(1), as no additional space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of two, `false` otherwise.
     */
    public static boolean isPowerofTwo(long n) {
        if (n == 0)
            return false; // 0 is not a power of two
        if (n == 1)
            return true; // 1 is a power of two (2^0)
        while (n > 1) {
            if ((n & 1) == 0) {
                n >>= 1; // Divide by 2 if divisible
            } else {
                return false; // If n is not divisible by 2, it's not a power of two
            }
        }
        return true; // If we reach 1, it's a power of two
    }

    /**
     * Approach 2: Bitwise Check
     * 
     * Intuition:
     * - If `n` is a power of two, it has exactly one bit set to 1 in its binary representation. 
     *   The expression `(n & (n - 1))` will be 0 if `n` has only one bit set.
     * - Special case: if `n` is 0, it is not a power of two.
     * 
     * Time Complexity:
     * - O(1), as the bitwise operations are constant-time.
     * 
     * Space Complexity:
     * - O(1), no additional space is used.
     * 
     * @param n The input number.
     * @return `true` if `n` is a power of two, `false` otherwise.
     */
    public static boolean isPowerOfTwoBit(int n) {
        return n != 0 && (n & (n - 1)) == 0; // Power of two check using bitwise AND
    }
}
