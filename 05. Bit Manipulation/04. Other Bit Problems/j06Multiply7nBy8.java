/**
 * Problem Statement:
 * 
 *     Given an integer `n`, compute the value of `(7 * n) / 8` without using multiplication, division, or floating-point arithmetic.
 * 
 * Input:
 *     - An integer `n`, where `n` is in the range of valid 32-bit signed integers.
 * 
 * Output:
 *     - An integer representing the result of `(7 * n) / 8`.
 * 
 * Example:
 *     Input:
 *         16
 *     Output:
 *         14
 * 
 *     Explanation:
 *         (7 * 16) / 8 = 112 / 8 = 14.
 * 
 *     Input:
 *         -32
 *     Output:
 *         -28
 * 
 *     Explanation:
 *         (7 * -32) / 8 = -224 / 8 = -28.
 */

import java.util.Scanner;

public class j06Multiply7nBy8 {

    public static void main(String args[]) {
        // Reading input
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(); // Input: the integer `n`

        // Call the solution method
        System.out.println(get7nBy8(n));

        in.close();
    }

    /**
     * Approach: Bitwise Operations for Efficient Computation
     * 
     * Intuition:
     * - `(7 * n)` can be rewritten as `(8 * n - n)`, which simplifies to `(n << 3) - n` using bitwise left shift.
     * - Dividing by 8 is equivalent to a right shift by 3 (`>> 3`).
     * - Combining these steps avoids multiplication and division, relying solely on bitwise operations.
     * 
     * Time Complexity:
     * - O(1), as the computation involves a constant number of operations.
     * 
     * Space Complexity:
     * - O(1), since no extra space is used.
     * 
     * @param n The input integer.
     * @return The result of `(7 * n) / 8`.
     */
    public static int get7nBy8(int n) {
        return ((n << 3) - n) >> 3;
    }
}
